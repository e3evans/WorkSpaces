<%@page session="false" contentType="text/html" pageEncoding="ISO-8859-1" import="java.util.*,javax.portlet.*,com.ibm.slwcmbulkupload.*" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>        
<%@taglib uri="http://www.ibm.com/xmlns/prod/websphere/portal/v6.1/portlet-client-model" prefix="portlet-client-model" %>


<portlet:defineObjects/>
<portlet-client-model:init>
      <portlet-client-model:require module="ibm.portal.xml.*"/>
      <portlet-client-model:require module="ibm.portal.portlet.*"/>   
</portlet-client-model:init> 

<%
	com.ibm.slwcmbulkupload.SLWcmBulkUploadPortletSessionBean sessionBean = (com.ibm.slwcmbulkupload.SLWcmBulkUploadPortletSessionBean)renderRequest.getPortletSession().getAttribute(com.ibm.slwcmbulkupload.SLWcmBulkUploadPortlet.SESSION_BEAN);
%>

<link rel="stylesheet"
	href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/js/jquery-ui.css") %>'
	type="text/css">
<script language="JavaScript"
	src='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/js/jquery.min.js") %>'></script>

<script language="JavaScript"
	src='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/js/jquery-ui.min.js") %>'></script>



<script>
//$(document).ready(function(){$('#progressbar').progressbar({ value: 37 });
		//alert('blah');
//		});

function startUpload(form){
	var $form =form;
	checkStatus();
    // let's select and cache all the fields
    $.ajax({
        url: "<portlet:resourceURL/>",
        type: "post",
        data: $('#foo').serialize(),
        // callback handler that will be called on success
        success: function(response, textStatus, jqXHR){
            // log a message to the console
           // console.log("Hooray, it worked!");
        },
        // callback handler that will be called on error
        error: function(jqXHR, textStatus, errorThrown){
            // log the error to the console
            console.log(
                "The following error occured: "+
                textStatus, errorThrown
            );
        },
        // callback handler that will be called on completion
        // which means, either on success or error
        complete: function(){
            // enable the inputs
           // $inputs.removeAttr("disabled");
        }
    });
}
function updateProgressBar(val){
	$('#progressbar').progressbar({ value: val });
}

function checkStatus(){
      $.ajax({
        url: "<portlet:resourceURL/>",
        type: "get",
        data: {check:'check'},
        // callback handler that will be called on success
        success: function(response, textStatus, jqXHR){
            // log a message to the console
            if (response<99){
            	            
            	updateProgressBar(parseInt(response));
            	setTimeout("checkStatus()", 10000);
            }else{
            	alert('finished');
            	updateProgressBar(100);

            }
           // console.log("Hooray, it worked!");
        },
        // callback handler that will be called on error
        error: function(jqXHR, textStatus, errorThrown){
            // log the error to the console
            console.log(
                "The following error occured: "+
                textStatus, errorThrown
            );
        },
        // callback handler that will be called on completion
        // which means, either on success or error
        complete: function(){
            // enable the inputs
           // $inputs.removeAttr("disabled");
        }
    });

}
var stat=0;
function dummy(){
	updateProgressBar(stat);
	stat++;
	alert($("#progressbar").progressbar("option", "value"));
}

  
</script> 

<DIV style="margin: 6px">
<div id="progressbar" style="border:1px solid red;"></div>
<DIV style="margin: 12px; margin-bottom: 36px">


	<FORM id="foo" >	 
		Input File Location <input name="<%=SLWcmBulkUploadPortlet.FILE_PATH %>" value="C:/temp/Files.xls"/><br>
		Files Directory <input name="<%=SLWcmBulkUploadPortlet.FILES_DIRECTORY %>" value="C:/temp"/>
		<input name="actionSubmit" value="submit" type="hidden"/>
		<INPUT name="<%=com.ibm.slwcmbulkupload.SLWcmBulkUploadPortlet.FORM_SUBMIT%>" type="button" value="Submit" onclick="startUpload(document.getElementById('foo'))"/>
	</FORM>

</DIV>

</DIV>