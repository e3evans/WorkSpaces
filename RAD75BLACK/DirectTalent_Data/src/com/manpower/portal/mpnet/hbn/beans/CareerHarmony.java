/*
 * Created on Aug 14, 2006
 *
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.util.Date;

/**
 * @author jsingh
 *
 * Persistent class for table CAREERHARMONY
 */
public class CareerHarmony extends Base{
	private static final long serialVersionUID =      -1297755274589151273L;
	private long id;
	private long siteId;
	private String securityKey;
	private String projectID;
	private String companyID;
	private String languageId;
	private long notification;
	private Date updatedOn;
	private String updatedBy;

	


	/**
	 * @return Returns the companyID.
	 */
	public String getCompanyID() {
		return companyID;
	}
	/**
	 * @param companyID The companyID to set.
	 */
	public void setCompanyID(String companyID) {
		this.companyID = companyID;
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
	 * @return Returns the languageId.
	 */
	public String getLanguageId() {
		return languageId;
	}
	/**
	 * @param languageId The languageId to set.
	 */
	public void setLanguageId(String languageId) {
		this.languageId = languageId;
	}
	/**
	 * @return Returns the notification.
	 */
	public long getNotification() {
		return notification;
	}
	/**
	 * @param notification The notification to set.
	 */
	public void setNotification(long notification) {
		this.notification = notification;
	}
	/**
	 * @return Returns the projectID.
	 */
	public String getProjectID() {
		return projectID;
	}
	/**
	 * @param projectID The projectID to set.
	 */
	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}
	/**
	 * @return Returns the securityKey.
	 */
	public String getSecurityKey() {
		return securityKey;
	}
	/**
	 * @param securityKey The securityKey to set.
	 */
	public void setSecurityKey(String securityKey) {
		this.securityKey = securityKey;
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
