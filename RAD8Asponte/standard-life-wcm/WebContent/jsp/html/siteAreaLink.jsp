<%@ page session="false" contentType="text/html" import="java.util.*,com.ibm.workplace.wcm.api.*,com.ibm.workplace.wcm.api.exceptions.*,ca.standardlife.wcm.*"
	%><%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"
	%><%@ taglib uri="http://www.ibm.com/xmlns/prod/websphere/portal/v7/portlet/ibm-portlet-ext" prefix="portlet-ext" 
	%><%@ taglib uri="/WEB-INF/tld/wcm.tld" prefix="wcm" 
	%><portlet:defineObjects 
	/><wcm:initworkspace user="<%= (java.security.Principal)request.getUserPrincipal() %>" /><%	
    // 1. Call wcm:initworkspace
	// 2. Get the rendering context and workspace from the request
	Workspace wkspc=(Workspace)pageContext.getAttribute(Workspace.WCM_WORKSPACE_KEY);
    RenderingContext renderingContext = (RenderingContext)request.getAttribute(Workspace.WCM_RENDERINGCONTEXT_KEY);
	DocumentId said=null;
    if(wkspc!=null&&renderingContext!=null){
    	DocumentLibrary currdoclib=null;
    	try{
			// 3. Login
    		wkspc.login();
			// 4. Save the current library and ignore if there is not one
    		try{currdoclib=wkspc.getCurrentDocumentLibrary();}catch(NullPointerException e){}
			// 5. Get your library(ies)
			// 6. Do your stuff
			DocumentId docid=renderingContext.getCurrentResultId();
			if(docid!=null){
				SiteArea siteArea=(SiteArea)wkspc.getById(docid,true);
				String title=siteArea.getTitle(request.getLocale());
				String uniqueName=null;
				if(siteArea.hasComponent(Constants.PAGE_UNIQUE_NAME_ELEMENT)){
					TextComponent cText=(TextComponent)siteArea.getComponentByReference(Constants.PAGE_UNIQUE_NAME_ELEMENT);
					String s=cText.getText();
					if(s!=null&&(s=s.trim()).length()>0){
						uniqueName=s;
					}
				}
				if(uniqueName!=null){
				%><a accesskey="" href='<portlet-ext:portalRenderURL contentNode="<%=uniqueName%>" />'><%}
				%><span class=""><%=title%></span><%
				if(uniqueName!=null){%></a><%}
			}
    	}catch(WCMException e){
    		// TODO Exception encountered rendering link
    	}finally{
			// 7. Restore the current lib if one was set 
			if(currdoclib!=null){wkspc.setCurrentDocumentLibrary(currdoclib);}
			// 8. Logout
			wkspc.logout();
		}
	}
%>
