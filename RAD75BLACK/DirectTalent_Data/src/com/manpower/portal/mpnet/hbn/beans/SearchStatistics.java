package com.manpower.portal.mpnet.hbn.beans;

import java.util.Date;

public class SearchStatistics extends Base{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8635120191571346230L;

	private long id;
	private long candidateId;
	private long siteId;
	private int resultsFound;
	private Date dateExecuted;
	private double timeConsumed;
	
	public long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(long candidateId) {
		this.candidateId = candidateId;
	}
	public Date getDateExecuted() {
		return dateExecuted;
	}
	public void setDateExecuted(Date dateExecuted) {
		this.dateExecuted = dateExecuted;
	}
	public int getResultsFound() {
		return resultsFound;
	}
	public void setResultsFound(int resultsFound) {
		this.resultsFound = resultsFound;
	}
	public long getSiteId() {
		return siteId;
	}
	public void setSiteId(long siteId) {
		this.siteId = siteId;
	}
	public double getTimeConsumed() {
		return timeConsumed;
	}
	public void setTimeConsumed(double timeConsumed) {
		this.timeConsumed = timeConsumed;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}
