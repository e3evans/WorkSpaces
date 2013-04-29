/*
 * Created on Apr 19, 2006
 *
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
/**
 * @author amillar
 *
 * Persistent class for table GEOLOCATION
 */
public class GeoLocation implements Serializable{
	private static final long serialVersionUID =     3923847504241475330L;
	private long geoLocationID;
	private String countryName;
	private String region;
	private String municipality;
	private String postalCode;
	private String latitude;
	private String longitude;
	private Date createdOn;
	private String createdBy; 
	private Date updatedOn;
	private String updatedBy;
	private String siteId;
	
	public GeoLocation() {
		super();
	}
	
	/**
	 * @return Returns the countryName.
	 */
	public String getCountryName() {
		return countryName;
	}
	/**
	 * @param countryName The countryName to set.
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	/**
	 * @return Returns the createdBy.
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy The createdBy to set.
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @return Returns the createdOn.
	 */
	public Date getCreatedOn() {
		return createdOn;
	}
	/**
	 * @param createdOn The createdOn to set.
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	/**
	 * @return Returns the geoLocationID.
	 */
	public long getGeoLocationID() {
		return geoLocationID;
	}
	/**
	 * @param geoLocationID The geoLocationID to set.
	 */
	public void setGeoLocationID(long geoLocationID) {
		this.geoLocationID = geoLocationID;
	}
	/**
	 * @return Returns the latitude.
	 */
	public String getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude The latitude to set.
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return Returns the longitude.
	 */
	public String getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude The longitude to set.
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	/**
	 * @return Returns the municipality.
	 */
	public String getMunicipality() {
		return municipality;
	}
	/**
	 * @param municipality The municipality to set.
	 */
	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}
	/**
	 * @return Returns the postalCode.
	 */
	public String getPostalCode() {
		return postalCode;
	}
	/**
	 * @param postalCode The postalCode to set.
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
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
	 * @return Returns the updatedOn.
	 */
	public Date getUpdatedOn() {
		return updatedOn;
	}
	/**
	 * @param updatedOn The updatedOn to set.
	 */
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	/**
	 * @return Returns the siteId.
	 */
	public String getSiteId() {
		return siteId;
	}
	/**
	 * @param siteId The siteId to set.
	 */
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if(obj instanceof GeoLocation == false) {
			return false;
		} if (obj == this) {
			return true;
		}
		GeoLocation aGeoLocation = (GeoLocation)obj;
		return new EqualsBuilder().
			appendSuper(super.equals(obj)).
			append(this.geoLocationID, aGeoLocation.getGeoLocationID()).
			append(this.countryName, aGeoLocation.getCountryName()).
			append(this.region, aGeoLocation.getRegion()).
			append(this.municipality,aGeoLocation.getMunicipality()).
			append(this.postalCode, aGeoLocation.getPostalCode()).
			append(this.latitude, aGeoLocation.getLatitude()).
			append(this.longitude, aGeoLocation.getLongitude()).
			append(this.siteId, aGeoLocation.getSiteId()).
			append(this.createdOn, aGeoLocation.getCreatedOn()).
			append(this.createdBy, aGeoLocation.getCreatedBy()).
			append(this.updatedBy, aGeoLocation.getUpdatedBy()).
			append(this.updatedOn, aGeoLocation.getUpdatedOn()).isEquals();
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
	    return new HashCodeBuilder(14, 73).		
			append(this.geoLocationID).
			append(this.countryName).
			append(this.region).
			append(this.municipality).
			append(this.postalCode).
			append(this.latitude).
			append(this.longitude).
			append(this.siteId).
			append(this.createdOn).
			append(this.createdBy).
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
			append("geoLocationID", this.geoLocationID).
			append("countryName", this.countryName).
			append("region", this.region).
			append("municipality", this.municipality).
			append("postalCode", this.postalCode).
			append("latitude", this.latitude).
			append("longitude", this.longitude).
			append("siteId", this.siteId).
			append("createdOn", this.createdOn).
			append("createdBy", this.createdBy).
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
