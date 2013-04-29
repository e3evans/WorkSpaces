package com.manpower.directtalentrecruitersearch.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;

import com.manpower.directtalentrecruitersearch.dao.CandidateKeywordSearchDAO;
import com.manpower.directtalentrecruitersearch.hbn.shared.HibernateUtilities;
import com.manpower.directtalentrecruitersearch.hbn.shared.dao.GenericHibernateDAO;
import com.manpower.hbn.beans.CandidateSearchResults;
import com.manpower.hbn.beans.ParameterMapKey;

public class CandidateKeywordSearchDAOImpl extends GenericHibernateDAO implements CandidateKeywordSearchDAO {

	public CandidateKeywordSearchDAOImpl(Session session) {
		super(CandidateSearchResults.class, session);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<Object> findAll() {
		// TODO Auto-generated method stub
		return getSession().createCriteria(CandidateSearchResults.class).list();
	}
	
	public List<Object> filterByBranch(String keyword,long siteId,int maxResults,int maxRows,int pageNumber,int branchId){
		
		
		return null;
	}
	
	private List<Object> executeQuery(SQLQuery query,int maxRows,int pageNumber,int maxResults){
		
		/*
		 * Get the correct Page and grab the right number of rows to display.
		 */
		ScrollableResults results = query.scroll(ScrollMode.SCROLL_SENSITIVE);
		List<Object> pageInfo = new ArrayList<Object>(2);
		
		if (results.next()){
			CandidateSearchResults csr = (CandidateSearchResults)results.get(0);
			int numberOfResults = Integer.parseInt(Long.toString(csr.getQ_total()));
			if (numberOfResults > maxResults)numberOfResults=maxResults;
			results.first();
			results.scroll((pageNumber*maxRows)-maxRows);
		
			List<Object> pageList = new ArrayList<Object>();
			int i = 0;
			while (maxRows > i++) {
				pageList.add(results.get(0));
				if (!results.next())break;
			}
			
			/*
			 * PUT THE RESULTS IN ONE SLOT AND THE TOTAL NUMBER OF RESULTS IN ANOTHER....
			 */
			
			pageInfo.add(0, pageList);
			pageInfo.add(1,Integer.toString(numberOfResults));
		}else{
			return null;
		}
		HibernateUtilities.closeSession();
		return pageInfo;
	}
	
	public List<Object> findAllByKeywords(String[] params,long siteId,int maxResults,int maxRows,int pageNumber){
		StringBuffer sb = new StringBuffer();
		StringBuffer countClause = new StringBuffer();
		StringBuffer whereClause = new StringBuffer();
		String keyword = params[ParameterMapKey.keywords];
		String branchId = params[ParameterMapKey.prefBranch];
		String locationId = params[ParameterMapKey.prefLocation];
		String inclCand = params[ParameterMapKey.inclCand];
		String inclApp = params[ParameterMapKey.inclApp];
		String inclgt30 = params[ParameterMapKey.inclgt30];
		
		String tableName = "bjh_resumes";
		sb.append("select cv_id as unique_id,candidate_id,region_id,location_id," +
				"site_id,resume_id,cv_id,count_of_jobs_applied_for,firstname,middlename,status,prefered_location," +
				"branchname,lastname,mime_type,resume_name,cand_type,dateapplied,last_login_date,updatedon,a.q_total " +
				"from ");

		if (!keyword.equals("")){
			whereClause.append("contains(t.resume,'"+keyword+"')>0 and ");
			countClause.append("contains(resume,'"+keyword+"')>0 and ");
		}
		whereClause.append("site_id="+Long.toString(siteId));
		countClause.append("site_id="+Long.toString(siteId));
		if (!locationId.equals("")){
			whereClause.append(" and location_id="+locationId);
			countClause.append(" and location_id="+locationId);
		}
		if (!branchId.equals("")){
			whereClause.append(" and branch_id="+branchId);
			countClause.append(" and branch_id="+branchId);
		}
		if (inclCand.equals("false")){
			whereClause.append(" and cand_type!='C'");
			countClause.append(" and cand_type!='C'");
			}
		if (inclApp.equals("false")){
			whereClause.append(" and cand_type!='A'");
			countClause.append(" and cand_type!='A'");
		}
		whereClause.append(" order by t.last_login_date desc");
		
		/*
		 * Switch over to the view instead of the table and search ALL records...
		 */
		if (inclgt30.equals("true"))tableName="recruiter_loc_rpt_v";
		sb.append(tableName+" t,(select count(*)q_total from "+tableName+" where "+countClause.toString()+") a where "+whereClause.toString());
		
		SQLQuery query = HibernateUtilities.currentSession().createSQLQuery(sb.toString()).addEntity(CandidateSearchResults.class);
		
		query.setMaxResults(maxResults);
//		
		return executeQuery(query, maxRows, pageNumber,maxResults);
	}
	
	public Object findByID(Serializable id) {
		// TODO Auto-generated method stub
		return getSession().get(CandidateSearchResults.class, id);
	}

}
