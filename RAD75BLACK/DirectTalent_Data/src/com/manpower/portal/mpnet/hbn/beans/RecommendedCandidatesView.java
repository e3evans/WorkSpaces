package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;

public class RecommendedCandidatesView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5747516314457165222L;
	
	private long advertisementId;
	private long siteId;
	private long recruiterId;
	private String referenceNumber;
	private String jobTitle;
	
	public long getAdvertisementId() {
		return advertisementId;
	}
	public void setAdvertisementId(long advertisementId) {
		this.advertisementId = advertisementId;
	}
	public long getSiteId() {
		return siteId;
	}
	public void setSiteId(long siteId) {
		this.siteId = siteId;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public long getRecruiterId() {
		return recruiterId;
	}
	public void setRecruiterId(long recruiterId) {
		this.recruiterId = recruiterId;
	}
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	
	
	

}
