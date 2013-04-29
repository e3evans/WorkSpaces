<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<portlet:defineObjects />


<script type="text/javascript" src='<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/js/functions.js") %>'></script>
<f:subview id="consentSubview">
	<f:loadBundle var="nlfile"
			basename="#{pc_Logon.countryBundleName}" />
	<h:panelGrid columns="2" style="padding-left: 15px;">
		<h:selectBooleanCheckbox id="confirmTrue" 
						value="#{pc_Consent.confirmTrue}"
						valueChangeListener="#{pc_Consent.changeConsent}">
		</h:selectBooleanCheckbox>
		<h:outputText value="#{nlfile.confirm_true}"></h:outputText>
		<h:column>
		</h:column>
		<h:outputText id="consentErrorTxt" value="#{nlfile.field_mandatory}" style="color: red; display: none"></h:outputText>
	</h:panelGrid>
	
</f:subview>