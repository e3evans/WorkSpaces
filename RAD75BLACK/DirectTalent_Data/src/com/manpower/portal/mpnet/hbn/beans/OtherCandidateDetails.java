/*
 * Created on 2007-6-15
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.util.Date;


/**
 * @author Miroslav Nachev
 *
 * 
 * Persistent class for table Other_Candidate_Details
 */
public class OtherCandidateDetails
	extends Base
{
	private static final long serialVersionUID =  4999259615239415837L;
	private long id;
	private long siteId;

	private String marketingQuestionCode;
	private String marketingQuestionAnswer;

	private Date updatedOn;
	private String updatedBy;
	private Candidate candidate;

	private Date lastLoginDate;
	private boolean onAssignement;
	private Date onAssignementAsOfDate;
	private Date emailRemainderLastDate;
	private boolean sentToFO;
	private boolean secureSelfServiceEnabled;
	private String resetPasswordToken;
	private String reviewStatus;

	
    /**
     * @return Returns the marketingQuestionAnswer.
     */
    public String getMarketingQuestionAnswer()
    {
        return marketingQuestionAnswer;
    }

    /**
     * @param answer The marketingQuestionAnswer to set.
     */
    public void setMarketingQuestionAnswer(String marketingQuestionAnswer)
    {
        this.marketingQuestionAnswer = marketingQuestionAnswer;
    }

    /**
     * @return Returns the candidate.
     */
    public Candidate getCandidate()
    {
        return candidate;
    }

    /**
     * @param candidate The candidate to set.
     */
    public void setCandidate(Candidate candidate)
    {
        this.candidate = candidate;
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
     * @return Returns the marketingQuestionCode.
     */
    public String getMarketingQuestionCode()
    {
        return marketingQuestionCode;
    }

    /**
     * @param questionCode The marketingQuestionCode to set.
     */
    public void setMarketingQuestionCode(String marketingQuestionCode)
    {
        this.marketingQuestionCode = marketingQuestionCode;
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
     * @return Returns the updatedBy.
     */
    public String getUpdatedBy()
    {
        return updatedBy;
    }

    /**
     * @param updatedBy The updatedBy to set.
     */
    public void setUpdatedBy(String updatedBy)
    {
        this.updatedBy = updatedBy;
    }

    /**
     * @return Returns the updatedOn.
     */
    public Date getUpdatedOn()
    {
        return updatedOn;
    }

    /**
     * @param updatedOn The updatedOn to set.
     */
    public void setUpdatedOn(Date updatedOn)
    {
        this.updatedOn = updatedOn;
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
     * @return Returns the onAssignementAsOfDate.
     */
    public Date getOnAssignementAsOfDate()
    {
        return onAssignementAsOfDate;
    }
    /**
     * @param onAssignementAsOfDate The onAssignementAsOfDate to set.
     */
    public void setOnAssignementAsOfDate(Date onAssignementAsOfDate)
    {
        this.onAssignementAsOfDate = onAssignementAsOfDate;
    }
    /**
     * @return Returns the onAssignement.
     */
    public boolean isOnAssignement()
    {
        return onAssignement;
    }
    /**
     * @param onAssignement The onAssignement to set.
     */
    public void setOnAssignement(boolean onAssignement)
    {
        this.onAssignement = onAssignement;
    }
    /**
     * @return Returns the emailRemainderLastDate.
     */
    public Date getEmailRemainderLastDate()
    {
        return emailRemainderLastDate;
    }
    /**
     * @param emailRemainderLastDate The emailRemainderLastDate to set.
     */
    public void setEmailRemainderLastDate(Date emailRemainderLastDate)
    {
        this.emailRemainderLastDate = emailRemainderLastDate;
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
    public boolean isSecureSelfServiceEnabled() {
		return secureSelfServiceEnabled;
	}

	public void setSecureSelfServiceEnabled(boolean secureSelfServiceEnabled) {
		this.secureSelfServiceEnabled = secureSelfServiceEnabled;
	}
	
	public String getResetPasswordToken() {
		return resetPasswordToken;
	}

	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}

	/**
	 * @return Returns the reviewStatus.
	 */
	public String getReviewStatus() {
		return reviewStatus;
	}
	/**
	 * @param reviewStatus The reviewStatus to set.
	 */
	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

}
