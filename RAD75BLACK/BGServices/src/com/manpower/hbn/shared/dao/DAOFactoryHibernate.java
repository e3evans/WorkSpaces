/*
 * Created on Nov 14, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.hbn.shared.dao;

import org.hibernate.Session;


import com.manpower.hbn.dao.AdvertContactDAO;
import com.manpower.hbn.dao.AdvertDAO;
import com.manpower.hbn.dao.BranchesDAO;
import com.manpower.hbn.dao.impl.AdvertContactDAOImpl;
import com.manpower.hbn.dao.impl.AdvertDAOImpl;
import com.manpower.hbn.dao.impl.BranchesDAOImpl;
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

	public BranchesDAO getBranchesDAO() {
		return new BranchesDAOImpl(getCurrentSession());
	}

	public AdvertContactDAO getAdvertContactDAO() {
		return new AdvertContactDAOImpl(getCurrentSession());
	}

	public AdvertDAO getAdvertDAO() {
		return new AdvertDAOImpl(getCurrentSession());
	}
	


}
	
	
