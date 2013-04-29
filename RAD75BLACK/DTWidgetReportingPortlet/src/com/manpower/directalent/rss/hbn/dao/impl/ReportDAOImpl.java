package com.manpower.directalent.rss.hbn.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.manpower.directalent.rss.hbn.beans.Report;
import com.manpower.directalent.rss.hbn.beans.ReportContent;
import com.manpower.directalent.rss.hbn.dao.ReportDAO;
import com.manpower.directalent.rss.hbn.shared.HibernateUtilities;
import com.manpower.directalent.rss.hbn.shared.dao.GenericHibernateDAO;

public class ReportDAOImpl extends GenericHibernateDAO implements ReportDAO {

	Logger logger = Logger.getLogger(ReportDAOImpl.class);

	public ReportDAOImpl(Session session) {
		super(Report.class, session);
	}

	/**
	 * Returns all reports.
	 * Report content is not fetched!
	 */
	public List findAllReports() {
		Session session = HibernateUtilities.currentSession();
		Query query = session.createQuery("from Report order by siteName desc");
		List results = query.list();
		HibernateUtilities.closeSession();
		return results;
	}

	/**
	 * Return a report content by id
	 */
	public ReportContent findReportContentByID(Serializable id) {
		Session session = HibernateUtilities.currentSession();
		ReportContent res = (ReportContent)session.get(ReportContent.class, id);
		getSession().evict(res);
		HibernateUtilities.closeSession();
		return res;
	}

	public List findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object findByID(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}
}
