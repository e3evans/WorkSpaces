package com.asponte.wcm.widgets;

import java.util.Locale;
import java.util.ResourceBundle;

import com.asponte.commons.AbstractResult;
import com.asponte.commons.Result;
import com.asponte.commons.portal.Utils;

public class ActionResult extends AbstractResult implements Result {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1209375377473656688L;

	public ActionResult(int severity, String code) {
		super(severity, code);
	}

	public ActionResult(int severity, String code, Object[] inserts) {
		super(severity, code, inserts);
	}

	public ActionResult(int severity, String code, Object[] inserts, Throwable t) {
		super(severity, code, inserts, t);
	}

	public String getMessage(Locale locale) {
		ResourceBundle rb=ResourceBundle.getBundle("com.asponte.wcm.widgets.resources.strings",locale);
		return Utils.formatString(rb.getString(getCode()),getInserts());
	}

}
