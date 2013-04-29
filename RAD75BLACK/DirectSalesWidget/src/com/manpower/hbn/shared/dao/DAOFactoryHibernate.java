/*
 * Created on Nov 14, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.hbn.shared.dao;

import org.hibernate.Session;

import com.manpower.hbn.dao.SQLQueryDAO;
import com.manpower.hbn.dao.impl.SQLQueryDAOImpl;
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
	public SQLQueryDAO getSqlQueryDAO() {
		// TODO Auto-generated method stub
		return new SQLQueryDAOImpl(getCurrentSession());
	}

}
	
	
