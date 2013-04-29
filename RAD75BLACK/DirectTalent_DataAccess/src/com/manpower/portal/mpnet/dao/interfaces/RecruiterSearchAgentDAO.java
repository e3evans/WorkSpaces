package com.manpower.portal.mpnet.dao.interfaces;

import java.util.List;

import com.manpower.portal.mpnet.hbn.beans.RecruiterSearchAgent;

public interface RecruiterSearchAgentDAO extends DAO {
	public List<RecruiterSearchAgent> getRecruiterSearchAgentsByPortletRecruiterId(String portletName, long recruiterId, int orderBy);
}
