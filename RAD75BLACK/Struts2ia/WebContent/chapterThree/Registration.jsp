<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>

	<head>
    <title>Portfolio Registration</title>
	</head>

	<body>
		<h4>Complete and submit the form to create your own portfolio.</h4> 
		<s:form action="Register">
    	  <s:textfield name="username" label="Username"/>
    	  <s:password name="password" label="Password"/>
    	  <s:textfield name="portfolioName" label="Enter a name for your portfolio"/>  	
    	<s:submit/>
		</s:form>	
	  
		
	</body>
	
</html>