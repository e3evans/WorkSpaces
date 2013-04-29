/*
 * Created on 2006-2-1
 *
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author alexander.todorov
 *
 * Persistent class for view V_LOOKUP_VALUES
 */
public 	class DomainValue 
		implements Serializable{

	private static final long serialVersionUID =     5193690890817382980L;
	long 	id	= 0;
	long 	siteId = 0;
	private	String	site		= null,
					language	= null,
					lookupName	= null,
					code		= null,
					desc		= null,
					sortCode	= null;
						
	
	public DomainValue(){
	
		super();
	}
	
	
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
	 * @return Returns the lookupName.
	 */
	public String getLookupName() {
		return lookupName;
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
	 * @param lookupName The lookupName to set.
	 */
	public void setLookupName(String lookupName) {
		this.lookupName = lookupName;
	}
	/**
	 * @param site The site to set.
	 */
	public void setSite(String site) {
		this.site = site;
	}
	/**
	 * @return Returns the sortCode.
	 */
	public String getSortCode() {
		return sortCode;
	}
	/**
	 * @param sortCode The sortCode to set.
	 */
	public void setSortCode(String sortCode) {
		this.sortCode = sortCode;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if(obj instanceof DomainValue == false) {
			return false;
		} if (obj == this) {
			return true;
		}
		DomainValue aDomainValue = (DomainValue)obj;
		return new EqualsBuilder().
			appendSuper(super.equals(obj)).
			append(this.id, aDomainValue.getId()).
			append(this.site, aDomainValue.getSite()).
			append(this.language, aDomainValue.getLanguage()).
			append(this.lookupName, aDomainValue.getLookupName()).
			append(this.code, aDomainValue.getCode()).
			append(this.desc, aDomainValue.getDesc()).
			append(this.sortCode, aDomainValue.getSortCode())
				.isEquals();
	}
			
	
	public int hashCode() {
		// you pick a hard-coded, randomly chosen, non-zero, odd number
	    // ideally different for each class
	    return new HashCodeBuilder(14, 37).
			append(this.id).
			append(this.site).
			append(this.language).
			append(this.lookupName).
			append(this.code).
			append(this.desc).
			append(this.sortCode).toHashCode();
	}
		
	public String toString() {
		return new ToStringBuilder(this).
			append("id", this.id).
			append("site", this.site).
			append("language", this.language).
			append("lookupName", this.lookupName).
			append("code", this.code).
			append("desc", this.desc).
			append("sortCode", this.sortCode)		
				.toString();
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
}
