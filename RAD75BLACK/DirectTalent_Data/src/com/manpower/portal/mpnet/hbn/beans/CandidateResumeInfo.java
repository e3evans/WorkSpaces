/*
 * Created on Jun 13, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;

/**
 * @author Eric Evans
 *
 * Barebones Resume bean for displaying information only.
 * Never put a BLOB in here.
 * 
 */
public class CandidateResumeInfo implements Serializable{
	private static final long serialVersionUID =   918476085850069751L;
	private long id;
	private long siteId;
	private long candidateId;
	private String cvName;
	private String lensID;
	
	

	/**
	 * @return Returns the candidateId.
	 */
	public long getCandidateId() {
		return candidateId;
	}
	/**
	 * @param candidateId The candidateId to set.
	 */
	public void setCandidateId(long candidateId) {
		this.candidateId = candidateId;
	}
	/**
	 * @return Returns the cvName.
	 */
	public String getCvName() {
		return cvName;
	}
	/**
	 * @param cvName The cvName to set.
	 */
	public void setCvName(String cvName) {
		this.cvName = cvName;
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
	/**
	 * @return Returns the lensID.
	 */
	public String getLensID() {
		return lensID;
	}
	/**
	 * @param lensID The lensID to set.
	 */
	public void setLensID(String lensID) {
		this.lensID = lensID;
	}
	/**
	 * @return Returns the siteId.
	 */
	public long getSiteId() {
		return siteId;
	}
	/**
	 * @param siteId The siteId to set.
	 */
	public void setSiteId(long siteId) {
		this.siteId = siteId;
	}
}
