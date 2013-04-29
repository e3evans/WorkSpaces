/*
 * Created on Nov 14, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.directtalentsearch30.hbn.shared.dao;

import org.hibernate.Session;

import com.manpower.directtalentsearch30.hbn.dao.DirectTalentAdSearchDAO;
import com.manpower.directtalentsearch30.hbn.dao.impl.DirectTalentAdSearchDAOImpl;
import com.manpower.directtalentsearch30.hbn.shared.HibernateUtilities;





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

	public DirectTalentAdSearchDAO getDirectTalentAdSearchDAO() {
		return new DirectTalentAdSearchDAOImpl(getCurrentSession());
	}
	
	
	
//	public VActiveAdvertsDAO getVActiveAdvertsDAO(){
//		return new VActiveAdvertsDAOImpl(getCurrentSession());
//	}
//
//	public VAdsIndSecDAO getVAdsIndSecDAO() {
//		return new VAdsIndSecDAOImpl(getCurrentSession());
//	}
//	
//	public TadContactDAO getTadContactDAO(){
//		return new TAdContactDAOImpl(getCurrentSession());
//	}
	

}
	
	
