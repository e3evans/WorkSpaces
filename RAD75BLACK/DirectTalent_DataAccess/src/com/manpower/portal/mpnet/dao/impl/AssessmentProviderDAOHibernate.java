/*
 * Created on 2007-11-6
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
import com.manpower.portal.mpnet.dao.interfaces.AssessmentProviderDAO;
import com.manpower.portal.mpnet.hbn.beans.AssessmentProvider;
/**
 * @author velin.doychinov
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AssessmentProviderDAOHibernate extends GenericHibernateDAO implements AssessmentProviderDAO {

	/**
	 * Constructor
	 * 
	 * @param session
	 */
	public AssessmentProviderDAOHibernate(Session session) {
		super(AssessmentProvider.class, session);
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO#findByID(java.io.Serializable)
	 */
	public Object findByID(Serializable id) {
		Object obj = HibernateUtil.getCurrentSession().get(AssessmentProvider.class, id);
		
		return obj;
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO#findAll()
	 */
	public List findAll() {
        Criteria query=HibernateUtil.getCurrentSession().createCriteria(AssessmentProvider.class);
        
        return query.list();
	}

}
