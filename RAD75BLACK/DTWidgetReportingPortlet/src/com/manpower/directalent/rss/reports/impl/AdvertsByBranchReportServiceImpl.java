package com.manpower.directalent.rss.reports.impl;

import java.util.ArrayList;
import java.util.List;

import com.manpower.directalent.rss.hbn.beans.AdsByBranchBean;
import com.manpower.directalent.rss.hbn.shared.HibernateUtilities;
import com.manpower.directalent.rss.reports.AdvertsByBranchReportService;

public class AdvertsByBranchReportServiceImpl implements AdvertsByBranchReportService {

	public String getAdvertsByBranch(String siteId,String siteName){
//		System.out.println("TESTING*"+siteName+"**");
		List advertsByBranch = new ArrayList();
		String sqlQuery="select branchname as branchname,site_id as site_id,sitecode as sitecode," +
		"count as count from v_adv_in_lastweek t " +
		"where t.SITE_ID="+siteId+" order by branchname";
		advertsByBranch = HibernateUtilities.currentSession().
			createSQLQuery(sqlQuery).addEntity(AdsByBranchBean.class).list();
		
		HibernateUtilities.closeSession();
		
		StringBuffer sb = new StringBuffer();
		sb.append("<table>");
		sb.append("<tr><td colspan=\"2\"style=\"background-color:#7ea190;font-family:Arial;font-weight:bold;font-size:x-small;color:#FFFFFF;\">");
		sb.append(siteName+"<br>Last 7 days Postings by branch");
		sb.append("</td></tr>");
		String style="norm";
		int total = 0;
		
		for (int i = 0;i<advertsByBranch.size();i++){

			AdsByBranchBean adcount = (AdsByBranchBean)advertsByBranch.get(i);
			
			sb.append("<tr>");
			sb.append("<td class=\""+style+"\">"+adcount.getId()+"</td>");
			sb.append("<td class=\""+style+"\" align=\"right\">"+adcount.getCount()+"</td>");
			sb.append("</tr>");
			if (style.equals("norm")){
				style="highlighted";
			}else{
				style="norm";
			}
			total+=Integer.parseInt(Long.toString(adcount.getCount()));
		}
		
		sb.append("<td class=\""+style+"\" align=\"right\"><b>Overall Total:</b></td>");
		sb.append("<td class=\""+style+"\" align=\"right\"><b>"+Integer.toString(total)+"</b></td>");
		
		sb.append("</table>");
		
		
		return sb.toString();
	}
	
}
