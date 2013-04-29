/*
 * Created on May 24, 2006
 *
 */
package com.manpower.portal.mpnet.dao.interfaces;

import java.util.List;
import java.util.Properties;

/**
 * @author amillar
 *  
 */
public interface VCandidateApplicationDAO extends DAO {

	/**
	 * Find job aplications by HQL query
	 * 
	 * @param value
	 * @param props
	 * @return List of VCandidateApplication objects
	 */
	List findByWhere(String value, Properties props);

}
