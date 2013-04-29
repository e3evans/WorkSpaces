/*
 * Created on Nov 14, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.directtalentseo.hbn.shared.dao;

import org.hibernate.Session;

import com.manpower.directtalentseo.hbn.dao.TadContactDAO;
import com.manpower.directtalentseo.hbn.dao.VActiveAdvertsDAO;
import com.manpower.directtalentseo.hbn.dao.VAdsIndSecDAO;
import com.manpower.directtalentseo.hbn.dao.impl.TAdContactDAOImpl;
import com.manpower.directtalentseo.hbn.dao.impl.VActiveAdvertsDAOImpl;
import com.manpower.directtalentseo.hbn.dao.impl.VAdsIndSecDAOImpl;
import com.manpower.directtalentseo.hbn.shared.HibernateUtilities;




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
	
	public VActiveAdvertsDAO getVActiveAdvertsDAO(){
		return new VActiveAdvertsDAOImpl(getCurrentSession());
	}

	public VAdsIndSecDAO getVAdsIndSecDAO() {
		return new VAdsIndSecDAOImpl(getCurrentSession());
	}
	
	public TadContactDAO getTadContactDAO(){
		return new TAdContactDAOImpl(getCurrentSession());
	}
	

}
	
	
