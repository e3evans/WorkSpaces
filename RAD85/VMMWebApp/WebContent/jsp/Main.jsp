<!DOCTYPE HTML><%@page import="java.util.ResourceBundle"%>
<%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%ResourceBundle rb = ResourceBundle.getBundle("com.asponte.resources.config"); %>
<html>
<head>
<title>VMM Remote Web Service Test</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript"
	src='<%=response.encodeURL(request.getContextPath() + "/js/jquery-latest.min.js") %>'>
</script>
<script>
function test(renderas){
		var searchString = document.getElementById('searchField').value;
		$('#ajax-panel2').empty();
		$.ajax({
		  type: 'POST',
		  dataType:'text',
		  url: '<%=rb.getString("vmmService")%>'+renderas,
		  data: { userName: '<%=rb.getString("userName")%>', 
		  password: '<%=rb.getString("password")%>',
		 searchBase:'<%=rb.getString("searchBase")%>',
		  searchTerm:searchString },
		  beforeSend:function(){
		    // this is where we append a loading image
		    $('#ajax-panel').html('<div class="loading"><img src="/images/loading.gif" alt="Loading..." /></div>');
		  },
		  success:function(data){
		    // successful request; do something with the data
		    //alert(data);
		    //parser=new DOMParser();
			//xmlDoc=parser.parseFromString(data,"text/xml");
			//alert(xmlDoc);

		    $('#ajax-panel').empty();
		    $('#ajax-panel').text(data.toString());
		  },
		  error:function(){
		    // failed request; give feedback to user
		    $('#ajax-panel').html('<p class="error"><strong>Oops!</strong> Try that again in a few moments.</p>');
		  }
		});	
	}

</script>

</head>
<body>
	<H2>VMM Remote Web Service Call</H2>
	<H3 style="margin-bottom: 3px">Enter Search String:</H3>
	<INPUT style="margin-bottom:3px;" name="searchField" id="searchField" type="text" size="25"/><br/>
	<input type="button" onclick="test('')" value="Get XML (AJAX)"/><input type="button" onclick="test('/json')" value="Get JSON (AJAX)"/>
	<H3 style="margin-bottom: 3px">Raw Data</H3>
	<div id="ajax-panel" style="border:1px solid red;padding:15px;width:80%;">
	
	</div>
</body>
</html>