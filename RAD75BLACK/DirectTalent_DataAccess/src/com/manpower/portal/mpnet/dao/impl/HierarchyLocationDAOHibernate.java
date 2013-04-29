package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.HierarchyLocationDAO;
import com.manpower.portal.mpnet.hbn.beans.HierarchyLocation;

public class HierarchyLocationDAOHibernate extends GenericHibernateDAO implements HierarchyLocationDAO {

	
	public HierarchyLocationDAOHibernate(Session session) {
		super(HierarchyLocation.class, session);
	}

	/*
	 * @see com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO#findByID(java.io.Serializable)
	 */
	public Object findByID(Serializable id) {
		return null;
	}
	
	public List findAll(){
		return null;
	}
	
	public List findAdvertisementLocationsByName(String rootName, String language, String locationName){
		return null;
	}
	
	public List<HierarchyLocation> findAllAdvertisementLocations(long siteID, String lang, String locationPrefix){
		
		String query = 	" SELECT V.* " +  
						" FROM V_LOCATIONS_HIERARCHY V " +
						" WHERE LEAF = 1 " +
						" AND LANG ='" + lang + "'" +
						" AND UPPER(LOCATIONPATH) like UPPER('%" + locationPrefix + "%')" + 
						" AND  ID IN (SELECT DISTINCT LOCATION FROM ACTIVE_ADVERTISEMENTS WHERE SITE_ID = " +  siteID +")";
		SQLQuery querySet = HibernateUtil.getCurrentSession().createSQLQuery(query);
		querySet.addEntity(HierarchyLocation.class);
		 
		List <HierarchyLocation>results = querySet.list();
		
		if(results==null || results.isEmpty()){
			return new ArrayList<HierarchyLocation>();
		}
		
		List ids = processLocations(results);
		
		Criteria idQuery = HibernateUtil.getCurrentSession().createCriteria(HierarchyLocation.class);
		idQuery.add(Restrictions.in("id", ids));
		idQuery.add(Restrictions.eq("leaf", Boolean.TRUE));
		results  = idQuery.list();
		return results;
	}
	
	private List<Long> processLocations(List<HierarchyLocation> foundLocations){
		
		String splitId = ("2139421");
		
		if(foundLocations != null && !foundLocations.isEmpty()){
			int size = foundLocations.size();
			HashSet<Long> uniqueLocations = new HashSet<Long>();
			String locationIDS[] = null;
			for(int i=0; i< size; i++){
				HierarchyLocation location = foundLocations.get(i);
				String[] locationsWithoutCountry = location.getLocationIdPath().split(splitId);
				locationIDS = locationsWithoutCountry[0].split("-");
				for(int j = 0; j < locationIDS.length; j++){
					
					try{
						Long id = new Long(locationIDS[j]);
						uniqueLocations.add(id);	
					}catch (Exception e) {
						e.printStackTrace();
						continue;
					}
					
					
				}
			}
	
			return new ArrayList<Long>(uniqueLocations);
		}else{
			return null;
		}
		
	}
	
	public List <HierarchyLocation> findByLocationName(String parentName, String language, String locationName){
		List <HierarchyLocation> locations = null;
		Criteria cquery = HibernateUtil.getCurrentSession().createCriteria(HierarchyLocation.class);
		cquery.add(Restrictions.eq("language", language));
		cquery.add(Restrictions.eq("parentName", parentName));
		cquery.add(Restrictions.like("locationName", locationName + "%"));
		
		cquery.setMaxResults(50);
		
		locations = cquery.list();
		
		return locations;
	}
	
	
	public List <HierarchyLocation> getAllLocationsByLang(String lang){
		//System.out.println("<<<LOCATIONS>>> DAO get all locations by lang " + lang);
		List <HierarchyLocation> locations = null;
		Criteria cquery = HibernateUtil.getCurrentSession().createCriteria(HierarchyLocation.class);
		
		if(lang!= null && !("").equals(lang)){
			cquery.add(Restrictions.eq("language", lang));
		}
		
		cquery.setMaxResults(50);
		
		locations = cquery.list();
		//System.out.println("<<<LOCATIONS>>> DAO done get all locations by lang " + locations);
		return locations;
	}
	
	public List <HierarchyLocation> getLocationsByPath(String lang,	String locationPath, int MaxResults){
		//System.out.println("<<<LOCATIONS>>> DAO get all locations by path " + locationPath);
		List <HierarchyLocation> locations = null;
		Criteria cquery = HibernateUtil.getCurrentSession().createCriteria(HierarchyLocation.class);
		
		if(lang!= null && !("").equals(lang)){
			cquery.add(Restrictions.eq("language", lang));
		}
		
		if(lang!= locationPath && !("").equals(locationPath)){
			locationPath = "%"+locationPath+"%";
			cquery.add(Restrictions.sqlRestriction("UPPER(locationPath) like UPPER(?)", locationPath, Hibernate.STRING));
		}
		
		cquery.setMaxResults(MaxResults);
		
		locations = cquery.list();
		//System.out.println("<<<LOCATIONS>>> DAO done get all locations by lang " + locations);
		return locations;
	}
	
	public List <HierarchyLocation> getAllCountries(){
		
		List <HierarchyLocation> locations = null;
		Criteria cquery = HibernateUtil.getCurrentSession().createCriteria(HierarchyLocation.class);
		
		cquery.add(Restrictions.eq("parentName", "World"));
		cquery.add(Restrictions.eq("level", 3));
				
		locations = cquery.list();
		//System.out.println("<<<LOCATIONS>>> DAO done get all locations by lang " + locations);
		return locations;
	}
	
	public List <HierarchyLocation> findLocationsByCountry(String locationPath, int countryLocationId, String lang, int MaxResults){
			
		List <HierarchyLocation> locations = null;
		Criteria cquery = HibernateUtil.getCurrentSession().createCriteria(HierarchyLocation.class);
		
		if(lang!= null && !("").equals(lang)){
			cquery.add(Restrictions.eq("language", lang));
		}
		
		cquery.add(Restrictions.ne("level", 1));
		
		if(countryLocationId!=0){
			String idSearch = "%-"+countryLocationId;
			cquery.add(Restrictions.sqlRestriction("LOCATION_ID_PATH like (?)", idSearch, Hibernate.STRING));
		}
				
		if(locationPath!= null && !("").equals(locationPath)){
			locationPath = "%"+locationPath+"%";
			cquery.add(Restrictions.sqlRestriction("UPPER(locationPath) like UPPER(?)", locationPath, Hibernate.STRING));
		}
		
		cquery.setMaxResults(MaxResults);
		
		locations = cquery.list();
		//System.out.println("<<<LOCATIONS>>> DAO done get all locations by lang " + locations);
		return locations;
	}
	
	public List<HierarchyLocation> findAllLocationsByParent(String parentName, String language){
		
		Criteria cquery = HibernateUtil.getCurrentSession().createCriteria(HierarchyLocation.class);
		cquery.add(Restrictions.eq("parentName", parentName));
		cquery.add(Restrictions.eq("language", language));
		return cquery.list();
	}
	
	public List<HierarchyLocation> findActiveLocationsByParent(String parentName, String language, long siteId){
			
		Criteria cquery = HibernateUtil.getCurrentSession().createCriteria(HierarchyLocation.class);
		cquery.add(Restrictions.eq("parentName", parentName));
		cquery.add(Restrictions.eq("language", language));
		cquery.add(Restrictions.sqlRestriction("this_.ID IN (SELECT DISTINCT LOCATION FROM ACTIVE_ADVERTISEMENTS WHERE SITE_ID = " +  siteId +")"));
		return cquery.list();
	}
	
	
	public HierarchyLocation getLocationByID(long lid, String language){
		Criteria cquery = HibernateUtil.getCurrentSession().createCriteria(HierarchyLocation.class);
		cquery.add(Restrictions.eq("id", Long.valueOf(lid)));
		cquery.add(Restrictions.eq("language", language));
		cquery.add(Restrictions.eq("level", Integer.valueOf(1)));
		return (HierarchyLocation)cquery.uniqueResult();
		
	}
	
	public List findAll(String rootName, String language){
		return null;
	}
	
	

	
}
