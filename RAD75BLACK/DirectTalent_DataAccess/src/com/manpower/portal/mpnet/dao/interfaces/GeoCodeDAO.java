/*
 * Created on Apr 19, 2006
 *
 */
package com.manpower.portal.mpnet.dao.interfaces;

import java.util.List;

import com.manpower.portal.mpnet.hbn.beans.GeoCode;

/**
 * @author amillar
 *  
 */
public interface GeoCodeDAO extends DAO {

	/**
	 * Find all locations for a country and a municipality
	 * 
	 * @param countryName
	 * @param municipality
	 * @return List of Geoocation objects
	 */
	public List<GeoCode> findGeolocation(String countryName, String region, String city, String postalCode);

	
}
