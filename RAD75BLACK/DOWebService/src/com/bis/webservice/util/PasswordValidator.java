package com.bis.webservice.util;

public class PasswordValidator {
	public final static int MIN_PASSWORD_LENGTH = 8;

	public final static int PASSWORD_TOO_SHORT= -1;
	public final static int NO_NUMERIC_IN_PASSOWRD = -2;
	public final static int NO_UPPERCASE_IN_PASSWORD = -3;
	public final static int PASSWORD_VALID = 0;
	
	public static int validateNewPasswordStrength (String newUID) {
		if (newUID.length() < MIN_PASSWORD_LENGTH) return PASSWORD_TOO_SHORT;
		if (isNumberPresentInString(newUID) == false) return NO_NUMERIC_IN_PASSOWRD;
		if (containsUppercaseCharacter(newUID)== false) return NO_UPPERCASE_IN_PASSWORD;
		return PASSWORD_VALID;
	}
	
	static boolean containsUppercaseCharacter(String newUID){
		
		int upperCaseCount = 0;
		for (int i=0; i<newUID.length(); i++)
			for(char c='A'; c<='Z'; c++)
		    	 if (newUID.charAt(i) == c) upperCaseCount++;
		          
		if (upperCaseCount > 0) return true;
		else return false;
		
	}
	
	static boolean isNumberPresentInString(String newUID){
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
}
