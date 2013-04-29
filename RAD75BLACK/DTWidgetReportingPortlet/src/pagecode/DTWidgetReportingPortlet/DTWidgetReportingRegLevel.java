/**
 * 
 */
package pagecode.DTWidgetReportingPortlet;

import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import pagecode.PageCodeBase;

import com.ibm.faces.component.html.HtmlCommandExButton;
import com.ibm.faces.component.html.HtmlScriptCollector;
import com.ibm.faces.component.html.HtmlRequestLink;

/**
 * @author Eric Evans
 *
 */
public class DTWidgetReportingRegLevel extends PageCodeBase {

	protected HtmlScriptCollector scriptCollector1;
	protected HtmlOutputText text1;
	protected HtmlForm form1;
	protected HtmlInputText sitename;
	protected HtmlInputText siteid;
	protected HtmlCommandExButton ghostRegLevelButton;
	protected HtmlRequestLink link1;
	protected HtmlScriptCollector getScriptCollector1() {
		if (scriptCollector1 == null) {
			scriptCollector1 = (HtmlScriptCollector) findComponentInRoot("scriptCollector1");
		}
		return scriptCollector1;
	}

	public void onPageLoadBegin(FacesContext facescontext) {
		// Type Java code to handle page load begin event here
//		HttpServletRequest hsr = (HttpServletRequest)facescontext.getExternalContext().getRequest();
//		hsr.setAttribute("test", "TEST1");
	
	}

	protected HtmlOutputText getText1() {
		if (text1 == null) {
			text1 = (HtmlOutputText) findComponentInRoot("text1");
		}
		return text1;
	}

	protected HtmlForm getForm1() {
		if (form1 == null) {
			form1 = (HtmlForm) findComponentInRoot("form1");
		}
		return form1;
	}

	protected HtmlInputText getSitename() {
		if (sitename == null) {
			sitename = (HtmlInputText) findComponentInRoot("sitename");
		}
		return sitename;
	}

	protected HtmlInputText getSiteid() {
		if (siteid == null) {
			siteid = (HtmlInputText) findComponentInRoot("siteid");
		}
		return siteid;
	}

	


	protected HtmlCommandExButton getGhostRegLevelButton() {
		if (ghostRegLevelButton == null) {
			ghostRegLevelButton = (HtmlCommandExButton) findComponentInRoot("ghostRegLevelButton");
		}
		return ghostRegLevelButton;
	}

	public String doButton1Action() {
		// Type Java code that runs when the component is clicked
		
		HttpServletRequest sReq = (HttpServletRequest) getFacesContext().getExternalContext().getRequest();
		sReq.setAttribute("siteId", getSiteid().getValue().toString());
		sReq.setAttribute("siteName", getSitename().getValue().toString());
		
		// TODO: Return outcome that corresponds to a navigation rule
		return "";
	}

	protected HtmlRequestLink getLink1() {
		if (link1 == null) {
			link1 = (HtmlRequestLink) findComponentInRoot("link1");
		}
		return link1;
	}

	public String doLink1Action() {
		// Type Java code that runs when the component is clicked
	
		// TODO: Return outcome that corresponds to a navigation rule
		return "goback";
	}

}