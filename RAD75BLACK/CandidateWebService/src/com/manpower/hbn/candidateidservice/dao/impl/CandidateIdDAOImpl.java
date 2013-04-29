package com.manpower.hbn.candidateidservice.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.manpower.hbn.candidateidservice.beans.CandidateId;
import com.manpower.hbn.candidateidservice.dao.CandidateIdDAO;
import com.manpower.hbn.shared.dao.GenericHibernateDAO;

public class CandidateIdDAOImpl extends GenericHibernateDAO implements CandidateIdDAO{

	public CandidateIdDAOImpl(Session session) {
		super(CandidateId.class, session);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List findAll() {
		// TODO Auto-generated method stub
		return getSession().createCriteria(CandidateId.class).list();
	}

	public Object findByID(Serializable id) {
		// TODO Auto-generated method stub
		return getSession().get(CandidateId.class,id);
		
	}
	
	@SuppressWarnings("unchecked")
	public Object findByUserID(String userid){
		Object result = null;
		Criteria criteria = getSession().createCriteria(CandidateId.class);
		criteria.add(Restrictions.eq("email", userid));
		List<Object> tempList = criteria.list();
		if (tempList!=null){
			if (tempList.size()>0){
				result=tempList.get(0);
			}
		}
		getSession().close();
		return result;
	}

}
