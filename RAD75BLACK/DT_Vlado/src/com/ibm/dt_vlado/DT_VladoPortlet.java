package com.ibm.dt_vlado;

import java.io.IOException;
import javax.portlet.*;
import javax.servlet.http.HttpServletRequest;

import com.ibm.ws.http.HttpRequest;


public class DT_VladoPortlet extends com.ibm.faces.portlet.FacesPortlet {

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
		HttpServletRequest req = (HttpServletRequest)request;
		System.out.println("TEST--->"+req.getQueryString());
		System.out.println("TEST 2---->"+req.getLocalAddr());
		System.out.println(req.getPathInfo());
		super.doView(request, response);
	}
	
	public void init() throws PortletException {
		super.init();
	}
	
	public void processAction(ActionRequest request, ActionResponse response) throws PortletException {
		
		super.processAction(request, response);
	}
	
	
	
	
}
