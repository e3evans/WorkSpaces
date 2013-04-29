/*
 * Created on Jul 17, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateAssessmentsDAO;
import com.manpower.portal.mpnet.hbn.beans.AdvertisementContact;
import com.manpower.portal.mpnet.hbn.beans.CandidateAssessments;

/**
 * @author vindukur
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class CandidateAssessmentsDAOHibernate extends GenericHibernateDAO
		implements CandidateAssessmentsDAO {

	/**
	 *  
	 */
	public CandidateAssessmentsDAOHibernate(Session session) {
		super(CandidateAssessments.class, session);
	}

	/*
	 * @see com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO#findByID(java.io.Serializable)
	 */
	public Object findByID(Serializable id) {
		Object candidateAssessments = HibernateUtil.getSessionFactory()
				.getCurrentSession().get(CandidateAssessments.class, id);
		return candidateAssessments;
	}

	/*
	 * @see com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO#findAll()
	 */
	public List findAll() {
		return HibernateUtil.getCurrentSession()
				.createCriteria(CandidateAssessments.class).list();
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.interfaces.CandidateAssessmentsDAO#findAllAssessmentsByRecruiter(com.manpower.portal.mpnet.hbn.beans.AdvertisementContact)
	 */
	public List findAllAssessmentsByRecruiter(AdvertisementContact adContact) {
		Session session = HibernateUtil.getCurrentSession();
        Query query = session.createQuery("from CandidateAssessments where recruiter = :recId");
        query.setParameter("recId", new Long(adContact.getId()));
		return query.list();
	}
    
}
