/*
 * Created on Nov 14, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.directalent.reporting.hbn.shared.dao;

import com.manpower.directalent.reporting.dao.AdvertReportService;
import com.manpower.directalent.reporting.dao.CandidateReportService;
import com.manpower.directalent.reporting.dao.OracleReportService;







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
	
	public abstract CandidateReportService getCandidateReportServiceDAO();
	public abstract AdvertReportService getAdverReportServiceDAO();
	public abstract OracleReportService getOracleReportServiceDAO();
//	public abstract VAdvertisementDAO getVAdvertisementDAO();
//	public abstract RptResultsDAO getRptResultsDAO();
//	public abstract ReportDAO getReportDAO();

}
