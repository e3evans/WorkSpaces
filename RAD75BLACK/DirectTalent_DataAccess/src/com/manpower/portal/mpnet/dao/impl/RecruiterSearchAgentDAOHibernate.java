package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.RecruiterSearchAgentDAO;
import com.manpower.portal.mpnet.hbn.beans.RecruiterSearchAgent;

public class RecruiterSearchAgentDAOHibernate extends GenericHibernateDAO implements RecruiterSearchAgentDAO {

	/**
	 * Constructor
	 * 
	 * @param persistentClass
	 * @param session
	 */
	public RecruiterSearchAgentDAOHibernate(Session session) {
		super(RecruiterSearchAgent.class, session);
	}

	@Override
	public List<RecruiterSearchAgent> findAll() {
		return new ArrayList<RecruiterSearchAgent>();
	}

	@Override
	public Object findByID(Serializable id) {
		Object result = HibernateUtil.getSessionFactory().getCurrentSession().get(RecruiterSearchAgent.class, id);
		return result;
	}

	/**
	 * 
	 * @param portletName
	 * @param recruiterId
	 * @param orderBy - 1 sorts by creation date asc, 2 by creation date desc, 3 by name asc, 4 by name desc
	 * @return
	 */
	public List<RecruiterSearchAgent> getRecruiterSearchAgentsByPortletRecruiterId(String portletName, long recruiterId, int orderBy) {
		Criteria query = HibernateUtil.getCurrentSession().createCriteria(RecruiterSearchAgent.class);
		query.add(Restrictions.eq("portletName", portletName));
		query.add(Restrictions.eq("recruiterId", new Long(recruiterId)));
		switch (orderBy) {
		case 1:
			query.addOrder(Order.asc("created_On"));
			break;
		case 2:
			query.addOrder(Order.desc("created_On"));
			break;
		case 3:
			query.addOrder(Order.asc("agentName").ignoreCase());
			break;
		case 4:
			query.addOrder(Order.desc("agentName").ignoreCase());
			break;
		default:
			query.addOrder(Order.desc("created_On"));
			break;
		}
		return (List<RecruiterSearchAgent>)query.list();
	}
}
