<%-- jsf:pagecode language="java" location="/src/pagecode/DO_SSSPortlet/DirectOffice_SSSPortletView.java" --%><%-- /jsf:pagecode --%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>        
           
<%@ page language="java" contentType="text/html" pageEncoding="ISO-8859-1" session="false"%>

<%@page import="com.manpower.portal.portlet.directoffice_sssportlet.DirectOffice_SSSPortlet"%>
<%@page import="javax.portlet.PortletSession"%><portlet:defineObjects />

<script type="text/javascript" src='<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/swfobject/swfobject.js")%>'></script>
<script type="text/javascript" src='<%=renderResponse.encodeURL(renderRequest.getContextPath() +"/jslib/AC_OETags.js")%>'></script>
<script type="text/javascript" src='<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/dojo/dojo.js") %>'></script>
<script type="text/javascript" LANGUAGE="JavaScript">
	
	function <portlet:namespace/>loginSSS(pObj){
		
		alert(pObj.ssspassword);
		
		return ("SUCCESS");
		
		
	}
	
	function <portlet:namespace/>returntomymanpower(){
	
		window.location.href = '/wps/myportal/USCampus/MyManpower';
	}
	 
	  

</script>
      
<f:view>

<div id="sssFlash">

</div>

<script type="text/javascript">

var flashvars = {};
	flashvars.actionurl = '<%=request.getAttribute("actionURL")%>';
	flashvars.namespace = '<portlet:namespace/>';
	
	//flashvars.loginservice = '<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/LoginSSS")%>';
	//flashvars.profileservice = '<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/ManageProfileService")%>';
	
	flashvars.loginservice = '<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/mySSS/LoginSSS")%>';
	flashvars.profileservice = '<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/mySSS/ManageProfile")%>';
	flashvars.dtcanid = '<%=renderRequest.getPortletSession().getAttribute(DirectOffice_SSSPortlet.SESS_DT_ID,PortletSession.APPLICATION_SCOPE) %>';
	flashvars.flashstate = '<%=renderRequest.getAttribute(DirectOffice_SSSPortlet.SESS_LOGIN_STATUS)%>';
	flashvars.selectedtab = '<%=renderRequest.getAttribute(DirectOffice_SSSPortlet.SESS_SELECTED_TAB)%>';

	var params = {};	
	var attributes = {};
	
	swfobject.embedSWF('<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/flex/SSS_Portlet_Proj.swf") %>', "sssFlash", "1024", "830", "9.0.0","expressInstall.swf", flashvars, params, attributes);

</script>

</f:view>
