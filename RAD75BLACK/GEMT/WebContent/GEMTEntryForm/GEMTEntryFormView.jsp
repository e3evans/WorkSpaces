<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/GEMTEntryForm/GEMTEntryFormView.java" --%><%-- /jsf:pagecode --%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://www.ibm.com/jsf/BrowserFramework" prefix="odc"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"
	import="com.manpower.portal.portlet.gemt.GEMTServlet"%>
<portlet:defineObjects />
<link rel="stylesheet" type="text/css"
	href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/stylesheet.css") %>'
	title="Style">
<link rel="stylesheet" type="text/css"
	href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/custStyle.css") %>'
	title="Style">
<f:view><%@page import="javax.faces.context.FacesContext"%>
	<%@page import="pagecode.GEMTEntryForm.GEMTEntryFormEdit"%>
	<script type="text/javascript"
		src='<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/jslib/dojo.js") %>'></script>
<script type="text/javascript"
		src='<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/jslib/global.js") %>'></script>
	<script>
	var lastSelected;
	var lastReport;
	var email='';
	var portletMode='<%= renderRequest.getPreferences().getValue(GEMTEntryFormEdit.PREF_MGREMPMODE, "0") %>';
	var activeTab='tab1';
	
	function getCheckedReports(fldName){
		
		var checkboxes = document.getElementsByName(fldName);
		var idString = '';
		for (var i=0;i<checkboxes.length;i++){
			if (checkboxes[i].checked){
				idString+=checkboxes[i].value+',';
			}
		}
		idString=idString.substring(0,idString.length-1);
		<portlet:namespace/>_loadHtml(idString,'<%=GEMTServlet.ACTION_QUICKVIEW_SELECTED%>');
	}
	
	function resetHistory(){
		var obj = document.getElementById('tab3');
		var eml='<h:outputText value="#{sessionScope.gemtentryformemail}" escape="false"/>';
		var action = '<%=GEMTServlet.ACTION_HISTORY%>';
		tabToggle(obj.id.substring(3),obj,action,eml);
	}
	
	function tabToggle(tabnum,obj,action,eml){
		activeTab='tab'+tabnum;
		email=eml;
		changeClass(obj,'GemtTab'+tabnum+'High');
		if (lastSelected!=obj){
			if (typeof lastSelected !='undefined'){
				var lastSelectedTabNum = lastSelected.id.substring(3);
				changeClass(lastSelected,'GemtTab'+lastSelectedTabNum);
			}
			lastSelected=obj;
		}else{
			lastSelected = obj;
		}
		<portlet:namespace/>_loadHtml('tabMain',action);
	}
	
	
	var form1SubmitId = 'view<portlet:namespace/>:form1';
	var storeSubmitId = 'submitId';
	var portletNamespace = '<portlet:namespace/>';
	var phantomButtonId='phantomButton';
	
	
	function submitJSFForm(action,reportId){
		if(document.getElementById) {
			var action = document.getElementById(form1SubmitId+":"+action);
			var field = document.getElementById(form1SubmitId+":"+storeSubmitId);
		} else if (document.all){
			var action = document.all[form1SubmitId+":"+action];
			var field = document.all[form1SubmitId+":"+storeSubmitId];
		}
		if (action!=null){	
			field.value=reportId;
			action.click();
		}
	}

	function deleteReport(action,reportId)
	{
		if(confirm("Are you sure?"))
		{
			submitJSFForm(action,reportId);
		}
	}
	
	function getAjaxData(id,action){

		var workingDiv = document.getElementById(id+"_summaryAreaDiv");

		if (lastReport==workingDiv && workingDiv.innerHTML!=""){
			workingDiv.innerHTML="";
		}else{
			
			if (typeof lastReport !='undefined'){
				if (lastReport.id.indexOf('tabMain')==-1){
					lastReport.innerHTML="";
				}
				lastReport=workingDiv;
			}else{
				lastReport=workingDiv;
			}
			
			<portlet:namespace/>_loadHtml(id,action);
		}
	}
	/* Use Dojo.io.bind to asynchronously get invoice content */
	function <portlet:namespace/>_loadHtml(id,action) {
		if (id != null && id != "null") {
			/*Put the working .gif up in the display area*/
			var workingGif = '<img src="';
			workingGif+='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/images/ajax-loader.gif") %>"';
			workingGif+=' style="align:left;">';
			var workingDiv = document.getElementById(id+"_summaryAreaDiv");
			if (typeof workingDiv =='undefined' || workingDiv==null){
				workingDiv=document.getElementById("tabMain_summaryAreaDiv");
			}
			workingDiv.innerHTML = "";
			workingDiv.innerHTML=workingGif;
			
			/* Put selectd invoice value on query string */
			var querystring = new Array();
			querystring['<%=GEMTServlet.PARAM_ID%>'] = id;
			querystring['<%=GEMTServlet.PARAM_ACTION%>']=action;
			querystring['<%=GEMTServlet.PARAM_EMAIL%>']=email;
			querystring['<%=GEMTServlet.PARAM_PORTLET_MODE%>']=portletMode;
			querystring['<%=GEMTServlet.SESS_ACTIVETAB%>']=activeTab;
			/* Set up bind arguments: */
			/* url:		url for request */
			/* method:	http method */
			/* content:	key/value mapping of parameters sent with request */
			/* handle:	function to run when there is a response */
			/* mimetype:mimetype of response */
			
			var bindArgs = {
				url: "<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/GEMTServlet")%>",
				method: "POST",
				content: querystring,
				handle: function(type, data, evt) {
					if (type == "error") {
						/* Swap returned error message */
						data = "<p style='color:red'>" + data.message + "</p>";
					}
					/* Swap returned data into div tag */
					//var div = document.getElementById(id+"_summaryAreaDiv");
					workingDiv.innerHTML = "";
					workingDiv.innerHTML = data;
				},
				mimetype: "text/html"
				
			};
			dojo.io.bind(bindArgs);
		}
		return "";
	};
	
	function getDirectReports(id,emplEmail,action){
		//this sets global email value to selected employee email
		email=emplEmail;
		
		var workingDiv = document.getElementById(id+"_summaryAreaDiv");

		if(workingDiv.innerHTML!="")
		{
			workingDiv.innerHTML="";
		}
		else
		{
			<portlet:namespace/>_loadHtml(id,action);
		}
	}
	
	function <portlet:namespace/>phantom_button(thisObj, thisEvent) {
		//use 'thisObj' to refer directly to this component instead of keyword 'this'
		//use 'thisEvent' to refer to the event generated instead of keyword 'event'
		if (thisObj.value!=""){
			var tabId = thisObj.value;
			var tabpos = tabId.substring(3);
			var elem = document.getElementById(tabId);
			changeClass(elem,'GEMTTab'+tabpos+'High');
			if (lastSelected!=elem){
				var lasttabpos = lastSelected.id.substring(3);
				if (typeof lastSelected !='undefined'){
					changeClass(lastSelected,'GemtTab'+lasttabpos);
				}
			}
			lastSelected = elem;
		}
		
	}
	
		function <portlet:namespace/>func_2(thisObj, thisEvent) {
			//use 'thisObj' to refer directly to this component instead of keyword 'this'
			//use 'thisEvent' to refer to the event generated instead of keyword 'event'
			changeClass(thisObj,'GEMTCommandButtonDarkOrange');
		
		}
		function <portlet:namespace/>func_3(thisObj, thisEvent) {
			//use 'thisObj' to refer directly to this component instead of keyword 'this'
			//use 'thisEvent' to refer to the event generated instead of keyword 'event'
			changeClass(thisObj,'GEMTCommandButtonOrange');
		
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
</script>
	<hx:scriptCollector id="scriptCollector1" preRender="#{pc_GEMTEntryFormView.onPageLoadBegin}">
	
		<h:form styleClass="form" id="form1">
		<div id="<portlet:namespace/>_main">
			
		
			
			<script>
			
			function getCurrentColumValue(obj,colid){
				var temp_array = obj.id.split(':');
				var columnId = ""
				for (var i = 0;i < temp_array.length-1;i++){
					columnId +=temp_array[i]+":";
				}
				columnId+=colid;
				return document.getElementById(columnId).innerHTML;
			}

			function <portlet:namespace/>func_1(thisObj, thisEvent) {
				//use 'thisObj' to refer directly to this component instead of keyword 'this'
				//use 'thisEvent' to refer to the event generated instead of keyword 'event'
				//alert(thisObj.id);
				var reportId = getCurrentColumValue(thisObj,'reportId');
				<portlet:namespace/>_loadHtml(reportId,'test');
				//alert(temp);
				return false;
			
			}	
				
			</script>
		

		
			<h:messages styleClass="messages" id="messages1"></h:messages>
			
			<TABLE cellpadding="0" cellspacing="0" width="100%">
				<TR>
					<TD style="background-color:#d47d18;">
						<hx:graphicImageEx id="imageEx2"  value="../images/tlOrange.gif" style="display:block;"></hx:graphicImageEx>
					</TD>
					<TD style="background-color:#d47d18;color:#FFFFFF;font-size:8pt;font-weight:bold;text-align:center;">
					</TD>
					<TD style="background-color:#d47d18;text-align:right;">
						<hx:graphicImageEx id="imageEx1"  value="../images/trOrange.gif" style="display: block; margin: 0 0 0 auto;"></hx:graphicImageEx>
					</TD>
				</TR>
				<TR>
					<TD colspan="2"  style="background-color:#d47d18;color:#FFFFFF;font-size:8pt;font-weight:bold;text-align:left;padding-left:10px;">
						<h:outputText id="text3" rendered="#{sessionScope.gemtentryformportletmode==2}" value="MY REVIEWS"/>
						<h:outputText id="text4" rendered="#{sessionScope.gemtentryformportletmode==1}" value="MY DIRECT REPORTS"/>
					</TD>
					<TD style="height:26px;padding-bottom:3px;text-align:right;background-color:#d47d18;color:#FFFFFF;font-size:8pt;font-weight:bold;text-align:right;padding-right:10px;" >
						
					</TD>
				</TR>
				<TR style="border-left:1px solid #d47d18;">
					
					<!--  <TD width="25%" id="tab1" onclick="tabToggle('1',this,'<%=GEMTServlet.ACTION_INPROGRESS%>','<h:outputText value="#{sessionScope.gemtentryformemail}" escape="false"/>');" class="GemtTab1High" style="cursor:pointer;">
						In Progress
					</TD> -->
					<TD width="25%" id="tab1" onclick="tabToggle('1',this,'<%=GEMTServlet.ACTION_MIDYEAR%>','<h:outputText value="#{sessionScope.gemtentryformemail}" escape="false"/>');" class="GemtTab1High" style="cursor:pointer;">
						Mid-Year
					</TD>
					<TD width="25%" id="tab2" onclick="tabToggle('2',this,'<%=GEMTServlet.ACTION_ANNUAL%>','<h:outputText value="#{sessionScope.gemtentryformemail}" escape="false"/>');" class="GemtTab2" style="cursor:pointer;">
						Annual
					</TD>
					<TD width="25%" id="tab3" onclick="tabToggle('3',this,'<%=GEMTServlet.ACTION_HISTORY%>','<h:outputText value="#{sessionScope.gemtentryformemail}" escape="false"/>');" class="GemtTab3" style="cursor:pointer;">
						History
					</TD>
				</TR>
				<TR>
					<TD colspan="3" id="tabMain_summaryAreaDiv" style="font-size:8pt;font-weight:normal;text-align:left;border-right:1px solid #d47d18;border-left:1px solid #d47d18;padding-top:10px;padding-bottom:10px;">
					<h:outputText id="text2" escape="false" value="#{sessionScope.gemtentryformreportlist}"></h:outputText>
					<h:outputText id="text1" styleClass="outputText" rendered="#{sessionScope.gemtentryformreportlist==null}" escape="false" value="&nbsp;"></h:outputText>	 
					</TD>
				
				</TR>
				<TR>
					<TD style="background-color:#d47d18;">
						<hx:graphicImageEx id="imageEx3" styleClass="graphicImageEx" value="../images/blOrange.gif" style="display:block;"></hx:graphicImageEx>
					</TD>
					<TD	style="background-color:#d47d18;color:#FFFFFF;font-size:8pt;font-weight:bold;text-align:center;">
					
					</TD>
					<TD style="background-color:#d47d18;text-align:right;">
						<hx:graphicImageEx id="imageEx4" styleClass="graphicImageEx" value="../images/brOrange.gif" style="display: block; margin: 0 0 0 auto;"></hx:graphicImageEx>
					</TD>
				</TR>
			</TABLE>

			<hx:commandExButton type="submit" value="viewReports"
				id="viewReports" action="#{pc_GEMTEntryFormView.doViewReportsAction}" style="visibility:hidden;">
				<f:param name="test" id="param2" value="test"></f:param>
			</hx:commandExButton>
			<hx:commandExButton type="submit" value="deleteReport"
				id="deleteReport" actionListener="#{pc_GEMTEntryFormView.doDeleteReport}" style="visibility:hidden;">
				<f:param name="test" id="param3" value="test"></f:param>
			</hx:commandExButton>			
			<h:inputText id="submitId" style="visibility:hidden;"></h:inputText>
			<br>
			<SCRIPT>
				lastSelected=document.getElementById('tab1');
			</SCRIPT>
			<hx:commandExButton type="button"
				value="#{sessionScope.gemtentryformactivetab}" id="phantomButton"
				styleClass="commandExButton" onclick="return #{facesContext.externalContext.response.namespace}phantom_button(this, event);" style="visibility:hidden;"></hx:commandExButton>
			<script>
				document.getElementById(form1SubmitId+":"+phantomButtonId).click();
			</script>
			</div>
			
			<TABLE cellpadding="0" cellspacing="0" width="100%">
				<TR>
					<TD style="background-color:#d47d18;">
						<hx:graphicImageEx value="../images/tlOrange.gif" style="display:block;"></hx:graphicImageEx>
					</TD>
					<TD style="background-color:#d47d18;color:#FFFFFF;font-size:8pt;font-weight:bold;text-align:center;">
					</TD>
					<TD style="background-color:#d47d18;text-align:right;">
						<hx:graphicImageEx value="../images/trOrange.gif" style="display: block; margin: 0 0 0 auto;"></hx:graphicImageEx>
					</TD>
				</TR>
				<TR>
					<TD colspan="3"  style="background-color:#d47d18;color:#FFFFFF;font-size:12pt;font-weight:bold;text-align:left;padding:10px;">
						Manage My Evaluations
					</TD>
				</TR>
				
				<TR>
					<TD colspan="3" id="tabMain_summaryAreaDiv" style="font-size:8pt;font-weight:normal;text-align:left;border-right:1px solid #d47d18;border-left:1px solid #d47d18;padding-top:10px;padding-bottom:10px;padding-left:5px;">
						
						<table>
							<tr>
								<td style="font-size:10pt;color:#c8504f;font-weight:bold;">
									<h:outputText id="txtViewEdit" value="View or Edit my Evaluations: "/>
								</td>
								<td valign="middle" style="padding-left:15px;">
									<hx:requestLink id="createMidYear" styleClass="mpThemeCommandLink" action="#{pc_GEMTEntryFormView.showMidYearReport}" rendered="#{sessionScope.gemtentryformportletmode==2}">
										<h:outputText id="text6" styleClass="outputText" value="Mid Year"/>
									</hx:requestLink>
									&nbsp;&nbsp;&nbsp;
									<hx:requestLink id="createAnnual" styleClass="mpThemeCommandLink" action="#{pc_GEMTEntryFormView.showAnnualReport}" rendered="#{sessionScope.gemtentryformportletmode==2}">
										<h:outputText id="text5" styleClass="outputText" value="Annual"/>
									</hx:requestLink>
									&nbsp;&nbsp;&nbsp;
									<h:commandLink id="link1" styleClass="mpThemeCommandLink">
										<h:outputText id="text7" styleClass="outputText" value="History"/>
									</h:commandLink>
								</td>
							</tr>
							<tr>
								<td colspan="2">
								<br>
								</td>
							</tr>
							<tr>
								<td style="font-size:10pt;color:#6698c2;font-weight:bold;">
									<h:outputText id="txtUserInfo" value="Edit my User Information:  "></h:outputText>
								</td>
								<td>
								</td>
							</tr>
						</table>
						<br>
						
					</TD>
				</TR>
				<TR>
					<TD style="background-color:#d47d18;">
						<hx:graphicImageEx styleClass="graphicImageEx" value="../images/blOrange.gif" style="display:block;"></hx:graphicImageEx>
					</TD>
					<TD	style="background-color:#d47d18;color:#FFFFFF;font-size:8pt;font-weight:bold;text-align:center;">
					
					</TD>
					<TD style="background-color:#d47d18;text-align:right;">
						<hx:graphicImageEx styleClass="graphicImageEx" value="../images/brOrange.gif" style="display: block; margin: 0 0 0 auto;"></hx:graphicImageEx>
					</TD>
				</TR>
			</TABLE>
		</h:form>
	</hx:scriptCollector>
</f:view>