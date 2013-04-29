/**
 * 
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateResumeSummaryDAO;
import com.manpower.portal.mpnet.hbn.beans.Candidate;
import com.manpower.portal.mpnet.hbn.beans.CandidateResumeSummary;

/**
 * @author Lyuben
 *
 */
public class CandidateResumeSummaryDAOHibernate extends GenericHibernateDAO
		implements CandidateResumeSummaryDAO {

	public CandidateResumeSummaryDAOHibernate(Session session) {
		super(CandidateResumeSummary.class, session);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findAll(java.io.Serializable)
	 */
	public List findAll() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO#findByID(java.io.Serializable)
	 */
	public Object findByID(Serializable id) {
		Object candidateResume = HibernateUtil.getSessionFactory()
				.getCurrentSession().get(CandidateResumeSummary.class, id);
		HibernateUtil.getSessionFactory().getCurrentSession().evict(
				candidateResume);
		return candidateResume;
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.interfaces.CandidateResumeSummaryDAO#findByCandidate(java.io.Serializable)
	 */
	public Object findByCandidate(Serializable id) {
		CandidateResumeSummary resume = null;
		
		Criteria query = HibernateUtil.getSessionFactory().getCurrentSession()
		.createCriteria(CandidateResumeSummary.class);

		Object candidate = HibernateUtil.getSessionFactory()
				.getCurrentSession().load(Candidate.class, id);
		
		query.add(Restrictions.eq("candidate", candidate));
		
		List resumes = query.list();
		if (resumes != null && !resumes.isEmpty()){
			resume = (CandidateResumeSummary)resumes.get(0);
		}
		
		HibernateUtil.getSessionFactory().getCurrentSession().evict(candidate);
		
		HibernateUtil.getSessionFactory().getCurrentSession().evict(resume);
		
		return resume;
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.interfaces.CandidateResumeSummaryDAO#findByIDs(java.util.List)
	 */
	public List findByIDs(List IDs) {
		return HibernateUtil.getSessionFactory().getCurrentSession()
		.createCriteria(CandidateResumeSummary.class).add(
				Expression.in("id", IDs)).list();
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.interfaces.CandidateResumeSummaryDAO#findCandidateResumes(long)
	 */
	public List findCandidateResumes(long candidateId) {
		
		Criteria query = HibernateUtil.getSessionFactory().getCurrentSession()
		.createCriteria(CandidateResumeSummary.class);

		
		query.add(Restrictions.sqlRestriction("SAVED_RESUME=1"));
		query.add(Restrictions.sqlRestriction("CANDIDATE_ID=" + candidateId));
		
		
		List resumes = query.list();
		
		return resumes;
		
	}

}
