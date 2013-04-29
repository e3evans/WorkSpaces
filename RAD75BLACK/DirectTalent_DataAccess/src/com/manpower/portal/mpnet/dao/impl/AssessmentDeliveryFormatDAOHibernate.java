/*
 * Created on 2007-11-7
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.AssessmentDeliveryFormatDAO;
import com.manpower.portal.mpnet.hbn.beans.AssessmentDeliveryFormat;

/**
 * @author velin.doychinov
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AssessmentDeliveryFormatDAOHibernate extends GenericHibernateDAO implements AssessmentDeliveryFormatDAO {

	/**
	 * Constructor
	 * 
	 * @param session
	 */
	public AssessmentDeliveryFormatDAOHibernate(Session session) {
		super(AssessmentDeliveryFormat.class, session);
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO#findByID(java.io.Serializable)
	 */
	public Object findByID(Serializable id) {
		Object obj = HibernateUtil.getCurrentSession().get(AssessmentDeliveryFormat.class, id);
		
		return obj;
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO#findAll()
	 */
	public List findAll() {
        Criteria query=HibernateUtil.getCurrentSession().createCriteria(AssessmentDeliveryFormat.class);
        
        return query.list();
	}

}