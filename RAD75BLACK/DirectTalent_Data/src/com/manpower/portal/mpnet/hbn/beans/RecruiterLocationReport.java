/*
 * Created on Dec 19, 2006
 *
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;


/**
 * @author Dimitar Bakardzhiev
 * 
 * Persistent class for view V_RECRUITER_LOCATION_REPORT
 *
 */
public class RecruiterLocationReport implements Serializable
{
	private static final long serialVersionUID =    7737316643119858553L;
    private long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private long resumeId;
    private String preferredLocation;
    private long locationId;
    private long regionId;
    private long siteId;
    private String branchName;
    private Date resumeUpdatedOn;
	//private Blob resume;    
	private String language;
	private Date dateApplied;
	private Date lastLoginDate;
	private String rating;
	private String status;
	private boolean sentToFO;
	private String bgScore;
	private String chScore;
	private int countOfJobsAppliedFor;
	private int candidateRowCount;
	private int activeStatus;
	private boolean primaryResume;	
    
    /**
     * @return Returns the branchName.
     */
    public String getBranchName()
    {
        return branchName;
    }
    /**
     * @param branchName The branchName to set.
     */
    public void setBranchName(String branchName)
    {
        this.branchName = branchName;
    }
    /**
     * @return Returns the resumeUpdatedOn.
     */
    public Date getResumeUpdatedOn()
    {
        return resumeUpdatedOn;
    }
    /**
     * @param resumeUpdatedOn The resumeUpdatedOn to set.
     */
    public void setResumeUpdatedOn(Date resumeUpdatedOn)
    {
        this.resumeUpdatedOn = resumeUpdatedOn;
    }
    /**
     * @return Returns the firstName.
     */
    public String getFirstName()
    {
        return firstName;
    }
    /**
     * @param firstName The firstName to set.
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    /**
     * @return Returns the id.
     */
    public long getId()
    {
        return id;
    }
    /**
     * @param id The id to set.
     */
    public void setId(long id)
    {
        this.id = id;
    }
    /**
     * @return Returns the lastName.
     */
    public String getLastName()
    {
        return lastName;
    }
    /**
     * @param lastName The lastName to set.
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    /**
     * @return Returns the middleName.
     */
    public String getMiddleName()
    {
        return middleName;
    }
    /**
     * @param middleName The middleName to set.
     */
    public void setMiddleName(String middleName)
    {
        this.middleName = middleName;
    }
    /**
     * @return Returns the preferredLocation.
     */
    public String getPreferredLocation()
    {
        return preferredLocation;
    }
    /**
     * @param preferredLocation The preferredLocation to set.
     */
    public void setPreferredLocation(String preferredLocation)
    {
        this.preferredLocation = preferredLocation;
    }
    /**
     * @return Returns the resumeId.
     */
    public long getResumeId()
    {
        return resumeId;
    }
    /**
     * @param resumeId The resumeId to set.
     */
    public void setResumeId(long resumeId)
    {
        this.resumeId = resumeId;
    }
    /**
     * @return Returns the siteId.
     */
    public long getSiteId()
    {
        return siteId;
    }
    /**
     * @param siteId The siteId to set.
     */
    public void setSiteId(long siteId)
    {
        this.siteId = siteId;
    }

    
    /**
     * @return Returns the locationId.
     */
    public long getLocationId()
    {
        return locationId;
    }
    /**
     * @param locationId The locationId to set.
     */
    public void setLocationId(long locationId)
    {
        this.locationId = locationId;
    }
    /**
     * @return Returns the regionId.
     */
    public long getRegionId()
    {
        return regionId;
    }
    /**
     * @param regionId The regionId to set.
     */
    public void setRegionId(long regionId)
    {
        this.regionId = regionId;
    }
	/**
	 * @return Returns the resume.
	 */
/*	public Blob getResume() 
	{		
		return this.resume;
	}*/
	/**
	 * @param resume The resume to set.
	 */
/*	public void setResume(Blob resume) 
	{
		this.resume = resume;
	}  */  
    /**
     * @return Returns the language.
     */
    public String getLanguage()
    {
        return language;
    }
    /**
     * @param language The language to set.
     */
    public void setLanguage(String language)
    {
        this.language = language;
    }
    /**
     * @return Returns the bgScore.
     */
    public String getBgScore()
    {
        return bgScore;
    }
    /**
     * @param bgScore The bgScore to set.
     */
    public void setBgScore(String bgScore)
    {
        this.bgScore = bgScore;
    }
    /**
     * @return Returns the chScore.
     */
    public String getChScore()
    {
        return chScore;
    }
    /**
     * @param chScore The chScore to set.
     */
    public void setChScore(String chScore)
    {
        this.chScore = chScore;
    }

    /**
     * @return Returns the countOfJobsAppliedFor.
     */
    public int getCountOfJobsAppliedFor()
    {
        return countOfJobsAppliedFor;
    }
    /**
     * @param countOfJobsAppliedFor The countOfJobsAppliedFor to set.
     */
    public void setCountOfJobsAppliedFor(int countOfJobsAppliedFor)
    {
        this.countOfJobsAppliedFor = countOfJobsAppliedFor;
    }
    /**
     * @return Returns the dateApplied.
     */
    public Date getDateApplied()
    {
        return dateApplied;
    }
    /**
     * @param dateApplied The dateApplied to set.
     */
    public void setDateApplied(Date dateApplied)
    {
        this.dateApplied = dateApplied;
    }
    /**
     * @return Returns the lastLoginDate.
     */
    public Date getLastLoginDate()
    {
        return lastLoginDate;
    }
    /**
     * @param lastLoginDate The lastLoginDate to set.
     */
    public void setLastLoginDate(Date lastLoginDate)
    {
        this.lastLoginDate = lastLoginDate;
    }
    /**
     * @return Returns the rating.
     */
    public String getRating()
    {
        return rating;
    }
    /**
     * @param rating The rating to set.
     */
    public void setRating(String rating)
    {
        this.rating = rating;
    }
    /**
     * @return Returns the sentToFO.
     */
    public boolean isSentToFO()
    {
        return sentToFO;
    }
    /**
     * @param sentToFO The sentToFO to set.
     */
    public void setSentToFO(boolean sentToFO)
    {
        this.sentToFO = sentToFO;
    }
    /**
     * @return Returns the status.
     */
    public String getStatus()
    {
        return status;
    }
    /**
     * @param status The status to set.
     */
    public void setStatus(String status)
    {
        this.status = status;
    }
    /**
     * @return Returns the candidateRowCount.
     */
    public int getCandidateRowCount()
    {
        return candidateRowCount;
    }
    /**
     * @param candidateRowCount The candidateRowCount to set.
     */
    public void setCandidateRowCount(int candidateRowCount)
    {
        this.candidateRowCount = candidateRowCount;
    }
	public int getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}
    /**
     * @return Returns the primaryResume.
     */
    public boolean isPrimaryResume()
    {
        return primaryResume;
    }
    /**
     * @param primaryResume The primaryResume to set.
     */
    public void setPrimaryResume(boolean primaryResume)
    {
        this.primaryResume = primaryResume;
    }
}
