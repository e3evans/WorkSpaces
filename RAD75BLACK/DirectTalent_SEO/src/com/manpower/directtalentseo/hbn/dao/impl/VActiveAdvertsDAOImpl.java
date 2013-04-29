package com.manpower.directtalentseo.hbn.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.manpower.directtalentseo.hbn.beans.VAdSingle;
import com.manpower.directtalentseo.hbn.beans.VAdsIndSec;
import com.manpower.directtalentseo.hbn.beans.Vactiveadverts;
import com.manpower.directtalentseo.hbn.dao.VActiveAdvertsDAO;
import com.manpower.directtalentseo.hbn.shared.HibernateUtilities;
import com.manpower.directtalentseo.hbn.shared.dao.GenericHibernateDAO;




public class VActiveAdvertsDAOImpl extends GenericHibernateDAO implements VActiveAdvertsDAO{

	public VActiveAdvertsDAOImpl(Session session) {
		super(Vactiveadverts.class, session);
	}

	@SuppressWarnings("unchecked")
	public List findAll() {
		// TODO Auto-generated method stub
		return getSession().createCriteria(Vactiveadverts.class).list();
	}
	
	@SuppressWarnings("unchecked")
	public List findAllbySiteId(String siteId){
		
		Criteria criteria = getSession().createCriteria(Vactiveadverts.class);
		criteria.add(Restrictions.eq("sitename", siteId));
		return criteria.list();
	}

	public Object findByID(Serializable id) {
		// TODO Auto-generated method stub
		return getSession().get(Vactiveadverts.class, id);
	}

	
	public Object findAdvertById(Serializable id) {
		// TODO Auto-generated method stub
		return getSession().get(VAdSingle.class, id);
	}

	@SuppressWarnings("unchecked")
	public Object findAdvertByIdLang(Serializable id, String lang) {
		Criteria criteria = getSession().createCriteria(VAdSingle.class);
		Object advert = null;
		criteria.add(Restrictions.eq("id", id));
		criteria.add(Restrictions.eq("language", lang));
		
		List<Object> temp = criteria.list();
		if (temp.size()>0)advert = temp.get(0);
		
		return advert;
	}

	@SuppressWarnings("unchecked")
	public List findaAllBySiteNameAndCategory(String sitename, String category,int maxRows,int pageNumber) {
		
		SQLQuery query = HibernateUtilities.currentSession().createSQLQuery("select * from v_ads_indsec13 t, (select count(*)q_total from v_ads_indsec13 " +
				"where sitename='"+sitename+"' and industry_sector='"+category+"')" +
				"where sitename='"+sitename+"' and industry_sector='"+category+"' order by publicationdate desc").addEntity(VAdsIndSec.class);
		
//		List tempList = query.list();
		return executeQuery(query, maxRows, pageNumber);
	}
	
	private List<Object> executeQuery(SQLQuery query,int maxRows,int pageNumber){
		
		/*
		 * Get the correct Page and grab the right number of rows to display.
		 */
		ScrollableResults results = query.scroll(ScrollMode.SCROLL_SENSITIVE);
		List<Object> pageInfo = new ArrayList<Object>();
	
		if (results.next()){
//			int numberOfResults = Integer.parseInt(Long.toString(csr.getQ_total()));
			results.first();
			results.scroll((pageNumber*maxRows)-maxRows);
		
//			List<Object> pageList = new ArrayList<Object>();
			int i = 0;
			while (maxRows > i++) {
				pageInfo.add(results.get(0));
				if (!results.next())break;
			}
			
			/*
			 * PUT THE RESULTS IN ONE SLOT AND THE TOTAL NUMBER OF RESULTS IN ANOTHER....
			 */
			
//			pageInfo.add(0, pageList);
//			pageInfo.add(1,Integer.toString(numberOfResults));
		}else{
			return null;
		}
		HibernateUtilities.closeSession();
		return pageInfo;
	}
	
	
	

}
