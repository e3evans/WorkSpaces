package com.tayloy.data.beans;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Technician extends LocationBean {
	
	private String icon;
	private String name;
	private List<Distributor> distributors;
	private List<Object> serviceAgencys;
	private String badgeNumber;
	private Date badgeExpires;
	private String certType;
	private String trainingHistory;
	private String photo;
	private String baseLocation;
	
	private HashMap<Object, Object> fieldNames = new HashMap<Object,Object>();
	
	public HashMap<Object,Object>getFields(){
		
		fieldNames.put("icon", getIcon());
		fieldNames.put("name", getName());
		fieldNames.put("distributors", getDistributors());
		fieldNames.put("serviceAgencys", getServiceAgencys());
		fieldNames.put("badgeNumber", getBadgeNumber());
		fieldNames.put("certType", getCertType());
		fieldNames.put("trainingHistory", getTrainingHistory());
		fieldNames.put("photo", getPhoto());
		fieldNames.put("baseLocation", getBaseLocation());
		fieldNames.put("longitude",getLongitude());
		fieldNames.put("latitude", getLatitude());
		return fieldNames;
	}
	
	
	
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Distributor> getDistributors() {
		return distributors;
	}
	public void setDistributors(List<Distributor> distributors) {
		this.distributors = distributors;
	}
	public List<Object> getServiceAgencys() {
		return serviceAgencys;
	}
	public void setServiceAgencys(List<Object> serviceAgencys) {
		this.serviceAgencys = serviceAgencys;
	}
	public String getBadgeNumber() {
		return badgeNumber;
	}
	public void setBadgeNumber(String badgeNumber) {
		this.badgeNumber = badgeNumber;
	}
	public Date getBadgeExpires() {
		return badgeExpires;
	}
	public void setBadgeExpires(Date badgeExpires) {
		this.badgeExpires = badgeExpires;
	}
	public String getCertType() {
		return certType;
	}
	public void setCertType(String certType) {
		this.certType = certType;
	}
	public String getTrainingHistory() {
		return trainingHistory;
	}
	public void setTrainingHistory(String trainingHistory) {
		this.trainingHistory = trainingHistory;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getBaseLocation() {
		return baseLocation;
	}
	public void setBaseLocation(String baseLocation) {
		this.baseLocation = baseLocation;
	}

	
	
	

}
