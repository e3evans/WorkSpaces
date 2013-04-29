/**
 * 
 */
package pagecode.DTWidgetReportingPortlet;

import pagecode.PageCodeBase;
import com.ibm.faces.component.html.HtmlScriptCollector;
import javax.faces.component.html.HtmlOutputText;
import com.ibm.faces.component.html.HtmlRequestLink;

/**
 * @author Eric Evans
 *
 */
public class DTWidgetReportingPortletView extends PageCodeBase {

	protected HtmlScriptCollector scriptCollector1;
	protected HtmlOutputText text1;
	protected HtmlRequestLink ghostLink;
	protected HtmlOutputText text2;
	protected HtmlRequestLink ghostRegLink;

	public String doGhostLinkAction() {
		// Type Java code that runs when the component is clicked
	
		// TODO: Return outcome that corresponds to a navigation rule
		return "top5";
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

	protected HtmlRequestLink getGhostLink() {
		if (ghostLink == null) {
			ghostLink = (HtmlRequestLink) findComponentInRoot("ghostLink");
		}
		return ghostLink;
	}

	protected HtmlOutputText getText2() {
		if (text2 == null) {
			text2 = (HtmlOutputText) findComponentInRoot("text2");
		}
		return text2;
	}

	protected HtmlRequestLink getGhostRegLink() {
		if (ghostRegLink == null) {
			ghostRegLink = (HtmlRequestLink) findComponentInRoot("ghostRegLink");
		}
		return ghostRegLink;
	}

	public String doGhostRegLinkAction() {
		// Type Java code that runs when the component is clicked
	
		// TODO: Return outcome that corresponds to a navigation rule
		// return "top5";
		// return "regLink";
		return "regLink";
	}


}