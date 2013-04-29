<%@page import="com.manpower.directtalentrecruitersearch.hbn.shared.dao.DAOFactory"%>
<%@page import="java.util.List"%>
<%@page import="com.manpower.hbn.beans.CandidateSearchResults"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.manpower.portal.portlet.directtalent_recruitersearch.utility.HtmlUtility"%>
<%@page import="com.manpower.hbn.beans.PrefLocFilter"%>
<%@page import="com.manpower.hbn.beans.ParameterMapKey"%>
<%@page import="com.manpower.hbn.beans.Branches"%>
<%@page import="com.manpower.hbn.beans.RecruiterAuditing"%>
<%@page import="java.util.Date"%>
<%@page import="com.manpower.portal.portlet.directtalent_recruitersearch.utility.JsonUtil"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<%
	//String keywords = request.getParameter("keywords");
	
	String [] params = request.getParameterValues("params");
	String pagenum = request.getParameter("page");
	String namespace = request.getParameter("namespace");
	String siteId = request.getParameter("siteid");
	String contextPath= request.getParameter("contextPath");
	List resultsList = new ArrayList();
	List branchFilter = new ArrayList();
	List preferredLocationList = new ArrayList();
	int defaultDisplay = 25;	
	if (!params[ParameterMapKey.pagesToDisplay].equals("")){
		defaultDisplay=Integer.parseInt(params[ParameterMapKey.pagesToDisplay]);
	}
	int pageNumber = Integer.parseInt(pagenum);
	if (params[0].equals("")){
		if (request.getSession().getAttribute("resultsList")!=null){
			resultsList = (List)request.getSession().getAttribute("resultsList");
			branchFilter = (List)request.getSession().getAttribute("branchFilter");
			preferredLocationList = (List)request.getSession().getAttribute("preferredLocationList");
			params[0]=(String)request.getSession().getAttribute("keywords");
			pagenum=(String)request.getSession().getAttribute("pagenum");
			pageNumber = Integer.parseInt(pagenum);
		}
	}else if (!params[0].equals("")){
		resultsList = DAOFactory.getDAOFactory().getCandidateKeywordSearchDAO().findAllByKeywords(params,Long.parseLong(siteId),1500,defaultDisplay,pageNumber);
		branchFilter = DAOFactory.getDAOFactory().getBranchesDAO().getBranchFilterList(params,Long.parseLong(siteId));
		preferredLocationList = DAOFactory.getDAOFactory().getPrefLocFilterDAO().getPreferredLocationList(params,Long.parseLong(siteId));
		if (resultsList!=null){
			request.getSession().setAttribute("resultsList",resultsList);
			request.getSession().setAttribute("keywords",params[0]);
			request.getSession().setAttribute("branchFilter",branchFilter);
			request.getSession().setAttribute("preferredLocationList",preferredLocationList);
			request.getSession().setAttribute("pagenum",pagenum);
		}
	}
	
	//if flag is true, save the search
	if (params != null && params[ParameterMapKey.saveSearchFlag].trim().equals("true")) {
		RecruiterAuditing ra = new RecruiterAuditing();
		ra.setAction("RECRUITERSEARCH");
		ra.setCreated_On(new Date());
		ra.setSite_id(5);
		ra.setSearch_data(JsonUtil.getSearchString(params));
		DAOFactory.getDAOFactory().getRecruiterAuditingDAO().saveRecruiterAuditing(ra);
	}
	
	
	List lastSearches = DAOFactory.getDAOFactory().getRecruiterAuditingDAO().getRecentSearchesByActionAndRecruiterId(5, "RECRUITERSEARCH", 0);
	
	List resultBeans = new ArrayList();
	String pager = "";
	
%>

<table>
	<tr>
		<td valign="top">
			
			<table cellpadding="0" cellspacing="3">
			<%if (lastSearches!=null){ %>
				<tr>
					<td style="font-size:10px;font-style:italic;color:#4e8abe;text-align:left;padding-right:3px;">
						Last 5 Searches
					</td>
				</tr>
			
				<tr>
				<td valign="top" style="font-size:10px;font-style:italic;color:#4e8abe;text-align:left;border:1px solid #9ab1c9;width:150px;" >
					<div style="height:95px;overflow:auto;">
							<table >
								<%for (int i = 0; i < lastSearches.size(); i++) { 
									RecruiterAuditing ra = (RecruiterAuditing) lastSearches.get(i);
									String keyword = JsonUtil.getValue(ra.getSearch_data(), ParameterMapKey.keywords_str);
									//String keyword = ra.getSearch_data();
								%>
								<tr>
									<td style="cursor:pointer;" onmouseover="this.style.backgroundColor='#efefef'" onmouseout="this.style.backgroundColor='#FFFFFF'">
										<a style="text-decoration:none;" href="javascript:performSavedSearch('<%=ra.getSearch_data().replaceAll("\"","^") %>');"><%=keyword%></a>
										<input type="hidden" value="<%=ra.getSearch_data()%>" />
									</td>
								</tr>
								<%} %>	
							</table>
						</div>	
					</td>
				</tr>
			<%} %>

<%
if (resultsList!=null){
		if (resultsList.size()>0){
			resultBeans = (List)resultsList.get(0);
			pager = HtmlUtility.getPager(params[0],namespace,Integer.parseInt(resultsList.get(1).toString()),defaultDisplay,pageNumber);
	
%>




				<tr>
					<td style="font-size:10px;font-style:italic;color:#4e8abe;text-align:left;padding-right:3px;">
						Preferred Branch
					</td>
				</tr>
				<tr>
					<td valign="top" style="font-size:10px;font-style:italic;color:#4e8abe;text-align:left;padding-right:3px;border:1px solid #9ab1c9;width:150px;">
					
						<div style="height:90px;overflow:auto;">
							<table style="border:1px solid #EFEFEF;" >
							<%
								if (!params[ParameterMapKey.prefBranch].equals("")){
									Branches branch = (Branches)branchFilter.get(0);
								%>
									<tr>
										<td valign="middle" style="background-color:#efefef;font-weight:bold;"><%=branch.getBranchname() %> (<%=branch.getMpmatches() %>)</td>
										<td valign="middle" style="width:15px;"><img width="15" height="14" style="cursor:pointer;" src="<%=contextPath%>/images/removeFilter.gif" alt="Remove Filter" title="Remove Filter" border="0" onclick="<%=namespace%>removeFilter('branch','<%=params[ParameterMapKey.prefLocation]%>')"></td>	
									</tr>
									
								<%}else{
									for (int i=0;i<branchFilter.size();i++){
										Branches branch = (Branches)branchFilter.get(i);
								 %>	
										<tr><td style="cursor:pointer;" onmouseover="this.style.backgroundColor='#efefef'" onmouseout="this.style.backgroundColor='#FFFFFF'" onclick="<%=namespace%>applyFilter('branch',<%=branch.getBranch_id()%>,'<%=params[ParameterMapKey.prefLocation]%>')"><%=branch.getBranchname() %> (<%=branch.getMpmatches() %>)</td></tr>
								 <%	} 
							 	}
							
							
							%>
							</table> 
						</div>
						</td>
					</tr>
					<tr>
						<td style="font-size:10px;font-style:italic;color:#4e8abe;text-align:left;padding-right:3px;">
							Preferred Location
						</td>
					</tr>
					<tr>
					<td valign="top" style="font-size:10px;font-style:italic;color:#4e8abe;text-align:left;border:1px solid #9ab1c9;width:150px;" >
						<div style="height:90px;overflow:auto;">
							<table >
							<%
								if (!params[ParameterMapKey.prefLocation].equals("")){
									PrefLocFilter prefLocFilter = (PrefLocFilter)preferredLocationList.get(0);
									%>
									
									<tr>
										<td valign="middle" style="background-color:#efefef;font-weight:bold;"><%=prefLocFilter.getPrefered_location() %> (<%=prefLocFilter.getMpmatches() %>)</td>
										<td valign="middle" align="right" style="width:15px;"><img width="15" height="14" style="cursor:pointer;" src="<%=contextPath%>/images/removeFilter.gif" alt="Remove Filter" title="Remove Filter" border="0" onclick="<%=namespace%>removeFilter('location','<%=params[ParameterMapKey.prefBranch]%>')"></td>		
									</tr>
									<%
								}else{
								for(int i=0;i<preferredLocationList.size();i++){
									PrefLocFilter prefLocFilter = (PrefLocFilter)preferredLocationList.get(i);
									%>
									<tr><td style="cursor:pointer;" onmouseover="this.style.backgroundColor='#efefef'" onmouseout="this.style.backgroundColor='#FFFFFF'" onclick="<%=namespace%>applyFilter('location',<%=prefLocFilter.getLocation_id()%>,'<%=params[ParameterMapKey.prefBranch]%>')"><%=prefLocFilter.getPrefered_location() %> (<%=prefLocFilter.getMpmatches() %>)</td></tr>		
								<%}
								}%>	
							</table>
						</div>	
					</td>
					</tr>
					
					
					
			</table>
			
		</td>
		
		<td valign="top">
	
			<table style="width:450px;">
				<tr>
					<td align="left" style="font-family:Helvetica,Verdana,Arial,sans-serif;font-size:11px;color:#333333;" valign="top">

						<table>
							
							<tr>
								<td class="pager" valign="top" style="padding-right:5px;">
									<table>
										<tr>
										
											<td class="pager" valign="top">
											Results per page &nbsp;
											</td>
											<td class="pager">
												<%
													String [] vals = {"25","50","100"};
												 %>
											
												<SELECT id="numberOfPages" onchange="<%=namespace%>pageResults(1,'<%=params[0] %>')">
													<%	String itsselected="";
														for (int i = 0;i<vals.length;i++){ %>
														<%if (Integer.parseInt(vals[i])==defaultDisplay){
															itsselected="SELECTED";
														}else{
															itsselected="";
														} %>
														<OPTION value="<%=vals[i] %>" <%=itsselected %>><%=vals[i] %>
													<%} %>
												</SELECT>
											</td>
										</tr>
									</table>
									
								</td>
								<td class="pager" style="padding-left:5px;">
									&nbsp;
								</td>
								<td>
									<img width="14" height="13" id="deleteSearchButton" style="cursor:pointer;visibility:hidden;" src="<%=contextPath%>/images/removeFilter.gif" alt="Remove Search" title="Remove Search" border="0" >
								</td>
							</tr>
							<tr>
								<td class="pager" colspan="3">
									<%=pager %> 
								</td>
							</tr>
			
						</table>
						
					</td>
				</tr>
				<%
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
					for (int i=0;i<resultBeans.size();i++){
						CandidateSearchResults resultBean = (CandidateSearchResults)resultBeans.get(i);
						String fullname = "";
						//System.out.println("FULL NAME START");
						fullname+=resultBean.getFirstname();
						if (resultBean.getMiddlename()!=null){
							if (!resultBean.getMiddlename().equals("")){
								fullname+=" "+resultBean.getMiddlename()+" ";
							}
						}
						fullname+=resultBean.getLastname();
						//System.out.println("FULL NAME FINISH");
						
						String lastLoggedIn="";
						if (resultBean.getLast_login_date()!=null){
							lastLoggedIn=sdf.format(resultBean.getLast_login_date());
						}
				 %>
				<tr>
					<td style="font-family:Helvetica,Verdana,Arial,sans-serif;font-size:10px;color:#333333;" valign="top">
						<div class="rule">&nbsp;</div>
						<table cellpadding="0" cellspacing="0" width="415" >
							<tr>
								<td>
									<%
									String imgsrc=contextPath+"/images/candMarker.gif";
									String ctype = "Candidate";
									if (resultBean.getCand_type().equals("A")){
										imgsrc=contextPath+"/images/appMarker.gif"; 
										ctype="Applicant";
									}	
									%>
									<b><%=fullname%> Last Login: <%=lastLoggedIn%></b>&nbsp;<img src="<%=imgsrc%>" border="0" alt="<%=ctype %>" title="<%=ctype%>"><br>
									Preferred Location: <%=resultBean.getPrefered_location() %><br>
									Preferred Branch: <%=resultBean.getBranchname() %><br>
									Status: <%=resultBean.getStatus() %><br>
									Has applied for <%=resultBean.getCount_of_jobs_applied_for() %> job(s).<br>
									
									
								</td>
								<td valign="top" align="right">
									<table cellpadding="0" cellspacing="0">
										<tr>
											<td>									
												<a href="<%=contextPath%>/DownloadResume?cvid=<%=resultBean.getCv_id()%>&mimetype=<%=resultBean.getMime_type()%>&cvname=<%=resultBean.getResume_name() %>&candType=<%=resultBean.getCand_type()%>">
													<img src="<%=contextPath%>/images/resumeattachment.gif" width="18" height="13" alt="Download / View CV" title="Download / View CV" border="0">
												</a>
											</td>
										</tr>
									</table>
								
								</td>
							</tr>
						
						</table>
						
					</td>	
				</tr>
				<%} %>
			</table>

	</td>
	</tr>	
	
<%	}	}else{%>
	<tr>
		<td rowspan="2" valign="top">
			<div style="font-family:Helvetica,Sans-Serif,Verdana;font-size:12px;font-weight:bold;color:red;">Your Search returned 0 results....</div>
		</td>
	</tr>
<% }%>

</table>