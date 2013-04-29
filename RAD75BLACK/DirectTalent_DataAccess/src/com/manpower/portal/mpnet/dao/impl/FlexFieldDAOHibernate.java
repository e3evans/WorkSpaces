/*
 * Created on Jun 14, 2007
 *
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.FlexFieldDAO;
import com.manpower.portal.mpnet.hbn.beans.Candidate;
import com.manpower.portal.mpnet.hbn.beans.FlexField;

/**
 * @author Dimitar Bakardzhiev
 *  
 */
public class FlexFieldDAOHibernate extends GenericHibernateDAO implements
		FlexFieldDAO {

	public FlexFieldDAOHibernate(Session session) {
		super(FlexField.class, session);
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findByID(java.io.Serializable)
	 */
	public Object findByID(Serializable id) {
		Object flexField = HibernateUtil.getSessionFactory()
				.getCurrentSession().get(FlexField.class, id);

		return flexField;
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findAll()
	 */
	public List findAll() {
		Criteria query = HibernateUtil.getCurrentSession()
				.createCriteria(FlexField.class);

		return query.list();
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.FlexFieldDAO#findCandidate(long)
	 */
	public FlexField findByCandidate(long candidateId) {
		Criteria query = HibernateUtil.getCurrentSession()
				.createCriteria(FlexField.class);

		Object candidate = HibernateUtil.getSessionFactory()
				.getCurrentSession().load(Candidate.class,
						new Long(candidateId));

		query.add(Restrictions.eq("candidate", candidate));

		Object flexFields = query.uniqueResult();

		HibernateUtil.getCurrentSession().evict(candidate);

		HibernateUtil.getCurrentSession().evict(flexFields);

		return (FlexField) flexFields;
	}
}
