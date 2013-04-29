package com.manpower.portal.gemt.ui.services.impl;

import java.util.List;

import com.manpower.portal.gemt.hbn.beans.GemtManagersHbnBean;
import com.manpower.portal.gemt.hbn.object.dao.GemtManagersDAO;
import com.manpower.portal.gemt.hbn.shared.dao.DAOFactory;
import com.manpower.portal.gemt.ui.beans.GemtManagersUIBean;
import com.manpower.portal.gemt.ui.services.GemtManagersService;

public class GemtManagersUIServiceImpl extends BaseUIServiceImpl implements GemtManagersService{
	
	public List findAll(){
		return createUIBeanList(getDAO().findAll(),GemtManagersUIBean.class.getName());
	}
	
	private GemtManagersDAO getDAO(){
		return DAOFactory.getDAOFactory().getGemtManagersDAO();
	}

	public GemtManagersUIBean findByEmail(String eMail){
		Object hbnBean = getDAO().findByEmail(eMail);
		GemtManagersUIBean gemt_mgrUIBean=null;
		if (hbnBean!=null){
			gemt_mgrUIBean=(GemtManagersUIBean)transferObject(hbnBean, GemtManagersUIBean.class.getName());
		}
		return gemt_mgrUIBean; 
	}
	

	public GemtManagersUIBean AddManager(GemtManagersUIBean gemtUI){
		Object hbnBean = transferObject(gemtUI,GemtManagersHbnBean.class.getName());
		beginTransaction();
		hbnBean = getDAO().makePersistent(hbnBean);
		commitTransaction();
		return (GemtManagersUIBean) transferObject(hbnBean,GemtManagersUIBean.class.getName());
		
	}
}
