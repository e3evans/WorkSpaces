package com.asponte.vmmportlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * A sample Java bean that stores portlet instance data in portlet session.
 *
 */
public class VMMPortletSessionBean {
	
	private String userName = "";
	private String password = "";
	private String searchBase = "";
	private String vmmService = "";
	private String rawtext = "";
	
	private List<HashMap> entities=new ArrayList<HashMap>();
	
	
	
	public List<HashMap> getEntities() {
		return entities;
	}

	public void setEntities(List<HashMap> entities) {
		this.entities = entities;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSearchBase() {
		return searchBase;
	}

	public void setSearchBase(String searchBase) {
		this.searchBase = searchBase;
	}

	public String getVmmService() {
		return vmmService;
	}

	public void setVmmService(String vmmService) {
		this.vmmService = vmmService;
	}

	/**
	 * Last text for the text form
	 */
	private String formText = "";

	/**
	 * Set last text for the text form.
	 * 
	 * @param formText last text for the text form.
	 */
	public void setFormText(String formText) {
		this.formText = formText;
	}

	/**
	 * Get last text for the text form.
	 * 
	 * @return last text for the text form
	 */
	public String getFormText() {
		return this.formText;
	}

	public String getRawtext() {
		return rawtext;
	}

	public void setRawtext(String rawtext) {
		this.rawtext = rawtext;
	}

}
