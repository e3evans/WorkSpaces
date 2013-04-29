/*
 * Created on Sep 21, 2007
 *
 */
package com.manpower.portal.mpnet.dao.interfaces;

import java.util.List;

import com.manpower.portal.mpnet.hbn.beans.ApplicantResponse;

/**
 * @author ssprout1
 *  
 */
public interface ApplicantResponseDAO extends DAO {

	List findByAdvertID(long advertID);

	/**
	 * This method returns list of all resumes with the supplied Lens IDs
	 * 
	 * @param IDs
	 * @return List of ApplicantResponse objects
	 */
	public List findByIDs(List IDs);

	/**
	 * Store a ApplicantResponse object in Lens with provided Longitude and
	 * Latitude
	 * 
	 * @param obj
	 *            ApplicantResponse object
	 * @param longitude
	 * @param latitude
	 * @return Stored Applicant Response object
	 */
	public Object makePersistent(Object obj, double longitude, double latitude);

	/**
	 * Find all Applicant Response resumes are no stored in Lens.
	 * 
	 * @param siteId
	 * @param maxResults
	 * @return List of ApplicantResponse objects
	 */
	public List readApplicantResponseResumesWhichDontHaveLensID(long siteId,
			int maxResults);
	
	public List findByResumeKeywordSearch(String keyword, long siteId, int fromIndex, int toIndex, int sortOrder, boolean showAllResults);

	public int countByResumeKeywordSearch(String keyword, long siteId, boolean showAllResults);
	
	public List getApplicantWithoutBGScore(long advertID);	
	
	public List<ApplicantResponse> findApplicantResponseByMobileApplicant(String firstName, String lastName, String email,
			String phoneNumber, String phoneID);
	
	public List<ApplicantResponse> findExistingJobAplicationByMobileApplicant(long advertId, String firstName, String lastName, String email,
			String phoneNumber, String phoneID);
	
	public List<ApplicantResponse> findExistingJobAplicationByMobileApplicant(long advertId, String firstName, String lastName, String email,
			String phoneNumber);
}
