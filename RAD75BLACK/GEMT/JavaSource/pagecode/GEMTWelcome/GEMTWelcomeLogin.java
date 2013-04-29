/**
 * 
 */
package pagecode.GEMTWelcome;

import java.util.ResourceBundle;

import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlInputSecret;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;

import pagecode.PageCodeBase;

import com.ibm.faces.component.html.HtmlGraphicImageEx;
import com.ibm.faces.component.html.HtmlScriptCollector;
import com.manpower.portal.portlet.gemt.GEMTWelcomePortlet;
import javax.faces.component.html.HtmlInputText;
import com.ibm.faces.component.html.HtmlCommandExButton;

/**
 * @author Eric Evans
 *
 */
public class GEMTWelcomeLogin extends PageCodeBase {

	public static String CONST_BUNDLE_NAME = "com.manpower.portal.portlet.gemtwelcome.nl.GEMTWelcomePortletResource";
	
	protected HtmlGraphicImageEx OrangeOnWhiteLogo;
	protected HtmlScriptCollector scriptCollector1;
	protected HtmlOutputText txt_welcome_policy;
	protected HtmlInputSecret password1;
	protected HtmlForm gemtloginform;

	protected HtmlOutputText txt_welcome;

	protected HtmlOutputText text2;

	protected HtmlInputText useremail;

	protected HtmlOutputText text4;

	protected HtmlOutputText text3;

	protected HtmlInputSecret password;

	protected HtmlCommandExButton loginuser;

	protected HtmlOutputText text1;
	protected HtmlScriptCollector getScriptCollector1() {
		if (scriptCollector1 == null) {
			scriptCollector1 = (HtmlScriptCollector) findComponentInRoot("scriptCollector1");
		}
		return scriptCollector1;
	}

	protected HtmlOutputText getTxt_welcome_policy() {
		if (txt_welcome_policy == null) {
			txt_welcome_policy = (HtmlOutputText) findComponentInRoot("txt_welcome_policy");
		}
		return txt_welcome_policy;
	}

	protected HtmlInputSecret getPassword1() {
		if (password1 == null) {
			password1 = (HtmlInputSecret) findComponentInRoot("password1");
		}
		return password1;
	}

	public String doLoginuserAction() {
		// Type Java code that runs when the component is clicked
		System.out.println("HERE's the jsf action.");
		// TODO: Return outcome that corresponds to a navigation rule
		// return "VIEW_PAGE"; // global 
		// return "VIEW_PAGE"; // global 
		// return "VIEW_PAGE"; // global 
		return "";
	}

	protected HtmlForm getGemtloginform() {
		if (gemtloginform == null) {
			gemtloginform = (HtmlForm) findComponentInRoot("gemtloginform");
		}
		return gemtloginform;
	}

	public void onPageLoadBegin(FacesContext facescontext) {

		ResourceBundle bundle = ResourceBundle.getBundle(CONST_BUNDLE_NAME);
		System.out.println("HERE!!!");
		PortletRequest request = (PortletRequest)facescontext.getExternalContext().getRequest();
		if (request.getPortletSession().getAttribute(GEMTWelcomePortlet.SESS_GEMTLOGIN_FAILED,PortletSession.APPLICATION_SCOPE)!=null){
			sessionScope.put("ERROR_LOGIN_ERROR",bundle.getString("gemt_welcome_login_error"));
		}else{
			sessionScope.remove("ERROR_LOGIN_ERROR");
		}
	}

	protected HtmlOutputText getTxt_welcome() {
		if (txt_welcome == null) {
			txt_welcome = (HtmlOutputText) findComponentInRoot("txt_welcome");
		}
		return txt_welcome;
	}

	protected HtmlOutputText getText2() {
		if (text2 == null) {
			text2 = (HtmlOutputText) findComponentInRoot("text2");
		}
		return text2;
	}

	protected HtmlInputText getUseremail() {
		if (useremail == null) {
			useremail = (HtmlInputText) findComponentInRoot("useremail");
		}
		return useremail;
	}

	protected HtmlOutputText getText4() {
		if (text4 == null) {
			text4 = (HtmlOutputText) findComponentInRoot("text4");
		}
		return text4;
	}

	protected HtmlOutputText getText3() {
		if (text3 == null) {
			text3 = (HtmlOutputText) findComponentInRoot("text3");
		}
		return text3;
	}

	protected HtmlInputSecret getPassword() {
		if (password == null) {
			password = (HtmlInputSecret) findComponentInRoot("password");
		}
		return password;
	}

	protected HtmlCommandExButton getLoginuser() {
		if (loginuser == null) {
			loginuser = (HtmlCommandExButton) findComponentInRoot("loginuser");
		}
		return loginuser;
	}

	protected HtmlOutputText getText1() {
		if (text1 == null) {
			text1 = (HtmlOutputText) findComponentInRoot("text1");
		}
		return text1;
	}

}