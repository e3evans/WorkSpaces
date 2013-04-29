<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/GEMTNotesEdit.java" --%><%-- /jsf:pagecode --%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ page language="java" contentType="text/html" pageEncoding="ISO-8859-1" session="false"%>
<portlet:defineObjects />
<f:view>
	<hx:scriptCollector id="scriptCollector1" preRender="#{pc_GEMTNotesFormView.onPageLoadBegin}">
	<h:form styleClass="form" id="form1" style="font-size:9.5pt;font-family:Arial;">
			
			
				 
			<TABLE Class="GEMTTable" border="0" cellpadding="0" cellspacing="0" style="margin-left:5px;">
			<TBODY>
			<TR>
				<TD>
				
				
					
					
						
							<h:outputText value="Notes"/>
						</TD>
						<TD>
						
							<h:outputText id="gemt_notes_content" value="#{sessionScope.GemtNotesUIBean.gemt_notes_content}"/>
				</TD>
				</TR>
				
				
				
				
			</TBODY>
			</TABLE>
	</h:form>
	</hx:scriptCollector>
</f:view>