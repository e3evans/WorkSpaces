/**
 * 
 */
package pagecode.GEMTWelcome;

import pagecode.PageCodeBase;
import com.ibm.faces.component.html.HtmlScriptCollector;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlSelectOneRadio;
import javax.portlet.ActionRequest;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;

import com.ibm.faces.component.html.HtmlCommandExButton;
import com.manpower.portal.portlet.gemt.GEMTWelcomePortlet;
import javax.faces.context.FacesContext;

/**
 * @author Eric Evans
 *
 */
public class GEMTWelcomeEdit extends PageCodeBase {

	public static String SESS_DISPLAYPAGE = "com_manpower_portal_portlet_gemtwelcome_sess_displaypage";
	protected HtmlScriptCollector scriptCollector1;
	protected HtmlForm form1;
	protected HtmlSelectOneRadio setprefs;
	protected HtmlCommandExButton button1;

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

	protected HtmlSelectOneRadio getSetprefs() {
		if (setprefs == null) {
			setprefs = (HtmlSelectOneRadio) findComponentInRoot("setprefs");
		}
		return setprefs;
	}

	protected HtmlCommandExButton getButton1() {
		if (button1 == null) {
			button1 = (HtmlCommandExButton) findComponentInRoot("button1");
		}
		return button1;
	}

	public String doButton1Action() {
		// Type Java code that runs when the component is clicked
		ActionRequest aReq = (ActionRequest)facesContext.getExternalContext().getRequest();
		PortletPreferences prefs = aReq.getPreferences();
		try {
			prefs.setValue(GEMTWelcomePortlet.PREF_DISPLAY_PAGE, getSetprefs().getValue().toString());
			prefs.store();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}

	public void onPageLoadBegin(FacesContext facescontext) {
		// Type Java code to handle page load begin event here
		RenderRequest rRequest = (RenderRequest)facescontext.getExternalContext().getRequest();
		PortletPreferences prefs = rRequest.getPreferences();
		sessionScope.put(SESS_DISPLAYPAGE, prefs.getValue(GEMTWelcomePortlet.PREF_DISPLAY_PAGE, "0"));
	
	}

}