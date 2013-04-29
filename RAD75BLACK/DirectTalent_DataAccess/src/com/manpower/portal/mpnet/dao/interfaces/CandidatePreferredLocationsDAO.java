package com.manpower.portal.mpnet.dao.interfaces;

import java.util.List;

public interface CandidatePreferredLocationsDAO extends DAO {
	
	public void savePreferredLocations(List locations, long candidateID, String locationFlag);
	public List getPreferredLocations(long candidateID);
	public void updatePreferredLocations(long candidateID, List newLocations, String locationFlag);

}
