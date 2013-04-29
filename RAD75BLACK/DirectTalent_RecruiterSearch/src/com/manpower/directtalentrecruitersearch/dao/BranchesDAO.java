package com.manpower.directtalentrecruitersearch.dao;

import java.io.Serializable;
import java.util.List;



public interface BranchesDAO {
	public List findAll();	
	public Object findByID(Serializable id);
	public List getBranchFilterList(String [] params, long siteId);
	

}
