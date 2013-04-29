/*
 * Created on Dec 19, 2006
 *
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;
import java.util.Date;


/**
 * @author Mantosh Kumar
 * 
 * Persistent class for view V_CANDIDATE_SEARCH_RESULTS
 *
 */
public class VCandidateSearchResults implements Serializable
{
	private static final long serialVersionUID = -4476560575729796251L;
    private long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String preferredBranch;
	private Date lastLoginDate;
	private String status;
	private boolean sentToFO;
	private int countOfJobsAppliedFor;
	private int activeStatus;
    
	/**
	 * @return Returns the countOfJobsAppliedFor.
	 */
	public int getCountOfJobsAppliedFor() {
		return countOfJobsAppliedFor;
	}
	/**
	 * @param countOfJobsAppliedFor The countOfJobsAppliedFor to set.
	 */
	public void setCountOfJobsAppliedFor(int countOfJobsAppliedFor) {
		this.countOfJobsAppliedFor = countOfJobsAppliedFor;
	}
	/**
	 * @return Returns the firstName.
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName The firstName to set.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
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
	 * @return Returns the lastLoginDate.
	 */
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	/**
	 * @param lastLoginDate The lastLoginDate to set.
	 */
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	/**
	 * @return Returns the lastName.
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName The lastName to set.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return Returns the preferredBranch.
	 */
	public String getPreferredBranch() {
		return preferredBranch;
	}
	/**
	 * @param preferredBranch The preferredBranch to set.
	 */
	public void setPreferredBranch(String preferredBranch) {
		this.preferredBranch = preferredBranch;
	}
	/**
	 * @return Returns the sentToFO.
	 */
	public boolean isSentToFO() {
		return sentToFO;
	}
	/**
	 * @param sentToFO The sentToFO to set.
	 */
	public void setSentToFO(boolean sentToFO) {
		this.sentToFO = sentToFO;
	}
	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return Returns the middleName.
	 */
	public String getMiddleName() {
		return middleName;
	}
	/**
	 * @param middleName The middleName to set.
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public int getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}
   }
