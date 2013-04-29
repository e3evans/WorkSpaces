package com.manpower.directoffice.pojos;

import java.util.Date;

public class CandidateProfile {
	
	Address payrollAddress;
	String xmlEntity;
	
	String firstName;
	
	
	String middleName;
	int backOfficeIDNumber;
	
	boolean acceptDirectDepositByEmail;
	boolean candidateAnsweredDepositQuest;
				
	
	boolean citizen;
	boolean candidatePointedResidence;
	boolean legallyEntiltedToWork;
	boolean candidatePointedWhetherEntitled;
	boolean haveGovernmentID;
	boolean candidatePointedAboutGovernmentId;
	boolean haveWorkVisa;
	boolean candidatePointedAboutWorkVisa;
	Date workVisaexpirationDate;
	boolean criminalConvictions;
	boolean candidateAnsweredCriminalQuest;
	
	boolean paymentAddrDiffFromMain;
	
	

	public Address getPayrollAddress() {
		return payrollAddress;
	}

	public void setPayrollAddress(Address payrollAddress) {
		this.payrollAddress = payrollAddress;
	}
	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	

	public int getBackOfficeIDNumber() {
		return backOfficeIDNumber;
	}

	public void setBackOfficeIDNumber(int backOfficeIDNumber) {
		this.backOfficeIDNumber = backOfficeIDNumber;
	}

	

	public boolean isAcceptDirectDepositByEmail() {
		return acceptDirectDepositByEmail;
	}

	public void setAcceptDirectDepositByEmail(boolean acceptDirectDepositByEmail) {
		this.acceptDirectDepositByEmail = acceptDirectDepositByEmail;
	}

	
	public boolean isCitizen() {
		return citizen;
	}

	public void setCitizen(boolean citizen) {
		this.citizen = citizen;
	}

	public boolean isLegallyEntiltedToWork() {
		return legallyEntiltedToWork;
	}

	public void setLegallyEntiltedToWork(boolean legallyEntiltedToWork) {
		this.legallyEntiltedToWork = legallyEntiltedToWork;
	}

	public boolean isHaveGovernmentID() {
		return haveGovernmentID;
	}

	public void setHaveGovernmentID(boolean haveGovernmentID) {
		this.haveGovernmentID = haveGovernmentID;
	}

	public boolean isHaveWorkVisa() {
		return haveWorkVisa;
	}

	public void setHaveWorkVisa(boolean haveWorkVisa) {
		this.haveWorkVisa = haveWorkVisa;
	}

	public Date getWorkVisaexpirationDate() {
		return workVisaexpirationDate;
	}

	public void setWorkVisaexpirationDate(Date workVisaexpirationDate) {
		this.workVisaexpirationDate = workVisaexpirationDate;
	}

	public boolean isCriminalConvictions() {
		return criminalConvictions;
	}

	public void setCriminalConvictions(boolean criminalConvictions) {
		this.criminalConvictions = criminalConvictions;
	}

	


	
	
	public String getXmlEntity() {
		return xmlEntity;
	}
	
	public void setXmlEntity(String xmlEntity) {
		this.xmlEntity = xmlEntity;
	}

	public boolean isCandidateAnsweredDepositQuest() {
		return candidateAnsweredDepositQuest;
	}

	public void setCandidateAnsweredDepositQuest(boolean candidateAnsweredDepositQuest) {
		
		this.candidateAnsweredDepositQuest = candidateAnsweredDepositQuest;
	}

	public boolean isCandidateAnsweredCriminalQuest() {
		return candidateAnsweredCriminalQuest;
	}

	public void setCandidateAnsweredCriminalQuest(
			boolean candidateAnsweredCriminalQuest) {
		this.candidateAnsweredCriminalQuest = candidateAnsweredCriminalQuest;
	}

	public boolean isCandidatePointedResidence() {
		return candidatePointedResidence;
	}

	public void setCandidatePointedResidence(boolean candidatePointedResidence) {
		this.candidatePointedResidence = candidatePointedResidence;
	}

	public boolean isCandidatePointedWhetherEntitled() {
		return candidatePointedWhetherEntitled;
	}

	public void setCandidatePointedWhetherEntitled(
			boolean candidatePointedWhetherEntitled) {
		this.candidatePointedWhetherEntitled = candidatePointedWhetherEntitled;
	}

	public boolean isCandidatePointedAboutGovernmentId() {
		return candidatePointedAboutGovernmentId;
	}

	public void setCandidatePointedAboutGovernmentId(
			boolean candidatePointedAboutGovernmentId) {
		this.candidatePointedAboutGovernmentId = candidatePointedAboutGovernmentId;
	}

	public boolean isCandidatePointedAboutWorkVisa() {
		return candidatePointedAboutWorkVisa;
	}

	public void setCandidatePointedAboutWorkVisa(
			boolean candidatePointedAboutWorkVisa) {
		this.candidatePointedAboutWorkVisa = candidatePointedAboutWorkVisa;
	}

	

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	public boolean isPaymentAddrDiffFromMain() {
		return paymentAddrDiffFromMain;
	}

	public void setPaymentAddrDiffFromMain(boolean paymentAddrDiffFromMain) {
		this.paymentAddrDiffFromMain = paymentAddrDiffFromMain;
	}
	
	
}
