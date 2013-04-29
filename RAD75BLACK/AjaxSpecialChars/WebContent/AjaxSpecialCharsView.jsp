<%-- jsf:pagecode language="java" location="/src/pagecode/AjaxSpecialCharsView.java" --%><%-- /jsf:pagecode --%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>        
<%@taglib uri="http://www.ibm.com/xmlns/prod/websphere/portal/v6.1/portlet-client-model"   
       prefix="portlet-client-model" %>              
<%@ page language="java" contentType="text/html" pageEncoding="ISO-8859-1" session="false"%>
<portlet:defineObjects />
<portlet-client-model:init>
      <portlet-client-model:require module="ibm.portal.xml.*"/>
      <portlet-client-model:require module="ibm.portal.portlet.*"/>   
</portlet-client-model:init>   
<script type="text/javascript" src='<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/dojo/dojo.js") %>'></script>

<script type="text/javascript">
	alert(dojo);
	function testSpecial(){
		
		var testfld = document.getElementById('testfield').value;
	
		dojo.xhrPost({
	     			url : '<%=renderResponse.encodeURL(renderRequest.getContextPath())%>/CharTest', 
	     			handleAs : "text", //MIME type
	     			sync : false, //Explicit asynchronous request
			content:{
				test:testfld
			},  
	     			//Run this function if the request is successful
	    				load : function(data,response) {
	          			
	          		//Our handleAs value, tells Dojo to strip
	          		//comments and convert the data to an object.
	          		//currentPageParams = params;
	          		alert(dojo.byId('tempdisp').innerHTML);
	         		dojo.byId('tempdisp').innerHTML= data;
	          		return response;
	     			},
	
	        //Run this function if the request is not successful
	        error : function(response, ioArgs) {
	        	//alert('this sux!'); 
	                    console.log("failed xhrGet");  
	                    // DEBUG ONLY!!! alert(response);
	                    dojo.byId('tempdisp').innerHTML = postError; 
	                    return response;
	        }
	  		});
		return "";
	
	
	}
</script>
      
<f:view>
	<input type="text" id="testfield" value="">
	<input type="button" onclick="testSpecial()" value="TEST ME!"><hr>
	<div id="tempdisp">xxx</div>
</f:view>
