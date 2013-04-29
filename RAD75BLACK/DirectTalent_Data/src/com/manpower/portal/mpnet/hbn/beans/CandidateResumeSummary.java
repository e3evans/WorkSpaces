/**
 * 
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.util.Date;

/**
 * @author Lyuben
 *
 */
public class CandidateResumeSummary extends Base {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2320295347702113147L;
	private String cvName; 
	private long id;
	private long siteId;
	private Date dateCreated;
	private Date updatedOn;
	private String updatedBy;
	private Candidate candidate;
	private String lensID;
	private String mimeType;
	private String resumeName;
	private boolean savedResume;
	private boolean defaultResume;
	private boolean myManpowerCreatedResume;
	private long size;
	
	
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public Candidate getCandidate() {
		return candidate;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	public String getCvName() {
		return cvName;
	}
	public void setCvName(String cvName) {
		this.cvName = cvName;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public boolean isDefaultResume() {
		return defaultResume;
	}
	public void setDefaultResume(boolean defaultResume) {
		this.defaultResume = defaultResume;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLensID() {
		return lensID;
	}
	public void setLensID(String lensID) {
		this.lensID = lensID;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public boolean isMyManpowerCreatedResume() {
		return myManpowerCreatedResume;
	}
	public void setMyManpowerCreatedResume(boolean myManpowerCreatedResume) {
		this.myManpowerCreatedResume = myManpowerCreatedResume;
	}
	public String getResumeName() {
		return resumeName;
	}
	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}
	public boolean isSavedResume() {
		return savedResume;
	}
	public void setSavedResume(boolean savedResume) {
		this.savedResume = savedResume;
	}
	public long getSiteId() {
		return siteId;
	}
	public void setSiteId(long siteId) {
		this.siteId = siteId;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

}
