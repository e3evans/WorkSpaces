package com.manpower.directoffice.xml.exception;

public class XMLHandlerGeneralInfoException extends Exception {

	private static final long serialVersionUID = 1L;

	public XMLHandlerGeneralInfoException() {
		this("An error occured while parsing the XML for the general candidate information.");
	}
	
	public XMLHandlerGeneralInfoException(String message) {
		super(message);
	}

}

