/*
 * Created on Jan 20, 2006
 *
 */
package com.manpower.portal.mpnet.hbn.beans;

import java.util.Date;
import java.util.Set;

/**
 * @author jsingh
 * 
 * Persistent Class for table ADVERTISEMENTS
 */
public class Advertisement extends Base{
	
	private static final long serialVersionUID = 3841780697430643669L;

	private long 	id,
					careerHarmonyId,
					siteId;
	
	private float	lonCoordinate;
	private float	latCoordinate;

	private Date 	updatedOn,
					publicationDate,
					expirationDate;

	private String 	contractType,
					updatedBy,
					createdBy,
					changedBy,
					jobTitle,
					jobDescription,
					candidateProfile,
					candidateSkills,
					industrySector,
					location,
					clientName,
					payRange,
					referenceNumber,
					regLevel,
					hoursWorked,
					assessmentTest,
					language,
					externalId,
					businessArea,
					jobCountry,
					loc_freeform,
					jobType,
					jobClass,
					jobCode,
					frontOfficeAdvertId ,
					certifications,
					degreeArea,
					degreeType,
					jobDuration,
					durationDetail,
					experienceMin,
					salaryUnit,
					jobTemplateType,
					office,
					accountingUnit,
					teaserText,
					postalCode1,
					postalCode2,
					postalCode3,
					notificationEmail,
					appliedFor,
					fromFrontOffice,
					extJoBoards;
	
	private AdvertisementContact adContact;
	
	private Set sites;
	private Set locations;
	private Set languages;
	private Set extSites;
	
	private int numVacancies;
	private float maxRate;
	private float minRate;
	
	private boolean resumeNotRequired;
	
	/**
	 * @return Returns the expirationDate.
	 */
	public Date getExpirationDate() {
		return expirationDate;
	}
	/**
	 * @param expirationDate The expirationDate to set.
	 */
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	/**
	 * @return Returns the regLevel.
	 */
	public String getRegLevel() {
		return regLevel;
	}
	/**
	 * @param regLevel The regLevel to set.
	 */
	public void setRegLevel(String regLevel) {
		this.regLevel = regLevel;
	}
	/**
	 * @return Returns the candidateProfile.
	 */
	public String getCandidateProfile() {
		return candidateProfile;
	}
	/**
	 * @param candidateProfile The candidateProfile to set.
	 */
	public void setCandidateProfile(String candidateProfile) {
		this.candidateProfile = candidateProfile;
	}
	/**
	 * @return Returns the candidateSkills.
	 */
	public String getCandidateSkills() {
		return candidateSkills;
	}
	/**
	 * @param candidateSkills The candidateSkills to set.
	 */
	public void setCandidateSkills(String candidateSkills) {
		this.candidateSkills = candidateSkills;
	}
	/**
	 * @return Returns the contractType.
	 */
	public String getContractType() {
		return contractType;
	}
	/**
	 * @param contractType The contractType to set.
	 */
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	/**
	 * @return Returns the industrySector.
	 */
	public String getIndustrySector() {
		return industrySector;
	}
	/**
	 * @param industrySector The industrySector to set.
	 */
	public void setIndustrySector(String industrySector) {
		this.industrySector = industrySector;
	}
	/**
	 * @return Returns the jobDescription.
	 */
	public String getJobDescription() {
		return jobDescription;
	}
	/**
	 * @param jobDescription The jobDescription to set.
	 */
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	/**
	 * @return Returns the jobTitle.
	 */
	public String getJobTitle() {
		return jobTitle;
	}
	/**
	 * @param jobTitle The jobTitle to set.
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	/**
	 * @return Returns the location.
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location The location to set.
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return Returns hoursWorked.
	 */
	public String getHoursWorked() {
		return hoursWorked;
	}
	/**
	 * @param hoursWorked The hoursWorked to set.
	 */
	public void setHoursWorked(String hoursWorked) {
		this.hoursWorked = hoursWorked;
	}
	/**
	 * @return Returns latCoordinate.
	 */
	public float getLatCoordinate() {
		return latCoordinate;
	}
	/**
	 * @param latCoordinate The latCoordinate to set.
	 */
	public void setLatCoordinate(float latCoordinate) {
		this.latCoordinate = latCoordinate;
	}
	/**
	 * @return Returns lonCoordinate.
	 */
	public float getLonCoordinate() {
		return lonCoordinate;
	}
	/**
	 * @param lonCoordinate The lonCoordinate to set.
	 */
	public void setLonCoordinate(float lonCoordinate) {
		this.lonCoordinate = lonCoordinate;
	}
	/**
	 * @return Returns the id.
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return Returns the siteId.
	 */
	public long getSiteId() {
		return siteId;
	}
	/**
	 * @param siteId The siteId to set.
	 */
	public void setSiteId(long siteId) {
		this.siteId = siteId;
	}
	/**
	 * @return Returns the updatedBy.
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}
	/**
	 * @param updatedBy The updatedBy to set.
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	/**
	 * @return Returns the updatedOn.
	 */
	public Date getUpdatedOn() {
		return updatedOn;
	}
	/**
	 * @param updatedOn The updatedOn to set.
	 */
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	/**
	 * @return Returns the clientName.
	 */
	public String getClientName() {
		return clientName;
	}
	/**
	 * @param clientName The clientName to set.
	 */
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	/**
	 * @return Returns the payRange.
	 */
	public String getPayRange() {
		return payRange;
	}
	/**
	 * @param payRange The payRange to set.
	 */
	public void setPayRange(String payRange) {
		this.payRange = payRange;
	}
	/**
	 * @return Returns the publicationDate.
	 */
	public Date getPublicationDate() {
		return publicationDate;
	}
	/**
	 * @param publicationDate The publicationDate to set.
	 */
	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}
	/**
	 * @return Returns the referenceNumber.
	 */
	public String getReferenceNumber() {
		return referenceNumber;
	}
	/**
	 * @param referenceNumber The referenceNumber to set.
	 */
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	/**
	 * @return Returns the adContact.
	 */
	public AdvertisementContact getAdvertisementContact() {
		return adContact;
	}
	/**
	 * @param adContact The adContact to set.
	 */
	public void setAdvertisementContact(AdvertisementContact adContact) {
		this.adContact = adContact;
	}
	/**
	 * @return Returns the assessmentTest.
	 */
	public String getAssessmentTest() {
		return assessmentTest;
	}
	/**
	 * @param assessmentTest The assessmentTest to set.
	 */
	public void setAssessmentTest(String assessmentTest) {
		this.assessmentTest = assessmentTest;
	}
	/**
	 * @return Returns the language.
	 */
	public String getLanguage() {
		return language;
	}
	/**
	 * @param language The language to set.
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	/**
	 * @return Returns the externalId.
	 */
	public String getExternalId() {
		return externalId;
	}
	/**
	 * @param externalId The externalId to set.
	 */
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	/**
	 * @return Returns the businessArea.
	 */
	public String getBusinessArea() {
		return businessArea;
	}
	/**
	 * @param businessArea The businessArea to set.
	 */
	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}

	/**
	 * 
	 * @return Returns Phone
	 */
	public String getPhone(){
	
		AdvertisementContact contact = getAdvertisementContact();
		
		if(contact != null){
		
			return contact.getPhone();
			
		}else{
			return null;
		}
		
	}
	
	/**
	 * 
	 * @param phone The Phone to set
	 */
	public void setPhone(String phone){
			
	}

	/**
	 * @return Returns the careerHarmonyId.
	 */
	public long getCareerHarmonyId() {
		return careerHarmonyId;
	}
	/**
	 * @param careerHarmonyId The careerHarmonyId to set.
	 */
	public void setCareerHarmonyId(long careerHarmonyId) {
		this.careerHarmonyId = careerHarmonyId;
	}
	public String getContactName(){
		return getAdvertisementContact().getContactName();
	}
	
	public void setContactName(String name){}{
		
	}
	/**
	 * @return Returns the jobCountry.
	 */
	public String getJobCountry() {
		return jobCountry;
	}
	/**
	 * @param jobCountry The jobCountry to set.
	 */
	public void setJobCountry(String jobCountry) {
		this.jobCountry = jobCountry;
	}

	/**
	 * @return Returns the loc_freeform.
	 */
	public String getLoc_freeform() {
		return loc_freeform;
	}
	/**
	 * @param loc_freeform The loc_freeform to set.
	 */
	public void setLoc_freeform(String loc_freeform) {
		this.loc_freeform = loc_freeform;
	}

    /**
     * @return Returns the languages.
     */
    public Set getLanguages()
    {
        return languages;
    }
    /**
     * @param languages The languages to set.
     */
    public void setLanguages(Set languages)
    {
        this.languages = languages;
    }
    /**
     * @return Returns the locations.
     */
    public Set getLocations()
    {
        return locations;
    }
    /**
     * @param locations The locations to set.
     */
    public void setLocations(Set locations)
    {
        this.locations = locations;
    }
    /**
     * @return Returns the sites.
     */
    public Set getSites()
    {
        return sites;
    }
    /**
     * @param sites The sites to set.
     */
    public void setSites(Set sites)
    {
        this.sites = sites;
    }
    /**
     * @return Returns the extSites.
     */
    public Set getExtSites()
    {
        return extSites;
    }
    /**
     * @param extSites The extSites to set.
     */
    public void setExtSites(Set extSites)
    {
        this.extSites = extSites;
    }
    /**
	 * @return Returns the certifications.
	 */
	public String getCertifications() {
		return certifications;
	}
	/**
	 * @param certifications The certifications to set.
	 */
	public void setCertifications(String certifications) {
		this.certifications = certifications;
	}
	/**
	 * @return Returns the degreeArea.
	 */
	public String getDegreeArea() {
		return degreeArea;
	}
	/**
	 * @param degreeArea The degreeArea to set.
	 */
	public void setDegreeArea(String degreeArea) {
		this.degreeArea = degreeArea;
	}
	/**
	 * @return Returns the degreeType.
	 */
	public String getDegreeType() {
		return degreeType;
	}
	/**
	 * @param degreeType The degreeType to set.
	 */
	public void setDegreeType(String degreeType) {
		this.degreeType = degreeType;
	}
	/**
	 * @return Returns the durationDetail.
	 */
	public String getDurationDetail() {
		return durationDetail;
	}
	/**
	 * @param durationDetail The durationDetail to set.
	 */
	public void setDurationDetail(String durationDetail) {
		this.durationDetail = durationDetail;
	}
	/**
	 * @return Returns the experienceMin.
	 */
	public String getExperienceMin() {
		return experienceMin;
	}
	/**
	 * @param experienceMin The experienceMin to set.
	 */
	public void setExperienceMin(String experienceMin) {
		this.experienceMin = experienceMin;
	}
	/**
	 * @return Returns the frontOfficeAdvertId.
	 */
	public String getFrontOfficeAdvertId() {
		return frontOfficeAdvertId;
	}
	/**
	 * @param frontOfficeAdvertId The frontOfficeAdvertId to set.
	 */
	public void setFrontOfficeAdvertId(String frontOfficeAdvertId) {
		this.frontOfficeAdvertId = frontOfficeAdvertId;
	}

	/**
	 * @return Returns the jobClass.
	 */
	public String getJobClass() {
		return jobClass;
	}
	/**
	 * @param jobClass The jobClass to set.
	 */
	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}
	/**
	 * @return Returns the jobCode.
	 */
	public String getJobCode() {
		return jobCode;
	}
	/**
	 * @param jobCode The jobCode to set.
	 */
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}
	/**
	 * @return Returns the jobDuration.
	 */
	public String getJobDuration() {
		return jobDuration;
	}
	/**
	 * @param jobDuration The jobDuration to set.
	 */
	public void setJobDuration(String jobDuration) {
		this.jobDuration = jobDuration;
	}
	/**
	 * @return Returns the jobTemplateType.
	 */
	public String getJobTemplateType() {
		return jobTemplateType;
	}
	/**
	 * @param jobTemplateType The jobTemplateType to set.
	 */
	public void setJobTemplateType(String jobTemplateType) {
		this.jobTemplateType = jobTemplateType;
	}

	/**
	 * @return Returns the maxRate.
	 */
	public float getMaxRate() {
		return maxRate;
	}
	/**
	 * @param maxRate The maxRate to set.
	 */
	public void setMaxRate(float maxRate) {
		this.maxRate = maxRate;
	}
	/**
	 * @return Returns the minRate.
	 */
	public float getMinRate() {
		return minRate;
	}
	/**
	 * @param minRate The minRate to set.
	 */
	public void setMinRate(float minRate) {
		this.minRate = minRate;
	}
	/**
	 * @return Returns the numVacancies.
	 */
	public int getNumVacancies() {
		return numVacancies;
	}
	/**
	 * @param numVacancies The numVacancies to set.
	 */
	public void setNumVacancies(int numVacancies) {
		this.numVacancies = numVacancies;
	}
	/**
	 * @return Returns the salaryUnit.
	 */
	public String getSalaryUnit() {
		return salaryUnit;
	}
	/**
	 * @param salaryUnit The salaryUnit to set.
	 */
	public void setSalaryUnit(String salaryUnit) {
		this.salaryUnit = salaryUnit;
	}
	/**
	 * @return Returns the accountingUnit.
	 */
	public String getAccountingUnit() {
		return accountingUnit;
	}
	/**
	 * @param accountingUnit The accountingUnit to set.
	 */
	public void setAccountingUnit(String accountingUnit) {
		this.accountingUnit = accountingUnit;
	}
	/**
	 * @return Returns the office.
	 */
	public String getOffice() {
		return office;
	}
	/**
	 * @param office The office to set.
	 */
	public void setOffice(String office) {
		this.office = office;
	}
	/**
	 * @return Returns the teaserText.
	 */
	public String getTeaserText() {
		return teaserText;
	}
	/**
	 * @param teaserText The teaserText to set.
	 */
	public void setTeaserText(String teaserText) {
		this.teaserText = teaserText;
	}
	/**
	 * @return Returns the jobType.
	 */
	public String getJobType() {
		return jobType;
	}
	/**
	 * @param jobType The jobType to set.
	 */
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	/**
	 * @return Returns the postalCode1.
	 */
	public String getPostalCode1() {
		return postalCode1;
	}
	/**
	 * @param postalCode1 The postalCode1 to set.
	 */
	public void setPostalCode1(String postalCode1) {
		this.postalCode1 = postalCode1;
	}
	/**
	 * @return Returns the postalCode2.
	 */
	public String getPostalCode2() {
		return postalCode2;
	}
	/**
	 * @param postalCode2 The postalCode2 to set.
	 */
	public void setPostalCode2(String postalCode2) {
		this.postalCode2 = postalCode2;
	}
	/**
	 * @return Returns the postalCode3.
	 */
	public String getPostalCode3() {
		return postalCode3;
	}
	/**
	 * @param postalCode3 The postalCode3 to set.
	 */
	public void setPostalCode3(String postalCode3) {
		this.postalCode3 = postalCode3;
	}
	
	
    /**
     * @return Returns the changedBy.
     */
    public String getChangedBy()
    {
        return changedBy;
    }
    /**
     * @param changedBy The changedBy to set.
     */
    public void setChangedBy(String changedBy)
    {
        this.changedBy = changedBy;
    }
    /**
     * @return Returns the createdBy.
     */
    public String getCreatedBy()
    {
        return createdBy;
    }
    /**
     * @param createdBy The createdBy to set.
     */
    public void setCreatedBy(String createdBy)
    {
        this.createdBy = createdBy;
    }
	/**
	 * @return Returns the notificationEmail.
	 */
	public String getNotificationEmail() {
		return notificationEmail;
	}
	/**
	 * @param notificationEmail The notificationEmail to set.
	 */
	public void setNotificationEmail(String notificationEmail) {
		this.notificationEmail = notificationEmail;
	}
	/**
	 * @return Returns the appliedFor.
	 */
	public String getAppliedFor() {
		return appliedFor;
	}
	/**
	 * @param appliedFor The appliedFor to set.
	 */
	public void setAppliedFor(String appliedFor) {
		this.appliedFor = appliedFor;
	}
	/**
	 * @return Returns the fromFrontOffice.
	 */
	public String getFromFrontOffice() {
		return fromFrontOffice;
	}
	/**
	 * @param fromFrontOffice The fromFrontOffice to set.
	 */
	public void setFromFrontOffice(String fromFrontOffice) {
		this.fromFrontOffice = fromFrontOffice;
	}
	
	
    /**
     * @return Returns the extJoBoards.
     */
    public String getExtJoBoards()
    {
        return extJoBoards;
    }
    /**
     * @param extJoBoards The extJoBoards to set.
     */
    public void setExtJoBoards(String extJoBoards)
    {
        this.extJoBoards = extJoBoards;
    }
	public boolean isResumeNotRequired() {
		return resumeNotRequired;
	}
	public void setResumeNotRequired(boolean resumeRequired) {
		this.resumeNotRequired = resumeRequired;
	}
}
