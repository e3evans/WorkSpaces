<%-- jsf:pagecode language="java" location="/src/pagecode/AjaxSimpleTestView.java" --%><%-- /jsf:pagecode --%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>        
         
<%@ page language="java" contentType="text/html" pageEncoding="ISO-8859-1" session="false"%>
<portlet:defineObjects />
 
 <script type="text/javascript"
		src="<%= renderResponse.encodeURL(renderRequest.getContextPath()+"/dojo/dojo/dojo.js")%>"  djConfig="parseOnLoad: true"></script>
 <script type="text/javascript" src='<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/swfobject/swfobject.js")%>'></script>
<script type="text/javascript" src='<%=renderResponse.encodeURL(renderRequest.getContextPath() +"/jslib/AC_OETags.js")%>'></script>      
<f:view>
<script>
			function loadHtmlPost() {
				//alert(document.getElementById('inclCand').checked);
				var contextPath = '<%=renderResponse.encodeURL(renderRequest.getContextPath())%>';
				
				ajaxUrl = contextPath + '/SimpleTest';
			
				
				
				dojo.xhrPost({
	       			url : ajaxUrl, 
	       			handleAs : "text", //MIME type
	       			sync : false, //Explicit asynchronous request
					content:{
						uniqueId:"test",
						targetDiv:"testing testing testing",
						namespace:'<portlet:namespace/>',
						action:"whatever",
						params:"blah",
						page:"blahblah",
						contextPath:contextPath,
						siteid:'10420'						
					},  
	       			//Run this function if the request is successful
	      				load : function(data,response) {
	           			dojo.byId('postdisplay').innerHTML= data;
	            		return response;
	       			},
	 
			        //Run this function if the request is not successful
			        error : function(response, ioArgs) {
	                      console.log("failed xhrGet");  
	                      //DEBUG ONLY alert(response);
	                     alert('load fail'); 
	                      return response;
			        }
	    		});
				return "";
			}

		function loadHtmlGet() {
				//alert(document.getElementById('inclCand').checked);
				var contextPath = '<%=renderResponse.encodeURL(renderRequest.getContextPath())%>';
				
				ajaxUrl = contextPath + '/SimpleTest';
			
				
				
				dojo.xhrGet({
	       			url : ajaxUrl, 
	       			handleAs : "text", //MIME type
	       			sync : false, //Explicit asynchronous request
					content:{
						uniqueId:"test",
						targetDiv:"testing testing testing",
						namespace:'<portlet:namespace/>',
						action:"whatever",
						params:"blah",
						page:"blahblah",
						contextPath:contextPath,
						siteid:'10420'						
					},  
	       			//Run this function if the request is successful
	      				load : function(data,response) {
	           			dojo.byId('getdisplay').innerHTML= data;
	            		return response;
	       			},
	 
			        //Run this function if the request is not successful
			        error : function(response, ioArgs) {
	                      console.log("failed xhrGet");  
	                      //DEBUG ONLY alert(response);
	                     alert('load fail'); 
	                      return response;
			        }
	    		});
				return "";
			}

</script>

	<table>
		<tr>
			<td valign="top">
				<input type="button" onclick="loadHtmlPost()" value="testing POST">
			</td>
			<td id="postdisplay">
				
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<hr>
			</td>
		</tr>
		<tr>
			<td valign="top">
				<input type="button" onclick="loadHtmlGet()" value="testing GET">
			</td>
			<td id="getdisplay">
			
			</td>
		</tr>
	</table>
<div id="sssFlash" style="border:1px solid red;">

</div>

<script type="text/javascript">

	var flashvars = {};
	flashvars.posturl = '<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/SimpleTest")%>';
	
	var params = {};	
	var attributes = {};
	swfobject.embedSWF('<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/flash/SimpleAjaxTest.swf") %>', "sssFlash", "300", "300", "9.0.0","expressInstall.swf", flashvars, params, attributes);

</script>
	
</f:view>
