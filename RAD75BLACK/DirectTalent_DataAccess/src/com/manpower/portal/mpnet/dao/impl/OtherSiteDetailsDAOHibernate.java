/*
 * Created on 2008-3-17
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.OtherSiteDetailsDAO;
import com.manpower.portal.mpnet.hbn.beans.OtherCandidateDetails;
import com.manpower.portal.mpnet.hbn.beans.OtherSiteDetails;

/**
 * @author alexander.todorov
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OtherSiteDetailsDAOHibernate extends GenericHibernateDAO
											implements OtherSiteDetailsDAO {


	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.interfaces.OtherSiteDetailsDAO#findBySiteId(long)
	 */
	
	public OtherSiteDetailsDAOHibernate(Session session){
		super(OtherSiteDetails.class, session);
	}
	
	public OtherSiteDetails findBySiteId(long siteId){
		Session session = HibernateUtil.getCurrentSession();
		Criteria query = session.createCriteria(OtherSiteDetails.class);
		query.add(Restrictions.eq("siteId", new Long(siteId)));
		OtherSiteDetails siteDetails = (OtherSiteDetails)query.uniqueResult();
		return siteDetails;
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findByID(java.io.Serializable)
	 */
	public Object findByID(Serializable id) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getCurrentSession();
		return session.load(OtherCandidateDetails.class, id);
		
	}
	
	public List findAll(){
		return null;
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findAll()
	 */
}
