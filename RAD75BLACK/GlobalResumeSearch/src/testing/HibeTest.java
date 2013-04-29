package testing;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.manpower.lucene.globalresumesearch.crawler.IndexService;

/**
 * Servlet implementation class HibeTest
 */
public class HibeTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HibeTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

 
//		System.out.println("STARTING INDEXING");
//		DAOFactory.getDAOFactory().getCandidateResumeInfoDAO().indexAllDocuments();
//		IndexService.getInstance().indexAllDTResumes(200, 6000);
		System.out.println("START OPTIMIZE...");
		IndexService.getInstance().optimizeIndex();
		System.out.println("OPTIMIZE FINISH....");
//		System.out.println("BEGIN TROUBlESHOOT....");
//		
//		SearchCandidate sc = (SearchCandidate) DAOFactory.getDAOFactory().getCandidateResumeInfoDAO().getCandidateResume(new Long("206828"));
//		CrawlerService.indexDocument(sc);
//		
//		System.out.println("END TROUBLESHOOT");

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
