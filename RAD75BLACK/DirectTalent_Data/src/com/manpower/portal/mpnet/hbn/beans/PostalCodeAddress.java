/*
 * Created on 2006-7-5
 *
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author alexander.todorov
 *
 * Persistent class for table POSTAL_CODE_CAMPUS
 */
public class PostalCodeAddress implements Serializable{
	private static final long serialVersionUID =    -4533839061214586219L;
	private long 	id					= 0,
					siteId				= 0;
	
	
	private String 	state				= null,
					country 			= null,
					postalCode			= null,
					city				= null,
					preferedLocation	= null,
					remarks				= null,
					updatedBy			= null,
					language			= null;
	
	private Date	updatedOn		= null,
					changedOn		= null,
					createdOn		= null;
	
	/**
	 * Constructor
	 *
	 */
	public PostalCodeAddress() {
		super();
	}

	
	/**
	 * @return Returns the changedOn.
	 */
	public Date getChangedOn() {
		return changedOn;
	}
	/**
	 * @param changedOn The changedOn to set.
	 */
	public void setChangedOn(Date changedOn) {
		this.changedOn = changedOn;
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
	 * @return Returns the createdOn.
	 */
	public Date getCreatedOn() {
		return createdOn;
	}
	/**
	 * @param createdOn The createdOn to set.
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
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
	 * @return Returns the remarks.
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * @param remarks The remarks to set.
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	 * @return Returns the street.
	 */
	
	/**
	 * @return Returns the streetNumber.
	 */
	public String getPreferedLocation() {
		return preferedLocation;
	}
	/**
	 * @param streetNumber The streetNumber to set.
	 */
	public void setPreferedLocation(String preferedLocation) {
		this.preferedLocation = preferedLocation;
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
	 * @return Returns the language.
	 */
	public String getLanguage() {
		return language;
	}
	/**
	 * @param language The language to set.
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if(obj instanceof PostalCodeAddress == false) {
			return false;
		} if (obj == this) {
			return true;
		}
		PostalCodeAddress aPostalCodeAddress = (PostalCodeAddress)obj;
		return new EqualsBuilder().
			appendSuper(super.equals(obj)).
			append(this.id, aPostalCodeAddress.getId()).
			append(this.siteId, aPostalCodeAddress.getSiteId()).
			append(this.state, aPostalCodeAddress.getState()).
			append(this.country, aPostalCodeAddress.getCountry()).
			append(this.postalCode, aPostalCodeAddress.getPostalCode()).
			append(this.city, aPostalCodeAddress.getCity()).
			append(this.preferedLocation, aPostalCodeAddress.getPreferedLocation()).
			append(this.remarks, aPostalCodeAddress.getRemarks()).
			append(this.language, aPostalCodeAddress.getLanguage()).
			append(this.createdOn, aPostalCodeAddress.getChangedOn()).
			append(this.updatedBy, aPostalCodeAddress.getUpdatedBy()).
			append(this.updatedOn, aPostalCodeAddress.getUpdatedOn()).isEquals();
	}
			
	
	/* This version is slower than the hard-coded version and will 
	 * fail under a security manager unless appropriate permissions are set.
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	//public boolean equals(Object obj) {
	//	return EqualsBuilder.reflectionEquals(this, obj);
	//}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		// you pick a hard-coded, randomly chosen, non-zero, odd number
	    // ideally different for each class
	    return new HashCodeBuilder(14, 53).
			append(this.id).
			append(this.siteId).
			append(this.state).
			append(this.country).
			append(this.postalCode).
			append(this.city).
			append(this.preferedLocation).
			append(this.remarks).
			append(this.language).
			append(this.createdOn).
			append(this.updatedBy).
			append(this.updatedOn).toHashCode();
	}
		
	/* This version is slower than the hard-coded version and will 
	 * fail under a security manager unless appropriate permissions are set.
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	//public int hashCode() {
	//   return HashCodeBuilder.reflectionHashCode(this);
	//}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).
			append("id", this.id).
			append("siteId", this.siteId).
			append("state", this.state).
			append("country", this.country).
			append("postalCode", this.postalCode).
			append("city", this.city).
			append("preferedLocation", this.preferedLocation).
			append("remarks", this.remarks).
			append("language", this.language).
			append("createdOn", this.createdOn).
			append("updatedBy", this.updatedBy).
			append("updatedOn", this.updatedOn)
				.toString();
	}
	
	/* This version is slower than the hard-coded version and will 
	 * fail under a security manager unless appropriate permissions are set.
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */	
	//public String toString() {
	//   return ToStringBuilder.reflectionToString(this);
	//}
}
