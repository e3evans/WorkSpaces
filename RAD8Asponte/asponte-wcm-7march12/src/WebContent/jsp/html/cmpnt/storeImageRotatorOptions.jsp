<%@ page session="false" contentType="text/html" import="java.util.*,java.util.logging.*,com.ibm.workplace.wcm.api.*,com.asponte.wcm.widgets.*,com.asponte.commons.portal.wcm.*"
    %><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"
	%><%@ taglib uri="/WEB-INF/tld/wcm.tld" prefix="wcm" 
    %><fmt:setBundle basename="com.asponte.wcm.widgets.resources.strings" 
	/><wcm:initworkspace user="<%= (java.security.Principal)request.getUserPrincipal() %>" /><%
    final String METHOD_NAME="storeImageRotatorOptions.jsp";
    boolean isTraceEnabled=Jsp.LOGGER.isLoggable(Level.FINER);
	boolean isDebugEnabled=Jsp.LOGGER.isLoggable(Level.FINEST);
	boolean isErrorEnabled=Jsp.LOGGER.isLoggable(Level.SEVERE);
    if(isTraceEnabled){Jsp.LOGGER.entering(Jsp.CLASS_NAME,METHOD_NAME,request.getParameterMap());}
    // Get the rendering context and workspace from the request
    Workspace wkspc=(Workspace)pageContext.getAttribute(Workspace.WCM_WORKSPACE_KEY);
    RenderingContext renderingContext = (RenderingContext)request.getAttribute(Workspace.WCM_RENDERINGCONTEXT_KEY);
	response.setContentType("application/json; charset=ISO-8859-1");		
	boolean success=false;
	List results=new ArrayList(); 
  	String _uuids=null;
    String siteArea=null;
    String effect=null;
    _uuids=com.asponte.commons.portal.Utils.param(request,"uuid");
   	if(com.asponte.commons.portal.Utils.empty(_uuids)){
        // TODO: Correct error message and Result ID
		if(isErrorEnabled){Jsp.LOGGER.log(Level.SEVERE,METHOD_NAME+": Missing uuid ID parameter (enable trace to see parameter dump)!");}
		results.add(new ActionResult(Result.WARNING,Results.STORE_IMGROT_OPTIONS_MISSING_UUIDS));
	}
    siteArea=com.asponte.commons.portal.Utils.param(request,"siteArea");
   	if(com.asponte.commons.portal.Utils.empty(siteArea)){
        // TODO: Correct error message and Result ID
		if(isErrorEnabled){Jsp.LOGGER.log(Level.SEVERE,METHOD_NAME+": Missing site area ID parameter (enable trace to see parameter dump)!");}
		results.add(new ActionResult(Result.WARNING,Results.STORE_IMGROT_OPTIONS_MISSING_SA));
	}    
    effect=com.asponte.commons.portal.Utils.param(request,"effect");
   	if(com.asponte.commons.portal.Utils.empty(effect)){
        // TODO: Correct error message and Result ID
		if(isErrorEnabled){Jsp.LOGGER.log(Level.SEVERE,METHOD_NAME+": Missing transition effect parameter (enable trace to see parameter dump)!");}
		results.add(new ActionResult(Result.WARNING,Results.STORE_IMGROT_OPTIONS_MISSING_EFFECT));
	}    
    if(results.isEmpty()&&wkspc!=null&&renderingContext!=null){
        DocumentLibrary currdoclib=null;
        try{
            if(isDebugEnabled){Jsp.LOGGER.finest(METHOD_NAME+": Logging in to workspace...");}
            wkspc.login();
            if(isDebugEnabled){Jsp.LOGGER.finest(METHOD_NAME+": Saving current library...");}
            try{currdoclib=wkspc.getCurrentDocumentLibrary();}catch(NullPointerException e){}
            if(isDebugEnabled){Jsp.LOGGER.finest(METHOD_NAME+": Loading our library...");}
		    
		    /* Handle the display order */
		    
			String[] uuids = _uuids.split(","); 
			DocumentId tempDocId = null;
			ArrayList docIdList = new ArrayList();
			for(int x=0;x<uuids.length;x++)
			{
			     docIdList.add(wkspc.createDocumentId(uuids[x]));
			}               
                   
			// now, since they are passed first, second, third, etc
			// just place them last under the top level site area.  That way, the
			// first will be last, then second be last which moved first up 
			// and so on         
			DocumentId parentId = null;
			Content tempContent = null;
			Iterator idIterator = docIdList.iterator();
			while(idIterator.hasNext())
			{
				tempDocId = (DocumentId)idIterator.next();
				tempContent = (Content)wkspc.getById(tempDocId);
				if(parentId == null)
				{
				 	parentId = tempContent.getDirectParent();
				}
				wkspc.moveSiteFrameworkDocument(tempDocId,parentId,null,ChildPosition.END);
			}

			/* Now handle the transition effect */		    

			SiteArea tempSA = null;
			TextComponent tc = null;
	        tempDocId = wkspc.createDocumentId(siteArea);
			tempSA = (SiteArea)wkspc.getById(tempDocId);
			if(tempSA.hasComponent("sliderEffect")){
				tc=(TextComponent)tempSA.getComponent("sliderEffect");
			}else{
				tc=(TextComponent)tempSA.createComponent("sliderEffect",DocumentTypes.ShortTextComponent);
			}
			if(tc != null)
			{
				tc.setText(effect);
				tempSA.setComponent("sliderEffect",tc);
				String []errors=wkspc.save(tempSA);
				if(errors.length==0){
					success=true;
				}else{
					for(int ii=0;ii<errors.length;ii++){	
            			// TODO: Correct error message and Result ID		
						if(isErrorEnabled){Jsp.LOGGER.log(Level.SEVERE,METHOD_NAME+": The image rotator with the id "+tempDocId+" could not be updated due to the following WCM error: " +errors[ii]);}
						results.add(new ActionResult(Result.ERROR,Results.STORE_IMGROT_OPTIONS_SAVE_FAILED,new Object[] {tempDocId,errors[ii]}));
					}
				}
			}
        }catch(Throwable t){
            // TODO: Correct error message and Result ID
			if(isErrorEnabled){Jsp.LOGGER.log(Level.SEVERE,METHOD_NAME+": The options for the image rotator at path "+renderingContext.getPath(true)+" could not be stored due to the following error: " +t.getMessage(),t);}
			results.add(new ActionResult(Result.ERROR,Results.STORE_IMGROT_OPTIONS_FAILED,new Object[] {renderingContext.getPath(true),t.getMessage()}));
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
    boolean jsonResult=success;
	List jsonResults=results;
	%><%@ include file="../includes/jsonResponse.jspf" %>