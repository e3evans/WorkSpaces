/**
 * 
 */
package pagecode.WcmViewer;

import pagecode.PageCodeBase;
import com.ibm.faces.component.html.HtmlScriptCollector;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.WindowState;
import javax.faces.component.html.HtmlInputSecret;
import com.ibm.faces.component.html.HtmlCommandExButton;

/**
 * @author Eric Evans
 *
 */
public class WcmViewerConfig extends PageCodeBase {
	
	public static String CONFIG_PREF_WCMUSERNAME="com.manpower.portal.portlet.wcmviewer.superusername";
	public static String CONFIG_PREF_WCMUSERPASSWORD="com.manpower.portal.portlet.wcmviewer.superuserpw";
	public static String SESS_PREF_WCMUSERNAME="com_manpower_portal_portlet_wcmviewer_superusername";
	public static String SESS_PREF_WCMUSERPASSWORD="com_manpower_portal_portlet_wcmviewer_superuserpw";
	protected HtmlScriptCollector scriptCollector1;
	protected HtmlForm form1;
	protected HtmlInputText wcmuser;
	protected HtmlInputSecret wcmuserpassword;
	protected HtmlCommandExButton saveConfiguration;
	protected HtmlCommandExButton cancelConfig;
	protected HtmlScriptCollector getScriptCollector1() {
		if (scriptCollector1 == null) {
			scriptCollector1 = (HtmlScriptCollector) findComponentInRoot("scriptCollector1");
		}
		return scriptCollector1;
	}
	protected HtmlForm getForm1() {
		if (form1 == null) {
			form1 = (HtmlForm) findComponentInRoot("form1");
		}
		return form1;
	}
	public void onPageLoadBegin(FacesContext facescontext) {
		PortletRequest request = (PortletRequest)facescontext.getExternalContext().getRequest();
		PortletPreferences prefs = request.getPreferences();
		getSessionScope().put(SESS_PREF_WCMUSERNAME, prefs.getValue(CONFIG_PREF_WCMUSERNAME,"Please Configure."));
		getSessionScope().put(SESS_PREF_WCMUSERPASSWORD, prefs.getValue(CONFIG_PREF_WCMUSERPASSWORD, ""));
	
	}
	protected HtmlInputText getWcmuser() {
		if (wcmuser == null) {
			wcmuser = (HtmlInputText) findComponentInRoot("wcmuser");
		}
		return wcmuser;
	}
	protected HtmlInputSecret getWcmuserpassword() {
		if (wcmuserpassword == null) {
			wcmuserpassword = (HtmlInputSecret) findComponentInRoot("wcmuserpassword");
		}
		return wcmuserpassword;
	}
	public String doSaveConfigurationAction() {
		// Type Java code that runs when the component is clicked
		ActionRequest request = (ActionRequest)getFacesContext().getExternalContext().getRequest();
		ActionResponse response = (ActionResponse)getFacesContext().getExternalContext().getResponse();
		
		PortletPreferences prefs = request.getPreferences();
		
		try{
			prefs.setValue(CONFIG_PREF_WCMUSERNAME, getWcmuser().getValue().toString());
			prefs.setValue(CONFIG_PREF_WCMUSERPASSWORD, getWcmuserpassword().getValue().toString());
			prefs.store();
			response.setWindowState(WindowState.NORMAL);
			response.setPortletMode(PortletMode.VIEW);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		// TODO: Return outcome that corresponds to a navigation rule
		return "";
	}
	protected HtmlCommandExButton getSaveConfiguration() {
		if (saveConfiguration == null) {
			saveConfiguration = (HtmlCommandExButton) findComponentInRoot("saveConfiguration");
		}
		return saveConfiguration;
	}
	protected HtmlCommandExButton getCancelConfig() {
		if (cancelConfig == null) {
			cancelConfig = (HtmlCommandExButton) findComponentInRoot("cancelConfig");
		}
		return cancelConfig;
	}
	public String doCancelConfigAction() {
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