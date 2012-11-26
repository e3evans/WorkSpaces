<%@ page session="false" isELIgnored="false" import="java.util.*,javax.portlet.*,com.asponte.portal.designer.Jsp" 
	%><%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"
	%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"
	%><fmt:setBundle basename="com.asponte.portal.designer.nl.PageElementPortletResource" 
	/><portlet:defineObjects/><%
String ns=renderResponse.getNamespace();
final String METHOD_NAME="feed.jsp";
if(Jsp.LOGGER.isLoggable(java.util.logging.Level.FINER)){Jsp.LOGGER.finest(METHOD_NAME+": ENTRY ("+ns+")");}
%><script type="text/javascript" language="JavaScript">
dojo.require('dijit.form.ValidationTextBox');
dojo.require('dojox.validate');
dojo.require('dojox.validate.web');
</script>
<div id="<%=ns%>feedBuilder">
	<div>
		<label for="<%=ns%>feedTitle" title="<fmt:message key="feed_builder_title_label_tip" />"><fmt:message key="feed_builder_title_label" /></label><br />
		<input type="text" name="feed.element.title" id="<%=ns%>feedTitle" value="" dojoType="dijit.form.ValidationTextBox" trim="true" propercase="true" required="true" invalidMessage="<fmt:message key="feed_builder_invalid_title_message" />" regExp="[^\s].*" /><br />
		<label for="<%=ns%>feedUrl" title="<fmt:message key="feed_builder_url_label_tip" />"><fmt:message key="feed_builder_url_label" /></label><br />
		<input type="text" name="feed.element.url" id="<%=ns%>feedUrl" value="" dojoType="dijit.form.ValidationTextBox" trim="true" required="true" invalidMessage="<fmt:message key="feed_builder_invalid_url_message" />" validator="dojox.validate.isUrl" />
	</div>
</div><%
if(Jsp.LOGGER.isLoggable(java.util.logging.Level.FINER)){Jsp.LOGGER.finest(METHOD_NAME+": EXIT ("+ns+")");}
%>