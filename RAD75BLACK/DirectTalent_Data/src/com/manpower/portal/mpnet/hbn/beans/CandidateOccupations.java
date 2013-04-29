package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;

public class CandidateOccupations implements Serializable{
	private static final long serialVersionUID = 8687329475972021317L;
	private long id;
	private long candidateId;
	private long occupationId;
	
	public long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(long candidateId) {
		this.candidateId = candidateId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getOccupationId() {
		return occupationId;
	}
	public void setOccupationId(long occupationId) {
		this.occupationId = occupationId;
	}
	
	
	
}
