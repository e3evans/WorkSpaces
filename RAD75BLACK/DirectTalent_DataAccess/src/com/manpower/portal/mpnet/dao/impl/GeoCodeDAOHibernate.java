/*
 * Created on Apr 19, 2006
 *
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.GeoCodeDAO;
import com.manpower.portal.mpnet.hbn.beans.GeoCode;

/**
 * @author amillar
 *  
 */
public class GeoCodeDAOHibernate extends GenericHibernateDAO implements
		GeoCodeDAO {
	
	private static final String FIND_SQL =  " SELECT *" +
											" FROM GEOCODES WHERE LTRIM(RTRIM(UPPER(COUNTRY))) = ':country' " +
											" AND LTRIM(RTRIM(UPPER(REGION))) = ':region'" +
											" AND LTRIM(RTRIM(UPPER(CITY))) = ':city' " +
											" AND LTRIM(RTRIM(UPPER(ZIP_CODE))) = ':postal'";

	/**
	 * Constructor
	 * 
	 * @param persistentClass
	 * @param session
	 */
	public GeoCodeDAOHibernate(Session session) {
		super(GeoCode.class, session);
	}

	public Object findByID(Serializable id) {
		return null;
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findAll()
	 */
	public List findAll() {
		return null;
	}


	/**
	 * Find locations by ISO country code and municipality
	 * 
	 * @return List of GeoLocation objects
	 */
	public List<GeoCode> findGeolocation(String countryName, String region, String city, String postalCode) {
		Session session = HibernateUtil.getCurrentSession();
		String query = FIND_SQL.replaceAll(":country", countryName).
								replaceAll(":region", region).
								replaceAll(":city", city).
								replaceAll(":postal",postalCode);
		List<GeoCode> result = session.createSQLQuery(query).addEntity(GeoCode.class).list();
		return result;
	}

	
}
