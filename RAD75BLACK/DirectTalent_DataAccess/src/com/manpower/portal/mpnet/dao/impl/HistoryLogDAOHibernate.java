/*
 * Created on Jul 5, 2007
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
import com.manpower.portal.mpnet.dao.interfaces.HistoryLogDAO;
import com.manpower.portal.mpnet.hbn.beans.HistoryLog;

/**
 * @author mstoffel
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class HistoryLogDAOHibernate extends GenericHibernateDAO implements
		HistoryLogDAO {

	/**
	 * @param persistentClass
	 * @param session
	 */
	public HistoryLogDAOHibernate(Session session) {
		super(HistoryLog.class, session);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO#findByID(java.io.Serializable)
	 */
	public Object findByID(Serializable id) {
		// Object flexField=getSession().get(FlexField.class,id);
		Object historyLog = HibernateUtil.getSessionFactory()
				.getCurrentSession().get(HistoryLog.class, id);
		return historyLog;
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
