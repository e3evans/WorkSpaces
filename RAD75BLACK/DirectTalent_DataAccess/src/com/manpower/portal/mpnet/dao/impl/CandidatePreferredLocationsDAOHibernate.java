package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;


import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidatePreferredLocationsDAO;
import com.manpower.portal.mpnet.hbn.beans.CandidatePreferredLocations;


public class CandidatePreferredLocationsDAOHibernate extends
		GenericHibernateDAO implements CandidatePreferredLocationsDAO {

	public CandidatePreferredLocationsDAOHibernate(Session session) {
		super(CandidatePreferredLocations.class, session);
	}

	public List getPreferredLocations(long candidateID) {
		Session session = HibernateUtil.getCurrentSession();
		List preferredLocations = session.createCriteria(CandidatePreferredLocations.class).add(Restrictions.eq("candidateId", new Long(candidateID))).list();
		return preferredLocations;
	}

	public void savePreferredLocations(List locations, long candidateID,
			String locationFlag) {
		
			if(locations != null && !locations.isEmpty()){
			
			Session session = HibernateUtil.getCurrentSession();
			String locationID = null;
			for(int i=0;i<locations.size();i++){
				CandidatePreferredLocations location= new CandidatePreferredLocations();
				locationID = (String)locations.get(i);
				location.setLocationId(Long.parseLong(locationID));
				location.setCandidateId(candidateID);
				location.setLocationFlag(locationFlag);
				session.save(location);
			}
		}

	}

	public void updatePreferredLocations(long candidateID, List newLocations,
			String locationFlag) {
		
		Iterator oldLocations = getPreferredLocations(candidateID).iterator();
		CandidatePreferredLocations location;
		Session session = HibernateUtil.getCurrentSession();
		while(oldLocations.hasNext()){
			location = (CandidatePreferredLocations)oldLocations.next();
			session.delete(location);
		}
		if(newLocations != null && !newLocations.isEmpty()){
			savePreferredLocations(newLocations, candidateID, locationFlag);
		}

	}
	
	

	public List findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object findByID(Serializable id) {
		Object candidatePreferredLocations = HibernateUtil.getCurrentSession()
		.get(CandidatePreferredLocations.class, id);

		return candidatePreferredLocations;
	}

	

}
