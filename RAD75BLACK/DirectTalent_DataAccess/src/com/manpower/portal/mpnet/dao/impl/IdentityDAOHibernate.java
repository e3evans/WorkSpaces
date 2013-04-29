/*
 * Created on Sep 24, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.IdentityDAO;
import com.manpower.portal.mpnet.hbn.beans.Identity;

/**
 * @author atodorov
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class IdentityDAOHibernate extends GenericHibernateDAO implements
		IdentityDAO {

	public IdentityDAOHibernate(Session session) {
		super(Identity.class, session);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findByID(java.io.Serializable)
	 */
	public Object findByID(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findAll()
	 */
	public List findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.interfaces.IdentityDAO#nextIdentity(java.lang.String)
	 */
	public Identity nextIdentity(String key) {
		if (key == null) {
			throw new IllegalArgumentException("NULL key is not allowed.");
		}

		Identity identity = getIdentity(key);

		return identity;
	}

	private Identity getIdentity(String key) {
		if (key == null) {
			throw new IllegalArgumentException("NULL key is not allowed.");
		}
		
		Session session = HibernateUtil.getCurrentSession();
			Query q = session
					.createQuery("from Identity as t where t.key = :key");
			q.setParameter("key", key);
			q.setLockMode("t",LockMode.UPGRADE);
			Identity identity = (Identity) q.uniqueResult();

			if (identity == null) {
				identity = new Identity();
				identity.setKey(key);				
				session.save(identity);
			}
			
			int incrementValue = identity.getIncrementValue();
			if (incrementValue == 0) {
				incrementValue = 1;
				identity.setIncrementValue(incrementValue);
			}

			int cacheSize = identity.getCacheSize();
			if (cacheSize == 0) {
				cacheSize = 20;
				identity.setCacheSize(cacheSize);
			}

			long nextValue = identity.getNextValue();
			if (nextValue == 0)
				nextValue = 1;

			nextValue += incrementValue;

			identity.setNextValue(nextValue);

			session.update(identity);
			session.flush();
			
			return identity;
	}

//	private void update(Identity identity, IdentityBean identityBean) {
//
//			identityBean.setId(identity.getId());
//			identityBean.setKey(identity.getKey());
//
//			int incrementValue = identity.getIncrementValue();
//			if (incrementValue == 0) {
//				incrementValue = 1;
//				identity.setIncrementValue(incrementValue);
//			}
//			identityBean.setIncrementValue(incrementValue);
//
//			int cacheSize = identity.getCacheSize();
//			if (cacheSize == 0) {
//				cacheSize = 20;
//				identity.setCacheSize(cacheSize);
//			}
//			identityBean.setCacheSize(cacheSize);
//
//			long nextValue = identity.getNextValue();
//			if (nextValue == 0)
//				nextValue = 1;
//			identityBean.setNextValue(nextValue);
//
//			nextValue += incrementValue;
//
//			identity.setNextValue(nextValue);
//
//			identityBean.setDescription(identity.getDescription());
//	}
}
