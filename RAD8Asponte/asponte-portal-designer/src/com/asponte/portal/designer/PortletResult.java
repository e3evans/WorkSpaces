package com.asponte.portal.designer;

import java.util.Locale;
import java.util.ResourceBundle;

import com.asponte.commons.AbstractResult;
import com.asponte.commons.portal.Utils;

public class PortletResult extends AbstractResult {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5178495874204225783L;
	public PortletResult(int severity, String code) {
		super(severity, code);
	}

	public PortletResult(int severity, String code, Object[] inserts) {
		super(severity, code, inserts);
	}

	public PortletResult(int severity, String code, Object[] inserts,Throwable t) {
		super(severity, code, inserts, t);
	}

	public String getMessage(Locale locale) {
		ResourceBundle rb=PageElementPortlet.getInstance().getResourceBundle(locale);
		return Utils.formatString(rb.getString(getCode()),getInserts());
	}

}
