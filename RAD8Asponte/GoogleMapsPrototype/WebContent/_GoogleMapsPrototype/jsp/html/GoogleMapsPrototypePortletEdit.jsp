<%@page session="false" contentType="text/html" pageEncoding="ISO-8859-1" import="java.util.*,javax.portlet.*,com.asponte.googlemapsprototype.*"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>                 
<%@taglib uri="http://www.ibm.com/xmlns/prod/websphere/portal/v6.1/portlet-client-model" prefix="portlet-client-model" %>        
<portlet:defineObjects/>
<portlet-client-model:init>
      <portlet-client-model:require module="ibm.portal.xml.*"/>
      <portlet-client-model:require module="ibm.portal.portlet.*"/>   
</portlet-client-model:init>         
 
<script type="text/javascript"
    src="<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/js/jquery-1.7.1.js") %>">
</script> 
<script type="text/javascript"
    src="http://maps.googleapis.com/maps/api/js?sensor=true">
</script>       
<script>
//http://code.google.com/apis/maps/documentation/javascript/services.html#Geocoding
//http://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&sensor=true_or_false
//$.getJSON("http://maps.googleapis.com/maps/api/geocode/json?address=1600+Ahitheatre+Parkway,+Mountain+View,+CA&sensor=false&output=json&callback=?",
//  function(data, textStatus){
//     console.log(data);
//  });
  var geocoder;
  
   function codeAddress() {
   geocoder = new google.maps.Geocoder();
    var address = '1600 Amphitheatre Parkway, Mountain+View, CA';
    geocoder.geocode( { 'address': address}, function(results, status) {
      if (status == google.maps.GeocoderStatus.OK) {
        //map.setCenter(results[0].geometry.location);
        alert(results[0].geometry.location);
//        var marker = new google.maps.Marker({
//            map: map,
//            position: results[0].geometry.location
//        });
      } else {
        alert("Geocode was not successful for the following reason: " + status);
      }
    });
  }
  codeAddress();
  

</script>
<DIV style="margin: 6px">
<H3 style="margin-bottom: 3px">Welcome!</H3>
This is a sample edit mode page.  You have to edit this page to customize it for your own use.<BR>
<H3 style="margin-bottom: 3px">Write to the PortletPreferences</H3>
This is a sample form to demonstrate how to change the PortletPreferences when the portlet is in edit mode.
<DIV style="margin: 12px; margin-bottom: 36px">
<% /******** Start of sample code ********/ %>
<% 
  PortletPreferences preferences = renderRequest.getPreferences();
  if( preferences!=null ) {
    String value = (String)preferences.getValue(com.asponte.googlemapsprototype.GoogleMapsPrototypePortlet.EDIT_KEY,"");
%> 
  <FORM ACTION="<portlet:actionURL/>" METHOD="POST">
    <LABEL for="<%=com.asponte.googlemapsprototype.GoogleMapsPrototypePortlet.EDIT_TEXT%>">New Value</LABEL><BR>
    <INPUT name="<%=com.asponte.googlemapsprototype.GoogleMapsPrototypePortlet.EDIT_TEXT%>" value="<%=value%>" type="text"/><BR>
    <INPUT name="<%=com.asponte.googlemapsprototype.GoogleMapsPrototypePortlet.EDIT_SUBMIT%>" value="Save" type="submit"/>
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