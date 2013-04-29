package com.manpower.directalent.reporting.dao;

import java.util.List;

public interface AdvertReportService {

	public List<Object> getAdvertReport();
	public List<Object> getAdsLastSevenDays();
	public List<Object> getCountrySelectorWithCount();
	public List<Object>	getAdvertsByBranch(String siteid);
	
}
