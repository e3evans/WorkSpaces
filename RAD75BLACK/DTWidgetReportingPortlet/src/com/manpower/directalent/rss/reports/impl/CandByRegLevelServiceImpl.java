package com.manpower.directalent.rss.reports.impl;

import java.util.ArrayList;
import java.util.List;

import com.manpower.directalent.rss.hbn.beans.CandByRegLevelBean;
import com.manpower.directalent.rss.hbn.shared.HibernateUtilities;
import com.manpower.directalent.rss.reports.CandByRegLevelService;

public class CandByRegLevelServiceImpl implements CandByRegLevelService{
	
	public Object[] getCandidatesBySite(String siteName){
		List myList = new ArrayList();
		myList = HibernateUtilities.currentSession().createSQLQuery("select rownum,sitename,reg_level," +
				"count from (select s.sitename as sitename, t.reg_level as reg_level, " +
				"count(*)as count from candidates t, sites s where s.sitename=\'"+siteName+"\' " +
				"and t.status = 'A' and " +
				"s.id = t.site_id group by s.sitename, " +
				"t.reg_level order by t.reg_level)").addEntity(CandByRegLevelBean.class).list();
		HibernateUtilities.closeSession();
		
		return myList.toArray();
	}

}
