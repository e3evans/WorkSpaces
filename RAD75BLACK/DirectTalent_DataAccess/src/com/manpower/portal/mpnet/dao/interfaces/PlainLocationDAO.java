package com.manpower.portal.mpnet.dao.interfaces;

import java.util.List;

import com.manpower.portal.mpnet.hbn.beans.PlainLocation;

public interface PlainLocationDAO extends DAO {

	
	public List<PlainLocation> getLocationsByCountry(String countryCode);
	public PlainLocation getLocationById(long id);
	
}
