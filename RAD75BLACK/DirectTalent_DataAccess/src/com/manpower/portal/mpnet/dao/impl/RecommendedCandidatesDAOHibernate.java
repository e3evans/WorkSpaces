package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.RecommendedCandidatesDAO;
import com.manpower.portal.mpnet.exception.ServiceException;
import com.manpower.portal.mpnet.hbn.beans.RecommendedCandidatesView;

public class RecommendedCandidatesDAOHibernate extends GenericHibernateDAO implements RecommendedCandidatesDAO {

	public RecommendedCandidatesDAOHibernate(Session session) {
		super(RecommendedCandidatesView.class, session);
	}
	
	public List findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object findByID(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RecommendedCandidatesView> getRecomendedCandidatesAdvertsByRecruiterId(
			long siteId, long recruiterId, int fromIndex, int toIndex,
			int orderBy) throws ServiceException {
		Session session = HibernateUtil.getCurrentSession();
		Criteria query = session.createCriteria(RecommendedCandidatesView.class);
		query.add(Restrictions.eq("recruiterId", recruiterId));
		
		switch (orderBy) {
		case 0:
			query.addOrder(Order.asc("jobTitle"));
			break;
		case 1:
			query.addOrder(Order.desc("jobTitle"));
			break;		
		case 2:
			query.addOrder(Order.asc("referenceNumber"));
			break;
		case 3:
			query.addOrder(Order.desc("referenceNumber"));
			break;
		default:
			query.addOrder(Order.desc("jobTitle"));
			break;
		}
		
		query.setFirstResult(fromIndex);
		query.setMaxResults(toIndex - fromIndex);
		
		List<RecommendedCandidatesView> result = (List<RecommendedCandidatesView>) query.list();
		return result;
	}


}
