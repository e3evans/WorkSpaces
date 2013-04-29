package com.manpower.directtalent.rss.ui.service;

import com.manpower.directtalent.rss.ui.service.impl.ReportServiceImpl;
import com.manpower.directtalent.rss.ui.service.impl.RptResultsServiceImpl;

public class OracleReportServiceLocator {
	
	private static OracleReportServiceLocator oracleReportService;
	private RptResultsService rptResultsService;
	private ReportService reportService;
	
	public static OracleReportServiceLocator getInstance(){
		
		if (oracleReportService==null){
			oracleReportService = new OracleReportServiceLocator();
		}
		return oracleReportService;
	}
	
	private OracleReportServiceLocator(){
//		reportResultsDao = (RptResultsDAO)new RptResultsDAOImpl();
		rptResultsService = new RptResultsServiceImpl();
		reportService = new ReportServiceImpl();
	}

	public RptResultsService getRptResultsService() {
		return rptResultsService;
	}
	
	public ReportService getReportService() {
		return reportService;
	}

}
