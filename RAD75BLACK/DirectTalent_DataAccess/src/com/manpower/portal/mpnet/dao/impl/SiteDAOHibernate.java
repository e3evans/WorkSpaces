/*
 * Created on Jan 23, 2006
 *
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.SiteDAO;
import com.manpower.portal.mpnet.hbn.beans.Site;

/**
 * @author jsingh
 *  
 */
public class SiteDAOHibernate extends GenericHibernateDAO implements SiteDAO {

	/**
	 * Constructor
	 * 
	 * @param persistentClass
	 * @param session
	 */
	public SiteDAOHibernate(Session session) {
		super(Site.class, session);
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findAll()
	 */
	public List findAll() {

		Session session = HibernateUtil.getCurrentSession();

		Criteria query = session.createCriteria(Site.class);

		List result = query.list();

		return result;
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findByID(java.io.Serializable)
	 */
	public Object findByID(Serializable id) {
		Session session = HibernateUtil.getCurrentSession();

		Object site = session.get(Site.class, id);

		return site;
	}

	/*
	 * @see com.manpower.portal.mpnet.dao.interfaces.SiteDAO#findByCountryCode(java.lang.String)
	 */
	public List findByCountryCode(String countryCode) {
		Session session = HibernateUtil.getCurrentSession();
		Criteria cquery = session.createCriteria(Site.class).add(
				Expression.eq("country", countryCode));
		return cquery.list();
	}

	public List findAllSitesForRecruiter(long recruiterId) {
		Session session = HibernateUtil.getCurrentSession();
		Criteria query = session.createCriteria(Site.class);
		query
				.add(Restrictions
						.sqlRestriction(
								"{alias}.COUNTRYCODE=( select AC.COUNTRY from ADVERTISEMENTCONTACTS AC where AC.ADVERTCONTACTID=(?))",
								new Long(recruiterId), Hibernate.LONG));

		return query.list();
	}
	
	public List findSitesByCountries(String countries[]){
		Session session = HibernateUtil.getCurrentSession();
		Criteria query = session.createCriteria(Site.class);
		query.add(Restrictions.in("country", countries));
		List results = query.list();
		return results;
	}
}
