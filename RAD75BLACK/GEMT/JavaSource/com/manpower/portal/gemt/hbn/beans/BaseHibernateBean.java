/*
 * Created on Jan 12, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.gemt.hbn.beans;

import java.util.Date;

/**
 * @author Eric Evans
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class BaseHibernateBean {
	public static String ORDER_ASC = "ASC";
	public static String ORDER_DESC = "DESC";
	
	private long id;
	private Date created_on; 
	private String created_by; 
	private Date updated_on; 
	private String updated_by; 
	
	

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
	 * @return Returns the created_by.
	 */
	public String getCreated_by() {
		return created_by;
	}
	/**
	 * @param created_by The created_by to set.
	 */
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	/**
	 * @return Returns the created_on.
	 */
	public Date getCreated_on() {
		return created_on;
	}
	/**
	 * @param created_on The created_on to set.
	 */
	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
	
	/**
	 * @return Returns the updated_by.
	 */
	public String getUpdated_by() {
		return updated_by;
	}
	/**
	 * @param updated_by The updated_by to set.
	 */
	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}
	/**
	 * @return Returns the updated_on.
	 */
	public Date getUpdated_on() {
		return updated_on;
	}
	/**
	 * @param updated_on The updated_on to set.
	 */
	public void setUpdated_on(Date updated_on) {
		this.updated_on = updated_on;
	}
}
