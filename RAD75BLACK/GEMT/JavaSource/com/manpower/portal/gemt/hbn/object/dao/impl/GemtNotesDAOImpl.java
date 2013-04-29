/*
 * Created on Jan 19, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.gemt.hbn.object.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import com.manpower.portal.gemt.hbn.beans.GemtNotesHbnBean;
import com.manpower.portal.gemt.hbn.object.dao.GemtNotesDAO;
import com.manpower.portal.gemt.hbn.shared.dao.GenericHibernateDAO;
 
/**
 * @author Eric Evans
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GemtNotesDAOImpl extends GenericHibernateDAO implements GemtNotesDAO{

	/**
	 * @param persistentClass
	 * @param session
	 */
	public GemtNotesDAOImpl(Session session) {
		
		super(GemtNotesHbnBean.class, session);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.gemt.hbn.shared.dao.DAO#findByID(java.io.Serializable)
	 */
	public Object findByID(Serializable id) {
		// TODO Auto-generated method stub
		return getSession().get(GemtNotesHbnBean.class,id);
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.gemt.hbn.shared.dao.DAO#findAll()
	 */
	public List findAll() {
		// TODO Auto-generated method stub
		return getSession().createCriteria(GemtNotesHbnBean.class).list();
	}

	
	/* (non-Javadoc)
	 * @see com.manpower.portal.gemt.hbn.shared.dao.DAO#makePersistent(java.lang.Object)
	 */
	public Object makePersistent(Object obj) {
		// TODO Auto-generated method stub
		return super.makePersistent(obj);
	}
	
	/* (non-Javadoc)
	 * @see com.manpower.portal.gemt.hbn.shared.dao.DAO#delete(java.lang.Object)
	 */
	public Object delete(Object obj) {
		// TODO Auto-generated method stub
		return super.delete(obj);
	}
}
