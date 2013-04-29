/*
 * Created on Sep 16, 2006
 *
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Dimitar Bakardzhiev
 * 
 * Persistent class for view V_RECRUITER_REPORT
 *
 */
public class RecruiterReport implements Serializable
{
	private static final long serialVersionUID =     -8995756511086804797L;
    private long candidateId;
    private String candidateFirstName;
    private String candidateLastName;
    private String candidateMiddleName;
    private long resumeId;
    private String preferredBranchName;
    private long siteId;
    private long advertisementId;
    private Date  dateApplied;    
    
	private Date lastLoginDate;
	private String rating;
	private String status;
	private boolean sentToFO;
	private String bgScore;
	private String chScore;
	private int countOfJobsAppliedFor;  
	private boolean activeStatus;
	private boolean primaryResume;		
    /**
     * @return Returns the advertisementId.
     */
    public long getAdvertisementId()
    {
        return advertisementId;
    }
    /**
     * @param advertisementId The advertisementId to set.
     */
    public void setAdvertisementId(long advertisementId)
    {
        this.advertisementId = advertisementId;
    }
    /**
     * @return Returns the candidateFirstName.
     */
    public String getCandidateFirstName()
    {
        return candidateFirstName;
    }
    /**
     * @param candidateFirstName The candidateFirstName to set.
     */
    public void setCandidateFirstName(String candidateFirstName)
    {
        this.candidateFirstName = candidateFirstName;
    }
    /**
     * @return Returns the candidateId.
     */
    public long getCandidateId()
    {
        return candidateId;
    }
    /**
     * @param candidateId The candidateId to set.
     */
    public void setCandidateId(long candidateId)
    {
        this.candidateId = candidateId;
    }
    /**
     * @return Returns the candidateLastName.
     */
    public String getCandidateLastName()
    {
        return candidateLastName;
    }
    /**
     * @param candidateLastName The candidateLastName to set.
     */
    public void setCandidateLastName(String candidateLastName)
    {
        this.candidateLastName = candidateLastName;
    }
    /**
     * @return Returns the preferredBranchName.
     */
    public String getPreferredBranchName()
    {
        return preferredBranchName;
    }
    /**
     * @param preferredBranchName The preferredBranchName to set.
     */
    public void setPreferredBranchName(String preferredBranchName)
    {
        this.preferredBranchName = preferredBranchName;
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
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj)
    {
        if(obj==this)
        {
            return true;
        }
        
        if(!(obj instanceof RecruiterReport))
        {
            return false;           
        }
        
        RecruiterReport recReport=(RecruiterReport)obj;
        
        return (recReport.advertisementId==this.advertisementId &&
                recReport.candidateId==this.candidateId &&
                recReport.resumeId==this.resumeId &&
                recReport.siteId==this.siteId);
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode()
    {
        int hashValue=11;
        
        hashValue=31*hashValue+(int) (advertisementId ^ (advertisementId >>> 32));
        hashValue=31*hashValue+(int) (candidateId ^ (candidateId>>> 32));
        hashValue=31*hashValue+(int) (resumeId ^ (resumeId >>> 32));
        hashValue=31*hashValue+(int) (siteId ^ (siteId >>> 32));
        
        return hashValue;
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
	 * @return Returns the candidateMiddleName.
	 */
	public String getCandidateMiddleName() {
		return candidateMiddleName;
	}
	/**
	 * @param candidateMiddleName The candidateMiddleName to set.
	 */
	public void setCandidateMiddleName(String candidateMiddleName) {
		this.candidateMiddleName = candidateMiddleName;
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
     * @return Returns the activeStatus.
     */
    public boolean isActiveStatus()
    {
        return activeStatus;
    }
    /**
     * @param activeStatus The activeStatus to set.
     */
    public void setActiveStatus(boolean activeStatus)
    {
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
