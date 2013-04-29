package com.manpower.portal.mpnet.hbn.beans;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;

import org.hibernate.Hibernate;

public class CandidateSearch implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4938148987227453056L;
	/**
	 * 
	 */
	
	/**
	 * 
	 */

	
	private long id;
	private long siteId;
	private long candidateId;
	private long applicantId;
	private long branchId;
	private long locationId;
	
	private String email;
	private String userId;
	private String firstName;
	private String lastName;
	private String middleName;
	private String phoneNumber;
	private String city;
	private String state;
	private String postalCode;
	private String country;
	private String resumeName;
	private String mimeType;
	private String status;
	private String score;
	
	private Date createdOn;
	private Date lastLoginDate;
	private Date dateAppliedOn;
	private Date lastResumeDate;
	private Date birthDate;
	
	private Blob resume;
	private  byte[] resumeArray ;
	private double latitude;
	private double longitude;
	
	private int geoCodeStatus;
	
	private String comment;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getSiteId() {
		return siteId;
	}
	public void setSiteId(long siteId) {
		this.siteId = siteId;
	}
	public long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(long candidateId) {
		this.candidateId = candidateId;
	}
	public long getApplicantId() {
		return applicantId;
	}
	public void setApplicantId(long applicantId) {
		this.applicantId = applicantId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getResumeName() {
		return resumeName;
	}
	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public Date getDateAppliedOn() {
		return dateAppliedOn;
	}
	public void setDateAppliedOn(Date dateAppliedOn) {
		this.dateAppliedOn = dateAppliedOn;
	}
	public Date getLastResumeDate() {
		return lastResumeDate;
	}
	public void setLastResumeDate(Date lastResumeDate) {
		this.lastResumeDate = lastResumeDate;
	}
	public Blob getResume() {
		
		if(this.resumeArray != null){
			return Hibernate.createBlob(this.resumeArray);
		}else{
			return null;
		}
		
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
	public long getBranchId() {
		return branchId;
	}
	public void setBranchId(long branchId) {
		this.branchId = branchId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public byte[] getResumeArray() {
		return resumeArray;
	}
	/**
	 * @param resumeArray The resumeArray to set.
	 */
	public void setResumeArray(byte[] resumeArray) {
		this.resumeArray = resumeArray;
	}
	
	private byte[] toByteArray(Blob fromBlob) {  
		ByteArrayOutputStream baos = new ByteArrayOutputStream();  
		try {   
			return toByteArrayImpl(fromBlob, baos);  } 
			catch (SQLException e) 
			{  
				e.printStackTrace();
				throw new RuntimeException(e);  
			}
		 	catch (IOException e) 
			{   e.printStackTrace();
		 		throw new RuntimeException(e);  
			}
		 	finally 
			{   if (baos != null) 
				{    try 
					{     baos.close();    
					}
					catch (IOException ex) {ex.printStackTrace();}   
				}  
			} 
	}
	
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
	 			catch (IOException ex) { ex.printStackTrace();   }
	 	       }
	         }  return baos.toByteArray();
	 	
	  }
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public long getLocationId() {
		return locationId;
	}
	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}
	public int getGeoCodeStatus() {
		return geoCodeStatus;
	}
	public void setGeoCodeStatus(int geoCodeStatus) {
		this.geoCodeStatus = geoCodeStatus;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
