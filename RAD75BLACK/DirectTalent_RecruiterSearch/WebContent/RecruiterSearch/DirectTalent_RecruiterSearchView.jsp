<%-- jsf:pagecode language="java" location="/src/pagecode/RecruiterSearch/DirectTalent_RecruiterSearchView.java" --%><%-- /jsf:pagecode --%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@page language="java" contentType="text/html"
	pageEncoding="ISO-8859-1" session="false"%>
<%@page import="com.manpower.hbn.beans.ParameterMapKey"%>
<portlet:defineObjects />
<link rel="stylesheet" type="text/css"
	href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/theme/stylesheet.css") %>'
	title="Style">

 
 
<style>
	.rule{
		font-size: 6px;
		margin: 5px 0px 3px;
		background-image: url(<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/images/rule.gif") %>);
		background-repeat: repeat-x;
		width:415px;
	}

	.pagingLink {
		font-size:10px;
		color:#333333;
		cursor:pointer;
		text-decoration:none;
	}
	.pagingLinkActive{
		font-size:10px;
		color:#333333;
		cursor:pointer;
		text-decoration:underline;
		font-weight:bold;
	}
	.pagingLink:hover{
		text-decoration:underline;
		font-weight:bold;
	}
	
	.pager{
		font-family:Helvetica,Verdana,Sans-Serif;
		font-size:11px;
		text-align:left;
		
	}
	
	.pager SELECT{
		font-family:Helvetica,Verdana,Sans-Serif;
		font-size:11px;
	}
	.pagerInput{
		font-family:Helvetica,Verdana,Sans-Serif;
		font-size:12px;
		border:1px solid #c2d1e1;
	
	}
	
	.pager a,a:visited{
		font-family:Helvetica,Verdana,Sans-Serif;
		font-size:12px;
		text-decoration:none;
		color:#9ab1c9;
	}
	
	.pager a:hover{
		font-family:Helvetica,Verdana,Sans-Serif;
		font-size:12px;
		text-decoration:underline;
		font-weight:bold;
		color:#9ab1c9;
	}
	
</style>
<f:view>
	<script type="text/javascript"
		src="<%= renderResponse.encodeURL(renderRequest.getContextPath()+"/dojo/dojo/dojo.js")%>"  djConfig="parseOnLoad: true"></script>
		
	<script type="text/javascript" 
		src="<%= renderResponse.encodeURL(renderRequest.getContextPath()+"/json/json_parse_state.js")%>"></script>

	<script type="text/javascript">
       dojo.require("dojo.parser");
       dojo.require("dijit.form.FilteringSelect");
       dojo.require("dojo.data.ItemFileReadStore");
	   
	   function getvalue(){
	   	var atFName=dijit.byId("state1");
		alert(atFName);
	   }
	   
	   function performSavedSearch(jsonStr) {
	   		var json = jsonStr.replace(/\^/g,"\"");
	   		var json_kw = json_parse(json).<%= ParameterMapKey.keywords_str %>;
	   		var json_ic = json_parse(json).<%= ParameterMapKey.inclCand_str%>;
	   		var json_ia = json_parse(json).<%= ParameterMapKey.inclApp_str %>;
	   		var json_ig = json_parse(json).<%= ParameterMapKey.inclgt30_str %>;
	   		var json_pl = json_parse(json).<%= ParameterMapKey.prefLocation_str%>;
	   		var json_pb = json_parse(json).<%= ParameterMapKey.prefBranch_str%>;
	   		
	   		var searchForm = 'view<portlet:namespace/>:keywordSearchForm:';
	   		document.getElementById(searchForm+'keywords').value = json_kw;
	   		
	   		if (json_ia == "true") {
	   			document.getElementById('inclApp').checked = 1;
	   		} else {
	   			document.getElementById('inclApp').checked = 0;
	   		}
	   		
	   		if (json_ic == "true") {
	   			document.getElementById('inclCand').checked = 1;
	   		} else {
	   			document.getElementById('inclCand').checked = 0;
	   		}
	   		
	   		if (json_ig == "true") {
	   			document.getElementById('inclgt30').checked = 1;
	   		} else {
	   			document.getElementById('inclgt30').checked = 0;
	   		}
	   		
			var params = new Array();
			params[<%=ParameterMapKey.keywords%>]= json_kw;
			params[<%=ParameterMapKey.prefBranch%>]= json_pb;
			params[<%=ParameterMapKey.prefLocation%>]= json_pl;
			
			<portlet:namespace/>loadHtml('','<portlet:namespace/>displaySearchResults','test',params,'1');
	   }
  	</script>
	<hx:scriptCollector id="scriptCollector1" preRender="#{pc_DirectTalent_RecruiterSearchView.onPageLoadBegin}">

		<script type="text/javascript">
		
			var pageLocation = '';
			var pageBranch = '';
		
			function getvalue(){
			   	var atFName=dijit.byId("state1");
				alert(atFName);
			}
		
			function <portlet:namespace/>setCheckboxes(clicked){
				var setBox = 'inclCand';
				if (clicked.checked==false){
					if (clicked.id==setBox){
						document.getElementById('inclApp').checked=true;
					}else{
						document.getElementById(setBox).checked=true;
					}
				}
					
			}
			function <portlet:namespace/>loadHtml(uniqueId,targetDiv,action,params,page) {
				//alert(document.getElementById('inclCand').checked);
				var contextPath = '<%=renderResponse.encodeURL(renderRequest.getContextPath())%>';
				
				var editjwdiv = "<div style=\"width:300px;height:300px;background-image:url(";
				var elsejwdiv = "<div style=\"width:610px;height:300px;background-image:url(";
				var enddiv = "/images/ajax-loader.gif);background-repeat:no-repeat;background-position:center center;\"></div>";
				

				var workingGif =''; 
				
				var viewCandidateSearchresults = '/RecruiterSearch/AjaxResults/CandidateSearchResults.jsp';
							
				ajaxUrl = contextPath + viewCandidateSearchresults;
				workingGif=elsejwdiv;
	 						
	 			if (params[<%=ParameterMapKey.saveSearchFlag%>] != 'true') {
	 				params[<%=ParameterMapKey.saveSearchFlag%>] = '';
	 			}
				
				/*fill in whether or not to search all or add in restrictions for applicant and candidate*/
				params[<%=ParameterMapKey.inclApp%>]=document.getElementById('inclApp').checked;
				params[<%=ParameterMapKey.inclCand%>]=document.getElementById('inclCand').checked;
				params[<%=ParameterMapKey.inclgt30%>]=document.getElementById('inclgt30').checked;
						
				if (document.getElementById('numberOfPages')!=null){
					//SET THE NUMBER OF RESULTS TO DISPLAY ON A PAGE.
					var optionsBox = document.getElementById('numberOfPages');
					params[<%=ParameterMapKey.pagesToDisplay%>]=optionsBox.options[optionsBox.selectedIndex].value;
				}else{
					params[<%=ParameterMapKey.pagesToDisplay%>]='25';
				}
				
				/*Put the working .gif up in the display area*/
				workingGif+=contextPath+enddiv;
				var workingDiv = dojo.byId(targetDiv);
				workingDiv.innerHTML = "";
				workingDiv.innerHTML=workingGif;
				
				dojo.xhrPost({
	       			url : ajaxUrl, 
	       			handleAs : "text", //MIME type
	       			sync : false, //Explicit asynchronous request
					content:{
						uniqueId:uniqueId,
						targetDiv:targetDiv,
						namespace:'<portlet:namespace/>',
						action:action,
						params:params,
						page:page,
						contextPath:contextPath,
						siteid:'10420'						
					},  
	       			//Run this function if the request is successful
	      				load : function(data,response) {
	           			dojo.byId(targetDiv).innerHTML= data;
	            		return response;
	       			},
	 
			        //Run this function if the request is not successful
			        error : function(response, ioArgs) {
	                      console.log("failed xhrGet");  
	                      //DEBUG ONLY alert(response);
	                      dojo.byId(targetDiv).innerHTML = ' We are unable to currently process your request. <br> Please try again later.<br>'+response; 
	                      return response;
			        }
	    		});
				return "";
			}
			
			function <portlet:namespace/>runquery(pagenum){
				var searchForm = 'view<portlet:namespace/>:keywordSearchForm:';
				var params = new Array();
				params[<%=ParameterMapKey.keywords%>]= document.getElementById(searchForm+'keywords').value;
				params[<%=ParameterMapKey.prefBranch%>]='';
				params[<%=ParameterMapKey.prefLocation%>]='';
				params[<%=ParameterMapKey.saveSearchFlag%>]='true';
				<portlet:namespace/>loadHtml('','<portlet:namespace/>displaySearchResults','test',params,pagenum);
			}

			function <portlet:namespace/>pageResults(pagenum,keywords,maxPage){
				if (pagenum>maxPage){
					alert('There are only '+maxPage+' pages available.');
					pagenum=maxPage;
				}
				var searchForm = 'view<portlet:namespace/>:keywordSearchForm:';
				var params = new Array();
				params[<%=ParameterMapKey.keywords%>]= keywords;
				params[<%=ParameterMapKey.prefBranch%>]=pageBranch;
				params[<%=ParameterMapKey.prefLocation%>]=pageLocation;
				<portlet:namespace/>loadHtml('','<portlet:namespace/>displaySearchResults','test',params,pagenum);

			}
			
			
			
			function <portlet:namespace/>initPage(){
				var searchForm = 'view<portlet:namespace/>:keywordSearchForm:';
				var params = new Array();
				params[<%=ParameterMapKey.keywords%>]= '';
				params[<%=ParameterMapKey.prefBranch%>]='';
				params[<%=ParameterMapKey.prefLocation%>]='';
				<portlet:namespace/>loadHtml('','<portlet:namespace/>displaySearchResults','test',params,'1');
			}
			function <portlet:namespace/>setKeywordsfromSession(keywords){
				document.getElementById(searchForm+'keywords').value=keywords;
			}
			
			function <portlet:namespace/>applyFilter(filterType,id,otherFilter){
				var searchForm = 'view<portlet:namespace/>:keywordSearchForm:';
				var params = new Array();
				params[<%=ParameterMapKey.keywords%>]= document.getElementById(searchForm+'keywords').value;
				params[<%=ParameterMapKey.prefBranch%>]=( filterType == 'branch' ) ? id : otherFilter;
				params[<%=ParameterMapKey.prefLocation%>]=( filterType== 'location') ? id: otherFilter;
				<portlet:namespace/>loadHtml('','<portlet:namespace/>displaySearchResults','test',params,'1');
				pageLocation = params[<%=ParameterMapKey.prefLocation%>];
				pageBranch = params[<%=ParameterMapKey.prefBranch%>];
				
			}
			
			function <portlet:namespace/>removeFilter(filterType,otherFilter){
			
				var searchForm = 'view<portlet:namespace/>:keywordSearchForm:';
				var params = new Array();
				params[<%=ParameterMapKey.keywords%>]= document.getElementById(searchForm+'keywords').value;
				params[<%=ParameterMapKey.prefBranch%>]=( filterType == 'branch' ) ? '' : otherFilter;
				params[<%=ParameterMapKey.prefLocation%>]=( filterType== 'location') ? '': otherFilter;
				<portlet:namespace/>loadHtml('','<portlet:namespace/>displaySearchResults','test',params,'1');
				pageLocation = params[<%=ParameterMapKey.prefLocation%>];
				pageBranch = params[<%=ParameterMapKey.prefBranch%>];
			
			} 
			
			function <portlet:namespace/>addDeleteButton(optionsBox){
				var incomingId = optionsBox.options[optionsBox.selectedIndex].value;
				if (incomingId=='saveCurrent' || incomingId==''){
					document.getElementById('deleteSearchButton').style.visibility='hidden';
				}else{
					document.getElementById('deleteSearchButton').style.visibility='visible';
				}
			}
			
		</script>


		<h:form styleClass="form" id="keywordSearchForm">
			
			<div style="width:600px;padding-top:5px;padding-left:15px;">			
			<div style="background-color:#dde6ef;padding-top:10px;padding-bottom:10px;padding-left:10px;width:600px;">
				<table>	
					<tr>
						<td style="font-size:10px;font-style:italic;color:#4e8abe;text-align:left;">
							Keywords
						</td>
						<td  style="font-size:10px;font-style:italic;color:#4e8abe;text-align:left;">
							
						</td>
					</tr>
					<tr>
						<td>
							<h:inputText styleClass="inputText" id="keywords" value="" style="width:275px;font-size:10px;border:1px solid #efefef;font-family:Helvetica,Arial,sans-serif;">
							</h:inputText>
							
						</td>
				
						<td align="right">
							<input type="button" value="Search" onclick="<portlet:namespace/>runquery('1')">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="checkbox" id="inclCand" CHECKED onclick="<portlet:namespace/>setCheckboxes(this);">Include Candidates
							<input type="checkbox" id="inclApp" CHECKED onclick="<portlet:namespace/>setCheckboxes(this);">Include Applicants
							<input type="checkbox" id="inclgt30">Inlcude &gt; 30 days
						</td>
					</tr>
					
				</table>
				
				
			</div>	
			<div id="<portlet:namespace/>displaySearchResults">
			
			</div>
			</div>
		
	
		</h:form>
		<script>
			<portlet:namespace/>initPage()
		</script>
	</hx:scriptCollector>
</f:view>
