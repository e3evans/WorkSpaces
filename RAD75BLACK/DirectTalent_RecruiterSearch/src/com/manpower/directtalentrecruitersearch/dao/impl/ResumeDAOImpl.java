package com.manpower.directtalentrecruitersearch.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import com.manpower.directtalentrecruitersearch.dao.ResumeDAO;
import com.manpower.directtalentrecruitersearch.hbn.shared.HibernateUtilities;
import com.manpower.directtalentrecruitersearch.hbn.shared.dao.GenericHibernateDAO;
import com.manpower.hbn.beans.Resume;

public class ResumeDAOImpl extends GenericHibernateDAO implements ResumeDAO{

	public ResumeDAOImpl(Session session) {
		super(Resume.class, session);
		// TODO Auto-generated constructor stub
	}

	public List findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object findByID(Serializable id) {
		// TODO Auto-generated method stub
		return getSession().get(Resume.class, id);
	}

	public Object findByIDAndType(Serializable id,String candType){
		Session session = HibernateUtilities.currentSession();
		Criteria criteria = session.createCriteria(Resume.class);
		criteria.add(Expression.eq("resume_id",id));
		criteria.add(Expression.eq("cand_type", candType));
		List temp = new ArrayList();
		temp = criteria.list();
		if (temp!=null)return temp.get(0);
		return null;
	}
	
}
