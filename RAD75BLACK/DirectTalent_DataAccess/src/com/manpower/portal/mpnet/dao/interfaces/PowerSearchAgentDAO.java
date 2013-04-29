package com.manpower.portal.mpnet.dao.interfaces;

import java.util.List;

import com.manpower.portal.mpnet.hbn.beans.PowerSearchAgent;

public interface PowerSearchAgentDAO extends DAO {

	public List<PowerSearchAgent> getPowerAgents(long siteId, long recruierId, String language);
	public PowerSearchAgent getAgentByName(long siteId, long recruiterId, String language, String agentName);
	public List<Long[]> getCandidatesByAgent(PowerSearchAgent agent);
	
}
