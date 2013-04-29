package com.manpower.directtalentseo.hbn.dao;

import java.io.Serializable;
import java.util.List;

public interface VActiveAdvertsDAO {

	@SuppressWarnings("unchecked")
	public List findAll();
	public Object findByID(Serializable id);
	public Object findAdvertById(Serializable id);
	public Object findAdvertByIdLang(Serializable id,String lang);
	@SuppressWarnings("unchecked")
	public List findAllbySiteId(String siteId);
	@SuppressWarnings("unchecked")
	public List findaAllBySiteNameAndCategory(String sitename,String category,int maxRows,int pageNumber);
	
}
