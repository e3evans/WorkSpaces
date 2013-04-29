package com.manpower.directtalentrecruitersearch.dao;

import java.io.Serializable;
import java.util.List;


public interface CandidateKeywordSearchDAO {
	public List findAll();
	public Object findByID(Serializable id); 
//	public List findAllByKeywords(String keyword,long locationid,long siteId,int maxResults,int maxRows,int pageNumber);
	public List findAllByKeywords(String[] params,long siteId,int maxResults,int maxRows,int pageNumber);
}
