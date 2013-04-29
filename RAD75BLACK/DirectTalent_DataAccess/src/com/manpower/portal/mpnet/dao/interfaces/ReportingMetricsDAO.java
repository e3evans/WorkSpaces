/*
 * Created on Jun 19, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.mpnet.dao.interfaces;

import java.util.List;

/**
 * @author vindukur
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface ReportingMetricsDAO extends DAO {

	/**
	 * Find by Site Id query
	 * 
	 * @param siteId
	 * @return List of Objects
	 */
	List findBySiteID(long siteId);

	/**
	 * Find by Country Code query
	 * 
	 * @param countryCode
	 * @return List of Objects
	 */
	List findByCountryCode(String countryCode);

}
