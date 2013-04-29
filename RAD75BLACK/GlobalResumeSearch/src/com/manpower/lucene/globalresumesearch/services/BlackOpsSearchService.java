package com.manpower.lucene.globalresumesearch.services;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.queryParser.ParseException;

import com.manpower.lucene.globalresumesearch.crawler.IndexService;

/**
 * Servlet implementation class BlackOpsSearchService
 */
public class BlackOpsSearchService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlackOpsSearchService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("TESTING GET...");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub2
//		System.out.println(request.getParameter("searchquery"));
//		System.out.println(request.getParameter("pagenum"));
		String queryString = request.getParameter("searchquery");
		String[] searchFields = {"resumetext","resumeid"};
		int pageNum = Integer.parseInt(request.getParameter("pagenum"));
		int maxRows = Integer.parseInt(request.getParameter("maxrows"));
		try {
			response.getWriter().println(IndexService.getInstance().queryDocuments(queryString, searchFields, pageNum,maxRows));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().println("");
		}
		
		
	}

}
