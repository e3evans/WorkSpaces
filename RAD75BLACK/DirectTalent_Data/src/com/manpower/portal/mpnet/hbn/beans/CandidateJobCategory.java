/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;

/**
 * Entity for the candidatejobcategory table
 * 
 * @author Vlado
 *  
 */
public class CandidateJobCategory implements Serializable {
	private static final long serialVersionUID = 8317195981000613963L;

	private long id = 0;

	private Candidate candidate = null;

	private JobCategory jobCategory = null;

	public CandidateJobCategory() {
		super();
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public long getId() {
		return id;
	}

	public JobCategory getJobCategory() {
		return jobCategory;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setJobCategory(JobCategory jobCategory) {
		this.jobCategory = jobCategory;
	}

	public long getCandidateId() {
		if (getCandidate() != null) {
			return getCandidate().getId();
		} else
			return 0;
	}

	public long getJobCategoryId() {
		if (getJobCategory() != null) {
			return getJobCategory().getId();
		} else
			return 0;
	}

	public void setCandidateId(long id) {
	}

	public void setLocationId(long id) {
	}
}
