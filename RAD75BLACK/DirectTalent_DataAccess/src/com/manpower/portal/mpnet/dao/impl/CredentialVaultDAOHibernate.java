/*
 * Created on Aug 14, 2006
 *
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.CredentialVaultDAO;
import com.manpower.portal.mpnet.hbn.beans.CredentialVault;

/**
 * @author jsingh
 *  
 */
public class CredentialVaultDAOHibernate extends GenericHibernateDAO implements
		CredentialVaultDAO {

	/**
	 * Constructor
	 * 
	 * @param persistentClass
	 * @param session
	 */
	public CredentialVaultDAOHibernate(Session session) {
		super(CredentialVault.class, session);
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO#findByID(java.io.Serializable)
	 */
	public Object findByID(Serializable id) {
		Object ch = HibernateUtil.getCurrentSession().get(
				CredentialVault.class, id);
		HibernateUtil.getCurrentSession().evict(ch);
		return ch;
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO#findAll()
	 */
	public List<CredentialVault> findAll() {

		Session session = HibernateUtil.getCurrentSession();

		Criteria query = session.createCriteria(CredentialVault.class);

		List<CredentialVault> result = query.list();

		return result;
	}
	
	public List<CredentialVault> findCredentials(String siteCode, String login, String type) {

		Session session = HibernateUtil.getCurrentSession();
		
		Criteria query = session.createCriteria(CredentialVault.class);

		query.add(Restrictions.eq("siteCode", siteCode));
		query.add(Restrictions.eq("login", login));
		query.add(Restrictions.eq("type", type));
		query.add(Restrictions.eq("requestType", "AUTHENTICATE"));
		query.addOrder(Order.asc("created_On"));
		
		List<CredentialVault> credentials = query.list(); 
		return credentials;
	}
	
	public List<CredentialVault> findCredentialLogins(String siteCode, String login, String type) {

		Session session = HibernateUtil.getCurrentSession();
		
		Criteria query = session.createCriteria(CredentialVault.class);

		query.add(Restrictions.eq("siteCode", siteCode));
		query.add(Restrictions.eq("login", login));
		query.add(Restrictions.eq("type", type));
		query.add(Restrictions.eq("requestType", "LOGIN"));
		query.addOrder(Order.desc("created_On"));
		
		List<CredentialVault> credentials = query.list(); 
		return credentials;
	}
	
	public Object makePersistent(Object obj) {
		Session session = HibernateUtil.getCurrentSession();
		session.saveOrUpdate(obj);
		return obj;
	}

}
