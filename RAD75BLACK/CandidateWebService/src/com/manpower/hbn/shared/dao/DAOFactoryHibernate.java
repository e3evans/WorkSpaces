/*
 * Created on Nov 14, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.hbn.shared.dao;

import org.hibernate.Session;

import com.manpower.hbn.candidateidservice.dao.CandidateIdDAO;
import com.manpower.hbn.candidateidservice.dao.impl.CandidateIdDAOImpl;
import com.manpower.hbn.shared.HibernateUtilities;




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

	public CandidateIdDAO getCandidateIdDAO() {
		// TODO Auto-generated method stub
		return new CandidateIdDAOImpl(getCurrentSession());
	}

	
	
//	public BranchesDAO getBranchesDAO() {
//		return new BranchesDAOImpl(getCurrentSession());
//	}
//
//	public AdvertContactDAO getAdvertContactDAO() {
//		return new AdvertContactDAOImpl(getCurrentSession());
//	}
//
//	public AdvertDAO getAdvertDAO() {
//		return new AdvertDAOImpl(getCurrentSession());
//	}
//	


}
	
	
