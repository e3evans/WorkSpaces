/*
 * Created on 2006-7-5
 *
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.RegionDAO;
import com.manpower.portal.mpnet.hbn.beans.Region;

/**
 * @author alexander.todorov
 *  
 */
public class RegionDAOHibernate extends GenericHibernateDAO implements
		RegionDAO {

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.RegionDAO#findRegionsByCountryLangRegionCode(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	public List findRegionsByCountryLangRegionCode(String country,
			String language, String regionCode) {
		Criteria query = HibernateUtil.getCurrentSession()
				.createCriteria(Region.class).add(
						Expression.eq("country", country)).add(
						Expression.eq("language", language)).add(
						Expression.eq("region", regionCode)).addOrder(
						Order.asc("preferedLocation"));

		return query.list();
	}

	/**
	 * Constructor
	 * 
	 * @param persistentClass
	 * @param session
	 */
	public RegionDAOHibernate(Session session) {
		super(Region.class, session);
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findAll()
	 */
	public List findAll() {
		return null;
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findByID(java.io.Serializable)
	 */
	public Object findByID(Serializable id) {

		Object region = HibernateUtil.getCurrentSession()
				.get(Region.class, id);

		return region;

	}
}
