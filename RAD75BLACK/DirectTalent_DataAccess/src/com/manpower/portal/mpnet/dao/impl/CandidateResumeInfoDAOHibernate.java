/*
 * Created on Jun 13, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateResumeInfoDAO;
import com.manpower.portal.mpnet.hbn.beans.CandidateResumeInfo;

/**
 * @author Eric Evans
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class CandidateResumeInfoDAOHibernate extends GenericHibernateDAO
		implements CandidateResumeInfoDAO {

	/**
	 * @param persistentClass
	 * @param session
	 */
	public CandidateResumeInfoDAOHibernate(Session session) {
		super(CandidateResumeInfo.class, session);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findByID(java.io.Serializable)
	 */
	public Object findByID(Serializable id) {
		// TODO Auto-generated method stub
		return HibernateUtil.getCurrentSession().get(
				CandidateResumeInfo.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findAll()
	 */
	public List findAll() {
		// TODO Auto-generated method stub
		return HibernateUtil.getCurrentSession()
				.createCriteria(CandidateResumeInfo.class).list();
	}

	public List findAllByCandidateId(Serializable id) {
		Criteria criteria = HibernateUtil.getSessionFactory()
				.getCurrentSession().createCriteria(CandidateResumeInfo.class);
		criteria.add(Restrictions.eq("candidateId", id));
		return criteria.list();
	}

}
