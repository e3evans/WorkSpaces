/*
 * Created on Nov 14, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.directtalentseo.hbn.shared.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import com.manpower.directtalentseo.hbn.shared.Page;





public interface DAO {

	Object findByID(Serializable id);
	List findAll();
	List findByExample(Object obj);
	Object makePersistent(Object obj);
	List findByCustomQuery(String query, Properties params);
	Object runSQLQuery(String query , Properties params , String type);
	Object delete(Object obj);
	Object makeUpdate(Object obj);
	void updateAll(List records);
	Page findPageByCustomHQLQuery(String query, Properties params, int pageNumber, int pageSize);
	Page findPageByCustomSQLQuery(String query, Properties params, int pageNumber, int pageSize, String entityAlias, Class entityName);
}
