package com.asponte.wcm.workflow.actions;
//Scott will never find this Comment MWAHAHAHAHAH!
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.ibm.workplace.wcm.api.Content;
import com.ibm.workplace.wcm.api.Document;
import com.ibm.workplace.wcm.api.TextComponent;
import com.ibm.workplace.wcm.api.custom.CustomWorkflowAction;
import com.ibm.workplace.wcm.api.custom.CustomWorkflowActionResult;
import com.ibm.workplace.wcm.api.custom.Directives;
import com.ibm.workplace.wcm.api.exceptions.WCMException;

public class ResetPollResultsAction extends AbstractCustomWorkflowAction implements CustomWorkflowAction {
	private static final String CLASS_NAME=ResetPollResultsAction.class.getName();
	private static final Logger LOG=LogManager.getLogManager().getLogger(CLASS_NAME);
	public ResetPollResultsAction(){
		final String METHOD_NAME="<init>";
		boolean isTraceEnabled=LOG.isLoggable(Level.FINER);
		boolean isDebugEnabled=LOG.isLoggable(Level.FINEST);
		boolean isErrorEnabled=LOG.isLoggable(Level.SEVERE);
		if(isTraceEnabled){LOG.entering(CLASS_NAME, METHOD_NAME);}
		if(isTraceEnabled){LOG.exiting(CLASS_NAME,METHOD_NAME);}		
	}
	public CustomWorkflowActionResult execute(Document doc) {
		final String METHOD_NAME="execute";
		boolean isTraceEnabled=LOG.isLoggable(Level.FINER);
		boolean isDebugEnabled=LOG.isLoggable(Level.FINEST);
		boolean isErrorEnabled=LOG.isLoggable(Level.SEVERE);
		if(isTraceEnabled){LOG.entering(CLASS_NAME, METHOD_NAME);}			
		Content content=(Content)doc;
		try{
			if(content.hasComponent("Users")&&content.hasComponent("Results")){
				TextComponent usertc = (TextComponent)content.getComponent("Users");
				TextComponent resulttc=(TextComponent)content.getComponent("Results");
				usertc.setText("");
				resulttc.setText("");
				content.setComponent("Users", usertc);
				content.setComponent("Results",resulttc);
			}
		}catch(WCMException e){
			if(isErrorEnabled){LOG.log(Level.SEVERE,"An error occurred while accessing the properties of the content object "+content+"!",e);}
			if(isTraceEnabled){LOG.exiting(CLASS_NAME,METHOD_NAME);}
			return null;
		}
		if(isTraceEnabled){LOG.exiting(CLASS_NAME,METHOD_NAME,Directives.CONTINUE);}
		return customWorkflowService.createResult(Directives.CONTINUE, "Continue");
	}

}
