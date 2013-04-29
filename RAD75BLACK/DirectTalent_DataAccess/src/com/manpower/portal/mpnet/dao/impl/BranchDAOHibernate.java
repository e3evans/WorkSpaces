/*
 * Created on Jan 20, 2006
 *
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.BranchDAO;
import com.manpower.portal.mpnet.hbn.beans.Branch;
import com.manpower.portal.mpnet.hbn.beans.BranchTable;

/**
 * @author jsingh
 *  
 */
public class BranchDAOHibernate extends GenericHibernateDAO implements
		BranchDAO {

	/**
	 * Constructor
	 * 
	 * @param persistentClass
	 * @param session
	 */
	public BranchDAOHibernate(Session session) {
		super(Branch.class, session);
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findAll()
	 */
	public List findAll() {

		Criteria query = HibernateUtil.getCurrentSession()
				.createCriteria(Branch.class);

		List values = query.list();

		return values;
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findByID(java.io.Serializable)
	 */
	public Object findByID(Serializable id) {

		Object branch = HibernateUtil.getCurrentSession()
				.get(Branch.class, id);

		return branch;
	}

	/**
	 * Find branches by Location name, Speciality and Site ID
	 * 
	 * @return List of BranchDAO objects
	 */
	public List findBranches(String location, String speciality, long siteId) {

		Criteria query = HibernateUtil.getCurrentSession()
				.createCriteria(Branch.class);

		query.add(Restrictions.eq("siteId", new Long(siteId)));

		if (location != null && location.trim().length() > 0) {

			query.add(Restrictions.eq("location", location));

		}

		if (speciality != null && speciality.trim().length() > 0) {

			query.add(Restrictions.eq("speciality", speciality));

		}

		return query.list();
	}

	/**
	 * Find branches by dinamic property
	 * 
	 * @return List of BranchDAO objects
	 */
	public List findUnique(String property) {

		String selectStatement = "SELECT DISTINCT branch." + property
				+ " FROM " + Branch.class.getName() + " as branch";

		List uniqueList = HibernateUtil.getCurrentSession()
				.createQuery(selectStatement).list();

		return uniqueList;
	}

	/**
	 * Perfor a dinamic SQL query based on provided column and value.
	 *  
	 */
	public List findUniqueWhereEquals(String property, String whereColumn,
			String whereValue) {

		String selectStatement = "SELECT DISTINCT branch." + property
				+ " FROM " + Branch.class.getName()
				+ " as branch WHERE branch." + whereColumn + "='" + whereValue
				+ "' order by branch." + property;

		List uniqueList = HibernateUtil.getCurrentSession()
				.createQuery(selectStatement).list();

		return uniqueList;
	}

	/**
	 * Perform custom SQL query
	 */
	public List findByCustomQuery(String query, Properties params) {

		return super.findByCustomQuery(query, params);
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.interfaces.BranchDAO#deleteSingleBranch(com.manpower.portal.mpnet.hbn.beans.Branch,java.lang.Boolean)
     * Author: Lyuben Ivanov
     * Comments: deletes a single branch from the Database  
	 */
	public boolean deleteSingleBranch(BranchTable branchToDelete) {
		if (branchToDelete == null){
            return false;
        }
        HibernateUtil.getCurrentSession().delete(branchToDelete);
        return true;
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.interfaces.BranchDAO#findBranchesBySiteOrdered(long, java.lang.String)
	 */
	public List findBranchesBySiteOrdered(long siteId, int fromIndex, int pageSize, int orderBy, boolean descending) {
		Criteria query = HibernateUtil.getCurrentSession().createCriteria(BranchTable.class);
		query.add(Restrictions.eq("siteId", new Long(siteId)));
		
		switch (orderBy) {
		case 1: 
			if (descending) {
				query.addOrder(Order.desc("city").ignoreCase());
				query.addOrder(Order.desc("branchName").ignoreCase());
			} else {
				query.addOrder(Order.asc("city").ignoreCase());
				query.addOrder(Order.asc("branchName").ignoreCase());
			}
			break;
		default :
			if (descending) {
				query.addOrder(Order.desc("city").ignoreCase());
				query.addOrder(Order.desc("branchName").ignoreCase());
			} else {
				query.addOrder(Order.asc("city").ignoreCase());
				query.addOrder(Order.asc("branchName").ignoreCase());
			}
			break;
		}
		
		query.setFirstResult(fromIndex);
		query.setMaxResults(pageSize);
//		 Session session = HibernateUtil.getCurrentSession();
//         Query query = session.createQuery("from BranchTable where siteId = :siteId order by upper(city), upper(branchName)");
//         query.setParameter("siteId", new Long(siteId));
         return query.list();
	}
	
	public long getBranchesCountBySiteId(long siteId) {
		Criteria query = HibernateUtil.getCurrentSession().createCriteria(BranchTable.class);
		query.add(Restrictions.eq("siteId", new Long(siteId)));
		query.setProjection(Projections.count("id"));
		Integer count = (Integer)query.uniqueResult();
		return count.longValue();
	}

	public Object findBranchByExternalId(String externalId,long siteId, String language) {
		Criteria query = HibernateUtil.getCurrentSession()
		.createCriteria(Branch.class);

		query.add(Restrictions.eq("externalId", externalId));
		query.add(Restrictions.eq("siteId", new Long(siteId)));
		query.add(Restrictions.eq("language", language));
		
		return query.uniqueResult();
	}
	
	public List findBySQLQuery(String query) {
		
		SQLQuery q = HibernateUtil.getCurrentSession().createSQLQuery(query).addEntity(Branch.class);
		return q.list();
	}
	
	public int getCountBySQLQuery(String query, String uniqueColumn) {

		query = "select count("+uniqueColumn+") " + query;

		Query q = HibernateUtil.getCurrentSession()
				.createQuery(query);
		
		Long count = (Long) q.uniqueResult();

		return count.intValue();
	}

}
