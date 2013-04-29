<%-- jsf:pagecode language="java" location="/src/pagecode/DTWidgetReportingPortlet/DTWidgetReportingRegLevel.java" --%><%-- /jsf:pagecode --%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://big.faceless.org/products/graph" prefix="bfg" %>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"
	import="com.manpower.directalent.rss.hbn.beans.SitesBean,com.manpower.directtalent.rss.ui.service.FeedServiceLocator,com.manpower.directalent.rss.hbn.beans.CandByRegLevelBean"%>
<portlet:defineObjects></portlet:defineObjects>
<link rel="stylesheet" type="text/css"
		href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/stylesheet.css") %>'
		title="Style">
<script type="text/javascript"
		src='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/jslib/bfograph.js")%>'></script>
<script type="text/javascript"
		src='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/jslib/global.js")%>'></script>
<style>
	.norm{font-size:x-small;font-family:Arial;}
	.highlighted{background-color:#ebebeb;font-size:x-small;font-family:Arial;}
	.menuOff{vertical-align:middle;text-align:left;border:1px solid #73a190;font-family:Arial;font-size:x-small;padding-left:10px;padding-right:10px;padding-top:3px;text-align:left;cursor:pointer;}
	.menuOn{vertical-align:middle;text-align:left;padding-top:3px;background-color:#73a190;font-family:Arial;font-size:x-small;padding-left:10px;padding-right:10px;color:#FFFFFF;font-weight:normal;cursor:pointer;}
	.headerBar{text-align:center;padding-left:10px;padding-right:10px;background-color:#7ea190;font-family:Arial;font-weight:bold;font-size:x-small;color:#FFFFFF;}
</style>

<f:view>
<hx:scriptCollector id="scriptCollector1" preRender="#{pc_DTWidgetReportingRegLevel.onPageLoadBegin}">
	
		<%

	String siteName="";
	String siteId="";
	if (request.getAttribute("siteId")!=null){
		siteId=request.getAttribute("siteId").toString();
	}
	if (request.getAttribute("siteName")!=null){
		siteName=request.getAttribute("siteName").toString();
		
	}else if(siteName.equals("")){
		SitesBean sb = (SitesBean)FeedServiceLocator.getInstance().getSiteSelectorService().getDefaultItem();
		siteName=sb.getSitename();
		siteId=Long.toString(sb.getSite_id());
	}
	
	//SitesBean sb = (SitesBean)FeedServiceLocator.getInstance().getSiteSelectorService().getDefaultItem();
	//siteName=sb.getSitename();
	//siteId=Long.toString(sb.getSite_id());
	Object [] regLevelListList = FeedServiceLocator.getInstance().getCandidateByRegLevelService().getCandidatesBySite(siteName);
	//Object [] siteSelectorList = FeedServiceLocator.getInstance().getSiteSelectorService().getSites();
%>
<%=FeedServiceLocator.getInstance().getSiteSelectorService().getSiteSelector(renderResponse.getNamespace())%>
<script>
	var activeBranch;
	
	function <portlet:namespace/>showBranchReport(action,siteName){
			changeClass(action,'menuOn');
			if (activeBranch!=null){
				<portlet:namespace/>changeClass(activeBranch,'menuOff');
			}
			activeBranch=action;
			//<portlet:namespace/>displayMenu('displayMenuRow','siteList');
			//activeMenu=document.getElementById('adsPerBranch');
			//var newLoc = 'reglevel.jsp?serverKey=&siteName='+siteName+'&siteid='+action.id;
			//window.location.href=newLoc;
			document.getElementById('view<portlet:namespace/>:form1:sitename').value=siteName;
			document.getElementById('view<portlet:namespace/>:form1:siteid').value=action.id;
			document.getElementById('view<portlet:namespace/>:form1:ghostRegLevelButton').click();
		
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
<h:form id="form1" styleClass="form">
<table width="550">
	<tr>
		<td class="menuOn" id="adsPerBranch"  style="padding-top:3px;" onclick="<portlet:namespace/>displayMenu('displayMenuRow','siteList');">Registration Level by Country <img id="expander" src="<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/images/closed.gif")%>"></td>
	</tr>
	<tr>
		<td id="displayMenuRow"></td>
	</tr>
	<tr>
		<td style="border:dotted 2px #73a190;padding:10px;">
			<bfg:axesgraph width="300" height="200" yrotation="30" xrotation="20" border="0" defaultcolors="#5f81aa, #a24447, #7ea190, #c8504f" >
	  			<bfg:label font="12pt bold Verdana" paddingbottom="5">Registration by Level for <%=siteName%></bfg:label>
	  			<bfg:barseries name="Fruit" barwidth="80%">
	  			<%
	  				for (int i =0; i<regLevelListList.length;i++){
	  				CandByRegLevelBean cbrlb = (CandByRegLevelBean) regLevelListList[i];
	  				String showLabel = "Level "+cbrlb.getReg_level()+" = "+cbrlb.getCount().toString();
	  				String xAxis = "Level "+cbrlb.getReg_level();
	  				if (cbrlb.getId().toString().equals("1")){
	  			 %>
 					<bfg:data color="#5f81aa" title="<%=showLabel%>" onmousemove="bfgShowPopup(this.title, event)" onmouseout="bfgHidePopup()" x="<%=xAxis %>" y="<%=cbrlb.getCount().toString()%>"/>
	  				
	  				<%}
	  				if (cbrlb.getId().toString().equals("2")){	
	  			 %>
	  				<bfg:data color="#6698c2" title="<%=showLabel%>" onmousemove="bfgShowPopup(this.title, event)" onmouseout="bfgHidePopup()" x="<%=xAxis %>" y="<%=cbrlb.getCount().toString()%>"/>
	  			<%}
	  				if (cbrlb.getId().toString().equals("3")){
	  			 %>
	  				<bfg:data color="#7ea190" title="<%=showLabel%>" onmousemove="bfgShowPopup(this.title, event)" onmouseout="bfgHidePopup()" x="<%=xAxis %>" y="<%=cbrlb.getCount().toString()%>"/>
	  				
	  			<%}
	  				if (cbrlb.getId().toString().equals("4")){
	  			 %>
	  			 	<bfg:data color="#c8504f" title="<%=showLabel%>" onmousemove="bfgShowPopup(this.title, event)" onmouseout="bfgHidePopup()" x="<%=xAxis %>" y="<%=cbrlb.getCount().toString()%>"/>
	  				
	  			<%} %>
	  				

	    			<%} %>
	  			</bfg:barseries>
			</bfg:axesgraph>
			
			
			
		</td>
		<td valign="top" align="center">
			<img src="<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/images/mp_logo.gif") %>">
			<br>
			<hx:requestLink id="link1" styleClass="requestLink" action="#{pc_DTWidgetReportingRegLevel.doLink1Action}">
						<h:outputText id="text1" styleClass="outputText"
							value=">>>back" escape="false"></h:outputText>
					</hx:requestLink>
		</td>
	</tr>
</table>

			
			<h:inputText id="sitename" styleClass="inputText" style="visibility:hidden;"></h:inputText>
			<h:inputText id="siteid" styleClass="inputText" style="visibility:hidden;"></h:inputText>
			
			<hx:commandExButton style="visibility:hidden;" type="submit" value="Submit" id="ghostRegLevelButton"
				styleClass="commandExButton" action="#{pc_DTWidgetReportingRegLevel.doButton1Action}"></hx:commandExButton>


		</h:form>
</hx:scriptCollector>
</f:view>