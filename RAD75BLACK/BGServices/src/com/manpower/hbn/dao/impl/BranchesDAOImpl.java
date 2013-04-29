package com.manpower.hbn.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.manpower.hbn.bgs.beans.DTBranchBean;
import com.manpower.hbn.dao.BranchesDAO;
import com.manpower.hbn.shared.dao.GenericHibernateDAO;

public class BranchesDAOImpl extends GenericHibernateDAO implements BranchesDAO {

	public BranchesDAOImpl(Session session) {
		super(DTBranchBean.class, session);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<Object> findAll() {
		// TODO Auto-generated method stub
		return getSession().createCriteria(DTBranchBean.class).list();
	}

	@Override
	public Object findByID(Serializable id) {
		// TODO Auto-generated method stub
		return getSession().get(DTBranchBean.class, id);
		
	}
	@SuppressWarnings("unchecked")
	public List<Object> findAllBySiteId(Serializable id) {
		// TODO Auto-generated method stub
		Criteria criteria = getSession().createCriteria(DTBranchBean.class);
		criteria.add(Restrictions.eq("site_id", id));
		criteria.addOrder(Order.asc("branchname"));
		return criteria.list();
	}

}
