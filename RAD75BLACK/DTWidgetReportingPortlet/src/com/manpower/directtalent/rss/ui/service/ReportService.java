package com.manpower.directtalent.rss.ui.service;

import java.util.List;

import com.ibm.workplace.util.exception.ServiceException;
import com.manpower.directtalent.rss.ui.beans.ReportContentUIBean;

public interface ReportService {
	public List findAllReports() throws ServiceException;
	public ReportContentUIBean findReportContentByID(Long id) throws ServiceException;
}
