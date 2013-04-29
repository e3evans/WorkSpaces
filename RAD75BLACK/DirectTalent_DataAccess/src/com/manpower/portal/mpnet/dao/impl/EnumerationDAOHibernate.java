/*
 * Created on Sep 24, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.portal.mpnet.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import org.hibernate.Query;
import org.hibernate.Session;

import com.manpower.j2ee.util.hibernate.HibernateUtil;
import com.manpower.portal.mpnet.dao.abstracts.GenericHibernateDAO;
import com.manpower.portal.mpnet.dao.interfaces.AddressDAO;
import com.manpower.portal.mpnet.dao.interfaces.AdvertisementContactDAO;
import com.manpower.portal.mpnet.dao.interfaces.AdvertisementDAO;
import com.manpower.portal.mpnet.dao.interfaces.BranchDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateCoverletterDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateEducationDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateJobAgentsDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateJobApplicationDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateJobHistoryDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidatePreferencesDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateReferencesDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateResumeDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateResumeInfoDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateSaveJobsDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateSkillsDAO;
import com.manpower.portal.mpnet.dao.interfaces.CareerHarmonyDAO;
import com.manpower.portal.mpnet.dao.interfaces.ConfigurationDAO;
import com.manpower.portal.mpnet.dao.interfaces.ConsentDAO;
import com.manpower.portal.mpnet.dao.interfaces.DomainValueDAO;
import com.manpower.portal.mpnet.dao.interfaces.EducationDegreeDAO;
import com.manpower.portal.mpnet.dao.interfaces.EmailDAO;
import com.manpower.portal.mpnet.dao.interfaces.EnumerationDAO;
import com.manpower.portal.mpnet.dao.interfaces.ForumDAO;
import com.manpower.portal.mpnet.dao.interfaces.GeoCodeDAO;
import com.manpower.portal.mpnet.dao.interfaces.LostCandidateDAO;
import com.manpower.portal.mpnet.dao.interfaces.OtherCandidateDetailsDAO;
import com.manpower.portal.mpnet.dao.interfaces.PersonDAO;
import com.manpower.portal.mpnet.dao.interfaces.PhoneDAO;
import com.manpower.portal.mpnet.dao.interfaces.PostalCodeAddressDAO;
import com.manpower.portal.mpnet.dao.interfaces.PostingDAO;
import com.manpower.portal.mpnet.dao.interfaces.RecruiterLocationReportDAO;
import com.manpower.portal.mpnet.dao.interfaces.RecruiterReportDAO;
import com.manpower.portal.mpnet.dao.interfaces.RegionDAO;
import com.manpower.portal.mpnet.dao.interfaces.SiteDAO;
import com.manpower.portal.mpnet.dao.interfaces.SkillsDAO;
import com.manpower.portal.mpnet.dao.interfaces.SteeringQuestionDAO;
import com.manpower.portal.mpnet.dao.interfaces.TopicDAO;
import com.manpower.portal.mpnet.dao.interfaces.UserDAO;
import com.manpower.portal.mpnet.dao.interfaces.UserLoginHistoryDAO;
import com.manpower.portal.mpnet.dao.interfaces.UserRoleDAO;
import com.manpower.portal.mpnet.dao.interfaces.VCandidateApplicationDAO;
import com.manpower.portal.mpnet.dao.interfaces.VCandidateResumeDAO;
import com.manpower.portal.mpnet.hbn.beans.Enumeration;

/**
 * @author atodorov
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class EnumerationDAOHibernate extends GenericHibernateDAO implements
		EnumerationDAO {

	public EnumerationDAOHibernate(Session session) {
		super(Enumeration.class, session);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getForumDAO()
	 */
	public ForumDAO getForumDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getTopicDAO()
	 */
	public TopicDAO getTopicDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getPostingDAO()
	 */
	public PostingDAO getPostingDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getUserDAO()
	 */
	public UserDAO getUserDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getCandidateJobHistoryDAO()
	 */
	public CandidateJobHistoryDAO getCandidateJobHistoryDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getCandidateDAO()
	 */
	public CandidateDAO getCandidateDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getAddressDAO()
	 */
	public AddressDAO getAddressDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getAdvertisementDAO()
	 */
	public AdvertisementDAO getAdvertisementDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getCandidateCoverLetterDAO()
	 */
	public CandidateCoverletterDAO getCandidateCoverLetterDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getCandidateEducationDAO()
	 */
	public CandidateEducationDAO getCandidateEducationDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getCandidateJobAgentsDAO()
	 */
	public CandidateJobAgentsDAO getCandidateJobAgentsDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getCandidateJobApplicationDAO()
	 */
	public CandidateJobApplicationDAO getCandidateJobApplicationDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getCandidatePreferencesDAO()
	 */
	public CandidatePreferencesDAO getCandidatePreferencesDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getCandidateReferencesDAO()
	 */
	public CandidateReferencesDAO getCandidateReferencesDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getCandidateResumeDAO()
	 */
	public CandidateResumeDAO getCandidateResumeDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getCandidateSaveJobsDAO()
	 */
	public CandidateSaveJobsDAO getCandidateSaveJobsDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getCandidateSkillsDAO()
	 */
	public CandidateSkillsDAO getCandidateSkillsDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getSkillsDAO()
	 */
	public SkillsDAO getSkillsDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getPersonDAO()
	 */
	public PersonDAO getPersonDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getUserLoginHistoryDAO()
	 */
	public UserLoginHistoryDAO getUserLoginHistoryDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getUserRoleDAO()
	 */
	public UserRoleDAO getUserRoleDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getDomainValueDAO()
	 */
	public DomainValueDAO getDomainValueDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getEnumerationDAO()
	 */
	public EnumerationDAO getEnumerationDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getBranchDAO()
	 */
	public BranchDAO getBranchDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getAdvertisementContactDAO()
	 */
	public AdvertisementContactDAO getAdvertisementContactDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getSteeringQuestionDAO()
	 */
	public SteeringQuestionDAO getSteeringQuestionDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getEmailDAO()
	 */
	public EmailDAO getEmailDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getSiteDAO()
	 */
	public SiteDAO getSiteDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getPhoneDAO()
	 */
	public PhoneDAO getPhoneDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getGeoLocationDAO()
	 */
	public GeoCodeDAO getGeoLocationDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getLostCandidateDAO()
	 */
	public LostCandidateDAO getLostCandidateDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getConfigurationDAO()
	 */
	public ConfigurationDAO getConfigurationDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getVCandidateResumeDAO()
	 */
	public VCandidateResumeDAO getVCandidateResumeDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getVCandidateApplicationDAO()
	 */
	public VCandidateApplicationDAO getVCandidateApplicationDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getRegionDAO()
	 */
	public RegionDAO getRegionDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getEducatoinDegreeDAO()
	 */
	public EducationDegreeDAO getEducatoinDegreeDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getPostalCodeAddressDAO()
	 */
	public PostalCodeAddressDAO getPostalCodeAddressDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getCareerHarmonyDAO()
	 */
	public CareerHarmonyDAO getCareerHarmonyDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getRecruiterReportDAO()
	 */
	public RecruiterReportDAO getRecruiterReportDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getRecruiterLocationReportDAO()
	 */
	public RecruiterLocationReportDAO getRecruiterLocationReportDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getConsentDAO()
	 */
	public ConsentDAO getConsentDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getCandidateResumeInfoDAO()
	 */
	public CandidateResumeInfoDAO getCandidateResumeInfoDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getOtherCandidateDetailsDAO()
	 */
	public OtherCandidateDetailsDAO getOtherCandidateDetailsDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	public int deleteAll(String siteCode, String language, String lookupName,
			String lookupCode) {

		StringBuffer queryString = new StringBuffer(
				"DELETE com.manpower.portal.mpnet.hbn.beans.Enumeration e where e.siteId = :siteId and e.lookupLang = :lang");

		if (lookupName != null) {

			queryString.append(" and e.lookupName = :lname");

		}

		if (lookupCode != null) {

			queryString.append(" and e.lookupCode = :lcode");

		}

		Session session = HibernateUtil.getCurrentSession();

		Query query = session.createQuery(queryString.toString());

		query.setString("siteId", siteCode);

		query.setString("lang", language);

		if (lookupName != null) {

			query.setString("lname", lookupName);

		}

		if (lookupCode != null) {

			query.setString("lcode", lookupCode);

		}

		int deleted = query.executeUpdate();

		return deleted;
	}

/*	public void saveAll(List enumerations) {

		Session session = HibernateUtil.getCurrentSession();

		try {

			int size = enumerations.size();

			EnumerationBean enumerationBean;

			Enumeration enumeration;

			for (int i = 0; i < size; i++) {

				enumerationBean = (EnumerationBean) enumerations.get(i);

				enumeration = EnumerationBuilder
						.createEnumeartion(enumerationBean);

				enumeration.setId(0);

				session.save(enumeration);

				if (i % 50 == 0) {

					session.flush();

					session.clear();

				}

			}

			session.clear();

		} catch (Exception ex) {

		}

	}*/
    
    public void storeByCustomSQLQuery(String queryString, Properties props) {
        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction().begin();
        Query query = session.createSQLQuery(queryString).setProperties(props);
        query.executeUpdate();
        session.getTransaction().commit();
    }
    
	public Object findByID(Serializable id) {
		return null;
	}

	public List findAll() {
		return null;
	}
}
