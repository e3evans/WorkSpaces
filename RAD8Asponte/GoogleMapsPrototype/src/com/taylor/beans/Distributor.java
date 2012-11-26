package com.taylor.beans;

import java.util.HashMap;

public class Distributor {
	
	public static String TYPE_OFFICE = "office";
	public static String TYPE_BRANCH = "branch";
	
	private String icon;
	private String name;
	private String address;
	private String email;
	private String principle;
	private String type;
	private long totalUnits;
	private long activetechs;
	private double latitude;
	private double longitude;
	
	private HashMap<Object, Object> fieldNames = new HashMap<Object,Object>();
	
	public HashMap<Object,Object> getFields(){
		fieldNames.put("icon", getIcon());
		fieldNames.put("name", getName());
		fieldNames.put("address", getAddress());
		fieldNames.put("email", getEmail());
		fieldNames.put("principle", getPrinciple());
		fieldNames.put("type", getType());
		fieldNames.put("totalUnits", getTotalUnits());
		fieldNames.put("activetechs", getActivetechs());
		fieldNames.put("latitude", getLatitude());
		fieldNames.put("longitude", getLongitude());	
		return fieldNames;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPrinciple() {
		return principle;
	}
	public void setPrinciple(String principle) {
		this.principle = principle;
	}
	public long getTotalUnits() {
		return totalUnits;
	}
	public void setTotalUnits(long totalUnits) {
		this.totalUnits = totalUnits;
	}
	public long getActivetechs() {
		return activetechs;
	}
	public void setActivetechs(long activetechs) {
		this.activetechs = activetechs;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getIcon() {
		return icon;
	}
	
	
	

}
