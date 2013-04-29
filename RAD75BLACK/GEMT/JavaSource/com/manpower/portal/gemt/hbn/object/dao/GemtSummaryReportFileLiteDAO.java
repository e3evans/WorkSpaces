package com.manpower.portal.gemt.hbn.object.dao;

import java.io.Serializable;
import java.util.List;

public interface GemtSummaryReportFileLiteDAO {

	public List findAll();
	public Object findByID(Serializable id); 
	public List findByReportId(long reportId);
	public Object delete(Object obj);
	public List findByBothReportIds(long reportId,long parentId);
}
