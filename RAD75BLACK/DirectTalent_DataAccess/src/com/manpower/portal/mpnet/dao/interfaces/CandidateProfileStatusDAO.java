package com.manpower.portal.mpnet.dao.interfaces;

import java.util.List;

import com.manpower.portal.mpnet.hbn.beans.CandidateProfileStatus;

public interface CandidateProfileStatusDAO extends DAO 
{
	public CandidateProfileStatus getByCandidateId(long candidateId);
	
	public List getCandidateHistory(long candidateId);
	
	public long setCandidateProfileStatus(long candidateId, boolean statusActive);
}
