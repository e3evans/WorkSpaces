package com.manpower.directalent.rss.hbn.dao;

import java.io.Serializable;
import java.util.List;

import com.manpower.directalent.rss.hbn.beans.Report;
import com.manpower.directalent.rss.hbn.beans.ReportContent;


public interface ReportDAO {
	public List findAllReports();
	public ReportContent findReportContentByID(Serializable id);
}
