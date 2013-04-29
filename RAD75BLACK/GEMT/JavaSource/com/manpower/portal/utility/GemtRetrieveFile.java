package com.manpower.portal.utility;


import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.manpower.portal.gemt.ui.beans.GemtSummaryReportFileUIBean;
import com.manpower.portal.gemt.ui.services.GEMTServiceLocator;
import com.manpower.portal.gemt.ui.services.GemtSummaryReportFileService;


/**
 * This class is used for downloading a CV and showing it to the candidate/recruiter.
 * 
 * @author Dimitar Bakardzhiev
 *
 */
public class GemtRetrieveFile extends HttpServlet implements Servlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8152512495691941914L;
	private static Logger log=Logger.getLogger(GemtRetrieveFile.class);
	
	/**
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public GemtRetrieveFile() {
		super();
	}

	/**
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest arg0, HttpServletResponse arg1)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		GemtSummaryReportFileService service =  GEMTServiceLocator.getInstance().getGemtSummaryReportFileService();

		long fileId = new Long((String)request.getParameter("fileId")).longValue();
		
		GemtSummaryReportFileUIBean file = service.findById(new Long(fileId));
		try {		
            response.setHeader("Content-Disposition","attachment; filename=\"" + file.getGemt_sum_file_name() + "\"");
            ServletOutputStream out = response.getOutputStream();
			out.write(file.getFileAsByteArray());
	    	out.flush();
	    	out.close();			
		} catch (IOException e) {
			log.error("Exception:",e);
		}
		
		
	}

	/**
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest arg0, HttpServletResponse arg1)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}