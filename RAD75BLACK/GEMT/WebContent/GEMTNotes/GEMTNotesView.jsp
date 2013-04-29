<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://www.ibm.com/jsf/BrowserFramework" prefix="odc"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false" %>
<portlet:defineObjects />
<link rel="stylesheet" type="text/css"
	href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/stylesheet.css") %>'
	title="Style">
<f:view> <hx:scriptCollector id="scriptCollector1" preRender="#{pc_GEMTNotesView.onPageLoadBegin}">	
<h:form>

	<p><h:dataTable id="table1"
		value="#{sessionScope.gemtnoteslist}"
		var="vargemtnoteslist" styleClass="dataTable"
		headerClass="GEMTHeaderClass" footerClass="footerClass"
		rowClasses="rowClass1,rowClass2" columnClasses="columnClass1"
		border="0" cellpadding="2" cellspacing="0" width="450">
		<h:column id="column1">
			<f:facet name="header">
				<h:outputText styleClass="outputText" value="ID" id="text1"></h:outputText>
			</f:facet>
			<h:outputText id="text2"
				value="#{vargemtnoteslist.id}"
				styleClass="outputText">
			</h:outputText>

		</h:column>
		<h:column id="column2">
			<f:facet name="header">
				<h:outputText styleClass="outputText" value="Name" id="text3"></h:outputText>
			</f:facet>
			<hx:requestLink id="linkViewNotes" styleClass="mpThemeCommandLink"
						value="#{vargemtnoteslist.gemt_notes_user_name}" action="#{pc_GEMTNotesView.doLinkViewNotesAction}">
			
				<f:param name="paramId" id="param1"
							value="#{vargemtnoteslist.id}"></f:param>
			</hx:requestLink>
		</h:column>
		
	</h:dataTable></p>
	</h:form>
	</hx:scriptCollector>
</f:view>
