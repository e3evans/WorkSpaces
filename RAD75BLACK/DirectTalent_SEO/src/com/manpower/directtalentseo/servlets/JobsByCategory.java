package com.manpower.directtalentseo.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.manpower.directtalentseo.hbn.shared.dao.DAOFactory;

/**
 * Servlet implementation class JobsByCategory
 */
public class JobsByCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JobsByCategory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		List testList = DAOFactory.getDAOFactory().getVAdsIndSecDAO().findUniqueCategoryList("USCampus", "en");
		
		System.out.println(testList.size());
		
		response.getWriter().println("TESTING 1 - 2 3 <hr>");
		response.getWriter().println(request.getPathInfo());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
