package com.asponte.portal.designer;

import javax.portlet.PortletMode;

public interface Constants {
	PortletMode EDIT_DEFAULTS_MODE=new PortletMode("edit_defaults");
	String EXTENSION_REGISTRY_JNDI_NAME="services/extensionregistry/global";
	String WIDGET_TEMPLATES_EXTENSION_POINT_ID="com.asponte.portal.designer.pageElementTemplates";
	String EXTENSION_REGISTRY="com.asponte.portal.designer.extensionRegistry";
	String PAGE_ELEMENT_CONFIG="com.asponte.portal.designer.pageElementConfig";
	String ACTION_RESULTS="com.asponte.portal.designer.actionResults";
	String FIRST_RUN="com.asponte.portal.designer.firstRun";
	String EXTENSION_ID="extensionId";
	String TEMPLATE_NAME="templateName";
}
