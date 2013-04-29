package com.manpower.directoffice.xml.exception;

public class CanNotAccessDirectOfficeException extends Exception {
	private static final long serialVersionUID = 1L;

	public CanNotAccessDirectOfficeException() {
		this("Direct Office is not responding");
	}
	
	public CanNotAccessDirectOfficeException(String message) {
		super(message);
	}

}

