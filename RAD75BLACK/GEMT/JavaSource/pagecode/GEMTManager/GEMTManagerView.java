/**
 * 
 */
package pagecode.GEMTManager;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;

import pagecode.PageCodeBase;

import com.ibm.faces.component.html.HtmlCommandExButton;
import com.ibm.faces.component.html.HtmlGraphicImageEx;
import com.ibm.faces.component.html.HtmlScriptCollector;
import com.ibm.portal.puma.Group;
import com.ibm.portal.puma.User;
import com.manpower.dom.util.DOMUtil;
import com.manpower.dom.util.XMLGenerator;
import com.manpower.portal.gemt.hbn.beans.GemtDirectReportHbnBean;
import com.manpower.portal.gemt.ui.beans.GemtManagersUIBean;
import com.manpower.portal.gemt.ui.services.GEMTServiceLocator;
import com.manpower.portal.portlet.gemt.GEMTManagerServlet;
import com.manpower.portal.utility.UserManager;

/**
 * @author Eric Evans
 *
 */
public class GEMTManagerView extends PageCodeBase {

	public static String SESS_MANAGER_DISPLAY = "com_manpower_portal_portlet_gemtmanager_managerdisplay";
	public static String SESS_MANAGER_LIST	  = "com_manpower_portal_portlet_gemtmanager_managerlist";
	public static String SESS_MANAGER_SELECTED = "com_manpower_portal_portlet_gemtmanager_managerselected";
	
	protected HtmlForm gemt_direct_reports;
	protected HtmlScriptCollector scriptCollector1;
	protected HtmlGraphicImageEx imageEx2;
	protected HtmlGraphicImageEx imageEx1;
	protected HtmlCommandExButton addUsers;
	protected HtmlGraphicImageEx imageEx3;
	protected HtmlGraphicImageEx imageEx4;
	protected HtmlSelectOneMenu gemt_managers_list;
	public void onPageLoadBegin(FacesContext facescontext) {
		PortletRequest request = (PortletRequest)facescontext.getExternalContext().getRequest();
		RenderResponse response = (RenderResponse)facescontext.getExternalContext().getResponse();
		
		PortletPreferences prefs = request.getPreferences();
		String [] prefValues = null;
		prefValues = prefs.getValues(GEMTManagerConfig.CONFIG_AVAIL_REGIONS, prefValues);
		request.getPortletSession().setAttribute(GEMTManagerServlet.SESS_AVAIL_REGIONS, prefValues, PortletSession.APPLICATION_SCOPE);
	
		/*
		 * Have we selected a manager with AJAX or set the variable into the session yet?
		 * If we have set it into the Session Scope so the manager selector list is pointed
		 * at the correct manager. Also Set selected managers for the default display group.
		 * 
		 */
		String selectedManager="";
		if (request.getPortletSession().getAttribute(GEMTManagerServlet.SESS_SELECTED_MANAGER,PortletSession.APPLICATION_SCOPE)!=null){
			selectedManager=(String)request.getPortletSession().getAttribute(GEMTManagerServlet.SESS_SELECTED_MANAGER,PortletSession.APPLICATION_SCOPE);
			getSessionScope().put(GEMTManagerServlet.SESS_SELECTED_MANAGER, selectedManager);
		}
		
		List managerList = new ArrayList();
		try {
			UserManager um = new UserManager(facescontext);
			/*
			 * Maybe change this by portlet setting?  Think about it.
			 */
			Group group = um.getGroup("gemt_managers");
			List managers = group.getMembers();
			for (int i = 0;i<managers.size();i++){
				User user = (User)managers.get(i);

				/*
				 * Attributes uid and cn, localityName (Maybe)
				 */
				SelectItem selectItem = new SelectItem(user.get("uid"),user.get("cn").toString());
				/*
				 * If there is no selected manager take the first one in the list and use that as the
				 * default.
				 * 
				 */
				if (selectedManager.equals("")&& i == 0){
					selectedManager = (String)user.get("uid");
					getSessionScope().put(GEMTManagerServlet.SESS_SELECTED_MANAGER, selectedManager);
					request.getPortletSession().setAttribute(GEMTManagerServlet.SESS_SELECTED_MANAGER, selectedManager, PortletSession.APPLICATION_SCOPE);
				}
				managerList.add(selectItem);				
			}
			sessionScope.put(SESS_MANAGER_LIST, managerList);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		/*
		 * GET THE DIRECT REPORTS OF THE DEFAULT GROUP OR THE SELECTED GROUP....
		 */
		StringBuffer xmlSb = new StringBuffer();
		try {
			GemtManagersUIBean gemtUI = GEMTServiceLocator.getInstance().getManagersService().findByEmail(selectedManager);
			if (gemtUI==null){
				gemtUI = getManagerFromLdap(facescontext,selectedManager);
				GEMTServiceLocator.getInstance().getManagersService().AddManager(gemtUI);
			}
			gemtUI.setGemt_direct_reports(GEMTServiceLocator.getInstance().getDirectReportService()
						.findAllByManagerId(gemtUI.getGemt_sum_mgremail(), "gemt_sum_empname", 
						GemtDirectReportHbnBean.ORDER_ASC));
			HttpServletRequest hRequest = (HttpServletRequest)facescontext.getExternalContext().getRequest();
			gemtUI.setPortletnamespace(response.getNamespace());
			xmlSb.append("<?xml-stylesheet type=\"text/xsl\"?>");
			xmlSb.append("<!DOCTYPE stylesheet [<!ENTITY nbsp \"&#160;\"><!-- no-break space --><!ENTITY amp \"&#38;#38;\"><!-- ampersand, U+0026 ISOnum -->]>");
			xmlSb.append("<results>");
			xmlSb.append(XMLGenerator.getAsXMLMessage(gemtUI));
			xmlSb.append("</results>");
			
			System.out.println(xmlSb.toString());
			
			Document document = DocumentHelper.parseText(xmlSb.toString());
			String filePath = hRequest.getSession().getServletContext().getRealPath("/");
			sessionScope.put(SESS_MANAGER_DISPLAY,DOMUtil.getTransformedString(document,filePath+"/xsl/GEMTDirectReports.xsl"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		/*
		 * GET THE LIST OF MANAGERS TO CHOOSE FROM...
		 */

		
		
	}
	private GemtManagersUIBean getManagerFromLdap(FacesContext facescontext,String managerUid){
		GemtManagersUIBean gemtUI = null;
		try {
			UserManager um = new UserManager(facescontext);
			User user = (User) um.findUserByUid(managerUid);
			if (user!=null){
				gemtUI=new GemtManagersUIBean(user);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return gemtUI;
	}
	protected HtmlForm getGemt_direct_reports() {
		if (gemt_direct_reports == null) {
			gemt_direct_reports = (HtmlForm) findComponentInRoot("gemt_direct_reports");
		}
		return gemt_direct_reports;
	}
	protected HtmlScriptCollector getScriptCollector1() {
		if (scriptCollector1 == null) {
			scriptCollector1 = (HtmlScriptCollector) findComponentInRoot("scriptCollector1");
		}
		return scriptCollector1;
	}
	protected HtmlGraphicImageEx getImageEx2() {
		if (imageEx2 == null) {
			imageEx2 = (HtmlGraphicImageEx) findComponentInRoot("imageEx2");
		}
		return imageEx2;
	}
	protected HtmlGraphicImageEx getImageEx1() {
		if (imageEx1 == null) {
			imageEx1 = (HtmlGraphicImageEx) findComponentInRoot("imageEx1");
		}
		return imageEx1;
	}
	protected HtmlCommandExButton getAddUsers() {
		if (addUsers == null) {
			addUsers = (HtmlCommandExButton) findComponentInRoot("addUsers");
		}
		return addUsers;
	}
	protected HtmlGraphicImageEx getImageEx3() {
		if (imageEx3 == null) {
			imageEx3 = (HtmlGraphicImageEx) findComponentInRoot("imageEx3");
		}
		return imageEx3;
	}
	protected HtmlGraphicImageEx getImageEx4() {
		if (imageEx4 == null) {
			imageEx4 = (HtmlGraphicImageEx) findComponentInRoot("imageEx4");
		}
		return imageEx4;
	}
	protected HtmlSelectOneMenu getGemt_managers_list() {
		if (gemt_managers_list == null) {
			gemt_managers_list = (HtmlSelectOneMenu) findComponentInRoot("gemt_managers_list");
		}
		return gemt_managers_list;
	}
	public String doAddUsersAction() {
		String returnAction = "ADD_USER";
		getSessionScope().put(SESS_MANAGER_SELECTED, getGemt_managers_list().getValue().toString());
		return returnAction;
	}

}