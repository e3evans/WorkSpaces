<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<%@page import="pagecode.DO_Logon.Logon"%>
<%@page import="java.util.ResourceBundle"%>
<portlet:defineObjects />

<LINK rel="stylesheet" type="text/css"
	href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/myManpowerStylesheet.css") %>'
	title="Style">
	

<script type="text/javascript" src='<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/js/functions.js") %>'></script>
<script type="text/javascript">
	var portletNamespace = 'view<portlet:namespace/>';
	
	var isShowHome = false;
	var isShowUSA = false;
	var isShowCanada = false;
	
	
	function showElement(formId,elementId)
	{
		document.getElementById(portletNamespace+":"+formId+":"+elementId).style.display = '';	
	}
	
	function hideElement(formId, elementId)
	{
		
		document.getElementById(portletNamespace+":"+formId+":"+elementId).style.display = 'none';	
	}
	
	function showHome()
	{
		showElement('candidate_form','homePanel');
		hideElement('candidate_form','usaSubview:usaCandidateData_subview:us_data_pg');
		hideElement('candidate_form','canadaSubview:canadaCandidateData_subview:us_data_pg');
		document.getElementById("home").className="active";
		document.getElementById("usa").className="inactive";
		document.getElementById("canadaTab").className="inactive";
		//isShowHome = true;
		//isShowUSA = false;
		//isShowCanada = false;
	}
	
	function showUSA()
	{
		hideElement('candidate_form','homePanel');
		showElement('candidate_form','usaSubview:usaCandidateData_subview:us_data_pg');
		hideElement('candidate_form','canadaSubview:canadaCandidateData_subview:us_data_pg');
		showElement('candidate_form','usaPanel');
		document.getElementById("home").className="inactive";
		document.getElementById("usa").className="active";
		document.getElementById("canadaTab").className="inactive";
		//isShowHome = false;
		//isShowUSA = true;
		//isShowCanada = false;
		
		usaExpDate = document.getElementById(portletNamespace + ":candidate_form:usaSubview:usaCandidateData_subview:datum11").value;
		usaBirthDate = document.getElementById(portletNamespace + ":candidate_form:usaSubview:usaCandidateData_subview:datum21").value;
		
		if(usaExpDate != null && usaExpDate != "")
		{
			datum1Element = document.getElementById("datum1");
			if(datum1Element != null)
			{
				datum1Element.value = usaExpDate;
			}
		}
		
		if(usaBirthDate != null && usaBirthDate != "")
		{
			datum2Element = document.getElementById("datum2");
			if(datum2Element != null)
			{
				datum2Element.value = usaBirthDate;
			}
		}
		
	}
	
	function showCanada()
	{
		hideElement('candidate_form','homePanel');
		hideElement('candidate_form','usaSubview:usaCandidateData_subview:us_data_pg');
		showElement('candidate_form','canadaSubview:canadaCandidateData_subview:us_data_pg');
		hideElement('candidate_form','usaPanel');
		document.getElementById('home').className="inactive";
		document.getElementById('usa').className="inactive";
		document.getElementById('canadaTab').className="active";
		//isShowHome = false;
		//isShowUSA = false;
		//isShowCanada = true;
		
		canadaExpDate = document.getElementById(portletNamespace + ":candidate_form:canadaSubview:canadaCandidateData_subview:datum31").value;
		canadaBirthDate = document.getElementById(portletNamespace + ":candidate_form:canadaSubview:canadaCandidateData_subview:datum41").value;
		
		if(canadaExpDate != null && canadaExpDate != "")
		{
			datum3Element = document.getElementById("datum3");
			if(datum3Element != null)
			{
				datum3Element.value = canadaExpDate;
			}
			
		}
		
		if(canadaBirthDate != null && canadaBirthDate != "")
		{	
			datum4Element = document.getElementById("datum4");
			if(datum4Element != null)
			{
				datum4Element.value = canadaBirthDate; 
			}
		}
	}
	
	
	
	function setUSACountry()
	{
		<%application.setAttribute("COUNTRY", "USA"); %>
	}
	
	function setCanadaCountry()
	{
		<%application.setAttribute("COUNTRY", "Canada"); %>
	}
	
	function setVisible(obj)
	{
		obj = document.getElementById(obj);
		obj.style.visibility = (obj.style.visibility == 'visible') ? 'hidden' : 'visible';
		var transparentDiv = document.getElementById('lightboxMyManp');
		if(obj.style.visibility == 'visible')
		{
			transparentDiv.style.display = 'block';
		}
		else
		{
			transparentDiv.style.display = 'none';
		}
	}
</script>
<script type="text/javascript" src='<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/js/functions.js") %>'></script>

<f:view>
	<f:loadBundle var="nlfile"
			basename="#{pc_Logon.countryBundleName}" />
			
		
		<h:form id="candidate_form" styleClass="section-4">
			
			<ul id="menu">
			
				<h:column>
					<f:verbatim>
						<li id="nav-1"><div id="home" class="active" onclick="showHome();">
					</f:verbatim>
					
					<h:outputText value="#{nlfile.home_tab}"></h:outputText>
					
					<f:verbatim>
						</div></li>
					</f:verbatim>
				</h:column>
				
				<h:column rendered="#{pc_GeneralInformation.hasAccess_USA}">
					<f:verbatim>
						<li id="nav-2"><div id="usa" class="inactive" onclick="showUSA();">
					</f:verbatim>
					
					<h:outputText value="#{nlfile.usa_tab}"></h:outputText>
						
					<f:verbatim>
						</div></li>
					</f:verbatim>
					
				</h:column>
				
				<h:column rendered="#{pc_GeneralInformation.hasAccess_Canada}">
					<f:verbatim>
						<li id="nav-3"><div id="canadaTab" class="inactive" onclick="showCanada();">
					</f:verbatim>
					<h:outputText value="#{nlfile.canada_tab}"></h:outputText>
					<f:verbatim>
						</div></li>
					</f:verbatim>
					
				</h:column>
			
			</ul>
			
			<div id="contents">
				<h:panelGrid id="homePanel">
					<jsp:include page="GeneralInformation.jsp"></jsp:include>
				</h:panelGrid>
				
				<h:panelGrid id="usaPanel">
					<jsp:include page="USA.jsp"></jsp:include>
				</h:panelGrid>
				
				<h:panelGrid id="canadaPanel">
					<jsp:include page="Canada.jsp"></jsp:include>
				</h:panelGrid>
			</div>
			
		</h:form>
		
	<div id="myManpowerCSS_inactivatePopupHelp" class="myManpowerCSS_popUpHelpGeneral">
		  <div id="popUpTop_inactivate" class="myManpowerCSS_popUp_top">&nbsp;</div>
		  <div class="myManpowerCSS_popUp_inner">
		  	  <div id="popUpTop_help" class="myManpowerCSS_popUp_top"></div>
			  <span id="close"><a href="javascript:setVisible('myManpowerCSS_inactivatePopupHelp')" style="text-decoration: none">
			  <h:graphicImage value="../theme/img/popUp_cross.gif" style="border: 0px none"></h:graphicImage>
			  </a></span>
			  <p>
			  	<%
			  		Logon logon = new Logon();
			  		String bundleName = logon.getCountryBundleName();
			  		ResourceBundle rb = ResourceBundle.getBundle(bundleName);
			  		String manpowerNeedsYouMsg = rb.getString("emergency_contact_msg");
			  		out.println(manpowerNeedsYouMsg);
			  	 %>
			  </p>
			  <div class="myManpowerCSS_popUp_bottom">
		  	  </div>
		  </div>
		  <div class="myManpowerCSS_popUp_bottom">&nbsp;
		  </div>	
	</div>
	<div id="myManpowerCSS_notMePopupHelp" class="myManpowerCSS_popUpHelpGeneral">
		<div id="popUpTop_not_me" class="myManpowerCSS_popUp_top">&nbsp;</div>
		<div class="myManpowerCSS_popUp_inner">
			<div id="popUpTop_notme_help" class="myManpowerCSS_popUp_top"></div>
			<span id="close"><a href="javascript:setVisible('myManpowerCSS_notMePopupHelp')" style="text-decoration: none">
			<h:graphicImage value="../theme/img/popUp_cross.gif" style="border: 0px none"></h:graphicImage>
			</a></span>
			<p>
				<%
					String thisIsNotMe = rb.getString("this_is_not_me");
					out.println(thisIsNotMe);
				 %>
			</p>
			<div class="myManpowerCSS_popUp_bottom">
		  	</div>
		</div>
		<div class="myManpowerCSS_popUp_bottom">&nbsp;
		</div>
	</div>
	<div id="myManpowerCSS_cannotChangePopupHelp" class="myManpowerCSS_popUpHelpGeneral">
		<div id="popUpTop_cannot_change" class="myManpowerCSS_popUp_top">&nbsp;</div>
		<div class="myManpowerCSS_popUp_inner">
			<div id="popUpTop_cannotchange_help" class="myManpowerCSS_popUp_top"></div>
			<span id="close"><a href="javascript:setVisible('myManpowerCSS_cannotChangePopupHelp')" style="text-decoration: none">
			<h:graphicImage value="../theme/img/popUp_cross.gif" style="border: 0px none"></h:graphicImage>
			</a></span>
			<p>
				<%
					String cannotChange = rb.getString("cannot_change_explanation");
					out.println(cannotChange);
				 %>
			</p>
			<div class="myManpowerCSS_popUp_bottom">
		  	</div>
		</div>
		<div class="myManpowerCSS_popUp_bottom">&nbsp;
		</div>
	</div>
	<div id="myManpowerCSS_whyDifferentPopupHelp" class="myManpowerCSS_popUpHelpGeneral">
		<div id="popUpTop_why_different" class="myManpowerCSS_popUp_top">&nbsp;</div>
		<div class="myManpowerCSS_popUp_inner">
			<div id="popUpTop_whydifferent_help" class="myManpowerCSS_popUp_top"></div>
			<span id="close"><a href="javascript:setVisible('myManpowerCSS_whyDifferentPopupHelp')" style="text-decoration: none">
			<h:graphicImage value="../theme/img/popUp_cross.gif" style="border: 0px none"></h:graphicImage>
			</a></span>
			<p>
				<%
					String whyDifferent = rb.getString("why_different_explanation");
					out.println(whyDifferent);
				 %>
			</p>
			<div class="myManpowerCSS_popUp_bottom">
		  	</div>
		</div>
		<div class="myManpowerCSS_popUp_bottom">&nbsp;
		</div>
	</div>
	<div id="myManpowerCSS_mainAdddrNeedPopupHelp" class="myManpowerCSS_popUpHelpGeneral">
		<div id="popUpTop_main_addr_need" class="myManpowerCSS_popUp_top">&nbsp;</div>
		<div class="myManpowerCSS_popUp_inner">
			<div id="popUpTop_mainaddrneed_help" class="myManpowerCSS_popUp_top"></div>
			<span id="close"><a href="javascript:setVisible('myManpowerCSS_mainAdddrNeedPopupHelp')" style="text-decoration: none">
			<h:graphicImage value="../theme/img/popUp_cross.gif" style="border: 0px none"></h:graphicImage>
			</a></span>
			<p>
				<%
					String mainAddrNeed = rb.getString("main_addr_explanation");
					out.println(mainAddrNeed);
				 %>
			</p>
			<div class="myManpowerCSS_popUp_bottom">
		  	</div>
		</div>
		<div class="myManpowerCSS_popUp_bottom">&nbsp;
		</div>
	</div>
	<div id="myManpowerCSS_workInfoPopupHelp" class="myManpowerCSS_popUpHelpGeneral">
		<div id="popUpTop_main_addr_need" class="myManpowerCSS_popUp_top">&nbsp;</div>
		<div class="myManpowerCSS_popUp_inner">
			<div id="popUpTop_mainaddrneed_help" class="myManpowerCSS_popUp_top"></div>
			<span id="close"><a href="javascript:setVisible('myManpowerCSS_workInfoPopupHelp')" style="text-decoration: none">
			<h:graphicImage value="../theme/img/popUp_cross.gif" style="border: 0px none"></h:graphicImage>
			</a></span>
			<p>
				<%
					String workIfoMsg = rb.getString("work_info_explanation");
					out.println(workIfoMsg);
				 %>
			</p>
			<div class="myManpowerCSS_popUp_bottom">
		  	</div>
		</div>
		<div class="myManpowerCSS_popUp_bottom">&nbsp;
		</div>
	</div>
	<div id="myManpowerCSS_payrollDetailsPopupHelp" class="myManpowerCSS_popUpHelpGeneral">
		<div id="popUpTop_main_addr_need" class="myManpowerCSS_popUp_top">&nbsp;</div>
		<div class="myManpowerCSS_popUp_inner">
			<div id="popUpTop_mainaddrneed_help" class="myManpowerCSS_popUp_top"></div>
			<span id="close"><a href="javascript:setVisible('myManpowerCSS_payrollDetailsPopupHelp')" style="text-decoration: none">
			<h:graphicImage value="../theme/img/popUp_cross.gif" style="border: 0px none"></h:graphicImage>
			</a></span>
			<p>
				<%
					String payrollDetailsMsg = rb.getString("payroll_details_explanation");
					out.println(payrollDetailsMsg);
				 %>
			</p>
			<div class="myManpowerCSS_popUp_bottom">
		  	</div>
		</div>
		<div class="myManpowerCSS_popUp_bottom">&nbsp;
		</div>
	</div>
	<f:verbatim>
			<table id="calenderTable">
		        <tbody id="calenderTableHead">
		          <tr>
		            <td colspan="4" align="center">
			          <select onChange="showCalenderBody(createCalender(document.getElementById('selectYear').value,
			           this.selectedIndex, false,''));" id="selectMonth">
			              <option value="0"></f:verbatim><h:outputText value="#{nlfile.january}"></h:outputText><f:verbatim></option>
			              <option value="1"></f:verbatim><h:outputText value="#{nlfile.february}"></h:outputText><f:verbatim></option>
			              <option value="2"></f:verbatim><h:outputText value="#{nlfile.march}"></h:outputText><f:verbatim></option>
			              <option value="3"></f:verbatim><h:outputText value="#{nlfile.april}"></h:outputText><f:verbatim></option>
			              <option value="4"></f:verbatim><h:outputText value="#{nlfile.may}"></h:outputText><f:verbatim></option>
			              <option value="5"></f:verbatim><h:outputText value="#{nlfile.june}"></h:outputText><f:verbatim></option>
			              <option value="6"></f:verbatim><h:outputText value="#{nlfile.july}"></h:outputText><f:verbatim></option>
			              <option value="7"></f:verbatim><h:outputText value="#{nlfile.august}"></h:outputText><f:verbatim></option>
			              <option value="8"></f:verbatim><h:outputText value="#{nlfile.september}"></h:outputText><f:verbatim></option>
			              <option value="9"></f:verbatim><h:outputText value="#{nlfile.october}"></h:outputText><f:verbatim></option>
			              <option value="10"></f:verbatim><h:outputText value="#{nlfile.november}"></h:outputText><f:verbatim></option>
			              <option value="11"></f:verbatim><h:outputText value="#{nlfile.december}"></h:outputText><f:verbatim></option>
			          </select>
		            </td>
		            <td colspan="3" align="center">
					    <select onChange="showCalenderBody(createCalender(this.value, 
						document.getElementById('selectMonth').selectedIndex, false,''));" id="selectYear">
						</select>
					</td>
		            
				  </tr>
		       </tbody>
		       <tbody id="calenderTableDays">
		         <tr style="">
		           <td></f:verbatim><h:outputText value="#{nlfile.sunday}"></h:outputText><f:verbatim></td>
		           <td></f:verbatim><h:outputText value="#{nlfile.monday}"></h:outputText><f:verbatim></td>
		           <td></f:verbatim><h:outputText value="#{nlfile.tuesday}"></h:outputText><f:verbatim></td>
		           <td></f:verbatim><h:outputText value="#{nlfile.wednesday}"></h:outputText><f:verbatim></td>
		           <td></f:verbatim><h:outputText value="#{nlfile.thursday}"></h:outputText><f:verbatim></td>
		           <td></f:verbatim><h:outputText value="#{nlfile.friday}"></h:outputText><f:verbatim></td>
		           <td></f:verbatim><h:outputText value="#{nlfile.saturday}"></h:outputText><f:verbatim></td>
		         </tr>
		       </tbody>
		       <tbody id="calender"></tbody>
		    </table>
		</f:verbatim>
</f:view>