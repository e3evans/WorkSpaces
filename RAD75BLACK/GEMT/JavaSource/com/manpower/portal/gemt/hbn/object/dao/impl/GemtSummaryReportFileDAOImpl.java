package com.manpower.portal.gemt.hbn.object.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.manpower.portal.gemt.hbn.beans.GemtSummaryReportFileHbnBean;
import com.manpower.portal.gemt.hbn.object.dao.GemtSummaryReportFileDAO;
import com.manpower.portal.gemt.hbn.shared.dao.GenericHibernateDAO;

public class GemtSummaryReportFileDAOImpl extends GenericHibernateDAO implements
		GemtSummaryReportFileDAO {
	
	public GemtSummaryReportFileDAOImpl(Session session)
	{
		super(GemtSummaryReportFileHbnBean.class, session);
	}
	
	public List findAll() {
		
		return getSession().createCriteria(GemtSummaryReportFileHbnBean.class).list();
	}

	public Object findByID(Serializable id) {
		
		return getSession().get(GemtSummaryReportFileHbnBean.class, id);
	}

	public List findByReportId(long reportId) {
		
		return getSession().createCriteria(GemtSummaryReportFileHbnBean.class).				
				createCriteria("gemt_sum_report").				
				add(Restrictions.eq("id", new Long(reportId))).list();
	}
}
