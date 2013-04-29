package pagecode.DO_Logon;

import java.util.ResourceBundle;

import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

import pagecode.PageCodeBase;
import utils.LogonConstants;
import utils.SSSConstants;

import com.manpower.directoffice.api.DOCandidateHandler;
import com.manpower.directoffice.api.DOSecurityUtility;
import com.manpower.directoffice.pojos.CandidateEntity;
import com.manpower.directoffice.pojos.CandidateGeneralInformation;
import com.manpower.directoffice.xml.exception.CanNotAccessDirectOfficeException;

public class Logon extends PageCodeBase{

	private static Logger logger = Logger.getLogger(Logon.class);
	
	private String password;
	private String goToPage;
	
	public String getCountryBundleName()
	{
		String countryBundleName="application.messages.nl.USA_ApplicationMessages";
		
		return countryBundleName;
	}
	
	public String forgotPassword()
	{
		return LogonConstants.FORGOT_PWD;
	}
	
	public String submit()
	{
		return getGoToPage();
	}
	
	public void forgotPassword(ActionEvent event)
	{
		long candidateId = getCandidateId();
		logger.debug("User with id = " + candidateId + " has forgotten the password");
		
		boolean passwordEmailed = DOSecurityUtility.userForgotPassword(new Long(candidateId).intValue());
		
		String message = "";
		ResourceBundle bundle = getResourceBundle(getCountryBundleName());
		if(passwordEmailed)
		{
			message = bundle.getString(LogonConstants.PASSWORD_EMAILED);
		}else{
			message = bundle.getString(LogonConstants.PASSWORD_NOT_EMAILED);
		}
		
		sessionScope.put(LogonConstants.PASSWORD_SESSION_KEY, message);
	}
	
	public void logon(ActionEvent event)
	{
		logger.debug("logon - ENTRY");
		long candidateId = getCandidateId();
		CandidateEntity entity = null;
		
		logger.debug("candidateId = " + candidateId);
//		DOSecurityUtility dOSecurityUtility = new DOSecurityUtility();
		logger.debug("password = " + getPassword());
		try {
			logger.debug("validate against Direct Office");
			entity = DOSecurityUtility.validateUser(getPassword(), "28317");
		} catch (CanNotAccessDirectOfficeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(entity.wasLoginSuccessful() == LogonConstants.LOGIN_SUCCESSFUL){
			
			setGoToPage(LogonConstants.WELCOME_PAGE);
			
		}else if(entity.wasLoginSuccessful() == LogonConstants.FIRST_TIME_LOGIN){
			
			setGoToPage(LogonConstants.NEW_PASSWORD);
		}else if(entity.wasLoginSuccessful() == LogonConstants.LOGIN_FAILED){
			
			String formId = "logon";
			String elementId = "passwordInput";
			ResourceBundle bundle = getResourceBundle(getCountryBundleName());
			String aMessage = bundle.getString(LogonConstants.LOGIN_FAILED_MESSAGE);
			
			applyErrorStyles(formId, elementId, aMessage);
			
			// count the number of incorrect password entries. If bigger than 3, show security screen
			Integer incorrectPwdNum = (Integer)sessionScope.get(LogonConstants.INCORRECT_PWD_NUM);
			
			if(incorrectPwdNum == null)
			{
				incorrectPwdNum = new Integer(1);
			}else{
				
				int value = incorrectPwdNum.intValue();
				value = value +1;
				incorrectPwdNum = new Integer(value);
				
				if(value == 4)
				{
					// the user made 3 failed attempts
					setGoToPage(LogonConstants.SECURITY_PAGE);
				}
			}
			
			sessionScope.put(LogonConstants.INCORRECT_PWD_NUM, incorrectPwdNum);
		}
		
		logger.debug("logon - EXIT");
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGoToPage() {
		return goToPage;
	}

	public void setGoToPage(String goToPage) {
		this.goToPage = goToPage;
	}
}
