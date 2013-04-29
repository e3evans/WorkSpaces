/*
 * Created on Jan 20, 2006
 *
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.util.Date;

/**
 * @author jsingh
 * 
 * Persistent Class for table ADVERTISEMENTS
 */
public class ActiveAdvert extends Base{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4789808847444020062L;

	private long 	id,
					siteId,
					locationId;
	
	private Date 	publicationDate;

	private String 	jobTitle,
					jobDescription,
					candidateProfile,
					candidateSkills,
					contractType,
					industrySector,
					referenceNumber,
					hoursWorked,
					language;
	
	
	/**
	 * @return Returns the candidateProfile.
	 */
	public String getCandidateProfile() {
		return candidateProfile;
	}
	/**
	 * @param candidateProfile The candidateProfile to set.
	 */
	public void setCandidateProfile(String candidateProfile) {
		this.candidateProfile = candidateProfile;
	}
	/**
	 * @return Returns the candidateSkills.
	 */
	public String getCandidateSkills() {
		return candidateSkills;
	}
	/**
	 * @param candidateSkills The candidateSkills to set.
	 */
	public void setCandidateSkills(String candidateSkills) {
		this.candidateSkills = candidateSkills;
	}
	/**
	 * @return Returns the industrySector.
	 */
	public String getIndustrySector() {
		return industrySector;
	}
	/**
	 * @param industrySector The industrySector to set.
	 */
	public void setIndustrySector(String industrySector) {
		this.industrySector = industrySector;
	}
	/**
	 * @return Returns the jobDescription.
	 */
	public String getJobDescription() {
		return jobDescription;
	}
	/**
	 * @param jobDescription The jobDescription to set.
	 */
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	/**
	 * @return Returns the jobTitle.
	 */
	public String getJobTitle() {
		return jobTitle;
	}
	/**
	 * @param jobTitle The jobTitle to set.
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	/**
	 * @return Returns the location.
	 */
	public long getLocationId() {
		return locationId;
	}
	/**
	 * @param location The location to set.
	 */
	public void setLocationId(long location) {
		this.locationId = location;
	}
	/**
	 * @return Returns hoursWorked.
	 */
	public String getHoursWorked() {
		return hoursWorked;
	}
	/**
	 * @param hoursWorked The hoursWorked to set.
	 */
	public void setHoursWorked(String hoursWorked) {
		this.hoursWorked = hoursWorked;
	}
	/**
	 * @return Returns the id.
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return Returns the siteId.
	 */
	public long getSiteId() {
		return siteId;
	}
	/**
	 * @param siteId The siteId to set.
	 */
	public void setSiteId(long siteId) {
		this.siteId = siteId;
	}
	/**
	 * @return Returns the publicationDate.
	 */
	public Date getPublicationDate() {
		return publicationDate;
	}
	/**
	 * @param publicationDate The publicationDate to set.
	 */
	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}
	/**
	 * @return Returns the referenceNumber.
	 */
	public String getReferenceNumber() {
		return referenceNumber;
	}
	/**
	 * @param referenceNumber The referenceNumber to set.
	 */
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	/**
	 * @return Returns the language.
	 */
	public String getLanguage() {
		return language;
	}
	/**
	 * @param language The language to set.
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getContractType() {
		return contractType;
	}
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	
	
}
