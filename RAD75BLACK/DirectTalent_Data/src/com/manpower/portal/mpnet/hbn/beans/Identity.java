/*
 * Created on Jun 11, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;

/**
 * @author Miroslav Nachev
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Identity implements Serializable
{
	private static final long serialVersionUID = 3737005977548588027L;
    private Long id;
    private String key;
    private long nextValue;
    private long maxValue;
    private int incrementValue;
    private int cacheSize;
    private String description;


    /**
     * @return Returns the cacheSize.
     */
    public int getCacheSize()
    {
        return cacheSize;
    }

    /**
     * @param cacheSize The cacheSize to set.
     */
    public void setCacheSize(int cacheSize)
    {
        this.cacheSize = cacheSize;
    }

    /**
     * @return Returns the description.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param description The description to set.
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * @return Returns the id.
     */
    public Long getId()
    {
        return id;
    }

    /**
     * @param id The id to set.
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * @return Returns the incrementValue.
     */
    public int getIncrementValue()
    {
        return incrementValue;
    }

    /**
     * @param incrementValue The incrementValue to set.
     */
    public void setIncrementValue(int incrementValue)
    {
        this.incrementValue = incrementValue;
    }

    /**
     * @return Returns the key.
     */
    public String getKey()
    {
        return key;
    }

    /**
     * @param key The key to set.
     */
    public void setKey(String key)
    {
        this.key = key;
    }

    /**
     * @return Returns the nextValue.
     */
    public long getNextValue()
    {
        return nextValue;
    }

    /**
     * @param nextValue The nextValue to set.
     */
    public void setNextValue(long nextValue)
    {
        this.nextValue = nextValue;
    }
    
    /**
     * @return Returns the maxValue.
     */
    public long getMaxValue()
    {
        return maxValue;
    }

    /**
     * @param maxValue The maxValue to set.
     */
    public void setMaxValue(long maxValue)
    {
        this.maxValue = maxValue;
    }

    public boolean equals(Object otherObject)
    {
        if(this == otherObject)
            return true;

        Long thisId, otherId;
        if(otherObject == null ||
           !(otherObject instanceof Identity) ||
           (thisId = getId()) == null ||
           (otherId = ((Identity)otherObject).getId()) == null)
        {
            return false;
        }

        return thisId == otherId || thisId.equals(otherId);
    }

    public int hashCode()
    {
        if(id == null)
            return 0;
        else
            return id.hashCode();
    }
}
