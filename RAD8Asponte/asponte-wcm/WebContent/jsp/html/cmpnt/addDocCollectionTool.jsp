<%@ page session="true" contentType="text/html" import="java.util.*,java.util.logging.*,com.ibm.workplace.wcm.api.*,com.asponte.wcm.widgets.*"
    %><%@ taglib uri="/WEB-INF/tld/std-portlet.tld" prefix="portlet" 
    %><%@ taglib uri="/WEB-INF/tld/wcm.tld" prefix="wcm" 
    %><portlet:defineObjects
    /><wcm:initworkspace user="<%= (java.security.Principal)request.getUserPrincipal() %>" /><%
    final String METHOD_NAME="addDocCollectionTool.jsp";
    //TODO: Review and enhance tracing
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
    // TODO: Bring this file in synch with best practices jsp cmpnt template
            wkspc.login();
            try{currdoclib=wkspc.getCurrentDocumentLibrary();}catch(NullPointerException e){}
            SiteArea []catPath=renderingContext.getSiteAreas();
            SiteArea docCatalog=catPath[catPath.length-1];
    // TODO: NON-NLS
    %><a class="awwAuthTool"
         href="javascript:asponte.aww.dialogs.addDocCollectionDialog.show({catalogId:'<%=docCatalog.getId().getId()%>',returnUrl:'<portlet:renderURL />'});" 
         target="" title="Add a new collection of documents" class="awwAuthTool">Add documents</a><%
        }finally{
            if(currdoclib!=null){wkspc.setCurrentDocumentLibrary(currdoclib);}
            wkspc.logout();
        }
    }
    if(isTraceEnabled){Jsp.LOGGER.exiting(Jsp.CLASS_NAME,METHOD_NAME);}
%>
