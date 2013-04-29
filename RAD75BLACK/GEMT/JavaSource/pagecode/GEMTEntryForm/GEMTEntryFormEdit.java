/*
 * Created on Jan 12, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pagecode.GEMTEntryForm;

import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlSelectOneRadio;
import javax.portlet.ActionRequest;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;

import pagecode.PageCodeBase;

import com.ibm.faces.component.html.HtmlCommandExButton;
import com.ibm.faces.component.html.HtmlScriptCollector;
import javax.faces.context.FacesContext;
/**
 * @author Eric Evans
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GEMTEntryFormEdit extends PageCodeBase {

	public static String PREF_MGREMPMODE = "com.manpower.portal.portlet.gemtentryformedit.mgremp";
	public static String SESS_MGREMPMODE = "com_manpower_portal_portlet_gemtentryformedit_sess_mgremp";
	
	protected HtmlScriptCollector scriptCollector1;
	protected HtmlForm form1;
	protected HtmlSelectOneRadio mgremp;
	protected HtmlCommandExButton savePreferences;

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

	protected HtmlSelectOneRadio getMgremp() {
		if (mgremp == null) {
			mgremp = (HtmlSelectOneRadio) findComponentInRoot("mgremp");
		}
		return mgremp;
	}


	protected HtmlCommandExButton getSavePreferences() {
		if (savePreferences == null) {
			savePreferences = (HtmlCommandExButton) findComponentInRoot("savePreferences");
		}
		return savePreferences;
	}

	public String doSavePreferencesAction() {
		// Type Java code that runs when the component is clicked
		ActionRequest aReq = (ActionRequest)facesContext.getExternalContext().getRequest();
		PortletPreferences prefs = aReq.getPreferences();
		try {
			prefs.setValue(PREF_MGREMPMODE, getMgremp().getValue().toString());
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
		sessionScope.put(SESS_MGREMPMODE, prefs.getValue(PREF_MGREMPMODE, "0"));
		
	
	}

}