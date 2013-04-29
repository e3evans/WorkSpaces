package com.manpower.portal.mpnet.hbn.beans;

import java.util.Date;

public class JobApplicant extends Base{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8873639186993895749L;
	
	private long id;
	private long siteId;
	private long advertId;	
	private String rating;
	private String externalId;
	private String firstName;
	private String lastName;
	private String email;
	private Date dateApplied;
	private Date lastLogin;
	private String branchName;
	private String status;
	private String bgScore;
	private int applicantType;
	private long candidateId;
	private boolean sentToFo;
	private int profileStatus;
	private int resumeStatus;
	private String createdBy;
	
	public long getAdvertId() {
		return advertId;
	}
	public void setAdvertId(long advertId) {
		this.advertId = advertId;
	}
	public Date getDateApplied() {
		return dateApplied;
	}
	public void setDateApplied(Date dateApplied) {
		this.dateApplied = dateApplied;
	}
	public String getExternalId() {
		return externalId;
	}
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public long getSiteId() {
		return siteId;
	}
	public void setSiteId(long siteId) {
		this.siteId = siteId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBgScore() {
		return bgScore;
	}
	public void setBgScore(String bgScore) {
		this.bgScore = bgScore;
	}
	public int getApplicantType() {
		return applicantType;
	}
	public void setApplicantType(int applicantType) {
		this.applicantType = applicantType;
	}
	public long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(long candidateId) {
		this.candidateId = candidateId;
	}
	public boolean isSentToFo() {
		return sentToFo;
	}
	public void setSentToFo(boolean sentToFo) {
		this.sentToFo = sentToFo;
	}
	public int getProfileStatus() {
		return profileStatus;
	}
	public void setProfileStatus(int profileStatus) {
		this.profileStatus = profileStatus;
	}
	public int getResumeStatus() {
		return resumeStatus;
	}
	public void setResumeStatus(int resumeStatus) {
		this.resumeStatus = resumeStatus;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
}
