package com.manpower.directalent.reporting.hbn.beans;

import java.util.ResourceBundle;

public class BaseBean {
	
	protected String flagpath=" ";
	public String getFlagpath() {
		ResourceBundle rb = ResourceBundle.getBundle("com.manpower.directalent.reporting.config.flagmapping");
		try{
			this.flagpath = (String)rb.getString("directorymapping")+(String)rb.getString(getSitecode());
		}catch (Exception e){
			//NOT FOUND THEN PUT THE DEFAULT IN THERE....
			this.flagpath = (String)rb.getString("directorymapping")+(String)rb.getString("defaultflag");
		}
		
		return flagpath;
	}
	
	public String getSitecode(){
		return "";
	}

}
