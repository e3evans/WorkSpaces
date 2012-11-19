<%@page import="com.asponte.vmmportlet.VMMPortlet"%>
<%@page session="false" contentType="text/html" pageEncoding="ISO-8859-1" import="java.util.*,javax.portlet.*,com.asponte.longloaderportlet.*" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>        
<%@taglib uri="http://www.ibm.com/xmlns/prod/websphere/portal/v6.1/portlet-client-model" prefix="portlet-client-model" %>        
<portlet:defineObjects/>
<portlet-client-model:init>
      <portlet-client-model:require module="ibm.portal.xml.*"/>
      <portlet-client-model:require module="ibm.portal.portlet.*"/>   
</portlet-client-model:init> 
<%
	com.asponte.longloaderportlet.LongAjaxLoaderPortletSessionBean sessionBean = (com.asponte.longloaderportlet.LongAjaxLoaderPortletSessionBean)renderRequest.getPortletSession().getAttribute(com.asponte.longloaderportlet.LongAjaxLoaderPortlet.SESSION_BEAN);
%>

 <script>
	function longLoader(renderas){
		var searchString = document.getElementById('searchField').value;
		$('#longLoad').empty();
		$.ajax({
		  type: 'POST',
		  dataType:'text',
		  url: '<portlet:resourceURL/>',
		  data: { dummy1: ''},
		  beforeSend:function(){
		    // this is where we append a loading image
		    $('#longLoad').html('<div class="loading"><img src="<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/images/ajax-loader.gif")%>" alt="Loading..." /></div>');
		  },
		  success:function(data){
		    // successful request; do something with the data
		    //alert(data);
		    //parser=new DOMParser();
			//xmlDoc=parser.parseFromString(data,"text/xml");
			//alert(xmlDoc);

		    $('#longLoad').empty();
		    $('#longLoad').html('<div class="loading"><img src="<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/images/cognos.jpg")%>" alt="Loading..." /><br/><input type="button" value="Reload" onclick="longLoader()"/></div>');
		    //$('#ajax-panel').text(data.toString());
		  },
		  error:function(){
		    // failed request; give feedback to user
		    $('#longLoad').html('<p class="error"><strong>Oops!</strong> Try that again in a few moments.</p>');
		  }
		});	
	}
	

</script>
<DIV id="longLoad" style="margin: 6px" align="center">

</DIV>

<script>
	window.onload = longLoader;
</script>