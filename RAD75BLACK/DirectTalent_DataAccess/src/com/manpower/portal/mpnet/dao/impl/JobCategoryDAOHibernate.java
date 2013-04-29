/*
 * Created on 2008-4-3
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.DAOFactory;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.JobCategoryDAO;
import com.manpower.portal.mpnet.hbn.beans.Candidate;
import com.manpower.portal.mpnet.hbn.beans.CandidateJobCategory;
import com.manpower.portal.mpnet.hbn.beans.JobCategory;
import com.manpower.portal.mpnet.hbn.beans.JobTitle;

/**
 * @author Vlado
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class JobCategoryDAOHibernate extends GenericHibernateDAO implements
		JobCategoryDAO {

	public JobCategoryDAOHibernate(Session session) {
		super(JobTitle.class, session);
	}

	/**
	 * @param persistentClass
	 * @param session
	 */
	public JobCategoryDAOHibernate(Class persistentClass, Session session) {
		super(persistentClass, session);
	}

	public Object findByID(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List findAll() {
		Session session = HibernateUtil.getCurrentSession();
        Query query = session.createQuery("from " + CandidateJobCategory.class.getName());
		return query.list();
	}

	public List findJobCategoryBySiteAndLanguage(long siteId, String language) {
		String jobCategoryQuery = " SELECT jc.ID, jcn.JOBCATEGORY_NAME"
				+ " FROM JOBCATEGORIES jc"
				+ " INNER JOIN JOBCATEGORYNAMES jcn ON jc.ID = jcn.JOBCATEGORY_ID"
				+ " WHERE jc.SITE_ID = " + siteId + " AND jcn.LANG = '"
				+ language + "'";
		List result = findBySQLQuery(jobCategoryQuery);
		return result;
	}

	public boolean saveJobCategory(List jobCategories, long candidateId){
		boolean result = false;
        
        if(jobCategories != null && !jobCategories.isEmpty()){
            
            Session session = HibernateUtil.getCurrentSession();
            
            Candidate candidate = (Candidate)session.load(Candidate.class, new Long(candidateId));
            String jobCategoryID = null;
            List saveList = new ArrayList();
            for(int i=0 ; i < jobCategories.size() ; i++){
                CandidateJobCategory candidateJobCategory = new CandidateJobCategory();
                jobCategoryID = (String)jobCategories.get(i);
                JobCategory jobCategory = new JobCategory();
                jobCategory.setId(Long.parseLong(jobCategoryID));
                candidateJobCategory.setJobCategory(jobCategory);
                candidateJobCategory.setCandidate(candidate);
                saveList.add(candidateJobCategory);
            }
            
            try{
                makePersistent(saveList);
            }catch(Exception ex){
                result = false;
            }

        }else{
            result = true;
        }
        
        return result;
	}

	public void updateJobCategories(List newJobCategories, long candidateID){
		JobCategoryDAO jobCategoryDAO = DAOFactory.getDAOFactory()
				.getJobCategoryDAO();
		Iterator oldJobCategories = getCandidateJobCategories(candidateID)
				.iterator();
		//TODO - not the best way to "UPDATE" an entity
		CandidateJobCategory candidateJobCategory;
		while (oldJobCategories.hasNext()) {
			candidateJobCategory = (CandidateJobCategory) oldJobCategories.next();
			jobCategoryDAO.delete(candidateJobCategory);
		}

		if (newJobCategories != null && !newJobCategories.isEmpty()) {
			saveJobCategory(newJobCategories, candidateID);
		}
	}
    
    public List getCandidateJobCategoryIdsByCandidate(long candidateId) {
        String jobCategoryQuery = " SELECT jc.JOB_CATEGORY_ID"
            + " FROM CANDIDATEJOBCATEGORIES jc"
            + " WHERE jc.CANDIDATE_ID = " + candidateId;
        List result = findBySQLQuery(jobCategoryQuery);
        return result;
    }
    
    private List getCandidateJobCategories(long candidateID){
        Session session = HibernateUtil.getCurrentSession();
        Query query = session.createQuery("from CandidateJobCategory where candidate.id = :id");
        query.setParameter("id", new Long(candidateID));
        
        return query.list();
    }
}
