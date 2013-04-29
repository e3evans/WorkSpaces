package com.manpower.directtalentseo.hbn.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import com.manpower.directtalentseo.hbn.beans.TAdContact;
import com.manpower.directtalentseo.hbn.dao.TadContactDAO;
import com.manpower.directtalentseo.hbn.shared.dao.GenericHibernateDAO;

public class TAdContactDAOImpl extends GenericHibernateDAO implements TadContactDAO{

	public TAdContactDAOImpl(Session session) {
		super(TAdContact.class, session);
		// TODO Auto-generated constructor stub
	}

	public List findAll() {
		// TODO Auto-generated method stub
		return getSession().createCriteria(TAdContact.class).list();
	}

	public Object findByID(Serializable id) {
		// TODO Auto-generated method stub
		return getSession().get(TAdContact.class, id);
	}

}
