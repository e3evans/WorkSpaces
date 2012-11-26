package com.asponte.portal.designer.wcm;

import com.ibm.workplace.wcm.api.DocumentId;

public class LrpConfig {
	private String contentType;
	private String contentContextType;
	private String contentIdr;
	private String componentIdr;
	private String categories;
	private String siteAreas;
	private String broadcastsTo;
	private String listensTo;
	private String portletTitle;
	public String getPortletTitle() {
		return portletTitle;
	}
	public void setPortletTitle(String portletTitle) {
		this.portletTitle = portletTitle;
	}
	public LrpConfig(){}
	public String getBroadcastsTo() {
		return broadcastsTo;
	}
	public void setBroadcastsTo(String broadcastsTo) {
		this.broadcastsTo = broadcastsTo;
	}
	public String getListensTo() {
		return listensTo;
	}
	public void setListensTo(String listensTo) {
		this.listensTo = listensTo;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getContentContextType() {
		return contentContextType;
	}
	public void setContentContextType(String contentContextType) {
		this.contentContextType = contentContextType;
	}
	public String getContentIdr() {
		return contentIdr;
	}
	public void setContentIdr(DocumentId contentIdr) {
		this.contentIdr = Utils.getIdrFromId(contentIdr);
	}
	public String getComponentIdr() {
		return componentIdr;
	}
	public void setComponentIdr(DocumentId componentIdr) {
		this.componentIdr = Utils.getIdrFromId(componentIdr);
	}
	
	public String getCategories() {
		return categories;
	}
	public void setCategories(String[] categories) {
		this.categories = com.asponte.commons.portal.Utils.join(categories,",");
	}
	public String getSiteAreas() {
		return siteAreas;
	}
	public void setSiteAreas(String[] siteAreas) {
		this.siteAreas = com.asponte.commons.portal.Utils.join(siteAreas,",");;
	}
	public String toString(){
		return "{ contentType: '"+this.contentType+"',"+
			   " contentContextType: '"+this.contentContextType+"',"+
			   " contentIdr: '"+this.contentIdr+"',"+
			   " componentIdr: '"+this.componentIdr+"',"+
			   " categories: '"+this.categories+"',"+
			   " siteAreas: '"+this.siteAreas+"',"+
			   " broadcastsTo: '"+this.broadcastsTo+"',"+
			   " listensTo: '"+this.listensTo+"' }";		   
	}
}
