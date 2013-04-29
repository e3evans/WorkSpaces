/*
 * Created on Nov 14, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.directtalentrecruitersearch.hbn.shared.dao;

import org.hibernate.Session;

import com.manpower.directtalentrecruitersearch.dao.BranchesDAO;
import com.manpower.directtalentrecruitersearch.dao.CandidateKeywordSearchDAO;
import com.manpower.directtalentrecruitersearch.dao.PrefLocFilterDAO;
import com.manpower.directtalentrecruitersearch.dao.PreferredLocationsDAO;
import com.manpower.directtalentrecruitersearch.dao.RecruiterAuditingDAO;
import com.manpower.directtalentrecruitersearch.dao.ResumeDAO;
import com.manpower.directtalentrecruitersearch.dao.impl.BranchesDAOImpl;
import com.manpower.directtalentrecruitersearch.dao.impl.CandidateKeywordSearchDAOImpl;
import com.manpower.directtalentrecruitersearch.dao.impl.PrefLocFilterDAOImpl;
import com.manpower.directtalentrecruitersearch.dao.impl.PreferredLocationsDAOImpl;
import com.manpower.directtalentrecruitersearch.dao.impl.RecruiterAuditingDAOHibernate;
import com.manpower.directtalentrecruitersearch.dao.impl.ResumeDAOImpl;
import com.manpower.directtalentrecruitersearch.hbn.shared.HibernateUtilities;




/**
 *
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DAOFactoryHibernate extends DAOFactory {

	protected Session getCurrentSession() {
        return HibernateUtilities.currentSession();
    }

	public CandidateKeywordSearchDAO getCandidateKeywordSearchDAO() {
		// TODO Auto-generated method stub
		return new CandidateKeywordSearchDAOImpl(getCurrentSession());
	}
	
	public PreferredLocationsDAO getPreferredLocationsDAO(){
		return new PreferredLocationsDAOImpl(getCurrentSession());	
	}
	
	public BranchesDAO getBranchesDAO(){
		return new BranchesDAOImpl(getCurrentSession());
	}

	public PrefLocFilterDAO getPrefLocFilterDAO() {
		// TODO Auto-generated method stub
		return new PrefLocFilterDAOImpl(getCurrentSession());
	}
	
	public ResumeDAO getResumeDAO(){
		return new ResumeDAOImpl(getCurrentSession());
	}

	public RecruiterAuditingDAO getRecruiterAuditingDAO() {
		return new RecruiterAuditingDAOHibernate(getCurrentSession());
	}

}
	
	
