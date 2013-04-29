package com.manpower.portal.mpnet.dao.interfaces;

import java.util.List;

public interface CandidateOccupationDAO extends DAO{

	
	public int saveOccupations(List occupations, long candidateId);
	public int deleteCandidateOccupations(long candidateId);
	public List findCandidateOccupations(long cid);
	
}
