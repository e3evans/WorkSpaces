/**
 * 
 */
package pagecode.AjaxMenus;

import javax.faces.component.html.HtmlForm;
import javax.faces.context.FacesContext;

import pagecode.PageCodeBase;

import com.ibm.faces.component.html.HtmlCommandExButton;
import com.ibm.faces.component.html.HtmlScriptCollector;

/**
 * @author Eric Evans
 *
 */
public class AjaxMenusViewSAVE extends PageCodeBase {

	protected HtmlForm form1;
	protected HtmlCommandExButton button1;
	protected HtmlScriptCollector scriptCollector1;

	protected HtmlForm getForm1() {
		if (form1 == null) {
			form1 = (HtmlForm) findComponentInRoot("form1");
		}
		return form1;
	}

	protected HtmlCommandExButton getButton1() {
		if (button1 == null) {
			button1 = (HtmlCommandExButton) findComponentInRoot("button1");
		}
		return button1;
	}

	public void onPageLoadBegin(FacesContext facescontext) {
		// Type Java code to handle page load begin event here
//		HttpServletRequest req = (HttpServletRequest)facescontext.getExternalContext().getRequest();
//		RenderRequest rRequest = (RenderRequest)facescontext.getExternalContext().getRequest();
//		String [] storedPrefs = rRequest.getPreferences().getValues(AjaxMenusPortlet.PREF_DISPLAY_ITEMS, null);
//	
//		if (storedPrefs!=null){
//			StringBuffer displayArea = new StringBuffer();
//			for (int i=0;i<storedPrefs.length;i++){
//				displayArea.append(AjaxMenuAssistantUIGenerator.getMenuCell(req, storedPrefs[i]));
//			}
//			getRequestScope().put("displayArea", displayArea.toString());
//		}
	
	}

	protected HtmlScriptCollector getScriptCollector1() {
		if (scriptCollector1 == null) {
			scriptCollector1 = (HtmlScriptCollector) findComponentInRoot("scriptCollector1");
		}
		return scriptCollector1;
	}


//	public void onPageLoadBegin(FacesContext facescontext) {
////		HttpServletRequest req = (HttpServletRequest)facescontext.getExternalContext().getRequest();
////		RenderRequest rRequest = (RenderRequest)facescontext.getExternalContext().getRequest();
////		String [] storedPrefs = rRequest.getPreferences().getValues(AjaxMenusPortlet.PREF_DISPLAY_ITEMS, null);
//		
////		if (storedPrefs!=null){
////			StringBuffer displayArea = new StringBuffer();
////			for (int i=0;i<storedPrefs.length;i++){
////				displayArea.append(AjaxMenuAssistantUIGenerator.getMenuCell(req, storedPrefs[i]));
////			}
////			getRequestScope().put("displayArea", displayArea.toString());
////		}
//		
//		
//	}

}