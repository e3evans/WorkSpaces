package com.manpower.directtalent.rss.ui.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.apache.commons.beanutils.BeanUtils;


import com.manpower.directalent.rss.hbn.beans.Report;
import com.manpower.directalent.rss.hbn.beans.ReportContent;
import com.manpower.directalent.rss.hbn.dao.ReportDAO;
import com.manpower.directalent.rss.hbn.shared.dao.DAOFactoryHibernate;
import com.manpower.directtalent.rss.ui.beans.ReportContentUIBean;
import com.manpower.directtalent.rss.ui.beans.ReportUIBean;
import com.manpower.directtalent.rss.ui.service.ReportService;

public class ReportServiceImpl implements ReportService {

	ReportDAO reportDAO;

	public ReportServiceImpl() {
		reportDAO = DAOFactoryHibernate.getDAOFactory().getReportDAO();
	}

	public List findAllReports() throws ServiceException {
		List reportBeans = null;
		try {
			List reports = reportDAO.findAllReports();
			reportBeans = new ArrayList(reports.size());
			System.out.println("in report service impl, reports found: " + reports.size());
			Iterator iter = reports.iterator();
			while (iter.hasNext()) {
				ReportUIBean reportBean = getReportUIBean((Report) iter.next());
				reportBeans.add(reportBean);
			}
		} catch (Exception e) {
			ServiceException se = new ServiceException(e.getMessage(), e);
			throw se;
		}
		return reportBeans;
	}

	public ReportContentUIBean findReportContentByID(Long id)
			throws ServiceException {
		ReportContentUIBean reportBean = null;
		try {
			ReportContent reportContent = reportDAO.findReportContentByID(id);
			reportBean = getReportContentUIBean(reportContent);
		} catch (IllegalAccessException e) {
			ServiceException se = new ServiceException(e.getMessage(), e);
			e.printStackTrace();
			throw se;
		} catch (InvocationTargetException e) {
			ServiceException se = new ServiceException(e.getMessage(), e);
			e.printStackTrace();
			throw se;
		}
		return reportBean;
	}

	private static Report getReport(ReportUIBean reportBean)
			throws IllegalAccessException, InvocationTargetException {
		Report report = new Report();
		BeanUtils.copyProperties(report, reportBean);
		return report;
	}

	private static ReportUIBean getReportUIBean(Report report)
			throws IllegalAccessException, InvocationTargetException {
		ReportUIBean reportBean = new ReportUIBean();
		BeanUtils.copyProperties(reportBean, report);
		return reportBean;
	}

	private static ReportContentUIBean getReportContentUIBean(
			ReportContent reportContent) throws IllegalAccessException,
			InvocationTargetException {
		ReportContentUIBean uiBean = new ReportContentUIBean();
		BeanUtils.copyProperties(uiBean, reportContent);
		return uiBean;
	}

	private static ReportContent getReportContent(
			ReportContentUIBean reportContentUIBean)
			throws IllegalAccessException, InvocationTargetException {
		ReportContent reportContent = new ReportContent();
		BeanUtils.copyProperties(reportContent, reportContentUIBean);
		return reportContent;
	}

}
