/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;

/**
 * @author Vlado
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class JobCategoryName implements Serializable {

	private static final long serialVersionUID = 5192366010817382308L;

	private String jobCategoryName = null;

	private String language = null;

	public JobCategoryName() {
		super();
	}

	public String getJobCategoryName() {
		return jobCategoryName;
	}

	public void setJobCategoryName(String categoryName) {
		this.jobCategoryName = categoryName;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
}
