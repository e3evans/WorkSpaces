package com.manpower.portal.portlet.directoffice_sssportlet.servlets;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.manpower.directoffice.api.DOSecurityUtility;
import com.manpower.directoffice.pojos.Address;
import com.manpower.directoffice.pojos.CandidateEntity;
import com.manpower.directoffice.pojos.CandidateGeneralInformation;
import com.manpower.directoffice.pojos.CandidateProfile;
import com.manpower.portal.portlet.directoffice_sssportlet.DirectOffice_SSSPortlet;
import com.manpower.portal.portlet.directoffice_sssportlet.utils.JSONUtils;

/**
 * Servlet implementation class ManageProfileService
 */
public class ManageProfileService extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 356222387813277879L;
	private static int US_Profile = 2; 
	private static int CAN_Profile = 3;  
	private static final String decodeCharset = "utf-8";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageProfileService() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contentType= "text/html;charset=UTF-8";
		response.setContentType(contentType);

		String action = "";
		if (request.getParameter("action")!=null)action = request.getParameter("action");
		if (!action.equals("")){
			CandidateEntity candidate = (CandidateEntity) request.getSession().getAttribute(DirectOffice_SSSPortlet.SESS_CAND_ENTITY);
			if (action.equals("updategeneralinfo")){
				response.getWriter().print(updateCandidateGeneralInfo(request, candidate));
			}else if (action.equals("updateprofileinfo")){
				response.getWriter().print(updateProfileInfo(request, candidate));
			}else if (action.equals("getsessioncandidate")){
				response.getWriter().print(getSessionCandidate(request));
			}else if (action.equals("updatetab")){
				updateTab(request);
			}else if (action.equals("updatepassword")){
				response.getWriter().print(updatePassword(request,candidate));
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */ 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}
	
	private String updatePassword(HttpServletRequest request,CandidateEntity candidate){
		
		request.getParameter("ssspassword");
		int retValue = DOSecurityUtility.changeUserid
						(request.getParameter("ssspassword"),candidate.getCandidateGeneralInformation().getDOEntityID());
		if (retValue == 0)return "success";
		
		return "fail";
	}
	
	private void updateTab(HttpServletRequest request){
		request.getSession().setAttribute(DirectOffice_SSSPortlet.SESS_SELECTED_TAB, request.getParameter("tabnumber"));
	}
	
	private String getSessionCandidate(HttpServletRequest request){
		String temp ="";
		if (request.getSession().getAttribute(DirectOffice_SSSPortlet.SESS_CAND_GENERAL)!=null){
			JSONArray jarray = (JSONArray)request.getSession().getAttribute(DirectOffice_SSSPortlet.SESS_CAND_GENERAL);
			temp = jarray.toString();
			
		}
		return temp;
	}
	private String updateProfileInfo(HttpServletRequest request,CandidateEntity candidate){
		int updatewhich = 0;
		String profilevarname = "";
		CandidateGeneralInformation cgi = candidate.getCandidateGeneralInformation();
		CandidateProfile cp = null;
		if (request.getParameter("updateprofile").equals("updateprofile1")){
			System.out.println("UPDATING PROF1");
			cp = candidate.getCandidateDetailsForCountry(CandidateProfile.BOCOUNTRY_USA);
			updatewhich = US_Profile;
			profilevarname = "profile1";
		}else if (request.getParameter("updateprofile").equals("updateprofile2")){
			System.out.println("UPDATING PROF2");
			cp = candidate.getCandidateDetailsForCountry(CandidateProfile.BOCOUNTRY_CANADA);
			updatewhich = CAN_Profile;
			profilevarname = "profile2";
		}else{
			//THESE ARE OUR ONLY TWO PROFILES RIGHT NOW.  IF WE GET TO HERE SOMETHING HAS GONE WRONG FAIL GRACEFULLY.
			return "";
		}
		cp.setIsPayAddressDifferentThanMain(request.getParameter("payAddressDifferent"));
		try {
			String tempDateS = request.getParameter("dateOfBirthString");
//			System.out.println("DOBSTRING:  "+URLDecoder.decode(tempDateS,decodeCharset));
			SimpleDateFormat incomingDateFormat = new SimpleDateFormat("MM/dd/yyyy");
			if (!tempDateS.equals("")){
				Date dob = incomingDateFormat.parse(URLDecoder.decode(tempDateS,decodeCharset));
				cgi.setDateOfBirth(dob);
			}
			String tempDateWv = request.getParameter("workVisaExpirationDate");
//			System.out.println("WVD:  "+tempDateWv);
			if (!tempDateWv.equals("")){
				Date wve = incomingDateFormat.parse(URLDecoder.decode(tempDateWv,decodeCharset));
				cp.setWorkVisaExpirationDate(wve);
			}
			
			Address address = cp.getPayrollAddress();
			address.setMunicipality(URLDecoder.decode(request.getParameter("paddressmunicipality"),decodeCharset));
			address.setAddressLine1(URLDecoder.decode(request.getParameter("paddressline1"),decodeCharset));
			
			address.setAddressLine2(URLDecoder.decode(request.getParameter("paddressline2"),decodeCharset));
			address.setCountry(URLDecoder.decode(request.getParameter("payaddresscountry"),decodeCharset));
			address.setState_province(URLDecoder.decode(request.getParameter("payaddressprovince"),decodeCharset));
			address.setPostal_code(URLDecoder.decode(request.getParameter("paddresspostalcode"),decodeCharset));
			cp.setPayrollAddress(address);
			

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();  
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		cp.setLegallyEntiltedToWork(request.getParameter("legallyEntiltedToWork"));
		cp.setHasWorkVisa(request.getParameter("hasWorkVisa"));
		cp.setAcceptDirectDepositByEmail(request.getParameter("acceptDirectDepositByEmail"));
		cp.setIsEighteenOrOlder(request.getParameter("eighteenOrOlder"));	
		cp.setIsWillingToTakeDrugTest(request.getParameter("isWillingToTakeDrugTest"));
		cp.setHaveGovermentID(request.getParameter("hasGovernmentID"));
		cp.setIsPayAddressDifferentThanMain(request.getParameter("payAddressDifferent"));
		cp.setCriminalConvictions(request.getParameter("criminalConvictions"));
		cp.setCitizen(request.getParameter("citizen"));
		candidate.addNewCandidateProfileForCountry(cp, CandidateProfile.BOCOUNTRY_USA);
		candidate.setCandidateGeneralInformation(cgi);
		DOSecurityUtility.updateGeneralInformation(candidate);
		DOSecurityUtility.updateProfileForCountry(candidate, CandidateProfile.BOCOUNTRY_USA);
		JSONArray jarray = (JSONArray)request.getSession().getAttribute(DirectOffice_SSSPortlet.SESS_CAND_GENERAL);

		try {
			jarray.put(1,JSONUtils.getJSONArray("candidategneralinfo",cgi));
			jarray.put(updatewhich, JSONUtils.getJSONArray(profilevarname, cp));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getSession().setAttribute(DirectOffice_SSSPortlet.SESS_CAND_ENTITY, candidate);
		request.getSession().setAttribute(DirectOffice_SSSPortlet.SESS_CAND_GENERAL,jarray);
		request.getSession().setAttribute(DirectOffice_SSSPortlet.SESS_SELECTED_TAB, Integer.toString(updatewhich-1));
		
		return "";
	}
	
	private String updateCandidateGeneralInfo(HttpServletRequest request,CandidateEntity candidate){
//			System.out.println("STARTING UPDATE GENERAL FUNCTION.");
		try {
			JSONArray returnArray = (JSONArray)request.getSession().getAttribute(DirectOffice_SSSPortlet.SESS_CAND_GENERAL);
//			System.out.println("GOT SESSION OBJECT!");
			CandidateGeneralInformation cgi = candidate.getCandidateGeneralInformation();
			cgi.setEmergencyContactName(URLDecoder.decode(request.getParameter("emergencyContactName"),decodeCharset));
			cgi.setEmergencyContactPhone(URLDecoder.decode(request.getParameter("emergencyContactPhone"),decodeCharset));
			candidate.setCandidateGeneralInformation(cgi);
			JSONObject jobj = JSONUtils.getJSONArray("candidategneralinfo",cgi);
			returnArray.put(1,jobj);
			request.getSession().setAttribute(DirectOffice_SSSPortlet.SESS_CAND_GENERAL, returnArray);
			request.getSession().setAttribute(DirectOffice_SSSPortlet.SESS_CAND_ENTITY, candidate);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DOSecurityUtility.updateGeneralInformation(candidate);
		return "";
	}

}
