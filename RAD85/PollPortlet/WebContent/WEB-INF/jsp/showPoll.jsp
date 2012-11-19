<%-- 
    Document   : showPoll
    Created on : 29 Aug, 2012, 6:17:19 PM
    Author     : praveen.ka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page contentType="text/html"%>
<%-- Uncomment below lines to add portlet taglibs to jsp--%>
<%@ page import="javax.portlet.*"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>

<portlet:defineObjects />
<%PortletPreferences prefs = renderRequest.getPreferences();%> 
<%
    String pollQ = request.getParameter("pollQ");
    String choice1 = request.getParameter("choice1");
    String choice2 = request.getParameter("choice2");
    request.setAttribute("pollQ", pollQ);
    
%>
<portlet:renderURL var="renderUrl">
	<portlet:param name="myaction" value="Votes"/>
</portlet:renderURL>
<html>
    <head>
        <script type="text/javascript">
            function check(){
                document.getElementById("yes").checked=true
                var value =  document.getElementById("yes").checked.value();
                alert(value);
            }

            function getRadioValue(id) {
                var btnid = id;
                alert("btnid="+btnid);
                var radioBtn = document.getElementById(id);
                alert(radioBtn.value);
            }  
        </script>

    </head>
    <body>
        <form id="mainForm" action="${renderUrl}" method="POST">
            <h2><%= pollQ%></h2>
            <%   request.setAttribute("pollQ", pollQ);  %>
            <%
            portletSession.setAttribute("pollQ", pollQ);
            %>
            <table><tr><td>
                        <input type="radio" id="111" name="123" value="yes" onclick="getRadioValue(this.id)" /></td><td><%= choice1%>    
                    </td></tr>
                <tr><td>
                        <input type="radio" id="1" name="123" value="no" onclick="getRadioValue(this.id)"/></td><td><%= choice2%>    
                    </td></tr>
            </table>
            <input type="submit" name="vote" onclick="getValue()" value="Vote"/>
        </form>
    </body>
</html>

<%-- Votes Controller --%>