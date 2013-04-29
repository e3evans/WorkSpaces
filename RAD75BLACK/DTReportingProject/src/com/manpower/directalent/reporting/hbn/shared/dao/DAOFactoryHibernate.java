/*
 * Created on Nov 14, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.directalent.reporting.hbn.shared.dao;

import org.hibernate.Session;

import com.manpower.directalent.reporting.dao.AdvertReportService;
import com.manpower.directalent.reporting.dao.CandidateReportService;
import com.manpower.directalent.reporting.dao.OracleReportService;
import com.manpower.directalent.reporting.dao.impl.AdvertReportServiceImpl;
import com.manpower.directalent.reporting.dao.impl.CandidateReportServiceImpl;
import com.manpower.directalent.reporting.dao.impl.OracleReportServiceImpl;
import com.manpower.directalent.reporting.hbn.shared.HibernateUtilities;


/**
 *
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DAOFactoryHibernate extends DAOFactory {

	protected Session getCurrentSession() {
        return HibernateUtilities.currentSession();
    }

	@Override
	public CandidateReportService getCandidateReportServiceDAO() {
		// TODO Auto-generated method stub
		return new CandidateReportServiceImpl();
	}

	@Override
	public AdvertReportService getAdverReportServiceDAO() {
		// TODO Auto-generated method stub
		return new AdvertReportServiceImpl();
	}

	@Override
	public OracleReportService getOracleReportServiceDAO() {
		// TODO Auto-generated method stub
		return new OracleReportServiceImpl();
	}
	
	
	
//	public VAdvertisementDAO getVAdvertisementDAO(){
//		return new VAdvertisementDAOImpl(getCurrentSession());
//	}
//	
//	public RptResultsDAO getRptResultsDAO(){
//		return new RptResultsDAOImpl(getCurrentSession());
//	}
//
//	public ReportDAO getReportDAO() {
//		return new ReportDAOImpl(getCurrentSession());
//	}
}
	
	
