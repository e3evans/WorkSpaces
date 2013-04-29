package com.manpower.portlet.wcmcrawler;

import java.io.IOException;
import java.util.HashMap;

import javax.portlet.*;



import com.ibm.workplace.wcm.api.DocumentIdIterator;
import com.ibm.workplace.wcm.api.DocumentTypes;
import com.ibm.workplace.wcm.api.Repository;
import com.ibm.workplace.wcm.api.WCM_API;
import com.ibm.workplace.wcm.api.Workspace;


public class WcmCrawlerPortlet extends com.ibm.faces.portlet.FacesPortlet {
	protected Repository repository;
	
	public void destroy() {
		super.destroy();
	}
	
	public void doConfigure(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		super.doConfigure(request, response);
	}
	
	
	public void doEdit(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		super.doEdit(request, response);
	}
	
	public void doHelp(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		super.doHelp(request, response);
	}
	
	public void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
//		HashMap params = new HashMap();
//		Workspace workspace = getWcmWorkSpace();
//		DocumentIdIterator siteIds = workspace.findByType(DocumentTypes.Site);
		super.doView(request, response);
	}
	
	public void init() throws PortletException {
		super.init();
	}
	
	public void processAction(ActionRequest request, ActionResponse response) throws PortletException {
		
		super.processAction(request, response);
	}
	
	private Workspace getWcmWorkSpace(){
		
		Workspace workspace = null;
		try {	
			
			workspace = getWcmRepository().getWorkspace("wpsadmin","wpsadmin");

//			getWcmRepository().getWorkspace(arg0)/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return workspace;
	}
	
	
	private Repository getWcmRepository(){
		repository=WCM_API.getRepository();
		return repository;
	}
	private void endRepository(){
		if (repository!=null){
			repository.endWorkspace();
			repository=null;
		}
	}
}
