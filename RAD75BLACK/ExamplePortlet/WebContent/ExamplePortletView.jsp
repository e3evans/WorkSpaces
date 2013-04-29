<%-- jsf:pagecode language="java" location="/src/pagecode/ExamplePortletView.java" --%><%-- /jsf:pagecode --%><%@taglib
	uri="http://java.sun.com/jsf/core" prefix="f"%><%@taglib
	uri="http://java.sun.com/portlet" prefix="portlet"%><%@taglib
	uri="http://www.ibm.com/xmlns/prod/websphere/portal/v6.1/portlet-client-model"
	prefix="portlet-client-model"%><%@taglib
	uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%><%@taglib
	uri="http://java.sun.com/jsf/html" prefix="h"%><%@page language="java"
	contentType="text/html" pageEncoding="ISO-8859-1" session="false"%><portlet-client-model:init>
	<portlet-client-model:require module="ibm.portal.xml.*" />
	<portlet-client-model:require module="ibm.portal.portlet.*" />
</portlet-client-model:init>
<portlet:defineObjects />
<link rel="stylesheet" type="text/css" title="Style"
	href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/stylesheet.css") %>'>
<f:view><hx:scriptCollector id="scriptCollector1">
		<h:form styleClass="form" id="form1">
			<hx:commandExButton type="submit" value="Submit"
				styleClass="commandExButton" id="testaction" action="#{pc_ExamplePortletView.doTestactionAction}"></hx:commandExButton>
		</h:form>

		<p>Place content here.</p>
	</hx:scriptCollector>
</f:view>