<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.2//EN"
"http://www.openmobilealliance.org/tech/DTD/xhtml-mobile12.dtd">
<html>

	<head>
		<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
		<title>Manpower, Inc.</title>
		<link id="portalStyles" href="../css/styles_002.css" rel="styleSheet" type="text/css">
		<link rel="stylesheet" type="text/css" href="../css/tundra.css">
		<link type="text/css" href="../css/styles.css" rel="stylesheet">
		<!--	if lte IE 7-->
		<!--    		<link type="text/css" href="../css/lte_ie7.css" rel="stylesheet">-->
		<!--	endif-->

		<script type="text/javascript" src="../js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="../js/jquery-ui-1.8.2.min.js"></script>
		<sj:head jqueryui="true"/>

		<script type="text/javascript">
			function backToStep2(){
				<% session.setAttribute("DO_NOT_REPROCESS_MERGE", "true"); %>
				return location.href='step2';
			}

			function showLoadingPicture(){
				var myDiv = document.getElementById('ajaxLoaderDiv');
				myDiv.style.visibility = "visible";
			}

			function getCheckedValue() {
				var checkObj = document.forms['step4Form'].elements['deployLocation'];

				document.getElementById('buttonToProcess').value = 'deploy';

				if(!checkObj)
					return "";

				var checkLength = checkObj.length;
				var text = null;

				if(checkLength == undefined)
					if(checkObj.checked)
						return checkObj.value;
					else
						return "";
					for(var i = 0; i < checkLength; i++) {
						if(checkObj[i].checked) {
							text = checkObj[i].value;
							showLoadingPicture();
							document.forms["step4Form"].submit();
						}
					}
					if(text == null && document.forms[0].All.checked == false){
						//$('selectLocation').dialog('open');
						alert('Please select a valid deploy location to proceed...');
						document.getElementById('step4Form').onsubmit=null;
					} else {
						showLoadingPicture();
						document.forms["step4Form"].submit();
					}
			}

			function checkAll() {
				if (document.forms[0].All.checked) {

					var count = document.forms[0].elements['deployLocation'].length;
					    for (i=0; i < count; i++) {
					    	if(document.forms[0].elements['deployLocation'][i].checked == 1) {
					    		document.forms[0].elements['deployLocation'][i].checked = 0;
					    	}
						}
				}
			}

			function unCheckAll(){
				if (document.forms[0].All.checked) {
					document.forms[0].All.checked=0;
				}
			}

		</script>
	</head>

	<body class="wptheme-mainbody tundra">
		<div id="FLYParent" class="wptheme-FLYParent">
			<jsp:include page="header.jsp" />
			<div id="main">
				<div id="contents">
					<div id="Page">
						<h2>Resources Manager</h2>
						<ul class="steps">
							<li>
								<a href="step1?CTRY=${selectedCountryCode}">Step 1: Select File</a>
							</li>
							<li>
								<a href="step2">Step 2: Update Descriptions</a>
							</li>
							<li class="selected">
								<a href="step3">Step 3: Confirmation</a>
							</li>
							<li>
								 <a href="#" onclick="return false">Results</a>
							</li>
						</ul>

						<div class="clear"></div>

						<div class="stepsContent">
							<form action="step4" id="step4Form" method="POST" class="step3" >
								<s:hidden name="selectedCountryCode" />
								<s:hidden name="selectedSiteId" />
								<s:hidden name="propFile" />
								<s:hidden name="languages" id="languages" cssClass="allLanguages" />
								<s:hidden name="deployLocation" />
								<s:hidden name="buttonToProcess" id="buttonToProcess" />
								<s:set var="first" value="true" />
								<ul class="step3list">
									<s:iterator value="file.propertyLines" var="line">
										<li	class="<s:if test="%{#line.modified}"> modified </s:if> <s:else> odd </s:else> <s:if test="%{first}"> first </s:if>">
											<s:set var="first" value="false" />
											<span>
												<b>Key: <s:property value="#line.propertyKey" /></b>
											</span>
											<table>
												<s:iterator value="#line.locatedValues" var="locValue">
													<c:set var="def" value="${file.defaultLang}" />
													<c:set var="row" value="${locValue.locale}" />
													<jsp:useBean id="def" type="java.lang.String" />
													<jsp:useBean id="row" type="java.lang.String" />
													<tr>
														<td style="color: #7DA090; font-size: 12px; font-weight: bold;">
															<c:if test='<%=def.equalsIgnoreCase(row)%>'>*</c:if><s:property value="#locValue.locale" /> :
														</td>
														<td>
															<p>${locValue.value}</p>
															<input type="hidden" name="${fn:replace(line.propertyKey, '.', '_dot_')}__${locValue.locale}" value="${locValue.value}" />
														</td>
													</tr>
												</s:iterator>
											</table>
											<div class="clear"></div>
<!--											<s:if test="%{#line.modified}">-->
<!--												<img src="../images/alert-square-red.png" alt="Alert" />-->
<!--											</s:if>-->
										</li>
									</s:iterator>
								</ul>
								<div class="continueButton">
									<p>
										<s:checkboxlist list="servers" id="servers" name="deployLocation" onclick="unCheckAll();"/>
										<s:checkbox name="All" fieldValue="true" label="All" onclick="checkAll();"/>
									</p>

   									 <sj:dialog id="selectLocation" href="%{remoteurl}" title="Please read below"
   									 	autoOpen="false" modal="true" showEffect="slide" hideEffect="explode">
   									    Please select a valid deploy location to proceed
   									 </sj:dialog>

									<input class="submit" type="button" value="Back" onclick="backToStep2();"></input>
									<input class="submit" type="submit" value="Save" onclick="showLoadingPicture()"/>
									<input class="submit" type="button" value="Save & Deploy" onclick="getCheckedValue();" />
									<br><div id="ajaxLoaderDiv" style="visibility: hidden;"><img src="../images/ajax-loader.gif" alt="Loading"/></div>
								</div>
								<div class="clear"></div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>