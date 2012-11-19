<%-- 
    Document   : Votes
    Created on : 30 Aug, 2012, 1:32:22 PM
    Author     : praveen.ka
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page contentType="text/html"%>
<%-- Uncomment below lines to add portlet taglibs to jsp--%>
<%@ page import="javax.portlet.*"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>

<portlet:defineObjects />
<%PortletPreferences prefs = renderRequest.getPreferences();%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>JSP Page</title>
    </head>
    <%
       
        String votes = request.getParameter("123");
        String pollQ = (String) portletSession.getAttribute("pollQ");
     //   Database db = new Database();
     //   db.getPollID();
     //   db.insertRow(pollQ,votes);
    %>
    <body>
        <h3><%= pollQ %></h3>
        <h1><%= votes%></h1>
    </body>
</html>
 