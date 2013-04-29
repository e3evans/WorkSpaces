/*
 * Created on Aug 30, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.gemt.ui.services;

import com.manpower.portal.gemt.ui.services.impl.GemtDirectReportUIServiceImpl;
import com.manpower.portal.gemt.ui.services.impl.GemtManagersUIServiceImpl;
import com.manpower.portal.gemt.ui.services.impl.GemtNotesUIServiceImpl;
import com.manpower.portal.gemt.ui.services.impl.GemtSummaryReportFileLiteServiceImpl;
import com.manpower.portal.gemt.ui.services.impl.GemtSummaryReportFileServiceImpl;
import com.manpower.portal.gemt.ui.services.impl.GemtSummaryReportUIServiceImpl;



/**
 * @author Eric Evans
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GEMTServiceLocator {

	private static GEMTServiceLocator serviceLocator;
	private GemtSummaryReportService gemtSummaryReportService;
	private GemtNotesService gemtNotesService;
	private GemtDirectReportService gemtDirectReportService;
	private GemtManagersService gemtManagerService;
	private GemtSummaryReportFileService gemtSummaryReportFileService;
	private GemtSummaryReportFileLiteService gemtSummaryReportFileLiteService;

	public static GEMTServiceLocator getInstance(){
		
		if (serviceLocator == null){
			serviceLocator = new GEMTServiceLocator();
		}
		return serviceLocator;
	}
	
	private GEMTServiceLocator(){
		gemtSummaryReportService = (GemtSummaryReportService)new GemtSummaryReportUIServiceImpl();
		gemtNotesService = (GemtNotesService)new GemtNotesUIServiceImpl();
		gemtDirectReportService = (GemtDirectReportService)new GemtDirectReportUIServiceImpl();
		gemtManagerService = (GemtManagersService)new GemtManagersUIServiceImpl();
		gemtSummaryReportFileService=new GemtSummaryReportFileServiceImpl();
		gemtSummaryReportFileLiteService=new GemtSummaryReportFileLiteServiceImpl();
	}
	public GemtSummaryReportService getSummaryReportService(){
		return gemtSummaryReportService;
	}

	public GemtNotesService getNotesService(){
		return gemtNotesService;
	}
	
	public GemtDirectReportService getDirectReportService(){
		return gemtDirectReportService;
	}
	public GemtManagersService getManagersService(){
		return gemtManagerService;
	}
	
	public GemtSummaryReportFileService getGemtSummaryReportFileService()
	{
		return gemtSummaryReportFileService; 
	}
	
	public GemtSummaryReportFileLiteService getGemtSummaryReportFileLiteService()
	{
		return gemtSummaryReportFileLiteService; 
	}	
}
