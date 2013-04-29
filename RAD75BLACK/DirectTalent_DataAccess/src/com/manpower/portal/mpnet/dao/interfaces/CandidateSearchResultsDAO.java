/*
 * Created on July 26, 2007
 *
 */
package com.manpower.portal.mpnet.dao.interfaces;

import java.util.List;

import com.manpower.portal.mpnet.hbn.beans.VCandidateSearchResults;

/**
 * @author Mantosh Kumar
 *  
 */
public interface CandidateSearchResultsDAO {
	/**
	 * 
	 * @param candidateID
	 * @return RecruiterLocationReport object if found else null
	 */
	public VCandidateSearchResults findByCandidateID(Long candidateID);

	public List findByCandidateIDs(List candidateIDs);
	
	public List findByCandidateIDs(List candidateIDs, int orderBy, boolean desc);
}
