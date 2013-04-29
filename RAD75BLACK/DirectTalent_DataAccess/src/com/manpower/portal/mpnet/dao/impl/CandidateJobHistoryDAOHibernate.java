/*
 * Created on Jan 5, 2006
 *
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateJobHistoryDAO;
import com.manpower.portal.mpnet.hbn.beans.CandidateJobHistory;
import com.manpower.portal.mpnet.hbn.util.HibernateResourseLocator;
import com.manpower.portal.mpnet.util.Constants;

/**
 * @author jsingh
 *  
 */
public class CandidateJobHistoryDAOHibernate extends GenericHibernateDAO
		implements CandidateJobHistoryDAO {

	/**
	 * Constructor
	 * 
	 * @param persistentClass
	 * @param session
	 */
	public CandidateJobHistoryDAOHibernate(Session session) {
		super(CandidateJobHistory.class, session);
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findAll()
	 */
	public List findAll() {

		return HibernateUtil
				.getSessionFactory()
				.getCurrentSession()
				.createQuery(
						HibernateResourseLocator
								.getInstance()
								.getValue(
										Constants.HIBERNATE_QUERY_CANDIDATE_JOBHISTORY_ALL_UNORDERED))
				.list();
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findByExample(java.lang.Object)
	 */
	public List findByExample(Object obj) {

		return super.findByExample(obj);
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findByID(java.io.Serializable)
	 */
	public Object findByID(Serializable id) {
		Object history = HibernateUtil.getCurrentSession()
				.get(CandidateJobHistory.class, id);
		HibernateUtil.getCurrentSession().evict(history);
		return history;
	}
}
