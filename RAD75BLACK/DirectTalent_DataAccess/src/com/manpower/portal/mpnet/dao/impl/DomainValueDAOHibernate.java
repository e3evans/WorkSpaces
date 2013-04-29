/*
 * Created on Jan 20, 2006
 *
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.DomainValueDAO;
import com.manpower.portal.mpnet.hbn.beans.DomainValue;

/**
 * @author jsingh
 *  
 */
public class DomainValueDAOHibernate extends GenericHibernateDAO implements
		DomainValueDAO {

	/**
	 * Constructor
	 * 
	 * @param persistentClass
	 * @param session
	 */
	public DomainValueDAOHibernate(Session session) {
		super(DomainValue.class, session);
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findAll()
	 */
	public List findAll() {

		Criteria query = HibernateUtil.getCurrentSession()
				.createCriteria(DomainValue.class);

		query.addOrder(Order.asc("site"));

		query.addOrder(Order.asc("language"));

		query.addOrder(Order.asc("sortCode"));

		List values = query.list();

		return values;
	}
	
	

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findByID(java.io.Serializable)
	 */
	public Object findByID(Serializable id) {

		return null;
	}
	
	public List findDomainValuesBySites(String[] sites) {

		Criteria query = HibernateUtil.getCurrentSession()
				.createCriteria(DomainValue.class);

		query.add(Restrictions.in("site", sites));

		query.addOrder(Order.asc("language"));

		query.addOrder(Order.asc("sortCode"));

		List values = query.list();

		return values;
	}
	
	public List findDomainValuesBySiteLangLookupName(String site, String language, String lookupName){
		
		List result = null;
		
		String query = 	" SELECT v.VALUE_CODE,v.LOOKUP_DESCRIPTION" +
						" FROM v_lookup_values v" +
						" WHERE v.SITECODE = '" + site + "' AND v.LANG = '"+ language +"' AND v.LOOKUP_NAME = '" + lookupName+ "'"+
						" ORDER BY v.sort_code";
		
		
		result = findBySQLQuery(query);
		return result;
	}
	
	public List findDomainValuesBySiteLangLookupNames(String site, String language, String lookupName[]){
		
		List result = null;
		
		StringBuffer query = 	new StringBuffer(" SELECT v.VALUE_CODE,v.LOOKUP_DESCRIPTION");
		query.append(" FROM v_lookup_values v");
		query.append(" WHERE v.SITECODE = '" + site + "'");
		query.append(" AND v.LANG = '" + language + "'");
		query.append(" AND v.LOOKUP_NAME IN(");
		String orderBy = " ORDER BY v.sort_code";

		for(int i=0;i<lookupName.length; i++){
			if(i == (lookupName.length -1))
				query.append(lookupName[i] + ")");
			else
				query.append(lookupName[i] + ",");
		}
		
		
		query.append(orderBy);
		result = findBySQLQuery(query.toString());
		return result;
	}
	
	public List findDomainValuesBySiteLanguage(String site, String language){
		List result = null;
		
		String query = 	" SELECT v.LOOKUP_NAME, v.VALUE_CODE,v.LOOKUP_DESCRIPTION" +
						" FROM v_lookup_values v" +
						" WHERE v.SITECODE = '" + site + "' AND v.LANG = '"+ language +"'" +
						" ORDER BY v.LOOKUP_NAME, v.sort_code";
		
		result = findBySQLQuery(query);
		return result;
	}
}
