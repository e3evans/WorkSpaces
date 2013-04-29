/*
 * Created on Feb 8, 2006
 *
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.AdvertisementContactDAO;
import com.manpower.portal.mpnet.hbn.beans.AdvertisementContact;
import com.manpower.portal.mpnet.hbn.beans.BranchTable;

/**
 * @author Eric Evans
 *  
 */
public class AdvertisementContactDAOHibernate extends GenericHibernateDAO
		implements AdvertisementContactDAO {

	/**
	 * Constructor
	 * 
	 * @param persistentClass
	 * @param session
	 */
	public AdvertisementContactDAOHibernate(Session session) {
		super(AdvertisementContact.class, session);
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findByID(java.io.Serializable)
	 */
	public Object findByID(Serializable id) {
		Session session = HibernateUtil.getCurrentSession();
		Object advertisementContact = session.get(AdvertisementContact.class,
				id);
		return advertisementContact;
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findAll()
	 */
	public List findAll() {
		return null;
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.AdvertisementContactDAO#findRecruiterByUserId(java.lang.String)
	 */
	public List findRecruiterByUserId(String siteId, String recruiterUserId, String lang) {
		Session session = HibernateUtil.getCurrentSession();
		Criteria query = session.createCriteria(AdvertisementContact.class);
		query.add(Restrictions.eq("siteId", new Long(siteId)));
		query.createCriteria("branch").add(Restrictions.eq("language", lang));
		query.add(Restrictions.sqlRestriction("UPPER(USER_ID) = UPPER(?)",	recruiterUserId, Hibernate.STRING));

		List recruiters = query.list();

		if (recruiters.isEmpty()) {
			return null;
		}

		return recruiters;
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.interfaces.AdvertisementContactDAO#findRecruitersByBranchId(com.manpower.portal.mpnet.hbn.beans.BranchTable)
	 */
	public List findRecruitersByBranchId(BranchTable fromBranch) {
		Session session = HibernateUtil.getCurrentSession();
        Query query = session.createQuery("from AdvertisementContact where branchId = :branchId");
        query.setParameter("branchId", new Long(fromBranch.getId()));
        return query.list();
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.interfaces.AdvertisementContactDAO#findAdvertContactByBranchAndUserId()
	 */
	public AdvertisementContact findAdvertContactByBranchAndUserId(BranchTable branch, String userId) {
		Session session = HibernateUtil.getCurrentSession();
        Query query = session.createQuery("from AdvertisementContact where branchId = :branchId and upper(userId) = upper(:userId)");
        query.setParameter("branchId",new Long(branch.getId()));
        query.setParameter("userId", userId);
		return (AdvertisementContact)query.uniqueResult();
	}

	public List findRecruiterByUserIdAndBranchId(long siteId, String recruiterUserId, String branchExternalId, String language) {
		Session session = HibernateUtil.getCurrentSession();
		Criteria query = session.createCriteria(AdvertisementContact.class);
		query.add(Restrictions.eq("siteId", new Long(siteId)));
		query.createCriteria("branch").add(Restrictions.eq("externalId", branchExternalId)).add(Restrictions.eq("language", language));
		query.add(Restrictions.sqlRestriction("UPPER(USER_ID) = UPPER(?)",	recruiterUserId, Hibernate.STRING));

		List recruiters = query.list();

		if (recruiters.isEmpty()) {
			return null;
		}

		return recruiters;
	}
	
	public List findAdvertisementContactsOrdered(String siteId, String branchId, int fromIndex, int pageSize, int orderBy, boolean descending)
	{
		Criteria query = HibernateUtil.getCurrentSession().createCriteria(AdvertisementContact.class);
		query.add(Restrictions.eq("siteId", new Long(siteId)));
		query.add(Restrictions.eq("branchId", new Long(branchId)));
		
		switch(orderBy) {
		case 1: 
			if(descending)
			{
				query.addOrder(Order.desc("contactName").ignoreCase());
			} 
			else
			{
				query.addOrder(Order.asc("contactName").ignoreCase());
			}
		break;
		default:
			if(descending)
			{
				query.addOrder(Order.desc("contactName").ignoreCase());
			} 
			else
			{
				query.addOrder(Order.asc("contactName").ignoreCase());
			}
		break;
		}
		query.setFirstResult(fromIndex);
		query.setMaxResults(pageSize);
		
		return query.list();
	}
}
