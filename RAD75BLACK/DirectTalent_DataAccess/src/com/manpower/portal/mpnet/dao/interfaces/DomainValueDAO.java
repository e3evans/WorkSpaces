/*
 * Created on 2006-2-1
 *
 */
package com.manpower.portal.mpnet.dao.interfaces;

import java.util.List;


/**
 * @author alexander.todorov
 *  
 */
public interface DomainValueDAO extends DAO {
	public List findDomainValuesBySites(String[] sites);
	public List findDomainValuesBySiteLangLookupName(String site, String language, String lookupName);
	public List findDomainValuesBySiteLanguage(String site, String language);
	public List findDomainValuesBySiteLangLookupNames(String site, String language, String lookupName[]);
}
