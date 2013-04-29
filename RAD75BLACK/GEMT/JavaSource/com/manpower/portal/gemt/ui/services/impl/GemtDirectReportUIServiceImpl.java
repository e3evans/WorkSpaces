package com.manpower.portal.gemt.ui.services.impl;

import java.io.Serializable;
import java.util.List;

import com.manpower.portal.gemt.hbn.beans.GemtDirectReportHbnBean;
import com.manpower.portal.gemt.hbn.object.dao.GemtDirectReportDAO;
import com.manpower.portal.gemt.hbn.shared.dao.DAOFactory;
import com.manpower.portal.gemt.ui.beans.GemtDirectReportUIBean;
import com.manpower.portal.gemt.ui.services.GemtDirectReportService;

public class GemtDirectReportUIServiceImpl extends BaseUIServiceImpl implements GemtDirectReportService {

	
	public List findAll(){
		return createUIBeanList(getDAO().findAll(),GemtDirectReportUIBean.class.getName());
	}
	
	public List findAllByManagerId(String mgrEmail,String orderBy,String ascDsc){
		return createUIBeanList(getDAO().findAllByManagerId(mgrEmail, orderBy, ascDsc),GemtDirectReportUIBean.class.getName());
	}

	
	private GemtDirectReportDAO getDAO(){
		return DAOFactory.getDAOFactory().getGemtDirectReportDAO();
	}
	
	public void delete(Serializable id){
		Object obj = getDAO().findByID(id);
		beginTransaction();
		getDAO().delete(obj);
		commitTransaction();
	}
	
	public GemtDirectReportUIBean findByEmployeeEmail(String empEmail){
		
		GemtDirectReportHbnBean directReport=(GemtDirectReportHbnBean)DAOFactory.getDAOFactory().getGemtDirectReportDAO().findByEmployeeEmail(empEmail);
		
		if(directReport==null)
		{
			return null;
		}
		
		return (GemtDirectReportUIBean)transferObject(
				directReport,
				GemtDirectReportUIBean.class.getName());
		
	}
	
	public GemtDirectReportUIBean addUpdateDirectReportFromLdap(GemtDirectReportUIBean gdrui){
		/*
		 * Check to see if the user is already in the table.
		 */
		GemtDirectReportHbnBean gdrhb = (GemtDirectReportHbnBean) getDAO().findByEmployeeEmail(gdrui.getGemt_sum_empemail());
	
		if (gdrhb!=null){
			/*
			 * If the user already exists update the table with the new manager.
			 */
			gdrhb.setGemt_sum_mgremail(gdrui.getGemt_sum_mgremail());
			beginTransaction();
			getDAO().makePersistent(gdrhb);
			commitTransaction();
			System.out.println("HERE1!");
		}else{
			/*
			 * Create a new user for the table...
			 */
			
			gdrhb = (GemtDirectReportHbnBean)transferObject(gdrui,GemtDirectReportHbnBean.class.getName());
			
			beginTransaction();
			getDAO().makePersistent(gdrhb);
			commitTransaction();
			System.out.println("HERE2");
		}
		return (GemtDirectReportUIBean)transferObject(gdrhb,GemtDirectReportUIBean.class.getName());
	}
}
