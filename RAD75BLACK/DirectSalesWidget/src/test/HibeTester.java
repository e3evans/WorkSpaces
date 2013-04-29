package test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.manpower.widget.report.services.OpportunityReportService;

/**
 * Servlet implementation class HibeTester
 */
public class HibeTester extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HibeTester() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("TESTING:  "+request.getParameter("action"));
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
//		out.println("TOP TEN:  "+OpportunityReportService.getTopTenReportData()+"<hr>");
//		out.println("306090:  "+OpportunityReportService.get306090()+"<hr>"); 
		out.println("EST REV: "+OpportunityReportService.getEstimatedRevenuData());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("TESTING:  "+request.getParameter("action"));
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
		out.println("TOP TEN:  "+OpportunityReportService.getTopTenReportData()+"<hr>");
		out.println("306090:  "+OpportunityReportService.get306090());
	}

}
