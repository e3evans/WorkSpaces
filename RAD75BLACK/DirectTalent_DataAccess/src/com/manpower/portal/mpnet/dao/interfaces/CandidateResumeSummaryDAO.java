/**
 * 
 */
package com.manpower.portal.mpnet.dao.interfaces;

import java.io.Serializable;
import java.util.List;

/**
 * @author Lyuben
 *
 */
public interface CandidateResumeSummaryDAO extends DAO {
	
	public Object findByCandidate(Serializable id);

	/**
	 * This method returns list of all resumes with the supplied Lens IDs
	 * 
	 * @param IDs
	 * @return List of CandidateResume objects
	 */
	public List findByIDs(List IDs);

		
	/**
	 * Find all resumes for a particular candidate
	 * @param candidateId
	 * @return
	 */
	public List findCandidateResumes(long candidateId);

}
