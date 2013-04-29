/*
 * Created on Nov 14, 2005
 *
 */
package com.manpower.portal.mpnet.dao.impl;

import org.hibernate.Session;

import com.manpower.portal.mpnet.dao.abstracts.DAOFactory;
import com.manpower.portal.mpnet.dao.interfaces.ActiveAdvertDAO;
import com.manpower.portal.mpnet.dao.interfaces.AddressDAO;
import com.manpower.portal.mpnet.dao.interfaces.AdvertisementContactDAO;
import com.manpower.portal.mpnet.dao.interfaces.AdvertisementDAO;
import com.manpower.portal.mpnet.dao.interfaces.Advertisement_HistoryDAO;
import com.manpower.portal.mpnet.dao.interfaces.ApplicantGeoCodeDAO;
import com.manpower.portal.mpnet.dao.interfaces.ApplicantResponseDAO;
import com.manpower.portal.mpnet.dao.interfaces.ApplicantResponseDetailDAO;
import com.manpower.portal.mpnet.dao.interfaces.AssessmentDeliveryFormatDAO;
import com.manpower.portal.mpnet.dao.interfaces.AssessmentProviderDAO;
import com.manpower.portal.mpnet.dao.interfaces.AssessmentRatingDAO;
import com.manpower.portal.mpnet.dao.interfaces.AssessmentSkillCategoryDAO;
import com.manpower.portal.mpnet.dao.interfaces.AssessmentSkillGroupDAO;
import com.manpower.portal.mpnet.dao.interfaces.AssessmentTestDAO;
import com.manpower.portal.mpnet.dao.interfaces.BranchDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateAssessmentsDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateCoverletterDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateEducationDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateExtraInfoDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateJobAgentsDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateJobApplicationDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateJobHistoryDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateOccupationDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidatePreferencesDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidatePreferredLocationsDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateProfileStatusDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateReferencesDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateResumeDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateResumeInfoDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateResumeSummaryDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateSaveJobsDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateSearchDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateSearchResultsDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateSkillsDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidatesAppliedDAO;
import com.manpower.portal.mpnet.dao.interfaces.CareerHarmonyDAO;
import com.manpower.portal.mpnet.dao.interfaces.ConfigurationDAO;
import com.manpower.portal.mpnet.dao.interfaces.ConsentDAO;
import com.manpower.portal.mpnet.dao.interfaces.CountryRegionDAO;
import com.manpower.portal.mpnet.dao.interfaces.CredentialVaultDAO;
import com.manpower.portal.mpnet.dao.interfaces.DomainValueDAO;
import com.manpower.portal.mpnet.dao.interfaces.DynaCacheDAO;
import com.manpower.portal.mpnet.dao.interfaces.EmailDAO;
import com.manpower.portal.mpnet.dao.interfaces.EnumerationDAO;
import com.manpower.portal.mpnet.dao.interfaces.FlexFieldDAO;
import com.manpower.portal.mpnet.dao.interfaces.GeoCodeDAO;
import com.manpower.portal.mpnet.dao.interfaces.HierarchyLocationDAO;
import com.manpower.portal.mpnet.dao.interfaces.HistoryLogDAO;
import com.manpower.portal.mpnet.dao.interfaces.IdentityDAO;
import com.manpower.portal.mpnet.dao.interfaces.JobCategoryDAO;
import com.manpower.portal.mpnet.dao.interfaces.JobTitleDAO;
import com.manpower.portal.mpnet.dao.interfaces.LostCandidateDAO;
import com.manpower.portal.mpnet.dao.interfaces.MobileAgentDAO;
import com.manpower.portal.mpnet.dao.interfaces.MobileDeviceDAO;
import com.manpower.portal.mpnet.dao.interfaces.PlainLocationDAO;
import com.manpower.portal.mpnet.dao.interfaces.PowerSearchAgentDAO;
import com.manpower.portal.mpnet.dao.interfaces.RecommendedCandidatesDAO;
import com.manpower.portal.mpnet.dao.interfaces.RecommendedCandidatesPreferencesDAO;
import com.manpower.portal.mpnet.dao.interfaces.RecruiterSearchAgentDAO;
import com.manpower.portal.mpnet.dao.interfaces.SearchStatisticsDAO;
//import com.manpower.portal.mpnet.dao.interfaces.MyManpowerResumeDAO;
import com.manpower.portal.mpnet.dao.interfaces.OtherCandidateDetailsDAO;
import com.manpower.portal.mpnet.dao.interfaces.OtherSiteDetailsDAO;
import com.manpower.portal.mpnet.dao.interfaces.PhoneDAO;
import com.manpower.portal.mpnet.dao.interfaces.PostalCodeAddressDAO;
import com.manpower.portal.mpnet.dao.interfaces.RecruiterAuditingDAO;
import com.manpower.portal.mpnet.dao.interfaces.RecruiterCommentsDAO;
import com.manpower.portal.mpnet.dao.interfaces.RecruiterLocationReportDAO;
import com.manpower.portal.mpnet.dao.interfaces.RecruiterReportDAO;
import com.manpower.portal.mpnet.dao.interfaces.RegionDAO;
import com.manpower.portal.mpnet.dao.interfaces.ReportingMetricsDAO;
import com.manpower.portal.mpnet.dao.interfaces.SiteDAO;
import com.manpower.portal.mpnet.dao.interfaces.SkillsDAO;
import com.manpower.portal.mpnet.dao.interfaces.SteeringQuestionDAO;
import com.manpower.portal.mpnet.dao.interfaces.SummaryAdvertisementsDAO;
import com.manpower.portal.mpnet.dao.interfaces.VCandidateApplicationDAO;
import com.manpower.portal.mpnet.dao.interfaces.VCandidateResumeDAO;
import com.manpower.portal.mpnet.dao.interfaces.NA_JobTypeDAO;
import com.manpower.portal.mpnet.dao.interfaces.EventsDAO;
//import com.manpower.portal.mpnet.ui.beans.MyManpowerResumeBean;

/**
 * Hibernate implementation of DAOFactory interface
 * 
 * @author jsingh
 */
public class DAOFactoryHibernate extends DAOFactory {

	/**
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getAdvertisementContactDAO()
	 */
	public AdvertisementContactDAO getAdvertisementContactDAO() {

		return new AdvertisementContactDAOHibernate(getCurrentSession());
	}

	/**
	 * Get Hibernate session
	 * 
	 * @return Current Hibernate session
	 */
	protected Session getCurrentSession() {
		Session session = com.manpower.j2ee.util.hibernate.HibernateUtil
				.getCurrentSession();
		return session;
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getCandidateJobHistoryDAO()
	 */
	public CandidateJobHistoryDAO getCandidateJobHistoryDAO() {

		return new CandidateJobHistoryDAOHibernate(getCurrentSession());
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getCandidateDAO()
	 */
	public CandidateDAO getCandidateDAO() {

		return new CandidateDAOHibernate(getCurrentSession());
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getAddressDAO()
	 */
	public AddressDAO getAddressDAO() {

		return new AddressDAOHibernate(getCurrentSession());
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getPhoneDAO()
	 */
	public PhoneDAO getPhoneDAO() {

		return new PhoneDAOHibernate(getCurrentSession());
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getAdvertisementDAO()
	 */
	public AdvertisementDAO getAdvertisementDAO() {
		Session session = com.manpower.j2ee.util.hibernate.HibernateUtil
				.getCurrentSession();
		return new AdvertisementDAOHibernate(session);
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getCandidateCoverLetterDAO()
	 */
	public CandidateCoverletterDAO getCandidateCoverLetterDAO() {

		return new CandidateCoverletterDAOHibernate(getCurrentSession());
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getCandidateEducationDAO()
	 */
	public CandidateEducationDAO getCandidateEducationDAO() {
		Session session = com.manpower.j2ee.util.hibernate.HibernateUtil
				.getCurrentSession();
		return new CandidateEducationDAOHibernate(session);
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getCandidateJobAgentsDAO()
	 */
	public CandidateJobAgentsDAO getCandidateJobAgentsDAO() {

		return new CandidateJobAgentsDAOHibernate(getCurrentSession());
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getCandidateJobApplicationDAO()
	 */
	public CandidateJobApplicationDAO getCandidateJobApplicationDAO() {

		return new CandidateJobApplicationDAOHibernate(getCurrentSession());
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getCandidatePreferencesDAO()
	 */
	public CandidatePreferencesDAO getCandidatePreferencesDAO() {

		return new CandidatePreferencesDAOHibernate(getCurrentSession());
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getCandidateReferencesDAO()
	 */
	public CandidateReferencesDAO getCandidateReferencesDAO() {

		return new CandidateReferencesDAOHibernate(getCurrentSession());
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getCandidateResumeDAO()
	 */
	public CandidateResumeDAO getCandidateResumeDAO() {

		return new CandidateResumeDAOHibernate(getCurrentSession());
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getCandidateSaveJobsDAO()
	 */
	public CandidateSaveJobsDAO getCandidateSaveJobsDAO() {

		return new CandidateSaveJobsDAOHibernate(getCurrentSession());
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getCandidateSkillsDAO()
	 */
	public CandidateSkillsDAO getCandidateSkillsDAO() {

		return new CandidateSkillsDAOHibernate(getCurrentSession());
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getSkillsDAO()
	 */
	public SkillsDAO getSkillsDAO() {

		return new SkillsDAOHibernate(getCurrentSession());
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getDomainValueDAO()
	 */
	public DomainValueDAO getDomainValueDAO() {
		return new DomainValueDAOHibernate(getCurrentSession());
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getBranchDAO()
	 */
	public BranchDAO getBranchDAO() {
		return new BranchDAOHibernate(getCurrentSession());
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getSteeringQuestionDAO()
	 */
	public SteeringQuestionDAO getSteeringQuestionDAO() {

		return new SteeringQuestionDAOHibernate(getCurrentSession());

	}

	/**
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getEmailDAO()
	 */
	public EmailDAO getEmailDAO() {

		return new EmailDAOHibernate(getCurrentSession());
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getSiteDAO()
	 */
	public SiteDAO getSiteDAO() {

		return new SiteDAOHibernate(getCurrentSession());

	}

	/**
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getGeoLocationDAO()
	 */
	public GeoCodeDAO getGeoLocationDAO() {

		return new GeoCodeDAOHibernate(getCurrentSession());
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getLostCandidateDAO()
	 */
	public LostCandidateDAO getLostCandidateDAO() {

		return new LostCandidateDAOHibernate(getCurrentSession());

	}

	/**
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getConfigurationDAO()
	 */
	public ConfigurationDAO getConfigurationDAO() {

		return new ConfigurationDAOHibernate(getCurrentSession());

	}

	/**
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getVCandidateResumeDAO()
	 */
	public VCandidateResumeDAO getVCandidateResumeDAO() {

		return new VCandidateResumeDAOHibernate(getCurrentSession());
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getVCandidateApplicationDAO()
	 */
	public VCandidateApplicationDAO getVCandidateApplicationDAO() {

		return new VCandidateApplicationDAOHibernate(getCurrentSession());
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getPostalCodeAddressDAO()
	 */
	public PostalCodeAddressDAO getPostalCodeAddressDAO() {

		return new PostalCodeAddressDAOHibernate(getCurrentSession());

	}

	/**
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getRegionDAO()
	 */
	public RegionDAO getRegionDAO() {

		return new RegionDAOHibernate(getCurrentSession());

	}

	/**
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getCareerHarmonyDAO()
	 */
	public CareerHarmonyDAO getCareerHarmonyDAO() {
		return new CareerHarmonyDAOHibernate(getCurrentSession());
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getRecruiterReportDAO()
	 */
	public RecruiterReportDAO getRecruiterReportDAO() {
		return new RecruiterReportDAOHibernate(getCurrentSession());
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getRecruiterLocationReportDAO()
	 */
	public RecruiterLocationReportDAO getRecruiterLocationReportDAO() {
		return new RecruiterLocationReportHibernateDAO(getCurrentSession());
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getEnumerationDAO()
	 */
	public EnumerationDAO getEnumerationDAO() {
		return new EnumerationDAOHibernate(getCurrentSession());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getConsentDAO()
	 */
	public ConsentDAO getConsentDAO() {
		return new ConsentDAOHibernate(getCurrentSession());
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getCandidateResumeInfoDAO()
	 */
	public CandidateResumeInfoDAO getCandidateResumeInfoDAO() {
		return new CandidateResumeInfoDAOHibernate(getCurrentSession());
	}

	/*
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getReportingMetricsDAO()
	 */
	public ReportingMetricsDAO getReportingMetricsDAO() {
		// TODO Auto-generated method stub
		return new ReportingMetricsDAOHibernate(getCurrentSession());
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getFlexFieldDAO()
	 */
	public FlexFieldDAO getFlexFieldDAO() {
		return new FlexFieldDAOHibernate(getCurrentSession());
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getSummaryAdvertisementsDAO()
	 */
	public SummaryAdvertisementsDAO getSummaryAdvertisementsDAO() {
		// TODO Auto-generated method stub
		return new SummaryAdvertisementsDAOHibernate(getCurrentSession());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getOtherCandidateDetailsDAO()
	 */
	public OtherCandidateDetailsDAO getOtherCandidateDetailsDAO() {
		return new OtherCandidateDetailsDAOHibernate(getCurrentSession());
	}

	public IdentityDAO getIdentityDAO() {

		return new IdentityDAOHibernate(getCurrentSession());
	}

	/**
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getCandidatesAppliedDAO()
	 */
	public CandidatesAppliedDAO getCandidatesAppliedDAO() {
		return new CandidatesAppliedDAOHibernate(getCurrentSession());
	}

	public HistoryLogDAO getHistoryLogDAO() {
		return new HistoryLogDAOHibernate(getCurrentSession());
	}

	public RecruiterCommentsDAO getRecruiterCommentsDAO() {
		return new RecruiterCommentsDAOHibernate(getCurrentSession());
	}

	/*
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getCandidateAssessmentsDAO()
	 */
	public CandidateAssessmentsDAO getCandidateAssessmentsDAO() {

		return new CandidateAssessmentsDAOHibernate(getCurrentSession());
	}

	/*
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getCandidateSearchResultsDAO()
	 */
	public CandidateSearchResultsDAO getCandidateSearchResultsDAO() {

		return new CandidateSearchResultsHibernateDAO(getCurrentSession());
	}

	/*
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getApplicantResponseDAO()
	 */
	public ApplicantResponseDAO getApplicantResponseDAO() {

		return new ApplicantResponseDAOHibernate(getCurrentSession());
	}

	/*
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getApplicantResponseDetailDAO()
	 */
	public ApplicantResponseDetailDAO getApplicantResponseDetailDAO() {

		return new ApplicantResponseDetailDAOHibernate(getCurrentSession());
	}
	
	/* 
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getNA_JobTypeDAO()
	 */
	public NA_JobTypeDAO getNA_JobTypeDAO() {
		
		 return new NA_JobTypeDAOHibernate(getCurrentSession());
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getAssessmentProviderDAO()
	 */
	public AssessmentProviderDAO getAssessmentProviderDAO() {
		return new AssessmentProviderDAOHibernate(getCurrentSession());
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getAssessmentRatingDAO()
	 */
	public AssessmentRatingDAO getAssessmentRatingDAO() {
		return new AssessmentRatingDAOHibernate(getCurrentSession());
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getAssessmentTestDAO()
	 */
	public AssessmentTestDAO getAssessmentTestDAO() {
		return new AssessmentTestDAOHibernate(getCurrentSession());
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getAssessmentDeliveryFormatDAO()
	 */
	public AssessmentDeliveryFormatDAO getAssessmentDeliveryFormatDAO() {
		return new AssessmentDeliveryFormatDAOHibernate(getCurrentSession());
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getAssessmentSkillCategoryDAO()
	 */
	public AssessmentSkillCategoryDAO getAssessmentSkillCategoryDAO() {
		return new AssessmentSkillCategoryDAOHibernate(getCurrentSession());
	}

	/* (non-Javadoc)
	 * @see com.manpower.portal.mpnet.dao.abstracts.DAOFactory#getAssessmentSkillGroupDAO()
	 */
	public AssessmentSkillGroupDAO getAssessmentSkillGroupDAO() {
		return new AssessmentSkillGroupDAOHibernate(getCurrentSession());
	}

	public CountryRegionDAO getCountryRegionDAO() {
		return new CountryRegionDAOHibernate(getCurrentSession());
	}
	
	public JobTitleDAO getJobTitleDAO() {
		return new JobTitleDAOHibernate(getCurrentSession());
	}

	public OtherSiteDetailsDAO getOtherSiteDetailsDAO(){
		return new OtherSiteDetailsDAOHibernate(getCurrentSession());
	}
	public JobCategoryDAO getJobCategoryDAO() {
        return new JobCategoryDAOHibernate(getCurrentSession());
    }
	public RecruiterAuditingDAO getRecruiterAuditingDAO() {
		return new RecruiterAuditingDAOHibernate(getCurrentSession());
	}
	
	public CandidateProfileStatusDAO getCandidateProfileStatusDAO() {
		return new CandidateProfileStatusDAOHibernate(getCurrentSession());
	}
	
	public CandidateOccupationDAO getCandidateOccupationDAO(){
		return new CandidateOccupationDAOHibernate(getCurrentSession());
	}
	
	public CandidatePreferredLocationsDAO getCandidatePreferredLocationsDAO(){
		return new CandidatePreferredLocationsDAOHibernate(getCurrentSession());		
	}
	
	public Advertisement_HistoryDAO getAdvertisement_HistoryDAO(){
		return new Advertisement_HistoryDAOHibernate(getCurrentSession());
	}

  public EventsDAO getEventsDAO()
  {
    return new EventsDAOHibernate(getCurrentSession());
  }
  
  public CandidateExtraInfoDAO getCandidateExtraInfoDAO(){
	  return new CandidateExtraInfoDAOHibernate(getCurrentSession());
  }

  public DynaCacheDAO getDynaCacheDAO()
  {
	  return new DynaCacheDAOHibernate(getCurrentSession());
  }
  
 	public SearchStatisticsDAO getSearchStatisticsDAO() {
		return new SearchStatisticsDAOHibernate(getCurrentSession());
	}

	public CandidateResumeSummaryDAO getCandidateResumeSummaryDAO() {
		return new CandidateResumeSummaryDAOHibernate(getCurrentSession());
	}
	
	public ActiveAdvertDAO getActiveAdvertDAO(){
		return new ActiveAdvertDAOHibernate(getCurrentSession());
	}
	
	public HierarchyLocationDAO getHierarchyLocationDAO(){
		return new HierarchyLocationDAOHibernate(getCurrentSession());
	}

	@Override
	public RecruiterSearchAgentDAO getRecruiterSearchAgentDAO() {
		return new RecruiterSearchAgentDAOHibernate(getCurrentSession());
	}
	
	public CandidateSearchDAO getCandidateSearchDAO() {

		return new CandidateSearchDAOHibernate(getCurrentSession());
	}

	public MobileAgentDAO getMobileAgentDAO(){
		return new MobileAgentDAOHibernate(getCurrentSession());
	}

	public MobileDeviceDAO getMobileDeviceDAO() {
		return new MobileDeviceDAOHibernate(getCurrentSession());
	}

	@Override
	public PowerSearchAgentDAO getPowerSearchAgentDAO() {
		// TODO Auto-generated method stub
		return new PowerSearchAgentDAOHibernate(getCurrentSession());
	}
	
	@Override
	public CredentialVaultDAO getCredentialVaultDAO() {
		// TODO Auto-generated method stub
		return new CredentialVaultDAOHibernate(getCurrentSession());
	}
	
	public ApplicantGeoCodeDAO getApplicantGeoCodeService(){
		return new ApplicantGeoCodeServiceHibernateDAO(getCurrentSession());
	}

	@Override
	public RecommendedCandidatesPreferencesDAO getRecommendedCandidatesPreferencesDAO() {
		return new RecommendedCandidatesPreferencesDAOHibernate(getCurrentSession());
	}

	@Override
	public RecommendedCandidatesDAO getRecommendedCandidatesDAO() {
		return new RecommendedCandidatesDAOHibernate(getCurrentSession());
	}
	
	public PlainLocationDAO getPlainLocationDAO(){
		return new PlainLocationDAOHibernate(getCurrentSession());
	}
}
