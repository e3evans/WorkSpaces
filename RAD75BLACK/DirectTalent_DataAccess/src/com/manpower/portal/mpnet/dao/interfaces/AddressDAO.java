/*
 * Created on Jan 20, 2006
 *
 */
package com.manpower.portal.mpnet.dao.interfaces;

import java.io.Serializable;

/**
 * @author jsingh
 *  
 */
public interface AddressDAO extends DAO {

	/**
	 * Find Address by Candidate ID
	 * 
	 * @param candiateId
	 * @return Address Object
	 */
	public Object findAddressByCandidate(Serializable candiateId);

}
