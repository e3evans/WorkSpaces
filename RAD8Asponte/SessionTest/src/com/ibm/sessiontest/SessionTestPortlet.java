package com.ibm.sessiontest;

import java.io.*;
import java.util.*;
import javax.portlet.*;

/**
 * A sample portlet based on GenericPortlet
 */
public class SessionTestPortlet extends GenericPortlet {

	public static final String JSP_FOLDER    = "/_SessionTest/jsp/";    // JSP folder name

	public static final String VIEW_JSP      = "SessionTestPortletView";         // JSP file name to be rendered on the view mode
	public static final String EDIT_JSP      = "SessionTestPortletEdit";         // JSP file name to be rendered on the edit mode
	
	public static final String CONFIG_JSP    = "SessionTestPortletConfig";       // JSP file name to be rendered on the configure mode
	public static final String SESSION_BEAN  = "SessionTestPortletSessionBean";  // Bean name for the portlet session
	public static final String FORM_SUBMIT   = "SessionTestPortletFormSubmit";   // Action name for submit form
	public static final String FORM_TEXT     = "SessionTestPortletFormText";     // Parameter name for the text input
   
	public static final String EDIT_SUBMIT   = "SessionTestPortletEditSubmit";   // Action name for submit form
	public static final String EDIT_TEXT     = "SessionTestPortletEditText";     // Parameter name for the text input
	public static final String EDIT_KEY      = ".SessionTestPortletEditKey";     // Key for the portlet preferences
	public static final String CONFIG_SUBMIT = "SessionTestPortletConfigSubmit"; // Action name for submit form
	public static final String CONFIG_TEXT   = "SessionTestPortletConfigText";   // Parameter name for the text input
	public static final String CONFIG_KEY    = ".SessionTestPortletConfigKey";   // Key for the portlet preferences

	private static final PortletMode CUSTOM_CONFIG_MODE = new PortletMode("config");
	/**
	 * @see javax.portlet.Portlet#init()
	 */
	public void init() throws PortletException{
		System.out.println("SESSION TEST INIT!!");
		
		super.init();
	}

	/**
	 * Serve up the <code>view</code> mode.
	 * 
	 * @see javax.portlet.GenericPortlet#doView(javax.portlet.RenderRequest, javax.portlet.RenderResponse)
	 */
	public void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		// Set the MIME type for the render response
		response.setContentType(request.getResponseContentType());
		PortletSession sess = request.getPortletSession();
		
		if (sess.getAttribute("WFS")==null){
			System.out.println("Setting Attribute.");
			sess.setAttribute("WFS", "This is a test",PortletSession.PORTLET_SCOPE);
		}else{
			System.out.println("Already Exists.");
		}
		

		// Check if portlet session exists
		SessionTestPortletSessionBean sessionBean = getSessionBean(request);
		if( sessionBean==null ) {
			response.getWriter().println("<b>NO PORTLET SESSION YET</b>");
			return;
		}

		// Invoke the JSP to render
		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(getJspFilePath(request, VIEW_JSP));
		rd.include(request,response);
	}

	/**
	 * Serve up the <code>edit</code> mode.
	 * 
	 * @see javax.portlet.GenericPortlet#doEdit(javax.portlet.RenderRequest, javax.portlet.RenderResponse)
	 */
	public void doEdit(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		// Set the MIME type for the render response
		response.setContentType(request.getResponseContentType());

		// Check if portlet session exists
		SessionTestPortletSessionBean sessionBean = getSessionBean(request);
		if( sessionBean==null ) {
		    response.getWriter().println("<b>NO PORTLET SESSION YET</b>");
			return;
		}

		// Invoke the JSP to render
		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(getJspFilePath(request, EDIT_JSP));
		rd.include(request,response);
	}
	
	/**
	 * Serve up the custom <code>config</code> mode.
	 */
	protected void doCustomConfigure(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		// Set the MIME type for the render response
		response.setContentType(request.getResponseContentType());
		// Invoke the JSP to render
		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(getJspFilePath(request, CONFIG_JSP));
		rd.include(request,response);
	}
	
	/**
	 * Override doDispatch method for handling custom portlet modes.
	 * 
	 * @see javax.portlet.GenericPortlet#doDispatch(javax.portlet.RenderRequest, javax.portlet.RenderResponse)
	 */
	protected void doDispatch(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		if (!WindowState.MINIMIZED.equals(request.getWindowState())){
			PortletMode mode = request.getPortletMode();			
			if (CUSTOM_CONFIG_MODE.equals(mode)) {
				doCustomConfigure(request, response);
				return;
			}
		}
		super.doDispatch(request, response);
	}

	/**
	 * Process an action request.
	 * 
	 * @see javax.portlet.Portlet#processAction(javax.portlet.ActionRequest, javax.portlet.ActionResponse)
	 */
	public void processAction(ActionRequest request, ActionResponse response) throws PortletException, java.io.IOException {
		if( request.getParameter(FORM_SUBMIT) != null ) {
			// Set form text in the session bean
			SessionTestPortletSessionBean sessionBean = getSessionBean(request);
			if( sessionBean != null )
				sessionBean.setFormText(request.getParameter(FORM_TEXT));
		}
		if( request.getParameter(EDIT_SUBMIT) != null ) {
			PortletPreferences prefs = request.getPreferences();
			try {
				prefs.setValue(EDIT_KEY,request.getParameter(EDIT_TEXT));
				prefs.store();
			}
			catch( ReadOnlyException roe ) {
			}
			catch( ValidatorException ve ) {
			}
		}
		if( request.getParameter(CONFIG_SUBMIT) != null ) {
			PortletPreferences prefs = request.getPreferences();
			try {
				prefs.setValue(CONFIG_KEY,request.getParameter(CONFIG_TEXT));
				prefs.store();
			}
			catch( ReadOnlyException roe ) {
			}
			catch( ValidatorException ve ) {
			}
		}
	}

	/**
	 * Get SessionBean.
	 * 
	 * @param request PortletRequest
	 * @return SessionTestPortletSessionBean
	 */
	private static SessionTestPortletSessionBean getSessionBean(PortletRequest request) {
		PortletSession session = request.getPortletSession();
		if( session == null )
			return null;
		SessionTestPortletSessionBean sessionBean = (SessionTestPortletSessionBean)session.getAttribute(SESSION_BEAN);
		if( sessionBean == null ) {
			sessionBean = new SessionTestPortletSessionBean();
			session.setAttribute(SESSION_BEAN,sessionBean);
		}
		return sessionBean;
	}

	/**
	 * Returns JSP file path.
	 * 
	 * @param request Render request
	 * @param jspFile JSP file name
	 * @return JSP file path
	 */
	private static String getJspFilePath(RenderRequest request, String jspFile) {
		String markup = request.getProperty("wps.markup");
		if( markup == null )
			markup = getMarkup(request.getResponseContentType());
		return JSP_FOLDER + markup + "/" + jspFile + "." + getJspExtension(markup);
	}

	/**
	 * Convert MIME type to markup name.
	 * 
	 * @param contentType MIME type
	 * @return Markup name
	 */
	private static String getMarkup(String contentType) {
		if( "text/vnd.wap.wml".equals(contentType) )
			return "wml";
        else
            return "html";
	}

	/**
	 * Returns the file extension for the JSP file
	 * 
	 * @param markupName Markup name
	 * @return JSP extension
	 */
	private static String getJspExtension(String markupName) {
		return "jsp";
	}

}
