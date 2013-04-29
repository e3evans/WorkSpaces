/*
 * Created on Jun 6, 2007
 *
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;

/**
 * @author Dimitar Bakardzhiev
 *
 */
public class Consent implements Serializable
{
	private static final long serialVersionUID =     5193690890817382980L;
    private long id;
    private long siteId;
    private Candidate candidate;
    private String consentType;
    private String consentValue;
    
    
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
     * @return Returns the consentType.
     */
    public String getConsentType()
    {
        return consentType;
    }
    /**
     * @param consentType The consentType to set.
     */
    public void setConsentType(String consentType)
    {
        this.consentType = consentType;
    }
    /**
     * @return Returns the consentValue.
     */
    public String getConsentValue()
    {
        return consentValue;
    }
    /**
     * @param consentValue The consentValue to set.
     */
    public void setConsentValue(String consentValue)
    {
        this.consentValue = consentValue;
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
}
