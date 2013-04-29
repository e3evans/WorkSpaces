package com.manpower.directtalentrecruitersearch.dao;

import java.util.List;

import com.manpower.hbn.beans.RecruiterAuditing;

public interface RecruiterAuditingDAO {
	public RecruiterAuditing saveRecruiterAuditing(RecruiterAuditing recruiterAuditing);
	public List getRecentSearchesByActionAndRecruiterId(int count, String action, long recruiterId);
}
