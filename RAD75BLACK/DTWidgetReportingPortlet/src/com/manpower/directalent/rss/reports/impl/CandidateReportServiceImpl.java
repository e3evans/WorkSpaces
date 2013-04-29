package com.manpower.directalent.rss.reports.impl;

import java.util.ArrayList;
import java.util.List;

import com.manpower.directalent.rss.hbn.beans.CandidateCountBean;
import com.manpower.directalent.rss.hbn.shared.HibernateUtilities;
import com.manpower.directalent.rss.reports.CandidateReportService;

public class CandidateReportServiceImpl implements CandidateReportService {
	
	public String getCandidateReport(){
		
		List myList = new ArrayList();
		myList = HibernateUtilities.currentSession().createSQLQuery("select s.sitename as sitename ," +
				"t.site_id as site_id, count(*)as count from candidates t," +
				" sites s where t.site_id = s.id and t.status = 'A' and t.site_id > 1000 group by s.sitename," +
				"t.site_id order by count desc").addEntity(CandidateCountBean.class).list();
		HibernateUtilities.closeSession();
		
		StringBuffer sb = new StringBuffer();
		sb.append("<table>");
		sb.append("<tr><td colspan=\"2\"style=\"background-color:#7ea190;font-family:Arial;font-weight:bold;font-size:x-small;color:#FFFFFF;\">");
		sb.append("Candidate Count by Site");
		sb.append("</td></tr>");
		String style="norm";
		int total = 0;
		for (int i = 0;i<myList.size();i++){
			CandidateCountBean ccb = (CandidateCountBean)myList.get(i);
			sb.append("<tr>");
			sb.append("<td class=\""+style+"\">"+ccb.getId()+"</td>");
			sb.append("<td class=\""+style+"\" align=\"right\">"+ccb.getCount()+"</td>");
			sb.append("</tr>");
			if (style.equals("norm")){
				style="highlighted";
			}else{
				style="norm";
			}
			total+=Integer.parseInt(Long.toString(ccb.getCount()));
		}
		
		sb.append("<td class=\""+style+"\" align=\"right\"><b>Overall Total:</b></td>");
		sb.append("<td class=\""+style+"\" align=\"right\"><b>"+Integer.toString(total)+"</b></td>");
		
		sb.append("</table>");
		return sb.toString();
	}
	
	public List getCandidateReportList(){
		List myList = new ArrayList();
		myList = HibernateUtilities.currentSession().createSQLQuery("select s.sitename as sitename ," +
				"t.site_id as site_id, count(*)as count from candidates t," +
				" sites s where t.site_id = s.id and t.status = 'A' and t.site_id > 1000 group by s.sitename," +
				"t.site_id order by s.sitename").addEntity(CandidateCountBean.class).list();
		HibernateUtilities.closeSession();
		return myList;
	}
	
	public Object [] getTopFiveSites(){
		List myList = new ArrayList();
		myList = HibernateUtilities.currentSession().createSQLQuery("select s.sitename as sitename ," +
				"t.site_id as site_id, count(*)as count from candidates t," +
				" sites s where t.site_id = s.id and t.status = 'A' and t.site_id > 1000 group by s.sitename," +
				"t.site_id order by count desc").addEntity(CandidateCountBean.class).list();
		HibernateUtilities.closeSession();
		List returnList = new ArrayList();
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
	
}
