/*
 * Created on Dec 19, 2006
 *
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.RecruiterLocationReportDAO;
import com.manpower.portal.mpnet.hbn.beans.RecruiterLocationReport;
import com.manpower.portal.mpnet.util.TextUtils;

/**
 * @author Dimitar Bakardzhiev
 *  
 */
public class RecruiterLocationReportHibernateDAO extends GenericHibernateDAO
		implements RecruiterLocationReportDAO {
	private static Logger logger = Logger
			.getLogger(RecruiterLocationReportHibernateDAO.class);
	
	private static final String SEARCH_BY_KEYWORD_QUERY_WITH_JOB_COUNT="SELECT  C.ID CANDIDATE_ID,"+
    "1 CANDIDATE_ROW_NUMBER,"+
    "(case when CPS.STATUS is null then 0 else CPS.STATUS end) ACTIVE_STATUS, "+
	   "CR.PRIMARY_RESUME, "+		                       
    "C.FIRSTNAME,"+
    "C.LASTNAME,"+
    "sysdate DATEAPPLIED,"+
    "OCD.LAST_LOGIN_DATE LAST_LOGIN_DATE,"+
    "'NRated' RATING,"+
    "(case when OCD.REVIEW_STATUS is null then 'NReview'"+
    "      else OCD.REVIEW_STATUS"+
    "       end) STATUS,"+
    "(case when C.MIDDLENAME is null then ' '"+
    "      else C.MIDDLENAME"+
    "       end) MIDDLENAME,"+
    "CR.ID RESUME_ID,"+
    "' '  PREFERED_LOCATION,"+
    "0 region_id,"+
    "0  location_id,"+
    "' ' LANG,"+
    "C.SITE_ID,"+
    "B.BRANCHNAME,"+
    "CR.UPDATEDON,"+
    "(case when OCD.SENT_TO_FO is null then 0"+
    "      else OCD.SENT_TO_FO"+
    "       end) SENT_TO_FO,"+
    "'0' BG_SCORE,"+
    "'0' CH_SCORE,"+
    "(case when JA.COUNT_OF_JOBS_APPLIED_FOR is null then 0"+
    "      else JA.COUNT_OF_JOBS_APPLIED_FOR" +
    "       end) COUNT_OF_JOBS_APPLIED_FOR" +
    " FROM CANDIDATES C"+
    " INNER JOIN CANDIDATERESUMES CR ON C.ID = CR.CANDIDATE_ID AND C.SITE_ID = :siteID AND CR.PRIMARY_RESUME=1" +
    " INNER JOIN CANDIDATEPROFILESTATUS CPS ON C.ID=CPS.candidateid AND  CPS.STATUS = 1" +
    " INNER JOIN OTHER_CANDIDATE_DETAILS OCD ON C.ID = OCD.CANDIDATE_ID"+                         
    " INNER JOIN BRANCHES B  ON C.BRANCH_ID = B.ID" +
	" LEFT OUTER JOIN mv_candidatejobapplications JA ON C.ID = JA.CANDIDATE_ID";
	
private static final String SEARCH_BY_KEYWORD_QUERY_WITHOUT_JOB_COUNT="SELECT  C.ID CANDIDATE_ID,"+
	    "1 CANDIDATE_ROW_NUMBER,"+
     "(case when CPS.STATUS is null then 0 else CPS.STATUS end) ACTIVE_STATUS, "+
	    "CR.PRIMARY_RESUME, "+							    
	    "C.FIRSTNAME,"+
	    "C.LASTNAME,"+
	    "sysdate DATEAPPLIED,"+
	    "(case when OCD.LAST_LOGIN_DATE is null then C.UPDATEDON"+
	    "      else OCD.LAST_LOGIN_DATE"+
	    "       end) LAST_LOGIN_DATE,"+
	    "'NRated' RATING,"+
	    "(case when OCD.REVIEW_STATUS is null then 'NReview'"+
	    "      else OCD.REVIEW_STATUS"+
	    "       end) STATUS,"+
	    "(case when C.MIDDLENAME is null then ' '"+
	    "      else C.MIDDLENAME"+
	    "       end) MIDDLENAME,"+
	    "CR.ID RESUME_ID,"+
	    "' '  PREFERED_LOCATION,"+
	    "0 region_id,"+
	    "0  location_id,"+
	    "' ' LANG,"+
	    "C.SITE_ID,"+
	    "B.BRANCHNAME,"+
	    "CR.UPDATEDON,"+
	    "(case when OCD.SENT_TO_FO is null then 0"+
	    "      else OCD.SENT_TO_FO"+
	    "       end) SENT_TO_FO,"+
	    "'0' BG_SCORE,"+
	    "'0' CH_SCORE,"+
	    " 0 COUNT_OF_JOBS_APPLIED_FOR" +
	    " FROM CANDIDATES C " +
	    " INNER JOIN CANDIDATERESUMES CR ON C.ID = CR.CANDIDATE_ID AND C.SITE_ID = :siteID AND CR.PRIMARY_RESUME=1" +
	    " INNER JOIN CANDIDATEPROFILESTATUS CPS ON C.id=CPS.candidateid AND CPS.STATUS = 1" +
	    " INNER JOIN OTHER_CANDIDATE_DETAILS OCD ON C.ID = OCD.CANDIDATE_ID" +                         
	    " INNER JOIN BRANCHES B ON C.BRANCH_ID = B.ID ";
	    



private static final String SEARCH_BY_KEYWORD_QUERY_WITH_JOB_COUNT_ALL_RESULTS_NOT_INCLUDED="SELECT  V.CANDIDATE_ID CANDIDATE_ID,"+
	    "1 CANDIDATE_ROW_NUMBER,"+
	    "V.ACTIVE_STATUS, "+
	    "CR.PRIMARY_RESUME, "+						    
	    "V.FIRSTNAME,"+
	    "V.LASTNAME,"+
	    "sysdate DATEAPPLIED,"+
	    "V.LAST_LOGIN_DATE,"+
	    "'NRated' RATING,"+
	    "V.STATUS,"+
	    "V.MIDDLENAME,"+
	    "CR.ID RESUME_ID,"+
	    "' '  PREFERED_LOCATION,"+
	    "0 region_id,"+
	    "0  location_id,"+
	    "' ' LANG,"+
	    "V.SITE_ID,"+
	    "V.BRANCHNAME,"+
	    "CR.UPDATEDON,"+
	    "V.SENT_TO_FO,"+
	    "'0' BG_SCORE,"+
	    "'0' CH_SCORE,"+
	    "(case when JA.COUNT_OF_JOBS_APPLIED_FOR is null then 0"+
	    "      else JA.COUNT_OF_JOBS_APPLIED_FOR"+
	    "       end) COUNT_OF_JOBS_APPLIED_FOR " +
	    " FROM RECRUITER_LOCATION_REPORT V "+
	    " INNER JOIN CANDIDATERESUMES CR ON V.CANDIDATE_ID = CR.CANDIDATE_ID AND V.SITE_ID = :siteID"+                         
	    " LEFT OUTER JOIN  mv_candidatejobapplications JA ON V.CANDIDATE_ID = JA.CANDIDATE_ID ";

private static final String SEARCH_BY_KEYWORD_QUERY_WITHOUT_JOB_COUNT_ALL_RESULTS_NOT_INCLUDED="SELECT  V.CANDIDATE_ID CANDIDATE_ID,"+
	    "1 CANDIDATE_ROW_NUMBER,"+
	    "V.ACTIVE_STATUS, "+
	    "CR.PRIMARY_RESUME, "+			    
	    "V.FIRSTNAME,"+
	    "V.LASTNAME,"+
	    "sysdate DATEAPPLIED,"+
	    "V.LAST_LOGIN_DATE,"+
	    "'NRated' RATING,"+
	    "V.STATUS,"+
	    "V.MIDDLENAME,"+
	    "CR.ID RESUME_ID,"+
	    "' '  PREFERED_LOCATION,"+
	    "0 region_id,"+
	    "0  location_id,"+
	    "' ' LANG,"+
	    "V.SITE_ID,"+
	    "V.BRANCHNAME,"+
	    "CR.UPDATEDON,"+
	    "V.SENT_TO_FO,"+
	    "'0' BG_SCORE,"+
	    "'0' CH_SCORE,"+					    
	    " 0 COUNT_OF_JOBS_APPLIED_FOR" +
	    "  FROM RECRUITER_LOCATION_REPORT V " +
	    " INNER JOIN CANDIDATERESUMES CR ON V.CANDIDATE_ID = CR.CANDIDATE_ID AND V.SITE_ID = :siteID";

	/**
	 * Constructor
	 * 
	 * @param persistentClass
	 * @param session
	 */
	public RecruiterLocationReportHibernateDAO(Session session) {
		super(RecruiterLocationReport.class, session);
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

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.interfaces.RecruiterLocationReportDAO#findCandidateById(long)
	 */
	public RecruiterLocationReport findCandidateById(long candidate_id) {
		Session session = HibernateUtil.getCurrentSession();
		Criteria query = session.createCriteria(RecruiterLocationReport.class)
				.add(Restrictions.eq("id", new Long(candidate_id)));
		return (RecruiterLocationReport) query.uniqueResult();
	}
	
    public int countCandidatesByKeyword(String keyword, String locations, long siteId, long locationANY,boolean showAllResults)
    {
        int number = 0;
        if(logger.isDebugEnabled())
            logger.debug("Escaped keyword is:" + keyword);
        
        List selectedCodes=new ArrayList(5);
        if(locations != null && locations.trim().length() > 0)
        {
            String selectedLocations[] = locations.split(",");
            selectedCodes = new ArrayList(selectedLocations.length);
            for(int i = 0; i < selectedLocations.length; i++){
                selectedCodes.add(Long.valueOf(selectedLocations[i]));
            }
            
            if(locationANY > 0){
            	selectedCodes.add(Long.valueOf(locationANY));
            }
        }
        
        StringBuffer sqlQuery=new StringBuffer();
        
        if(showAllResults)
        {
            sqlQuery = generateCountQueryAllResultsIncluded(keyword, siteId, selectedCodes);
        }
        else
        {
            sqlQuery = generateCountQueryAllResultsNotIncluded(keyword, siteId, selectedCodes);            
        }
        String SQL = sqlQuery.toString();
        SQL = SQL.replaceFirst(":siteID", String.valueOf(siteId));
        Session session = HibernateUtil.getCurrentSession();
        SQLQuery query = session.createSQLQuery(SQL);
        Object pObject = query.uniqueResult();
        if(pObject != null && (pObject instanceof Number))
            number = ((Number)pObject).intValue();
        return number;
    }

    /**
     * @param keyword
     * @param location
     * @param regionCode
     * @param siteId
     * @param searchByLocation
     * @param locationANY
     * @param regionANY
     * @param selectedCodes
     * @return
     */
    private StringBuffer generateCountQueryAllResultsIncluded(String keyword,long siteId, List selectedCodes)
    {
        StringBuffer sqlQuery = new StringBuffer("SELECT COUNT(*) FROM CANDIDATES C");
        sqlQuery.append(" INNER JOIN CANDIDATERESUMES CR ON C.ID = CR.CANDIDATE_ID AND C.SITE_ID = :siteID AND CR.PRIMARY_RESUME=1");
        sqlQuery.append(" INNER JOIN  CANDIDATEPROFILESTATUS CPS ON C.id=CPS.candidateid AND CPS.STATUS = 1");
        sqlQuery.append(" INNER JOIN OTHER_CANDIDATE_DETAILS OCD ON C.ID = OCD.CANDIDATE_ID");
        String selectLocations="";
        if(selectedCodes!=null && !selectedCodes.isEmpty())
        {
            sqlQuery.append(" ,(SELECT cp1.candidate_id, max(location_id) location_id, max(location_flag) location_flag FROM Candidatepreferredlocations cp1 ");
            selectLocations=" and CP.candidate_id=CR.candidate_id ";
	        sqlQuery.append(" where LOCATION_ID in (");
	        sqlQuery.append(TextUtils.arrayToSQLNumbers(selectedCodes.toArray()) + ") GROUP BY candidate_id ) CP");
        }
        
        sqlQuery.append(" WHERE contains(CR.resume,");
        sqlQuery.append("'" + keyword + "')>0");
        sqlQuery.append(selectLocations);
	    sqlQuery.append(" AND C.BRANCH_ID > 0"); 

        if(logger.isDebugEnabled())
            logger.debug("Query is:" + sqlQuery.toString());
        return sqlQuery;
    }
    
    private StringBuffer generateQueryCandidateIdsAllResultsIncluded(String keyword,long siteId, List selectedCodes)
    {
        StringBuffer sqlQuery = new StringBuffer("SELECT C.ID FROM CANDIDATES C");
        sqlQuery.append(" INNER JOIN CANDIDATERESUMES CR ON C.ID = CR.CANDIDATE_ID AND C.SITE_ID = :siteID AND CR.PRIMARY_RESUME=1");
        sqlQuery.append(" INNER JOIN  CANDIDATEPROFILESTATUS CPS ON C.id=CPS.candidateid AND CPS.STATUS = 1");
        sqlQuery.append(" INNER JOIN OTHER_CANDIDATE_DETAILS OCD ON C.ID = OCD.CANDIDATE_ID");
        String selectLocations="";
        if(selectedCodes!=null && !selectedCodes.isEmpty())
        {
            sqlQuery.append(" ,(SELECT cp1.candidate_id, max(location_id) location_id, max(location_flag) location_flag FROM Candidatepreferredlocations cp1 ");
            selectLocations=" and CP.candidate_id=CR.candidate_id ";
	        sqlQuery.append(" where LOCATION_ID in (");
	        sqlQuery.append(TextUtils.arrayToSQLNumbers(selectedCodes.toArray()) + ") GROUP BY candidate_id ) CP");
        }
        
        sqlQuery.append(" WHERE contains(CR.resume,");
        sqlQuery.append("'" + keyword + "')>0");
        sqlQuery.append(selectLocations);
	    sqlQuery.append(" AND C.BRANCH_ID > 0"); 

        if(logger.isDebugEnabled())
            logger.debug("Query is:" + sqlQuery.toString());
        return sqlQuery;
    }

    /**
     * @param keyword
     * @param location
     * @param regionCode
     * @param siteId
     * @param searchByLocation
     * @param locationANY
     * @param regionANY
     * @param selectedCodes
     * @return
     */
    private StringBuffer generateCountQueryAllResultsNotIncluded(String keyword, long siteId, List selectedCodes)
    {
        StringBuffer sqlQuery = new StringBuffer("SELECT COUNT(*) FROM RECRUITER_LOCATION_REPORT V");
        sqlQuery.append(" INNER JOIN CANDIDATERESUMES CR ON V.CANDIDATE_ID = CR.CANDIDATE_ID AND V.SITE_ID = :siteID AND CR.PRIMARY_RESUME=1 AND V.ACTIVE_STATUS=1");
        String selectLocations="";
        if(selectedCodes!=null && !selectedCodes.isEmpty())
        {
            sqlQuery.append(" ,(SELECT cp1.candidate_id, max(location_id) location_id, max(location_flag) location_flag FROM Candidatepreferredlocations cp1 ");
            selectLocations=" and CP.candidate_id=CR.candidate_id ";
	        sqlQuery.append(" where LOCATION_ID in (");
	        sqlQuery.append(TextUtils.arrayToSQLNumbers(selectedCodes.toArray()) + ") GROUP BY candidate_id ) CP");
        }
        sqlQuery.append(" WHERE contains(CR.resume,");
        sqlQuery.append("'" + keyword + "') > 0");
        sqlQuery.append(selectLocations);
        
        
        if(logger.isDebugEnabled())
            logger.debug("Query is:" + sqlQuery.toString());
        return sqlQuery;
    }    
    
    private StringBuffer generateQueryCandidateIdsAllResultsNotIncluded(String keyword, long siteId, List selectedCodes)
    {
        StringBuffer sqlQuery = new StringBuffer("SELECT V.CANDIDATE_ID FROM RECRUITER_LOCATION_REPORT V");
        sqlQuery.append(" INNER JOIN CANDIDATERESUMES CR ON V.CANDIDATE_ID = CR.CANDIDATE_ID AND V.SITE_ID = :siteID AND CR.PRIMARY_RESUME=1 AND V.ACTIVE_STATUS=1");
        String selectLocations="";
        if(selectedCodes!=null && !selectedCodes.isEmpty())
        {
            sqlQuery.append(" ,(SELECT cp1.candidate_id, max(location_id) location_id, max(location_flag) location_flag FROM Candidatepreferredlocations cp1 ");
            selectLocations=" and CP.candidate_id=CR.candidate_id ";
	        sqlQuery.append(" where LOCATION_ID in (");
	        sqlQuery.append(TextUtils.arrayToSQLNumbers(selectedCodes.toArray()) + ") GROUP BY candidate_id ) CP");
        }
        sqlQuery.append(" WHERE contains(CR.resume,");
        sqlQuery.append("'" + keyword + "') > 0");
        sqlQuery.append(selectLocations);
        
        
        if(logger.isDebugEnabled())
            logger.debug("Query is:" + sqlQuery.toString());
        return sqlQuery;
    }
    
    
    /**
     * @param keyword
     * @param location
     * @param regionCode
     * @param siteId
     * @param searchByLocation
     * @param locationANY
     * @param regionANY
     * @param fromIndex
     * @param toIndex
     * @param sortOrder
     * @param includeJobCount
     * @param sqlQuery
     * @param selectedCodes
     */
    private void generateSelectQueryAllResultsIncluded(String keyword, long siteId, int maxResult,int fromIndex, int toIndex, int sortOrder, boolean includeJobCount, StringBuffer sqlQuery, List selectedCodes)
    {
        String selectjobCount="";
        
        sqlQuery.append("select /*+ FIRST_ROWS */ * FROM(select B.*, rownum rnum FROM ( SELECT A.* FROM (");
        if(includeJobCount)
        {
           sqlQuery.append(SEARCH_BY_KEYWORD_QUERY_WITH_JOB_COUNT);         
        }
        else
        {
            sqlQuery.append(SEARCH_BY_KEYWORD_QUERY_WITHOUT_JOB_COUNT);
        }
        
        String locationsSelect="";
        
        if(selectedCodes!=null && !selectedCodes.isEmpty())
        {
            sqlQuery.append(" ,(SELECT cp1.candidate_id, max(location_id) location_id, max(location_flag) location_flag FROM Candidatepreferredlocations cp1 ");
            locationsSelect=" AND cp.candidate_id  =c.id ";
            
	        sqlQuery.append(" where LOCATION_ID in (");
	        sqlQuery.append(TextUtils.arrayToSQLNumbers(selectedCodes.toArray()) + ") GROUP BY candidate_id ) CP ");
	    }
        
        
        sqlQuery.append(" WHERE contains(CR.resume,'" + keyword + "') > 0");

        sqlQuery.append(locationsSelect);
        sqlQuery.append(selectjobCount);
        
        
        switch(sortOrder)
        {
        case 2: // '\002'
            sqlQuery.append(" order by LAST_LOGIN_DATE ASC");
            break;

        default : // '\001'
            sqlQuery.append(" order by LAST_LOGIN_DATE DESC");
            break;
        }
        sqlQuery.append(" ) A WHERE ROWNUM <= " + maxResult);
        
        switch(sortOrder)
        {
        case 6: // '\006'
            sqlQuery.append(" order by BRANCHNAME ASC");
            break;

        case 5: // '\005'
            sqlQuery.append(" order by BRANCHNAME DESC");
            break;

        case 4: // '\004'
            sqlQuery.append(" order by upper(FIRSTNAME||MIDDLENAME||LASTNAME) ASC");
            break;

        case 3: // '\003'
            sqlQuery.append(" order by upper(FIRSTNAME||MIDDLENAME||LASTNAME) DESC");
            break;

        default:
            break;
        }
        sqlQuery.append(" ) B");
        sqlQuery.append(") where RNUM BETWEEN " + fromIndex + " AND " + toIndex );
    }
    
    /**
     * @param keyword
     * @param location
     * @param regionCode
     * @param siteId
     * @param searchByLocation
     * @param locationANY
     * @param regionANY
     * @param fromIndex
     * @param toIndex
     * @param sortOrder
     * @param includeJobCount
     * @param sqlQuery
     * @param selectedCodes
     */
    private void generateSelectQueryAllResultsNotIncluded(String keyword, long siteId, int maxResult,int fromIndex, int toIndex, int sortOrder, boolean includeJobCount, StringBuffer sqlQuery, List selectedCodes)
    {
        String selectjobCount="";
        
        sqlQuery.append("select /*+ FIRST_ROWS */ * FROM(select B.*, rownum rnum FROM ( SELECT A.* FROM (");
        if(includeJobCount)
        {
           sqlQuery.append(SEARCH_BY_KEYWORD_QUERY_WITH_JOB_COUNT_ALL_RESULTS_NOT_INCLUDED);
        }
        else
        {
            sqlQuery.append(SEARCH_BY_KEYWORD_QUERY_WITHOUT_JOB_COUNT_ALL_RESULTS_NOT_INCLUDED);
        }

        String locationsSelect="";
        
        if(selectedCodes!=null && !selectedCodes.isEmpty())
        {
            sqlQuery.append(" ,(SELECT cp1.candidate_id, max(location_id) location_id, max(location_flag) location_flag FROM Candidatepreferredlocations cp1 ");
            locationsSelect=" AND cp.candidate_id  =V.candidate_id ";

            sqlQuery.append(" where LOCATION_ID in (");
	        sqlQuery.append(TextUtils.arrayToSQLNumbers(selectedCodes.toArray()) + ") GROUP BY candidate_id ) CP ");
        }
        
        sqlQuery.append(" where V.SITE_ID=" + String.valueOf(siteId));
        sqlQuery.append(locationsSelect);
        sqlQuery.append(selectjobCount);
        sqlQuery.append(" AND V.ACTIVE_STATUS=1 ");            
	    sqlQuery.append(" AND CR.PRIMARY_RESUME=1 ");
        
        sqlQuery.append(" AND contains(CR.resume,");
        sqlQuery.append("'" + keyword + "') > 0");
        
        switch(sortOrder)
        {
        case 2: // '\002'
            sqlQuery.append(" order by LAST_LOGIN_DATE ASC");
            break;

        default:
            sqlQuery.append(" order by LAST_LOGIN_DATE DESC");
            break;
        }
        
        sqlQuery.append(" ) A WHERE ROWNUM <= " + maxResult);
        
        switch(sortOrder)
        {
	        case 6: // '\006'
	            sqlQuery.append(" order by BRANCHNAME ASC");
	            break;
	
	        case 5: // '\005'
	            sqlQuery.append(" order by BRANCHNAME DESC");
	            break;
	
	        case 4: // '\004'
	            sqlQuery.append(" order by upper(FIRSTNAME||MIDDLENAME||LASTNAME) ASC");
	            break;
	
	        case 3: // '\003'
	            sqlQuery.append(" order by upper(FIRSTNAME||MIDDLENAME||LASTNAME) DESC");
	            break;
	
	        default:
	            break;
        }
        sqlQuery.append(" ) B");
        sqlQuery.append(") where RNUM BETWEEN " + fromIndex + " AND " + toIndex );
    }
    
    private void generateSelectQueryCandidateIdsAllResultsNotIncluded(String keyword, long siteId, boolean includeJobCount, StringBuffer sqlQuery, List selectedCodes)
    {
        String selectjobCount="";
        
        sqlQuery.append("select /*+ FIRST_ROWS */ * FROM(select B.*, rownum rnum FROM ( SELECT A.* FROM (");
        if(includeJobCount)
        {
           sqlQuery.append(SEARCH_BY_KEYWORD_QUERY_WITH_JOB_COUNT_ALL_RESULTS_NOT_INCLUDED);
        }
        else
        {
            sqlQuery.append(SEARCH_BY_KEYWORD_QUERY_WITHOUT_JOB_COUNT_ALL_RESULTS_NOT_INCLUDED);
        }

        String locationsSelect="";
        
        if(selectedCodes!=null && !selectedCodes.isEmpty())
        {
            sqlQuery.append(" ,(SELECT cp1.candidate_id, max(location_id) location_id, max(location_flag) location_flag FROM Candidatepreferredlocations cp1 ");
            locationsSelect=" AND cp.candidate_id  =V.candidate_id ";

            sqlQuery.append(" where LOCATION_ID in (");
	        sqlQuery.append(TextUtils.arrayToSQLNumbers(selectedCodes.toArray()) + ") GROUP BY candidate_id ) CP ");
        }
        
        sqlQuery.append(" where V.SITE_ID=" + String.valueOf(siteId));
        sqlQuery.append(locationsSelect);
        sqlQuery.append(selectjobCount);
        sqlQuery.append(" AND V.ACTIVE_STATUS=1 ");            
	    sqlQuery.append(" AND CR.PRIMARY_RESUME=1 ");
        
        sqlQuery.append(" AND contains(CR.resume,");
        sqlQuery.append("'" + keyword + "') > 0");
    }
    
    
    
    public List findCandidatesByKeyword(String keyword, String locations, long siteId,long locationANY, int maxResult,int fromIndex, int toIndex, int sortOrder,boolean includeJobCount, boolean showAllResults)
    {    	
        if(logger.isDebugEnabled())
            logger.debug("Escaped keyword is:" + keyword);

        List selectedCodes=new ArrayList(5);
        if(locations != null && locations.trim().length() > 0)
        {
            String selectedLocations[] = locations.split(",");
            selectedCodes = new ArrayList(selectedLocations.length);
            for(int i = 0; i < selectedLocations.length; i++)
                selectedCodes.add(Long.valueOf(selectedLocations[i]));
            
            if(locationANY > 0){
            	selectedCodes.add(Long.valueOf(locationANY));
            }
        }
       
        StringBuffer sqlQuery = new StringBuffer();
        if(showAllResults)
        {
            generateSelectQueryAllResultsIncluded(keyword, siteId, maxResult,fromIndex, toIndex, sortOrder, includeJobCount, sqlQuery, selectedCodes);
           
        }
        else
        {
            generateSelectQueryAllResultsNotIncluded(keyword, siteId, maxResult,fromIndex, toIndex, sortOrder, includeJobCount, sqlQuery, selectedCodes);
        }
        String SQL = sqlQuery.toString();
        SQL = SQL.replaceFirst(":siteID", String.valueOf(siteId));
        if(logger.isDebugEnabled())
            logger.debug("Query is:" + sqlQuery.toString());
        Session session = HibernateUtil.getCurrentSession();
        
        SQLQuery query = session.createSQLQuery(SQL).addEntity(com.manpower.portal.mpnet.hbn.beans.RecruiterLocationReport.class);
        List results = query.list();
        
        return results;
    }
    
    public List<Long> findAllCandidateIdsByKeyword(String keyword, String locations, long siteId,long locationANY, boolean includeJobCount, boolean showAllResults)
    {
    	List<BigDecimal> candidateIds;
    	List<Long> candidateIdsLong = new ArrayList<Long>();
        if(logger.isDebugEnabled())
            logger.debug("Escaped keyword is:" + keyword);
        
        List selectedCodes=new ArrayList(5);
        if(locations != null && locations.trim().length() > 0)
        {
            String selectedLocations[] = locations.split(",");
            selectedCodes = new ArrayList(selectedLocations.length);
            for(int i = 0; i < selectedLocations.length; i++){
                selectedCodes.add(Long.valueOf(selectedLocations[i]));
            }
            
            if(locationANY > 0){
            	selectedCodes.add(Long.valueOf(locationANY));
            }
        }
        
        StringBuffer sqlQuery=new StringBuffer();
        
        if(showAllResults)
        {
            sqlQuery = generateQueryCandidateIdsAllResultsIncluded(keyword, siteId, selectedCodes);
        }
        else
        {
            sqlQuery = generateQueryCandidateIdsAllResultsNotIncluded(keyword, siteId, selectedCodes);            
        }
        String SQL = sqlQuery.toString();
        SQL = SQL.replaceFirst(":siteID", String.valueOf(siteId));
        Session session = HibernateUtil.getCurrentSession();
        SQLQuery query = session.createSQLQuery(SQL);
        candidateIds = query.list();
        for (BigDecimal bdCandidateId : candidateIds) {
        	candidateIdsLong.add(new Long(bdCandidateId.longValue()));
		}
        return candidateIdsLong;
    }
    
    
}
