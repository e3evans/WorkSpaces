/*
 * Created on Jul 9, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.RecruiterCommentsDAO;
import com.manpower.portal.mpnet.hbn.beans.AdvertisementContact;
import com.manpower.portal.mpnet.hbn.beans.RecruiterComments;

/**
 * @author mstoffel
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class RecruiterCommentsDAOHibernate extends GenericHibernateDAO
		implements RecruiterCommentsDAO {

	public RecruiterCommentsDAOHibernate(Session session) {
		super(RecruiterComments.class, session);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO#findByID(java.io.Serializable)
	 */
	public Object findByID(Serializable id) {
		Object recruiterComment = HibernateUtil.getSessionFactory()
				.getCurrentSession().get(RecruiterComments.class, id);
		return recruiterComment;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO#findAll()
	 */
	public List findAll() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.interfaces.RecruiterCommentsDAO#findAllCommentsByRecruiter(com.manpower.portal.mpnet.hbn.beans.AdvertisementContact)
	 */
	public List findAllCommentsByRecruiter(AdvertisementContact adContact) {
		Session session = HibernateUtil.getCurrentSession();
        Query query = session.createQuery("from RecruiterComments where recruiter_id = :recId");
        query.setParameter("recId", new Long(adContact.getId()));
		return query.list();
	}

}
