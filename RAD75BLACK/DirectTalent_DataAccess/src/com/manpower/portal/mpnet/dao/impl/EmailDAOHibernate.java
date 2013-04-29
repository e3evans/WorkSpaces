/*
 * Created on 16-mar-2006
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.EmailDAO;
import com.manpower.portal.mpnet.hbn.beans.Candidate;
import com.manpower.portal.mpnet.hbn.beans.Email;

/**
 * @author MartinMuguiro
 */
public class EmailDAOHibernate extends GenericHibernateDAO implements EmailDAO {

	/**
	 * Constructor
	 * 
	 * @param persistentClass
	 * @param session
	 */
	public EmailDAOHibernate(Session session) {
		super(Email.class, session);
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findAll()
	 */
	public List findAll() {
		return HibernateUtil.getCurrentSession()
				.createCriteria(Candidate.class).list();
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findByID(java.io.Serializable)
	 */
	public Object findByID(Serializable id) {
		Object email = HibernateUtil.getCurrentSession()
				.get(Email.class, id);

		return email;
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#makePersistent(java.lang.Object)
	 */
	public Object makePersistent(Object obj) {
		return super.makePersistent(obj);
	}
	
	public void sendEmailsToCandidates(List emails){
		if(emails != null && !emails.isEmpty()){
			Session session = HibernateUtil.getCurrentSession();
			for(int i=0; i<emails.size(); i++){
				Email email = (Email)emails.get(i);
				session.persist(email);
				if(i%10 == 0){
					session.flush();
					session.clear();
				}
			}
			
		}
	}
}
