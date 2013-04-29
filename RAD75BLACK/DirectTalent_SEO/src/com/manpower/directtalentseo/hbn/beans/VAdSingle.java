package com.manpower.directtalentseo.hbn.beans;

import java.util.Date;

public class VAdSingle {

	private long id; 
	private long advertcontactid;
	private String sitename;
	private String language;
	private String jobtitle; 
	private String jobdescription; 
	private String candidateprofile; 
	private String candidateskills; 
	private String industry_sector; 
	private String location; 
	private String payrange; 
	private String countrycode;
	private String referencenumber;
	private String contracttype;
	private String branch_name;
	private String branch_address1;
	private String branch_address2;
	private String branch_city;
	private String branch_zipcode;
	private String branch_state;
	
	private Date publicationdate;
	private Date expirationdate;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getAdvertcontactid() {
		return advertcontactid;
	}
	public void setAdvertcontactid(long advertcontactid) {
		this.advertcontactid = advertcontactid;
	}
	public String getSitename() {
		return sitename;
	}
	public void setSitename(String sitename) {
		this.sitename = sitename;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getJobtitle() {
		return jobtitle;
	}
	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}
	public String getJobdescription() {
		return jobdescription;
	}
	public void setJobdescription(String jobdescription) {
		this.jobdescription = jobdescription;
	}
	public String getCandidateprofile() {
		return candidateprofile;
	}
	public void setCandidateprofile(String candidateprofile) {
		this.candidateprofile = candidateprofile;
	}
	public String getCandidateskills() {
		return candidateskills;
	}
	public void setCandidateskills(String candidateskills) {
		this.candidateskills = candidateskills;
	}
	public String getIndustry_sector() {
		return industry_sector;
	}
	public void setIndustry_sector(String industry_sector) {
		this.industry_sector = industry_sector;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPayrange() {
		return payrange;
	}
	public void setPayrange(String payrange) {
		this.payrange = payrange;
	}
	public String getCountrycode() {
		return countrycode;
	}
	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}
	public String getReferencenumber() {
		return referencenumber;
	}
	public void setReferencenumber(String referencenumber) {
		this.referencenumber = referencenumber;
	}
	public Date getPublicationdate() {
		return publicationdate;
	}
	public void setPublicationdate(Date publicationdate) {
		this.publicationdate = publicationdate;
	}
	public Date getExpirationdate() {
		return expirationdate;
	}
	public void setExpirationdate(Date expirationdate) {
		this.expirationdate = expirationdate;
	}
	public String getContracttype() {
		return contracttype;
	}
	public void setContracttype(String contracttype) {
		this.contracttype = contracttype;
	}
	

	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	public String getBranch_address1() {
		return branch_address1;
	}
	public void setBranch_address1(String branch_address1) {
		this.branch_address1 = branch_address1;
	}
	public String getBranch_address2() {
		return branch_address2;
	}
	public void setBranch_address2(String branch_address2) {
		this.branch_address2 = branch_address2;
	}
	public String getBranch_city() {
		return branch_city;
	}
	public void setBranch_city(String branch_city) {
		this.branch_city = branch_city;
	}
	public String getBranch_zipcode() {
		return branch_zipcode;
	}
	public void setBranch_zipcode(String branch_zipcode) {
		this.branch_zipcode = branch_zipcode;
	}
	public String getBranch_state() {
		return branch_state;
	}
	public void setBranch_state(String branch_state) {
		this.branch_state = branch_state;
	}
	public String getJobDescByWord(int numOfWords){
		String [] temp = getJobdescription().split(" ");
		if (temp.length>0){
			if (temp.length < numOfWords ) return getJobdescription();
			StringBuffer sb = new StringBuffer();
			for (int i=0;i<numOfWords;i++){
				sb.append(temp[i]);
				if (temp[i].length()-1>-1){
					if (temp[i].substring((temp[i].length())-1).equals(".")){
						sb.append("  ");
					}else{
						sb.append(" ");
					}
				}
	
			}
			sb.append("...");
			return sb.toString();
		}else{
			return getJobdescription();
		}
	}
	
}
