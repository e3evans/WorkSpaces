/**
 * 
 */
package pagecode.WcmViewer;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.portlet.ActionResponse;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.WindowState;
import javax.servlet.http.HttpServletRequest;

import org.dom4j.DocumentHelper;

import pagecode.PageCodeBase;

import com.ibm.faces.component.html.HtmlCommandExButton;
import com.ibm.faces.component.html.HtmlScriptCollector;
import com.ibm.workplace.wcm.api.Content;
import com.ibm.workplace.wcm.api.DocumentId;
import com.ibm.workplace.wcm.api.DocumentIdIterator;
import com.ibm.workplace.wcm.api.DocumentTypes;
import com.ibm.workplace.wcm.api.SiteArea;
import com.ibm.workplace.wcm.api.Workspace;
import com.manpower.dom.util.DOMUtil;
import com.manpower.dom.util.XMLGenerator;
import com.manpower.portal.portlet.wcmutility.beans.WcmSiteAreaBean;
import com.manpower.portal.portlet.wcmviewer.WcmViewerPortlet;

/**
 * @author Eric Evans
 *
 */
public class WcmViewerEdit extends PageCodeBase {

	public static String SESS_SITEAREAS = "com_manpower_portal_portlet_wcmviewer_siteareas";
	public static String SESS_STORED_CONTENT = "com_manpower_portal_portlet_wcmviewer_storedcontent";
	public static String SESS_EXPAND_LIST = "com_manpower_portal_portlet_wcmviewer_expandlist";
	public static String SESS_STORED_CONTENT_PREVIEW = "com_manpower_portal_portlet_wcmviewer_storedcontentpreview";
	protected HtmlScriptCollector scriptCollector1;
	protected HtmlOutputText SiteName;
	protected HtmlForm formsavePrefs;
	protected HtmlInputText dispalyContent;
	protected HtmlCommandExButton savePreferences;
	protected HtmlOutputText text1;
	protected HtmlCommandExButton returnToView;
	public void onPageLoadBegin(FacesContext facescontext) {

		/*
		 * Get Servlet Request to transform XML Documents....
		 * Get Workspace to find first level of Sites....
		 */
		HttpServletRequest request = (HttpServletRequest)facescontext.getExternalContext().getRequest();
		request.getSession().setAttribute(SESS_PORTLET_PREFERENCES, getPortletPreferences());
		Workspace workspace = getWcmWorkSpace();
		if (request.getSession().getAttribute(WcmViewerPortlet.SESS_DISPLAY_STORED_CONTENT)!=null){
			getSessionScope().put(SESS_STORED_CONTENT_PREVIEW, (String)request.getSession().getAttribute(WcmViewerPortlet.SESS_DISPLAY_STORED_CONTENT));
		}
		
		
		DocumentIdIterator siteIds = workspace.findByType(DocumentTypes.Site);
		StringBuffer sb = new StringBuffer();
		
		getSessionScope().put(SESS_STORED_CONTENT, getStoredContentPreference());
		
		sb.append("<?xml-stylesheet type=\"text/xsl\"?>");
		sb.append("<!DOCTYPE stylesheet [<!ENTITY nbsp \"&#160;\"><!-- no-break space --><!ENTITY amp \"&#38;#38;\" > <!-- ampersand, U+0026 ISOnum -->]>");
		sb.append("<results>");
		WcmSiteAreaBean wsab = new WcmSiteAreaBean();
//		System.out.println("SITE COUNT:  "+siteIds.getCount());
		if (siteIds.getCount()==0){
			sb.append("<NORESULT>");
			sb.append("Please have your administrator configure a valid WCMSuper User for your country.");
			sb.append("</NORESULT>");
		}
		
		while (siteIds.hasNext()) {
			try {
				//Create SiteArea path
				DocumentId siteID = (DocumentId)siteIds.next();
				wsab = new WcmSiteAreaBean(workspace.getById(siteID));
				sb.append(XMLGenerator.getAsXMLMessage(wsab));
			} catch (Exception e) {
				e.printStackTrace();
			} 			
			
		}
		sb.append("</results>");
		try{
			org.dom4j.Document document = DocumentHelper.parseText(sb.toString());
			String filePath = request.getSession().getServletContext().getRealPath("/");
			getSessionScope().put(SESS_SITEAREAS,DOMUtil.getTransformedString(document,filePath+"/xsl/wcmselector.xsl"));
		}catch(Exception e){
			e.printStackTrace();
		}

		if (getSessionScope().get(SESS_STORED_CONTENT)!=null){
			List expandList = new ArrayList();
			/*
			 * Get the First piece of stored content which will ALWAYS be content
			 * We are not storing any other kind of object in this parameter.
			 */
			DocumentIdIterator siteTest = workspace.findByName(DocumentTypes.Content, 
					(String)getSessionScope().get(SESS_STORED_CONTENT));
			if (siteTest.hasNext()){
				DocumentId selectedContentId = (DocumentId)siteTest.next();
				try {
					Object temp = workspace.getById(selectedContentId);
					Content selectedContent = (Content)temp;
					expandList.add(0,temp);
					DocumentIdIterator parents = selectedContent.getParents();
					/*
					 * Each child object should only have one preceding parent.
					 * So we're able to check the class and apend it to the List.
					 * When we get to the top item it will have no parents and the loop will end.
					 * 
					 */
					if (parents.hasNext()){
						DocumentId parentId = (DocumentId)parents.next();
						temp = workspace.getById(parentId);
						WcmSiteAreaBean siteAreaParentBean = new WcmSiteAreaBean(temp);
						boolean stillHasParents = true;
						while(stillHasParents){
							if (siteAreaParentBean.getClassname().equals(WcmSiteAreaBean.SITE_AREA_CLASSNAME)){
								SiteArea child = (SiteArea)temp;
								expandList.add(0,temp);
								parentId = child.getParent();
								temp=workspace.getById(parentId);
								siteAreaParentBean = new WcmSiteAreaBean(temp);
							}else if(siteAreaParentBean.getClassname().equals(WcmSiteAreaBean.SITE_CLASS_NAME)){
								expandList.add(0,temp);
								stillHasParents = false;
							}
						}

					}
					if (expandList.size()>0){
						getSessionScope().put(SESS_EXPAND_LIST, generateExpandListJavaScript(expandList));
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			
		}
		
		
		repository.endWorkspace();

	}

	private String generateExpandListJavaScript(List list){
		StringBuffer sb = new StringBuffer();
		WcmSiteAreaBean wsab;
		
		
		for (int i = 0;i < list.size(); i++	){
			
			wsab = new WcmSiteAreaBean(list.get(i));
			
			if (i==list.size()-1){
				sb.append(wsab.getSiteName());
			}else{
				sb.append(wsab.getSiteName()+"/");
				
			}
		}
	
		return sb.toString();
	}

	protected HtmlScriptCollector getScriptCollector1() {
		if (scriptCollector1 == null) {
			scriptCollector1 = (HtmlScriptCollector) findComponentInRoot("scriptCollector1");
		}
		return scriptCollector1;
	}



	protected HtmlForm getFormsavePrefs() {
		if (formsavePrefs == null) {
			formsavePrefs = (HtmlForm) findComponentInRoot("formsavePrefs");
		}
		return formsavePrefs;
	}



	private String getStoredContentPreference(){
		PortletRequest request = (PortletRequest)getFacesContext().getExternalContext().getRequest();
		PortletPreferences prefs  = request.getPreferences();
		String result="";
		result = prefs.getValue(WcmViewerPortlet.PREF_DISPLAY_CONTENT,"");
		return result;
	}


	public String doSavePreferencesAction() {
		// Type Java code that runs when the component is clicked
		 PortletRequest request = (PortletRequest)getFacesContext().getExternalContext().getRequest();
		 PortletPreferences prefs  = request.getPreferences();
		 try{
			 
			 prefs.setValue(WcmViewerPortlet.PREF_DISPLAY_CONTENT, getDispalyContent().getValue().toString());
			 prefs.store();
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		// TODO: Return outcome that corresponds to a navigation rule
		return "";
	}

	protected HtmlInputText getDispalyContent() {
		if (dispalyContent == null) {
			dispalyContent = (HtmlInputText) findComponentInRoot("dispalyContent");
		}
		return dispalyContent;
	}

	protected HtmlCommandExButton getSavePreferences() {
		if (savePreferences == null) {
			savePreferences = (HtmlCommandExButton) findComponentInRoot("savePreferences");
		}
		return savePreferences;
	}

	protected HtmlOutputText getText1() {
		if (text1 == null) {
			text1 = (HtmlOutputText) findComponentInRoot("text1");
		}
		return text1;
	}

	protected HtmlCommandExButton getReturnToView() {
		if (returnToView == null) {
			returnToView = (HtmlCommandExButton) findComponentInRoot("returnToView");
		}
		return returnToView;
	}

	public String doReturnToViewAction() {
		// Type Java code that runs when the component is clicked
		ActionResponse response = (ActionResponse)getFacesContext().getExternalContext().getResponse();
		try{
			response.setWindowState(WindowState.NORMAL);
			response.setPortletMode(PortletMode.VIEW);
		}catch(Exception e){
			e.printStackTrace();
		}
		// TODO: Return outcome that corresponds to a navigation rule
		return "";
	}

}