package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;

public class ApplicantSearchGeoCode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 992472377645424793L;
	
	private long id;
	private long powerSearchId;
	private long siteId;
	private String country;
	private String region;
	private String city;
	private String postalCode;
	private String email;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public void setSiteId(long siteId) {
		this.siteId = siteId;
	}
	public long getSiteId() {
		return siteId;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public long getPowerSearchId() {
		return powerSearchId;
	}
	public void setPowerSearchId(long powerSearchId) {
		this.powerSearchId = powerSearchId;
	}
	
}
