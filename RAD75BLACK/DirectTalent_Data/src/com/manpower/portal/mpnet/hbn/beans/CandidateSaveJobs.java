/*
 * Created on Jan 20, 2006
 *
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.util.Date;

/**
 * @author jsingh
 *
 * Persistent class for table CANDIDATESAVEJOBS 
 */
public class CandidateSaveJobs extends Base{
	private static final long serialVersionUID =    1298479649430170004L;
	 private long id;
	 private long siteId;
	 private Date  dateSaved;
	 private Date updatedOn;
	 private String updatedBy;
	 private Candidate candidate;
	 
	 private Advertisement advertisement;
	/**
	 * @return Returns the advertisement.
	 */
	public Advertisement getAdvertisement() {
		return advertisement;
	}
	/**
	 * @param advertisement The advertisement to set.
	 */
	public void setAdvertisement(Advertisement advertisement) {
		this.advertisement = advertisement;
	}


	/**
	 * @return Returns the candidate.
	 */
	public Candidate getCandidate() {
		return candidate;
	}
	/**
	 * @param candidate The candidate to set.
	 */
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	/**
	 * @return Returns the dateSaved.
	 */
	public Date getDateSaved() {
		return dateSaved;
	}
	/**
	 * @param dateSaved The dateSaved to set.
	 */
	public void setDateSaved(Date dateSaved) {
		this.dateSaved = dateSaved;
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
	/**
	 * @return Returns the updatedBy.
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}
	/**
	 * @param updatedBy The updatedBy to set.
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	/**
	 * @return Returns the updatedOn.
	 */
	public Date getUpdatedOn() {
		return updatedOn;
	}
	/**
	 * @param updatedOn The updatedOn to set.
	 */
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
}
