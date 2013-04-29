package com.manpower.portal.mpnet.dao.interfaces;

import java.util.List;

import com.manpower.portal.mpnet.exception.ServiceException;
import com.manpower.portal.mpnet.hbn.beans.RecommendedCandidatesPreferences;

public interface RecommendedCandidatesPreferencesDAO extends DAO {
	
	public RecommendedCandidatesPreferences findRecommendedCandidatesPreferencesByAdvert(long advertId, long recruiterId) throws ServiceException;

	public List<RecommendedCandidatesPreferences> findRecommendedCanidatesPreferencesByRecruiterId(
			long recruiterId) throws ServiceException;
	
}
