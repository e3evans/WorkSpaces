<%-- jsf:pagecode language="java" location="/src/pagecode/DTWidgetReportingPortlet/DTWidgetReportingTop5.java" --%><%-- /jsf:pagecode --%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://big.faceless.org/products/graph" prefix="bfg"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"
	import="com.manpower.directtalent.rss.ui.service.FeedServiceLocator,com.manpower.directalent.rss.hbn.beans.CandidateCountBean"%>
<portlet:defineObjects />
<link rel="stylesheet" type="text/css"
	href='<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/stylesheet.css")%>'
	title="Style">
<link rel="stylesheet" type="text/css"
	href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/stylesheet.css") %>'
	title="Style">
<f:view><script type="text/javascript"
		src='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/jslib/bfograph.js")%>'>
</script>
	<%
Object [] tObjList = FeedServiceLocator.getInstance().getCandidateReportService().getTopFiveSites();
%>
	<hx:scriptCollector id="scriptCollector1">
		<table width="550">
			<tr>
				<td><bfg:piegraph border="0" height="350" width="350"
					yrotation="-10" xrotation="-20"
					defaultcolors="#5f81aa, #a24447, #7ea190, #c8504f, #d47c18, #878684">
					<bfg:label font="12pt bold Verdana" paddingbottom="5">Top 5 Sites by Candidate Registration</bfg:label>

					<%for (int i=0;i<tObjList.length;i++){ 
					CandidateCountBean ccb = (CandidateCountBean)tObjList[i];
					String temp = ccb.getId()+" = "+Long.toString(ccb.getCount());
					%>
					<bfg:data key="<%=ccb.getId()%>"
						value="<%=Long.toString(ccb.getCount())%>" extend="10%"
						title="<%=temp%>"
						onmousemove="bfgShowPopup(this.title, event)"
						onmouseout="bfgHidePopup()">
						<bfg:label font="10pt bold Verdana" distance="1.2">
							<%=Long.toString(ccb.getCount())%>
						</bfg:label>
					</bfg:data>

					<%} %>

					<bfg:key align="bottom" bordercolor="black" />
				</bfg:piegraph></td>
				<td valign="top" align="center"><img
					src='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/images/mp_logo.gif") %>'>
				<br>
				<hx:requestLink id="link1" styleClass="requestLink" action="#{pc_DTWidgetReportingTop5.doLink1Action}">
					<h:outputText id="text1" styleClass="outputText"
						value="&lt;&lt;&lt;back" escape="false"></h:outputText>
				</hx:requestLink>
				</td>
			</tr>
		</table>

	</hx:scriptCollector>
</f:view>