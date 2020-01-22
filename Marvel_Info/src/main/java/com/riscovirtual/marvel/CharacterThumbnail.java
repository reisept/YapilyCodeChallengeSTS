package com.riscovirtual.marvel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacterThumbnail {
	
	@JsonView(View.Public.class)
	private String path;
	
	@JsonView(View.Public.class)
	private String extension;
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getExtension() {
		return extension;
	}
	
	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	@Override
    public String toString() {
        return "CharacterThumbnail{" +
                "path='" + path + '\'' +
                ", extension=" + extension +
                '}';
    }
}
