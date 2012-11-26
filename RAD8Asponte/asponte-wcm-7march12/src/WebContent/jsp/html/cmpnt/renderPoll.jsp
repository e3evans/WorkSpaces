<%@ page session="true" contentType="text/html" import="java.util.*,java.util.logging.*,com.ibm.workplace.wcm.api.*,com.asponte.wcm.widgets.*,com.asponte.commons.portal.wcm.*"
	%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"
	%><%@ taglib uri="/WEB-INF/tld/wcm.tld" prefix="wcm"
	%><fmt:setBundle basename="com.asponte.wcm.widgets.resources.strings" 
	/><%!
	private static com.ibm.portal.um.PumaHome PUMA_HOME;
	private static com.ibm.portal.identification.Identification IDENTIFICATION;
	public void jspInit(){
	      try{
	    	  javax.naming.Context ctx = new javax.naming.InitialContext();
	    	  javax.naming.Name myjndiname = new javax.naming.CompositeName(com.ibm.portal.um.PumaHome.JNDI_NAME);
	    	  PUMA_HOME = (com.ibm.portal.um.PumaHome) ctx.lookup(myjndiname);
			  IDENTIFICATION = (com.ibm.portal.identification.Identification) ctx.lookup( "portal:service/Identification" );
	      }catch(javax.naming.NamingException e){
	    	  throw new RuntimeException("Missing PumaHome!",e);
	      }
	}
	public String getCurrentUserObjectId() throws com.ibm.portal.um.exceptions.PumaException, com.ibm.portal.serialize.SerializationException{
		com.ibm.portal.um.User currUser=PUMA_HOME.getProfile().getCurrentUser();
		return serialize(currUser.getObjectID());	
	}
    protected String serialize( com.ibm.portal.ObjectID oid ) throws com.ibm.portal.serialize.SerializationException {
        return IDENTIFICATION.serialize( oid );
    }
	%><wcm:initworkspace user="<%= (java.security.Principal)request.getUserPrincipal() %>" 
	/><%
	final String METHOD_NAME="renderPoll.jsp";
	boolean isTraceEnabled=Jsp.LOGGER.isLoggable(Level.FINER);
	boolean isDebugEnabled=Jsp.LOGGER.isLoggable(Level.FINEST);
	boolean isErrorEnabled=Jsp.LOGGER.isLoggable(Level.SEVERE);
	if(isTraceEnabled){Jsp.LOGGER.entering(Jsp.CLASS_NAME,METHOD_NAME);}		
	Workspace wkspc = (Workspace) pageContext.getAttribute(Workspace.WCM_WORKSPACE_KEY);
	RenderingContext renderingContext = (RenderingContext) request.getAttribute(Workspace.WCM_RENDERINGCONTEXT_KEY);
	if (wkspc != null && renderingContext != null) {
		DocumentLibrary currdoclib = null;
		boolean displayResults=false;
		try {
			Content content=null;
			/* Step 2: Login */
			wkspc.login();
			/* Step 3: Save the current library, if any, and load our libraries */
			try {currdoclib = wkspc.getCurrentDocumentLibrary();} catch (NullPointerException e) {}
			/* Step 4: Grab the current content */
			content = renderingContext.getContent();
			/* Lookup the widget config */
   			DocumentLibrary awwDesignLib=wkspc.getDocumentLibrary(AwwConfig.getDesignLibraryFor(content));
			if(awwDesignLib!=null){
				/* Step 5: Now, let's look for and grab the components we need */
				if (content.hasComponent(Constants.POLL_USERS_TEXT)) {
					TextComponent tc = (TextComponent)content.getComponentByReference(Constants.POLL_USERS_TEXT);
					String txt=tc.getText();
					if(txt!=null){
						String objectId=getCurrentUserObjectId();
						if(txt.contains(":"+objectId+":")){displayResults=true;}
					}
				}
				wkspc.setCurrentDocumentLibrary(awwDesignLib);
				if(displayResults){
					DocumentIdIterator itr=wkspc.findComponentByName(Constants.POLL_RESULTS_HTML);
					if(itr.hasNext()){
						LibraryComponent cmpnt=(LibraryComponent)wkspc.getById(itr.nextId(),true);
						%><%=wkspc.render(renderingContext,cmpnt)%><%
					}
				}else{
					DocumentIdIterator itr=wkspc.findComponentByName(Constants.POLL_HTML);
					if(itr.hasNext()){
						LibraryComponent cmpnt=(LibraryComponent)wkspc.getById(itr.nextId(),true);
						%><%=wkspc.render(renderingContext,cmpnt)%><%
					}
				}
			}else{
   				if(isErrorEnabled){Jsp.LOGGER.severe(METHOD_NAME+": No widget configuration was found for the poll at "+renderingContext.getPath(true)+"!");}
   				%><fmt:message key="<%=Results.RENDER_POLL_WIDGET_CONFIG_NOT_FOUND%>"><fmt:param value="<%=renderingContext.getPath(true)%>"/></fmt:message><%
			}
		}catch(Throwable t){
			if(isErrorEnabled){Jsp.LOGGER.log(Level.SEVERE,METHOD_NAME+": The poll at path "+renderingContext.getPath(true)+" could not be rendered due to the following error: " +t.getMessage(),t);}
  			%><fmt:message key="<%=Results.RENDER_POLL_FAILED%>"><fmt:param value="<%=renderingContext.getPath(true)%>"/><fmt:param value="<%=t.getMessage()%>" /></fmt:message><%
        }finally {
			if (currdoclib != null) {wkspc.setCurrentDocumentLibrary(currdoclib);}
			wkspc.logout();
   			// JSP components should not call endWorkspace()
			if(isTraceEnabled){Jsp.LOGGER.exiting(Jsp.CLASS_NAME,METHOD_NAME);}
		}
	}
%>
