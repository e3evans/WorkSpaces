/*
 * Created on Jan 23, 2006
 *
 */
package com.manpower.portal.mpnet.dao.interfaces;

import java.io.Serializable;
import java.util.List;

/**
 * @author Marin
 *  
 */
public interface MyManpowerResumeDAO extends DAO {

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
	 * Find all My Manpower resumes by candidate Id
	 * @param candidateId
	 * @return List of MyManpower resumes.
	 */
	public List findMyManpowerResumesByCandidateId (long candidateId);
}
