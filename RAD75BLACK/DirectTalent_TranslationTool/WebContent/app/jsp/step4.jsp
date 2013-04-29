<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.2//EN"
"http://www.openmobilealliance.org/tech/DTD/xhtml-mobile12.dtd">
<html class="dj_gecko dj_ff3 dj_contentbox" lang="en">
	<head>
		<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
		<title>Manpower, Inc.</title>
		<link id="portalStyles" href="../css/styles_002.css" rel="styleSheet" type="text/css">
		<link rel="stylesheet" type="text/css" href="../css/tundra.css">
		<link rel="stylesheet" type="text/css" href="../css/jquery-ui.css">
		<link type="text/css" href="../css/styles.css" rel="stylesheet">
		<!--[if lte IE 7]>
    		<link type="text/css" href="../css/lte_ie7.css" rel="stylesheet">
		<![endif]-->

	</head>

<body class="wptheme-mainbody tundra">
	<div id="FLYParent" class="wptheme-FLYParent">
		<jsp:include page="header.jsp" />
			<div id="main">
				<div id="contents">
					<div id="Page">
						<h2> Resources Manager </h2>
						<ul class="steps">
							<li>
								<a href="step1?CTRY=${selectedCountryCode}">Step 1: Select File</a>
							</li>
							<li>
								<a href="step2">
									Step 2: Update Descriptions </a>
							</li>
							<li>
								<a href="step3"> Step 3: Confirmation </a>
							</li>
							<li class="selected">
								  <a href="#" onclick="return false">Results</a>
							</li>
						</ul>
						<div class="stepsContent">
							<h2> The Changes has been Saved </h2>
							<input class="submit"
								type="button"
								value="Go Back to First Step"
								onclick="location.href='step1'" />
<!--								onclick="location.href='step1?CTRY= <% String.valueOf(session.getAttribute("selectedCountryCode")); %> ';"-->
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>