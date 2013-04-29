/*
 * Created on 2006-12-27
 *
 */
package com.manpower.portal.mpnet.dao.interfaces;

import java.util.Properties;

/**
 * @author alexander.todorov
 *  
 */
public interface EnumerationDAO extends DAO {
	public int deleteAll(String siteCode, String language, String lookupName,
			String lookupCode);
    public void storeByCustomSQLQuery(String queryString, Properties props);
}
