package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;
import java.util.Date;

public class CredentialVault implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 537516244519890833L;
	private long id;
	private String type;
	private String login;
	private String nonce;
	private String passwordSum;
	private String timestamp;
	private String siteCode;
	private String countryCode;
	private String requestType;
	private String created_By;
	private Date created_On;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getNonce() {
		return nonce;
	}
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	public String getPasswordSum() {
		return passwordSum;
	}
	public void setPasswordSum(String passwordSum) {
		this.passwordSum = passwordSum;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getCreated_By() {
		return created_By;
	}
	public void setCreated_By(String created_By) {
		this.created_By = created_By;
	}
	public Date getCreated_On() {
		return created_On;
	}
	public void setCreated_On(Date created_On) {
		this.created_On = created_On;
	}

}
