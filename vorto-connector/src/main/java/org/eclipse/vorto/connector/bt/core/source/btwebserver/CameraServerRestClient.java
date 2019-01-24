/**
 * Copyright (c) 2019 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.vorto.connector.bt.core.source.btwebserver;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Component
public class CameraServerRestClient {

	private Gson gson = new Gson();

	@Autowired
	private CloseableHttpClient httpClient;

	public CompletableFuture<String> getData(final String endpointUrl, final MediaType mediaType) {
		return CompletableFuture.supplyAsync(() -> {
			HttpGet request = new HttpGet(endpointUrl);
			request.addHeader("Accept", mediaType.toString());
			request.addHeader("Accept-Language", "en-US");
			try {
				return httpClient.execute(request, response -> {
					int statusCode = response.getStatusLine().getStatusCode();
					if (statusCode >= 200 && statusCode <= 299) {
						if (MediaType.IMAGE_JPEG.equals(mediaType)) {
							String image = convertFileAttachmentToBase64String(response);
							String json = "{\"data\": \"" + image + "\",\"mediaType\":\""+mediaType.toString()+"\"}";
							return json;
						} else if (MediaType.APPLICATION_XML.equals(mediaType)) {
							final String xmlResponse = EntityUtils.toString(response.getEntity(), "UTF-8");
							try {
								JSONObject xmlJSONObj = XML.toJSONObject(xmlResponse);
								return xmlJSONObj.toString();
							} catch (Exception e) {
								throw new RuntimeException(String.format("%d - %s", statusCode, getErrorString(response)));
							}
						} else {
							return EntityUtils.toString(response.getEntity(), "UTF-8");
						}
					} else {
						throw new RuntimeException(String.format("%d - %s", statusCode, getErrorString(response)));
					}
				});
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

	private String convertFileAttachmentToBase64String(HttpResponse response) {
		try {
			BufferedImage image = ImageIO.read(response.getEntity().getContent());
			ByteArrayOutputStream compressedImage = new ByteArrayOutputStream();

			Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
			ImageWriter writer = (ImageWriter) writers.next();

			ImageOutputStream ios = ImageIO.createImageOutputStream(compressedImage);
			writer.setOutput(ios);

			ImageWriteParam param = writer.getDefaultWriteParam();

			param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			param.setSourceSubsampling(8, 8, 0, 0);
			writer.write(null, new IIOImage(image, null, null), param);

			return Base64.getEncoder().encodeToString(compressedImage.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("Error in converting response to string", e);
		}
	}

	private String getErrorString(HttpResponse response) {
		Map<String, Object> errorMap = new HashMap<String, Object>();
		Map<String, Object> responseHeader = new HashMap<String, Object>();

		for (Header header : response.getAllHeaders()) {
			responseHeader.put(header.getName(), header.getValue());
		}
		errorMap.put("header", responseHeader);
		try {
			errorMap.put("body", gson.fromJson(IOUtils.toString(response.getEntity().getContent()),
					new TypeToken<Map<String, String>>() {
					}.getType()));
		} catch (Exception e) {
		}

		return gson.toJson(errorMap);
	}
}
