/*
 * Created on Nov 14, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.directtalentseo.hbn.shared.dao;

import com.manpower.directtalentseo.hbn.dao.TadContactDAO;
import com.manpower.directtalentseo.hbn.dao.VActiveAdvertsDAO;
import com.manpower.directtalentseo.hbn.dao.VAdsIndSecDAO;







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
	
	public abstract VActiveAdvertsDAO getVActiveAdvertsDAO();
	public abstract VAdsIndSecDAO getVAdsIndSecDAO();
	public abstract TadContactDAO getTadContactDAO();
	
	

}
