<%@page session="false" 
	  	contentType="text/html" 
	  	pageEncoding="UTF-8" 
	  	import="java.util.*,javax.portlet.*,com.asponte.portal.designer.*,com.asponte.commons.*,java.util.logging.*" 
%><%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"
%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" 
	%><fmt:setBundle basename="com.asponte.portal.designer.nl.PageElementPortletResource" 
	/><portlet:defineObjects/><% 
final String METHOD_NAME="wizard.jsp";
if(Jsp.LOGGER.isLoggable(Level.FINER)){Jsp.LOGGER.entering(Jsp.CLASS_NAME,METHOD_NAME);} 
String ns=renderResponse.getNamespace(); 
List actionResults=(List)renderRequest.getAttribute(Constants.ACTION_RESULTS);
Boolean firstRun=(Boolean)renderRequest.getAttribute(Constants.FIRST_RUN);
boolean show;
if(firstRun!=null&&firstRun.booleanValue()){show=true;}
else{show=false;}
%><link rel="stylesheet" href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/css/Styles.css") %>' type="text/css">
<script type="text/javascript" src='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/js/Wizard.js") %>'></script>
<style type="text/css">
#<%=ns%>AwwWizardDialog{
	background-color:#eee;
}
#<%=ns%>AwwWizardDialog .dijitDialogPaneContent{
	background-color:#2a3740;
	padding:0px;
}
</style>
<script type="text/javascript" language="JavaScript">
dojo.require('dojo.parser');
dojo.require('dijit.layout.TabContainer');
dojo.require('dijit.layout.ContentPane');
dojo.require('dijit.form.Form');
dojo.require('dijit.form.Button');
dojo.addOnLoad(function(){dojo.parser.parse('<%=ns%>AwwWizard');});
</script>
<div class="awwUnconfigured">
<%if(actionResults!=null&&actionResults.size()>0){
	show=false;
%><h3><fmt:message key="wizard_errors" /></h3>
<ul class="awwMessages"><%
for(Iterator itr=actionResults.iterator();itr.hasNext();){
	Result result=(Result)itr.next();
%><li><%=result.getMessage(renderRequest.getLocale())%></li><%
}%></ul>
	<p><a href="javascript:dijit.byId('<%=ns%>AwwWizardDialog').show();"><fmt:message key="wizard_launch" /></a></p>
<%}else{%>
	<h3><fmt:message key="wizard_not_setup" /></h3>
	<p><a href="javascript:dijit.byId('<%=ns%>AwwWizardDialog').show();"><fmt:message key="wizard_launch" /></a></p>
<%}%>
</div>
<div id="<%=ns%>AwwWizard" class="awwWizard">
	<div dojoType="asponte.aww.Wizard" title='<fmt:message key="wizard_dialog_title" />' class="awwWizardDialog" id="<%=ns%>AwwWizardDialog" namespace="<%=ns%>" <%if(!show){%>showOnStartup="false"<%}%>>
	    <div dojoType="dijit.layout.TabContainer" class="awwWizardTabSet" id="<%=ns%>AwwWizardTabber">
		<%
	    org.eclipse.core.runtime.IExtensionRegistry extensionRegistry = (org.eclipse.core.runtime.IExtensionRegistry)portletConfig.getPortletContext().getAttribute(Constants.EXTENSION_REGISTRY);
		org.eclipse.core.runtime.IExtensionPoint ep=extensionRegistry.getExtensionPoint(Constants.WIDGET_TEMPLATES_EXTENSION_POINT_ID);
		if(ep!=null){
			org.eclipse.core.runtime.IExtension []exts=ep.getExtensions();
			for(int ii=0;ii<exts.length;ii++){
				org.eclipse.core.runtime.IConfigurationElement []configElements=exts[ii].getConfigurationElements();
				if(configElements!=null&&configElements.length>0){
					for(int jj=0;jj<configElements.length;jj++){
						org.eclipse.core.runtime.IConfigurationElement ice=configElements[jj];
						String id=exts[ii].getUniqueIdentifier();
						String name=ice.getAttribute("name");
						String icon=ice.getAttribute("icon");
						String title=ice.getAttribute("title");
						String description=ice.getAttribute("description");
						String path=ice.getAttribute("path");
						String listener=ice.getAttribute("listener");
						if(name!=null&&icon!=null&&title!=null&&description!=null&&path!=null){
	        				PortletRequestDispatcher rd=portletConfig.getPortletContext().getRequestDispatcher(path);
	        				if(rd!=null){	
	        					renderRequest.setAttribute(Constants.PAGE_ELEMENT_CONFIG,ice);			
	      %><div dojoType="dijit.layout.ContentPane" 
	             id="<%=ns+ii+"_"+jj+"_"%>AwwWizardBuilder" 
	             title='<img height="32" width="32" title="<%=title%>" alt="<%=title%>" src="<%=renderResponse.encodeURL(renderRequest.getContextPath() + icon)%>" /><h3><%=title%></h3>'
	             <%if(jj==0){%>selected="true"<%}%>>
	            <script type="dojo/connect">
					this.formName='<%=ns+ii+"_"+jj+"_"%>AwwWizardForm';
                    this.extensionId="<%=id%>";
                    this.templateName="<%=name%>";
                </script>
	            <div class="awwWizardTabPaneHeader"><h3><%=title%></h3><p><%=description%></p></div>
				<form id="<%=ns+ii+"_"+jj+"_"%>AwwWizardForm" dojoType="dijit.form.Form" name="<%=ns+ii+"_"+jj+"_"%>AwwWizardForm" method="post" action="<portlet:actionURL />">
					<script type="dojo/method" event="onValidStateChange" args="isValid">
						dijit.byId('<%=ns%>AwwWizardDialog').setInputValid(isValid); 
					</script> 
					<input type="hidden" name="<%=Constants.EXTENSION_ID%>" value="" />
					<input type="hidden" name="<%=Constants.TEMPLATE_NAME%>" value="" />	            
	         		<div class="awwWizardTabPaneContent"><%out.flush();rd.include(renderRequest,renderResponse);%></div>
	         	</form>
	         	<%if(listener!=null){%><script type="dojo/connect">this.addListener(new <%=listener%>());</script><%}%>
	        </div><%
	        				}
						}
					}
				}
			}
		}	    
	    %>
	    </div>
		<div class="awwWizardButtons"><button dojoType="dijit.form.Button" id="<%=ns%>AwwWizardSave" type="button" onClick="return dijit.byId('<%=ns%>AwwWizardDialog').finish();"><fmt:message key="wizard_save" /></button>&nbsp;<button dojoType="dijit.form.Button" id="<%=ns%>AwwWizardCancel" type="button" onClick="return dijit.byId('<%=ns%>AwwWizardDialog').cancel();"><fmt:message key="wizard_cancel" /></button></div>
	</div>
</div><%
if(Jsp.LOGGER.isLoggable(Level.FINER)){Jsp.LOGGER.exiting(Jsp.CLASS_NAME,METHOD_NAME);} 
%>