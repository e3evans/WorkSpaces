/**
 * 
 */
package pagecode.WcmUtilityPortlet;

import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlInputText;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pagecode.PageCodeBase;

import com.ibm.faces.component.html.HtmlCommandExButton;
import com.ibm.faces.component.html.HtmlScriptCollector;
import com.ibm.portal.Locator;
import com.ibm.portal.navigation.NavigationModel;
import com.ibm.portal.navigation.NavigationNode;
import com.ibm.wps.model.ModelUtil;
import com.ibm.wps.model.NavigationModelUtil;
import com.ibm.wps.model.factory.IsolationMode;

/**
 * @author Eric Evans
 *
 */
public class WcmUtilityView extends PageCodeBase {

	protected HtmlScriptCollector scriptCollector1;
	protected HtmlForm wcmutility;
	protected HtmlCommandExButton redirect;
	protected HtmlInputText redirectUrl;
	@SuppressWarnings("unchecked")
	public String doRedirectAction() {
		String redirectPage=getRedirectUrl().getValue().toString();
	
		try{
			HttpServletRequest hReq = (HttpServletRequest)getFacesContext().getExternalContext().getRequest();
			HttpServletResponse hRes = (HttpServletResponse)getFacesContext().getExternalContext().getResponse();
			ModelUtil util = ModelUtil.from(hReq,hRes);
			
	        util.setIsolationMode(IsolationMode.LIVE);
	        NavigationModel model = util.getNavigationModel();
	        NavigationModelUtil navUtil = NavigationModelUtil.from(hReq);
	        Locator locator = model.getLocator();
	        NavigationNode node = (NavigationNode)locator.findByUniqueName(redirectPage);
	        String redirectUrl=navUtil.createSelectionChangeURL(node);
	        getFacesContext().getExternalContext().redirect(redirectUrl);
		}catch (Exception e){
			System.out.println("UNABLE TO REDIRECT TO SPECIFIED UNIQUE PAGE ID--->"+redirectPage);
		}

		return "";
	}

	protected HtmlScriptCollector getScriptCollector1() {
		if (scriptCollector1 == null) {
			scriptCollector1 = (HtmlScriptCollector) findComponentInRoot("scriptCollector1");
		}
		return scriptCollector1;
	}

	protected HtmlForm getWcmutility() {
		if (wcmutility == null) {
			wcmutility = (HtmlForm) findComponentInRoot("wcmutility");
		}
		return wcmutility;
	}

	protected HtmlCommandExButton getRedirect() {
		if (redirect == null) {
			redirect = (HtmlCommandExButton) findComponentInRoot("redirect");
		}
		return redirect;
	}

	protected HtmlInputText getRedirectUrl() {
		if (redirectUrl == null) {
			redirectUrl = (HtmlInputText) findComponentInRoot("redirectUrl");
		}
		return redirectUrl;
	}

}