package utils;

public class LogonConstants {

	public static final String FORGOT_PWD = "forgotPassword";
	public static final String SUBMIT = "submit";
	
	public static final String NEW_PASSWORD = "newPassword";
	public static final String WELCOME_PAGE = "welcomePage";
	public static final String SECURITY_PAGE = "securityPage";
	
	public static final String LOGIN_FAILED_MESSAGE = "login_failed";
	public static final String PASSWORD_EMAILED = "password_emailed";
	public static final String PASSWORD_NOT_EMAILED = "password_not_emailed";
	public static final String PASSWORD_SESSION_KEY = "password_msg";
	public static final String INCORRECT_PWD_NUM = "incorrect_pwd_num";
	public static final String PWD_TOO_SHORT_MSG = "pwd_too_short_msg";
	public static final String NO_NUMERIC_IN_PWD_MSG = "no_num_in_pwd_msg";
	public static final String NO_UPPERCASE_IN_PWD_MSG = "no_uppercase_in_pwd_msg";
	
	public static final String TEXT_NOT_MATCH = "text_not_match";
	public static final String CAPTCHA_NOT_MATCH = "captch_not_match";
	
	public final static int MIN_PASSWORD_LENGTH = 8;
	public final static int FIRST_TIME_LOGIN = 1;
	public final static int LOGIN_SUCCESSFUL = 2;
	public final static int LOGIN_FAILED = 3;
	public final static int PASSWORD_TOO_SHORT= -1;
	public final static int NO_NUMERIC_IN_PASSOWRD = -2;
	public final static int NO_UPPERCASE_IN_PASSWORD = -3;
	public final static int PASSWORD_VALID = 0;
}
