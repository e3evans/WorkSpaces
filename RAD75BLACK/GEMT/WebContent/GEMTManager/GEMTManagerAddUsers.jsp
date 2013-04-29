<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/GEMTManager/GEMTManagerAddUsers.java" --%><%-- /jsf:pagecode --%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<portlet:defineObjects />
<link rel="stylesheet"
	href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/custStyle.css") %>'
	type="text/css">
<link rel="stylesheet"
	href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/stylesheet.css") %>'
	type="text/css">
<link rel="stylesheet" type="text/css"
	href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/stylesheet.css") %>'
	title="Style">
<f:view>
	<hx:scriptCollector id="scriptCollector1" preRender="#{pc_GemtManagerAddUsers.onPageLoadBegin}">

		<h:form id="form1" styleClass="form">
			<hx:commandExButton type="submit" value="Return" id="returntomain"
				styleClass="commandExButton" action="#{pc_GemtManagerAddUsers.doReturntomainAction}"></hx:commandExButton>
		</h:form>
	</hx:scriptCollector>
</f:view>