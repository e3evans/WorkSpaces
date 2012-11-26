<%@ page session="true" contentType="text/html" import="java.util.*,java.util.logging.*,com.ibm.workplace.wcm.api.*,com.asponte.wcm.widgets.*,com.asponte.commons.portal.wcm.*"
	%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"
	%><%@ taglib uri="/WEB-INF/tld/wcm.tld" prefix="wcm"
	%><fmt:setBundle basename="com.asponte.wcm.widgets.resources.strings" 
	/><wcm:initworkspace user="<%= (java.security.Principal)request.getUserPrincipal() %>" /><%
	final String METHOD_NAME="renderDocumentIcons.jsp";
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
                boolean foundVideo=false;
                boolean foundAttachment=false;
                if(content.hasComponent(Constants.VIDEO_VIDEO_FILE)){
                	FileComponent videoFile=(FileComponent)content.getComponentByReference(Constants.VIDEO_VIDEO_FILE);
                	if(videoFile.getSize()>0){foundVideo=true;}
                }
                if(!foundVideo&&content.hasComponent(Constants.VIDEO_VIDEO_EMBED)){
                	HTMLComponent embed=(HTMLComponent)content.getComponentByReference(Constants.VIDEO_VIDEO_EMBED);
                	String text=embed.getHTML();
                	if(text!=null&&(text=text.trim()).length()>0){foundVideo=true;}
                }
                if(!foundVideo&&content.hasComponent(Constants.VIDEO_VIDEO_LINK_1)){
                	LinkComponent link=(LinkComponent)content.getComponentByReference(Constants.VIDEO_VIDEO_LINK_1);
                	int linkType=link.getLinkType();
                	String url=link.getURL();
                	DocumentId refid=link.getDocumentReference();
                	if(linkType==LinkComponent.TYPE_EXTERNALURL&&url!=null&&(url=url.trim()).length()>0){
                	   	foundVideo=true;
                	}else if(linkType==LinkComponent.TYPE_MANAGEDCONTENT&&
                			 (refid=link.getDocumentReference())!=null&&
                			 refid.isOfType(DocumentTypes.Content)){
                		foundVideo=true;
                		content=(Content)wkspc.getById(refid,true);
                		parent=(SiteArea)wkspc.getById(content.getDirectParent(),true);
                	}
                }
                if(content.hasComponent(Constants.ATTACHMENT_FILE)){
                	FileComponent attchFile=(FileComponent)content.getComponentByReference(Constants.ATTACHMENT_FILE);
                	if(attchFile.getSize()>0){foundAttachment=true;}
                }
               	Map params=new HashMap();
                params.put("presentationtemplate",awwDesignLib.getName()+"/rich text");
                if(foundVideo){
                	Utils.renderCmpnt(pageContext,wkspc,awwDesignLib,content,parent,Constants.VIDEO_ICON_HTML,params);
   				}
   				if(foundAttachment){
   					Utils.renderCmpnt(pageContext,wkspc,awwDesignLib,content,parent,Constants.ATTACHMENT_ICON_HTML,params);
   				}
        	}else{
   				if(isErrorEnabled){Jsp.LOGGER.severe(METHOD_NAME+": No widget configuration was found for the content with id "+content.getId()+"!");}
   				%><fmt:message key="<%=Results.RENDER_VIDEO_ICON_WIDGET_CONFIG_NOT_FOUND%>"><fmt:param value="<%=renderingContext.getPath(true)%>"/></fmt:message><%
   			}
        }catch(Throwable t){
			if(isErrorEnabled){Jsp.LOGGER.log(Level.SEVERE,METHOD_NAME+": The document icons for the content at path "+renderingContext.getPath(true)+" could not be rendered due to the following error: " +t.getMessage(),t);}
  			%><fmt:message key="<%=Results.RENDER_VIDEO_ICON_FAILED%>"><fmt:param value="<%=renderingContext.getPath(true)%>"/><fmt:param value="<%=t.getMessage()%>" /></fmt:message><%
        }finally{
   			if(currdoclib!=null){wkspc.setCurrentDocumentLibrary(currdoclib);}
   			wkspc.logout();        	
   			// JSP components should not call endWorkspace()	
			if(isTraceEnabled){Jsp.LOGGER.exiting(Jsp.CLASS_NAME,METHOD_NAME);}       		
       	}
	}
%>