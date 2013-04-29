<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/GEMTManager/GEMTManagerView.java" --%><%-- /jsf:pagecode --%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@page language="java" contentType="text/html"
	pageEncoding="ISO-8859-1" session="false"
	import="com.manpower.portal.portlet.gemt.GEMTManagerServlet"%>
<portlet:defineObjects />
<link rel="stylesheet" type="text/css"
	href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/stylesheet.css") %>'
	title="Style">
<link rel="stylesheet" type="text/css"
	href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/custStyle.css") %>'
	title="Style">
<f:view><script type="text/javascript"
		src='<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/jslib/dojo.js") %>'></script>
	<script type="text/javascript"
		src='<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/jslib/global.js") %>'></script>
	<script type="text/javascript">
		function <portlet:namespace/>mouseover(thisObj) {
			changeClass(thisObj,'GEMTCommandButtonDarkOrange');
		
		}
		function <portlet:namespace/>mouseout(thisObj) {
			changeClass(thisObj,'GEMTCommandButtonOrange');
		}
		//initialize the array so other functions don't error out.  
		//this only get's set when a filter is applied.
		var <portlet:namespace/>selects = '';
		function <portlet:namespace/>addFilter(){
			//Clear the selects variable....
			<portlet:namespace/>selects = '';
			var filterselector;
			var selectedArray = new Array();
			if (document.all){
				filterselector = document.all.filterbyregion;
			}else{
				filterselector = document.getElementById('filterbyregion');
			}
			//Call getSelected from the global.js
			selectedArray = getSelected(filterselector);
			for (var i=0;i<selectedArray.length;i++){
				<portlet:namespace/>selects +=selectedArray[i];
				if (i<selectedArray.length-1)<portlet:namespace/>selects+=',';
			}
			<portlet:namespace/>_loadHtml('1','<%=GEMTManagerServlet.ACTION_FILTERUSERS%>')
		}
		function <portlet:namespace/>switchManager(thisObj, thisEvent) {
		//use 'thisObj' to refer directly to this component instead of keyword 'this'
		//use 'thisEvent' to refer to the event generated instead of keyword 'event'
		//Call AJAX function to change the display area of the view managers section...
			<portlet:namespace/>_loadHtml(thisObj.options[thisObj.selectedIndex].value,'<%=GEMTManagerServlet.ACTION_VIEWMANAGERS%>');
		}
		var <portlet:namespace/>selectedusers = '';
		function <portlet:namespace/>addUser(){
			var boxes;
			selectedusers='';
			if (document.all){
				boxes = document.all.checkbox_addusers;
			
			}else{
				boxes = document.getElementsByName('checkbox_addusers');
				
			}
				var boxvals='';
				for (var i = 0;i<boxes.length;i++){
					if (boxes[i].checked){
						boxvals+=boxes[i].value+',';
					}
				}
			if (boxvals.length>0){
				
				<portlet:namespace/>selectedusers=boxvals.substring(0,boxvals.length-1);
				<portlet:namespace/>_loadHtml('0','<%=GEMTManagerServlet.ACTION_ADDUSER%>');
				
			}else{
		
				alert('Please select a user to continue.');
			}
			
		}
		/* Use Dojo.io.bind to asynchronously get invoice content */
		function <portlet:namespace/>_loadHtml(id,action) {
		
		
			if (id != null && id != "null") {
				/*Put the working .gif up in the display area*/
				var workingGif = '<img src="';
				workingGif+='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/images/ajax-loader.gif") %>"';
				workingGif+=' style="align:left;">';
				var workingDiv = document.getElementById('<portlet:namespace/>_managerdisplay');
				workingDiv.innerHTML = "";
				workingDiv.innerHTML=workingGif;
				
				/* Put selectd invoice value on query string */
				var querystring = new Array();
				
				querystring['<%=GEMTManagerServlet.PARAM_ID%>'] = id;
				querystring['<%=GEMTManagerServlet.PARAM_ACTION%>']=action;
				querystring['<%=GEMTManagerServlet.PARAM_NAMESPACE%>']='<portlet:namespace/>';
				querystring['<%=GEMTManagerServlet.PARAM_SELECTED_REGION%>']=<portlet:namespace/>selects;
				querystring['<%=GEMTManagerServlet.PARAM_SELECTED_USERS%>']=<portlet:namespace/>selectedusers;
				
				//querystring['']=email; 
				/* Set up bind arguments: */
				/* url:		url for request */
				/* method:	http method */
				/* content:	key/value mapping of parameters sent with request */
				/* handle:	function to run when there is a response */
				/* mimetype:mimetype of response */
				
				var bindArgs = {
					url: "<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/GEMTManagerServlet")%>",
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
		function <portlet:namespace/>phantomButton(actionId){
			var formId = 'view<portlet:namespace/>:gemt_direct_reports';
			if(document.getElementById) {
				var action = document.getElementById(formId+":"+actionId);
			} else if (document.all){
				var action = document.all[formId+":"+actionId];
			}
			if (action!=null){	
				action.click();
			}
		}
	</script>
<hx:scriptCollector id="scriptCollector1" preRender="#{pc_GEMTManagerView.onPageLoadBegin}">
		<h:form id="gemt_direct_reports" styleClass="form">
		<TABLE cellpadding="0" cellspacing="0" width="100%">
			<TR>
				<TD width="100%">
				Select a manager to see their direct reports:  
				<h:selectOneMenu id="gemt_managers_list" styleClass="GEMTSelector"
						onchange="return #{facesContext.externalContext.response.namespace}switchManager(this, event);"
						value="#{sessionScope.com_manpower_portal_portlet_gemt_gemtmanager_selectedmanager}">
						<f:selectItems
							value="#{sessionScope.com_manpower_portal_portlet_gemtmanager_managerlist}" />
					</h:selectOneMenu>
					
				</TD>
			</TR>
			<TR>
				<TD>
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
								<TD colspan="3" id="<portlet:namespace/>_managerdisplay" style="border:1px solid #d47d18;">
								<h:outputText
									value="#{sessionScope.com_manpower_portal_portlet_gemtmanager_managerdisplay}"
									escape="false" />
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
				</TD>
			</TR>
		</TABLE>
			
			<hx:commandExButton type="submit" value="addUsers" id="addUsers"
				styleClass="commandExButton" action="#{pc_GEMTManagerView.doAddUsersAction}" style="visibility:hidden;"></hx:commandExButton>
		</h:form>
	</hx:scriptCollector>
</f:view>