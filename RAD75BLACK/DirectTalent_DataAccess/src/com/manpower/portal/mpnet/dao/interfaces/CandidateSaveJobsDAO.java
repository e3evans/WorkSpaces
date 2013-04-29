/*
 * Created on Jan 23, 2006
 *
 */
package com.manpower.portal.mpnet.dao.interfaces;

import java.util.List;

/**
 * @author jsingh
 *  
 */
public interface CandidateSaveJobsDAO extends DAO {
	public List getAllSavedJobsSimple(long candidateId);
}
