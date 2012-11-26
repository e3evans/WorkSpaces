package com.ibm.standardlifefeeds;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.naming.NamingException;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.PortletResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import com.ibm.portal.portlet.service.PortletServiceHome;
import com.ibm.portal.portlet.service.PortletServiceUnavailableException;
import com.ibm.portal.portlet.service.contentaccess.ContentAccessService;

/**
 * A sample portlet
 */
public class StandardLifeFeedsPortlet extends javax.portlet.GenericPortlet {

	public static String PREF_LANG = "language";
	public static String PREF_FRENCH = "french";
	public static String PREF_ENGLISH = "english";
	public static String PREF_FEEDURL = "feedURL";
	public static String EDIT_SUBMIT = "SLPEditSubmit";
	public static String DISP_RESPONSEDATA = "SLPResponeData";
	
	private PortletServiceHome casHome = null;
	/**
	 * @see javax.portlet.Portlet#init()
	 */
	public void init() throws PortletException{
		  
	  try {
			  javax.naming.Context ctx = new javax.naming.InitialContext();
			  Object home = ctx.lookup("portletservice/com.ibm.portal.portlet.service.contentaccess.ContentAccessService");

		      if (home != null) casHome = (PortletServiceHome) home;

		  } catch(javax.naming.NameNotFoundException ex) {
			  getPortletConfig().getPortletContext().log("Content Access Service is not available");
		  } catch (NamingException e) {
			e.printStackTrace();
		}
	}

	protected InputStream getHTTPInputStream(String urlS, PortletRequest request, PortletResponse response) throws IOException, PortletServiceUnavailableException {

		getPortletConfig().getPortletContext().log("getHTTPInputStream: " + urlS);
		
		if (casHome != null){
			ContentAccessService contentAccessservice =   (ContentAccessService) casHome.getPortletService(ContentAccessService.class);
			return contentAccessservice.getInputStream( urlS, request, response );
		} else {
			return (new URL(urlS).openStream());
		}
		
	}
	
	
	/**
	 * Serve up the <code>view</code> mode.
	 * 
	 * @see javax.portlet.GenericPortlet#doView(javax.portlet.RenderRequest, javax.portlet.RenderResponse)
	 */
	public void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		// Set the MIME type for the render response
		response.setContentType(request.getResponseContentType());
		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher("/_StandardLifeFeeds/jsp/html/StandardLifeFeedsPortletView.jsp");
		rd.include(request,response);
	}

	

	/**
	 * Process an action request.
	 * 
	 * @see javax.portlet.Portlet#processAction(javax.portlet.ActionRequest, javax.portlet.ActionResponse)
	 */
	public void processAction(ActionRequest request, ActionResponse response) throws PortletException, java.io.IOException {
		
	}

		
	@Override
	public void serveResource(ResourceRequest request, ResourceResponse response)
			throws PortletException, IOException {
		
		response.setContentType(request.getResponseContentType());
		PortletPreferences prefs = request.getPreferences();
		BufferedReader in;				
		String responseData = "";
		try {
			in = new BufferedReader(new InputStreamReader(getHTTPInputStream(prefs.getValue(PREF_FEEDURL, "")+prefs.getValue(PREF_LANG, "en-CA"), request, response), "utf-8") );						 
			String inputLine;					
			while ((inputLine = in.readLine()) != null) {
				  responseData += inputLine;
			}
			in.close();			
		} catch (IOException e) {
			e.printStackTrace();
			/*
			 * NEED TO GET THEIR IMAGE!
			 */
			responseData = "<img src='/images/indexfeed_err.jpg' />";
//			out.print("<img src='/images/indexfeed_err.jpg' />");
		}
		
		request.setAttribute(DISP_RESPONSEDATA,responseData);
		
		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher("/_StandardLifeFeeds/jsp/html/StandardLifeFeedsFrame.jsp");
		rd.include(request,response);
	}

	/**
	 * Override doDispatch method for handling custom portlet modes.
	 * 
	 * @see javax.portlet.GenericPortlet#doDispatch(javax.portlet.RenderRequest, javax.portlet.RenderResponse)
	 */
	protected void doDispatch(RenderRequest request, RenderResponse response) throws PortletException, IOException {
	
		super.doDispatch(request, response);
	}

}
