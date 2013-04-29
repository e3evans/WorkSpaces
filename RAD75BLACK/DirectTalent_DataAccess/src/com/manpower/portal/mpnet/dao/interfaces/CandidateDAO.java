/*
 * Created on Jan 16, 2006
 *
 */
package com.manpower.portal.mpnet.dao.interfaces;

import java.util.Date;
import java.util.List;

import com.manpower.portal.mpnet.hbn.beans.BranchTable;
import com.manpower.portal.mpnet.hbn.beans.Candidate;
import com.manpower.portal.mpnet.hbn.beans.CandidateProfileSearch;

/**
 * @author jsingh
 *  
 */
public interface CandidateDAO extends DAO {

	/**
	 * Check if candidate's email is registered
	 * 
	 * @param email
	 * @return True if the email is registered, otherwise False
	 */
	public boolean isCandidateRegistered(String email);

	/**
	 * Find Candidate by email
	 * 
	 * @param email
	 * @return Candidate Object
	 */
	public Object findByEmail(String email);

	public Object findByEmail(String email, String status);

	/**
	 * Find all candidates
	 * 
	 * @return List of Candidate objects
	 */
	public List findAll();

	/**
	 * Find Active Candidate by email
	 * 
	 * @param email
	 * @return Candidate Object
	 */
	public Object findByActiveEmail(String email, long siteId);

	/**
	 * Find all candidates which match the params passed
	 * 
	 * @return List of Candidate objects
	 */
	public List findKnownCandidates(Candidate candidate, String primaryPhone, int fromIndex, int toIndex, int sortOrder, boolean desc);

	public List findUnKnownCandidates(String siteId,
			CandidateProfileSearch profileSearchBean, int fromIndex,
			int toIndex, int sortOrder, boolean desc);
	
	public int findUnKnownCandidatesCount(String siteId, 
			CandidateProfileSearch profileSearchBean);
	
	public List<Long> findUnKnownCandidatesIds(String siteId, 
			CandidateProfileSearch profileSearchBean);
	
	/**
	 * Find Candidate by email
	 * 
	 * @param email
	 * @return Candidate Object
	 */
	public Object findUnconfirmedCandidateByEmail(String email);
    
    public List findCandidatesByBranch(BranchTable branch);
    
	/**
	 * Find by email if a candidate with status A or D exists
	 * @param email
	 * @return
	 */
	public Object findByExistingEmail(String email);
	
	/**
	 * Find candidate by correspondence email
	 * checking for:
	 * 1)is candidate activated (status A)
	 * 2)is candidate waiting for confirmation (status D)
	 */
	public Object findByExistingCorrespondenceEmail(String email);
	
	/**
	 * Find known candidate search count
	 * @param candidate
	 * @param primaryPhone
	 * @return
	 */
	public int findKnownCandidatesCount(Candidate candidate, String primaryPhone);
	
	/**
	 * Find known candidate search Ids
	 * @param candidate
	 * @param primaryPhone
	 * @return
	 */
	public List<Long> findKnownCandidateIds(Candidate candidate, String primaryPhone);
	
	public List getCandidatesByIDs(List ids, int fromIndex, int toIndex, int orderBy, boolean ascending);
}
