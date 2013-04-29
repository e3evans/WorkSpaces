package com.ibm.portal.portlet.ajaxmenus;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;


public class AjaxMenusPortlet extends com.ibm.faces.portlet.FacesPortlet {
	
	public static String PREF_DISPLAY_ITEMS="com.ibm.portlet.ajaxmenus.pref_dispaly_items";
	public static String PREF_HIDE_WORD="com.ibm.portlet.ajaxmenus.hide_word";
	public static String PREF_INCLUDE_DROPDOWN="com.ibm.portlet.ajaxmenus.include_dropdown";
	
	public void init() throws PortletException {
		super.init();
	}
	
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
		super.doView(request, response);
	}
	public void processAction(ActionRequest request, ActionResponse response) throws PortletException {	
		super.processAction(request, response);
	}
}
