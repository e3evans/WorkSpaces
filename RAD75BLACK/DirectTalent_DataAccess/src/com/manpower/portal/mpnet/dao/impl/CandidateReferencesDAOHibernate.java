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
import com.manpower.portal.mpnet.dao.interfaces.CandidateReferencesDAO;
import com.manpower.portal.mpnet.hbn.beans.CandidateReferences;
import com.manpower.portal.mpnet.hbn.util.HibernateResourseLocator;
import com.manpower.portal.mpnet.util.Constants;

/**
 * @author jsingh
 *  
 */
public class CandidateReferencesDAOHibernate extends GenericHibernateDAO
		implements CandidateReferencesDAO {

	/**
	 * Constructor
	 * 
	 * @param persistentClass
	 * @param session
	 */
	public CandidateReferencesDAOHibernate(Session session) {
		super(CandidateReferences.class, session);
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
										Constants.HIBERNATE_QUERY_CANDIDATE_REFERENCES_ALL_UNORDERED))
				.list();
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findByID(java.io.Serializable)
	 */
	public Object findByID(Serializable id) {
		Object references = HibernateUtil.getSessionFactory()
				.getCurrentSession().get(CandidateReferences.class, id);
		HibernateUtil.getCurrentSession().evict(references);
		return references;
	}
}
