<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page	language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>Candidate Search</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<s:form action="search">
		<s:textfield name="searchCriteria" label="Enter Search Words:  "/>
		<s:checkbox name="searchFields" fieldValue="resumetext" label="Resume: " value="true"/>
		<s:checkbox name="searchFields" fieldValue="email" label="E-mail: " value="false"/>
		<s:checkbox name="searchFields" fieldValue="resumename" label="File Name: " value="false"/>
		<s:checkbox name="searchFields" fieldValue="candidateid" label="Candidate ID: " value="false"/>
		<s:submit action="search" name="SEARCH" value="SEARCH"/>
		<s:submit action="delete" name="DELETE" value="DELETE RECORD"/>
	</s:form>
	
	<s:property value="resultsList" escape="false"/>
</body>
</html>