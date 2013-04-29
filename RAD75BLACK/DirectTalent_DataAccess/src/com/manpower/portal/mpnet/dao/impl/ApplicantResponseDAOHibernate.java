/*
 * Created on Sep 21, 2007
 *
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.ApplicantResponseDAO;
import com.manpower.portal.mpnet.hbn.beans.ApplicantResponse;
import com.manpower.portal.mpnet.util.TextUtils;

/**
 * @author ssprout1
 *  
 */
public class ApplicantResponseDAOHibernate extends GenericHibernateDAO
		implements ApplicantResponseDAO {

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.ApplicantResponseDAO#makePersistent(java.lang.Object,
	 *      double, double)
	 */
	public Object makePersistent(Object obj, double longitude, double latitude) {
		return null;
	}

	/**
	 * Constructor
	 * 
	 * @param persistentClass
	 * @param session
	 */
	public ApplicantResponseDAOHibernate(Session session) {
		super(ApplicantResponse.class, session);
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findAll()
	 */
	public List findAll() {

		return null;
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findByID(java.io.Serializable)
	 */
	public Object findByID(Serializable id) {

		Object applicantResponse = HibernateUtil.getSessionFactory()
				.getCurrentSession().get(ApplicantResponse.class, id);
		HibernateUtil.getCurrentSession().evict(
				applicantResponse);
		return applicantResponse;

	}

	/**
	 * Find Resumes by a list of Advert IDs
	 */
	public List findByAdvertID(long advertID) {
		return HibernateUtil.getCurrentSession()
				.createCriteria(ApplicantResponse.class).add(
						Expression.eq("advertId", new Long(advertID))).list();
	}

	/**
	 * Find Resumes by a list of Applicant Response IDs
	 */
	public List findByIDs(List IDs) {
		return HibernateUtil.getCurrentSession()
				.createCriteria(ApplicantResponse.class).add(
						Expression.in("id", IDs)).list();
	}

	/**
	 * Find Resumes by a list of Applicant Response IDs
	 */
	public List findByResumeKeywordSearch(String p_keyword, long p_siteId, int fromIndex, int toIndex, int sortOrder, boolean showAllResults) {
		
		
		if ("".equals(p_keyword)) {
			return new ArrayList(0);
		}

		StringBuffer sqlQuery = new StringBuffer(
				"select * from(select a.*, rownum rnum from (select * from APPLICANTRESPONSE this_ where");
		sqlQuery.append(" this_.SITE_ID=" + String.valueOf(p_siteId));
		
		if(!showAllResults)
		{
			sqlQuery.append(" and trunc(created_on) BETWEEN trunc(SYSDATE-30) AND trunc(SYSDATE) ");
		}
		
		sqlQuery.append(" and contains(this_.resume,");
		sqlQuery.append("'" + p_keyword + "'");
		sqlQuery.append(")>0");

        switch(sortOrder)
        {
        case 1: // '\001'
            sqlQuery.append(" order by trunc(this_.CREATED_ON) DESC");
            break;        
        case 4: // '\004'
            sqlQuery.append(" order by this_.FIRSTNAME ASC");
            break;

        case 3: // '\003'
            sqlQuery.append(" order by this_.FIRSTNAME DESC");
            break;

        default:
            sqlQuery.append(" order by trunc(this_.CREATED_ON) DESC");
            break;
        }
        sqlQuery.append(" ) a where rownum<=" + toIndex);
        sqlQuery.append(") where rnum>=" + fromIndex);                

		Session session = HibernateUtil.getCurrentSession();
		SQLQuery query = session.createSQLQuery(sqlQuery.toString()).addEntity(
				ApplicantResponse.class);

		List results=query.list();
		 
		return results;
	}

	public int countByResumeKeywordSearch(String p_keyword, long p_siteId, boolean showAllResults) {
		if ("".equals(p_keyword)) {
			return 0;
		}
		
		
        int number = 0;
		
		StringBuffer sqlQuery = new StringBuffer(
					"select count(*) from APPLICANTRESPONSE this_ where");
		sqlQuery.append(" this_.SITE_ID=" + String.valueOf(p_siteId));
		if(!showAllResults)
		{
				sqlQuery.append(" and trunc(created_on) BETWEEN trunc(SYSDATE-30) AND trunc(SYSDATE) ");
		}			
		sqlQuery.append(" and contains(this_.resume,");
		sqlQuery.append("'" + p_keyword + "'");
		sqlQuery.append(")>0");

		Session session = HibernateUtil.getCurrentSession();
		SQLQuery query = session.createSQLQuery(sqlQuery.toString());

		Object pObject = query.uniqueResult();
        if(pObject != null && (pObject instanceof Number)) {
            number = ((Number)pObject).intValue();
        }
		
        return number;	
    }

	/**
	 * Find all Resumes not stored in Lens
	 */
	public List readApplicantResponseResumesWhichDontHaveLensID(long siteId,
			int maxResults) {
		Criteria query = HibernateUtil.getCurrentSession()
				.createCriteria(ApplicantResponse.class);

		query.add(Expression.isNull("lensID")).add(
				Expression.eq("siteId", new Long(siteId))).setMaxResults(
				maxResults);

		return query.list();
	}
	
	public List getApplicantWithoutBGScore(long advertID){
		String SQL = " SELECT *" +
					 " FROM APPLICANTRESPONSE A" +
					 " WHERE A.ADVERT_ID = " + advertID + " AND" +
					 " CASE WHEN A.BG_SCORE IS NULL THEN 0 ELSE TO_NUMBER(A.BG_SCORE) END <= 0";
		SQLQuery q = HibernateUtil.getCurrentSession().createSQLQuery(SQL);
		q.addEntity(ApplicantResponse.class);
		List result = q.list();
		return result;
	}

	public List<ApplicantResponse> findApplicantResponseByMobileApplicant(
			String firstName, String lastName, String email, String phoneNumber, String phoneID) {
		
		Criteria query = HibernateUtil.getCurrentSession()
		.createCriteria(ApplicantResponse.class);
		query.add(
				Restrictions.eq("firstName",firstName)).add(
						Restrictions.eq("lastName",lastName)).add(
								Restrictions.eq("email",email)).add(
										Restrictions.eq("phoneOne",phoneNumber)).add(
												Restrictions.eq("iphoneUID",phoneID));
		
		return query.list();
	}

	public List<ApplicantResponse> findExistingJobAplicationByMobileApplicant(long advertId,
			String firstName, String lastName, String email,
			String phoneNumber, String phoneID) {
		Criteria query = HibernateUtil.getCurrentSession()
		.createCriteria(ApplicantResponse.class);
		query.add(
				Restrictions.eq("firstName",firstName)).add(
						Restrictions.eq("lastName",lastName)).add(
								Restrictions.eq("email",email)).add(
										Restrictions.eq("phoneOne",phoneNumber)).add(
												Restrictions.eq("iphoneUID",phoneID)).add(
														Restrictions.eq("advertId",Long.valueOf(advertId)));
		
		return query.list();
	}
	
	public List<ApplicantResponse> findExistingJobAplicationByMobileApplicant(long advertId,
			String firstName, String lastName, String email,
			String phoneNumber) {
		Criteria query = HibernateUtil.getCurrentSession()
		.createCriteria(ApplicantResponse.class);
		query.add(
				Restrictions.eq("firstName",firstName)).add(
						Restrictions.eq("lastName",lastName)).add(
								Restrictions.eq("email",email)).add(
										Restrictions.eq("phoneOne",phoneNumber)).add(
												Restrictions.eq("advertId",Long.valueOf(advertId)));
		
		return query.list();
	}
}
