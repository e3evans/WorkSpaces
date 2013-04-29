package com.manpower.directoffice.api;

public class DOSecurityUtility {
	final static int MIN_PASSWORD_LENGTH = 8;
	final static int FIRST_TIME_LOGIN = 1;
	final static int LOGIN_SUCCESSFUL = 2;
	final static int LOGIN_FAILED = 3;
	final static int PASSWORD_TOO_SHORT= -1;
	final static int NO_NUMERIC_IN_PASSOWRD = -2;
	final static int NO_UPPERCASE_IN_PASSWORD = -3;
	final static int PASSWORD_VALID = 0;
	
	
	
	/**
	 * @param userid This is the user id that was entered to access Self Service.  This id is what is
	 * going to be validated.
	 * 
	 * @param candidateID this is the Direct Talent candidate id.
	 * 
	 * @return 	- returns 1 if the login was successful and is the first time login. 
	 * 			- returns 2 if the login was successful.
	 * 			- returns 3 if the login failed.
	 */

	static public int validateUser (String userid, long candidateID) {
		// TODO: send to direct office the authentication
		if (userid.compareTo("11111") == 0) return FIRST_TIME_LOGIN;
		if (userid.compareTo("22222") == 0) return LOGIN_SUCCESSFUL;
		return LOGIN_FAILED;
	}
	
	/**
	 * @param oldUID - previous userid 
	 * @param newUID - new userid
	 * @param candidateID - Direct Talent Candidate Id
	 * 
	 * @return - returns true if the user id is changed.  Returns false on failure
	 */
	static public int changeUserid ( String newUID, long candidateID){
		int retValue = 0;
		retValue = validateNewPasswordStrength(newUID);
		if (retValue < 0) return retValue;
		// TODO: send request to Direct Office.
		return 0;
	}
	
	/**
	 * @param candidateID - direct talent candidate id. 	 
	 * @return - returns true if the user the request was successful.  False otherwise.
	 */
	static public boolean userForgotPassword ( long candidateID){
		// TODO: Send the request to Direct Office for the password re-send
		return true;
	}
	
	
	
	static private int validateNewPasswordStrength (String newUID) {
		if (newUID.length() < MIN_PASSWORD_LENGTH) return PASSWORD_TOO_SHORT;
		if (isNumberPresentInString(newUID) == false) return NO_NUMERIC_IN_PASSOWRD;
		if (isNumberPresentInString(newUID) == false) return NO_UPPERCASE_IN_PASSWORD;
		
		return PASSWORD_VALID;
	}
	
	static private boolean isNumberPresentInString(String newUID){
		int i;
		boolean returnVal = true;
		
		for (i = 0; i < newUID.length() ; i++ ) {
			String ch =  newUID.substring(i, (i+1));
				
			try {
				Integer.valueOf(ch);
				return true;
			} catch (NumberFormatException e) {
				returnVal = false;
			}
		}
		
		return returnVal;
	}
	
	private static boolean isUpperCaseLetterPresent(String newUID)
	{
		boolean isPresent = false;
		if(newUID == null || newUID.length() == 0)
		{
			return false;
		}
		for(int i = 0; i < newUID.length(); i++)
		{
			if(Character.isUpperCase(newUID.charAt(i)))
			{
				isPresent = true;
			}
		}
		return isPresent;
	}
}
