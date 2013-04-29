/**
 * 
 */
package pagecode.DTWidgetReportingPortlet;

import javax.faces.context.FacesContext;

import pagecode.PageCodeBase;
import com.ibm.faces.component.html.HtmlScriptCollector;
import javax.faces.component.html.HtmlOutputText;
import com.ibm.faces.component.html.HtmlRequestLink;

/**
 * @author Eric Evans
 *
 */
public class DTWidgetReportingTop5 extends PageCodeBase {

	protected HtmlScriptCollector scriptCollector1;
	protected HtmlOutputText text1;
	protected HtmlRequestLink link1;
	public void onPageLoadBegin(FacesContext facescontext) {
		// Type adJava code to handle page load begin event here
//		Object [] tObjList = FeedServiceLocator.getInstance().getCandidateReportService().getTopFiveSites();	
//		getSessionScope().put("tObjList", tObjList);
	
	}

	protected HtmlScriptCollector getScriptCollector1() {
		if (scriptCollector1 == null) {
			scriptCollector1 = (HtmlScriptCollector) findComponentInRoot("scriptCollector1");
		}
		return scriptCollector1;
	}

	protected HtmlOutputText getText1() {
		if (text1 == null) {
			text1 = (HtmlOutputText) findComponentInRoot("text1");
		}
		return text1;
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