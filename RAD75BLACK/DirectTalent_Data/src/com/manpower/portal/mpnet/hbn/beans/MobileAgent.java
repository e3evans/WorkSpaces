package com.manpower.portal.mpnet.hbn.beans;

public class MobileAgent{

	private static final long serialVersionUID = 614317933970936791L;
	
	private long id;
	private long deviceId;
	private String name;
	private String data;
	private long newJobsCount;
	private MobileDevice device;
	
	public long getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(long deviceId) {
		this.deviceId = deviceId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getNewJobsCount() {
		return newJobsCount;
	}
	public void setNewJobsCount(long newJobsCount) {
		this.newJobsCount = newJobsCount;
	}
	
	public MobileDevice getDevice() {
		return device;
	}
	public void setDevice(MobileDevice device) {
		this.device = device;
	}		
}
