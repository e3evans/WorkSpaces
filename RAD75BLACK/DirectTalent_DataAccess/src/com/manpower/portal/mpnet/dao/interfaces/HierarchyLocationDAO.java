/*
 * Created on Jan 23, 2006
 *
 */
package com.manpower.portal.mpnet.dao.interfaces;

import java.util.List;


import com.manpower.portal.mpnet.hbn.beans.HierarchyLocation;
/**
 * @author jsingh
 *  
 */
public interface HierarchyLocationDAO extends DAO {

	public List <HierarchyLocation> findAdvertisementLocationsByName(String rootName, String language, String locationName);
	public List <HierarchyLocation> findAllAdvertisementLocations(long siteID, String lang, String locationPrefix);
	public List <HierarchyLocation> findByLocationName(String rootName, String language, String locationName);
	public List <HierarchyLocation> findAll(String rootName, String language);
	public List <HierarchyLocation> getAllLocationsByLang(String lang);
	public List <HierarchyLocation> getLocationsByPath(String lang,	String locationPath, int MaxResults);
	public List <HierarchyLocation> getAllCountries();
	public List <HierarchyLocation> findLocationsByCountry(String locationPath, int countryLocationId, String lang, int MaxResults);
	public List <HierarchyLocation> findAllLocationsByParent(String parentName, String language);
	public List <HierarchyLocation> findActiveLocationsByParent(String parentName, String language, long siteId);
	public HierarchyLocation getLocationByID(long lid, String language);
	
}
