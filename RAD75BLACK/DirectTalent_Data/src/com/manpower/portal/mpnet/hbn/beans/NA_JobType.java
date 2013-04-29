/*
 * Created on Nov 8, 2007
 *
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.util.List;

/**
 * @author auljane1
 *
 *	Persistent class for table NA_JOB_TYPE
 */
public class NA_JobType extends Base{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4976333075339600874L;
	private long id;
	private long siteId;
	
	private String display;
	
	private List jobClasses;
	
	/**
	 * @return Returns the display.
	 */
	public String getDisplay() {
		return display;
	}
	/**
	 * @param display The display to set.
	 */
	public void setDisplay(String display) {
		this.display = display;
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
	 * @return Returns the jobClasses.
	 */
	public List getJobClasses() {
		return jobClasses;
	}
	/**
	 * @param jobClasses The jobClasses to set.
	 */
	public void setJobClasses(List jobClasses) {
		this.jobClasses = jobClasses;
	}
}
