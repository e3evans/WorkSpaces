<%@page session="false" contentType="text/html" pageEncoding="ISO-8859-1" import="java.util.*,javax.portlet.*,com.asponte.vmmportlet.*" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>        
<%@taglib uri="http://www.ibm.com/xmlns/prod/websphere/portal/v6.1/portlet-client-model" prefix="portlet-client-model" %>        
<portlet:defineObjects/>
<portlet-client-model:init>
      <portlet-client-model:require module="ibm.portal.xml.*"/>
      <portlet-client-model:require module="ibm.portal.portlet.*"/>   
</portlet-client-model:init> 
<%
	com.asponte.vmmportlet.VMMPortletSessionBean sessionBean = (com.asponte.vmmportlet.VMMPortletSessionBean)renderRequest.getPortletSession().getAttribute(com.asponte.vmmportlet.VMMPortlet.SESSION_BEAN);
	List entities = new ArrayList();
	if (sessionBean.getEntities()!=null)entities = sessionBean.getEntities();

%>
<script type="text/javascript"
	src='<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/js/jquery-latest.min.js") %>'>
</script>

<script>
	function test(renderas){
		var searchString = document.getElementById('searchField').value;
		$('#ajax-panel2').empty();
		$.ajax({
		  type: 'POST',
		  dataType:'text',
		  url: '<%=sessionBean.getVmmService()%>'+renderas,
		  data: { <%=VMMPortlet.PREF_USERNAME%>: '<%=sessionBean.getUserName()%>', 
		  <%=VMMPortlet.PREF_PASSWORD%>: '<%=sessionBean.getPassword()%>',
		  <%=VMMPortlet.PREF_SEARCHBASE%>:'<%=sessionBean.getSearchBase()%>',
		  searchTerm:searchString },
		  beforeSend:function(){
		    // this is where we append a loading image
		    $('#ajax-panel').html('<div class="loading"><img src="/images/loading.gif" alt="Loading..." /></div>');
		  },
		  success:function(data){
		    // successful request; do something with the data
		    //alert(data);
		    //parser=new DOMParser();
			//xmlDoc=parser.parseFromString(data,"text/xml");
			//alert(xmlDoc);

		    $('#ajax-panel').empty();
		    $('#ajax-panel').text(data.toString());
		  },
		  error:function(){
		    // failed request; give feedback to user
		    $('#ajax-panel').html('<p class="error"><strong>Oops!</strong> Try that again in a few moments.</p>');
		  }
		});	
	}
	

</script>


 

<DIV style="margin: 6px">


<!-- 
http://localhost:10039/VMMRestProject/resources/search
?userName=waslocal&password=waslocal&searchBase=o=defaultWIMFileBasedRealm&searchTerm=w
 -->
 
<DIV style="margin: 12px; margin-bottom: 36px">
<portlet:actionURL var="xmlRequest"><portlet:param name="reqType" value="xml"/></portlet:actionURL>
<portlet:actionURL var="jsonRequest"><portlet:param name="reqType" value="json"/></portlet:actionURL>
<table width="100%">
<tr>
</tr>
	<tr>
		<td width="50%">
			<H3 style="margin-bottom: 3px">Welcome!</H3>
			This is a simple portlet that accesses the local VMM instance and will query by 'uid'<BR>
			Some things to keep in mind<BR>
			<ul>
				<li>A '*' is added to each query so, smith == smith*.</li>
				<li>Make sure to edit your portlet to specify your admin user and search base.</li>
				<li>Also specify the destination web service.</li>
			</ul>
			<H3 style="margin-bottom: 3px">Query VMM</H3>
			This is a sample form query to an existing Web Service.
			Some more things to keep in mind<BR>
			<ul>
				<li>Using JQUERY to run the AJAX Requests</li>
				<li>There's a method for XML and JSON so you can return either.</li>
				<li>Only calling "POST" methods for this demo.</li>
			</ul>
		
			
			<FORM method="POST" action="<portlet:actionURL/>">
				<H3 style="margin-bottom: 3px">Enter Search String:</H3>
				<INPUT name="<%=com.asponte.vmmportlet.VMMPortlet.FORM_TEXT%>" id="searchField" type="text" size="25"/>
				<H3 style="margin-bottom: 3px">Post Demo (Returns data parsed and formatted from the webservice.)</H3>
				<INPUT name="XML" type="button" value="Submit (XML)" onclick="this.form.action='<%=xmlRequest%>';this.form.submit()"/>
				<INPUT name="JSON" type="button" value="Submit (JSON)" onclick="this.form.action='<%=jsonRequest%>';this.form.submit()"/>
			</FORM>
			
			<H3 style="margin-bottom: 3px">AJAX Demo (Returns raw data only.)</H3>
			
			<input type="button" onclick="test('')" value="Get XML (AJAX)"/><input type="button" onclick="test('/json')" value="Get JSON (AJAX)"/>
		
		</td>
		<td styl="border-left:2px solid blue;" valign="top" width="50%">
			
		
		</td>
	</tr>
</table>	
	
	<H3 style="margin-bottom: 3px">Raw Data</H3>
	<div id="ajax-panel" style="border:1px solid red;padding:15px;width:80%;">
		<%=sessionBean.getFormText() %>
	</div>
	
	
	<H3 style="margin-bottom: 3px">Formatted Data</H3>
	<div id="ajax-panel2" style="border:1px solid blue;padding:15px;width:80%;">
	<%for (int i = 0; i<entities.size();i++){
				HashMap temp = (HashMap)entities.get(i);
				Iterator it = temp.keySet().iterator();
				while (it.hasNext()){
					String tempS = (String)it.next();
					tempS = tempS+"="+temp.get(tempS);
					%><%=tempS%><br><%
				}
				%><HR/><%
			%><%} %>
	</div>
</DIV>
</DIV>
