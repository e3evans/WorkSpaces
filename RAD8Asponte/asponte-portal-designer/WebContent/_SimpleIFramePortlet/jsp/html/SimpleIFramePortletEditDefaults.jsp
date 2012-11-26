<%@page session="false" contentType="text/html" pageEncoding="UTF-8" import="javax.portlet.*,com.asponte.portal.designer.portlets.*" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>        
<portlet:defineObjects/>      
<% 
  	IFrameConfigBean iframeConfig=(IFrameConfigBean)renderRequest.getAttribute(Constants.IFRAME_CONFIG_BEAN);
  	String ns=renderResponse.getNamespace();
%>
<div>
  <form action="<portlet:actionURL/>" method="post">
    <label for="<%=ns%>url">URL</label><br />
    <input id="<%=ns%>url" name="<%=Constants.IFRAME_URL%>" value="<%=iframeConfig.getUrl()%>" type="text"/><br />
    <label for="<%=ns%>width">Width</label><br />
    <input id="<%=ns%>width" name="<%=Constants.IFRAME_WIDTH%>" value="<%=iframeConfig.getWidth()%>" type="text"/><br />
    <label for="<%=ns%>height">Height</label><br />
    <input id="<%=ns%>height" name="<%=Constants.IFRAME_HEIGHT%>" value="<%=iframeConfig.getHeight()%>" type="text"/><br />
    <label for="<%=ns%>scrolling">Scrolling</label><br />
    <select id="<%=ns%>scrolling" name="<%=Constants.IFRAME_SCROLLING%>" type="text">
    	<option value="yes" <%if(iframeConfig.getScrolling().equalsIgnoreCase("yes")){%>selected="selected"<%}%>>Yes</option>
    	<option value="no" <%if(iframeConfig.getScrolling().equalsIgnoreCase("no")){%>selected="selected"<%}%>>No</option>
    	<option value="auto" <%if(iframeConfig.getScrolling().equalsIgnoreCase("auto")){%>selected="selected"<%}%>>Auto</option>
    </select><br />
    <INPUT name="<%=Constants.EDIT_DEFAULTS_SUBMIT%>" value="Save" type="submit"/>
  </form>
</div>
