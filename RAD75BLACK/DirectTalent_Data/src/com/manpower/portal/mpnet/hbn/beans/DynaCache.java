package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;

public class DynaCache implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2725542574729540245L;
	
	private long id;
	private String sessionId;
	private String key;
	private String value;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}	
}
