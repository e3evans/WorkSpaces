/*
 * Created on 2008-4-3
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.mpnet.dao.interfaces;

import java.util.List;

/**
 * @author Vlado
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface JobCategoryDAO extends DAO{

    public List findJobCategoryBySiteAndLanguage(long siteId,  String language);
    
    public boolean saveJobCategory(List jobCategories, long candidateId);
    
    public void updateJobCategories(List newJobCategories, long candidateID);
    
    public List getCandidateJobCategoryIdsByCandidate(long candidateId);
    
}
