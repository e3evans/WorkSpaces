package com.asponte.portal.designer;

import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

public interface PageElementProvider {
	String getPortletId();
	void buildPortletConfiguration(ActionRequest request,ActionResponse response,Map<String,Object> adminPrefs,Map<String,Object> sharedPrefs) throws DesignerException;
}
