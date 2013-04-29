/*
 * Created on 2006-8-25
 *
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;
import java.util.Map;

/**
 * @author alexander.todorov
 *
 * Persistent class for table Job Titles
 */
public class JobTitle implements Serializable{

	private static final long serialVersionUID =     8392233179817381307L;
	
	private long	id;
	private long	siteId;
	
	private String 	language			= null,
					code				= null,
					description			= null;
	
	private Map		jobTitleNames			= null;
	
	public JobTitle() {
		super();
	}
	
	/**
	 * @return Returns the id.
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return Returns the language.
	 */
	public String getLanguage() {
		return language;
	}
	/**
	 * @param language The language to set.
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	/**
	 * @return Returns the region.
	 */
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return Returns the locationNames.
	 */
	public Map getJobTitleNames() {
		return jobTitleNames;
	}
	/**
	 * @param locationNames The locationNames to set.
	 */
	public void setJobTitleNames(Map locationNames) {
		this.jobTitleNames = locationNames;
	}
	/**
	 * @return Returns the siteId.
	 */
	public long getSiteId() {
		return siteId;
	}
	/**
	 * @param siteId The siteId to set.
	 */
	public void setSiteId(long siteId) {
		this.siteId = siteId;
	}
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
