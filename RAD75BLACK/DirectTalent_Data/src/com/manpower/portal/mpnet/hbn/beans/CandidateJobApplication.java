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
 * Persistent class for table CANDIDATEJOBAPPLICATIONS
 */
public class CandidateJobApplication extends Base{
	
	private static final long serialVersionUID =    3316456155023348806L;
	 private long id;
	 private long siteId;
	 private long advertisementID;
	 private String testTaken;
	 private Date  dateApplied;
	 private Date updatedOn;
	 private String updatedBy;
	 private Candidate candidate;
	 private Advertisement advertisement;
	 private String coverLetter;
	 private String cvName;
	 private Date cvUpdatedOn;
	 private String chScore;
	 private String bgScore;
	 private String chLink;
	 private String chTestLink;
	 private String candidateFirstName;
     private String rating;
     private boolean deleted;
     private String jobBoard;
     private String punchTime;
     private  byte[] resumeArray ;
     private Blob resume; 
     private String resumeMimeType;
     
	 
	/**
	 * @return Returns the application rating
	 */ 
	public String getRating() {
		return rating;
	}
    
    /**
     * @param rating Sets the application rating
     */
	public void setRating(String rating) {
		this.rating = rating;
	}
    
		/**
		 * @return Returns the chLink.
		 */
		public String getChLink() {
			return chLink;
		}
		/**
		 * @param chLink The chLink to set.
		 */
		public void setChLink(String chLink) {
			this.chLink = chLink;
		}
		
		
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
	 * @return Returns the advertisement.
	 */
	public Advertisement getAdvertisement() {
		return advertisement;
	}
	/**
	 * @param advertisement The advertisement to set.
	 */
	public void setAdvertisement(Advertisement advertisement) {
		this.advertisement = advertisement;
	}
	/**
	 * @return Returns the coverLetter.
	 */
	public String getCoverLetter() {
		return coverLetter;
	}
	/**
	 * @param coverLetter The coverLetter to set.
	 */
	public void setCoverLetter(String coverLetter) {
		this.coverLetter = coverLetter;
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
	 * @return Returns the cvUpdatedOn.
	 */
	public Date getCvUpdatedOn() {
		return cvUpdatedOn;
	}
	/**
	 * @param cvUpdatedOn The cvUpdatedOn to set.
	 */
	public void setCvUpdatedOn(Date cvUpdatedOn) {
		this.cvUpdatedOn = cvUpdatedOn;
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
	/**
	 * @return Returns the chScore.
	 */
	public String getChScore() {
		return chScore;
	}
	/**
	 * @param chScore The chScore to set.
	 */
	public void setChScore(String chScore) {
		this.chScore = chScore;
	}
	/**
	 * @return Returns the chTestLink.
	 */
	public String getChTestLink() {
		return chTestLink;
	}
	/**
	 * @param chTestLink The chTestLink to set.
	 */
	public void setChTestLink(String chTestLink) {
		this.chTestLink = chTestLink;
	}
	/**
	 * @return Returns the advertisementID.
	 */
	public long getAdvertisementID() {
		
		return getAdvertisement().getId();
	}
	/**
	 * @param advertisementID The advertisementID to set.
	 */
	public void setAdvertisementID(long advertisementID) {
		this.advertisementID = advertisementID;
	}
	/**
	 * @return Returns the candidateFirstName.
	 */
	public String getCandidateFirstName() {
		return getCandidate().getFirstName();
	}
	/**
	 * @param candidateFirstName The candidateFirstName to set.
	 */
	public void setCandidateFirstName(String candidateFirstName) {
		this.candidateFirstName = candidateFirstName;
	}
    /**
     * @return Returns the deleted.
     */
    public boolean isDeleted()
    {
        return deleted;
    }
    /**
     * @param deleted The deleted to set.
     */
    public void setDeleted(boolean deleted)
    {
        this.deleted = deleted;
    }
	/**
	 * @return Returns the jobBoard.
	 */
	public String getJobBoard() {
		return jobBoard;
	}
	/**
	 * @param jobBoard The jobBoard to set.
	 */
	public void setJobBoard(String jobBoard) {
		this.jobBoard = jobBoard;
	}
	/**
	 * @return Returns the punchTime.
	 */
	public String getPunchTime() {
		return punchTime;
	}
	/**
	 * @param punchTime The punchTime to set.
	 */
	public void setPunchTime(String punchTime) {
		this.punchTime = punchTime;
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

	public String getResumeMimeType() {
		return resumeMimeType;
	}

	public void setResumeMimeType(String resumeMimeType) {
		this.resumeMimeType = resumeMimeType;
	}	
}
