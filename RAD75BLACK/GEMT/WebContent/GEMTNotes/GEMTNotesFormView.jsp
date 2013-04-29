<%-- tpl:metadata --%>
	<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/GEMTNOTES/GEMTNotesFormView.java" --%><%-- /jsf:pagecode --%>
<%-- /tpl:metadata --%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<portlet:defineObjects />
<link rel="stylesheet" type="text/css"
	href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/stylesheet.css") %>'
	title="Style">
<link rel="stylesheet" type="text/css"
	href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/custStyle.css") %>'
	title="Style">
<f:view><%
		String jsfNamespace = "view"+renderResponse.getNamespace();
		String form1Base = jsfNamespace+":form1:";
	 %>
	<SCRIPT type="text/javascript">
	var djConfig={isDebug:false,
				allowQueryConfig:false,
				baseScriptUri:'<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/jslib/dojolib/")%>',
				baseRelativePath:'<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/jslib/dojolib/")%>',
				libraryScriptUri:'<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/jslib/dojolib/")%>',
				iePreventClobber:false,
				ieClobberMinimal:true,
				preventBackButtonFix:true,
				delayMozLoadingFix:false,
				searchIds:['perfCommentsEditor'],
				parseWidgets:false};
		function <portlet:namespace/>_highlightButton(thisObj,thisEvent){
		if (thisObj.className.indexOf('Dark')>0){
			changeClass(thisObj,'GEMTCommandButtonOrange');
		}else{
			changeClass(thisObj,'GEMTCommandButtonDarkOrange');
		}
	}
	
</SCRIPT>
	<SCRIPT	src='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/jslib/dojolib/dojoEditor.js") %>'
		type="text/javascript"> 
    </SCRIPT>
    <SCRIPT src='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/jslib/bullets.js") %>' type="text/javascript">
    </SCRIPT>
    <script type="text/javascript"
		src='<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/jslib/global.js") %>'></script>
	<SCRIPT type="text/javascript">
		dojo.require('dojo.widget.Editor2');
	</SCRIPT>

	<script>
		
	
		

		function func_1(thisObj, thisEvent) {
			//use 'thisObj' to refer directly to this component instead of keyword 'this'
			//use 'thisEvent' to refer to the event generated instead of keyword 'event'
			
			changeClass(thisObj,'GEMTinputTextActive');
		}
		function func_2(thisObj, thisEvent) {
			//use 'thisObj' to refer directly to this component instead of keyword 'this'
			//use 'thisEvent' to refer to the event generated instead of keyword 'event'
			changeClass(thisObj,'GEMTinputText');
		}
	

	
	var currentTextAreaId;
	var currentNamespace;
	var startPos;
	var editingBullet;
	function mousePos(position){
		startPos=position;
	}
	
	function <portlet:namespace/>show_bullet_Editor(thisObj, thisEvent,textAreaId,namespace) {
				//use 'thisObj' to refer directly to this component instead of keyword 'this'
			//use 'thisEvent' to refer to the event generated instead of keyword 'event'
			currentTextAreaId=textAreaId;
			currentNamespace = namespace;
			startPos=findButtonPos(thisObj);
			
			showPostPanel('',startPos);
			
	}
	function <portlet:namespace/>edit_Bullets(thisObj, thisEvent,textAreaId,namespace) {
		//use 'thisObj' to refer directly to this component instead of keyword 'this'
		//use 'thisEvent' to refer to the event generated instead of keyword 'event'
		currentTextAreaId=textAreaId;
		currentNamespace = namespace;
		startPos=findButtonPos(thisObj);
		toggleEdit(currentTextAreaId);
	}
</script>
	<div id="enclosingNode" style="position:absolute;top:-100;left:-100;z-index:5;background-color:#FFFFFF;border:1px solid #908f8f;padding:10px;">
		<input class="GEMTCommandButtonWhite" type="button" value="Close" onclick="closePostPanel()" />
		<input class="GEMTCommandButtonWhite" type="button" value="Save Bullet" onclick="addItem()" />
		<div style="width:400px;height:50px;" id="masterBullets"></div>
	</div>
	<hx:scriptCollector id="scriptCollector1"
		preRender="#{pc_GEMTNotesFormView.onPageLoadBegin}">
		<h:form styleClass="form" id="form1"
			style="font-size:9.5pt;font-family:Arial;">



			<TABLE Class="GEMTTable" border="0" cellpadding="0" cellspacing="0"
				style="margin-left:5px;">
				<TBODY>
					<TR>
						<TD width="968"><h:outputText value="User Name : " /><h:outputText
							id="gemt_notes_user_name"
							value="#{sessionScope.GemtNotesUIBean.gemt_notes_user_name}" /></TD>
					</TR>
					<TR>
						<TD><BR />
						</TD>
					</TR>
					<TR>
						<TD width="950"><h:outputText value="Notes : " /><h:outputText
							escape="false" value="#{sessionScope.GemtNotesUIBean.gemt_notes_content}" />&nbsp;&nbsp; &nbsp;&nbsp; <hx:requestLink styleClass="mpThemeCommandLink"
							value="Delete"
							action="#{pc_GEMTNotesFormView.doDeleteReportAction}">

							<f:param name="paramId"
								value="#{sessionScope.GemtNotesUIBean.id}"></f:param>
						</hx:requestLink></TD>

					</TR>
					<tr>
						<td><br />
						</td>
					</tr>

					<TR>
					
						<TD>
							<div style="border: 1px solid #908f8f; width:100%;" id="perfCommentsEditor">
								<textArea escape="false" class="GEMTDefaultEditor" dojoType="Editor"
							name="<%=jsfNamespace%>:form1:gemt_notes_content" cols="80"
							rows="4" items="textGroup;|;justifyGroup;|;listGroup;">				       
				    				
				    				<h:outputText escape="false"
							value="#{sessionScope.GemtNotesUIBean.gemt_notes_content}" />
				    				
				    			</textArea>
							</div>
						</TD>
				
						
					
					</TR>
					<tr>
						<td height="42"><br />
						</td>
					</tr>
					<TR>


						
						<td>
						<hx:commandExButton type="submit" value="Submit" id="submit"
							 onmouseout="return #{facesContext.externalContext.response.namespace}_highlightButton(this, event);" 
							action="#{pc_GEMTNotesFormView.doSubmitAction}" >
							<f:param name="paramId"
								 value="#{sessionScope.GemtNotesUIBean.id}"></f:param>
								 </hx:commandExButton>
						</td>
					</TR>



				</TBODY>
			</TABLE>
			<input type="hidden" name="formBase" id ="formBase" value="<%=form1Base%>">
			
		</h:form>
	</hx:scriptCollector>
</f:view>