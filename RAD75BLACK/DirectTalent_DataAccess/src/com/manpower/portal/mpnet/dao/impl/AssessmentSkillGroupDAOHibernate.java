/*
 * Created on 2007-11-7
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.AssessmentSkillGroupDAO;
import com.manpower.portal.mpnet.hbn.beans.AssessmentSkillGroup;

/**
 * @author velin.doychinov
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AssessmentSkillGroupDAOHibernate extends GenericHibernateDAO implements AssessmentSkillGroupDAO {

	private static final Logger logger = Logger.getLogger(AssessmentSkillGroupDAOHibernate.class);
	
	/**
	 * Constructor
	 * 
	 * @param session
	 */
	public AssessmentSkillGroupDAOHibernate(Session session) {
		super(AssessmentSkillGroup.class, session);
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO#findByID(java.io.Serializable)
	 */
	public Object findByID(Serializable id) {
		Object obj = HibernateUtil.getCurrentSession().get(AssessmentSkillGroup.class, id);
		
		return obj;
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO#findAll()
	 */
	public List findAll() {
        Criteria query=HibernateUtil.getCurrentSession().createCriteria(AssessmentSkillGroup.class);
        
        return query.list();
	}

}
