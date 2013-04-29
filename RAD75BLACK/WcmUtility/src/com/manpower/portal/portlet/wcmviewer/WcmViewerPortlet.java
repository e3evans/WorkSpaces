package com.manpower.portal.portlet.wcmviewer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import pagecode.WcmViewer.WcmViewerConfig;

import com.ibm.workplace.wcm.api.Content;
import com.ibm.workplace.wcm.api.DocumentId;
import com.ibm.workplace.wcm.api.DocumentIdIterator;
import com.ibm.workplace.wcm.api.RenderingContext;
import com.ibm.workplace.wcm.api.Repository;
import com.ibm.workplace.wcm.api.SiteArea;
import com.ibm.workplace.wcm.api.WCM_API;
import com.ibm.workplace.wcm.api.Workspace;
import com.manpower.portal.portlet.wcmutility.beans.WcmSiteAreaBean;


public class WcmViewerPortlet extends com.ibm.faces.portlet.FacesPortlet {
	
	protected Repository repository;
	
	public static String PREF_DISPLAY_CONTENT = "com_manpower_portal_portlet_wcmviewer_pref_displaycontent";
	public static String SESS_DISPLAY_STORED_CONTENT= "com_manpower_portal_portlet_wcmviewer_pref_storedcontent";
	
	public void destroy() {
		super.destroy();
	}
	
	public void doConfigure(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		super.doConfigure(request, response);
	}
	
	
	public void doEdit(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		HashMap params = new HashMap();
		String storedContent = request.getPreferences().getValue(PREF_DISPLAY_CONTENT, "");
		PortletPreferences prefs = getPortletPreferences(request);
		if (!storedContent.equals("")){
			try{
				String appPath = "http://"+request.getServerName()+":"+request.getServerPort()+"/wps/wcm";
				Workspace workspace = getWcmWorkSpace(prefs);
				if (!workspace.getUserProfile().getUsername().equals("anonymous portal user")){
					RenderingContext rc = workspace.createRenderingContext(request,response,params,appPath,"/connect");
					DocumentId contentId = workspace.createDocumentId(storedContent);
					DocumentIdIterator dii = null;
					Content content = (Content) workspace.getById(contentId);
					dii = content.getParents();
					SiteArea siteArea = (SiteArea)workspace.getById((DocumentId)dii.next());
					rc.setRenderedContent(content,siteArea);
					if (rc.getContent() != null) {
						StringBuffer contentDisplay = new StringBuffer();
						contentDisplay.append(workspace.render(rc));
						request.getPortletSession().setAttribute(SESS_DISPLAY_STORED_CONTENT, contentDisplay.toString());
					}
				}
			}catch (Exception e){
				e.printStackTrace();
			}finally{
				endRepository();
			}
		}
		
		super.doEdit(request, response);
	}
	
	public void doHelp(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		super.doHelp(request, response);
	}
	
	private String getWorkspacePath(Workspace workspace,Content content, String locale){
		String path="";
		
			String baseContentName = getBaseContentName(content.getName())+"_"+locale;
			DocumentIdIterator dii = content.getParents();
			boolean findparents = true;
			List myPath = new ArrayList();
			if (dii.hasNext()){
				try{
					Object object = workspace.getById((DocumentId)dii.next());
					WcmSiteAreaBean wsab = new WcmSiteAreaBean(object);
					while(findparents){
						
						if (wsab.getClassname().equals(WcmSiteAreaBean.SITE_AREA_CLASSNAME)){
							myPath.add(0,wsab.getSiteName());
							SiteArea bean = (SiteArea)object;
							DocumentId id = bean.getParent();
							object =workspace.getById(id);
							wsab = new WcmSiteAreaBean(object);
						}else if (wsab.getClassname().equals(WcmSiteAreaBean.SITE_CLASS_NAME)){
							myPath.add(0,wsab.getSiteName());
							findparents = false;
						}else if (wsab.getClassname().equals(WcmSiteAreaBean.SITE_CONTENT_NAME)){
							findparents = false;
						}
					}
					
				}catch (Exception e){
					e.printStackTrace();
				}
				StringBuffer sb = new StringBuffer();
				for (int i=0;i<myPath.size();i++){
					sb.append(myPath.get(i)+"/");
				}
				sb.append(baseContentName);
				path=sb.toString();
			}
		
		return path;
	}
	
	public void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		HashMap params = new HashMap();
		String storedContent = request.getPreferences().getValue(PREF_DISPLAY_CONTENT, "");
		String locale = request.getLocale().toString();
		PortletPreferences prefs = getPortletPreferences(request);
		if (!storedContent.equals("")){
			try{
				String appPath = "http://"+request.getServerName()+":"+request.getServerPort()+"/wps/wcm";
				Workspace workspace = getWcmWorkSpace(prefs);
				if (!workspace.getUserProfile().getUsername().equals("anonymous portal user")){
					
					
	//				Workspace workspace = getWcmWorkSpace(request);
					RenderingContext rc = workspace.createRenderingContext(request,response,params,appPath,"/connect");
					DocumentId contentId = workspace.createDocumentId(storedContent);
					Content content = (Content) workspace.getById(contentId);
					String path = getWorkspacePath(workspace,content,locale);
				
					DocumentIdIterator dii = (DocumentIdIterator)content.getParents();
					SiteArea siteArea = (SiteArea)workspace.getById((DocumentId)dii.next());
					

					System.out.println("PAHT:  "+path);
					rc.setRenderedContent("/"+path);
					System.out.println("CONTENT!!!"+rc.getContent());
//					
					if (rc.getContent() != null) {
						StringBuffer contentDisplay = new StringBuffer();
						contentDisplay.append(workspace.render(rc));
						request.getPortletSession().setAttribute(SESS_DISPLAY_STORED_CONTENT, contentDisplay.toString());
					}else{
						rc.setRenderedContent(content,siteArea);
						StringBuffer contentDisplay = new StringBuffer();
						contentDisplay.append(workspace.render(rc));
						request.getPortletSession().setAttribute(SESS_DISPLAY_STORED_CONTENT, contentDisplay.toString());
					}
				}else{
					request.getPortletSession().setAttribute(SESS_DISPLAY_STORED_CONTENT, "Please Configure");
				}
			}catch (Exception e){
//				System.out.println("Security Violation...WCM Super User Not configured!");
				e.printStackTrace();
			}finally{
				endRepository();
			}
		}else{
			request.getPortletSession().setAttribute(SESS_DISPLAY_STORED_CONTENT,"No Content Selected");
		}
		
		super.doView(request, response);
	}
	
	public void init() throws PortletException {
		super.init();
	}
	
	public void processAction(ActionRequest request, ActionResponse response) throws PortletException {
		
		super.processAction(request, response);
	}
	
	private String getBaseContentName(String storedContent){
		/*
		 * Get the base file name.  We have to chop the language off the end of the name.
		 * Split the name of the Content Based on "_".
		 * Our Content nameing conventions is based on XXXX_nl_be, XXXX_de, etc.
		 */
		String [] storedContentArray = storedContent.split("_");
		/*
		 * Check the second to last entry to see if it matches any of our languages...
		 */
		HashMap langMap = getLanguageMap();
		boolean twoParter = false;
		if (storedContentArray.length> 2){
			if (langMap.get(storedContentArray[storedContentArray.length-2])!=null)twoParter=true;
		}
		int length = storedContentArray.length;
		if (twoParter){
			length=length-2;
		}else{
			length=length-1;
		}
		StringBuffer sb = new StringBuffer();
		for (int i=0;i<length;i++){
			sb.append(storedContentArray[i]);
			if (i!=length-1)sb.append("_");
		}
		/*
		 * Is it a two part language like nl_be or be_fr?
		 */
		
		return sb.toString();
	}
	private HashMap getLanguageMap(){
		HashMap langMap = new HashMap();
		String langString = ResourceBundle.getBundle("com.manpower.portal.portlet.wcmviewer.nl.WcmViewerPortletAvailLanguages").getString("avail_langs");
		String [] splitLangs = langString.split(",");
		
		for(int i = 0;i<splitLangs.length;i++){
			langMap.put(splitLangs[i], splitLangs[i]);
		}
		return langMap;
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
	
	private Workspace getWcmWorkSpace(PortletPreferences prefs){
		Workspace workspace = null;
		try {	
			
			workspace = getWcmRepository().getWorkspace(prefs.getValue(WcmViewerConfig.CONFIG_PREF_WCMUSERNAME, ""),
					prefs.getValue(WcmViewerConfig.CONFIG_PREF_WCMUSERPASSWORD, ""));

//			getWcmRepository().getWorkspace(arg0)/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return workspace;
	}
	
	private PortletPreferences getPortletPreferences(RenderRequest request){
		return request.getPreferences();
	}

	
	
}
