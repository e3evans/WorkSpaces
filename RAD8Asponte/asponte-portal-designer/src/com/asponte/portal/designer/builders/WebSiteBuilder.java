package com.asponte.portal.designer.builders;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;

import com.asponte.commons.Result;
import com.asponte.portal.designer.DesignerException;
import com.asponte.portal.designer.PageElementProvider;
import com.asponte.portal.designer.PortletResult;
import com.asponte.portal.designer.Results;

public class WebSiteBuilder implements PageElementProvider, IExecutableExtension {
	private static final String CLASS_NAME=WebSiteBuilder.class.getName();
	private static final Logger LOGGER=Logger.getLogger(CLASS_NAME);

	private static final String SIMPLE_IFRAME_PORTLET_UNIQUENAME = "asponte.portal.SimpleIFrame";

	public WebSiteBuilder() {
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
		if(isTraceEnabled){LOGGER.exiting(CLASS_NAME,METHOD_NAME);}			
	}	

	public void buildPortletConfiguration(ActionRequest request,ActionResponse response,Map<String, Object> adminPrefs, Map<String, Object> sharedPrefs) throws DesignerException {
		final String METHOD_NAME="buildPortletConfiguration";
		boolean isTraceEnabled=LOGGER.isLoggable(Level.FINER);
		boolean isDebugEnabled=LOGGER.isLoggable(Level.FINEST);
		boolean isErrorEnabled=LOGGER.isLoggable(Level.SEVERE);
		if(isTraceEnabled){LOGGER.entering(CLASS_NAME,METHOD_NAME);}
		String url=request.getParameter("website.element.url");
		String width=request.getParameter("website.element.width");
		String height=request.getParameter("website.element.height");
		String scrolling=request.getParameter("website.element.scrolling");
		if(url!=null&&(url=url.trim()).length()>0){
			sharedPrefs.put("url", url);
			if(width!=null){sharedPrefs.put("width", width);}
			if(height!=null){sharedPrefs.put("height", height);}
			if(scrolling!=null){sharedPrefs.put("scrolling", scrolling);}
		}else{
			throw new DesignerException(new PortletResult(Result.WARNING,Results.WEBSITE_MISSING_PAGE_URL));
		}
		if(isTraceEnabled){LOGGER.exiting(CLASS_NAME,METHOD_NAME);}
	}

	public String getPortletId() {
		return SIMPLE_IFRAME_PORTLET_UNIQUENAME;
	}
}
