/*
 * Created on Feb 8, 2006
 *
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.util.Date;


/**
 * @author Eric Evans
 *
 *	Persistent class for table ADVERTISEMENTCONTACTS
 */
public class AdvertisementContact
	extends Base
	implements Cloneable
{

	private static final long serialVersionUID = -7815238834990241229L;
	private long id;
	private long siteId;
	private String contactName;
	private String address1;
	private String address2;
	private String poBox;
	private String city;
	private String state;
	private String postalCode;
	private String country;
	private String phone;
	private long branchId;
	private long advertisementId;

	private Date updatedOn;
	private String updatedBy;
    private String createdBy;
    private String changedBy;
	private String externalId;
	private String recruiterUserId;
	private Branch branch;
	private String userId;

	/**
	 * @return Returns the address1.
	 */
	public String getAddress1() {
		return address1;
	}
	/**
	 * @param address1 The address1 to set.
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	/**
	 * @return Returns the address2.
	 */
	public String getAddress2() {
		return address2;
	}
	/**
	 * @param address2 The address2 to set.
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	/**
	 * @return Returns the advertisementId.
	 */
	public long getAdvertisementId() {
		return advertisementId;
	}
	/**
	 * @param advertisementId The advertisementId to set.
	 */
	public void setAdvertisementId(long advertisementId) {
		this.advertisementId = advertisementId;
	}
	/**
	 * @return Returns the branchId.
	 */
	public long getBranchId() {
		return branchId;
	}
	/**
	 * @param branchId The branchId to set.
	 */
	public void setBranchId(long branchId) {
		this.branchId = branchId;
	}
	/**
	 * @return Returns the city.
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city The city to set.
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return Returns the contactName.
	 */
	public String getContactName() {
		return contactName;
	}
	/**
	 * @param contactName The contactName to set.
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	/**
	 * @return Returns the country.
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country The country to set.
	 */
	public void setCountry(String country) {
		this.country = country;
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
	 * @return Returns the poBox.
	 */
	public String getPoBox() {
		return poBox;
	}
	/**
	 * @param poBox The poBox to set.
	 */
	public void setPoBox(String poBox) {
		this.poBox = poBox;
	}
	/**
	 * @return Returns the postalCode.
	 */
	public String getPostalCode() {
		return postalCode;
	}
	/**
	 * @param postalCode The postalCode to set.
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
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
	 * @return Returns the state.
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state The state to set.
	 */
	public void setState(String state) {
		this.state = state;
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
	 * @return Returns the externalId.
	 */
	public String getExternalId() {
		return externalId;
	}
	/**
	 * @param externalId The externalId to set.
	 */
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	/**
	 * @return Returns the phone.
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone The phone to set.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
    /**
     * @return Returns the recruiterUserId.
     */
    public String getRecruiterUserId()
    {
        return recruiterUserId;
    }
    /**
     * @param recruiterUserId The recruiterUserId to set.
     */
    public void setRecruiterUserId(String recruiterUserId)
    {
        this.recruiterUserId = recruiterUserId;
    }
    
    // this should be used when  postal code and city needs to be shown in one line.
    public String getPostalCodeAndCity(){
       StringBuffer str=new StringBuffer();	
       
       if (city != null && city.trim().length() > 0)
		{
		    str.append(city);
		}
                  
       if (getState() != null && getState().trim().length() > 0)
        {
            str.append(", ");
            str.append(getState());
        }
       
       if (postalCode != null && postalCode.trim().length() > 0)
        {
            str.append(" ");
            str.append(postalCode);
        }
       
       return str.toString();
    }
    /**
     * @return Returns the branch.
     */
    public Branch getBranch()
    {
        return branch;
    }
    /**
     * @param branch The branch to set.
     */
    public void setBranch(Branch branch)
    {
        this.branch = branch;
    }
    /**
     * @return Returns the userId.
     */
    public String getUserId()
    {
        return userId;
    }
    /**
     * @param userId The userId to set.
     */
    public void setUserId(String userId)
    {
        this.userId = userId;
    }
    
	public String getChangedBy() {
		return changedBy;
	}
	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
}
