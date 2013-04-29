/*
 * Created on Jan 20, 2006
 *
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.AddressDAO;
import com.manpower.portal.mpnet.hbn.beans.Address;
import com.manpower.portal.mpnet.hbn.beans.Candidate;

/**
 * 
 * @author jsingh
 *
 */
public class AddressDAOHibernate extends GenericHibernateDAO implements
		AddressDAO {
	private static final Logger logger = Logger
			.getLogger(AddressDAOHibernate.class);

	/**
	 * Constructor
	 * 
	 * @param persistentClass
	 * @param session
	 */
	public AddressDAOHibernate(Session session) {
		super(Address.class, session);
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

		Object address = HibernateUtil.getCurrentSession()
				.get(Address.class, id);

		return address;
	}

	/**
	 * Find persisted Address object by Candidate ID
	 *
	 * @return Persisted object
	 */
	public Object findAddressByCandidate(Serializable candiateId) {

		Session session = HibernateUtil.getCurrentSession();

		Criteria query = session.createCriteria(Address.class);

		Object candidate = session.load(Candidate.class, candiateId);

		query.add(Restrictions.eq("candidate", candidate));

		Object address = query.uniqueResult();

		session.evict(candidate);

		session.evict(address);

		return address;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#delete(java.lang.Object)
	 */
	public Object delete(Object obj) {
		try {
			// TODO Auto-generated method stub
			HibernateUtil.getCurrentSession().delete(obj);
		} catch (RuntimeException e) {
			if (logger.isEnabledFor(Level.ERROR)) {
				logger.error(e.getMessage());
			}
			throw e;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#makeUpdate(java.lang.Object)
	 */
	public Object makeUpdate(Object obj) {
		try {
			
			HibernateUtil.getCurrentSession().saveOrUpdate(
					obj);
		} catch (RuntimeException e) {
			if (logger.isEnabledFor(Level.ERROR)) {
				logger.error(e.getMessage());
			}
			throw e;
		}
		return null;
	}
}
