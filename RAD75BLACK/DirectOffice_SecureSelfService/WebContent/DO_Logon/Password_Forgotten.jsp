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
	var formName = "logonPwdForgotten";
	
	function submitLogonForm()
	{
		if ((event.which && event.which == 13) || (event.keyCode && event.keyCode== 13))
		{
			var btn = document.getElementById(portletNamespace+':'+formName+':'+"submitPasswordBtn");
			document.getElementById(portletNamespace+':'+formName+':'+"submitPasswordBtn").click();
		}
	}
</script>
<script type="text/javascript" src='<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/js/functions.js") %>'></script>
<f:view>
	
	
		<f:loadBundle var="nlfile"
			basename="#{pc_Logon.countryBundleName}" />
		
		<h:form id="logonPwdForgotten">
		
			<div id="lightboxMyManp" class="myManpowerCSS_transparent" style="position: absolute; top: -1px; left: -1px; background-color: white; z-index: 900; display: none; width: 200%; height: 200%;">
			</div>
			
			<h:panelGrid styleClass="myManpowerMainPageStyle" 
				headerClass="myManpowerMainPageHederStyle">
						
				<f:facet name="header">
					<h:panelGrid styleClass="myManpowerCSS_column-left-aligned">
						<h:outputText id="logong_title_out" value="#{nlfile.logon_title}" styleClass="logon_title"></h:outputText>
					</h:panelGrid>
				</f:facet>
				
				<f:verbatim>
					<br>
				</f:verbatim>
				
				<h:panelGrid style="background-color: #FFF">
					
					<h:outputText id="pwd_emailed_out" value="#{pc_Password_Forgotten.passwordMessage}"></h:outputText>
					
					<f:verbatim>
						<br>
					</f:verbatim>
					
					<h:panelGrid id="logon_panelGrid">
						<h:column>
							<h:outputText id="enter_pwd_out" value="#{nlfile.enter_pwd}"></h:outputText>
						</h:column>
						
						<h:column>
							<h:panelGrid id="enter_pwd_panelGrid" 
										columns="2"
										columnClasses="myManpowerMainPageColumn1Style, myManpowerMainPageColumn2Style">
								<h:column>
									<h:inputSecret id="passwordInput" 
												redisplay="true" 
												style="width: 140px" 
												value="#{pc_Logon.password}" 
												onkeypress="submitLogonForm();"/>
									
									<f:verbatim>
										&nbsp;
									</f:verbatim>
								</h:column>
								
								<h:column>
									<h:commandButton id="submitPasswordBtn" 
												type="submit" 
												actionListener="#{pc_Logon.logon}"
												action="#{pc_Logon.submit}" 
												value="#{nlfile.btn_submit}"
												styleClass="submit myManpowerCSS_orange24">
									</h:commandButton>
								</h:column>
							</h:panelGrid>
						</h:column>
						
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
