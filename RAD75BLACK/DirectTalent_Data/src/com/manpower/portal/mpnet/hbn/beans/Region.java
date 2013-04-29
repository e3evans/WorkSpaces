/*
 * Created on 2006-8-25
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
 * Persistent class for table REGIONS
 */
public class Region implements Serializable{
	private static final long serialVersionUID =    -8251858197268411614L;
	private long	id;
	
	private double	longitude,
					latitude;
	
	private String 	language			= null,
					country 			= null,
					region 				= null,
					regionName			= null,
					locationCode		= null,
					preferedLocation 	= null,
					updatedBy			= null;
	
	private Date	updateOn			= null,
					changedOn			= null,
					createdOn			= null;

	public Region() {
		super();
	}

	
	/**
	 * @return Returns the changedBy.
	 */
	public Date getCreatedOn() {
		return createdOn;
	}
	/**
	 * @param changedBy The changedBy to set.
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	/**
	 * @return Returns the changedOn.
	 */
	public Date getChangedOn() {
		return changedOn;
	}
	/**
	 * @param changedOn The changedOn to set.
	 */
	public void setChangedOn(Date changedOn) {
		this.changedOn = changedOn;
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
	public String getRegion() {
		return region;
	}
	/**
	 * @param region The region to set.
	 */
	public void setRegion(String region) {
		this.region = region;
	}
	/**
	 * @return Returns the updatedBy.
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}
	/**
	 * @param updatedBy The updatedBy to set.
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	/**
	 * @return Returns the updateOn.
	 */
	public Date getUpdateOn() {
		return updateOn;
	}
	/**
	 * @param updateOn The updateOn to set.
	 */
	public void setUpdateOn(Date updateOn) {
		this.updateOn = updateOn;
	}
	/**
	 * @return Returns the preferedLocation.
	 */
	public String getPreferedLocation() {
		return preferedLocation;
	}
	/**
	 * @param preferedLocation The preferedLocation to set.
	 */
	public void setPreferedLocation(String preferedLocation) {
		this.preferedLocation = preferedLocation;
	}
	/**
	 * @return Returns the regionName.
	 */
	public String getRegionName() {
		return regionName;
	}
	/**
	 * @param regionName The regionName to set.
	 */
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	/**
	 * @return Returns the locationCode.
	 */
	public String getLocationCode() {
		return locationCode;
	}
	/**
	 * @param locationCode The locationCode to set.
	 */
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	/**
	 * @return Returns the latitude.
	 */
	public double getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude The latitude to set.
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return Returns the longitude.
	 */
	public double getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude The longitude to set.
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if(obj instanceof Region == false) {
			return false;
		} if (obj == this) {
			return true;
		}
		Region aRegion = (Region)obj;
		return new EqualsBuilder().
			appendSuper(super.equals(obj)).
			append(this.id, aRegion.getId()).
			append(this.longitude, aRegion.getLongitude()).
			append(this.latitude, aRegion.getLatitude()).
			append(this.language, aRegion.getLanguage()).
			append(this.country, aRegion.getCountry()).
			append(this.region, aRegion.getRegion()).
			append(this.regionName, aRegion.getRegionName()).
			append(this.locationCode, aRegion.getLocationCode()).
			append(this.preferedLocation, aRegion.getPreferedLocation()).
			append(this.changedOn, aRegion.getChangedOn()).
			append(this.createdOn, aRegion.getCreatedOn()).
			append(this.updatedBy, aRegion.getUpdatedBy()).
			append(this.updateOn, aRegion.getUpdateOn()).isEquals();
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
	    return new HashCodeBuilder(11, 33).
			append(this.id).
			append(this.longitude).
			append(this.latitude).
			append(this.language).
			append(this.country).
			append(this.region).
			append(this.regionName).
			append(this.locationCode).
			append(this.preferedLocation).
			append(this.changedOn).
			append(this.createdOn).
			append(this.updatedBy).
			append(this.updateOn).toHashCode();
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
			append("longitute", this.longitude).
			append("latitute", this.latitude).
			append("language", this.language).
			append("country", this.country).
			append("region", this.region).
			append("regionName", this.regionName).
			append("locationCode", this.locationCode).
			append("preferedLocation", this.preferedLocation).
			append("changedOn", this.changedOn).
			append("createdOn", this.createdOn).
			append("updatedBy", this.updatedBy).
			append("updateOn", this.updateOn)
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
