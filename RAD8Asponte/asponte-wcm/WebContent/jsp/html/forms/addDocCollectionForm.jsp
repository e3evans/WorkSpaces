<%@ page session="false" isELIgnored="false" import="java.util.*,java.util.logging.*,com.ibm.workplace.wcm.api.*,com.ibm.workplace.wcm.api.exceptions.*,com.asponte.commons.*,com.asponte.commons.portal.*,com.asponte.wcm.widgets.*"
	%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"
	%><fmt:setBundle basename="com.asponte.wcm.widgets.resources.strings"
/><div>
	<form action="/aww/jsp/html/actions/dcatAddCollection.jsp" method="post" name="AwwAddDocCollectionForm" id="AwwAddDocCollectionForm">
		<input type="hidden" name="awwAction" value="save" />
		<input type="hidden" name="catalogId" value="" />
		<label for="AwwAddDocCollectionTitle" title="<fmt:message key="<%=Results.DOCCAT_ADD_BUILDER_TITLE_LABEL%>"/>"><fmt:message key="<%=Results.DOCCAT_ADD_BUILDER_TITLE_LABEL%>" /></label><br />
		<input type="text" name="collectionTitle" id="AwwAddDocCollectionTitle" value="" dojoType="dijit.form.TextBox" trim="true" propercase="true" />
	</form>
</div>