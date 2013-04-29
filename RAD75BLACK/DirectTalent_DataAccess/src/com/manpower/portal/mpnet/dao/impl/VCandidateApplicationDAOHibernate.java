/*
 * Created on Jan 23, 2006
 *
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.hibernate.Query;
import org.hibernate.Session;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.VCandidateApplicationDAO;
import com.manpower.portal.mpnet.hbn.beans.VCandidateApplication;

/**
 * @author jsingh
 *  
 */
public class VCandidateApplicationDAOHibernate extends GenericHibernateDAO
		implements VCandidateApplicationDAO {

	/**
	 * Constructor
	 * 
	 * @param persistentClass
	 * @param session
	 */
	public VCandidateApplicationDAOHibernate(Session session) {
		super(VCandidateApplication.class, session);
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

		return null;

	}

	/**
	 * Perform a custom SQL query
	 */
	public List findPageByCustomSQLQuery(String query, Properties params,
			int pageNumber, int pageSize, String entityAlias, Class entityName) {

		return super.findPageByCustomSQLQuery(query, params, pageNumber,
				pageSize, entityAlias, entityName);
	}

	/**
	 * Perform a custom SQL query
	 */
	public List findByWhere(String query, Properties params) {
		Query q = HibernateUtil.getCurrentSession()
				.createQuery(query);

		Iterator iter = params.keySet().iterator();
		while (iter.hasNext()) {
			String stKey = (String) iter.next();
			String stVal = (String) params.get(stKey);
			q.setString(stKey, stVal);
		}
		return q.list();
	}
}
