package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;

public class CandidatePreferredLocations implements Serializable{
	private static final long serialVersionUID = 8687322342342317L;
	
	private long id;
	private long candidateId;
	private long locationId;
	private String locationFlag;
	
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
	public long getLocationId() {
		return locationId;
	}
	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}
	public String getLocationFlag() {
		return locationFlag;
	}
	public void setLocationFlag(String locationFlag) {
		this.locationFlag = locationFlag;
	}

}

