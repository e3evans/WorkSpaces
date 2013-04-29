<%-- jsf:pagecode language="java" location="/src/pagecode/DTWidgetReportingPortlet/DTWidgetReportingPortletView.java" --%><%-- /jsf:pagecode --%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>

<%@page language="java" contentType="text/html"
	pageEncoding="ISO-8859-1"
	import="com.manpower.directtalent.rss.ui.service.FeedServiceLocator"
	session="false"%>
<portlet:defineObjects/>
<f:view>
	<hx:scriptCollector id="scriptCollector1">
		<link rel="stylesheet" type="text/css"
			href='<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/stylesheet.css") %>'
			title="Style">
		<style>
		.norm{font-size:x-small;font-family:Arial;}
		.highlighted{background-color:#ebebeb;font-size:x-small;font-family:Arial;}
		.menuOff{vertical-align:middle;text-align:left;border:1px solid #73a190;font-family:Arial;font-size:x-small;padding-left:10px;padding-right:10px;padding-top:3px;text-align:left;cursor:pointer;}
		.menuOn{vertical-align:middle;text-align:left;padding-top:3px;background-color:#73a190;font-family:Arial;font-size:x-small;padding-left:10px;padding-right:10px;color:#FFFFFF;font-weight:normal;cursor:pointer;}
		.headerBar{text-align:center;padding-left:10px;padding-right:10px;background-color:#7ea190;font-family:Arial;font-weight:bold;font-size:x-small;color:#FFFFFF;}
	</style>
		<script type="text/javascript"
			src='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/jslib/bfograph.js")%>'></script>
		<script type="text/javascript"
			src='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/jslib/dojo.js")%>'></script>
		<script type="text/javascript"
			src='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/jslib/global.js")%>'></script>

		<script>


var serverKey = '';


function <portlet:namespace/>loadHtml(id,action,reportName) {
	try {
    netscape.security.PrivilegeManager.enablePrivilege("UniversalBrowserRead");
   } catch (e) {
    //alert("Permission UniversalBrowserRead denied.");
   }
		
			if (id != null && id != "null") {
				/*Put the working .gif up in the display area*/
				var workingGif = '<img src="';
				workingGif+='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/images/ajax-loader.gif")%>"';
				workingGif+=' style="align:left;">';
				var workingDiv = document.getElementById('<portlet:namespace/>managerdisplay');
				workingDiv.innerHTML = "";
				workingDiv.innerHTML=workingGif;
				
				/* Put selectd invoice value on query string */
				var querystring = new Array();
				
				querystring['reportId'] = reportName;
				querystring['siteName']=action;
				querystring['serverKey']=serverKey;
				querystring['test4']='blah2';
				querystring['test5']='blah3';
				
				//querystring['']=email; 
				/* Set up bind arguments: */
				/* url:		url for request */
				/* method:	http method */
				/* content:	key/value mapping of parameters sent with request */
				/* handle:	function to run when there is a response */
				/* mimetype:mimetype of response */
				var bindArgs = {
					url: "<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/ReportServlet")%>",
					method: "GET",
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
		
		
		var activeMenu; 
		var activeBranch;
		var menuOpen=false;
		activeMenu=document.getElementById('candCounts');
		function <portlet:namespace/>switchReport(action,reportName){
			if (activeMenu!=action){
				changeClass(activeMenu,'menuOff');
				changeClass(action,'menuOn');
				activeMenu=action;
				<portlet:namespace/>loadHtml(action.id,'test',reportName);
				if (menuOpen){
					<portlet:namespace/>displayMenu('displayMenuRow','siteList');
				}
			}
			
		}
		
		function <portlet:namespace/>showBranchReport(action,siteName){
			//changeClass(activeMenu,'menuOff');
			//activeMenu = document.getElementById('adsPerBranch');
			
			changeClass(action,'menuOn');
			if (activeBranch!=null){
				changeClass(activeBranch,'menuOff');
			}
			activeBranch=action;
			
			<portlet:namespace/>displayMenu('displayMenuRow','siteList');
			activeMenu=document.getElementById('<portlet:namespace/>adsPerBranch');
			<portlet:namespace/>loadHtml(action.id,siteName,action.id);
		
		}
				function highLight(col){
			if (col.className=='menuOff' && col != activeBranch){
				changeClass(col,'menuOn');
			}else if (col.className=='menuOn' && col != activeBranch){
				changeClass(col,'menuOff' );
			}
		}

		
	
		function <portlet:namespace/>findPos(obj) {
			
			var curleft = curtop = 0;
			
			if (obj.offsetParent) {
				curleft = obj.offsetLeft
				curtop = obj.offsetTop
				while (obj = obj.offsetParent) {
					curleft += obj.offsetLeft
					curtop += obj.offsetTop
				}
			}
			return [curleft,curtop];
		}
		
		function <portlet:namespace/>displayMenu(startObjId,menuId){
			var obj = document.getElementById(startObjId);
			var expImage = document.getElementById('expander');

			if (expImage.src.indexOf('closed')>0){
				expImage.src = '<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/images/open.gif")%>';
			}else{
				expImage.src = '<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/images/closed.gif")%>';
			}
			<portlet:namespace/>showMenu(obj,menuId);
		}
		
		function <portlet:namespace/>showMenu(startObj,menuId){
			var pos = <portlet:namespace/>findPos(startObj);
			
			var menu = document.getElementById(menuId);
			
			if (menu.style.left == '-500px'){
				menu.style.left=pos[0];
				menu.style.top=pos[1];
				menuOpen = true;
			}else{
				menu.style.left=-500;
				menuOpen = false;
			}
			
		}
	
		
</script>
		<%=FeedServiceLocator.getInstance().getSiteSelectorService().getSiteSelector(renderResponse.getNamespace())%>
		<table cellpadding="0" cellspacing="0">
			<tr>
				<td rowspan="5" style="border:1px solid #7ea190;" valign="top">
				<div id="<portlet:namespace/>managerdisplay"></div>
				</td>
				<td valign="top">
				<table cellpadding="0" cellspacing="0">
					<tr>
						<td align="left" class="menuOn"
							id="<portlet:namespace/>candCounts"
							onclick="<portlet:namespace/>switchReport(this,'candCounts')">
						Candidate Counts <a href="javascript:window.location.href=document.getElementById('view<portlet:namespace/>:ghostLink').href"><img border="0"
							style="border:1px solid white;"
							src='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/images/graphMe.gif")%>'
							title="Graph It!" alt="Graph It!"></a></td>
					</tr>
					<tr>
						<td class="menuOff" id="<portlet:namespace/>adCounts"
							onclick="<portlet:namespace/>switchReport(this,'adCounts')">Advertisement
						Counts</td>
					</tr>
					<tr>
						<td class="menuOff" id="<portlet:namespace/>adsThisWeek"
							onclick="<portlet:namespace/>switchReport(this,'adsThisWeek')">Ads
						Created This Week</td>
					</tr>
					<tr>
						<td class="menuOff" id="<portlet:namespace/>adsPerBranch"
							style="padding-top:3px;"
							onclick="<portlet:namespace/>displayMenu('displayMenuRow','siteList');">Ads
						Per Branch <img id="expander"
							src='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/images/closed.gif")%>'></td>
					</tr>
					<tr>
						<td class="menuOff" id="<portlet:namespace/>regLevelPerSite"
							style="padding-top:3px;"
							onclick="javascript:window.location.href=document.getElementById('view<portlet:namespace/>:ghostRegLink').href">Registration
						Levels</td>
					</tr>
					<tr>
						<td id="displayMenuRow"></td>
					</tr>
					<tr>
						<td align="center" style="padding-top:25px;"><img
							src='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/images/mp_logo.gif")%>'>
						</td>
					</tr>

				</table>
				</td>
			</tr>

		</table>

		<hx:requestLink id="ghostLink" styleClass="requestLink"
			action="#{pc_DTWidgetReportingPortletView.doGhostLinkAction}" style="visibility:hidden;">
			<h:outputText id="text1" styleClass="outputText" value="GhostLink"></h:outputText>
		</hx:requestLink>
		<hx:requestLink id="ghostRegLink" styleClass="requestLink" 
			action="#{pc_DTWidgetReportingPortletView.doGhostRegLinkAction}" style="visibility:hidden;">
			<h:outputText id="text2" styleClass="outputText" value="GhostRegLink"></h:outputText>
		</hx:requestLink>
		<script>
			<portlet:namespace/>loadHtml('candCounts','test','candCounts');
			activeMenu = document.getElementById("<portlet:namespace/>candCounts");
		</script>


		<link rel="stylesheet" type="text/css"
			href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/stylesheet.css") %>'
			title="Style">
	</hx:scriptCollector>
</f:view>
