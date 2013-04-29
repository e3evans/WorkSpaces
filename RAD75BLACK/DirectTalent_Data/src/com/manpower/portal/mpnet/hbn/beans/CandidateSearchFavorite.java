package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;

public class CandidateSearchFavorite implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8521241269633301246L;
	
	private long id;
	private long candidateId;
	private long recruiterId;
	private String comment;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(long candidateId) {
		this.candidateId = candidateId;
	}
	public long getRecruiterId() {
		return recruiterId;
	}
	public void setRecruiterId(long recruiterId) {
		this.recruiterId = recruiterId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	

}
