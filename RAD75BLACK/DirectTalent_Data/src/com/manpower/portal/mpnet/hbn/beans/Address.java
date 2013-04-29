/*
 * Created on Jan 19, 2006
 *
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;



/**
 * @author jsingh
 *
 *	Persistent class for table ADDRESSES
 */
public class Address extends Base {
	 private static final long serialVersionUID = -3635520191521346230L;
	 
	 private long id;
	 private long siteId;
	 private String addressType;
	 private String	address1;
	 private String	address2;
	 private String	address3;
	 private String	poBox;
     private String city;
     private String state;
     private String code;
     private String country;
	 private Date updatedOn;
	 private String updatedBy;
	 private Candidate candidate;

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
	 * @return Returns the addressType.
	 */
	public String getAddressType() {
		return addressType;
	}
	/**
	 * @param addressType The addressType to set.
	 */
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	/**
	 * @return Returns the candidate.
	 */
	public Candidate getCandidate() {
		return candidate;
	}
	/**
	 * @param candidate The candidate to set.
	 */
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
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
	 * @return Returns the code.
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code The code to set.
	 */
	public void setCode(String code) {
		this.code = code;
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
	 * @return Returns the address3.
	 */
	public String getAddress3() {
		return address3;
	}
	/**
	 * @param address3 The address3 to set.
	 */
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if(obj instanceof Address == false) {
			return false;
		} if (obj == this) {
			return true;
		}
		Address anAddress = (Address)obj;
		return new EqualsBuilder()
			.appendSuper(super.equals(obj))
			.append(siteId, anAddress.getSiteId())
			.append(addressType, anAddress.getAddressType())
			.append(address1, anAddress.getAddress1())
			.append(address2, anAddress.getAddress2())
			.append(address3, anAddress.getAddress3())
			.append(poBox, anAddress.getPoBox())
			.append(city, anAddress.getCity())
			.append(state, anAddress.getState())
			.append(code, anAddress.getCode())
			.append(country, anAddress.getCountry()).isEquals();
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
	    return new HashCodeBuilder(17, 37).
	       append(siteId).
	       append(addressType).
	       append(address1).
		   append(address2).
		   append(address3).
		   append(poBox).
		   append(city).
		   append(state).
		   append(code).
		   append(country).toHashCode();
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
			append("siteId", siteId).
			append("addressType", addressType).
			append("address1", address1).
			append("address2", address2).
			append("address3", address3).
			append("poBox", poBox).
			append("city", city).
			append("state", state).
			append("code", code).
			append("country", country).
			toString();
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
