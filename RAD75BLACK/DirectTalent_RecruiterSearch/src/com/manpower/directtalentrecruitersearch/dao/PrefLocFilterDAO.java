package com.manpower.directtalentrecruitersearch.dao;

import java.io.Serializable;
import java.util.List;



public interface PrefLocFilterDAO {
	public List findAll();	
	public Object findByID(Serializable id);
	public List getPreferredLocationList(String [] params,long siteId);
	

}
