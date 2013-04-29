package com.manpower.portal.gemt.hbn.object.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.manpower.portal.gemt.hbn.beans.GemtSummaryReportFileLiteHbnBean;
import com.manpower.portal.gemt.hbn.object.dao.GemtSummaryReportFileLiteDAO;
import com.manpower.portal.gemt.hbn.shared.dao.GenericHibernateDAO;

public class GemtSummaryReportFileLiteDAOImpl extends GenericHibernateDAO implements
		GemtSummaryReportFileLiteDAO {
	
	public GemtSummaryReportFileLiteDAOImpl(Session session)
	{
		super(GemtSummaryReportFileLiteHbnBean.class, session);
	}
	
	public List findAll() {
		
		return getSession().createCriteria(GemtSummaryReportFileLiteHbnBean.class).list();
	}

	public Object findByID(Serializable id) {
		
		return getSession().get(GemtSummaryReportFileLiteHbnBean.class, id);
	}

	public List findByReportId(long reportId) {
		
		Criteria query=getSession().createCriteria(GemtSummaryReportFileLiteHbnBean.class).				
		createCriteria("gemt_sum_report").				
		add(Restrictions.eq("id", new Long(reportId)));
		
		return query.list();
	}
	
	public List findByBothReportIds(long reportId,long parentId) {
		
		Criteria query=getSession().createCriteria(GemtSummaryReportFileLiteHbnBean.class).				
		createCriteria("gemt_sum_report").				
		add(Restrictions.or(Restrictions.eq("id", new Long(reportId)), Restrictions.eq("id", new Long(parentId))));
		
		return query.list();
	}	
}
