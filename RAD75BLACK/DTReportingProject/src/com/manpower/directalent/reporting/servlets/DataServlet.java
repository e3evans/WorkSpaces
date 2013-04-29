package com.manpower.directalent.reporting.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;

import com.manpower.directalent.reporting.hbn.shared.dao.DAOFactory;
import com.manpower.directaltent.reporting.utils.JSONUtils;
import com.manpower.directaltent.reporting.utils.UUIDFunctions;

/**
 * Servlet implementation class DataServlet
 */
public class DataServlet extends HttpServlet {
	public static String ACTION_PARAM = "reportaction";
	public static String ACTION_GETBRANCHES = "getbranches";
	public static String ACTION_GETDAILYREG = "getdailyreg";
	public static String ACTION_INIT = "init";
	public static String PARAM_SITEID = "siteid";
	public static String PARAM_NUMOFDAYS = "numofdays";
	public static String PARAM_UUIDTOKEN = "paramToken";
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		List actionResult = new ArrayList<Object>();
		if (request.getParameter(ACTION_PARAM)!=null && request.getParameter(PARAM_UUIDTOKEN)!=null){
			String actionparam = request.getParameter(ACTION_PARAM);
			String uuidToken = request.getParameter(PARAM_UUIDTOKEN);
			if (UUIDFunctions.isValidUUID(uuidToken)){
				if (actionparam.equals(ACTION_GETBRANCHES)){
					String siteid = request.getParameter(PARAM_SITEID);
					actionResult = DAOFactory.getDAOFactory().getAdverReportServiceDAO().getAdvertsByBranch(siteid);
					try {
						JSONArray temp = new JSONArray();
						temp.put(JSONUtils.getJSONArray("BRANCHLIST", actionResult));
						response.getWriter().println(temp.toString());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						response.getWriter().println("ERROR.");
					}
				}
				else if (actionparam.equals(ACTION_GETDAILYREG)){
					String numofDays = request.getParameter(PARAM_NUMOFDAYS);
					actionResult = DAOFactory.getDAOFactory().getCandidateReportServiceDAO().getCandidatesByDay(numofDays);
					try {
						JSONArray temp = new JSONArray();
						temp.put(JSONUtils.getJSONArray("DAILYACTIVITY", actionResult));
						response.getWriter().println(temp.toString());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						response.getWriter().println("ERROR.");
					}
				}
				else if (actionparam.equals(ACTION_INIT)){
					List candidatecounts = DAOFactory.getDAOFactory().getCandidateReportServiceDAO().getCandidateReport();
					List adList = DAOFactory.getDAOFactory().getAdverReportServiceDAO().getAdvertReport();
					List lastSevenDays = DAOFactory.getDAOFactory().getAdverReportServiceDAO().getAdsLastSevenDays();
					List countrySelector = DAOFactory.getDAOFactory().getAdverReportServiceDAO().getCountrySelectorWithCount();
					List dailyactivity = DAOFactory.getDAOFactory().getCandidateReportServiceDAO().getCandidatesByDay("7");
					try {
						JSONArray temp = new JSONArray();
						temp.put(JSONUtils.getJSONArray("CANDIDATECOUNT",candidatecounts));
						temp.put(JSONUtils.getJSONArray("ADCOUNTS", adList));
						temp.put(JSONUtils.getJSONArray("CANDCOUNTLIST",candidatecounts));
						temp.put(JSONUtils.getJSONArray("LASTSEVEN", lastSevenDays));
						temp.put(JSONUtils.getJSONArray("COUNTRYSELECTOR", countrySelector));
						temp.put(JSONUtils.getJSONArray("DAILYACTIVITY",dailyactivity));
						response.getWriter().println(temp.toString());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else{
				response.getWriter().println("BAD_TOKEN");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		List actionResult = new ArrayList<Object>();
		if (request.getParameter(ACTION_PARAM)!=null && request.getParameter(PARAM_UUIDTOKEN)!=null){
			String actionparam = request.getParameter(ACTION_PARAM);
			String uuidToken = request.getParameter(PARAM_UUIDTOKEN);
			if (UUIDFunctions.isValidUUID(uuidToken)){
				if (actionparam.equals(ACTION_GETBRANCHES)){
					String siteid = request.getParameter(PARAM_SITEID);
					actionResult = DAOFactory.getDAOFactory().getAdverReportServiceDAO().getAdvertsByBranch(siteid);
					try {
						JSONArray temp = new JSONArray();
						temp.put(JSONUtils.getJSONArray("BRANCHLIST", actionResult));
						response.getWriter().println(temp.toString());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						response.getWriter().println("ERROR.");
					}
				}
				else if (actionparam.equals(ACTION_GETDAILYREG)){
					String numofDays = request.getParameter(PARAM_NUMOFDAYS);
					actionResult = DAOFactory.getDAOFactory().getCandidateReportServiceDAO().getCandidatesByDay(numofDays);
					try {
						JSONArray temp = new JSONArray();
						temp.put(JSONUtils.getJSONArray("DAILYACTIVITY", actionResult));
						response.getWriter().println(temp.toString());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						response.getWriter().println("ERROR.");
					}
				}
				else if (actionparam.equals(ACTION_INIT)){
					List candidatecounts = DAOFactory.getDAOFactory().getCandidateReportServiceDAO().getCandidateReport();
					List adList = DAOFactory.getDAOFactory().getAdverReportServiceDAO().getAdvertReport();
					List lastSevenDays = DAOFactory.getDAOFactory().getAdverReportServiceDAO().getAdsLastSevenDays();
					List countrySelector = DAOFactory.getDAOFactory().getAdverReportServiceDAO().getCountrySelectorWithCount();
					List dailyactivity = DAOFactory.getDAOFactory().getCandidateReportServiceDAO().getCandidatesByDay("7");
					try {
						JSONArray temp = new JSONArray();
						temp.put(JSONUtils.getJSONArray("CANDIDATECOUNT",candidatecounts));
						temp.put(JSONUtils.getJSONArray("ADCOUNTS", adList));
						temp.put(JSONUtils.getJSONArray("CANDCOUNTLIST",candidatecounts));
						temp.put(JSONUtils.getJSONArray("LASTSEVEN", lastSevenDays));
						temp.put(JSONUtils.getJSONArray("COUNTRYSELECTOR", countrySelector));
						temp.put(JSONUtils.getJSONArray("DAILYACTIVITY",dailyactivity));
						response.getWriter().println(temp.toString());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else{
				response.getWriter().println("BAD_TOKEN");
			}
		}
		
	}


}
