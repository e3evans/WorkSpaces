<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/GEMTEntryForm/GEMTEntryFormEdit.java" --%><%-- /jsf:pagecode --%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<portlet:defineObjects />
<link rel="stylesheet" type="text/css"
	href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/stylesheet.css") %>'
	title="Style">
<f:view><hx:scriptCollector id="scriptCollector1" preRender="#{pc_GEMTEntryFormEdit.onPageLoadBegin}">
		<h:form id="form1" styleClass="form">
			<h:selectOneRadio disabledClass="selectOneRadio_Disabled"
				enabledClass="selectOneRadio_Enabled" id="mgremp"
				styleClass="selectOneRadio" value="#{sessionScope.com_manpower_portal_portlet_gemtentryformedit_sess_mgremp}">
				<f:selectItem itemValue="1" itemLabel="Manager Mode" />
				<f:selectItem itemValue="2" itemLabel="Employee View Mode" />
			</h:selectOneRadio>
			<hx:commandExButton type="submit" value="Submit" id="savePreferences"
				styleClass="commandExButton" action="#{pc_GEMTEntryFormEdit.doSavePreferencesAction}"></hx:commandExButton>
		</h:form>
	</hx:scriptCollector>
</f:view>