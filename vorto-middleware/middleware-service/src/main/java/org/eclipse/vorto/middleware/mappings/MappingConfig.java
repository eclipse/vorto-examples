package org.eclipse.vorto.middleware.mappings;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

@Entity
public class MappingConfig {

	@Id
	private String id;
	
	@NotNull
	@Lob
	private String content;

	public MappingConfig(String id, String content) {
		super();
		this.id = id;
		this.content = content;
	}
	
	public MappingConfig() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
