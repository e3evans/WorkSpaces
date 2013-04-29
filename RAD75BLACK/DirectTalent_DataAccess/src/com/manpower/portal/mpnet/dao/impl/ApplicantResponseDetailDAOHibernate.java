/*
 * Created on Oct 11, 2007
 *
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.ApplicantResponseDetailDAO;
import com.manpower.portal.mpnet.hbn.beans.ApplicantResponseDetail;

/**
 * @author ssprout1
 *  
 */
public class ApplicantResponseDetailDAOHibernate extends GenericHibernateDAO
		implements ApplicantResponseDetailDAO {

	/**
	 * Constructor
	 * 
	 * @param persistentClass
	 * @param session
	 */
	public ApplicantResponseDetailDAOHibernate(Session session) {
		super(ApplicantResponseDetail.class, session);
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

		Object applicantResponseDetail = HibernateUtil.getSessionFactory()
				.getCurrentSession().get(ApplicantResponseDetail.class, id);
		HibernateUtil.getCurrentSession().evict(
				applicantResponseDetail);
		return applicantResponseDetail;
	}

	/**
	 * Find Resumes by a list of Applicant Response IDs
	 */
	public List findByApplicantResponseID(long applicantResponseID) {
		return HibernateUtil.getCurrentSession()
				.createCriteria(ApplicantResponseDetail.class).add(
						Expression.eq("applicantResponseId", new Long(
								applicantResponseID))).list();
	}
}
