package com.manpower.portal.mpnet.hbn.beans;

import java.util.Date;
import java.util.List;

public class CandidateProfileSearch {
	private static final long serialVersionUID = 8841555337621286636L;
	// unknown candidate search
    private Date beginLastDateCandidateLoggedIn;
    private Date endLastDateCandidateLoggedIn;
    
    private String residenceStatus;
    private String motherTongue;
    private String workPermit;
    
    private String positionType;
    private String willingToRelocateWithinCountry;
    private String willingToRelocateToAnotherCountry;
    private String jobTitle;
    private String workExperience;
    private String educationLevel;
    private String preferredBranch;
    private Date beginRegistrationDate;
    private Date endRegistrationDate;
    
    private Date beginCandidateStartDate;
    private Date endCandidateStartDate;
    
    private List preferredWorkLocation;
    private List skills;
    private List industrySector;
    
    // boolean fields for condition on other candidate detail
    private boolean beginLastDateCandidateLoggedInProvided=false;
    private boolean endLastDateCandidateLoggedInProvided=false;

    // boolean fields for condition on candidates
    private boolean residenceStatusProvided=false;
    private boolean motherTongueProvided=false;
    private boolean workPermitProvided=false;
    private boolean preferredBranchProvided=false;
    private boolean beginRegistrationDateProvided=false;
    private boolean endRegistrationDateProvided=false;
    
    
    // boolean fields for condition on candidate preferences
    private boolean preferredWorkLocationProvided=false;
    private boolean positionTypeProvided=false;
    private boolean willingToRelocateWithinCountryProvided=false;
    private boolean willingToRelocateToAnotherCountryProvided=false;
    private boolean industrySectorProvided=false;    
    private boolean jobTitleProvided=false;    
    private boolean beginCandidateStartDateProvided=false;
    private boolean endCandidateStartDateProvided=false;
    
    
    // boolean fields for condition on candidate job history
    private boolean workExperienceProvided=false;
    
//  boolean fields for condition on candidate education
    private boolean educationLevelProvided=false;
    
//  boolean fields for condition on candidate skills
    private boolean skillsProvided=false;

    
    
    public boolean ifJoinCandidatePreferences(){
    	return (positionTypeProvided ||
		willingToRelocateWithinCountryProvided || willingToRelocateToAnotherCountryProvided ||
		industrySectorProvided || jobTitleProvided ||
		(beginCandidateStartDateProvided && endCandidateStartDateProvided));
    }
    
    public boolean ifJoinPreferredWorkLocation() {
        return preferredWorkLocationProvided;
    }
    
    public boolean ifJoinOtherCandidateDetails(){
    	return (beginLastDateCandidateLoggedInProvided && endLastDateCandidateLoggedInProvided);
    }
    
    public boolean ifJoinCandidateJobHistory(){
    	return workExperienceProvided;
    }
    
    public boolean ifJoinCandidateEducation(){
    	return educationLevelProvided;
    }

    public boolean ifJoinCandidateSkills(){
    	return skillsProvided;
    }

    public String toString(){
    	String str = "";
    	str += " beginlastDateCandidateLoggedIn :  " +beginLastDateCandidateLoggedIn +"\n"; 
    	str += " endLastDateCandidateLoggedIn :  " +endLastDateCandidateLoggedIn +"\n";
    	str += " residenceStatus :  " +residenceStatus+"\n";
    	str += " motherTongue :  " +motherTongue+"\n";
    	str += " workPermit :  " +workPermit+"\n";
    	str += " positionType :  " +positionType+"\n";
    	str += " willingToRelocateWithinCountry :  " +willingToRelocateWithinCountry+"\n";
    	str += " willingToRelocateToAnotherCountry :  " +willingToRelocateToAnotherCountry+"\n";
    	
    	str += " jobTitle :  " +jobTitle+"\n";
    	str += " workExperience :  " +workExperience+"\n";
    	str += " educationLevel :  " +educationLevel+"\n";
    	
    	str += " preferredBranch :  " +preferredBranch+"\n";
    	
    	str += " beginRegistrationDate :  " +beginRegistrationDate+"\n";
    	str += " endRegistrationDate :  " +endRegistrationDate+"\n";
    	str += " beginCandidateStartDate :  " +beginCandidateStartDate+"\n";
    	str += " endCandidateStartDate :  " +endCandidateStartDate+"\n";
    	
    	return str;
    	
    }
    
	/**
	 * @return Returns the industrySector.
	 */
	public List getIndustrySector() {
		return industrySector;
	}
	/**
	 * @param industrySector The industrySector to set.
	 */
	public void setIndustrySector(List industrySector) {
		this.industrySector = industrySector;
		if(industrySector !=null && industrySector.size()>0){
			industrySectorProvided = true;
		}
	}
	/**
	 * @return Returns the preferredWorkLocation.
	 */
	public List getPreferredWorkLocation() {
		return preferredWorkLocation;
		
	}
	/**
	 * @param preferredWorkLocation The preferredWorkLocation to set.
	 */
	public void setPreferredWorkLocation(List preferredWorkLocation) {
		this.preferredWorkLocation = preferredWorkLocation;
		if(preferredWorkLocation!=null && preferredWorkLocation.size()>0){
			preferredWorkLocationProvided = true;
		}
	}
	/**
	 * @return Returns the skills.
	 */
	public List getSkills() {
		return skills;
	}
	/**
	 * @param skills The skills to set.
	 */
	public void setSkills(List skills) {
		this.skills = skills;
		if(skills!=null && skills.size()>0){
			skillsProvided = true;
		}
		
	}
    
	/**
	 * @return Returns the beginCandidateStartDate.
	 */
	public Date getBeginCandidateStartDate() {
		return beginCandidateStartDate;
	}
	/**
	 * @param beginCandidateStartDate The beginCandidateStartDate to set.
	 */
	public void setBeginCandidateStartDate(Date beginCandidateStartDate) {
		this.beginCandidateStartDate = beginCandidateStartDate;
		if(beginCandidateStartDate !=null){
			beginCandidateStartDateProvided=true;
		}
	}
	/**
	 * @return Returns the beginRegistrationDate.
	 */
	public Date getBeginRegistrationDate() {
		return beginRegistrationDate;
	}
	/**
	 * @param beginRegistrationDate The beginRegistrationDate to set.
	 */
	public void setBeginRegistrationDate(Date beginRegistrationDate) {
		this.beginRegistrationDate = beginRegistrationDate;
		if(beginRegistrationDate !=null){
			beginRegistrationDateProvided = true;
		}
	}
	/**
	 * @return Returns the educationLevel.
	 */
	public String getEducationLevel() {
		return educationLevel;
	}
	/**
	 * @param educationLevel The educationLevel to set.
	 */
	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
		if(!isStringNullOrEmpty(educationLevel)){
			educationLevelProvided=true;
		}
	}
	/**
	 * @return Returns the endCandidateStartDate.
	 */
	public Date getEndCandidateStartDate() {
		return endCandidateStartDate;
	}
	/**
	 * @param endCandidateStartDate The endCandidateStartDate to set.
	 */
	public void setEndCandidateStartDate(Date endCandidateStartDate) {
		this.endCandidateStartDate = endCandidateStartDate;
		if(endCandidateStartDate !=null){
			endCandidateStartDateProvided = true;
		}
	}
	/**
	 * @return Returns the endRegistrationDate.
	 */
	public Date getEndRegistrationDate() {
		return endRegistrationDate;
	}
	/**
	 * @param endRegistrationDate The endRegistrationDate to set.
	 */
	public void setEndRegistrationDate(Date endRegistrationDate) {
		this.endRegistrationDate = endRegistrationDate;
		if(endRegistrationDate !=null){
			endRegistrationDateProvided = true;
		}
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
		if(!isStringNullOrEmpty(jobTitle)){
			jobTitleProvided=true;
		}

	}
	/**
	 * @return Returns the motherTongue.
	 */
	public String getMotherTongue() {
		return motherTongue;
	}
	/**
	 * @param motherTongue The motherTongue to set.
	 */
	public void setMotherTongue(String motherTongue) {
		this.motherTongue = motherTongue;
		if(!isStringNullOrEmpty(motherTongue)){
			motherTongueProvided=true;
		}

	}
	/**
	 * @return Returns the positionType.
	 */
	public String getPositionType() {
		return positionType;
	}
	/**
	 * @param positionType The positionType to set.
	 */
	public void setPositionType(String positionType) {
		this.positionType = positionType;
		if(!isStringNullOrEmpty(positionType)){
			positionTypeProvided=true;
		}
	
	}
	/**
	 * @return Returns the preferredBranch.
	 */
	public String getPreferredBranch() {
		return preferredBranch;
	}
	/**
	 * @param preferredBranch The preferredBranch to set.
	 */
	public void setPreferredBranch(String preferredBranch) {
		this.preferredBranch = preferredBranch;
		if(!isStringNullOrEmpty(preferredBranch)){
			preferredBranchProvided=true;
		}

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
		if(!isStringNullOrEmpty(residenceStatus)){
			residenceStatusProvided=true;
		}
		
	}
	/**
	 * @return Returns the willingToRelocateToAnotherCountry.
	 */
	public String getWillingToRelocateToAnotherCountry() {
		return willingToRelocateToAnotherCountry;
	}
	/**
	 * @param willingToRelocateToAnotherCountry The willingToRelocateToAnotherCountry to set.
	 */
	public void setWillingToRelocateToAnotherCountry(
			String willingToRelocateToAnotherCountry) {
		this.willingToRelocateToAnotherCountry = willingToRelocateToAnotherCountry;
		if(!isStringNullOrEmpty(willingToRelocateToAnotherCountry)){
			willingToRelocateToAnotherCountryProvided=true;
		}
		
	}
	/**
	 * @return Returns the willingToRelocateWithinCountry.
	 */
	public String getWillingToRelocateWithinCountry() {
		return willingToRelocateWithinCountry;
	}
	/**
	 * @param willingToRelocateWithinCountry The willingToRelocateWithinCountry to set.
	 */
	public void setWillingToRelocateWithinCountry(
			String willingToRelocateWithinCountry) {
		this.willingToRelocateWithinCountry = willingToRelocateWithinCountry;
		if(!isStringNullOrEmpty(willingToRelocateWithinCountry)){
			willingToRelocateWithinCountryProvided=true;
		}

	}
	/**
	 * @return Returns the workExperience.
	 */
	public String getWorkExperience() {
		return workExperience;
	}
	/**
	 * @param workExperience The workExperience to set.
	 */
	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience;
		if(!isStringNullOrEmpty(workExperience)){
			workExperienceProvided=true;
		}
		
	}
	/**
	 * @return Returns the workPermit.
	 */
	public String getWorkPermit() {
		return workPermit;
	}
	/**
	 * @param workPermit The workPermit to set.
	 */
	public void setWorkPermit(String workPermit) {
		this.workPermit = workPermit;
		if(!isStringNullOrEmpty(workPermit)){
			workPermitProvided=true;
		}

	}
	
	/**
	 * @return Returns the beginCandidateStartDateProvided.
	 */
	public boolean isBeginCandidateStartDateProvided() {
		return beginCandidateStartDateProvided;
	}
	/**
	 * @return Returns the beginRegistrationDateProvided.
	 */
	public boolean isBeginRegistrationDateProvided() {
		return beginRegistrationDateProvided;
	}
	/**
	 * @return Returns the educationLevelProvided.
	 */
	public boolean isEducationLevelProvided() {
		return educationLevelProvided;
	}
	/**
	 * @return Returns the endCandidateStartDateProvided.
	 */
	public boolean isEndCandidateStartDateProvided() {
		return endCandidateStartDateProvided;
	}
	/**
	 * @return Returns the endRegistrationDateProvided.
	 */
	public boolean isEndRegistrationDateProvided() {
		return endRegistrationDateProvided;
	}
	/**
	 * @return Returns the industrySectorProvided.
	 */
	public boolean isIndustrySectorProvided() {
		return industrySectorProvided;
	}
	/**
	 * @return Returns the jobTitleProvided.
	 */
	public boolean isJobTitleProvided() {
		return jobTitleProvided;
	}
	
	/**
	 * @return Returns the motherTongueProvided.
	 */
	public boolean isMotherTongueProvided() {
		return motherTongueProvided;
	}
	/**
	 * @return Returns the positionTypeProvided.
	 */
	public boolean isPositionTypeProvided() {
		return positionTypeProvided;
	}
	/**
	 * @return Returns the preferredBranchProvided.
	 */
	public boolean isPreferredBranchProvided() {
		return preferredBranchProvided;
	}
	/**
	 * @return Returns the preferredWorkLocationProvided.
	 */
	public boolean isPreferredWorkLocationProvided() {
		return preferredWorkLocationProvided;
	}
	/**
	 * @return Returns the residenceStatusProvided.
	 */
	public boolean isResidenceStatusProvided() {
		return residenceStatusProvided;
	}
	/**
	 * @return Returns the skillsProvided.
	 */
	public boolean isSkillsProvided() {
		return skillsProvided;
	}
	/**
	 * @return Returns the willingToRelocateToAnotherCountryProvided.
	 */
	public boolean isWillingToRelocateToAnotherCountryProvided() {
		return willingToRelocateToAnotherCountryProvided;
	}
	/**
	 * @return Returns the willingToRelocateWithinCountryProvided.
	 */
	public boolean isWillingToRelocateWithinCountryProvided() {
		return willingToRelocateWithinCountryProvided;
	}
	/**
	 * @return Returns the workExperienceProvided.
	 */
	public boolean isWorkExperienceProvided() {
		return workExperienceProvided;
	}
	/**
	 * @return Returns the workPermitProvided.
	 */
	public boolean isWorkPermitProvided() {
		return workPermitProvided;
	}
	/**
	 * @return Returns the beginLastDateCandidateLoggedIn.
	 */
	public Date getBeginLastDateCandidateLoggedIn() {
		return beginLastDateCandidateLoggedIn;
	}
	/**
	 * @param beginLastDateCandidateLoggedIn The beginLastDateCandidateLoggedIn to set.
	 */
	public void setBeginLastDateCandidateLoggedIn(
			Date beginLastDateCandidateLoggedIn) {
		this.beginLastDateCandidateLoggedIn = beginLastDateCandidateLoggedIn;
		if(beginLastDateCandidateLoggedIn !=null){
			beginLastDateCandidateLoggedInProvided=true;
		}

	}
	/**
	 * @return Returns the endLastDateCandidateLoggedIn.
	 */
	public Date getEndLastDateCandidateLoggedIn() {
		return endLastDateCandidateLoggedIn;
	}
	/**
	 * @param endLastDateCandidateLoggedIn The endLastDateCandidateLoggedIn to set.
	 */
	public void setEndLastDateCandidateLoggedIn(
			Date endLastDateCandidateLoggedIn) {
		this.endLastDateCandidateLoggedIn = endLastDateCandidateLoggedIn;
		if(endLastDateCandidateLoggedIn !=null){
			endLastDateCandidateLoggedInProvided=true;
		}

	}
	/**
	 * @return Returns the beginLastDateCandidateLoggedInProvided.
	 */
	public boolean isBeginLastDateCandidateLoggedInProvided() {
		return beginLastDateCandidateLoggedInProvided;
	}
	/**
	 * @return Returns the endLastDateCandidateLoggedInProvided.
	 */
	public boolean isEndLastDateCandidateLoggedInProvided() {
		return endLastDateCandidateLoggedInProvided;
	}
	
	private static boolean isStringNullOrEmpty(String str){
		if(str == null || str.equals("")){
			return true;
		}
		return false;
	}
}
