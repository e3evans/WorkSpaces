<%-- jsf:pagecode language="java" location="/src/pagecode/WcmViewer/WcmViewerConfig.java" --%><%-- /jsf:pagecode --%>
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
<link rel="stylesheet" type="text/css"
	href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/custstylesheet.css") %>'
	title="Style">
<script type="text/javascript"
	src='<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/jslib/global.js") %>'></script>
<script type="text/javascript">
	function <portlet:namespace/>func_1(thisObj, thisEvent) {
		//use 'thisObj' to refer directly to this component instead of keyword 'this'
		//use 'thisEvent' to refer to the event generated instead of keyword 'event'
		changeClass(thisObj, 'CommandButtonDarkOrange');
	}
	function <portlet:namespace/>func_2(thisObj, thisEvent) {
		//use 'thisObj' to refer directly to this component instead of keyword 'this'
		//use 'thisEvent' to refer to the event generated instead of keyword 'event'
		changeClass(thisObj, 'CommandButtonOrange');
	}
</script>

	
<f:view><hx:scriptCollector id="scriptCollector1" preRender="#{pc_WcmViewerConfig.onPageLoadBegin}">

		<h:form id="form1" styleClass="form">
		<table>
			<tbody>
				<tr>
					<td align="right" style="padding-right:5px;">
						Wcm Super User:
					</td>
					<td>
						<h:inputText id="wcmuser" styleClass="inputText" value="#{sessionScope.com_manpower_portal_portlet_wcmviewer_superusername}"/>
					</td>
				</tr>
				<tr>
					<td align="right" style="padding-right:5px;">
						Wcm Super User Password:
					</td>
					<td>
						<h:inputSecret id="wcmuserpassword" styleClass="inputSecret" value="#{sessionScope.com_manpower_portal_portlet_wcmviewer_superuserpw}"></h:inputSecret>
					</td>
				</tr>
				<tr>
					<td align="center" valign="middle">
						<hx:commandExButton type="submit" value="Save Configuration" id="saveConfiguration" styleClass="CommandButtonOrange" action="#{pc_WcmViewerConfig.doSaveConfigurationAction}" onmouseover="return #{facesContext.externalContext.response.namespace}func_1(this, event);" onmouseout="return #{facesContext.externalContext.response.namespace}func_2(this, event);"/>
					</td>
					<td align="center" valign="middle">
						<hx:commandExButton type="submit" value="Cancel" id="cancelConfig" styleClass="CommandButtonOrange" onmouseover="return #{facesContext.externalContext.response.namespace}func_1(this, event);" onmouseout="return #{facesContext.externalContext.response.namespace}func_2(this, event);" action="#{pc_WcmViewerConfig.doCancelConfigAction}"/>
					</td>
				</tr>
			</tbody>
		</table>
			  
			
		</h:form>
	</hx:scriptCollector>
</f:view>