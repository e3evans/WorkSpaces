/*
 * Created on 2008-3-17
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;

/**
 * @author alexander.todorov
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OtherSiteDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8898651229163934593L;
	
	private long id;
	private long siteId;
	private String carreerHarmonyTestURL;
	private String externalAdvertURL;
	
	public OtherSiteDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return Returns the carreerHarmonyTestURL.
	 */
	public String getCarreerHarmonyTestURL() {
		return carreerHarmonyTestURL;
	}
	/**
	 * @return Returns the id.
	 */
	public long getId() {
		return id;
	}
	/**
	 * @return Returns the site_id.
	 */
	public long getSiteId() {
		return siteId;
	}
	/**
	 * @param carreerHarmonyTestURL The carreerHarmonyTestURL to set.
	 */
	public void setCarreerHarmonyTestURL(String carreerHarmonyTestURL) {
		this.carreerHarmonyTestURL = carreerHarmonyTestURL;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @param site_id The site_id to set.
	 */
	public void setSiteId(long site_id) {
		this.siteId = site_id;
	}

	public String getExternalAdvertURL() {
		return externalAdvertURL;
	}

	public void setExternalAdvertURL(String externalAdvertURL) {
		this.externalAdvertURL = externalAdvertURL;
	}
}
