package com.manpower.directtalent.rss.ui.service.impl;

import java.util.List;

import com.manpower.directalent.rss.hbn.dao.RptResultsDAO;
import com.manpower.directalent.rss.hbn.shared.dao.DAOFactory;
import com.manpower.directtalent.rss.ui.beans.RptResultsUIBean;
import com.manpower.directtalent.rss.ui.service.RptResultsService;

public class RptResultsServiceImpl extends BaseUIServiceImpl implements RptResultsService {
	
	private RptResultsDAO getDAO(){
		return DAOFactory.getDAOFactory().getRptResultsDAO();
	}
	
	public List findAll() {
		return createUIBeanList(getDAO().findAll(),RptResultsUIBean.class.getName());
	}
	
	public RptResultsUIBean findByID(Long id){
		return (RptResultsUIBean)transferObject(getDAO().findByID(id), RptResultsUIBean.class.getName());
	}

//	public Object findByID(Serializable id) {

}
