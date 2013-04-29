/*
 * Created on 2006-8-28
 *
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;

/**
 * @author alexander.todorov
 *
 * Persistent class for table EDUCATIONDEGREES
 */
public class EducationDegree implements Serializable{

	private static final long serialVersionUID =	1177077731187778045L;
	private long id;
	
	private String 	country			= null, 
					language		= null,
					degreeCode		= null,
					degreeName		= null;
	
	public EducationDegree() {
		super();
	}

	/**
	 * @return Returns the country.
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country The country to set.
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return Returns the degreeCode.
	 */
	public String getDegreeCode() {
		return degreeCode;
	}
	/**
	 * @param degreeCode The degreeCode to set.
	 */
	public void setDegreeCode(String degreeCode) {
		this.degreeCode = degreeCode;
	}
	/**
	 * @return Returns the degreeName.
	 */
	public String getDegreeName() {
		return degreeName;
	}
	/**
	 * @param degreeName The degreeName to set.
	 */
	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
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
