package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;
import java.util.Date;

public class CandidateProfileStatus implements Serializable
{
	
	private static final long serialVersionUID = 572747550257289277L;
	
	private long id;
	private long candidateId;
	private Date createdOn;
	private boolean statusActive;
	
	
	public long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(long candidateId) {
		this.candidateId = candidateId;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public boolean isStatusActive() {
		return statusActive;
	}
	public void setStatusActive(boolean statusActive) {
		this.statusActive = statusActive;
	}
	
	
}
