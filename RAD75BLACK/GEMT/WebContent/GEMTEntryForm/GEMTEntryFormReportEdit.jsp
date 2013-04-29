<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/GEMTEntryForm/GEMTEntryFormReportEdit.java" --%><%-- /jsf:pagecode --%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<%@page import="com.manpower.portal.portlet.gemt.GEMTEntryForm"%>
<portlet:defineObjects />
<link rel="stylesheet" type="text/css"
	href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/stylesheet.css") %>'
	title="Style">
<link rel="stylesheet" type="text/css"
	href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/custStyle.css") %>'
	title="Style">
<f:view><script type="text/javascript">
	
		function openCV(varFileID, row_id){
		
			var a = document.getElementById(row_id);
			if (a.href.indexOf('fileId=') == -1) {
				a.href = a.href + "?fileId=" + varFileID;		
			}		
		}
		
		function validateCVSelection(thisObj, thisEvent) {
		
		  var viewName = "view<%=renderResponse.getNamespace()%>";
		  var formName = "filesUploading";
		  var form = document.getElementById(viewName+":"+formName);
		
		if(form.elements['uploadedFile'].value == '') {
		
			document.getElementById('cvJSErrorMsg').style['visibility'] = 'visible';
			return false;
		}
		
		return true;
		}		
	
	</script>
	<%
		String jsfNamespace = "view"+renderResponse.getNamespace();
		String form1Base = jsfNamespace+":form1:";
	 	
	 %>
	<SCRIPT type="text/javascript">
	var djConfig={isDebug:false,
				allowQueryConfig:false,
				baseScriptUri:'<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/jslib/dojolib/")%>',
				baseRelativePath:'<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/jslib/dojolib/")%>',
				libraryScriptUri:'<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/jslib/dojolib/")%>',
				iePreventClobber:false,
				ieClobberMinimal:true,
				preventBackButtonFix:true,
				delayMozLoadingFix:false,
				searchIds:['perfCommentsEditor','perfHighlightsEditor','perfMissedOppsEditor','perfPrioritiesEditor','perfActionsEditor','perfNeedsEditor','perfStrenghtsEditor'],
				parseWidgets:false};
	
		
	function <portlet:namespace/>_highlightButton(thisObj,thisEvent){
		if (thisObj.className.indexOf('Dark')>0){
			changeClass(thisObj,'GEMTCommandButtonOrange');
		}else{
			changeClass(thisObj,'GEMTCommandButtonDarkOrange');
		}
	}
	
</SCRIPT>
	<SCRIPT	src='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/jslib/dojolib/dojoEditor.js") %>'
		type="text/javascript"> 
    </SCRIPT>
    <SCRIPT src='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/jslib/bullets.js") %>' type="text/javascript">
    </SCRIPT>
    <script type="text/javascript"
		src='<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/jslib/global.js") %>'></script>
	<SCRIPT type="text/javascript">
		dojo.require('dojo.widget.Editor2');
	</SCRIPT>

	<script>
		
	
		

		function func_1(thisObj, thisEvent) {
			//use 'thisObj' to refer directly to this component instead of keyword 'this'
			//use 'thisEvent' to refer to the event generated instead of keyword 'event'
			
			changeClass(thisObj,'GEMTinputTextActive');
		}
		function func_2(thisObj, thisEvent) {
			//use 'thisObj' to refer directly to this component instead of keyword 'this'
			//use 'thisEvent' to refer to the event generated instead of keyword 'event'
			changeClass(thisObj,'GEMTinputText');
		}
	

	
	var currentTextAreaId;
	var currentNamespace;
	var startPos;
	var editingBullet;
	function mousePos(position){
		startPos=position;
	}
	
	function <portlet:namespace/>show_bullet_Editor(thisObj, thisEvent,textAreaId,namespace) {
				//use 'thisObj' to refer directly to this component instead of keyword 'this'
			//use 'thisEvent' to refer to the event generated instead of keyword 'event'
			currentTextAreaId=textAreaId;
			currentNamespace = namespace;
			startPos=findButtonPos(thisObj);
			
			showPostPanel('',startPos);
			
	}
	function <portlet:namespace/>edit_Bullets(thisObj, thisEvent,textAreaId,namespace) {
		//use 'thisObj' to refer directly to this component instead of keyword 'this'
		//use 'thisEvent' to refer to the event generated instead of keyword 'event'
		currentTextAreaId=textAreaId;
		currentNamespace = namespace;
		startPos=findButtonPos(thisObj);
		toggleEdit(currentTextAreaId);
	}
</script>
	<div id="enclosingNode" style="position:absolute;top:-100;left:-100;z-index:5;background-color:#FFFFFF;border:1px solid #908f8f;padding:10px;">
		<input class="GEMTCommandButtonWhite" type="button" value="Close" onclick="closePostPanel()" />
		<input class="GEMTCommandButtonWhite" type="button" value="Save Bullet" onclick="addItem()" />
		<div style="width:400px;height:50px;" id="masterBullets"></div>
	</div>
	
	<hx:scriptCollector id="scriptCollector1" preRender="#{pc_GEMTEntryFormReportEdit.onPageLoadBegin}">
		<h:form styleClass="form" id="form1" style="font-size:9.5pt;font-family:Arial;">
			
			<f:loadBundle var="bundle"
				basename="com.manpower.portal.portlet.gemt.nl.GEMTEntryFormResource" />
			<TABLE class="GEMTTable" border="0" cellpadding="0" cellspacing="0">
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
							styleClass="GEMTCommandButtonOrange" id="cancel" action="#{pc_GEMTEntryFormReportEdit.doCancelAction}" onmouseout="return #{facesContext.externalContext.response.namespace}_highlightButton(this, event);" onmouseover="return #{facesContext.externalContext.response.namespace}_highlightButton(this, event);"></hx:commandExButton>
						<hx:commandExButton type="submit" rendered="#{pc_GEMTEntryFormReportEdit.notFinalized}" value="#{pc_GEMTEntryFormReportEdit.routeButtonName}" id="route" action="#{pc_GEMTEntryFormReportEdit.doRouteDocumentAction}" actionListener="#{pc_GEMTEntryFormReportEdit.doRouteDocument}"
							styleClass="GEMTCommandButtonOrange" onmouseout="return #{facesContext.externalContext.response.namespace}_highlightButton(this, event);" onmouseover="return #{facesContext.externalContext.response.namespace}_highlightButton(this, event);"></hx:commandExButton>
						<hx:commandExButton type="submit" value="Toggle View / Edit" id="toggle"
							styleClass="GEMTCommandButtonOrange" action="#{pc_GEMTEntryFormReportEdit.doToggleAction}" onmouseout="return #{facesContext.externalContext.response.namespace}_highlightButton(this, event);" onmouseover="return #{facesContext.externalContext.response.namespace}_highlightButton(this, event);"></hx:commandExButton>
						<hx:commandExButton type="submit" value="Save as Draft"
							id="saveReport" styleClass="GEMTCommandButtonOrange"
							action="#{pc_GEMTEntryFormReportEdit.doCreateRowTestAction}"
							onmouseout="return #{facesContext.externalContext.response.namespace}_highlightButton(this, event);"
							onmouseover="return #{facesContext.externalContext.response.namespace}_highlightButton(this, event);"></hx:commandExButton>
						
						
						</TD>
						
					</TR>
				</TBODY>
			</TABLE>
			<BR>
			<BR>
			<TABLE Class="GEMTTable" border="0" cellpadding="0" cellspacing="0" style="margin-left:5px;">
				<TBODY>
					<TR>
						<TD>
							<h:outputText id="textevalueeName" value="#{bundle.gemt_gis_name}"></h:outputText>:
						</TD>
						<TD align="left">
							<h:outputText styleClass="GEMTinputText" id="gemt_sum_empname" value="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_empname}"></h:outputText>
						</TD>
						<TD>
							<h:outputText id="textevalueeTitle" value="#{bundle.gemt_gis_title}"/>:
						</TD>
						<TD>
							<h:inputText styleClass="GEMTinputText" id="gemt_sum_emptitle"
							onfocus="return func_1(this, event);"
							onblur="return func_2(this, event);" tabindex="2" value="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_emptitle}"></h:inputText>
						</TD>
					</TR>
					<TR>
						<TD>
							<h:outputText id="textevalDate" value="#{bundle.gemt_gis_date}"/>:
						</TD>
						<TD>
							<h:inputText styleClass="GEMTinputText" id="gemt_sum_repdate" 
							onfocus="return func_1(this, event);"
							onblur="return func_2(this, event);"
							 tabindex="1" value="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_repdate}">
							<f:convertDateTime pattern="MM/dd/yyyy" />
							<hx:inputHelperDatePicker />
							</h:inputText>
						</TD>
						<TD>
							<h:outputText id="textManager" value="#{bundle.gemt_gis_manager}"/>:
						</TD>
						<TD>
							<h:outputText styleClass="GEMTinputText" id="gemt_sum_managername" value="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_managername}"></h:outputText>
						</TD>
					</TR>
					<TR>
						<TD>
							<h:outputText id="textselectperiod" value="#{bundle.gemt_gis_period}" styleClass="outputText"></h:outputText>
						</TD>
						<TD>
								<h:outputText escape="false" value="Mid-Year" rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_period==1}"/>
								<h:outputText escape="false" value="Annual" rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_period==2}"/>								
						</TD>
						<TD colspan="2">
						</TD>
					</TR>
				</TBODY>
			</TABLE>
			<BR>
			<TABLE Class="GEMTTable" cellpadding="0" cellspacing="0" border="0">
				<TBODY>
					<TR>
						<TD class="GEMTTitleCol" valign="middle" align="left"><h:outputText id="textPerfHighs" value="#{bundle.gemt_gis_perfhighs}"/>
						</TD>
						<TD class="GEMTTitleCol" valign="middle" align="center" style="padding-bottom:5px;">
						&nbsp;
						</TD>
						<TD class="GEMTTitleCol"><h:outputText id="textMissedOpps" value="#{bundle.gemt_gis_missedopps}"/></TD>
						<TD class="GEMTTitleCol" valign="middle" align="center" style="padding-bottom:5px;">
						&nbsp;
						</TD>
					</TR>
					<TR> 
						<TD valign="top" colspan="2">
							<div style="border: 1px solid #908f8f; width:98%;font-size:8pt;font-family:Arial;" id="perfHighlightsEditor">
							<textArea class="GEMTDefaultEditor"  dojoType="Editor"  items="textGroup;|;justifyGroup;|;listGroup;" id="<%=jsfNamespace%>:form1:gemt_sum_perfhighlights" name="<%=jsfNamespace%>:form1:gemt_sum_perfhighlights" >				       
				    				<span style="font-size:8pt;font-family:Arial;">
										<h:outputText value="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_perfhighlights}" escape="false"/>
									</span>
				    		</textArea>
							</div>
						</TD>
						<TD valign="top" colspan="2">
							<div style="border: 1px solid #908f8f; width:100%;font-size:8pt;font-family:Arial;" id="perfMissedOppsEditor">
								<textArea class="GEMTDefaultEditor" dojoType="Editor"  items="textGroup;|;justifyGroup;|;listGroup;" id="<%=jsfNamespace%>:form1:gemt_sum_missedopps" name="<%=jsfNamespace%>:form1:gemt_sum_missedopps" >				       
				    				<span style="font-size:8pt;font-family:Arial;">
										<h:outputText value="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_missedopps}" escape="false"/>
									</span>
				    			</textArea>
							</div>
						</TD>
					</TR>
				</TBODY>
			</TABLE>
			
			<BR>
			<TABLE Class="GEMTTable" border="0" cellpadding="0" cellspacing="0" style="margin-left:5px;">
				<TBODY>
					<TR>
						<TD class="GEMTTitleCol"><h:outputText id="textLeadershipRole" value="#{bundle.gemt_gis_leadershiprole}"/></TD>
						<TD class="GEMTRatingCol"><h:outputText id="textExpectations" value="#{bundle.gemt_gis_expected}"/></TD>
						<TD class="GEMTRatingCol"><h:outputText id="textUnsat" value="#{bundle.gemt_gis_unsat}"/></TD>
						<TD class="GEMTRatingCol"><h:outputText id="textDeveloping" value="#{bundle.gemt_gis_developing}"/></TD>
						<TD class="GEMTRatingCol"><h:outputText id="textProficient" value="#{bundle.gemt_gis_proficient}"/></TD>
						<TD class="GEMTRatingCol"><h:outputText id="textOutstanding" value="#{bundle.gemt_gis_outstand}"/></TD>
					</TR>
					
					<TR>
						<TD class="GEMTlrBlue"><h:outputText id="textClient" value="#{bundle.gemt_gis_client}"/></TD>
						<TD></TD>
						<TD class="GEMTRadioCol">
							<INPUT type="radio" 
							name="<%=jsfNamespace%>:form1:gemt_sum_roleclient" 
							id="<%=jsfNamespace%>:form1:gemt_sum_roleclient" 
							value="1"
							<h:outputText escape="false" value="CHECKED" rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_roleclient==1}"/>>
							 
						</TD>
						<TD class="GEMTRadioCol">
							<INPUT type="radio" 
							name="<%=jsfNamespace%>:form1:gemt_sum_roleclient" 
							id="<%=jsfNamespace%>:form1:gemt_sum_roleclient"
							value="2"
							<h:outputText escape="false" value="CHECKED" rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_roleclient==2}"/>>
						</TD>
						<TD class="GEMTRadioCol">
							<INPUT type="radio" 
							name="<%=jsfNamespace%>:form1:gemt_sum_roleclient" 
							id="<%=jsfNamespace%>:form1:gemt_sum_roleclient"
							value="3"
							<h:outputText escape="false" value="CHECKED" rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_roleclient==3}"/>>
						</TD>
						<TD class="GEMTRadioCol">
							<INPUT type="radio" 
							name="<%=jsfNamespace%>:form1:gemt_sum_roleclient" 
							id="<%=jsfNamespace%>:form1:gemt_sum_roleclient"
							value="4"
							<h:outputText escape="false" value="CHECKED" rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_roleclient==4}"/>></TD>
					</TR>
				
					<TR>
						<TD class="GEMTlrGreen"><h:outputText id="textPeople" value="#{bundle.gemt_gis_people}"/></TD>
						<TD></TD>
						<TD class="GEMTRadioCol">
							<INPUT type="radio" 
							name="<%=jsfNamespace%>:form1:gemt_sum_rolepeople" 
							id="gemt_sum_rolepeople" 
							value="1"
							<h:outputText escape="false" value="CHECKED" rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_rolepeople==1}"/>>
						</TD>
						<TD class="GEMTRadioCol">
							<INPUT type="radio" 
							name="<%=jsfNamespace%>:form1:gemt_sum_rolepeople" 
							id="gemt_sum_rolepeople" 
							value="2"
							<h:outputText escape="false" value="CHECKED" rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_rolepeople==2}"/>>
						</TD>
						<TD class="GEMTRadioCol">
							<INPUT type="radio" 
							name="<%=jsfNamespace%>:form1:gemt_sum_rolepeople" 
							id="gemt_sum_rolepeople" 
							value="3"
							<h:outputText escape="false" value="CHECKED" rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_rolepeople==3}"/>>
						</TD>
						<TD class="GEMTRadioCol">
							<INPUT type="radio" 
							name="<%=jsfNamespace%>:form1:gemt_sum_rolepeople" 
							id="gemt_sum_rolepeople" 
							value="4"
							<h:outputText escape="false" value="CHECKED" rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_rolepeople==4}"/>>
						</TD>
					</TR>
					<TR>
						<TD class="GEMTlrOrange"><h:outputText id="textThought" value="#{bundle.gemt_gis_thought}"/></TD>
						<TD>
						</TD>
						<TD class="GEMTRadioCol">
							<INPUT type="radio" 
							name="<%=jsfNamespace%>:form1:gemt_sum_rolethought" 
							id="gemt_sum_rolethought" 
							value="1"
							<h:outputText escape="false" value="CHECKED" rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_rolethought==1}"/>>
						</TD>
						<TD class="GEMTRadioCol">
							<INPUT type="radio" 
							name="<%=jsfNamespace%>:form1:gemt_sum_rolethought" 
							id="gemt_sum_rolethought" 
							value="2"
							<h:outputText escape="false" value="CHECKED" rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_rolethought==2}"/>>
						</TD>
						<TD class="GEMTRadioCol">
							<INPUT type="radio" 
							name="<%=jsfNamespace%>:form1:gemt_sum_rolethought" 
							id="gemt_sum_rolethought" 
							value="3"
							<h:outputText escape="false" value="CHECKED" rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_rolethought==3}"/>>
						</TD>
						<TD class="GEMTRadioCol">
							<INPUT type="radio" 
							name="<%=jsfNamespace%>:form1:gemt_sum_rolethought" 
							id="gemt_sum_rolethought" 
							value="4"
							<h:outputText escape="false" value="CHECKED" rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_rolethought==4}"/>>
						</TD>
					</TR>
					<TR>
						<TD class="GEMTlrRed"><h:outputText id="textDay2Day" value="#{bundle.gemt_gis_day2day}"/></TD>
						<TD>
						
						</TD>
						<TD class="GEMTRadioCol">
							<INPUT type="radio" 
							name="<%=jsfNamespace%>:form1:gemt_sum_roledaytoday" 
							id="gemt_sum_roledaytoday" 
							value="1"
							<h:outputText escape="false" value="CHECKED" rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_roledaytoday==1}"/>>
						</TD>
						<TD class="GEMTRadioCol">
							<INPUT type="radio" 
							name="<%=jsfNamespace%>:form1:gemt_sum_roledaytoday" 
							id="gemt_sum_roledaytoday" 
							value="2"
							<h:outputText escape="false" value="CHECKED" rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_roledaytoday==2}"/>>
						</TD>
						<TD class="GEMTRadioCol">
							<INPUT type="radio" 
							name="<%=jsfNamespace%>:form1:gemt_sum_roledaytoday" 
							id="gemt_sum_roledaytoday" 
							value="3"
							<h:outputText escape="false" value="CHECKED" rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_roledaytoday==3}"/>>
						</TD>
						<TD class="GEMTRadioCol">
							<INPUT type="radio" 
							name="<%=jsfNamespace%>:form1:gemt_sum_roledaytoday" 
							id="gemt_sum_roledaytoday" 
							value="4"
							<h:outputText escape="false" value="CHECKED" rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_roledaytoday==4}"/>>
						</TD>
					</TR>
					<TR>
						<TD colspan="5">&nbsp;</TD>
					</TR>
					<TR>
						<TD class="GEMTTitleCol"><h:outputText id="textResultsOverview" value="#{bundle.gemt_gis_overview}"></h:outputText></TD>
						<TD class="GEMTRatingCol">&nbsp;</TD>
						<TD class="GEMTRatingCol"><h:outputText	id="textBelowThresh" value="#{bundle.gemt_gis_belowthresh}"/></TD>
						<TD class="GEMTRatingCol"><h:outputText id="textThresh" value="#{bundle.gemt_gis_thresh}"/></TD>
						<TD class="GEMTRatingCol"><h:outputText id="textTarget" value="#{bundle.gemt_gis_target}"/></TD>
						<TD class="GEMTRatingCol"><h:outputText id="textOutstanding2" value="#{bundle.gemt_gis_outstand}"/></TD>
					</TR>
					<TR>
						<TD><h:outputText styleClass="GEMTPlainResults" id="textScoreCardFinancial" value="#{bundle.gemt_gis_scorefinancial}"/></TD>
						<TD></TD>
						<TD class="GEMTRadioCol">
							<INPUT type="radio" 
							name="<%=jsfNamespace%>:form1:gemt_sum_overviewscorefinan" 
							id="gemt_sum_overviewscorefinan" 
							value="1"
							<h:outputText escape="false" value="CHECKED" rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_overviewscorefinan==1}"/>>
						</TD>	
						<TD class="GEMTRadioCol">
							<INPUT type="radio" 
							name="<%=jsfNamespace%>:form1:gemt_sum_overviewscorefinan" 
							id="gemt_sum_overviewscorefinan" 
							value="2"
							<h:outputText escape="false" value="CHECKED" rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_overviewscorefinan==2}"/>>
						</TD>
						<TD class="GEMTRadioCol">
							<INPUT type="radio" 
							name="<%=jsfNamespace%>:form1:gemt_sum_overviewscorefinan" 
							id="gemt_sum_overviewscorefinan" 
							value="3"
							<h:outputText escape="false" value="CHECKED" rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_overviewscorefinan==3}"/>>
						</TD>
						<TD class="GEMTRadioCol">
							<INPUT type="radio" 
							name="<%=jsfNamespace%>:form1:gemt_sum_overviewscorefinan" 
							id="gemt_sum_overviewscorefinan" value="4"
							<h:outputText escape="false" value="CHECKED" rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_overviewscorefinan==4}"/>>
						</TD>
					</TR>
					<TR>
						<TD><h:outputText styleClass="GEMTPlainResults" id="textScoreCardKPI" value="#{bundle.gemt_gis_scorekpi}"/></TD>
						<TD></TD>
						<TD class="GEMTRadioCol">
							<INPUT type="radio" 
							name="<%=jsfNamespace%>:form1:gemt_sum_overviewscorekpi" 
							id="gemt_sum_overviewscorekpi" 
							value="1"
							<h:outputText escape="false" value="CHECKED" rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_overviewscorekpi==1}"/>>
						</TD>
						<TD class="GEMTRadioCol">
							<INPUT type="radio" 
							name="<%=jsfNamespace%>:form1:gemt_sum_overviewscorekpi" 
							id="gemt_sum_overviewscorekpi" 
							value="2"
							<h:outputText escape="false" value="CHECKED" rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_overviewscorekpi==2}"/>>
						</TD>
						<TD class="GEMTRadioCol">
							<INPUT type="radio" 
							name="<%=jsfNamespace%>:form1:gemt_sum_overviewscorekpi" 
							id="gemt_sum_overviewscorekpi" 
							value="3"
							<h:outputText escape="false" value="CHECKED" rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_overviewscorekpi==3}"/>>
						</TD>
						<TD class="GEMTRadioCol">
							<INPUT type="radio" 
							name="<%=jsfNamespace%>:form1:gemt_sum_overviewscorekpi" 
							id="gemt_sum_overviewscorekpi" 
							value="4"
							<h:outputText escape="false" value="CHECKED" rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_overviewscorekpi==4}"/>>
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
								<INPUT type="radio" 
								name="<%=jsfNamespace%>:form1:gemt_sum_overallperf" 
								value="1"
								<h:outputText escape="false" value="CHECKED" rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_overallperf==1}"/>>
							</TD>
							<TD class="GEMTRadioCol">
								<INPUT type="radio" 
								name="<%=jsfNamespace%>:form1:gemt_sum_overallperf" 
								value="2"
								<h:outputText escape="false" value="CHECKED" rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_overallperf==2}"/>>
							</TD>
							<TD class="GEMTRadioCol">
								<INPUT type="radio" 
								name="<%=jsfNamespace%>:form1:gemt_sum_overallperf" 
								value="3"
								<h:outputText escape="false" value="CHECKED" rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_overallperf==3}"/>>
							</TD>
							<TD class="GEMTRadioCol">
								<INPUT type="radio" 
								name="<%=jsfNamespace%>:form1:gemt_sum_overallperf" 
								value="4"
								<h:outputText escape="false" value="CHECKED" rendered="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_overallperf==4}"/>>
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
						<TD>
							<div style="border: 1px solid #908f8f; width:100%;" id="perfCommentsEditor">
								<textArea class="GEMTDefaultEditor"	dojoType="Editor" name="<%=jsfNamespace%>:form1:gemt_sum_comments" items="textGroup;|;justifyGroup;|;listGroup;">				       

			    					<span style="font-size:8pt;font-family:Arial;">

			    						<h:outputText escape="false" value="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_comments}" />
			    		
			    					</span>

				    			</textArea>
							</div>
						</TD>
					</TR>
				</TBODY>
			</TABLE>
			<br>
			
			<div class="GEMTOrangeTableContainer">
				<TABLE class="GEMTTable" border="0" cellpadding="0" cellspacing="0">
					<TBODY>
						<TR>
							<TD class="GEMTOrangeSectionTitleCol" colspan="4">
								<h:outputText id="textIndividualDev" value="#{bundle.gemt_gis_individualdev}"/>
							</TD>
						</TR>
						<TR>
							<TD width="25%" class="GEMTOrangeSectionResults" valign="middle">
								<h:outputText id="textDevStrengths" style="font-weight:bold;" value="#{bundle.gemt_gis_devstrengths}"/>
							</TD>
							<TD width="25%" style="padding-top:5px;">
								&nbsp;
							</TD>
							<TD width="25%" class="GEMTOrangeSectionResults" valign="middle">
							<h:outputText id="textDevNeeds" style="font-weight:bold;" value="#{bundle.gemt_gis_devneeds}"/>
							</TD>
							<TD width="25%" style="padding-top:5px;">
								&nbsp;
							</TD>
						</TR>
						<TR>
						<TD class="GEMTOrangeSectionResults" valign="top" style="padding-right:5px;width:50%;" colspan="2">
								<div style="border: 1px solid #908f8f; width:98%;" id="perfStrenghtsEditor">
									<textArea class="GEMTDefaultEditor" dojoType="Editor" items="textGroup;|;justifyGroup;|;listGroup;" id="<%=jsfNamespace%>:form1:gemt_sum_devstrengths" name="<%=jsfNamespace%>:form1:gemt_sum_devstrengths">				       	
						    			<span style="font-size:8pt;font-family:Arial;">
											<h:outputText value="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_devstrengths}" escape="false"/>
										</span>	
					    			</textArea>
								</div>
						</TD>
						<TD class="GEMTOrangeSectionResults" valign="top" style="padding-right:5px;width:50%;" colspan="2">
								<div style="border: 1px solid #908f8f; width:100%;" id="perfNeedsEditor">
									<textArea class="GEMTDefaultEditor" dojoType="Editor"  items="textGroup;|;justifyGroup;|;listGroup;" id="<%=jsfNamespace%>:form1:gemt_sum_devneeds" name="<%=jsfNamespace%>:form1:gemt_sum_devneeds" >				       
						    			<span style="font-size:8pt;font-family:Arial;">
											<h:outputText value="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_devneeds}" escape="false"/>
										</span>
									</textArea>
								</div>
							</TD>
						</TR>
						<TR>
						<TD class="GEMTOrangeSectionResults" valign="top" style="padding-right:5px;width:50%;" colspan="2" >
							<h:outputText id="text1" style="font-weight:bold;" value="#{bundle.gemt_gis_priorities}"></h:outputText>
							<div style="border: 1px solid #908f8f; width:98%;font-size:8pt;font-family:Arial;" id="perfPrioritiesEditor">
								<textArea class="GEMTDefaultEditor"  dojoType="Editor"  items="textGroup;|;justifyGroup;|;listGroup;" id="<%=jsfNamespace%>:form1:gemt_sum_devpriorities" name="<%=jsfNamespace%>:form1:gemt_sum_devpriorities" >				       
				    				<span style="font-size:8pt;font-family:Arial;">
										<h:outputText value="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_devpriorities}" escape="false"/>
									</span>
			    				</textArea>
							</div>
						</TD>
						<TD class="GEMTOrangeSectionResults" style="padding-bottom:10px;padding-right:5px;" colspan="2" valign="top" >
							<h:outputText id="textDevActions" style="font-weight:bold;" value="#{bundle.gemt_gis_devactions}"/>
							<div style="border: 1px solid #908f8f; width:100%;margin-top:5px;" id="perfActionsEditor" >
								<textArea class="GEMTDefaultEditor" dojoType="Editor" items="textGroup;|;justifyGroup;|;listGroup;"  id="<%=jsfNamespace%>:form1:gemt_sum_devactions" name="<%=jsfNamespace%>:form1:gemt_sum_devactions" >				       
				    					<span style="font-size:8pt;font-family:Arial;">
											<h:outputText value="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_devactions}" escape="false"/>
										</span>
				    			</textArea>
							</div>
						</TD>
						
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
						headerClass="GEMTOrangeTitleCol" border="0">
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
					<f:attribute name="style" value="border:0px;" />
				</h:column>
			<h:column>
			
				<f:facet name="header">
					<h:outputText value="Action"></h:outputText>
				</f:facet>
				<h:commandLink id="removeFile"
								value="Delete"
								action="#{pc_GEMTEntryFormReportEdit.removeFileAction}"
								actionListener="#{pc_GEMTEntryFormReportEdit.removeFile}"></h:commandLink>
			</h:column>
		
		</h:dataTable>
	
		<br/>
		<br>
			<TABLE class="GEMTTable" border="0" cellpadding="0" cellspacing="0">
				<TBODY>	
					<TR>
						<TD class="GEMTHeader" style="padding:5px;text-align:right;" colspan="2">
								
						<hx:commandExButton type="submit" value=" < Return to Main"
							 onmouseout="return #{facesContext.externalContext.response.namespace}_highlightButton(this, event);" onmouseover="return #{facesContext.externalContext.response.namespace}_highlightButton(this, event);"
							 styleClass="GEMTCommandButtonOrange" id="cancel2" action="#{pc_GEMTEntryFormReportEdit.doCancelAction}" ></hx:commandExButton>
						<hx:commandExButton type="submit" rendered="#{pc_GEMTEntryFormReportEdit.notFinalized}" value="#{pc_GEMTEntryFormReportEdit.routeButtonName}" id="route2" action="#{pc_GEMTEntryFormReportEdit.doRouteDocumentAction}" actionListener="#{pc_GEMTEntryFormReportEdit.doRouteDocument}"
							 onmouseout="return #{facesContext.externalContext.response.namespace}_highlightButton(this, event);" onmouseover="return #{facesContext.externalContext.response.namespace}_highlightButton(this, event);"
							 styleClass="GEMTCommandButtonOrange"></hx:commandExButton>
						<hx:commandExButton type="submit" value="Toggle View / Edit" id="toggle2"
							 onmouseout="return #{facesContext.externalContext.response.namespace}_highlightButton(this, event);" onmouseover="return #{facesContext.externalContext.response.namespace}_highlightButton(this, event);"
							 styleClass="GEMTCommandButtonOrange" action="#{pc_GEMTEntryFormReportEdit.doToggleAction}"></hx:commandExButton>
						
						<hx:commandExButton type="submit" value="Save as Draft"
							id="saveReport2"
							onmouseout="return #{facesContext.externalContext.response.namespace}_highlightButton(this, event);"
							onmouseover="return #{facesContext.externalContext.response.namespace}_highlightButton(this, event);"
							styleClass="GEMTCommandButtonOrange"
							action="#{pc_GEMTEntryFormReportEdit.doCreateRowTestAction}"></hx:commandExButton>
						
						</TD>	
					</TR>
					<TR>
						<TD style="background-color:#d47d18;">
							<hx:graphicImageEx id="imageEx3"  value="../images/blOrange.gif" style="display:block;"></hx:graphicImageEx>
						</TD>
						<TD style="background-color:#d47d18;text-align:right;">
							<hx:graphicImageEx id="imageEx4"  value="../images/brOrange.gif" style="display: block; margin: 0 0 0 auto;"></hx:graphicImageEx>
						</TD>
					</TR>
				</TBODY>
			</TABLE>
	
			<input type="hidden" name="formBase" id ="formBase" value="<%=form1Base%>">
			<h:inputText styleClass="inputText" id="id" value="#{sessionScope.GemtSummaryReportUIBean.id}" rendered="#{sessionScope.GemtSummaryReportUIBean.id!=null}" style="visibility:hidden;"></h:inputText>
			<!-- 
			Mgr Email:<h:inputText styleClass="inputText" id="gemt_sum_mgremail" value="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_mgremail}"/>
			-->
			<h:inputText id="gemt_sum_empemail" styleClass="inputText"
				value="#{sessionScope.GemtSummaryReportUIBean.gemt_sum_empemail}" style="visibility:hidden;"></h:inputText>
		</h:form>
		<h:form id="filesUploading" enctype="multipart/form-data" onsubmit="return validateCVSelection();"
				styleClass="" style="font-size:9.5pt;font-family:Arial;">
			<input type="file" name="uploadedFile" class="GEMTUploadButtonOrange"/>
			<hx:commandExButton type="submit" onmouseout="return #{facesContext.externalContext.response.namespace}_highlightButton(this, event);" onmouseover="return #{facesContext.externalContext.response.namespace}_highlightButton(this, event);"
								value="Upload" styleClass="GEMTCommandButtonOrange" id="uploadFile"/>
			<f:verbatim>
				<div id="cvJSErrorMsg"
					style="color: red; visibility: hidden; background-color: #ebebeb; border-bottom-width: 0px; border-left-width: 0px; border-right-width: 0px; border-top-width: 0px; border-width: 0px">
					Please Select a file.
				</div>
			</f:verbatim>
			<h:outputText value="#{sessionScope.UPLOAD_ERROR}"
					style="color: red; background-color: #ebebeb; border-bottom-width: 0px; border-left-width: 0px; border-right-width: 0px; border-top-width: 0px; border-width: 0px"></h:outputText>
				<%		
					renderRequest.getPortletSession().setAttribute(GEMTEntryForm.UPLOAD_ERROR,"");
			 	%>
		</h:form>				
		</hx:scriptCollector>
		</f:view>