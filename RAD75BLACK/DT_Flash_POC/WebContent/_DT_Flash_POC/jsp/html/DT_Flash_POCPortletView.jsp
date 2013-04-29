<%@page session="false" contentType="text/html" pageEncoding="ISO-8859-1" import="java.util.*,javax.portlet.*,com.manpower.portal.portlet.dt_flash_poc.*" %>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>        
<%@taglib uri="http://www.ibm.com/xmlns/prod/websphere/portal/v6.1/portlet-client-model" prefix="portlet-client-model" %>        
<portlet:defineObjects/>
<portlet-client-model:init>
      <portlet-client-model:require module="ibm.portal.xml.*"/>
      <portlet-client-model:require module="ibm.portal.portlet.*"/>   
</portlet-client-model:init> 
<script type="text/javascript" src='<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/swfobject/swfobject.js")%>'></script>
<script type="text/javascript" src='<%=renderResponse.encodeURL(renderRequest.getContextPath() +"/jslib/AC_OETags.js")%>'></script>
<script type="text/javascript" LANGUAGE="JavaScript">
			var flashvars = {};
			flashvars.actionurl = "<%=request.getAttribute("actionURL")%>";
			flashvars.thing2 = "test2"
			
 			var params = {};
 			
			var attributes = {
			  id: "myDynamicContent",
			  name: "myDynamicContent",
			};
			
			function login(pObj){
			
				alert(pObj.username+'---'+pObj.password);
				
				return "x";
			}	
				
</script>

<div id="loginFlash">
	NO CONTENT
</div>

<script type="text/javascript">

swfobject.embedSWF('<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/_DT_Flash_POC/flex/TESTME.swf") %>', "loginFlash", "1024", "768", "9.0.0","expressInstall.swf", flashvars, params, attributes);

</script>

<!--  
 		<object id='mySwf' classid='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000' codebase='http://fpdownload.macromedia.com/get/flashplayer/current/swflash.cab' height='200' width='400'>
                <param name='src' value='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/_DT_Flash_POC/flex/TESTME.swf") %>'/>
                <param name='flashVars' value=''/>
                <embed name='mySwf' src='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/_DT_Flash_POC/flex/TESTME.swf") %>' pluginspage='http://www.adobe.com/go/getflashplayer' height='100%' width='100%' flashVars=''/>
            </object>

-->


