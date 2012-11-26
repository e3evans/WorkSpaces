package com.tayloy.data.beans;

import java.util.Date;
import java.util.HashMap;

public class Equipment extends LocationBean {
	
	private String icon;
	private String location;
	private String modelNumber;
	private String serialNumber;
	private String description;
	private String storeName;
	private String storeAddress;
	private Date installDate;
	private Date certDate;
	private Date warrantyExpiration;
	
	private HashMap<Object, Object> fieldNames = new HashMap<Object,Object>();
	
	public HashMap<Object,Object>getFields(){
		fieldNames.put("icon", getIcon());
		fieldNames.put("location", getLocation());
		fieldNames.put("modelNumber", getModelNumber());
		fieldNames.put("serialNumber", getSerialNumber());
		fieldNames.put("description", getDescription());
		fieldNames.put("storeName", getStoreName());
		fieldNames.put("storeAddress", getStoreAddress());
		fieldNames.put("installDate", getInstallDate());
		fieldNames.put("certDate", getCertDate());
		fieldNames.put("warrantyExpireation", getWarrantyExpiration());
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
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

	public Date getWarrantyExpiration() {
		return warrantyExpiration;
	}

	public void setWarrantyExpiration(Date warrantyExpiration) {
		this.warrantyExpiration = warrantyExpiration;
	}
	
	
}
