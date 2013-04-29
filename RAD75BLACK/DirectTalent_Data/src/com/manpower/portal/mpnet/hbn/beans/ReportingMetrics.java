/*
 * Created on Jun 19, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author vindukur
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ReportingMetrics implements Serializable{

	/**
	 * 
	 */
	public ReportingMetrics() {
		super();
		// TODO Auto-generated constructor stub
	}
private static final long serialVersionUID = 	-2247498881828557327L;
private long id,
    siteId,
	applicants,
	candidates,
	loggedIn,
	activeJobs,
	reviewedCandidates;

private Date   createdOn;
private String countryCode;


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
* @return Returns the siteId.
*/
public long getSiteId() {
return siteId;
}

/**
* @return Returns the applicants.
*/
public long getApplicants() {
return applicants;
}
/**
* @return Returns the candidates.
*/
public long getCandidates() {
return candidates;
}

/**
* @return Returns the loggedIn.
*/
public long getLoggedIn() {
return loggedIn;
}
/**
* @return Returns the activeJobs.
*/
public long getActiveJobs() {
return activeJobs;
}
/**
* @return Returns the reviewedCandidates.
*/
public long  getReviewedCandidates() {
return reviewedCandidates;
}
/**
* @return Returns the countryCode.
*/
public String  getCountryCode() {
return countryCode;
}




/**
* @param id The id to set.
*/
public void setId(long id) {
this.id = id;
}

/**
* @param siteId The siteId to set.
*/
public void setSiteId(long siteId) {
this.siteId = siteId;
}
/**
* @param applicants The applicants to set.
*/
public void setApplicants(long applicants) {
this.applicants = applicants;
}
/**
* @param candidates The candidates to set.
*/
public void setCandidates(long candidates) {
this.candidates = candidates;
}
/**
* @param loggedIn The loggedIn to set.
*/
public void setLoggedIn(long loggedIn) {
this.loggedIn = loggedIn;
}
/**
* @param activeJobs The activeJobs to set.
*/
public void setActiveJobs(long activeJobs) {
this.activeJobs = activeJobs;
}
/**
* @param reviewedCandidates The reviewedCandidates to set.
*/
public void setReviewedCandidates(long reviewedCandidates) {
this.reviewedCandidates = reviewedCandidates;
}

/**
* @param countryCode The countryCode to set.
*/
public void setCountryCode(String countryCode) {
this.countryCode = countryCode;
}

}
