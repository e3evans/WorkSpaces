/*
 * Created on Aug 14, 2006
 *
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.CareerHarmonyDAO;
import com.manpower.portal.mpnet.hbn.beans.CareerHarmony;

/**
 * @author jsingh
 *  
 */
public class CareerHarmonyDAOHibernate extends GenericHibernateDAO implements
		CareerHarmonyDAO {

	/**
	 * Constructor
	 * 
	 * @param persistentClass
	 * @param session
	 */
	public CareerHarmonyDAOHibernate(Session session) {
		super(CareerHarmony.class, session);
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO#findByID(java.io.Serializable)
	 */
	public Object findByID(Serializable id) {
		Object ch = HibernateUtil.getCurrentSession().get(
				CareerHarmony.class, id);
		HibernateUtil.getCurrentSession().evict(ch);
		return ch;
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO#findAll()
	 */
	public List findAll() {

		Session session = HibernateUtil.getCurrentSession();

		Criteria query = session.createCriteria(CareerHarmony.class);

		List result = query.list();

		return result;
	}

}
