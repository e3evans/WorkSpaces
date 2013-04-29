/*
 * Created on Jan 20, 2006
 *
 */
package com.manpower.portal.mpnet.dao.interfaces;

import java.io.Serializable;
import java.util.List;

/**
 * @author jsingh
 *  
 */
public interface PhoneDAO extends DAO {

	/**
	 * Find Candidate's phone number given a phone type
	 * 
	 * @param candiateId
	 * @param phoneType
	 * @return Phone object
	 */
	public Object findPhoneByCandidate(Serializable candiateId, String phoneType);

	public List findCandidatePhones(Serializable candidateId);
	
	public void deletePhone(long id);
}
