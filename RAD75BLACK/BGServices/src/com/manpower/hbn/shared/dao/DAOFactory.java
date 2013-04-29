/*
 * Created on Nov 14, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.hbn.shared.dao;

import com.manpower.hbn.dao.AdvertContactDAO;
import com.manpower.hbn.dao.AdvertDAO;
import com.manpower.hbn.dao.BranchesDAO;

//import com.eee.hibernate.objects.client.CountryWebsitesDAO;
//import com.eee.hibernate.objects.project.ProjectDAO;



/**
 * @author 
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class DAOFactory {
	

	
	public static DAOFactory getDAOFactory() {
		return new DAOFactoryHibernate();
	}
	
	public abstract BranchesDAO getBranchesDAO();
	public abstract AdvertContactDAO getAdvertContactDAO();
	public abstract AdvertDAO getAdvertDAO();
	
//	public abstract CandidateKeywordSearchDAO getCandidateKeywordSearchDAO();
//	public abstract PreferredLocationsDAO getPreferredLocationsDAO();
////	public abstract VActiveAdvertsDAO getVActiveAdvertsDAO();
//	public abstract BranchesDAO getBranchesDAO();
//	public abstract PrefLocFilterDAO getPrefLocFilterDAO();
//	public abstract ResumeDAO getResumeDAO();
//	public abstract RecruiterAuditingDAO getRecruiterAuditingDAO();
	

}
