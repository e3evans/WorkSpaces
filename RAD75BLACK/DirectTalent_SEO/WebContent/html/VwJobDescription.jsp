<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><%@page
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.util.ResourceBundle,java.util.List"%>
<%@page import="com.manpower.directtalentseo.hbn.shared.dao.DAOFactory"%>
<%@page import="java.net.URLEncoder"%>
<%
	String sitename = request.getPathInfo().substring(1);
	
	ResourceBundle siteRb = ResourceBundle.getBundle("manpower.countrylangs."+sitename); 
	ResourceBundle transRb = ResourceBundle.getBundle("manpower.translations.JobCategory");
	String viewjoblink = "http://candidate.manpower.com";
	
	try{
		viewjoblink = siteRb.getString("viewjoblink");	
	}catch (Exception e){
		//DO NOTHING  JOB LINK HASN'T BEEN ADDED TO RESOURCE YET....
	}
	VAdSingle adinfo = null;
	adinfo=(VAdSingle)DAOFactory.getDAOFactory().getVActiveAdvertsDAO().findAdvertByIdLang(new Long(request.getParameter("adid")),request.getParameter("lang"));
	if (adinfo == null) response.sendRedirect(request.getContextPath()+"/html/VwJobNotAvail.jsp?sitename="+sitename);
 %>
<%@page import="com.manpower.directtalentseo.hbn.beans.VAdSingle"%>
<%@page import="com.manpower.directtalentseo.utility.PageUtils"%><html>
<head>
	<title><%=siteRb.getString("sitetitle")%> / <%=adinfo.getJobtitle() %></title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<LINK rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/styles.css" title="Style">
	<style>
	.resultstable td{
		font-family:Helvetica,Verdana,Arial;
		color:#5b6d7b;
		font-size:11px;
	}
	</style>
</head>
<body style="margin:auto;">
	<table>
		<tr>
			<td align="left" style="padding-left:20px;">
				<img src="<%=request.getContextPath() %>/images/mp_logo.gif" alt="Manpower Logo" width="87" height="76">
			</td>
		</tr>
	</table>
	<table cellpadding="0" cellspacing="0" width="796" >
	<tr>
		<td class="leftSpanTop">
		</td>
		<td class="centerSpanTop">

		</td>
		<td class="rightSpanTop">
		</td>
	</tr>
	<tr>
		<td class="leftSpanTopCenter">
		
		</td>
		<td class="centerSpanTopCenter">
			<br style="font-size:12px;">
			<h3><%=adinfo.getJobtitle() %><br><%=adinfo.getReferencenumber() %></h3>
		</td>
		<td class="rightSpanTopCenter">
		
		</td>
	
	</tr>
	<tr>
		<td class="leftSpanMiddle">
		</td>
		<td class="centerSpanMiddle">
			<table cellpadding="5" cellspacing="5"  class="resultstable">
				<tr>
					<td>
						<%=transRb.getString("location") %>
					</td>
					<td>
						<%=PageUtils.checkforNulls(adinfo.getLocation()) %>
					</td>
				</tr>
				<tr>
					<td>
						<%=transRb.getString("salary") %>
					</td>
					<td>
						<%=PageUtils.checkforNulls(adinfo.getPayrange()) %>
					</td>
				</tr>
				<tr>
					<td>
						<%=transRb.getString("occupationtype") %>
					</td>
					<td>
						<%=PageUtils.checkforNulls(adinfo.getIndustry_sector()) %>
					</td>
				</tr>
				<tr>
					<td>
						<%=transRb.getString("advertid") %>
					</td>
					<td>
						<%=PageUtils.checkforNulls(adinfo.getReferencenumber()) %>
					</td>
				</tr>
				<tr>
					<td>
						<%=transRb.getString("positiontype") %>
					</td>
					<td>
						<%=PageUtils.checkforNulls(adinfo.getContracttype()) %>
					</td>
				</tr>
			</table>
			<br>
			<table class="resultstable">
				<tr>
					<td class="jobtitlelink">
						<%=transRb.getString("jobdesc") %>
					</td>
				</tr>
				<tr>
					<td>
						<%=adinfo.getJobdescription() %>
						<br><br>
					</td>
				</tr>
				<tr>
					<td class="jobtitlelink">
						<%=transRb.getString("candskills") %>
					</td>
				</tr>
				<tr>
					<td>
						<%=adinfo.getCandidateskills() %>
						<br><br>
					</td>
				</tr>
				<tr>
					<td class="jobtitlelink">
						<%=transRb.getString("branchinfo") %>
					</td>
				</tr>
				<tr>
					<td>
						<%=PageUtils.checkforNulls(adinfo.getBranch_address1()) %>
						<%="<br>"+PageUtils.checkforNulls(adinfo.getBranch_address2()) %>
						<%="<br>"+PageUtils.checkforNulls(adinfo.getBranch_city())+", "+PageUtils.checkforNulls(adinfo.getBranch_state())+" "+PageUtils.checkforNulls(adinfo.getBranch_zipcode()) %>
					</td>
				</tr>
			</table>
		</td>
		<td class="rightSpanMiddle">
		
		</td>
	</tr>
	<tr>
		<td class="leftSpanBottomCenter">
		</td>
		<td class="centerSpanBottomCenter" align="center">
			<table width="100%">
			<tr>
			<td align="right">
			<table cellpadding="0" cellspacing="0" >
					<tr>
						<td width="16">
							<img src="<%=request.getContextPath() %>/images/button_03.jpg" width="16" height="26" alt="">
						</td>
						<td class="buttonMiddle" onclick="window.location.href='<%=viewjoblink%>/wps/PA_DirectTalentJobApp/ViewJobAdvertisement?site=<%=sitename %>&JobId=<%=adinfo.getId() %>'">
							<%=transRb.getString("applynow") %>
						</td>
						<td width="15">
							<img src="<%=request.getContextPath() %>/images/button_06.jpg" width="15" height="26" alt="">
						</td>
					</tr>
				
				</table>
			</td>
			</tr>
			</table>
		</td>
		<td class="rightSpanBottomCenter"></td>
	</tr>
	<tr>
		<td class="leftSpanBottom">
		</td>
		<td class="centerSpanBottom">
		</td>
		<td class="rightSpanBottom">
		</td>
	</tr>

</table>
	


</body>
</html>