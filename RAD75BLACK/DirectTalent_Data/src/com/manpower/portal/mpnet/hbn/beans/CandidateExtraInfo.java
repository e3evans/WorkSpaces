package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;

public class CandidateExtraInfo  implements Serializable 
{
	private static final long serialVersionUID =  -6812110890989638307L;
	
	private long 	id;
	private long 	candidateId;
	private boolean noEducated;
	private boolean noExperienced;
	private boolean noReferenced;
	
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
	public boolean isNoEducated() {
		return noEducated;
	}
	public void setNoEducated(boolean noEducated) {
		this.noEducated = noEducated;
	}
	public boolean isNoExperienced() {
		return noExperienced;
	}
	public void setNoExperienced(boolean noExperienced) {
		this.noExperienced = noExperienced;
	}
	public boolean isNoReferenced() {
		return noReferenced;
	}
	public void setNoReferenced(boolean noReferenced) {
		this.noReferenced = noReferenced;
	}
}
