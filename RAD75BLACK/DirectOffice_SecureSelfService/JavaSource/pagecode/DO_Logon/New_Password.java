package pagecode.DO_Logon;

import java.util.ResourceBundle;

import javax.faces.event.ActionEvent;

import com.manpower.directoffice.api.DOSecurityUtility;

import pagecode.PageCodeBase;
import utils.LogonConstants;

public class New_Password extends PageCodeBase{

	private String newPassword;
	private String confirmPassword;
	private String goToPage;
	
	public String getCountryBundleName()
	{
		String countryBundleName="application.messages.nl.USA_ApplicationMessages";
		
		return countryBundleName;
	}
	
	
	public void processNewPassword(ActionEvent event)
	{
		
		String newPassword = getNewPassword();
		String confirmPassword = getConfirmPassword();
		
		String formId = "newPasswordForm";
		String elementId = "passwordColumn";
		ResourceBundle bundle = getResourceBundle(getCountryBundleName());
		
		if(!newPassword.equals(confirmPassword))
		{
			String aMessage = bundle.getString(LogonConstants.TEXT_NOT_MATCH);
			applyErrorStyles(formId, elementId, aMessage);
			
			setGoToPage("");
			return;
		}
		
		int userIdChanged = DOSecurityUtility.changeUserid(newPassword, "28317");
		if(userIdChanged < 0)
		{
			String aMessage = "";
			if(userIdChanged == LogonConstants.PASSWORD_TOO_SHORT){
				
				aMessage = bundle.getString(LogonConstants.PWD_TOO_SHORT_MSG);
			}
			if(userIdChanged == LogonConstants.NO_NUMERIC_IN_PASSOWRD){
				
				aMessage = bundle.getString(LogonConstants.NO_NUMERIC_IN_PWD_MSG);
			}
			if(userIdChanged == LogonConstants.NO_UPPERCASE_IN_PASSWORD){
				
				aMessage = bundle.getString(LogonConstants.NO_UPPERCASE_IN_PWD_MSG);
			}
			
			applyErrorStyles(formId, elementId, aMessage);
			setGoToPage("");
			return;
		}
		
		setGoToPage(LogonConstants.WELCOME_PAGE);
	}
	
	
	public String submit()
	{
		return getGoToPage();
	}
	
	
	
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getGoToPage() {
		return goToPage;
	}

	public void setGoToPage(String goToPage) {
		this.goToPage = goToPage;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	
	
	
}
