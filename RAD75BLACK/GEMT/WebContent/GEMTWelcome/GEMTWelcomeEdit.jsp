<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/GEMTWelcome/GEMTWelcomeEdit.java" --%><%-- /jsf:pagecode --%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@page language="java" contentType="text/html"
	pageEncoding="ISO-8859-1" session="false"%>
<portlet:defineObjects />
<link rel="stylesheet" type="text/css"
	href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/stylesheet.css") %>'
	title="Style">
<f:view><hx:scriptCollector id="scriptCollector1" preRender="#{pc_GEMTWelcomeEdit.onPageLoadBegin}">

		<h:form id="form1" styleClass="form">
			<h:selectOneRadio disabledClass="selectOneRadio_Disabled"
				enabledClass="selectOneRadio_Enabled" id="setprefs"
				styleClass="selectOneRadio" value="#{sessionScope.com_manpower_portal_portlet_gemtwelcome_sess_displaypage}" layout="pageDirection">
				<f:selectItem itemValue="0" itemLabel="Change Password Portlet" />
				<f:selectItem itemValue="1" itemLabel="Login Portlet" />
			</h:selectOneRadio>
			<hx:commandExButton type="submit" value="Save Preferences" id="button1"
				styleClass="commandExButton" action="#{pc_GEMTWelcomeEdit.doButton1Action}"></hx:commandExButton>
		</h:form>
	</hx:scriptCollector>
</f:view>