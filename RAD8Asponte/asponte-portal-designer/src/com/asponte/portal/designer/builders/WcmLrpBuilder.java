package com.asponte.portal.designer.builders;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;

import com.asponte.commons.Result;
import com.asponte.commons.portal.Utils;
import com.asponte.portal.designer.DesignerException;
import com.asponte.portal.designer.PageElementProvider;
import com.asponte.portal.designer.PortletResult;
import com.asponte.portal.designer.wcm.Constants;
import com.asponte.portal.designer.wcm.LrpConfig;
import com.asponte.portal.designer.wcm.Results;
import com.asponte.portal.designer.wcm.WidgetFactory;

public class WcmLrpBuilder implements PageElementProvider, IExecutableExtension {
	private static final String CLASS_NAME=WcmLrpBuilder.class.getName();
	private static final Logger LOGGER=Logger.getLogger(CLASS_NAME);

	private static final String WCM_LRP_286_UNIQUENAME = "ibm.portal.Web.Content.Viewer.Jsr286";

	private String templateId;
	private boolean hasStyles=false;
	private boolean requiresSubtitle=false;
	
	public WcmLrpBuilder() {
		final String METHOD_NAME="<init>";
		boolean isTraceEnabled=LOGGER.isLoggable(Level.FINER);
		boolean isDebugEnabled=LOGGER.isLoggable(Level.FINEST);
		boolean isErrorEnabled=LOGGER.isLoggable(Level.SEVERE);
		if(isTraceEnabled){LOGGER.entering(CLASS_NAME,METHOD_NAME);}	
		if(isTraceEnabled){LOGGER.exiting(CLASS_NAME,METHOD_NAME);}	
	}
	
	public void setInitializationData(IConfigurationElement config, String propertyName, Object ignore) throws CoreException {
		final String METHOD_NAME="setInitializationData";
		boolean isTraceEnabled=LOGGER.isLoggable(Level.FINER);
		boolean isDebugEnabled=LOGGER.isLoggable(Level.FINEST);
		boolean isErrorEnabled=LOGGER.isLoggable(Level.SEVERE);
		if(isTraceEnabled){LOGGER.entering(CLASS_NAME,METHOD_NAME);}	
		this.templateId=config.getAttribute("wcmTemplateId");
		String subTitle=config.getAttribute("requiresSubtitle");
		if(subTitle!=null){this.requiresSubtitle=Boolean.valueOf(subTitle).booleanValue();}
		if(config.getChildren("style").length>0){this.hasStyles=true;}
		if(isDebugEnabled){LOGGER.finest(METHOD_NAME+" Builder initialized with template ID "+this.templateId+", requiresSubtitle="+this.requiresSubtitle+", hasStyles="+this.hasStyles+"...");}
		if(isTraceEnabled){LOGGER.exiting(CLASS_NAME,METHOD_NAME);}			
	}

	public void buildPortletConfiguration(ActionRequest request,ActionResponse response,Map<String, Object> adminPrefs, Map<String, Object> sharedPrefs) throws DesignerException {
		final String METHOD_NAME="buildPortletConfiguration";
		boolean isTraceEnabled=LOGGER.isLoggable(Level.FINER);
		boolean isDebugEnabled=LOGGER.isLoggable(Level.FINEST);
		boolean isErrorEnabled=LOGGER.isLoggable(Level.SEVERE);
		if(isTraceEnabled){LOGGER.entering(CLASS_NAME,METHOD_NAME,new Object[]{request.getParameterMap()});}
		Properties args=new Properties();
		List<Result> results=new ArrayList<Result>();
		processParameters(request,args,results);
		if(results.size()==0){
			WidgetFactory f=WidgetFactory.createWidgetFactory(request,response);
			LrpConfig lrpConfig=createWidget(f,args,results);
			if(lrpConfig!=null){
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+" Widget creation successful! LRP config is "+lrpConfig);}
				sharedPrefs.put(Constants.WCM_CONTENT_TYPE_PREF, lrpConfig.getContentType());
				sharedPrefs.put(Constants.WCM_CONTENT_CONTEXT_TYPE_PREF, lrpConfig.getContentContextType());
				sharedPrefs.put(Constants.WCM_CONTENT_CONTEXT_IDR_PREF, lrpConfig.getContentIdr());
				String componentIdr=lrpConfig.getComponentIdr();
				String broadcastsTo=lrpConfig.getBroadcastsTo();
				String listensTo=lrpConfig.getListensTo();
				String categories=lrpConfig.getCategories();
				String siteAreas=lrpConfig.getSiteAreas();
				String title=lrpConfig.getPortletTitle();
				if(componentIdr!=null){sharedPrefs.put(Constants.WCM_COMPONENT_IDR_PREF, componentIdr);}
				if(broadcastsTo!=null){sharedPrefs.put(Constants.WCM_BROADCASTS_TO_PREF, broadcastsTo);}
				if(listensTo!=null){sharedPrefs.put(Constants.WCM_LISTENS_TO_PREF, listensTo);}
				if(categories!=null){sharedPrefs.put(Constants.WCM_CATEGORY_OVERRIDE_PREF,categories);}
				if(siteAreas!=null){sharedPrefs.put(Constants.WCM_SITEAREA_OVERRIDE_PREF,siteAreas);}
				if(title!=null){
					sharedPrefs.put(Constants.WCM_PORTLET_TITLE_PREF,title);
					sharedPrefs.put(Constants.WCM_PORTLET_TITLE_TYPE_PREF,Constants.WCM_PORTLET_TITLE_TYPE_GENERAL);
				}
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+" Configured shared prefs: "+sharedPrefs);}
			}else{
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+" Widget creation failed!");}
				DesignerException e=new DesignerException();
				for(Result r:results){e.addResult(r);}
				throw e;
			}			
		}else{
			DesignerException e=new DesignerException();
			for(Result r:results){e.addResult(r);}
			throw e;
		}
		if(isTraceEnabled){LOGGER.exiting(CLASS_NAME,METHOD_NAME);}
	}
	
	public String getPortletId() {
		return WCM_LRP_286_UNIQUENAME;
	}
	
	public String getTemplateId(){
		return this.templateId;
	}
	
	public boolean hasStyles(){
		return this.hasStyles;
	}
	
	public boolean requiresSubtitle(){
		return this.requiresSubtitle;
	}
	
	protected void processParameters(ActionRequest request, Properties args, List<Result> results) {
		final String METHOD_NAME="processParameters";
		boolean isTraceEnabled=LOGGER.isLoggable(Level.FINER);
		boolean isDebugEnabled=LOGGER.isLoggable(Level.FINEST);
		boolean isErrorEnabled=LOGGER.isLoggable(Level.SEVERE);
		if(isTraceEnabled){LOGGER.entering(CLASS_NAME,METHOD_NAME,new Object[]{request.getParameterMap(),args,results});}	
		String title=Utils.param(request,this.templateId+"_title");
		if(isDebugEnabled){LOGGER.finest(METHOD_NAME+" Checking for widget title...");}	
		if(!Utils.empty(title)){
			if(isDebugEnabled){LOGGER.finest(METHOD_NAME+" Found widget title "+title+"...");}
			args.setProperty("title",title);
		}else{
			results.add(new PortletResult(Result.WARNING,Results.MISSING_TITLE));
		}
		if(requiresSubtitle()){
			String subTitle=Utils.param(request,this.templateId+"_subTitle");			
			if(isDebugEnabled){LOGGER.finest(METHOD_NAME+" Checking for widget subTitle...");}	
			if(!Utils.empty(subTitle)){
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+" Found widget subTitle "+subTitle+"...");}
				args.setProperty("subTitle",subTitle);
			}else{
				results.add(new PortletResult(Result.WARNING,Results.MISSING_SUBTITLE));
			}
		}
		if(hasStyles()){
			String style=Utils.param(request,getTemplateId()+"_style");
			if(isDebugEnabled){LOGGER.finest(METHOD_NAME+" Checking for template style...");}	
			if(!Utils.empty(style)){
				if(isDebugEnabled){LOGGER.finest(METHOD_NAME+" Found template style "+style+"...");}
				args.setProperty("style",style);
			}else{
				results.add(new PortletResult(Result.WARNING,Results.MISSING_STYLE));
			}
		}
		if(isTraceEnabled){LOGGER.exiting(CLASS_NAME,METHOD_NAME);}
	}

	protected LrpConfig createWidget(WidgetFactory f, Properties args, List<Result> results) {
		final String METHOD_NAME="createWidget";
		boolean isTraceEnabled=LOGGER.isLoggable(Level.FINER);
		boolean isDebugEnabled=LOGGER.isLoggable(Level.FINEST);
		boolean isErrorEnabled=LOGGER.isLoggable(Level.SEVERE);
		if(isTraceEnabled){LOGGER.entering(CLASS_NAME,METHOD_NAME,new Object[]{f,args,results});}			
		if(isDebugEnabled){LOGGER.finest(METHOD_NAME+" Invoking factory to create WCM assets...");}
		String templateId=getTemplateId();
		if(hasStyles()){
			String style=args.getProperty("style");
			templateId=templateId+"_"+style;
		}
		LrpConfig lrpConfig=f.createWidget(templateId, args, results);
		if(isTraceEnabled){LOGGER.exiting(CLASS_NAME,METHOD_NAME,lrpConfig);}
		return lrpConfig;
	}	
}
