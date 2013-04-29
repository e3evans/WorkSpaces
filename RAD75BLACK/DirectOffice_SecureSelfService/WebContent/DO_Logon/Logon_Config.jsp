<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<portlet:defineObjects />
<link rel="stylesheet" type="text/css"
	href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/myManpowerStylesheet.css") %>'
	title="Style">
<script type="text/javascript" src='<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/js/functions.js") %>'></script>						
<f:view>

		<f:loadBundle var="nlfile"
			basename="#{pc_DTCandidateProfileEdit.countryBundleName}" />
	<h:form id="editForm">
	<h:outputText value="#{nlfile.mm_edit_title}"/>

		<h:panelGrid width="580px" columns="2" headerClass="myManpowerCSS_innerTitle_create"
					columnClasses="myManpowerCSS_configColumnClass1, myManpowerCSS_configColumnClass2">
			<f:facet name="header">
				<h:outputText value="#{nlfile.flex_field_header}"></h:outputText>
			</f:facet>
			<h:column>
				<h:outputText value="#{nlfile.mm_edit_show_flex_first_input}"/>
			</h:column>
			
			<h:column>
				<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox"
							value="#{pc_DTCandidateProfileEdit.showFlexFirstInput}"></h:selectBooleanCheckbox>			
			</h:column>
			
			<h:column>
				<h:outputText value="#{nlfile.mm_edit_show_flex_second_input}"></h:outputText>
			</h:column>
			
			<h:column>
				<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox"
							value="#{pc_DTCandidateProfileEdit.showFlexSecondInput}"></h:selectBooleanCheckbox>						
			</h:column>			
			
			<h:column>
				<h:outputText value="#{nlfile.mm_edit_show_flex_first_list}"></h:outputText>
			</h:column>
			
			<h:column>
				<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox"
							value="#{pc_DTCandidateProfileEdit.showFlexFirstList}"></h:selectBooleanCheckbox>						
			</h:column>
			
			<h:column>
				<h:outputText value="#{nlfile.mm_edit_show_flex_second_list}"></h:outputText>
			</h:column>
			
			<h:column>
				<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox"
							value="#{pc_DTCandidateProfileEdit.showFlexSecondList}"></h:selectBooleanCheckbox>						
			</h:column>			


			<h:column>
				<h:outputText value="#{nlfile.mm_edit_required_flex_first_input}"></h:outputText>
			</h:column>
			
			<h:column>
				<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox"
							value="#{pc_DTCandidateProfileEdit.requiredFlexFirstInput}"></h:selectBooleanCheckbox>			
			</h:column>
			
			<h:column>
				<h:outputText value="#{nlfile.mm_edit_required_flex_second_input}"></h:outputText>
			</h:column>
			
			<h:column>
				<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox"
							value="#{pc_DTCandidateProfileEdit.requiredFlexSecondInput}"></h:selectBooleanCheckbox>						
			</h:column>			
			
			<h:column>
				<h:outputText value="#{nlfile.mm_edit_required_flex_first_list}"></h:outputText>
			</h:column>
			
			<h:column>
				<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox"
							value="#{pc_DTCandidateProfileEdit.requiredFlexFirstList}"></h:selectBooleanCheckbox>						
			</h:column>
			
			<h:column>
				<h:outputText value="#{nlfile.mm_edit_required_flex_second_list}"></h:outputText>
			</h:column>
			
			<h:column>
				<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox"
							value="#{pc_DTCandidateProfileEdit.requiredFlexSecondList}"></h:selectBooleanCheckbox>						
			</h:column>			
			
			</h:panelGrid>
			<h:panelGrid width="580px" columns="2" headerClass="myManpowerCSS_innerTitle_create"
						columnClasses="myManpowerCSS_configColumnClass1, myManpowerCSS_configColumnClass2">
				<f:facet name="header">
					<h:outputText value="#{nlfile.branch_details_header}"></h:outputText>
				</f:facet>
				<h:column>
				<h:outputText value="#{nlfile.mm_edit_show_preferred_locations}"></h:outputText>
			</h:column>
			
			<h:column>
				<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox"
							value="#{pc_DTCandidateProfileEdit.showPreferredWorkLocations}"></h:selectBooleanCheckbox>						
			</h:column>
			
			<h:column>
				<h:outputText value="#{nlfile.mm_edit_show_branches}"></h:outputText>
			</h:column>
			
			<h:column>
				<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox"
							value="#{pc_DTCandidateProfileEdit.showBranch}"></h:selectBooleanCheckbox>						
			</h:column>
			
			<h:column>
				<h:outputText value="#{nlfile.mm_edit_show_branch_specialties}"></h:outputText>
			</h:column>
			
			<h:column>
				<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox"
							value="#{pc_DTCandidateProfileEdit.showBranchSpecialties}"></h:selectBooleanCheckbox>						
			</h:column>
			</h:panelGrid>
			<h:panelGrid width="580px" columns="2" headerClass="myManpowerCSS_innerTitle_create" columnClasses="myManpowerCSS_configColumnClass1, myManpowerCSS_configColumnClass2">
				<f:facet name="header">
					<h:outputText value="#{nlfile.legal_question_details_header}"></h:outputText>
				</f:facet>
				<h:column>
					<h:outputText value="#{nlfile.mm_edit_show_legalqeustions}"></h:outputText>
				</h:column>
				
				<h:column>
					<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox"
								value="#{pc_DTCandidateProfileEdit.showLegalQuestions}"></h:selectBooleanCheckbox>						
				</h:column>
			</h:panelGrid>
			<h:panelGrid width="580px" 
						columns="2" 
						headerClass="myManpowerCSS_innerTitle_create" 
						columnClasses="myManpowerCSS_configColumnClass1, myManpowerCSS_configColumnClass2">
				<f:facet name="header">
					<h:outputText value="#{nlfile.contact_details_header}"></h:outputText>
				</f:facet>
			
				<h:column>
					<h:outputText value="#{nlfile.mm_address_line_1_required}"></h:outputText>
				</h:column>
			
				<h:column>
					<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox"
								value="#{pc_DTCandidateProfileEdit.addressLine1Required}"></h:selectBooleanCheckbox>						
				</h:column>
			
			<h:column>
				<h:outputText value="#{nlfile.mm_address_line_2_required}"></h:outputText>
			</h:column>
			
			<h:column>
				<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox"
							value="#{pc_DTCandidateProfileEdit.addressLine2Required}"></h:selectBooleanCheckbox>						
			</h:column>
			
			<h:column>
				<h:outputText value="#{nlfile.mm_address_line_3_required}"></h:outputText>
			</h:column>
			
			<h:column>
				<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox"
							value="#{pc_DTCandidateProfileEdit.addressLine3Required}"></h:selectBooleanCheckbox>						
			</h:column>
			
			<h:column>
				<h:outputText value="#{nlfile.mm_show_address_line_1}"></h:outputText>
			</h:column>
			
			<h:column>
				<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox"
							value="#{pc_DTCandidateProfileEdit.showAddressLine1}"></h:selectBooleanCheckbox>						
			</h:column>
			
			<h:column>
				<h:outputText value="#{nlfile.mm_show_address_line_2}"></h:outputText>
			</h:column>
			
			<h:column>
				<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox"
							value="#{pc_DTCandidateProfileEdit.showAddressLine2}"></h:selectBooleanCheckbox>						
			</h:column>
			
			<h:column>
				<h:outputText value="#{nlfile.mm_show_address_line_3}"></h:outputText>
			</h:column>
			
			<h:column>
				<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox"
							value="#{pc_DTCandidateProfileEdit.showAddressLine3}"></h:selectBooleanCheckbox>									
			</h:column>	
			
			<h:column>
				<h:outputText value="#{nlfile.mm_show_postal_code}"></h:outputText>
			</h:column>
			
			<h:column>
				<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox"
							value="#{pc_DTCandidateProfileEdit.showPostalCode}"></h:selectBooleanCheckbox>						
			</h:column>
			
			<h:column>
				<h:outputText value="#{nlfile.mm_show_country}"></h:outputText>
			</h:column>
			
			<h:column>
				<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox"
							value="#{pc_DTCandidateProfileEdit.showCountry}"></h:selectBooleanCheckbox>						
			</h:column>
			
			</h:panelGrid>
			<h:panelGrid width="580px" columns="2" headerClass="myManpowerCSS_innerTitle_create" columnClasses="myManpowerCSS_configColumnClass1, myManpowerCSS_configColumnClass2">
			<f:facet name="header">
				<h:outputText value="#{nlfile.candidate_details_header}"></h:outputText>
			</f:facet>
			<!-- CANDIDATE -->
			<h:column>
				<h:outputText value="#{nlfile.mm_show_nationality}"></h:outputText>
			</h:column>
			
			<h:column>
				<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox"
							value="#{pc_DTCandidateProfileEdit.showNationality}"></h:selectBooleanCheckbox>						
			</h:column>
				
				
			<h:column>
				<h:outputText value="#{nlfile.mm_show_nationalnumber}"></h:outputText>
			</h:column>
			
			<h:column>
				<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox"
							value="#{pc_DTCandidateProfileEdit.showNationalNumber}"></h:selectBooleanCheckbox>						
			</h:column>
			
			<h:column>
				<h:outputText value="#{nlfile.mm_show_mother_tongue}"></h:outputText>
			</h:column>
			
			<h:column>
				<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox"
							value="#{pc_DTCandidateProfileEdit.showMotherTongue}"></h:selectBooleanCheckbox>						
			</h:column>
			
			<h:column>
				<h:outputText value="#{nlfile.mm_date_of_birth}"></h:outputText>
			</h:column>
			
			<h:column>
				<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox"
							value="#{pc_DTCandidateProfileEdit.showDateOfBirth}"></h:selectBooleanCheckbox>						
			</h:column>																																																				
		</h:panelGrid>
		<h:panelGrid columns="2" headerClass="myManpowerCSS_innerTitle_create" columnClasses="myManpowerCSS_configColumnClass1, myManpowerCSS_configColumnClass2" width="580px">
			<f:facet name="header">
				<h:outputText value="#{nlfile.education_details_header}"></h:outputText>
			</f:facet>
			<h:column>
				<h:outputText value="#{nlfile.mm_education_country}"></h:outputText>
			</h:column>
			
			<h:column>
				<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox"
							value="#{pc_DTCandidateProfileEdit.showEducationCountry}"></h:selectBooleanCheckbox>						
			</h:column>
			<h:column>
				<h:outputText value="#{nlfile.mm_education_state}"></h:outputText>
			</h:column>
			
			<h:column>
				<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox"
							value="#{pc_DTCandidateProfileEdit.showEducationState}"></h:selectBooleanCheckbox>						
			</h:column>
			<h:column>
				<h:outputText value="#{nlfile.mm_education_city}"></h:outputText>
			</h:column>
			
			<h:column>
				<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox"
							value="#{pc_DTCandidateProfileEdit.showEducationCity}"></h:selectBooleanCheckbox>						
			</h:column>
			<h:column>
				<h:outputText value="#{nlfile.mm_education_dates}"></h:outputText>
			</h:column>
			
			<h:column>
				<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox"
							value="#{pc_DTCandidateProfileEdit.showEducationDates}"></h:selectBooleanCheckbox>						
			</h:column>
			<h:column>
				<h:outputText value="#{nlfile.mm_education_program}"></h:outputText>
			</h:column>
			
			<h:column>
				<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox"
							value="#{pc_DTCandidateProfileEdit.showEducationSchoolProgramName}"></h:selectBooleanCheckbox>						
			</h:column>
		</h:panelGrid>
			<h:panelGrid style="vertical-align: middle; text-align: center" width="570px">
				<h:commandButton value="#{nlfile.mm_edit_save_button_title}"
					actionListener="#{pc_DTCandidateProfileEdit.doSave}"
					styleClass="myManpowerCSS_blue24"
					action="#{pc_DTCandidateProfileEdit.doSaveAction}"></h:commandButton>
			</h:panelGrid>
		</h:form>	
</f:view>