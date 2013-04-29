/*
 * Created on Jun 14, 2007
 *
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;

/**
 * @author Dimitar Bakardzhiev
 *
 */
public class FlexField implements Serializable
{
	private static final long serialVersionUID = -5534090590298571155L;
    private long id;
    private long siteId;
    private Candidate candidate;
    private String flexInput1;
    private String flexInput2;
    private String flexList1;
    private String flexList2;
    
    
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
     * @return Returns the flexInput1.
     */
    public String getFlexInput1()
    {
        return flexInput1;
    }
    /**
     * @param flexInput1 The flexInput1 to set.
     */
    public void setFlexInput1(String flexInput1)
    {
        this.flexInput1 = flexInput1;
    }
    /**
     * @return Returns the flexInput2.
     */
    public String getFlexInput2()
    {
        return flexInput2;
    }
    /**
     * @param flexInput2 The flexInput2 to set.
     */
    public void setFlexInput2(String flexInput2)
    {
        this.flexInput2 = flexInput2;
    }
    /**
     * @return Returns the flexList1.
     */
    public String getFlexList1()
    {
        return flexList1;
    }
    /**
     * @param flexList1 The flexList1 to set.
     */
    public void setFlexList1(String flexList1)
    {
        this.flexList1 = flexList1;
    }
    /**
     * @return Returns the flexList2.
     */
    public String getFlexList2()
    {
        return flexList2;
    }
    /**
     * @param flexList2 The flexList2 to set.
     */
    public void setFlexList2(String flexList2)
    {
        this.flexList2 = flexList2;
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
