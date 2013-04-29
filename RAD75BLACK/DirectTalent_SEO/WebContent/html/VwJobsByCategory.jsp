<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><%@page
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.util.ResourceBundle,java.util.List"%>
<%@page import="com.manpower.directtalentseo.hbn.shared.dao.DAOFactory"%>
<%@page import="com.manpower.directtalentseo.hbn.beans.VAdsIndSecList"%>
<%
	String [] sitename = request.getPathInfo().substring(1).split("/");
	String linkPrefix = request.getContextPath()+"/Results/"+sitename[0];
	ResourceBundle siteRb = ResourceBundle.getBundle("manpower.countrylangs."+sitename[0]);
	ResourceBundle transRb = ResourceBundle.getBundle("manpower.translations.JobCategory");
	List categoryList = DAOFactory.getDAOFactory().getVAdsIndSecDAO().findUniqueCategoryList(sitename[0],request.getLocale().toString());
	int listItems = 25;
	double numOfColumns = Math.ceil(Double.parseDouble(Integer.toString(categoryList.size()))/Double.parseDouble(Integer.toString(listItems)));
	int columns = (int)numOfColumns;
	int count = 0;
	
 %>



<%@page import="java.net.URLEncoder"%><html>
<head>
	<title><%=siteRb.getString("sitetitle")%> / <%=transRb.getString("viewjobsbycat") %></title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<LINK rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/styles.css" title="Style">
</head>
<body style="margin:auto;">
	<table>
		<tr>
			<td align="left" style="padding-left:20px;">
				<img src="<%=request.getContextPath() %>/images/mp_logo.gif" alt="Manpower Logo" width="87" height="76">
			</td>
		</tr>
	</table>
	<table cellpadding="0" cellspacing="0" >
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
			<h3><%=transRb.getString("viewjobsbycat") %></h3>
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
				<%for(int i = 0;i<categoryList.size();i++){ 
					VAdsIndSecList listBean = (VAdsIndSecList)categoryList.get(i);
					
					if (i==0){
						%><td valign="top" NOWRAP><%
					}else if (count == listItems-1){
						count=0;
						%></td><td valign="top" NOWRAP><%
					}
					
				%>
				
						<a href="<%=response.encodeURL(linkPrefix+"?categoryname="+URLEncoder.encode(listBean.getIndustry_sector(),"UTF-8")+"&pagenum=1&lang="+listBean.getLanguage()) %>" class="bluelinks">- <%=listBean.getIndustry_sector() %></a><br><br>
				
				<% 	if (i==categoryList.size()-1){
						%></td><%
					}
					count++;
				}%>
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