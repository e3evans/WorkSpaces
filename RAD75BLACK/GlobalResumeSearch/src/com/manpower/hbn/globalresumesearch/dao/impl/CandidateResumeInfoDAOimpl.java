package com.manpower.hbn.globalresumesearch.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.CacheMode;
import org.hibernate.FlushMode;
import org.hibernate.SQLQuery;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;

import com.manpower.hbn.beans.SearchCandidate;
import com.manpower.hbn.beans.SearchCandidateNoResume;
import com.manpower.hbn.globalresumesearch.dao.CandidateResumeInfoDAO;
import com.manpower.hbn.shared.HibernateUtilities;
import com.manpower.hbn.shared.dao.GenericHibernateDAO;

public class CandidateResumeInfoDAOimpl extends GenericHibernateDAO implements CandidateResumeInfoDAO {

	public CandidateResumeInfoDAOimpl(Session session) {
		super(SearchCandidate.class, session);
	}

	@Override
	public List<Object> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object findByID(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public Object getCandidateResume(long resumeId) {
		StringBuffer sb = new StringBuffer();
		sb.append("select t.id as id,t.status as status,t.nationality as nationality,(select count(*) from candidates)as mycount,t.nationalnumber as nationalnumber,t.native_language as nativelanguage, t.email as email,t.correspondemail as correspondemail,t.firstname as firstname,t.middlename as middlename,t.lastname as lastname, nvl(r.id,0) as resumeid, r.name as resumename,r.mime_type as mimetype,r.resume as resume from candidates t,candidateresumes r " +
				"where t.id=r.candidate_id(+) and r.id="+Long.toString(resumeId));	
		SQLQuery query = HibernateUtilities.currentSession().createSQLQuery(sb.toString()).addEntity(SearchCandidate.class);
		List tempList = query.list();
		
		if (tempList.size()>0)return tempList.get(0);
		return null;
	}
	

	public List<Object> findAllbyPageNoResume(int pageNumber, int numofRows) {
		StringBuffer sb = new StringBuffer();
		sb.append("select t.id as id,t.status as status,t.nationality as nationality,t.nationalnumber as nationalnumber,t.native_language as nativelanguage, t.email as email,t.correspondemail as correspondemail,t.firstname as firstname,t.middlename as middlename,t.lastname as lastname from candidates t");
			
		//GOOD ONE 22632 / 22670
		
		SQLQuery query = HibernateUtilities.currentSession().createSQLQuery(sb.toString()).addEntity(SearchCandidateNoResume.class);
		return executePagedQuery(query, numofRows, pageNumber);
	}

	public List<Object> findAllbyPage(int pageNumber,int numofRows){
		
		StringBuffer sb = new StringBuffer();
		sb.append("select t.id as id,t.status as status,t.nationality as nationality,(select count(*) from candidates)as mycount,t.nationalnumber as nationalnumber,t.native_language as nativelanguage, t.email as email,t.correspondemail as correspondemail,t.firstname as firstname,t.middlename as middlename,t.lastname as lastname, nvl(r.id,0) as resumeid, r.name as resumename,r.mime_type as mimetype,r.resume as resume from candidates t,candidateresumes r " +
				"where t.id=r.candidate_id(+)");		
		
		//GOOD ONE 22632 / 22670
		
		SQLQuery query = HibernateUtilities.currentSession().createSQLQuery(sb.toString()).addEntity(SearchCandidate.class);
		return executePagedQuery(query, numofRows, pageNumber);
	}
	
	
	
	
	public ScrollableResults indexAllDocuments() {
		StringBuffer sb = new StringBuffer();
		sb.append("select t.id as id,t.status as status,t.nationality as nationality,(select count(*) from candidates)as mycount,t.nationalnumber as nationalnumber,t.native_language as nativelanguage, t.email as email,t.correspondemail as correspondemail,t.firstname as firstname,t.middlename as middlename,t.lastname as lastname, nvl(r.id,0) as resumeid, r.name as resumename,r.mime_type as mimetype,r.resume as resume from candidates t,candidateresumes r " +
				"where t.id=r.candidate_id(+)");		
		
		//GOOD ONE 22632 / 22670
		Session sess = HibernateUtilities.currentSession();
		sess.setFlushMode(FlushMode.AUTO);
	    sess.setCacheMode(CacheMode.GET);
	    
		SQLQuery query = sess.createSQLQuery(sb.toString()).addEntity(SearchCandidate.class);
		ScrollableResults results = query.scroll(ScrollMode.FORWARD_ONLY);
		
		
		return results;
		
	}

	private List<Object> executePagedQuery(SQLQuery query,int maxRows,int pageNumber){
		
		/*
		 * Get the correct Page and grab the right number of rows to display.
		 */
		ScrollableResults results = query.scroll(ScrollMode.SCROLL_SENSITIVE);
	
		List<Object> pageList = new ArrayList<Object>();
		
		if (results.next()){
			results.first();
			results.scroll((pageNumber*maxRows)-maxRows);
			
		
			int i = 0;
			while (maxRows > i++) {
				pageList.add(results.get(0));
				if (!results.next())break;
			}
			
	
		}else{
			return null;
		}
		HibernateUtilities.closeSession();
		return pageList;
	}

	
	
	
}
