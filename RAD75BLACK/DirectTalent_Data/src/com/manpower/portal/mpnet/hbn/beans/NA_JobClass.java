/*
 * Created on Nov 8, 2007
 *
 */
package com.manpower.portal.mpnet.hbn.beans;

/**
 * @author auljane1
 *
 *	Persistent class for table NA_JOB_CLASS
 */
public class NA_JobClass extends Base{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5212200616343948209L;
	private long id;
	private long siteId;
	private long jobTypeId;
	
	private String jobClass;
	private String jobCode;
	
	
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
	 * @return Returns the jobClass.
	 */
	public String getJobClass() {
		return jobClass;
	}
	/**
	 * @param jobClass The jobClass to set.
	 */
	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}
	/**
	 * @return Returns the jobCode.
	 */
	public String getJobCode() {
		return jobCode;
	}
	/**
	 * @param jobCode The jobCode to set.
	 */
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}
	/**
	 * @return Returns the jobTypeId.
	 */
	public long getJobTypeId() {
		return jobTypeId;
	}
	/**
	 * @param jobTypeId The jobTypeId to set.
	 */
	public void setJobTypeId(long jobTypeId) {
		this.jobTypeId = jobTypeId;
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
