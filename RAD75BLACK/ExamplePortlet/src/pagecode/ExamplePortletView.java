/**
 * 
 */
package pagecode;

import com.ibm.faces.component.html.HtmlScriptCollector;
import javax.faces.component.html.HtmlForm;
import com.ibm.faces.component.html.HtmlCommandExButton;

/**
 * @author Eric Evans
 *
 */
public class ExamplePortletView extends PageCodeBase {

	protected HtmlScriptCollector scriptCollector1;
	protected HtmlForm form1;
	protected HtmlCommandExButton testaction;

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

	protected HtmlCommandExButton getTestaction() {
		if (testaction == null) {
			testaction = (HtmlCommandExButton) findComponentInRoot("testaction");
		}
		return testaction;
	}

	public String doTestactionAction() {
		
		System.out.println("TESTING!!!");
		return "";
	}

}