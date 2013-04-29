/*
 * Created on 2006-2-21
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
import com.manpower.portal.mpnet.dao.interfaces.SteeringQuestionDAO;
import com.manpower.portal.mpnet.hbn.beans.SteeringQuestion;

/**
 * @author alexander.todorov
 *  
 */
public class SteeringQuestionDAOHibernate extends GenericHibernateDAO implements
		SteeringQuestionDAO {

	/**
	 * Constructor
	 * 
	 * @param persistentClass
	 * @param session
	 */
	public SteeringQuestionDAOHibernate(Session session) {
		super(SteeringQuestion.class, session);
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findByID(java.io.Serializable)
	 */
	public Object findByID(Serializable id) {

		Session session = HibernateUtil.getCurrentSession();

		Object question = session.get(SteeringQuestion.class, id);

		return question;

	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findAll()
	 */
	public List findAll() {

		return null;
	}

	/**
	 * Find steering questions by Site and language
	 */
	public List findQuestions(long siteId, String language) {

		Session session = HibernateUtil.getCurrentSession();

		Criteria query = session.createCriteria(SteeringQuestion.class);

		//	Alexander Todorov;
		//	We will search for specified language if it is not empty or null.
		//	Else we will search questions for a site.

		if (language != null && language.trim().length() > 0) {

			query.add(Restrictions.eq("language", language));

		}

		query.add(Restrictions.eq("siteId", new Long(siteId)));

		query.addOrder(Order.asc("questionId"));

		return query.list();
	}

	/**
	 * Find legal questions by Site and language
	 */
	public List findLegalQuestions(long siteId, String language) {

		Session session = HibernateUtil.getCurrentSession();

		Criteria query = session.createCriteria(SteeringQuestion.class);

		//	Alexander Todorov;
		//	We will search for specified language if it is not empty or null.
		//	Else we will search questions for a site.

		if (language != null && language.trim().length() > 0) {

			query.add(Restrictions.eq("language", language));

		}

		query.add(Restrictions.eq("siteId", new Long(siteId)));
		query.add(Restrictions.eq("questionType", "LGL"));
		query.addOrder(Order.asc("questionId"));
		return query.list();
	}

}
