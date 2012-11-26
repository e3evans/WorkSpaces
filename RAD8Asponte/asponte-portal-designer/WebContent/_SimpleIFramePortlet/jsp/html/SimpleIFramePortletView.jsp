<%@page session="false" contentType="text/html" pageEncoding="UTF-8" import="java.util.*,javax.portlet.*,com.asponte.portal.designer.portlets.*" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>        
<portlet:defineObjects/>
<%
	IFrameConfigBean iframeConfig=(IFrameConfigBean)renderRequest.getAttribute(Constants.IFRAME_CONFIG_BEAN);
	String ns=renderResponse.getNamespace();
%>
<iframe id="<%=ns%>iframe" name="<%=ns%>iframe" width="<%=iframeConfig.getWidth()%>" height="<%=iframeConfig.getHeight()%>" frameborder="0" scrolling="<%=iframeConfig.getScrolling()%>" src="<%=iframeConfig.getUrl()%>"></iframe>
