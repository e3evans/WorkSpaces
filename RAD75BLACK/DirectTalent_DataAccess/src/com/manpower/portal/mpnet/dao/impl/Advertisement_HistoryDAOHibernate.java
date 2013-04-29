/*
 * Created on Jan 23, 2006
 *
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.Advertisement_HistoryDAO;
import com.manpower.portal.mpnet.hbn.beans.Advertisement_History;


/**
 * @author jsingh
 *  
 */
public class Advertisement_HistoryDAOHibernate extends GenericHibernateDAO implements Advertisement_HistoryDAO  {
    
    private static Logger logger = Logger.getLogger(Advertisement_HistoryDAOHibernate.class.getName());

	/**
	 * Constructor
	 * 
	 * @param persistentClass
	 * @param session
	 */
	public Advertisement_HistoryDAOHibernate(Session session) {
		super(Advertisement_History.class, session);
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findAll()
	 */
	public List findAll() {
		
		return HibernateUtil.getCurrentSession().createCriteria(Advertisement_History.class).list();
	}

	public Object findByID(Serializable id) {
		// TODO Auto-generated method stub

		return HibernateUtil.getCurrentSession().get(Advertisement_History.class, id);
	}
	
	public List findAllByCandidateId(Serializable id){
		if (logger.isDebugEnabled())
        {
			logger.debug("Parameters:  "+id);
        }

		Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(Advertisement_History.class);
		criteria.add(Restrictions.eq(Advertisement_History.MF_CANDIDATE_ID, id));
		criteria.addOrder(Order.desc(Advertisement_History.MF_CREATED_ON));
		return criteria.list();
	}
	
	public Object delete(Object obj) {
		// TODO Auto-generated method stub
		return super.delete(obj);
	}
	
	public Object makePersistent(Object obj) {
		// TODO Auto-generated method stub
		return super.makePersistent(obj);
	}
	
//	private Session getSession(){
//		return HibernateUtil.getCurrentSession();
//	}

	
	
}
