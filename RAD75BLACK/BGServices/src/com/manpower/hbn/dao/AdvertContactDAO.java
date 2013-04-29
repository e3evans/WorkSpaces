package com.manpower.hbn.dao;

import java.io.Serializable;
import java.util.List;


public interface AdvertContactDAO {

	public List <Object> findAll();
	public Object findByID(Serializable id);
	public List <Object> findAllByBranchId(Serializable id);
	
	
}
