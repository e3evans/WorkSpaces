/*
 * Created on 2007-6-15
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.OtherCandidateDetailsDAO;
import com.manpower.portal.mpnet.hbn.beans.Candidate;
import com.manpower.portal.mpnet.hbn.beans.OtherCandidateDetails;

/**
 * @author Miroslav Nachev
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class OtherCandidateDetailsDAOHibernate extends GenericHibernateDAO
		implements OtherCandidateDetailsDAO {

	/**
	 * @param persistentClass
	 * @param session
	 */
	public OtherCandidateDetailsDAOHibernate(Session session) {
		super(OtherCandidateDetails.class, session);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO#findByID(java.io.Serializable)
	 */
	public Object findByID(Serializable id) {
		Session session = null;
		//Transaction tx = null;
		OtherCandidateDetails otherCandidateDetails = null;
		try {
			session = HibernateUtil.getCurrentSession();
			//	tx = session.beginTransaction();

			otherCandidateDetails = (OtherCandidateDetails) session.get(
					OtherCandidateDetails.class, id);

			//	tx.commit();

			return otherCandidateDetails;
		} catch (RuntimeException ex) {
			//		    try
			//		    {
			//	    	    if(tx != null)
			//	    	    {
			//	    	        tx.rollback();
			//	    	        tx = null;
			//	    	    }
			//		    }
			//		    finally
			//		    {
			if (session != null) {
				session.close();
				session = null;
			}
			//		    }
			throw ex;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO#findAll()
	 */
	public List findAll() {
		Session session = null;
		//  Transaction tx = null;
		try {
			session = HibernateUtil.getCurrentSession();
			//	tx = session.beginTransaction();

			Query q = session.createQuery("from OtherCandidateDetails");
			List result = q.list();

			//	tx.commit();

			return result;
		} catch (RuntimeException ex) {
			//		    try
			//		    {
			//	    	    if(tx != null)
			//	    	    {
			//	    	        tx.rollback();
			//	    	        tx = null;
			//	    	    }
			//		    }
			//		    finally
			//		    {
			if (session != null) {
				session.close();
				session = null;
			}
			//		    }
			throw ex;
		}
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.OtherCandidateDetailsDAO#findByCandidateId(long)
	 */
	public OtherCandidateDetails findByCandidateId(long candidateId) {
		Session session = HibernateUtil.getCurrentSession();
		Criteria query = session.createCriteria(OtherCandidateDetails.class);

		Object candidate = session.load(Candidate.class, new Long(candidateId));
		query.add(Restrictions.eq("candidate", candidate));

		OtherCandidateDetails otherDetails = (OtherCandidateDetails) query
				.uniqueResult();

		session.evict(candidate);
		session.evict(otherDetails);

		return otherDetails;

	}
	
}
