package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;
import java.util.Date;

public class Advertisement_History implements Serializable{

	private static final long serialVersionUID = -4052218937175560503L;
	public static final String MF_ID = "id";
	public static final String MF_CANDIDATE_ID = "candidate_id";
	public static final String MF_ADVERTISEMENT_ID = "advertisement_id";
	public static final String MF_CREATED_ON="created_on";
	
	private long id;
	private long candidate_id;
	private long advertisement_id;
	private String	advertisement_name;
	private Date created_on;
	
	public long getAdvertisement_id() {
		return advertisement_id;
	}
	public void setAdvertisement_id(long advertisement_id) {
		this.advertisement_id = advertisement_id;
	}
	public String getAdvertisement_name() {
		return advertisement_name;
	}
	public void setAdvertisement_name(String advertisement_name) {
		this.advertisement_name = advertisement_name;
	}
	public long getCandidate_id() {
		return candidate_id;
	}
	public void setCandidate_id(long candidate_id) {
		this.candidate_id = candidate_id;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	} 
	public Date getCreated_on() {
		return created_on;
	}
	/**
	 * @param created_On The created_On to set.
	 */
	public void setCreated_on(Date created_On) {
		this.created_on = created_On;
	}	
}
