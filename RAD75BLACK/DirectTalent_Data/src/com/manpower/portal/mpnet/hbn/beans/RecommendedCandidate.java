package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;
import java.util.Date;

public class RecommendedCandidate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5747516314457165222L;
	
	private long id;
	private long candidateId;
	private long applicantId;
	private long advertisementId;
	private long siteId;
	private String bgScore;
	private Date dateApplied;
	private Date lastUpdatedOn;
	private String firstName;
	private String lastName;
	private String email;
	private String jobTitle;
	private long recruiterId;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(long candidateId) {
		this.candidateId = candidateId;
	}
	public long getApplicantId() {
		return applicantId;
	}
	public void setApplicantId(long applicantId) {
		this.applicantId = applicantId;
	}
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
	public String getBgScore() {
		return bgScore;
	}
	public void setBgScore(String bgScore) {
		this.bgScore = bgScore;
	}
	public Date getDateApplied() {
		return dateApplied;
	}
	public void setDateApplied(Date dateApplied) {
		this.dateApplied = dateApplied;
	}
	public Date getLastUpdatedOn() {
		return lastUpdatedOn;
	}
	public void setLastUpdatedOn(Date lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	
	

}
