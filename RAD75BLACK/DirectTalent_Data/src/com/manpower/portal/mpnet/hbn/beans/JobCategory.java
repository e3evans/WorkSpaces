/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Vlado
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class JobCategory implements Serializable {

	private static final long serialVersionUID = 8392233179817386387L;

	private long id;

	private long siteId;

	private String language = null;

	private String code = null;

	private String description = null;

	private Map jobCategoryNames = null;

	public JobCategory() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Map getJobCategoryNames() {
		return jobCategoryNames;
	}

	public void setJobCategoryNames(Map jobCategoryNames) {
		this.jobCategoryNames = jobCategoryNames;
	}

	public long getSiteId() {
		return siteId;
	}

	public void setSiteId(long siteId) {
		this.siteId = siteId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
