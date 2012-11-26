package com.taylor.beans;

import java.util.Date;
import java.util.List;

public class Technician {
	
	private String icon;
	private String name;
	private List<Distributor> distributors;
	private List<Distributor> serviceAgency;
	private String badgeNumber;
	private Date badgeExpire;
	private String certType;
	private String training;
	private String photo;
	private long latitude;
	private long longitude;
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
	public List<Distributor> getServiceAgency() {
		return serviceAgency;
	}
	public void setServiceAgency(List<Distributor> serviceAgency) {
		this.serviceAgency = serviceAgency;
	}
	public String getBadgeNumber() {
		return badgeNumber;
	}
	public void setBadgeNumber(String badgeNumber) {
		this.badgeNumber = badgeNumber;
	}
	public Date getBadgeExpire() {
		return badgeExpire;
	}
	public void setBadgeExpire(Date badgeExpire) {
		this.badgeExpire = badgeExpire;
	}
	public String getCertType() {
		return certType;
	}
	public void setCertType(String certType) {
		this.certType = certType;
	}
	public String getTraining() {
		return training;
	}
	public void setTraining(String training) {
		this.training = training;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public long getLatitude() {
		return latitude;
	}
	public void setLatitude(long latitude) {
		this.latitude = latitude;
	}
	public long getLongitude() {
		return longitude;
	}
	public void setLongitude(long longitude) {
		this.longitude = longitude;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getIcon() {
		return icon;
	}

}
