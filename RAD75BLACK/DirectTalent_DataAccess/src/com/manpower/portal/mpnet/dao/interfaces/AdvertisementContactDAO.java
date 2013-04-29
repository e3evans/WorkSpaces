/*
 * Created on Feb 8, 2006
 *
 */
package com.manpower.portal.mpnet.dao.interfaces;

import java.util.List;

import com.manpower.portal.mpnet.hbn.beans.AdvertisementContact;
import com.manpower.portal.mpnet.hbn.beans.BranchTable;

/**
 * @author Eric Evans
 *  
 */
public interface AdvertisementContactDAO extends DAO {

	public List findRecruiterByUserId(String siteId, String recruiterUserId, String lang);
    
    public List findRecruitersByBranchId(BranchTable fromBranch);
    
    public AdvertisementContact findAdvertContactByBranchAndUserId(BranchTable branch, String userId);
    
	public List findRecruiterByUserIdAndBranchId(long siteId, String recruiterUserId, String branchExternalId, String language);
	
	public List findAdvertisementContactsOrdered(String siteId, String branchId, int fromIndex, int pageSize, int orderBy, boolean descending);
}
