/*
 * Created on 2007-11-7
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.util.Set;

/**
 * @author velin.doychinov
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AssessmentSkillGroup extends Base {

	private long id;
	private String name;
	private Set skillCategories;

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
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return Returns the skillCategories.
	 */
	public Set getSkillCategories() {
		return skillCategories;
	}
	/**
	 * @param skillCategories The skillCategories to set.
	 */
	public void setSkillCategories(Set skillCategories) {
		this.skillCategories = skillCategories;
	}
}
