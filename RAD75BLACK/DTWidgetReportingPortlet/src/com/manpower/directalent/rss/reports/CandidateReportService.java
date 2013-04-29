package com.manpower.directalent.rss.reports;

import java.util.List;

public interface CandidateReportService {

	public String getCandidateReport();
	@SuppressWarnings("unchecked")
	public List getCandidateReportList();
	public Object [] getTopFiveSites();
}
