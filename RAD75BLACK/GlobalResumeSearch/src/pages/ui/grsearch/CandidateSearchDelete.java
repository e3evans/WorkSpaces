package pages.ui.grsearch;

import java.util.List;

import com.manpower.lucene.globalresumesearch.crawler.IndexService;
import com.opensymphony.xwork2.ActionSupport;

public class CandidateSearchDelete extends ActionSupport {

	private static final long serialVersionUID = 308176653766821991L;
	private String searchCriteria;
	private List<Object> searchFields;
	private String resultsList;
	
	public String getSearchCriteria() {
		return searchCriteria;
	}
	public void setSearchCriteria(String searchCriteria) {
		this.searchCriteria = searchCriteria;
	}
	public List<Object> getSearchFields() {
		return searchFields;
	}
	public void setSearchFields(List<Object> searchFields) {
		this.searchFields = searchFields;
	}
	public String getResultsList() {
		return resultsList;
	}
	public void setResultsList(String resultsList) {
		this.resultsList = resultsList;
	}
	public String execute() throws Exception{
		System.out.println("TESTING DELETE!!!");
		System.out.println("FIELD VALUE:  "+getSearchCriteria());
		IndexService.getInstance().deleteDocument("candidateid", getSearchCriteria());
		return "SUCCESS";
	}
	

}
