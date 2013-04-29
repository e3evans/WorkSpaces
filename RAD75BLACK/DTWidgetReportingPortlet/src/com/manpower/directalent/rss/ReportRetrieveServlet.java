package com.manpower.directalent.rss;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.workplace.util.exception.ServiceException;
import com.manpower.directtalent.rss.ui.beans.ReportContentUIBean;
import com.manpower.directtalent.rss.ui.service.OracleReportServiceLocator;

public class ReportRetrieveServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet{

	private static final long serialVersionUID = -202587388765777449L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileId = request.getParameter("fileId").toString();
		try {
			Long id = Long.valueOf(fileId);
			ReportContentUIBean report = OracleReportServiceLocator.getInstance().getReportService().findReportContentByID(id);
			
			response.setHeader("Content-Disposition","attachment; filename=\"" + report.getReportName() + "\"");
            response.setContentType(report.getMimeType());
            
            ServletOutputStream out = response.getOutputStream();
            out.write(report.getReportContent().getBytes());
	    	out.flush();
	    	out.close();
		}
		catch (NumberFormatException exc) {
			exc.printStackTrace();
		}
		catch (ServiceException exc) {
			exc.printStackTrace();
		}
	}
}
