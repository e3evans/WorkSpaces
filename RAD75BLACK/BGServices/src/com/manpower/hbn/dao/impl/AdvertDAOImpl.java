package com.manpower.hbn.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.manpower.hbn.bgs.beans.DTAdvertBean;
import com.manpower.hbn.bgs.beans.DTBranchBean;
import com.manpower.hbn.dao.AdvertDAO;
import com.manpower.hbn.shared.dao.GenericHibernateDAO;

public class AdvertDAOImpl extends GenericHibernateDAO implements AdvertDAO {

	public AdvertDAOImpl(Session session) {
		super(DTBranchBean.class, session);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<Object> findAll() {
		// TODO Auto-generated method stub
		return getSession().createCriteria(DTAdvertBean.class).list();
	}

	@Override
	public Object findByID(Serializable id) {
		// TODO Auto-generated method stub
		return getSession().get(DTAdvertBean.class, id);
		
	}

	@SuppressWarnings("unchecked")
	public List<Object> findAllByContactId(Serializable id) {
		Criteria criteria = getSession().createCriteria(DTAdvertBean.class);
		criteria.add(Restrictions.eq("advertcontactid", id));
		criteria.addOrder(Order.asc("jobtitle"));
		return criteria.list();
	}


}
