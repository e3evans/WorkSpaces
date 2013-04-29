<%@page session="false" contentType="text/html" pageEncoding="ISO-8859-1" import="java.util.*,javax.portlet.*,com.manpower.tridiontest.*" %>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>        
<%@taglib uri="http://www.ibm.com/xmlns/prod/websphere/portal/v6.1/portlet-client-model" prefix="portlet-client-model" %>        
<portlet:defineObjects/>
<portlet-client-model:init>
      <portlet-client-model:require module="ibm.portal.xml.*"/>
      <portlet-client-model:require module="ibm.portal.portlet.*"/>   
</portlet-client-model:init> 
<%
	com.manpower.tridiontest.TridionTestPortletSessionBean sessionBean = (com.manpower.tridiontest.TridionTestPortletSessionBean)renderRequest.getPortletSession().getAttribute(com.manpower.tridiontest.TridionTestPortlet.SESSION_BEAN);
%>   
<%=request.getAttribute("test")%>