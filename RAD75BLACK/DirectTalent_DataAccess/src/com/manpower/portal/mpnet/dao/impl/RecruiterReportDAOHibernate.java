/*
 * Created on Sep 16, 2006
 *
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.RecruiterReportDAO;
import com.manpower.portal.mpnet.hbn.beans.Advertisement;
import com.manpower.portal.mpnet.hbn.beans.RecruiterReport;

/**
 * @author Dimitar Bakardzhiev
 *  
 */
public class RecruiterReportDAOHibernate extends GenericHibernateDAO implements
		RecruiterReportDAO {
	/**
	 * Constructor
	 * 
	 * @param persistentClass
	 * @param session
	 */
	public RecruiterReportDAOHibernate(Session session) {
		super(RecruiterReport.class, session);
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findAll()
	 */
	public List findAll() {

		return null;
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findByID(java.io.Serializable)
	 */
	public Object findByID(Serializable id) {

		return null;
	}

	/**
	 * Find resumes for Candidates by Advertisement
	 * 
	 * @param IDs
	 *            List of Candidates IDs
	 * @param advertisementId
	 *            Advertisement ID
	 */
	public List findByResumeIDs(List IDs, long advertisementId, int version) {
		if (IDs.size() == 0) {
			return new ArrayList();
		}

		Criteria query = HibernateUtil.getCurrentSession()
				.createCriteria(RecruiterReport.class).add(
						Expression.in("resumeId", IDs));

		if (advertisementId != 0) {
			query.add(Expression.or(Expression.eq("advertisementId",
					new Long(advertisementId)),Expression.eq("advertisementId",
							new Long(0))));
		}

		if(version==20)
		{
			query.add(Restrictions.eq("activeStatus", Boolean.TRUE));
			query.add(Restrictions.eq("primaryResume",Boolean.TRUE));
		}
		
		List results=query.list();
		
		 if(results!=null)
		 {
		     results=removeDuplicates(results);
		 }
		 
		return results;
	}
	
	public List removeDuplicates(List items) 
	{
	    Set set = new LinkedHashSet(items.size());
	    set.addAll(items);
	    return new ArrayList(set);
	}	
}
