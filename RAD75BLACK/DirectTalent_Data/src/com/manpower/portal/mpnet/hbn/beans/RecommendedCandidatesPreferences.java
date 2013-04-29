package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;

public class RecommendedCandidatesPreferences implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4705310374922278029L;
	private long id;
	private long advertId;
	private long recruiterId;
	private int distance;
	private String distanceUnit;
	private String score;

	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getAdvertId() {
		return advertId;
	}
	public void setAdvertId(long advertId) {
		this.advertId = advertId;
	}
	public long getRecruiterId() {
		return recruiterId;
	}
	public void setRecruiterId(long recruiterId) {
		this.recruiterId = recruiterId;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public String getDistanceUnit() {
		return distanceUnit;
	}
	public void setDistanceUnit(String distanceUnit) {
		this.distanceUnit = distanceUnit;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	

}
