package com.asponte.portal.designer.builders;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
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
import com.asponte.portal.designer.Results;

public class FeedBuilder implements PageElementProvider, IExecutableExtension {
	private static final String CLASS_NAME=FeedBuilder.class.getName();
	private static final Logger LOGGER=Logger.getLogger(CLASS_NAME);

	private static final String FEEDSPACE_UNIQUENAME = "wps.p.Feedspace";
	private static final DateFormat DATE_FORMATTER = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
	
	static{
		DATE_FORMATTER.setTimeZone(TimeZone.getTimeZone("GMT"));
	}

	public FeedBuilder() {
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
		String title=Utils.param(request,"feed.element.title");
		String url=Utils.param(request,"feed.element.url");
		List<Result> results=new ArrayList<Result>();
		if(isDebugEnabled){LOGGER.finest(METHOD_NAME+" Checking for feed title...");}
		if(Utils.empty(title)){
			results.add(new PortletResult(Result.WARNING,Results.MISSING_FEED_TITLE));
		}
		if(isDebugEnabled){LOGGER.finest(METHOD_NAME+" Checking for feed url...");}
		if(Utils.empty(url)){
			results.add(new PortletResult(Result.WARNING,Results.MISSING_FEED_URL));
		}
		if(results.size()==0){
			if(isDebugEnabled){LOGGER.finest(METHOD_NAME+" Creating new feed with title "+title+" and url "+url+"...");}
			sharedPrefs.put("presentationStyle", "2");
			sharedPrefs.put("selection","/0");
			sharedPrefs.put("showChannelbar","false");
			sharedPrefs.put("channel.itemsPerPage", "5");
			sharedPrefs.put("subscriptions",buildOpml(title,url));
		}else{
			DesignerException e=new DesignerException();
			for(Result r:results){
				e.addResult(r);
			}
			throw e;
		}
		if(isTraceEnabled){LOGGER.exiting(CLASS_NAME,METHOD_NAME);}
	}

	private String buildOpml(String title, String url) {
		final String METHOD_NAME="buildPortletConfiguration";
		boolean isTraceEnabled=LOGGER.isLoggable(Level.FINER);
		boolean isDebugEnabled=LOGGER.isLoggable(Level.FINEST);
		boolean isErrorEnabled=LOGGER.isLoggable(Level.SEVERE);
		if(isTraceEnabled){LOGGER.entering(CLASS_NAME,METHOD_NAME);}		
		StringBuffer sb=new StringBuffer();
		String now=DATE_FORMATTER.format(new Date());
		sb.append("<?xml version='1.0' encoding='utf-8'?><opml version='2.0'><head><title><![CDATA[My Subscription List]]></title>");
		sb.append("<dateModified><![CDATA["+now+"]]></dateModified></head>");
		sb.append("<body><outline xml:id='Subscription_1' text='"+title+"' type='rss' isComment='false' isBreakpoint='false' created='"+now+"' category='' title='"+title+"' xmlUrl='"+url+"' /></body>");
		sb.append("</opml>");
		if(isTraceEnabled){LOGGER.exiting(CLASS_NAME,METHOD_NAME,sb.toString());}
		return sb.toString();
	}

	public String getPortletId() {
		return FEEDSPACE_UNIQUENAME;
	}
}
