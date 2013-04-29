/*
 * Created on Jan 24, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.gemt.ui.services;

import java.io.Serializable;
import java.util.List;

import com.manpower.portal.gemt.ui.beans.GemtSummaryReportUIBean;

/**
 * @author Eric Evans
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface GemtSummaryReportService {

	public GemtSummaryReportUIBean findById(Serializable id);
	public List getAllbyUserEmail(String email, String orderBy, String ascDsc);
	public List getAllbyPeriod(String email,Serializable period, String mode);
	public List getAllBeforeAfterMonth(String email,String month,String greaterThan,String orderBy,String ascDsc);
	public List getAllByIdArray(Object[]ids,String orderBy,String ascDsc);
	public List findAll();
	public List findAllOrderBy(String orderBy, String ascDsc);
	public GemtSummaryReportUIBean makePersistent(GemtSummaryReportUIBean gsrb);
	public void delete(Serializable id);
	public void update(GemtSummaryReportUIBean gsrb);
	public GemtSummaryReportUIBean findParentReport(Serializable id);
	public List findAllFinalized(String email, String mode);
	public List getAllActiveReportsByPeriod(String email,Serializable period, String mode);
}
