<%@ page session="false" isELIgnored="false" import="java.util.*,javax.portlet.*,com.asponte.portal.designer.*" 
	%><%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"
	%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"
	%><fmt:setBundle basename="com.asponte.portal.designer.nl.PageElementPortletResource" 
	/><portlet:defineObjects/><%
String ns=renderResponse.getNamespace();
final String METHOD_NAME="dcat.jsp";
if(Jsp.LOGGER.isLoggable(java.util.logging.Level.FINER)){Jsp.LOGGER.finest(METHOD_NAME+": ENTRY ("+ns+")");}
%><script type="text/javascript" language="JavaScript">
dojo.require('dijit.form.ValidationTextBox');
</script>
<div id="<%=ns%>dcatBuilder">
	<div>
		<label for="<%=ns%>dcatPageTitle" title="<fmt:message key="dcat_builder_title_label_tip" />"><fmt:message key="dcat_builder_title_label" /></label><br />
		<input type="text" name="doc_catalog_title" id="<%=ns%>dcatPageTitle" value="" dojoType="dijit.form.ValidationTextBox" trim="true" propercase="true" required="true" invalidMessage="<fmt:message key="dcat_builder_invalid_title_message" />" regExp="[^\s].*" /><br/>
		<label for="<%=ns%>dcatPageSubTitle" title="<fmt:message key="dcat_builder_subTitle_label_tip" />"><fmt:message key="dcat_builder_subTitle_label" /></label><br />
		<input type="text" name="doc_catalog_subTitle" id="<%=ns%>dcatPageSubTitle" value="" dojoType="dijit.form.ValidationTextBox" trim="true" propercase="true" required="true" invalidMessage="<fmt:message key="dcat_builder_invalid_subTitle_message" />" regExp="[^\s].*" />
	</div>
</div><%
if(Jsp.LOGGER.isLoggable(java.util.logging.Level.FINER)){Jsp.LOGGER.finest(METHOD_NAME+": EXIT ("+ns+")");}
%>