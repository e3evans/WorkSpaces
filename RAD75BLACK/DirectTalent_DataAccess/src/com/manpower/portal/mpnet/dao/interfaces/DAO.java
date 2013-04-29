/*
 * Created on Nov 14, 2005
 *
 */
package com.manpower.portal.mpnet.dao.interfaces;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import org.hibernate.type.Type;

/**
 * Interface which all DAO objects implement
 * 
 * @author jsingh
 *  
 */
public interface DAO {

	/**
	 * Find by Object's Primary Key
	 * 
	 * @param id
	 * @return Persisted object
	 */
	Object findByID(Serializable id);

	/**
	 * Find all objects
	 * 
	 * @return List of Objects
	 */
	List findAll();

	/**
	 * Find by Hibernate Example Query
	 * 
	 * @param obj
	 * @return List of Objects
	 */
	List findByExample(Object obj);

	/**
	 * Persist an Object
	 * 
	 * @param obj
	 * @return Persisted Object
	 */
	Object save(Object obj);

	/**
	 * Persist an Object
	 * 
	 * @param obj
	 * @return Persisted Object
	 */

	Object makePersistent(Object obj);

	/**
	 * Find by custom HQL query
	 * 
	 * @param query
	 * @param params
	 * @return List of Objects
	 */
	List findByCustomQuery(String query, Properties params);

	/**
	 * 
	 * @param query
	 * @param params
	 * @return
	 */
	
	List findByCustomSQLQuery(String query, Properties params);
	
	List findByCustomSQLQuery(String query, Properties params, String name,
			Type type);
	
	/**
	 * 
	 * @param query
	 * @param params
	 * @param entityClass
	 * @return List
	 */
	
	List findByCustomSQLQueryByEntity(String query, Properties params,
			Class entityClass);

	/**
	 * Issue an SQL query with the supplied parameters
	 * 
	 * @param query
	 * @param params
	 * @param type
	 * @return Persisted Object
	 */
	Object runSQLQuery(String query, Properties params, String type);
	
	void runSQLUpdateQuery(String query, Properties params);

	/**
	 * Delete a Persisted Object frm DB
	 * 
	 * @param obj
	 * @return Deleted Object
	 */
	Object delete(Object obj);

	/**
	 * Update a persisted object
	 * 
	 * @param obj
	 * @return Updated persisted object
	 */
	Object makeUpdate(Object obj);

	/**
	 * Batch update persisted objects
	 * 
	 * @param records
	 */
	void updateAll(List records);

	/**
	 * Batch delete persisted objects
	 * 
	 * @param records
	 */
	void deleteAll(List records);

	/**
	 * Batch save persistent objects
	 * 
	 * @param records
	 */
	void saveAll(List records);

	/**
	 * Return a page with a defined size by issuing a HQL query
	 * 
	 * @param query
	 * @param params
	 * @param pageNumber
	 * @param pageSize
	 * @return Page object
	 */
	List findPageByCustomHQLQuery(String query, Properties params,
			int pageNumber, int pageSize);

	/**
	 * Return a page with a defined size by issuing a HQL query
	 * 
	 * @param query
	 * @param params
	 * @param pageNumber
	 * @param pageSize
	 * @param entityAlias
	 * @param entityName
	 * @return Page object
	 */
	List findPageByCustomSQLQuery(String query, Properties params,
			int pageNumber, int pageSize, String entityAlias, Class entityName);

	List findPageByCustomHQLQuery(String query, Properties params,
			int pageNumber, int pageSize, boolean calculateTotalCount);

	List findResultsByCustomHQLQuery(String query, Properties params,
			int startIndex, int endIndex);
	
	public List findBySQLQuery(String query);
	
	public long runCountQuery(String query, Properties params);
}
