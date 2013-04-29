package com.manpower.portal.gemt.hbn.object.dao;

import java.io.Serializable;
import java.util.List;

public interface GemtManagersDAO {
	public List findAll();
	public Object findByID(Serializable id); 
	public Object findByEmail(String eMail);
	public Object makePersistent(Object obj) ;
}
