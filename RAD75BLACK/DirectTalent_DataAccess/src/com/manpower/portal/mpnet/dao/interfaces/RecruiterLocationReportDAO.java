/*
 * Created on Dec 19, 2006
 *
 */
package com.manpower.portal.mpnet.dao.interfaces;

import java.util.List;

import com.manpower.portal.mpnet.hbn.beans.RecruiterLocationReport;

/**
 * @author Dimitar Bakardzhiev
 *  
 */
public interface RecruiterLocationReportDAO {
/*	*//**
	 * Find all candidate resumes for a give list of Lens IDs, location, region
	 * code and site
	 * 
	 * @param IDs
	 * @param location
	 * @param regionCode
	 * @param siteId
	 * @param language
	 *            TODO
	 * 
	 * @return List of RecruiterLocationReport objects
	 *//*
	public List findByResumeIDs(List IDs, String location, String regionCode,
			long siteId, String language, boolean searchByLocations, long locationANY, long regionANY, int version);

	*//**
	 * 
	 * @param IDs
	 * @param locations
	 * @param siteId
	 * @param language
	 * @return
	 *//*
	public List findByResumeIDs(List IDs, String locations, long siteId,
			String language, boolean searchByLocations, long locationANY, long regionANY, int version);

	*//**
	 * Search for candidates with the given preferred location, given preferred
	 * branch and which have the given keyword in their resumes
	 * 
	 * @param keyword
	 * @param location
	 * @param regionCode
	 * @param siteId
	 * @return List of RecruiterLocationReport objects
	 */
/*	public List findCandidatesByKeyword(String keyword, String location,
			String regionCode, long siteId, String language,
			boolean searchByLocation, long locationANY, long regionANY);

	public List findCandidatesByKeyword(String keyword, String locations,
			long siteId, String language, boolean searchByLocation, long locationANY, long regionANY);
*/
	public RecruiterLocationReport findCandidateById(long candidate_id);
/*	
	public int countCandidatesByKeyword(String keyword, String location, String regionCode, long siteId, String language, boolean searchByLocation, 
            long locationANY, long regionANY, boolean showAllResults, int version);
*/	
	public int countCandidatesByKeyword(String keyword, String locations, long siteId, long locationANY, boolean showAllResults);
	
	public List findCandidatesByKeyword(String keyword, String locations, long siteId, long locationANY, int maxResult, int fromIndex, int toIndex, int sortOrder,boolean includeJobCount, boolean showAllResults);
	
	public List<Long> findAllCandidateIdsByKeyword(String keyword, String locations, long siteId, long locationANY, boolean includeJobCount, boolean showAllResults);
	
	/*public List findCandidatesByKeyword(String keyword, String location, String regionCode, long siteId, String language, boolean searchByLocation, 
            long locationANY, long regionANY, int fromIndex, int toIndex, int sortOrder,boolean includeJobCount, boolean showAllResults, int version);*/	
}
