/*
 * Created on Jun 28, 2007
 *
 */
package com.manpower.portal.mpnet.dao.interfaces;

import java.util.Date;
import java.util.List;

/**
 * @author Dimitar Bakardzhiev
 *  
 */
public interface SummaryAdvertisementsDAO extends DAO {
	public List findByRecruiterUserId(long recruiterId);

	public List find(long recruiterId, long branchId, Date startDate,
			Date endDate, String jobTitle, String referenceNumber);
	
	public int countByRecruiterUserId(long recruiterId, int advertStatus);
	
	public int count(long recruiterId, long branchId, Date startDate, Date endDate, String jobTitle, 
            String referenceNumber, int advertStatus);
	
    public List find(long recruiterId, long branchId, Date startDate, Date endDate, String jobTitle, 
            String referenceNumber, int fromIndex, int toIndex, int advertStatus, int sortOrder);
    
    public List findByRecruiterUserId(long recruiterId, int fromIndex, int toIndex, int sortOrder, int advertStatus);    

    public long findAppliedCountByAdvertId(long advertId);
}
