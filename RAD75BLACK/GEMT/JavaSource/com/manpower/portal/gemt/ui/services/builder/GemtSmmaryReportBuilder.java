package com.manpower.portal.gemt.ui.services.builder;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.manpower.portal.gemt.hbn.beans.GemtSummaryReportHbnBean;
import com.manpower.portal.gemt.ui.beans.GemtSummaryReportUIBean;

public class GemtSmmaryReportBuilder 
{
	private static Logger log=Logger.getLogger(GemtSmmaryReportBuilder.class);
	
	public static void populateGemtSummaryReportHbnBean(GemtSummaryReportUIBean gsrb, GemtSummaryReportHbnBean gsrhb) {
		try {
			BeanUtils.copyProperties(gsrhb,gsrb);
			
		} catch (Exception e) {
			log.error("Could not populate GemtSummaryReportHbnBean " + e.toString(), e);
		}
	}
	
	/**
	 * Create AdvertisementBean object using Advertisement object's data
	 * @param gsrhb
	 * @return AdvertisementBean object
	 * @throws ServiceException
	 */
	public static GemtSummaryReportUIBean createAdvertisementBean(GemtSummaryReportHbnBean gsrhb){
		GemtSummaryReportUIBean gsrb = new GemtSummaryReportUIBean();
		try {
			BeanUtils.copyProperties(gsrb, gsrhb);		

		} catch (Exception e) {
			log.error("Could not build GemtSummaryReportUIBean " + e.toString(), e);
		}
		
		return gsrb; 
	}
	
	public static GemtSummaryReportUIBean clone(GemtSummaryReportUIBean gsrb)
	{
		GemtSummaryReportUIBean cloned=new GemtSummaryReportUIBean();
		
		try {
			BeanUtils.copyProperties(cloned,gsrb);
			
		} catch (Exception e) {
			log.error("Could not clone GemtSummaryReportBean " + e.toString(), e);
		}		
		
		return cloned;
	}
}
