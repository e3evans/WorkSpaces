<%@page import="com.ibm.standardlifefeeds.StandardLifeFeedsPortlet"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%> 

<portlet:defineObjects />
<%=request.getAttribute(StandardLifeFeedsPortlet.DISP_RESPONSEDATA)%>