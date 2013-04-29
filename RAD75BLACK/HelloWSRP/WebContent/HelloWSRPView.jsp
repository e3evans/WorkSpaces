<%-- jsf:pagecode language="java" location="/src/pagecode/HelloWSRPView.java" --%><%-- /jsf:pagecode --%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>                
<%@ page language="java" contentType="text/html" pageEncoding="ISO-8859-1" session="false"
	import="java.util.Enumeration,java.util.Set,java.util.Map,java.util.Iterator"
%>

<%@page import="javax.portlet.PortletRequest"%><portlet:defineObjects />  
LOCAL ATTRIBUTES<br>
<table cellpadding="5">
<%
	Map userInfo = (Map) renderRequest.getAttribute(PortletRequest.USER_INFO);
        if (userInfo != null) {
	        Set keys = userInfo.keySet();
	        Iterator i = keys.iterator();
	        boolean everyother=false;
		    while(i.hasNext()) {
		      String k = (String)i.next();
		      String cellStyle=" style=\"border:1px solid #000000;background-color:#e1e1e1;\" ";
		      if (everyother){
		      	cellStyle=" style=\"border:1px solid #000000;\" ";
		      	everyother=false;
		      }else{
		      	everyother=true;
		      }
		      %><tr><td<%=cellStyle%>><%=k%></td><td<%=cellStyle%>><%=userInfo.get(k)%></td></tr><%	     
		    } 
        }
	
	
%>
</table>

<hr>

<%=userInfo.get("user.home-info.online.email") %>

<f:view>

</f:view>
