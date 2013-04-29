/*
 * Created on July 26, 2007
 *
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateSearchResultsDAO;
import com.manpower.portal.mpnet.hbn.beans.VCandidateSearchResults;

/**
 * @author Mantosh Kumar
 *  
 */
public class CandidateSearchResultsHibernateDAO extends GenericHibernateDAO
		implements CandidateSearchResultsDAO {
	/**
	 * Constructor
	 * 
	 * @param persistentClass
	 * @param session
	 */
	public CandidateSearchResultsHibernateDAO(Session session) {
		super(VCandidateSearchResults.class, session);
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findByID(java.io.Serializable)
	 */
	public Object findByID(Serializable id) {

		return null;

	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findAll()
	 */
	public List findAll() {

		return null;
	}

	public VCandidateSearchResults findByCandidateID(Long candidateID) {
		Criteria query = HibernateUtil.getCurrentSession()
				.createCriteria(VCandidateSearchResults.class).add(
						Restrictions.eq("id", candidateID));

		return (VCandidateSearchResults) query.uniqueResult();

	}

	public List findByCandidateIDs(List candidateIDs) {
		return findByCandidateIDs(candidateIDs, 1, true);
	}
	
	public List findByCandidateIDs(List candidateIDs, int orderBy, boolean desc) {
		Criteria query = HibernateUtil.getCurrentSession()
		.createCriteria(VCandidateSearchResults.class).add(
				Restrictions.in("id", candidateIDs));
		if (candidateIDs == null || candidateIDs.size() == 0)
			return new ArrayList(0);
		
		switch (orderBy) {
		case 1:
		{	
			if (desc) {
				query.addOrder(Property.forName("lastLoginDate").desc().ignoreCase());
			} else {
				query.addOrder(Property.forName("lastLoginDate").asc().ignoreCase());
			}
		}
			break;
		case 2:
		{	
			if (desc) {
				query.addOrder(Property.forName("firstName").desc().ignoreCase());
				query.addOrder(Property.forName("middleName").desc().ignoreCase());
				query.addOrder(Property.forName("lastName").desc().ignoreCase());
			} else {
				query.addOrder(Property.forName("firstName").asc().ignoreCase());
				query.addOrder(Property.forName("middleName").asc().ignoreCase());
				query.addOrder(Property.forName("lastName").asc().ignoreCase());
			}
		}
			break;
		case 3:
		{	
			if (desc) {
				query.addOrder(Property.forName("preferredBranch").desc().ignoreCase());
			} else {
				query.addOrder(Property.forName("preferredBranch").asc().ignoreCase());
			}
		}
			break;
		default:
		{	
			if (desc) {
				query.addOrder(Property.forName("lastLoginDate").desc().ignoreCase());
			} else {
				query.addOrder(Property.forName("lastLoginDate").asc().ignoreCase());
			}
		}
			break;
		}
		
		return query.list();
	}

}
