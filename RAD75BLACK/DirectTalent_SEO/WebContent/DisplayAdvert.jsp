<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.manpower.directtalentseo.hbn.beans.VAdsIndSec"%>
<%@page import="com.manpower.directtalentseo.hbn.shared.dao.DAOFactory"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.manpower.directtalentseo.hbn.beans.TAdContact"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Enumeration"%>
<html>
<%
	VAdsIndSec adBean = (VAdsIndSec) DAOFactory.getDAOFactory().getVAdsIndSecDAO().findByID(new Long(request.getParameter("adId")));
	String jobTitle = "";
	if (adBean!=null)jobTitle=" ("+adBean.getJobtitle()+")";
	ResourceBundle linkBundle = ResourceBundle.getBundle("com.manpower.directtalentseo.config."+request.getParameter("site")+"Links");
	StringBuffer sb=new StringBuffer();
	try{
		for (Enumeration e = linkBundle.getKeys();e.hasMoreElements();){
			String key = (String)e.nextElement();
			String [] link = (String[])linkBundle.getString(key).split("~");
			sb.append("<a href=\""+link[0]+"\">"+link[1]+"</a><br><br>");
		}
	}catch (Exception e){
		e.printStackTrace();
	}
	
 %>
<head>
<title>Manpower <%=jobTitle %></title>
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
		height:300px;
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
	.blueBoxHead{
		background:url("images/blueBoxTop.gif") no-repeat top left;
		width:294px;
		height:9px;
		font-size:3px;
	}
	.blueBoxMiddle a{
	
		font-family:Helvetica,Verdana,sans-serif;
		font-size:12px;
		color:#3c75ae;
		font-weight:bold;
		text-decoration:none;

	}
	.blueBoxMiddle a:hover{
	
		font-family:Helvetica,Verdana,sans-serif;
		font-size:12px;
		color:#3c75ae;
		font-weight:bold;
		text-decoration:underline; 

	}
	.blueBoxMiddle{
		background:url("images/blueBoxMiddle.jpg") repeat-y top left;
		width:294px;
		font-family:Helvetica,Verdana,sans-serif;
		font-size:12px;
		padding-right:5px;
		padding-left:10px;
		padding-top:10px;
		padding-bottom:10px;
		color:#3c75ae;
		font-weight:bold;
		text-decoration:none;

	}
	.blueBoxBottom{
		background:url("images/blueBoxBottom.jpg") no-repeat top left;
		width:294px;
		height:9px;
		font-size:3px;
	}
</style>

</head>
<body style="margin:auto;">

	
	
	<%
		if (adBean!=null){
		String jobId = request.getParameter("adId");
		String site = request.getParameter("site");
		String DATE_FORMAT = "MM/dd/yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		String contactId = Long.toString(adBean.getAdvertcontactid());
		TAdContact adContact = new TAdContact();
		adContact = (TAdContact)DAOFactory.getDAOFactory().getTadContactDAO().findByID(new Long(contactId));
	 	ResourceBundle rb = ResourceBundle.getBundle("com.manpower.directtalentseo.config.settings");
	 %>

	<table width="640">
		<tr>
			<td style="padding-left:20px;">
									 	<!-- AddThis Button BEGIN -->
<script type="text/javascript">var addthis_pub = "e3evans";</script>
<a href="http://www.addthis.com/bookmark.php" onmouseover="return addthis_open(this, '', '[URL]', '[TITLE]')" onmouseout="addthis_close()" onclick="return addthis_sendto()"><img src="http://s7.addthis.com/static/btn/lg-share-en.gif" width="125" height="16" border="0" alt="" /></a><script type="text/javascript" src="http://s7.addthis.com/js/152/addthis_widget.js"></script>
<!-- AddThis Button END -->
			</td>
			<td align="right">
				<img src="images/mp_logo.gif" alt="Manpower Logo" width="87" height="76">
			</td>
		</tr>
	
	</table>

	<table cellpadding="0" cellspacing="0">
		<tr>
			<td class="headerTitleBg">
				<%=adBean.getJobtitle() %>
				<br><span style="font-size:12px;font-weight:normal;">
				Posted on:  <%=sdf.format(adBean.getPublicationdate()) %>
				</span>
				
			</td>
		</tr>
		<tr>
			<td class="jobDetails">
				<table>
					<tr>
						<td class="leftHead">
						Plaats:
						</td>
						<td class="rightData">
						 <%=adBean.getLocation() %>
						</td>
					</tr>
					<%if (adContact.getPhonenumber()!=null){ %>
					<tr>
						<td class="leftHead">
						Telefoon:
						</td>
						<td class="rightData">
							<%=adContact.getPhonenumber() %>
						</td>
					</tr>
					<%} %>
					<%if (adBean.getPayrange()!=null){ %>
					<tr>
						<td class="leftHead">
						Salaris:
						</td>
						<td class="rightData">
						 <%=adBean.getPayrange() %>
						</td>
					</tr>
					<%} %>
					<tr>	
						<td class="leftHead">
						Vakgebied:
						</td>
						<td class="rightData">
						<%=adBean.getIndustry_sector() %>
						</td>
					</tr>
					<tr>
						<td class="leftHead">
						Referentienummer:
						</td>
						<td class="rightData">
						<%=adBean.getReferencenumber() %>
						</td>
					</tr>
					
				</table>

				
				 
			</td>
		</tr>
		<tr>
			<td class="jobBody">
				<div style="width:540px;">
				<div style="font-weight:bold;width:540px;">Functieomschrijving</div>
				<%=adBean.getJobdescription() %>
				<br><br>
				<div style="font-weight:bold;width:540px;">Profiel</div>
				<%=adBean.getCandidateskills() %>
				<br><br>
				<div style="font-weight:bold;width:540px;">Contactgegevens</div>
				<%=adContact.getName() %><br>
				<%if (adContact.getAddress1()!=null){ %>
				<%=adContact.getAddress1() %><br>
				<%} %>
				<%if (adContact.getAddress2()!=null){ %>
				<%=adContact.getAddress2() %><br>
				<%} %>
				<%=adContact.getCity() %>, <%=adContact.getState() %>
				</div>
			</td>
		</tr>
		<tr>
			<td class="jobFooter" >
				<table cellpadding="0" cellspacing="0" >
					<tr>
						<td width="16">
							<img src="images/button_03.jpg" width="16" height="26" alt="">
						</td>
						<td class="buttonMiddle" onclick="window.location.href='https://portal.manpower.com/wps/PA_1_0_FN/ViewJobAdvertisement?site=<%=site%>&JobId=<%=jobId%>'">
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
	<div style="position:relative;top:-550px;left:635;">
		<table cellpadding="0" cellspacing="0">
					<tr>
						<td class="blueBoxHead">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td class="blueBoxMiddle">
						<%=sb.toString() %></td>
					</tr>
					<tr>
						<td class="blueBoxbottom">&nbsp;</td>
					</tr>
				
				</table>
	</div>
	<%}else{ %>
		That advertisement is no longer valid.  Please click the link below to visit Manpower and look for more great career opportunities....<a href="http://portal.manpower.com/wps/portal/NLMPNet">Click here for Jobs!</a>
	<%} %>
		<script type="text/javascript">
			var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
			document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
		</script>
		<script type="text/javascript">
			try {
				var pageTracker = _gat._getTracker("UA-6896641-1");
				pageTracker._trackPageview();
			} catch(err) {}
		</script>
</body>
</html>
