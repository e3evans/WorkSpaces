package com.manpower.directalent.reporting.dao;

import java.util.List;

public interface CandidateReportService {

	public List<Object> getCandidateReport();
	public List<Object> getCandidateReportList();
	public List<Object> getCandidatesByDay(String dayspast);
	public Object [] getTopFiveSites();
}
