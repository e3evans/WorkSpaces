<%-- jsf:pagecode language="java" location="/src/pagecode/JSR286TestView.java" --%><%-- /jsf:pagecode --%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>        
<%@taglib uri="http://www.ibm.com/xmlns/prod/websphere/portal/v6.1/portlet-client-model"   
       prefix="portlet-client-model" %>              
<%@ page language="java" contentType="text/html" pageEncoding="ISO-8859-1" session="false" import="java.util.Enumeration,java.util.Set,java.util.Map,java.util.Iterator,javax.portlet.PortletRequest"%>
<portlet:defineObjects />
   
<h3>
	JAVA 286 ATTRIBS
</h3>
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
        
        Map userInfo2 = (Map) request.getAttribute(PortletRequest.USER_INFO);
        
        
String loginId = (userInfo2!=null)? (String) userInfo2.get(PortletRequest.P3PUserInfos.USER_LOGIN_ID.toString()) : "";
String firstname = (userInfo2!=null)? (String) userInfo2.get(PortletRequest.P3PUserInfos.USER_NAME_GIVEN.toString()) : "";
String lastname = (userInfo2!=null)? (String) userInfo2.get(PortletRequest.P3PUserInfos.USER_NAME_FAMILY.toString()) : "";
	
%>
</table>
Login ID: <%=loginId %><hr>
First Name: <%=firstname %><hr>
Last Name: <%=lastname %><hr>
     
