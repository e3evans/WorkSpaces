<%@page import="com.ibm.websphere.personalization.ContentSpot"%>
<%@page import="java.security.Principal"%>
<%@page import="java.net.URLEncoder"%>
<%@ page session="false" contentType="text/html" import="java.util.*,com.ibm.workplace.wcm.api.*,com.ibm.workplace.wcm.api.exceptions.*,ca.standardlife.wcm.*,javax.naming.*,com.ibm.portal.model.*,com.ibm.portal.navigation.*"
	%><%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"
	%><%@ taglib uri="http://www.ibm.com/xmlns/prod/websphere/portal/v7/portlet/ibm-portlet-ext" prefix="portlet-ext" 
	%><%@ taglib uri="/WEB-INF/tld/wcm.tld" prefix="wcm" 
	%><portlet:defineObjects/>
	<wcm:initworkspace user="<%= (java.security.Principal)request.getUserPrincipal() %>">
		Could not create workspace for user <%=request.getUserPrincipal()%>
	</wcm:initworkspace>
	<%
	// 1. Call wcm:initworkspace
	// 2. Get the rendering context and workspace from the request
	Workspace wkspc=(Workspace)pageContext.getAttribute(Workspace.WCM_WORKSPACE_KEY);
    RenderingContext renderingContext = (RenderingContext)request.getAttribute(Workspace.WCM_RENDERINGCONTEXT_KEY);
    if(wkspc!=null&&renderingContext!=null){
    	DocumentLibrary currdoclib=null;
    	DocumentIdIterator idIterator;
		LibraryComponent libCmpnt; 
		RenderingContext navContext;
    	String pathSuffix = "";
    	String currentPath="";
    	String currentLibrary="";
    	Object[] content = null;
    	try{
			// 3. Login
		    wkspc.login();
		    // 4. Get the profiler rule that we are going to execute.
			ContentSpot contentSpot = new ContentSpot();
			contentSpot.setRequest(request);
			contentSpot.setRuleName("/Advisor Portal/Carousel/Carousel Profiler");
			// 5. Get the current path and the current library to create the context to render based on the rule.	
			currentPath = renderingContext.getPath();
		    currentLibrary = renderingContext.getLibrary().getName();
			try{		
				content = contentSpot.getRuleResults();
				if (content.length>0){
				// 6. Set the rendering context to contain the result from the profiler rule.
					renderingContext.setRenderedContent("/"+currentPath + "/"+content[0]);
				}
		   	}catch (Throwable e){
		   		e.printStackTrace();
		   	}
		   	
			// 7. Save the current library and ignore if there is not one
		    try{currdoclib=wkspc.getCurrentDocumentLibrary();}catch(NullPointerException e){}
		    // 8. Change the lib over to the design library and grab the image rotator.
		   	wkspc.setCurrentDocumentLibrary(wkspc.getDocumentLibrary("Standard Life Design Library"));
		   	idIterator = wkspc.findComponentByName("Image Rotator Navigator");
		   	libCmpnt = (LibraryComponent) wkspc.getById(idIterator.nextId());
		 	String result = wkspc.render(renderingContext,libCmpnt);
		 	out.println(result);
    	}finally{
			// 9. Restore the current lib if one was set 
			if(currdoclib!=null){wkspc.setCurrentDocumentLibrary(currdoclib);}
			// 10. Logout
			wkspc.logout();
		}
	}     
    %>
   
   	
    
   
    