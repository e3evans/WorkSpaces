<%@ page session="true" contentType="text/html" import="java.util.*,java.util.logging.*,com.ibm.workplace.wcm.api.*,com.asponte.wcm.widgets.*,com.asponte.commons.portal.wcm.*"
    %><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"
	%><%@ taglib uri="/WEB-INF/tld/wcm.tld" prefix="wcm" 
    %><fmt:setBundle basename="com.asponte.wcm.widgets.resources.strings" 
	/><wcm:initworkspace user="<%= (java.security.Principal)request.getUserPrincipal() %>" /><%
    final String METHOD_NAME="addDocTool.jsp";
    boolean isTraceEnabled=Jsp.LOGGER.isLoggable(Level.FINER);
	boolean isDebugEnabled=Jsp.LOGGER.isLoggable(Level.FINEST);
	boolean isErrorEnabled=Jsp.LOGGER.isLoggable(Level.SEVERE);
    if(isTraceEnabled){Jsp.LOGGER.entering(Jsp.CLASS_NAME,METHOD_NAME);}
    // Get the rendering context and workspace from the request
    Workspace wkspc=(Workspace)pageContext.getAttribute(Workspace.WCM_WORKSPACE_KEY);
    RenderingContext renderingContext = (RenderingContext)request.getAttribute(Workspace.WCM_RENDERINGCONTEXT_KEY);
    if(wkspc!=null&&renderingContext!=null){
        DocumentLibrary currdoclib=null;
        try{
            if(isDebugEnabled){Jsp.LOGGER.finest(METHOD_NAME+": Logging in to workspace...");}
            wkspc.login();
            if(isDebugEnabled){Jsp.LOGGER.finest(METHOD_NAME+": Saving current library...");}
            try{currdoclib=wkspc.getCurrentDocumentLibrary();}catch(NullPointerException e){}
            if(isDebugEnabled){Jsp.LOGGER.finest(METHOD_NAME+": Loading our library...");}
            DocumentId docid=renderingContext.getCurrentResultId();
            if(docid!=null){
	            Content content=renderingContext.getContent();
	            DocumentLibrary awwDesignLib=wkspc.getDocumentLibrary(AwwConfig.getDesignLibraryFor(content));
            	if(awwDesignLib!=null){
                    SiteArea sa=(SiteArea)wkspc.getById(docid,true);
                    wkspc.setCurrentDocumentLibrary(awwDesignLib);
                    if(isDebugEnabled){Jsp.LOGGER.finest(METHOD_NAME+": Locating the component named '"+Constants.ADD_DOCUMENT_TOOL+"'...");}
                    DocumentIdIterator itr=wkspc.findComponentByName(Constants.ADD_DOCUMENT_TOOL);
                    if(itr.hasNext()){
                        LibraryComponent cmpnt=(LibraryComponent)wkspc.getById(itr.nextId(),true);
                        if(isDebugEnabled){Jsp.LOGGER.finest(METHOD_NAME+": Creating a new rendering context...");}
                        RenderingContext rc2=wkspc.createRenderingContext(request,response,new HashMap());
                        rc2.setRenderedContent(sa);
                        if(isDebugEnabled){Jsp.LOGGER.finest(METHOD_NAME+": Rendering tool...");}
                %><%=wkspc.render(rc2,cmpnt)%><%
                    }
                }else{
                	// TODO: Correct error message and Result ID
	   				if(isErrorEnabled){Jsp.LOGGER.severe(METHOD_NAME+": No widget configuration was found for the content with id "+content.getId()+"!");}
	   				%><fmt:message key="<%=Results.RENDER_VIDEO_ICON_WIDGET_CONFIG_NOT_FOUND%>"><fmt:param value="<%=renderingContext.getPath(true)%>"/></fmt:message><%
	   			}
            }
        }catch(Throwable t){
            // TODO: Correct error message and Result ID
			if(isErrorEnabled){Jsp.LOGGER.log(Level.SEVERE,METHOD_NAME+": The document icons for the content at path "+renderingContext.getPath(true)+" could not be rendered due to the following error: " +t.getMessage(),t);}
  			%><fmt:message key="<%=Results.RENDER_VIDEO_ICON_FAILED%>"><fmt:param value="<%=renderingContext.getPath(true)%>"/><fmt:param value="<%=t.getMessage()%>" /></fmt:message><%
        }finally{
            if(isDebugEnabled){Jsp.LOGGER.finest(METHOD_NAME+": Restoring last document library...");}
            if(currdoclib!=null){wkspc.setCurrentDocumentLibrary(currdoclib);}
            if(isDebugEnabled){Jsp.LOGGER.finest(METHOD_NAME+": Logging out of workspace...");}
            wkspc.logout();
   			// JSP components should not call endWorkspace()	
			if(isTraceEnabled){Jsp.LOGGER.exiting(Jsp.CLASS_NAME,METHOD_NAME);} 
        }
    }
    if(isTraceEnabled){Jsp.LOGGER.exiting(Jsp.CLASS_NAME,METHOD_NAME);}
%>