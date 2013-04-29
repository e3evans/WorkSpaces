/*
 * Created on Jun 28, 2007
 *
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Dimitar Bakardzhiev
 *
 */
public class SummaryAdvertisements implements Serializable
{
	private static final long serialVersionUID =     -4445893974478431307L;
    private long id;
    private long recruiterId;
    private long branchId;
    private Date updatedOn;
    private Date publicationDate;
    private String jobTitle;
    private String referenceNumber;
    private Date expDate;
    private int appliedCount;
    private int externalAppliedCount;
    private String appliedFor;
    private String fromFrontOffice;
	
    public String getAppliedFor() {
		return appliedFor;
	}
    
	public void setAppliedFor(String appliedFor) {
		this.appliedFor = appliedFor;
	}
    
	public String getFromFrontOffice() {
		return fromFrontOffice;
	}
    
	public void setFromFrontOffice(String fromFrontOffice) {
		this.fromFrontOffice = fromFrontOffice;
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
     * @return Returns the appliedCount.
     */
    public int getAppliedCount()
    {
        return appliedCount;
    }
    /**
     * @param appliedCount The appliedCount to set.
     */
    public void setAppliedCount(int appliedCount)
    {
        this.appliedCount = appliedCount;
    }
    /**
     * @return Returns the branchId.
     */
    public long getBranchId()
    {
        return branchId;
    }
    /**
     * @param branchId The branchId to set.
     */
    public void setBranchId(long branchId)
    {
        this.branchId = branchId;
    }
    /**
     * @return Returns the expDate.
     */
    public Date getExpDate()
    {
        return expDate;
    }
    /**
     * @param expDate The expDate to set.
     */
    public void setExpDate(Date expDate)
    {
        this.expDate = expDate;
    }
    /**
     * @return Returns the jobTitle.
     */
    public String getJobTitle()
    {
        return jobTitle;
    }
    /**
     * @param jobTitle The jobTitle to set.
     */
    public void setJobTitle(String jobTitle)
    {
        this.jobTitle = jobTitle;
    }

    /**
     * @return Returns the recruiterId.
     */
    public long getRecruiterId()
    {
        return recruiterId;
    }
    /**
     * @param recruiterId The recruiterId to set.
     */
    public void setRecruiterId(long recruiterId)
    {
        this.recruiterId = recruiterId;
    }
    /**
     * @return Returns the referenceNumber.
     */
    public String getReferenceNumber()
    {
        return referenceNumber;
    }
    /**
     * @param referenceNumber The referenceNumber to set.
     */
    public void setReferenceNumber(String referenceNumber)
    {
        this.referenceNumber = referenceNumber;
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
	 * @return Returns the externalAppliedCount.
	 */
	public int getExternalAppliedCount() {
		return externalAppliedCount;
	}
	/**
	 * @param externalAppliedCount The externalAppliedCount to set.
	 */
	public void setExternalAppliedCount(int externalAppliedCount) {
		this.externalAppliedCount = externalAppliedCount;
	}
	/**
	 * @return Returns the publicationDate.
	 */
	public Date getPublicationDate() {
		return publicationDate;
	}
	/**
	 * @param publicationDate The publicationDate to set.
	 */
	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}
}
