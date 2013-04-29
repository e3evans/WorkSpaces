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
import com.manpower.portal.mpnet.dao.interfaces.CandidateEducationDAO;
import com.manpower.portal.mpnet.hbn.beans.CandidateEducation;
import com.manpower.portal.mpnet.hbn.util.HibernateResourseLocator;
import com.manpower.portal.mpnet.util.Constants;

/**
 * @author jsingh
 *  
 */
public class CandidateEducationDAOHibernate extends GenericHibernateDAO
		implements CandidateEducationDAO {

	/**
	 * Constructor
	 * 
	 * @param persistentClass
	 * @param session
	 */
	public CandidateEducationDAOHibernate(Session session) {
		super(CandidateEducation.class, session);
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
										Constants.HIBERNATE_QUERY_CANDIDATE_EDUCATION_ALL_UNORDERED))
				.list();
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findByID(java.io.Serializable)
	 */
	public Object findByID(Serializable id) {
		Object education = HibernateUtil.getSessionFactory()
				.getCurrentSession().get(CandidateEducation.class, id);
		HibernateUtil.getCurrentSession().evict(education);
		return education;
	}

}
