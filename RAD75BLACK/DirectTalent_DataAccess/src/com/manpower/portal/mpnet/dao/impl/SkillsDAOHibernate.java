/*
 * Created on Jan 23, 2006
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
import com.manpower.portal.mpnet.dao.interfaces.SkillsDAO;
import com.manpower.portal.mpnet.hbn.beans.Skills;

/**
 * @author jsingh
 *  
 */
public class SkillsDAOHibernate extends GenericHibernateDAO implements
		SkillsDAO {

	/**
	 * Constructor
	 * 
	 * @param persistentClass
	 * @param session
	 */
	public SkillsDAOHibernate(Session session) {
		super(Skills.class, session);
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findAll()
	 */
	public List findAll() {

		Session session = HibernateUtil.getCurrentSession();

		Criteria query = session.createCriteria(Skills.class);

		List result = query.list();

		return result;
	}

	/**
	 * Find all skills for Candidate by Site ID and language
	 * 
	 * @param siteId
	 * @param lang
	 * @return List of Skill objects
	 */
	public List findAll(long siteId, String lang) {

		Session session = HibernateUtil.getCurrentSession();

		Criteria query = session.createCriteria(Skills.class);

		query.add(Restrictions.eq("siteId", new Long(siteId)));

		query.add(Restrictions.eq("language", lang));
		
		query.addOrder(Order.asc("skilldescription"));

		List result = query.list();

		return result;
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findByID(java.io.Serializable)
	 */
	public Object findByID(Serializable id) {

		Session session = HibernateUtil.getCurrentSession();

		Object skill = session.get(Skills.class, id);

		return skill;
	}

}
