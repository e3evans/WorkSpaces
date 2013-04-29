<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/GEMTWelcome/GEMTWelcomeView.java" --%><%-- /jsf:pagecode --%>
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
	href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/custStyle.css") %>'
	title="Style">
<script type="text/javascript"
	src='<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/jslib/global.js") %>'></script>
<f:view>

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

}</script>

	<hx:scriptCollector id="scriptCollector1">
		<h:form id="form1" styleClass="form">
		<f:loadBundle basename="com.manpower.portal.portlet.gemtwelcome.nl.GEMTWelcomePortletResource" var="bundle"/>
			
		
		<table border="0" cellspacing="5">
		
			<tbody>
				<tr>
					<td valign="top">
					<hx:graphicImageEx id="OrangeOnWhiteLogo"
						styleClass="graphicImageEx" value="../images/OrangeonWhiteLogo.gif"></hx:graphicImageEx>
					</td>
					<td valign="top" style="font-face:Arial;font-size:8pt;width:100%;">
						<h:outputText id="txt_welcome" styleClass="GEMTOrangeWhiteHeader" value="#{bundle.gemt_welcome_title}"/>
						<h:outputText id="txt_welcome_policy" value="#{bundle.gemt_welcome_policy}" styleClass="GEMTRegularText" escape="false"/>
					</td>
				</tr>
				<tr>
					<td>
					
					</td>
					<td style="font-face:Arial;font-size:8pt;width:100%;">
						<h:outputText id="text1" value="#{bundle.gemt_welcome_prompt}" styleClass="GEMTRegularText"/>
						<hr color="#6d937f">
						<table>
							<tr>
								<td style="font-face:Arial;font-size:8pt;text-align:right;" valign="middle">
									<h:outputText id="text2" styleClass="GEMTRegularText" value="#{bundle.gemt_welcome_newpassword}"></h:outputText>
								</td>
								<td valign="middle">
									<h:inputSecret id="password1" styleClass="inputSecret" style="width:200px;"></h:inputSecret>
								</td>
								<td rowspan="3" valign="top"><h:message id="msg_badpassword"
									styleClass="message" for="password1"
									style="font-size:8pt;color:red;"></h:message>
								</td>
							</tr>
							<tr>
								<td style="font-face:Arial;font-size:8pt;text-align:right;" valign="middle">
									<h:outputText id="text3" styleClass="GEMTRegularText" value="#{bundle.gemt_welcome_confirmpassword}"></h:outputText>
								</td>
								<td valign="middle">
									<h:inputSecret id="password2" styleClass="inputSecret" style="width:200px;"></h:inputSecret>
								</td>
							</tr>
							<tr>
								<td>
								</td>
								<td>
									<hx:commandExButton type="submit" value="#{bundle.gemt_welcome_btnchangepassword}" id="change_password"
									styleClass="GEMTCommandButtonOrange" onmouseover="return #{facesContext.externalContext.response.namespace}func_1(this, event);" onmouseout="return #{facesContext.externalContext.response.namespace}func_2(this, event);" style="width:200px;" action="#{pc_GEMTWelcomeView.doChange_passwordAction}"></hx:commandExButton>
								</td>
							</tr>
							
						</table>
					</td>
				</tr>
			</tbody>
		</table>
		</h:form>
	</hx:scriptCollector>
</f:view>