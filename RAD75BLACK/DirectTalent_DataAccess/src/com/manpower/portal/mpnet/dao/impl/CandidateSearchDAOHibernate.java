package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateSearchDAO;
import com.manpower.portal.mpnet.hbn.beans.CandidateSearch;
import com.manpower.portal.mpnet.hbn.beans.CandidateSearchFavorite;
import com.manpower.portal.mpnet.hbn.beans.CandidateSearchResume;

public class CandidateSearchDAOHibernate extends GenericHibernateDAO implements
		CandidateSearchDAO {
	private static final String SELECT_CLAUSE = " SELECT  	CS.ID," +
															"SITE_ID, " +
															"CAND_ID, " +
															"APPL_ID, " +
															"EMAIL, " +
															"FIRSTNAME, " +
															"MIDDLENAME, " +
															"LASTNAME, " +
															"PHONENUMBER, " +
															"BIRTHDATE, " +
															"RESIDENCEPERMIT, " +
															"WORKPERMITS, " +
															"INDUSTRYSECTOR, " +
															"DEGREECODE, " +
															"BRANCH_ID, " +
															"COUNTRY, " +
															"CITY, " +
															"STATE, " +
															"CODE, " +
															"LAST_LOGIN," +
															"CREATED_ON, " +
															"REVIEW_STATUS, " +
															"APPLIED_ON, " +
															"LAST_RESUME_DATE, " +
															"MIME_TYPE," +
															"FILENAME," +
															"LAST_UPDATED," +
															"LATITUDE," +
															"LONGITUDE," +
															"SCORE(1) TEXT_SCORE," +
															"LOCATION_ID";
	
	private static final String FROM_CLAUSE = 				" FROM CANDIDATESEARCH CS ";
	
	private static final String SQL_KEYWORD = SELECT_CLAUSE + FROM_CLAUSE;
	
	private static final String SQL_COUNT = "SELECT COUNT(*) " + FROM_CLAUSE;
	
	private static final String FAVORITE_SQL = "SELECT cs.id," +
													 " cs.cand_id," +
													 " cs.appl_id," +
													 " cs.firstname," +
													 " cs.middlename," +
													 " cs.lastname," +
													 " cs.email," +
													 " cf.recruiter_comment" +
											 " FROM candidatesearch cs, candidatesearchfavorites cf" +
											 " WHERE  cs.id = cf.candidate_id AND" +
												 	" cs.id IN (SELECT candidate_id FROM candidatesearchfavorites WHERE recruiter_id = ?) ";
	
	private static final String FAVOURITE_EXISTS_SQL = "SELECT 1 FROM dual WHERE EXISTS (SELECT 1 FROM candidatesearchfavorites WHERE candidate_id = ? AND recruiter_id = ?)";
	
	private static final String FAVORITE_COUNT_SQL = "SELECT count(*)" +
													" FROM (" +
																" SELECT 1 from candidatesearch cs, candidatesearchfavorites cf" +
																" WHERE cs.id = cf.candidate_id AND" +
																	  " cs.id IN (SELECT candidate_id FROM candidatesearchfavorites WHERE recruiter_id = ?) " +
															")";
	
	private static final String FAVODITE_DELETE_SQL = "DELETE FROM CANDIDATESEARCHFAVORITES WHERE CANDIDATE_ID = ? AND RECRUITER_ID = ?";
	
	
	
	public CandidateSearchDAOHibernate(Session session) {
		super(CandidateSearch.class, session);
	}
	
	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object findByID(Serializable id) {
//		System.out.println("looking for:" +id);
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getCurrentSession();
		CandidateSearch candidate = (CandidateSearch)session.get(CandidateSearch.class, id);
//		System.out.println("found candidate id:" +candidate.getId());
		return candidate;
	}

	public List<CandidateSearch> getCandidatesByKeyword(String keyword, long siteId,
			int orderByColumn, int maxResult, int fromIndex, int toIndex) {
		String SQL = generateSQLQuery(keyword, siteId, maxResult, orderByColumn, fromIndex, toIndex);
		Session session = HibernateUtil.getCurrentSession();
		SQLQuery query = session.createSQLQuery(SQL).addEntity(com.manpower.portal.mpnet.hbn.beans.CandidateSearch.class);
        List<CandidateSearch> results = query.list();
		return results;
	}
	
	public int getCandidatesByKeywordCount(String keyword, long siteId) {
		int number = 0;
		String SQL = generateSQLQueryCount(keyword, siteId);
		Session session = HibernateUtil.getCurrentSession();
		SQLQuery query = session.createSQLQuery(SQL);
		Object pObject = query.uniqueResult();
        if(pObject != null && (pObject instanceof Number))
            number = ((Number)pObject).intValue();
        return number;

	}
	
	   private String generateSQLQuery(String keyword, long siteId, int sortOrder, int maxResult, int fromIndex, int toIndex)
	    {
	        StringBuffer sqlQuery = new StringBuffer();
	        sqlQuery.append("select /*+ FIRST_ROWS */ * FROM(select B.*, rownum rnum FROM ( SELECT A.* FROM (");
	        sqlQuery.append(SQL_KEYWORD);
	        sqlQuery.append(" WHERE CS.SITE_ID=" + String.valueOf(siteId));
	        sqlQuery.append(" AND contains(CS.resume,");
	        sqlQuery.append("'" + keyword + "', 1) > 0");
	        
	        switch(sortOrder)
	        {
	        case 2: // '\002'
	            sqlQuery.append(" order by LAST_LOGIN ASC");
	            break;

	        default:
	            sqlQuery.append(" order by LAST_LOGIN DESC");
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
	        return sqlQuery.toString();
	    }

	   private String generateSQLQueryCount(String keyword, long siteId)
	   {
			StringBuffer sqlQuery = new StringBuffer("SELECT COUNT(*) FROM CANDIDATESEARCH C");
			sqlQuery.append(" WHERE contains(C.resume,");
			sqlQuery.append("'" + keyword + "')>0");
			sqlQuery.append(" AND C.SITE_ID = " + siteId); 
			
			return sqlQuery.toString();
	   }
	   
	   
	   public CandidateSearchResume getCandidateResume(long id){
		   Session session = HibernateUtil.getCurrentSession();
		   CandidateSearchResume resume = (CandidateSearchResume)session.get(CandidateSearchResume.class, id);
		   return resume;
	   }
	   
	   
	   private String generateSimpleSearchQuery(boolean select, long siteId, String keyword,List<Long> locationsIds, double latitude, double longitude, int distance, String distanceUnit){
		   String locId = Arrays.toString(locationsIds.toArray());
		   locId = "(" + locId.substring(1, locId.length() -1) + ")";
		   StringBuffer SQL_QUERY = new StringBuffer();
		   if(select)
			   SQL_QUERY.append(SQL_KEYWORD);
		   else
			   SQL_QUERY.append(SQL_COUNT);
		   SQL_QUERY.append(" WHERE	SITE_ID = " + siteId + " AND  ");
		   SQL_QUERY.append(" CONTAINS(RESUME, '" + keyword + " ',1)>0" );
		   SQL_QUERY.append(" AND (");
		   SQL_QUERY.append(" CS.LOCATION_ID  IN " + locId);
		   SQL_QUERY.append(" OR ");
		   SQL_QUERY.append(" ZIPDISTANCE.DISTCHECK("+ latitude + ", "+ longitude +",CS.LATITUDE, CS.LONGITUDE, '" + distanceUnit + "') < " + distance);
		   SQL_QUERY.append(")");
		   return SQL_QUERY.toString();
	   }
	   
	   public int getSimpleSearchCount(long siteId, String keyword,List<Long> locationsIds, double latitude, double longitude, int distance, String distanceUnit){
		   int count = 0;
		   String SQL_QUERY = generateSimpleSearchQuery(false,siteId, keyword, locationsIds, latitude, longitude, distance, distanceUnit);
		   Session session = HibernateUtil.getCurrentSession();
		   SQLQuery query = session.createSQLQuery(SQL_QUERY);
		   Object pObject = query.uniqueResult();
	        if(pObject != null && (pObject instanceof Number))
	        	count = ((Number)pObject).intValue();
		   return count;
	   }
	   
	   public List<CandidateSearch> getSimpleSearch(long siteId, String keyword,List<Long> locationsIds, double latitude, double longitude, int distance, String distanceUnit, int orderBy, int fromIndex, int toIndex){
		   String SQL_QUERY = generateSimpleSearchQuery(true,siteId, keyword, locationsIds, latitude, longitude, distance, distanceUnit);
		   StringBuffer ORDER_BY =new StringBuffer("SELECT * FROM (");
		   
		   switch(orderBy){
			   	case 1:
			   		ORDER_BY.append(SQL_QUERY + ")  ORDER BY LAST_RESUME_DATE ASC");
			   		break;
			   	case 2:
			   		ORDER_BY.append(SQL_QUERY + ")  ORDER BY LAST_RESUME_DATE DESC");
					break;
			   	case 3:
			   		ORDER_BY.append(SQL_QUERY + ")  ORDER BY UPPER(FIRSTNAME || LASTNAME) ASC");
			   		break;
				case 4:
					ORDER_BY.append(SQL_QUERY + ")   ORDER BY UPPER(FIRSTNAME || LASTNAME) DESC");
					break;
				default:
					ORDER_BY.append(SQL_QUERY + ")  ORDER BY LAST_RESUME_DATE ASC");
					break;
				
		   }
		   
		   StringBuffer PAGING_QUERY = new StringBuffer(	" SELECT  /*+ FIRST_ROWS */ *" +
		   			" FROM (" +
	   				" SELECT B.*, ROWNUM RNUM" +
	   				" FROM (");
		   PAGING_QUERY.append(ORDER_BY + ") B ) C WHERE RNUM BETWEEN  " + fromIndex + " AND " + toIndex);
		   
		   		   
		   Session session = HibernateUtil.getCurrentSession();
		   SQLQuery query = session.createSQLQuery(PAGING_QUERY.toString()).addEntity(com.manpower.portal.mpnet.hbn.beans.CandidateSearch.class);		   
		   
		   return query.list();
		   
	   }

	public CandidateSearchFavorite addFavoriteCandidate(long candidateId, long recruiterId, String comment) {
		CandidateSearchFavorite entity = new CandidateSearchFavorite();
		entity.setCandidateId(candidateId);
		entity.setRecruiterId(recruiterId);
		entity.setComment(comment);
		return (CandidateSearchFavorite)makePersistent(entity);
	}

	public List<CandidateSearch> getAllFavoriteCandidatesByRecruiter(long recruiterId, int orderByColumn, int fromIndex, int toIndex) {
		Session session = HibernateUtil.getCurrentSession();
		String orderBy = "";
		switch (orderByColumn) {
		case 1:
			orderBy += "ORDER BY upper(firstname||middlename||lastname) ASC";
			break;
		case 2:
			orderBy += "ORDER BY upper(firstname||middlename||lastname) DESC";
			break;
		default:
			orderBy += "ORDER BY upper(firstname||middlename||lastname) ASC";
			break;
		}
		SQLQuery query = session.createSQLQuery(FAVORITE_SQL + orderBy);
		query.setParameter(0, new Long(recruiterId));
		query.setFirstResult(fromIndex);
		query.setMaxResults(toIndex);
		
		List<Object[]> resultSet = query.list();
		List<CandidateSearch> resultSearches = new ArrayList<CandidateSearch>(resultSet.size());
		for (Object[] set : resultSet) {
			CandidateSearch cs = new CandidateSearch();
			cs.setId(((BigDecimal)set[0]).longValue());
			cs.setCandidateId(((BigDecimal)set[1]).longValue());
			cs.setApplicantId(((BigDecimal)set[2]).longValue());
			cs.setFirstName((String)set[3]);
			cs.setMiddleName((String)set[4]);
			cs.setLastName((String)set[5]);
			cs.setEmail((String)set[6]);
			cs.setComment((String)set[7]);
			resultSearches.add(cs);
		}
		return resultSearches;
	}

	public int getAllFavoriteCandidatesByRecruiterCount(long recruiterId) {
		/*
		Session session = HibernateUtil.getCurrentSession();
		Criteria query = session.createCriteria(CandidateSearchFavorite.class);
		query.add(Restrictions.eq("recruiterId", recruiterId));
		query.setProjection(Projections.countDistinct("id"));
		Integer rowCount = (Integer) query.uniqueResult();
		return rowCount.intValue();
		*/
		
		int count = 0;
		Session session = HibernateUtil.getCurrentSession();
		SQLQuery query = session.createSQLQuery(FAVORITE_COUNT_SQL);
		query.setParameter(0, new Long(recruiterId));
		Object pObject = query.uniqueResult();
        
		if(pObject != null && pObject instanceof Number) {
        	count = ((Number) pObject).intValue();
        }
        
        return count;
	}
	
	public boolean favoriteCandidateExists(long recruiterId, long candidateId) {
		int count = 0;
		Session session = HibernateUtil.getCurrentSession();
		SQLQuery query = session.createSQLQuery(FAVOURITE_EXISTS_SQL);
		query.setParameter(0, new Long(candidateId));
		query.setParameter(1, new Long(recruiterId));
		Object pObject = query.uniqueResult();
        if(pObject != null && (pObject instanceof Number))
        	count = ((Number)pObject).intValue();
        return count > 0;
	}

	public List<CandidateSearch> getPowerSearchApplicantsByIdList(List<Long> idList){
		Session session = HibernateUtil.getCurrentSession();
		Criteria query = session.createCriteria(CandidateSearch.class);
		List <CandidateSearch> result =  query.add(Restrictions.in("id", idList)).list();
		return result;
		
	}

	public void deleteFavoriteCandidate(long candidateId, long recruiterId) {
		Session session = HibernateUtil.getCurrentSession();
		SQLQuery query = session.createSQLQuery(FAVODITE_DELETE_SQL);
		query.setParameter(0, new Long(candidateId));
		query.setParameter(1, new Long(recruiterId));
		query.executeUpdate();
	}
}
