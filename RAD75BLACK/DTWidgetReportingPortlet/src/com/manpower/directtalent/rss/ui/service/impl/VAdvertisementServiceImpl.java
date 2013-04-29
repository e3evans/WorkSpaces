package com.manpower.directtalent.rss.ui.service.impl;

import java.util.List;

import com.manpower.directalent.rss.hbn.dao.VAdvertisementDAO;
import com.manpower.directalent.rss.hbn.shared.dao.DAOFactoryHibernate;
import com.manpower.directtalent.rss.ui.beans.VAdvertismentUIBean;
import com.manpower.directtalent.rss.ui.service.VAdvertisementService;

public class VAdvertisementServiceImpl extends BaseUIServiceImpl implements VAdvertisementService {

	private VAdvertisementDAO getDAO(){
		return DAOFactoryHibernate.getDAOFactory().getVAdvertisementDAO();
	}
	
	public List finaAll(){
		return createUIBeanList(getDAO().findAll(), VAdvertismentUIBean.class.getName());
	}
	
	public List findBySiteId(Long siteId){
		
		return createUIBeanList(getDAO().findBySiteId(siteId), VAdvertismentUIBean.class.getName());
		
	}
	
}
