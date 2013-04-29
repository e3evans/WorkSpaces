/*
 * Created on Jan 18, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.gemt.ui.beans;

import java.util.Date;
import java.util.Map;

/**
 * @author rrajaram
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GemtNotesUIBean extends BaseUIBean {

	private long id;
	
	private String gemt_notes_user_name; 	

	private String gemt_notes_content;

	private String gemt_notes_user_email;
		
	private String xml_real_path;	
	
	private Date gemt_notes_date;
	

	public void convertHtmlVals(){
		this.gemt_notes_content=getHtmlfromString(this.gemt_notes_content);
		
	}

	/**
	 * @return the gemt_notes_date
	 */
	public Date getGemt_notes_date() {
		return gemt_notes_date;
	}

	/**
	 * @param gemt_notes_date the gemt_notes_date to set
	 */
	public void setGemt_notes_date(Date gemt_notes_date) {
		this.gemt_notes_date = gemt_notes_date;
	}

	public GemtNotesUIBean(String formBase,Map incomingFields){
		setPropreties(formBase,incomingFields,GemtNotesUIBean.class);
	}

	/**
	 * @return the gemt_notes_content
	 */
	public String getGemt_notes_content() {
		return gemt_notes_content;
	}

	/**
	 * @param gemt_notes_content the gemt_notes_content to set
	 */
	public void setGemt_notes_content(String gemt_notes_content) {
		this.gemt_notes_content = gemt_notes_content;
	}

	/**
	 * @return the gemt_notes_user_email
	 */
	public String getGemt_notes_user_email() {
		return gemt_notes_user_email;
	}

	/**
	 * @param gemt_notes_user_email the gemt_notes_user_email to set
	 */
	public void setGemt_notes_user_email(String gemt_notes_user_email) {
		this.gemt_notes_user_email = gemt_notes_user_email;
	}

	public GemtNotesUIBean() {
	}

	

	
	

	

	/**
	 * @return the gemt_notes_user_name
	 */
	public String getGemt_notes_user_name() {
		return gemt_notes_user_name;
	}

	/**
	 * @param gemt_notes_user_name the gemt_notes_user_name to set
	 */
	public void setGemt_notes_user_name(String gemt_notes_user_name) {
		this.gemt_notes_user_name = gemt_notes_user_name;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the xml_real_path
	 */
	public String getXml_real_path() {
		return xml_real_path;
	}

	/**
	 * @param xml_real_path the xml_real_path to set
	 */
	public void setXml_real_path(String xml_real_path) {
		this.xml_real_path = xml_real_path;
	}


	
}
