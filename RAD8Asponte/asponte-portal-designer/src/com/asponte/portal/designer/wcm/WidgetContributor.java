package com.asponte.portal.designer.wcm;

import java.util.List;
import java.util.Properties;

import com.asponte.portal.designer.DesignerException;
import com.ibm.workplace.wcm.api.Document;
import com.ibm.workplace.wcm.api.DocumentLibrary;
import com.ibm.workplace.wcm.api.Workspace;

public interface WidgetContributor {
	void contentCreated(WidgetFactory f,
						Properties config, 
					  	String templateId,
					  	Workspace workspace,
					  	DocumentLibrary awwDesignLib, 
					  	DocumentLibrary awwContentLib,
					  	Properties args,
					  	List<Document> docs) throws DesignerException;
	void contentSaved(WidgetFactory f,
					  Properties config, 
					  String templateId,
					  Workspace workspace,
					  DocumentLibrary awwDesignLib, 
					  DocumentLibrary awwContentLib,
					  Properties args,
					  List<Document> docs) throws DesignerException;	
}
