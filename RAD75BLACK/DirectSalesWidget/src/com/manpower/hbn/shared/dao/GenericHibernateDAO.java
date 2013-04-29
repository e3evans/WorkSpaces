/*
 * Created on Nov 14, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.hbn.shared.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;

import com.manpower.hbn.shared.HibernatePage;
import com.manpower.hbn.shared.Page;





/**
 * @author Dmitry
 * @author jsingh added new methods
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class GenericHibernateDAO implements DAO {

	public static String ORDER_ASC = "ASC";
	public static String ORDER_DESC = "DESC";
	
	private static final int BATCH_SIZE = 5;
	
	@SuppressWarnings("unchecked")
	private Class persistentClass;
	private Session session;
	

	@SuppressWarnings("unchecked")
	public GenericHibernateDAO(Class persistentClass,Session session) {
		this.persistentClass=persistentClass;
		this.session=session;
	}
	
	protected Session getSession() {
        return session;
    }

	@SuppressWarnings("unchecked")
	public Class getPersistentClass() {
        return persistentClass;
    }

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findByID(long)
	 */
	public abstract Object findByID(Serializable id);


	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findAll()
	 */
	public abstract List <Object> findAll();

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findByExample(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public  List <Object> findByExample(Object obj) {
		Criteria criteria=getSession().createCriteria(persistentClass);
		criteria.add(Example.create(obj));
		return criteria.list();
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#makePersistent(java.lang.Object)
	 */
	public Object makePersistent(Object obj) {
		session.beginTransaction();
		session.saveOrUpdate(obj);			
		//Let the service layer manage transaction 
		//session.getTransaction().commit();
		return obj;
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findByCustomQuery(java.lang.String, java.util.Properties)
	 */
	@SuppressWarnings("unchecked")
	public List <Object> findByCustomQuery(String query, Properties params) {
		Query q=getSession().createQuery(query);

		Iterator <Object> iter =params.keySet().iterator();
		while(iter.hasNext()) {
			String stKey=(String)iter.next();
			String stVal=(String)params.get(stKey);
			q.setString(stKey,stVal);
		}
		return q.list();
	}
	
	
	/* (sin Javadoc)
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findPageByCustomHQLQuery(java.lang.String, java.util.Properties, int, int)
	 */
	public Page findPageByCustomHQLQuery(String query, Properties params,int pageNumber, int pageSize) {
		Query q=getSession().createQuery(query);
		
		Iterator <Object> iter =params.keySet().iterator();
		while(iter.hasNext()) {
			String stKey=(String)iter.next();
			String stVal=(String)params.get(stKey);
			q.setString(stKey,stVal);
		}
		
		return HibernatePage.getHibernatePageInstance(q, params, pageNumber, pageSize);
	}
	
	/* (sin Javadoc)
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findPageByCustomSQLQuery(java.lang.String, java.util.Properties, int, int, java.lang.String, java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public Page findPageByCustomSQLQuery(String query, Properties params,int pageNumber, int pageSize, String entityAlias, Class entityName) {
		SQLQuery q=getSession().createSQLQuery(query);
		q.addEntity(entityAlias,entityName);
		
		Iterator <Object> iter =params.keySet().iterator();
		while(iter.hasNext()) {
			String stKey=(String)iter.next();
			String stVal=(String)params.get(stKey);
			q.setString(stKey,stVal);
		}
		
		return HibernatePage.getHibernatePageInstance(q, params, pageNumber, pageSize);
	}
	
	public Object runSQLQuery(String query , Properties params , String type )
	{
		
		SQLQuery q = getSession().createSQLQuery(query);
		Iterator <Object> iter =params.keySet().iterator();
		while(iter.hasNext()) {
			String stKey=(String)iter.next();
			String stVal=(String)params.get(stKey);
			q.setString(stKey,stVal);
		}
		q.addScalar(type, Hibernate.DATE);
		Date max = (Date) q.uniqueResult();
		
       	return max;
		
		
	}
	
	public Object delete(Object obj)
	{
		session.delete(obj);
		return obj;
		
	}
	
	public Object makeUpdate(Object obj) {
		session.beginTransaction();
		
		session.update(obj);
		//Let the service layer manage transaction 
		//session.getTransaction().commit();
		return obj;
	}
	
	/*
	 * Alexander Todorov.
	 * The application needs of methods for batch update, insert and delete.
	 * 
	 * */
	
	public void updateAll(List <Object> records){
	
		if(	records != null && 
			!records.isEmpty()){
			
			int recSize = records.size();
			
			Object forUpdate;
			
			for(int i=0; i<recSize; i++){
			
				forUpdate = records.get(i);
					
				session.update(forUpdate);
					
				if(i % BATCH_SIZE == 0){
			
					session.flush();
					
					session.clear();
						
				}
				
			}
		
		}
	}
	protected Criteria addOrder(Criteria criteria,String orderBy,String ascDsc){
		if (ascDsc.equals(ORDER_ASC)){
			criteria.addOrder(Order.asc(orderBy));
		}else{
			criteria.addOrder(Order.desc(orderBy));
		}
		return criteria;
	}
}
