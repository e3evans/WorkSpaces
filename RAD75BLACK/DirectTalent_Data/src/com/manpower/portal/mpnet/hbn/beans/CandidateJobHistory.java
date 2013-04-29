/*
 * Created on Jan 5, 2006
 *
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.util.Date;

/**
 * @author jsingh
 *
 * Persistent class for table CANDIDATEJOBHISTORIES
 */
public class CandidateJobHistory extends Base{
	 private static final long serialVersionUID =    4365812125672174883L;
	 private long id;
	 private long siteId;
	 private Date startDate;
	 private Date endDate;
	 private String company = "";
	 private String location;
	 private String industry = "";
	 private String jobTitle = "";
	 private String jobDesc;
	 private Date updatedOn;
	 private String updatedBy;
	 private Candidate candidate;
	 private String city;
	 private String region;

	/**
	 * @return Returns the company.
	 */
	public String getCompany() {
		return company;
	}
	/**
	 * @param company The company to set.
	 */
	public void setCompany(String company) {
		this.company = company;
	}
	/**
	 * @return Returns the description.
	 */
	public String getJobDesc() {
		return jobDesc;
	}
	/**
	 * @param description The description to set.
	 */
	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}
	/**
	 * @return Returns the endDate.
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate The endDate to set.
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	 * @return Returns the industry.
	 */
	public String getIndustry() {
		return industry;
	}
	/**
	 * @param industry The industry to set.
	 */
	public void setIndustry(String industry) {
		this.industry = industry;
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
	public String getLocation() {
		return location;
	}
	/**
	 * @param location The location to set.
	 */
	public void setLocation(String location) {
		this.location = location;
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
	 * @return Returns the startDate.
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate The startDate to set.
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return Returns the updatedBy.
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}
	/**
	 * @param updatedBy The updatedBy to set.
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	/**
	 * @return Returns the updatedOn.
	 */
	public Date getUpdatedOn() {
		return updatedOn;
	}
	/**
	 * @param updatedOn The updatedOn to set.
	 */
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	/**
	 * @return Returns the candidate.
	 */
	public Candidate getCandidate() {
		return candidate;
	}
	/**
	 * @param candidate The candidate to set.
	 */
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	
	/**
	 * Compare two objects of type CandidateJobHistory
	 */
	public boolean equals(Object val){
	
		if(val == null){
			
			return false;
			
		}else if(val == this){
			
			return true;
			
		}else if(val instanceof CandidateJobHistory){
			
			CandidateJobHistory history = (CandidateJobHistory)val;
			
			return 	history.getSiteId() == siteId &&
					history.getCandidate().equals(candidate) &&
					history.getCompany().equals(company) &&
					history.getJobTitle().equals(jobTitle) &&
					history.getIndustry().equals(industry);
				
			
		}else{
		
			return false;
			
		}
		
	}
	
	public String toString(){
	
		return ("site :" + siteId + ", company : " + company + ", job title :" + jobTitle + ", industry :" + industry); 
		
	}
	
	public int hashCode(){
	
		return (company + ", " + jobTitle + "," + industry +", " + siteId + "," + candidate.hashCode()).hashCode();
		
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
}
