/*
 * Created on Jun 19, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.ReportingMetricsDAO;
import com.manpower.portal.mpnet.hbn.beans.ReportingMetrics;
import com.manpower.portal.mpnet.hbn.util.HibernateResourseLocator;
import com.manpower.portal.mpnet.util.Constants;

/**
 * @author vindukur
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ReportingMetricsDAOHibernate extends GenericHibernateDAO implements
		ReportingMetricsDAO {

	/**
	 *  
	 */
	public ReportingMetricsDAOHibernate(Session session) {
		super(ReportingMetrics.class, session);
	}

	/*
	 * @see com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO#findByID(java.io.Serializable)
	 */
	public Object findByID(Serializable id) {
		return HibernateUtil.getCurrentSession().get(
				ReportingMetrics.class, id);
	}

	/*
	 * @see com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO#findAll()
	 */
	public List findAll() {
		return HibernateUtil
				.getSessionFactory()
				.getCurrentSession()
				.createQuery(
						HibernateResourseLocator
								.getInstance()
								.getValue(
										Constants.HIBERNATE_QUERY_REPORTING_METRICS_ALL_UNORDERED))
				.list();
	}

	/*
	 * @see com.manpower.portal.mpnet.dao.interfaces.ReportingMetricsDAO#findBySiteID(java.lang.String,
	 *      long)
	 */
	public List findBySiteID(long siteId) {
		Criteria cquery = HibernateUtil.getCurrentSession()
				.createCriteria(ReportingMetrics.class).add(
						Expression.eq("siteId", new Long(siteId)));
		return cquery.list();
	}

	/*
	 * @see com.manpower.portal.mpnet.dao.interfaces.ReportingMetricsDAO#findByCountryCode(java.lang.String)
	 */
	public List findByCountryCode(String countryCode) {
		Criteria cquery = HibernateUtil.getCurrentSession()
				.createCriteria(ReportingMetrics.class).add(
						Expression.eq("countryCode", countryCode));
		return cquery.list();
	}

}
