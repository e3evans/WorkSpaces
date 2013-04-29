package com.manpower.portal.portlet.directoffice_sssportlet;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

import com.manpower.hbn.candidateidservice.beans.CandidateId;
import com.manpower.hbn.shared.dao.DAOFactory;
import com.manpower.portal.portlet.directoffice_sssportlet.servlets.LoginService;


public class DirectOffice_SSSPortlet extends com.ibm.faces.portlet.FacesPortlet {
	public static String SESS_LOGIN_STATUS = "com.manpower.portal.portlet.directoffice_sssportlet.loginstatus";
	public static String SESS_SELECTED_TAB = "com.manpower.portal.portlet.directoffice_sssportlet.selectedtab";
	public static String SESS_CAND_GENERAL = "com.manpower.portal.portlet.directoffice_sssportlet.candgeneral";
	public static String SESS_CAND_ENTITY  = "com.manpower.portal.portlet.directoffice_sssportlet.candentity";
	public static String SESS_DT_ID 	   = "com.manpower.portal.portlet.directoffice_sssportlet.dtid";
	
	public void destroy() {
		super.destroy();
	}
	
	
	public void doEdit(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		super.doEdit(request, response);
	}
	
	public void doHelp(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		super.doHelp(request, response);
	}
	
	public void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		 
		
		String tempUser = request.getUserPrincipal().toString();
		System.out.println(tempUser);
		
		
		if (DAOFactory.getDAOFactory().getCandidateIdDAO().findByUserID(tempUser)!=null){
			CandidateId cid = (CandidateId) DAOFactory.getDAOFactory().getCandidateIdDAO().findByUserID(tempUser); 
			request.getPortletSession().setAttribute(SESS_DT_ID, cid.getId(), PortletSession.APPLICATION_SCOPE);
		}else{
			
			request.getPortletSession().setAttribute(SESS_DT_ID, "64933", PortletSession.APPLICATION_SCOPE);
//			request.getPortletSession().setAttribute(SESS_DT_ID, "BADID", PortletSession.APPLICATION_SCOPE);
		}
		
		if (request.getPortletSession().getAttribute(SESS_LOGIN_STATUS, PortletSession.APPLICATION_SCOPE)==null){
			request.setAttribute(SESS_LOGIN_STATUS, LoginService.LOGIN_LOGGEDOUT);
			request.setAttribute(SESS_SELECTED_TAB, "0");
			request.setAttribute(SESS_CAND_GENERAL, "");
		}else{
			request.setAttribute(SESS_LOGIN_STATUS, request.getPortletSession()
					.getAttribute(SESS_LOGIN_STATUS, PortletSession.APPLICATION_SCOPE));
			request.setAttribute(SESS_SELECTED_TAB, request.getPortletSession()
					.getAttribute(SESS_SELECTED_TAB,PortletSession.APPLICATION_SCOPE));
			request.setAttribute(SESS_CAND_GENERAL, request.getPortletSession()
					.getAttribute(SESS_CAND_GENERAL,PortletSession.APPLICATION_SCOPE));
		}
		super.doView(request, response);
	}
	
	public void init() throws PortletException {
		super.init();
	}
	
	public void processAction(ActionRequest request, ActionResponse response) throws PortletException {
		
		super.processAction(request, response);
	}
	
	
	
	
	private static final PortletMode CUSTOM_EDIT_DEFAULTS_MODE = new PortletMode("edit_defaults");
	private static final PortletMode CUSTOM_CONFIG_MODE = new PortletMode("config");

	/**
	 * Override doDispatch method for handling custom portlet modes.
	 * 
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
	
	protected void doCustomEditDefaults(RenderRequest request, RenderResponse response) throws PortletException, IOException {
			super.doDispatch(request, response);
	}
	
	protected void doCustomConfigure(RenderRequest request, RenderResponse response) throws PortletException, IOException {
			super.doDispatch(request, response);
	}
	
}
