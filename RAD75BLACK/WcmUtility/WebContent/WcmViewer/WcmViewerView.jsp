<%-- jsf:pagecode language="java" location="/src/pagecode/WcmViewer/WcmViewerView.java" --%><%-- /jsf:pagecode --%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@page language="java" contentType="text/html"
	pageEncoding="ISO-8859-1" session="false"%>
<portlet:defineObjects />
<link rel="stylesheet" type="text/css"
	href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/stylesheet.css") %>'
	title="Style">
	<script type="text/javascript"
		src='<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/jslib/global.js") %>'></script>
	<script type="text/javascript">
		var wcmUtilitySubmitId = 'view<portlet:namespace/>:wcmutility';
		var wcmRedirectButtonId = wcmUtilitySubmitId+':redirect';
		var wcmRedirectURLFieldId = wcmUtilitySubmitId+':redirectUrl';
		
		function wcmPageRedirect(url){
			var urlField = document.getElementById(wcmRedirectURLFieldId);
			var subBtn = document.getElementById(wcmRedirectButtonId);
			urlField.value=url; 
			subBtn.click();
		}
	</script>
	<f:view>
		<hx:scriptCollector id="scriptCollector1" >
			<div style="visibility:hidden;position:absolute;top:-100px;left:-100px">
			<h:form id="wcmutility" styleClass="form" onclick="return #{facesContext.externalContext.response.namespace}func_1(this, event);">
				<hx:commandExButton type="submit" value="Submit" id="redirect" styleClass="commandExButton" action="#{pc_WcmViewerView.doRedirectAction}"/>
				<h:inputText id="redirectUrl" styleClass="inputText"/>
			</h:form>
			</div>
		</hx:scriptCollector>
	</f:view>