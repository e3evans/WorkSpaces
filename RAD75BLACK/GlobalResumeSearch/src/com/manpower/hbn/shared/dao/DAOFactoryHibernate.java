/*
 * Created on Nov 14, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.hbn.shared.dao;

import org.hibernate.Session;

import com.manpower.hbn.globalresumesearch.dao.CandidateResumeInfoDAO;
import com.manpower.hbn.globalresumesearch.dao.impl.CandidateResumeInfoDAOimpl;
import com.manpower.hbn.shared.HibernateUtilities;




/**
 *
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DAOFactoryHibernate extends DAOFactory {

	protected Session getCurrentSession() {
        return HibernateUtilities.currentSession();
    }

	@Override
	public CandidateResumeInfoDAO getCandidateResumeInfoDAO() {
		// TODO Auto-generated method stub
		return new CandidateResumeInfoDAOimpl(getCurrentSession());
	}

	
	
//	public SQLQueryDAO getSqlQueryDAO() {
//		// TODO Auto-generated method stub
//		return new SQLQueryDAOImpl(getCurrentSession());
//	}

}
	
	
