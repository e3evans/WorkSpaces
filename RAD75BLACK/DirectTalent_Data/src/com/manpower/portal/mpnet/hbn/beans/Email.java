/*
 * Creado el 16-mar-2006
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * @author MartinMuguiro
 * 
 * Persistent class for table EMAILS
 */
public class Email implements Serializable {
	private static final long serialVersionUID =      -5414785401543294860L;
	private long id;
	private long siteId;
	private String friendEmail;
	private String friendName;
	private Candidate candidate;
	private String message;
	private String state;
	private String type;
	private Date changedOn;
	private String changedBy;
	private Date updatedOn;
	private String updatedBy;
	private Date createdOn;
	private String createdBy;
	private String senderEmail;
	private String subject;
	private String fromLine;
	/**
	 * @return Returns the subject.
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject The subject to set.
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return Returns the createdBy.
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy The createdBy to set.
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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
	 * @return Returns the changedBy.
	 */
	public String getChangedBy() {
		return changedBy;
	}
	/**
	 * @param changedBy The changedBy to set.
	 */
	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
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
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
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
	 * @return Returns friendEmail.
	 */
	public String getFriendEmail() {
		return friendEmail;
	}
	/**
	 * @param friendEmail The friendEmail to set.
	 */
	public void setFriendEmail(String friendEmail) {
		this.friendEmail = friendEmail;
	}
	/**
	 * @return Returns friendName.
	 */
	public String getFriendName() {
		return friendName;
	}
	/**
	 * @param friendName The friendName to set.
	 */
	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}
	/**
	 * @return Returns id.
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
	 * @return Returns message.
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message The message to set.
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return Returns siteId.
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
	 * @return Returns state.
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
	 * @return Returns the senderEmail.
	 */
	public String getSenderEmail() {
		return senderEmail;
	}
	/**
	 * @param senderEmail The senderEmail to set.
	 */
	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}
	/**
	 * @return Returns the fromLine.
	 */
	public String getFromLine() {
		return fromLine;
	}
	/**
	 * @param fromLine The fromLine to set.
	 */
	public void setFromLine(String fromLine) {
		this.fromLine = fromLine;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if(obj instanceof Email == false) {
			return false;
		} if (obj == this) {
			return true;
		}
		Email anEmail = (Email)obj;
		return new EqualsBuilder().
			appendSuper(super.equals(obj)).
			append(this.id, anEmail.getId()).
			append(this.siteId, anEmail.getSiteId()).
			append(this.friendEmail, anEmail.getFriendEmail()).
			append(this.friendName, anEmail.getFriendName()).
			append(this.message, anEmail.getMessage()).
			append(this.state, anEmail.getState()).
			append(this.type, anEmail.getType()).
			append(this.senderEmail, anEmail.getSenderEmail()).
			append(this.subject, anEmail.getSubject()).
			append(this.fromLine, anEmail.getFromLine()).
			append(this.createdBy, anEmail.getCreatedBy()).
			append(this.createdOn, anEmail.getCreatedOn()).
			append(this.changedBy, anEmail.getChangedBy()).
			append(this.changedOn, anEmail.getChangedOn()).
			append(this.updatedBy, anEmail.getUpdatedBy()).
			append(this.updatedOn, anEmail.getUpdatedOn()).isEquals();
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
	    return new HashCodeBuilder(94, 37).
			append(this.id).
			append(this.siteId).
			append(this.friendEmail).
			append(this.friendName).
			append(this.message).
			append(this.state).
			append(this.type).
			append(this.senderEmail).
			append(this.subject).
			append(this.fromLine).
			append(this.createdBy).
			append(this.createdOn).
			append(this.changedBy).
			append(this.changedOn).
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
			append("friendEmail", this.friendEmail).
			append("friendName", this.friendName).
			append("message", this.message).
			append("state", this.state).
			append("type", this.type).
			append("senderEmail", this.senderEmail).
			append("subject", this.subject).
			append("fromLine", this.fromLine).
			append("createdBy", this.createdBy).
			append("createdOn", this.createdOn).
			append("changedBy", this.changedBy).
			append("changedOn", this.changedOn).
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
