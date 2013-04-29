/*
 * Created on 2007-11-15
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;

/**
 * @author alexander.todorov
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CandidateJobTitle implements Serializable{

	private static final long serialVersionUID =   8317195981000613963L;
	
	private long 		id = 0;
	private Candidate 	candidate = null;
	private JobTitle 	jobTitle = null;
	private String 		jobTitleFreeText = null;
	public CandidateJobTitle() {
		super();
	}

	/**
	 * @return Returns the candidate.
	 */
	public Candidate getCandidate() {
		return candidate;
	}
	/**
	 * @return Returns the id.
	 */
	public long getId() {
		return id;
	}
	/**
	 * @return Returns the location.
	 */
	public JobTitle getJobTitle() {
		return jobTitle;
	}
	/**
	 * @return Returns the locationFlag.
	 */

	/**
	 * @param candidate The candidate to set.
	 */
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @param location The location to set.
	 */
	public void setJobTitle(JobTitle jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	
	public long getCandidateId(){
		if(getCandidate() != null){
			return getCandidate().getId();
		}else
			return 0;
	}
	
	public long getJobTitleId(){
		if(getJobTitle() != null){
			return getJobTitle().getId();
		}else
			return 0;
	}
	
	public void setCandidateId(long id){
	}	
	
	public void setLocationId(long id){
	}
	
	/**
	 * @return Returns the jobTitleFreeText.
	 */
	public String getJobTitleFreeText() {
		return jobTitleFreeText;
	}
	/**
	 * @param jobTitleFreeText The jobTitleFreeText to set.
	 */
	public void setJobTitleFreeText(String jobTitleFreeText) {
		this.jobTitleFreeText = jobTitleFreeText;
	}
}
