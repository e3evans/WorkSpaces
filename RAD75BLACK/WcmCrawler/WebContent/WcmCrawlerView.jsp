<%-- jsf:pagecode language="java" location="/src/pagecode/WcmCrawlerView.java" --%><%-- /jsf:pagecode --%><%@taglib
	uri="http://java.sun.com/jsf/core" prefix="f"%><%@taglib
	uri="http://java.sun.com/portlet" prefix="portlet"%><%@taglib
	uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%><%@taglib
	uri="http://java.sun.com/jsf/html" prefix="h"%><%@page language="java"
	contentType="text/html" pageEncoding="ISO-8859-1" session="false"%><portlet:defineObjects />
<link rel="stylesheet" type="text/css" title="Style"
	href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/stylesheet.css") %>'>
<f:view><hx:scriptCollector id="scriptCollector1" preRender="#{pc_WcmCrawlerView.onPageLoadBegin}">

		<h:form styleClass="form" id="wcmCrawlerSelector">
		Select Country Library:   
			<h:selectOneMenu styleClass="selectOneMenu" id="LibrarySelector">
				<f:selectItem itemLabel="Label1" itemValue="test" id="selectItem1" />
				<f:selectItems value="#{sessionScope.libItems}" id="selectItems1" />
			</h:selectOneMenu>
			<hr>
		Search for String: <h:inputText styleClass="inputText" id="searchtext"></h:inputText>&nbsp;&nbsp;
			<hx:commandExButton type="submit" value="Search for text -->"
				styleClass="commandExButton" id="search" action="#{pc_WcmCrawlerView.doSearchAction}"></hx:commandExButton>
			<hr>
			<h:outputText styleClass="outputText" id="fi" value="#{sessionScope.foundissues}" escape="false"></h:outputText>
		</h:form>
	</hx:scriptCollector>
</f:view>