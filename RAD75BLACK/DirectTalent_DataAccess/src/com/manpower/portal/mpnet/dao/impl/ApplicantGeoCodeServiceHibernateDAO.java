package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.ApplicantGeoCodeDAO;
import com.manpower.portal.mpnet.hbn.beans.ApplicantSearchGeoCode;

public class ApplicantGeoCodeServiceHibernateDAO extends GenericHibernateDAO
		implements ApplicantGeoCodeDAO {

	public static final String QUERY_APPLICANTS = 	"SELECT CITY, STATE, COUNTRY, ADDRESSLINE1, POSTALCODE, APPLICANT_ID" +
													" FROM  (SELECT R.CITY, R.STATE, R.COUNTRY, R.ADDRESSLINEONE ADDRESSLINE1, R.POSTALCODE,S.APPL_ID APPLICANT_ID, ROWNUM RNUM" +
													" FROM CANDIDATESEARCH S " +
													" INNER JOIN APPLICANTRESPONSE R ON S.APPL_ID = R.ID" +
													" WHERE APPL_ID > 0 AND " +
													" R.CITY IS NOT NULL AND  " +
													" R.STATE IS NOT NULL " +
													" AND S.SITE_ID = :siteId) A where RNUM BETWEEN :fromIndex and :toIndex";
	
	public ApplicantGeoCodeServiceHibernateDAO(Session session) {
		super(Object.class, session);
	}
	
	public List findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object findByID(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ApplicantSearchGeoCode> getApplicantGeoLocations(long siteId, int fromIndex,int toIndex) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getCurrentSession();
		Criteria criteria = session.createCriteria(ApplicantSearchGeoCode.class);
		criteria.add(Restrictions.eq("siteId", Long.valueOf(siteId)));
		criteria.addOrder(Order.asc("id"));
		criteria.setFirstResult(fromIndex +1);
		criteria.setMaxResults(toIndex-fromIndex + 1);
		List <ApplicantSearchGeoCode> results = criteria.list();
		return results;
	}
	
	
	public List<ApplicantSearchGeoCode> getApplicantGeoLocationsByIds(List<Long> idList) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getCurrentSession();
		Criteria criteria = session.createCriteria(ApplicantSearchGeoCode.class);
		criteria.add(Restrictions.in("id", idList));
		criteria.addOrder(Order.asc("id"));
		List <ApplicantSearchGeoCode> results = criteria.list();
		return results;
	}

}
