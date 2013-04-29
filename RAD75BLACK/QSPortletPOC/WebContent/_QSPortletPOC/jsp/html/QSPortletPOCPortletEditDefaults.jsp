<%@page session="false" contentType="text/html" pageEncoding="ISO-8859-1" import="javax.portlet.*,com.ibm.qsportletpoc.*" %>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>        
<%@taglib uri="http://www.ibm.com/xmlns/prod/websphere/portal/v6.1/portlet-client-model" prefix="portlet-client-model" %>        
<portlet:defineObjects/>
<portlet-client-model:init>
      <portlet-client-model:require module="ibm.portal.xml.*"/>
      <portlet-client-model:require module="ibm.portal.portlet.*"/>   
</portlet-client-model:init>         

<DIV style="margin: 6px">

<H3 style="margin-bottom: 3px">Welcome!</H3>
This is a sample edit shared settings mode page.  You have to edit this page to customize it for your own use.<BR>
<H3 style="margin-bottom: 3px">Write to the PortletPreferences</H3>
This is a sample form to demonstrate how to change the PortletPreferences when the portlet is in edit shared settings mode.
To see these changes, you must log out and log back in as another user.
<DIV style="margin: 12px; margin-bottom: 36px">
<% /******** Start of sample code ********/ %>
<% 
  PortletPreferences preferences = renderRequest.getPreferences();
  if (preferences != null) {
    String value = (String)preferences.getValue(com.ibm.qsportletpoc.QSPortletPOCPortlet.EDIT_DEFAULTS_KEY, "");
%> 
  <FORM ACTION="<portlet:actionURL/>" METHOD="POST">
    <LABEL for="<%=com.ibm.qsportletpoc.QSPortletPOCPortlet.EDIT_DEFAULTS_TEXT%>">New Value</LABEL><BR>
    <INPUT name="<%=com.ibm.qsportletpoc.QSPortletPOCPortlet.EDIT_DEFAULTS_TEXT%>" value="<%=value%>" type="text"/><BR>
    <INPUT name="<%=com.ibm.qsportletpoc.QSPortletPOCPortlet.EDIT_DEFAULTS_SUBMIT%>" value="Save" type="submit"/>
  </FORM>
<%
  } else {
  %>Error: PortletPreferences is null.<%
  }
%>
<% /******** End of sample code *********/ %>
</DIV>
</DIV>