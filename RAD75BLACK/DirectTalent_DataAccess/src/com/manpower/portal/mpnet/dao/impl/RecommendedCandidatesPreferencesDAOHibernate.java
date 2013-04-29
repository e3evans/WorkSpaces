package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.RecommendedCandidatesPreferencesDAO;
import com.manpower.portal.mpnet.exception.ServiceException;
import com.manpower.portal.mpnet.hbn.beans.RecommendedCandidate;
import com.manpower.portal.mpnet.hbn.beans.RecommendedCandidatesPreferences;

public class RecommendedCandidatesPreferencesDAOHibernate extends GenericHibernateDAO implements RecommendedCandidatesPreferencesDAO{

	public RecommendedCandidatesPreferencesDAOHibernate(Session session) {
		super(RecommendedCandidate.class, session);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object findByID(Serializable id) {
		Object recommendedCandidate = HibernateUtil.getSessionFactory()
		.getCurrentSession().get(RecommendedCandidate.class, id);
		return recommendedCandidate;
	}

	public RecommendedCandidatesPreferences findRecommendedCandidatesPreferencesByAdvert(
			long advertId, long recruiterId) throws ServiceException {
		Session session = HibernateUtil.getCurrentSession();
		Criteria query = session.createCriteria(RecommendedCandidatesPreferences.class)
				.add(Restrictions.eq("advertId", advertId));
		query.add(Restrictions.eq("recruiterId", recruiterId));
		
		return (RecommendedCandidatesPreferences) query.uniqueResult();
	}

	public List<RecommendedCandidatesPreferences> findRecommendedCanidatesPreferencesByRecruiterId(
			long recruiterId) throws ServiceException {
		Session session = HibernateUtil.getCurrentSession();
		Criteria query = session.createCriteria(RecommendedCandidatesPreferences.class);
		query.add(Restrictions.eq("recruiterId", recruiterId));
		
		List<RecommendedCandidatesPreferences> result = (List<RecommendedCandidatesPreferences>) query.list();
			
//		System.out.println("SEARCHING FOR RECOMMENDED. RESULT : " + result);
		
//		if(result!=null){
//			System.out.println("SEARCHING FOR RECOMMENDED. RESULT SIZE: " + result.size());
//		}
		return result;
	}

}
