<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<portlet:defineObjects />
<link rel="stylesheet" type="text/css"
	href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/myManpowerStylesheet.css") %>'
	title="Style">
<f:view>
	<f:loadBundle var="nlfile"
			basename="#{pc_Config.countryBundleName}" />
	<h:form>
		<h:outputText value="#{nlfile.config_page_title}">
		</h:outputText>
		<h:panelGrid 
			columns="2" 
			border="1">
			
			<h:column>
				<h:outputText value="#{nlfile.please_enter_ws_port}"></h:outputText>
			</h:column>
			
			<h:column>
				<h:inputText value="#{pc_Config.port}"></h:inputText>
			</h:column>
			
			<h:column>
				<h:commandButton value="#{nlfile.save_preferences}" 
							actionListener="#{pc_Config.doSave}" 
							action="#{pc_Config.save}" 
							styleClass="submit myManpowerCSS_orange24">
					
				</h:commandButton>
			</h:column>
			
			<h:column></h:column>
		</h:panelGrid>
	</h:form>
</f:view>