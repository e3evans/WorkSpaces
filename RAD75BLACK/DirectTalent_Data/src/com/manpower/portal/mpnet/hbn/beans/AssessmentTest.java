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
public class AssessmentTest extends Base {

	private long id;
	private String name;
	private String skillMeasured;
	private Set deliveryFormats;
	private Set ratings;
	private long providerId;

	
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
	 * @return Returns the providerId.
	 */
	public long getProviderId() {
		return providerId;
	}
	/**
	 * @param providerId The providerId to set.
	 */
	public void setProviderId(long providerId) {
		this.providerId = providerId;
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
	 * @return Returns the skillMeasured.
	 */
	public String getSkillMeasured() {
		return skillMeasured;
	}
	/**
	 * @param skillMeasured The skillMeasured to set.
	 */
	public void setSkillMeasured(String skillMeasured) {
		this.skillMeasured = skillMeasured;
	}
	
	/**
	 * @return Returns the deliveryFormats.
	 */
	public Set getDeliveryFormats() {
		return deliveryFormats;
	}
	/**
	 * @param deliveryFormats The deliveryFormats to set.
	 */
	public void setDeliveryFormats(Set deliveryFormats) {
		this.deliveryFormats = deliveryFormats;
	}
	/**
	 * @return Returns the ratings.
	 */
	public Set getRatings() {
		return ratings;
	}
	/**
	 * @param ratings The ratings to set.
	 */
	public void setRatings(Set ratings) {
		this.ratings = ratings;
	}
	
}
