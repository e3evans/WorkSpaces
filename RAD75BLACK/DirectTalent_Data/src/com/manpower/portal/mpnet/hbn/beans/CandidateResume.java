/*
 * Created on Jan 20, 2006
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
 * @author jsingh
 *
 * Persistent class for table CANDIDATERESUMES
 */
public class CandidateResume extends Base{
	private static final long serialVersionUID =   8903790881000613954L;
	private String cvName; 
	private long id;
	private long siteId;
	private Date  dateCreated;
	private Blob resume;
	private Date updatedOn;
	private String updatedBy;
	private Candidate candidate;
	private  byte[] resumeArray ;
	private String lensID;
	private String mimeType;
	private String resumeName;
	private boolean savedResume;
	private boolean defaultResume;
	private boolean myManpowerCreatedResume;
	/**
	 * @return Returns the candidate.
	 */
	public Candidate getCandidate() {
		return candidate;
	}
	/**
	 * @param candidate The candidate to set.
	 */
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	/**
	 * @return Returns the dateCreated.
	 */
	public Date getDateCreated() {
		return dateCreated;
	}
	/**
	 * @param dateCreated The dateCreated to set.
	 */
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
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
	 * @return Returns the updatedBy.
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}
	/**
	 * @param updatedBy The updatedBy to set.
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	/**
	 * @return Returns the updatedOn.
	 */
	public Date getUpdatedOn() {
		return updatedOn;
	}
	/**
	 * @param updatedOn The updatedOn to set.
	 */
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	 
	/**
	 * @return Returns the cvName.
	 */
	public String getCvName() {
		return cvName;
	}
	/**
	 * @param cvName The cvName to set.
	 */
	public void setCvName(String cvName) {
		this.cvName = cvName;
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
	 			catch (IOException ex) { ex.printStackTrace();   }
	 	       }
	         }  return baos.toByteArray();
	 	
	  }
	 
    /**
     * @return Returns the lensID.
     */
    public String getLensID()
    {
        return lensID;
    }
    /**
     * @param lensID The lensID to set.
     */
    public void setLensID(String lensID)
    {
        this.lensID = lensID;
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
	public String getResumeName() {
		return resumeName;
	}
	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}
	public boolean isSavedResume() {
		return savedResume;
	}
	public void setSavedResume(boolean tempResume) {
		this.savedResume = tempResume;
	}
	public boolean isDefaultResume() {
		return defaultResume;
	}
	public void setDefaultResume(boolean defaultResume) {
		this.defaultResume = defaultResume;
	}
	public boolean isMyManpowerCreatedResume() {
		return myManpowerCreatedResume;
	}
	public void setMyManpowerCreatedResume(boolean myManpowerCreatedResume) {
		this.myManpowerCreatedResume = myManpowerCreatedResume;
	}
	
	
}
