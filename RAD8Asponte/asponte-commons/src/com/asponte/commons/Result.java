package com.asponte.commons;

public interface Result extends MessageProvider {
	static final int ERROR = 2;
	static final int WARNING = 1;
	int getSeverity();
	String getCode();
	Object[] getInserts();
	Throwable getThrowable();
}