/*
 * Created on Jan 23, 2006
 *
 */
package com.manpower.portal.mpnet.dao.interfaces;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.manpower.portal.mpnet.hbn.beans.AdvertisementContact;
import com.manpower.portal.mpnet.hbn.beans.CandidateJobAgents;

/**
 * @author jsingh
 *  
 */
public interface AdvertisementDAO extends DAO {

    public final static String ADVERTISMENTS_ALIAS = "adverts";
    public final static String ADVERTISMENTS_CONTACTS_ALIAS = "contacts";
	/**
	 * Calculate the number of advertisements by SQL query
	 * 
	 * @param query
	 *            SQL Query
	 * @return Number of advertisements
	 */
	public int getCountBySQLQuery(String query);

	/**
	 * Calculate the number of advertisements by SQL query and some additional
	 * parameters
	 * 
	 * @param query
	 *            SQL Query
	 * @param params
	 *            Additional parameters
	 * @return Number of advertisements
	 */
	public int getCountBySQLQuery(String query, Properties params);

	public int findNumberOfAdvertsPerBranch(Long branchId);

	public List findByBranchID(long branchID);

	/**
	 * Find all job advertisements stored based on the data provided. If a
	 * parameter is null it will not be part of the search.
	 * 
	 * @param branchID
	 * @param beginDate
	 * @param endDate
	 * @param contactID
	 * @param jobTitle
	 * @return list of Advertisement
	 */
	public List find(long branchID, Date beginDate, Date endDate,
			long contactID, String jobTitle, String filter, String advertID,String orderBy, boolean ascending, int pageNumber, int pageSize);
	
	public long getTotalCount(long branchID, Date beginDate, Date endDate,
			long contactID, String jobTitle, String filter, String advertID);
	
	public List<Long> getAllAdvertIds(long branchID, Date beginDate, Date endDate,
			long contactID, String jobTitle, String filter, String advertID);	
	

	public List getAdvertisements(CandidateJobAgents jobAgent,
			String localeLanguage, String advertId, int pageNumber, int pageSize);

	public List findSubListByJobAgent(CandidateJobAgents jobAgent,
			String localeLanguage, String advertID, String orderByProperty,
			boolean descnding, int startIndex, int endIndex);

	public int getCountByJobAgent(CandidateJobAgents jobAgent, String advertID);
	
	public String getLanguage();
	public void setLanguage(String language);
	public List getAdvertismentsResultsBy(String siteID, String language, String branch, String jobTitle,String name, String advertID, String fromDate, String toDate, String orderBy, boolean ascending, int pageNumber, int pageSize);
	public int getAdvertismentsCount(String siteID, String language, String branch, String jobTitle,String name, String advertID, String fromDate, String toDate);
    public List findAdvertisementsByRecruiter(AdvertisementContact adContact);
	public void updateExpirationDate(long _advertId, Date _expirationDate);
    public void updateExtJoBoard(long advertisementId, String extJoBoard);
    public List findAdvertisementsByExternalId(String externalId, long siteId);
    public List getAdvertisementsByIDs(List ids, int fromIndex, int toIndex, int orderBy, boolean ascending);
}
