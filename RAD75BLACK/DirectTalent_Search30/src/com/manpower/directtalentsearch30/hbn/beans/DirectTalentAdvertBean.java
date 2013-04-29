package com.manpower.directtalentsearch30.hbn.beans;

import java.util.Date;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.TermVector;

public class DirectTalentAdvertBean {

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
	
	
	private Document luceneDocument;
	
	public Document getLuceneDocument(){
		luceneDocument = new Document();
		luceneDocument.add(new Field("adid",Long.toString(getId()),Field.Store.YES,Field.Index.ANALYZED));
		luceneDocument.add(new Field("advertcontactid",Long.toString(getAdvertcontactid()),Field.Store.YES,Field.Index.ANALYZED));
		if (getSitename()!=null)luceneDocument.add(new Field("sitename",getSitename(),Field.Store.YES,Field.Index.ANALYZED));
		if (getJobtitle()!=null)luceneDocument.add(new Field("jobtitle",getJobtitle(),Field.Store.YES,Field.Index.ANALYZED));
		if (getJobdescription()!=null)luceneDocument.add(new Field("jobdescription",getJobdescription(),Field.Store.YES,Field.Index.ANALYZED,TermVector.YES));
		if (getCandidateprofile()!=null)luceneDocument.add(new Field("candidateprofile",getCandidateprofile(),Field.Store.YES,Field.Index.ANALYZED));
		if (getCandidateskills()!=null)luceneDocument.add(new Field("candidateskills",getCandidateskills(),Field.Store.YES,Field.Index.ANALYZED,TermVector.YES));
		if (getIndustry_sector()!=null)luceneDocument.add(new Field("industry_sector",getIndustry_sector(),Field.Store.YES,Field.Index.ANALYZED));
		if (getLocation()!=null)luceneDocument.add(new Field("location",getLocation(),Field.Store.YES,Field.Index.ANALYZED,TermVector.YES));
		if (getPayrange()!=null)luceneDocument.add(new Field("payrange",getPayrange(),Field.Store.YES,Field.Index.ANALYZED,TermVector.YES));
		if (getCountrycode()!=null)luceneDocument.add(new Field("countrycode",getCountrycode(),Field.Store.YES,Field.Index.ANALYZED));
		if (getReferencenumber()!=null)luceneDocument.add(new Field("referencenumber",getReferencenumber(),Field.Store.YES,Field.Index.ANALYZED));
		if (getContracttype()!=null)luceneDocument.add(new Field("contracttype",getContracttype(),Field.Store.YES,Field.Index.ANALYZED,TermVector.YES));
		if (getBranch_name()!=null)luceneDocument.add(new Field("branch_name",getBranch_name(),Field.Store.YES,Field.Index.ANALYZED,TermVector.YES));
		if (getBranch_address1()!=null)luceneDocument.add(new Field("branch_address1",getBranch_address1(),Field.Store.YES,Field.Index.ANALYZED));
		if (getBranch_address2()!=null)luceneDocument.add(new Field("branch_address2",getBranch_address2(),Field.Store.YES,Field.Index.ANALYZED));
		if (getBranch_city()!=null)luceneDocument.add(new Field("branch_city",getBranch_city(),Field.Store.YES,Field.Index.ANALYZED));
		if (getBranch_zipcode()!=null)luceneDocument.add(new Field("branch_zipcode",getBranch_zipcode(),Field.Store.YES,Field.Index.ANALYZED));
		if (getBranch_state()!=null)luceneDocument.add(new Field("branch_state",getBranch_state(),Field.Store.YES,Field.Index.ANALYZED,TermVector.YES));
		
		if (getPublicationdate()!=null)luceneDocument.add(new Field("publicationdate",getPublicationdate().toString(),Field.Store.YES,Field.Index.ANALYZED));
		if (getExpirationdate()!=null)luceneDocument.add(new Field("expirationdate",getExpirationdate().toString(),Field.Store.YES,Field.Index.ANALYZED));
		
		
		
		
		return luceneDocument;
	}
	
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
