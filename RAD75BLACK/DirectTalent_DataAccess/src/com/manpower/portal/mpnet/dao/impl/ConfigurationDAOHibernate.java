/*
 * Created on Jan 20, 2006
 *
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.ConfigurationDAO;
import com.manpower.portal.mpnet.hbn.beans.UIConfiguration;

/**
 * @author jsingh
 *  
 */
public class ConfigurationDAOHibernate extends GenericHibernateDAO implements
		ConfigurationDAO {

	/**
	 * Constructor
	 * 
	 * @param persistentClass
	 * @param session
	 */
	public ConfigurationDAOHibernate(Session session) {
		super(UIConfiguration.class, session);
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findAll()
	 */
	public List findAll() {
		Criteria query = HibernateUtil.getCurrentSession()
				.createCriteria(UIConfiguration.class);
		List values = query.list();
		return values;
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findByID(java.io.Serializable)
	 */
	public Object findByID(Serializable id) {
		return null;
	}
}
