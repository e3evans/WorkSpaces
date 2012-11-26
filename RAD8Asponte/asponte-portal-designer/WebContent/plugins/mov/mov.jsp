<%@ page session="false" isELIgnored="false" import="java.util.*,javax.portlet.*,com.asponte.portal.designer.*" 
	%><%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"
	%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"
	%><fmt:setBundle basename="com.asponte.portal.designer.nl.PageElementPortletResource" 
	/><portlet:defineObjects/><%
String ns=renderResponse.getNamespace();
final String METHOD_NAME="mov.jsp";
if(Jsp.LOGGER.isLoggable(java.util.logging.Level.FINER)){Jsp.LOGGER.finest(METHOD_NAME+": ENTRY ("+ns+")");}
org.eclipse.core.runtime.IConfigurationElement ice=(org.eclipse.core.runtime.IConfigurationElement)renderRequest.getAttribute(Constants.PAGE_ELEMENT_CONFIG);
%><script type="text/javascript" language="JavaScript">
dojo.require('dijit.form.ValidationTextBox');
</script>
<div id="<%=ns%>movBuilder">
	<div>
<%org.eclipse.core.runtime.IConfigurationElement []styles=ice.getChildren("style");
if(styles.length>0){%>
        <label for="<%=ns%>videoStyle" title="<fmt:message key="mov_builder_style_label_tip" />"><fmt:message key="mov_builder_style_label" /></label><br />
        <select name="video_style" id="<%=ns%>videoStyle">
<%for(int ii=0;ii<styles.length;ii++){
    String title=styles[ii].getAttribute("title");
    String styleId=styles[ii].getAttribute("styleId");
    if(title!=null&&styleId!=null){
    %>
        <option value="<%=styleId%>"><%=title%></option>
    <%}
}%>
        </select><br />
<%}%>		
		<label for="<%=ns%>movPageTitle" title="<fmt:message key="mov_builder_title_label_tip" />"><fmt:message key="mov_builder_title_label" /></label><br />
		<input type="text" name="video_title" id="<%=ns%>movPageTitle" value="" dojoType="dijit.form.ValidationTextBox" trim="true" propercase="true" required="true" invalidMessage="<fmt:message key="mov_builder_invalid_title_message" />" regExp="[^\s].*" />
	</div>
</div><%
if(Jsp.LOGGER.isLoggable(java.util.logging.Level.FINER)){Jsp.LOGGER.finest(METHOD_NAME+": EXIT ("+ns+")");}
%>