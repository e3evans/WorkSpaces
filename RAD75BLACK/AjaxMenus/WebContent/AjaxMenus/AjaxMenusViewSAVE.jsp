<%-- jsf:pagecode language="java" location="/src/pagecode/AjaxMenus/AjaxMenusViewSAVE.java" --%><%-- /jsf:pagecode --%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@page language="java" contentType="text/html"
	pageEncoding="ISO-8859-1" session="false"%>

		<style>
	
			.menuPortBox{background-color:#e1eef6;padding:15px;font-family:arial;}
	
			a.menuPortLinkHead,a.menuPortLinkHead:hover,a.menuPortLinkHead:visited{
				color:#64696d;
				cursor:pointer;
				font-weight:bold;
				text-decoration:none;
				font-family:arial;
				font-size:x-small;
			}
			
			a.menuPortLink,a.menuPortLink:hover,a.menuPortLink:visited{
				color:#93a6b7;
				cursor:pointer;
				font-weight:bold;
				text-decoration:none;
				font-family:arial;
				font-size:xx-small;
			}

		</style>

		<portlet:defineObjects />
		<link rel="stylesheet" type="text/css"
			href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/stylesheet.css") %>'
			title="Style">
		<script type="text/javascript"
			src='<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/dojo/dojo.js") %>'></script>
		<script>
		/* Use Dojo.io.bind to asynchronously get invoice content */
		function <portlet:namespace/>loadHtml(uniqueId,targetDiv) {
			
				/*Put the working .gif up in the display area*/
				var workingGif = '<img src="';
				workingGif+='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/images/ajax-loader.gif") %>"';
				workingGif+=' style="align:left;">';
				var workingDiv = dojo.byId('<portlet:namespace/>'+targetDiv);
				workingDiv.innerHTML = "";
				workingDiv.innerHTML=workingGif;
		
				dojo.xhrGet({
        			url : "<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/AjaxServlet")%>", 
        			handleAs : "text", //MIME type
        			sync : false, //Explicit asynchronous request
					content:{
						uniqueId:uniqueId,
						targetDiv:targetDiv
					},  
        			//Run this function if the request is successful
       				load : function(data,response) {
	            			
	            		//Our handleAs value, tells Dojo to strip
	            		//comments and convert the data to an object.
	           			dojo.byId("<portlet:namespace/>"+targetDiv).innerHTML= data;
	            		return response;
        			},

			        //Run this function if the request is not successful
			        error : function(response, ioArgs) {
			        	alert('this sux!');
			            console.log("failed xhrGet"); 
			            return response;
			        }
    		});
						
				
				
				
			return "";
		};

</script>
<hx:scriptCollector id="scriptCollector1" preRender="#{pc_AjaxMenusView.onPageLoadBegin}">
	<f:view>

		<h:form styleClass="form" id="form1">
			<hx:commandExButton type="submit" value="Submit"
				styleClass="commandExButton" id="button1"></hx:commandExButton>
		</h:form>

		<table width="100%" class="menuPortBox">
			<tr>

			</tr>

		</table>
	

	</f:view>
</hx:scriptCollector>
