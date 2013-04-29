package com.manpower.directtalentrecruitersearch.dao;

import java.io.Serializable;
import java.util.List;



public interface PreferredLocationsDAO {

	public List findAll();
	public Object findByID(Serializable id);
	public List findLocationsBySiteId(long siteId);
}
