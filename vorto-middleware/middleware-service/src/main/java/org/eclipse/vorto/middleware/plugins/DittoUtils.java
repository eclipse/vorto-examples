package org.eclipse.vorto.middleware.plugins;

import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class DittoUtils {

	public static String getDittoNamespaceFromDeviceId(final String deviceId) {
		Pattern pattern = Pattern.compile("(.*):.*");
		Matcher matcher = pattern.matcher(deviceId);

		if (matcher.matches()) {
			return matcher.group(1);
		}

		throw new NoSuchElementException(
				"No namespace was provided for your device. Please use the namespace header or prefix your deviceId with the according namespace");
	}
	
	public static String getDittoSuffixFromDeviceId(final String deviceId) {
		Pattern pattern = Pattern.compile(".*:(.*)");
		Matcher matcher = pattern.matcher(deviceId);

		if (matcher.matches()) {
			return matcher.group(1);
		}

		throw new NoSuchElementException("The deviceId does not comply to Eclipse Ditto ThingID convention.");
	}

}
