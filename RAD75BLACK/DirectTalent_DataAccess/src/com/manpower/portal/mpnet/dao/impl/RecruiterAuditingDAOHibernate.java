/*
 * Created on Jul 9, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.RecruiterAuditingDAO;
import com.manpower.portal.mpnet.hbn.beans.RecruiterAuditing;

/**
 * @author mstoffel
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class RecruiterAuditingDAOHibernate extends GenericHibernateDAO
		implements RecruiterAuditingDAO {

	public RecruiterAuditingDAOHibernate(Session session) {
		super(RecruiterAuditing.class, session);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO#findByID(java.io.Serializable)
	 */
	public Object findByID(Serializable id) {
		Session session = HibernateUtil.getCurrentSession();
		Object recruiterAuditing = session.get(RecruiterAuditing.class, id);
		
		return recruiterAuditing;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO#findAll()
	 */
	public List findAll() {
		return null;
	}



}
