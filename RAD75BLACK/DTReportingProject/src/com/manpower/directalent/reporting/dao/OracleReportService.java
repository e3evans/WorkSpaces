package com.manpower.directalent.reporting.dao;

import java.util.List;



public interface OracleReportService {
	
	public List<Object> getAllReports();
	public List<Object> getReportFilterList();
	public List<Object> getReportsBySiteName(String sitename);
}
