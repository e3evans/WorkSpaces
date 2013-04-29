package com.manpower.directalent.rss.hbn.dao;

import java.io.Serializable;
import java.util.List;


public interface VAdvertisementDAO {

	public List findAll();
	public Object findByID(Serializable id);
	public List findBySiteId(Serializable id);
}
