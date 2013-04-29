/*
 * Created on Jul 3, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mstoffel
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RecruiterComments implements Serializable{
	private static final long serialVersionUID = 717560709572386760L;
	private long id;
	private long candidate_id;
	private long recruiter_id;
	private long site_id;
	private String comments;
	private String created_by;
	private Date created_on;


	/**
	 * @return Returns the candidate_id.
	 */
	public long getCandidate_id() {
		return candidate_id;
	}
	/**
	 * @param candidate_id The candidate_id to set.
	 */
	public void setCandidate_id(long candidate_id) {
		this.candidate_id = candidate_id;
	}
	/**
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return Returns the created_by.
	 */
	public String getCreated_by() {
		return created_by;
	}
	/**
	 * @param created_by The created_by to set.
	 */
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
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
	 * @return Returns the recruiter_id.
	 */
	public long getRecruiter_id() {
		return recruiter_id;
	}
	/**
	 * @param recruiter_id The recruiter_id to set.
	 */
	public void setRecruiter_id(long recruiter_id) {
		this.recruiter_id = recruiter_id;
	}
	/**
	 * @return Returns the site_id.
	 */
	public long getSite_id() {
		return site_id;
	}
	/**
	 * @param site_id The site_id to set.
	 */
	public void setSite_id(long site_id) {
		this.site_id = site_id;
	}
	/**
	 * @return Returns the created_on.
	 */
	public Date getCreated_on() {
		return created_on;
	}
	/**
	 * @param created_on The created_on to set.
	 */
	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
}
