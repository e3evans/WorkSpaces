package com.manpower.portal.mpnet.dao.interfaces;

import java.util.List;

import com.manpower.portal.mpnet.hbn.beans.MobileAgent;

public interface MobileAgentDAO  extends DAO {

	public List<MobileAgent> getJobAgents(String deviceID);
	
	public List<MobileAgent> findByName(String agentName, String deviceID);
}
