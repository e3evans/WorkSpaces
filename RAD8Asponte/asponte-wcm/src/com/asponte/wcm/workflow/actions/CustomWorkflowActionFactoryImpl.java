package com.asponte.wcm.workflow.actions;

import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.ibm.workplace.wcm.api.custom.CustomWorkflowActionFactory;

public class CustomWorkflowActionFactoryImpl extends AbstractCustomWorkflowActionFactory implements CustomWorkflowActionFactory{
	private static final String CLASS_NAME=CustomWorkflowActionFactoryImpl.class.getName();
	private static final Logger LOG=LogManager.getLogManager().getLogger(CLASS_NAME);
	@Override
	protected void initActions(Map<String, CustomWorkflowActionDesc> actions) {
		final String METHOD_NAME="initActions";
		boolean isTraceEnabled=LOG.isLoggable(Level.FINER);
		boolean isDebugEnabled=LOG.isLoggable(Level.FINEST);
		boolean isErrorEnabled=LOG.isLoggable(Level.SEVERE);
		if(isTraceEnabled){LOG.entering(CLASS_NAME, METHOD_NAME);}		
		actions.put("Result Poll Results", new CustomWorkflowActionDesc("Reset Poll Results","Reset the poll result fields",ResetPollResultsAction.class));
		if(isTraceEnabled){LOG.exiting(CLASS_NAME,METHOD_NAME);}			
	}

	public String getName() {
		return "CustomWorkflowActionFactoryImpl";
	}

	public String getTitle(Locale arg0) {
		return "Asponte Custom Workflow Action Factory";
	}

}
