package com.manpower.hbn.globalresumesearch.dao;

import java.util.List;

import org.hibernate.ScrollableResults;

public interface CandidateResumeInfoDAO {

	public List<Object> findAllbyPage(int pageNumber,int numofRows);
	
	public List<Object> findAllbyPageNoResume(int pageNumber,int numofRows);
	
	public Object getCandidateResume(long resumeId);
	
	public ScrollableResults indexAllDocuments();
	
}
