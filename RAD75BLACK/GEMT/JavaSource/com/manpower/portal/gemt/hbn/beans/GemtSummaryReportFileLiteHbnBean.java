package com.manpower.portal.gemt.hbn.beans;

public class GemtSummaryReportFileLiteHbnBean extends BaseHibernateBean {
	
	private GemtSummaryReportHbnBean gemt_sum_report;
	private String gemt_sum_file_name;

	public String getGemt_sum_file_name() {
		return gemt_sum_file_name;
	}
	public void setGemt_sum_file_name(String gemt_sum_file_name) {
		this.gemt_sum_file_name = gemt_sum_file_name;
	}
	
	public GemtSummaryReportHbnBean getGemt_sum_report() {
		return gemt_sum_report;
	}
	public void setGemt_sum_report(GemtSummaryReportHbnBean gemt_sum_report) {
		this.gemt_sum_report = gemt_sum_report;
	}		 		
}
