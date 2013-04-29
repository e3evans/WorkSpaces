<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<%@page import="pagecode.DO_Logon.SecurityPage"%>
<portlet:defineObjects />
<LINK rel="stylesheet" type="text/css"
	href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/myManpowerStylesheet.css") %>'
	title="Style">
<script type="text/javascript" src='<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/js/functions.js") %>'></script>
<script type="text/javascript">
function openNewWindow()
{
	window.open('<%=javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() %>/CaptchaServlet2?test=value');
}
</script>
<f:view>
	
		<f:loadBundle var="nlfile"
					basename="#{pc_Logon.countryBundleName}" />
		<h:form id="securityForm">
			<div id="lightboxMyManp" class="myManpowerCSS_transparent" style="position: absolute; top: -1px; left: -1px; background-color: white; z-index: 900; display: none; width: 200%; height: 200%;">
			</div>
			<h:panelGrid styleClass="myManpowerMainPageStyle" 
						headerClass="myManpowerMainPageHederStyle">
						
				<f:facet name="header">
					<h:outputText value="#{nlfile.security_page}"></h:outputText>
				</f:facet>
				
				<h:panelGrid style="background-color: #FFF; width: 292px; margin-top: 3px;">
					<h:column>
						<h:outputText value="#{nlfile.enter_txt}"></h:outputText>
					</h:column>
					<h:column>
						
						<f:verbatim>
							<img src="<%=javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() %>/Captcha.jpg"/>

						</f:verbatim>
						
					</h:column>
					
					<h:panelGrid columns="2">
						<h:column>
							<h:inputText id="captchaInput" 
										value="#{pc_SecurityPage.captcha}" 
										style="width: 140px" 
										onkeypress="submitLogonForm();">
							</h:inputText>
							<f:verbatim>
								<br>
							</f:verbatim>
							
						</h:column>
						
						<h:commandButton value="#{nlfile.btn_submit}" 
										action="#{pc_SecurityPage.submit}"
										actionListener="#{pc_SecurityPage.processCaptcha}"
										styleClass="submit myManpowerCSS_orange24">
						</h:commandButton>
						
					</h:panelGrid>
					<h:message for="captchaInput" style="color: red"></h:message>
				</h:panelGrid>
			</h:panelGrid>
		</h:form>
</f:view>