package com.ibm.hellowsrp;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;


public class HelloWSRPPortlet extends com.ibm.faces.portlet.FacesPortlet {

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
		
	
		
//		System.out.println(request.getRemoteUser());
//		PortletRequest pr = (PortletRequest)request;
//
//		HttpServletRequest hr = (HttpServletRequest)request;
//		
//		Map userInfo = (Map) pr.getAttribute(PortletRequest.USER_INFO);
//        if (userInfo != null) {
//        Set keys = userInfo.keySet();
//        Iterator i = keys.iterator();
////        while (i.hasNext()){
////        	String k = (String)i.next();
////        	
////        }
//	        for (Object k : keys) {
//	          System.out.println(k + ": " + userInfo.get(k));
//	        }
//        }

		
		
//		System.out.println(pr.getUserPrincipal());
//		System.out.println(hr.getUserPrincipal());
//		
//		for (Enumeration e = hr.getHeaderNames();e.hasMoreElements();){
//			
//			String temp = e.nextElement().toString();
//			System.out.println(temp+"------------->"+hr.getHeader(temp));
//		}
//		
		
		
		super.doView(request, response);
	}
	
	public void init() throws PortletException {
		super.init();
	}
	
	public void processAction(ActionRequest request, ActionResponse response) throws PortletException {
		
		super.processAction(request, response);
	}
	
	
	
	
}
