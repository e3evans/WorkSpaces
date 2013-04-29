package com.manpower.portal.mpnet.dao.interfaces;

import java.util.List;

import com.manpower.portal.mpnet.exception.ServiceException;
import com.manpower.portal.mpnet.hbn.beans.RecommendedCandidatesView;



public interface RecommendedCandidatesDAO extends DAO {
	
	public List <RecommendedCandidatesView> getRecomendedCandidatesAdvertsByRecruiterId(long siteId, long recruiterId, int fromIndex, int toIndex, int orderBy) throws ServiceException;

}
