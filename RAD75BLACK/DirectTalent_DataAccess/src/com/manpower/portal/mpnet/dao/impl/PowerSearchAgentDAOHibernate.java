package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.wsif.schema.Restriction;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.PowerSearchAgentDAO;
import com.manpower.portal.mpnet.hbn.beans.PlainLocation;
import com.manpower.portal.mpnet.hbn.beans.PowerSearchAgent;
import com.manpower.portal.mpnet.util.SquareBoxUtils;

public class PowerSearchAgentDAOHibernate extends GenericHibernateDAO implements PowerSearchAgentDAO {

	private static final String SELECT_CLAUSE = "SELECT CAND_ID, APPL_ID ";
	private static final String FROM_CLAUSE =  " FROM CANDIDATESEARCH CS ";
	
	public PowerSearchAgentDAOHibernate(Session session) {
		super(PowerSearchAgent.class, session);
	}
	
	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object findByID(Serializable id) {
		Object agent = HibernateUtil.getCurrentSession().load(PowerSearchAgent.class, id);
		return agent;
	}

	public List<PowerSearchAgent> getPowerAgents(long siteId, long recruierId, String language) {
		Session session = HibernateUtil.getCurrentSession();
		Criteria query =  session.createCriteria(PowerSearchAgent.class);
		query.add(Restrictions.eq("siteId",Long.valueOf(siteId)));
		query.add(Restrictions.eq("recruiterId",Long.valueOf(recruierId)));
		query.add(Restrictions.eq("language",language));

		List <PowerSearchAgent> result = query.list();
		
		return result;
	}

	public PowerSearchAgent getAgentByName(long siteId, long recruiterId, String language, String agentName){
		Session session = HibernateUtil.getCurrentSession();
		Criteria query =  session.createCriteria(PowerSearchAgent.class);
		query.add(Restrictions.eq("siteId",Long.valueOf(siteId)));
		query.add(Restrictions.eq("recruiterId",Long.valueOf(recruiterId)));
		query.add(Restrictions.eq("language",language));
		query.add(Restrictions.eq("agentName",agentName));
		
		List <PowerSearchAgent> result = query.list();
		
		if(result != null && !result.isEmpty())
			return result.get(0);
		else
			return new PowerSearchAgent();
	}

	public List<Long[]> getCandidatesByAgent(PowerSearchAgent agent) {
		Session session = HibernateUtil.getCurrentSession();
		
		String 	residenceStatus = agent.getResidenceStatus();
		String 	workPermit = agent.getWorkPermit();
		String 	industry= agent.getIndustry();
		String 	education = agent.getEducationLevel();
		long branchId = agent.getBranchId();
		String firstName = agent.getFirstName();
		String middleName = agent.getMiddleName();
		String lastName = agent.getLastName();
		String email = agent.getEmail();
		String phoneNumber = agent.getPhoneNumber();
		String userId = agent.getUserId();
		
		String keyword = agent.getKeyword();
		//List<Long> locationsIds = search.getLocationsIds();
		
		int distance = agent.getProximity();
		String distanceUnit = agent.getDistanceUnit();
		
		double latitude = 0;
		double longitude = 0;

		if (agent.getLocationId() > 0) {
			Criteria query =  session.createCriteria(PlainLocation.class);
			query.add(Restrictions.eq("id", agent.getLocationId()));
			PlainLocation location = (PlainLocation)query.uniqueResult();
			
			if (location != null) {
				latitude = location.getLatitude();
				longitude = location.getLongitude();
			}
		}
		
		boolean doCandidateSearch = false;
		
		StringBuffer SQL_QUERY = new StringBuffer(SELECT_CLAUSE + FROM_CLAUSE);
		
		SQL_QUERY.append(" WHERE SITE_ID= ");
		SQL_QUERY.append(agent.getSiteId());
		
		
		if(keyword != null && !"".equals(keyword.trim())){
			SQL_QUERY.append(" AND	CONTAINS(RESUME, '");
			SQL_QUERY.append(keyword);
			SQL_QUERY.append("', 1) > 0");
		}
		
		if(Math.abs(latitude) > 0.01 && Math.abs(longitude) > 0.01){
			double minLongitude = SquareBoxUtils.getLongitude(longitude, distance, distanceUnit, false);
			double maxLongitude = SquareBoxUtils.getLongitude(longitude, distance, distanceUnit, true);
			double minLatitude = SquareBoxUtils.getLongitude(latitude, distance, distanceUnit, false);
			double maxLatitude = SquareBoxUtils.getLongitude(latitude, distance, distanceUnit, true);
			SQL_QUERY.append(" AND ");
			SQL_QUERY.append(" (CS.LATITUDE BETWEEN ");
			SQL_QUERY.append(minLatitude); 
			SQL_QUERY.append(" AND ");
			SQL_QUERY.append(maxLatitude);
			SQL_QUERY.append(" AND ");
			
			SQL_QUERY.append(" CS.LONGITUDE BETWEEN ");
			SQL_QUERY.append(minLongitude); 
			SQL_QUERY.append(" AND ");
			SQL_QUERY.append(maxLongitude);
			SQL_QUERY.append(")");
		}
		
		if(firstName != null && !"".equals(firstName.trim())){
			firstName = firstName.trim().toUpperCase();
			SQL_QUERY.append(" AND UPPER(LTRIM(RTRIM(FIRSTNAME))) = '");
			SQL_QUERY.append(firstName);
			SQL_QUERY.append("'");
		}
		if(middleName != null && !"".equals(middleName.trim())){
			middleName = middleName.trim().toUpperCase();
			SQL_QUERY.append(" AND  UPPER(LTRIM(RTRIM(MIDDLENAME))) = '");
			SQL_QUERY.append(middleName);
			SQL_QUERY.append("'");
		}
		if(lastName != null && !"".equals(lastName.trim())){
			lastName = lastName.trim().toUpperCase();
			SQL_QUERY.append(" AND  UPPER(LTRIM(RTRIM(LASTNAME))) = '");
			SQL_QUERY.append(lastName);
			SQL_QUERY.append("'");
		}
		if(email != null && !"".equals(email.trim())){
			email = email.toUpperCase().trim();
			SQL_QUERY.append(" AND  UPPER(LTRIM(RTRIM(EMAIL))) = '");
			SQL_QUERY.append(email);
			SQL_QUERY.append("'");
		}
		
		if(userId != null && !"".equals(userId.trim())){
			userId = userId.toUpperCase().trim();
			SQL_QUERY.append(" AND  UPPER(LTRIM(RTRIM(USER_ID))) = '");
			SQL_QUERY.append(userId);
			SQL_QUERY.append("'");
		}
		
		if(residenceStatus != null && !"".equals(residenceStatus.trim())){
			SQL_QUERY.append(" AND  RESIDENCEPERMIT = '");
			SQL_QUERY.append(residenceStatus);
			SQL_QUERY.append("'");
			doCandidateSearch = true;
		}
		
		if(workPermit != null && !"".equals(workPermit.trim())){
			SQL_QUERY.append(" AND  WORKPERMITS = '");
			SQL_QUERY.append(workPermit);
			SQL_QUERY.append("'");
			doCandidateSearch = true;
		}
		
		if(industry != null && !"".equals(industry.trim())){
			SQL_QUERY.append(" AND  CONTAINS(INDUSTRYSECTOR, '{");
			SQL_QUERY.append(industry);
			SQL_QUERY.append("}') > 0");
			doCandidateSearch = true;
		}
		
		if(education != null && !"".equals(education.trim())){
			SQL_QUERY.append(" AND  CONTAINS(DEGREECODE, '{");
			SQL_QUERY.append(education);
			SQL_QUERY.append("}') > 0");
			doCandidateSearch = true;
		}
		
		if(branchId > 0){
			SQL_QUERY.append(" AND  BRANCH_ID = ");
			SQL_QUERY.append(branchId);
			doCandidateSearch = true;
		}
		
		if(phoneNumber != null && !"".equals(phoneNumber.trim())){
			phoneNumber = phoneNumber.toUpperCase().trim();
			SQL_QUERY.append(" AND UPPER(LTRIM(RTRIM(PHONENUMBER))) = '");
			SQL_QUERY.append(phoneNumber);
			SQL_QUERY.append("'");
			doCandidateSearch = true;
		}
		
		if(doCandidateSearch){
			SQL_QUERY.append(" AND  CAND_ID > 0");
		}
		
		SQLQuery sqlQuery = session.createSQLQuery(SQL_QUERY.toString());
		List<Object[]> result = sqlQuery.list();
		List<Long[]> ids = new ArrayList<Long[]>(result.size());
		for (Object[] obj : result) {
			Long[] arr = new Long[2];
			arr[0] = new Long(((BigDecimal)obj[0]).longValue());
			arr[1] = new Long(((BigDecimal)obj[1]).longValue());
			ids.add(arr);
		}
		return ids;
	}
	
}
