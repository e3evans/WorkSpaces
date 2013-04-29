<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/GEMTEntryForm/GEMTEntryFormReportView.java" --%><%-- /jsf:pagecode --%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@page import="javax.faces.context.FacesContext"%>
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
	function <portlet:namespace/>_highlightButton(thisObj,thisEvent){
		if (thisObj.className.indexOf('Dark')>0){
			changeClass(thisObj,'GEMTCommandButtonOrange');
		}else{
			changeClass(thisObj,'GEMTCommandButtonDarkOrange');
		}
	}
</script>
	<SCRIPT type="text/javascript">
	
		function openCV(varFileID, row_id){
		
			var a = document.getElementById(row_id);
			if (a.href.indexOf('fileId=') == -1) {
				a.href = a.href + "?fileId=" + varFileID;
			}		
		}
	
		function openCenteredWindow(url) {
		    var width = 300;
		    var height = 300;
		    var left = parseInt((screen.availWidth/2) - (width/2));
		    var top = parseInt((screen.availHeight/2) - (height/2));
		    var windowFeatures = "width=" + width + ",height=" + height + 
		        ",status,resizable,left=" + left + ",top=" + top + 
		        ",screenX=" + left + ",screenY=" + top;
		    myWindow = window.open('<%=FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() %>/'+url, "subWind", windowFeatures);
		}	
	</SCRIPT>
<f:view>
	<%
		String jsfNamespace = "view"+renderResponse.getNamespace();
		String form1Base = jsfNamespace+":form1:";
	 	
	 %>

	<hx:scriptCollector id="scriptCollector1" preRender="#{pc_GEMTEntryFormReportView.onPageLoadBegin}"><h:form styleClass="form" id="form1" style="font-size:9.5pt;font-family:Arial;">
			
			<f:loadBundle var="bundle"
				basename="com.manpower.portal.portlet.gemt.nl.GEMTEntryFormResource" />
			
				 
			<TABLE class="GEMTTable" border="0" cellpadding="0" cellspacing="0" width="100%">
				<TBODY>
					<TR>
						<TD style="background-color:#d47d18;">
							<hx:graphicImageEx id="imageEx2"  value="../images/tlOrange.gif" style="display:block;"></hx:graphicImageEx>
						</TD>
						<TD style="background-color:#d47d18;text-align:right;">
							<hx:graphicImageEx id="imageEx1"  value="../images/trOrange.gif" style="display: block; margin: 0 0 0 auto;"></hx:graphicImageEx>
						</TD>
					</TR>
					<TR>
										
						<TD class="GEMTHeader" style="padding:5px;text-align:right;" colspan="2">
						<hx:commandExButton type="submit" value=" < Return to Main"
							styleClass="GEMTCommandButtonOrange" id="cancel" action="#{pc_GEMTEntryFormReportView.doCancelAction}" 
							onmouseout="return #{facesContext.externalContext.response.namespace}_highlightButton(this, event);" onmouseover="return #{facesContext.externalContext.response.namespace}_highlightButton(this, event);"></hx:commandExButton>
						<hx:commandExButton type="submit" rendered="#{pc_GEMTEntryFormReportEdit.notFinalized}" value="#{pc_GEMTEntryFormReportEdit.routeButtonName}" id="route" action="#{pc_GEMTEntryFormReportEdit.doRouteDocumentAction}" actionListener="#{pc_GEMTEntryFormReportEdit.doRouteSavedReport}"
							styleClass="GEMTCommandButtonOrange" onmouseout="return #{facesContext.externalContext.response.namespace}_highlightButton(this, event);" onmouseover="return #{facesContext.externalContext.response.namespace}_highlightButton(this, event);"></hx:commandExButton>							
						<hx:commandExButton type="submit" value="Edit Document" id="editReport" rendered="#{pc_GEMTEntryFormReportView.editButtonShown}"
							styleClass="GEMTCommandButtonOrange" action="#{pc_GEMTEntryFormReportView.doEditReportAction}"
							onmouseout="return #{facesContext.externalContext.response.namespace}_highlightButton(this, event);" onmouseover="return #{facesContext.externalContext.response.namespace}_highlightButton(this, event);"></hx:commandExButton>
						<hx:commandExButton type="submit" value="Original Report" rendered="#{pc_GEMTEntryFormReportView.originalReportAvailabe}"
											styleClass="GEMTCommandButtonOrange" id="showParentReport"
											action="#{pc_GEMTEntryFormReportView.doShowParentReportAction}"
											onmouseout="return #{facesContext.externalContext.response.namespace}_highlightButton(this, event);" 
											onmouseover="return #{facesContext.externalContext.response.namespace}_highlightButton(this, event);"/>
						<hx:commandExButton type="submit" value="Download as PDF file" id="pdfReport"
							styleClass="GEMTCommandButtonOrange" onclick="openCenteredWindow('GEMTFOPServlet?reportid=#{sessionScope.GemtSummaryReportUIBean.id}'); return false;"
							onmouseout="return #{facesContext.externalContext.response.namespace}_highlightButton(this, event);" onmouseover="return #{facesContext.externalContext.response.namespace}_highlightButton(this, event);"></hx:commandExButton>
											
						</TD>
					</TR>
				</TBODY>
			</TABLE>

			<BR>
			<BR>
			<TABLE Class="GEMTTable" border="0" cellpadding="0" cellspacing="0" style="margin-left:5px;" width="100%">
				<TBODY>
					<TR>
						<TD>
							<h:outputText id="textevalueeName" value="#{bundle.gemt_gis_name}"></h:outputText>:
						</TD>
						<TD align="left">
							<h:outputText styleClass="GEMTinputText" id="gemt_sum_empname" value="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_empname}"/>
						</TD>
						<TD>
							<h:outputText id="textevalueeTitle" value="#{bundle.gemt_gis_title}"/>:
						</TD>
						<TD>
							<h:outputText styleClass="GEMTinputText" id="gemt_sum_emptitle"
							value="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_emptitle}"/>
						</TD>
					</TR>
					<TR>
						<TD>
							<h:outputText id="textevalDate" value="#{bundle.gemt_gis_date}"/>:
						</TD>
						<TD>
							<h:outputText styleClass="GEMTinputText" id="gemt_sum_repdate" value="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_repdate}">
							<f:convertDateTime />
						</h:outputText>
							
						</TD>
						<TD>
							<h:outputText id="textManager" value="#{bundle.gemt_gis_manager}"/>:
						</TD>
						<TD>
							<h:outputText styleClass="GEMTinputText" id="gemt_sum_managername" value="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_managername}"/>
						</TD>
					</TR>
					<TR>
						<TD>
							<h:outputText id="textselectperiod" value="#{bundle.gemt_gis_period}" styleClass="outputText"></h:outputText>:
						</TD>
						<TD>
							<h:outputText styleClass="outputText" escape="false" value="Mid Year Review" rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_period==1}"/>
							<h:outputText styleClass="outputText" escape="false" value="Annual Review" rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_period==2}"/>
						</TD>
						<TD colspan="2">
						</TD>
					</TR>
				</TBODY>
			</TABLE>
			<BR>
			
			<BR>
			<TABLE Class="GEMTTable" border="0" cellpadding="0" cellspacing="0" style="margin-left:5px;">
				<TBODY>
					<TR>
						<TD class="GEMTTitleCol"><h:outputText id="textLeadershipRole" value="#{bundle.gemt_gis_leadershiprole}"/></TD>
						<TD class="GEMTRatingCol"><h:outputText id="textUnsat" value="#{bundle.gemt_gis_unsat}"/></TD>
						<TD class="GEMTRatingCol"><h:outputText id="textDeveloping" value="#{bundle.gemt_gis_developing}"/></TD>
						<TD class="GEMTRatingCol"><h:outputText id="textProficient" value="#{bundle.gemt_gis_proficient}"/></TD>
						<TD class="GEMTRatingCol"><h:outputText id="textOutstanding" value="#{bundle.gemt_gis_outstand}"/></TD>
					</TR>
					
					<TR>
						<TD class="GEMTlrBlue"><h:outputText id="textClient" value="#{bundle.gemt_gis_client}"/></TD>
						<TD class="GEMTRadioCol">
							<hx:graphicImageEx rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_roleclient==1}" value="../images/blueCheck.gif"></hx:graphicImageEx>
						</TD>
						<TD class="GEMTRadioCol">
							<hx:graphicImageEx rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_roleclient==2}" value="../images/blueCheck.gif"></hx:graphicImageEx>
						</TD>
						<TD class="GEMTRadioCol">
							<hx:graphicImageEx rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_roleclient==3}" value="../images/blueCheck.gif"></hx:graphicImageEx>
						</TD>
						<TD class="GEMTRadioCol">
							<hx:graphicImageEx rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_roleclient==4}" value="../images/blueCheck.gif"></hx:graphicImageEx>
						</TD>
					</TR>
				
					<TR>
						<TD class="GEMTlrGreen"><h:outputText id="textPeople" value="#{bundle.gemt_gis_people}"/></TD>
						<TD class="GEMTRadioCol">
							<hx:graphicImageEx rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_rolepeople==1}" value="../images/blueCheck.gif"></hx:graphicImageEx>
						</TD>
						<TD class="GEMTRadioCol">
							<hx:graphicImageEx rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_rolepeople==2}" value="../images/blueCheck.gif"></hx:graphicImageEx>
						</TD>
						<TD class="GEMTRadioCol">
							<hx:graphicImageEx  rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_rolepeople==3}" value="../images/blueCheck.gif"></hx:graphicImageEx>
						</TD>
						<TD class="GEMTRadioCol">
							<hx:graphicImageEx rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_rolepeople==4}" value="../images/blueCheck.gif"></hx:graphicImageEx>
						</TD>
					</TR>
					<TR>
						<TD class="GEMTlrOrange"><h:outputText id="textThought" value="#{bundle.gemt_gis_thought}"/></TD>
						<TD class="GEMTRadioCol">
							<hx:graphicImageEx rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_rolethought==1}" value="../images/blueCheck.gif"></hx:graphicImageEx>
						</TD>
						<TD class="GEMTRadioCol">
							<hx:graphicImageEx rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_rolethought==2}" value="../images/blueCheck.gif"></hx:graphicImageEx>
						</TD>
						<TD class="GEMTRadioCol">
							<hx:graphicImageEx rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_rolethought==3}" value="../images/blueCheck.gif"></hx:graphicImageEx>
						</TD>
						<TD class="GEMTRadioCol">
							<hx:graphicImageEx rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_rolethought==4}" value="../images/blueCheck.gif"></hx:graphicImageEx>
						</TD>
					</TR>
					<TR>
						<TD class="GEMTlrRed"><h:outputText id="textDay2Day" value="#{bundle.gemt_gis_day2day}"/></TD>
						<TD class="GEMTRadioCol">
							<hx:graphicImageEx rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_roledaytoday==1}" value="../images/blueCheck.gif"></hx:graphicImageEx>
							
						</TD>
						<TD class="GEMTRadioCol">
							<hx:graphicImageEx rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_roledaytoday==2}" value="../images/blueCheck.gif"></hx:graphicImageEx>
								
						</TD>
						<TD class="GEMTRadioCol">
							<hx:graphicImageEx rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_roledaytoday==3}" value="../images/blueCheck.gif"></hx:graphicImageEx>
						</TD>
						<TD class="GEMTRadioCol">
							<hx:graphicImageEx rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_roledaytoday==4}" value="../images/blueCheck.gif"></hx:graphicImageEx>
							
						</TD>
					</TR>
					<TR>
						<TD colspan="5">&nbsp;</TD>
					</TR>
					<TR>
						<TD class="GEMTTitleCol"><h:outputText id="textResultsOverview" value="#{bundle.gemt_gis_overview}"></h:outputText></TD>
						<TD class="GEMTRatingCol"><h:outputText	id="textBelowThresh" value="#{bundle.gemt_gis_belowthresh}"/></TD>
						<TD class="GEMTRatingCol"><h:outputText id="textThresh" value="#{bundle.gemt_gis_thresh}"/></TD>
						<TD class="GEMTRatingCol"><h:outputText id="textTarget" value="#{bundle.gemt_gis_target}"/></TD>
						<TD class="GEMTRatingCol"><h:outputText id="textOutstanding2" value="#{bundle.gemt_gis_outstand}"/></TD>
					</TR>
					<TR>
						<TD><h:outputText styleClass="GEMTPlainResults" id="textScoreCardFinancial" value="#{bundle.gemt_gis_scorefinancial}"/></TD>
						<TD class="GEMTRadioCol">
							<hx:graphicImageEx rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_overviewscorefinan==1}" value="../images/blueCheck.gif"></hx:graphicImageEx>
						</TD>	
						<TD class="GEMTRadioCol">
							<hx:graphicImageEx rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_overviewscorefinan==2}" value="../images/blueCheck.gif"></hx:graphicImageEx>
						</TD>
						<TD class="GEMTRadioCol">
							<hx:graphicImageEx rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_overviewscorefinan==3}" value="../images/blueCheck.gif"></hx:graphicImageEx>
						</TD>
						<TD class="GEMTRadioCol">
							<hx:graphicImageEx rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_overviewscorefinan==4}" value="../images/blueCheck.gif"></hx:graphicImageEx>
						</TD>
					</TR>
					<TR>
						<TD><h:outputText styleClass="GEMTPlainResults" id="textScoreCardKPI" value="#{bundle.gemt_gis_scorekpi}"/></TD>
						<TD class="GEMTRadioCol">
							<hx:graphicImageEx rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_overviewscorekpi==1}" value="../images/blueCheck.gif"></hx:graphicImageEx>
						</TD>
						<TD class="GEMTRadioCol">
							<hx:graphicImageEx rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_overviewscorekpi==2}" value="../images/blueCheck.gif"></hx:graphicImageEx>
						</TD>
						<TD class="GEMTRadioCol">
							<hx:graphicImageEx rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_overviewscorekpi==3}" value="../images/blueCheck.gif"></hx:graphicImageEx>
						</TD>
						<TD class="GEMTRadioCol">
							<hx:graphicImageEx rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_overviewscorekpi==4}" value="../images/blueCheck.gif"></hx:graphicImageEx>
						</TD>
					</TR>
				</TBODY>
			</TABLE>
			

			<br>
			<TABLE Class="GEMTTable" cellpadding="0" cellspacing="0" border="0">
				<TBODY>
					<TR>
						<TD class="GEMTTitleCol"><h:outputText id="textPerfHighs" value="#{bundle.gemt_gis_perfhighs}"/></TD>
						<TD class="GEMTTitleCol"><h:outputText id="textMissedOpps" value="#{bundle.gemt_gis_missedopps}"/></TD>
					</TR>
					<TR>
						<TD class="GEMTDefaultEditor" valign="top">
							<h:outputText escape="false" value="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_perfhighlights}" />
						</TD>
						<TD class="GEMTDefaultEditor" valign="top">
				    		<h:outputText escape="false" value="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_missedopps}" />
						</TD>
					</TR>
				</TBODY>
			</TABLE>

			<br>
			<div class="GEMTOrangeTableContainer">
				<TABLE class="GEMTTable" border="0" cellpadding="0" cellspacing="0">
					<TBODY>
						<TR>
							<TD class="GEMTOrangeTitleCol"><h:outputText id="textOverall" value="#{bundle.gemt_gis_overall}"/></TD>
							<TD class="GEMTOrangeRatingCol"><h:outputText id="textUnsat2" value="#{bundle.gemt_gis_unsat}"/></TD>
							<TD class="GEMTOrangeRatingCol"><h:outputText id="textDev2" value="#{bundle.gemt_gis_developing}"/></TD>
							<TD class="GEMTOrangeRatingCol"><h:outputText id="textProf2" value="#{bundle.gemt_gis_proficient}"/></TD>
							<TD class="GEMTOrangeRatingCol"><h:outputText id="textOut2" value="#{bundle.gemt_gis_outstand}"/></TD>
						</TR>
						<TR>
							<TD class="GEMTWrapMe"><h:outputText  id="textresultsOverview" value="#{bundle.gemt_gis_res_overview}"/></TD>
							<TD class="GEMTRadioCol">
								<hx:graphicImageEx rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_overallperf==1}" value="../images/blueCheck.gif"></hx:graphicImageEx>
							</TD>
							<TD class="GEMTRadioCol">
								<hx:graphicImageEx rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_overallperf==2}" value="../images/blueCheck.gif"></hx:graphicImageEx>
							</TD>
							<TD class="GEMTRadioCol">
								<hx:graphicImageEx rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_overallperf==3}" value="../images/blueCheck.gif"></hx:graphicImageEx>
							</TD>
							<TD class="GEMTRadioCol">
								<hx:graphicImageEx rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_overallperf==4}" value="../images/blueCheck.gif"></hx:graphicImageEx>
							</TD>
						</TR>
					</TBODY>
				</TABLE>
			</div>
			<br>

			<TABLE class="GEMTTable" border="0" cellpadding="0" cellspacing="0">
				<TBODY>
					<TR>
						<TD class="GEMTTitleCol"><h:outputText id="textComments" value="#{bundle.gemt_gis_comments}"/></TD>
					</TR>
					<TR>
						<TD class="GEMTDefaultEditor">
		    				<h:outputText value="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_comments}" escape="false"/>
						</TD>
					</TR>
				</TBODY>
			</TABLE>
			<br>
			
			<div class="GEMTOrangeTableContainer">
				<TABLE class="GEMTTable" border="0" cellpadding="0" cellspacing="0">
					<TBODY>
						<TR>
							<TD class="GEMTOrangeSectionTitleCol" colspan="2">
								<h:outputText id="textIndividualDev" value="#{bundle.gemt_gis_individualdev}"/>
							</TD>
						</TR>
						<TR>
							<TD class="GEMTOrangeSectionResults" style="font-weight:bold;" valign="top">
								<h:outputText id="textDevStrengths" value="#{bundle.gemt_gis_devstrengths}"/>
								<div class="GEMTDefaultEditor" style="font-weight:normal;">
									<h:outputText escape="false" value="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_devstrengths}" />	
								</div>
							</TD>
							<TD class="GEMTOrangeSectionResults" valign="top" style="padding-right:5px;font-weight:bold;">
								<h:outputText id="textDevNeeds" value="#{bundle.gemt_gis_devneeds}"/>
								<div class="GEMTDefaultEditor" style="font-weight:normal;">
									<h:outputText escape="false" value="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_devneeds}" />
					    		</div>
							</TD>
						</TR>
						<TR>
							<TD class="GEMTOrangeSectionResults" style="padding-bottom:10px;font-weight:bold;" valign="top">
								<h:outputText id="textDevPriorities" value="#{bundle.gemt_gis_priorities}"/>
								<div class="GEMTDefaultEditor" style="font-weight:normal;">
									<h:outputText escape="false" value="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_devpriorities}" />
								</div>
							</TD>						
							<TD class="GEMTOrangeSectionResults" style="padding-bottom:10px;padding-right:5px;font-weight:bold;" valign="top">
								<h:outputText id="textDevActions" value="#{bundle.gemt_gis_devactions}"/>
								<div class="GEMTDefaultEditor" style="font-weight:normal;">
									<h:outputText escape="false" value="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_devactions}" />
								</div>
							</TD>
						</TR>
					</TBODY>
				</TABLE>
			</div>
			<br>
			<div class="GEMTOrangeTableContainer">
				<TABLE class="GEMTTable" border="0" cellpadding="0" cellspacing="0">
					<TBODY>
						<TR>
							<TD class="GEMTOrangeSectionTitleCol" colspan="2"><h:outputText id="textEmpAck" value="#{bundle.gemt_gis_empacknowledge}"/></TD>	
						</TR>
						<TR>
							<TD class="GEMTOrangeSectionResults" style="vertical-align:top;padding-bottom:15px;"><h:outputText id="textEmpSig" value="#{bundle.gemt_gis_signature}"/>:</TD>
							<TD class="GEMTOrangeSectionResults" style="vertical-align:top;padding-bottom:15px;"><h:outputText id="textSigDate" value="#{bundle.gemt_gis_revdate}"/>:</TD>
						</TR>
					</TBODY>
				</TABLE>
			</div>
			<br>
	
		<h:dataTable value="#{pc_GEMTEntryFormReportEdit.commonFiles}"
						var="file"
						cellpadding="0"
						cellspacing="0"
						styleClass="GEMTTable"
						headerClass="GEMTOrangeTitleCol">
			<h:column>
				<f:facet name="header">
					<h:outputText value="File name"></h:outputText>
				</f:facet>
				
				<hx:outputLinkEx id="viewFileLink"
								value="#{pc_GEMTEntryFormReportEdit.fileURL}"
								target="_blank"
								onclick="openCV(#{file.id}, this.id)">
					<h:outputText value="#{file.gemt_sum_file_name}"></h:outputText>				
				</hx:outputLinkEx>
			</h:column>
		
		</h:dataTable>
			<br>
			<br>
				 
			<TABLE class="GEMTTable" border="0" cellpadding="0" cellspacing="0">
				<TBODY>
					<TR>
						<TD class="GEMTHeader" style="padding:5px;text-align:right;" colspan="2">
						<hx:commandExButton type="submit" value=" < Return to Main"
							styleClass="GEMTCommandButtonOrange" 
							onmouseout="return #{facesContext.externalContext.response.namespace}_highlightButton(this, event);" onmouseover="return #{facesContext.externalContext.response.namespace}_highlightButton(this, event);"
							id="cancel2" action="#{pc_GEMTEntryFormReportView.doCancelAction}" ></hx:commandExButton>
						<hx:commandExButton type="submit" rendered="#{pc_GEMTEntryFormReportEdit.notFinalized}" value="#{pc_GEMTEntryFormReportEdit.routeButtonName}" id="route1" action="#{pc_GEMTEntryFormReportEdit.doRouteDocumentAction}" actionListener="#{pc_GEMTEntryFormReportEdit.doRouteSavedReport}"
							styleClass="GEMTCommandButtonOrange" onmouseout="return #{facesContext.externalContext.response.namespace}_highlightButton(this, event);" onmouseover="return #{facesContext.externalContext.response.namespace}_highlightButton(this, event);"></hx:commandExButton>														
						<hx:commandExButton type="submit" value="Edit Document" id="editReport2"
							styleClass="GEMTCommandButtonOrange"  rendered="#{pc_GEMTEntryFormReportView.editButtonShown}"
							onmouseout="return #{facesContext.externalContext.response.namespace}_highlightButton(this, event);" onmouseover="return #{facesContext.externalContext.response.namespace}_highlightButton(this, event);"
							action="#{pc_GEMTEntryFormReportView.doEditReportAction}"></hx:commandExButton>
						<hx:commandExButton type="submit" value="Original Report"
											styleClass="GEMTCommandButtonOrange" rendered="#{pc_GEMTEntryFormReportView.originalReportAvailabe}"
											action="#{pc_GEMTEntryFormReportView.doShowParentReportAction}"
											onmouseout="return #{facesContext.externalContext.response.namespace}_highlightButton(this, event);" 
											onmouseover="return #{facesContext.externalContext.response.namespace}_highlightButton(this, event);"/>
						<hx:commandExButton type="submit" value="Download as PDF file" id="pdfReport1"
							styleClass="GEMTCommandButtonOrange" onclick="openCenteredWindow('GEMTFOPServlet?reportid=#{sessionScope.GemtSummaryReportUIBean.id}'); return false;"
							onmouseout="return #{facesContext.externalContext.response.namespace}_highlightButton(this, event);" onmouseover="return #{facesContext.externalContext.response.namespace}_highlightButton(this, event);"></hx:commandExButton>						
						</TD>
					</TR>
					<TR>
						<TD style="background-color:#d47d18;">
							<hx:graphicImageEx id="imageEx3" styleClass="graphicImageEx" value="../images/blOrange.gif" style="display:block;"></hx:graphicImageEx>
						</TD>
						<TD style="background-color:#d47d18;text-align:right;">
							<hx:graphicImageEx id="imageEx4" styleClass="graphicImageEx" value="../images/brOrange.gif" style="display: block; margin: 0 0 0 auto;"></hx:graphicImageEx>
						</TD>
					</TR>
				</TBODY>
			</TABLE>
				
			<input type="hidden" name="formBase" id ="formBase" value="<%=form1Base%>">
			<h:inputText styleClass="inputText" id="id" value="#{sessionScope.GemtSummaryReportUIBean.id}" rendered="#{sessionScope.GemtSummaryReportUIBean.id!=null}" style="visibility:hidden;"></h:inputText>

				
		</h:form></hx:scriptCollector>
	
	</f:view>