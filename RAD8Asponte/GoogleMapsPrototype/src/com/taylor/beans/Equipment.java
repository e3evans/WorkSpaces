package com.taylor.beans;

import java.util.Date;

public class Equipment {
	
	private String icon;
	private String model;
	private String serialNumber;
	private String description;
	private String storeName;
	private String storeAddress;
	private Date installDate;
	private Date certDate;
	private String linkToMove;
	private Date warrantyExpire;
	private long latitude;
	private long longitude;
	
	
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
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStoreAddress() {
		return storeAddress;
	}
	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}
	public Date getInstallDate() {
		return installDate;
	}
	public void setInstallDate(Date installDate) {
		this.installDate = installDate;
	}
	public Date getCertDate() {
		return certDate;
	}
	public void setCertDate(Date certDate) {
		this.certDate = certDate;
	}
	public String getLinkToMove() {
		return linkToMove;
	}
	public void setLinkToMove(String linkToMove) {
		this.linkToMove = linkToMove;
	}
	public Date getWarrantyExpire() {
		return warrantyExpire;
	}
	public void setWarrantyExpire(Date warrantyExpire) {
		this.warrantyExpire = warrantyExpire;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getIcon() {
		return icon;
	}
	
	
	/*
	 * SERVICE HISTORY IN PHASE 2
	 */


}
