<%@ page session="true" contentType="text/html" import="java.util.*,java.util.logging.*,com.ibm.workplace.wcm.api.*,com.asponte.wcm.widgets.*"
	%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"
	%><%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"
	%><%@ taglib uri="/WEB-INF/tld/wcm.tld" prefix="wcm"
	%><fmt:setBundle basename="com.asponte.wcm.widgets.resources.strings" 
	/><portlet:defineObjects 
	/><wcm:initworkspace user="<%= (java.security.Principal)request.getUserPrincipal() %>" 
	/><%
	// TODO: Get rid of portlet API dependency!!
	final String METHOD_NAME="renderPollForm.jsp";
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
			Content content = renderingContext.getContent();
			String ns=content.getId().getId();
			%><script type="text/javascript">
			dojo.require('dojox.widget.Standby');
			dojo.addOnLoad(function(){
				if(!document._pollVoteStandby){
					/* dewittsc - DEF019 */
					var standby=new dojox.widget.Standby({target:dojo.body()});
					/* dewittsc - DEF019 */
        			document.body.appendChild(standby.domNode);
        			standby.startup();
					document._pollVoteStandby=standby;
				}
			});
			function _<%=ns%>_vote(){
				var resultUrl='<portlet:actionURL/>';
				function _handleSuccess(result,ioargs){
					if(result.success){document.location=resultUrl;}
					// TODO: $$NON-NLS$$
					else{_handlerError('Vote could not be cast!',ioargs);}
					document._pollVoteStandby.hide();
				}
				function _handleError(jsError,ioargs){
					alert(jsError);
					document._pollVoteStandby.hide();
				}
				document._pollVoteStandby.show();
				dojo.xhrPost({
					url:'/aww/jsp/html/actions/pollVote.jsp',
					form:'<%=ns%>_poll',
					handleAs:'json',
					load:_handleSuccess,
					error:_handleError
				});	
			}
			</script><form method="post" action="" id="<%=ns%>_poll" name="<%=ns%>_poll"><input type="hidden" name="docid" value="<%=content.getId().toString()%>" /><%
			/* Step 5: Now, let's look for and grab the components we need */
			if (content.hasComponent("Answers")) {
				TextComponent tc = (TextComponent)content.getComponentByReference("Answers");
				String txt=tc.getText();
				if(txt!=null){
					String []answers=txt.split(",");
					if(answers.length>0){
				%><div><ul><%
						for(int iii=0;iii<answers.length;iii++){
				%><li><input id="<%=ns%>_poll_choice_<%=Integer.toString(iii)%>" type="radio" name="answer" value="<%=Integer.toString(iii)%>" <%if(iii==0){%>checked="checked"<%}%>/><label for="<%=ns%>_poll_choice_<%=Integer.toString(iii)%>"><%=answers[iii]%></label></li><%
						}
				%></ul></div><%
					}
				}
			}%><button type="button" onClick="_<%=ns%>_vote();">Vote</button></form><%
		}catch(Throwable t){
			if(isErrorEnabled){Jsp.LOGGER.log(Level.SEVERE,METHOD_NAME+": The form for the poll at path "+renderingContext.getPath(true)+" could not be rendered due to the following error: " +t.getMessage(),t);}
  			%><fmt:message key="<%=Results.RENDER_POLL_FORM_FAILED%>"><fmt:param value="<%=renderingContext.getPath(true)%>"/><fmt:param value="<%=t.getMessage()%>" /></fmt:message><%
        }finally {
			if (currdoclib != null) {wkspc.setCurrentDocumentLibrary(currdoclib);}
			wkspc.logout();
   			// JSP components should not call endWorkspace()
			if(isTraceEnabled){Jsp.LOGGER.exiting(Jsp.CLASS_NAME,METHOD_NAME);}
		}
	}
%>