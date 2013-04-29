<%-- jsf:pagecode language="java" location="/src/pagecode/AjaxMenus/AjaxMenusHelp.java" --%><%-- /jsf:pagecode --%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@page language="java" contentType="text/html"
	pageEncoding="ISO-8859-1" session="false"%>
<portlet:defineObjects />
<link rel="stylesheet" type="text/css"
	href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/stylesheet.css") %>'
	title="Style">
<f:view><hx:scriptCollector id="scriptCollector1" preRender="#{pc_AjaxMenusHelp.onPageLoadBegin}">

		<h:form styleClass="form" id="form1">
			<hx:commandExButton type="submit" value="Submit"
				styleClass="commandExButton" id="button1"></hx:commandExButton>
		</h:form>
	</hx:scriptCollector>
</f:view>