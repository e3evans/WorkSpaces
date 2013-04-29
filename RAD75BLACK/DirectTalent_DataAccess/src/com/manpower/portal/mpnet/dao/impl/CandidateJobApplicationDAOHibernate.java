/*
 * Created on Jan 23, 2006
 *
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.lob.SerializableClob;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateJobApplicationDAO;
import com.manpower.portal.mpnet.hbn.beans.Advertisement;
import com.manpower.portal.mpnet.hbn.beans.AdvertisementContact;
import com.manpower.portal.mpnet.hbn.beans.Candidate;
import com.manpower.portal.mpnet.hbn.beans.CandidateJobApplication;

/**
 * @author jsingh
 *  
 */
public class CandidateJobApplicationDAOHibernate extends GenericHibernateDAO
		implements CandidateJobApplicationDAO {

	private Logger logger = Logger.getLogger(CandidateJobApplicationDAOHibernate.class);
	
	/**
	 * @see com.manpower.portal.mpnet.dao.interfaces.CandidateJobApplicationDAO#findCandidatesAppliedForJobApplication(long)
	 */
	public List findCandidatesAppliedForJobApplication(Advertisement advert) {
		Criteria query = HibernateUtil.getCurrentSession()
				.createCriteria(Candidate.class).createCriteria(
						"jobApplication").add(
						Expression.eq("advertisement", advert));
		return query.list();
	}
    
    /**
     * @see com.manpower.portal.mpnet.dao.interfaces.CandidateJobApplicationDAO#findUndeletedCandidateApplications(long, long)
     */
    public CandidateJobApplication findUndeletedCandidateApplications(long candidateId, long advertisementId)
    {
        Criteria query = HibernateUtil.getCurrentSession().
        createCriteria(CandidateJobApplication.class).
            add(Restrictions.eq("deleted", new Boolean(false)));
        Criteria advCriteria = query.createCriteria("advertisement");
        advCriteria.add(Restrictions.eq("id", new Long(advertisementId)));
        
        Criteria candidateCriteria = query.createCriteria("candidate");
        candidateCriteria.add(Restrictions.eq("id", new Long(candidateId)));
            
        logger.debug("Getting undeleted job applications.");

        List results = query.list();
        
        logger.debug("Results found: " + results);
        
        if (results != null && results.size() != 0)
        {
        	logger.debug("Results count: " + results.size());
            return (CandidateJobApplication)results.get(0);
        }
        
        return null;
    }
    

	/**
	 * Constructor
	 * 
	 * @param persistentClass
	 * @param session
	 */
	public CandidateJobApplicationDAOHibernate(Session session) {
		super(CandidateJobApplication.class, session);
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

		Object candidateJobApplication = HibernateUtil.getSessionFactory()
				.getCurrentSession().get(CandidateJobApplication.class, id);
		HibernateUtil.getCurrentSession().evict(
				candidateJobApplication);
		return candidateJobApplication;
	}
	
	public List getAllUndeletedJobApplicationsSimple(long candidateId) {
		Session session = HibernateUtil.getCurrentSession();
		
		SQLQuery query = session.createSQLQuery(
				"SELECT j.ID, j.Dateapplied, j.advertisement_id, a.advertisementid, a.jobtitle, j.testtaken, a.careerharmony_id , j.CH_TEST_LINK " +
				"FROM candidatejobapplications j " +
				"INNER JOIN advertisements a ON j.advertisement_id = a.advertisementid " +
				"WHERE j.candidate_id = '" + candidateId + "' AND j.deleted = 0 " +
				"order by j.dateapplied desc");
		List result = query.list();
		List jobApplications = new ArrayList(result.size());
		Iterator iter = result.iterator();
		while (iter.hasNext()) {
			Object[] resultSet = (Object[])iter.next();
			CandidateJobApplication ja = new CandidateJobApplication();
			ja.setId(Long.parseLong(resultSet[0].toString()));
			ja.setDateApplied((Date)resultSet[1]);
			ja.setAdvertisementID(Long.parseLong(resultSet[2].toString()));
			if(resultSet[5]!=null){
				ja.setTestTaken(resultSet[5].toString());
			}
			Advertisement a = new Advertisement();
			a.setId(Long.parseLong(resultSet[3].toString()));
			a.setJobTitle(resultSet[4].toString());
			a.setCareerHarmonyId(Long.parseLong(resultSet[6].toString()));
			a.setAdvertisementContact(new AdvertisementContact());
			ja.setAdvertisement(a);
			ja.setCandidate(new Candidate());
			if(resultSet[7] != null )
        ja.setChTestLink(resultSet[7].toString());
			jobApplications.add(ja);
		}
		return jobApplications;
		
//		SELECT j.ID, j.Dateapplied, advertisement_id, a.jobtitle
//		FROM candidatejobapplications j
//		INNER JOIN advertisements a ON j.advertisement_id = a.advertisementid
//		WHERE j.candidate_id = AND j.site_id = 10420
		
		//from com.manpower.portal.mpnet.hbn.beans.CandidateJobApplication as h where h.candidate.id = :cid and h.deleted=0 order by h.dateApplied desc
	}

	public boolean undeletedJobApplicationExists(long candidateId, long advertisementId) {
		Session session = HibernateUtil.getCurrentSession();
		SQLQuery query = session.createSQLQuery(
				"select count(*) from candidatejobapplications j " +
				"where j.candidate_id = '" + candidateId + "' " +
				"and j.advertisement_id = '" + advertisementId + "'");
		Object obj = query.uniqueResult();
		BigDecimal count = (BigDecimal)obj;
		return count.intValue() > 0;
	}
	
	public void setJobApplicationToDeleted(Serializable id) {
		CandidateJobApplication jobApplication = (CandidateJobApplication)findByID(id);
		jobApplication.setDeleted(true);
		makeUpdate(jobApplication);
	}

	public String[] getCoverLetter(long jobApplicationId) {
		
		String sqlQuery = 
				"select ca.FIRSTNAME || ' ' ||  ca.LASTNAME || ' - ' || ad.JOBTITLE as NAME, " +
				"this_.COVERLETTER from CANDIDATEJOBAPPLICATIONS this_, CANDIDATES ca, ADVERTISEMENTS ad " +
				"where ca.ID = this_.CANDIDATE_ID " +
				"and ad.ADVERTISEMENTID = this_. advertisement_id " +
				"and this_.ID = ?";
		
		Session session = HibernateUtil.getCurrentSession();
		SQLQuery query = session.createSQLQuery(sqlQuery);
		query.setLong(0, jobApplicationId);
		
		Object[] resultSet = (Object[])query.uniqueResult();
		
		String candidateName = (String)resultSet[0];
		SerializableClob coverLetter = (SerializableClob)resultSet[1];
		
		if (coverLetter == null) {
			return null;
		}
		try {
			long length = coverLetter.length();
			if (length != 0) {
				return new String[]{candidateName, coverLetter.getSubString(1l, (int)length)};
			} else {
				return null;
			}
		} catch (SQLException e) {
			logger.error("Error reading cover letter: " + jobApplicationId, e);
			return null;
		}
	}
	
	private String convertStreamToString(InputStream is) {

		if (is != null) {
			StringBuilder sb = new StringBuilder();
			String line;
		
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				while ((line = reader.readLine()) != null) {
					sb.append(line).append("\n");
				}
			} catch(IOException ioe) {
				logger.error("Error serializing CLOB", ioe);
			}
			finally {
				try {
					is.close();
				} catch (IOException ioe) {
					logger.error("Error serializing CLOB", ioe);
				}
			}
			return sb.toString();
		} else {       
			return "";
		}
	}	
}
