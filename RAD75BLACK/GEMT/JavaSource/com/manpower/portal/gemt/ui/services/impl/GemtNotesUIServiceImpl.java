/*
 * Created on Jan 24, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.gemt.ui.services.impl;

import java.io.Serializable;
import java.util.List;

import com.manpower.portal.gemt.hbn.beans.GemtNotesHbnBean;
import com.manpower.portal.gemt.hbn.object.dao.GemtNotesDAO;
import com.manpower.portal.gemt.hbn.shared.dao.DAOFactory;
import com.manpower.portal.gemt.ui.beans.GemtNotesUIBean;
import com.manpower.portal.gemt.ui.services.GemtNotesService;

/**
 * @author rrajaram
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GemtNotesUIServiceImpl extends BaseUIServiceImpl implements GemtNotesService{
	
	
	public GemtNotesUIBean findById(Serializable id){
		return (GemtNotesUIBean) transferObject(getDAO().findByID(id),GemtNotesUIBean.class.getName());
	}
	
	public List findAll(){
//		List myList = (List)getDAO().findAll();
		return createUIBeanList(getDAO().findAll(),GemtNotesUIBean.class.getName());
	}
	
	private GemtNotesDAO getDAO(){
		return DAOFactory.getDAOFactory().getGemtNotesDAO();
	}
	
	public GemtNotesUIBean makePersistent(GemtNotesUIBean gsrb){
		GemtNotesHbnBean gsrhb = new GemtNotesHbnBean();
		gsrhb = (GemtNotesHbnBean) transferObject(gsrb,GemtNotesHbnBean.class.getName());
		beginTransaction();
		gsrhb=(GemtNotesHbnBean) getDAO().makePersistent(gsrhb);
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

}
