package com.manpower.portal.gemt.ui.services;

import java.io.Serializable;
import java.util.List;

import com.manpower.portal.gemt.ui.beans.GemtDirectReportUIBean;

public interface GemtDirectReportService {
	public List findAll();
	public List findAllByManagerId(String mgrEmail,String orderBy,String ascDsc);
	public void delete(Serializable id);
	public GemtDirectReportUIBean findByEmployeeEmail(String empEmail);
	public GemtDirectReportUIBean addUpdateDirectReportFromLdap(GemtDirectReportUIBean gdrui);
}
