<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<portlet:defineObjects />

<LINK rel="stylesheet" type="text/css"
	href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/myManpowerStylesheet.css") %>'
	title="Style">
	
<LINK rel="stylesheet" type="text/css"
	href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/calendar.css") %>'
	title="Style">

<script type="text/javascript" src='<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/js/functions.js") %>'></script>
<script type="text/javascript" src='<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/js/canadavalidation.js") %>'></script>
<script type="text/javascript" src='<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/js/calendar.js") %>'></script>


<script type="text/javascript">
		var portletNamespace = 'view<portlet:namespace/>';
		
		function setCanadaVisible(obj)
		{
			obj = document.getElementById(obj);
			obj.style.visibility = (obj.style.visibility == 'visible') ? 'hidden' : 'visible';
			var transparentDiv = document.getElementById('lightboxMyManp');
			if(obj.style.visibility == 'visible')
			{
				transparentDiv.style.display = 'block';
			}
			else
			{
				transparentDiv.style.display = 'none';
			}
		}
		
</script>
<f:subview id="canadaCandidateData_subview">
	
	<div id="lightboxMyManp" class="myManpowerCSS_transparent" style="position: absolute; top: -1px; left: -1px; background-color: white; z-index: 900; display: none; width: 200%; height: 200%;">
	</div>
	
	<f:loadBundle var="nlfile"
			basename="#{pc_Logon.countryBundleName}" />
			
	<f:verbatim>
		<div style="text-align: top" id="style_div1" class="mainPageStyle">
	</f:verbatim>
	
	<h:panelGrid id="us_data_pg" columns="2" 
				columnClasses="myManpowerCSS_width50" 
				style="margin: 1; text-align: top; padding-left: 15px; padding-right: 15px; padding-top: 5px;"
				headerClass="header_left_align">
		
		<f:facet name="header">
			<h:panelGrid id="usa_header_pg" style="text-align: left">
				<h:column id="h_col1">
					<h:outputText id="titleOut" value="#{nlfile.sss_intake_title}" styleClass="header-text"></h:outputText>
				</h:column>
			</h:panelGrid>
		</f:facet>
		
		
		<h:column id="us_data_col1">
		
			<f:verbatim>
				<div id="left_padding_1" style="padding-left: 15px; padding-top: 15px">
			</f:verbatim>
			
			<h:panelGrid id="mail_panelGrid" width="100%">
				<h:column id="mail_col1">
					<h:panelGrid id="personalInfo" columns="2" 
						columnClasses="myManpowerCSS_column-right-aligned, myManpowerCSS_column-right-aligned" 
						headerClass="usaNameHeader">
						<f:facet name="header">
							<h:column>
								
								<f:verbatim>
									<a id="cannot_change_href1" href="javascript:setCanadaVisible('myManpowerCSS_cannotChangePopupHelp')">
								</f:verbatim>
								
								<h:outputText id="change_out1" value="#{nlfile.why_cannot_change}" 
												styleClass="myManpowerCSS_optionsTitle"></h:outputText>
												
								<f:verbatim>
									</a>
								</f:verbatim>
								
								<f:verbatim>
									<br><br>
								</f:verbatim>
								
								<h:outputText id="personalInfo_out" value="#{nlfile.name}"></h:outputText>
							</h:column>
							
						</f:facet>
						<h:column id="personalInfo_col1">
							<h:outputText id="title_out" value="#{nlfile.title}"></h:outputText>
						</h:column>
						<h:column id="personalInfo_col2">
							<h:inputText id="title_in" value="#{pc_Canada_Candidate_Data.generalInformation.title}" readonly="true" styleClass="manpower_read_only_box"></h:inputText>
						</h:column>
						<h:column id="personalInfo_col3">
							<h:outputText id="first_name_out" value="#{nlfile.first_name}"></h:outputText>
						</h:column>
						<h:column id="personalInfo_col4">
							<h:inputText id="first_name_in" value="#{pc_Canada_Candidate_Data.candidateProfile.firstName}" readonly="true" styleClass="manpower_read_only_box"></h:inputText>
						</h:column>
						<h:column id="personalInfo_col5">
							<h:outputText id="know_as_out" value="#{nlfile.known_as}"></h:outputText>
						</h:column>
						<h:column id="personalInfo_col6">
							<h:inputText id="know_as_in" value="#{pc_Canada_Candidate_Data.generalInformation.knownAs}" readonly="true" styleClass="manpower_read_only_box"></h:inputText>
						</h:column>
						<h:column id="personalInfo_col7">
							<h:outputText id="middle_name_out" value="#{nlfile.middle_name}"></h:outputText>
						</h:column>
						<h:column id="personalInfo_col8">
							<h:inputText id="middle_name_in" value="#{pc_Canada_Candidate_Data.candidateProfile.middleName}" readonly="true" styleClass="manpower_read_only_box"></h:inputText>							
						</h:column>
						<h:column id="personalInfo_col9">
							<h:outputText id="last_name_out" value="#{nlfile.surname}"></h:outputText>
						</h:column>
						<h:column id="personalInfo_col0">
							<h:inputText id="last_name_in" value="#{pc_Canada_Candidate_Data.generalInformation.lastName}" readonly="true" styleClass="manpower_read_only_box"></h:inputText>
						</h:column>
						
					</h:panelGrid>
				</h:column>
				
				<h:column>
					<h:panelGrid id="main_addr_pg"  
								headerClass="myManpowerCSS_column-left-aligned">
						<f:facet name="header">
							<h:column id="h_col2">
								<h:outputText id="mail_addr_header_out" value="#{nlfile.main_address}"></h:outputText>
							</h:column>
						</f:facet>
					</h:panelGrid>
				</h:column>
				
				<h:column id="mail_col2">
					<h:panelGrid id="mainAddress_panelGrid" columns="2" columnClasses="myManpowerCSS_column-right-aligned, myManpowerCSS_column-right-aligned">
						
						<h:column id="mainAddress_col1">
							<h:outputText id="mail_addr_out" value="#{nlfile.mail_address_one}"></h:outputText>
						</h:column>
						<h:column id="mainAddress_col2">
							<h:inputText id="mail_addr_in" value="#{pc_Canada_Candidate_Data.candidateProfile.payrollAddress.addressLine1}" readonly="true" styleClass="manpower_read_only_box"></h:inputText>
						</h:column>
						<h:column id="mainAddress_col3">
							<h:outputText id="mail_addr2_out" value="#{nlfile.mail_address_two}"></h:outputText>
						</h:column>
						<h:column id="mainAddress_col4">
							<h:inputText id="mail_addr2_in" value="#{pc_Canada_Candidate_Data.candidateProfile.payrollAddress.addressLine2}" readonly="true" styleClass="manpower_read_only_box"></h:inputText>
						</h:column>
						<h:column id="mainAddress_col5">
							<h:outputText id="mail_city_out" value="#{nlfile.mail_city}"></h:outputText>
						</h:column>
						<h:column id="mainAddress_col6">
							<h:inputText id="mail_city_in" value="#{pc_Canada_Candidate_Data.candidateProfile.payrollAddress.city}" readonly="true" styleClass="manpower_read_only_box"></h:inputText>
						</h:column>
						<h:column id="mainAddress_col7">
							<h:outputText id="mail_reg_out" value="#{nlfile.mail_region}"></h:outputText>
						</h:column>
						<h:column id="mainAddress_col8">
							<h:inputText id="mail_reg_in" value="#{pc_Canada_Candidate_Data.candidateProfile.payrollAddress.state_province}" readonly="true" styleClass="manpower_read_only_box"></h:inputText>
						</h:column>
						<h:column id="mainAddress_col9">
							<h:outputText id="mail_post_out" value="#{nlfile.mail_post_code}"></h:outputText>
						</h:column>
						<h:column id="mainAddress_col10">
							<h:inputText id="mail_post_in" value="#{pc_Canada_Candidate_Data.candidateProfile.payrollAddress.postal_code}" readonly="true" styleClass="manpower_read_only_box"></h:inputText>
						</h:column>
						<h:column id="mainAddress_col11">
							<h:outputText id="mail_country_out" value="#{nlfile.mail_country}"></h:outputText>
						</h:column>
						<h:column id="mainAddress_col12">
							<h:inputText id="mail_country_in" value="#{pc_Canada_Candidate_Data.candidateProfile.payrollAddress.country}" readonly="true" styleClass="manpower_read_only_box"></h:inputText>
						</h:column>
					</h:panelGrid>
				</h:column>
				
				<h:column>
				</h:column>
				
				<h:column>
				</h:column>
				
				<h:column>
				</h:column>
				
				<h:column>
				</h:column>
				
			</h:panelGrid>
			
			<f:verbatim>
				</div>
			</f:verbatim>
		</h:column>
		
		<h:column id="us_data_col2">
		
			<f:verbatim>
				<div id="right_padding_1" style="text-align: left; padding-right: 20px; padding-top: 10px;">
			</f:verbatim>
			
			<h:panelGrid id="rightToWork_panelGrid" 
						columnClasses="myManpowerCSS_column-left-aligned" 
						headerClass="myManpowerCSS_column-left-aligned">
						
				<f:facet name="header">
					<h:column id="h_col3">
						<h:outputText id="right_to_work_out" value="#{nlfile.id_and_right_to_work}"></h:outputText>
					</h:column>
				</f:facet>
				
				<h:column id="rightToWork_col1">
					
					<h:panelGrid id="rightToWork_inner_panelGrid" 
								columns="2" 
								columnClasses="myManpowerCSS_column-right-aligned_50, myManpowerCSS_column-left-aligned_50" 
								headerClass="myManpowerCSS_column-left-aligned">
						
						
						<h:column id="rightToWork_inner_col1">
							<h:outputText id="legal_age_out" value="#{nlfile.at_least_legal_age}"></h:outputText>
						</h:column>
						<h:column id="rightToWork_inner_col2">
							<h:selectOneMenu id="legalAgeMenu"
										value="#{pc_Canada_Candidate_Data.eighteenYearsOldorOlder}">
								
								<f:selectItems value="#{pc_Canada_Candidate_Data.workSelectItems}"/>
							</h:selectOneMenu>
						</h:column>
						
						<h:column id="rightToWork_inner_col5">
							<h:outputText id="work_out" value="#{nlfile.entitled_work_canada}"></h:outputText>
						</h:column>
						<h:column id="rightToWork_inner_col6">
							<h:selectOneMenu id="entitledMenu"
										value="#{pc_Canada_Candidate_Data.legallyEntitledToWork}">
								<f:selectItems value="#{pc_Canada_Candidate_Data.workSelectItems}"/>
							</h:selectOneMenu>
						</h:column>
						<h:column id="rightToWork_inner_col7">
							<h:outputText id="ssn_out" value="#{nlfile.have_sin_number}"></h:outputText>
						</h:column>
						<h:column id="rightToWork_inner_col8">
							<h:selectOneMenu id="ssnMenu"
											value="#{pc_Canada_Candidate_Data.haveGovernmentId}">
								<f:selectItems value="#{pc_Canada_Candidate_Data.workSelectItems}"/>
							</h:selectOneMenu>
						</h:column>
						<h:column id="rightToWork_inner_col9">
							<h:outputText id="work_visa_out" value="#{nlfile.have_work_visa}"></h:outputText>
						</h:column>
						<h:column id="rightToWork_inner_col10">
							<h:selectOneMenu id="visaMenu"
											value="#{pc_Canada_Candidate_Data.haveWorkVisa}">
								<f:selectItems value="#{pc_Canada_Candidate_Data.workVisaSelectItems}"/>
							</h:selectOneMenu>
							
							<f:verbatim>
								<br>
							</f:verbatim>
							
							<h:outputText id="visaPossessError" value="#{nlfile.confirm_visa}" style="color: red; display: none"></h:outputText>
						</h:column>
						
						<h:column id="rightToWork_inner_col11">
							<h:outputText  id="wv_exp_date_txt" value="#{nlfile.work_visa_exp_date}"></h:outputText>
						</h:column>
						<h:column id="rightToWork_inner_col12">
						
							<f:verbatim>
								<div style="block; padding-top: 10px">
							</f:verbatim>
							
							<h:inputText id="datum3"
										style="width: 112px" 
										value="#{pc_Canada_Candidate_Data.workVisaExpirationDate}">
							</h:inputText>
							
							<f:verbatim>
									<a href="#" onClick="setYears(1800, 2020);
								       showCalender(this, 'datum3', 'canada');">
								      	<img src='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/img/calendar.gif") %>' 
								      		style="border: none;">
								    </a>
								</div>
							</f:verbatim>
							
							<f:verbatim>
								<br>
							</f:verbatim>
							
							<h:outputText id="visaExpError" value="#{nlfile.enter_visa}" style="color: red; display: none"></h:outputText>
							
							<h:outputText id="visaDateError" value="#{nlfile.visa_expired}" style="color: red; display: none"></h:outputText>
							
							
							
						</h:column>
						
						<h:column id="rightToWork_inner_col13">
							<h:outputText id="convictions_out" value="#{nlfile.canada_criminal_convictions}"></h:outputText>
						</h:column>
						<h:column id="rightToWork_inner_col14">
							<h:selectOneMenu id="convictionsMenu"
											value="#{pc_Canada_Candidate_Data.haveCriminalConvictions}">
								<f:selectItems value="#{pc_Canada_Candidate_Data.workSelectItems}"/>
							</h:selectOneMenu>
						</h:column>
						
						<h:column>
						</h:column>
						
						<h:column>
						</h:column>
						
						<h:column></h:column>
					</h:panelGrid>
					
				</h:column>
				
				
				<h:column id="rightToWork_col2">
					<f:verbatim>
						<div style="text-align: left">
					</f:verbatim>
						<h:panelGrid id="tax_payroll_panelGrid" 
									columns="2" 
									width="100%" 
									columnClasses="myManpowerCSS_column-right-aligned, myManpowerCSS_column-left-aligned" 
									headerClass="myManpowerCSS_column-left-aligned">
									
							<f:facet name="header">
								<h:column id="h_col4">
									<h:outputText id="payroll_out" value="#{nlfile.tax_payroll_details}"></h:outputText>
								</h:column>
							</f:facet>
							
							<h:column id="tax_payroll_col1">
								<h:outputText id="bo_out" value="#{nlfile.bo_id_num}"></h:outputText>
							</h:column>
							<h:column id="tax_payroll_col2">
								<h:inputText id="bo_in" value="#{pc_Canada_Candidate_Data.candidateProfile.backOfficeIDNumber}" readonly="true" styleClass="manpower_read_only_box"></h:inputText>
							</h:column>
							
							<h:column id="tax_payroll_col3">
								<h:outputText  id="birth_date_txt" value="#{nlfile.birth_date}"></h:outputText>
							</h:column>
							<h:column id="tax_payroll_col4">
							
								<f:verbatim>
									<div style="block">
								</f:verbatim>
								
								<h:inputText id="datum4" 
											style="width: 112px"
											value="#{pc_Canada_Candidate_Data.dateOfBirth}">
								</h:inputText>
								
								<f:verbatim>
										<a href="#" onClick="setYears(1800, 2020);
									       showCalender(this, 'datum4', 'canada');">
									      	<img src='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/img/calendar.gif") %>' 
									      		style="border: none;">
									    </a>
									</div>
								</f:verbatim>
							</h:column>
							
							<h:column>
							</h:column>
						</h:panelGrid>
					<f:verbatim>
						</div>
					</f:verbatim>
				</h:column>
			</h:panelGrid>
			
			<f:verbatim>
				</div>
			</f:verbatim>
		</h:column>
		
		<h:column id="main_payroll_header_col">
			<f:verbatim>
				<div id="left_padding_2" style="padding-left: 15px;">
			</f:verbatim>
			<h:panelGrid id="payment_addr_pg" style="text-align: left">
				<f:facet name="header">
					<h:column id="main_col1">
						<h:outputText id="mail_out" value="#{nlfile.payment_mailing_addr}"></h:outputText>
					
					
						<f:verbatim>
							&nbsp;
						</f:verbatim>
						
						<f:verbatim>
							<a id="why_need_href" href="javascript:setCanadaVisible('myManpowerCSS_mainAdddrNeedPopupHelp')">
						</f:verbatim>
						
						<h:outputText id="why_need_href_txt3" value="#{nlfile.what_this_means_msg}" 
										styleClass="myManpowerCSS_optionsTitle"></h:outputText>
										
						<f:verbatim>
							</a>
						</f:verbatim>
					</h:column>	
				</f:facet>
			</h:panelGrid>
			<f:verbatim>
				</div>
			</f:verbatim>
		</h:column>
		
		<h:column id="space_col">
		</h:column>
		
		<h:column id="x_col1">
			
		</h:column>
		
		<h:column id="space_col3">
		</h:column>
		
		<h:column id="us_data_col3">
			<f:verbatim>
				<div style="text-align: left; padding-left: 20px;">
			</f:verbatim>
			<h:panelGrid id="main_panelGrid" 
						columns="2" 
						width="380px"
						columnClasses="myManpowerCSS_column-right-aligned, myManpowerCSS_column-left-aligned">
				<f:facet name="header">
					<h:panelGrid columns="2"
							styleClass="myManpowerCSS_column-right-aligned, myManpowerCSS_column-right-aligned">
							
						<h:column>
							<h:outputText id="diff_out" value="#{nlfile.payment_addr_diff_from_main}"></h:outputText>
						</h:column>
						
						<h:column id="main_col3">
							<h:selectOneMenu id="payrollDiffFromMain" value="#{pc_Canada_Candidate_Data.candidateProfile.paymentAddrDiffFromMain}">
								<f:selectItems value="#{pc_Canada_Candidate_Data.selectItems}"/>
							</h:selectOneMenu>
						</h:column>
						
						<h:column id="main_coll1">
							<h:outputText id="payrollDiffError" 
										style="color: red; display: none" 
										value="#{nlfile.confirm_payroll_different}">
							</h:outputText>
						</h:column>
					</h:panelGrid>
				</f:facet>
				
				<h:column id="main_col4">
					<h:outputText id="main_addr_out" value="#{nlfile.main_addr_one}"></h:outputText>
				</h:column>
				<h:column id="main_col5">
					<h:inputText id="addressLineOne" value="#{pc_Canada_Candidate_Data.generalInformation.mainAddress.addressLine1}" style="width: 200px"></h:inputText>
					
					<f:verbatim>
						<br>
					</f:verbatim>
					
					<h:outputText id="addressLineOneError" value="#{nlfile.addressone_required}" style="color: red; display: none"></h:outputText>
				</h:column>
				<h:column id="main_col6">
					<h:outputText id="main_addr2_out" value="#{nlfile.main_addr_two}"></h:outputText>
				</h:column>
				<h:column id="main_col7">
					<h:inputText id="main_addr2_in" value="#{pc_Canada_Candidate_Data.generalInformation.mainAddress.addressLine2}" style="width: 200px"></h:inputText>
					
					<f:verbatim>
						<br>
					</f:verbatim>
					
					<h:outputText id="addressLineTwoError" value="#{nlfile.field_mandatory}" style="color: red; display: none"></h:outputText>
				</h:column>
				<h:column id="main_coll7">
					<h:outputText id="city_out" value="#{nlfile.main_city}"></h:outputText>
				</h:column>
				<h:column id="main_col8">
					<h:inputText id="main_city_in" value="#{pc_Canada_Candidate_Data.generalInformation.mainAddress.city}" style="width: 200px"></h:inputText>
					
					<f:verbatim>
						<br>
					</f:verbatim>
					
					<h:outputText id="cityError" value="#{nlfile.city_required}" style="color: red; display: none"></h:outputText>
				</h:column>
				<h:column id="main_col9">
					<h:outputText id="main_region_out" value="#{nlfile.main_region}"></h:outputText>
				</h:column>
				<h:column id="main_col10">
					<h:selectOneMenu id="main_state_select" 
									value="#{pc_Canada_Candidate_Data.generalInformation.mainAddress.state_province}"
									style="width: 200px">
						
						<f:selectItems value="#{pc_Canada_Candidate_Data.regionSelectItems}"/>
					</h:selectOneMenu>
					
					<f:verbatim>
						<br>
					</f:verbatim>
					
					<h:outputText id="stateError" value="#{nlfile.state_required}" style="color: red; display: none"></h:outputText>
				</h:column>
				<h:column id="main_col11">
					<h:outputText id="main_post_out" value="#{nlfile.main_post_code}"></h:outputText>
				</h:column>
				<h:column id="main_col12">
					<h:inputText id="main_code_in" value="#{pc_Canada_Candidate_Data.generalInformation.mainAddress.postal_code}" style="width: 200px"></h:inputText>
					
					<f:verbatim>
						<br>
					</f:verbatim>
					
					<h:outputText id="postalCodeError" value="#{nlfile.field_mandatory}" style="color: red; display: none"></h:outputText>
				</h:column>
				<h:column id="main_col13">
					<h:outputText id="main_country_out" value="#{nlfile.main_country}"></h:outputText>
				</h:column>
				<h:column id="main_col14">
					<h:inputText id="main_country_in" value="#{pc_Canada_Candidate_Data.generalInformation.mainAddress.country}" style="width: 200px"></h:inputText>
					
					<f:verbatim>
						<br>
					</f:verbatim>
					
					<h:outputText id="countryError" value="#{nlfile.country_required}" style="color: red; display: none"></h:outputText>
				</h:column>
			</h:panelGrid>
			<f:verbatim>
				</div>
			</f:verbatim>
		</h:column>
		
		<h:column></h:column>
		
		<h:column>
			<f:verbatim>
				<div id="jsp_left_div" style="padding-left: 20px;">
			</f:verbatim>
			
				<jsp:include page="Consent.jsp"></jsp:include>
			
			<f:verbatim>
				</div>
			</f:verbatim>
		</h:column>
		
		<h:column></h:column>
		
		<h:column>
			<f:verbatim>
				<div id="left_padding_3" style="padding-left: 15px;">
			</f:verbatim>
			
			<h:panelGrid id="usControl_panelGrid" columns="3">
	
				<h:column>
					<h:commandButton id="submitChangesBtn" 
						actionListener="#{pc_Canada_Candidate_Data.submitData}"
						action="#{pc_Canada_Candidate_Data.submit}"
						value="#{nlfile.submit_changes}" 
						styleClass="submit myManpowerCSS_orange24" 
						onclick="return checkCanadaForm();"/>
				</h:column>
				
						
				<h:column></h:column>
				
				<h:column>
					<h:commandButton id="cancelChangesBtn" 
						actionListener="#{pc_Canada_Candidate_Data.cancel}"
						value="#{nlfile.cancel_return_to_mymanpower}" 
						styleClass="submit myManpowerCSS_orange24"/>
				</h:column>
				
						
			</h:panelGrid>
			
			<f:verbatim>
				</div>
			</f:verbatim>
		</h:column>
		
	</h:panelGrid>
	
	<f:verbatim>
		</div>
	</f:verbatim>
	
</f:subview>