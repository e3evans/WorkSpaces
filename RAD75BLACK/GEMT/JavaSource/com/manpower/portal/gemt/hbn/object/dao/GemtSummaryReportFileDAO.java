package com.manpower.portal.gemt.hbn.object.dao;

import java.io.Serializable;
import java.util.List;

public interface GemtSummaryReportFileDAO {

	public List findAll();
	public Object findByID(Serializable id); 
	public Object makePersistent(Object obj);
	public List findByReportId(long reportId);
	public Object delete(Object obj);
}
