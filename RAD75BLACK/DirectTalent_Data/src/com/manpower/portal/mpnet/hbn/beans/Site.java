/*
 * Created on 2006-3-20
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
 * Persistent class for table SITES
 */

public class Site implements Serializable{
	private static final long serialVersionUID =6005337380201895560L;
	private long id;
	
	private String 	siteCode, 
					siteName,
					owner, 
					firstLangauge, 
					secondLangauge, 
					thirdLangauge, 
					country,
					defaultLevel,
					integrationFlag,
					status,
					updatedBy,
					distanceUnit,
					lensFlag,
					resourcePrefix;
	
	private Date	updatedOn;
	
	public Site() {
		super();
	}
	
	/**
	 * @return Returns the siteName.
	 */
	public String getSiteName() {
		return siteName;
	}
	
	/**
	 * @return Returns the firstLangauge.
	 */
	public String getFirstLangauge() {
		return firstLangauge;
	}
	/**
	 * @return Returns the id.
	 */
	public long getId() {
		return id;
	}
	/**
	 * @return Returns the owner.
	 */
	public String getOwner() {
		return owner;
	}
	/**
	 * @return Returns the secondLangauge.
	 */
	public String getSecondLangauge() {
		return secondLangauge;
	}
	/**
	 * @return Returns the siteCode.
	 */
	public String getSiteCode() {
		return siteCode;
	}
	/**
	 * @return Returns the thirdLangauge.
	 */
	public String getThirdLangauge() {
		return thirdLangauge;
	}
	/**
	 * @return Returns the updateBy.
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}
	/**
	 * @return Returns the updateOn.
	 */
	public Date getUpdatedOn() {
		return updatedOn;
	}
	/**
	 * @param firstLangauge The firstLangauge to set.
	 */
	public void setFirstLangauge(String firstLangauge) {
		this.firstLangauge = firstLangauge;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @param owner The owner to set.
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}
	/**
	 * @param secondLangauge The secondLangauge to set.
	 */
	public void setSecondLangauge(String secondLangauge) {
		this.secondLangauge = secondLangauge;
	}
	/**
	 * @param siteCode The siteCode to set.
	 */
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	/**
	 * @param thirdLangauge The thirdLangauge to set.
	 */
	public void setThirdLangauge(String thirdLangauge) {
		this.thirdLangauge = thirdLangauge;
	}
	/**
	 * @param updateBy The updateBy to set.
	 */
	public void setUpdatedBy(String updateBy) {
		this.updatedBy = updateBy;
	}
	/**
	 * @param updateOn The updateOn to set.
	 */
	public void setUpdatedOn(Date updateOn) {
		this.updatedOn = updateOn;
	}
	/**
	 * @return Returns the country.
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country The country to set.
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return Returns the siteType.
	 */
	public String getDefaultLevel() {
		return defaultLevel;
	}
	/**
	 * @param siteType The siteType to set.
	 */
	public void setDefaultLevel(String siteType) {
		this.defaultLevel = siteType;
	}
	/**
	 * @return Returns the integrationFlag.
	 */
	public String getIntegrationFlag() {
		return integrationFlag;
	}
	/**
	 * @param integrationFlag The integrationFlag to set.
	 */
	public void setIntegrationFlag(String integrationFlag) {
		this.integrationFlag = integrationFlag;
	}
	/**
	 * @return Returns the distanceUnit.
	 */
	public String getDistanceUnit() {
		return distanceUnit;
	}
	/**
	 * @param distanceUnit The distanceUnit to set.
	 */
	public void setDistanceUnit(String distanceUnit) {
		this.distanceUnit = distanceUnit;
	}
	/**
	 * @return Returns the lensFlag.
	 */
	public String getLensFlag() {
		return lensFlag;
	}
	/**
	 * @param lensFlag The lensFlag to set.
	 */
	public void setLensFlag(String lensFlag) {
		this.lensFlag = lensFlag;
	}
	/**
	 * @return Returns the resourcePrefix.
	 */
	public String getResourcePrefix() {
		return resourcePrefix;
	}
	/**
	 * @param resourcePrefix The resourcePrefix to set.
	 */
	public void setResourcePrefix(String resourcePrefix) {
		this.resourcePrefix = resourcePrefix;
	}
	/**
	 * @param siteName The siteName to set.
	 */
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if(obj instanceof Site == false) {
			return false;
		} if (obj == this) {
			return true;
		}
		Site aSite = (Site)obj;
		return new EqualsBuilder().
			appendSuper(super.equals(obj)).
			append(this.id, aSite.getId()).
			append(this.siteCode, aSite.getSiteCode()).
			append(this.siteName, aSite.getSiteName()).
			append(this.owner, aSite.getOwner()).
			append(this.firstLangauge, aSite.getFirstLangauge()).
			append(this.secondLangauge, aSite.getSecondLangauge()).
			append(this.thirdLangauge, aSite.getThirdLangauge()).
			append(this.country, aSite.getCountry()).
			append(this.defaultLevel, aSite.getDefaultLevel()).
			append(this.integrationFlag, aSite.getIntegrationFlag()).
			append(this.status, aSite.getStatus()).
			append(this.distanceUnit, aSite.getDistanceUnit()).
			append(this.lensFlag, aSite.getLensFlag()).
			append(this.resourcePrefix, aSite.getResourcePrefix()).
			append(this.updatedBy, aSite.getUpdatedBy()).
			append(this.updatedOn, aSite.getUpdatedOn()).isEquals();
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
	    return new HashCodeBuilder(17, 37).
			append(this.id).
			append(this.siteCode).
			append(this.siteName).
			append(this.owner).
			append(this.firstLangauge).
			append(this.secondLangauge).
			append(this.thirdLangauge).
			append(this.country).
			append(this.defaultLevel).
			append(this.integrationFlag).
			append(this.status).
			append(this.distanceUnit).
			append(this.lensFlag).
			append(this.resourcePrefix).
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
			append("siteCode", this.siteCode).
			append("siteName", this.siteName).
			append("owner", this.owner).
			append("firstLanguage", this.firstLangauge).
			append("secondLanguage", this.secondLangauge).
			append("thirdLanguage", this.thirdLangauge).
			append("country", this.country).
			append("defaultLevel", this.defaultLevel).
			append("integrationFlag", this.integrationFlag).
			append("status", this.status).
			append("distanceUnit", this.distanceUnit).
			append("lensFlag", this.lensFlag).
			append("resourcePrefix", this.resourcePrefix).
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
