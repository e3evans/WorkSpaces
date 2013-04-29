/*
 * Created on Nov 14, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.directtalentsearch30.hbn.shared.dao;

import com.manpower.directtalentsearch30.hbn.dao.DirectTalentAdSearchDAO;









//import com.eee.hibernate.objects.client.CountryWebsitesDAO;
//import com.eee.hibernate.objects.project.ProjectDAO;



/**
 * @author 
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class DAOFactory {
	

	
	public static DAOFactory getDAOFactory() {
		return new DAOFactoryHibernate();
	}
	
	
	public abstract DirectTalentAdSearchDAO getDirectTalentAdSearchDAO();
	
//	public abstract VActiveAdvertsDAO getVActiveAdvertsDAO();
//	public abstract VAdsIndSecDAO getVAdsIndSecDAO();
//	public abstract TadContactDAO getTadContactDAO();
	
	

}
