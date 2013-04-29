package com.manpower.portal.portlet.gemt;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.log4j.Logger;

import com.ibm.portal.portlet.service.PortletServiceHome;
import com.ibm.websphere.security.PasswordCheckFailedException;
import com.ibm.websphere.security.auth.AuthenticationFailedException;
import com.ibm.wps.portletservice.authentication.AuthenticationPortletService;


public class GEMTWelcomePortlet extends com.ibm.faces.portlet.FacesPortlet {
	
	private static Logger log=Logger.getLogger(GEMTWelcomePortlet.class);
	
	public static String PARAM_GEMTLOGIN_ACTION = "gemt_login_action_login";
	public static String PARAM_GEMTLOGIN_PORTLETID ="gemt_login_portlet_id";
	
	public static String SESS_GEMTLOGIN_FAILED = "gemt_login_failed";
	
	public static String FIELD_USEREMAIL = "useremail";
	public static String FIELD_PASSWORD = "password";
	
	public static String ACTION_LOGIN_ACTION = "LOGIN";
	public static String CONST_GEMTLOING_FORMID = ":gemtloginform:";
	
	public static String LOGINPAGE = "/GEMTWelcome/GEMTWelcomeLogin.jsp";
	public static String WELCOMEPAGE = "/GEMTWelcome/GEMTWelcomeView.jsp";
	
	public static String PREF_DISPLAY_PAGE = "com.manpower.portal.portlet.gemtwelcomeportlet.pref.displaypage";
	
	public void destroy() {
		super.destroy();
	}
	
	public void doConfigure(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		super.doConfigure(request, response);
	}
	
	
	public void doEdit(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		super.doEdit(request, response);
	}
	
	public void doHelp(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		super.doHelp(request, response);
	}
	
	public void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		PortletPreferences prefs = request.getPreferences();
		
		if (prefs.getValue(PREF_DISPLAY_PAGE, "0").equals("1")){
			request.getPortletSession().setAttribute("com.ibm.faces.portlet.page.view",LOGINPAGE);
		}else if (prefs.getValue(PREF_DISPLAY_PAGE, "0").equals("0")){
			request.getPortletSession().setAttribute("com.ibm.faces.portlet.page.view",WELCOMEPAGE);
		}
		super.doView(request, response);
	}
	
	public void init() throws PortletException {
		super.init();
	}
	
	public void processAction(ActionRequest request, ActionResponse response) throws PortletException {
		String action = request.getParameter(PARAM_GEMTLOGIN_ACTION);
		boolean successfullogin = false;
		if (action!=null && action.equals(ACTION_LOGIN_ACTION)){
			String portletId = request.getParameter(PARAM_GEMTLOGIN_PORTLETID);
			String fieldPrefix = "view"+portletId+CONST_GEMTLOING_FORMID;
			String userID = request.getParameter(fieldPrefix+FIELD_USEREMAIL);
			String password = request.getParameter(fieldPrefix+FIELD_PASSWORD);

			successfullogin = doLogin(userID,password,false,request,response);
			
			if (!successfullogin){
				request.getPortletSession().setAttribute(SESS_GEMTLOGIN_FAILED, SESS_GEMTLOGIN_FAILED, PortletSession.APPLICATION_SCOPE);
			}else{
				request.getPortletSession().removeAttribute(SESS_GEMTLOGIN_FAILED);
			}
		}
		if (!successfullogin){
			super.processAction(request, response);
		}
	}
	
	protected static boolean doLogin(String userID, String password, boolean resumeSession, ActionRequest aReq, ActionResponse aRes)
	 {
	     
	     boolean loginSuccessful = false;
//	     PortletPreferences prefs = aReq.getPreferences();
	     AuthenticationPortletService authService = null;
	     
	     try
	     {
	     	javax.naming.Context ctx = new javax.naming.InitialContext();
	     	PortletServiceHome psh = (PortletServiceHome)ctx.lookup("portletservice/com.ibm.wps.portletservice.authentication.AuthenticationPortletService");
	        authService = (AuthenticationPortletService)psh.getPortletService(com.ibm.wps.portletservice.authentication.AuthenticationPortletService.class);
	        authService.doLogin(userID, password, resumeSession, aReq, aRes);
	       
	        loginSuccessful = true;
	     }
	     catch(AuthenticationFailedException afe)
	     {
	         log.debug("Login failed with:\n",afe);
	         loginSuccessful=false;
	     }
	     catch(PasswordCheckFailedException pce)
	     {
	         log.debug("Login failed with:\n",pce);
	         loginSuccessful=false;
	     }
	     catch(Exception plde)
		 {
	     	StringBuffer sb = new StringBuffer();
	     	sb.append("ERROR:  "+plde.getMessage());
//	     	if(Constants.SHOW_SYSTEM_PRINTLN){
//	     		System.out.println(sb.toString());
//	     	}
	     
	     	log.debug("Login failed with:\n",plde);
	     }
	     

	     return loginSuccessful;
	 }
	
	
	
	
}
