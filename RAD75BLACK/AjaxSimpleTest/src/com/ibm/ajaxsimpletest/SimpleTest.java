package com.ibm.ajaxsimpletest;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SimpleTest
 */
public class SimpleTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SimpleTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contentType= "text/html;charset=UTF-8";
		response.setContentType(contentType);
		System.out.println("GETTING");
		System.out.println("DISPLAY PARAMS");
		
		StringBuffer sb = new StringBuffer();
		for (Enumeration e = request.getParameterNames();e.hasMoreElements();){
			String paramName = (String)e.nextElement();
			sb.append("PARAM:"+paramName+":"+request.getParameter(paramName));
			
		}
		
		response.getWriter().print(sb.toString());
			
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String contentType= "text/html;charset=UTF-8";
//		response.setContentType(contentType);
		System.out.println("POSTING");
		System.out.println("DISPLAY PARAMS");
		StringBuffer sb = new StringBuffer();
		for (Enumeration e = request.getParameterNames();e.hasMoreElements();){
			String paramName = (String)e.nextElement();
			sb.append("PARAM:"+paramName+":"+request.getParameter(paramName));
			
		}
		
		response.getWriter().print(sb.toString());
	}

}
