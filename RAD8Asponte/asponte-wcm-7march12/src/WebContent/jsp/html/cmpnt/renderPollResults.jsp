<%@ page session="true" contentType="text/html" import="java.util.*,java.util.logging.*,com.ibm.workplace.wcm.api.*,com.asponte.wcm.widgets.*"
	%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"
	%><%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"
	%><%@ taglib uri="/WEB-INF/tld/wcm.tld" prefix="wcm"
	%><fmt:setBundle basename="com.asponte.wcm.widgets.resources.strings" 
	/><portlet:defineObjects 
	/><wcm:initworkspace user="<%= (java.security.Principal)request.getUserPrincipal() %>" 
	/><%
	// TODO: Get rid of portlet API dependency!!
	final String METHOD_NAME="renderPollResults.jsp";
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
			String docid=content.getId().getId();
			/* Step 5: Now, let's look for and grab the components we need */
			if (content.hasComponent(Constants.POLL_ANSWERS_TEXT)&&content.hasComponent(Constants.POLL_RESULTS_TEXT)&&
				content.hasComponent(Constants.POLL_BARCOLOR_TEXT)) {
				TextComponent anstc = (TextComponent)content.getComponent(Constants.POLL_ANSWERS_TEXT);
				TextComponent resulttc=(TextComponent)content.getComponent(Constants.POLL_RESULTS_TEXT);
				TextComponent bartc=(TextComponent)content.getComponent(Constants.POLL_BARCOLOR_TEXT);
				int total=0;
				int barHeight=18;
				int barMaxWidth=250;
				String barColor=bartc.getText();
				if(barColor==null||barColor.trim().length()==0){barColor="#333";}
				String ans=anstc.getText();
				if(ans==null){ans="";}
				String results=resulttc.getText();
				if(results==null){results="";}
				List answerList=Arrays.asList(ans.split(","));
				List resultList=Arrays.asList(results.split(","));
				if(answerList.size()==resultList.size()){
%><div id="<%=docid%>_chart" class="awwPollChart"><%
					java.text.DecimalFormat df=new java.text.DecimalFormat("###.#");
					int iii=0;
					for(Iterator itr=resultList.iterator();itr.hasNext();){
						String v=(String)itr.next();
						int tally;
						try{
							tally=Integer.parseInt(v);	
						}catch(NumberFormatException e){
							tally=0;
						}
						total+=tally;
					}
					for(Iterator itr=resultList.iterator(),itr2=answerList.iterator();itr.hasNext();iii++){
						String v=(String)itr.next();
						String u=(String)itr2.next();
						int tally;
						try{
							tally=Integer.parseInt(v);	
						}catch(NumberFormatException e){
							tally=0;
						}
						double pct=((double)tally/(double)total)*100;
						int fill=(int)((pct/100)*barMaxWidth);
						// TODO: $$NON-NLS$$
						u+=" "+df.format(pct)+"% ("+tally+" votes)";
		%><h4><%=u%></h4>
		<div style="border:1px solid #ccc;width:<%=barMaxWidth%>px;height:<%=barHeight%>px;"><div style="background-color:<%=barColor%>;height:<%=barHeight%>px;width:<%=fill%>px;"></div></div><%
					}
					// TODO: $$NON-NLS$$
				%>
</div><div class="awwPollTotals">Total Votes: <%=Integer.toString(total)%></div>
<div class="awwPollRefresh"><a href="<portlet:actionURL/>">Refresh poll results</a></div><%
				}else{
					// TODO: $$NON-NLS$$
				%><span>Poll results are not currently available.</span><%
				}	
			}
		}catch(Throwable t){
			if(isErrorEnabled){Jsp.LOGGER.log(Level.SEVERE,METHOD_NAME+": The results for the poll at path "+renderingContext.getPath(true)+" could not be rendered due to the following error: " +t.getMessage(),t);}
  			%><fmt:message key="<%=Results.RENDER_POLL_RESULTS_FAILED%>"><fmt:param value="<%=renderingContext.getPath(true)%>"/><fmt:param value="<%=t.getMessage()%>" /></fmt:message><%
        }finally {
			if (currdoclib != null) {wkspc.setCurrentDocumentLibrary(currdoclib);}
			wkspc.logout();
   			// JSP components should not call endWorkspace()
			if(isTraceEnabled){Jsp.LOGGER.exiting(Jsp.CLASS_NAME,METHOD_NAME);}
		}
	}
%>
