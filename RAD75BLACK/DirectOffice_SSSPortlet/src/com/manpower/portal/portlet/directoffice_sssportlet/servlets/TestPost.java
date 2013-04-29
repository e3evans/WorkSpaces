package com.manpower.portal.portlet.directoffice_sssportlet.servlets;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestPost
 */
public class TestPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestPost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contentType= "text/html;charset=UTF-8";
		response.setContentType(contentType);
		System.out.println("CHANGE IS IN3 GET.");
		
		for (Enumeration e=request.getParameterNames();e.hasMoreElements();){
			String temp = (String)e.nextElement();
			System.out.println("PARAM: "+temp+"------------>"+request.getParameter(temp));
			
		}
		// TODO Auto-generated method stub
		System.out.println("****TEST1****"+request.getParameter("test1"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contentType= "text/html;charset=UTF-8";
		response.setContentType(contentType);
		System.out.println("CHANGE IS IN2.");
		
		for (Enumeration e=request.getParameterNames();e.hasMoreElements();){
			String temp = (String)e.nextElement();
			System.out.println("PARAM: "+temp+"------------>"+request.getParameter(temp));
			
		}
		// TODO Auto-generated method stub
		System.out.println("****TEST1****"+request.getParameter("test1"));
	}

}
