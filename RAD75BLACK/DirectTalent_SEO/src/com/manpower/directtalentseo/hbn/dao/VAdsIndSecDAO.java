package com.manpower.directtalentseo.hbn.dao;

import java.io.Serializable;
import java.util.List;


public interface VAdsIndSecDAO {

	public List findAll();
	public Object findByID(Serializable id);
	public List finaAllByCoutryCode(String sitename);
	public List findUniqueCategoryList(String sitename,String lang);
	
}
