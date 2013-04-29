<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.manpower.directtalentseo.hbn.beans.VAdsIndSec"%>
<%@page import="com.manpower.directtalentseo.hbn.shared.dao.DAOFactory"%>
<%@page import="java.text.SimpleDateFormat"%>

<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.List"%>
<%@page import="java.text.DateFormat"%>
<html>
<head>
<title>Manpower Netherlands</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Application Developer">
<style>
	table {
		font-family:Helvetica,Verdana,sans-serif;
		font-size:12px;
	
	}
	.headerTitleBg{
		background:url("images/jobdesc_03.jpg") no-repeat top left;
		width:640px;
		height:103px;
		padding-left:55px;
		padding-bottom:10px;
		font-size:18px;
		font-weight:bold;
		vertical-align:bottom;
		
	}
	.jobDetails{
		background:url("images/jobdesc_05.jpg") no-repeat top left;
		width:640px;
		color:#989898;
		padding-left:65px;
		padding-top:10px;
		padding-bottom:10px;
		vertical-align:top;
	}
	.jobBody{
		background:url("images/jobdesc_06.jpg") repeat-y top left;
	
		width:540px;
		padding-left:55px;
		vertical-align:top;
		padding-top:10px;	
		padding-right:50px;
		
	}
	.jobFooter{
		background:url("images/jobdesc_10.jpg") no-repeat top left;
		height:108px;
		width:640px;
		padding-left:55px;
	
	}
	.leftHead{
		color:#989898;
	}
	.rightData{
		padding-left:25px;
		color:#525252;
	}

	.buttonMiddle{
		background:url("images/button_04.jpg") repeat-x top left;
		text-align:center;
		font-size:12px;
		color:#FFFFFF;
		font-family:Helvetica,Verdana,sans-serif;
		font-weight:bold;
		padding-bottom:7px;
		cursor:pointer;
	}
	
	.jobLink,.jobLink:hover,jobLink:visited{
		font-weight:bold;
		color:#5f81aa;
		font-size:12px;
		text-decoration:none;
	
	}
	
</style>

</head>
<body style="margin:auto;">
	
	
	<%
		
		
		List adList = DAOFactory.getDAOFactory().getVAdsIndSecDAO().finaAllByCoutryCode("NLD");
		String siteid="NLMPNet";
		%>
		<%
	 	ResourceBundle rb = ResourceBundle.getBundle("com.manpower.directtalentseo.config.settings");
	 	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	 %>
	<table width="640">
		<tr>
			<td align="right">
				<img src="images/mp_logo.gif" alt="Manpower Logo" width="87" height="76">
			</td>
		</tr>
	</table>
	<table cellpadding="0" cellspacing="0">
		<tr>
			<td class="headerTitleBg">
				
				&nbsp;
			</td>
		</tr>
		
			<%for (int i=0;i<adList.size();i++){
				VAdsIndSec adBean = (VAdsIndSec)adList.get(i);
				if (i==10) break;
			%>
			<tr>
				<td class="jobBody">
					<div style="width:500px;background-color:#f0f0f0;padding:10px;">
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td valign="top" align="left">
								<a href="https://portal.manpower.com/wps/PA_1_0_FN/ViewJobAdvertisement?site=<%=siteid%>&JobId=<%=adBean.getId()%>" class="jobLink"><%=adBean.getJobtitle() %></a>
							</td>
							<td valign="top" align="right" style="font-color:#000000;font-weight:bold;">
								Posted on: <%=df.format(adBean.getPublicationdate()) %>
							</td>
						</tr>
					</table>
					
					
					<%=adBean.getJobdescription() %>
					<table width="100%" cellpadding="0" cellspacing="0" style="padding-top:8px;">
						<tr>
							<td align="center">
								<table cellpadding="0" cellspacing="0" >
								<tr>
									<td width="16">
										<img src="images/button_03.jpg" width="16" height="26" alt="">
									</td>
									<td class="buttonMiddle" onclick="window.location.href='https://portal.manpower.com/wps/PA_1_0_FN/ViewJobAdvertisement?site=<%=siteid%>&JobId=<%=adBean.getId()%>'">
										Solliciteer Nu!
									</td>
									<td width="15">
										<img src="images/button_06.jpg" width="15" height="26" alt="">
									</td>
								</tr>
							
								</table>
							</td>
						</tr>
					</table>
					</div>
				</td>
			</tr>
			 <%}
			 %>
			


		<tr>
			<td class="jobFooter" >
				
			</td>
		</tr>
	
	</table>
	
</body>
</html>
