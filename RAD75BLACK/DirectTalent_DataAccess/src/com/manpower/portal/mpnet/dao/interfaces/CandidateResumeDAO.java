/*
 * Created on Jan 23, 2006
 *
 */
package com.manpower.portal.mpnet.dao.interfaces;

import java.io.Serializable;
import java.util.List;

import com.manpower.portal.mpnet.hbn.beans.CandidateResume;

/**
 * @author jsingh
 *  
 */
public interface CandidateResumeDAO extends DAO {

	public Object findByCandidate(Serializable id);

	/**
	 * This method returns list of all resumes with the supplied Lens IDs
	 * 
	 * @param IDs
	 * @return List of CandidateResume objects
	 */
	public List findByIDs(List IDs);

	/**
	 * Store a CandidateResume object in Lens with provided Longitude and
	 * Latitude
	 * 
	 * @param obj
	 *            CandidateResume object
	 * @param longitude
	 * @param latitude
	 * @return Stored Candidate Resume object
	 */
	public Object makePersistent(Object obj, double longitude, double latitude);

	/**
	 * Find all Candidate resumes are no stored in Lens.
	 * 
	 * @param siteId
	 * @param maxResults
	 * @return List of CandidateResume objects
	 */
	public List readCandidateResumesWhichDontHaveLensID(long siteId,
			int maxResults);
	
	/**
	 * Find all resumes for a particular candidate
	 * @param candidateId
	 * @return
	 */
	public List findCandidateResumes(long candidateId);
	
	public CandidateResume findMyManpowerResume(long candidateId);
	
	/**
	 * Find the default resume of the candidate
	 * @param candidateId
	 * @return
	 */
	public CandidateResume findPrimaryResumeByCandidateId(long candidateId);
}
