package com.manpower.directalent.rss;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.manpower.directtalent.rss.ui.beans.RptResultsUIBean;
import com.manpower.directtalent.rss.ui.service.OracleReportServiceLocator;
import com.manpower.directtalent.rss.ui.service.RptResultsService;

/**
 * Servlet implementation class for Servlet: OracleReportServlet
 *
 */
 public class OracleReportServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6850542852170927008L;

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public OracleReportServlet() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("STARTING POINT...");
		RptResultsService rrs = (RptResultsService)OracleReportServiceLocator.getInstance().getRptResultsService();
		System.out.println(rrs.findAll().size());
		RptResultsUIBean file = rrs.findByID(new Long("1"));
		System.out.println(file.getRpt_xls_name());
		
		try {	
			response.reset();
			response.setContentType("application/msexcel");
            response.setHeader("Content-Disposition","attachment; filename=\"" + file.getRpt_proc_name() + ".xls\"");
            ServletOutputStream out = response.getOutputStream();
			out.write(file.getRpt_xls_name().getBytes());
	    	out.flush();
	    	out.close();			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}  	  	  	    
}