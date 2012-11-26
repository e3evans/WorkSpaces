<%@ page session="false" isELIgnored="false" import="java.util.*,javax.portlet.*,com.asponte.portal.designer.Jsp" 
	%><%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"
	%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"
	%><fmt:setBundle basename="com.asponte.portal.designer.nl.PageElementPortletResource" 
	/><portlet:defineObjects/><%
String ns=renderResponse.getNamespace();
final String METHOD_NAME="website.jsp";
if(Jsp.LOGGER.isLoggable(java.util.logging.Level.FINER)){Jsp.LOGGER.finest(METHOD_NAME+": ENTRY ("+ns+")");}
%><script type="text/javascript" language="JavaScript">
dojo.require('dijit.form.ValidationTextBox');
dojo.require('dojox.validate');
dojo.require('dojox.validate.web');
</script>
<div id="<%=ns%>websiteBuilder">
	<div>
		<label for="<%=ns%>websiteUrl" title="<fmt:message key="website_builder_url_label_tip" />"><fmt:message key="website_builder_url_label" /></label><br />
		<input type="text" name="website.element.url" id="<%=ns%>websiteUrl" value="" dojoType="dijit.form.ValidationTextBox" trim="true" required="true" invalidMessage="<fmt:message key="website_builder_invalid_url_message" />" validator="dojox.validate.isUrl" /><br />
		<label for="<%=ns%>websiteWidth" title="<fmt:message key="website_builder_width_label_tip" />"><fmt:message key="website_builder_width_label" /></label><br />
		<input type="text" name="website.element.width" id="<%=ns%>websiteWidth" value="" dojoType="dijit.form.ValidationTextBox" trim="true" required="true" invalidMessage="<fmt:message key="website_builder_invalid_length_message" />" regExp="^[0-9]+[%]?" /><br />
		<label for="<%=ns%>websiteHeight" title="<fmt:message key="website_builder_height_label_tip" />"><fmt:message key="website_builder_height_label" /></label><br />
		<input type="text" name="website.element.height" id="<%=ns%>websiteHeight" value="" dojoType="dijit.form.ValidationTextBox" trim="true" required="true" invalidMessage="<fmt:message key="website_builder_invalid_length_message" />" regExp="^[0-9]+[%]?" /><br />
		<label for="<%=ns%>websiteScrolling" title="<fmt:message key="website_builder_scrolling_label_tip" />"><fmt:message key="website_builder_scrolling_label" /></label><br />
		<select name="website.element.scrolling" id="<%=ns%>websiteScrolling">
	    	<option value="yes"><fmt:message key="website_builder_scrolling_yes" /></option>
	    	<option value="no"><fmt:message key="website_builder_scrolling_no" /></option>
	    	<option value="auto"><fmt:message key="website_builder_scrolling_auto" /></option>			
		</select>
	</div>
</div><%
if(Jsp.LOGGER.isLoggable(java.util.logging.Level.FINER)){Jsp.LOGGER.finest(METHOD_NAME+": EXIT ("+ns+")");}
%>