<%-- jsf:pagecode language="java" location="/src/pagecode/AjaxMenus/AjaxMenusEdit.java" --%><%-- /jsf:pagecode --%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html"
	pageEncoding="ISO-8859-1" session="false"%>
<portlet:defineObjects />
<link rel="stylesheet" type="text/css" href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/stylesheet.css") %>' title="Style">
<link rel="stylesheet" href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/custStyles.css") %>' type="text/css">
<style>
	#greyBoxOutside {
		background: #f0f0f0 url('<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/images/boxgrey_bottomcurve.gif")%>') no-repeat left bottom; /*width: 873px;*/
		/*border: 1px solid red;*/
	}
	#greyBoxInside {
		background: url('<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/images/boxgrey_topcurve.gif")%>') no-repeat left top; 
		width: 100%; 
		padding-bottom: 5px;
		font-size: 10px;
		color: #999999;
		line-height: 14px;
		padding: 19px 22px 30px 22px;
		/*border: 1px solid green;*/
		width: 100%;
	}
	#greyBoxInside h6 {
		font-size: 10px;
		color: #666666;
		margin: 0 0 5px 0;
		padding: 0;
		line-height: 14px;
	}
	
	.footerList {
		
		margin-right: 22px;
		float: left;
	}
	#greyBoxInside h6 {
		font-size: 10px;
		color: #666666;
		margin: 0 0 5px 0;
		padding: 0;
		line-height: 14px;
	}

</style>

<script type="text/javascript">
	function <portlet:namespace/>func_1(thisObj, thisEvent) {
		window.open (thisObj.value, "MANPOWER","location=1,status=1,scrollbars=1,width=800,height=600");
	}
</script>
<f:view><hx:scriptCollector id="scriptCollector1" preRender="#{pc_AjaxMenusEdit.onPageLoadBegin}">
		<h:form styleClass="form" id="form1">
			
			<table width="100%">
				<tr>
					<td colspan="2">
						<font style="color:red;">To be selectable a unique name MUST be defined!</font><hr>
						<h:outputText styleClass="outputText" id="text1" value="#{sessionScope.breadcrumbs}"></h:outputText>
						<hx:requestLink styleClass="clearCrumbsLink" id="link1" action="#{pc_AjaxMenusEdit.doLink1Action}">
						<h:outputText id="text2" styleClass="outputText" value="(Reset)"></h:outputText>
					</hx:requestLink>
					</td>
				</tr>
				<tr>
					<td valign="top">
						<h:selectManyListbox styleClass="selectbox" id="lev1List">
						<f:selectItems value="#{sessionScope.topLevelList}" />
					</h:selectManyListbox>
					</td>
					<td style="padding-left:20px;" valign="top" align="left">
					<hx:dataTableEx id="tableEx1" value="#{sessionScope.storedPrefs}"
						var="varstoredPrefs" styleClass="dataTableEx"
						headerClass="headerClass" footerClass="footerClass"
						rowClasses="rowClass1, rowClass2" columnClasses="columnClass1"
						border="0" cellpadding="2" cellspacing="0">
						<hx:columnEx id="columnEx1">
							<f:facet name="header">
								<h:outputText styleClass="outputText" value="Sequence"
									id="text3"></h:outputText>
							</f:facet>
							<h:outputText id="textSequence1"
								value="#{varstoredPrefs.sequence}" styleClass="outputText">
							</h:outputText>
						</hx:columnEx>
						<hx:columnEx id="columnEx2">
							<f:facet name="header">
								<h:outputText styleClass="outputText" value="DisplayName"
									id="text4"></h:outputText>
							</f:facet>
							<h:outputText id="textDisplayName1"
								value="#{varstoredPrefs.displayName}" styleClass="outputText">
							</h:outputText>
						</hx:columnEx>
						<hx:columnEx id="columnEx3">
							<f:facet name="header">
								<h:outputText styleClass="outputText" value="Uniqueid"
									id="text5"></h:outputText>
							</f:facet>
							<h:outputText id="textUniqueid1"
								value="#{varstoredPrefs.uniqueid}" styleClass="outputText">
							</h:outputText>
						</hx:columnEx>
						<hx:columnEx id="columnEx4">
							<f:facet name="header">
								<h:outputText value="Action" styleClass="outputText" id="text6"></h:outputText>
							</f:facet>
							<hx:commandExButton type="submit" value="Delete"
								styleClass="commandExButton" id="deleteRow" style="width:75px;" action="#{pc_AjaxMenusEdit.doDeleteRowAction}"></hx:commandExButton>
							<hx:commandExButton type="submit" value="Up"
								styleClass="commandExButton" id="moveUp"  style="width:75px;" rendered="#{varstoredPrefs.sequence!='0'}" action="#{pc_AjaxMenusEdit.doMoveUpAction}"></hx:commandExButton>
							<hx:commandExButton type="submit" value="Down"
								styleClass="commandExButton" id="moveDown" style="width:75px;" rendered="#{varstoredPrefs.sequence!=sessionScope.lastentry}" action="#{pc_AjaxMenusEdit.doMoveDownAction}"></hx:commandExButton>
						
						</hx:columnEx>
					</hx:dataTableEx>
					<hr>
					Hide if unique name contains: <h:inputText styleClass="inputText" id="hideword" value="#{sessionScope.hideword}"></h:inputText><br>
					<!-- Show dropdown box: <h:selectBooleanCheckbox	styleClass="selectBooleanCheckbox" id="hidedropdown" value="#{sessionScope.showdropdown}"></h:selectBooleanCheckbox>
					<br><hx:commandExButton type="submit" value="Update Portlet Settings"
							styleClass="commandExButton" id="acceptButton" action="#{pc_AjaxMenusEdit.doAcceptButtonAction}"></hx:commandExButton>-->
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<hx:commandExButton type="submit" value="Drill" styleClass="commandExButton" id="button1"
							action="#{pc_AjaxMenusEdit.doButton1Action}"></hx:commandExButton>
						<hx:commandExButton type="submit" value="Update Preferences"
						styleClass="commandExButton" id="updatePreferences" action="#{pc_AjaxMenusEdit.doUpdatePreferencesAction}"></hx:commandExButton>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<hr>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						PREVIEW AREA<hr>
					</td>
				</tr>
				<tr>
					<td colspan="3">
					
						<div id="greyBoxOutside"><!-- f0f0f0 -->
						<div id="greyBoxInside">
							<h:outputText value="#{sessionScope.previewArea}" escape="false"></h:outputText>
							<div style="clear: both;"></div>
						</div>
						<div style="clear: both;"></div>
					</div>
					
					
					</td>
				</tr>
			</table>
			
			<br>
	
		</h:form>

	</hx:scriptCollector>
</f:view>