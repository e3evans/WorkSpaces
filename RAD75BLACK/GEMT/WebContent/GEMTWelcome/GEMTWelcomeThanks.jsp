<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/GEMTWelcome/GEMTWelcomeThanks.java" --%><%-- /jsf:pagecode --%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/WEB-INF/tld/portal.tld" prefix="wps" %>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<portlet:defineObjects />
<link rel="stylesheet" type="text/css"
	href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/stylesheet.css") %>'
	title="Style">
<link rel="stylesheet" type="text/css"
	href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/custStyle.css") %>'
	title="Style">
<script type="text/javascript"
		src='<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/jslib/global.js") %>'></script>
	<script type="text/javascript">
		function <portlet:namespace/>func_1(thisObj, thisEvent) {
			//use 'thisObj' to refer directly to this component instead of keyword 'this'
			//use 'thisEvent' to refer to the event generated instead of keyword 'event'
			changeClass(thisObj,'GEMTCommandButtonDarkOrange');
		}
		function <portlet:namespace/>func_2(thisObj, thisEvent) {
			//use 'thisObj' to refer directly to this component instead of keyword 'this'
			//use 'thisEvent' to refer to the event generated instead of keyword 'event'
			changeClass(thisObj,'GEMTCommandButtonOrange');
		}
		function <portlet:namespace/>logoutuser(){
			window.location.href = '<wps:url command="LogoutUser"/>';
		}
	
		function <portlet:namespace/>func_3(thisObj, thisEvent) {
			//use 'thisObj' to refer directly to this component instead of keyword 'this'
			//use 'thisEvent' to refer to the event generated instead of keyword 'event'
			<portlet:namespace/>logoutuser();
			
		}
</script>
<f:view><hx:scriptCollector id="scriptCollector1">

		<h:form id="form1" styleClass="form">
			<f:loadBundle basename="com.manpower.portal.portlet.gemtwelcome.nl.GEMTWelcomePortletResource" var="bundle"/>
		
			<table border="0" cellspacing="5">
		
			<tbody>
				<tr>
					<td valign="top">
					<hx:graphicImageEx id="OrangeOnWhiteLogo"
						styleClass="graphicImageEx" value="../images/OrangeonWhiteLogo.gif"></hx:graphicImageEx>
					</td>
					<td valign="top" style="font-face:Arial;font-size:8pt;text-align:center;width:100%;">
						<h:outputText id="txt_welcome" styleClass="GEMTOrangeWhiteHeader" value="#{bundle.gemt_welcome_thanks}"/><br>
						<br>
						
						<hx:commandExButton type="button" value="#{bundle.gemt_welcome_takemetologin}"
							id="takemetologin" styleClass="GEMTCommandButtonOrange"
							onmouseover="return #{facesContext.externalContext.response.namespace}func_1(this, event);"
							onmouseout="return #{facesContext.externalContext.response.namespace}func_2(this, event);"
							onclick="return #{facesContext.externalContext.response.namespace}func_3(this, event);"></hx:commandExButton>
					
						<hr color="#6d937f">
					</td>
				</tr>
				<tr>
					<td>
					
					</td>
					<td style="font-face:Arial;font-size:8pt;">
						
						
					</td>
				</tr>
			</tbody>
		</table>

		</h:form>
	</hx:scriptCollector>
</f:view>