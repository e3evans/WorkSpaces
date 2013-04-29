/**
 * 
 */
package pagecode.WcmViewer;

import javax.faces.context.FacesContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import pagecode.PageCodeBase;

import com.ibm.faces.component.html.HtmlScriptCollector;
import com.ibm.portal.Locator;
import com.ibm.portal.navigation.NavigationModel;
import com.ibm.portal.navigation.NavigationNode;
import com.ibm.wps.model.ModelUtil;
import com.ibm.wps.model.NavigationModelUtil;
import com.ibm.wps.model.factory.IsolationMode;
import com.manpower.portal.portlet.wcmviewer.WcmViewerPortlet;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlInputText;

/**
 * @author Eric Evans
 *
 */
public class WcmViewerView extends PageCodeBase {

	protected HtmlScriptCollector scriptCollector1;
	protected HtmlForm wcmutility;
	protected HtmlInputText redirectUrl;
	protected HtmlScriptCollector getScriptCollector1() {
		if (scriptCollector1 == null) {
			scriptCollector1 = (HtmlScriptCollector) findComponentInRoot("scriptCollector1");
		}
		return scriptCollector1;
	}
	public void onPageLoadBegin(FacesContext facescontext) {
		// Type Java code to handle page load begin event here
//		HttpServletRequest request = (HttpServletRequest)facescontext.getExternalContext().getRequest();
//		if (request.getSession().getAttribute(WcmViewerPortlet.SESS_DISPLAY_STORED_CONTENT)!=null){
//			getRequestScope().put(SESS_STORED_CONTENT_PREVIEW, (String)request.getSession().getAttribute(WcmViewerPortlet.SESS_DISPLAY_STORED_CONTENT));
//		}
	
	}
	protected HtmlForm getWcmutility() {
		if (wcmutility == null) {
			wcmutility = (HtmlForm) findComponentInRoot("wcmutility");
		}
		return wcmutility;
	}
	
	public String doRedirectAction() {
		String redirectPage=getRedirectUrl().getValue().toString();

		try{
			ServletRequest hReq = (HttpServletRequest)getFacesContext().getExternalContext().getRequest();
			ModelUtil util = ModelUtil.from(hReq);
	        util.setIsolationMode(IsolationMode.LIVE);
	        NavigationModel model = util.getNavigationModel();
	        NavigationModelUtil navUtil = NavigationModelUtil.from(hReq);
	        Locator locator = model.getLocator();
	        NavigationNode node = (NavigationNode)locator.findByUniqueName(redirectPage);
	        String redirectUrl=navUtil.createSelectionChangeURL(node);
//	        System.out.println("REDIRECT URL:  "+redirectUrl);
	        getFacesContext().getExternalContext().redirect(redirectUrl);
		}catch (Exception e){
			System.out.println("UNABLE TO REDIRECT TO SPECIFIED UNIQUE PAGE ID--->"+redirectPage);
//			e.printStackTrace();
		}

		return "";
	}
	protected HtmlInputText getRedirectUrl() {
		if (redirectUrl == null) {
			redirectUrl = (HtmlInputText) findComponentInRoot("redirectUrl");
		}
		return redirectUrl;
	}

}