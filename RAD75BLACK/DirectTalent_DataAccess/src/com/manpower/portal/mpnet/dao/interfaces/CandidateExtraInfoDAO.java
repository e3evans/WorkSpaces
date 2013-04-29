package com.manpower.portal.mpnet.dao.interfaces;

import com.manpower.portal.mpnet.hbn.beans.CandidateExtraInfo;

public interface CandidateExtraInfoDAO extends DAO {

	public CandidateExtraInfo findByCandidateId(long candidateId);
	
	public void saveCandidateExtraInfo(CandidateExtraInfo extraInfo);
	
	public void updateCandidateExtraInfo(CandidateExtraInfo extraInfo);
	
	public void deleteCandidateExtraInfo(long id);
}
