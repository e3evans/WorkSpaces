/*
 * Created on 2006-7-5
 *
 */
package com.manpower.portal.mpnet.dao.interfaces;

import java.util.List;

/**
 * @author alexander.todorov
 *  
 */
public interface RegionDAO extends DAO {

	/**
	 * Find all regions by a country, language and regin code
	 * 
	 * @param country
	 * @param language
	 * @param regionCode
	 * @return List of Region objects
	 */
	public List findRegionsByCountryLangRegionCode(String country,
			String language, String regionCode);
}
