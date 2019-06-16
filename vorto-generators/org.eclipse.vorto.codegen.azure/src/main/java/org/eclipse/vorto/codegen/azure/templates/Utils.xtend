package org.eclipse.vorto.codegen.azure.templates

import org.eclipse.vorto.core.api.model.model.Model

class Utils {
	
	def static String createURI(Model model) {
		var uri = new StringBuilder("http://")
		var namespaceParts = model.namespace.split("\\.");
		namespaceParts = namespaceParts.reverse
		uri.append(namespaceParts.join("."));
		uri.append("/").append(model.name).append("/").append(model.version)
		return uri.toString
	}
}