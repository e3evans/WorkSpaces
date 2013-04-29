package com.manpower.portal.portlet.wcmutility.beans;

import com.ibm.workplace.wcm.api.Content;
import com.ibm.workplace.wcm.api.DocumentId;
import com.ibm.workplace.wcm.api.Site;
import com.ibm.workplace.wcm.api.SiteArea;

public class WcmSiteAreaBean {

	private DocumentId siteAreaId;
	private String siteName;
	private String classname;
	private String type;
	private int allChildrenCount;
	private int childrenCount;
//	private SiteArea siteAreaBean;
//	private Site siteBean;
//	private Content contentBean;
	
	public static final String SITE_AREA_CLASSNAME = "WCM_SiteArea";
	public static final String SITE_CLASS_NAME ="WCM_Site";
	public static final String SITE_CONTENT_NAME = "WCM_Content";
	
	public WcmSiteAreaBean(){}
	
	public WcmSiteAreaBean(Object object){
		setClassName(object);
		try{
			if (getClassname().equals(SITE_AREA_CLASSNAME)){
				SiteArea bean = (SiteArea)object;
				this.siteAreaId=bean.getId();
				this.siteName=bean.getId().getName();
				this.allChildrenCount=bean.getAllChildren().getCount();
				this.childrenCount=bean.getChildren().getCount();
				this.type=bean.getClass().getName();
			}else if (getClassname().equals(SITE_CLASS_NAME)){
				Site bean = (Site)object;
				this.siteAreaId=bean.getId();
				this.siteName=bean.getName();
				this.allChildrenCount=bean.getAllChildren().getCount();
				this.childrenCount=bean.getChildren().getCount();
				this.type=bean.getClass().getName();
			}else if (getClassname().equals(SITE_CONTENT_NAME)){
				Content bean = (Content)object;
				this.siteAreaId=bean.getId();
				this.siteName=bean.getName();
				this.type=bean.getClass().getName();
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private void setClassName(Object obj){
		String temp = obj.getClass().getName();
		String [] classnamepath = temp.split("\\.");
		this.classname=classnamepath[classnamepath.length-1];
	}
	
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public int getAllChildrenCount() {
		return allChildrenCount;
	}
	public void setAllChildrenCount(int allChildrenCount) {
		this.allChildrenCount = allChildrenCount;
	}
	public int getChildrenCount() {
		return childrenCount;
	}
	public void setChildrenCount(int childrenCount) {
		this.childrenCount = childrenCount;
	}
	public DocumentId getSiteAreaId() {
		return siteAreaId;
	}



	public void setSiteAreaId(DocumentId siteAreaId) {
		this.siteAreaId = siteAreaId;
	}



	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	
}
