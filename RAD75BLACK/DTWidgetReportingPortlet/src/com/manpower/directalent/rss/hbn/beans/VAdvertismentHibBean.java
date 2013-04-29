package com.manpower.directalent.rss.hbn.beans;

import java.util.Date;

public class VAdvertismentHibBean {

	private long id; 
	private long site_id; 
	private String jobtitle; 
	private String jobdescription; 
	private Date publicationdate; 
	private Date created_on; 
	private Date changed_on; 
	private Date updatedon; 
	private Date expirationdate; 

	public Date getChanged_on() {
		return changed_on;
	}
	public void setChanged_on(Date changed_on) {
		this.changed_on = changed_on;
	}
	public Date getCreated_on() {
		return created_on;
	}
	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
	public Date getExpirationdate() {
		return expirationdate;
	}
	public void setExpirationdate(Date expirationdate) {
		this.expirationdate = expirationdate;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getJobdescription() {
		return jobdescription;
	}
	public void setJobdescription(String jobdescription) {
		this.jobdescription = jobdescription;
	}
	public String getJobtitle() {
		return jobtitle;
	}
	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}
	public Date getPublicationdate() {
		return publicationdate;
	}
	public void setPublicationdate(Date publicationdate) {
		this.publicationdate = publicationdate;
	}
	public long getSite_id() {
		return site_id;
	}
	public void setSite_id(long site_id) {
		this.site_id = site_id;
	}
	public Date getUpdatedon() {
		return updatedon;
	}
	public void setUpdatedon(Date updatedon) {
		this.updatedon = updatedon;
	}
	
	
	
	
	
	
}
