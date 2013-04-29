<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<portlet:defineObjects />
<LINK rel="stylesheet" type="text/css"
	href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/myManpowerStylesheet.css") %>'
	title="Style">

<script type="text/javascript">

	var viewName = "view<%=renderResponse.getNamespace()%>";
	var portletNamespace = 'view<portlet:namespace/>';
	var formName = "newPasswordForm";
	
	function submitLogonForm()
	{
		if ((event.which && event.which == 13) || (event.keyCode && event.keyCode== 13))
		{
			var btn = document.getElementById(portletNamespace+':'+formName+':'+"submitPasswordBtn");
			document.getElementById(portletNamespace+':'+formName+':'+"submitPasswordBtn").click();
		}
	}
</script>
<f:view>
	<script type="text/javascript" src='<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/js/functions.js") %>'></script>
	
	
		<f:loadBundle var="nlfile"
			basename="#{pc_Logon.countryBundleName}" />
		
		<h:form id="newPasswordForm">
			
			<h:panelGrid width="100%" styleClass="myManpowerMainPageStyle" 
				headerClass="myManpowerMainPageHederStyle">
				
				<f:facet name="header">
					<h:outputText id="logong_title_out" value="#{nlfile.logon_title}"></h:outputText>
				</f:facet>
				
				<f:verbatim>
					<br>
				</f:verbatim>
				
				<h:panelGrid style="background-color: #FFF">
				
					<h:column>
						<h:outputText id="pwd_emailed_out" value="#{nlfile.enter_new_pwd}"></h:outputText>
					</h:column>
					
					<f:verbatim>
						<br>
					</f:verbatim>
				
					<h:column>
						<h:outputText id="enter_pwd_out" value="#{nlfile.pwd_requirements}"></h:outputText>
					</h:column>
				
				
					<h:panelGrid id="logon_panelGrid" 
							style="text-align: right">
					
										
						<h:column id="passwordColumn">
							<h:panelGrid id="newPassword_panelGrid" 
									columns="2">
							
								<h:column>
									<h:outputText value="#{nlfile.new_pwd}"></h:outputText>
								</h:column>
								<h:column>
									<h:inputSecret id="passwordInput" 
												redisplay="true" 
												value="#{pc_New_Password.newPassword}" 
												style="width: 140px" 
												onkeypress="submitLogonForm();">
									</h:inputSecret>
								</h:column>
								
								<h:column>
									<h:outputText value="#{nlfile.confirm_pwd}"></h:outputText>
								</h:column>
								<h:column>
									<h:inputSecret id="passwordConfirm" 
												redisplay="true" 
												value="#{pc_New_Password.confirmPassword}" 
												style="width: 140px" 
												onkeypress="submitLogonForm();">
									</h:inputSecret>
								</h:column>
														
							</h:panelGrid>
						</h:column>
						<h:message for="passwordColumn" style="color: red"></h:message>
						
						<h:panelGrid style="text-align: right">
						
							<h:commandButton id="newPasswordSubmit" 
											value="#{nlfile.btn_submit}" 
											actionListener="#{pc_New_Password.processNewPassword}"
											action="#{pc_New_Password.submit}"
											styleClass="submit myManpowerCSS_orange24">
							</h:commandButton>
						</h:panelGrid>
						
						
					</h:panelGrid>
				</h:panelGrid>
			</h:panelGrid>
			
			<h:panelGrid 	styleClass="myManpowerMainPageStyleEnd"
							width="300"
							style="margin-top:-3px;" >
							<f:verbatim><br></f:verbatim>
			</h:panelGrid>
		</h:form>
</f:view>
