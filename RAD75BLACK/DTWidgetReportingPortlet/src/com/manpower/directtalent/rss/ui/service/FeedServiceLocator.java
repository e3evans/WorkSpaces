package com.manpower.directtalent.rss.ui.service;

import com.manpower.directalent.rss.reports.AdvertReportService;
import com.manpower.directalent.rss.reports.AdvertsByBranchReportService;
import com.manpower.directalent.rss.reports.CandByRegLevelService;
import com.manpower.directalent.rss.reports.CandidateReportService;
import com.manpower.directalent.rss.reports.SiteSelectorService;
import com.manpower.directalent.rss.reports.impl.AdvertReportServiceImpl;
import com.manpower.directalent.rss.reports.impl.AdvertsByBranchReportServiceImpl;
import com.manpower.directalent.rss.reports.impl.CandByRegLevelServiceImpl;
import com.manpower.directalent.rss.reports.impl.CandidateReportServiceImpl;
import com.manpower.directalent.rss.reports.impl.SiteSelectorServiceImpl;
import com.manpower.directtalent.rss.ui.service.impl.VAdvertisementServiceImpl;

public class FeedServiceLocator {
	
	private static FeedServiceLocator servicelocator;
	private VAdvertisementService vAdvertismentService;
	private AdvertReportService advertReportService;
	private CandidateReportService candidateReportService;
	private SiteSelectorService siteSelectorService;
	private AdvertsByBranchReportService advertByBranchReportService;
	private CandByRegLevelService candidateByRegLevelService;
	
	public static FeedServiceLocator getInstance(){
		
		if (servicelocator==null){
			servicelocator = new FeedServiceLocator();
		}
		return servicelocator;
	}
	
	private FeedServiceLocator(){
		vAdvertismentService = (VAdvertisementService)new VAdvertisementServiceImpl();
		advertReportService = (AdvertReportService)new AdvertReportServiceImpl();
		candidateReportService =(CandidateReportService)new CandidateReportServiceImpl();
		siteSelectorService = (SiteSelectorService)new SiteSelectorServiceImpl();
		advertByBranchReportService = (AdvertsByBranchReportService)new AdvertsByBranchReportServiceImpl();
		candidateByRegLevelService=(CandByRegLevelService)new CandByRegLevelServiceImpl();
	}
	
	public CandByRegLevelService getCandidateByRegLevelService() {
		return candidateByRegLevelService;
	}

	public VAdvertisementService getVAdvertismentService(){
		return vAdvertismentService;
	}

	public AdvertReportService getAdvertReportService() {
		return advertReportService;
	}

	public CandidateReportService getCandidateReportService() {
		return candidateReportService;
	}

	public SiteSelectorService getSiteSelectorService() {
		return siteSelectorService;
	}

	public AdvertsByBranchReportService getAdvertByBranchReportService() {
		return advertByBranchReportService;
	}
	
	

}
