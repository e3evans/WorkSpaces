<%@page import="com.ibm.googlemaps.controllers.GoogleMapsViewController"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<portlet:defineObjects />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<c:url var="images" value="/images"/>
<style type="text/css">
  html { height: 100% }
  body { height: 100%; margin: 0; padding: 0 }
  #map_canvas { height: 100% }
</style>
<script type="text/javascript"
    src="http://maps.googleapis.com/maps/api/js?sensor=true">
</script>
<script type="text/javascript">
  var map;
  var d = <%=request.getAttribute(GoogleMapsViewController.REQ_DISTRIBS) %>;  
  
  function initialize() {
  //42.95   87.90 
    var latlng = new google.maps.LatLng(42.454371,-89.088744);
    var myOptions = {
      zoom: 8,
      center: latlng,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    map = new google.maps.Map(document.getElementById("map_canvas"),
        myOptions);
        
   
    
    var contentString = '<div id="content">'+
        '<div id="siteNotice">'+
        '</div>'+
        '<h1 id="firstHeading" class="firstHeading">Taylor</h1>'+
        '<div id="bodyContent">'+
        '<p>Taylor has set the pace in the foodservice industry since 1926. Custom-built equipment, simple operational features and a strong worldwide service support system are key reasons foodservice operators look to Taylor.</p>'+
        '<p><a href="http://www.taylor-company.com/about/aboutTaylor.htm">'+
        'Taylor Company</a></p>'+
        '</div>'+
        '</div>';
        
    var infowindow = new google.maps.InfoWindow({
        content: contentString
    });
	 var image = '<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/images/taylor.png") %>';
    var marker = new google.maps.Marker({
        position: latlng,
        map: map,
        title: 'Taylor Company (Rockton, Illinos)',
        animation: google.maps.Animation.DROP,
        icon:image
    });
    google.maps.event.addListener(marker, 'click', function() {
      infowindow.open(map,marker);
    });    
        
  }
  
  function addMarker(){
   	 var latlng = new google.maps.LatLng(42.444371,-89.088744);
  	 var marker = new google.maps.Marker({
        position: latlng,
        map: map,
        title: 'Taylor Company (Distributor)',
        animation: google.maps.Animation.DROP
    });
  }
  
  function addMarkers(){
  	var lat = 42.444371;
  	var lng = -89.088744;
  	for (i=0;i<10;i++){
  		lat = lat +.1;
  		var latlng = new google.maps.LatLng(lat,lng);
  		var marker = new google.maps.Marker({
        position: latlng,
        map: map,
        title: 'Taylor Company (Distributor)',
        animation: google.maps.Animation.DROP
    	});
  	}
  
  }
  function addMarker(marker){
  	var latlng = new google.maps.LatLng(marker.latitude,marker.longitude);
  	var mark = new google.maps.Marker({
  		position: latlng,
  		map:map,
  		title:marker.name,
  		icon:marker.icon,
  		animation:google.maps.Animation.DROP
  	});
  }
  
  function testing(){
  	var arr = d.distributors;
  	for (var i=0;i<arr.length;i++){
  		addMarker(arr[i]);  	
  	}

  
  }
  
  window.onload=initialize;

</script>

<div id="map_canvas" style="width:100%; height:500px;border:1px solid red;"></div>
<p>
	<img border="1"
		src='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/images/taylor.png") %>'
		width="16" height="12">
</p>

<a href="<portlet:resourceURL><portlet:param name="action" value="getDistributors"/></portlet:resourceURL>">TESTING</a>
<input type="button" onclick="addMarkers()" value="test"/>
  <input type="button" onclick="testing()" value="X"/>