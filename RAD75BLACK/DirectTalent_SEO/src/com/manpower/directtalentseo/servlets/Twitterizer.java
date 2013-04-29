package com.manpower.directtalentseo.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.manpower.directtalentseo.utility.TinyURLUtils;

import winterwell.jtwitter.Twitter;

/**
 * Servlet implementation class Twitterizer
 */
public class Twitterizer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Twitterizer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		 * Twitter Test...
		 */
//		Twitter twitter = new Twitter("mpttest","manpowertest1");
//		
//		for (int i=0;i<150;i++){
//			System.out.println("STATUS#  "+i);
//			twitter.updateStatus("Status# "+i);
//		}
//	
//		System.out.println("EXITING!!");
		
		/*
		 * Tiny URL Test...
		 * http://candidate.manpower.com/wps/PA_DirectTalentJobApp/ViewJobAdvertisement?site=USCampus&JobId=441531
		 */
		String temp = TinyURLUtils.getTinyUrl("http://candidate.manpower.com/wps/PA_DirectTalentJobApp/ViewJobAdvertisement?site=USCampus&JobId=441531");
		
		response.getWriter().println(temp);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
