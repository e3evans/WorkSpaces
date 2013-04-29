/*
 * Created on 2006-5-17
 *
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.LostCandidateDAO;
import com.manpower.portal.mpnet.hbn.beans.LostCandidate;

/**
 * @author alexander.todorov
 *  
 */
public class LostCandidateDAOHibernate extends GenericHibernateDAO implements
		LostCandidateDAO {

	public LostCandidateDAOHibernate(Session session) {
		super(LostCandidate.class, session);
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findByID(java.io.Serializable)
	 */
	public Object findByID(Serializable id) {

		return null;
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findAll()
	 */
	public List findAll() {

		return null;
	}

}
