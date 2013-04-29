/*
 * Created on 2008-3-31
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.DAOFactory;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.JobTitleDAO;
import com.manpower.portal.mpnet.hbn.beans.Candidate;
import com.manpower.portal.mpnet.hbn.beans.CandidateJobTitle;
import com.manpower.portal.mpnet.hbn.beans.JobTitle;

/**
 * @author alexander.todorov
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JobTitleDAOHibernate extends GenericHibernateDAO implements
		JobTitleDAO {

	
	public JobTitleDAOHibernate(Session session) {
		super(JobTitle.class, session);
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findByID(java.io.Serializable)
	 */
	public Object findByID(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findAll()
	 */
	public List findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.interfaces.JobTitleDAO#findJobTitleByLanguage(long, java.lang.String)
	 */
	public List findJobTitleBySiteAndLanguage(long siteId, String language) {
		String jobTitleQuery =	" SELECT jt.Id, jn.jobtitle_name"
			+ " FROM JOBTITLES JT"
			+ " INNER JOIN JOBTITLENAMES JN ON JT.ID = JN.JOBTITLE_ID"
			+ " WHERE JT.SITE_ID = " + siteId +" AND JN.LANG = '" + language + "'";
		List result = findBySQLQuery(jobTitleQuery);
		return result;
	}
	
	public boolean saveJobTitle(List jobTitles, long candidateId){
		boolean result = false;
		if(jobTitles != null && !jobTitles.isEmpty()){
			try{
			Session session = HibernateUtil.getCurrentSession();
			
			Candidate candidate = (Candidate)session.load(Candidate.class, new Long(candidateId));
			String jobTitleID = null;
			List saveList = new ArrayList();
			for(int i=0;i<jobTitles.size();i++){
				CandidateJobTitle candidateJobTitle = new CandidateJobTitle();
				jobTitleID = (String)jobTitles.get(i);
				JobTitle jobTitle = new JobTitle();
				jobTitle.setId(Long.parseLong(jobTitleID));
				candidateJobTitle.setJobTitle(jobTitle);
				candidateJobTitle.setCandidate(candidate);
				saveList.add(candidateJobTitle);
			}
			
			makePersistent(saveList);
				
			}catch(Exception ex){
				result = false;
				ex.printStackTrace();
			}

		}else{
			result = true;
		}
		
		return result;
	}
	
	public void updateJobTitles(List newJobTitles, long candidateID){
			JobTitleDAO jobTitleDAO = DAOFactory.getDAOFactory().getJobTitleDAO();
			Iterator oldJobTitles = getCandidateJobTitles(candidateID).iterator();
			CandidateJobTitle candidateJobTitle;
			while(oldJobTitles.hasNext()){
				candidateJobTitle = (CandidateJobTitle)oldJobTitles.next();
				jobTitleDAO.delete(candidateJobTitle);
			}
			
			if(newJobTitles != null && !newJobTitles.isEmpty()){
				saveJobTitle(newJobTitles, candidateID);
			}
		}

    public List getCandidateJobTitleIdsByCandidate(long candidateId) {
        String jobCategoryQuery = " SELECT jt.JOB_TITLE_ID"
            + " FROM CANDIDATEJOBTITLES jt"
            + " WHERE jt.CANDIDATE_ID = " + candidateId;
        List result = findBySQLQuery(jobCategoryQuery);
        return result;
    }
    
	private List getCandidateJobTitles(long candidateID){
		
		String query = "FROM " + CandidateJobTitle.class.getName() + " AS JT where JT.candidate.id = :cid";
		Properties props = new Properties();
		props.put("cid", String.valueOf(candidateID));
		JobTitleDAO jobTitleDAO = DAOFactory.getDAOFactory().getJobTitleDAO();
		List results = jobTitleDAO.findByCustomQuery(query, props);
		
		return results;
	}
	
	public void saveJobTitle(String jobTitles, long candidateId, long defaultJobTitleID){
		Session session = HibernateUtil.getCurrentSession();
		CandidateJobTitle candidateJobTitle = new CandidateJobTitle();
		Candidate candidate = new Candidate();
		candidate.setId(candidateId);
		JobTitle jobTitle= new JobTitle();
		jobTitle.setId(defaultJobTitleID);
		candidateJobTitle.setJobTitleFreeText(jobTitles);
		candidateJobTitle.setCandidate(candidate);
		candidateJobTitle.setJobTitle(jobTitle);
		session.saveOrUpdate(candidateJobTitle);
		
	}
	
	public void updateJobTitles(String newJobTitles, long jobTitleID){
		
		Session session = HibernateUtil.getCurrentSession();
		CandidateJobTitle candidateJobTitle = (CandidateJobTitle)session.load(CandidateJobTitle.class, new Long(jobTitleID));
		
		if(candidateJobTitle !=null){
			candidateJobTitle.setJobTitleFreeText(newJobTitles);
			session.update(candidateJobTitle);
		}
	}

	public CandidateJobTitle find(long candidateID, long jobTitleID){
		Session session = HibernateUtil.getCurrentSession();
		Criteria query = session.createCriteria(CandidateJobTitle.class);
		query.createCriteria("candidate", "candidate");
		query.createCriteria("jobTitle", "jobTitle");
		query.add(Restrictions.eq("candidate.id", new Long(candidateID)));
		query.add(Restrictions.eq("jobTitle.id", new Long(jobTitleID)));
		
		CandidateJobTitle candidateJobTitle = (CandidateJobTitle)query.uniqueResult();
		
		return candidateJobTitle;
		
	}
	
	public List getCandidateJobTitles(long candidateId, String lang){
		
		String query = "SELECT JOBTITLE_NAME " +
				" FROM (" +
				" SELECT JN.JOBTITLE_NAME AS JOBTITLE_NAME, CANDIDATE_ID " +
				" FROM candidatejobtitles CJ " +
				" INNER JOIN JOBTITLENAMES JN ON JN.JOBTITLE_ID = CJ.JOB_TITLE_ID " +
				" WHERE JN.LANG = '" + lang + "' " +
				" UNION ALL " +
				" SELECT JOB_TITLE_FREE_FORM AS JOBTITLE_NAME, CANDIDATE_ID " +
				" FROM candidatejobtitles CJ " +
				" INNER JOIN JOBTITLES JN ON JN.ID = CJ.JOB_TITLE_ID " +
				" WHERE JN.CODE = 'DEFAULT') " +
				" WHERE CANDIDATE_ID = " + candidateId;
		List result = super.findBySQLQuery(query);
		
		return result;
	}
}
