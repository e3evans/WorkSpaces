/*
 * Created on 2007-11-23
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.type.Type;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.CountryRegionDAO;
import com.manpower.portal.mpnet.hbn.beans.CountryRegion;


/**
 * @author alexander.todorov
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CountryRegionDAOHibernate extends GenericHibernateDAO implements CountryRegionDAO {
	

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findByID(java.io.Serializable)
	 */
	
	public CountryRegionDAOHibernate(Session session) {
		super(CountryRegion.class, session);
	}
	public Object findByID(Serializable id) {
		// TODO Auto-generated method stub
		Object region = HibernateUtil.getCurrentSession().get(CountryRegion.class, id);
		return region;
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findAll()
	 */
	public List findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findByExample(java.lang.Object)
	 */
	public List findByExample(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#save(java.lang.Object)
	 */
	public Object save(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#makePersistent(java.lang.Object)
	 */
	public Object makePersistent(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findByCustomQuery(java.lang.String, java.util.Properties)
	 */
	public List findByCustomQuery(String query, Properties params) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findByCustomSQLQuery(java.lang.String, java.util.Properties, java.lang.String, org.hibernate.type.Type)
	 */
	public List findByCustomSQLQuery(String query, Properties params,
			String name, Type type) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#runSQLQuery(java.lang.String, java.util.Properties, java.lang.String)
	 */
	public Object runSQLQuery(String query, Properties params, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#delete(java.lang.Object)
	 */
	public Object delete(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#makeUpdate(java.lang.Object)
	 */
	public Object makeUpdate(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#updateAll(java.util.List)
	 */
	public void updateAll(List records) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#deleteAll(java.util.List)
	 */
	public void deleteAll(List records) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#saveAll(java.util.List)
	 */
	public void saveAll(List records) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findPageByCustomHQLQuery(java.lang.String, java.util.Properties, int, int)
	 */
	public List findPageByCustomHQLQuery(String query, Properties params,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findPageByCustomSQLQuery(java.lang.String, java.util.Properties, int, int, java.lang.String, java.lang.Class)
	 */
	public List findPageByCustomSQLQuery(String query, Properties params,
			int pageNumber, int pageSize, String entityAlias, Class entityName) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findPageByCustomHQLQuery(java.lang.String, java.util.Properties, int, int, boolean)
	 */
	public List findPageByCustomHQLQuery(String query, Properties params,
			int pageNumber, int pageSize, boolean calculateTotalCount) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findResultsByCustomHQLQuery(java.lang.String, java.util.Properties, int, int)
	 */
	public List findResultsByCustomHQLQuery(String query, Properties params,
			int startIndex, int endIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
