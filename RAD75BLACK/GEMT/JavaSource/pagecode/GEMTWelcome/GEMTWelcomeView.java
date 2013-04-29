/**
 * 
 */
package pagecode.GEMTWelcome;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlInputSecret;
import javax.faces.component.html.HtmlMessage;
import javax.faces.component.html.HtmlOutputText;

import pagecode.PageCodeBase;

import com.ibm.faces.component.html.HtmlCommandExButton;
import com.ibm.faces.component.html.HtmlGraphicImageEx;
import com.ibm.faces.component.html.HtmlScriptCollector;
import com.ibm.portal.puma.User;
import com.manpower.portal.utility.PasswordTool;
import com.manpower.portal.utility.UserManager;

/**
 * @author Eric Evans
 *
 */
public class GEMTWelcomeView extends PageCodeBase {
	
	public static String SESS_ERROR_MESSAGE = "com_manpower_portal_portlet_gemtwelcome_errormsg";
	
	public static String CONST_GROUP_NEW_USERS="gemt_new_users";
	public static String CONST_GROUP_NEW_MANAGERS="gemt_new_managers";
	public static String CONST_GROUP_USERS="gemt_users";
	public static String CONST_GROUP_MANAGERS="gemt_managers";
	public static String CONST_BUNDLE_NAME = "com.manpower.portal.portlet.gemtwelcome.nl.GEMTWelcomePortletResource";
	
	protected HtmlGraphicImageEx OrangeOnWhiteLogo;

	protected HtmlForm form1;

	protected HtmlOutputText txt_welcome;

	protected HtmlOutputText text1;

	protected HtmlOutputText text2;

	protected HtmlInputSecret password1;

	protected HtmlMessage msg_badpassword;

	protected HtmlOutputText text3;

	protected HtmlInputSecret password2;

	protected HtmlCommandExButton change_password;

	protected HtmlScriptCollector scriptCollector1;

	protected HtmlOutputText txt_welcome_policy;
	
	public String doChange_passwordAction() {
		String RETURN_MESSAGE_ERROR = "ERROR";
		String RETURN_MESSAGE_THANKS = "THANKS";
		FacesMessage message = new FacesMessage();
		String pwd1 = getPassword1().getValue().toString().trim();
		String pwd2 = getPassword2().getValue().toString().trim();
		StringBuffer errorsb = new StringBuffer();
		ResourceBundle bundle = ResourceBundle.getBundle(CONST_BUNDLE_NAME);
		
//		PortletRequest preq = (PortletRequest)facesContext.getExternalContext().getRequest();
//		System.out.println("PRINCIPAL:  "+preq.getUserPrincipal());
////		HttpServletRequest hreq = (HttpServletRequest)getFacesContext().getExternalContext().getRemoteUser();
//		System.out.println(getFacesContext().getExternalContext().getRemoteUser());
		/*
		 * Perform a check on password.
		 * 	-Are the password and confirm password equal.
		 * 	-Does the password match the password policy?
		 */
		if (!pwd1.equals(pwd2)){
			errorsb.append(bundle.getString("gemt_welcome_nomatch"));
			errorsb.append("\r\n");
			errorsb.append(bundle.getString("gemt_welcome_tryagain"));
			message.setSummary(errorsb.toString());
			getFacesContext().addMessage(password1.getClientId(getFacesContext()), message);
			return "";
		}else if (!PasswordTool.verifyPassword(pwd1,PasswordTool.ALPHA+PasswordTool.NUMBERS+8)){
			errorsb.append(bundle.getString("gemt_welcome_passpolicy"));
			errorsb.append("\r\n");
			errorsb.append(bundle.getString("gemt_welcome_tryagain"));
			message.setSummary(errorsb.toString());
			getFacesContext().addMessage(password1.getClientId(getFacesContext()), message);
			return "";
		}
		else if (getFacesContext().getExternalContext().getRemoteUser().indexOf("wpsadmin")>-1){
			/*
			 * Changing wpsadmin's password is in a BAD idea in the history of bad ideas.  
			 * You will crash your entire portal environment which would be really, really
			 * hugely bad.  Since it's almost impossible to get back once a few things get
			 * overwritten in the DB.  DO NOT EVER TAKE THIS CODE OUT.
			 */
			errorsb.append("Cannot Change 'wpsadmin's' password! REALLY BAD IDEA.");
			errorsb.append("\r\n");
			errorsb.append(bundle.getString("gemt_welcome_tryagain"));
			message.setSummary(errorsb.toString());
			getFacesContext().addMessage(password1.getClientId(getFacesContext()), message);
			return "";
		}
		/*
		 * Password has been validated if we make it this far.
		 * 	-Remove the user from the "new" users group.
		 *  -Remove the user from the "new" managers group if they're in it.
		 *  -Add the user to the users group.
		 *  -Add the user to the managers group if they were in the "new" managers group.
		 */
		try {
			UserManager um = new UserManager(facesContext);
			User user = (User)um.getCurrentUser();
//			User user = (User) um.findUserByUid("tschultz@na.manpower.com");
			String email = (String) user.get("uid");
			um.updatePassword(email, pwd1);
			um.removeUserFromGroup(user, CONST_GROUP_NEW_USERS);
			um.addUserToGroup(user, CONST_GROUP_USERS);
			if (um.isMemberOf(user, CONST_GROUP_NEW_MANAGERS)){
				um.removeUserFromGroup(user,CONST_GROUP_NEW_MANAGERS );
				um.addUserToGroup(user,CONST_GROUP_MANAGERS);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			getSessionScope().put(SESS_ERROR_MESSAGE, e.getMessage());
			return RETURN_MESSAGE_ERROR;
		}
		
		return RETURN_MESSAGE_THANKS;
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
	protected HtmlOutputText getText1() {
		if (text1 == null) {
			text1 = (HtmlOutputText) findComponentInRoot("text1");
		}
		return text1;
	}
	protected HtmlOutputText getText2() {
		if (text2 == null) {
			text2 = (HtmlOutputText) findComponentInRoot("text2");
		}
		return text2;
	}
	protected HtmlInputSecret getPassword1() {
		if (password1 == null) {
			password1 = (HtmlInputSecret) findComponentInRoot("password1");
		}
		return password1;
	}
	protected HtmlMessage getMsg_badpassword() {
		if (msg_badpassword == null) {
			msg_badpassword = (HtmlMessage) findComponentInRoot("msg_badpassword");
		}
		return msg_badpassword;
	}
	protected HtmlOutputText getText3() {
		if (text3 == null) {
			text3 = (HtmlOutputText) findComponentInRoot("text3");
		}
		return text3;
	}
	protected HtmlInputSecret getPassword2() {
		if (password2 == null) {
			password2 = (HtmlInputSecret) findComponentInRoot("password2");
		}
		return password2;
	}
	protected HtmlCommandExButton getChange_password() {
		if (change_password == null) {
			change_password = (HtmlCommandExButton) findComponentInRoot("change_password");
		}
		return change_password;
	}
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

}