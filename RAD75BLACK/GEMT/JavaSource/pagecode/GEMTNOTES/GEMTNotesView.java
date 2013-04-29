/**
 * 
 */
package pagecode.GEMTNOTES;

import java.util.List;

import javax.faces.component.UIParameter;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;

import pagecode.PageCodeBase;

import com.ibm.faces.component.html.HtmlRequestLink;
import com.ibm.faces.component.html.HtmlScriptCollector;
import com.manpower.portal.gemt.ui.services.GEMTServiceLocator;

/**
 * @author rrajaram
 * 
 */
public class GEMTNotesView extends PageCodeBase {

	public static String SESS_NOTESLIST = "gemtnoteslist";

	public static String SESS_DISPLAYREPORT = "gemtnotesdisplay";

	public static String SESS_CONTEXTPATH = "gemtnotescontextpath";

	protected HtmlRequestLink linkViewNotes;

	protected HtmlOutputText text1;

	protected HtmlOutputText text2;

	protected HtmlOutputText text3;

	protected HtmlDataTable table1;

	protected UIParameter param1;

	protected HtmlScriptCollector scriptCollector1;

	public String doLinkViewNotesAction() {
		if (HtmlRequestLink.getParameter("paramId") == null)
			System.out.println("Null");
		else if ((HtmlRequestLink.getParameter("paramId").toString())
				.equals(null))
			System.out.println("Null");
		else
			sessionScope.put(SESS_DISPLAYREPORT, HtmlRequestLink.getParameter(
					"paramId").toString());
		return "VIEW_PAGE";
	}

	protected HtmlRequestLink getLinkViewNotes() {
		if (linkViewNotes == null) {
			linkViewNotes = (HtmlRequestLink) findComponentInRoot("linkViewNotes");
		}
		return linkViewNotes;
	}

	protected UIParameter getParam1() {
		if (param1 == null) {
			param1 = (UIParameter) findComponentInRoot("param1");
		}
		return param1;
	}

	protected HtmlScriptCollector getScriptCollector1() {
		if (scriptCollector1 == null) {
			scriptCollector1 = (HtmlScriptCollector) findComponentInRoot("scriptCollector1");
		}
		return scriptCollector1;
	}

	protected HtmlDataTable getTable1() {
		if (table1 == null) {
			table1 = (HtmlDataTable) findComponentInRoot("table1");
		}
		return table1;
	}

	protected HtmlOutputText getText1() {
		if (text1 == null) {
			text1 = (HtmlOutputText) findComponentInRoot("text1");
		}
		return text1;
	}

	protected HtmlOutputText getText2() {
		if (text2 == null) {
			text2 = (HtmlOutputText) findComponentInRoot("text2");
		}
		return text2;
	}

	protected HtmlOutputText getText3() {
		if (text3 == null) {
			text3 = (HtmlOutputText) findComponentInRoot("text3");
		}
		return text3;
	}

	public void onPageLoadBegin(FacesContext facescontext) {
		// Type Java code to handle page load begin event here
		List list = GEMTServiceLocator.getInstance().getNotesService()
				.findAll();
		sessionScope.put(SESS_NOTESLIST, list);

	}

}