/*
 * Created on 2006-2-7
 *
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author alexander.todorov
 *
 * Persistent class for view V_BRANCHES
 */
public class Branch implements Serializable {
	private static final long serialVersionUID =  -6883918090989638307L;
	private	long	id,
					siteId;
	
	private String	language,
					branchName,
					branchCode,
					state,
					location,
					speciality,
					street,
					address1,
					address2,
					email,
					phoneNumber , 
					faxNumber , 
					postalCode , 
					country ,
					city,
					locationInfo,
					operationHours,
					comments,
					externalId,
					mapId,
					mapUrl;
	
	private	float	latitude;
	private	float	longitude;
	
	

	/**
	 * @return Returns the branchCode.
	 */
	public String getBranchCode() {
		return branchCode;
	}
	/**
	 * @return Returns the branchName.
	 */
	public String getBranchName() {
		return branchName;
	}
	/**
	 * @return Returns the id.
	 */
	public long getId() {
		return id;
	}
	/**
	 * @return Returns the location.
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @return Returns the siteId.
	 */
	public long getSiteId() {
		return siteId;
	}
	/**
	 * @return Returns the speciality.
	 */
	public String getSpeciality() {
		return speciality;
	}
	/**
	 * @return Returns the state.
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param branchCode The branchCode to set.
	 */
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	/**
	 * @param branchName The branchName to set.
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @param location The location to set.
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @param siteId The siteId to set.
	 */
	public void setSiteId(long siteId) {
		this.siteId = siteId;
	}
	/**
	 * @param speciality The speciality to set.
	 */
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	/**
	 * @param state The state to set.
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return Returns the street.
	 */
	public String getStreet() {
		return street;
	}
	/**
	 * @param street The street to set.
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	/**
	 * @return Returns the address1.
	 */
	public String getAddress1() {
		return address1;
	}
	/**
	 * @param address1 The address1 to set.
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	/**
	 * @return Returns the email.
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email The email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return Returns the phoneNumber.
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber The phoneNumber to set.
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return Returns the city.
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city The city to set.
	 */
	public void setCity(String city) {
		this.city = city;
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
	 * @return Returns the faxNumber.
	 */
	public String getFaxNumber() {
		return faxNumber;
	}
	/**
	 * @param faxNumber The faxNumber to set.
	 */
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
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
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return Returns the locationInfo.
	 */
	public String getLocationInfo() {
		return locationInfo;
	}
	/**
	 * @param locationInfo The locationInfo to set.
	 */
	public void setLocationInfo(String locationInfo) {
		this.locationInfo = locationInfo;
	}
	/**
	 * @return Returns the operationHours.
	 */
	public String getOperationHours() {
		return operationHours;
	}
	/**
	 * @param operationHours The operationHours to set.
	 */
	public void setOperationHours(String operationHours) {
		this.operationHours = operationHours;
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
	 * @return Returns the address2.
	 */
	public String getAddress2() {
		return address2;
	}
	/**
	 * @param address2 The address2 to set.
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getExternalId() {
		return externalId;
	}
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	public String getMapId() {
		return mapId;
	}
	public void setMapId(String mapId) {
		this.mapId = mapId;
	}
	public String getMapUrl() {
		return mapUrl;
	}
	public void setMapUrl(String mapUrl) {
		this.mapUrl = mapUrl;
	}
	/**
	 * @return the latitude
	 */
	public float getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the longitude
	 */
	public float getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	
	
}
