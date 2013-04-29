/*
 * Created on Jan 18, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.gemt.hbn.beans;


/**
 * @author Eric Evans
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class GemtNotesHbnBean extends BaseHibernateBean {

	private long id ;
	
	private String gemt_notes_user_name;
	private String gemt_notes_user_email; 
	private String gemt_notes_content;
	
	public String getGemt_notes_content() {
		return gemt_notes_content;
	}
	public void setGemt_notes_content(String gemt_notes_content) {
		this.gemt_notes_content = gemt_notes_content;
	}
	public String getGemt_notes_user_email() {
		return gemt_notes_user_email;
	}
	public void setGemt_notes_user_email(String gemt_notes_user_email) {
		this.gemt_notes_user_email = gemt_notes_user_email;
	}
	public String getGemt_notes_user_name() {
		return gemt_notes_user_name;
	}
	public void setGemt_notes_user_name(String gemt_notes_user_name) {
		this.gemt_notes_user_name = gemt_notes_user_name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	

}
