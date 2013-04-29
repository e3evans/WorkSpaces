package com.manpower.utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 */
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//COLS_DTCreateAccountResource.properties
		Locale locale = null;
		if (request.getParameter("locale")!=null){
			locale = new Locale(request.getParameter("locale"));
		}else{
			locale = new Locale("en");
		}
		
		
		ResourceBundle rb = ResourceBundle.getBundle(request.getParameter("CTRY")+"_COLS_DTCreateAccountResource",locale);
		response.getWriter().println("START DO GET!!");
		response.getWriter().println("<HR>");
		response.getWriter().println(rb.getString("first_flex_list_label"));
		response.getWriter().println("<HR>");
		response.getWriter().println("END DO GET!!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().println("DO POST!!");
	}

	
}
