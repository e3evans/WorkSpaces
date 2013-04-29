/*
 * Created on Jun 29, 2007
 *
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

/**
 * @author Dimitar Bakardzhiev
 *
 */
public class CandidatesApplied implements Serializable
{
	private static final long serialVersionUID =5211353916544904667L;
    private long candidateId;
    private long advertisementId;
    private String firstName;
    private String lastName;
    private Date dateApplied;
    private Date lastLoginDate;
    private String rating;
    private String status;
    private String preferredBranch;    
    private boolean sentToFO;
    private String bgScore;
    private String chScore;
    private long jobAppID;
    private long activeCandidate;
    private String hasResume;
    
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
     * @return Returns the preferredBranch.
     */
    public String getPreferredBranch()
    {
        return preferredBranch;
    }
    /**
     * @param preferredBranch The preferredBranch to set.
     */
    public void setPreferredBranch(String preferredBranch)
    {
        this.preferredBranch = preferredBranch;
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
	public long getJobAppID() {
		return jobAppID;
	}
	public void setJobAppID(long jobAppID) {
		this.jobAppID = jobAppID;
	}
	public long getActiveCandidate() {
		return activeCandidate;
	}
	public void setActiveCandidate(long activeCandidate) {
		this.activeCandidate = activeCandidate;
	}
	public String getHasResume() {
		return hasResume;
	}
	public void setHasResume(String hasResume) {
		this.hasResume = hasResume;
	}

}
