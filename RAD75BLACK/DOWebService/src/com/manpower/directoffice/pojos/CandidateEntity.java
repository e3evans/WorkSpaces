package com.manpower.directoffice.pojos;

import java.util.Hashtable;

public class CandidateEntity {
	public static final boolean USE_TEST_DATA = false;
	public static final String USA = "USA";
	public static final String CANADA = "CAN";
	int loginStatus;

	CandidateGeneralInformation candidateGeneralInformation = new CandidateGeneralInformation();
	Hashtable candidateProfiles = new Hashtable();
	
	public int wasLoginSuccessful(){
		return loginStatus;
	}
	
	public void setLoginStatus(int status) {
		loginStatus = status;
	}
	
	public CandidateGeneralInformation getCandidateGeneralInformation() {
		return candidateGeneralInformation;
	}

	public void setCandidateGeneralInformation(
			CandidateGeneralInformation candidateGeneralInformation) {
		this.candidateGeneralInformation = candidateGeneralInformation;
	}


	public void addNewCandidateProfileForCountry(CandidateProfile profile, String countryCode) {
		candidateProfiles.put(countryCode,profile);
	}

	
	public CandidateProfile getCandidateDetailsForCountry(String country){
		
		if (USE_TEST_DATA) {
			System.out.println("USING TEST DATA!!!!");
			CandidateProfile profile = new CandidateProfile();
			if (country.equals(USA)){
				profile.countryID=USA;
				return profile;
			}
			if (country.equals(CANADA)){
				profile.countryID=CANADA;
				return profile;
			}
		}
		
		return (CandidateProfile) candidateProfiles.get(country);
		
	}
}
