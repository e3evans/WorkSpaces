/*
 * Created on Nov 8, 2007
 *
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.NA_JobTypeDAO;
import com.manpower.portal.mpnet.hbn.beans.NA_JobClass;
import com.manpower.portal.mpnet.hbn.beans.NA_JobType;

/**
 * @author auljane1
 *
 */
public class NA_JobTypeDAOHibernate extends GenericHibernateDAO implements NA_JobTypeDAO {
	
	/**
	 * Constructor
	 * 
	 * @param persistentClass
	 * @param session
	 */
	public NA_JobTypeDAOHibernate(Session session) {
		super(NA_JobTypeDAOHibernate.class, session);
	}
	
	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findByID(java.io.Serializable)
	 */
	public Object findByID(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findAll()
	 */
	public List findAll() {
		return HibernateUtil.getCurrentSession().createCriteria(NA_JobType.class).list();
	}

    /**
     * @see com.manpower.portal.mpnet.dao.interfaces.NA_JobTypeDAO#findByName(java.lang.String)
     */
    public NA_JobType findByName(String jobTypeName)
    {
        Criteria query=HibernateUtil.getCurrentSession().createCriteria(NA_JobType.class).add(Restrictions.eq("display",jobTypeName));
        return (NA_JobType)query.uniqueResult();
    }
    /**
     * @see com.manpower.portal.mpnet.dao.interfaces.NA_JobTypeDAO#findJobClass(java.lang.String, java.lang.String)
     */
    public List findJobClass(String jobTypeName, String jobClassName)
    {
        NA_JobType jobType=findByName(jobTypeName);
        if(jobType!=null)
        {
            Criteria query=HibernateUtil.getCurrentSession().createCriteria(NA_JobClass.class).add(Restrictions.and(Restrictions.eq("jobClass",jobClassName),Restrictions.eq("jobTypeId",new Long(jobType.getId()))));
            
            return query.list();
        }
        else
        {
            return null;
        }
    }
}
