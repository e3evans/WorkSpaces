<%-- jsf:pagecode language="java" location="/src/pagecode/WcmViewer/WcmViewerEdit.java" --%><%-- /jsf:pagecode --%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@page language="java" contentType="text/html"
	pageEncoding="ISO-8859-1" session="false"
	import="com.manpower.portal.portlet.wcmviewer.WcmViewerServlet"%>
<portlet:defineObjects />
<link rel="stylesheet" type="text/css"
	href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/stylesheet.css") %>'
	title="Style">
<link rel="stylesheet" type="text/css"
	href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/custstylesheet.css") %>'
	title="Style">
<f:view><script type="text/javascript"
		src='<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/jslib/dojo.js") %>'></script>
	<script type="text/javascript"
		src='<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/jslib/global.js") %>'></script>
	<script>
	
	var selectedContent;
	var selectedType;
	var selectedContentId;
	
	function changeContentClass (Elem, myClass){
		if (selectedContent!=Elem){
			changeClass(Elem, myClass);
		}
	}
	
	function wcmViewerCallAjax(id,type){
		
		<portlet:namespace/>_loadHtml(id,type);
	}
	function wcmViewerSelectContent(elem,id,type){
		if (selectedContent!=null && selectedContent!=elem){
			changeClass(selectedContent,'redTableRow');
			changeClass(elem,'redTableRowHighlighted');
		}
		selectedContent=elem;
		selectedType=type;
		selectedContentId=id;
		
		
		
		<portlet:namespace/>_loadHtml(id,type);
	}
	/* Use Dojo.io.bind to asynchronously get invoice content */
	function <portlet:namespace/>_loadHtml(id,type) {
	
	if (id != null && id != "null") {
		/*Put the working .gif up in the display area*/
		var workingGif = '<img src="';
		workingGif+='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/images/ajax-loader.gif") %>"';
		workingGif+=' style="align:left;">';
		
		if (type.indexOf('WCM_Content')>-1){
			
			var workingDiv = document.getElementById("preview_summaryAreaDiv");
		} else {
			var workingDiv = document.getElementById(id+"_summaryAreaDiv");
		}
		
		if (typeof workingDiv =='undefined' || workingDiv==null){
			workingDiv=document.getElementById("tabMain_summaryAreaDiv");
		}
	
		if (workingDiv.innerHTML!=""){
			workingDiv.innerHTML = "";
			if (type.indexOf('WCM_Content')<0)return;
		}else{
			workingDiv.innerHTML = "";
			workingDiv.innerHTML=workingGif;
		}
		
		
		/* Put selectd invoice value on query string */
		var querystring = new Array();
		querystring['<%=WcmViewerServlet.PARAM_SITE_ID%>'] = id;
		querystring['<%=WcmViewerServlet.PARAM_SITE_TYPE%>']=type;
		//querystring['test3']="myTEST3";
		/* Set up bind arguments: */
		/* url:		url for request */
		/* method:	http method */
		/* content:	key/value mapping of parameters sent with request */
		/* handle:	function to run when there is a response */
		/* mimetype:mimetype of response */
		
		var bindArgs = {
			url: "<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/WcmViewerServlet")%>",
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


	function <portlet:namespace/>savePrefs(thisObj, thisEvent) {
		//use 'thisObj' to refer directly to this component instead of keyword 'this'
		//use 'thisEvent' to refer to the event generated instead of keyword 'event'
		setSavedPrefField(thisObj,'dispalyContent',selectedContentId);
	}
	
	function <portlet:namespace/>func_1(thisObj, thisEvent) {
		//use 'thisObj' to refer directly to this component instead of keyword 'this'
		//use 'thisEvent' to refer to the event generated instead of keyword 'event'
		changeClass(thisObj, 'CommandButtonDarkOrange');
	}
	function <portlet:namespace/>func_2(thisObj, thisEvent) {
		//use 'thisObj' to refer directly to this component instead of keyword 'this'
		//use 'thisEvent' to refer to the event generated instead of keyword 'event'
		changeClass(thisObj, 'CommandButtonOrange');
	}
	</script>
	<hx:scriptCollector id="scriptCollector1" preRender="#{pc_WcmViewerEdit.onPageLoadBegin}">
	
		
		<br>
		<h:form id="formsavePrefs" styleClass="form">
		
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tbody>
					<tr>
						<td class="orangeTableRow" NOWRAP>
							<b>Currently Displayed Content:</b>  <h:outputText value="#{sessionScope.com_manpower_portal_portlet_wcmviewer_expandlist}" escape="false"/>
						</td>
						<td class="orangeTableRow">
							<h:inputText id="dispalyContent" styleClass="inputText" value="#{sessionScope.com_manpower_portal_portlet_wcmviewer_storedcontent}" style="visibility:hidden;"></h:inputText>
						</td>
						<td class="orangeTableRow" align="right">
							<hx:commandExButton type="submit" value="Return to View" id="returnToView"
							styleClass="CommandButtonOrange" onmouseover="return #{facesContext.externalContext.response.namespace}func_1(this, event);" onmouseout="return #{facesContext.externalContext.response.namespace}func_2(this, event);" action="#{pc_WcmViewerEdit.doReturnToViewAction}"/>
							<hx:commandExButton type="submit" value="Save Preferences"
							id="savePreferences" styleClass="CommandButtonOrange"
							onclick="return #{facesContext.externalContext.response.namespace}savePrefs(this, event);"
							action="#{pc_WcmViewerEdit.doSavePreferencesAction}" onmouseover="return #{facesContext.externalContext.response.namespace}func_1(this, event);" onmouseout="return #{facesContext.externalContext.response.namespace}func_2(this, event);"/>
						</td>
					</tr>
					<tr>	
						<td colspan="3">
						<h:outputText id="text1" value="#{sessionScope.com_manpower_portal_portlet_wcmviewer_siteareas}" escape="false"/>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tbody>
									<tr>
										<td class="orangeTableHeader">
											Content Preview Area
										</td>
									</tr>
									<tr>
										<td id="preview_summaryAreaDiv" class="orangeTableDisplayArea">
											<h:outputText value="#{sessionScope.com_manpower_portal_portlet_wcmviewer_storedcontentpreview}" escape="false"/>
										</td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>
				</tbody>
			</table>
			
		</h:form>


		

	</hx:scriptCollector>
	
</f:view>