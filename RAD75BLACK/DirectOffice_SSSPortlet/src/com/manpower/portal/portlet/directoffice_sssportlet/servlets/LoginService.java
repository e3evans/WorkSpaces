package com.manpower.portal.portlet.directoffice_sssportlet.servlets;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.manpower.directoffice.api.DOSecurityUtility;
import com.manpower.directoffice.codelists.CodeListHandler;
import com.manpower.directoffice.pojos.CandidateEntity;
import com.manpower.directoffice.pojos.CandidateGeneralInformation;
import com.manpower.directoffice.pojos.CandidateProfile;
import com.manpower.portal.portlet.directoffice_sssportlet.DirectOffice_SSSPortlet;
import com.manpower.portal.portlet.directoffice_sssportlet.utils.JSONUtils;

/**
 * Servlet implementation class LoginService
 */
public class LoginService extends HttpServlet {

	private static final long serialVersionUID = -344562986673524234L;
	public static final String LOGIN_SUCCESSFUL = "loginsuccess";
	public static final String LOGIN_LOGGEDOUT = "loggedout";
	/**
     * @see HttpServlet#HttpServlet()
     */
    public LoginService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contentType= "text/html;charset=UTF-8";
		response.setContentType(contentType);
		System.out.println("GETTING");
		String strTalentID = request.getParameter("dtcanid");
		String sssPinNumber = request.getParameter("password");
		CandidateEntity candidate;
		try {
			JSONArray returnArray = new JSONArray();
			System.out.println("strTalentId---->"+strTalentID);
			System.out.println("sssPin----->"+sssPinNumber);
			candidate = DOSecurityUtility.validateUser(sssPinNumber,strTalentID);
			JSONObject loginStatus = new JSONObject();
			returnArray.put(loginStatus);  
			//TESTING FORCE LOGIN STATUS BEGIN
//			candidate.setLoginStatus(1);
			//FORCE LOGIN SERVICE END
			loginStatus.put("status", Integer.toString(candidate.wasLoginSuccessful()));
			
			if (candidate.wasLoginSuccessful()==2){
				CandidateGeneralInformation cgi = candidate.getCandidateGeneralInformation();
				
				CandidateProfile cp1 = candidate.getCandidateDetailsForCountry(CandidateProfile.BOCOUNTRY_USA);
				CandidateProfile cp2 = candidate.getCandidateDetailsForCountry(CandidateProfile.BOCOUNTRY_CANADA);
				JSONObject jobj = JSONUtils.getJSONArray("candidategneralinfo",cgi);
				JSONObject jprof1 = new JSONObject();
				JSONObject jprof2 = new JSONObject();
				jprof1.put("profile1","empty");
				jprof2.put("profile2","empty");
				if (cp1!=null) jprof1 = JSONUtils.getJSONArray("profile1", cp1);  
				if (cp2!=null) jprof2 = JSONUtils.getJSONArray("profile2", cp2);
				JSONObject jprof3 = JSONUtils.getComboBoxArray("statelist", CodeListHandler.getStateList(), "data", "label");
				JSONObject jprof4 = JSONUtils.getComboBoxArray("titlelist", CodeListHandler.getTitleList(), "data", "label");
				JSONObject jprof5 = JSONUtils.getComboBoxArray("countrylist", CodeListHandler.getCountryList(), "data", "label");
				returnArray.put(jobj);
				returnArray.put(jprof1);
				returnArray.put(jprof2);
				returnArray.put(jprof3); 
				returnArray.put(jprof4);
				returnArray.put(jprof5);
				request.getSession().setAttribute(DirectOffice_SSSPortlet.SESS_LOGIN_STATUS, LOGIN_SUCCESSFUL);
				request.getSession().setAttribute(DirectOffice_SSSPortlet.SESS_SELECTED_TAB, "0");
				request.getSession().setAttribute(DirectOffice_SSSPortlet.SESS_CAND_GENERAL, returnArray);
				request.getSession().setAttribute(DirectOffice_SSSPortlet.SESS_CAND_ENTITY, candidate);
			}else if (candidate.wasLoginSuccessful()==1){
				request.getSession().setAttribute(DirectOffice_SSSPortlet.SESS_CAND_ENTITY, candidate);
				
			}else{     
				
				
			}

			
//			System.out.println(returnArray.toString());
			response.getWriter().print(returnArray.toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.getWriter().print("LoginStatus:3");
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contentType= "text/html;charset=UTF-8";
		response.setContentType(contentType);
		String strTalentID = request.getParameter("dtcanid");
		String sssPinNumber = request.getParameter("password");
		CandidateEntity candidate;
		System.out.println("BEGIN POST:  ");
		for (Enumeration e = request.getParameterNames();e.hasMoreElements();){
			
			System.out.println("PARAM:  "+e.nextElement());
		}
		
		try {
			JSONArray returnArray = new JSONArray();
			System.out.println("POSTING!!");
			System.out.println("strTalentId---->"+strTalentID);
			System.out.println("sssPin----->"+sssPinNumber);
			candidate = DOSecurityUtility.validateUser(sssPinNumber,strTalentID);
			System.out.println("past candidate....");
			JSONObject loginStatus = new JSONObject();
			returnArray.put(loginStatus);  
			//TESTING FORCE LOGIN STATUS BEGIN
//			candidate.setLoginStatus(1);
			//FORCE LOGIN SERVICE END
			loginStatus.put("status", Integer.toString(candidate.wasLoginSuccessful()));
			
			if (candidate.wasLoginSuccessful()==2){
				CandidateGeneralInformation cgi = candidate.getCandidateGeneralInformation();
				
				CandidateProfile cp1 = candidate.getCandidateDetailsForCountry(CandidateProfile.BOCOUNTRY_USA);
				CandidateProfile cp2 = candidate.getCandidateDetailsForCountry(CandidateProfile.BOCOUNTRY_CANADA);
				JSONObject jobj = JSONUtils.getJSONArray("candidategneralinfo",cgi);
				JSONObject jprof1 = new JSONObject();
				JSONObject jprof2 = new JSONObject();
				jprof1.put("profile1","empty");
				jprof2.put("profile2","empty");
				if (cp1!=null) jprof1 = JSONUtils.getJSONArray("profile1", cp1);  
				if (cp2!=null) jprof2 = JSONUtils.getJSONArray("profile2", cp2);
				JSONObject jprof3 = JSONUtils.getComboBoxArray("statelist", CodeListHandler.getStateList(), "data", "label");
				JSONObject jprof4 = JSONUtils.getComboBoxArray("titlelist", CodeListHandler.getTitleList(), "data", "label");
				JSONObject jprof5 = JSONUtils.getComboBoxArray("countrylist", CodeListHandler.getCountryList(), "data", "label");
				returnArray.put(jobj);
				returnArray.put(jprof1);
				returnArray.put(jprof2);
				returnArray.put(jprof3); 
				returnArray.put(jprof4);
				returnArray.put(jprof5);
				request.getSession().setAttribute(DirectOffice_SSSPortlet.SESS_LOGIN_STATUS, LOGIN_SUCCESSFUL);
				request.getSession().setAttribute(DirectOffice_SSSPortlet.SESS_SELECTED_TAB, "0");
				request.getSession().setAttribute(DirectOffice_SSSPortlet.SESS_CAND_GENERAL, returnArray);
				request.getSession().setAttribute(DirectOffice_SSSPortlet.SESS_CAND_ENTITY, candidate);
			}else if (candidate.wasLoginSuccessful()==1){
				request.getSession().setAttribute(DirectOffice_SSSPortlet.SESS_CAND_ENTITY, candidate);
				
			}else{     
				System.out.println("EXITING WITH ERROR");
				response.getWriter().print("LoginStatus:3");
			}

			
//			System.out.println(returnArray.toString());
			response.getWriter().print(returnArray.toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.getWriter().print("LoginStatus:3");
			e.printStackTrace();
		}
		
	}
	
	


}
