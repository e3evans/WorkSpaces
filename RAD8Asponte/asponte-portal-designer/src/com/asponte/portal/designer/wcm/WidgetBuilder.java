package com.asponte.portal.designer.wcm;

import java.util.List;
import java.util.Properties;

import com.asponte.commons.Result;
import com.ibm.workplace.wcm.api.DocumentLibrary;
import com.ibm.workplace.wcm.api.Workspace;

public interface WidgetBuilder {
	LrpConfig createWidget(WidgetFactory f,
						   Properties config, 
					  	   String templateId,
					  	   Workspace workspace,
					  	   DocumentLibrary awwDesignLib, 
					  	   DocumentLibrary awwContentLib,
					  	   Properties args, 
					  	   List<Result> faults);
}
