<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.2//EN"
"http://www.openmobilealliance.org/tech/DTD/xhtml-mobile12.dtd">
<html>
	<head>
		<title>Manpower, Inc.</title>
		<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
		<link id="portalStyles" href="../css/styles_002.css" rel="styleSheet" type="text/css">
		<link rel="stylesheet" type="text/css" href="../css/tundra.css">
		<link rel="stylesheet" type="text/css" href="../css/jquery-ui.css">
		<link type="text/css" href="../css/styles.css" rel="stylesheet">
		<!--[if lte IE 7]>
    	<link type="text/css" href="../css/lte_ie7.css" rel="stylesheet">
		<![endif]-->

		<script type="text/javascript" src="../js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="../js/jquery-ui-1.8.2.min.js"></script>
		<script type="text/javascript" src="../js/addLanguages.js"></script>
		<script type="text/javascript">

//			function alternate(tag) {
//				if (tag.style.display == "none") {
//					tag.style.display = ""
//				} else {
//					tag.style.display = "none"
//				}
//			}

			// valid tasks parameters are: 'add_language',
			//	'remove_language', 'verify_default', 'update_screen'
			function langProcess(task) {
				var cbo; var opt;
				document.getElementById('taskSelected').value = task;
				if (task === 'add_language') {
					cbo = document.getElementById('allLangCbo');
					if (cbo.selectedIndex > -1){
						opt = cbo.options[cbo.selectedIndex];
						document.getElementById('languageToProcess').value = opt.value;
					}
					document.getElementById("miscForm").submit();

				} else if (task === 'remove_language') {
					cbo = document.getElementById('defaultLangCbo');
					if (cbo.selectedIndex > -1){
						opt = cbo.options[cbo.selectedIndex];
						document.getElementById('languageToProcess').value = opt.value;
					}
					document.getElementById("miscForm").submit();

				} else if (task === 'verify_default') {
					cbo = document.getElementById('defaultLangCbo');
					if (cbo.selectedIndex > -1){
						opt = cbo.options[cbo.selectedIndex];
						document.getElementById('languageToProcess').value = opt.value;
//						alert('${file.defaultLang}');
//						alert(opt.value);
						if ('${file.defaultLang}' === opt.value){
							document.getElementById('deleteBtn').disabled = true;
							document.getElementById('defaultBtn').disabled = true;
						}else{
							document.getElementById('deleteBtn').disabled = false;
							document.getElementById('defaultBtn').disabled = false;
						}
					}
					document.getElementById("miscForm").onsubmit=null;

				} else if (task === 'update_screen' ) {
					<% session.setAttribute("DO_NOT_REPROCESS_MERGE", "false"); %>
					cbo = document.getElementById('defaultLangCbo');
					if (cbo.selectedIndex > -1){
						opt = cbo.options[cbo.selectedIndex];
						document.getElementById('languageToProcess').value = opt.value;

						if ('${file.defaultLang}' === opt.value){
							document.getElementById('deleteBtn').disabled = true;
							document.getElementById('defaultBtn').disabled = true;
						} else {
							document.getElementById('deleteBtn').disabled = false;
							document.getElementById('defaultBtn').disabled = false;
						}
					} else {
						document.getElementById('deleteBtn').disabled = true;
						document.getElementById('defaultBtn').disabled = true;
					}
				}
			}
		</script>

	</head>

	<body class="wptheme-mainbody tundra" onload="langProcess('update_screen');">
		<div id="FLYParent" class="wptheme-FLYParent">
			<jsp:include page="header.jsp" />

			<div id="remLang" style="display: none;"> Select the languages to filter: <br />
				<s:iterator value="filteLangs" var="lang">
					<s:checkbox cssClass="filterLangCheck"
						label="%{#lang.lang}"
						name="%{#lang.lang}"
						value="%{#lang.show}"
						fieldValue="%{#lang.show}" />
				</s:iterator><br />
				<div class="divDown">
					<input type="submit" value="Done" class="remLangBtn" />
					<input type="submit" value="Cancel" class="remCancelLangBtn" />
				</div>
			</div>

			<div id="main">
				<div id="contents">
					<div id="Page">
						<h2> Resources Manager </h2>
						<ul class="steps">
							<li>
								<a href="step1?CTRY=${selectedCountryCode}">Step 1: Select File</a>
							</li>
							<li class="selected">
								<a href="step2">Step 2: Update Descriptions</a>
							</li>
							<li>
							 	<a href="javascript:void(document.forms['step2Form'].submit())">Step 3: Confirmation</a>
							</li>
							<li>
								 <a href="#" onclick="return false">Results</a>
							</li>
						</ul>

						<div class="clear"></div>
						<div style="display: none">
							<dl class="langTemplate">
								<dt class="langName"><s:property value="#locValue.locale" />:</dt>
								<dd>
									<input class="langValue" type="text" size="100" name="hola"	value="" />
								</dd>
							</dl>
						</div>
						<div class="stepsContent">

							<s:form action="step2" id="miscForm" method="POST" cssClass="step2" theme="simple" >
								<s:hidden name="languageToProcess" id="languageToProcess" />
								<s:hidden name="taskSelected" id="taskSelected" />

								<p style="font-size: 16px;font-family: sans-serif ">
									<s:label name="countryCodeTextLabel"> Country code:  <s:property value="selectedCountryCode"/> </s:label>
									<s:label  name="countryTextLabel">. Country:  <s:property value="selectedCountryName"/> </s:label>
								</p>
								<p class="languageBar">	Languages Supported: </p>
<!--								<p>-->
<!--									<s:textfield id="languages" name="languages" readonly="true" cssClass="languagesShow" />-->
<!--									<input class="remLangButton" type="button" value="+ Display filters">-->
<!--								</p>-->
								<p>
									<s:select id="defaultLangCbo" name="defaultLangCbo" list="langFile" onchange="langProcess('verify_default');"/>
									<s:submit id="deleteBtn" disabled="true" value="Delete Language"  onclick="langProcess('remove_language');" />
									<s:submit id="defaultBtn" disabled="true" value="Set As Default" onclick="langProcess('verify_default');" />
								</p>
								<p>
									<s:select id="allLangCbo" name="allLangCbo" list="languagesList" />
									<s:submit id="addLangBtn" value="Add Language" onclick="onclick=langProcess('add_language');" />
								</p>
							</s:form>

							<s:form action="step2" id="step2Form" method="POST" cssClass="step2">
								<s:hidden name="selectedCountryCode" />
								<s:hidden name="selectedCountryName" />
								<s:hidden name="selectedSiteId" />
								<s:hidden name="propFile" />
								<s:hidden name="languages" id="languages" cssClass="allLanguages" />

								<ul class="step2list">
									<s:iterator value="file.propertyLines" var="line">
										<input type="hidden" value="${line.propertyKey}" />
										<li class="odd propLine">
											<span><b>Key: <span class="propKey"><s:property
															value="#line.propertyKey" /> </span> </b> </span>
											<dl class="dlPropLine">
												<s:iterator value="#line.locatedValues" var="locValue">
													<dt class="${line.escapedPropertyKey}_${locValue.locale}">
													<c:set var="def" value="${file.defaultLang}" />
													<c:set var="row" value="${locValue.locale}" />
													<jsp:useBean id="def" type="java.lang.String" />
													<jsp:useBean id="row" type="java.lang.String" />
													<c:if test='<%=def.equalsIgnoreCase(row)%>'>*</c:if><s:property value="#locValue.locale" />:</dt>
													<dd>
														<input type="text" size="130"
															name="prp__${fn:replace(line.propertyKey, '.', '_dot_')}__${locValue.locale}"

															value="${locValue.value}" />
<!--														<input type="text" size="130"-->
<!--															name="prp__${line.propertyKey}__${locValue.locale}"-->
<!--															value="${locValue.value}" />-->
													</dd>
												</s:iterator>
											</dl>
											<div class="clear"></div>
										</li>
									</s:iterator>
								</ul>
								<div class="continueButton">
									<input class="submit" type="button" value="Back" onclick="location.href='step1?CTRY=${selectedCountryCode}';" />
									<input class="submit" type="submit" value="Continue" />
								</div>
								<div class="clear"></div>
							</s:form>

						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
