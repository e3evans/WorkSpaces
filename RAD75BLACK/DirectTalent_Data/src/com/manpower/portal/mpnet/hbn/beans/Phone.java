/*
 * Created on Jan 19, 2006
 *
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.util.Date;

/**
 * @author jsingh
 *
 * Persistent class for table PHONES
 */
public class Phone extends Base{
	private static final long serialVersionUID = -4866720879052518550L;
	 private long id;
	 private long siteId;
	 private String phoneType;
	 private String	number;
	 private String	primary;
	 private Date updatedOn;
	 private String updatedBy;
	 private Candidate candidate;
	
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
	 * @return Returns the number.
	 */
	public String getNumber() {
		return number;
	}
	/**
	 * @param number The number to set.
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	/**
	 * @return Returns the phoneType.
	 */
	public String getPhoneType() {
		return phoneType;
	}
	/**
	 * @param phoneType The phoneType to set.
	 */
	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}
	/**
	 * @return Returns the primary.
	 */
	public String getPrimary() {
		return primary;
	}
	/**
	 * @param primary The primary to set.
	 */
	public void setPrimary(String primary) {
		this.primary = primary;
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
