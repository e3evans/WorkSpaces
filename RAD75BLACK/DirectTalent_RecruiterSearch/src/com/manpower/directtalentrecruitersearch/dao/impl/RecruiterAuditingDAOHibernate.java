package com.manpower.directtalentrecruitersearch.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import com.manpower.directtalentrecruitersearch.dao.RecruiterAuditingDAO;
import com.manpower.directtalentrecruitersearch.hbn.shared.HibernateUtilities;
import com.manpower.directtalentrecruitersearch.hbn.shared.dao.GenericHibernateDAO;
import com.manpower.hbn.beans.RecruiterAuditing;

public class RecruiterAuditingDAOHibernate extends GenericHibernateDAO implements RecruiterAuditingDAO {

	public RecruiterAuditingDAOHibernate(Session session) {
		super(RecruiterAuditing.class, session);
	}
	
	public List getRecentSearchesByActionAndRecruiterId(int count, String action, long recruiterId) {
		Session session = HibernateUtilities.currentSession();
		
		Criteria criteria = session.createCriteria(RecruiterAuditing.class);
		criteria.add(Expression.eq("action", action));
		criteria.add(Expression.eq("recruiter_id", new Long(recruiterId)));
		criteria.addOrder(Order.desc("created_On"));
		criteria.setMaxResults(count);
		List results = criteria.list(); 
		HibernateUtilities.closeSession();
		return results;
	}

	public RecruiterAuditing saveRecruiterAuditing(RecruiterAuditing recruiterAuditing) {
		Session session = HibernateUtilities.currentSession();
		session.saveOrUpdate(recruiterAuditing);
		session.flush();
		HibernateUtilities.closeSession();
		return recruiterAuditing;
	}
	
	public List findAll() {
		Session session = HibernateUtilities.currentSession();
		Criteria criteria = session.createCriteria(RecruiterAuditing.class);
		List results = criteria.list(); 
		HibernateUtilities.closeSession();
		return results;
	}

	public Object findByID(Serializable id) {
		Session session = HibernateUtilities.currentSession();
		Object result = session.get(RecruiterAuditing.class, id);
		HibernateUtilities.closeSession();
		return result;
	}
}
