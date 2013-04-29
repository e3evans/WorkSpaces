/*
 * Created on Jan 24, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.gemt.ui.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import com.manpower.portal.gemt.hbn.beans.BaseHibernateBean;
import com.manpower.portal.gemt.hbn.beans.GemtSummaryReportHbnBean;
import com.manpower.portal.gemt.hbn.object.dao.GemtSummaryReportDAO;
import com.manpower.portal.gemt.hbn.object.dao.impl.GemtSummaryReportDAOImpl;
import com.manpower.portal.gemt.hbn.shared.HibernateResourseLocator;
import com.manpower.portal.gemt.hbn.shared.dao.DAOFactory;
import com.manpower.portal.gemt.ui.beans.GemtSummaryReportUIBean;
import com.manpower.portal.gemt.ui.services.GemtSummaryReportService;
import com.manpower.portal.gemt.ui.services.builder.GemtSmmaryReportBuilder;

/**
 * @author Eric Evans
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GemtSummaryReportUIServiceImpl extends BaseUIServiceImpl implements GemtSummaryReportService{
	
	
	public GemtSummaryReportUIBean findById(Serializable id){
		return (GemtSummaryReportUIBean) transferObject(getDAO().findByID(id),GemtSummaryReportUIBean.class.getName());
	}
	
	public List getAllbyUserEmail(String email, String orderBy, String ascDsc){
		return createUIBeanList(getDAO().getAllbyUserEmail(email, orderBy, ascDsc), GemtSummaryReportUIBean.class.getName());
	}
	public List getAllbyPeriod(String email,Serializable period, String mode){
		return createUIBeanList(getDAO().findAllByPeriod(email,period, mode),GemtSummaryReportUIBean.class.getName());
	}
	public List getAllBeforeAfterMonth(String email,String month,String greaterThan,String orderBy,String ascDsc){
		//ADD THE PROPERTIES...
		Properties props=new Properties();
		props.put("gemt_email", email);
		props.put("mnth", month);
		//SET THE SORTING AND GREATER THAN LESS THAN...
		StringBuffer customQuery = new StringBuffer();
		customQuery.append(HibernateResourseLocator.getInstance().getValue(GemtSummaryReportDAOImpl.CUSTOM_MIDANDANNUAL_QUERY));
		customQuery.append(greaterThan+":mnth");
		customQuery.append(" AND gsr.gemt_sum_empemail=:gemt_email");
		customQuery.append(" ORDER BY gsr."+orderBy+" "+ascDsc);


		return createUIBeanList(getDAO().findByCustomQuery(customQuery.toString(), props), GemtSummaryReportUIBean.class.getName());
	}
	public List getAllByIdArray(Object[]ids,String orderBy,String ascDsc){
		return createUIBeanList(getDAO().getAllByIdArray(ids, "gemt_sum_repdate", BaseHibernateBean.ORDER_ASC),GemtSummaryReportUIBean.class.getName());
	}
	
	public List findAll(){
		return createUIBeanList(getDAO().findAll(),GemtSummaryReportUIBean.class.getName());
	}
	
	public List findAllOrderBy(String orderBy, String ascDsc){
		return createUIBeanList(getDAO().findAllOrderBy(orderBy, ascDsc),GemtSummaryReportUIBean.class.getName());
	}
	
	private GemtSummaryReportDAO getDAO(){
		return DAOFactory.getDAOFactory().getGemtSummaryReportDAO();
	}
	
	public GemtSummaryReportUIBean makePersistent(GemtSummaryReportUIBean gsrb){
		GemtSummaryReportHbnBean gsrhb = new GemtSummaryReportHbnBean();
		gsrhb = (GemtSummaryReportHbnBean) transferObject(gsrb,GemtSummaryReportHbnBean.class.getName());
		beginTransaction();
		gsrhb=(GemtSummaryReportHbnBean) getDAO().makePersistent(gsrhb);
		commitTransaction();
		
		if (gsrhb!=null)gsrb.setId(gsrhb.getId());
		return gsrb;
	}
	
	public void delete(Serializable id){
		Object obj = getDAO().findByID(id);
		beginTransaction();
		getDAO().delete(obj);
		commitTransaction();
	}

	public void update(GemtSummaryReportUIBean gsrb) {
		GemtSummaryReportHbnBean gsrhb = (GemtSummaryReportHbnBean) getDAO().findByID(new Long(gsrb.getId()));
		
		GemtSmmaryReportBuilder.populateGemtSummaryReportHbnBean(gsrb, gsrhb);
		
		getDAO().update(gsrhb);
		commitTransaction();		
	}

	public GemtSummaryReportUIBean findParentReport(Serializable id) {
		
		GemtSummaryReportUIBean report=(GemtSummaryReportUIBean) transferObject(getDAO().findByID(id),GemtSummaryReportUIBean.class.getName());
		
		if(report.getGemt_parent_id()==0)
		{
			return null;
		}
		
		return (GemtSummaryReportUIBean) transferObject(getDAO().findByID(new Long(report.getGemt_parent_id())),GemtSummaryReportUIBean.class.getName());
	}
	
	public List findAllFinalized(String email, String mode)
	{
		return createUIBeanList(getDAO().findAllFinalized(email,mode),GemtSummaryReportUIBean.class.getName());
	}

	public List getAllActiveReportsByPeriod(String email, Serializable period, String mode) 
	{
		return createUIBeanList(getDAO().findAllActiveReportsByPeriod(email,period,mode),GemtSummaryReportUIBean.class.getName());
	}
}
