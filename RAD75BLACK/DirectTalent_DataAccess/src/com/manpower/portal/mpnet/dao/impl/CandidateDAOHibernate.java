/*
 * Created on Jan 16, 2006
 *
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StringType;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateDAO;
import com.manpower.portal.mpnet.hbn.beans.BranchTable;
import com.manpower.portal.mpnet.hbn.beans.Candidate;
import com.manpower.portal.mpnet.hbn.beans.CandidateProfileSearch;
import com.manpower.portal.mpnet.util.ApplicationConstants;
import com.manpower.portal.mpnet.util.Constants;
import com.manpower.portal.mpnet.util.TextUtils;

/**
 * @author jsingh
 *  
 */
public class CandidateDAOHibernate extends GenericHibernateDAO implements
		CandidateDAO {
	
	// hardcoded value for contract type allTypes
	private static String allTypes="ALLTYPES";
    
	private static final String UNKNOWN_CANDIDATE_SEARCH_QUERY=" select "+
    "this_.ID, "+
    "this_.SITE_ID "+
    "FROM CANDIDATES this_ ";

	private static final String KNOWN_CANDIDATE_SEARCH_QUERY=" select " +
	"this_.ID, "+
    "this_.SITE_ID "+
    "from CANDIDATES this_ ";
    
	/**
	 * Constructor
	 * 
	 * @param persistentClass
	 * @param session
	 */
	public CandidateDAOHibernate(Session session) {
		super(Candidate.class, session);
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findAll()
	 */
	public List findAll() {

		return HibernateUtil.getCurrentSession()
				.createCriteria(Candidate.class).list();
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findByID(java.io.Serializable)
	 */
	public Object findByID(Serializable id) {
		Object candidate = HibernateUtil.getSessionFactory()
				.getCurrentSession().get(Candidate.class, id);

		return candidate;
	}

	public Object findByEmail(String email) {
		return findByEmail(email, Constants.CANDIDATE_IS_ACTIVE_FLAG);
	}

	/**
	 * Find Candidate by email address
	 * 
	 * @return Candidate object
	 */
	public Object findByEmail(String email, String status) {
		Criteria query = HibernateUtil.getCurrentSession()
				.createCriteria(Candidate.class);
		query.add(Restrictions.eq("email", email));
		if (status != null && (status = status.trim()).length() > 0)
			query.add(Restrictions.eq("status", status));

		List results = query.list();
		if (results != null && results.size() > 0)
			return results.get(0);
		//Object candidate = query.uniqueResult();

		return null;

	}

	/**
	 * Check if an email is already registered
	 * 
	 * @return True for existing email, otherwise false
	 */
	public boolean isCandidateRegistered(String email) {
		Criteria query = HibernateUtil.getCurrentSession()
				.createCriteria(Candidate.class);
		query.add(Restrictions.eq("email", email));
		//Object candidate = query.uniqueResult();
		List candidates = query.list();
		if (candidates != null && candidates.size() > 0)
			return true;
		else
			return false;
	}

	/**
	 * Find Candidate by active email address
	 * 
	 * @return Candidate object
	 */
	public Object findByActiveEmail(String email, long siteId) {
//		Object candidate = findByEmail(email, "A");

		Criteria query = HibernateUtil.getCurrentSession().createCriteria(Candidate.class);
		
		//add candidate email restriction
		query.add(Restrictions.eq("email", email).ignoreCase());

		//add candidate status restriction
		query.add(Restrictions.eq("status", "A"));
		
		//add candidate siteId restriction
		query.add(Restrictions.eq("siteId", new Long(siteId)));

		List results = query.list();
		
		if(results != null && results.size() > 0) {
			return results.get(0);
		}
		
		return null;
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.CandidateDAO#findKnownCandidates(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.util.Date)
	 */
	public List findKnownCandidates(Candidate candidate, String primaryPhone, int fromIndex, int toIndex, int sortOrder, boolean desc) {

		String queryString = getKnownCandidateSearchQuery(candidate, primaryPhone);
		
		StringBuilder orderBy = new StringBuilder(" order by ");
		String orderDesc = desc ? "desc" : "asc";
		switch (sortOrder) {
		case 1:
		{
			orderBy.append("othercandi1_.LAST_LOGIN_DATE ");
			orderBy.append(orderDesc);
		}
			break;
		case 2:
		{
			orderBy.append("lower(this_.FIRSTNAME) ");
			orderBy.append(orderDesc);
			orderBy.append(", lower(this_.MIDDLENAME) ");
			orderBy.append(orderDesc);
			orderBy.append(", lower(this_.LASTNAME) ");
			orderBy.append(orderDesc);
		}
			break;
		case 3:
		{
			orderBy.append("lower(br_.BRANCHNAME) ");
			orderBy.append(orderDesc);
		}
			break;
		default:
		{
			orderBy.append("othercandi1_.LAST_LOGIN_DATE ");
			orderBy.append(orderDesc);
		}
			break;
		}
		
		queryString += orderBy.toString();
//		System.out.println("query is: " + queryString);
		SQLQuery query = populateParameters(queryString, candidate, primaryPhone);
//		System.out.println("populated query is: " + query.getQueryString());
		query.setFirstResult(fromIndex);
		query.setMaxResults(toIndex - fromIndex);
		return query.list();
	}
	
	public int findKnownCandidatesCount(Candidate candidate, String primaryPhone) {
		StringBuffer queryString = new StringBuffer("select count(*) from (");
		queryString.append(getKnownCandidateSearchQuery(candidate, primaryPhone));
		queryString.append(")");
//		System.out.println("query is: " + queryString);
		SQLQuery query = populateParameters(queryString.toString(), candidate, primaryPhone);
//		System.out.println("populated query is: " + query.getQueryString());
		Object obj = query.uniqueResult();
		BigDecimal count = (BigDecimal)obj;
		return count.intValue();
	}
	
	public List<Long> findKnownCandidateIds(Candidate candidate, String primaryPhone) {
		List<Long> candidateIds = new ArrayList<Long>();
		
		StringBuffer queryString = new StringBuffer("select ID from (");
		queryString.append(getKnownCandidateSearchQuery(candidate, primaryPhone));
		queryString.append(")");
//		System.out.println("query is: " + queryString);
		SQLQuery query = populateParameters(queryString.toString(), candidate, primaryPhone);
//		System.out.println("populated query is: " + query.getQueryString());
		List<BigDecimal> results = query.list();
		if (results!=null && !results.isEmpty()){
			for (BigDecimal bigDecimal : results) {
				candidateIds.add(bigDecimal.longValue());
			}
		}
		return candidateIds;
	}
	
	
	
	private SQLQuery populateParameters(String queryString, Candidate candidate, String primaryPhone) {
		SQLQuery query = HibernateUtil.getCurrentSession().createSQLQuery(queryString);
		int i = 0;
		query.setLong(i, candidate.getSiteId());
		i++;
		if (!TextUtils.isStringNullOrEmpty(candidate.getEmail())) {
			query.setString(i, candidate.getEmail());
			i++;
		}
		
		if (!TextUtils.isStringNullOrEmpty(candidate.getFirstName())) {
			query.setString(i, candidate.getFirstName());
			i++;
		}
		
		if (!TextUtils.isStringNullOrEmpty(candidate.getMiddleName())) {
			query.setString(i, candidate.getMiddleName());
			i++;
		}
		
		if (!TextUtils.isStringNullOrEmpty(candidate.getLastName())) {
			query.setString(i, candidate.getLastName());
			i++;
		}
		
		if (!TextUtils.isStringNullOrEmpty(candidate.getNationalNumber())) {
			query.setString(i, candidate.getNationalNumber());
			i++;
		}
		
		if (candidate.getBirthdate() != null) {
			query.setDate(i, candidate.getBirthdate());
			i++;
		}
		
		if (!TextUtils.isStringNullOrEmpty(primaryPhone)) {
			query.setString(i, primaryPhone);
			i++;
		}
//		System.out.println("Parameters set: " + i);
		return query;
	}
	
	private String getKnownCandidateSearchQuery(Candidate candidate, String primaryPhone) {
		
		StringBuffer query = new StringBuffer(KNOWN_CANDIDATE_SEARCH_QUERY);
		//joining with other candidate details
		query.append(", OTHER_CANDIDATE_DETAILS othercandi1_ ");
		//joining with branches
		query.append(", BRANCHES br_ ");
		
		if (!TextUtils.isStringNullOrEmpty(primaryPhone)) {
			query.append(", PHONES ph_ ");
		}
		
		query.append(" where this_.SITE_ID = ? ");
		query.append(" and this_.STATUS = 'A' ");
		query.append(" and this_.ID = othercandi1_.CANDIDATE_ID ");
		query.append(" and br_.ID = this_.BRANCH_ID ");
		
		if (!TextUtils.isStringNullOrEmpty(candidate.getEmail())) {
			query.append(" and UPPER(this_.EMAIL) = UPPER(?) ");
		}
		
		if (!TextUtils.isStringNullOrEmpty(candidate.getFirstName())) {
			query.append(" and UPPER(this_.FIRSTNAME) = UPPER(?) ");
		}
		
		if (!TextUtils.isStringNullOrEmpty(candidate.getMiddleName())) {
			query.append(" and UPPER(this_.MIDDLENAME) = UPPER(?) ");
		}
		
		if (!TextUtils.isStringNullOrEmpty(candidate.getLastName())) {
			query.append(" and UPPER(this_.LASTNAME) = UPPER(?)");
		}
		
		if (!TextUtils.isStringNullOrEmpty(candidate.getNationalNumber())) {
			query.append(" and this_.NATIONALNUMBER = ? ");
		}
		
		if (candidate.getBirthdate() != null) {
			query.append(" and this_.BIRTHDATE = ? ");
		}
		
		if (!TextUtils.isStringNullOrEmpty(primaryPhone)) {
			query.append(" and ph_.CANDIDATE_ID = this_.ID ");
			query.append(" and ph_.PRIMARY = 'Y' ");
			query.append(" and ph_.PHONENUMBER = ? ");
		}
		return query.toString();
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.CandidateDAO#findKnownCandidates(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.util.Date)
	 */
	public List findUnKnownCandidates(String siteId, CandidateProfileSearch profileSearchBean, int fromIndex, int toIndex, int sortOrder, boolean desc) {

		String query = generateUnknownCandidateQuery(siteId, profileSearchBean);
		
		StringBuilder orderBy = new StringBuilder(" order by ");
		String orderDesc = desc ? "desc" : "asc";
		switch (sortOrder) {
		case 1:
		{
			orderBy.append("trunc(othercandi1_.LAST_LOGIN_DATE) ");
			orderBy.append(orderDesc);
		}
			break;
		case 2:
		{
			orderBy.append("lower(this_.FIRSTNAME) ");
			orderBy.append(orderDesc);
			orderBy.append(", lower(this_.MIDDLENAME) ");
			orderBy.append(orderDesc);
			orderBy.append(", lower(this_.LASTNAME) ");
			orderBy.append(orderDesc);
		}
			break;
		case 3:
		{
			orderBy.append("lower(br_.BRANCHNAME) ");
			orderBy.append(orderDesc);
		}
			break;
		default:
		{
			orderBy.append("trunc(othercandi1_.LAST_LOGIN_DATE) ");
			orderBy.append(orderDesc);
		}
			break;
		}
		
		query += orderBy.toString();
		
        Session session = HibernateUtil.getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(query.toString());
        sqlQuery.setFirstResult(fromIndex);
        sqlQuery.setMaxResults(toIndex - fromIndex);
        List results = sqlQuery.list();
        return results;
	}
	
	public int findUnKnownCandidatesCount(String siteId, CandidateProfileSearch profileSearchBean) {
		StringBuilder query = new StringBuilder("select count(*) from ( ");
		query.append(generateUnknownCandidateQuery(siteId, profileSearchBean));
		query.append(")");
		
		Session session = HibernateUtil.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery(query.toString());
		Object obj = sqlQuery.uniqueResult();
		BigDecimal count = (BigDecimal)obj;
		return count.intValue();
	}
	
	public List<Long> findUnKnownCandidatesIds(String siteId, CandidateProfileSearch profileSearchBean) {
		List<Long> candidateIds = new ArrayList<Long>();
		
		StringBuilder query = new StringBuilder("select ID from ( ");
		query.append(generateUnknownCandidateQuery(siteId, profileSearchBean));
		query.append(")");
		
		Session session = HibernateUtil.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery(query.toString());
		List<BigDecimal> result= sqlQuery.list();
		if(result!=null && !result.isEmpty()){
			for (int i = 0; i < result.size(); i++) {
				candidateIds.add(result.get(i).longValue());
			}
		}
		return candidateIds;
	}
	
	
	
	private String generateUnknownCandidateQuery(String siteId, CandidateProfileSearch profileSearchBean) {
		StringBuffer query=new StringBuffer(UNKNOWN_CANDIDATE_SEARCH_QUERY);
		
		//joining with other candidate details
		query.append(" INNER JOIN  CANDIDATEPROFILESTATUS STAT ON this_.ID=STAT.CANDIDATEID AND STAT.STATUS = 1");
		query.append(" INNER JOIN  OTHER_CANDIDATE_DETAILS othercandi1_ ON this_.ID=othercandi1_.CANDIDATE_ID");
		
		//joining with branches
		query.append(" INNER JOIN BRANCHES br_ ON br_.ID = this_.BRANCH_ID");
		
		
		if (profileSearchBean.ifJoinCandidateSkills()) 
		{
			query.append(" INNER JOIN CANDIDATESKILLS candidates1_ ON this_.ID=candidates1_.CANDIDATE_ID");
		}
		
		if (profileSearchBean.ifJoinCandidateEducation()) 
		{
			query.append(" INNER JOIN CANDIDATEEDUCATIONS candidatee1_ ON candidatee1_.CANDIDATE_ID = this_.id");
		}
		
		if (profileSearchBean.ifJoinCandidateJobHistory()) 
		{
			query.append(" INNER JOIN CANDIDATEJOBHISTORIES candidatej1_ ON this_.ID=candidatej1_.CANDIDATE_ID");
		}
		
		if (profileSearchBean.ifJoinPreferredWorkLocation()) 
		{
			query.append(" INNER JOIN CANDIDATEPREFERREDLOCATIONS preferredl3_ ON this_.ID=preferredl3_.CANDIDATE_ID");
		}
		
		if (profileSearchBean.ifJoinCandidatePreferences()) 
		{
			query.append(" INNER JOIN CANDIDATEPREFERENCES candidatep1_ ON this_.ID=candidatep1_.CANDIDATE_ID");
		}
		
		if (profileSearchBean.isJobTitleProvided()) 
		{
			query.append(" INNER JOIN CANDIDATEJOBTITLES ON this_.ID=CANDIDATEJOBTITLES.CANDIDATE_ID");			
		}
		
		if (profileSearchBean.isIndustrySectorProvided()) 
		{
			query.append(" INNER JOIN CANDIDATEOCCUPATIONS O ON this_.ID = O.CANDIDATE_ID");
		}
		
		query.append(" where this_.SITE_ID="+siteId );
		query.append(" and this_.STATUS='A' ");
             


		if (profileSearchBean.isResidenceStatusProvided()) {
			query.append(" and RESIDENCESTATUS='"+profileSearchBean
					.getResidenceStatus()+"'");
		}

		if (profileSearchBean.isMotherTongueProvided()) {
			query.append(" and NATIVE_LANGUAGE='"+profileSearchBean
					.getMotherTongue()+"'");
		}

		if (profileSearchBean.isWorkPermitProvided()) {
			query.append(" and WORKPERMITS='"+ profileSearchBean
					.getWorkPermit()+"'");
		}

		if (profileSearchBean.isPreferredBranchProvided()) {
			query.append(" and BRANCH_ID="+profileSearchBean
					.getPreferredBranch());
		}

		if (profileSearchBean.getBeginRegistrationDate() != null
				&& profileSearchBean.getEndRegistrationDate() != null) {
			query.append(" and trunc(this_.CREATED_ON) between to_date('"+getDateString(profileSearchBean
						.getBeginRegistrationDate(),"MM/dd/yyyy")+ "','MM/dd/yyyy')"+" and to_date('"+getDateString(profileSearchBean
						.getEndRegistrationDate(),"MM/dd/yyyy")+ "','MM/dd/yyyy')");
		}        

		if (profileSearchBean.getSkills() != null
					&& profileSearchBean.getSkills().size() > 0) {
				String skills=profileSearchBean
				.getSkills().toString();
				query.append(" and candidates1_.SKILLS_ID in ("+skills.substring(1, skills.length()-1) +")");
		}
		

		if (profileSearchBean.isWorkExperienceProvided()){
				query.append(" and lower(candidatej1_.COMPANY) LIKE lower('%"+
						profileSearchBean.getWorkExperience()+"%')");
		}
		
		if (profileSearchBean.ifJoinOtherCandidateDetails()) {
			
			if (profileSearchBean.getBeginLastDateCandidateLoggedIn() != null
					&& profileSearchBean.getEndLastDateCandidateLoggedIn() != null) {
				query.append(" and trunc(othercandi1_.LAST_LOGIN_DATE) between to_date('"+getDateString(profileSearchBean
						.getBeginLastDateCandidateLoggedIn(),"MM/dd/yyyy")+ "','MM/dd/yyyy')"+" and to_date('"+getDateString(profileSearchBean
						.getEndLastDateCandidateLoggedIn(),"MM/dd/yyyy")+ "','MM/dd/yyyy')");
			}
		}

		if (profileSearchBean.ifJoinPreferredWorkLocation()) {

			if (profileSearchBean.getPreferredWorkLocation() != null
					&& profileSearchBean.getPreferredWorkLocation().size() > 0) {
				String locations=profileSearchBean
				.getPreferredWorkLocation().toString();
			    query.append(" and preferredl3_.LOCATION_ID in ("+
			    		locations.substring(1,locations.length()-1)+")");
			}
		}
		
		if (profileSearchBean.ifJoinCandidateEducation()) {
			if (profileSearchBean.getEducationLevel() != null && profileSearchBean.getEducationLevel().trim().length() > 0) {
				query.append(" and candidatee1_.DEGREECODE='");
				query.append(profileSearchBean.getEducationLevel());
				query.append("'");
			}
		}

		if (profileSearchBean.ifJoinCandidatePreferences()) {
			
            if (profileSearchBean.isPositionTypeProvided()) {
				if(!profileSearchBean.getPositionType().equals(allTypes)){
					query.append(" and (candidatep1_.CONTRACTTYPE='"+profileSearchBean.getPositionType()+"' or candidatep1_.CONTRACTTYPE='"+allTypes+"')");
				}
			}
            
			if (profileSearchBean.isWillingToRelocateWithinCountryProvided()) {
				query.append(" and candidatep1_.RELOCATECOUNTRY='"+
						profileSearchBean.getWillingToRelocateWithinCountry()+"'");
			}
			if (profileSearchBean.isWillingToRelocateToAnotherCountryProvided()) {
				query.append(" and candidatep1_.RELOCATEINTERNATIONAL='"+profileSearchBean
								.getWillingToRelocateToAnotherCountry()+"'");
			}

			if (profileSearchBean.isJobTitleProvided()) {
				
				query.append(" and lower(CANDIDATEJOBTITLES.JOB_TITLE_FREE_FORM)=lower('"+profileSearchBean.getJobTitle()+"')");
			}
			
			if (profileSearchBean.getBeginCandidateStartDate() != null
					&& profileSearchBean.getEndCandidateStartDate() != null) {
				query.append(" and trunc(candidatep1_.STARTDATE) between to_date('"+getDateString(profileSearchBean
						.getBeginCandidateStartDate(),"MM/dd/yyyy")+ "','MM/dd/yyyy')"+" and to_date('"+getDateString(profileSearchBean
						.getEndCandidateStartDate(),"MM/dd/yyyy")+ "','MM/dd/yyyy')");
			}
				
			if(profileSearchBean.isIndustrySectorProvided())
			{
				Iterator sectors=profileSearchBean.getIndustrySector().iterator();
				String comma="";
			    StringBuffer sqlIndustrySector=new StringBuffer(" and O.OCCUPATION_ID IN (");
				while(sectors.hasNext())
				{
				    String sector=(String)sectors.next();
				    sqlIndustrySector.append(comma+"'"+sector+"'");
				    comma=" , ";
				}
				sqlIndustrySector.append(")");
			    query.append(sqlIndustrySector.toString());
			}
		}
	
		return query.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.interfaces.CandidateDAO#findUnconfirmedCandidateByEmail(java.lang.String)
	 */
	public Object findUnconfirmedCandidateByEmail(String email) {
		Criteria query = HibernateUtil.getCurrentSession()
				.createCriteria(Candidate.class);

		query.add(Restrictions.eq("email", email));

		query.add(Restrictions.eq("status",
				Constants.CANDIDATE_IS_WAITING_FOR_CONFIRMATION_FLAG));
		Object candidate = query.uniqueResult();

		return candidate;
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.interfaces.CandidateDAO#findCandidatesByBranch(com.manpower.portal.mpnet.hbn.beans.BranchTable)
	 */
	public List findCandidatesByBranch(BranchTable branch) {
		Session session = HibernateUtil.getCurrentSession();
        Query query = session.createQuery("from Candidate where branchId = :branchId");
        query.setParameter("branchId", new Long(branch.getId()));
        return query.list();
	}

	/**
	 * Find Candidate by email address
	 * 
	 * @return Candidate object
	 */
	public Object findByExistingEmail(String email)	{
		
		Criteria query = HibernateUtil.getCurrentSession().createCriteria(Candidate.class);
		
		query.add(Restrictions.eq("email", email));

		query.add(Restrictions.or(Restrictions.eq("status", Constants.CANDIDATE_IS_ACTIVE_FLAG),Restrictions.eq("status", Constants.CANDIDATE_IS_WAITING_FOR_CONFIRMATION_FLAG)));
		Object candidate = query.uniqueResult();
		
		return candidate;
		
	}  	
	
	/**
	 * Find candidate by correspondence email
	 * checking for:
	 * 1)is candidate activated
	 * 2)is candidate waiting for confirmation
	 */
	public Object findByExistingCorrespondenceEmail(String email)
	{
		Criteria query = HibernateUtil.getCurrentSession().createCriteria(Candidate.class);
		
		query.add(Restrictions.eq("correspondEmail", email).ignoreCase());

		query.add(Restrictions.or(Restrictions.eq("status", Constants.CANDIDATE_IS_ACTIVE_FLAG),Restrictions.eq("status", Constants.CANDIDATE_IS_WAITING_FOR_CONFIRMATION_FLAG)));

		List candidates = query.list();
		if (candidates==null || candidates.isEmpty()){
			return null;
		}
		
		return candidates.get(0);		
	}
	
    private String getDateString(Date date, String dateFormat)
    {
        SimpleDateFormat fmt = new SimpleDateFormat(dateFormat);
        return fmt.format(date);
    }
    
    public List getCandidatesByIDs(List ids, int fromIndex, int toIndex, int orderBy, boolean ascending){
    	List results = null;

    	if(ids != null && ids.size() > 0){
    		StringBuffer idList = new StringBuffer();	
    		for(int i=0; i<ids.size(); i++){
    			if(i != 0)
    				idList.append(",");
    			idList.append(ids.get(i));
    			
    		}
    		StringBuffer sqlQuery = new StringBuffer("SELECT ID, CANDIDATENAME, CORRESPONDEMAIL FROM (");
    		sqlQuery.append(" SELECT ID, FIRSTNAME || ' ' || LASTNAME CANDIDATENAME, C.CORRESPONDEMAIL, ROWNUM RNUM FROM CANDIDATES C WHERE ID IN (");
    		sqlQuery.append(idList);
    		sqlQuery.append(")");
    		if(orderBy > 0){
    			switch (orderBy){
    			case ApplicationConstants.SORT_BY_NAME:
    				sqlQuery.append(" ORDER BY (FIRSTNAME || ' ' || LASTNAME)");
    				break;
    			case ApplicationConstants.SORT_BY_EMAIL:
    				sqlQuery.append(" ORDER BY CORRESPONDEMAIL");
    				break;
    			default: break;
    			}
    			sqlQuery.append(ascending?" ASC": " DESC");
    		}
    		sqlQuery.append(") A WHERE RNUM BETWEEN ");
    		sqlQuery.append(fromIndex);
    		sqlQuery.append(" AND ");
    		sqlQuery.append(toIndex);
    		Session session = HibernateUtil.getCurrentSession();
    		results = session.createSQLQuery(sqlQuery.toString()).list();
        	 
    	}
    	
    	return results;
    }
}
