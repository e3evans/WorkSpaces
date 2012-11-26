package com.tayloy.data.beans;

import java.util.HashMap;

public class Distributor extends LocationBean{
	
	private String icon;
	private String name;
	private String address;
	private String phone;
	private String email;
	private String principle;
	private String serviceManager;
	
	private HashMap<Object, Object> fieldNames = new HashMap<Object,Object>();
	
	public HashMap<Object,Object>getFields(){
		
		fieldNames.put("icon", getIcon());
		fieldNames.put("address", getAddress());
		fieldNames.put("name", getName());
		fieldNames.put("phone", getPhone());
		fieldNames.put("email", getEmail());
		fieldNames.put("principle", getPrinciple());
		fieldNames.put("serviceManage", getServiceManager());
		fieldNames.put("longitude",getLongitude());
		fieldNames.put("latitude", getLatitude());
		return fieldNames;
		
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getServiceManager() {
		return serviceManager;
	}

	public void setServiceManager(String serviceManager) {
		this.serviceManager = serviceManager;
	}

}
