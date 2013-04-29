/*
 * Created on 2006-7-5
 *
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;


import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateOccupationDAO;
import com.manpower.portal.mpnet.hbn.beans.CandidateOccupations;
import com.manpower.portal.mpnet.hbn.beans.Region;

/**
 * @author alexander.todorov
 *  
 */
public class CandidateOccupationDAOHibernate extends GenericHibernateDAO implements
	CandidateOccupationDAO {

	
	/**
	 * Constructor
	 * 
	 * @param persistentClass
	 * @param session
	 */
	public CandidateOccupationDAOHibernate(Session session) {
		super(Region.class, session);
	}
	
	public int saveOccupations(List occupations, long candidateId){
		Long occupationId = null;
		int addedCount = -1;
		if(occupations != null){
			CandidateOccupations candidateOccupation = null;
			Session session = HibernateUtil.getCurrentSession();
			for(int i=0; i< occupations.size(); i++){
				occupationId = (Long)occupations.get(i);
				candidateOccupation = new CandidateOccupations();
				candidateOccupation.setCandidateId(candidateId);
				candidateOccupation.setOccupationId(occupationId.longValue());
				session.save(candidateOccupation);
				addedCount = (i+1);
			}
		}
		
		return addedCount;
	}
	public int deleteCandidateOccupations(long candidateId){
		
		String sqlQuery = "DELETE FROM CANDIDATEOCCUPATIONS WHERE CANDIDATE_ID =" + candidateId;
		Session session = HibernateUtil.getCurrentSession();
		int deleted = session.createSQLQuery(sqlQuery).executeUpdate();
		return deleted;
		
		
	}
	
	public List findCandidateOccupations(long cid){
		Session session = HibernateUtil.getCurrentSession();
		List occupations = session.createCriteria(CandidateOccupations.class).add(Restrictions.eq("candidateId", new Long(cid))).list();
		return occupations;
		
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

		Object candidateOccupation = HibernateUtil.getCurrentSession()
				.get(CandidateOccupations.class, id);

		return candidateOccupation;

	}
}
