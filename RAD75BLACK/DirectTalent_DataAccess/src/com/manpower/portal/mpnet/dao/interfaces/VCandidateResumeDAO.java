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
public interface VCandidateResumeDAO extends DAO {

	/**
	 * Find candidate resumes by a HQL query
	 * 
	 * @param value
	 * @param props
	 * @return List of VCandidateResume objects
	 */
	List findByWhere(String value, Properties props);

}
