package com.riscovirtual.marvel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacterInfo {

	@JsonView(View.Public.class)
	private int id;

	@JsonView(View.Public.class)
	private String name;

	@JsonView(View.Public.class)
	private String description;

	@JsonView(View.Public.class)
	private CharacterThumbnail thumbnail;

	@JsonView(View.Private.class)
	private CharacterUrls[] urls;

	public CharacterUrls[] getUrls() {
		return urls;
	}

	public void setUrls(CharacterUrls[] urls) {
		this.urls = urls;
	}

	public CharacterThumbnail getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(CharacterThumbnail thumbnail) {
		this.thumbnail = thumbnail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "CharacterInfo{" + "id=" + id + ", name=" + name + ", description=" + description + '}';
	}

}
