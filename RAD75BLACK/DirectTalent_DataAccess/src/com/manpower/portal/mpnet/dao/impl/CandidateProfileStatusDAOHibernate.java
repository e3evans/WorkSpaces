package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateProfileStatusDAO;
import com.manpower.portal.mpnet.hbn.beans.CandidateProfileStatus;

public class CandidateProfileStatusDAOHibernate extends GenericHibernateDAO 
	implements CandidateProfileStatusDAO
{
	
	public CandidateProfileStatusDAOHibernate(Session session) 
	{
		super(CandidateProfileStatus.class, session);
	}
	
	public CandidateProfileStatus getByCandidateId(long candidateId)
	{
		Session session = HibernateUtil.getCurrentSession();
		
		Query query = session.createQuery("from CandidateProfileStatus cps where candidateId = :candidateId order by cps.id desc");
		query.setParameter("candidateId", new Long(candidateId));
		
		List candidateProfiles = query.list();
		
		if(candidateProfiles!=null && !candidateProfiles.isEmpty())
		{
			return (CandidateProfileStatus)candidateProfiles.get(0);
		}
		
		return null;
	}
	
	
	public List getCandidateHistory(long candidateId)
	{
		Session session = HibernateUtil.getCurrentSession();
		
		Query query = session.createQuery("from CandidateProfileStatus where candidateId = :candidateId");
		
		query.setParameter("candidateId", new Long(candidateId));
		
		return query.list();
	}
	
	public long setCandidateProfileStatus(long candidateId, boolean statusActive)
	{
		CandidateProfileStatus candidateProfileStatus = getByCandidateId(candidateId);
		
		if(candidateProfileStatus != null)
		{
			candidateProfileStatus.setStatusActive(statusActive);
		} else {
			candidateProfileStatus = new CandidateProfileStatus();
			candidateProfileStatus.setCandidateId(candidateId);
			candidateProfileStatus.setCreatedOn(new Date());
			candidateProfileStatus.setStatusActive(statusActive);
		}
		
		makePersistent(candidateProfileStatus);
		
		return candidateProfileStatus.getId();
	}


	public List findAll() {
		// TODO Auto-generated method stub
		return null;
	}


	public Object findByID(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}
}
