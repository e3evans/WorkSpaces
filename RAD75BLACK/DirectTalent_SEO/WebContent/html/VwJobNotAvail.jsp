<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><%@page
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.util.ResourceBundle,java.util.List"%>
<%@page import="com.manpower.directtalentseo.hbn.shared.dao.DAOFactory"%>
<%@page import="com.manpower.directtalentseo.hbn.beans.VAdsIndSecList"%>
<%
	String sitename = request.getParameter("sitename");
	//String linkPrefix = request.getContextPath()+"/Results/"+sitename[0];
	ResourceBundle siteRb = ResourceBundle.getBundle("manpower.countrylangs."+sitename);
	ResourceBundle transRb = ResourceBundle.getBundle("manpower.translations.JobCategory");
	//List categoryList = DAOFactory.getDAOFactory().getVAdsIndSecDAO().findUniqueCategoryList(sitename[0],request.getLocale().toString());
	//int listItems = 25;
	//double numOfColumns = Math.ceil(Double.parseDouble(Integer.toString(categoryList.size()))/Double.parseDouble(Integer.toString(listItems)));
	//int columns = (int)numOfColumns;
	//int count = 0;
	
 %>



<%@page import="java.net.URLEncoder"%><html>
<head>
	<title><%=siteRb.getString("sitetitle")%> / </title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<LINK rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/styles.css" title="Style">
	<style>
		.whitelinkul{
			color:#FFFFFF;
			text-decoration:underline;
			font-family:Helvetica,Verdana,Arial;
			font-size:12px;
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
			<br>
			<a href="#" class="whitelinkul"><%=transRb.getString("searchmanpowerjobscaps") %></a>
			<br style="font-size:12px;">
			<h3><%=transRb.getString("itemna") %></h3>
		</td>
		<td class="rightSpanTopCenter">
		
		</td>
	
	</tr>
	<tr>
		<td class="leftSpanMiddle">
		</td>
		<td class="centerSpanMiddle">
			<table cellpadding="5" cellspacing="5" border="0">
					<tr>
						<td>
							<br>
							<br>
							<br>
							<b><%=transRb.getString("tofindmorejobs") %>&nbsp;</b><a href="#" style="text-decoration:underline;" class="jobtitlelink"><%=transRb.getString("searchmanpowerjobslc") %></a>
							<br>
							<br>
							<br>
							<br>
							<br>
							<br>
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
		<td class="centerSpanBottomCenter">
			
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