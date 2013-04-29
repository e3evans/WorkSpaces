package com.manpower.portal.portlet.gemt;

import java.io.IOException;
import javax.portlet.*;

import com.ibm.faces.portlet.FacesPortlet;

public class GEMTNotes extends FacesPortlet {

	public static final String JSP_FOLDER    = "/GEMTNotes/";
	public static final String JSP_PAGE_SPLASH = "GEMTNotesSplash.jsp";
	public static final String JSP_PAGE_REPORTVIEW = "/GEMTNotes/GEMTNotesFormView.jsp";
	public static final String JSP_PAGE_REPORTEDIT = "/GEMTEntryForm/GEMTNotesFormView.jsp";
	
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
		
//		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(JSP_FOLDER+JSP_PAGE_SPLASH);
		
//		rd.include(request,response);
		
//		super.doView(request, response);
		//request.getPortletSession().setAttribute("com.ibm.faces.portlet.page.view", PAGE1);
//		request.getPortletSession().setAttribute("com.ibm.faces.portlet.page.view",JSP_PAGE_REPORTVIEW);
		super.doView(request, response);
	}

	public void init(PortletConfig portletConfig) throws PortletException {
		super.init(portletConfig);
	}

	public void processAction(ActionRequest request, ActionResponse response) throws PortletException {
		super.processAction(request, response);
//		System.out.println("PRIOR TO JSF:-->"+request.getParameter("testingInput"));
	}

}
