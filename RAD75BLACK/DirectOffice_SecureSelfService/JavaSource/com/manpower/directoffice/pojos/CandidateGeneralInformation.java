package com.manpower.directoffice.pojos;

import java.rmi.RemoteException;
import java.util.Date;

import org.apache.log4j.Logger;

import com.manpower.ldap.ws.UserAccessWSProxy;

public class CandidateGeneralInformation {
	
	private static Logger logger = Logger.getLogger(CandidateGeneralInformation.class);
	
	String candidateName;
	String emergencyContactName;
	String emergencyContactPhone;
	String emergencyContactNotes;
	Boolean errorRetrievingInfo;
	Address mainAddress;
	String title;
	String lastName;
	String knownAs;
	Date dateOfBirth;
	boolean eighteenYearsOldorOlder;
	boolean isCandidatePointedAboutAge;
	
	boolean willingToTakeDrugTest;
	boolean candidatePointedAboutDrugTest;
	UserAccessWSProxy accessServiceProxy = null;
	
	
	public CandidateGeneralInformation()
	{
		accessServiceProxy = new UserAccessWSProxy();
		
	}
	
	public boolean isWillingToTakeDrugTest() {
		return willingToTakeDrugTest;
	}

	public void setWillingToTakeDrugTest(boolean willingToTakeDrugTest) {
		this.willingToTakeDrugTest = willingToTakeDrugTest;
	}
	
	public boolean isCandidatePointedAboutDrugTest() {
		return candidatePointedAboutDrugTest;
	}

	public void setCandidatePointedAboutDrugTest(
			boolean candidatePointedAboutDrugTest) {
		this.candidatePointedAboutDrugTest = candidatePointedAboutDrugTest;
	}
	
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	
	public Boolean getErrorRetrievingInfo() {
		return errorRetrievingInfo;
	}
	public void setErrorRetrievingInfo(Boolean errorRetrievingInfo) {
		this.errorRetrievingInfo = errorRetrievingInfo;
	}
	public String getEmergencyContactName() {
		return emergencyContactName;
	}
	public void setEmergencyContactName(String emergencyContactName) {
		this.emergencyContactName = emergencyContactName;
	}
	public String getEmergencyContactPhone() {
		return emergencyContactPhone;
	}
	public void setEmergencyContactPhone(String emergencyContactPhone) {
		this.emergencyContactPhone = emergencyContactPhone;
	}
	public String getEmergencyContactNotes() {
		return emergencyContactNotes;
	}
	public void setEmergencyContactNotes(String emergencyContactNotes) {
		this.emergencyContactNotes = emergencyContactNotes;
	}

	public Address getMainAddress() {
		// Todo: make real call to get main address.  this is stubbed just for testing.
		Address mainAddress = new Address();
		mainAddress.setAddress_type(Address.PRIMARY_ADDRESS);
		mainAddress.setAddressLine1("123 Testing Lane");
		mainAddress.setAddressLine2("Apt 1234");
		mainAddress.setMunicipality("Milwaukee");
		mainAddress.setPostal_code("12345");
		mainAddress.setState_province("Wisconsin");
		return mainAddress;
	}
	
	public boolean hasAccess_USA(long candidateId)
	{
		boolean accessUSA = false;
		try {
			accessUSA = accessServiceProxy.hasAccess_USA(candidateId);
		} catch (RemoteException e) {

			logger.error("RemoteException while checking rights for viewing USA page:", e);
		}
		
		logger.debug("accessUSA = " + accessUSA);
		return accessUSA; 
	}
	
	public boolean hasAccess_Canada(long candidateId)
	{
		boolean accessCanada = false;
		try {
			accessCanada = accessServiceProxy.hasAccess_Canada(candidateId);
		} catch (RemoteException e) {

			logger.error("RemoteException while checking rights for viewing Canada page:", e);
		}
		
		logger.debug("accessCanada = " + accessCanada);
		return accessCanada; 
	}

	public void setMainAddress(Address mainAddress) {
		this.mainAddress = mainAddress;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getKnownAs() {
		return knownAs;
	}

	public void setKnownAs(String knownAs) {
		this.knownAs = knownAs;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public boolean isEighteenYearsOldorOlder() {
		return eighteenYearsOldorOlder;
	}

	public void setEighteenYearsOldorOlder(boolean eighteenYearsOldorOlder) {
		this.eighteenYearsOldorOlder = eighteenYearsOldorOlder;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isCandidatePointedAboutAge() {
		return isCandidatePointedAboutAge;
	}

	public void setCandidatePointedAboutAge(boolean isCandidatePointedAboutAge) {
		this.isCandidatePointedAboutAge = isCandidatePointedAboutAge;
	}
	
	
	
	
}
