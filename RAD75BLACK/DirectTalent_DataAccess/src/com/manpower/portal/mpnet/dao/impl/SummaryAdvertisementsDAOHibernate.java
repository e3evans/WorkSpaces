/*
 * Created on Jun 28, 2007
 *
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.SummaryAdvertisementsDAO;
import com.manpower.portal.mpnet.hbn.beans.SummaryAdvertisements;
import com.manpower.portal.mpnet.util.Constants;

/**
 * @author Dimitar Bakardzhiev
 *  
 */
public class SummaryAdvertisementsDAOHibernate extends GenericHibernateDAO
		implements SummaryAdvertisementsDAO {
	public SummaryAdvertisementsDAOHibernate(Session session) {
		super(SummaryAdvertisementsDAOHibernate.class, session);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.interfaces.SummaryAdvertisementsDAO#findByRecruiterUserId(java.lang.String)
	 */
	public List findByRecruiterUserId(long recruiterId) {
		Criteria query = HibernateUtil.getCurrentSession()
				.createCriteria(SummaryAdvertisements.class);

		query.add(Restrictions.eq("recruiterId", new Long(recruiterId)));

		return query.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.interfaces.SummaryAdvertisementsDAO#find(java.lang.String,
	 *      long, java.util.Date, java.lang.String, java.lang.String,
	 *      java.util.Date)
	 */
	public List find(long recruiterId, long branchId, Date startDate,
			Date endDate, String jobTitle, String referenceNumber) {
		Criteria query = HibernateUtil.getCurrentSession()
				.createCriteria(SummaryAdvertisements.class);

		if (recruiterId != 0) {
			query.add(Restrictions.eq("recruiterId", new Long(recruiterId)));
		}

		if (branchId != 0) {
			query.add(Restrictions.eq("branchId", new Long(branchId)));
		}

		if (jobTitle != null && !jobTitle.equals("")) {
			StringBuffer jt = new StringBuffer("%");
			jt.append(jobTitle.trim());
			jt.append("%");

			query.add(Restrictions.ilike("jobTitle", jt.toString()));
		}

		if (referenceNumber != null && !referenceNumber.equals("")) {
			query.add(Restrictions.eq("referenceNumber", referenceNumber));
		}

		if (startDate != null && endDate != null) {
			query.add(Restrictions.between("updatedOn", startDate, endDate));
		}

		return query.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findByID(java.io.Serializable)
	 */
	public Object findByID(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findAll()
	 */
	public List findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findByExample(java.lang.Object)
	 */
	public List findByExample(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#save(java.lang.Object)
	 */
	public Object save(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#makePersistent(java.lang.Object)
	 */
	public Object makePersistent(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findByCustomQuery(java.lang.String,
	 *      java.util.Properties)
	 */
	public List findByCustomQuery(String query, Properties params) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#runSQLQuery(java.lang.String,
	 *      java.util.Properties, java.lang.String)
	 */
	public Object runSQLQuery(String query, Properties params, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#delete(java.lang.Object)
	 */
	public Object delete(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#makeUpdate(java.lang.Object)
	 */
	public Object makeUpdate(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#updateAll(java.util.List)
	 */
	public void updateAll(List records) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#deleteAll(java.util.List)
	 */
	public void deleteAll(List records) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#saveAll(java.util.List)
	 */
	public void saveAll(List records) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findPageByCustomHQLQuery(java.lang.String,
	 *      java.util.Properties, int, int)
	 */
	public List findPageByCustomHQLQuery(String query, Properties params,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findPageByCustomSQLQuery(java.lang.String,
	 *      java.util.Properties, int, int, java.lang.String, java.lang.Class)
	 */
	public List findPageByCustomSQLQuery(String query, Properties params,
			int pageNumber, int pageSize, String entityAlias, Class entityName) {
		// TODO Auto-generated method stub
		return null;
	}

    public int countByRecruiterUserId(long recruiterId, int advertStatus)
    {
        Session session = HibernateUtil.getCurrentSession();
        Criteria query = session.createCriteria(com.manpower.portal.mpnet.hbn.beans.SummaryAdvertisements.class).setProjection(Projections.rowCount()).add(Restrictions.eq("recruiterId", new Long(recruiterId)));
        
        switch(advertStatus)
        {
        	case 1:
        	{
        	    query.add(Restrictions.sqlRestriction(" TRUNC(EXPIRATIONDATE)>=TRUNC(SYSDATE)"));
        	    break;
        	}
        	case 2:
        	{
        	    query.add(Restrictions.sqlRestriction(" TRUNC(EXPIRATIONDATE)<TRUNC(SYSDATE)"));
        	    break;
        	}
        	case 3:
        	{
        	    break;
        	}
        	default:
        	{
        	    query.add(Restrictions.sqlRestriction(" TRUNC(EXPIRATIONDATE)>=TRUNC(SYSDATE)"));
        	}
        }
        return ((Integer)query.uniqueResult()).intValue();
    }	
    
    public int count(long recruiterId, long branchId, Date startDate, Date endDate, String jobTitle, 
            String referenceNumber, int advertStatus)
    {
        Criteria query = HibernateUtil.getCurrentSession().createCriteria(com.manpower.portal.mpnet.hbn.beans.SummaryAdvertisements.class).setProjection(Projections.rowCount());
        if(recruiterId != 0L)
            query.add(Restrictions.eq("recruiterId", new Long(recruiterId)));
        if(branchId != 0L)
            query.add(Restrictions.eq("branchId", new Long(branchId)));
        if(jobTitle != null && !jobTitle.equals(""))
        {
            StringBuffer jt = new StringBuffer("%");
            jt.append(jobTitle.trim());
            jt.append("%");
            query.add(Restrictions.ilike("jobTitle", jt.toString()));
        }
        if(referenceNumber != null && !referenceNumber.equals(""))
            query.add(Restrictions.eq("referenceNumber", referenceNumber));
        if(startDate != null && endDate != null)
            query.add(Restrictions.between("updatedOn", startDate, endDate));
        
        switch(advertStatus)
        {
        	case 1:
        	{
        	    query.add(Restrictions.sqlRestriction(" TRUNC(EXPIRATIONDATE)>=TRUNC(SYSDATE)"));
        	    break;
        	}
        	case 2:
        	{
        	    query.add(Restrictions.sqlRestriction(" TRUNC(EXPIRATIONDATE)<TRUNC(SYSDATE)"));
        	    break;
        	}
        	case 3:
        	{
        	    break;
        	}
        	default:
        	{
        	    query.add(Restrictions.sqlRestriction(" TRUNC(EXPIRATIONDATE)>=TRUNC(SYSDATE)"));
        	}
        }
        
        return ((Integer)query.uniqueResult()).intValue();
    }    
    
    public List findByRecruiterUserId(long recruiterId, int fromIndex, int toIndex, int sortOrder, int advertStatus)
    {
        StringBuffer sqlQuery = new StringBuffer("select * from(select a.*, rownum rnum from (select * from  V_SUMMARY_ADVERTISEMENTS this_ where  this_.ADVERTCONTACTID=" + recruiterId);
        
        switch(advertStatus)
        {
        	case 1:
        	{
        	    sqlQuery.append(" and TRUNC(EXPIRATIONDATE)>=TRUNC(SYSDATE)");
        	    break;
        	}
        	case 2:
        	{
        	    sqlQuery.append(" and TRUNC(EXPIRATIONDATE)<TRUNC(SYSDATE)");
        	    break;
        	}
        	case 3:
        	{
        	    break;
        	}
        	default:
        	{
        	    sqlQuery.append(" and TRUNC(EXPIRATIONDATE)>=TRUNC(SYSDATE)");
        	}
        }
        
        switch(sortOrder)
        {
        case 1: // '\002'
            sqlQuery.append(" order by this_.UPDATEDON ASC");
            break;

        case 2: // '\001'
            sqlQuery.append(" order by this_.UPDATEDON DESC");
            break;

        case 5: // '\006'
            sqlQuery.append(" order by EXPIRATIONDATE ASC");
            break;

        case 6: // '\005'
            sqlQuery.append(" order by EXPIRATIONDATE DESC");
            break;

        case 3: // '\004'
            sqlQuery.append(" order by upper(trim(this_.JOBTITLE)) ASC");
            break;

        case 4: // '\003'
            sqlQuery.append(" order by upper(trim(this_.JOBTITLE)) DESC");
            break;
            
        case 7: // '\007'
            sqlQuery.append(" order by (APPLIED+EXT_APPLIED) ASC");
            break;
            
        case 8: // '\008'
            sqlQuery.append(" order by (APPLIED+EXT_APPLIED) DESC");
            break;            

        case 9: // '\007'
            sqlQuery.append(" order by upper(trim(REFERENCENUMBER)) ASC");
            break;
            
        case 10: // '\008'
            sqlQuery.append(" order by upper(trim(REFERENCENUMBER)) DESC");
            break;
            
        case 11: // '\011'
            sqlQuery.append(" order by PUBLICATIONDATE ASC");
            break;
            
        case 12: // '\012'
            sqlQuery.append(" order by PUBLICATIONDATE DESC");
            break;
            
        default:
            sqlQuery.append(" order by this_.UPDATEDON DESC");
            break;
        }
        
        sqlQuery.append(" ) a where rownum<=" + toIndex);
        sqlQuery.append(") where rnum>=" + fromIndex);
        
        Session session = HibernateUtil.getCurrentSession();
        SQLQuery query = session.createSQLQuery(sqlQuery.toString()).addEntity(com.manpower.portal.mpnet.hbn.beans.SummaryAdvertisements.class);
        return query.list();
    }

    public List find(long recruiterId, long branchId, Date startDate, Date endDate, String jobTitle, 
            String referenceNumber, int fromIndex, int toIndex, int advertStatus, int sortOrder)
    {
        StringBuffer sqlQuery = new StringBuffer("select * from(select a.*, rownum rnum from (select * from V_SUMMARY_ADVERTISEMENTS this_ where ");
        
        if(branchId != 0L)
	    {
	        sqlQuery.append(" this_.BRANCH_ID=" + branchId);
	    }
        if(referenceNumber != null && !referenceNumber.equals(""))
        {
            sqlQuery.append(" and this_.REFERENCENUMBER='" + referenceNumber + "'");
        }else{
		    switch(advertStatus)
		    {
		    	case Constants.ADVERT_STATUS_ACTIVE:
		    	{
		    	    sqlQuery.append(" and this_.EXPIRATIONDATE>=to_date('" + getDateString(new Date(), "MM/dd/yyyy") + "','MM/dd/yyyy')");
		    	    break;
		    	}
		    	case Constants.ADVERT_STATUS_EXPIRED:
		    	{
		    	    sqlQuery.append(" and this_.EXPIRATIONDATE<to_date('" + getDateString(new Date(), "MM/dd/yyyy") + "','MM/dd/yyyy')");
		    	    break;
		    	}
		    	case Constants.ADVERT_STATUS_ALL:
		    	{
		    	    break;
		    	}
		    	default:
		    	{
		    	    sqlQuery.append(" and this_.EXPIRATIONDATE>=to_date('" + getDateString(new Date(), "MM/dd/yyyy") + "','MM/dd/yyyy')");
		    	}
		    }        
		    
		
		    if(recruiterId != 0L)
		    {
		        sqlQuery.append(" and this_.ADVERTCONTACTID=" + recruiterId);
		    }
		   
		    if(jobTitle != null && !jobTitle.equals(""))
		    {
		        StringBuffer jt = new StringBuffer("'%");
		        jt.append(jobTitle.trim());
		        jt.append("%'");
		        sqlQuery.append(" and lower(this_.JOBTITLE) like lower(" + jt.toString()+")");
		    }
		    if(startDate != null && endDate != null)
		    {
		        sqlQuery.append(" and this_.UPDATEDON between to_date('" + getDateString(startDate, "MM/dd/yyyy") + "','MM/dd/yyyy') and to_date('" + getDateString(endDate, "MM/dd/yyyy") + "','MM/dd/yyyy')");
		    }
		}
		    
	    switch(sortOrder)
	    {
	    case 1: // '\002'
	        sqlQuery.append(" order by this_.UPDATEDON ASC");
	        break;
	
	    case 2: // '\001'
	        sqlQuery.append(" order by this_.UPDATEDON DESC");
	        break;
	
	    case 5: // '\006'
	        sqlQuery.append(" order by EXPIRATIONDATE ASC");
	        break;
	
	    case 6: // '\005'
	        sqlQuery.append(" order by EXPIRATIONDATE DESC");
	        break;
	
	    case 3: // '\004'
	        sqlQuery.append(" order by upper(trim(this_.JOBTITLE)) ASC");
	        break;
	
	    case 4: // '\003'
	        sqlQuery.append(" order by upper(trim(this_.JOBTITLE)) DESC");
	        break;
	        
	    case 7: // '\007'
	        sqlQuery.append(" order by (APPLIED+EXT_APPLIED) ASC");
	        break;
	        
	    case 8: // '\008'
	        sqlQuery.append(" order by (APPLIED+EXT_APPLIED) DESC");
	        break;            
	
	    case 9: // '\007'
	        sqlQuery.append(" order by upper(trim(REFERENCENUMBER)) ASC");
	        break;
	        
	    case 10: // '\008'
	        sqlQuery.append(" order by upper(trim(REFERENCENUMBER)) DESC");
	        break; 
	        
	    case 11: // '\011'
	        sqlQuery.append(" order by PUBLICATIONDATE ASC");
	        break;
	        
	    case 12: // '\012'
	        sqlQuery.append(" order by PUBLICATIONDATE DESC");
	        break;
	        
	    default:
	        sqlQuery.append(" order by this_.UPDATEDON DESC");
	        break;
	    }
	    
	    sqlQuery.append(" ) a where rownum<=" + toIndex);
	    sqlQuery.append(") where rnum>=" + fromIndex);
		    
        
        SQLQuery query = HibernateUtil.getCurrentSession().createSQLQuery(sqlQuery.toString()).addEntity(com.manpower.portal.mpnet.hbn.beans.SummaryAdvertisements.class);
        return query.list();
    }   
    
    private String getDateString(Date date, String dateFormat)
    {
        SimpleDateFormat fmt = new SimpleDateFormat(dateFormat);
        return fmt.format(date);
    }

	public long findAppliedCountByAdvertId(long advertId) {
		long applicants = 0;
		
		Criteria query = HibernateUtil.getCurrentSession().createCriteria(SummaryAdvertisements.class);
		query.add(Restrictions.eq("id", advertId));
		SummaryAdvertisements advert = (SummaryAdvertisements)query.uniqueResult();
		
		if(advert != null) {
			applicants = advert.getAppliedCount() + advert.getExternalAppliedCount();
		}
		
		return applicants;
	}    
}
