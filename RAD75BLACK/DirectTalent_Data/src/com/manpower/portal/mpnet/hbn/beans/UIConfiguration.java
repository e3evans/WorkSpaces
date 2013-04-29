/*
 * Created on 2006-5-19
 *
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;

/**
 * @author alexander.todorov
 *
 * Persistent class for view V_CONFIGURATION
 */
public class UIConfiguration implements Serializable {
	private static final long serialVersionUID =    7794678388186795368L;
	private long id	= 0;
	
	private String 	siteCode	= null, 
					binding		= null, 
					view		= null;
	
	
	/**
	 * @return Returns the binding.
	 */
	public String getBinding() {
		return binding;
	}
	/**
	 * @param binding The binding to set.
	 */
	public void setBinding(String binding) {
		this.binding = binding;
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
	 * @return Returns the siteCode.
	 */
	public String getSiteCode() {
		return siteCode;
	}
	/**
	 * @param siteCode The siteCode to set.
	 */
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	/**
	 * @return Returns the view.
	 */
	public String getView() {
		return view;
	}
	/**
	 * @param view The view to set.
	 */
	public void setView(String view) {
		this.view = view;
	}
	
	
}
