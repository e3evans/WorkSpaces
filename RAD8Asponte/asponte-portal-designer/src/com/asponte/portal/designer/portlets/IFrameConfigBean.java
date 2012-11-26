package com.asponte.portal.designer.portlets;

public class IFrameConfigBean {
	
	private String url;
	private String width;
	private String height;
	private String scrolling;
	
	public IFrameConfigBean(){}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getScrolling() {
		return scrolling;
	}

	public void setScrolling(String scrolling) {
		this.scrolling = scrolling;
	}
	
}
