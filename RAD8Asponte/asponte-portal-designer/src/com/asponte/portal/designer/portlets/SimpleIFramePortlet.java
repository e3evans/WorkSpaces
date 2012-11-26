package com.asponte.portal.designer.portlets;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.ReadOnlyException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ValidatorException;
import javax.portlet.WindowState;

/**
 * A sample portlet based on GenericPortlet
 */
public class SimpleIFramePortlet extends GenericPortlet {

	public static final String JSP_FOLDER    = "/_SimpleIFramePortlet/jsp/";    // JSP folder name

	public static final String VIEW_JSP      = "SimpleIFramePortletView";         // JSP file name to be rendered on the view mode
	public static final String EDIT_DEFAULTS_JSP    = "SimpleIFramePortletEditDefaults";       // JSP file name to be rendered on the configure mode
   
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

		request.setAttribute(Constants.IFRAME_CONFIG_BEAN,getIFrameConfig(request));
		
		// Invoke the JSP to render
		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(getJspFilePath(request, VIEW_JSP));
		rd.include(request,response);
	}
	
	/**
	 * Serve up the custom <code>edit_defaults</code> mode.
	 */
	protected void doCustomEditDefaults(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		// Set the MIME type for the render response
		response.setContentType(request.getResponseContentType());

		request.setAttribute(Constants.IFRAME_CONFIG_BEAN,getIFrameConfig(request));
		
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
			if (CUSTOM_EDIT_DEFAULTS_MODE.equals(mode)) {
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
		if( request.getParameter(Constants.EDIT_DEFAULTS_SUBMIT) != null ) {
			PortletPreferences prefs = request.getPreferences();
			try {
				String url=param(request,Constants.IFRAME_URL);
				String width=param(request,Constants.IFRAME_WIDTH);
				String height=param(request,Constants.IFRAME_HEIGHT);
				String scrolling=param(request,Constants.IFRAME_SCROLLING);
				if(url!=null){
					prefs.setValue(Constants.IFRAME_URL,url);
				}
				if(width!=null){
					prefs.setValue(Constants.IFRAME_WIDTH,width);
				}
				if(height!=null){
					prefs.setValue(Constants.IFRAME_HEIGHT,height);
				}
				if(scrolling!=null){
					prefs.setValue(Constants.IFRAME_SCROLLING,scrolling);	
				}				
				prefs.store();
			}
			catch( ReadOnlyException e ) {
				throw new PortletException(e);
			}
			catch( ValidatorException e ) {
				throw new PortletException(e);
			}
		}
	}

	private String param(PortletRequest request,String name){
		String param=request.getParameter(name);
		if(param!=null&&(param=param.trim()).length()>0){
			return param;
		}else{
			return null;
		}
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

	private IFrameConfigBean getIFrameConfig(RenderRequest request) {
		IFrameConfigBean iframeConfig=new IFrameConfigBean();
		PortletPreferences prefs=request.getPreferences();
		String url,width,height,scrolling;
		url=prefs.getValue(Constants.IFRAME_URL,"about:blank");
		width=prefs.getValue(Constants.IFRAME_WIDTH,"");
		height=prefs.getValue(Constants.IFRAME_HEIGHT,"");
		scrolling=prefs.getValue(Constants.IFRAME_SCROLLING,"");
		iframeConfig.setUrl(url);
		iframeConfig.setWidth(width);
		iframeConfig.setHeight(height);
		iframeConfig.setScrolling(scrolling);
		return iframeConfig;
	}
}
