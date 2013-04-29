/*
 * Created on Jan 23, 2006
 *
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateResumeDAO;
import com.manpower.portal.mpnet.hbn.beans.Candidate;
import com.manpower.portal.mpnet.hbn.beans.CandidateResume;

/**
 * @author jsingh
 *  
 */
public class CandidateResumeDAOHibernate extends GenericHibernateDAO implements
		CandidateResumeDAO {

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.CandidateResumeDAO#makePersistent(java.lang.Object,
	 *      double, double)
	 */
	public Object makePersistent(Object obj, double longitude, double latitude) {

		return null;
	}

	/**
	 * Constructor
	 * 
	 * @param persistentClass
	 * @param session
	 */
	public CandidateResumeDAOHibernate(Session session) {
		super(CandidateResume.class, session);
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

		Object candidateResume = HibernateUtil.getSessionFactory()
				.getCurrentSession().get(CandidateResume.class, id);
		HibernateUtil.getCurrentSession().evict(
				candidateResume);
		return candidateResume;

	}

	/**
	 * Find Resume by Candidate ID
	 * 
	 * @return Candidate object
	 */
	public Object findByCandidate(Serializable id) {

		Criteria query = HibernateUtil.getCurrentSession()
				.createCriteria(CandidateResume.class);

		Object candidate = HibernateUtil.getSessionFactory()
				.getCurrentSession().load(Candidate.class, id);

		query.add(Restrictions.eq("candidate", candidate));

		Object resume = query.uniqueResult();

		HibernateUtil.getCurrentSession().evict(candidate);

		HibernateUtil.getCurrentSession().evict(resume);

		return resume;

	}

	/**
	 * Perform custom query
	 */
	public List findPageByCustomSQLQuery(String query, Properties params,
			int pageNumber, int pageSize, String entityAlias, Class entityName) {

		return super.findPageByCustomSQLQuery(query, params, pageNumber,
				pageSize, entityAlias, entityName);
	}

	/**
	 * Find Resumes by a list of Candidate IDs
	 */
	public List findByIDs(List IDs) {
		return HibernateUtil.getCurrentSession()
				.createCriteria(CandidateResume.class).add(
						Expression.in("id", IDs)).list();
	}

	/**
	 * Find all Resumes not stored in Lens
	 */
	public List readCandidateResumesWhichDontHaveLensID(long siteId,
			int maxResults) {
		Criteria query = HibernateUtil.getCurrentSession()
				.createCriteria(CandidateResume.class);

		query.add(Expression.isNull("lensID")).add(
				Expression.eq("siteId", new Long(siteId))).createCriteria(
				"candidate").add(Expression.gt("branchId", new Long(0)))
				.setMaxResults(maxResults);

		return query.list();
	}

	public List findCandidateResumes(long candidateId) {
		Criteria query = HibernateUtil.getSessionFactory().getCurrentSession()
		.createCriteria(CandidateResume.class);

		query.add(Restrictions.sqlRestriction("SAVED_RESUME=1"));
		query.add(Restrictions.sqlRestriction("CANDIDATE_ID=" + candidateId));
	
		List results=query.list();	
		 
		return results;
	}
	
	public CandidateResume findMyManpowerResume(long candidateId) {
        String sqlQuery = " select ID,SITE_ID,DATECREATED,RESUME,CANDIDATE_ID,UPDATEDON,UPDATEDBY,NAME,CREATED_ON,CHANGED_ON,LENS_ID,MIME_TYPE,RESUME_NAME,SAVED_RESUME, PRIMARY_RESUME, MYMANPOWER_RESUME from  CANDIDATERESUMES"+
        " where SAVED_RESUME=1 and MYMANPOWER_RESUME = 1 and CANDIDATE_ID=" + candidateId;
		Session session = HibernateUtil.getCurrentSession();
		SQLQuery query = session.createSQLQuery(sqlQuery).addEntity(
				CandidateResume.class);
		CandidateResume resume = (CandidateResume)query.uniqueResult();
		return resume;
	}
	
	public CandidateResume findPrimaryResumeByCandidateId(long candidateId){
		Criteria query = HibernateUtil.getSessionFactory().getCurrentSession()
		.createCriteria(CandidateResume.class);
		
		CandidateResume resume = null;
		
		query.add(Restrictions.sqlRestriction("CANDIDATE_ID=" + candidateId));
		query.add(Restrictions.sqlRestriction("PRIMARY_RESUME=1"));
		
		List<CandidateResume> results=query.list();
		
		if(results!=null && !results.isEmpty()){
				resume = results.get(0);
		} else {
			return null;
		}
			
		return resume;
		
	}
}
