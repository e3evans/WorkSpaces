/*
 * Created on 2006-7-5
 *
 */
package com.manpower.portal.mpnet.dao.interfaces;

import java.util.List;

import com.manpower.portal.mpnet.hbn.beans.CandidateJobTitle;


/**
 * @author alexander.todorov
 *  
 */
public interface JobTitleDAO extends DAO {

	public List findJobTitleBySiteAndLanguage(long siteId,	String language);
	
	public boolean saveJobTitle(List jobTitles, long candidateId);
	
	public void updateJobTitles(List newJobTitles, long candidateID);
	
	public void saveJobTitle(String jobTitles, long candidateId, long defaultJobTitleID);
	
	public void updateJobTitles(String newJobTitles, long jobTitleID);
	
	public CandidateJobTitle find(long candidateID, long jobTitleID);

    
    public List getCandidateJobTitleIdsByCandidate(long candidateId);
    
    public List getCandidateJobTitles(long candidateId, String lang);
}
