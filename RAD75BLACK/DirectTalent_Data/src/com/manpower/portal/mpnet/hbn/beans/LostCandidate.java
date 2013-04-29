/*
 * Created on 2006-5-17
 *
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * @author alexander.todorov
 *
 * Persistent class for table LOSTCANDIDATES
 */
public class LostCandidate implements Serializable{
	private static final long serialVersionUID =    6820050456312883979L;
	private long 		id		= 0, 
						siteId 	= 0;
	
	private String		reason		= null,
						reasonDesc	= null,
						updatedBy	= null,
						email		= null;

	private Date		updatedOn	= null;
	
	
	
	
	
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
	 * @return Returns the reason.
	 */
	public String getReason() {
		return reason;
	}
	/**
	 * @param reason The reason to set.
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	/**
	 * @return Returns the reasonDesc.
	 */
	public String getReasonDesc() {
		return reasonDesc;
	}
	/**
	 * @param reasonDesc The reasonDesc to set.
	 */
	public void setReasonDesc(String reasonDesc) {
		this.reasonDesc = reasonDesc;
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
	/**
	 * @return Returns the email.
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email The email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
}
