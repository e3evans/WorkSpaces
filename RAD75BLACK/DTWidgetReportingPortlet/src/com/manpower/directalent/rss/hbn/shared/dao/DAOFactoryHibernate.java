/*
 * Created on Nov 14, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.directalent.rss.hbn.shared.dao;

import org.hibernate.Session;

import com.manpower.directalent.rss.hbn.dao.ReportDAO;
import com.manpower.directalent.rss.hbn.dao.RptResultsDAO;
import com.manpower.directalent.rss.hbn.dao.VAdvertisementDAO;
import com.manpower.directalent.rss.hbn.dao.impl.ReportDAOImpl;
import com.manpower.directalent.rss.hbn.dao.impl.RptResultsDAOImpl;
import com.manpower.directalent.rss.hbn.dao.impl.VAdvertisementDAOImpl;
import com.manpower.directalent.rss.hbn.shared.HibernateUtilities;


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
	
	public VAdvertisementDAO getVAdvertisementDAO(){
		return new VAdvertisementDAOImpl(getCurrentSession());
	}
	
	public RptResultsDAO getRptResultsDAO(){
		return new RptResultsDAOImpl(getCurrentSession());
	}

	public ReportDAO getReportDAO() {
		return new ReportDAOImpl(getCurrentSession());
	}
}
	
	
