/*
 * Created on Jan 16, 2006

 */
package com.manpower.portal.mpnet.hbn.beans;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author jsingh
 *
 * Persistent class for table CANDIDATES
 */
public class Candidate extends Base{
	
  private static final long serialVersionUID = 7966475924128069918L;

   private long id;
	 private long siteId;
	 private String status;
	 private Date workPermitExpDate;
	 private String workPermits;
	 private String nationality;
	 private String residenceStatus;
	 private String residencePermit;
	 private Date residencePermitExpDate;
	 private Date passportExpDate;
	 private long branchId;
	 private Date updatedOn;
	 private String updatedBy;
	 private String firstName;
	 private String middleName;
	 private String lastName;
	 private String nationalNumber;
	 private Date birthdate;
	 private String birthplace;
	 private String gender;
	 private String maritialStatus;
	 private String email;
	 private String password;
	 private String passwordHintQuestion;
	 private String passwordHintAnswer;
	 private String userapplicationlanguage;
	 private String contactByEmail;
	 private String regLevel;
	 private String correspondEmail;
	 private String nativeLanguage;
	 private int badPwdCount;

	 /**
	 * @return Returns the correspondEmail.
	 */
	public String getCorrespondEmail() {
		return correspondEmail;
	}
	/**
	 * @param correspondEmail The correspondEmail to set.
	 */
	public void setCorrespondEmail(String correspondEmail) {
		this.correspondEmail = correspondEmail;
	}

	  
	 private Set 	history =new HashSet(),
					coverLetter = new HashSet(),
					education =  new HashSet(),
					jobAgents = new HashSet(),
					jobApplication = new HashSet(),
					preferences = new HashSet(),
					references = new HashSet(),
					resume = new HashSet(),
					saveJobs = new HashSet(),
					skills = new HashSet(),
					address = new HashSet(),
					phone	= new HashSet(),
					otherCandidateDetails = new HashSet(),
					preferredLocations = new HashSet();
	 
	/**
	 * @return Returns the email.
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email The email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return Returns the passwordHintAnswer.
	 */
	public String getPasswordHintAnswer() {
		return passwordHintAnswer;
	}
	/**
	 * @param passwordHintAnswer The passwordHintAnswer to set.
	 */
	public void setPasswordHintAnswer(String passwordHintAnswer) {
		this.passwordHintAnswer = passwordHintAnswer;
	}
	/**
	 * @return Returns the passwordHintQuestion.
	 */
	public String getPasswordHintQuestion() {
		return passwordHintQuestion;
	}
	/**
	 * @param passwordHintQuestion The passwordHintQuestion to set.
	 */
	public void setPasswordHintQuestion(String passwordHintQuestion) {
		this.passwordHintQuestion = passwordHintQuestion;
	}
	/**
	 * @return Returns the userapplicationlanguage.
	 */
	public String getUserapplicationlanguage() {
		return userapplicationlanguage;
	}
	/**
	 * @param userapplicationlanguage The userapplicationlanguage to set.
	 */
	public void setUserapplicationlanguage(String userapplicationlanguage) {
		this.userapplicationlanguage = userapplicationlanguage;
	}
	/**
	 * @return Returns the birthdate.
	 */
	public Date getBirthdate() {
		return birthdate;
	}
	/**
	 * @param birthdate The birthdate to set.
	 */
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	/**
	 * @return Returns the birthplace.
	 */
	public String getBirthplace() {
		return birthplace;
	}
	/**
	 * @param birthplace The birthplace to set.
	 */
	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}
	/**
	 * @return Returns the firstName.
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName The firstName to set.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return Returns the gender.
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender The gender to set.
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return Returns the lastName.
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName The lastName to set.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return Returns the maritialStatus.
	 */
	public String getMaritialStatus() {
		return maritialStatus;
	}
	/**
	 * @param maritialStatus The maritialStatus to set.
	 */
	public void setMaritialStatus(String maritialStatus) {
		this.maritialStatus = maritialStatus;
	}
	/**
	 * @return Returns the middleName.
	 */
	public String getMiddleName() {
		return middleName;
	}
	/**
	 * @param middleName The middleName to set.
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	/**
	 * @return Returns the nationalNumber.
	 */
	public String getNationalNumber() {
		return nationalNumber;
	}
	/**
	 * @param nationalNumber The nationalNumber to set.
	 */
	public void setNationalNumber(String nationalNumber) {
		this.nationalNumber = nationalNumber;
	}
	
	
	/**
	 * @return Returns the address.
	 */
	public Set getAddress() {
		return address;
	}
	/**
	 * @param address The address to set.
	 */
	public void setAddress(Set address) {
		this.address = address;
	}
	/**
	 * @return Returns the phone.
	 */
	public Set getPhone() {
		return phone;
	}
	/**
	 * @param phone The phone to set.
	 */
	public void setPhone(Set phone) {
		this.phone = phone;
	}
	/**
	 * @return Returns the coverLetter.
	 */
	public Set getCoverLetter() {
		return coverLetter;
	}
	/**
	 * @param coverLetter The coverLetter to set.
	 */
	public void setCoverLetter(Set coverLetter) {
		this.coverLetter = coverLetter;
	}
	/**
	 * @return Returns the education.
	 */
	public Set getEducation() {
		return education;
	}
	/**
	 * @param education The education to set.
	 */
	public void setEducation(Set education) {
		this.education = education;
	}
	/**
	 * @return Returns the jobAgents.
	 */
	public Set getJobAgents() {
		return jobAgents;
	}
	/**
	 * @param jobAgents The jobAgents to set.
	 */
	public void setJobAgents(Set jobAgents) {
		this.jobAgents = jobAgents;
	}
	/**
	 * @return Returns the jobApplication.
	 */
	public Set getJobApplication() {
		return jobApplication;
	}
	/**
	 * @param jobApplication The jobApplication to set.
	 */
	public void setJobApplication(Set jobApplication) {
		this.jobApplication = jobApplication;
	}
	/**
	 * @return Returns the preferences.
	 */
	public Set getPreferences() {
		return preferences;
	}
	/**
	 * @param preferences The preferences to set.
	 */
	public void setPreferences(Set preferences) {
		this.preferences = preferences;
	}
	/**
	 * @return Returns the references.
	 */
	public Set getReferences() {
		return references;
	}
	/**
	 * @param references The references to set.
	 */
	public void setReferences(Set references) {
		this.references = references;
	}
	/**
	 * @return Returns the resume.
	 */
	public Set getResume() {
		return resume;
	}
	/**
	 * @param resume The resume to set.
	 */
	public void setResume(Set resume) {
		this.resume = resume;
	}
	/**
	 * @return Returns the saveJobs.
	 */
	public Set getSaveJobs() {
		return saveJobs;
	}
	/**
	 * @param saveJobs The saveJobs to set.
	 */
	public void setSaveJobs(Set saveJobs) {
		this.saveJobs = saveJobs;
	}
	/**
	 * @return Returns the skills.
	 */
	public Set getSkills() {
		return skills;
	}
	/**
	 * @param skills The skills to set.
	 */
	public void setSkills(Set skills) {
		this.skills = skills;
	}
	
	/**
	 * @return Returns the branchId.
	 */
	public long getBranchId() {
		return branchId;
	}
	/**
	 * @param branchId The branchId to set.
	 */
	public void setBranchId(long branchId) {
		this.branchId = branchId;
	}
	/**
	 * @return Returns the history.
	 */
	public Set getHistory() {
		return history;
	}
	/**
	 * @param history The history to set.
	 */
	public void setHistory(Set history) {
		this.history = history;
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
	 * @return Returns the nationality.
	 */
	public String getNationality() {
		return nationality;
	}
	/**
	 * @param nationality The nationality to set.
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	/**
	 * @return Returns the passportExpDate.
	 */
	public Date getPassportExpDate() {
		return passportExpDate;
	}
	/**
	 * @param passportExpDate The passportExpDate to set.
	 */
	public void setPassportExpDate(Date passportExpDate) {
		this.passportExpDate = passportExpDate;
	}

	/**
	 * @return Returns the residencePermintExpDate.
	 */
	public Date getResidencePermitExpDate() {
		return residencePermitExpDate;
	}
	/**
	 * @param residencePermintExpDate The residencePermintExpDate to set.
	 */
	public void setResidencePermitExpDate(Date residencePermintExpDate) {
		this.residencePermitExpDate = residencePermintExpDate;
	}
	/**
	 * @return Returns the residencePermit.
	 */
	public String getResidencePermit() {
		return residencePermit;
	}
	/**
	 * @param residencePermit The residencePermit to set.
	 */
	public void setResidencePermit(String residencePermit) {
		this.residencePermit = residencePermit;
	}
	/**
	 * @return Returns the residenceStatus.
	 */
	public String getResidenceStatus() {
		return residenceStatus;
	}
	/**
	 * @param residenceStatus The residenceStatus to set.
	 */
	public void setResidenceStatus(String residenceStatus) {
		this.residenceStatus = residenceStatus;
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
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * @return Returns the workPermitExpDate.
	 */
	public Date getWorkPermitExpDate() {
		return workPermitExpDate;
	}
	/**
	 * @param workPermitExpDate The workPermitExpDate to set.
	 */
	public void setWorkPermitExpDate(Date workPermitExpDate) {
		this.workPermitExpDate = workPermitExpDate;
	}
	/**
	 * @return Returns the workPermits.
	 */
	public String getWorkPermits() {
		return workPermits;
	}
	/**
	 * @param workPermits The workPermits to set.
	 */
	public void setWorkPermits(String workPermits) {
		this.workPermits = workPermits;
	}
	/**
	 * @return Returns the contactByEmail.
	 */
	public String getContactByEmail() {
		return contactByEmail;
	}
	/**
	 * @param contactByEmail The contactByEmail to set.
	 */
	public void setContactByEmail(String contactByEmail) {
		this.contactByEmail = contactByEmail;
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
	 * @return Returns the nativeLanguage.
	 */
	public String getNativeLanguage() {
		return nativeLanguage;
	}
	/**
	 * @param nativeLanguage The nativeLanguage to set.
	 */
	public void setNativeLanguage(String nativeLanguage) {
		this.nativeLanguage = nativeLanguage;
	}
	/**
	 * @return Returns the otherCandidateDetails.
	 */
	public Set getOtherCandidateDetails() {
		return otherCandidateDetails;
	}
	/**
	 * @param otherCandidateDetails The otherCandidateDetails to set.
	 */
	public void setOtherCandidateDetails(Set otherCandidateDetails) {
		this.otherCandidateDetails = otherCandidateDetails;
	}
	/**
	 * @return Returns the preferredLocations.
	 */
	public Set getPreferredLocations() {
		return preferredLocations;
	}
	/**
	 * @param preferredLocations The preferredLocations to set.
	 */
	public void setPreferredLocations(Set preferredLocations) {
		this.preferredLocations = preferredLocations;
	}

	public int getBadPwdCount()
  {
    return badPwdCount;
  }
  public void setBadPwdCount(int badPwdCount)
  {
    this.badPwdCount = badPwdCount;
  }

}
