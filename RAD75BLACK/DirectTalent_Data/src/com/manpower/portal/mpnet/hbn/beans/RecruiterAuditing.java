/*
 * Created on Jul 3, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.mpnet.hbn.beans;

/**
 * @author mstoffel
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RecruiterAuditing extends Base{
	private static final long serialVersionUID = -6773384057570984161L;
	
	private long id;
	private long recruiter_id;
	private long site_id;
	private long foundResult;
	private long entityId;
	
	private String action;
	private String portletName;
	private String searchString;
	private String entity;

	/**
	 * @return Returns the recruiter_id.
	 */
	public long getRecruiter_id() {
		return recruiter_id;
	}
	/**
	 * @param recruiter_id The recruiter_id to set.
	 */
	public void setRecruiter_id(long recruiter_id) {
		this.recruiter_id = recruiter_id;
	}
	/**
	 * @return Returns the site_id.
	 */
	public long getSite_id() {
		return site_id;
	}
	/**
	 * @param site_id The site_id to set.
	 */
	public void setSite_id(long site_id) {
		this.site_id = site_id;
	}

	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * @return Returns the search_data.
	 */
	public String getSearchString() {
		return searchString;
	}
	/**
	 * @param search_data The search_data to set.
	 */
	public void setSearchString(String search_data) {
		this.searchString = search_data;
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
	public long getFoundResult() {
		return foundResult;
	}
	public void setFoundResult(long foundResult) {
		this.foundResult = foundResult;
	}
	public long getEntityId() {
		return entityId;
	}
	public void setEntityId(long entityId) {
		this.entityId = entityId;
	}
	public String getPortletName() {
		return portletName;
	}
	public void setPortletName(String portletName) {
		this.portletName = portletName;
	}
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
}
