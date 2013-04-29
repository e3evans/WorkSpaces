package com.manpower.portal.gemt.ui.services.impl;

import java.io.Serializable;
import java.util.List;

import com.manpower.portal.gemt.hbn.beans.GemtSummaryReportFileHbnBean;
import com.manpower.portal.gemt.hbn.beans.GemtSummaryReportHbnBean;
import com.manpower.portal.gemt.hbn.object.dao.GemtSummaryReportFileDAO;
import com.manpower.portal.gemt.hbn.shared.dao.DAOFactory;
import com.manpower.portal.gemt.ui.beans.GemtSummaryReportFileUIBean;
import com.manpower.portal.gemt.ui.services.GemtSummaryReportFileService;

public class GemtSummaryReportFileServiceImpl extends BaseUIServiceImpl
		implements GemtSummaryReportFileService {

	public void delete(Serializable id) {
		Object obj = getDAO().findByID(id);
		beginTransaction();
		getDAO().delete(obj);
		commitTransaction();
	}

	public List findAll() {
		return createUIBeanList(getDAO().findAll(),GemtSummaryReportFileUIBean.class.getName());
	}

	public GemtSummaryReportFileUIBean findById(Serializable id) {
		return (GemtSummaryReportFileUIBean) transferObject(getDAO().findByID(id),GemtSummaryReportFileUIBean.class.getName());
	}

	public GemtSummaryReportFileUIBean makePersistent(
			GemtSummaryReportFileUIBean gsrb, long reportId) {
		GemtSummaryReportFileHbnBean gsrhb = new GemtSummaryReportFileHbnBean();
		
		GemtSummaryReportHbnBean report=(GemtSummaryReportHbnBean)DAOFactory.getDAOFactory().getGemtSummaryReportDAO().findByID(new Long(reportId));
		
		gsrhb = (GemtSummaryReportFileHbnBean) transferObject(gsrb,GemtSummaryReportFileHbnBean.class.getName());
		gsrhb.setGemt_sum_report(report);
		
		beginTransaction();
		gsrhb=(GemtSummaryReportFileHbnBean) getDAO().makePersistent(gsrhb);
		commitTransaction();
		
		if (gsrhb!=null)gsrb.setId(gsrhb.getId());
		return gsrb;
	}

	private GemtSummaryReportFileDAO getDAO(){
		return DAOFactory.getDAOFactory().getGemtSummaryReportFileDAO();
	}

	public List findAllByReportId(long reportId) {
		
		return createUIBeanList(getDAO().findByReportId(reportId),GemtSummaryReportFileUIBean.class.getName());
	}	
}
