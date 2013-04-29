/*
 * Created on Jan 23, 2006
 *
 */
package com.manpower.portal.mpnet.dao.interfaces;

import java.util.List;

/**
 * @author jsingh
 *  
 */
public interface SiteDAO extends DAO {

	/**
	 * Find by Country Code query
	 * 
	 * @param countryCode
	 * @return List of Objects
	 */
	List findByCountryCode(String countryCode);
	
	List findSitesByCountries(String countries[]);

	public List findAllSitesForRecruiter(long recruiterId);
}
