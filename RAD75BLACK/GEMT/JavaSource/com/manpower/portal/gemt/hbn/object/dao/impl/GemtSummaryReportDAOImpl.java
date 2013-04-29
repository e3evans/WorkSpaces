/*
 * Created on Jan 19, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.gemt.hbn.object.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.manpower.portal.gemt.hbn.beans.GemtSummaryReportHbnBean;
import com.manpower.portal.gemt.hbn.object.dao.GemtSummaryReportDAO;
import com.manpower.portal.gemt.hbn.shared.dao.GenericHibernateDAO;
import com.manpower.portal.portlet.gemt.GEMTEntryForm;

/**
 * @author Eric Evans
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GemtSummaryReportDAOImpl extends GenericHibernateDAO implements GemtSummaryReportDAO{

	private static Logger log=Logger.getLogger(GemtSummaryReportDAOImpl.class);
	
	public static String CUSTOM_MIDANDANNUAL_QUERY ="sql.gemtentryform.midandannual.report";
	public static String STATUS_FINAL="FINALIZED";
	public static String STATUS_FORREVIEW="FOR_REVIEW";
	/**
	 * @param persistentClass
	 * @param session
	 */
	public GemtSummaryReportDAOImpl(Session session) {
		
		super(GemtSummaryReportHbnBean.class, session);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.gemt.hbn.shared.dao.DAO#findByID(java.io.Serializable)
	 */
	public Object findByID(Serializable id) {
		// TODO Auto-generated method stub
		return getSession().get(GemtSummaryReportHbnBean.class,id);
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.gemt.hbn.shared.dao.DAO#findAll()
	 */
	public List findAll() {
		// TODO Auto-generated method stub
		return getSession().createCriteria(GemtSummaryReportHbnBean.class).list();
	}

	public List findAllOrderBy(String orderBy, String ascDsc){
		Criteria criteria = getSession().createCriteria(GemtSummaryReportHbnBean.class);
		criteria = addOrder(criteria, orderBy, ascDsc);
		return criteria.list();
	}
	
	/* (non-Javadoc)
	 * @see com.manpower.portal.gemt.hbn.shared.dao.DAO#makePersistent(java.lang.Object)
	 */
	public Object makePersistent(Object obj) {
		// TODO Auto-generated method stub
		return super.makePersistent(obj);
	}
	
	/* (non-Javadoc)
	 * @see com.manpower.portal.gemt.hbn.shared.dao.DAO#delete(java.lang.Object)
	 */
	public Object delete(Object obj) {
		// TODO Auto-generated method stub
		return super.delete(obj);
	}
	
	public List getAllbyUserEmail(String email, String orderBy, String ascDsc){
		Criteria criteria = getSession().createCriteria(GemtSummaryReportHbnBean.class);
		criteria.add(Restrictions.eq("gemt_sum_empemail",email));
		criteria=addOrder(criteria,orderBy,ascDsc);
		return criteria.list();
	}
	
	public List getAllByIdArray(Object[]ids,String orderBy,String ascDsc){
		Criteria criteria = getSession().createCriteria(GemtSummaryReportHbnBean.class);
		criteria.add(Restrictions.in("id", ids));
		criteria=addOrder(criteria,orderBy,ascDsc);
		return criteria.list();
	}

	public List findAllByPeriod(String email,Serializable period, String mode){
		
		Criteria criteria = getSession().createCriteria(GemtSummaryReportHbnBean.class);		
		criteria.add(Restrictions.eq("gemt_sum_period", period));
		
		log.debug("Mode is:"+mode);
		
		if(mode.equals(GEMTEntryForm.MODE_MANAGER))
		{
			criteria.add(Restrictions.eq("gemt_sum_mgremail",email));
			criteria.add(Restrictions.or(Restrictions.eq("gemt_sum_locked", STATUS_FINAL), Restrictions.eq("gemt_sum_locked", STATUS_FORREVIEW)));	
		}
		else if(mode.equals(GEMTEntryForm.MODE_EMPLOYEE))
		{
			criteria.add(Restrictions.eq("gemt_sum_empemail",email));
//			criteria.add(Restrictions.or(Restrictions.eq("gemt_sum_locked", STATUS_FINAL), Restrictions.isNull("gemt_sum_locked")));	
		}
		else
		{
			return new ArrayList();
		}
		
		return criteria.list();
	}
	
	public List findByCustomQuery(String query, Properties params) {
		// TODO Auto-generated method stub
		return super.findByCustomQuery(query, params);
	}

	public Object update(Object obj) {
		return makeUpdate(obj);
	}

	public List findParentByID(long id) {
				
		Criteria criteria = getSession().createCriteria(GemtSummaryReportHbnBean.class);
		criteria.add(Restrictions.eq("gemt_parent_id", new Long(id)));
		
		return criteria.list();
	}
	
	public List findAllFinalized(String email, String mode){
		
		Criteria criteria = getSession().createCriteria(GemtSummaryReportHbnBean.class);		
		criteria.add(Restrictions.eq("gemt_sum_locked", STATUS_FINAL));
		
		log.debug("Mode is:"+mode);
		
		if(mode.equals(GEMTEntryForm.MODE_MANAGER))
		{
			criteria.add(Restrictions.eq("gemt_sum_mgremail",email));
		}
		else if(mode.equals(GEMTEntryForm.MODE_EMPLOYEE))
		{
			criteria.add(Restrictions.eq("gemt_sum_empemail",email));
		}
		else
		{
			return new ArrayList();
		}
		
		return criteria.list();
	}

	public List findAllActiveReportsByPeriod(String email, Serializable period, String mode) 
	{
		Criteria criteria = getSession().createCriteria(GemtSummaryReportHbnBean.class);		
		criteria.add(Restrictions.eq("gemt_sum_period", period))
				.add(Restrictions.eq("gemt_sum_status", new Long(1)));
		
		log.debug("Mode is:"+mode);
		
		
		if(mode.equals(GEMTEntryForm.MODE_MANAGER))
		{
			criteria.add(Restrictions.eq("gemt_sum_mgremail",email));
			criteria.add(Restrictions.or(Restrictions.eq("gemt_sum_locked", STATUS_FINAL), Restrictions.eq("gemt_sum_locked", STATUS_FORREVIEW)));	
		}
		else if(mode.equals(GEMTEntryForm.MODE_EMPLOYEE))
		{
			criteria.add(Restrictions.eq("gemt_sum_empemail",email));
//			criteria.add(Restrictions.or(Restrictions.eq("gemt_sum_locked", STATUS_FINAL), Restrictions.isNull("gemt_sum_locked")));	
		}
		else
		{
			return new ArrayList();
		}
		
		return criteria.list();
	}	
}
