package com.asponte.wcm.workflow.actions;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.ibm.workplace.wcm.api.Document;
import com.ibm.workplace.wcm.api.custom.CustomWorkflowAction;

public abstract class AbstractCustomWorkflowActionFactory implements com.ibm.workplace.wcm.api.custom.CustomWorkflowActionFactory {
	private static final String CLASS_NAME=AbstractCustomWorkflowActionFactory.class.getName();
	private static final Logger LOG=LogManager.getLogManager().getLogger(CLASS_NAME);	
	protected class CustomWorkflowActionDesc{
		String title;
		String description;
		Class<? extends CustomWorkflowAction> clazz;
		CustomWorkflowActionDesc(String title,String description,Class<? extends CustomWorkflowAction> clazz){
			this.title=title;
			this.description=description;
			this.clazz=clazz;
		}
	}
	private Map<String,CustomWorkflowActionDesc> actions=new HashMap<String,CustomWorkflowActionDesc>();
	protected AbstractCustomWorkflowActionFactory(){initActions(actions);}
	protected abstract void initActions(Map<String,CustomWorkflowActionDesc> actions);
	
	public CustomWorkflowAction getAction(String name, Document arg1) {
		final String METHOD_NAME="getAction";
		boolean isTraceEnabled=LOG.isLoggable(Level.FINER);
		boolean isDebugEnabled=LOG.isLoggable(Level.FINEST);
		boolean isErrorEnabled=LOG.isLoggable(Level.SEVERE);
		if(isTraceEnabled){LOG.entering(CLASS_NAME, METHOD_NAME);}			
		CustomWorkflowActionDesc desc=(CustomWorkflowActionDesc)this.actions.get(name);
		try {
			if(isDebugEnabled){LOG.finest(METHOD_NAME+" Creating new instance of class "+desc.clazz.getName()+" for custom workflow action "+name+"...");}
			return (CustomWorkflowAction)desc.clazz.newInstance();
		} catch (IllegalAccessException e) {
			throw new IllegalStateException("The action named "+name+" could not be created!",e);
		} catch (InstantiationException e) {
			throw new IllegalStateException("The action named "+name+" could not be created!",e);
		}finally{
			if(isTraceEnabled){LOG.exiting(CLASS_NAME,METHOD_NAME);}	
		}
	}

	public String getActionDescription(Locale l, String name) {
		CustomWorkflowActionDesc desc=(CustomWorkflowActionDesc)this.actions.get(name);
		return desc.description;
	}

	public String getActionTitle(Locale l, String name) {
		CustomWorkflowActionDesc desc=(CustomWorkflowActionDesc)this.actions.get(name);
		return desc.title;
	}
	
	public String[] getActionNames() {
		Set<String> names=this.actions.keySet();
		String []u=new String[names.size()];
		return (String[])names.toArray(u);
	}
}