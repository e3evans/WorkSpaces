package com.manpower.portal.mpnet.dao.interfaces;

public interface DynaCacheDAO extends DAO {

	public String getValue(String sessionId,String key);
	
	public void setValue(String sessionId,String key,String value);
	
	public String getValueAndDeleteIt(String sessionId, String key);
	
	public void clear(String sessionId);
}
