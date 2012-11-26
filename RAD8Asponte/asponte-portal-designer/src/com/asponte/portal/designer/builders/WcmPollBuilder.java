package com.asponte.portal.designer.builders;

import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.asponte.commons.Result;
import com.asponte.portal.designer.wcm.LrpConfig;
import com.asponte.portal.designer.wcm.WidgetFactory;


public class WcmPollBuilder extends WcmLrpBuilder {
	private static final String CLASS_NAME=WcmPollBuilder.class.getName();
	private static final Logger LOGGER=Logger.getLogger(CLASS_NAME);
	
	public WcmPollBuilder(){
		final String METHOD_NAME="<init>";
		boolean isTraceEnabled=LOGGER.isLoggable(Level.FINER);
		boolean isDebugEnabled=LOGGER.isLoggable(Level.FINEST);
		boolean isErrorEnabled=LOGGER.isLoggable(Level.SEVERE);
		if(isTraceEnabled){LOGGER.entering(CLASS_NAME,METHOD_NAME);}	
		if(isTraceEnabled){LOGGER.exiting(CLASS_NAME,METHOD_NAME);}			
	}
	
	@Override
	protected LrpConfig createWidget(WidgetFactory f, Properties args, List<Result> results) {
		final String METHOD_NAME="createWidget";
		boolean isTraceEnabled=LOGGER.isLoggable(Level.FINER);
		boolean isDebugEnabled=LOGGER.isLoggable(Level.FINEST);
		boolean isErrorEnabled=LOGGER.isLoggable(Level.SEVERE);
		if(isTraceEnabled){LOGGER.entering(CLASS_NAME,METHOD_NAME,new Object[]{f,args,results});}			
		LrpConfig lrpConfig=super.createWidget(f, args, results);
		if(lrpConfig!=null){
			if(!f.publish(lrpConfig.getContentIdr(), results)){
				lrpConfig=null;
			}
		}
		if(isTraceEnabled){LOGGER.exiting(CLASS_NAME,METHOD_NAME,lrpConfig);}
		return lrpConfig;
	}
	
}
