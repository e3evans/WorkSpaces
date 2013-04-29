package pagecode.DO_Logon;

import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.util.Enumeration;
import java.util.ResourceBundle;

import javax.faces.event.ActionEvent;
import javax.portlet.PortletSession;

import nl.captcha.Captcha;

import org.apache.log4j.Logger;

import pagecode.PageCodeBase;
import utils.CreateCaptchaImg;
import utils.LogonConstants;
import utils.SSSConstants;

public class SecurityPage extends PageCodeBase{

	private static Logger logger = Logger.getLogger(SecurityPage.class);
	private String captcha;
	private String goToPage;
	private String captchaKey;
	
	
	
	public SecurityPage()
	{
		initCaptcha();
	}
	
	private void initCaptcha()
	{
		logger.debug("=============================================================");
		logger.debug("isHeadless: " + GraphicsEnvironment.isHeadless());
		logger.debug("java.version: " + System.getProperty("java.version"));
		logger.debug("=============================================================");
		System.setProperty("java.awt.headless", "true");
		
		logger.debug("=============================================================");
		logger.debug("Test again after setting the property programmatically");
		logger.debug("isHeadless: " + GraphicsEnvironment.isHeadless());
		logger.debug("java.version: " + System.getProperty("java.version"));
		logger.debug("=============================================================");
		Boolean isInit = (Boolean)sessionScope.get(SSSConstants.CAPTCHA_INIT);
		
		if(isInit == null || isInit.booleanValue() == false)
		{
//			calculateCaptcha();
			sessionScope.put(SSSConstants.CAPTCHA_INIT, new Boolean(true));
		}
	}
	
//	private void calculateCaptcha()
//	{
//		Random r = new Random();
//	    String token = Long.toString(Math.abs(r.nextLong()), 36);
//	    String ch = token.substring(0,6);
//	    
//	    logger.debug("Captcha initialized to " + ch);
//	    
//	    setCaptchaKey(ch);
//	}
	
	public String getCountryBundleName()
	{
		String countryBundleName="application.messages.nl.USA_ApplicationMessages";
		
		return countryBundleName;
	}
	
	public String getGoToPage() {
		return goToPage;
	}

	public void setGoToPage(String goToPage) {
		this.goToPage = goToPage;
	}

	public String submit()
	{
		return getGoToPage();
	}
	
	public void processCaptcha(ActionEvent event)
	{
		logger.debug("SecurityPage.processCaptcha - ENTRY");
		String captcha = getCaptcha();
		logger.debug("captcha=" + captcha);
		
		Captcha captchaObj = (Captcha)getPortletSession().getAttribute(SSSConstants.CAPTCHA_KEY, PortletSession.APPLICATION_SCOPE);
		
		if(captchaObj.isCorrect(captcha)){
			logger.debug("check succeeded");
			setGoToPage(LogonConstants.WELCOME_PAGE);
		}else{
			logger.debug("check did not succeed");
			String formId = "securityForm";
			String elementId = "captchaInput";
			ResourceBundle bundle = getResourceBundle(getCountryBundleName());
			String aMessage = bundle.getString(LogonConstants.CAPTCHA_NOT_MATCH);
			applyErrorStyles(formId, elementId, aMessage);
			setGoToPage("");
		}
//		calculateCaptcha();
		
		logger.debug("SecurityPage.processCaptcha - EXIT");
	}
	
	public Image getImage()
	{
		CreateCaptchaImg createCaptcha = new CreateCaptchaImg(300, 200);
		Image image = createCaptcha.createImage();
		
		return image;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	
	public String getCaptchaKey() {
		logger.debug("SecurityPage.getCaptchaKey - ENTRY");
		
		Enumeration enumeration = getPortletSession().getAttributeNames();
		logger.debug("Print attribute names");
		while (enumeration.hasMoreElements())
		{
			String attrName = (String)enumeration.nextElement();
			logger.debug("Portlet session attribute: " + attrName);
		}
		
		String str = (String)getPortletSession().getPortletContext().getAttribute(SSSConstants.CAPTCHA_KEY);
		logger.debug("str = " + str);
		logger.debug("captchaKey = " + captchaKey);
		logger.debug("SecurityPage.getCaptchaKey - EXIT");
		return captchaKey;
	}
	public void setCaptchaKey(String captchaKey) {
		
		sessionScope.put(SSSConstants.CAPTCHA_KEY, captchaKey);
		this.captchaKey = captchaKey;
	}
}
