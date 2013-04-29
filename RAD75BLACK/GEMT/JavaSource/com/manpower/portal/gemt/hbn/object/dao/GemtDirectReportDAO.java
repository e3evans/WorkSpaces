package com.manpower.portal.gemt.hbn.object.dao;

import java.io.Serializable;
import java.util.List;

public interface GemtDirectReportDAO {
	public List findAll();
	public Object findByID(Serializable id); 
	public List findAllByManagerId(String mgrEmail,String orderBy,String ascDsc);
	public Object delete(Object obj);
	public Object findByEmployeeEmail(String empEmail);
	public Object makePersistent(Object obj);
}
