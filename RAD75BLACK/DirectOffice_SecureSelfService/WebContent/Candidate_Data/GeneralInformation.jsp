<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<portlet:defineObjects />

<LINK rel="stylesheet" type="text/css"
	href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/myManpowerStylesheet.css") %>'
	title="Style">


<f:subview id="generalInfo_subview">
			<div id="lightboxMyManp" class="myManpowerCSS_transparent" style="position: absolute; top: -1px; left: -1px; background-color: white; z-index: 900; display: none; width: 200%; height: 200%;">
			</div>
			<f:loadBundle var="nlfile"
				basename="#{pc_GeneralInformation.countryBundleName}" />
				
			<h:panelGrid id="generail_info_pg">
			
				<h:panelGrid columns="2" 
							id="general_info_msg_pg">
					<h:column>
						<h:panelGrid id="general_info_msg_lef_pg">
							<h:column>
								
								<h:panelGrid id="general_info_welcome_pg" 
											styleClass="myManpowerCSS_column-left-aligned">
									<h:column>
										<h:outputText value="#{nlfile.wecome_msg}" 
													rendered="#{pc_GeneralInformation.showWelcomeMessage}" 
													styleClass="welcome-title">
										</h:outputText>
										<h:outputText value="&nbsp;" escape="false" rendered="#{pc_GeneralInformation.showWelcomeMessage}"></h:outputText>
										<h:outputText value="#{pc_GeneralInformation.candidateName}" 
													rendered="#{pc_GeneralInformation.showWelcomeMessage}" 
													styleClass="manpower_blue_name">
										</h:outputText>
										<h:outputText value="&nbsp;" escape="false" rendered="#{pc_GeneralInformation.showWelcomeMessage}"></h:outputText>
										<h:outputText value="#{nlfile.wecome_end_msg}" 
													rendered="#{pc_GeneralInformation.showWelcomeMessage}" 
													styleClass="welcome-title">
										</h:outputText>
										<h:outputText value="&nbsp;&nbsp;" 
													escape="false" 
													rendered="#{pc_GeneralInformation.showWelcomeMessage}">
										</h:outputText>
										
										<h:outputText value="#{nlfile.got_info_err_msg}" rendered="#{!pc_GeneralInformation.showWelcomeMessage}"></h:outputText>
									</h:column>
								</h:panelGrid>
							</h:column>
							
							<h:column>
								<h:panelGrid id="general_info_action_msg_pg" 
											columns="2" columnClasses="myManpowerCSS_width60, myManpowerCSS_width40" 
											width="810px">
											
									<h:column>
										<f:verbatim>
											<p>
										</f:verbatim>
										
										<h:outputText value="#{nlfile.msg_saved}" 
											rendered="#{pc_GeneralInformation.showConfirmationMsg}" 
											styleClass="save-result-font">
										</h:outputText>
										<f:verbatim>
											</p><p>
										</f:verbatim>
										<h:outputText value="#{nlfile.info_msg_one}" 
													styleClass="text-font">
										</h:outputText>
										<f:verbatim>
											</p><p>
										</f:verbatim>
										
										<h:outputText value="#{nlfile.info_msg_two}" 
													styleClass="text-font">
										</h:outputText>
										
										<f:verbatim>
											</p><p>
										</f:verbatim>
										
										<h:outputText value="#{nlfile.info_msg_three}" 
													styleClass="text-font">
										</h:outputText>
										
										<f:verbatim>
											</p><p>
										</f:verbatim>
										
										<f:verbatim>
											<ul>
											<li>
										</f:verbatim>
										<h:outputText value="#{nlfile.assist_item_one}" styleClass="text-font"></h:outputText>
										<f:verbatim>
											</li>
											<li>
										</f:verbatim>
										<h:outputText value="#{nlfile.assist_item_two}" styleClass="text-font"></h:outputText>
										<f:verbatim>
											</li>
											<li>
										</f:verbatim>
										<h:outputText value="#{nlfile.assist_item_three}" styleClass="text-font"></h:outputText>
										<f:verbatim>
											</li>
											<li>
										</f:verbatim>
										<h:outputText value="#{nlfile.assist_item_four}" styleClass="text-font"></h:outputText>
										<f:verbatim>
											</li>
											<li>
										</f:verbatim>
										<h:outputText value="#{nlfile.assist_item_five}" styleClass="text-font"></h:outputText>
										<f:verbatim>
											</li></ul>
										</f:verbatim>
										
										<f:verbatim>
											</p
										</f:verbatim>
										
										
									</h:column>
									
									<h:column>
										<f:verbatim>
											<div id="img_padding_div"
												style="padding-left: 10px;">
												<img border="0" 
													src='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/img/BRAND_lock_and_key-bw.jpg") %>' 
													width="280" height="280" alt="">
											</div>
										</f:verbatim>
									</h:column>
								</h:panelGrid>
								
							</h:column>
						</h:panelGrid>
					</h:column>
					
					<h:column>
					</h:column>
					
					<h:column>
						<h:panelGrid styleClass="special-text myManpowerCSS_column-left-aligned" 
									id="sss_intake_msg_pg">
							<h:outputText value="#{nlfile.sss_intake_msg}" styleClass="manpower_blue"></h:outputText>
						</h:panelGrid>
					</h:column>
				</h:panelGrid>
				
				<h:column>
				
				<f:verbatim>	
						<div id="topContactInfo" 
								 class="emergency-top-style">
						</div>
						<div id="emergencyContainerDiv">
				</f:verbatim>
				<h:panelGrid id="general_info_data_pg" 
					style="margin-right: 5px; border-left: 1px solid #000000; 
						border-right: 1px solid #000000; 
						width: 848px;
						padding-left: 4px;
						margin-left: 3px;">
					
					<h:column>
						<f:verbatim>
							<div id="marginDiv" style="margin-left: 5px">
						</f:verbatim>
						<h:outputText styleClass="manpower_green"
								value="#{nlfile.contact_info_msg}">
						</h:outputText>
						
					</h:column>
					
					<h:column>
						<h:panelGrid columns="2" 
									id="emergency_data_pg" 
									style="margin-left: 5px">
							<h:column>
								<h:outputText value="#{nlfile.emergency_name}"></h:outputText>
							</h:column>
							<h:column>
								<h:inputText value="#{pc_GeneralInformation.emergencyContactName}" 
											style="width: 650px;"></h:inputText>
							</h:column>
							
							<h:column>
								<h:outputText value="#{nlfile.emergency_phone}"></h:outputText>
							</h:column>
							<h:column>
								<h:inputText value="#{pc_GeneralInformation.emergencyContactPhone}" 
											style="width: 650px;"></h:inputText>
							</h:column>
							
							<h:column>
							</h:column>
							
							<h:column>
							</h:column>
							
						</h:panelGrid>
					</h:column>
					
					<h:column>
						<h:panelGrid columns="2" 
							width="100%" 
							id="emergency_btns_pg" 
							style="margin-top: 25px; margin-bottom: 25px;">
							
							<h:column>
								<h:panelGrid style="text-align: left" 
											id="emergency_left_btn_pg">
									<h:commandButton value="#{nlfile.back_to_my_manpower}" 
													styleClass="submit myManpowerCSS_orange24"
													actionListener="#{pc_GeneralInformation.back}">
									</h:commandButton>
								</h:panelGrid>
							</h:column>
							
							<h:column>
								<h:panelGrid style="text-align: right" 
											id="emergency_right_btn_pg">
									<h:commandButton value="#{nlfile.btn_submit}" 
												styleClass="submit myManpowerCSS_orange24"
												actionListener="#{pc_GeneralInformation.submitData}"
												action="#{pc_GeneralInformation.submit}">
									</h:commandButton>
								</h:panelGrid>
							</h:column>
							
						</h:panelGrid>
					</h:column>
				</h:panelGrid>
				
				<f:verbatim>
						</div>
						<div id="centerBottomBorderContactInfo" class="emergency-botom-style" style="margin-left: 0px;">
						</div>
				</f:verbatim>
				
				</h:column>
			</h:panelGrid>
	
</f:subview>