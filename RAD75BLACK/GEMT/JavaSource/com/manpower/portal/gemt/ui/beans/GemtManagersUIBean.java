package com.manpower.portal.gemt.ui.beans;

import java.util.ArrayList;
import java.util.List;

import com.ibm.portal.puma.User;
import com.manpower.portal.portlet.gemt.GEMTManagerServlet;

public class GemtManagersUIBean extends BaseUIBean {
	
	private String gemt_sum_managername; 
	private String gemt_sum_mgremail;
	private String gemt_sum_ui_action_remove = GEMTManagerServlet.ACTION_REMOVEUSER;
	private String gemt_sum_ui_action_pickuser = GEMTManagerServlet.ACTION_PICKUSER;
	private String gemt_sum_ui_action_adduser= GEMTManagerServlet.ACTION_ADDUSER;
	
	private List gemt_direct_reports = new ArrayList(); 
	
	public GemtManagersUIBean(){}
	
	public GemtManagersUIBean(User user) {
		try{
			this.gemt_sum_managername=user.getCommonName();
			this.gemt_sum_mgremail=user.getUserID();
			setUpdateModified();
		}catch(Exception e){
			e.printStackTrace();
		}
		// TODO Auto-generated constructor stub
	}
	public String getGemt_sum_managername() {
		return gemt_sum_managername;
	}
	public void setGemt_sum_managername(String gemt_sum_managername) {
		this.gemt_sum_managername = gemt_sum_managername;
	}
	public String getGemt_sum_mgremail() {
		return gemt_sum_mgremail;
	}
	public void setGemt_sum_mgremail(String gemt_sum_mgremail) {
		this.gemt_sum_mgremail = gemt_sum_mgremail;
	}
	public List getGemt_direct_reports() {
		return gemt_direct_reports;
	}
	public void setGemt_direct_reports(List gemt_direct_reports) {
		this.gemt_direct_reports = gemt_direct_reports;
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

	public String getGemt_sum_ui_action_remove() {
		return gemt_sum_ui_action_remove;
	}

	public void setGemt_sum_ui_action_remove(String gemt_sum_ui_action_remove) {
		this.gemt_sum_ui_action_remove = gemt_sum_ui_action_remove;
	} 
	
	
	
}
