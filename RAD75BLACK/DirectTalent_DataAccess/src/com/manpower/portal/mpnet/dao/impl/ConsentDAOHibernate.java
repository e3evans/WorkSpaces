/*
 * Created on Jun 6, 2007
 *
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.ConsentDAO;

/**
 * @author Dimitar Bakardzhiev
 *  
 */
public class ConsentDAOHibernate extends GenericHibernateDAO implements
		ConsentDAO {
	/**
	 * Constructor
	 * 
	 * @param persistentClass
	 * @param session
	 */
	public ConsentDAOHibernate(Session session) {
		super(ConsentDAOHibernate.class, session);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findByID(java.io.Serializable)
	 */
	public Object findByID(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findAll()
	 */
	public List findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
