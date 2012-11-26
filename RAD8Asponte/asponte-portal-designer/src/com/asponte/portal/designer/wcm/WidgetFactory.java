package com.asponte.portal.designer.wcm;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import com.asponte.commons.Result;
import com.asponte.commons.portal.wcm.AwwConfig;
import com.asponte.portal.designer.DesignerException;
import com.asponte.portal.designer.PortletResult;
import com.ibm.portal.MetaData;
import com.ibm.portal.ModelException;
import com.ibm.portal.content.ContentMetaDataModel;
import com.ibm.portal.content.ContentNode;
import com.ibm.portal.navigation.NavigationNode;
import com.ibm.portal.navigation.NavigationSelectionModel;
import com.ibm.portal.portlet.service.PortletServiceHome;
import com.ibm.portal.portlet.service.PortletServiceUnavailableException;
import com.ibm.portal.portlet.service.model.ContentMetaDataModelProvider;
import com.ibm.portal.portlet.service.model.NavigationSelectionModelProvider;
import com.ibm.workplace.wcm.api.ChildPosition;
import com.ibm.workplace.wcm.api.Content;
import com.ibm.workplace.wcm.api.Document;
import com.ibm.workplace.wcm.api.DocumentId;
import com.ibm.workplace.wcm.api.DocumentIdIterator;
import com.ibm.workplace.wcm.api.DocumentLibrary;
import com.ibm.workplace.wcm.api.DocumentTypes;
import com.ibm.workplace.wcm.api.LibraryComponent;
import com.ibm.workplace.wcm.api.ReferenceComponent;
import com.ibm.workplace.wcm.api.SiteArea;
import com.ibm.workplace.wcm.api.TextComponent;
import com.ibm.workplace.wcm.api.WCM_API;
import com.ibm.workplace.wcm.api.Workspace;
import com.ibm.workplace.wcm.api.exceptions.AuthorizationException;
import com.ibm.workplace.wcm.api.exceptions.ComponentNotFoundException;
import com.ibm.workplace.wcm.api.exceptions.DocumentCreationException;
import com.ibm.workplace.wcm.api.exceptions.DocumentIdCreationException;
import com.ibm.workplace.wcm.api.exceptions.DocumentRetrievalException;
import com.ibm.workplace.wcm.api.exceptions.DocumentSaveException;
import com.ibm.workplace.wcm.api.exceptions.DuplicateChildException;
import com.ibm.workplace.wcm.api.exceptions.IllegalDocumentTypeException;
import com.ibm.workplace.wcm.api.exceptions.IllegalTypeChangeException;
import com.ibm.workplace.wcm.api.exceptions.NoMoreWorkflowStagesException;
import com.ibm.workplace.wcm.api.exceptions.OperationFailedException;
import com.ibm.workplace.wcm.api.exceptions.PropertyRetrievalException;
import com.ibm.workplace.wcm.api.exceptions.WCMException;

public class WidgetFactory {
	private static final String CLASS_NAME=WidgetFactory.class.getName();
	private static final Logger LOGGER=Logger.getLogger(CLASS_NAME);
	private static PortletServiceHome NSM_HOME;
	private static PortletServiceHome CMDM_HOME;
	private Properties config;
	private Principal currUser;
	static{
		try{
			javax.naming.Context ctx = new javax.naming.InitialContext();
			NSM_HOME = (PortletServiceHome) ctx.lookup("portletservice/com.ibm.portal.portlet.service.model.NavigationSelectionModelProvider");
			CMDM_HOME = (PortletServiceHome) ctx.lookup("portletservice/com.ibm.portal.portlet.service.model.ContentMetaDataModelProvider");
		}catch(NamingException e){
			// If this happens, there is something else majorly wrong
			throw new RuntimeException(e);
		}
	}
	private WidgetFactory(){}
	
	public static WidgetFactory createWidgetFactory(PortletRequest request,PortletResponse response) throws DesignerException {
		final String METHOD_NAME="createWidgetFactory";
		boolean isTraceEnabled=LOGGER.isLoggable(Level.FINER);
		boolean isDebugEnabled=LOGGER.isLoggable(Level.FINEST);
		boolean isErrorEnabled=LOGGER.isLoggable(Level.SEVERE);
		if(isTraceEnabled){LOGGER.entering(CLASS_NAME,METHOD_NAME);}		
		NavigationSelectionModelProvider nsmp;
		ContentMetaDataModelProvider cmdmp;
		try {
			String configPath=null;
			PortletPreferences prefs=request.getPreferences();
			if(isDebugEnabled){LOGGER.finest(METHOD_NAME+" Checking preferences for WCM widget configuration path...");}
			configPath=prefs.getValue(AwwConfig.WIDGET_CONFIG_PARAM, null);
			if(configPath==null){
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+" Accessing navigation selection model portlet service...");}
				nsmp = (NavigationSelectionModelProvider) NSM_HOME.getPortletService(NavigationSelectionModelProvider.class);
				cmdmp= (ContentMetaDataModelProvider) CMDM_HOME.getPortletService(ContentMetaDataModelProvider.class);
				NavigationSelectionModel nsm = nsmp.getNavigationSelectionModel(request, response);
				ContentMetaDataModel cmdm = cmdmp.getContentMetaDataModel(request, response);
				NavigationNode aNode=(NavigationNode)nsm.getSelectedNode();
				ContentNode cNode=aNode.getContentNode();
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+" Checking metadata model for current node for widget config content library parameter...");}
				MetaData md = cmdm.getMetaData(cNode);
				Object _configPath=md.getValue(AwwConfig.WIDGET_CONFIG_PARAM);
				String cp;
				if(_configPath!=null&&(cp=_configPath.toString()).length()>0){
					if(isDebugEnabled){LOGGER.finest(METHOD_NAME+" Found config path parameter "+cp+"...");}
					configPath=cp;
				}
			}
			try{
	       	  	if(isDebugEnabled){LOGGER.finest(METHOD_NAME+" Loading widget configuration from "+configPath+" ('null' means repository-wide discovery)...");}
				Properties config=AwwConfig.getConfiguration(configPath);
				if(config!=null){
					if(isDebugEnabled){LOGGER.finest(METHOD_NAME+" Found config "+config+". Creating factory...");}
					WidgetFactory f=new WidgetFactory();
					f.config=config;
					f.currUser=request.getUserPrincipal();
					if(isDebugEnabled){LOGGER.finest(METHOD_NAME+" Factory created with "+f.config+" and user principal "+f.currUser+"...");}
					return f;
				}else{
					if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": Could not create widget factory for the request with portlet window ID "+request.getWindowID()+" because the widget configuration could not be found !");}
					throw new DesignerException(new PortletResult(Result.ERROR,Results.WIDGET_CONFIG_NOT_FOUND,new Object[]{request.getWindowID()}));				
				}
			} catch (WCMException e) {
				if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": Could not create widget factory for the request with portlet window ID "+request.getWindowID()+" because a WCM exception occurred while loading the widget configuration!",e);}
				throw new DesignerException(new PortletResult(Result.ERROR,Results.CREATE_WIDGET_FACTORY_FAILED_1,new Object[]{request.getWindowID()}),e);				
			} catch (IOException e) {
				if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": Could not create widget factory for the request with portlet window ID "+request.getWindowID()+" because an I/O exception occurred while loading the widget configuration!",e);}
				throw new DesignerException(new PortletResult(Result.ERROR,Results.CREATE_WIDGET_FACTORY_FAILED_2,new Object[]{request.getWindowID()}),e);				
			} 
		} catch (PortletServiceUnavailableException e) {
			if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": Could not create widget factory for the request with portlet window ID "+request.getWindowID()+" because one or more of the required model portlet services id unavailable!",e);}
			throw new DesignerException(new PortletResult(Result.ERROR,Results.CREATE_WIDGET_FACTORY_FAILED_3,new Object[]{request.getWindowID()}),e);
		} catch (ModelException e) {
			if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": Could not create widget factory for the request with portlet window ID "+request.getWindowID()+" because a model error occurred while accessing the selected navigation node!",e);}
			throw new DesignerException(new PortletResult(Result.ERROR,Results.CREATE_WIDGET_FACTORY_FAILED_4,new Object[]{request.getWindowID()}),e);
		} finally {
			if(isTraceEnabled){LOGGER.exiting(CLASS_NAME,METHOD_NAME);}
		}
	}
	
	private Object createPlugin(String className,Class<?> clazz) throws DesignerException {
		final String METHOD_NAME="createWidget";
		boolean isTraceEnabled=LOGGER.isLoggable(Level.FINER);
		boolean isDebugEnabled=LOGGER.isLoggable(Level.FINEST);
		boolean isErrorEnabled=LOGGER.isLoggable(Level.SEVERE);
		if(isTraceEnabled){LOGGER.entering(CLASS_NAME,METHOD_NAME);}		
		try {
			Object obj;
			if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Looking up plugin class "+className+"...");}
			Class<?> objClass=Class.forName(className);
			if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Checking type compatibility of plugin class "+className+"...");}
			if(clazz.isAssignableFrom(objClass)){
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Creating new instance for plugin class "+className+"...");}
				obj=objClass.newInstance();
				if(isTraceEnabled){LOGGER.exiting(CLASS_NAME,METHOD_NAME,obj);}
				return obj;
			}else{
				if(isErrorEnabled){LOGGER.severe(METHOD_NAME+": The plugin "+className+" is not of the appropriate type "+clazz.getName()+"!");}
				throw new DesignerException(new PortletResult(Result.ERROR,Results.PLUGIN_TYPE_EXCEPTION,new Object[] {className,clazz.getName()}));
			}
		} catch (ClassNotFoundException e) {
			if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": Could not find the plugin "+className+"!",e);}
			throw new DesignerException(new PortletResult(Result.ERROR,Results.PLUGIN_CLASS_NOT_FOUND_EXCEPTION,new Object[] {className}));
		} catch (IllegalAccessException e) {
			if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": Could not create the plugin "+className+" because the class could not be accessed!",e);}
			throw new DesignerException(new PortletResult(Result.ERROR,Results.PLUGIN_ACCESS_EXCEPTION,new Object[] {className}));
		} catch (InstantiationException e) {
			if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": Could not create the plugin "+className+" due to the following error: "+e.getLocalizedMessage(),e);}
			throw new DesignerException(new PortletResult(Result.ERROR,Results.PLUGIN_INSTANTIATION_EXCEPTION,new Object[] {className,e.getLocalizedMessage()}));
		}	
	}
	
	/**
	 * Create a new widget.  A widget is a collection of WCM site areas and/or content.  Widgets are created
	 * from templates.  Templates are identified by a unique ID and provide a set of key/value pairs that
	 * direct the widget creation process.  Arguments can be passed in to the creation process to provide values
	 * for "points of variability".
	 * 
	 * @param pageContext
	 * @param templateId
	 * @param args
	 * @param faults
	 * @return
	 */
	
	public LrpConfig createWidget(String templateId, Properties args,List<Result> faults){
		final String METHOD_NAME="createWidget";
		boolean isTraceEnabled=LOGGER.isLoggable(Level.FINER);
		boolean isDebugEnabled=LOGGER.isLoggable(Level.FINEST);
		boolean isErrorEnabled=LOGGER.isLoggable(Level.SEVERE);
		if(isTraceEnabled){LOGGER.entering(CLASS_NAME,METHOD_NAME);}
		LrpConfig lrpConfig=null;	
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
				/* Find the libraries */
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Loading our libraries...");}
				String awwDesignLibName=AwwConfig.getDesignLibrary(this.config,templateId);
				DocumentLibrary awwDesignLib=workspace.getDocumentLibrary(awwDesignLibName);
				if(awwDesignLib!=null){
					String awwContentLibName=AwwConfig.getContentLibrary(this.config,templateId);
					DocumentLibrary awwContentLib=workspace.getDocumentLibrary(awwContentLibName);
					if(awwContentLib!=null){
						if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Using libraries '"+awwContentLibName+"' and '"+awwDesignLibName+"'...");}
						String widgetType=AwwConfig.getTemplateParameter(this.config, templateId, AwwConfig.WIDGET_TYPE_TEMPLATE_PARAM);
						if(widgetType!=null&&(widgetType=widgetType.trim()).length()>0){
							if(widgetType.equalsIgnoreCase(Constants.CONTENT_TYPE)){
								lrpConfig=createContent(this.config,templateId,workspace,awwDesignLib,awwContentLib,args,faults);
							}else if(widgetType.equalsIgnoreCase(Constants.LIST_TYPE)){
								lrpConfig=createList(this.config,templateId,workspace,awwDesignLib,awwContentLib,args,faults);
							}else if(widgetType.equalsIgnoreCase(Constants.COMPOSITE_LIST_TYPE)){
								lrpConfig=createCompositeList(this.config,templateId,workspace,awwDesignLib,awwContentLib,args,faults);
							}else if (widgetType.equalsIgnoreCase(Constants.NONE_TYPE)){
								lrpConfig=configureEmptyLrp(this.config,templateId,workspace,awwDesignLib,awwContentLib,args,faults);
							}else{
								try {
									WidgetBuilder template=(WidgetBuilder)createPlugin(widgetType,WidgetBuilder.class);
									if(template!=null){
										template.createWidget(this,this.config,templateId,workspace,awwDesignLib,awwContentLib,args,faults);
									}
								} catch (DesignerException e) {
									for(Result r:e.getResults()){faults.add(r);}
								}
							}
						}else{
							if(isErrorEnabled){LOGGER.severe(METHOD_NAME+": No widget type was found for the template with the ID "+templateId+"!");}
							faults.add(new PortletResult(Result.ERROR,Results.MISSING_WIDGET_TYPE,new Object[] {templateId}));
						}
					}else{
						if(isErrorEnabled){LOGGER.severe(METHOD_NAME+": Could not find the content library "+awwContentLibName+"!");}
						faults.add(new PortletResult(Result.ERROR,Results.MISSING_CONTENT_LIBRARY,new Object[] {awwContentLibName}));
					}
				}else{
					if(isErrorEnabled){LOGGER.severe(METHOD_NAME+": Could not find the design library "+awwDesignLibName+"!");}
					faults.add(new PortletResult(Result.ERROR,Results.MISSING_DESIGN_LIBRARY,new Object[] {awwDesignLibName}));			
				}
			}finally{
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Restoring last document library...");}
				if(currDocLib!=null){workspace.setCurrentDocumentLibrary(currDocLib);}
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Logging out of workspace...");}
				workspace.logout();
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Ending workspace...");}
				WCM_API.getRepository().endWorkspace();
			}
		}catch(WCMException e){
			if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": A user workspace could not be created for the principal "+this.currUser+"!",e);}
			faults.add(new PortletResult(Result.ERROR,Results.WORKSPACE_ACCESS_EXCEPTION,new Object[] {this.currUser}));
		}
		if(isTraceEnabled){LOGGER.exiting(CLASS_NAME,METHOD_NAME,lrpConfig);}
		return lrpConfig;
	}
	
	public LrpConfig configureEmptyLrp(Properties config2, String templateId,
			Workspace workspace, DocumentLibrary awwDesignLib,
			DocumentLibrary awwContentLib, Properties args, List<Result> faults) {
		final String METHOD_NAME="configureEmptyLrp";
		boolean isTraceEnabled=LOGGER.isLoggable(Level.FINER);
		boolean isDebugEnabled=LOGGER.isLoggable(Level.FINEST);
		boolean isErrorEnabled=LOGGER.isLoggable(Level.SEVERE);
		if(isTraceEnabled){LOGGER.entering(CLASS_NAME,METHOD_NAME);}	
		LrpConfig lrpConfig=null;
		String atName=AwwConfig.getTemplateParameter(config, templateId, AwwConfig.AT_TEMPLATE_PARAM);
		String workflow=AwwConfig.getTemplateParameter(config, templateId, AwwConfig.WORKFLOW_TEMPLATE_PARAM);
		String saPath=AwwConfig.getTemplateParameter(config, templateId, AwwConfig.SA_TEMPLATE_PARAM);
		String generateName=AwwConfig.getTemplateParameter(config, templateId, AwwConfig.GENERATE_NAME_TEMPLATE_PARAM);
		String title=args.getProperty("title");
		String broadcast=AwwConfig.getTemplateParameter(config,templateId,AwwConfig.BROADCAST_TEMPLATE_PARAM);
		String listen=AwwConfig.getTemplateParameter(config,templateId,AwwConfig.LISTEN_TEMPLATE_PARAM);
		String _plugin=AwwConfig.getTemplateParameter(config,templateId,AwwConfig.PLUGIN_TEMPLATE_PARAM);
		
		lrpConfig = Utils.createLrpConfig(null,null,"content",null,null,broadcast,listen,title);
		return lrpConfig;
	}
	
	public LrpConfig createContent(Properties config,String templateId,Workspace workspace,DocumentLibrary awwDesignLib,DocumentLibrary awwContentLib,Properties args,List<Result> faults){
		final String METHOD_NAME="createContent";
		boolean isTraceEnabled=LOGGER.isLoggable(Level.FINER);
		boolean isDebugEnabled=LOGGER.isLoggable(Level.FINEST);
		boolean isErrorEnabled=LOGGER.isLoggable(Level.SEVERE);
		if(isTraceEnabled){LOGGER.entering(CLASS_NAME,METHOD_NAME);}	
		LrpConfig lrpConfig=null;
		String atName=AwwConfig.getTemplateParameter(config, templateId, AwwConfig.AT_TEMPLATE_PARAM);
		String workflow=AwwConfig.getTemplateParameter(config, templateId, AwwConfig.WORKFLOW_TEMPLATE_PARAM);
		String saPath=AwwConfig.getTemplateParameter(config, templateId, AwwConfig.SA_TEMPLATE_PARAM);
		String generateName=AwwConfig.getTemplateParameter(config, templateId, AwwConfig.GENERATE_NAME_TEMPLATE_PARAM);
		String title=args.getProperty("title");
		String broadcast=AwwConfig.getTemplateParameter(config,templateId,AwwConfig.BROADCAST_TEMPLATE_PARAM);
		String listen=AwwConfig.getTemplateParameter(config,templateId,AwwConfig.LISTEN_TEMPLATE_PARAM);
		String _plugin=AwwConfig.getTemplateParameter(config,templateId,AwwConfig.PLUGIN_TEMPLATE_PARAM);
		WidgetContributor plugin=null;
		boolean genName=false;
		/* Validate inputs */
		if(atName==null||(atName=atName.trim()).length()==0){
			if(isErrorEnabled){LOGGER.severe(METHOD_NAME+": Authoring template name template parameter is missing!");}
			faults.add(new PortletResult(Result.ERROR,Results.CONTENT_WIDGET_MISSING_AT_EXCEPTION));
			return null;
		}
		if(saPath==null||(saPath=saPath.trim()).length()==0){
			if(isErrorEnabled){LOGGER.severe(METHOD_NAME+": Site area template parameter is missing!");}
			faults.add(new PortletResult(Result.ERROR,Results.CONTENT_WIDGET_MISSING_SA_EXCEPTION));
			return null;
		}
		if(title==null||(title=title.trim()).length()==0){
			if(isErrorEnabled){LOGGER.severe(METHOD_NAME+": Title argument is missing!");}
			faults.add(new PortletResult(Result.ERROR,Results.CONTENT_WIDGET_MISSING_TITLE_EXCEPTION));
			return null;
		}	
		if(generateName!=null&&(generateName=generateName.trim()).length()>0){
			genName=Boolean.valueOf(generateName).booleanValue();
		}
		if(broadcast!=null&&(broadcast=broadcast.trim()).length()>0){
			if(broadcast.equalsIgnoreCase("none")){broadcast=Constants.WCM_LINKING_NONE;}
			else if(broadcast.equalsIgnoreCase("self")){broadcast=Constants.WCM_LINKING_SELF;}
			else if(broadcast.equalsIgnoreCase("other")){broadcast=Constants.WCM_LINKING_OTHER;}
			else if(broadcast.equalsIgnoreCase("dynamic")){broadcast=Constants.WCM_LINKING_DYNAMIC;}
			else{broadcast=null;}
		}else{
			broadcast=null;
		}
		if(listen!=null&&(listen=listen.trim()).length()>0){
			if(listen.equalsIgnoreCase("none")){listen=Constants.WCM_LINKING_NONE;}
			else if(listen.equalsIgnoreCase("self")){listen=Constants.WCM_LINKING_SELF;}
			else if(listen.equalsIgnoreCase("other")){listen=Constants.WCM_LINKING_OTHER;}
			else{listen=Constants.WCM_LINKING_NONE;}
		}else{
			listen=Constants.WCM_LINKING_NONE;
		}
		if(_plugin!=null&&(_plugin=_plugin.trim()).length()>0){
			try {
				plugin=(WidgetContributor)createPlugin(_plugin,WidgetContributor.class);
			} catch (DesignerException e) {
				for(Result r:e.getResults()){faults.add(r);}
				return null;
			}
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
					faults.add(new PortletResult(Result.ERROR,Results.CONTENT_WIDGET_AT_DOCLIB_NOT_FOUND_EXCEPTION,new Object[] {lib,atName}));
					return null;					
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
				faults.add(new PortletResult(Result.ERROR,Results.CONTENT_WIDGET_AT_NOT_FOUND_EXCEPTION,new Object[] {atName,awwDesignLib.getTitle()}));
				return null;
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
					faults.add(new PortletResult(Result.ERROR,Results.CONTENT_WIDGET_WORKFLOW_DOCLIB_NOT_FOUND_EXCEPTION,new Object[] {lib,workflow}));
					return null;					
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
				faults.add(new PortletResult(Result.ERROR,Results.CONTENT_WIDGET_WORKFLOW_NOT_FOUND_EXCEPTION,new Object[] {workflow,awwDesignLib.getTitle()}));
				return null;
			}
		}			
		/* Proceed */
		workspace.setCurrentDocumentLibrary(awwContentLib);
		String fullSaPath="/"+awwContentLib.getName()+saPath;
		if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Locating the site area '"+fullSaPath+"'...");}
		DocumentIdIterator itr=workspace.findByPath(fullSaPath,Workspace.WORKFLOWSTATUS_PUBLISHED);
		if(itr.hasNext()){
			DocumentId saId=itr.nextId();
			try{
				List<Document> docs=new ArrayList<Document>(5);
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Creating a new content document in the workspace with the title '"+title+"'...");}
				// Create the new content
				Content content=workspace.createContent(atId,saId,null,ChildPosition.END);
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
				if(plugin!=null){plugin.contentCreated(this,config,templateId,workspace,awwDesignLib,awwContentLib,args,docs);}
				try{
					if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Saving the document to the workspace...");}
					// TODO: Prolly should do this in a transaction so we don't leave dangling stuff
					if(Utils.saveDocuments(workspace,docs,faults)){
						if(plugin!=null){plugin.contentSaved(this,config,templateId,workspace,awwDesignLib,awwContentLib,args,docs);}
						lrpConfig = Utils.createLrpConfig(content,null,"content",null,null,broadcast,listen,title);
					}
				}catch(DocumentSaveException e){
					if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": Could not save the new content document with the title '"+title+"'!",e);}
					faults.add(new PortletResult(Result.ERROR,Results.CONTENT_WIDGET_DOCUMENT_SAVE_EXCEPTION,new Object[] {title},e));
				}catch(AuthorizationException e){
					if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": User is not authorized to save the new content document with the title '"+title+"'!",e);}
					faults.add(new PortletResult(Result.ERROR,Results.CONTENT_WIDGET_DOCUMENT_SAVE_ACCESS_EXCEPTION,new Object[] {title},e));
				}catch(DuplicateChildException e){
					if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": A content item with the title '"+title+"' already exists!",e);}
					faults.add(new PortletResult(Result.ERROR,Results.CONTENT_WIDGET_DOCUMENT_DUP_EXCEPTION,new Object[] {title},e));									
				}catch(DesignerException e){
					for(Result r:e.getResults()){faults.add(r);}
				}
			}catch(DocumentCreationException e){
				if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": Could not create a new content document with the title '"+title+"'!",e);}
				faults.add(new PortletResult(Result.ERROR,Results.CONTENT_WIDGET_DOCUMENT_CREATE_EXCEPTION,new Object[] {title},e));
			}catch(AuthorizationException e){
				if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": User is not authorized to create a new content document with the title '"+title+"'!",e);}
				faults.add(new PortletResult(Result.ERROR,Results.CONTENT_WIDGET_DOCUMENT_CREATE_ACCESS_EXCEPTION,new Object[] {title},e));
			}catch (OperationFailedException e) {
				if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": The workflow could not be set on the document with the title '"+title+"'!",e);}
				faults.add(new PortletResult(Result.ERROR,Results.CONTENT_WIDGET_DOCUMENT_SET_WORKFLOW_EXCEPTION,new Object[] {wfId.getName(),title},e));
			}catch(IllegalDocumentTypeException ignore){
				// we know all our inputs are correct
				assert false;
			}catch(DesignerException e){
				for(Result r:e.getResults()){faults.add(r);}
			}
		}else{
			if(isErrorEnabled){LOGGER.severe(METHOD_NAME+": Could not find the site area "+saPath+"!");}
			faults.add(new PortletResult(Result.ERROR,Results.CONTENT_WIDGET_SA_NOT_FOUND_EXCEPTION,new Object[] {saPath,awwDesignLib.getTitle()}));
		}
		if(isTraceEnabled){LOGGER.exiting(CLASS_NAME,METHOD_NAME,lrpConfig);}
		return lrpConfig;
	}
	
	/* ***************************************************************
	 * Pattern 2: Create a new "List" possibly with content
	 * *************************************************************** */
	
	public LrpConfig createList(Properties config,String templateId,Workspace workspace,DocumentLibrary awwDesignLib,DocumentLibrary awwContentLib,Properties args,List<Result> faults){
		final String METHOD_NAME="createList";
		boolean isTraceEnabled=LOGGER.isLoggable(Level.FINER);
		boolean isDebugEnabled=LOGGER.isLoggable(Level.FINEST);
		boolean isErrorEnabled=LOGGER.isLoggable(Level.SEVERE);
		if(isTraceEnabled){LOGGER.entering(CLASS_NAME,METHOD_NAME);}		
		LrpConfig lrpConfig=null;
		String atName=AwwConfig.getTemplateParameter(config, templateId, AwwConfig.AT_TEMPLATE_PARAM);
		String workflow=AwwConfig.getTemplateParameter(config, templateId, AwwConfig.WORKFLOW_TEMPLATE_PARAM);
		String saPath=AwwConfig.getTemplateParameter(config, templateId, AwwConfig.SA_TEMPLATE_PARAM);
		String saWorkflow=AwwConfig.getTemplateParameter(config, templateId, AwwConfig.SA_WORKFLOW_TEMPLATE_PARAM);
		String generateName=AwwConfig.getTemplateParameter(config, templateId, AwwConfig.GENERATE_NAME_TEMPLATE_PARAM);
		String cmpntName=AwwConfig.getTemplateParameter(config, templateId, AwwConfig.CMPNT_NAME_TEMPLATE_PARAM);
		String title=args.getProperty("title");
		String broadcast=AwwConfig.getTemplateParameter(config,templateId,AwwConfig.BROADCAST_TEMPLATE_PARAM);
		String listen=AwwConfig.getTemplateParameter(config,templateId,AwwConfig.LISTEN_TEMPLATE_PARAM);
		String style=AwwConfig.getTemplateParameter(config,templateId,AwwConfig.STYLE_TEMPLATE_PARAM);
		String _plugin=AwwConfig.getTemplateParameter(config,templateId,AwwConfig.PLUGIN_TEMPLATE_PARAM);
		WidgetContributor plugin=null;
		boolean genName=false;
		/* Validate inputs */
		if(saPath==null||(saPath=saPath.trim()).length()==0){
			if(isErrorEnabled){LOGGER.severe(METHOD_NAME+": Site area template parameter is missing!");}
			faults.add(new PortletResult(Result.ERROR,Results.LIST_WIDGET_MISSING_SA_EXCEPTION));
			return null;
		}
		if(title==null||(title=title.trim()).length()==0){
			if(isErrorEnabled){LOGGER.severe(METHOD_NAME+": Title argument is missing!");}
			faults.add(new PortletResult(Result.ERROR,Results.LIST_WIDGET_MISSING_TITLE_EXCEPTION));
			return null;
		}
		if(generateName!=null&&(generateName=generateName.trim()).length()>0){
			genName=Boolean.valueOf(generateName).booleanValue();
		}
		if(broadcast!=null&&(broadcast=broadcast.trim()).length()>0){
			if(broadcast.equalsIgnoreCase("none")){broadcast=Constants.WCM_LINKING_NONE;}
			else if(broadcast.equalsIgnoreCase("self")){broadcast=Constants.WCM_LINKING_SELF;}
			else if(broadcast.equalsIgnoreCase("other")){broadcast=Constants.WCM_LINKING_OTHER;}
			else if(broadcast.equalsIgnoreCase("dynamic")){broadcast=Constants.WCM_LINKING_DYNAMIC;}
			else{broadcast=null;}
		}else{
			broadcast=null;
		}
		if(listen!=null&&(listen=listen.trim()).length()>0){
			if(listen.equalsIgnoreCase("none")){listen=Constants.WCM_LINKING_NONE;}
			else if(listen.equalsIgnoreCase("self")){listen=Constants.WCM_LINKING_SELF;}
			else if(listen.equalsIgnoreCase("other")){listen=Constants.WCM_LINKING_OTHER;}
			else{listen=Constants.WCM_LINKING_NONE;}
		}else{
			listen=Constants.WCM_LINKING_NONE;
		}
		if(_plugin!=null&&(_plugin=_plugin.trim()).length()>0){
			try {
				plugin=(WidgetContributor)createPlugin(_plugin,WidgetContributor.class);
			} catch (DesignerException e) {
				for(Result r:e.getResults()){faults.add(r);}
				return null;
			}
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
					faults.add(new PortletResult(Result.ERROR,Results.LIST_WIDGET_AT_DOCLIB_NOT_FOUND_EXCEPTION,new Object[] {lib,atName}));
					return null;					
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
				faults.add(new PortletResult(Result.ERROR,Results.LIST_WIDGET_AT_NOT_FOUND_EXCEPTION,new Object[] {atName,awwDesignLib.getTitle()}));
				return null;
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
					faults.add(new PortletResult(Result.ERROR,Results.LIST_WIDGET_WORKFLOW_DOCLIB_NOT_FOUND_EXCEPTION,new Object[] {lib,workflow}));
					return null;					
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
				faults.add(new PortletResult(Result.ERROR,Results.LIST_WIDGET_WORKFLOW_NOT_FOUND_EXCEPTION,new Object[] {workflow,awwDesignLib.getTitle()}));
				return null;
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
					faults.add(new PortletResult(Result.ERROR,Results.LIST_WIDGET_SA_WORKFLOW_DOCLIB_NOT_FOUND_EXCEPTION,new Object[] {lib,saWorkflow}));
					return null;					
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
				faults.add(new PortletResult(Result.ERROR,Results.LIST_WIDGET_SA_WORKFLOW_NOT_FOUND_EXCEPTION,new Object[] {saWorkflow,awwDesignLib.getTitle()}));
				return null;
			}
		}		
		/* Lookup the library component if specified */
		DocumentId cmpntId=null;
		if(cmpntName!=null&&(cmpntName=cmpntName.trim()).length()>0){
			DocumentLibrary doclib=awwDesignLib;
			int idx=cmpntName.indexOf("/");
			if(idx>0&&(idx+1<cmpntName.length())){
				String lib=cmpntName.substring(0,idx);
				doclib=workspace.getDocumentLibrary(lib);
				if(doclib==null){
					if(isErrorEnabled){LOGGER.severe(METHOD_NAME+": The document library "+lib+" referenced in the component "+saWorkflow+" could not be found!");}
					faults.add(new PortletResult(Result.ERROR,Results.LIST_WIDGET_CMPNT_DOCLIB_NOT_FOUND_EXCEPTION,new Object[] {lib,cmpntName}));
					return null;					
				}
				cmpntName=cmpntName.substring(idx+1);
			}			
			workspace.setCurrentDocumentLibrary(doclib);
			if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Locating the library component '"+cmpntName+"'...");}
			DocumentIdIterator itr=workspace.findComponentByName(cmpntName);
			if(itr.hasNext()){
				cmpntId=itr.nextId();
			}else{
				if(isErrorEnabled){LOGGER.severe(METHOD_NAME+": Could not find the library component "+cmpntName+"!");}
				faults.add(new PortletResult(Result.ERROR,Results.LIST_WIDGET_CMPNT_NOT_FOUND_EXCEPTION,new Object[] {cmpntName,awwDesignLib.getTitle()}));
				return null;
			}
		}		
		/* Now lookup the Site area */
		workspace.setCurrentDocumentLibrary(awwContentLib);
		String fullSaPath="/"+awwContentLib.getName()+saPath;
		if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Locating the parent site area '"+fullSaPath+"'...");}
		DocumentIdIterator itr=workspace.findByPath(fullSaPath,Workspace.WORKFLOWSTATUS_PUBLISHED);
		if(itr.hasNext()){
			DocumentId saId=itr.nextId();
			Content content=null;
			try{
				List<Document> docs=new ArrayList<Document>(5);
				// Create the new site area
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Creating a new site area in the workspace with the title '"+title+"'...");}
				SiteArea sa=workspace.createSiteArea(saId,null,ChildPosition.END);
				// Generate the name and set the name/title
				String name=Utils.makeName(title);
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Setting site area name to '"+name+"'...");}
				sa.setName(name);
				sa.setTitle(title);
				if(saWfId!=null){
					if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Setting site area workflow to '"+saWfId.getName()+"'...");}
					sa.setWorkflowId(saWfId);
					sa.setEffectiveDate(new java.util.Date());
				}
				docs.add(sa);
				if(atId==null){
					// If an AT was not specified, default the content to the parents default.
					SiteArea parentSa=(SiteArea)workspace.getById(saId,true);
					sa.setDefaultContent(parentSa.getDefaultContent());
				}else{
					// Otherwise, create the new content
					if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Creating a new content document in the workspace with the title '"+title+"'...");}
					content=workspace.createContent(atId,sa.getId(),null,ChildPosition.END);
					content.setTitle(title);
					if(genName){
						// Generate the name and set the name/title
						name=Utils.makeName(title);
						if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Setting content name to '"+name+"'...");}
						content.setName(name);
					}
					if(wfId!=null){
						if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Setting content workflow to '"+wfId.getName()+"'...");}
						content.setWorkflowId(wfId);
						content.setEffectiveDate(new Date());
					}
					if(cmpntId!=null&&content.hasComponent(Constants.WRAPPER_CMPNT_NAME)){		
						try {
							if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Content '"+title+"' is a component wrapper. Setting component reference to '"+cmpntId+"'...");}
							ReferenceComponent ref=(ReferenceComponent)content.getComponent(Constants.WRAPPER_CMPNT_NAME);
							LibraryComponent cmpnt=(LibraryComponent)workspace.getById(cmpntId,true);
							ref.setComponentRef(cmpnt);
							content.setComponent(Constants.WRAPPER_CMPNT_NAME, ref);
							cmpntId=null;
						}catch(DocumentRetrievalException e){
							if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": Could not access the component with id '"+cmpntId+"'!",e);}
							faults.add(new PortletResult(Result.ERROR,Results.LIST_WIDGET_WRAPPER_CMPNT_ACCESS_EXCEPTION,new Object[] {cmpntId},e));
							return null;
						}catch(AuthorizationException e){
							if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": User is not authorized to access the component with id '"+cmpntId+"'!",e);}
							faults.add(new PortletResult(Result.ERROR,Results.LIST_WIDGET_WRAPPER_CMPNT_AUTH_EXCEPTION,new Object[] {title},e));
							return null;
						}catch(ComponentNotFoundException ignore){
							// We are using hasComponent to check first so this should never happen
							assert false;
						}catch(IllegalTypeChangeException ignore){
							// We are setting the same component we get so this should never happen
							assert false;
						}
					}
					if(style!=null&&(style=style.trim()).length()>0&&content.hasComponent(Constants.STYLE_CMPNT_NAME)){
						try {
							if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Content '"+title+"' has a style specified. Setting style to '"+style+"'...");}
							TextComponent text=(TextComponent)content.getComponent(Constants.STYLE_CMPNT_NAME);
							text.setText(style);
							content.setComponent(Constants.STYLE_CMPNT_NAME, text);
						}catch(ComponentNotFoundException ignore){
							// We are using hasComponent to check first so this should never happen
							assert false;
						}catch(IllegalTypeChangeException ignore){
							// We are setting the same component we get so this should never happen
							assert false;
						}
					}
					docs.add(content);
				}
				if(plugin!=null){plugin.contentCreated(this,config,templateId,workspace,awwDesignLib,awwContentLib,args,docs);}
				// Save everything
				try{
					if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Saving documents to the workspace...");}
					// TODO: Prolly should do this in a transaction so we don't leave dangling stuff
					if(Utils.saveDocuments(workspace, docs, faults)){			
						if(plugin!=null){plugin.contentSaved(this,config,templateId,workspace,awwDesignLib,awwContentLib,args,docs);}		
						if(content!=null){
							docs.clear();								
							// Assign the default content
							if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Setting the default content for the new site area...");}
							sa.setDefaultContent(content.getId());
							docs.add(sa);
							// Save the SA again
							if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Saving the modified sitearea to the workspace...");}
							if(Utils.saveDocuments(workspace,docs,faults)){
								lrpConfig = Utils.createLrpConfig(content,cmpntId,"content",null,null,broadcast,listen,title);
							}
						}else{
							lrpConfig = Utils.createLrpConfig(sa,cmpntId,"sitearea",null,null,broadcast,listen,title);
						}
					}
				}catch(DocumentSaveException e){
					if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": Could not save the new list with the title '"+title+"'!",e);}
					faults.add(new PortletResult(Result.ERROR,Results.LIST_WIDGET_DOCUMENT_SAVE_EXCEPTION,new Object[] {title},e));
				}catch(AuthorizationException e){
					if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": User is not authorized to save the list with the title '"+title+"'!",e);}
					faults.add(new PortletResult(Result.ERROR,Results.LIST_WIDGET_DOCUMENT_SAVE_ACCESS_EXCEPTION,new Object[] {title},e));
				}catch(DuplicateChildException e){
					if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": A list with the title '"+title+"' already exists!",e);}
					faults.add(new PortletResult(Result.ERROR,Results.LIST_WIDGET_DOCUMENT_DUP_EXCEPTION,new Object[] {title},e));									
				}catch(DesignerException e){
					for(Result r:e.getResults()){faults.add(r);}
				}
			}catch(DocumentCreationException e){
				if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": Could not create a new list with the title '"+title+"'!",e);}
				faults.add(new PortletResult(Result.ERROR,Results.LIST_WIDGET_DOCUMENT_CREATE_EXCEPTION,new Object[] {title},e));
			}catch(AuthorizationException e){
				if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": User is not authorized to create a list with the title '"+title+"'!",e);}
				faults.add(new PortletResult(Result.ERROR,Results.LIST_WIDGET_DOCUMENT_CREATE_ACCESS_EXCEPTION,new Object[] {title},e));
			}catch (DocumentRetrievalException e){
				if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": Could not access the site area at path '"+saPath+"'!",e);}
				faults.add(new PortletResult(Result.ERROR,Results.LIST_WIDGET_DOCUMENT_PARENT_ACCESS_EXCEPTION,new Object[] {saPath},e));
			}catch (PropertyRetrievalException e){
				if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": Could not get default content for site area at path '"+saPath+"'!",e);}
				faults.add(new PortletResult(Result.ERROR,Results.LIST_WIDGET_DOCUMENT_PARENT_DEFAULT_EXCEPTION,new Object[] {saPath},e));
			}catch (OperationFailedException e) {
				if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": The workflow could not be set on the document with the title '"+title+"'!",e);}
				faults.add(new PortletResult(Result.ERROR,Results.LIST_WIDGET_DOCUMENT_SET_WORKFLOW_EXCEPTION,new Object[] {wfId.getName(),title},e));
			}catch(IllegalDocumentTypeException ignore){
				assert false;
			}catch(DesignerException e){
				for(Result r:e.getResults()){faults.add(r);}
			}
		}else{
			if(isErrorEnabled){LOGGER.severe(METHOD_NAME+": Could not find the parent site area "+saPath+"!");}
			faults.add(new PortletResult(Result.ERROR,Results.LIST_WIDGET_SA_NOT_FOUND_EXCEPTION,new Object[] {saPath,awwDesignLib.getTitle()}));
		}
		if(isTraceEnabled){LOGGER.exiting(CLASS_NAME,METHOD_NAME,lrpConfig);}
		return lrpConfig;
	}

	public LrpConfig createCompositeList(Properties config,String templateId,Workspace workspace,DocumentLibrary awwDesignLib,DocumentLibrary awwContentLib,Properties args,List<Result> faults){
		final String METHOD_NAME="createCompositeList";
		boolean isTraceEnabled=LOGGER.isLoggable(Level.FINER);
		boolean isDebugEnabled=LOGGER.isLoggable(Level.FINEST);
		boolean isErrorEnabled=LOGGER.isLoggable(Level.SEVERE);
		if(isTraceEnabled){LOGGER.entering(CLASS_NAME,METHOD_NAME);}		
		LrpConfig lrpConfig=null;
		String atName=AwwConfig.getTemplateParameter(config, templateId, AwwConfig.AT_TEMPLATE_PARAM);
		String workflow=AwwConfig.getTemplateParameter(config, templateId, AwwConfig.WORKFLOW_TEMPLATE_PARAM);
		String saPath=AwwConfig.getTemplateParameter(config, templateId, AwwConfig.SA_TEMPLATE_PARAM);
		String saWorkflow=AwwConfig.getTemplateParameter(config, templateId, AwwConfig.SA_WORKFLOW_TEMPLATE_PARAM);
		String generateName=AwwConfig.getTemplateParameter(config, templateId, AwwConfig.GENERATE_NAME_TEMPLATE_PARAM);
		String cmpntName=AwwConfig.getTemplateParameter(config, templateId, AwwConfig.CMPNT_NAME_TEMPLATE_PARAM);
		String title=args.getProperty("title");
		String subTitle=args.getProperty("subTitle");
		String broadcast=AwwConfig.getTemplateParameter(config,templateId,AwwConfig.BROADCAST_TEMPLATE_PARAM);
		String listen=AwwConfig.getTemplateParameter(config,templateId,AwwConfig.LISTEN_TEMPLATE_PARAM);
		String style=AwwConfig.getTemplateParameter(config,templateId,AwwConfig.STYLE_TEMPLATE_PARAM);
		String _plugin=AwwConfig.getTemplateParameter(config,templateId,AwwConfig.PLUGIN_TEMPLATE_PARAM);
		WidgetContributor plugin=null;
		boolean genName=false;
		/* Validate inputs */
		if(saPath==null||(saPath=saPath.trim()).length()==0){
			if(isErrorEnabled){LOGGER.severe(METHOD_NAME+": Site area template parameter is missing!");}
			faults.add(new PortletResult(Result.ERROR,Results.COMPLIST_WIDGET_MISSING_SA_EXCEPTION));
			return null;
		}
		if(title==null||(title=title.trim()).length()==0){
			if(isErrorEnabled){LOGGER.severe(METHOD_NAME+": Title argument is missing!");}
			faults.add(new PortletResult(Result.ERROR,Results.COMPLIST_WIDGET_MISSING_TITLE_EXCEPTION));
			return null;
		}
		if(subTitle==null||(subTitle=subTitle.trim()).length()==0){
			if(isErrorEnabled){LOGGER.severe(METHOD_NAME+": Sub-title argument is missing!");}
			faults.add(new PortletResult(Result.ERROR,Results.COMPLIST_WIDGET_MISSING_SUBTITLE_EXCEPTION));
			return null;
		}	
		if(generateName!=null&&(generateName=generateName.trim()).length()>0){
			genName=Boolean.valueOf(generateName).booleanValue();
		}	
		if(broadcast!=null&&(broadcast=broadcast.trim()).length()>0){
			if(broadcast.equalsIgnoreCase("none")){broadcast=Constants.WCM_LINKING_NONE;}
			else if(broadcast.equalsIgnoreCase("self")){broadcast=Constants.WCM_LINKING_SELF;}
			else if(broadcast.equalsIgnoreCase("other")){broadcast=Constants.WCM_LINKING_OTHER;}
			else if(broadcast.equalsIgnoreCase("dynamic")){broadcast=Constants.WCM_LINKING_DYNAMIC;}
			else{broadcast=null;}
		}else{
			broadcast=null;
		}
		if(listen!=null&&(listen=listen.trim()).length()>0){
			if(listen.equalsIgnoreCase("none")){listen=Constants.WCM_LINKING_NONE;}
			else if(listen.equalsIgnoreCase("self")){listen=Constants.WCM_LINKING_SELF;}
			else if(listen.equalsIgnoreCase("other")){listen=Constants.WCM_LINKING_OTHER;}
			else{listen=Constants.WCM_LINKING_NONE;}
		}else{
			listen=Constants.WCM_LINKING_NONE;
		}	
		if(_plugin!=null&&(_plugin=_plugin.trim()).length()>0){
			try {
				plugin=(WidgetContributor)createPlugin(_plugin,WidgetContributor.class);
			} catch (DesignerException e) {
				for(Result r:e.getResults()){faults.add(r);}
				return null;
			}
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
					faults.add(new PortletResult(Result.ERROR,Results.COMPLIST_WIDGET_AT_DOCLIB_NOT_FOUND_EXCEPTION,new Object[] {lib,atName}));
					return null;					
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
				faults.add(new PortletResult(Result.ERROR,Results.COMPLIST_WIDGET_AT_NOT_FOUND_EXCEPTION,new Object[] {atName,awwDesignLib.getTitle()}));
				return null;
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
					faults.add(new PortletResult(Result.ERROR,Results.COMPLIST_WIDGET_WORKFLOW_DOCLIB_NOT_FOUND_EXCEPTION,new Object[] {lib,workflow}));
					return null;					
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
				faults.add(new PortletResult(Result.ERROR,Results.COMPLIST_WIDGET_WORKFLOW_NOT_FOUND_EXCEPTION,new Object[] {workflow,awwDesignLib.getTitle()}));
				return null;
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
					faults.add(new PortletResult(Result.ERROR,Results.COMPLIST_WIDGET_SA_WORKFLOW_DOCLIB_NOT_FOUND_EXCEPTION,new Object[] {lib,workflow}));
					return null;					
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
				faults.add(new PortletResult(Result.ERROR,Results.COMPLIST_WIDGET_SA_WORKFLOW_NOT_FOUND_EXCEPTION,new Object[] {saWorkflow,awwDesignLib.getTitle()}));
				return null;
			}
		}	
		/* Lookup the library component if specified */
		DocumentId cmpntId=null;
		if(cmpntName!=null&&(cmpntName=cmpntName.trim()).length()>0){
			DocumentLibrary doclib=awwDesignLib;
			int idx=cmpntName.indexOf("/");
			if(idx>0&&(idx+1<cmpntName.length())){
				String lib=cmpntName.substring(0,idx);
				doclib=workspace.getDocumentLibrary(lib);
				if(doclib==null){
					if(isErrorEnabled){LOGGER.severe(METHOD_NAME+": The document library "+lib+" referenced in the component "+saWorkflow+" could not be found!");}
					faults.add(new PortletResult(Result.ERROR,Results.COMPLIST_WIDGET_CMPNT_DOCLIB_NOT_FOUND_EXCEPTION,new Object[] {lib,cmpntName}));
					return null;					
				}
				cmpntName=cmpntName.substring(idx+1);
			}			
			workspace.setCurrentDocumentLibrary(doclib);
			if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Locating the library component '"+cmpntName+"'...");}
			DocumentIdIterator itr=workspace.findComponentByName(cmpntName);
			if(itr.hasNext()){
				cmpntId=itr.nextId();
			}else{
				if(isErrorEnabled){LOGGER.severe(METHOD_NAME+": Could not find the library component "+cmpntName+"!");}
				faults.add(new PortletResult(Result.ERROR,Results.COMPLIST_WIDGET_CMPNT_NOT_FOUND_EXCEPTION,new Object[] {cmpntName,awwDesignLib.getTitle()}));
				return null;
			}
		}			
		workspace.setCurrentDocumentLibrary(awwContentLib);
		String fullSaPath="/"+awwContentLib.getName()+saPath;
		if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Locating the parent site area '"+fullSaPath+"'...");}
		DocumentIdIterator itr=workspace.findByPath(fullSaPath,Workspace.WORKFLOWSTATUS_PUBLISHED);
		if(itr.hasNext()){
			DocumentId saId=itr.nextId();
			Content content=null;
			try{
				List<Document> docs=new ArrayList<Document>(5);
				SiteArea parentSa=(SiteArea)workspace.getById(saId,true);
				DocumentId defaultContentId=parentSa.getDefaultContent();
				// Create the list site area
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Creating a new site area in the workspace with the title '"+title+"'...");}
				SiteArea list=workspace.createSiteArea(saId, null, ChildPosition.END);
				String listName=Utils.makeName(title);
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Setting site area name to '"+listName+"'...");}
				list.setName(listName);
				list.setTitle(title);
				if(saWfId!=null){
					if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Setting site area workflow on parent to '"+saWfId.getName()+"'...");}
					list.setWorkflowId(saWfId);
					list.setEffectiveDate(new java.util.Date());
				}
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Setting default content ID to '"+defaultContentId+"'...");}
				list.setDefaultContent(defaultContentId);
				docs.add(list);
				// Create the child list site area
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Creating a new site area in the workspace with the title '"+subTitle+"'...");}
				SiteArea childList=workspace.createSiteArea(list.getId(),null,ChildPosition.END);
				String childListName=Utils.makeName(subTitle);
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Setting site area name to '"+childListName+"'...");}
				childList.setName(childListName);
				childList.setTitle(subTitle);
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
					if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Creating a new content document in the workspace with the title '"+subTitle+"'...");}
					content=workspace.createContent(atId,childList.getId(),null,ChildPosition.END);
					content.setTitle(subTitle);
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
					if(cmpntId!=null&&content.hasComponent(Constants.WRAPPER_CMPNT_NAME)){		
						try {
							if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Content '"+title+"' is a component wrapper. Setting component reference to '"+cmpntId+"'...");}
							ReferenceComponent ref=(ReferenceComponent)content.getComponent(Constants.WRAPPER_CMPNT_NAME);
							LibraryComponent cmpnt=(LibraryComponent)workspace.getById(cmpntId,true);
							ref.setComponentRef(cmpnt);
							content.setComponent(Constants.WRAPPER_CMPNT_NAME, ref);
							cmpntId=null;
						}catch(DocumentRetrievalException e){
							if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": Could not access the component with id '"+cmpntId+"'!",e);}
							faults.add(new PortletResult(Result.ERROR,Results.COMPLIST_WIDGET_WRAPPER_CMPNT_ACCESS_EXCEPTION,new Object[] {cmpntId},e));
							return null;
						}catch(AuthorizationException e){
							if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": User is not authorized to access the component with id '"+cmpntId+"'!",e);}
							faults.add(new PortletResult(Result.ERROR,Results.COMPLIST_WIDGET_WRAPPER_CMPNT_AUTH_EXCEPTION,new Object[] {title},e));
							return null;
						}catch(ComponentNotFoundException ignore){
							// We are using hasComponent to check first so this should never happen
							assert false;
						}catch(IllegalTypeChangeException ignore){
							// We are setting the same component we get so this should never happen
							assert false;
						}
					}
					if(style!=null&&(style=style.trim()).length()>0&&content.hasComponent(Constants.STYLE_CMPNT_NAME)){
						try {
							if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Content '"+title+"' has a style specified. Setting style to '"+style+"'...");}
							TextComponent text=(TextComponent)content.getComponent(Constants.STYLE_CMPNT_NAME);
							text.setText(style);
							content.setComponent(Constants.STYLE_CMPNT_NAME, text);
						}catch(ComponentNotFoundException ignore){
							// We are using hasComponent to check first so this should never happen
							assert false;
						}catch(IllegalTypeChangeException ignore){
							// We are setting the same component we get so this should never happen
							assert false;
						}
					}					
					docs.add(content);					
				}
				if(plugin!=null){plugin.contentCreated(this,config,templateId,workspace,awwDesignLib,awwContentLib,args,docs);}
				// Save everything
				try{
					if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Saving documents to the workspace...");}
					// TODO: Prolly should do this in a transaction so we don't leave dangling stuff
					if(Utils.saveDocuments(workspace, docs, faults)){
						if(plugin!=null){plugin.contentSaved(this,config,templateId,workspace,awwDesignLib,awwContentLib,args,docs);}
						if(content!=null){
							docs.clear();
							// Assign the default content
							if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Setting the default content for the new site area...");}
							childList.setDefaultContent(content.getId());
							docs.add(childList);
							// Save the SA again
							if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Saving the modified sitearea to the workspace...");}
							if(Utils.saveDocuments(workspace,docs,faults)){				
								lrpConfig = Utils.createLrpConfig(content,cmpntId,"content",null,null,broadcast,listen,title);
							}							
						}else{
							lrpConfig = Utils.createLrpConfig(list,cmpntId,"sitearea",null,null,broadcast,listen,title);
						}
					}
				}catch(DocumentSaveException e){
					if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": Could not save the new composite list with the title '"+title+"'!",e);}
					faults.add(new PortletResult(Result.ERROR,Results.COMPLIST_WIDGET_DOCUMENT_SAVE_EXCEPTION,new Object[] {title},e));
				}catch(AuthorizationException e){
					if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": User is not authorized to save the composite list with the title '"+title+"'!",e);}
					faults.add(new PortletResult(Result.ERROR,Results.COMPLIST_WIDGET_DOCUMENT_SAVE_ACCESS_EXCEPTION,new Object[] {title},e));
				}catch(DuplicateChildException e){
					if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": A composite list with the title '"+title+"' already exists!",e);}
					faults.add(new PortletResult(Result.ERROR,Results.COMPLIST_WIDGET_DOCUMENT_DUP_EXCEPTION,new Object[] {title},e));									
				}catch(DesignerException e){
					for(Result r:e.getResults()){faults.add(r);}
				}
			}catch(DocumentCreationException e){
				if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": Could not create a new composite list with the title '"+title+"'!",e);}
				faults.add(new PortletResult(Result.ERROR,Results.COMPLIST_WIDGET_DOCUMENT_CREATE_EXCEPTION,new Object[] {title},e));
			}catch(AuthorizationException e){
				if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": User is not authorized to create a composite list with the title '"+title+"'!",e);}
				faults.add(new PortletResult(Result.ERROR,Results.COMPLIST_WIDGET_DOCUMENT_CREATE_ACCESS_EXCEPTION,new Object[] {title},e));
			}catch (DocumentRetrievalException e){
				if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": Could not access the site area at path '"+saPath+"'!",e);}
				faults.add(new PortletResult(Result.ERROR,Results.COMPLIST_WIDGET_DOCUMENT_PARENT_ACCESS_EXCEPTION,new Object[] {saPath},e));
			}catch (PropertyRetrievalException e){
				if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": Could not get default content for site area at path '"+saPath+"'!",e);}
				faults.add(new PortletResult(Result.ERROR,Results.COMPLIST_WIDGET_DOCUMENT_PARENT_DEFAULT_EXCEPTION,new Object[] {saPath},e));
			}catch (OperationFailedException e) {
				if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": The workflow could not be set on the document with the title '"+title+"'!",e);}
				faults.add(new PortletResult(Result.ERROR,Results.COMPLIST_WIDGET_DOCUMENT_SET_WORKFLOW_EXCEPTION,new Object[] {wfId.getName(),title},e));
			}catch(IllegalDocumentTypeException ignore){
				assert false;	
			}catch(DesignerException e){
				for(Result r:e.getResults()){faults.add(r);}
			}
		}else{
			if(isErrorEnabled){LOGGER.severe(METHOD_NAME+": Could not find the parent site area "+saPath+"!");}
			faults.add(new PortletResult(Result.ERROR,Results.COMPLIST_WIDGET_SA_NOT_FOUND_EXCEPTION,new Object[] {saPath,awwDesignLib.getTitle()}));
		}
		if(isTraceEnabled){LOGGER.exiting(CLASS_NAME,METHOD_NAME,lrpConfig);}
		return lrpConfig;	
	}
	
	public boolean publish(String idr,List<Result> faults){
		final String METHOD_NAME="publish";
		boolean isTraceEnabled=LOGGER.isLoggable(Level.FINER);
		boolean isDebugEnabled=LOGGER.isLoggable(Level.FINEST);
		boolean isErrorEnabled=LOGGER.isLoggable(Level.SEVERE);
		if(isTraceEnabled){LOGGER.entering(CLASS_NAME,METHOD_NAME);}
		DocumentLibrary currDocLib=null;		
		boolean success=false;
		try{
			/* Access the workspace */
			if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Accessing the workspace...");}
			Workspace workspace = WCM_API.getRepository().getWorkspace(this.currUser);	
			try {				
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Logging in to workspace...");}
				workspace.login();
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Saving current library...");}
				try{currDocLib=workspace.getCurrentDocumentLibrary();}catch(NullPointerException ignore){/*This can safely be ignored per best practices.*/}
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Loading the content with idr '"+idr+"'...");}
				Content content;
				DocumentId docid=workspace.createDocumentId(idr);
				content = (Content)workspace.getById(docid);
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Pushing content to next workflow stage...");}
				// TODO: Would be nice to be able to do this in a loop until we check that it really is published.
				content.nextWorkflowStage();	
				success=true;
			} catch (DocumentRetrievalException e) {
				if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": Could not access the content with id '"+idr+"'!",e);}
				faults.add(new PortletResult(Result.ERROR,Results.PUBLISH_DOCUMENT_ACCESS_EXCEPTION,new Object[] {idr},e));
			} catch (AuthorizationException e) {
				if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": User is not authorized to move the content with the id '"+idr+"' to the next workflow stage!",e);}
				faults.add(new PortletResult(Result.ERROR,Results.PUBLISH_DOCUMENT_APPROVE_ACCESS_EXCEPTION,new Object[] {idr},e));
			} catch (OperationFailedException e) {
				if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": An error occurred while trying to move the content with the id '"+idr+"' to the next workflow stage!",e);}
				faults.add(new PortletResult(Result.ERROR,Results.PUBLISH_DOCUMENT_OPERATION_EXCEPTION,new Object[] {idr},e));
			} catch (NoMoreWorkflowStagesException e) {
				if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": The content with the id '"+idr+"' could not be moved to the next workflow stage because there are no more workflow stages in the workflow assigned to the content!",e);}
				faults.add(new PortletResult(Result.ERROR,Results.PUBLISH_DOCUMENT_WORKFLOW_EXCEPTION,new Object[] {idr},e));
			} catch (DocumentIdCreationException e) {
				if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": Could not create a document id for the content with the id '"+idr+"'!",e);}
				faults.add(new PortletResult(Result.ERROR,Results.PUBLISH_DOCUMENT_CREATE_ID_EXCEPTION,new Object[] {idr},e));
			} finally{	
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Restoring last document library...");}
				if(currDocLib!=null){workspace.setCurrentDocumentLibrary(currDocLib);}
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Logging out of workspace...");}
				workspace.logout();
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Ending workspace...");}
				WCM_API.getRepository().endWorkspace();
			}
		}catch(WCMException e){
			if(isErrorEnabled){LOGGER.log(Level.SEVERE,METHOD_NAME+": A user workspace could not be created for the principal "+this.currUser+"!",e);}
			faults.add(new PortletResult(Result.ERROR,Results.PUBLISH_DOCUMENT_WORKSPACE_ACCESS_EXCEPTION,new Object[] {this.currUser}));
		}
		if(isTraceEnabled){LOGGER.exiting(CLASS_NAME,METHOD_NAME,success);}
		return success;
	}
}
