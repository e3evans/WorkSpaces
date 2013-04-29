package pagecode;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlForm;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.servlet.http.HttpServletRequest;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import utils.Constants;
//import utils.DynamicCacheConstants;

import com.ibm.portal.Locator;
import com.ibm.portal.navigation.NavigationModel;
import com.ibm.portal.navigation.NavigationNode;
import com.ibm.wps.model.ModelUtil;
import com.ibm.wps.model.NavigationModelUtil;
import com.ibm.wps.model.factory.IsolationMode;
import com.manpower.portal.mpnet.dynamiccache.util.DynamicCacheUtils;
//import com.manpower.portal.mpnet.util.MPNETAppConstants;
import com.manpower.sss.util.PropertiesUtil;



/**
 * Provides a common base class for all generated code behind files.
 */
public abstract class PageCodeBase
{
	private static Logger logger = Logger.getLogger(PageCodeBase.class);
	
	protected FacesContext facesContext;
	protected Map sessionScope;
	protected Map requestScope;
	
	public PageCodeBase()
	{
		facesContext = FacesContext.getCurrentInstance();
		
		requestScope =
			(Map) facesContext
				.getApplication()
				.createValueBinding("#{requestScope}")
				.getValue(facesContext);
		
		sessionScope =
			(Map) facesContext
				.getApplication()
				.createValueBinding("#{sessionScope}")
				.getValue(facesContext);
	}
	
	protected long getCandidateId()
	{
		// return temporarily hardcoded candidateId
		return 28317;
	}
	
//	protected long getCandidateId(){
//		
//		Long candidateId=(Long) getPortletSession().getAttribute(Constants.CANDIDATE_ID, PortletSession.APPLICATION_SCOPE);	
//		
//		if(candidateId==null)
//		{
//			
//			PortletSession session=((PortletRequest)facesContext
//					.getExternalContext().getRequest()).getPortletSession();
//			
//			
//				candidateId=(Long)DynamicCacheUtils.getDataFromDynamicCache(DynamicCacheConstants.CANDIDATE_ID,session);
//				if(candidateId!=null)
//				{				
//					getPortletSession().setAttribute(Constants.CANDIDATE_ID, candidateId, PortletSession.APPLICATION_SCOPE);
//				}
//				else
//				{
//					candidateId=new Long(Constants.CANDIATE_TEST_ID);	
//				}
//		}
//
//				
//		return candidateId.longValue();
//	}
	
	public PortletSession getPortletSession(){
		PortletRequest request = (PortletRequest)facesContext.getExternalContext().getRequest();
		PortletSession portletSession = request.getPortletSession();
		return portletSession;
	}
	
	public void applyErrorStyles(String formId, String childId, String aMessage)
	{
		logger.debug("--->>> applyErrorStyles formId=" + formId);
		logger.debug("--->>> childId=" + childId);
		logger.debug("--->>> aMessage=" + aMessage);
		FacesContext context = FacesContext.getCurrentInstance();
		List children = context.getViewRoot().getChildren();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, aMessage, aMessage);
		
		UIComponent child = null;
		HtmlForm aForm = null;
		for(int i = 0; i < children.size(); i++)
		{
			child = (UIComponent)children.get(0);
			String id = child.getId();
			if(formId.equals(id))
			{
				aForm = (HtmlForm)child;
				break;
			}
		}
		
		UIComponent component = null;
		logger.debug("the found form: " + aForm);
		if(aForm != null)
			component = aForm.findComponent(childId);
		
		logger.debug("the found form = " + aForm + ", component = " + component);
		
		if(aForm != null &&   (component) != null)
		{
			logger.debug("Validation message is set.  ID = " + component.getClientId(context) + ", msg = " + message.getDetail());
			
			context.addMessage(component.getClientId(context), message);
			
			context.renderResponse();
		}
		else
		{
			logger.debug("Validation message is NOT set.");
			context.addMessage(null, message);
		}
	}
	
	public static ResourceBundle getResourceBundle(String bundleName){
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		Locale loc = context != null ? context.getViewRoot().getLocale() : getLocale();
		
		ResourceBundle rb = ResourceBundle.getBundle(bundleName, loc);
		
		return rb;
		
	}
	
	public static Locale getLocale(){
		
		FacesContext context = FacesContext.getCurrentInstance();

        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

	    Locale loc = request.getLocale();	        
	    
	    return loc;
	}
	
	public List getConfigPropertyValueAsList(String property){
		
		List result = PropertiesUtil.getPropertyValuesAsList(property);
		if(result != null)
		{
			logger.debug("Found " + result.size() + " items");
		}else {
			logger.debug("List is NULL");
		}
		
		return result;
	}
	
	public String getStringValue(String property)
	{
		String value = PropertiesUtil.getStringValue(property);
		if(value == null || value.equals(""))
		{
			logger.debug("Property " + property + " is not found or is not set");
		}
		return value;
	}
	
	protected boolean isStringInputValid(String value){
		
		return isStringInputValid(value, null);
		
	}
	
	protected boolean isStringInputValid(String value, String notDefault)
	{
		if(value == null)
			return false;
		
		synchronized(value)
		{
			if("".equals(value))
				return false;
			
			if(value.trim().length() == 0)
				return  false;
			
			if(value.equals(notDefault))
				return false;
			
			return true;
		}
	}
	
	public static String setRedirectURL(HttpServletRequest request , String uniqueName)
	{	
		
		try {
			ModelUtil util = ModelUtil.from(request);		
			util.setIsolationMode(IsolationMode.LIVE);
		    NavigationModel model = util.getNavigationModel();
		    Locator locator = model.getLocator();
		    NavigationNode node = (NavigationNode)locator.findByUniqueName(uniqueName);
		    NavigationModelUtil navUtil = NavigationModelUtil.from(request);
		   return (String) navUtil.createSelectionChangeURL(node);
		}catch(Exception e ) {
			if(logger.isEnabledFor(Level.ERROR)) {
				logger.error("Error for site  redirecting to page " + uniqueName + " Error = " + e);
			}
		}
		return null;
	}
	
	public void redirectToPortlet(String pageConstant)
	{
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest httpServletRequest = (HttpServletRequest)externalContext.getRequest();
		String redirectURL = setRedirectURL(httpServletRequest, pageConstant);
		logger.debug("redirectURL = " + redirectURL);
		try {
			externalContext.redirect(redirectURL);
		} catch (IOException e) {
			logger.error("Exception while redirecting", e);
		}
	}
}

