package com.manpower.portal.gemt.hbn.object.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.manpower.portal.gemt.hbn.beans.GemtManagersHbnBean;
import com.manpower.portal.gemt.hbn.object.dao.GemtManagersDAO;
import com.manpower.portal.gemt.hbn.shared.dao.GenericHibernateDAO;

public class GemtManagersDAOImpl extends GenericHibernateDAO implements GemtManagersDAO{

	public GemtManagersDAOImpl(Session session) {
		super(GemtManagersHbnBean.class, session);
		// TODO Auto-generated constructor stub
	}

	public List findAll() {
		// TODO Auto-generated method stub
		return getSession().createCriteria(GemtManagersHbnBean.class).list();
	}

	public Object findByEmail(String eMail){
		Criteria criteria = getSession().createCriteria(GemtManagersHbnBean.class);
		criteria.add(Restrictions.eq("gemt_sum_mgremail", eMail));
		return criteria.uniqueResult();
	}
	public Object findByID(Serializable id) {
		// TODO Auto-generated method stub
		return getSession().get(GemtManagersHbnBean.class, id);
	}

	public Object makePersistent(Object obj) {
		// TODO Auto-generated method stub
		return super.makePersistent(obj);
	}
}
