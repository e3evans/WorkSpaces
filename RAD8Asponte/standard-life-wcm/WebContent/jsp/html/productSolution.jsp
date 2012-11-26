<%@page import="java.net.URLEncoder"%>
<%@ page session="false" contentType="text/html" import="java.util.*,com.ibm.workplace.wcm.api.*,com.ibm.workplace.wcm.api.exceptions.*,ca.standardlife.wcm.*,javax.naming.*,com.ibm.portal.model.*,com.ibm.portal.navigation.*"
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
    	SiteArea currSa=null;
    	try{
			// 3. Login
		    wkspc.login();
			// 4. Save the current library and ignore if there is not one
		    try{currdoclib=wkspc.getCurrentDocumentLibrary();}catch(NullPointerException e){}
			// 5. Get your library(ies)
			DocumentLibrary designLibrary=wkspc.getDocumentLibrary(Constants.DESIGN_LIBRARY);
			// 6. Do your stuff					
			DocumentId docid=renderingContext.getCurrentResultId();
			if(docid!=null){
				Document doc=wkspc.getById(docid,true);
				Content content=null;
				// TODO: products and solutions and sell sheets should always be links so might want
				// to have separate JSP that allows the use of content rather than links - but for now,
				// do with a single JSP that handles both.
				if(doc.getId().isOfType(DocumentTypes.ContentLink)){
					ContentLink contentLink=(ContentLink)doc;
					content=(Content)wkspc.getById(contentLink.getContentId(),true);
				}else{
					content=(Content)doc;
				}
				DocumentId at=content.getAuthoringTemplateID();
				String atName=at.getName();
				String cmpntName=Constants.PRODUCTS_SOLUTIONS_ITEM;
				if(atName.equalsIgnoreCase(Constants.AT_TESTIMONIAL)){
					cmpntName=Constants.TESTIMONIAL_ITEM;	
				}else if(atName.equalsIgnoreCase(Constants.AT_SELL_SHEET)){
					if(content.hasComponent(Constants.AT_SELL_SHEET_LINK)){
						LinkComponent link=(LinkComponent)content.getComponentByReference(Constants.AT_SELL_SHEET_LINK);
						if(link.getLinkType()==LinkComponent.TYPE_MANAGEDCONTENT){
							DocumentId ref=link.getDocumentReference();
							if(ref.getType()==DocumentTypes.Content){
								if(wkspc.hasUserAccess(ref)){
									content=(Content)wkspc.getById(ref,true);
								}
							}
						}
					}
				}
				LibraryComponent cmpnt=null;
				wkspc.setCurrentDocumentLibrary(designLibrary);
				DocumentIdIterator itr=wkspc.findComponentByName(cmpntName);
				if(itr.hasNext()){
					cmpnt=(LibraryComponent)wkspc.getById(itr.nextId(),true);
					if(currdoclib!=null){wkspc.setCurrentDocumentLibrary(currdoclib);}
					String path=wkspc.getPathById(content.getId(),false,true);
					RenderingContext altrc=wkspc.createRenderingContext(request, response, new HashMap());
					altrc.setRenderedContent(path);
					%><%=wkspc.render(altrc,cmpnt)%><%
				}
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
