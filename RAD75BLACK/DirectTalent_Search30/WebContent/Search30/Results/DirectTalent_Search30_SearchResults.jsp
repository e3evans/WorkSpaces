<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@page import="com.manpower.directtalentsearch30.utility.SearchUtility"%>
<%@page import="com.manpower.directtalentsearch30.utility.beans.SearchResultsBean"%><%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" session="false"%>

<%@page import="java.text.SimpleDateFormat"%><style>
	.jobTitle{
		color:#558cbf;
		font-family:Helvetica,Verdana,Arial;
		font-size:11px;
		font-weight:bold;
		border-bottom:1px dotted #525252;
		padding-top:10px;
		width:600px;
	}
	
	.jobInfo{
		color:#525252;
		font-family:Helvetica,Verdan,Arial;
		font-size:10px;
		padding-right:10px;
		vertical-align:top;
	
	}
	
	.jobDescrip{
		color:#525252;
		font-family:Helvetica,Verdan,Arial;
		font-size:10px;
		padding-right:10px;
		vertical-align:top;
		padding:10px;
		font-style:italic;
	}
	
</style>
<%
	String[] searchFields = {"jobdescription","branch_city","branch_state"};
	String params = request.getParameter("searchparams");
	SearchResultsBean srb = SearchUtility.getInstance().findMatchingAdverts(params,searchFields,0,5);
	JSONArray jarray = srb.getResults();
	JSONObject jobj;
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
	for (int i=0;i<jarray.length();i++){
	
		jobj = jarray.getJSONObject(i);
		%>
			<div class="jobTitle"><%=jobj.getString("jobtitle") %> (Relevancy:  <%=jobj.getString("score").substring(0,4) %>)
				<table width="100%">
					<tr>
						<td valign="top" width="175">
							<table>
									<tr>
										<td class="jobInfo">
											Location
										</td>
										<td class="jobInfo">
											<%=jobj.getString("location") %>
										</td>
										
									</tr>
									<tr>
										<td class="jobInfo">
											Type
										</td>
										<td class="jobInfo">
											<%=jobj.getString("contracttype") %>
										</td>
									</tr>
									<tr>
										<td class="jobInfo">
											Date Posted
										</td>
										<td class="jobInfo">
											<%=formatter.format(formatter.parse(jobj.getString("publicationdate"))) %>
										
										</td>
									</tr>
								</table>
							</td>
							<td class="jobDescrip">
								<%=jobj.getString("jobdescription").substring(0,200)%>
							
							</td>
						</tr>
				</table>
				
				
				
			
				
							
				
			</div>	

		<%
	}
 %>

<!-- TESTING POST = <%=request.getParameter("namespace")%>  

<hr>
Search Params = <%=request.getParameter("searchparams")%>
<hr>
--->
