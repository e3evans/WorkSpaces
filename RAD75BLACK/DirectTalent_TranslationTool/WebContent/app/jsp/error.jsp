<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.2//EN"
"http://www.openmobilealliance.org/tech/DTD/xhtml-mobile12.dtd">

<html class="dj_gecko dj_ff3 dj_contentbox" lang="en">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<title>Manpower, Inc.</title>
		<link id="portalStyles" href="css/styles_002.css" rel="styleSheet" type="text/css">
		<link rel="stylesheet" type="text/css" href="css/tundra.css">
		<link type="text/css" href="css/styles.css" rel="stylesheet">
	</head>

	<body class="wptheme-mainbody tundra">
		<div id="FLYParent" class="wptheme-FLYParent">
			<jsp:include page="header.jsp" />
			<div id="main">
				<div id="contents">
					<div id="Page">
						<h2>Resources Manager</h2>
						<div class="stepsContent">
							<h2>Sorry. You cannot access this page like this</h2>
							<font face="arial" size=2 color=red>
								<s:property value="resultMessage" />
							</font>
							<br>
							<input class="submit" type="button" value="Go Back to First Step"
								onclick="location.href='step1'" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>