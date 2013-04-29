package com.manpower.directtalentrecruitersearch.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.manpower.directtalentrecruitersearch.dao.PreferredLocationsDAO;
import com.manpower.directtalentrecruitersearch.hbn.shared.HibernateUtilities;
import com.manpower.directtalentrecruitersearch.hbn.shared.dao.GenericHibernateDAO;
import com.manpower.hbn.beans.PreferredLocations;

public class PreferredLocationsDAOImpl extends GenericHibernateDAO implements PreferredLocationsDAO {

	public PreferredLocationsDAOImpl(Session session) {
		super(PreferredLocations.class, session);
		// TODO Auto-generated constructor stub
	}

	public List findAll() {
		// TODO Auto-generated method stub
		return getSession().createCriteria(PreferredLocations.class).list();
	}

	public Object findByID(Serializable id) {
		// TODO Auto-generated method stub
		return getSession().get(PreferredLocations.class, id);
	}
	
	public List findLocationsBySiteId(long siteId){
		SQLQuery query = HibernateUtilities.currentSession().createSQLQuery("select location_id,prefered_location " +
				"from bjh_resumes where site_id="+Long.toString(siteId)+
				" group by location_id,prefered_location " +
				"order by prefered_location asc")
				.addEntity(PreferredLocations.class);
		
		
		return query.list();
	}

}
