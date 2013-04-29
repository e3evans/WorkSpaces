/*
 * Created on Jan 16, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.directtalent.rss.ui.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.manpower.directalent.rss.hbn.shared.HibernateUtilities;



/**
 * @author Eric Evans
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BaseUIServiceImpl {
	
	protected List createUIBeanList(List hibernateList,String classname){

		List returnList = new ArrayList(hibernateList.size());
		Object dataBean;
		Object uiBean;
		try{
			Class uiBeanClass = Class.forName(classname);

			for (int i=0;i<hibernateList.size();i++){
				uiBean = uiBeanClass.newInstance();
				dataBean = (Object)hibernateList.get(i);
				BeanUtils.copyProperties(uiBean,dataBean);
				returnList.add(uiBean);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnList;
		
	}
	
	/**
	 * Transfers the properties of "inBean" to a bean of the class "className"
	 * @param inBean
	 * @param className
	 * @return
	 */
	protected Object transferObject(Object inBean,String className){
		Object outBean = new Object();
		try{
			Class outBeanClass = Class.forName(className);
			outBean = outBeanClass.newInstance();
			BeanUtils.copyProperties(outBean,inBean);
		}catch(Exception e){
			e.printStackTrace();
		}
		return outBean;
	}
	
	protected void beginTransaction(){
		HibernateUtilities.currentSession().close();
		HibernateUtilities.currentSession().beginTransaction();
	}
	protected void commitTransaction(){
		HibernateUtilities.currentSession().getTransaction().commit();
		
	}
	
}
