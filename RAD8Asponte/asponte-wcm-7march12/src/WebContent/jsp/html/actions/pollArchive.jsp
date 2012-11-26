<%@ page session="false" isELIgnored="false" import="java.util.*,java.util.logging.*,com.ibm.workplace.wcm.api.*,com.ibm.workplace.wcm.api.exceptions.*,com.asponte.commons.*,com.asponte.commons.portal.*,com.asponte.commons.portal.wcm.*,com.asponte.wcm.widgets.*" 
	%><%@ taglib uri="/WEB-INF/tld/wcm.tld" prefix="wcm"
	%><wcm:initworkspace user="<%=request.getUserPrincipal()%>" /><%
	final String METHOD_NAME="pollArchive.jsp";
	boolean isTraceEnabled=Jsp.LOGGER.isLoggable(Level.FINER);
	boolean isDebugEnabled=Jsp.LOGGER.isLoggable(Level.FINEST);
	boolean isErrorEnabled=Jsp.LOGGER.isLoggable(Level.SEVERE);
	if(isTraceEnabled){Jsp.LOGGER.entering(Jsp.CLASS_NAME,METHOD_NAME,new Object[]{request.getParameterMap()});}	
	response.setContentType("application/json; charset=ISO-8859-1");
	String idnum=com.asponte.commons.portal.Utils.param(request,"idnum");
	boolean success=false;
	List results=new ArrayList();
	if(!com.asponte.commons.portal.Utils.empty(idnum)){
		Workspace workspace = (Workspace) pageContext.getAttribute(Workspace.WCM_WORKSPACE_KEY);
		DocumentLibrary currDocLib=null;
		DocumentLibrary contentLib=null;
		try{
			workspace.login();
			try{currDocLib=workspace.getCurrentDocumentLibrary();}catch(NullPointerException ignore){/*This can safely be ignored per best practices.*/}
			DocumentId docid=workspace.createDocumentId(idnum);
			Content content=(Content)workspace.getById(docid,true);
			Properties widgetConfig=AwwConfig.getConfigFor(content);
			if(widgetConfig!=null){
				String saPath=AwwConfig.getTemplateParameter(widgetConfig,AwwConfig.POLL_TEMPLATE_ID,AwwConfig.ARCHIVE_SA_TEMPLATE_PARAM);
				if(saPath!=null&&(saPath=saPath.trim()).length()>0){
					contentLib=content.getOwnerLibrary();
					DocumentIdIterator itr=workspace.findByPath("/"+contentLib.getName()+saPath,Workspace.WORKFLOWSTATUS_PUBLISHED);
					if(itr.hasNext()){
						DocumentId newpid=itr.nextId();
						Content copy=(Content)workspace.copySiteFrameworkDocument(docid,newpid,null,ChildPosition.END);	
						/* dewittsc - DEF008 */
						String name=copy.getName();
						name+="_ARCHIVE_"+System.currentTimeMillis();
						copy.setName(name);
						/* dewittsc - DEF008 */
                        /* dewittsc - Add poll close date */
                        copy.setGeneralDateOne(new Date());
                        /* dewittsc - Add poll close date */						
						String []errors=workspace.save(copy);
						if(errors.length==0){
							success=true;
						}else{
							for(int ii=0;ii<errors.length;ii++){			
								if(isErrorEnabled){Jsp.LOGGER.log(Level.SEVERE,METHOD_NAME+": The poll with the id "+idnum+" could not be copied to the archive area due to the following WCM error: " +errors[ii]);}
								results.add(new ActionResult(Result.ERROR,Results.ARCHIVE_POLL_SAVE_FAILED,new Object[] {idnum,errors[ii]}));
							}
						}
					}else{
						if(isErrorEnabled){Jsp.LOGGER.log(Level.SEVERE,METHOD_NAME+": The poll with the id "+idnum+" could not be archived because the archive site area could not be found!");}
						results.add(new ActionResult(Result.ERROR,Results.ARCHIVE_POLL_SA_NOT_FOUND,new Object[] {idnum}));
					}
				}else{
					if(isErrorEnabled){Jsp.LOGGER.log(Level.SEVERE,METHOD_NAME+": The poll with the id "+idnum+" could not be archived because the archive site area is missing!");}
					results.add(new ActionResult(Result.ERROR,Results.ARCHIVE_POLL_MISSING_SA_PATH,new Object[] {idnum}));
				}
			}else{
				if(isErrorEnabled){Jsp.LOGGER.severe(METHOD_NAME+": No widget configuration was found for the content with id "+idnum+" ("+content.getTitle()+")!");}
				results.add(new ActionResult(Result.ERROR,Results.ARCHIVE_POLL_WIDGET_CONFIG_NOT_FOUND,new Object[] {idnum,content.getTitle()}));   				
			}
		}catch(Throwable t){
			if(isErrorEnabled){Jsp.LOGGER.log(Level.SEVERE,METHOD_NAME+": The poll with the id "+idnum+" could not be archived due to the following error: " +t.getMessage(),t);}
			results.add(new ActionResult(Result.ERROR,Results.ARCHIVE_POLL_FAILED,new Object[] {idnum,t.getMessage()},t));
	    }finally{
			if(currDocLib!=null){workspace.setCurrentDocumentLibrary(currDocLib);}
			workspace.logout();
			// Non-JSP components must call endWorkspace()
			WCM_API.getRepository().endWorkspace();	
			if(isTraceEnabled){Jsp.LOGGER.exiting(Jsp.CLASS_NAME,METHOD_NAME,Boolean.valueOf(success));}
		}
	}else{
		if(isErrorEnabled){Jsp.LOGGER.log(Level.SEVERE,METHOD_NAME+": Missing required parameters (enable trace to see parameter dump)!");}
		results.add(new ActionResult(Result.ERROR,Results.ARCHIVE_POLL_INVALID_INPUT));
	}
	boolean jsonResult=success;
	List jsonResults=results;
	%><%@ include file="../includes/jsonResponse.jspf" %>