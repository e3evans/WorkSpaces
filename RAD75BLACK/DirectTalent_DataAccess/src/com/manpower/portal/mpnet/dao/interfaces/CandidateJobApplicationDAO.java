/*
 * Created on Jan 23, 2006
 *
 */
package com.manpower.portal.mpnet.dao.interfaces;

import java.io.Serializable;
import java.util.List;

import com.manpower.portal.mpnet.exception.ServiceException;
import com.manpower.portal.mpnet.hbn.beans.Advertisement;
import com.manpower.portal.mpnet.hbn.beans.CandidateJobApplication;

/**
 * @author jsingh
 *  
 */
public interface CandidateJobApplicationDAO extends DAO {
	/**
	 * Find all applications for a job
	 * 
	 * @param advert
	 *            Job Advertisement
	 * @return List of CandidateJobApplication objects.
	 */
	public List findCandidatesAppliedForJobApplication(Advertisement advert);
    
    /**
     * Finds the candidate application that is not deleted 
     * @param candidateId
     * @param advertisementId
     * @return CandidateJobApplication or null if such is not found
     */
    public CandidateJobApplication findUndeletedCandidateApplications(long candidateId, long advertisementId);
    
    /**
     * Gets few of the fields, excludes the uploaded resume
     * @param candidateId
     * @return
     */
    public List getAllUndeletedJobApplicationsSimple(long candidateId);
    
    /**
     * Checks the count of undeleted job applications by 
     * candidate id and advertisement id. If the count is 
     * greater than 0, the method returns true, else - false
     * @param candidateId
     * @param advertisementId
     * @return
     */
    public boolean undeletedJobApplicationExists(long candidateId, long advertisementId);
    
    /**
     * Sets the job application statuts to deleted
     * @param id
     */
    public void setJobApplicationToDeleted(Serializable id);
    
    /**
     * 
     * @param jobApplicationId
     * @return Cover Letter as string
     */
    public String[] getCoverLetter(long jobApplicationId);
}
