package com.manpower.directalent.rss.reports.impl;

import java.util.ArrayList;
import java.util.List;

import com.manpower.directalent.rss.hbn.beans.SitesBean;
import com.manpower.directalent.rss.hbn.shared.HibernateUtilities;
import com.manpower.directalent.rss.reports.SiteSelectorService;

public class SiteSelectorServiceImpl implements SiteSelectorService {

	//select t.id as id,t.sitecode as sitecode,t.sitename as sitename from sites t order by t.sitename
		
	public String getSiteSelector(String namespace) {
		
		List myList = new ArrayList();
		myList = HibernateUtilities.currentSession().createSQLQuery("select t.id as site_id," +
				"t.sitecode as sitecode," +
				"t.sitename as sitename from sites t where t.id > 100 " +
				"order by t.sitename").addEntity(SitesBean.class).list();
		HibernateUtilities.closeSession();
		StringBuffer sb=new StringBuffer();
		sb.append("<div id=\"siteList\" style=\"text-align:right;position" +
				":absolute;top:0px;left:-500px;z-index:100;background-color:#FFFFFF;" +
				"border:1px solid #b9b9b9;width:225px;\">");
		if (myList.size()>0){
			sb.append("<table width=\"100%\">");
			for (int i=0;i<myList.size();i++){
				SitesBean site = (SitesBean)myList.get(i);
				sb.append("<tr>");
				sb.append("<td class=\"menuOff\" onmouseover=\"highLight(this);\"" +
						"onmouseout=\"highLight(this);\" id=\""+site.getSite_id()+"\" onclick=\""+namespace+"showBranchReport(this,'"+site.getSitename()+"');\">");
				sb.append(site.getSitename());
				sb.append("</td>");
				sb.append("</tr>");
			}
			sb.append("</table>");
		}else{
			sb.append("NO SITES AVAILABLE.");
		}
		sb.append("</div>");

		
		
		return sb.toString();
	}
	
	public Object [] getSites(){
		List myList = new ArrayList();
		myList = HibernateUtilities.currentSession().createSQLQuery("select t.id as site_id," +
				"t.sitecode as sitecode," +
				"t.sitename as sitename from sites t where t.id > 100 " +
				"order by t.sitename").addEntity(SitesBean.class).list();
		HibernateUtilities.closeSession();
		return myList.toArray();
	}
	
	public Object getDefaultItem(){
		List myList = new ArrayList();
		myList = HibernateUtilities.currentSession().createSQLQuery("select t.id as site_id," +
				"t.sitecode as sitecode," +
				"t.sitename as sitename from sites t where t.id > 100 " +
				"order by t.sitename").addEntity(SitesBean.class).list();
		HibernateUtilities.closeSession();
		Object temp = null;
		if (myList.size()>0){
			temp = myList.get(0);
		}
		return temp;
	}
}
