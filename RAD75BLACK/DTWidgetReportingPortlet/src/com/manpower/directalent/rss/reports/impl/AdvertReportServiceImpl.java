package com.manpower.directalent.rss.reports.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.manpower.directalent.rss.hbn.beans.AdvertisementCountBean;
import com.manpower.directalent.rss.hbn.shared.HibernateUtilities;
import com.manpower.directalent.rss.reports.AdvertReportService;

public class AdvertReportServiceImpl implements AdvertReportService{
	
	public static String ADTYPE_LIVE = "live";
	public static String ADTYPE_EXP = "expired";
	
	public String getAdsLastSevenDays(){
		List adverts = new ArrayList();
		adverts = HibernateUtilities.currentSession().createSQLQuery("select s.sitename as sitename," +
				"t.site_id as site_id, count(*) as count from advertisements t, " +
				"sites s where to_char(t.created_on,'ww') = to_char(sysdate,'ww') " +
				"and to_char(t.created_on,'yyyy') = to_char(sysdate,'yyyy') " +
				"and t.site_id = s.id and t.site_id > 1000 group by s.sitename," +
				"t.site_id order by count desc").addEntity(AdvertisementCountBean.class).list();
		HibernateUtilities.currentSession().close();
		
		StringBuffer sb = new StringBuffer();
		sb.append("<table>");
		sb.append("<tr><td colspan=\"2\"style=\"background-color:#7ea190;font-family:Arial;font-weight:bold;font-size:x-small;color:#FFFFFF;\">");
		sb.append("Ads Posted in Last 7 Days");
		sb.append("</td></tr>");
		String style="norm";
		int total = 0;
		
		for (int i = 0;i<adverts.size();i++){
			AdvertisementCountBean adcount = (AdvertisementCountBean)adverts.get(i);
			sb.append("<tr>");
			sb.append("<td class=\""+style+"\">"+adcount.getSitename()+"</td>");
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
	
	public String getAdvertReport(){
		
		List expAdverts = new ArrayList();
		expAdverts = HibernateUtilities.currentSession().createSQLQuery("select t.site_id as site_id,s.sitename as sitename, count(*) as count from advertisements t," +
				" sites s where trunc(t.expirationdate) < trunc(sysdate) " +
				"and t.site_id = s.id and t.site_id > 1000 group by s.sitename," +
				"t.site_id order by s.sitename").addEntity(AdvertisementCountBean.class).list();
		HibernateUtilities.currentSession().clear();

		List liveAdverts = new ArrayList();
		liveAdverts = HibernateUtilities.currentSession().createSQLQuery("select s.sitename as sitename," +
				"t.site_id as site_id, count(*)as count from advertisements t, " +
				"sites s where trunc(t.expirationdate) >= trunc(sysdate) " +
				"and t.site_id = s.id and t.site_id > 1000 " +
				"group by s.sitename,t.site_id order by s.sitename").addEntity(AdvertisementCountBean.class).list();
		

		HibernateUtilities.closeSession();
		 
		/*
		 * Map the live Adverts
		 * 
		 */
		HashMap advertMap = mapAdverts(new HashMap(),expAdverts,ADTYPE_EXP);
		advertMap = mapAdverts(advertMap,liveAdverts,ADTYPE_LIVE);

		/*
		 * Generate Table
		 * 
		 */
		StringBuffer sb = new StringBuffer();
		
		sb.append("<table><tr>");
		sb.append("<td class=\"headerBar\">Advertisement Totals</td><td class=\"headerBar\">" +
				"Expired</td><td class=\"headerBar\">Live</td>");
		sb.append("</tr>");
		
		Set set = advertMap.keySet();
		Iterator i = set.iterator();
		
		String style="norm";
		while(i.hasNext()){

			AdvertisementCountBean adCount = (AdvertisementCountBean)advertMap.get(i.next());
			sb.append("<tr><td class=\""+style+"\">");
			sb.append(adCount.getSitename()); 
			sb.append("</td>");
			sb.append("<td class=\""+style+"\" align=\"right\" style=\"padding-right:10px;\">");
			sb.append(Long.toString(adCount.getCount()));
			sb.append("</td>");
			sb.append("<td class=\""+style+"\" align=\"right\" style=\"padding-right:10px;\">");
			sb.append(Long.toString(adCount.getLiveCount()));
			sb.append("</td>");
			sb.append("</tr>");
			if (style.equals("norm")){
				style="highlighted";
			}else{
				style="norm";
			}
		}
		sb.append("</table>");
		return sb.toString();
	}
	
	private HashMap mapAdverts(HashMap map,List advertList,String adType){
		
		for (int i = 0;i<advertList.size();i++){
			AdvertisementCountBean adCount = (AdvertisementCountBean)advertList.get(i);
//			System.out.println(adType+" -- "+adCount.getId()+" -- "+adCount.getCount());
			if (adType.equals(ADTYPE_LIVE)){
//				System.out.println(i +": LIVE - " + adCount.getCount());
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
//				System.out.println(i +": EXP "+adCount.getCount());
				map.put(Long.toString(adCount.getId()), adCount);
			}
		}
		
		return map;
		
	}

}
