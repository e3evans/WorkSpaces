package com.manpower.portal.portlet.gemt.beans;

import com.ibm.portal.puma.User;

public class GEMTUserBean {
	
	private String locality;
	private String firstname;
	private String lastname;
	private String commonname;
	private String uid;
	
	
	public GEMTUserBean (){}
	
	public GEMTUserBean(User user){
		try{
			this.locality=(String) user.get("localityName");
			this.commonname=user.getCommonName().toString();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.firstname=user.getGivenName();
			this.lastname=user.getFamilyName();
			this.uid=user.getUserID();
		}
	}

	public String getCommonname() {
		return commonname;
	}

	public void setCommonname(String commonname) {
		this.commonname = commonname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	

}
