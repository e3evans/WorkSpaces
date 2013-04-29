<%-- jsf:pagecode language="java" location="/src/pagecode/Search30/DirectTalent_Search30View.java" --%><%-- /jsf:pagecode --%><%@taglib
	uri="http://java.sun.com/jsf/core" prefix="f"%><%@taglib
	uri="http://java.sun.com/portlet" prefix="portlet"%><%@taglib
	uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%><%@taglib
	uri="http://java.sun.com/jsf/html" prefix="h"%><%@page language="java"
	contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	session="false"%><portlet:defineObjects />
<link rel="stylesheet" type="text/css" title="Style"
	href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/stylesheet.css") %>'>
<f:view>
	<script type="text/javascript"
		src='<%= renderResponse.encodeURL(renderRequest.getContextPath()+"/js/dojo/dojo.js")%>'></script>
	<script type="text/javascript">
		var posturl = '<%= renderResponse.encodeURL(renderRequest.getContextPath()+"/Search30/Results/DirectTalent_Search30_SearchResults.jsp")%>';
		
		function loadSearchResults(){
		
			var xhrArgs = {
               	url : posturl, 
	       		handleAs : 'text', //MIME type
	       		sync : false, //Explicit asynchronous request
				content:{
					uniqueId:'test1',
					targetDiv:'test2',
					namespace:'<portlet:namespace/>',
					action:'test3',
					searchparams:'java',
					page:'test5',
					contextPath:'test6',
					siteid:'10420'						
				},
                load: function(data) {
                    dojo.byId('response2').innerHTML = data;
                },
                error: function(error) {
                    //We'll 404 in the demo, but that's okay.  We don't have a 'postIt' service on the
                    //docs server.
                    dojo.byId('response2').innerHTML = 'ERROR!!';
                }
            }
			
			var deferred = dojo.xhrPost(xhrArgs);
			
		
		}
		
	
	</script>
	<hx:scriptCollector id="scriptCollector1">


		<h:form styleClass="form" id="form1">
			<hx:commandExButton type="submit" value="ReIndex Ads"
				styleClass="commandExButton" id="reindex" action="#{pc_DirectTalent_Search30View.doReindexAction}"></hx:commandExButton>
		</h:form>
		<hr>
		<div id="response2">
			BLANK.....
		</div>
		<input type="button" onclick="loadSearchResults()" value="Ajax me!">
		
	</hx:scriptCollector>
</f:view>