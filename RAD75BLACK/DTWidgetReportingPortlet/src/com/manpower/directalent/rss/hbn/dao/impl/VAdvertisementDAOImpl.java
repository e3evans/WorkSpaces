package com.manpower.directalent.rss.hbn.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.manpower.directalent.rss.hbn.beans.VAdvertismentHibBean;
import com.manpower.directalent.rss.hbn.dao.VAdvertisementDAO;
import com.manpower.directalent.rss.hbn.shared.dao.GenericHibernateDAO;


public class VAdvertisementDAOImpl extends GenericHibernateDAO implements VAdvertisementDAO{

	public VAdvertisementDAOImpl(Session session) {
		super(VAdvertismentHibBean.class, session);
		// TODO Auto-generated constructor stub
	}

	public List findAll() {
		// TODO Auto-generated method stub
		return getSession().createCriteria(VAdvertismentHibBean.class).list();
	}

	public Object findByID(Serializable id) {
		// TODO Auto-generated method stub
		return getSession().get(VAdvertismentHibBean.class,id);
	}
	
	public List findBySiteId(Serializable id){
		Criteria criteria = getSession().createCriteria(VAdvertismentHibBean.class);
		criteria.add(Restrictions.eq("site_id", id));
		criteria = addOrder(criteria, "created_on", ORDER_DESC);
		return criteria.list();
	}

}
