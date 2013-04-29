/*
 * Created on Jan 23, 2006
 *
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateSaveJobsDAO;
import com.manpower.portal.mpnet.hbn.beans.Advertisement;
import com.manpower.portal.mpnet.hbn.beans.AdvertisementContact;
import com.manpower.portal.mpnet.hbn.beans.CandidateSaveJobs;

/**
 * @author jsingh
 *  
 */
public class CandidateSaveJobsDAOHibernate extends GenericHibernateDAO
		implements CandidateSaveJobsDAO {

	/**
	 * Constructor
	 * 
	 * @param persistentClass
	 * @param session
	 */
	public CandidateSaveJobsDAOHibernate(Session session) {
		super(CandidateSaveJobs.class, session);
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
		Object candidateSavedJob = HibernateUtil.getSessionFactory()
				.getCurrentSession().get(CandidateSaveJobs.class, id);

		return candidateSavedJob;
	}
	
	public List getAllSavedJobsSimple(long candidateId) {
		Session session = HibernateUtil.getCurrentSession();
		
		SQLQuery query = session.createSQLQuery(
				"select s.id, s.datesaved, s.advertisement_id, a.jobtitle " +
				"from candidatesavejobs s " +
				"inner join advertisements a on s.advertisement_id = a.advertisementid " +
				"where s.candidate_id = '" + candidateId + "' order by s.dateSaved desc");
		List result = query.list();
		List savedJobs = new ArrayList(result.size());
		Iterator iter = result.iterator();
		while (iter.hasNext()) {
			Object[] resultSet = (Object[])iter.next();
			
			CandidateSaveJobs savedJob = new CandidateSaveJobs();
			savedJob.setId(Long.parseLong(resultSet[0].toString()));
			savedJob.setDateSaved((Date)resultSet[1]);
			
			Advertisement ad = new Advertisement();
			ad.setId(Long.parseLong(resultSet[2].toString()));
			ad.setJobTitle(resultSet[3].toString());
			ad.setAdvertisementContact(new AdvertisementContact());

			savedJob.setAdvertisement(ad);
			savedJobs.add(savedJob);
		}
		return savedJobs;
	}
}
