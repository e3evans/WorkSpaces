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
public class RegionName implements Serializable{

	private static final long serialVersionUID =     5193690890817381201L;

	private String 	regionName 		= null,
					language		= null;
	
	public RegionName() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @return Returns the regionName.
	 */
	public String getRegionName() {
		return regionName;
	}

	/**
	 * @param regionName The regionName to set.
	 */
	public void setRegionName(String regionName) {
		this.regionName = regionName;
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
