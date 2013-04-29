/*
 * Created on Nov 14, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.gemt.hbn.shared.dao;

import org.hibernate.Session;

import com.manpower.portal.gemt.hbn.object.dao.GemtDirectReportDAO;
import com.manpower.portal.gemt.hbn.object.dao.GemtManagersDAO;
import com.manpower.portal.gemt.hbn.object.dao.GemtNotesDAO;
import com.manpower.portal.gemt.hbn.object.dao.GemtSummaryReportDAO;
import com.manpower.portal.gemt.hbn.object.dao.GemtSummaryReportFileDAO;
import com.manpower.portal.gemt.hbn.object.dao.GemtSummaryReportFileLiteDAO;
import com.manpower.portal.gemt.hbn.object.dao.impl.GemtDirectReportDAOImpl;
import com.manpower.portal.gemt.hbn.object.dao.impl.GemtManagersDAOImpl;
import com.manpower.portal.gemt.hbn.object.dao.impl.GemtNotesDAOImpl;
import com.manpower.portal.gemt.hbn.object.dao.impl.GemtSummaryReportDAOImpl;
import com.manpower.portal.gemt.hbn.object.dao.impl.GemtSummaryReportFileDAOImpl;
import com.manpower.portal.gemt.hbn.object.dao.impl.GemtSummaryReportFileLiteDAOImpl;
import com.manpower.portal.gemt.hbn.shared.HibernateUtilities;

/**
 *
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DAOFactoryHibernate extends DAOFactory {

	public GemtSummaryReportFileLiteDAO getGemtSummaryReportFileLiteDAO() {
		
		return new GemtSummaryReportFileLiteDAOImpl(getCurrentSession());
	}

	protected Session getCurrentSession() {
        return HibernateUtilities.currentSession();
    }
	
	public GemtSummaryReportDAO getGemtSummaryReportDAO(){
		return new GemtSummaryReportDAOImpl(getCurrentSession());
	}

	public GemtNotesDAO getGemtNotesDAO(){
		return new GemtNotesDAOImpl(getCurrentSession());
	}
	
	public GemtDirectReportDAO getGemtDirectReportDAO(){
		return new GemtDirectReportDAOImpl(getCurrentSession());
	}
	
	public GemtManagersDAO getGemtManagersDAO(){
		return new GemtManagersDAOImpl(getCurrentSession());
	}
	
	public GemtSummaryReportFileDAO getGemtSummaryReportFileDAO()
	{
		return new GemtSummaryReportFileDAOImpl(getCurrentSession());
	}
}
	
	
