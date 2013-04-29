/*
 * Created on Sep 21, 2007
 *
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;

import org.hibernate.Hibernate;

/**
 * @author ssprout1
 *
 * Persistent Class for table APPLICANTRESPONSE
 */
public class ApplicantResponse extends Base {

	/*
	 * 
create table APPLICANTRESPONSE
(
  ID             NUMBER not null,
  SITE_ID        NUMBER(10) not null,
  ADVERT_ID      NUMBER(10) not null,
  EXTERNALID     VARCHAR2(32),
  EMAIL          VARCHAR2(100),
  FIRSTNAME      VARCHAR2(50),
  MIDDLENAME     VARCHAR2(50),
  LASTNAME       VARCHAR2(50),
  PHONEONE       VARCHAR2(50),
  PHONETWO       VARCHAR2(50),
  PHONETHREE     VARCHAR2(50),
  ADDRESSLINEONE VARCHAR2(200),
  ADDRESSLINETWO VARCHAR2(200),
  CITY           VARCHAR2(50),
  STATE          VARCHAR2(50),
  COUNTRY        VARCHAR2(25),
  POSTALCODE     VARCHAR2(15),
  RESUME         BLOB,
  LENS_ID        VARCHAR2(20),
  MIME_TYPE      VARCHAR2(50),
  CREATED_ON     DATE,
  CREATED_BY     VARCHAR2(200),
  CHANGED_ON     DATE,
  CHANGED_BY     VARCHAR2(200),
  IPHONE_UID	 VARCHAR2(40)
)	 */
	private static final long serialVersionUID = 8097193326652092995L;
	private long id;
	private long siteId;
	private long advertId;
	private String externalId;
	private String email;
	private String firstName;
	private String middleName;
	private String lastName;
	private String phoneOne;
	private String phoneTwo;
	private String phoneThree;
	private String addressLineOne;
	private String addressLineTwo;
	private String city;
	private String state;
	private String country;
	private String postalCode;
	private String rating;
	private String bgScore;
	private Blob resume ;
	private byte[] resumeArray ;
	private String lensId;
	private String mimeType;
	private Date created_On;
	private String createdBy;
	private Date changed_On;
	private String updatedBy;
	private String resumeName;
	private long rawRespId;
	private String iphoneUID;
	
	
	/**
	 * @return Returns the addressLineOne.
	 */
	public String getAddressLineOne() {
		return addressLineOne;
	}
	/**
	 * @param addressLineOne The addressLineOne to set.
	 */
	public void setAddressLineOne(String addressLineOne) {
		this.addressLineOne = addressLineOne;
	}
	/**
	 * @return Returns the addressLineTwo.
	 */
	public String getAddressLineTwo() {
		return addressLineTwo;
	}
	/**
	 * @param addressLineTwo The addressLineTwo to set.
	 */
	public void setAddressLineTwo(String addressLineTwo) {
		this.addressLineTwo = addressLineTwo;
	}
	/**
	 * @return Returns the advertId.
	 */
	public long getAdvertId() {
		return advertId;
	}
	/**
	 * @param advertId The advertId to set.
	 */
	public void setAdvertId(long advertId) {
		this.advertId = advertId;
	}
	/**
	 * @return Returns the city.
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city The city to set.
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return Returns the country.
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country The country to set.
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return Returns the email.
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email The email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return Returns the externalId.
	 */
	public String getExternalId() {
		return externalId;
	}
	/**
	 * @param externalId The externalId to set.
	 */
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	/**
	 * @return Returns the firstName.
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName The firstName to set.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return Returns the id.
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return Returns the lastName.
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName The lastName to set.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	 * @return Returns the middleName.
	 */
	public String getMiddleName() {
		return middleName;
	}
	/**
	 * @param middleName The middleName to set.
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	/**
	 * @return Returns the mimeType.
	 */
	public String getMimeType() {
		return mimeType;
	}
	/**
	 * @param mimeType The mimeType to set.
	 */
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	/**
	 * @return Returns the phoneOne.
	 */
	public String getPhoneOne() {
		return phoneOne;
	}
	/**
	 * @param phoneOne The phoneOne to set.
	 */
	public void setPhoneOne(String phoneOne) {
		this.phoneOne = phoneOne;
	}
	/**
	 * @return Returns the phoneThree.
	 */
	public String getPhoneThree() {
		return phoneThree;
	}
	/**
	 * @param phoneThree The phoneThree to set.
	 */
	public void setPhoneThree(String phoneThree) {
		this.phoneThree = phoneThree;
	}
	/**
	 * @return Returns the phoneTwo.
	 */
	public String getPhoneTwo() {
		return phoneTwo;
	}
	/**
	 * @param phoneTwo The phoneTwo to set.
	 */
	public void setPhoneTwo(String phoneTwo) {
		this.phoneTwo = phoneTwo;
	}
	/**
	 * @return Returns the postalCode.
	 */
	public String getPostalCode() {
		return postalCode;
	}
	/**
	 * @param postalCode The postalCode to set.
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
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
	 * @return Returns the resume.
	 */
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
	 * @return Returns the state.
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state The state to set.
	 */
	public void setState(String state) {
		this.state = state;
	}


	/**
	 * Convert java.sql.Blob object to a byte array
	 * 
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
	  * Convert java.sql.Blob object to a byte array
	  * 
	  * @param fromBlob
	  * @param baos
	  * @return byte array
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
	 * @return Returns the changed_On.
	 */
	public Date getChanged_On() {
		return changed_On;
	}
	/**
	 * @param changed_On The changed_On to set.
	 */
	public void setChanged_On(Date changed_On) {
		this.changed_On = changed_On;
	}
	/**
	 * @return Returns the created_On.
	 */
	public Date getCreated_On() {
		return created_On;
	}
	/**
	 * @param created_On The created_On to set.
	 */
	public void setCreated_On(Date created_On) {
		this.created_On = created_On;
	}
	/**
	 * @return Returns the rating.
	 */
	public String getRating() {
		return rating;
	}
	/**
	 * @param rating The rating to set.
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}
	/**
	 * @return Returns the bgScore.
	 */
	public String getBgScore() {
		return bgScore;
	}
	/**
	 * @param bgScore The bgScore to set.
	 */
	public void setBgScore(String bgScore) {
		this.bgScore = bgScore;
	}
	public String getResumeName() {
		return resumeName;
	}
	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}
	public String getIphoneUID() {
		return iphoneUID;
	}
	public void setIphoneUID(String iphoneUID) {
		this.iphoneUID = iphoneUID;
	}
	public long getRawRespId() {
		return rawRespId;
	}
	public void setRawRespId(long rawRespId) {
		this.rawRespId = rawRespId;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
}
