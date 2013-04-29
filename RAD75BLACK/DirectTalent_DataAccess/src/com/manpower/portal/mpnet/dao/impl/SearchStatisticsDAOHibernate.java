package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.SearchStatisticsDAO;
import com.manpower.portal.mpnet.hbn.beans.SearchStatistics;

public class SearchStatisticsDAOHibernate extends GenericHibernateDAO implements SearchStatisticsDAO{

	public SearchStatisticsDAOHibernate(Session session) {
		super(SearchStatistics.class, session);
		// TODO Auto-generated constructor stub
	}

	public List findAll() {
		Session session = HibernateUtil.getCurrentSession();
		Criteria query = session.createCriteria(SearchStatistics.class);
		return query.list();
	}

	public Object findByID(Serializable id) {
		Session session = HibernateUtil.getCurrentSession();
		return session.get(SearchStatistics.class, id);
	}

}
