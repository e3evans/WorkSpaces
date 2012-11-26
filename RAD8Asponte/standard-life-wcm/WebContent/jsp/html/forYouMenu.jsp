<%@page import="java.net.URLEncoder"%>
<%@ page session="false" contentType="text/html" import="java.util.*,com.ibm.workplace.wcm.api.*,com.ibm.workplace.wcm.api.exceptions.*,ca.standardlife.wcm.*,javax.naming.*,com.ibm.portal.model.*,com.ibm.portal.navigation.*"
	%><%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"
	%><%@ taglib uri="http://www.ibm.com/xmlns/prod/websphere/portal/v7/portlet/ibm-portlet-ext" prefix="portlet-ext" 
	%><%@ taglib uri="/WEB-INF/tld/wcm.tld" prefix="wcm" 
	%><portlet:defineObjects 
	/><wcm:initworkspace user="<%= (java.security.Principal)request.getUserPrincipal() %>" /><%
	// 1. Call wcm:initworkspace
	// 2. Get the rendering context and workspace from the request
	Workspace wkspc=(Workspace)pageContext.getAttribute(Workspace.WCM_WORKSPACE_KEY);
    RenderingContext renderingContext = (RenderingContext)request.getAttribute(Workspace.WCM_RENDERINGCONTEXT_KEY);
    if(wkspc!=null&&renderingContext!=null){
    	DocumentLibrary currdoclib=null;
    	try{
			// 3. Login
		    wkspc.login();
			// 4. Save the current library and ignore if there is not one
		    try{currdoclib=wkspc.getCurrentDocumentLibrary();}catch(NullPointerException e){}
		    String currentPath = renderingContext.getPath();
		    String currentLibrary = renderingContext.getLibrary().getName();
		    Map myparams = new HashMap();
		    myparams.put("SiteAreas", currentLibrary+"/Advisor Portal/Documents/Marketing Material") ;
		    %><wcm:setExplicitContext path="<%=currentPath%>" requestParameters="<%=myparams%>"
		    /><wcm:libraryComponent name="<%=Constants.FOR_YOU_MENU%>" library="<%=Constants.DESIGN_LIBRARY%>" /><%
    	}finally{
			// 7. Restore the current lib if one was set 
			if(currdoclib!=null){wkspc.setCurrentDocumentLibrary(currdoclib);}
			// 8. Logout
			wkspc.logout();
		}
	}
%>
