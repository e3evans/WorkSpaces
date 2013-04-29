package com.manpower.lucene.globalresumesearch.services;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.manpower.hbn.beans.SearchCandidate;
import com.manpower.hbn.shared.dao.DAOFactory;

/**
 * Servlet implementation class BlackOpsDownloadService
 */
public class BlackOpsDownloadService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlackOpsDownloadService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("resumeid")!=null){
			Long resumeId = new Long(request.getParameter("resumeid"));
			SearchCandidate sc = (SearchCandidate) DAOFactory.getDAOFactory().getCandidateResumeInfoDAO().getCandidateResume(resumeId);
			response.setContentType(sc.getMimetype());
	        response.setHeader("Content-Disposition","attachment; filename=\"" + sc.getResumename() + "\"");
	        ServletOutputStream out = response.getOutputStream();
			out.write(sc.getFileAsByteArray());
	    	out.flush();
	    	out.close();			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("resumeid")!=null){
			Long resumeId = new Long(request.getParameter("resumeid"));
			SearchCandidate sc = (SearchCandidate) DAOFactory.getDAOFactory().getCandidateResumeInfoDAO().getCandidateResume(resumeId);
			response.setContentType(sc.getMimetype());
	        response.setHeader("Content-Disposition","attachment; filename=\"" + sc.getResumename() + "\"");
	        ServletOutputStream out = response.getOutputStream();
			out.write(sc.getFileAsByteArray());
	    	out.flush();
	    	out.close();			
		}
	}
	
	

}
