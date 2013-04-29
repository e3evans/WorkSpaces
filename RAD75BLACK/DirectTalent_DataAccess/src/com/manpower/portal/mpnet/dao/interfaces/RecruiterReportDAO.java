/*
 * Created on Sep 16, 2006
 *
 */
package com.manpower.portal.mpnet.dao.interfaces;

import java.util.List;

import com.manpower.portal.mpnet.hbn.beans.Advertisement;

/**
 * @author Dimitar Bakardzhiev
 *  
 */
public interface RecruiterReportDAO {

	/**
	 * Find all candidates applied for an advertisement given a list of Lens IDs
	 * 
	 * @param IDs
	 * @param advertisementId
	 * @return
	 */
	public List findByResumeIDs(List IDs, long advertisementId, int version);
}
