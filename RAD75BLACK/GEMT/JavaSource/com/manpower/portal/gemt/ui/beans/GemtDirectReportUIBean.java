package com.manpower.portal.gemt.ui.beans;

import com.ibm.portal.puma.User;
import com.manpower.portal.portlet.gemt.GEMTManagerServlet;

public class GemtDirectReportUIBean extends BaseUIBean {

	
	private String gemt_sum_empname;
	private String gemt_sum_mgremail; 
	private String gemt_sum_region;
	private String gemt_sum_empemail;
	
	private String gemt_sum_ui_action_remove = GEMTManagerServlet.ACTION_REMOVEUSER;
	private String gemt_sum_ui_action_pickuser = GEMTManagerServlet.ACTION_PICKUSER;
	private String gemt_sum_ui_action_adduser= GEMTManagerServlet.ACTION_ADDUSER;
	
	public GemtDirectReportUIBean(){}
	
	public GemtDirectReportUIBean(User user){
		try{
			this.gemt_sum_empname = user.getCommonName();
//			System.out.println("USER ID:  "+user.get("uid").toString());
			this.gemt_sum_empemail = user.get("uid").toString();
			this.gemt_sum_region = user.get("localityName").toString();
			setUpdateModified();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public String getGemt_sum_empemail() {
		return gemt_sum_empemail;
	}
	public void setGemt_sum_empemail(String gemt_sum_empemail) {
		this.gemt_sum_empemail = gemt_sum_empemail;
	}
	public String getGemt_sum_empname() {
		return gemt_sum_empname;
	}
	public void setGemt_sum_empname(String gemt_sum_empname) {
		this.gemt_sum_empname = gemt_sum_empname;
	}
	public String getGemt_sum_mgremail() {
		return gemt_sum_mgremail;
	}
	public void setGemt_sum_mgremail(String gemt_sum_mgremail) {
		this.gemt_sum_mgremail = gemt_sum_mgremail;
	}
	public String getGemt_sum_region() {
		return gemt_sum_region;
	}
	public void setGemt_sum_region(String gemt_sum_region) {
		this.gemt_sum_region = gemt_sum_region;
	}
	public String getGemt_sum_ui_action_remove() {
		return gemt_sum_ui_action_remove;
	}
	public void setGemt_sum_ui_action_remove(String gemt_sum_ui_action_remove) {
		this.gemt_sum_ui_action_remove = gemt_sum_ui_action_remove;
	}

	public String getGemt_sum_ui_action_adduser() {
		return gemt_sum_ui_action_adduser;
	}

	public void setGemt_sum_ui_action_adduser(String gemt_sum_ui_action_adduser) {
		this.gemt_sum_ui_action_adduser = gemt_sum_ui_action_adduser;
	}

	public String getGemt_sum_ui_action_pickuser() {
		return gemt_sum_ui_action_pickuser;
	}

	public void setGemt_sum_ui_action_pickuser(String gemt_sum_ui_action_pickuser) {
		this.gemt_sum_ui_action_pickuser = gemt_sum_ui_action_pickuser;
	}
	
	
	
}
