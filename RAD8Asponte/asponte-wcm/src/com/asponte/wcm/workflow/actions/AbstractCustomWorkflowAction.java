package com.asponte.wcm.workflow.actions;

import java.util.Date;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.ibm.workplace.wcm.api.Document;
import com.ibm.workplace.wcm.api.WebContentCustomWorkflowService;
import com.ibm.workplace.wcm.api.custom.CustomWorkflowAction;

public abstract class AbstractCustomWorkflowAction implements CustomWorkflowAction {
	static WebContentCustomWorkflowService customWorkflowService;
	static{
		 try
		 {
		    // Construct and initial Context
		    InitialContext ctx = new InitialContext();
		    // Retrieve WebContentCustomWorkflowService using JNDI name
		    customWorkflowService = (WebContentCustomWorkflowService) ctx.lookup("portal:service/wcm/WebContentCustomWorkflowService");
		 }
		 catch (NamingException ignore)
		 {
			 // If this happens, something is really wrong
			 ignore.printStackTrace();
		 }
	}
	AbstractCustomWorkflowAction(){}
	public Date getExecuteDate(Document arg0) {
		return DATE_EXECUTE_NOW;
	}
}
