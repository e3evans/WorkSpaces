package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class MobileDevice implements Serializable{

	private static final long serialVersionUID = -1282687604876739157L;
	
	private long id;
	private String deviceId;
	private String type;
	private String pushToken;
	private boolean sendPush;
	
	private Set agents =new HashSet();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPushToken() {
		return pushToken;
	}
	public void setPushToken(String pushToken) {
		this.pushToken = pushToken;
	}
	public Set getAgents() {
		return agents;
	}
	public void setAgents(Set agents) {
		this.agents = agents;
	}
	public boolean isSendPush() {
		return sendPush;
	}
	public void setSendPush(boolean sendPush) {
		this.sendPush = sendPush;
	}
}
