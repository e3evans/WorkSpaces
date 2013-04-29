/*
 * Created on Nov 14, 2005
 *
 */
package com.manpower.portal.mpnet.dao.abstracts;

import com.manpower.portal.mpnet.dao.impl.DAOFactoryHibernate;
import com.manpower.portal.mpnet.dao.interfaces.AddressDAO;
import com.manpower.portal.mpnet.dao.interfaces.AdvertisementContactDAO;
import com.manpower.portal.mpnet.dao.interfaces.AdvertisementDAO;
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
import com.manpower.portal.mpnet.dao.interfaces.CandidateJobAgentsDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateJobApplicationDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateJobHistoryDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidatePreferencesDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateProfileStatusDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateReferencesDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateResumeDAO;
//import com.manpower.portal.mpnet.dao.interfaces.MyManpowerResumeDAO;
import com.manpower.portal.mpnet.dao.interfaces.ActiveAdvertDAO;
import com.manpower.portal.mpnet.dao.interfaces.Advertisement_HistoryDAO;
import com.manpower.portal.mpnet.dao.interfaces.ApplicantGeoCodeDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateExtraInfoDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidateOccupationDAO;
import com.manpower.portal.mpnet.dao.interfaces.CandidatePreferredLocationsDAO;
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
import com.manpower.portal.mpnet.dao.interfaces.NA_JobTypeDAO;
import com.manpower.portal.mpnet.dao.interfaces.OtherCandidateDetailsDAO;
import com.manpower.portal.mpnet.dao.interfaces.OtherSiteDetailsDAO;
import com.manpower.portal.mpnet.dao.interfaces.PhoneDAO;
import com.manpower.portal.mpnet.dao.interfaces.PlainLocationDAO;
import com.manpower.portal.mpnet.dao.interfaces.PostalCodeAddressDAO;
import com.manpower.portal.mpnet.dao.interfaces.PowerSearchAgentDAO;
import com.manpower.portal.mpnet.dao.interfaces.RecommendedCandidatesDAO;
import com.manpower.portal.mpnet.dao.interfaces.RecommendedCandidatesPreferencesDAO;
import com.manpower.portal.mpnet.dao.interfaces.RecruiterAuditingDAO;
import com.manpower.portal.mpnet.dao.interfaces.RecruiterCommentsDAO;
import com.manpower.portal.mpnet.dao.interfaces.RecruiterLocationReportDAO;
import com.manpower.portal.mpnet.dao.interfaces.RecruiterReportDAO;
import com.manpower.portal.mpnet.dao.interfaces.RecruiterSearchAgentDAO;
import com.manpower.portal.mpnet.dao.interfaces.RegionDAO;
import com.manpower.portal.mpnet.dao.interfaces.ReportingMetricsDAO;
import com.manpower.portal.mpnet.dao.interfaces.SearchStatisticsDAO;
import com.manpower.portal.mpnet.dao.interfaces.SiteDAO;
import com.manpower.portal.mpnet.dao.interfaces.SkillsDAO;
import com.manpower.portal.mpnet.dao.interfaces.SteeringQuestionDAO;
import com.manpower.portal.mpnet.dao.interfaces.SummaryAdvertisementsDAO;
import com.manpower.portal.mpnet.dao.interfaces.VCandidateApplicationDAO;
import com.manpower.portal.mpnet.dao.interfaces.VCandidateResumeDAO;
import com.manpower.portal.mpnet.dao.interfaces.EventsDAO;
import com.manpower.portal.mpnet.hbn.beans.PowerSearchAgent;
/**
 * @author
 * 
 * Factory used for creation of all DAO objects.
 *  
 */
public abstract class DAOFactory {

	/**
	 * Create PostingDAO object.
	 * 
	 * 
	 * /** Create CandidateJobHistoryDAO object.
	 * 
	 * @return CandidateJobHistoryDAO object.
	 */
	public abstract CandidateJobHistoryDAO getCandidateJobHistoryDAO();

	/**
	 * Create CandidateDAO object.
	 * 
	 * @return CandidateDAO object
	 */
	public abstract CandidateDAO getCandidateDAO();

	/**
	 * Create AddressDAO object.
	 * 
	 * @return AddressDAO object
	 */
	public abstract AddressDAO getAddressDAO();

	/**
	 * Create AdvertisementDAO object.
	 * 
	 * @return AdvertisementDAO object
	 */
	public abstract AdvertisementDAO getAdvertisementDAO();

	/**
	 * Create CandidateCoverletterDAO object.
	 * 
	 * @return CandidateCoverletterDAO object
	 */
	public abstract CandidateCoverletterDAO getCandidateCoverLetterDAO();

	/**
	 * Create CandidateEducationDAO object.
	 * 
	 * @return CandidateEducationDAO object
	 */
	public abstract CandidateEducationDAO getCandidateEducationDAO();

	/**
	 * Create CandidateJobAgentsDAO object.
	 * 
	 * @return CandidateJobAgentsDAO object
	 */
	public abstract CandidateJobAgentsDAO getCandidateJobAgentsDAO();

	/**
	 * Create CandidateJobApplicationDAO object.
	 * 
	 * @return CandidateJobApplicationDAO object
	 */
	public abstract CandidateJobApplicationDAO getCandidateJobApplicationDAO();

	/**
	 * Create CandidatePreferencesDAO object.
	 * 
	 * @return CandidatePreferencesDAO object
	 */
	public abstract CandidatePreferencesDAO getCandidatePreferencesDAO();

	/**
	 * Create CandidateReferencesDAO object.
	 * 
	 * @return CandidateReferencesDAO object
	 */
	public abstract CandidateReferencesDAO getCandidateReferencesDAO();

	/**
	 * Create CandidateResumeDAO object.
	 * 
	 * @return CandidateResumeDAO object
	 */
	public abstract CandidateResumeDAO getCandidateResumeDAO();
	
//	public abstract MyManpowerResumeDAO getMyManpowerResumeDAO();

	/**
	 * Create CandidateSaveJobsDAO object.
	 * 
	 * @return CandidateSaveJobsDAO object
	 */
	public abstract CandidateSaveJobsDAO getCandidateSaveJobsDAO();

	/**
	 * Create CandidateSkillsDAO object.
	 * 
	 * @return CandidateSkillsDAO object
	 */
	public abstract CandidateSkillsDAO getCandidateSkillsDAO();

	/**
	 * Create SkillsDAO object.
	 * 
	 * @return SkillsDAO object.
	 */
	public abstract SkillsDAO getSkillsDAO();

	/**
	 * Create DomainValueDAO object.
	 * 
	 * @return DomainValueDAO object
	 */
	public abstract DomainValueDAO getDomainValueDAO();

	/**
	 * Create EnumerationDAO object.
	 * 
	 * @return EnumerationDAO object.
	 */
	public abstract EnumerationDAO getEnumerationDAO();

	/**
	 * Create BranchDAO object.
	 * 
	 * @return BranchDAO object.
	 */
	public abstract BranchDAO getBranchDAO();

	/**
	 * Create AdvertisementContactDAO object.
	 * 
	 * @return AdvertisementContactDAO object
	 */
	public abstract AdvertisementContactDAO getAdvertisementContactDAO();

	/**
	 * Create SteeringQuestionDAO object.
	 * 
	 * @return SteeringQuestionDAO object.
	 */
	public abstract SteeringQuestionDAO getSteeringQuestionDAO();

	/**
	 * Create EmailDAO object.
	 * 
	 * @return EmailDAO object.
	 */
	public abstract EmailDAO getEmailDAO();

	/**
	 * Create SiteDAO object.
	 * 
	 * @return SiteDAO object.
	 */
	public abstract SiteDAO getSiteDAO();

	/**
	 * Create PhoneDAO object.
	 * 
	 * @return PhoneDAO object.
	 */
	public abstract PhoneDAO getPhoneDAO();

	/**
	 * Create GeoLocationDAO object.
	 * 
	 * @return GeoLocationDAO object.
	 */
	public abstract GeoCodeDAO getGeoLocationDAO();

	/**
	 * Create LostCandidateDAO object.
	 * 
	 * @return LostCandidateDAO object.
	 */
	public abstract LostCandidateDAO getLostCandidateDAO();

	/**
	 * Create ConfigurationDAO object.
	 * 
	 * @return ConfigurationDAO object.
	 */
	public abstract ConfigurationDAO getConfigurationDAO();

	/**
	 * Create VCandidateResumeDAO object.
	 * 
	 * @return VCandidateResumeDAO object.
	 */
	public abstract VCandidateResumeDAO getVCandidateResumeDAO();

	/**
	 * Create VCandidateApplicationDAO object.
	 * 
	 * @return VCandidateApplicationDAO object.
	 */
	public abstract VCandidateApplicationDAO getVCandidateApplicationDAO();

	/**
	 * Create RegionDAO object.
	 * 
	 * @return RegionDAO object.
	 */
	public abstract RegionDAO getRegionDAO();

	/**
	 * Create DAOFactory object.
	 * 
	 * @return DAOFactory object.
	 */
	public static DAOFactory getDAOFactory() {
		return new DAOFactoryHibernate();
	}

	/**
	 * Create PostalCodeAddressDAO object.
	 * 
	 * @return PostalCodeAddressDAO object.
	 */
	public abstract PostalCodeAddressDAO getPostalCodeAddressDAO();

	/**
	 * Create CareerHarmonyDAO object.
	 * 
	 * @return CareerHarmonyDAO object.
	 */
	public abstract CareerHarmonyDAO getCareerHarmonyDAO();

	/**
	 * Create RecruiterReportDAO object.
	 * 
	 * @return RecruiterReportDAO object.
	 */
	public abstract RecruiterReportDAO getRecruiterReportDAO();

	/**
	 * Create RecruiterLocationReportDAO object.
	 * 
	 * @return RecruiterLocationReportDAO object.
	 */
	public abstract RecruiterLocationReportDAO getRecruiterLocationReportDAO();

	public abstract ConsentDAO getConsentDAO();

	/**
	 * Create CandidateResumeInfoDAO object.
	 * 
	 * @return CandidateResumeInfoDAO
	 */

	public abstract CandidateResumeInfoDAO getCandidateResumeInfoDAO();

	//public abstract OtherCandidateDetailsDAO getOtherCandidateDetailsDAO();

	public abstract IdentityDAO getIdentityDAO();

	/**
	 * Create ReportingMetricsDAO object.
	 * 
	 * @return reportingMetricsDAO
	 */

	public abstract ReportingMetricsDAO getReportingMetricsDAO();

	public abstract FlexFieldDAO getFlexFieldDAO();

	public abstract SummaryAdvertisementsDAO getSummaryAdvertisementsDAO();

	public abstract OtherCandidateDetailsDAO getOtherCandidateDetailsDAO();

	public abstract CandidatesAppliedDAO getCandidatesAppliedDAO();

	public abstract HistoryLogDAO getHistoryLogDAO();

	public abstract RecruiterCommentsDAO getRecruiterCommentsDAO();

	public abstract CandidateAssessmentsDAO getCandidateAssessmentsDAO();

	public abstract CandidateSearchResultsDAO getCandidateSearchResultsDAO();

	public abstract ApplicantResponseDAO getApplicantResponseDAO();

	public abstract ApplicantResponseDetailDAO getApplicantResponseDetailDAO();
	
	public abstract AssessmentProviderDAO getAssessmentProviderDAO();

	public abstract AssessmentRatingDAO getAssessmentRatingDAO();

	public abstract AssessmentTestDAO getAssessmentTestDAO();

	public abstract AssessmentDeliveryFormatDAO getAssessmentDeliveryFormatDAO();

	public abstract AssessmentSkillCategoryDAO getAssessmentSkillCategoryDAO();

	public abstract AssessmentSkillGroupDAO getAssessmentSkillGroupDAO();
	
	public abstract NA_JobTypeDAO getNA_JobTypeDAO();
	
	public abstract CountryRegionDAO getCountryRegionDAO();
	public abstract OtherSiteDetailsDAO getOtherSiteDetailsDAO();
	public abstract JobTitleDAO getJobTitleDAO();
    
    public abstract JobCategoryDAO getJobCategoryDAO();
	public abstract RecruiterAuditingDAO getRecruiterAuditingDAO();
	
	public abstract CandidateProfileStatusDAO getCandidateProfileStatusDAO();
	
	public abstract CandidateOccupationDAO getCandidateOccupationDAO();
	
	public abstract CandidatePreferredLocationsDAO getCandidatePreferredLocationsDAO();
	
	public abstract Advertisement_HistoryDAO getAdvertisement_HistoryDAO();
  
	public abstract EventsDAO getEventsDAO();
	
	public abstract CandidateExtraInfoDAO getCandidateExtraInfoDAO();
	
	public abstract DynaCacheDAO getDynaCacheDAO();
	
	public abstract SearchStatisticsDAO getSearchStatisticsDAO();
	
	public abstract CandidateResumeSummaryDAO getCandidateResumeSummaryDAO();
	public abstract ActiveAdvertDAO getActiveAdvertDAO();
	public abstract HierarchyLocationDAO getHierarchyLocationDAO();
	public abstract RecruiterSearchAgentDAO getRecruiterSearchAgentDAO();
	public abstract CandidateSearchDAO getCandidateSearchDAO();
	public abstract MobileAgentDAO getMobileAgentDAO();
	public abstract PowerSearchAgentDAO getPowerSearchAgentDAO();
	public abstract MobileDeviceDAO getMobileDeviceDAO();
	public abstract CredentialVaultDAO getCredentialVaultDAO();
	public abstract ApplicantGeoCodeDAO getApplicantGeoCodeService();
	public abstract RecommendedCandidatesPreferencesDAO getRecommendedCandidatesPreferencesDAO();
	public abstract RecommendedCandidatesDAO getRecommendedCandidatesDAO();
	
	public abstract PlainLocationDAO getPlainLocationDAO();
}
