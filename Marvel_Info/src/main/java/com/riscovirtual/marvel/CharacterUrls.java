package com.riscovirtual.marvel;

import com.fasterxml.jackson.annotation.JsonView;

public class CharacterUrls {
	@JsonView(View.Public.class)
	private String type;
	
	@JsonView(View.Public.class)
	private String url;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
