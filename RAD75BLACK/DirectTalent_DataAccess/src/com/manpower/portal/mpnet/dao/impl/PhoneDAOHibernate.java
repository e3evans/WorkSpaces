/*
 * Created on Jan 20, 2006
 *
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.PhoneDAO;
import com.manpower.portal.mpnet.hbn.beans.Candidate;
import com.manpower.portal.mpnet.hbn.beans.Phone;
import com.manpower.portal.mpnet.util.MPNETAppConstants;

/**
 * @author jsingh
 *  
 */
public class PhoneDAOHibernate extends GenericHibernateDAO implements PhoneDAO {

	/**
	 * Constructor
	 * 
	 * @param persistentClass
	 * @param session
	 */
	public PhoneDAOHibernate(Session session) {
		super(Phone.class, session);
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

		Object phone = HibernateUtil.getCurrentSession()
				.get(Phone.class, id);

		return phone;

	}

	/**
	 * Find Candidate phone
	 * 
	 * @return Phone object
	 */
	public Object findPhoneByCandidate(Serializable candiateId,
			String primaryPhone) {

		Session session = HibernateUtil.getCurrentSession();

		Criteria query = session.createCriteria(Phone.class);

		Object candidate = session.load(Candidate.class, candiateId);

		query.add(Restrictions.eq("candidate", candidate));

		if (primaryPhone == null || primaryPhone.trim().length() != 1) {

			primaryPhone = MPNETAppConstants.YES_ANSWER;

		}

		query.add(Restrictions.eq("primary", primaryPhone));

		Object phone = query.uniqueResult();

		session.evict(phone);

		session.evict(candidate);

		return phone;
	}

	/*
	 * Find all of the candidate phone numbers. Order the list by Primary phone =
	 * "Y" as first number listed.
	 *  
	 */
	public List findCandidatePhones(Serializable candidateId) {
		Criteria criteria = HibernateUtil.getSessionFactory()
				.getCurrentSession().createCriteria(Phone.class);
		Object candidate = HibernateUtil.getCurrentSession().load(Candidate.class, candidateId);
		criteria.add(Restrictions.eq("candidate", candidate));
		criteria = addOrder(criteria, "primary", ORDER_DESC);
		return criteria.list();
	}

	public void deletePhone(long id){
		Session session = HibernateUtil.getCurrentSession();
		Phone phone = (Phone)session.load(Phone.class, new Long(id));
		session.delete(phone);
	}
}
