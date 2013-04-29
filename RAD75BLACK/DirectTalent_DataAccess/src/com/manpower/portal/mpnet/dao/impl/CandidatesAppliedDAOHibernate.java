/*
 * Created on Jul 2, 2007
 *
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidatesAppliedDAO;
import com.manpower.portal.mpnet.hbn.beans.CandidatesApplied;
import com.manpower.portal.mpnet.hbn.beans.JobApplicant;
import com.manpower.portal.mpnet.hbn.util.HibernatePage;
import com.manpower.portal.mpnet.hbn.util.Page;

/**
 * @author Dimitar Bakardzhiev
 *  
 */
public class CandidatesAppliedDAOHibernate extends GenericHibernateDAO
		implements CandidatesAppliedDAO {
	/**
	 * Constructor
	 * 
	 * @param persistentClass
	 * @param session
	 */
	public CandidatesAppliedDAOHibernate(Session session) {
		super(CandidatesApplied.class, session);
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findByID(java.io.Serializable)
	 */
	public Object findByID(Serializable id) {
		Object candidatesApplied = HibernateUtil.getSessionFactory()
				.getCurrentSession().get(CandidatesApplied.class, id);

		return candidatesApplied;
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findAll()
	 */
	public List findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.CandidatesAppliedDAO#findAllByAdvertisementId(java.lang.Long)
	 */
	public List findAllByAdvertisementId(Long advertisementId) {
		Criteria query = HibernateUtil.getCurrentSession()
				.createCriteria(CandidatesApplied.class);

		query.add(Restrictions.eq("advertisementId", advertisementId));

		return query.list();
	}
	
	/**
	 * Searches all applicants for a particular advertisement id 
	 * Finds both external and dt applicants using a view
	 * @param advertId
	 * @param fromIndex
	 * @param toIndex
	 * @param sortOrder
	 * @return
	 */
	public Page findAllApplicantsByAdvertId(long advertId, int pageNumber, int pageSize, int sortOrder, boolean desc, String filter) {
		
		StringBuffer sqlQuery = new StringBuffer();

		sqlQuery.append("select * from(select * from(select row_.*, rownum rownum1 from" +
				"(select this_.* from V_JOB_APPLICANTS this_ where this_.ADVERT_ID=");
		sqlQuery.append(advertId);
		if (filter != null && filter.trim().length() != 0) {
			sqlQuery.append(" and this_.RATING='");
			sqlQuery.append(filter);
			sqlQuery.append("'");
		}
		sqlQuery.append(" order by ");
		
		String order = desc ? " desc " : " asc ";
		
		switch(sortOrder) {
        case 1:
        	sqlQuery.append("upper(this_.FIRSTNAME)");
        	sqlQuery.append(order);
        	sqlQuery.append(", upper(this_.LASTNAME)");
            break;        
        case 2:
        	sqlQuery.append("this_.LASTLOGIN");
            break;
        case 3:
        	sqlQuery.append("this_.DATEAPPLIED");
            break;
        case 4:
        	sqlQuery.append("this_.RATING");
        	break;
        case 5:
        	sqlQuery.append("this_.STATUS");
        	break;
        case 6:
        	sqlQuery.append("upper(this_.BRANCHNAME)");
        	break;
        case 7:
        	sqlQuery.append("this_.BG_SCORE");
        	break;
        default:
        	sqlQuery.append("this_.LASTLOGIN");
            break;
        }
		sqlQuery.append(order);
		int fromIndex = ((pageNumber + 1) * pageSize) - pageSize;
		int toIndex = fromIndex + pageSize;
		sqlQuery.append(") row_) where rownum1 <= ");
		sqlQuery.append(toIndex);
		sqlQuery.append(") where rownum1 > ");
		sqlQuery.append(fromIndex);
		
		
		Session session = HibernateUtil.getCurrentSession();
		SQLQuery query = session.createSQLQuery(sqlQuery.toString()).addEntity(
				JobApplicant.class);

		List results = query.list();
		 
		HibernatePage page = new HibernatePage(pageNumber, pageSize);
		if(results != null && !results.isEmpty()){
			page.setElements(results);
//			JobApplicant jobApp = null;
//			JobApplicantBean jobAppBean = null;
//			int foundResults = results.size();
//			List resutlBeans = new ArrayList(foundResults);
//			try{
//				for(int i=0; i< foundResults; i++){
//					jobApp = (JobApplicant)results.get(i);
//					jobAppBean = JobApplicantBuilder.createJobApplicantBean(jobApp);
//					resutlBeans.add(jobAppBean);
//				}
//				page.setElements(resutlBeans);
//			}catch (ServiceException e) {
//				throw new ServiceException(e.getMessage());
//			}
		}
		return page;
	}
	
	private String getSQLQuery(long advertId, boolean countQuery){
		
		StringBuffer sqlQuery = new StringBuffer();
		if(countQuery)
			sqlQuery.append("SELECT COUNT(*) FROM(");
		else
			sqlQuery.append("SELECT * FROM(");
		
		sqlQuery.append("SELECT V.*");
		sqlQuery.append(" FROM V_JOB_APPLICANTS V");
		sqlQuery.append(" INNER JOIN CANDIDATESEARCH S ON S.APPL_ID = V.ID AND V.applicant_type = 2");
		sqlQuery.append(" WHERE V.ADVERT_ID=");
		sqlQuery.append(advertId);
		sqlQuery.append(" UNION");
		sqlQuery.append(" SELECT V.*");
		sqlQuery.append(" FROM V_JOB_APPLICANTS V");
		sqlQuery.append(" INNER JOIN CANDIDATESEARCH S ON S.CAND_ID = V.ID AND V.applicant_type = 1");
		sqlQuery.append(" WHERE V.ADVERT_ID=");
		sqlQuery.append(advertId);
		sqlQuery.append(") V");
		
		return sqlQuery.toString();
	}
	
	public Page findAllAppliedCandidatesByAdvertId(long advertId, int pageNumber, int pageSize, int sortOrder, boolean desc) {
		
		StringBuffer sqlQuery = new StringBuffer();

		sqlQuery.append("select * from(select * from(select row_.*, rownum rownum1 from (");
		String sql = getSQLQuery(advertId, false);
		sqlQuery.append(sql);
		sqlQuery.append(" ORDER by ");
		
		String order = desc ? " desc " : " asc ";
		
		switch(sortOrder) {
        case 1:
        	sqlQuery.append("upper(V.FIRSTNAME)");
        	sqlQuery.append(order);
        	sqlQuery.append(", upper(V.LASTNAME)");
            break;        
        case 2:
        	sqlQuery.append("V.LASTLOGIN");
            break;
        case 3:
        	sqlQuery.append("V.DATEAPPLIED");
            break;
        case 4:
        	sqlQuery.append("V.RATING");
        	break;
        case 5:
        	sqlQuery.append("upper(V.STATUS)");
        	break;
        case 6:
        	sqlQuery.append("upper(V.BRANCHNAME)");
        	break;
        case 7:
        	sqlQuery.append("V.BG_SCORE");
        	break;
        default:
        	sqlQuery.append("V.LASTLOGIN");
            break;
        }
		sqlQuery.append(order);
		int fromIndex = ((pageNumber + 1) * pageSize) - pageSize;
		int toIndex = fromIndex + pageSize;
		sqlQuery.append(") row_) where rownum1 <= ");
		sqlQuery.append(toIndex);
		sqlQuery.append(") where rownum1 > ");
		sqlQuery.append(fromIndex);
		
		
		Session session = HibernateUtil.getCurrentSession();
		SQLQuery query = session.createSQLQuery(sqlQuery.toString()).addEntity(
				JobApplicant.class);

		List results = query.list();
		 
		HibernatePage page = new HibernatePage(pageNumber, pageSize);
		if(results != null && !results.isEmpty()){
			page.setElements(results);
		}
		return page;
	}
	
	public int getCountAppliedCandidatesByAdvertId(long advertId){
		String sql =getSQLQuery(advertId, true);
		Session session = HibernateUtil.getCurrentSession();
		BigDecimal number = (BigDecimal)session.createSQLQuery(sql).uniqueResult();
		return number.intValue();
	}
	
	public int getCountAllApplicantsByAdvertId(long advertId, String filter) {
		Session session = HibernateUtil.getCurrentSession();
		Criteria query = session.createCriteria(JobApplicant.class).setProjection(Projections.rowCount());
		query.add(Restrictions.eq("advertId", new Long(advertId)));
		if (filter != null && filter.trim().length() != 0) {
			query.add(Restrictions.eq("rating", filter));
		}
		
		return ((Integer)query.uniqueResult()).intValue();
	}
}
