<%@ page session="true" contentType="text/html" import="java.util.*,java.util.logging.*,com.ibm.workplace.wcm.api.*,com.asponte.wcm.widgets.*,com.asponte.commons.portal.wcm.*"
	%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"
	%><%@ taglib uri="/WEB-INF/tld/wcm.tld" prefix="wcm"
	%><fmt:setBundle basename="com.asponte.wcm.widgets.resources.strings" 
	/><wcm:initworkspace user="<%= (java.security.Principal)request.getUserPrincipal() %>" /><%
	final String METHOD_NAME="renderVideo.jsp";
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
			Content content=null;
       		wkspc.login();
			try {currdoclib = wkspc.getCurrentDocumentLibrary();} catch (NullPointerException e) {}
   	        DocumentId docid=renderingContext.getCurrentResultId();
   	        if(docid!=null){content=(Content)wkspc.getById(docid,true);}
   	        else{content=renderingContext.getContent();}
   			DocumentLibrary awwDesignLib=wkspc.getDocumentLibrary(AwwConfig.getDesignLibraryFor(content));
   			if(awwDesignLib!=null){
				SiteArea parent=(SiteArea)wkspc.getById(content.getDirectParent(),true);
				LibraryComponent cmpnt=null;
                if(content.hasComponent(Constants.VIDEO_VIDEO_FILE)){
                	FileComponent videoFile=(FileComponent)content.getComponentByReference(Constants.VIDEO_VIDEO_FILE);
                	if(videoFile.getSize()>0){
       					wkspc.setCurrentDocumentLibrary(awwDesignLib);
       					DocumentIdIterator itr=wkspc.findComponentByName(Constants.VIDEO_VIDEO_HTML);
       					if(itr.hasNext()){
       						cmpnt=(LibraryComponent)wkspc.getById(itr.nextId(),true);
       					}
                	}
                }
                if(cmpnt==null&&content.hasComponent(Constants.VIDEO_VIDEO_EMBED)){
                	HTMLComponent embed=(HTMLComponent)content.getComponentByReference(Constants.VIDEO_VIDEO_EMBED);
                	String text=embed.getHTML();
                	if(text!=null&&(text=text.trim()).length()>0){
       					wkspc.setCurrentDocumentLibrary(awwDesignLib);
       					DocumentIdIterator itr=wkspc.findComponentByName(Constants.VIDEO_VIDEO_EMBED_HTML);
       					if(itr.hasNext()){
       						cmpnt=(LibraryComponent)wkspc.getById(itr.nextId(),true);
       					}		                		
                	}
                }
                if(cmpnt==null&&content.hasComponent(Constants.VIDEO_VIDEO_LINK_1)){
                	LinkComponent link=(LinkComponent)content.getComponentByReference(Constants.VIDEO_VIDEO_LINK_1);
                	String url=link.getURL();
                	if(url!=null&&(url=url.trim()).length()>0){
       					wkspc.setCurrentDocumentLibrary(awwDesignLib);
       					DocumentIdIterator itr=wkspc.findComponentByName(Constants.VIDEO_EXTERNAL_VIDEO_HTML);
       					if(itr.hasNext()){
       						cmpnt=(LibraryComponent)wkspc.getById(itr.nextId(),true);
       					}                		
                	}
                }
                if(cmpnt!=null){
					RenderingContext renderingContext2=wkspc.createRenderingContext(request,response,new HashMap());
					renderingContext2.setRenderedContent(content,parent);
					%><%=wkspc.render(renderingContext2,cmpnt)%><%		                	
                }
   			}else{
   				if(isErrorEnabled){Jsp.LOGGER.severe(METHOD_NAME+": No widget configuration was found for the video with id "+content.getId()+"!");}
   				%><fmt:message key="<%=Results.RENDER_VIDEO_WIDGET_CONFIG_NOT_FOUND%>"><fmt:param value="<%=docid%>"/></fmt:message><%
   			}
        }catch(Throwable t){
        	DocumentId docid=renderingContext.getCurrentResultId();
        	if(docid==null){docid=renderingContext.getContent().getId();}
			if(isErrorEnabled){Jsp.LOGGER.log(Level.SEVERE,METHOD_NAME+": The video with id "+docid+" could not be rendered due to the following error: " +t.getMessage(),t);}
  			%><fmt:message key="<%=Results.RENDER_VIDEO_FAILED%>"><fmt:param value="<%=docid%>"/><fmt:param value="<%=t.getMessage()%>" /></fmt:message><%
        }finally{
   			if(currdoclib!=null){wkspc.setCurrentDocumentLibrary(currdoclib);}
   			wkspc.logout();        	
   			// JSP components should not call endWorkspace()	
			if(isTraceEnabled){Jsp.LOGGER.exiting(Jsp.CLASS_NAME,METHOD_NAME);}
       	}
	}
%>