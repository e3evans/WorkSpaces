package com.manpower.directalent.rss.hbn.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import com.manpower.directalent.rss.hbn.beans.RptResults;
import com.manpower.directalent.rss.hbn.dao.RptResultsDAO;
import com.manpower.directalent.rss.hbn.shared.dao.GenericHibernateDAO;

public class RptResultsDAOImpl extends GenericHibernateDAO implements RptResultsDAO {

	public RptResultsDAOImpl(Session session) {
		super(RptResults.class, session);
		// TODO Auto-generated constructor stub
	}

	public List findAll() {
		// TODO Auto-generated method stub
		return getSession().createCriteria(RptResults.class).list();
	}

	public Object findByID(Serializable id) {
		// TODO Auto-generated method stub
		return getSession().get(RptResults.class,id);
	}

}
