package com.manpower.hbn.beans;

import java.io.Serializable;
import java.util.Date;

public class RecruiterAuditing implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7017203843002943975L;
	
	private long id;
	private long recruiter_id;
	private long site_id;
	private String action;
	private String search_data;
	private Date created_On;


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
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * @return Returns the search_data.
	 */
	public String getSearch_data() {
		return search_data;
	}
	/**
	 * @param search_data The search_data to set.
	 */
	public void setSearch_data(String search_data) {
		this.search_data = search_data;
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
	
	public Date getCreated_On() {
		return created_On;
	}

	public void setCreated_On(Date created_On) {
		this.created_On = created_On;
	}
}
