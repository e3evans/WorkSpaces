package com.manpower.hellostruts2portlet.domain;

public class Bookmark {
	
	private String name;
	private String url;
	
	public Bookmark(String name,String url){
		this.name=name;
		this.url=url;	
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	

}
