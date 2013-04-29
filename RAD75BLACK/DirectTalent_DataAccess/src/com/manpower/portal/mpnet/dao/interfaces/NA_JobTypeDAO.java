/*
 * Created on Nov 8, 2007
 *
 */
package com.manpower.portal.mpnet.dao.interfaces;

import java.util.List;

import com.manpower.portal.mpnet.hbn.beans.NA_JobType;


/**
 * @author auljane1
 *
  */
public interface NA_JobTypeDAO extends DAO {
	 
    public NA_JobType findByName(String jobTypeName);
    
    public List findJobClass(String jobTypeName,String jobClassName);
}
