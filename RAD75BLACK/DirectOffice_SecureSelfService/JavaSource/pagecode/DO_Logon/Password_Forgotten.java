package pagecode.DO_Logon;

import pagecode.PageCodeBase;
import utils.LogonConstants;

public class Password_Forgotten extends PageCodeBase{

	private String passwordMessage;
	
	public Password_Forgotten()
	{
		String passwordMessage = (String)sessionScope.get(LogonConstants.PASSWORD_SESSION_KEY);
		setPasswordMessage(passwordMessage);
	}
	

	public String getPasswordMessage() {
		return passwordMessage;
	}

	public void setPasswordMessage(String passwordMessage) {
		this.passwordMessage = passwordMessage;
	}
}
