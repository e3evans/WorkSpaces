/*
 * Created on 2006-12-27
 *
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * @author alexander.todorov
 *
 * Persistent class for table LOOKUP_VALUES
 */
public class Enumeration implements Serializable{
	private static final long serialVersionUID =     -276127717665664947L;
	private long 	id, 
					siteId;
	
	
	private String	lookupName,
					lookupDesc,
					lookupCode,
					lookupLang,
					lookupSort,
					updatedBy;
	
	Date			updatedOn;
		
	
	public Enumeration() {
		super();
	}

	/**
	 * @return Returns the id.
	 */
	public long getId() {
		return id;
	}
	/**
	 * @return Returns the lookupCode.
	 */
	public String getLookupCode() {
		return lookupCode;
	}
	/**
	 * @return Returns the lookupDesc.
	 */
	public String getLookupDesc() {
		return lookupDesc;
	}
	/**
	 * @return Returns the lookupLang.
	 */
	public String getLookupLang() {
		return lookupLang;
	}
	/**
	 * @return Returns the lookupName.
	 */
	public String getLookupName() {
		return lookupName;
	}
	/**
	 * @return Returns the lookupSort.
	 */
	public String getLookupSort() {
		return lookupSort;
	}
	/**
	 * @return Returns the siteId.
	 */
	public long getSiteId() {
		return siteId;
	}
	/**
	 * @return Returns the updatedBy.
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}
	/**
	 * @return Returns the updatedOn.
	 */
	public Date getUpdatedOn() {
		return updatedOn;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @param lookupCode The lookupCode to set.
	 */
	public void setLookupCode(String lookupCode) {
		this.lookupCode = lookupCode;
	}
	/**
	 * @param lookupDesc The lookupDesc to set.
	 */
	public void setLookupDesc(String lookupDesc) {
		this.lookupDesc = lookupDesc;
	}
	/**
	 * @param lookupLang The lookupLang to set.
	 */
	public void setLookupLang(String lookupLang) {
		this.lookupLang = lookupLang;
	}
	/**
	 * @param lookupName The lookupName to set.
	 */
	public void setLookupName(String lookupName) {
		this.lookupName = lookupName;
	}
	/**
	 * @param lookupSort The lookupSort to set.
	 */
	public void setLookupSort(String lookupSort) {
		this.lookupSort = lookupSort;
	}
	/**
	 * @param siteId The siteId to set.
	 */
	public void setSiteId(long siteId) {
		this.siteId = siteId;
	}
	/**
	 * @param updatedBy The updatedBy to set.
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	/**
	 * @param updatedOn The updatedOn to set.
	 */
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if(obj instanceof Enumeration == false) {
			return false;
		} if (obj == this) {
			return true;
		}
		Enumeration anEnumeration = (Enumeration)obj;
		return new EqualsBuilder().
			appendSuper(super.equals(obj)).
			append(this.id, anEnumeration.getId()).
			append(this.siteId, anEnumeration.getSiteId()).
			append(this.lookupName, anEnumeration.getLookupName()).
			append(this.lookupDesc, anEnumeration.getLookupDesc()).
			append(this.lookupCode, anEnumeration.getLookupCode()).
			append(this.lookupLang, anEnumeration.getLookupLang()).
			append(this.lookupSort, anEnumeration.getLookupSort()).
			append(this.updatedBy, anEnumeration.getUpdatedBy()).
			append(this.updatedOn, anEnumeration.getUpdatedOn()).isEquals();
	}
			
	
	/* This version is slower than the hard-coded version and will 
	 * fail under a security manager unless appropriate permissions are set.
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	//public boolean equals(Object obj) {
	//	return EqualsBuilder.reflectionEquals(this, obj);
	//}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		// you pick a hard-coded, randomly chosen, non-zero, odd number
	    // ideally different for each class
	    return new HashCodeBuilder(14, 37).
			append(this.id).
			append(this.siteId).
			append(this.lookupName).
			append(this.lookupDesc).
			append(this.lookupCode).
			append(this.lookupLang).
			append(this.lookupSort).
			append(this.updatedBy).
			append(this.updatedOn).toHashCode();
	}
		
	/* This version is slower than the hard-coded version and will 
	 * fail under a security manager unless appropriate permissions are set.
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	//public int hashCode() {
	//   return HashCodeBuilder.reflectionHashCode(this);
	//}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).
			append("id", this.id).
			append("siteId", this.siteId).
			append("lookupName", this.lookupName).
			append("lookupDesc", this.lookupDesc).
			append("lookupCode", this.lookupCode).
			append("lookupLang", this.lookupLang).
			append("lookupSort", this.lookupSort).
			append("updatedBy", this.updatedBy).
			append("updatedOn", this.updatedOn)
				.toString();
	}
	/* This version is slower than the hard-coded version and will 
	 * fail under a security manager unless appropriate permissions are set.
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */	
	//public String toString() {
	//   return ToStringBuilder.reflectionToString(this);
	//}
}
