package testing;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 */
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		List tempList = DAOFactory.getDAOFactory().getCandidateReportServiceDAO().getCandidateReport();
//		List tempList2 = DAOFactory.getDAOFactory().getCandidateReportServiceDAO().getCandidatesByDay("5");
//		System.out.println("LENGTH:  "+tempList2.size());
//		response.setContentType("text/html; charset=UTF-8");
//		
//		System.out.println(tempList2.size());
//		try {
//			JSONArray temp = new JSONArray();
////			temp.put(getJSONArray("CANDIDATECOUNT",tempList));
//
//			temp.put(getJSONArray("DAILYACTIVITY", tempList2));
//
//			response.getWriter().println(temp.toString());
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text/html; charset=UTF-8");
//		List candidatecounts = DAOFactory.getDAOFactory().getCandidateReportServiceDAO().getCandidateReport();
//		List adList = DAOFactory.getDAOFactory().getAdverReportServiceDAO().getAdvertReport();
//		List lastSevenDays = DAOFactory.getDAOFactory().getAdverReportServiceDAO().getAdsLastSevenDays();
//		List countrySelector = DAOFactory.getDAOFactory().getAdverReportServiceDAO().getCountrySelectorWithCount();
//		List dailyactivity = DAOFactory.getDAOFactory().getCandidateReportServiceDAO().getCandidatesByDay("7");
//		try {
//			JSONArray temp = new JSONArray();
//			temp.put(JSONUtils.getJSONArray("CANDIDATECOUNT",candidatecounts));
//			temp.put(JSONUtils.getJSONArray("ADCOUNTS", adList));
//			temp.put(JSONUtils.getJSONArray("CANDCOUNTLIST",candidatecounts));
//			temp.put(JSONUtils.getJSONArray("LASTSEVEN", lastSevenDays));
//			temp.put(JSONUtils.getJSONArray("COUNTRYSELECTOR", countrySelector));
//			temp.put(JSONUtils.getJSONArray("DAILYACTIVITY",dailyactivity));
//			response.getWriter().println(temp.toString());
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	
	}
	

	

	

}
