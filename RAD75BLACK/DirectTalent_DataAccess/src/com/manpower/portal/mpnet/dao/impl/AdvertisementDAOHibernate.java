/*
 * Created on Jan 23, 2006
 *
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.Type;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.ActiveAdvertDAO;
import com.manpower.portal.mpnet.dao.interfaces.AdvertisementDAO;
import com.manpower.portal.mpnet.hbn.beans.Advertisement;
import com.manpower.portal.mpnet.hbn.beans.AdvertisementContact;
import com.manpower.portal.mpnet.hbn.beans.CandidateJobAgents;
import com.manpower.portal.mpnet.util.ApplicationConstants;
import com.manpower.portal.mpnet.util.Collections;
import com.manpower.portal.mpnet.util.Constants;
import com.manpower.portal.mpnet.util.MPNETAppConstants;
import com.manpower.portal.mpnet.util.TextUtils;


/**
 * @author jsingh
 *  
 */
public class AdvertisementDAOHibernate extends GenericHibernateDAO implements
		AdvertisementDAO {
    
    private static Logger logger = Logger.getLogger(AdvertisementDAOHibernate.class.getName());
    private static final String SYSDATE_STR = "TRUNC(SYSDATE)";
    private static final String ACTIVE_ADVERT_SQL = "TRUNC(SYSDATE) BETWEEN TRUNC(PUBLICATIONDATE) AND TRUNC(EXPIRATIONDATE)";
    private static final String EXPIRED_ADVERT_SQL = "TRUNC(EXPIRATIONDATE) < TRUNC(SYSDATE)";

	/**
	 * Constructor
	 * 
	 * @param persistentClass
	 * @param session
	 */
	public AdvertisementDAOHibernate(Session session) {
		super(Advertisement.class, session);
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
		Object adv = HibernateUtil.getCurrentSession().get(
				Advertisement.class, id);
		HibernateUtil.getCurrentSession().evict(adv);
		return adv;
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findByCustomQuery(java.lang.String,
	 *      java.util.Properties)
	 */
	public List findByCustomQuery(String query, Properties params) {
		return super.findByCustomQuery(query, params);
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findPageByCustomHQLQuery(java.lang.String,
	 *      java.util.Properties, int, int)
	 */
	public List findPageByCustomHQLQuery(String query, Properties params,
			int pageNumber, int pageSize) {

		
		return super.findPageByCustomHQLQuery(query, params, pageNumber,
				pageSize);
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.DAO#findPageByCustomSQLQuery(java.lang.String,
	 *      java.util.Properties, int, int, java.lang.String, java.lang.Class)
	 */
	public List findPageByCustomSQLQuery(String query, Properties params,
			int pageNumber, int pageSize, String entityAlias, Class entityName) {

		return super.findPageByCustomSQLQuery(query, params, pageNumber,
				pageSize, entityAlias, entityName);
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.AdvertisementDAO#getCountBySQLQuery(java.lang.String)
	 */
	public int getCountBySQLQuery(String query) {

		query = "select count(*) " + query;
		
		Integer count = (Integer) HibernateUtil.getSessionFactory()
				.getCurrentSession().createQuery(query).uniqueResult();

		return count.intValue();
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.AdvertisementDAO#getCountBySQLQuery(java.lang.String,
	 *      java.util.Properties)
	 */
	public int getCountBySQLQuery(String query, Properties params) {

		query = "select count(id) " + query;

		Query q = HibernateUtil.getCurrentSession()
				.createQuery(query);

		Iterator iter = params.keySet().iterator();
		while (iter.hasNext()) {
			String stKey = (String) iter.next();
			String stVal = (String) params.get(stKey);
			q.setString(stKey, stVal);
		}

		Long count = (Long) q.uniqueResult();

		return count.intValue();
	}

	public int findNumberOfAdvertsPerBranch(Long branchId) {
		Session session = HibernateUtil.getCurrentSession();
		Criteria query = session.createCriteria(Advertisement.class)
				.setProjection(Projections.rowCount()).createCriteria(
						"adContact").add(Restrictions.eq("branchId", branchId));
		return ((Integer) query.uniqueResult()).intValue();
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.AdvertisementDAO#findByBranchID(long)
	 */
	public List findByBranchID(long branchID) {
		Session session = HibernateUtil.getCurrentSession();
		Criteria query = session.createCriteria(Advertisement.class);

		query.createCriteria("adContact").add(
				Restrictions.eq("branchId", new Long(branchID)));

		query.addOrder(Order.desc("updatedOn"));

		return query.list();
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.AdvertisementDAO#find(long,
	 *      java.util.Date, java.util.Date, long, java.lang.String)
	 */
	public List find(long branchID,Date beginDate, Date endDate,
			long contactID, String jobTitle, String filter, String advertID, String orderBy, boolean ascending,int pageNumber, int pageSize) {
	
		Session session = HibernateUtil.getCurrentSession();
		Criteria query = session.createCriteria(Advertisement.class);
		Criteria contactQuery = query.createCriteria("adContact", "contacts");
		
		if (branchID != 0) {
			contactQuery.add(Restrictions.eq("contacts.branchId", new Long(branchID)));
		}
		
		if(advertID != null && !MPNETAppConstants.DEFAULT_SELECTED_VALUE.equals(advertID.trim())){
		
			query.add(Restrictions.eq("referenceNumber", advertID));
			
		}else{
			
			if(MPNETAppConstants.ADVERT_STATUS_ACTIVE.equals(filter)){
				query.add(Restrictions.sqlRestriction(ACTIVE_ADVERT_SQL));// publish date <= today
			}else if(MPNETAppConstants.ADVERT_STATUS_EXPIRED.equals(filter)){
				query.add(Restrictions.sqlRestriction(EXPIRED_ADVERT_SQL));
			}
			if (beginDate != null && endDate != null) {
					query.add(Restrictions.between("updatedOn", beginDate, endDate));
			}	
			if (jobTitle != null && !MPNETAppConstants.DEFAULT_SELECTED_VALUE.equals(jobTitle.trim())) {
				if("%".equals(jobTitle.trim())){
					String SQL = " jobtitle LIKE '%\\%%' ESCAPE '\\' ";
					query.add(Restrictions.sqlRestriction(SQL));
				}else{
					StringBuffer jt = new StringBuffer();
					jt.append("%");
					jt.append(jobTitle.trim());
					jt.append("%");
					query.add(Restrictions.ilike("jobTitle", jt.toString()));
				}
				
			}

			if (contactID != 0) {
					contactQuery.add(Restrictions.eq("contacts.id", new Long(contactID)));
			}
			
		}
		
		if(ApplicationConstants.ORDER_BY_JOB_TITLE.equals(orderBy)){
			Order order = ascending?Order.asc("jobTitle"): Order.desc("jobTitle");
			query.addOrder(order.ignoreCase());

		}else if(ApplicationConstants.ORDER_BY_EXPIRE_DATE.equals(orderBy)){
			if(ascending){
				query.addOrder(Order.asc("expirationDate"));
			}else{
				query.addOrder(Order.desc("expirationDate"));
			}
		}else if(ApplicationConstants.ORDER_BY_ADVERT_REFERENCE.equals(orderBy)){
			Order order = ascending?Order.asc("referenceNumber"): Order.desc("referenceNumber");
			query.addOrder(order.ignoreCase());
		}else if(ApplicationConstants.ORDER_BY_RECRUITER.equals(orderBy)){
			
				Order order = ascending?Order.asc("contacts.contactName"): Order.desc("contacts.contactName");
				query.addOrder(order.ignoreCase());

		}else {
			if(ascending){
				query.addOrder(Order.asc("updatedOn"));
			}else{
				query.addOrder(Order.desc("updatedOn"));
			}
		}
		List elements = query.setFirstResult(pageNumber * pageSize).setMaxResults(
				pageSize).list();
		return elements;
	}

	
	public long getTotalCount(long branchID, Date beginDate, Date endDate,
			long contactID, String jobTitle, String filter, String advertID) {
		Session session = HibernateUtil.getCurrentSession();
		Criteria query = session.createCriteria(Advertisement.class);
		Criteria contactQuery = query.createCriteria("adContact", "contacts");
		query.setProjection(Projections.count("id"));
		
		if(advertID != null && !MPNETAppConstants.DEFAULT_SELECTED_VALUE.equals(advertID.trim())){
			
				query.add(Restrictions.eq("referenceNumber", advertID));
				if (branchID != 0) {
					contactQuery.add(Restrictions.eq("contacts.branchId", new Long(branchID)));
				}
				
		}else{
		
			if(MPNETAppConstants.ADVERT_STATUS_ACTIVE.equals(filter)){
				query.add(Restrictions.sqlRestriction(ACTIVE_ADVERT_SQL));// publish date <= today
			}else if(MPNETAppConstants.ADVERT_STATUS_EXPIRED.equals(filter)){
				query.add(Restrictions.sqlRestriction(EXPIRED_ADVERT_SQL));
			}
			
			if (beginDate != null && endDate != null) {
					query.add(Restrictions.between("updatedOn", beginDate, endDate));
			}	
			
			if (jobTitle != null && !MPNETAppConstants.DEFAULT_SELECTED_VALUE.equals(jobTitle.trim())) {
					if("%".equals(jobTitle.trim())){
						String SQL = " jobtitle LIKE '%\\%%' ESCAPE '\\' ";
						query.add(Restrictions.sqlRestriction(SQL));
					}else{
						StringBuffer jt = new StringBuffer();
						jt.append("%");
						jt.append(jobTitle.trim());
						jt.append("%");
						query.add(Restrictions.ilike("jobTitle", jt.toString()));
					}
					
			}

			
	
			if(branchID > 0)
				contactQuery.add(Restrictions.eq("branchId", new Long(branchID)));
	
			if (contactID > 0) {
					contactQuery.add(Restrictions.eq("id", new Long(contactID)));
			}
			
		}
		
		Integer count = (Integer)query.uniqueResult();
		
		return count.longValue();
		
	}
	
	public List<Long> getAllAdvertIds(long branchID, Date beginDate, Date endDate,
			long contactID, String jobTitle, String filter, String advertID) {
		Session session = HibernateUtil.getCurrentSession();
		Criteria query = session.createCriteria(Advertisement.class);
		Criteria contactQuery = query.createCriteria("adContact", "contacts");
		query.setProjection(Projections.projectionList().add(Projections.property("id")));
		
		if(advertID != null && !MPNETAppConstants.DEFAULT_SELECTED_VALUE.equals(advertID.trim())){
			
				query.add(Restrictions.eq("referenceNumber", advertID));
				if (branchID != 0) {
					contactQuery.add(Restrictions.eq("contacts.branchId", new Long(branchID)));
				}
				
		}else{
		
			if(MPNETAppConstants.ADVERT_STATUS_ACTIVE.equals(filter)){
				Date today = getCurrentDate();
				query.add(Restrictions.sqlRestriction(ACTIVE_ADVERT_SQL));// publish date <= today
			}else if(MPNETAppConstants.ADVERT_STATUS_EXPIRED.equals(filter)){
				Date today = getCurrentDate();
				//expire date < today
				query.add(Restrictions.sqlRestriction(EXPIRED_ADVERT_SQL));
			}
			
			if (beginDate != null && endDate != null) {
					query.add(Restrictions.between("updatedOn", beginDate, endDate));
			}	
			
			if (jobTitle != null && !MPNETAppConstants.DEFAULT_SELECTED_VALUE.equals(jobTitle.trim())) {
					if("%".equals(jobTitle.trim())){
						String SQL = " jobtitle LIKE '%\\%%' ESCAPE '\\' ";
						query.add(Restrictions.sqlRestriction(SQL));
					}else{
						StringBuffer jt = new StringBuffer();
						jt.append("%");
						jt.append(jobTitle.trim());
						jt.append("%");
						query.add(Restrictions.ilike("jobTitle", jt.toString()));
					}
					
			}

			
	
			if(branchID > 0)
				contactQuery.add(Restrictions.eq("branchId", new Long(branchID)));
	
			if (contactID > 0) {
					contactQuery.add(Restrictions.eq("id", new Long(contactID)));
			}
			
		}
		
		List<Long> countAdverts = query.list();
		return countAdverts;
		
	}
	
	public List getAdvertisements(CandidateJobAgents jobAgent,
			String localeLanguage, String advertId, int pageNumber, int pageSize) {
		List page = findPageByJobAgent(jobAgent, localeLanguage, advertId,
				"publicationDate", true, pageNumber, pageSize, true);

		return page;

	}

	private static final String SKILL_CRITERIA = "(contains({alias}.CANDIDATESKILLS, ?) > 0 or"
			+ " contains({alias}.JOBDESCRIPTION, ?) > 0 or"
			+ " contains({alias}.CANDIDATEPROFILE, ?) > 0 or"
			+ " contains({alias}.JOBTITLE, ?) > 0)";

	private static final String SKILL_AND_JOBTITLE_CRITERIA = "((contains({alias}.CANDIDATESKILLS, ?) > 0 or"
			+ " contains({alias}.JOBDESCRIPTION, ?) > 0 or"
			+ " contains({alias}.CANDIDATEPROFILE, ?) > 0) and"
			+ " contains({alias}.JOBTITLE, ?) > 0)";

	private static final Type[] QUADRUPLE_STRING_TYPES = new Type[] {
			Hibernate.STRING, Hibernate.STRING, Hibernate.STRING,
			Hibernate.STRING };

	
	private Criteria buildQuery(Criteria q, CandidateJobAgents jobAgent,
			String advertId) {
		q.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		q.createCriteria("sites").add(
				Restrictions.eq("id", new Long(jobAgent.getSiteId())));

		q.add(Restrictions.sqlRestriction(ACTIVE_ADVERT_SQL));
		
		String language = jobAgent.getJobLanguage();
		String value;

		if (advertId != null && (advertId = advertId.trim()).length() > 0) {
			q.add(Restrictions.sqlRestriction("UPPER(referenceNumber) = ?",	advertId.toUpperCase(), Hibernate.STRING));
            q.add(Restrictions.sqlRestriction("(?) in (SELECT L.LANGUAGE_CODE FROM ADVERTISEMENT_LANGUAGES L WHERE {alias}.ADVERTISEMENTID = L.LANGADVERT_ID)",language, Hibernate.STRING));
			return q;
		}

		
		value = jobAgent.getJobLocation();
		if (value != null && (value = value.trim()).length() > 0) {
			String loc[] = value.split(",");
			Long lid[] = new Long[loc.length];
			for(int i=0; i < loc.length; i++){
				lid[i] = Long.valueOf(loc[i]);
			}
			List locationNames = Collections.asList(lid);
			int size = locationNames != null ?locationNames.size():0;
			if (size > 0){
				q.createCriteria("locations").add(
						Restrictions.in("id", locationNames));

			}
		}

		value = jobAgent.getCandidateSkills();
		String jobTitle = jobAgent.getJobTitle();
		if ((value = TextUtils.processAndTrimText(value)) != null
				&& value.length() > 0) {
			String[] params = new String[] { value, value, value, value };
			String sql;
			if ((jobTitle = TextUtils.processAndTrimText(jobTitle)) != null
					&& jobTitle.length() > 0) {
				params[3] = jobTitle;
				sql = SKILL_AND_JOBTITLE_CRITERIA;
			} else
				sql = SKILL_CRITERIA;
			q.add(Restrictions.sqlRestriction(sql, params,
					QUADRUPLE_STRING_TYPES));
		} else if ((jobTitle = TextUtils.processAndTrimText(jobTitle)) != null
				&& jobTitle.length() > 0) {
			q.add(Restrictions.sqlRestriction(
					"contains({alias}.JOBTITLE, ?) > 0", jobTitle,
					Hibernate.STRING));
		}

		value = jobAgent.getJobIndustry();
		if (value != null && (value = value.trim()).length() > 0) {
			String[] industry = TextUtils.stringToArray(value);
			if (industry != null && industry.length > 0) {
				q.add(Restrictions.in("industrySector", industry));
			}
		}

		value = jobAgent.getContractType();
		if (value != null && (value = value.trim()).length() > 0
				&& !Constants.CONTRACT_TYPE_ALL.equals(value)) {
			String types[] = new String[] { value, Constants.CONTRACT_TYPE_ALL };
			q.add(Restrictions.in("contractType", types));
		}

		value = jobAgent.getHoursWorked();
		if (value != null && (value = value.trim()).length() > 0 && !Constants.CONTRACT_TYPE_ALL.equals(value)) {
			q.add(Restrictions.eq("hoursWorked", value));
		}

		value = jobAgent.getBusinessArea();
		if (value != null && (value = value.trim()).length() > 0) {
			q.add(Restrictions.eq("businessArea", value));
		}

		StringBuffer log = new StringBuffer("\n\t<SEARCH PARAMETERS ARE:>\n");
		Date date = getCurrentDate();
		log.append("\t\t <site = " + jobAgent.getSiteId() + "/>\n");
		log.append("\t\t <lang = " + language + "/>\n");
		log.append("\t\t <job title = " + jobAgent.getJobTitle() + "/>\n");
		log.append("\t\t <candidate skills = " + jobAgent.getCandidateSkills()
				+ "/>\n");
		log.append("\t\t <positionType = " + jobAgent.getContractType()
				+ "/>\n");
		log
				.append("\t\t <hours worked = " + jobAgent.getHoursWorked()
						+ "/>\n");
		log.append("\t\t <industry sectors = " + jobAgent.getJobIndustry()
				+ "/>\n");
		log.append("\t\t <locations = " + jobAgent.getJobLocation() + "/>\n");
		log.append("\t\t <current date = " + date + ", date format = "
				+ Constants.ORACLE_DATE_FORMAT + "/>\n");
		log.append("\t<SEARCH PARAMETERS ARE:>\n");

		if (logger.isDebugEnabled())
        {
			logger.debug("\nParameters are: \n" + log.toString());
			logger.debug("\nQuery is: \n" + q.toString());
        }
		q
				.add(Restrictions
						.sqlRestriction(
								"(?) in (SELECT L.LANGUAGE_CODE FROM ADVERTISEMENT_LANGUAGES L WHERE {alias}.ADVERTISEMENTID = L.LANGADVERT_ID)",
								language, Hibernate.STRING));

		return q;
	}

	private Date getCurrentDate() {
		return getDate(new Date());
	}

	private Date getDate(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		return calendar.getTime();
	}

	public List findSubListByJobAgent(CandidateJobAgents jobAgent,
			String localeLanguage, String advertID, String orderByProperty,
			boolean descending, int startIndex, int endIndex) {

		ActiveAdvertDAO activeAdvertsDAO = DAOFactoryHibernate.getDAOFactory().getActiveAdvertDAO();
		List elements = activeAdvertsDAO.getAdvertisementIDs(jobAgent, advertID, orderByProperty, descending, startIndex, endIndex);
		List foundAdverts = new ArrayList();
		Session session = HibernateUtil.getCurrentSession();
		if(elements != null && !elements.isEmpty()){
			Criteria query1 = session.createCriteria(Advertisement.class);
			query1.add(Restrictions.in("id", elements));
			if (descending) {
				query1.addOrder(Order.desc(orderByProperty));
			} else {
				query1.addOrder(Order.asc(orderByProperty));
			}
			foundAdverts = query1.list();
		}

		return foundAdverts;
	}

	public List findPageByJobAgent(CandidateJobAgents jobAgent,
			String localeLanguage, String advertID, String orderByProperty,
			boolean descnding, int pageNumber, int pageSize,
			boolean calculateTotalCount) {

		Session session = HibernateUtil.getCurrentSession();
		Criteria query = session.createCriteria(Advertisement.class);
		ProjectionList aList = Projections.projectionList();
		aList.add(Projections.property("publicationDate"));
		aList.add(Projections.id());
		query.setProjection(Projections.distinct(aList));
		query = buildQuery(query, jobAgent, advertID);
		
		if (descnding) {
			query.addOrder(Order.desc(orderByProperty));
		} else {
			query.addOrder(Order.asc(orderByProperty));
		}

		List elements = query.setFirstResult(pageNumber * pageSize)
		.setMaxResults(pageSize).list();

		List adverts = new ArrayList();
		if(elements != null && !elements.isEmpty()){
			Criteria query1 = session.createCriteria(Advertisement.class);
			query1.add(Restrictions.in("id", elements));
			if (descnding) {
				query1.addOrder(Order.desc(orderByProperty));
			} else {
				query1.addOrder(Order.asc(orderByProperty));
			}
			
			adverts = query1.list();
			setLanguage(localeLanguage);
		}
		
		return adverts;
	}

	public int getCountByJobAgent(CandidateJobAgents jobAgent, String advertID) {
		ActiveAdvertDAO activeAdvertsDAO = DAOFactoryHibernate.getDAOFactory().getActiveAdvertDAO();
		return activeAdvertsDAO.getCount(jobAgent, advertID);
	}
	
	public List getAdvertismentsResultsBy(String siteID, String language, String branch, String jobTitle,String name, String advertID, String fromDate, String toDate, String orderBy, boolean ascending, int pageNumber, int pageSize){
		Session session = HibernateUtil.getCurrentSession();
	 	Criteria query = session.createCriteria(Advertisement.class, ADVERTISMENTS_ALIAS);
	 	String orderByColumn = "adverts.publicationDate";
	 	boolean addRecruiter = false;
	 	List adverts = new ArrayList();
	 	if(ApplicationConstants.ORDER_BY_ADVERT_REFERENCE.equals(orderBy)){
			
			orderByColumn = ADVERTISMENTS_ALIAS + ".referenceNumber";
	 		
		}else if(orderBy.equals(ApplicationConstants.ORDER_BY_CLIENT_NAME)){
			
			orderByColumn = ADVERTISMENTS_ALIAS + ".clientName";
			
		}else if(orderBy.equals(ApplicationConstants.ORDER_BY_RECRUITER)){
			
			orderByColumn = ADVERTISMENTS_CONTACTS_ALIAS + ".contactName";
			addRecruiter = true;
			
		}else if(orderBy.equals(ApplicationConstants.ORDER_BY_JOB_TITLE)){
			orderByColumn = ADVERTISMENTS_ALIAS + ".jobTitle";
		}
		else{
			orderByColumn = ADVERTISMENTS_ALIAS + ".publicationDate";
		}
		Date startDate = null;
		Date endDate = null;
		if(fromDate != null && toDate != null){
			SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.ORACLE_DATE_FORMAT);
			try {
				startDate = dateFormat.parse(fromDate);
				endDate = dateFormat.parse(toDate);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				startDate = null;
				endDate = null;
			}
			
		}

		query = buildRecruiterQuery(query, Long.parseLong(siteID), jobTitle, language,  advertID, branch, name, startDate, endDate, addRecruiter);
		
	 	ProjectionList aList = Projections.projectionList();
		aList.add(Projections.property(orderByColumn));
		aList.add(Projections.id());
		query.setProjection(Projections.distinct(aList));
		
		if (ascending) {
			query.addOrder(Order.asc(orderByColumn));
		} else {
			query.addOrder(Order.desc(orderByColumn));
		}

		List elements = query.setFirstResult(pageNumber * pageSize)
		.setMaxResults(pageSize).list();

		
		
		int count = elements.size();
		List ids = new ArrayList(count);
		Object[] values = null;
		
		for(int i =0; i<count;i++){
			values = (Object[])elements.get(i);
			ids.add(values[1]);
		}
		
		
		if(elements != null && !elements.isEmpty()){
			Criteria query1 = session.createCriteria(Advertisement.class, ADVERTISMENTS_ALIAS);
			if(orderBy.equals(ApplicationConstants.ORDER_BY_RECRUITER)){
				query1.createCriteria("adContact", ADVERTISMENTS_CONTACTS_ALIAS);
			}
			query1.add(Restrictions.in("id", ids));
			
			if(ascending)
				query1.addOrder(Order.asc(orderByColumn));
			else
				query1.addOrder(Order.desc(orderByColumn));
			
			adverts = query1.list();
		}

		return adverts;

		
	}
	
	
	private Criteria buildRecruiterQuery(Criteria q, long siteId, String jobTitle, String language, 
			String advertId, String branch, String recruiterName, Date fromDate, Date toDate, boolean addRecruiterAlias) {
		
		q.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		q.createCriteria("sites").add(
				Restrictions.eq("id", new Long(siteId)));

		if(addRecruiterAlias){
			q.createCriteria("adContact", ADVERTISMENTS_CONTACTS_ALIAS);
		}

		if(branch != null || recruiterName != null){
		
			if(!addRecruiterAlias){
				q.createCriteria("adContact", ADVERTISMENTS_CONTACTS_ALIAS);
			}
			
			if(branch != null){
				q.add(Restrictions.eq(ADVERTISMENTS_CONTACTS_ALIAS + ".branchId", new Long(branch)));
			}
			
			if(recruiterName != null){
				q.add(Restrictions.eq(ADVERTISMENTS_CONTACTS_ALIAS + ".contactName", recruiterName));
			}
			
		}
		

		if(fromDate != null && toDate != null){
				q.add(Restrictions.between("publicationDate", fromDate, toDate));
		}

		if (advertId != null && (advertId = advertId.trim()).length() > 0) {
			q.add(Restrictions.sqlRestriction("UPPER(referenceNumber) = ?",	advertId, Hibernate.STRING));
			return q;
		}
		
		if ((jobTitle = TextUtils.processAndTrimText(jobTitle)) != null
				&& jobTitle.length() > 0) {
			q.add(Restrictions.sqlRestriction(
					"contains({alias}.JOBTITLE, ?) > 0", jobTitle,
					Hibernate.STRING));
		}


		StringBuffer log = new StringBuffer("\n\t<SEARCH PARAMETERS ARE:>\n");
		log.append("\t\t <site = " + siteId+ "/>\n");
		log.append("\t\t <lang = " + language + "/>\n");
		log.append("\t\t <job title = " + jobTitle + "/>\n");
		log.append("\t\t <recruiter name = " + recruiterName + "/>\n");
		log.append("\t\t <branch = " + branch + "/>\n");
		if(fromDate != null && toDate != null){
			log.append("\t\t <from date = " + fromDate+ "/>\n");
			log.append("\t\t <from to = " + fromDate+ "/>\n");
		}
		log.append("\t<SEARCH PARAMETERS ARE:>\n");
		
		if (logger.isDebugEnabled())
        {
			logger.debug("\nParameters are: \n" + log.toString());
			logger.debug("\nQuery is: \n" + q.toString());
        }
		
		q
				.add(Restrictions
						.sqlRestriction(
								"(?) in (SELECT L.LANGUAGE_CODE FROM ADVERTISEMENT_LANGUAGES L WHERE {alias}.ADVERTISEMENTID = L.LANGADVERT_ID)",
								language, Hibernate.STRING));

		return q;
	}

	public int getAdvertismentsCount(String siteID, String language, String branch, String jobTitle,String name, String advertID, String fromDate, String toDate){
		Session session = HibernateUtil.getCurrentSession();
		Criteria query = session.createCriteria(Advertisement.class);
		Date startDate = null;
		Date endDate = null;
		if(fromDate != null && toDate != null){
			SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.ORACLE_DATE_FORMAT);
			try {
				startDate = dateFormat.parse(fromDate);
				endDate = dateFormat.parse(toDate);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				startDate = null;
				endDate = null;
			}
			
		}
		query = buildRecruiterQuery(query,Long.parseLong(siteID), jobTitle, language,  advertID, branch, name, startDate, endDate, false);
		query.setProjection(Projections.countDistinct("id"));
		Integer rowCount = (Integer) query.uniqueResult();
		return rowCount.intValue();
		
	}
    
    public List findAdvertisementsByRecruiter(AdvertisementContact adContact) {
    	Session session = HibernateUtil.getCurrentSession();
        Query query = session.createQuery("from Advertisement where adContact = :adContact");
        query.setParameter("adContact", adContact);
        return query.list();
    }

    /**
     * @see com.manpower.portal.mpnet.dao.interfaces.AdvertisementDAO#updateExpirationDate(java.util.Date)
     */
    public void updateExpirationDate(long _advertId, Date _expirationDate)
    {
		Properties props = new Properties();

		props.put("advertId", String.valueOf(_advertId));
		props.put("expirationdate",getDateString(_expirationDate, 
		"MM/dd/yyyy"));
		
        runSQLUpdateQuery("update ADVERTISEMENTS set EXPIRATIONDATE=to_date(:expirationdate,'MM/DD/YYYY') where ADVERTISEMENTID = :advertId",props);                
    }
    
	/**
	 * Convert a date to a string
	 * 
	 * @param date
	 * @param dateFormat
	 * @return
	 */
	private String getDateString(Date date, String dateFormat) {
		SimpleDateFormat fmt = new SimpleDateFormat(dateFormat);

		return fmt.format(date);
	}    
    /**
     * @see com.manpower.portal.mpnet.dao.interfaces.AdvertisementDAO#updateExtJoBoard(long, java.lang.String)
     */
    public void updateExtJoBoard(long advertisementId, String extJoBoard)
    {
        Session session = HibernateUtil.getCurrentSession();

        Transaction transaction = null;
        try
        {
            transaction = session.beginTransaction();
            java.sql.Statement myStatement = session.connection()
                    .createStatement();
            StringBuffer sql=new StringBuffer();
            sql.append("update ADVERTISEMENTS set EXT_JOB_BOARDS=");
            sql.append("'");
            sql.append(extJoBoard);
            sql.append("'");
            sql.append(" where ADVERTISEMENTID=");
            sql.append(advertisementId);
            
            if (logger.isDebugEnabled())
	        {
            	logger.debug("SQL to be executed: "+sql.toString());
	        }
            
            myStatement.execute(sql.toString());
            
            transaction.commit();

        }
        catch (Exception e)
        {
            logger.error("Exception while updating advert with ID=:"
                    + advertisementId, e);
            try
            {
                transaction.rollback();
            }
            catch (Exception e2)
            {
                logger.error(
                        "Exception while commiting the update of advert with ID=:"
                                + advertisementId, e);
            }
        }
        finally
        {
            try
            {
                if (session != null)
                    session.close();
            }
            catch (Exception e)
            {
                logger.error("Exception while clossing Hibernate session:", e);
            }
        }

    }
    
    public List findAdvertisementsByExternalId(String externalId, long siteId) {
    	Session session = HibernateUtil.getCurrentSession();
		Criteria query = session.createCriteria(Advertisement.class);

		query.add(
				Restrictions.eq("externalId", externalId))
				.add(Restrictions.eq("siteId", new Long(siteId)));

		query.addOrder(Order.desc("updatedOn"));

		List adverts=query.list();
		
		session.clear();
		
		return adverts;
    }    
    
    
    public List getAdvertisementsByIDs(List ids, int fromIndex, int toIndex, int orderBy, boolean ascending){
    	List results = null;

    	if(ids != null && ids.size() > 0){
    		StringBuffer idList = new StringBuffer();	
    		for(int i=0; i<ids.size(); i++){
    			if(i != 0)
    				idList.append(",");
    			idList.append(ids.get(i));
    			
    		}
    		StringBuffer sqlQuery = new StringBuffer("SELECT ADVERTISEMENTID, REFERENCENUMBER, JOBTITLE FROM (");
    		sqlQuery.append(" SELECT ADVERTISEMENTID, REFERENCENUMBER, JOBTITLE, ROWNUM RNUM FROM ADVERTISEMENTS WHERE ADVERTISEMENTID IN (");
    		sqlQuery.append(idList);
    		sqlQuery.append(")");
    		if(orderBy > 0){
    			switch (orderBy){
	    			case ApplicationConstants.SORT_BY_REFERENCE:
	    				sqlQuery.append(" ORDER BY REFERENCENUMBER");
	    				break;
	    			case ApplicationConstants.SORT_BY_JOBTITLE:
	    				sqlQuery.append(" ORDER BY JOBTITLE");
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
