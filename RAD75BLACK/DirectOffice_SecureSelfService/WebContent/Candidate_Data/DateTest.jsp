<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<portlet:defineObjects />


<LINK rel="stylesheet" type="text/css"
	href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/myManpowerStylesheet.css") %>'
	title="Style">
<script type="text/javascript" src='<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/js/functions.js") %>'></script>
<script type="text/javascript" src='<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/js/usavalidation.js") %>'></script>

<style type="text/css">
    @import "dojo-release-1.2.3/dijit/themes/tundra/tundra.css";
    @import "dojo-release-1.2.3/dojo/resources/dojo.css"
</style>
<script type="text/javascript" 
	src='<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/js/dojo/dojo.js") %>' 
    djConfig="parseOnLoad: true"></script>
<script type="text/javascript">
    dojo.require("dojo.parser");
    dojo.require("dijit.form.DateTextBox");
</script>

<f:view>
	<input type="text" name="date1" value="2005-12-30"
                dojoType="dijit.form.DateTextBox"
                required="true" />
</f:view>
