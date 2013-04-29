package com.manpower.directalent.reporting.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.manpower.directalent.reporting.dao.CandidateReportService;
import com.manpower.directalent.reporting.hbn.beans.CandidateCountBean;
import com.manpower.directalent.reporting.hbn.beans.CandidateCountPerDayBean;
import com.manpower.directalent.reporting.hbn.shared.HibernateUtilities;



public class CandidateReportServiceImpl implements CandidateReportService {
	
	@SuppressWarnings("unchecked")
	public List<Object> getCandidateReport(){
		
		List<Object> myList = new ArrayList<Object>();
		myList = HibernateUtilities.currentSession().createSQLQuery("select s.sitename as sitename ,s.sitecode as sitecode," +
				"t.site_id as site_id, count(*)as count from dt_candidates t," +
				" dt_sites s where t.site_id = s.id and t.status = 'A' and t.site_id > 1000 group by s.sitename,s.sitecode," +
				"t.site_id order by count desc").addEntity(CandidateCountBean.class).list();
		HibernateUtilities.closeSession();
		return myList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getCandidateReportList(){
		List<Object> myList = new ArrayList<Object>();
		myList = HibernateUtilities.currentSession().createSQLQuery("select s.sitename as sitename ," +
				"t.site_id as site_id, count(*)as count from dt_candidates t," +
				" dt_sites s where t.site_id = s.id and t.status = 'A' and t.site_id > 1000 group by s.sitename," +
				"t.site_id order by s.sitename").addEntity(CandidateCountBean.class).list();
		HibernateUtilities.closeSession();
		return myList;
	}
	
	@SuppressWarnings("unchecked")
	public Object [] getTopFiveSites(){
		List<Object> myList = new ArrayList<Object>();
		myList = HibernateUtilities.currentSession().createSQLQuery("select s.sitename as sitename ," +
				"t.site_id as site_id, count(*)as count from dt_candidates t," +
				" dt_sites s where t.site_id = s.id and t.status = 'A' and t.site_id > 1000 group by s.sitename," +
				"t.site_id order by count desc").addEntity(CandidateCountBean.class).list();
		HibernateUtilities.closeSession();
		List<CandidateCountBean> returnList = new ArrayList<CandidateCountBean>();
		CandidateCountBean other = new CandidateCountBean();
		other.setId("Other");
		other.setCount(0);
		for(int i =0;i<myList.size();i++){
			CandidateCountBean temp = (CandidateCountBean)myList.get(i);
			if (i<5){
				returnList.add(temp);
			}else{
				other.setCount(other.getCount()+temp.getCount());
			}
		}
		returnList.add(other);
		return returnList.toArray();
	}
	@SuppressWarnings("unchecked")
	public List<Object> getCandidatesByDay(String dayspast){
		List<Object> myList = new ArrayList<Object>();
		
		int numofDays = Integer.parseInt(dayspast);
		
		for(int i=0;i<numofDays;i++){
			int sysdatesubtract = i+1;
			List<Object> tempList = new ArrayList<Object>();
			tempList = HibernateUtilities.currentSession().createSQLQuery("select rownum site_id, s.sitename, s.sitecode, " +
					"NVL(c.HR00,0)+NVL(c.HR01,0)+NVL(c.HR02,0)+NVL(c.HR03,0)+NVL(c.HR04,0)+NVL(c.HR05,0)+ NVL(c.HR06,0)+" +
					"NVL(c.HR07,0)+NVL(c.HR08,0)+NVL(c.HR09,0)+NVL(c.HR10,0)+NVL(c.HR11,0)+ NVL(c.HR12,0)+NVL(c.HR13,0)+" +
					"NVL(c.HR14,0)+NVL(c.HR15,0)+NVL(c.HR16,0)+NVL(c.HR17,0)+ NVL(c.HR18,0)+NVL(c.HR19,0)+NVL(c.HR20,0)+NVL(c.HR21,0)" +
					"+NVL(c.HR22,0)+NVL(c.HR23,0) \"count\", trunc(sysdate-"+Integer.toString(sysdatesubtract)+") countdate from " +
							"dt_candidate_counts c, dt_sites s where c.site_id=s.id and c.reg_date = trunc(sysdate-"+Integer.toString(sysdatesubtract)+") " +
									"and flag='DT' order by sitename").addEntity(CandidateCountPerDayBean.class).list();
//			CandidateCountPerDayBean cb = (CandidateCountPerDayBean)tempList.get(0);
			HibernateUtilities.closeSession();
			myList.addAll(tempList);
		}
		
		return myList;
	}
	
	
	
}
