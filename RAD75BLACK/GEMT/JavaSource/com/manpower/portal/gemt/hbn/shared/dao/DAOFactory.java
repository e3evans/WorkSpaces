/*
 * Created on Nov 14, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.gemt.hbn.shared.dao;

import com.manpower.portal.gemt.hbn.object.dao.GemtDirectReportDAO;
import com.manpower.portal.gemt.hbn.object.dao.GemtManagersDAO;
import com.manpower.portal.gemt.hbn.object.dao.GemtNotesDAO;
import com.manpower.portal.gemt.hbn.object.dao.GemtSummaryReportDAO;
import com.manpower.portal.gemt.hbn.object.dao.GemtSummaryReportFileDAO;
import com.manpower.portal.gemt.hbn.object.dao.GemtSummaryReportFileLiteDAO;



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
	
	public abstract GemtSummaryReportDAO getGemtSummaryReportDAO();
	
	public abstract GemtNotesDAO getGemtNotesDAO();
	
	public abstract GemtDirectReportDAO getGemtDirectReportDAO();
	public abstract GemtManagersDAO getGemtManagersDAO();

	public abstract GemtSummaryReportFileDAO getGemtSummaryReportFileDAO();
	
	public abstract GemtSummaryReportFileLiteDAO getGemtSummaryReportFileLiteDAO();	
}
