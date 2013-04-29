package com.manpower.hbn.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.manpower.hbn.bgs.beans.DTAdvertContactBean;
import com.manpower.hbn.bgs.beans.DTBranchBean;
import com.manpower.hbn.dao.AdvertContactDAO;
import com.manpower.hbn.shared.dao.GenericHibernateDAO;

public class AdvertContactDAOImpl extends GenericHibernateDAO implements AdvertContactDAO {

	public AdvertContactDAOImpl(Session session) {
		super(DTBranchBean.class, session);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<Object> findAll() {
		// TODO Auto-generated method stub
		return getSession().createCriteria(DTAdvertContactBean.class).list();
	}

	@Override
	public Object findByID(Serializable id) {
		// TODO Auto-generated method stub
		return getSession().get(DTAdvertContactBean.class, id);
		
	}
	@SuppressWarnings("unchecked")
	public List<Object> findAllByBranchId(Serializable id) {
		// TODO Auto-generated method stub
		Criteria criteria = getSession().createCriteria(DTAdvertContactBean.class);
		criteria.add(Restrictions.eq("branch_id", id));
		criteria.addOrder(Order.asc("name"));
		return criteria.list();
	}


}
