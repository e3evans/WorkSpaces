/*
 * Created on 2006-8-25
 *
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @author alexander.todorov
 *
 * Persistent class for table REGIONS
 */
public class CountryRegion implements Serializable{

	private static final long serialVersionUID =     5193690890817381213L;
	
	private long	id;
	
	private double	longitude,
					latitude;
	
	private String 	language				= null,
					country 				= null,
					regionCode 				= null,
					locationCode			= null,
					updatedBy				= null,
					createdBy				= null,
					preferredRegionFlag 	= null;
	
	private Date	updatedOn				= null,
					createdOn				= null;

	private Map		regionNames			= null;
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
	public String getRegionCode() {
		return regionCode;
	}
	/**
	 * @param region The region to set.
	 */
	public void setRegionCode(String region) {
		this.regionCode = region;
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
	public Date getUpdatedOn() {
		return updatedOn;
	}
	/**
	 * @param updateOn The updateOn to set.
	 */
	public void setUpdatedOn(Date updateOn) {
		this.updatedOn = updateOn;
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
	 * @return Returns the locationNames.
	 */
	public Map getRegionNames() {
		return regionNames;
	}
	/**
	 * @param locationNames The locationNames to set.
	 */
	public void setRegionNames(Map locationNames) {
		this.regionNames = locationNames;
	}
	/**
	 * @return Returns the preferredLocationFlag.
	 */
	public String getPreferredRegionFlag() {
		return preferredRegionFlag;
	}
	/**
	 * @param preferredLocationFlag The preferredLocationFlag to set.
	 */
	public void setPreferredRegionFlag(String preferredLocationFlag) {
		this.preferredRegionFlag = preferredLocationFlag;
	}
}
