package com.manpower.directalent.reporting.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.manpower.directalent.reporting.dao.OracleReportService;
import com.manpower.directalent.reporting.hbn.beans.OracleReportBean;
import com.manpower.directalent.reporting.hbn.beans.OracleReportFilterListBean;
import com.manpower.directalent.reporting.hbn.shared.HibernateUtilities;

public class OracleReportServiceImpl implements OracleReportService{

	@SuppressWarnings("unchecked")
	public List<Object> getAllReports() {
		List<Object> reports = new ArrayList<Object>();
		reports = HibernateUtilities.currentSession().createSQLQuery("select r.id,r.rpt_name,r.mime_type," +
				"r.sitename,r.rpt_descr,r.created_on from dt_reports r").addEntity(OracleReportBean.class).list();
		HibernateUtilities.currentSession().close();
		
		return reports;
	}

	@SuppressWarnings("unchecked")
	public List<Object> getReportFilterList() {
		List<Object>filterList = new ArrayList<Object>();
		filterList = HibernateUtilities.currentSession().createSQLQuery("select rownum id,t.sitename from (select sitename from " +
				"dt_reports group by sitename) t order by t.sitename").addEntity(OracleReportFilterListBean.class).list();
		HibernateUtilities.currentSession().close();
		
		return filterList;
	}

	public List<Object> getReportsBySiteName(String sitename) {
		List<Object> reports = new ArrayList<Object>();
		
		return null;
	}
	
	

}
