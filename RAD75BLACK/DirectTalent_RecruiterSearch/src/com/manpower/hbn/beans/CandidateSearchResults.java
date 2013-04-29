package com.manpower.hbn.beans;

import java.util.Date;

public class CandidateSearchResults {
	
	private long unique_id;
	private long candidate_id;
	private long region_id; 
	private long location_id;
	private long site_id;
	private long resume_id;
	private long count_of_jobs_applied_for;
	private long cv_id;
	private long q_total;
	
	public long getQ_total() {
		return q_total;
	}
	public void setQ_total(long q_total) {
		this.q_total = q_total;
	}
	private String firstname;
	private String middlename;
	private String status;
	private String prefered_location;
	private String branchname;
	private String lastname;
	private String cand_type;
	private String mime_type;
	private String resume_name;
	
	private Date dateapplied;
	private Date last_login_date;
	private Date updatedon;
	public String getBranchname() {
		return branchname;
	}
	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}
	public long getCount_of_jobs_applied_for() {
		return count_of_jobs_applied_for;
	}
	public void setCount_of_jobs_applied_for(long count_of_jobs_applied_for) {
		this.count_of_jobs_applied_for = count_of_jobs_applied_for;
	}
	public Date getDateapplied() {
		return dateapplied;
	}
	public void setDateapplied(Date dateapplied) {
		this.dateapplied = dateapplied;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public long getCandidate_id() {
		return candidate_id;
	}
	public void setCandidate_id(long candidate_id) {
		this.candidate_id = candidate_id;
	}
	public Date getLast_login_date() {
		return last_login_date;
	}
	public void setLast_login_date(Date last_login_date) {
		this.last_login_date = last_login_date;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public long getLocation_id() {
		return location_id;
	}
	public void setLocation_id(long location_id) {
		this.location_id = location_id;
	}
	public String getMiddlename() {
		return middlename;
	}
	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}
	public String getPrefered_location() {
		return prefered_location;
	}
	public void setPrefered_location(String prefered_location) {
		this.prefered_location = prefered_location;
	}
	public long getRegion_id() {
		return region_id;
	}
	public void setRegion_id(long region_id) {
		this.region_id = region_id;
	}
	public long getResume_id() {
		return resume_id;
	}
	public void setResume_id(long resume_id) {
		this.resume_id = resume_id;
	}
	public long getSite_id() {
		return site_id;
	}
	public void setSite_id(long site_id) {
		this.site_id = site_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getUpdatedon() {
		return updatedon;
	}
	public void setUpdatedon(Date updatedon) {
		this.updatedon = updatedon;
	}
	public String getCand_type() {
		return cand_type;
	}
	public void setCand_type(String cand_type) {
		this.cand_type = cand_type;
	}
	public long getCv_id() {
		return cv_id;
	}
	public void setCv_id(long cv_id) {
		this.cv_id = cv_id;
	}
	public String getMime_type() {
		return mime_type;
	}
	public void setMime_type(String mime_type) {
		this.mime_type = mime_type;
	}
	public String getResume_name() {
		return resume_name;
	}
	public void setResume_name(String resume_name) {
		this.resume_name = resume_name;
	}
	public long getUnique_id() {
		return unique_id;
	}
	public void setUnique_id(long unique_id) {
		this.unique_id = unique_id;
	}
	
	
	
	
}
