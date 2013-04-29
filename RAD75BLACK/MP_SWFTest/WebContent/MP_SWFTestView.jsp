<%-- jsf:pagecode language="java" location="/src/pagecode/MP_SWFTestView.java" --%><%-- /jsf:pagecode --%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>        
<%@taglib uri="http://www.ibm.com/xmlns/prod/websphere/portal/v6.1/portlet-client-model"   
       prefix="portlet-client-model" %>              
<%@ page language="java" contentType="text/html" pageEncoding="ISO-8859-1" session="false"%>
<portlet:defineObjects />
    
<OBJECT classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,40,0" WIDTH="1000" HEIGHT="600" id="myMovieName">
			<PARAM NAME=movie VALUE="<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/swffiles/Stats.swf") %>">
			<PARAM NAME=quality VALUE=high>
			<PARAM NAME=bgcolor VALUE=#FFFFFF>
			<EMBED href="/support/flash/ts/documents/myFlashMovie.swf" 
			quality=high bgcolor=#FFFFFF WIDTH="1000" HEIGHT="600" NAME="myMovieName" ALIGN="" 
			TYPE="application/x-shockwave-flash" 
			PLUGINSPAGE="http://www.macromedia.com/go/getflashplayer">
			</EMBED>
		</OBJECT> 
		
		<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
			id="ContributorCarousel" width="663" height="277"
			codebase="http://fpdownload.macromedia.com/get/flashplayer/current/swflash.cab">
			<param name="movie" value="ContributorCarousel.swf" />
			<param name="quality" value="high" />
			<param name="bgcolor" value="#ffffff" />
			<param name="allowScriptAccess" value="sameDomain" />
			<embed src="ContributorCarousel.swf" quality="high" bgcolor="#ffffff"
				width="663" height="277" name="ContributorCarousel" align="middle"
				play="true"
				loop="false"
				quality="high"
				allowScriptAccess="sameDomain"
				type="application/x-shockwave-flash"
				pluginspage="http://www.adobe.com/go/getflashplayer">
			</embed>
	</object>
		