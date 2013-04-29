package com.manpower.directalent.rss;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.manpower.directtalent.rss.ui.service.FeedServiceLocator;

/**
 * Servlet implementation class for Servlet: ReportServlet
 *
 */
 public class ReportServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2103522660002297857L;

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ReportServlet() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String report = request.getParameter("reportId");
		if (report.equals("candCounts")){
			out.println(FeedServiceLocator.getInstance().getCandidateReportService().getCandidateReport());
		}else if (report.equals("adCounts")){
			out.println(FeedServiceLocator.getInstance().getAdvertReportService().getAdvertReport());
		}else if (report.equals("adsThisWeek")){
			out.println(FeedServiceLocator.getInstance().getAdvertReportService().getAdsLastSevenDays());
		}else{
			String siteName = request.getParameter("siteName");
			out.println(FeedServiceLocator.getInstance().getAdvertByBranchReportService().getAdvertsByBranch(report,siteName));
		}

	
	}  	
	
	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}   	  	    
}