<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><%@page
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ResourceBundle,java.util.List"%>
<%@page import="com.manpower.directtalentseo.hbn.shared.dao.DAOFactory"%>
<%@page import="com.manpower.directtalentseo.hbn.beans.VAdsIndSecList"%>
<%
	request.setCharacterEncoding("UTF-8");
	int jobsPerPage = 25;
	String sitename = request.getPathInfo().substring(1);
	ResourceBundle siteRb = ResourceBundle.getBundle("manpower.countrylangs."+sitename);
	ResourceBundle transRb = ResourceBundle.getBundle("manpower.translations.JobCategory");
	List resultsList = DAOFactory.getDAOFactory().getVActiveAdvertsDAO()
	.findaAllBySiteNameAndCategory(sitename,request.getParameter("categoryname"),jobsPerPage,Integer.parseInt(request.getParameter("pagenum")));
	VAdsIndSec adinfo=null; 
	if (resultsList.size()>0){
		adinfo=(VAdsIndSec)resultsList.get(0);
	}
//	int listItems = 5;
	String DATE_FORMAT = "MM/dd/yyyy"; 
	SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	String pageNumUrl = request.getRequestURI()+"?"+request.getQueryString().substring(0,request.getQueryString().length()-1);
	pageNumUrl=pageNumUrl.substring(0,pageNumUrl.indexOf("pagenum=")+8); 
	String pagerComp = HtmlUtility.getPager(Integer.parseInt(Long.toString(adinfo.getQ_total())),jobsPerPage,Integer.parseInt(request.getParameter("pagenum")),pageNumUrl);
	String jobdescLink = request.getContextPath()+"/Job/"+sitename+"?";
 %>
<%@page import="com.manpower.directtalentseo.hbn.beans.VAdsIndSec"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.manpower.directtalentseo.utility.PageUtils"%>
<%@page import="com.manpower.directtalentseo.utility.HtmlUtility"%>
<%@page import="java.net.URLEncoder"%><html>
<head>
	<meta http-equiv="Content-Type" content="text/html; UTF-8">
	<title><%=siteRb.getString("sitetitle")%> / <%=transRb.getString("viewjobsbycat") %> / <%=request.getParameter("categoryname") %> </title>
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
				<h3><%=transRb.getString("viewResults") %> <%=request.getParameter("categoryname")%></h3>
			</td>
			<td class="rightSpanTopCenter">
			
			</td>
		
		</tr>
		<tr>
			<td class="leftSpanMiddle">
			</td>
			<td class="centerSpanMiddle" align="right">
				<%=pagerComp %>
				<%for (int i=0;i<resultsList.size();i++){
					adinfo = (VAdsIndSec)resultsList.get(i); 
				 %>
				 <table border="0" cellpadding="5" cellspacing="2" width="100%" class="resultstable">
					<tr>
						<td colspan="3">
							<a href="<%=jobdescLink%>jobtitle=<%=URLEncoder.encode(adinfo.getJobtitle(),"UTF-8")+"&"%>adid=<%=adinfo.getId() %>" class="jobtitlelink"><%=adinfo.getJobtitle() %></a>
						</td>
					</tr>
					<tr>
						<td valign="top">
							<%=transRb.getString("salaryinfo") %>
						</td>
						<td valign="top">
						
							<%=PageUtils.checkforNulls(adinfo.getPayrange()) %>
						
						</td>
						<td rowspan="3" valign="top" style="font-style:italic;">
							<%
								String tempDesc = adinfo.getJobDescByWord(50);
								String more = "viewjob";
								if (tempDesc.length()>0){
									if (tempDesc.substring(tempDesc.length()-3).equals("..."))more="more";
								}
							 %>
							<%=tempDesc%>
							<a href="<%=jobdescLink%>jobtitle=<%=URLEncoder.encode(adinfo.getJobtitle(),"UTF-8")+"&adid="+adinfo.getId()+"&lang="+adinfo.getLanguage()%>" class="jobtitlemorelink">
								<%=transRb.getString(more) %>
							</a>
						</td>
					</tr>
					<tr>
						<td valign="top">
							<%=transRb.getString("location") %>
						</td>
						<td valign="top">
							<%=PageUtils.checkforNulls(adinfo.getLocation()) %>
						</td>
					</tr>
					<tr>
						<td valign="top">
							<%=transRb.getString("jobtype") %>
						</td>
						<td valign="top">
							<%=adinfo.getContracttype() %>
						</td>
					</tr>
					
					<tr>
						<td valign="top">
							<%=transRb.getString("dateposted") %>
						</td>
						<td valign="top">
							<%=sdf.format(adinfo.getPublicationdate()) %>
						</td>
					</tr>
				</table>
				 
			
				<hr class="jobseperator"/>
				<%
					//if (i==listItems-1)break;
				} %>
			</td>
			<td class="rightSpanMiddle">
			
			</td>
		</tr>
		<tr>
			<td class="leftSpanBottomCenter">
			</td>
			<td class="centerSpanBottomCenter">
				<%=pagerComp %>
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