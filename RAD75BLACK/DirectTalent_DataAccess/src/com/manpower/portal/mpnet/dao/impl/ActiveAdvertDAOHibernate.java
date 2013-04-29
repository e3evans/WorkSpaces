package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;


import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;


import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.ActiveAdvertDAO;
import com.manpower.portal.mpnet.hbn.beans.ActiveAdvert;
import com.manpower.portal.mpnet.hbn.beans.CandidateJobAgents;
import com.manpower.portal.mpnet.util.Constants;
import com.manpower.portal.mpnet.util.TextUtils;

public class ActiveAdvertDAOHibernate extends GenericHibernateDAO implements ActiveAdvertDAO{

	public ActiveAdvertDAOHibernate(Session session) {
		super(ActiveAdvert.class, session);

	}

	private static Logger logger = Logger.getLogger(AdvertisementDAOHibernate.class.getName());
	
	private Criteria buildDistinctAdvertsQuery(Criteria query, CandidateJobAgents jobAgent,
			String advertId) {
		
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		query.add(Restrictions.eq("siteId", new Long(jobAgent.getSiteId())));
		query.add(Restrictions.eq("language",jobAgent.getJobLanguage()));

		String value;

		if (advertId != null && (advertId = advertId.trim()).length() > 0) {
			query.add(Restrictions.sqlRestriction("UPPER(referenceNumber) = ?",	advertId, Hibernate.STRING));
			return query;
		}

		value = jobAgent.getJobLocation();
		if (value != null && (value = value.trim()).length() > 0) {
			String[] locataions = TextUtils.stringToArray(value);
			
			if (locataions != null && locataions.length > 0){
				Long[] ids = new Long[locataions.length];
				for(int i=0;i<locataions.length; i++){
					ids[i] = Long.valueOf(locataions[i]);
				}
				query.add(Restrictions.in("locationId", ids));

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
			query.add(Restrictions.sqlRestriction(sql, params,
					QUADRUPLE_STRING_TYPES));
		} else if ((jobTitle = TextUtils.processAndTrimText(jobTitle)) != null
				&& jobTitle.length() > 0) {
			query.add(Restrictions.sqlRestriction(
					"contains({alias}.JOBTITLE, ?) > 0", jobTitle,
					Hibernate.STRING));
		}

		value = jobAgent.getJobIndustry();
		if (value != null && (value = value.trim()).length() > 0) {
			String[] industry = TextUtils.stringToArray(value);
			if (industry != null && industry.length > 0) {
				query.add(Restrictions.in("industrySector", industry));
			}
		}

		value = jobAgent.getContractType();
		if (value != null && (value = value.trim()).length() > 0
				&& !Constants.CONTRACT_TYPE_ALL.equals(value)) {
			String types[] = new String[] { value, Constants.CONTRACT_TYPE_ALL };
			query.add(Restrictions.in("contractType", types));
		}

		value = jobAgent.getHoursWorked();
		if (value != null && (value = value.trim()).length() > 0 && !Constants.CONTRACT_TYPE_ALL.equals(value)) {
			query.add(Restrictions.eq("hoursWorked", value));
		}

		StringBuffer log = new StringBuffer("\n\t<SEARCH PARAMETERS ARE:>\n");
		log.append("\t\t <site = " + jobAgent.getSiteId() + "/>\n");
		log.append("\t\t <job title = " + jobAgent.getJobTitle() + "/>\n");
		log.append("\t\t <candidate skills = " + jobAgent.getCandidateSkills()
				+ "/>\n");
		log.append("\t\t <positionType = " + jobAgent.getContractType()
				+ "/>\n");
		log.append("\t\t <hours worked = " + jobAgent.getHoursWorked()
						+ "/>\n");
		log.append("\t\t <industry sectors = " + jobAgent.getJobIndustry()
				+ "/>\n");
		log.append("\t\t <locations = " + jobAgent.getJobLocation() + "/>\n");
		
		log.append("\t<SEARCH PARAMETERS ARE:>\n");
		if (logger.isDebugEnabled())
        {
			logger.debug("\nParameters are: \n" + log.toString());
			logger.debug("\nQuery is: \n" + query.toString());
        }
		
		return query;
	}

	


	public List getAdvertisementIDs(CandidateJobAgents jobAgent,
			String advertId, String orderByProperty,boolean descending, int startIndex, int endIndex) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getCurrentSession();
		Criteria query = session.createCriteria(ActiveAdvert.class);
		ProjectionList aList = Projections.projectionList();
		aList.add(Projections.property("publicationDate"));
		aList.add(Projections.property("jobTitle"));
		aList.add(Projections.id());
		query.setProjection(Projections.distinct(aList));
		query = buildDistinctAdvertsQuery(query,jobAgent, advertId);
		if (descending) {
			query.addOrder(Order.desc(orderByProperty));
		} else {
			query.addOrder(Order.asc(orderByProperty));
		}
		query.setFirstResult(startIndex).setMaxResults(endIndex);
		return query.list();
	}

	public int getCount(CandidateJobAgents jobAgent, String advertID) {
		Session session = HibernateUtil.getCurrentSession();
		Criteria query = session.createCriteria(ActiveAdvert.class);
		query = buildDistinctAdvertsQuery(query, jobAgent, advertID);
		query.setProjection(Projections.countDistinct("id"));
		Integer rowCount = (Integer)query.uniqueResult();
		return rowCount.intValue();
	}




	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public Object findByID(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}


}
