/**
 * 
 */
package pagecode.GEMTWelcome;

import pagecode.PageCodeBase;
import com.ibm.faces.component.html.HtmlGraphicImageEx;
import com.ibm.faces.component.html.HtmlScriptCollector;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlOutputText;
import com.ibm.faces.component.html.HtmlCommandExButton;

/**
 * @author Eric Evans
 *
 */
public class GEMTWelcomeThanks extends PageCodeBase {

	protected HtmlGraphicImageEx OrangeOnWhiteLogo;
	protected HtmlScriptCollector scriptCollector1;
	protected HtmlForm form1;
	protected HtmlOutputText txt_welcome;
	protected HtmlCommandExButton takemetologin;
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
	protected HtmlOutputText getTxt_welcome() {
		if (txt_welcome == null) {
			txt_welcome = (HtmlOutputText) findComponentInRoot("txt_welcome");
		}
		return txt_welcome;
	}
	protected HtmlCommandExButton getTakemetologin() {
		if (takemetologin == null) {
			takemetologin = (HtmlCommandExButton) findComponentInRoot("takemetologin");
		}
		return takemetologin;
	}

}