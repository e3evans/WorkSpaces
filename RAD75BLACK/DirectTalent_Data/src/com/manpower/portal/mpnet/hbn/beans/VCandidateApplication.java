/*
 * Created on Jun 19, 2006
 *
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;

import org.hibernate.Hibernate;

/**
 * @author peter.ivanov
 *
 * Persistent class for view V_CANDIDATEAPPLICATIONS
 */
public class VCandidateApplication implements Serializable {
	private static final long serialVersionUID =    4428824612911381108L;
	private  byte[] resumeArray ;	
	private long application_id;
	private long candidate_id;
	private long branchId;
	private String firstname;
	private String lastname;
	private String contact_by_email;
	private String branch_city;
	private String branchname;
	private String	testTaken;
	private String branch_address1;
	private String branch_address2;
	private String branch_state;
	private String branch_country;
	private String branch_postalcode;
	private String branch_phone;
	private String branch_fax;
	private String branch_email;
	private String site_country;
	private long 	resume_id,
					siteId;
	private String resume_name;
	private String lensId;
	private Blob resume;
	private String candidate_email;
	private String branch_language;	
	private String testScore, lensScore;
	
	private String 	advertisementNumber;
	private long	advertisementId;
	private Date dateApplied;		
	
	/**
	 * @return Returns the resume.
	 */
	public Blob getResume() {
		
		if(this.resumeArray != null){
		
			return Hibernate.createBlob(this.resumeArray);
			
		}else{
		
			return null;
			
		}
		//return resume;
		
	}
	/**
	 * @param resume The resume to set.
	 */
	public void setResume(Blob resume) {
		this.resume = resume;
		if(resume == null){}
		else
		{
		this.resumeArray = this.toByteArray(resume);
		}
	}
	 
		/**
	 * @return Returns the resumeArray.
	 */
	public byte[] getResumeArray() {
		return resumeArray;
	}
	/**
	 * @param resumeArray The resumeArray to set.
	 */
	public void setResumeArray(byte[] resumeArray) {
		this.resumeArray = resumeArray;
	}
	
	/**
	 * Convert java.sql.Blob to a byte array
	 * @param fromBlob
	 * @return
	 */
	 private byte[] toByteArray(Blob fromBlob) {  
		ByteArrayOutputStream baos = new ByteArrayOutputStream();  
		try {   
			return toByteArrayImpl(fromBlob, baos);  } 
			catch (SQLException e) 
			{   throw new RuntimeException(e);  
			}
		 	catch (IOException e) 
			{   throw new RuntimeException(e);  
			}
		 	finally 
			{   if (baos != null) 
				{    try 
					{     baos.close();    
					} 
					catch (IOException ex) {    }   
				}  
			} 
	} 
	 
	 /**
	  * Convert java.sql.Blob to a byte array
	  * 
	  * @param fromBlob
	  * @param baos
	  * @return
	  * @throws SQLException
	  * @throws IOException
	  */
	 private byte[] toByteArrayImpl(Blob fromBlob, ByteArrayOutputStream baos)  throws SQLException, IOException 
	 {  byte[] buf = new byte[40000];
	   InputStream is = fromBlob.getBinaryStream();
	 	  try 
	 	     {   for (;;) 
	 		{    int dataSize = is.read(buf);
	 		    if (dataSize == -1)     break;
	     			baos.write(buf, 0, dataSize);
	 	        }  
	 	      } 
	 	finally 
	 	{   if (is != null) 
	 		{    try 
	 			{     is.close();    } 
	 			catch (IOException ex) {    }
	 	       }
	         }  return baos.toByteArray();
	 	
	  }
	 
	/**
	 * @return Returns the branch_address1.
	 */
	public String getBranch_address1() {
		return branch_address1;
	}
	/**
	 * @param branch_address1 The branch_address1 to set.
	 */
	public void setBranch_address1(String branch_address1) {
		this.branch_address1 = branch_address1;
	}
	/**
	 * @return Returns the branch_address2.
	 */
	public String getBranch_address2() {
		return branch_address2;
	}
	/**
	 * @param branch_address2 The branch_address2 to set.
	 */
	public void setBranch_address2(String branch_address2) {
		this.branch_address2 = branch_address2;
	}
	/**
	 * @return Returns the branch_city.
	 */
	public String getBranch_city() {
		return branch_city;
	}
	/**
	 * @param branch_city The branch_city to set.
	 */
	public void setBranch_city(String branch_city) {
		this.branch_city = branch_city;
	}
	/**
	 * @return Returns the branch_country.
	 */
	public String getBranch_country() {
		return branch_country;
	}
	/**
	 * @param branch_country The branch_country to set.
	 */
	public void setBranch_country(String branch_country) {
		this.branch_country = branch_country;
	}
	/**
	 * @return Returns the branch_email.
	 */
	public String getBranch_email() {
		return branch_email;
	}
	/**
	 * @param branch_email The branch_email to set.
	 */
	public void setBranch_email(String branch_email) {
		this.branch_email = branch_email;
	}
	/**
	 * @return Returns the branch_fax.
	 */
	public String getBranch_fax() {
		return branch_fax;
	}
	/**
	 * @param branch_fax The branch_fax to set.
	 */
	public void setBranch_fax(String branch_fax) {
		this.branch_fax = branch_fax;
	}
	/**
	 * @return Returns the branch_phone.
	 */
	public String getBranch_phone() {
		return branch_phone;
	}
	/**
	 * @param branch_phone The branch_phone to set.
	 */
	public void setBranch_phone(String branch_phone) {
		this.branch_phone = branch_phone;
	}
	/**
	 * @return Returns the branch_postalcode.
	 */
	public String getBranch_postalcode() {
		return branch_postalcode;
	}
	/**
	 * @param branch_postalcode The branch_postalcode to set.
	 */
	public void setBranch_postalcode(String branch_postalcode) {
		this.branch_postalcode = branch_postalcode;
	}
	/**
	 * @return Returns the branch_state.
	 */
	public String getBranch_state() {
		return branch_state;
	}
	/**
	 * @param branch_state The branch_state to set.
	 */
	public void setBranch_state(String branch_state) {
		this.branch_state = branch_state;
	}
	/**
	 * @return Returns the branchname.
	 */
	public String getBranchname() {
		return branchname;
	}
	/**
	 * @param branchname The branchname to set.
	 */
	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}
	

	/**
	 * @return Returns the candidate_id.
	 */
	public long getCandidate_id() {
		return candidate_id;
	}
	/**
	 * @param candidate_id The candidate_id to set.
	 */
	public void setCandidate_id(long candidate_id) {
		this.candidate_id = candidate_id;
	}

	/**
	 * @return Returns the contact_by_email.
	 */
	public String getContact_by_email() {
		return contact_by_email;
	}
	/**
	 * @param contact_by_email The contact_by_email to set.
	 */
	public void setContact_by_email(String contact_by_email) {
		this.contact_by_email = contact_by_email;
	}
	/**
	 * @return Returns the firstname.
	 */
	public String getFirstname() {
		return firstname;
	}
	/**
	 * @param firstname The firstname to set.
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	/**
	 * @return Returns the lastname.
	 */
	public String getLastname() {
		return lastname;
	}
	/**
	 * @param lastname The lastname to set.
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	/**
	 * @return Returns the resume_id.
	 */
	public long getResume_id() {
		return resume_id;
	}
	/**
	 * @param resume_id The resume_id to set.
	 */
	public void setResume_id(long resume_id) {
		this.resume_id = resume_id;
	}
	/**
	 * @return Returns the resume_name.
	 */
	public String getResume_name() {
		return resume_name;
	}
	/**
	 * @param resume_name The resume_name to set.
	 */
	public void setResume_name(String resume_name) {
		this.resume_name = resume_name;
	}
	/**
	 * @return Returns the site_country.
	 */
	public String getSite_country() {
		return site_country;
	}
	/**
	 * @param site_country The site_country to set.
	 */
	public void setSite_country(String site_country) {
		this.site_country = site_country;
	}
	/**
	 * @return Returns the candidate_email.
	 */
	public String getCandidate_email() {
		return candidate_email;
	}
	/**
	 * @param candidate_email The candidate_email to set.
	 */
	public void setCandidate_email(String candidate_email) {
		this.candidate_email = candidate_email;
	}
	/**
	 * @return Returns the advertisementNumber.
	 */
	public String getAdvertisementNumber() {
		return advertisementNumber;
	}
	/**
	 * @param advertisementNumber The advertisementNumber to set.
	 */
	public void setAdvertisementNumber(String advertisementNumber) {
		this.advertisementNumber = advertisementNumber;
	}
	/**
	 * @return Returns the dateApplied.
	 */
	public Date getDateApplied() {
		return dateApplied;
	}
	/**
	 * @param dateApplied The dateApplied to set.
	 */
	public void setDateApplied(Date dateApplied) {
		this.dateApplied = dateApplied;
	}
	/**
	 * @return Returns the application_id.
	 */
	public long getApplication_id() {
		return application_id;
	}
	/**
	 * @param application_id The application_id to set.
	 */
	public void setApplication_id(long application_id) {
		this.application_id = application_id;
	}
	public String getBranch_language() {
		return branch_language;
	}
	public void setBranch_language(String branch_language) {
		this.branch_language = branch_language;
	}
	/**
	 * @return Returns the branchId.
	 */
	public long getBranchId() {
		return branchId;
	}
	/**
	 * @param branchId The branchId to set.
	 */
	public void setBranchId(long branchId) {
		this.branchId = branchId;
	}
	/**
	 * @return Returns the testTaken.
	 */
	public String getTestTaken() {
		return testTaken;
	}
	/**
	 * @param testTaken The testTaken to set.
	 */
	public void setTestTaken(String testTaken) {
		this.testTaken = testTaken;
	}
	/**
	 * @return Returns the lensScore.
	 */
	public String getLensScore() {
		return lensScore;
	}
	/**
	 * @param lensScore The lensScore to set.
	 */
	public void setLensScore(String lensScore) {
		this.lensScore = lensScore;
	}
	/**
	 * @return Returns the testScore.
	 */
	public String getTestScore() {
		return testScore;
	}
	/**
	 * @param testScore The testScore to set.
	 */
	public void setTestScore(String testScore) {
		this.testScore = testScore;
	}
	/**
	 * @return Returns the lensId.
	 */
	public String getLensId() {
		return lensId;
	}
	/**
	 * @param lensId The lensId to set.
	 */
	public void setLensId(String lensId) {
		this.lensId = lensId;
	}
	/**
	 * @return Returns the siteId.
	 */
	public long getSiteId() {
		return siteId;
	}
	/**
	 * @param siteId The siteId to set.
	 */
	public void setSiteId(long siteId) {
		this.siteId = siteId;
	}
	/**
	 * @return Returns the advertisementId.
	 */
	public long getAdvertisementId() {
		return advertisementId;
	}
	/**
	 * @param advertisementId The advertisementId to set.
	 */
	public void setAdvertisementId(long advertisementId) {
		this.advertisementId = advertisementId;
	}
}
