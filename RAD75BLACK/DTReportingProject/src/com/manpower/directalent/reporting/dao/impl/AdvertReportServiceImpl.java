package com.manpower.directalent.reporting.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.manpower.directalent.reporting.dao.AdvertReportService;
import com.manpower.directalent.reporting.hbn.beans.AdvertisementCountBean;
import com.manpower.directalent.reporting.hbn.beans.BranchCountBean;
import com.manpower.directalent.reporting.hbn.shared.HibernateUtilities;



public class AdvertReportServiceImpl implements AdvertReportService{
	
	public static String ADTYPE_LIVE = "live";
	public static String ADTYPE_EXP = "expired";
	
	@SuppressWarnings("unchecked")
	public List getAdsLastSevenDays(){
		List adverts = new ArrayList();
		adverts = HibernateUtilities.currentSession().createSQLQuery("select s.sitename as sitename,s.sitecode as sitecode," +
				"t.site_id as site_id, count(*) as count from dt_advertisements t, " +
				"dt_sites s where to_char(t.created_on,'ww') = to_char(sysdate,'ww') " +
				"and to_char(t.created_on,'yyyy') = to_char(sysdate,'yyyy') " +
				"and t.site_id = s.id and t.site_id > 1000 group by s.sitename,s.sitecode," +
				"t.site_id order by count desc").addEntity(AdvertisementCountBean.class).list();
		HibernateUtilities.currentSession().close();
		
		return adverts;
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<Object> getAdvertsByBranch(String siteid) {
		List branchList = new ArrayList();
		
		branchList = HibernateUtilities.currentSession().createSQLQuery("SELECT s.ID site_id, s.sitecode sitecode,b.branchname branchname," +
				"count(*) COUNT FROM dt_advertisements t, dt_branches b, dt_advertisementcontacts a, dt_sites s " +
				"WHERE a.advertcontactid = t.advertcontactid(+) and b.id = a.branch_id and s.id = "+siteid+" and s.id = t.site_id " +
				"and t.expirationdate > sysdate GROUP BY s.id,b.branchname,s.sitecode ORDER BY s.id,b.branchname ").addEntity(BranchCountBean.class).list();
		
		
		return branchList;
	}



	@SuppressWarnings("unchecked")
	public List<Object> getCountrySelectorWithCount() {
		List countrySelector = new ArrayList();
		countrySelector = HibernateUtilities.currentSession().createSQLQuery("select t.id as site_id,t.sitecode as sitecode,t.sitename as sitename," +
				"NVL(a.adverts,0) as count from dt_sites t, " +
				"(select site_id, count(*) as adverts from dt_advertisements " +
				"where trunc(expirationdate) >= trunc(sysdate) group by site_id) a where t.id = a.site_id(+) " +
				"and t.id > 100 order by t.sitename").addEntity(AdvertisementCountBean.class).list();
		HibernateUtilities.currentSession().close();
		return countrySelector;
	}



	@SuppressWarnings("unchecked")
	public List<Object> getAdvertReport(){
		
		List expAdverts = new ArrayList();
		expAdverts = HibernateUtilities.currentSession().createSQLQuery("select t.site_id as site_id,s.sitecode as sitecode,s.sitename as sitename, count(*) as count from dt_advertisements t," +
				" dt_sites s where trunc(t.expirationdate) < trunc(sysdate) " +
				"and t.site_id = s.id and t.site_id > 1000 group by s.sitename,s.sitecode," +
				"t.site_id order by s.sitename").addEntity(AdvertisementCountBean.class).list();
		HibernateUtilities.currentSession().clear();

		List liveAdverts = new ArrayList();
		liveAdverts = HibernateUtilities.currentSession().createSQLQuery("select s.sitename as sitename,s.sitecode as sitecode," +
				"t.site_id as site_id, count(*)as count from dt_advertisements t, " +
				"dt_sites s where trunc(t.expirationdate) >= trunc(sysdate) " +
				"and t.site_id = s.id and t.site_id > 1000 " +
				"group by s.sitename,s.sitecode,t.site_id order by s.sitename").addEntity(AdvertisementCountBean.class).list();
		

		HibernateUtilities.closeSession();
		 
		/*
		 * Map the live Adverts
		 * 
		 */
		HashMap advertMap = mapAdverts(new HashMap(),expAdverts,ADTYPE_EXP);
		advertMap = mapAdverts(advertMap,liveAdverts,ADTYPE_LIVE);
		
		Set set = advertMap.keySet();
		Iterator i = set.iterator();
		List tempList = new ArrayList<Object>();
		
		while(i.hasNext()){
			AdvertisementCountBean acb =(AdvertisementCountBean)advertMap.get(i.next());
			tempList.add(acb);
		}
		
		return tempList;
	}
	
	@SuppressWarnings("unchecked")
	private HashMap mapAdverts(HashMap map,List advertList,String adType){
		
		for (int i = 0;i<advertList.size();i++){
			AdvertisementCountBean adCount = (AdvertisementCountBean)advertList.get(i);
			if (adType.equals(ADTYPE_LIVE)){
				if (map.get(Long.toString(adCount.getId()))!=null){
					AdvertisementCountBean tempCount = (AdvertisementCountBean)map.get(Long.toString(adCount.getId()));
					tempCount.setLiveCount(adCount.getCount());
					map.put(Long.toString(adCount.getId()), tempCount);
				}else{
					adCount.setLiveCount(adCount.getCount());
					adCount.setCount(0);
					map.put(Long.toString(adCount.getId()), adCount);
				}
			}else{
				map.put(Long.toString(adCount.getId()), adCount);
			}
		}
		
		return map;
		
	}

}
