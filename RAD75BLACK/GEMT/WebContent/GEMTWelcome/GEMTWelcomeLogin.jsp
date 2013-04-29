<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/GEMTWelcome/GEMTWelcomeLogin.java" --%><%-- /jsf:pagecode --%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"
	import="com.manpower.portal.portlet.gemt.GEMTWelcomePortlet"%>
<portlet:defineObjects />
<link rel="stylesheet" type="text/css"
	href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/stylesheet.css") %>'
	title="Style">
<link rel="stylesheet" type="text/css"
	href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/custStyle.css") %>'
	title="Style">
<f:view><script type="text/javascript">
function <portlet:namespace/>func_1(thisObj, thisEvent) {
//use 'thisObj' to refer directly to this component instead of keyword 'this'
//use 'thisEvent' to refer to the event generated instead of keyword 'event'
changeClass(thisObj,'GEMTCommandButtonDarkOrange');
}
function <portlet:namespace/>func_2(thisObj, thisEvent) {
//use 'thisObj' to refer directly to this component instead of keyword 'this'
//use 'thisEvent' to refer to the event generated instead of keyword 'event'
changeClass(thisObj,'GEMTCommandButtonOrange');
}</script>
	<script type="text/javascript"
		src='<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/jslib/global.js") %>'></script>
	<hx:scriptCollector id="scriptCollector1" preRender="#{pc_GEMTWelcomeLogin.onPageLoadBegin}">

		<f:loadBundle
			basename="com.manpower.portal.portlet.gemtwelcome.nl.GEMTWelcomePortletResource"
			var="bundle" />

		<h:form id="gemtloginform" styleClass="form">
		<table border="0" cellspacing="5">

			<tbody>
				<tr>
					<td valign="top"><hx:graphicImageEx id="OrangeOnWhiteLogo"
						styleClass="graphicImageEx"
						value="../images/OrangeonWhiteLogo.gif"></hx:graphicImageEx></td>
					<td valign="top" style="font-face:Arial;font-size:8pt;width:100%;"><h:outputText
						id="txt_welcome" styleClass="GEMTOrangeWhiteHeader"
						value="#{bundle.gemt_welcome_title}" /> 
						<br>
						<h:outputText
						id="text1" value="#{bundle.gemt_welcome_login_prompt}"
						styleClass="GEMTRegularText" />
					<hr color="#6d937f">
					<table>
						<tr>
							<td style="font-face:Arial;font-size:8pt;text-align:right;"
								valign="middle"><h:outputText id="text2"
								styleClass="GEMTRegularText"
								value="#{bundle.gemt_welcome_login_email}"></h:outputText></td>
							<td valign="middle"><h:inputText id="useremail"
									styleClass="inputText" style="width:200px;"></h:inputText></td>
							<td rowspan="3" valign="top">
								<h:outputText id="text4" styleClass="outputText" style="font-size:8pt;color:red;" value="#{sessionScope.ERROR_LOGIN_ERROR}"/>
							</td>
						</tr>
						<tr>
							<td style="font-face:Arial;font-size:8pt;text-align:right;"
								valign="middle"><h:outputText id="text3"
								styleClass="GEMTRegularText"
								value="#{bundle.gemt_welcome_login_password}"></h:outputText></td>
							<td valign="middle"><h:inputSecret id="password"
								styleClass="inputSecret" style="width:200px;"></h:inputSecret></td>
						</tr>
						<tr>
							<td>
							</td>
							<td>
								<hx:commandExButton type="submit" value="Login to CIS" id="loginuser"
									styleClass="GEMTCommandButtonOrange" style="width:200px;" onmouseover="return #{facesContext.externalContext.response.namespace}func_1(this, event);" onmouseout="return #{facesContext.externalContext.response.namespace}func_2(this, event);" action="#{pc_GEMTWelcomeLogin.doLoginuserAction}"></hx:commandExButton>
							</td>
						</tr>
					</table>
						
					</td>
				</tr>
				<tr>
					<td></td>
					<td style="font-face:Arial;font-size:8pt;">
					</td>
				</tr>
			</tbody>
		</table>
			<input type="hidden" name="<%=GEMTWelcomePortlet.PARAM_GEMTLOGIN_ACTION%>" id="<%=GEMTWelcomePortlet.PARAM_GEMTLOGIN_ACTION%>" value="<%=GEMTWelcomePortlet.ACTION_LOGIN_ACTION%>"/>
			<input type="hidden" name="<%=GEMTWelcomePortlet.PARAM_GEMTLOGIN_PORTLETID%>" id="<%=GEMTWelcomePortlet.PARAM_GEMTLOGIN_PORTLETID%>" value="<portlet:namespace/>"/>
		</h:form>
	</hx:scriptCollector>
</f:view>