/*
 * Created on Jan 23, 2006
 *
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateCoverletterDAO;
import com.manpower.portal.mpnet.hbn.beans.CandidateCoverletter;
import com.manpower.portal.mpnet.hbn.util.HibernateResourseLocator;
import com.manpower.portal.mpnet.util.Constants;

/**
 * @author jsingh
 *  
 */
public class CandidateCoverletterDAOHibernate extends GenericHibernateDAO
		implements CandidateCoverletterDAO {

	/**
	 * Constructor
	 * 
	 * @param persistentClass
	 * @param session
	 */
	public CandidateCoverletterDAOHibernate(Session session) {
		super(CandidateCoverletter.class, session);
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
										Constants.HIBERNATE_QUERY_CANDIDATE_COVERLETTER_ALL_UNORDERED))
				.list();
	}

	public List findByExample(Object obj) {

		return super.findByExample(obj);
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findByID(java.io.Serializable)
	 */
	public Object findByID(Serializable id) {

		return HibernateUtil.getCurrentSession().get(
				CandidateCoverletter.class, id);
	}

	public Object delete(Object anObject) {
		Session aSession = HibernateUtil.getSessionFactory()
				.getCurrentSession();
		aSession.delete(anObject);
		return anObject;
	}
}
