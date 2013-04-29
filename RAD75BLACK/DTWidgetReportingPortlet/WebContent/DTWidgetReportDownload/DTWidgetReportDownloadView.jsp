<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>

<%@page language="java" contentType="text/html"
	pageEncoding="ISO-8859-1" session="false"%>
<portlet:defineObjects />
<LINK rel="stylesheet" type="text/css"
	href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/stylesheet2.css") %>'
	title="Style">
<script type="text/javascript">
	function openCV(varFileID, row_id){
        var a = document.getElementById(row_id);
        if (a.href.indexOf('fileId=') == -1) {
            a.href = a.href + "?fileId=" + varFileID;
        }       
    }
</script>
<f:view>
	<hx:scriptCollector id="scriptCollector1">
		<h:panelGrid styleClass="form">
			<h:dataTable styleClass="dataTable" footerClass="list-footer"
				headerClass="resultsBlueHeadingCentered" border="0"
				columnClasses="columnClassBlueBottom" cellpadding="5"
				cellspacing="0" var="rep"
				value="#{pc_DTWidgetReportDownloadView.reports}">
				
				<h:column>
					<f:facet name="header">
						<h:outputText value="Site name"/>
					</f:facet>
					<h:outputText value="#{rep.siteName}"/>
				</h:column>
				
				<h:column>
					<f:facet name="header">
						<h:outputText value="Report description"/>
					</f:facet>
					<h:outputText value="#{rep.reportDescription}"/>
				</h:column>

				<h:column>
					<f:facet name="header">
						<h:outputText value="Created on" />
					</f:facet>
					<h:outputText value="#{rep.createdOn}">
						<f:convertDateTime pattern="dd MMMM yyyy" />
					</h:outputText>
				</h:column>
				
				<h:column>
					<f:facet name="header">
						<h:outputText value="Report name" />
					</f:facet>
					<hx:outputLinkEx id="viewFileLink"
						value="#{pc_DTWidgetReportDownloadView.fileURL}" target="_blank"
						onclick="openCV(#{rep.id}, this.id)">
						<h:outputText value="#{rep.reportName}"></h:outputText>
					</hx:outputLinkEx>
				</h:column>
				
			</h:dataTable>
		</h:panelGrid>
	</hx:scriptCollector>
</f:view>
