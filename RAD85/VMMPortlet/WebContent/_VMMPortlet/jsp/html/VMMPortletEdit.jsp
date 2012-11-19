<%@page session="false" contentType="text/html" pageEncoding="ISO-8859-1" import="java.util.*,javax.portlet.*,com.asponte.vmmportlet.*"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>                 
<%@taglib uri="http://www.ibm.com/xmlns/prod/websphere/portal/v6.1/portlet-client-model" prefix="portlet-client-model" %>        
<portlet:defineObjects/>
<portlet-client-model:init>
      <portlet-client-model:require module="ibm.portal.xml.*"/>
      <portlet-client-model:require module="ibm.portal.portlet.*"/>   
</portlet-client-model:init>         
        

<DIV style="margin: 6px">
<H3 style="margin-bottom: 3px">Welcome!</H3>
This is a sample edit mode page.  You have to edit this page to customize it for your own use.<BR>
<H3 style="margin-bottom: 3px">Write to the PortletPreferences</H3>
This is a sample form to demonstrate how to change the PortletPreferences when the portlet is in edit mode.
<DIV style="margin: 12px; margin-bottom: 36px">
<% /******** Start of sample code ********/ %>
  <FORM ACTION="<portlet:actionURL/>" METHOD="POST">
<% 
  PortletPreferences prefs = renderRequest.getPreferences();
  Enumeration e = prefs.getNames();
  while (e.hasMoreElements()){
  	String prefName = (String)e.nextElement();
  	String prefValue = prefs.getValue(prefName,"");
  	if (prefName.indexOf(".")<0){
  	%><%=prefName %>:  <INPUT name="PREF_<%=prefName%>" value="<%=prefValue%>"/><br><%
  	}
  }
  
  
  if( prefs!=null ) {
    String value = (String)prefs.getValue(com.asponte.vmmportlet.VMMPortlet.EDIT_KEY,"");
%> 
    <INPUT name="<%=com.asponte.vmmportlet.VMMPortlet.EDIT_SUBMIT%>" value="Save" type="submit"/>
  </FORM>
<%
  }
else {
  %>Error: PortletPreferences is null.<%
  }
%>
<% /******** End of sample code *********/ %>
</DIV>


<FORM ACTION='<portlet:renderURL portletMode="view"/>' METHOD="POST">
	<INPUT NAME="back" TYPE="submit" VALUE="Back to view mode">
</FORM>
</DIV>