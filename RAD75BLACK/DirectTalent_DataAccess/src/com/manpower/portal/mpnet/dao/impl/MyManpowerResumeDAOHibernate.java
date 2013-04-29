/*
 * Created on Jan 23, 2006
 *
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.MyManpowerResumeDAO;
import com.manpower.portal.mpnet.hbn.beans.Candidate;
import com.manpower.portal.mpnet.hbn.beans.MyManpowerResume;

/**
 * @author jsingh
 *  
 */
public class MyManpowerResumeDAOHibernate extends GenericHibernateDAO implements
	MyManpowerResumeDAO {

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
	public MyManpowerResumeDAOHibernate(Session session) {
		super(MyManpowerResume.class, session);
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
				.getCurrentSession().get(MyManpowerResume.class, id);
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
				.createCriteria(MyManpowerResume.class);

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
				.createCriteria(MyManpowerResume.class).add(
						Expression.in("id", IDs)).list();
	}

	/**
	 * Find all Resumes not stored in Lens
	 */
	public List readCandidateResumesWhichDontHaveLensID(long siteId,
			int maxResults) {
		Criteria query = HibernateUtil.getCurrentSession()
				.createCriteria(MyManpowerResume.class);

		query.add(Expression.isNull("lensID")).add(
				Expression.eq("siteId", new Long(siteId))).createCriteria(
				"candidate").add(Expression.gt("branchId", new Long(0)))
				.setMaxResults(maxResults);

		return query.list();
	}

	public List findMyManpowerResumesByCandidateId(long candidateId) {
		
		Object candidate = HibernateUtil.getCurrentSession().
			get(Candidate.class, new Long(candidateId));
			
		Criteria query = HibernateUtil.getCurrentSession().
							createCriteria(MyManpowerResume.class);
		
		query.add(Restrictions.eq("candidate", candidate));
				
		return query.list();
	}
	
}
