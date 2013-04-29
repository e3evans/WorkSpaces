/*
 * Created on Nov 14, 2005
 *
 */
package com.manpower.portal.mpnet.dao.abstracts;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.type.Type;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.interfaces.DAO;
import com.manpower.portal.mpnet.hbn.beans.CandidateResume;

/**
 * 
 * Abstract class to provide general DAO functionality
 *  
 */
public abstract class GenericHibernateDAO implements DAO {

	public static String ORDER_ASC = "ASC";

	public static String ORDER_DESC = "DESC";

	/**
	 * Batch size for batch operations
	 */
	private static final int BATCH_SIZE = 5;

	private Class persistentClass;

	private String language = null;
	
	private static Logger log = Logger.getLogger(GenericHibernateDAO.class);

	/**
	 * Hibernate sesion object.
	 */
	//private Session session;
	/**
	 * Constructor to create DAO object
	 * 
	 * @param persistentClass
	 * @param session
	 */
	public GenericHibernateDAO(Class persistentClass, Session session) {
		this.persistentClass = persistentClass;
		//this.session=session;
		//this.session = HibernateUtil.getSessionFactory().getCurrentSession();
	}

	/**
	 * 
	 * @return Persistent class object.
	 */
	public Class getPersistentClass() {
		return persistentClass;
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findByID(long)
	 */
	public abstract Object findByID(Serializable id);

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findAll()
	 */
	public abstract List findAll();

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findByExample(java.lang.Object)
	 */
	public List findByExample(Object obj) {
		
		Criteria criteria = HibernateUtil.getSessionFactory()
				.getCurrentSession().createCriteria(persistentClass);
		
		
		criteria.add(Example.create(obj));
		return criteria.list();
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#makePersistent(java.lang.Object)
	 */
	public Object makePersistent(Object obj) {
		//session.saveOrUpdate(obj);
		//session.beginTransaction();
	
	try{
    if (obj instanceof CandidateResume) 
    	
    	log.fatal("NOTICE: Resume DAO  BEFORE   INSERT makePersistentDAO :BLOB size  " + (((CandidateResume)obj).getResume()!=null?((CandidateResume)obj).getResume().length():0)+" bytes");
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	HibernateUtil.getCurrentSession().saveOrUpdate(obj);
	
	
	try{
        if (obj instanceof CandidateResume) 
        	
        	log.fatal("NOTICE: Resume EJB  AFTER   Update makePersistentDAO :BLOB size  " + (((CandidateResume)obj).getResume()!=null?((CandidateResume)obj).getResume().length():0)+" bytes");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	return obj;
}
	public Collection makePersistent(Collection collection) {
		Iterator it = collection.iterator();
		long counter = 0;
		Session session = HibernateUtil.getCurrentSession();
		while(it.hasNext()){
			Object next = it.next();
			counter ++;
			session.saveOrUpdate(next);
			if(counter == 10){
				session.flush();
				session.clear();
			}
			
		}
		return collection;
	}

	public Object save(Object obj) {
		
		HibernateUtil.getCurrentSession().save(obj);
		return obj;
		
		
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findByCustomQuery(java.lang.String,
	 *      java.util.Properties)
	 */
	public List findByCustomQuery(String query, Properties params) {
		
		Query q = HibernateUtil.getCurrentSession()
				.createQuery(query);

		if (params != null) {
			Iterator iter = params.keySet().iterator();

			while (iter.hasNext()) {
				String stKey = (String) iter.next();
				String stVal = (String) params.get(stKey);
				q.setString(stKey, stVal);
			}
		}
		return q.list();
	}

	/**
	 * 
	 * @param query
	 * @param params
	 * @param scalarName
	 * @param type
	 * @return
	 */
	
	public List findBySQLQuery(String query) {
		
		SQLQuery q = HibernateUtil.getCurrentSession().createSQLQuery(query);
		return q.list();
	}
	
	/**
	 * 
	 * @param query
	 * @param params
	 * @param entityClass
	 * @return List
	 */
	
	public List findByCustomSQLQueryByEntity(String query, Properties params,
			Class entityClass) {
		
		SQLQuery q = HibernateUtil.getCurrentSession()
				.createSQLQuery(query);

		if (params != null) {
			Iterator iter = params.keySet().iterator();

			while (iter.hasNext()) {
				String stKey = (String) iter.next();
				String stVal = (String) params.get(stKey);
				q.setString(stKey, stVal);
			}
		}
		
		q.addEntity(entityClass);
		return q.list();
	}
	
	/**
	 * 
	 * @param query
	 * @param params
	 * @return List
	 */
	
	public List findByCustomSQLQuery(String query, Properties params) {
		
		SQLQuery q = HibernateUtil.getCurrentSession()
				.createSQLQuery(query);

		if (params != null) {
			Iterator iter = params.keySet().iterator();

			while (iter.hasNext()) {
				String stKey = (String) iter.next();
				String stVal = (String) params.get(stKey);
				q.setString(stKey, stVal);
			}
		}
		
		return q.list();
	}

	
	public List findByCustomSQLQuery(String query, Properties params,
			String scalarName, Type type) {
		
		SQLQuery q = HibernateUtil.getCurrentSession()
				.createSQLQuery(query);

		if (params != null) {
			Iterator iter = params.keySet().iterator();

			while (iter.hasNext()) {
				String stKey = (String) iter.next();
				String stVal = (String) params.get(stKey);
				q.setString(stKey, stVal);
			}
		}

		q.addScalar(scalarName, type);
		return q.list();
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findPageByCustomHQLQuery(java.lang.String,
	 *      java.util.Properties, int, int)
	 */
	public List findPageByCustomHQLQuery(String query, Properties params,
			int pageNumber, int pageSize) {
		List pg = findPageByCustomHQLQuery(query, params, pageNumber, pageSize,
				true);
		return pg;
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findPageByCustomSQLQuery(java.lang.String,
	 *      java.util.Properties, int, int, java.lang.String, java.lang.Class)
	 */
	public List findPageByCustomSQLQuery(String query, Properties params,
			int pageNumber, int pageSize, String entityAlias, Class entityName) {
		
		Session session = HibernateUtil.getCurrentSession();
		if (session == null || !session.isOpen()) {
			session = null;
			session = HibernateUtil.getSessionFactory().openSession();
		}
		SQLQuery q = session.createSQLQuery(query);
		q.addEntity(entityAlias, entityName);

		Iterator iter = params.keySet().iterator();
		while (iter.hasNext()) {
			String stKey = (String) iter.next();
			String stVal = (String) params.get(stKey);
			q.setString(stKey, stVal);
		}

		List elements = q.setFirstResult(pageNumber * pageSize).setMaxResults(
				pageSize).list();
/*		HibernatePage page = new HibernatePage(pageNumber, pageSize);
		page.setElements(elements);
		return page;
*/
		return elements;
	}

	/**
	 * Issue a SQL query
	 * 
	 * @return java.util.Date object
	 */
	public Object runSQLQuery(String query, Properties params, String type) {

		SQLQuery q = HibernateUtil.getCurrentSession()
				.createSQLQuery(query);
		Iterator iter = params.keySet().iterator();
		while (iter.hasNext()) {
			String stKey = (String) iter.next();
			String stVal = (String) params.get(stKey);
			q.setString(stKey, stVal);
		}

		q.addScalar(type, Hibernate.DATE);
		Date max = (Date) q.uniqueResult();

		return max;

	}
	
	public void runSQLUpdateQuery(String query, Properties params){
		
		SQLQuery q = HibernateUtil.getCurrentSession()
		.createSQLQuery(query);
		Iterator iter = params.keySet().iterator();
		while (iter.hasNext()) {
			String stKey = (String) iter.next();
			String stVal = (String) params.get(stKey);
			q.setString(stKey, stVal);
		}
		q.executeUpdate();
	}

	public long runCountQuery(String query, Properties params) {

		Session session = HibernateUtil.getCurrentSession();
		if (session == null || !session.isOpen()) {
			session = null;
			session = HibernateUtil.getSessionFactory().openSession();
		}

		Query q = session.createQuery(query);

		Iterator iter = params.keySet().iterator();
		while (iter.hasNext()) {
			String stKey = (String) iter.next();
			String stVal = (String) params.get(stKey);
			q.setString(stKey, stVal);
		}

		Long result = (Long) q.uniqueResult();

		return result.longValue();

	}

	/**
	 * Delete a persisted object
	 * 
	 * @return Deleted object.
	 */
	public Object delete(Object obj) {
		
		HibernateUtil.getCurrentSession().delete(obj);
		return obj;

	}

	/**
	 * Update a persisted object.
	 * 
	 * @return Updated object.
	 */
	public Object makeUpdate(Object obj) {
		//session.beginTransaction();
		
		try{
        if (obj instanceof CandidateResume) 
        	
        	log.fatal("NOTICE: Resume DAO  BEFORE   Update makeUpdateDAO :BLOB size  " + (((CandidateResume)obj).getResume()!=null?((CandidateResume)obj).getResume().length():0)+" bytes");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		HibernateUtil.getCurrentSession().update(obj);
		
		
		try{
	        if (obj instanceof CandidateResume) 
	        	
	        	log.fatal("NOTICE: Resume DAO  AFTER   Update makeUpdateDAO :BLOB size  " + (((CandidateResume)obj).getResume()!=null?((CandidateResume)obj).getResume().length():0)+" bytes");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		return obj;
	}

	/**
	 * Perform batch update.
	 *  
	 */
	public void updateAll(List records) {

		if (records != null && !records.isEmpty()) {

			int recSize = records.size();

			Object forUpdate;

			for (int i = 0; i < recSize; i++) {

				forUpdate = records.get(i);

				HibernateUtil.getCurrentSession().update(
						forUpdate);

				if (i % BATCH_SIZE == 0) {

					//	session.flush();

					//	session.clear();

				}

			}

		}
	}

	/**
	 * Perform batch delete.
	 */
	public void deleteAll(List records) {

		if (records != null && !records.isEmpty()) {

			int recSize = records.size();

			Object forDelete;

			for (int i = 0; i < recSize; i++) {

				forDelete = records.get(i);

				HibernateUtil.getCurrentSession().delete(
						forDelete);

				if (i % BATCH_SIZE == 0) {

					//session.flush();

					//session.clear();

				}

			}

		}
	}

	/**
	 * Perform batch save.
	 */
	public void saveAll(List records) {

		if (records != null && !records.isEmpty()) {

			int recSize = records.size();

			Object forInsert;

			for (int i = 0; i < recSize; i++) {

				forInsert = records.get(i);

				HibernateUtil.getCurrentSession().save(
						forInsert);

				if (i % BATCH_SIZE == 0) {

					//	session.flush();

					//	session.clear();

				}

			}

		}
	}

	protected Criteria addOrder(Criteria criteria, String orderBy, String ascDsc) {
		if (ascDsc.equals(ORDER_ASC)) {
			criteria.addOrder(Order.asc(orderBy));
		} else {
			criteria.addOrder(Order.desc(orderBy));
		}
		return criteria;
	}

	public List findPageByCustomHQLQuery(String query, Properties params,
			int pageNumber, int pageSize, boolean calculateTotalCount) {
		
		Session session = HibernateUtil.getCurrentSession();
		if (session == null || !session.isOpen()) {
			session = null;
			session = HibernateUtil.getSessionFactory().openSession();
		}
		Query q = session.createQuery(query);

		Iterator iter = params.keySet().iterator();
		while (iter.hasNext()) {
			String stKey = (String) iter.next();
			String stVal = (String) params.get(stKey);
			q.setString(stKey, stVal);
		}

		List elements = q.setFirstResult(pageNumber * pageSize).setMaxResults(
				pageSize).list();
		
		/*HibernatePage page = new HibernatePage(pageNumber, pageSize);
		
		List beanElements = getBeanElements(elements);
		page.setElements(beanElements);

		if (calculateTotalCount) {
			String countQuery = "SELECT COUNT(*) " + query;
			long totalCount = runCountQuery(countQuery, params);
			page.setTotalElements((int) totalCount);
		}*/

		return elements;
	}

	public List findResultsByCustomHQLQuery(String query, Properties params,
			int startIndex, int endIndex) {

		Session session = HibernateUtil.getCurrentSession();
		if (session == null || !session.isOpen()) {
			session = null;
			session = HibernateUtil.getSessionFactory().openSession();
		}
		Query q = session.createQuery(query);

		Iterator iter = params.keySet().iterator();
		while (iter.hasNext()) {
			String stKey = (String) iter.next();
			String stVal = (String) params.get(stKey);
			q.setString(stKey, stVal);
		}

		List elements = q.setFirstResult(startIndex).setMaxResults(endIndex)
				.list();
		return elements;
	}

	/**
	 * @return Returns the language.
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language
	 *            The language to set.
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
    
}
