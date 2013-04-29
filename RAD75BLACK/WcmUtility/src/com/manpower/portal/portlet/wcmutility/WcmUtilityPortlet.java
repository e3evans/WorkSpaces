package com.manpower.portal.portlet.wcmutility;

import java.io.IOException;
import javax.portlet.*;


public class WcmUtilityPortlet extends com.ibm.faces.portlet.FacesPortlet {

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
	
	public void init() throws PortletException {
		super.init();
	}
	
	public void processAction(ActionRequest request, ActionResponse response) throws PortletException {
//		try {
//			response.sendRedirect("http://www.yahoo.com");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		super.processAction(request, response);
	}
	
	
	
	
}
