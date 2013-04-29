package com.manpower.portal.gemt.ui.services.impl;

import java.io.Serializable;
import java.util.List;

import com.manpower.portal.gemt.hbn.object.dao.GemtSummaryReportFileLiteDAO;
import com.manpower.portal.gemt.hbn.shared.dao.DAOFactory;
import com.manpower.portal.gemt.ui.beans.GemtSummaryReportFileLiteUIBean;
import com.manpower.portal.gemt.ui.beans.GemtSummaryReportUIBean;
import com.manpower.portal.gemt.ui.services.GemtSummaryReportFileLiteService;

public class GemtSummaryReportFileLiteServiceImpl extends BaseUIServiceImpl
		implements GemtSummaryReportFileLiteService {

	public void delete(Serializable id) {
		Object obj = getDAO().findByID(id);
		beginTransaction();
		getDAO().delete(obj);
		commitTransaction();
	}

	public List findAll() {
		return createUIBeanList(getDAO().findAll(),GemtSummaryReportFileLiteUIBean.class.getName());
	}

	public GemtSummaryReportFileLiteUIBean findById(Serializable id) {
		return (GemtSummaryReportFileLiteUIBean) transferObject(getDAO().findByID(id),GemtSummaryReportFileLiteUIBean.class.getName());
	}

	public GemtSummaryReportFileLiteUIBean makePersistent(GemtSummaryReportFileLiteUIBean gsrb, long reportId) {
		
		return null;
	}

	private GemtSummaryReportFileLiteDAO getDAO(){
		return DAOFactory.getDAOFactory().getGemtSummaryReportFileLiteDAO();
	}

	public List findAllByReportId(long reportId) {
		
		return createUIBeanList(getDAO().findByReportId(reportId),GemtSummaryReportFileLiteUIBean.class.getName());
	}	
	
	public List findCommonFilesByReportId(long reportId) {
		
		GemtSummaryReportUIBean report=(GemtSummaryReportUIBean) transferObject(DAOFactory.getDAOFactory().getGemtSummaryReportDAO().findByID(new Long(reportId)),GemtSummaryReportUIBean.class.getName());
		
		if(report.getGemt_parent_id()==0)
		{
			return findAllByReportId(reportId);
		}
		
		return createUIBeanList(getDAO().findByBothReportIds(reportId,report.getGemt_parent_id()),GemtSummaryReportFileLiteUIBean.class.getName());
	}		
}
