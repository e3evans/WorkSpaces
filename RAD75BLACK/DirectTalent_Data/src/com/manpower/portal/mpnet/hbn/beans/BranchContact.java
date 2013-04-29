/*
 * Creado el 27-mar-2006
 *
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Vivi
 *
 * It is not a persistent class.
 */
public class BranchContact implements Serializable{
	
	    private static final long serialVersionUID =-6576544775630143399L;
		private long id; 
		private long siteId;
		private String contactName;
		private String phoneNumber;
		private String faxNumber;
		private String email;
		private Branch branch;
		private Date updatedOn;
		private String updatedBy;
		 
		 
		 
		/**
		 * @return Returns contactName.
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
		 * @return Returns email.
		 */
		public String getEmail() {
			return email;
		}
		/**
		 * @param email The email to set.
		 */
		public void setEmail(String email) {
			this.email = email;
		}
		/**
		 * @return Returns faxNumber.
		 */
		public String getFaxNumber() {
			return faxNumber;
		}
		/**
		 * @param faxNumber The faxNumber to set.
		 */
		public void setFaxNumber(String faxNumber) {
			this.faxNumber = faxNumber;
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
		 * @return Returns phoneNumber.
		 */
		public String getPhoneNumber() {
			return phoneNumber;
		}
		/**
		 * @param phoneNumber The phoneNumber to set.
		 */
		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
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
		 * @return Returns updatedBy.
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
		 * @return Returns updatedOn.
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
		 * @return Returns branch.
		 */
		public Branch getBranch() {
			return branch;
		}
		/**
		 * @param branch The branch to set.
		 */
		public void setBranch(Branch branch) {
			this.branch = branch;
		}
}
