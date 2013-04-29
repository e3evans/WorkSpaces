package com.manpower.portal.gemt.ui.services;

import java.io.Serializable;
import java.util.List;

import com.manpower.portal.gemt.ui.beans.GemtSummaryReportFileLiteUIBean;

public interface GemtSummaryReportFileLiteService {
	public GemtSummaryReportFileLiteUIBean findById(Serializable id);
	public List findAll();
	public GemtSummaryReportFileLiteUIBean makePersistent(GemtSummaryReportFileLiteUIBean gsrb, long reportId);
	public void delete(Serializable id);
	public List findAllByReportId(long reportId);
	public List findCommonFilesByReportId(long reportId);
}
