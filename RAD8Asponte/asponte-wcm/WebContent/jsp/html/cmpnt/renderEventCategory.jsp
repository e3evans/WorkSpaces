<%@ page session="true" contentType="text/html" import="java.util.*,java.util.logging.*,com.ibm.workplace.wcm.api.*,com.asponte.wcm.widgets.*"
	%><%@ taglib uri="/WEB-INF/tld/wcm.tld" prefix="wcm"
	%><wcm:initworkspace user="<%= (java.security.Principal)request.getUserPrincipal() %>" /><%
	final String METHOD_NAME="eventCategory.jsp";
	boolean isTraceEnabled=Jsp.LOGGER.isLoggable(Level.FINER);
	boolean isDebugEnabled=Jsp.LOGGER.isLoggable(Level.FINEST);
	boolean isErrorEnabled=Jsp.LOGGER.isLoggable(Level.SEVERE);
	if(isTraceEnabled){Jsp.LOGGER.entering(Jsp.CLASS_NAME,METHOD_NAME);}	
	/* Step 1: Grab the workspace and rendering context from the request */
	Workspace wkspc = (Workspace) pageContext.getAttribute(Workspace.WCM_WORKSPACE_KEY);
	RenderingContext renderingContext = (RenderingContext) request.getAttribute(Workspace.WCM_RENDERINGCONTEXT_KEY);
	if (wkspc != null && renderingContext != null) {
		DocumentLibrary currdoclib = null;
		try {
			/* Step 2: login */
			wkspc.login();
			/* Step 3: Save the current library, if any, and load our libraries */
			try {currdoclib = wkspc.getCurrentDocumentLibrary();} catch (NullPointerException e) {}
			/* Step 4: Grab the current content */
			Content content=null;
			DocumentId currResult=renderingContext.getCurrentResultId();
			if(currResult!=null){content=(Content)wkspc.getById(currResult,true);}
			else{content=renderingContext.getContent();}
			DocumentId []catids=content.getCategoryIds();
			String title=null,className=null;
			// TODO: There's likely to be more than one cat, what do to
			// how to recognize the cat that holds the style? maybe by checking parent tax?
			if(catids.length>0){
				for(int xyz=0;className==null&&xyz<catids.length;xyz++){
					Category cat=(Category)wkspc.getById(catids[xyz],true);
					String desc=cat.getDescription();
					if(desc!=null&&desc.length()>0){
						title=cat.getTitle();
						className=desc;
					}
				}
			}
			if(className!=null){%>{'title':'<%=title%>','className':'<%=className%>'}<%}
			else{%>{'title':'','className':'eventDefault'}<%}
		}catch(Throwable t){
        	DocumentId docid=renderingContext.getCurrentResultId();
        	if(docid==null){docid=renderingContext.getContent().getId();}
			if(isErrorEnabled){Jsp.LOGGER.log(Level.SEVERE,METHOD_NAME+": The category for the content with id "+docid+" could not be rendered due to the following error: " +t.getMessage(),t);}
  			%><fmt:message key="<%=Results.RENDER_EVENT_CATEGORY_FAILED%>"><fmt:param value="<%=docid%>"/><fmt:param value="<%=t.getMessage()%>" /></fmt:message><%
        }finally {
			if (currdoclib != null) {
				wkspc.setCurrentDocumentLibrary(currdoclib);
			}
			wkspc.logout();
		}
	}
%>
