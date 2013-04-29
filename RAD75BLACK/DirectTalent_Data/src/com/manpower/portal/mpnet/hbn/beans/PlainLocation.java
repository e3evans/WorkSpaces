package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;

public class PlainLocation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4156529106866901602L;
	
	private long id;
	private double latitude;
	private double longitude;
	private String country;
	private String locationName;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

}
