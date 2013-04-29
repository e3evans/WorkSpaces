package com.manpower.portal.mpnet.dao.interfaces;

import java.util.List;

import com.manpower.portal.mpnet.hbn.beans.CandidateSearch;
import com.manpower.portal.mpnet.hbn.beans.CandidateSearchFavorite;
import com.manpower.portal.mpnet.hbn.beans.CandidateSearchResume;
import com.manpower.portal.mpnet.ui.beans.ApplicantSearchGeoCodeBean;

public interface CandidateSearchDAO extends DAO{

	
	
	public List<CandidateSearch> getCandidatesByKeyword(String keyword, long siteId,
			int orderByColumn, int maxResult, int fromIndex, int toIndex);
	
	public int getCandidatesByKeywordCount(String keyword, long siteId);
	
	public CandidateSearchResume getCandidateResume(long id);
	
	public List<CandidateSearch> getSimpleSearch(long siteId, String keyword,List<Long> locationsIds, double latitude, double longitude, int distance, String distanceUnit, int orderBy, int fromIndex, int toIndex);
	
	public int getSimpleSearchCount(long siteId, String keyword,List<Long> locationsIds, double latitude, double longitude, int distance, String distanceUnit);
	
	public CandidateSearchFavorite addFavoriteCandidate(long candidateId, long recruiterId, String comment);
	
	public List<CandidateSearch> getAllFavoriteCandidatesByRecruiter(long recruiterId, int orderByColumn, int fromIndex, int toIndex);
	
	public int getAllFavoriteCandidatesByRecruiterCount(long recruiterId);
	
	public boolean favoriteCandidateExists(long recruiterId, long candidateId);
	
	public void deleteFavoriteCandidate(long candidateId, long recruiterId);
	
	public List<CandidateSearch> getPowerSearchApplicantsByIdList(List<Long> idList);
}
