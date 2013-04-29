/*
 * Created on Jan 23, 2006
 *
 */
package com.manpower.portal.mpnet.dao.interfaces;

import com.manpower.portal.mpnet.hbn.beans.OtherSiteDetails;

/**
 * @author jsingh
 *  
 */
public interface OtherSiteDetailsDAO extends DAO {

	/**
	 * Find by Country Code query
	 * 
	 * @param countryCode
	 * @return List of Objects
	 */
	public OtherSiteDetails findBySiteId(long siteId);
	
}
