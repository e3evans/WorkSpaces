package com.manpower.portal.portlet.dt_flash_poc;

import java.io.*;
import java.util.*;
import javax.portlet.*;
import javax.servlet.http.HttpServletRequest;

/**
 * A sample portlet based on GenericPortlet
 */
public class DT_Flash_POCPortlet extends GenericPortlet {

	public static final String JSP_FOLDER    = "/_DT_Flash_POC/jsp/";    // JSP folder name

	public static final String VIEW_JSP      = "DT_Flash_POCPortletView";         // JSP file name to be rendered on the view mode
	public static final String EDIT_JSP      = "DT_Flash_POCPortletEdit";         // JSP file name to be rendered on the edit mode
	public static final String CONFIG_JSP    = "DT_Flash_POCPortletConfig";       // JSP file name to be rendered on the configure mode
	public static final String EDIT_DEFAULTS_JSP    = "DT_Flash_POCPortletEditDefaults";       // JSP file name to be rendered on the configure mode
	public static final String SESSION_BEAN  = "DT_Flash_POCPortletSessionBean";  // Bean name for the portlet session
	public static final String FORM_SUBMIT   = "DT_Flash_POCPortletFormSubmit";   // Action name for submit form
	public static final String FORM_TEXT     = "DT_Flash_POCPortletFormText";     // Parameter name for the text input
   
	public static final String EDIT_SUBMIT   = "DT_Flash_POCPortletEditSubmit";   // Action name for submit form
	public static final String EDIT_TEXT     = "DT_Flash_POCPortletEditText";     // Parameter name for the text input
	public static final String EDIT_KEY      = ".DT_Flash_POCPortletEditKey";     // Key for the portlet preferences
	public static final String CONFIG_SUBMIT = "DT_Flash_POCPortletConfigSubmit"; // Action name for submit form
	public static final String CONFIG_TEXT   = "DT_Flash_POCPortletConfigText";   // Parameter name for the text input
	public static final String CONFIG_KEY    = ".DT_Flash_POCPortletConfigKey";   // Key for the portlet preferences
	public static final String EDIT_DEFAULTS_SUBMIT = "DT_Flash_POCPortletEditDefaultsSubmit"; // Action name for submit form
	public static final String EDIT_DEFAULTS_TEXT   = "DT_Flash_POCPortletEditDefaultsText";   // Parameter name for the text input
	public static final String EDIT_DEFAULTS_KEY    = ".DT_Flash_POCPortletEditDefaultsKey";   // Key for the portlet preferences

	private static final PortletMode CUSTOM_CONFIG_MODE = new PortletMode("config");

	private static final PortletMode CUSTOM_EDIT_DEFAULTS_MODE = new PortletMode("edit_defaults");

	/**
	 * @see javax.portlet.Portlet#init()
	 */
	public void init() throws PortletException{
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
		PortletURL actionURL = response.createActionURL();
		request.setAttribute("actionURL", actionURL);
		HttpServletRequest hr = (HttpServletRequest)request;
		System.out.println("QUERY STRING:  "+response.createRenderURL().toString());
		
		// Check if portlet session exists
		DT_Flash_POCPortletSessionBean sessionBean = getSessionBean(request);
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
		DT_Flash_POCPortletSessionBean sessionBean = getSessionBean(request);
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
	 * Serve up the custom <code>edit_defaults</code> mode.
	 */
	protected void doCustomEditDefaults(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		// Set the MIME type for the render response
		response.setContentType(request.getResponseContentType());

		// Invoke the JSP to render
		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(getJspFilePath(request, EDIT_DEFAULTS_JSP));
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
			else if (CUSTOM_EDIT_DEFAULTS_MODE.equals(mode)) {
				doCustomEditDefaults(request, response);
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
			DT_Flash_POCPortletSessionBean sessionBean = getSessionBean(request);
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
		if( request.getParameter(EDIT_DEFAULTS_SUBMIT) != null ) {
			PortletPreferences prefs = request.getPreferences();
			try {
				prefs.setValue(EDIT_DEFAULTS_KEY,request.getParameter(EDIT_DEFAULTS_TEXT));
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
	 * @return DT_Flash_POCPortletSessionBean
	 */
	private static DT_Flash_POCPortletSessionBean getSessionBean(PortletRequest request) {
		PortletSession session = request.getPortletSession();
		if( session == null )
			return null;
		DT_Flash_POCPortletSessionBean sessionBean = (DT_Flash_POCPortletSessionBean)session.getAttribute(SESSION_BEAN);
		if( sessionBean == null ) {
			sessionBean = new DT_Flash_POCPortletSessionBean();
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
