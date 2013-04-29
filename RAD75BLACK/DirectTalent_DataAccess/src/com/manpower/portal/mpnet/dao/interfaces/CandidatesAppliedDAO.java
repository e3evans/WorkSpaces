/*
 * Created on Jul 2, 2007
 *
 */
package com.manpower.portal.mpnet.dao.interfaces;

import java.util.List;

import com.manpower.portal.mpnet.hbn.util.Page;

/**
 * @author Dimitar Bakardzhiev
 *  
 */
public interface CandidatesAppliedDAO extends DAO {
	public List findAllByAdvertisementId(Long advertisementId);
	public Page findAllApplicantsByAdvertId(long advertId, int fromIndex, int pageSize, int sortOrder, boolean desc, String filter);
	public int getCountAllApplicantsByAdvertId(long advertId, String filter);
	
	public Page findAllAppliedCandidatesByAdvertId(long advertId, int pageNumber, int pageSize, int sortOrder, boolean desc);
	public int getCountAppliedCandidatesByAdvertId(long advertId);
}
