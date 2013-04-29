package com.manpower.directtalentsearch30.hbn.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.manpower.directtalentsearch30.hbn.beans.DirectTalentAdvertBean;
import com.manpower.directtalentsearch30.hbn.beans.SiteNameBean;
import com.manpower.directtalentsearch30.hbn.dao.DirectTalentAdSearchDAO;
import com.manpower.directtalentsearch30.hbn.shared.HibernateUtilities;
import com.manpower.directtalentsearch30.hbn.shared.dao.GenericHibernateDAO;


@SuppressWarnings("unchecked")
public class DirectTalentAdSearchDAOImpl extends GenericHibernateDAO implements DirectTalentAdSearchDAO {

	public DirectTalentAdSearchDAOImpl(Session session) {
		super(DirectTalentAdvertBean.class, session);
	}


	@Override
	public List findAll() {
		// TODO Auto-generated method stub
//		return getSession().createCriteria(DirectTalentAdvertBean.class).add(Restrictions.eq("countrycode", "ITA")).list();
		return getSession().createCriteria(DirectTalentAdvertBean.class).list();
	}

	@Override
	public Object findByID(Serializable id) {
		// TODO Auto-generated method stub
		return getSession().get(DirectTalentAdvertBean.class, id);
	}

	
	
	public List findAllSites() {
		
		List<Object> myList = new ArrayList<Object>();
		myList = HibernateUtilities.currentSession().createSQLQuery("select rownum as id, sitename from " +
				"(select sitename from v_ads_indsec13  group by sitename) order by sitename").addEntity(SiteNameBean.class).list();
		HibernateUtilities.currentSession().close();
		return myList;
	}


	public List finaAllBySiteName(String sitename) {		
		return getSession().createCriteria(DirectTalentAdvertBean.class).add(Restrictions.eq("sitename", sitename)).list();
	}

}
