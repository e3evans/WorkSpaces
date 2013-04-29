package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;

import com.manpower.portal.mpnet.dao.interfaces.DynaCacheDAO;

import com.manpower.portal.mpnet.hbn.beans.DynaCache;

public class DynaCacheDAOHibernate extends GenericHibernateDAO implements
		DynaCacheDAO {

	private static Logger log=Logger.getLogger(DynaCacheDAOHibernate.class);
	/**
	 * Constructor
	 * 
	 * @param persistentClass
	 * @param session
	 */
	public DynaCacheDAOHibernate(Session session) {
		super(DynaCache.class, session);
	}

	public List findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object findByID(Serializable id) {
		Object dynaCache = HibernateUtil.getCurrentSession()
		.get(DynaCache.class, id);

		return dynaCache;
	}

	public DynaCache getDynaCache(String sessionId, String key)
	{
		Session session = HibernateUtil.getCurrentSession();
		Criteria query = session.createCriteria(DynaCache.class);
		
		query.add(Restrictions.eq("sessionId", sessionId)).add(Restrictions.eq("key", key));
		
		return (DynaCache)query.uniqueResult();
	}
	
	public String getValue(String sessionId, String key) {		
		
		DynaCache dynaCache=getDynaCache(sessionId, key);
		
		if(dynaCache!=null)
		{
			return dynaCache.getValue();
		}
		
		return null;
	}

	public void setValue(String sessionId,String key,String value) {
		
		DynaCache dynaCache = getDynaCache(sessionId, key);
		
		if (dynaCache == null) {
			dynaCache = new DynaCache();
			dynaCache.setSessionId(sessionId);
			dynaCache.setKey(key);
		}
		dynaCache.setValue(value);
		
		try {
			HibernateUtil.getCurrentSession().saveOrUpdate(
					dynaCache);
		} catch (RuntimeException e) {
			if (log.isEnabledFor(Level.ERROR)) {
				log.error(e.getMessage());
			}
			throw e;
		}
	}

	public String getValueAndDeleteIt(String sessionId, String key) {
		
		String value=null;
		
		DynaCache dynaCache=getDynaCache(sessionId, key);
		
		if(dynaCache!=null)
		{			
			value=dynaCache.getValue();
			
			delete(dynaCache);
		}	
		
		return value;
	}

	public void clear(String sessionId) {
		StringBuffer queryString = new StringBuffer(
		"delete from DYNACACHE where SESSION_ID=");
		queryString.append("'");
		queryString.append(sessionId);
		queryString.append("'");

		Session session = HibernateUtil.getCurrentSession();

		session.createSQLQuery(queryString.toString()).executeUpdate();
	}
}
