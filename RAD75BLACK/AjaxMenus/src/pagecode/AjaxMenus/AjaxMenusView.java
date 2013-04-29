/**
 * 
 */
package pagecode.AjaxMenus;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;

import pagecode.PageCodeBase;

import com.ibm.portal.portlet.ajaxmenus.AjaxMenusPortlet;
import com.manpower.portal.theme.ajax.AjaxMenuAssistantUIGenerator;
import javax.faces.model.SelectItem;
import com.ibm.faces.component.html.HtmlScriptCollector;
import javax.faces.component.html.HtmlForm;

/**
 * @author Eric Evans
 *
 */
public class AjaxMenusView extends PageCodeBase {

	protected HtmlScriptCollector scriptCollector1;
	protected HtmlForm form1;

	public void onPageLoadBegin(FacesContext facescontext) {
		HttpServletRequest req = (HttpServletRequest)facescontext.getExternalContext().getRequest();
//		System.out.println("STATE ACCESSOR:  "+req.getAttribute("com.ibm.portal.state.accessors.StateAccessor"));
		RenderRequest rRequest = (RenderRequest)facescontext.getExternalContext().getRequest();
		String [] storedPrefs = rRequest.getPreferences().getValues(AjaxMenusPortlet.PREF_DISPLAY_ITEMS, null);
		String hideword = rRequest.getPreferences().getValue(AjaxMenusPortlet.PREF_HIDE_WORD, null);
		String showdropdown = rRequest.getPreferences().getValue(AjaxMenusPortlet.PREF_INCLUDE_DROPDOWN, "false");
		getRequestScope().put("showdropdown", showdropdown);
		if (storedPrefs!=null){
			StringBuffer displayArea = new StringBuffer();
			for (int i=0;i<storedPrefs.length;i++){
				displayArea.append(AjaxMenuAssistantUIGenerator.getMenuCell(req, storedPrefs[i],hideword));
			}
			getRequestScope().put("displayArea", displayArea.toString());
		}
		
		ResourceBundle rb = ResourceBundle.getBundle("com.ibm.portal.portlet.ajaxmenus.nl.CountryWebsites");
		int count = 0;
		for (Enumeration e = rb.getKeys();e.hasMoreElements();){
			e.nextElement();
			count++;
		}
		count++;
		List websites = new ArrayList();
		for (int i=1;i<count;i++){
			SelectItem si = new SelectItem();
			String [] siteInfo = rb.getString("site"+Integer.toString(i)).split(",");
			si.setLabel(siteInfo[0]+"-->"+siteInfo[1]);
			si.setValue(siteInfo[2]);
			websites.add(si);
		}
		getRequestScope().put("websites",websites);
	}

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

}