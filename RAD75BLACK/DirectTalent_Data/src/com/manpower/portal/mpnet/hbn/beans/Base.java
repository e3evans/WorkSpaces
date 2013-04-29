/*
 * Created on Jun 21, 2006
 *
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * @author jsingh
 *
 * Base persistent class. 
 */
public class Base implements Serializable {
	private static final long serialVersionUID =  -6883918090989638307L;
	private Date created_On;
	private Date changed_On;
	
	/**
	 * @return Returns the changed_On.
	 */
	public Date getChanged_On() {
		return changed_On;
	}
	/**
	 * @param changed_On The changed_On to set.
	 */
	public void setChanged_On(Date changed_On) {
		this.changed_On = changed_On;
	}
	/**
	 * @return Returns the created_On.
	 */
	public Date getCreated_On() {
		return created_On;
	}
	/**
	 * @param created_On The created_On to set.
	 */
	public void setCreated_On(Date created_On) {
		this.created_On = created_On;
	}


}
