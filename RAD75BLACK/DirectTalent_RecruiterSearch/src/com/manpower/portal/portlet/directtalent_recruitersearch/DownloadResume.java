package com.manpower.portal.portlet.directtalent_recruitersearch;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.manpower.directtalentrecruitersearch.hbn.shared.dao.DAOFactory;
import com.manpower.hbn.beans.Resume;
import com.manpower.portal.portlet.directtalent_recruitersearch.utility.StringUtils;

/**
 * Servlet implementation class for Servlet: DownloadResume
 *
 */
 public class DownloadResume extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3924128584731897424L;

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public DownloadResume() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cvid=request.getParameter("cvid");
		String mimetype=request.getParameter("mimetype");
		String cvname=request.getParameter("cvname");
		String candType=request.getParameter("candType");
		Resume resume = (Resume)DAOFactory.getDAOFactory().getResumeDAO().findByIDAndType(new Long(cvid), candType);
		ResourceBundle rb = ResourceBundle.getBundle("com.manpower.servlets.downloads.extensions");
		String fileext="";
		try{
			fileext=rb.getString(StringUtils.replace(mimetype, "/", "_"));
		} catch (Exception e){
			//DO NOTHING IF YOU CAN'T FIND THE EXTENSION....
		}
		try {	
			if (!fileext.equals(""))fileext="."+fileext;
			response.setContentType(mimetype);
            response.setHeader("Content-Disposition","attachment; filename=\"" + cvname+fileext + "\"");
            ServletOutputStream out = response.getOutputStream();
			out.write(resume.getFileAsByteArray());
	    	out.flush();
	    	out.close();			
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}  	
	
	
	
	  	  	    
}