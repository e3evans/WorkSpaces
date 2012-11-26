package com.asponte.googlemapsprototype;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.PortletSession;
import javax.portlet.ReadOnlyException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ValidatorException;
import javax.portlet.WindowState;

import org.json.JSONException;

import com.taylor.beans.Distributor;
import com.taylor.utils.JSONUtils;

/**
 * A sample portlet based on GenericPortlet
 */
public class GoogleMapsPrototypePortlet extends GenericPortlet {

	public static final String JSP_FOLDER    = "/_GoogleMapsPrototype/jsp/";    // JSP folder name

	public static final String VIEW_JSP      = "GoogleMapsPrototypePortletView";         // JSP file name to be rendered on the view mode
	public static final String EDIT_JSP      = "GoogleMapsPrototypePortletEdit";         // JSP file name to be rendered on the edit mode
	
	public static final String CONFIG_JSP    = "GoogleMapsPrototypePortletConfig";       // JSP file name to be rendered on the configure mode
	public static final String EDIT_DEFAULTS_JSP    = "GoogleMapsPrototypePortletEditDefaults";       // JSP file name to be rendered on the configure mode
	
	public static final String SESSION_BEAN  = "GoogleMapsPrototypePortletSessionBean";  // Bean name for the portlet session
	public static final String FORM_SUBMIT   = "GoogleMapsPrototypePortletFormSubmit";   // Action name for submit form
	public static final String FORM_TEXT     = "GoogleMapsPrototypePortletFormText";     // Parameter name for the text input
   
	public static final String EDIT_SUBMIT   = "GoogleMapsPrototypePortletEditSubmit";   // Action name for submit form
	public static final String EDIT_TEXT     = "GoogleMapsPrototypePortletEditText";     // Parameter name for the text input
	public static final String EDIT_KEY      = ".GoogleMapsPrototypePortletEditKey";     // Key for the portlet preferences
	public static final String CONFIG_SUBMIT = "GoogleMapsPrototypePortletConfigSubmit"; // Action name for submit form
	public static final String CONFIG_TEXT   = "GoogleMapsPrototypePortletConfigText";   // Parameter name for the text input
	public static final String CONFIG_KEY    = ".GoogleMapsPrototypePortletConfigKey";   // Key for the portlet preferences
	public static final String EDIT_DEFAULTS_SUBMIT = "GoogleMapsPrototypePortletEditDefaultsSubmit"; // Action name for submit form
	public static final String EDIT_DEFAULTS_TEXT   = "GoogleMapsPrototypePortletEditDefaultsText";   // Parameter name for the text input
	public static final String EDIT_DEFAULTS_KEY    = ".GoogleMapsPrototypePortletEditDefaultsKey";   // Key for the portlet preferences
	
	public static final String REQ_DISTRIBUTORS = "request.taylor.distribuotors.json";

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

		// Check if portlet session exists
		GoogleMapsPrototypePortletSessionBean sessionBean = getSessionBean(request);
		if( sessionBean==null ) {
			response.getWriter().println("<b>NO PORTLET SESSION YET</b>");
			return;
		}
		List<Object> myDistributors = new ArrayList<Object>();
		double lat = 42.444371;
	  	double lng = -89.088744;
		for (int i =0;i<10;i++){
			Distributor d = new Distributor();
			d.setAddress("111 Anywhere St.<br>Nowhere, OH USA");
			d.setName("Distributor "+i);
			d.setEmail("test@test.com");
			d.setIcon(response.encodeURL(request.getContextPath()+"/images/distributor.gif"));
			d.setLatitude(lat);
			d.setLongitude(lng);
			lng=lng+.1;
			lat=lat-.2;
			d.setType(Distributor.TYPE_OFFICE);
			myDistributors.add(d);
		}
		
		try {
			request.setAttribute(REQ_DISTRIBUTORS, JSONUtils.getJSONArray("distributors", myDistributors));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			request.setAttribute(REQ_DISTRIBUTORS,"ERROR!");
			e.printStackTrace();
		}
		
		Class c = Distributor.class;
		Field[] f = c.getFields();
		Method[] m = c.getMethods();
		
		System.out.println("Method LENGTH:  "+m.length);
		for (int i=0;i<m.length;i++){
			
			System.out.println(m[i].getName());
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
		GoogleMapsPrototypePortletSessionBean sessionBean = getSessionBean(request);
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
			GoogleMapsPrototypePortletSessionBean sessionBean = getSessionBean(request);
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
	 * @return GoogleMapsPrototypePortletSessionBean
	 */
	private static GoogleMapsPrototypePortletSessionBean getSessionBean(PortletRequest request) {
		PortletSession session = request.getPortletSession();
		if( session == null )
			return null;
		GoogleMapsPrototypePortletSessionBean sessionBean = (GoogleMapsPrototypePortletSessionBean)session.getAttribute(SESSION_BEAN);
		if( sessionBean == null ) {
			sessionBean = new GoogleMapsPrototypePortletSessionBean();
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
