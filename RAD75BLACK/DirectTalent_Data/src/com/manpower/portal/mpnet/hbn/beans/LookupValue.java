/*
 * Created on 2006-2-16
 *
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;

/**
 * @author alexander.todorov
 *
 * It is not a persistent class.
 * It is used for presenting an Enumeratin.
 */
public class LookupValue implements Serializable{
	private static final long serialVersionUID = -5987680520660710882L;
	
	private long 	id;
	
	private String	site		= null,
					language	= null,
					code		= null,
					desc		= null,
					lookupName	= null;
	
	
	
	
	/**
	 * @return Returns the code.
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @return Returns the desc.
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * @return Returns the id.
	 */
	public long getId() {
		return id;
	}
	/**
	 * @return Returns the language.
	 */
	public String getLanguage() {
		return language;
	}
	/**
	 * @return Returns the site.
	 */
	public String getSite() {
		return site;
	}
	/**
	 * @param code The code to set.
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @param desc The desc to set.
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @param language The language to set.
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	/**
	 * @param site The site to set.
	 */
	public void setSite(String site) {
		this.site = site;
	}
	/**
	 * @return Returns the lookupName.
	 */
	public String getLookupName() {
		return lookupName;
	}
	/**
	 * @param lookupName The lookupName to set.
	 */
	public void setLookupName(String lookupName) {
		this.lookupName = lookupName;
	}
}
