/*
 * Created on Aug 14, 2006
 *
 */
package com.manpower.portal.mpnet.dao.interfaces;

import java.util.List;

import com.manpower.portal.mpnet.hbn.beans.CredentialVault;

/**
 * @author jsingh
 *  
 */
public interface CredentialVaultDAO extends DAO {
	
	//public List<CredentialVault> findCredentials(String siteCode, String login, String type);
	
	public List<CredentialVault> findCredentialLogins(String siteCode, String login, String type);

	public List findCredentials(String siteCode, String login, String type);

}
