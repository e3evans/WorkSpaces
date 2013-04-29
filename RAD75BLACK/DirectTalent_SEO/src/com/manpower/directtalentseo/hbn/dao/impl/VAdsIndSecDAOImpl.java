package com.manpower.directtalentseo.hbn.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;


import com.manpower.directtalentseo.hbn.beans.VAdsIndSec;
import com.manpower.directtalentseo.hbn.beans.VAdsIndSecList;
import com.manpower.directtalentseo.hbn.dao.VAdsIndSecDAO;
import com.manpower.directtalentseo.hbn.shared.HibernateUtilities;
import com.manpower.directtalentseo.hbn.shared.dao.GenericHibernateDAO;

public class VAdsIndSecDAOImpl extends GenericHibernateDAO implements VAdsIndSecDAO{

	public VAdsIndSecDAOImpl(Session session) {
		super(VAdsIndSec.class, session);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List findAll() {
		// TODO Auto-generated method stub
		return getSession().createCriteria(VAdsIndSec.class).list();
	}

	public Object findByID(Serializable id) {
		// TODO Auto-generated method stub
		return getSession().get(VAdsIndSec.class, id);
	}

	@SuppressWarnings("unchecked")
	public List finaAllByCoutryCode(String sitename){
		
		Criteria criteria = getSession().createCriteria(VAdsIndSec.class);
		criteria.add(Restrictions.eq("sitename", sitename)).addOrder(Order.desc("publicationdate")).addOrder(Order.asc("jobtitle"));
		
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List findUniqueCategoryList(String sitename, String lang) {

		List<Object> myList = new ArrayList<Object>();
		myList = HibernateUtilities.currentSession().createSQLQuery("select rownum as rownumber,t.sitename,t.industry_sector,t.language " +
				"from (select sitename,industry_sector,language from v_ads_indsec13 " +
				"group by sitename,industry_sector,language) t where t.sitename='"+sitename+"' order by " +
				"initcap(t.industry_sector) ").addEntity(VAdsIndSecList.class).list();
		HibernateUtilities.currentSession().close();
		return myList;
	}
	
	
	

}
