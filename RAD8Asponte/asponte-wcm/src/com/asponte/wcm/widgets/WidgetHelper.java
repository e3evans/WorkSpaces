package com.asponte.wcm.widgets;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asponte.commons.Result;
import com.asponte.commons.portal.wcm.AwwConfig;
import com.ibm.workplace.wcm.api.ChildPosition;
import com.ibm.workplace.wcm.api.Content;
import com.ibm.workplace.wcm.api.Document;
import com.ibm.workplace.wcm.api.DocumentId;
import com.ibm.workplace.wcm.api.DocumentIdIterator;
import com.ibm.workplace.wcm.api.DocumentLibrary;
import com.ibm.workplace.wcm.api.DocumentTypes;
import com.ibm.workplace.wcm.api.SiteArea;
import com.ibm.workplace.wcm.api.WCM_API;
import com.ibm.workplace.wcm.api.Workspace;
import com.ibm.workplace.wcm.api.exceptions.AuthorizationException;
import com.ibm.workplace.wcm.api.exceptions.DocumentCreationException;
import com.ibm.workplace.wcm.api.exceptions.DocumentIdCreationException;
import com.ibm.workplace.wcm.api.exceptions.DocumentRetrievalException;
import com.ibm.workplace.wcm.api.exceptions.DocumentSaveException;
import com.ibm.workplace.wcm.api.exceptions.DuplicateChildException;
import com.ibm.workplace.wcm.api.exceptions.IllegalDocumentTypeException;
import com.ibm.workplace.wcm.api.exceptions.OperationFailedException;
import com.ibm.workplace.wcm.api.exceptions.PropertyRetrievalException;
import com.ibm.workplace.wcm.api.exceptions.WCMException;


public class WidgetHelper {
	private static final String CLASS_NAME=WidgetHelper.class.getName();
	private static final Logger LOGGER=Logger.getLogger(CLASS_NAME);
	private Principal currUser;
	private WidgetHelper(){}
	public static WidgetHelper createWidgetHelper(HttpServletRequest request,HttpServletResponse response) {
		final String METHOD_NAME="createWidgetHelper";
		boolean isTraceEnabled=LOGGER.isLoggable(Level.FINER);
		boolean isDebugEnabled=LOGGER.isLoggable(Level.FINEST);
		boolean isErrorEnabled=LOGGER.isLoggable(Level.SEVERE);
		if(isTraceEnabled){LOGGER.entering(CLASS_NAME,METHOD_NAME);}
		WidgetHelper f=new WidgetHelper();
		f.currUser=request.getUserPrincipal();
		if(isTraceEnabled){LOGGER.exiting(CLASS_NAME,METHOD_NAME,f);}
		return f;
	}
	
	/**
	 * Create a new child for widgets that can have children (for example page navigators, document catalogs, etc), i.e.
	 * composite lists.
	 * 
	 * @param pageContext
	 * @param templateId
	 * @param args
	 * @param faults
	 * @return
	 */
	
	public boolean createChild(String templateId, String parentId, Properties args,List<Result> faults){
		final String METHOD_NAME="createChild";
		boolean isTraceEnabled=LOGGER.isLoggable(Level.FINER);
		boolean isDebugEnabled=LOGGER.isLoggable(Level.FINEST);
		boolean isErrorEnabled=LOGGER.isLoggable(Level.SEVERE);
		if(isTraceEnabled){LOGGER.entering(CLASS_NAME,METHOD_NAME);}
		boolean success=false;	
		try{
			if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Boostrapping our configuration...");}
			/* Access the workspace */
			if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Accessing the workspace...");}
			Workspace workspace = WCM_API.getRepository().getWorkspace(this.currUser);
			/* Now we can proceed */
			DocumentLibrary currDocLib=null;
			try{
				/* Login */
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Logging in to workspace...");}
				workspace.login();
				/* Save off the current library */
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Saving current library...");}
				try{currDocLib=workspace.getCurrentDocumentLibrary();}catch(NullPointerException ignore){/*This can safely be ignore per best practices.*/}
				/* Load the config */
				DocumentId docid=workspace.createDocumentId(parentId);
				Document doc=(Document)workspace.getById(docid,true);
				Properties config=AwwConfig.getConfigFor(doc);		
//				Content content=(Content)workspace.getById(docid,true);
//				Properties config=AwwConfig.getConfigFor(content);		
				if(config!=null){
					/* Find the libraries */
					if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Loading our libraries...");}
					String awwDesignLibName=AwwConfig.getDesignLibrary(config,templateId);
					DocumentLibrary awwDesignLib=workspace.getDocumentLibrary(awwDesignLibName);
					if(awwDesignLib!=null){
						String awwContentLibName=AwwConfig.getContentLibrary(config,templateId);
						DocumentLibrary awwContentLib=workspace.getDocumentLibrary(awwContentLibName);
						if(awwContentLib!=null){
							if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Using libraries '"+awwContentLibName+"' and '"+awwDesignLibName+"'...");}
							success=createChild(config,templateId,parentId,workspace,awwDesignLib,awwContentLib,args,faults);
						}else{
							if(isErrorEnabled){LOGGER.severe(METHOD_NAME+": Could not find the content library "+awwContentLibName+"!");}
							faults.add(new ActionResult(Result.ERROR,Results.CREATE_CHILD_MISSING_CONTENT_LIBRARY,new Object[] {awwContentLibName}));
						}
					}else{
						if(isErrorEnabled){LOGGER.severe(METHOD_NAME+": Could not find the design library "+awwDesignLibName+"!");}
						faults.add(new ActionResult(Result.ERROR,Results.CREATE_CHILD_MISSING_DESIGN_LIBRARY,new Object[] {awwDesignLibName}));			
					}
				}else{
					if(isErrorEnabled){Jsp.LOGGER.severe(METHOD_NAME+": No widget configuration was found for the content with id "+parentId+" ("+doc.getTitle()+")!");}
					faults.add(new ActionResult(Result.ERROR,Results.CREATE_CHILD_WIDGET_CONFIG_NOT_FOUND,new Object[] {parentId,doc.getTitle()}));   				
				}
			}catch(Throwable t){
				if(isErrorEnabled){Jsp.LOGGER.log(Level.SEVERE,METHOD_NAME+": A child could not be created for the parent with id "+parentId+" due to the following error: " +t.getMessage(),t);}
				faults.add(new ActionResult(Result.ERROR,Results.CREATE_CHILD_FAILED,new Object[] {parentId,t.getMessage()},t));
			}finally{
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Restoring last document library...");}
				if(currDocLib!=null){workspace.setCurrentDocumentLibrary(currDocLib);}
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Logging out of workspace...");}
				workspace.logout();
				// Non-JSP components must call endWorkspace()
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Ending workspace...");}
				WCM_API.getRepository().endWorkspace();
			}
		}catch(WCMException e){
			if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": A user workspace could not be created for the principal "+this.currUser+"!",e);}
			faults.add(new ActionResult(Result.ERROR,Results.CREATE_CHILD_WORKSPACE_ACCESS_EXCEPTION,new Object[] {this.currUser}));
		}
		if(isTraceEnabled){LOGGER.exiting(CLASS_NAME,METHOD_NAME,success);}
		return success;
	}	
		
	public static boolean createChild(Properties config,String templateId,String parentId,Workspace workspace,DocumentLibrary awwDesignLib,DocumentLibrary awwContentLib,Properties args,List<Result> faults){
		final String METHOD_NAME="createChild";
		boolean isTraceEnabled=LOGGER.isLoggable(Level.FINER);
		boolean isDebugEnabled=LOGGER.isLoggable(Level.FINEST);
		boolean isErrorEnabled=LOGGER.isLoggable(Level.SEVERE);
		if(isTraceEnabled){LOGGER.entering(CLASS_NAME,METHOD_NAME);}
		boolean success=false;
		String atName=AwwConfig.getTemplateParameter(config, templateId, AwwConfig.AT_TEMPLATE_PARAM);
		String workflow=AwwConfig.getTemplateParameter(config, templateId, AwwConfig.WORKFLOW_TEMPLATE_PARAM);
		String saWorkflow=AwwConfig.getTemplateParameter(config, templateId, AwwConfig.SA_WORKFLOW_TEMPLATE_PARAM);
		String generateName=AwwConfig.getTemplateParameter(config, templateId, AwwConfig.GENERATE_NAME_TEMPLATE_PARAM);
		String title=args.getProperty("title");
		boolean genName=false;
		/* Validate inputs */
		if(title==null||(title=title.trim()).length()==0){
			if(isErrorEnabled){LOGGER.severe(METHOD_NAME+": Title argument is missing!");}	
			faults.add(new ActionResult(Result.ERROR,Results.CHILD_WIDGET_MISSING_TITLE_EXCEPTION));
			return false;
		}
		if(generateName!=null&&(generateName=generateName.trim()).length()>0){
			genName=Boolean.valueOf(generateName).booleanValue();
		}	
		/* Lookup the AT if one was specified */
		DocumentId atId=null;
		if(atName!=null&&(atName=atName.trim()).length()>0){
			DocumentLibrary doclib=awwDesignLib;
			int idx=atName.indexOf("/");
			if(idx>0&&(idx+1<atName.length())){
				String lib=atName.substring(0,idx);
				doclib=workspace.getDocumentLibrary(lib);
				if(doclib==null){
					if(isErrorEnabled){LOGGER.severe(METHOD_NAME+": The document library "+lib+" referenced in the authoring template "+atName+" could not be found!");}				
					faults.add(new ActionResult(Result.ERROR,Results.CHILD_WIDGET_AT_DOCLIB_NOT_FOUND_EXCEPTION,new Object[] {lib,atName}));
					return false;					
				}
				atName=atName.substring(idx+1);
			}
			workspace.setCurrentDocumentLibrary(doclib);
			if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Locating the authoring template '"+atName+"'...");}
			DocumentIdIterator itr=workspace.findByName(DocumentTypes.AuthoringTemplate,atName);
			if(itr.hasNext()){
				atId=itr.nextId();
			}else{
				if(isErrorEnabled){LOGGER.severe(METHOD_NAME+": Could not find the authoring template "+atName+"!");}
				faults.add(new ActionResult(Result.ERROR,Results.CHILD_WIDGET_AT_NOT_FOUND_EXCEPTION,new Object[] {atName,awwDesignLib.getTitle()}));
				return false;
			}
		}	
		/* Lookup the content workflow if one was specified */
		DocumentId wfId=null;
		if(workflow!=null&&(workflow=workflow.trim()).length()>0){
			DocumentLibrary doclib=awwDesignLib;
			int idx=workflow.indexOf("/");
			if(idx>0&&(idx+1<workflow.length())){
				String lib=workflow.substring(0,idx);
				doclib=workspace.getDocumentLibrary(lib);
				if(doclib==null){
					if(isErrorEnabled){LOGGER.severe(METHOD_NAME+": The document library "+lib+" referenced in the workflow "+workflow+" could not be found!");}			
					faults.add(new ActionResult(Result.ERROR,Results.CHILD_WIDGET_WORKFLOW_DOCLIB_NOT_FOUND_EXCEPTION,new Object[] {lib,workflow}));
					return false;					
				}
				workflow=workflow.substring(idx+1);
			}			
			workspace.setCurrentDocumentLibrary(doclib);
			if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Locating the workflow '"+workflow+"'...");}
			DocumentIdIterator itr=workspace.findByName(DocumentTypes.Workflow,workflow);
			if(itr.hasNext()){
				wfId=itr.nextId();
			}else{
				if(isErrorEnabled){LOGGER.severe(METHOD_NAME+": Could not find the workflow "+workflow+"!");}
				faults.add(new ActionResult(Result.ERROR,Results.CHILD_WIDGET_WORKFLOW_NOT_FOUND_EXCEPTION,new Object[] {workflow,awwDesignLib.getTitle()}));
				return false;
			}
		}		
		/* Lookup the site area workflow if workflowed site areas are in use */
		DocumentId saWfId=null;
		if(saWorkflow!=null&&(saWorkflow=saWorkflow.trim()).length()>0){
			DocumentLibrary doclib=awwDesignLib;
			int idx=saWorkflow.indexOf("/");
			if(idx>0&&(idx+1<saWorkflow.length())){
				String lib=saWorkflow.substring(0,idx);
				doclib=workspace.getDocumentLibrary(lib);
				if(doclib==null){
					if(isErrorEnabled){LOGGER.severe(METHOD_NAME+": The document library "+lib+" referenced in the site area workflow "+saWorkflow+" could not be found!");}				
					faults.add(new ActionResult(Result.ERROR,Results.CHILD_WIDGET_SA_WORKFLOW_DOCLIB_NOT_FOUND_EXCEPTION,new Object[] {lib,workflow}));
					return false;					
				}
				saWorkflow=saWorkflow.substring(idx+1);
			}			
			workspace.setCurrentDocumentLibrary(doclib);
			if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Locating the site area workflow '"+saWorkflow+"'...");}
			DocumentIdIterator itr=workspace.findByName(DocumentTypes.Workflow,saWorkflow);
			if(itr.hasNext()){
				saWfId=itr.nextId();
			}else{
				if(isErrorEnabled){LOGGER.severe(METHOD_NAME+": Could not find the site area workflow "+saWorkflow+"!");}
				faults.add(new ActionResult(Result.ERROR,Results.CHILD_WIDGET_SA_WORKFLOW_NOT_FOUND_EXCEPTION,new Object[] {saWorkflow,awwDesignLib.getTitle()}));
				return false;
			}
		}			
		workspace.setCurrentDocumentLibrary(awwContentLib);
		try{
			List<Document> docs=new ArrayList<Document>(5);
			Content content=null;
			if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Locating the list site area using document ID '"+parentId+"'...");}
			DocumentId saId=workspace.createDocumentId(parentId);
			SiteArea list = (SiteArea)workspace.getById(saId,true);
			DocumentId defaultContentId=list.getDefaultContent();
			if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Creating a new site area in the workspace with the title '"+title+"'...");}
			SiteArea childList=workspace.createSiteArea(list.getId(),null,ChildPosition.END);
			String childListName=Utils.makeName(title);
			if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Setting site area name to '"+childListName+"'...");}
			childList.setName(childListName);
			childList.setTitle(title);				
			if(saWfId!=null){
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Setting site area workflow on child to '"+saWfId.getName()+"'...");}
				childList.setWorkflowId(saWfId);
				childList.setEffectiveDate(new java.util.Date());
			}
			docs.add(childList);
			if(atId==null){
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Setting default content ID to '"+defaultContentId+"'...");}
				childList.setDefaultContent(defaultContentId);	
			}else{
				// Create the new content
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Creating a new content document in the workspace with the title '"+title+"'...");}
				content=workspace.createContent(atId,childList.getId(),null,ChildPosition.END);
				content.setTitle(title);
				if(genName){
					// Generate the name and set the name/title
					String name=Utils.makeName(title);
					if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Setting content name to '"+name+"'...");}
					content.setName(name);
				}
				if(wfId!=null){
					if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Setting content workflow to '"+wfId.getName()+"'...");}
					content.setWorkflowId(wfId);
					content.setEffectiveDate(new Date());
				}					
				docs.add(content);					
			}
			// Save everything
			try{
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Saving documents to the workspace...");}
				// TODO: Prolly should do this in a transaction so we don't leave dangling stuff
				if(Utils.saveDocuments(workspace, docs, faults)){
					if(content!=null){
						docs.clear();
						// Assign the default content
						if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Setting the default content for the new site area...");}
						childList.setDefaultContent(content.getId());
						docs.add(childList);
						// Save the SA again
						if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Saving the modified sitearea to the workspace...");}
						if(Utils.saveDocuments(workspace,docs,faults)){									
							success=true;
						}							
					}else{
						success=true;
					}
				}
			}catch(DocumentSaveException e){
				if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": Could not save the new child list list with the title '"+title+"'!",e);}
				faults.add(new ActionResult(Result.ERROR,Results.CHILD_WIDGET_DOCUMENT_SAVE_EXCEPTION,new Object[] {title},e));
			}catch(AuthorizationException e){
				if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": User is not authorized to save the child list with the title '"+title+"'!",e);}
				faults.add(new ActionResult(Result.ERROR,Results.CHILD_WIDGET_DOCUMENT_SAVE_ACCESS_EXCEPTION,new Object[] {title},e));
			}catch(DuplicateChildException e){
				if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": A child list with the title '"+title+"' already exists!",e);}
				faults.add(new ActionResult(Result.ERROR,Results.CHILD_WIDGET_DOCUMENT_DUP_EXCEPTION,new Object[] {title},e));									
			}
		} catch (DocumentIdCreationException e) {
			if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": Could not create a document id for the list with the id '"+parentId+"'!",e);}
			faults.add(new ActionResult(Result.ERROR,Results.CHILD_WIDGET_DOCUMENT_CREATE_ID_EXCEPTION,new Object[] {title},e));
		} catch (DocumentCreationException e) {
			if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": Could not create a new child list with the title '"+title+"'!",e);}
			faults.add(new ActionResult(Result.ERROR,Results.CHILD_WIDGET_DOCUMENT_CREATE_EXCEPTION,new Object[] {title},e));
		} catch (AuthorizationException e) {
			if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": User is not authorized to create a child list with the title '"+title+"'!",e);}
			faults.add(new ActionResult(Result.ERROR,Results.CHILD_WIDGET_DOCUMENT_CREATE_ACCESS_EXCEPTION,new Object[] {title},e));
		} catch (DocumentRetrievalException e) {
			if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": Could not access the site area with id '"+parentId+"'!",e);}
			faults.add(new ActionResult(Result.ERROR,Results.CHILD_WIDGET_DOCUMENT_PARENT_ACCESS_EXCEPTION,new Object[] {parentId},e));
		} catch (PropertyRetrievalException e) {
			if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": Could not get default content for site area with id '"+parentId+"'!",e);}
			faults.add(new ActionResult(Result.ERROR,Results.CHILD_WIDGET_DOCUMENT_PARENT_DEFAULT_EXCEPTION,new Object[] {parentId},e));
		} catch (OperationFailedException e) {
			if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": The workflow could not be set on the document with the title '"+title+"'!",e);}
			faults.add(new ActionResult(Result.ERROR,Results.CHILD_WIDGET_DOCUMENT_SET_WORKFLOW_EXCEPTION,new Object[] {wfId.getName(),title},e));
		} catch (IllegalDocumentTypeException ignore) {
			assert false;
		}					
		if(isTraceEnabled){LOGGER.exiting(CLASS_NAME,METHOD_NAME,success);}
		return success;
	}		

}
