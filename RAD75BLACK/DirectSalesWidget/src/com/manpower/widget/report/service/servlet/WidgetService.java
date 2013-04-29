package com.manpower.widget.report.service.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.manpower.widget.report.services.OpportunityReportService;

/**
 * Servlet implementation class WidgetService
 */
public class WidgetService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WidgetService() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = "";
		if (request.getParameter("action")!=null){
			action = request.getParameter("action");
		}
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		if (action.equals("getRevenue")){
			out.println(OpportunityReportService.getEstimatedRevenuData());
		}else if (action.equals("getCount")){
			out.println(OpportunityReportService.getOpportunityCount());
		}else if (action.equals("getTopTen")){
			out.println(OpportunityReportService.getTopTenReportData());
		}else if (action.equals("306090")){
			out.println(OpportunityReportService.get306090());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = "";
		if (request.getParameter("action")!=null){
			action = request.getParameter("action");
		}
		System.out.println("!!!!!!  This Serlvet is being hit Action is: " + action + " !!!!!!!!!!");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		if (action.equals("getRevenue")){
			out.println(OpportunityReportService.getEstimatedRevenuData());
		}else if (action.equals("getCount")){
			out.println(OpportunityReportService.getOpportunityCount());
		}else if (action.equals("getTopTen")){
			out.println(OpportunityReportService.getTopTenReportData());
		}else if (action.equals("306090")){
			out.println(OpportunityReportService.get306090());
		}
	}

}
