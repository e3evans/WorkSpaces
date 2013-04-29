/*
 * Created on 2006-7-5
 *
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.PostalCodeAddressDAO;
import com.manpower.portal.mpnet.hbn.beans.PostalCodeAddress;

/**
 * @author alexander.todorov
 *  
 */
public class PostalCodeAddressDAOHibernate extends GenericHibernateDAO
		implements PostalCodeAddressDAO {

	/**
	 * Constructor
	 * 
	 * @param persistentClass
	 * @param session
	 */
	public PostalCodeAddressDAOHibernate(Session session) {
		super(PostalCodeAddress.class, session);
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

		Object postalAddress = HibernateUtil.getSessionFactory()
				.getCurrentSession().get(PostalCodeAddress.class, id);

		return postalAddress;

	}
}
