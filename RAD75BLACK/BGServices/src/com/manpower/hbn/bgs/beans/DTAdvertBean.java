package com.manpower.hbn.bgs.beans;

import java.util.Date;

public class DTAdvertBean {
	
	private long advertisementid; 
	private long site_id; 
	private String jobtitle; 
	private String jobdescription; 
	private String candidateprofile; 
	private String candidateskills;
	private long advertcontactid; 
	private Date expirationdate; 
	private String jobcode; 
	private String foadvertid;
	public long getAdvertisementid() {
		return advertisementid;
	}
	public void setAdvertisementid(long advertisementid) {
		this.advertisementid = advertisementid;
	}
	public long getSite_id() {
		return site_id;
	}
	public void setSite_id(long site_id) {
		this.site_id = site_id;
	}
	public String getJobtitle() {
		return jobtitle;
	}
	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}
	public String getJobdescription() {
		return jobdescription;
	}
	public void setJobdescription(String jobdescription) {
		this.jobdescription = jobdescription;
	}
	public String getCandidateprofile() {
		return candidateprofile;
	}
	public void setCandidateprofile(String candidateprofile) {
		this.candidateprofile = candidateprofile;
	}
	public String getCandidateskills() {
		return candidateskills;
	}
	public void setCandidateskills(String candidateskills) {
		this.candidateskills = candidateskills;
	}
	public long getAdvertcontactid() {
		return advertcontactid;
	}
	public void setAdvertcontactid(long advertcontactid) {
		this.advertcontactid = advertcontactid;
	}
	public Date getExpirationdate() {
		return expirationdate;
	}
	public void setExpirationdate(Date expirationdate) {
		this.expirationdate = expirationdate;
	}
	public String getJobcode() {
		return jobcode;
	}
	public void setJobcode(String jobcode) {
		this.jobcode = jobcode;
	}
	public String getFoadvertid() {
		return foadvertid;
	}
	public void setFoadvertid(String foadvertid) {
		this.foadvertid = foadvertid;
	} 

	
}
