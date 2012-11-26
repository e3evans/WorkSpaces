package com.asponte.commons.portal.wcm;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ibm.workplace.wcm.api.Content;
import com.ibm.workplace.wcm.api.Document;
import com.ibm.workplace.wcm.api.DocumentId;
import com.ibm.workplace.wcm.api.DocumentIdIterator;
import com.ibm.workplace.wcm.api.DocumentLibrary;
import com.ibm.workplace.wcm.api.DocumentTypes;
import com.ibm.workplace.wcm.api.TextComponent;
import com.ibm.workplace.wcm.api.WCM_API;
import com.ibm.workplace.wcm.api.Workspace;
import com.ibm.workplace.wcm.api.exceptions.WCMException;

public class AwwConfig {
	/* Default Libraries */
	public static final String AWW_CONTENT_LIBRARY="AWW Content Library";
	public static final String AWW_DESIGN_LIBRARY="AWW Design Library";
	
	/* Design Library Default Site */
	public static final String AWW_SITE="AWW";
	
	/* Widget Configuration Stuff */
	public static final String WIDGET_CONFIG_PARAM="com.asponte.portal.designer.wcm.WidgetConfiguration";
	public static final String WIDGET_CONFIG_TEXT_CMPNT_NAME="Body";
	public static final String WIDGET_CONFIG_PATH="/"+AWW_SITE+"/Config/Widget Configuration";
	
	/* Template parameters */
	public static final String CONFIG_LIB_NAME="configLib";
	public static final String DEFAULT_CONTENT_LIBRARY="contentLibrary";
	public static final String DEFAULT_DESIGN_LIBRARY="designLibrary";
	public static final String WIDGET_TYPE_TEMPLATE_PARAM="widgetType";
	public static final String GENERATE_NAME_TEMPLATE_PARAM="generateName";
	public static final String DESIGN_LIBRARY_TEMPLATE_PARAM="designLibrary";
	public static final String CONTENT_LIBRARY_TEMPLATE_PARAM="contentLibrary";
	public static final String AT_TEMPLATE_PARAM="authoringTemplate";
	public static final String WORKFLOW_TEMPLATE_PARAM="workflow";
	public static final String SA_TEMPLATE_PARAM="siteArea";
	public static final String SA_WORKFLOW_TEMPLATE_PARAM="siteAreaWorkflow";
	public static final String ARCHIVE_SA_TEMPLATE_PARAM="archiveSiteArea";
	public static final String CMPNT_NAME_TEMPLATE_PARAM="cmpnt";
	public static final String BROADCAST_TEMPLATE_PARAM="broadcast";
	public static final String LISTEN_TEMPLATE_PARAM="listen";
	public static final String STYLE_TEMPLATE_PARAM="style";
	public static final String PLUGIN_TEMPLATE_PARAM="plugin";
	
	/* Template IDs */
	public static final String ANNC_TEMPLATE_ID="announcement";
	public static final String CAL_TEMPLATE_ID="calendar";
	public static final String CLIPS_TEMPLATE_ID="clips";
	public static final String DMSG_TEMPLATE_ID="daily_message";
	public static final String DCAT_TEMPLATE_ID="doc_catalog";
	public static final String DLST_TEMPLATE_ID="doc_list";
	public static final String EVTLST_TEMPLATE_ID="event_list";
	public static final String IMG_TEMPLATE_ID="image";
	public static final String MOV_TEMPLATE_ID="movie";
	public static final String NEWS_TEMPLATE_ID="news";
	public static final String PNAV_TEMPLATE_ID="page_navigator";
	public static final String POLL_TEMPLATE_ID="poll";
	public static final String RTE_TEMPLATE_ID="rich_text";
	public static final String SWF_TEMPLATE_ID="swf";
	public static final String VIDLST_TEMPLATE_ID="video_list";
	
	private static final String CLASS_NAME=AwwConfig.class.getName();
	private static final Logger LOGGER=Logger.getLogger(CLASS_NAME);	
	private static final Properties NULL_PROPERTIES=new Properties();
	private static Map<String,Properties> configCache=new HashMap<String,Properties>();
	private AwwConfig(){}
	public static Properties getConfigFor(Document content) throws WCMException, IOException{
		final String METHOD_NAME="getConfigFor";
		boolean isTraceEnabled=LOGGER.isLoggable(Level.FINER);
		boolean isDebugEnabled=LOGGER.isLoggable(Level.FINEST);
		boolean isErrorEnabled=LOGGER.isLoggable(Level.SEVERE);
		if(isTraceEnabled){LOGGER.entering(CLASS_NAME,METHOD_NAME);}
		DocumentLibrary ownerLib=content.getOwnerLibrary();
		String configPath=ownerLib.getName()+WIDGET_CONFIG_PATH;
		Properties props=getConfiguration(configPath);
		if(isTraceEnabled){LOGGER.exiting(CLASS_NAME,METHOD_NAME,props);}
		return props;
	}
	public static String getDesignLibraryFor(Document content) throws WCMException, IOException{
		final String METHOD_NAME="getDesignLibraryFor";
		boolean isTraceEnabled=LOGGER.isLoggable(Level.FINER);
		boolean isDebugEnabled=LOGGER.isLoggable(Level.FINEST);
		boolean isErrorEnabled=LOGGER.isLoggable(Level.SEVERE);
		if(isTraceEnabled){LOGGER.entering(CLASS_NAME,METHOD_NAME);}		
		String designLib=null;
		Properties props=getConfigFor(content);
		String lib=null;
		if(props!=null){
			 lib=props.getProperty(DEFAULT_DESIGN_LIBRARY);
			 if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Design library config default is '"+lib+"'...");}	
			 if(lib==null){
				 lib=AWW_DESIGN_LIBRARY;
			 }			 
		}
		if(isTraceEnabled){LOGGER.exiting(CLASS_NAME,METHOD_NAME,lib);}
		return lib;
	}
	public static Properties getConfiguration(String configPath) throws WCMException, IOException {
		final String METHOD_NAME="getConfiguration";
		boolean isTraceEnabled=LOGGER.isLoggable(Level.FINER);
		boolean isDebugEnabled=LOGGER.isLoggable(Level.FINEST);
		boolean isErrorEnabled=LOGGER.isLoggable(Level.SEVERE);
		if(isTraceEnabled){LOGGER.entering(CLASS_NAME,METHOD_NAME);}		
		Properties props=configCache.get(configPath);
		if(props==null){
			synchronized(AwwConfig.class){
				props=(Properties)configCache.get(configPath);
				if(props==null){
					props=_getConfiguration(configPath);
					if(props!=null){
						configCache.put(configPath,props);
					}else{
						configCache.put(configPath,NULL_PROPERTIES);
					}
				}
			}
		}
		if(isTraceEnabled){LOGGER.exiting(CLASS_NAME,METHOD_NAME,(props!=null&&props!=NULL_PROPERTIES?props:null));}
		return (props!=null&&props!=NULL_PROPERTIES?props:null);
	}
	private static Properties _getConfiguration(String configPath) throws WCMException, IOException {
		final String METHOD_NAME="refreshConfiguration";
		boolean isTraceEnabled=LOGGER.isLoggable(Level.FINER);
		boolean isDebugEnabled=LOGGER.isLoggable(Level.FINEST);
		boolean isErrorEnabled=LOGGER.isLoggable(Level.SEVERE);
		if(isTraceEnabled){LOGGER.entering(CLASS_NAME, METHOD_NAME);}		
		Workspace workspace=WCM_API.getRepository().getSystemWorkspace();
		DocumentLibrary olddoclib=null;
		Properties props=null; 
		try{
			DocumentId configId=null;
			if(isDebugEnabled){LOGGER.finest(METHOD_NAME+" Logging into WCM system workspace...");}
			workspace.login();
			if(isDebugEnabled){LOGGER.finest(METHOD_NAME+" Backing up current document library...");}
			try{olddoclib=workspace.getCurrentDocumentLibrary();}catch(NullPointerException e){}
			if(isDebugEnabled){LOGGER.finest(METHOD_NAME+" Searching for widget configuration...");}
			if(configPath!=null){
				DocumentIdIterator itr=workspace.findByPath(configPath, Workspace.WORKFLOWSTATUS_PUBLISHED);
				if(itr.hasNext()){
					configId=itr.nextId();
				}
			}else{
				/* Grab all the document libraries to search through */
				Iterator ii=workspace.getDocumentLibraries();
				List ll=new LinkedList();
				if(ii.hasNext()){for(;ii.hasNext();){ll.add(ii.next());}}
				DocumentLibrary []doclibs=new DocumentLibrary[ll.size()];
				doclibs=(DocumentLibrary[])ll.toArray(doclibs);
			    DocumentIdIterator itr = workspace.findAllByPath(WIDGET_CONFIG_PATH,DocumentTypes.Content,Workspace.WORKFLOWSTATUS_PUBLISHED,doclibs);
			    if(itr.hasNext()){
			    	configId=itr.nextId();
			    }
			}
			if(configId!=null){
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+" Accessing widget configuration...");}
		    	Content content = (Content)workspace.getById(configId,true);
		    	if(content.hasComponent(WIDGET_CONFIG_TEXT_CMPNT_NAME)){
		    		TextComponent tc=(TextComponent)content.getComponentByReference(WIDGET_CONFIG_TEXT_CMPNT_NAME);
					if(isDebugEnabled){LOGGER.finest(METHOD_NAME+" Processing widget configuration...");}
		    		ByteArrayInputStream in=new ByteArrayInputStream(tc.getText().getBytes("UTF8"));
		    		props = new Properties();
					props.load(in);
					props.setProperty(CONFIG_LIB_NAME, content.getOwnerLibrary().getName());
		    	}
			}
		} finally {
			if(isDebugEnabled){LOGGER.finest(METHOD_NAME+" Restoring old document library...");}
			if(olddoclib!=null){workspace.setCurrentDocumentLibrary(olddoclib);}
			if(isDebugEnabled){LOGGER.finest(METHOD_NAME+" Logging out of WCM system workspace...");}
			workspace.logout();
			WCM_API.getRepository().endWorkspace();
			if(isTraceEnabled){LOGGER.exiting(CLASS_NAME,METHOD_NAME,props);}
		}
		return props;
	}	
	public static final String getContentLibrary(Properties config,String templateId){
		final String METHOD_NAME="getContentLibrary";
		boolean isTraceEnabled=LOGGER.isLoggable(Level.FINER);
		boolean isDebugEnabled=LOGGER.isLoggable(Level.FINEST);
		boolean isErrorEnabled=LOGGER.isLoggable(Level.SEVERE);
		if(isTraceEnabled){LOGGER.entering(CLASS_NAME, METHOD_NAME);}
		String lib=getTemplateParameter(config, templateId, CONTENT_LIBRARY_TEMPLATE_PARAM);
		if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Content library template param is '"+lib+"'...");}
		if(lib==null||(lib=lib.trim()).length()==0){
			lib=config.getProperty(DEFAULT_CONTENT_LIBRARY);
			if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Content library config default is '"+lib+"'...");}	
			if(lib==null||(lib=lib.trim()).length()==0){
				lib=config.getProperty(CONFIG_LIB_NAME);
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Config library name is '"+lib+"'...");}	
				if(lib==null||(lib=lib.trim()).length()==0){
					if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Using default content library...");}
					lib=AWW_CONTENT_LIBRARY;
				}
			}
		}
		if(isTraceEnabled){LOGGER.exiting(CLASS_NAME, METHOD_NAME,lib);}
		return lib;		
	}	
	
	public static final String getDesignLibrary(Properties config,String templateId){
		final String METHOD_NAME="getDesignLibrary";
		boolean isTraceEnabled=LOGGER.isLoggable(Level.FINER);
		boolean isDebugEnabled=LOGGER.isLoggable(Level.FINEST);
		boolean isErrorEnabled=LOGGER.isLoggable(Level.SEVERE);
		if(isTraceEnabled){LOGGER.entering(CLASS_NAME, METHOD_NAME);}	
		String lib=getTemplateParameter(config, templateId, DESIGN_LIBRARY_TEMPLATE_PARAM);
		if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Design library template param is '"+lib+"'...");}
		if(lib==null||(lib=lib.trim()).length()==0){
			lib=config.getProperty(DEFAULT_DESIGN_LIBRARY);
			if(isDebugEnabled){LOGGER.finest(METHOD_NAME+": Design library config default is '"+lib+"'...");}		
			if(lib==null||(lib=lib.trim()).length()==0){
				lib=AWW_DESIGN_LIBRARY;
			}
		}
		if(isTraceEnabled){LOGGER.exiting(CLASS_NAME, METHOD_NAME,lib);}
		return lib;
	}

	public static String getTemplateParameter(Properties config,String templateId,String param){
		return config.getProperty(templateId+"."+param);
	}	
}
