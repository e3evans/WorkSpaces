package com.manpower.directtalentsearch30.hbn.dao;

import java.io.Serializable;
import java.util.List;
@SuppressWarnings("unchecked")
public interface DirectTalentAdSearchDAO {


	public List findAll();
	public Object findByID(Serializable id);
	public List finaAllBySiteName(String sitename);
	public List findAllSites();
	
}
