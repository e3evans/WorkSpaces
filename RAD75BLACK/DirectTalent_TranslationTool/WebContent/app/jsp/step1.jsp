<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.2//EN"
"http://www.openmobilealliance.org/tech/DTD/xhtml-mobile12.dtd">
<html>
<head>
<title>Manpower, Inc.</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<link id="portalStyles" href="../css/styles_002.css" rel="styleSheet" type="text/css">

<link rel="stylesheet" type="text/css" href="../css/tundra.css">

<link type="text/css" href="../css/styles.css" rel="stylesheet"></head>

<body class="wptheme-mainbody tundra">
	<script type="text/javascript">
	   // function submitCombo() {
	     //   document.getElementById("comboSubmit").value = true;
	     //   document.getElementById("myForm").submit();
	    //}

//		function getCombo(sel) {
//		    var value = sel.options[sel.selectedIndex].value;
//		    document.getElementById("propFile").value = value;
//		}
//
//		function getCombo2(sel) {
//		    var value = sel.options[sel.selectedIndex].value;
//		    //alert(value);
//		}
	</script>
<div id="FLYParent" class="wptheme-FLYParent">
<jsp:include page="header.jsp"/>


	<div id="main">
		<div id="contents">
                    <div id="Page">
                        <h2>Resources Manager</h2>
                        <ul class="steps">
                            <li class="selected"><a href="#">Step 1: Select File</a></li>
                            
                            <li><a href="javascript:void(document.forms['myForm'].submit())">Step 2: Update Descriptions</a></li>
                            <li><a href="#">Step 3: Confirmation</a></li>
                            <li><a href="#" onclick="return false">Results</a></li>
                        </ul>
                		 <div class="stepsContent">
                            <form action="step1" method="POST" id="myForm" class="step1">
<!--                            <s:hidden name="propFile"/>-->
                            <s:hidden name="selectedCountryCode"/>
                            <s:hidden name="selectedCountryName"/>
                                <dl>
                                    <dt>Selected Country:</dt>
                                    <dd>
                                    	 <span><s:property value="country.counrty"/></span>
<!--                                    	<s:select name="countryCode" list="countriesId" emptyOption="true"  headerValue="Select a country" listKey="countryCode" listValue="counrty" />-->
                                    </dd>
                                    <dt>Select a Template File:</dt>
                                    <dd>
                                        <s:select name="propFile" emptyOption="true" list="propFiles" headerValue="Select a file" />
                                    </dd>
                                    <dt><!-- --></dt>
                                    <dd>
                                        <input type="submit" value="Continue" class="submit"/>
                                    </dd>
                                </dl>
                                <div class="clear"></div>

                            </form>

                        </div>
                    </div>
                </div>
  		</div>
	</div>
</body></html>