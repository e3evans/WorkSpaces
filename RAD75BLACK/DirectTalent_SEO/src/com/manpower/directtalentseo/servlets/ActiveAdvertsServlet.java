package com.manpower.directtalentseo.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.manpower.directtalentseo.hbn.beans.Vactiveadverts;
import com.manpower.directtalentseo.hbn.shared.dao.DAOFactory;

/**
 * Servlet implementation class for Servlet: ActiveAdvertsServlet
 *
 */
 public class ActiveAdvertsServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6805308875637886654L;

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ActiveAdvertsServlet() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List myList = DAOFactory.getDAOFactory().getVActiveAdvertsDAO().findAllbySiteId("NLMPNet");
//		List myList = DAOFactory.getDAOFactory().getVActiveAdvertsDAO().finaAllbySiteId("USCampus");
		
		
		//response.getWriter().println("TESTING!!");
		response.setContentType("text/html");
		ResourceBundle rb = ResourceBundle.getBundle("com.manpower.directtalentseo.config.settings");
		PrintWriter out = response.getWriter();
		
//		out.println("<a href=\"");
//			out.println(rb.getString("servlet_url")+"?site="+advert.getSitename()+"&JobId="+advert.getId());
//			out.println("\">");
//			out.println(advert.getId()+"---"+advert.getSitename()+"----"+advert.getPublicationdate().toString());
//			out.println("</a>");
		
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">");
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		//http://localhost:9080/DirectTalent_SEO/DisplayAdvert.jsp?adId=10141&site=NLMPNet
		for (int i=0;i<myList.size();i++){
			Vactiveadverts advert = (Vactiveadverts)myList.get(i);
			out.println("<url>");
			out.println("<loc>");
			out.println(rb.getString("servlet_url")+"?site="+advert.getSitename()+"&amp;adId="+advert.getId());
			out.println("</loc>");
			out.println("<lastmod>"+formatter.format(advert.getPublicationdate())+"</lastmod>");
			out.println("<changefreq>daily</changefreq>");
			out.println("<priority>0.5</priority>");
			out.println("</url>");
		}
		out.println("</urlset>");
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}   	  	    
}