package com.manpower.portal.gemt.ui.services;

import java.io.Serializable;
import java.util.List;

import com.manpower.portal.gemt.ui.beans.GemtSummaryReportFileUIBean;

public interface GemtSummaryReportFileService {
	public GemtSummaryReportFileUIBean findById(Serializable id);
	public List findAll();
	public GemtSummaryReportFileUIBean makePersistent(GemtSummaryReportFileUIBean gsrb, long reportId);
	public void delete(Serializable id);
	public List findAllByReportId(long reportId);
}
