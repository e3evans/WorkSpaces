/*
 * Created on 2007-6-25
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;

/**
 * @author alexander.todorov
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JobTitleName implements Serializable{

	private static final long serialVersionUID =     5192312010817382308L;

	private String 	jobTitleName 	= null,
					language		= null;
	
	public JobTitleName() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return Returns the locationName.
	 */
	public String getJobTitleName() {
		return jobTitleName;
	}
	
	/**
	 * @param locationName The locationName to set.
	 */
	public void setJobTitleName(String locationName) {
		this.jobTitleName = locationName;
	}
	
	/**
	 * @return Returns the language.
	 */
	public String getLanguage() {
		return language;
	}
	/**
	 * @param language The language to set.
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
}
