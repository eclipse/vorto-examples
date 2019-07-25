package org.eclipse.vorto.example.mapping.internal;

public class DeserializerFactory {
	public static Deserializer getDeserializer(String contentType) {
		switch (contentType) {
		case MimeTypes.Text.CSV:
			return new CsvDeserializer();
		case MimeTypes.Application.JSON:
			return new JsonDeserializer();
		default:
			return null;
		}
	}
}
