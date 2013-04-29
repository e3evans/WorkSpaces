<%-- jsf:pagecode language="java" location="/src/pagecode/AjaxMenus/AjaxMenusView.java" --%><%-- /jsf:pagecode --%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<portlet:defineObjects />
<head>
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
</head>
	
<f:view><hx:scriptCollector id="scriptCollector1" preRender="#{pc_AjaxMenusViewNew.onPageLoadBegin}">
		<h:form styleClass="form" id="form1">
		
		<table cellspacing="0" cellpadding="0" border="0" width="100%">
			<tr>
				<td>
					<div id="greyBoxOutside"><!-- f0f0f0 -->
						<div id="greyBoxInside">
							<h:outputText value="#{requestScope.displayArea}" escape="false"></h:outputText>
							<div style="clear: both;"></div>
						</div>
						<div style="clear: both;"></div>
					</div>
				</td>
			</tr>
		</table>

	
		</h:form>
	</hx:scriptCollector>
</f:view>