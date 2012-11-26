<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<portlet:defineObjects />

<h1>First Spring Portlet</h1>

<portlet:renderURL var="pageTwoUrl">
	<portlet:param name="page" value="page2"/>
	<portlet:param name="action" value="example"/>
</portlet:renderURL>

<p><a href="${pageTwoUrl }">page two</a></p>


<%= renderResponse.encodeURL(renderRequest.getContextPath()) %>

<img border="0"
	src='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/test.jpg") %>'
	width="0" height="0">
<portlet:actionURL var="actionUrl">
</portlet:actionURL>

<p><a href="${actionUrl }">Action Link</a></p>