package com.manpower.directoffice.api;

import com.manpower.directoffice.pojos.CandidateGeneralInformation;
import com.manpower.directoffice.pojos.CandidateProfile;

public class DOCandidateHandler {

	public void getCandidateData (long dtcandid){
		
	}
	
	public static CandidateGeneralInformation getGeneralInformationForCandidate (long dtcid){
		CandidateGeneralInformation candGenInfo = new CandidateGeneralInformation();
		
		// TODO: populate this information from the emergency info in DO.
		populateGeneralInformationForTesting(candGenInfo);
		
		return candGenInfo;
	}
	
	public CandidateProfile getCandidateDetailsForCountry (long dtcid, String country) {
	
		return new CandidateProfile();
		
	}
	
	public boolean setCandidateDetailsForcountry (long dtcid,String country,CandidateProfile candidateProfile){
		return true;
	}
	
	private static void populateGeneralInformationForTesting (CandidateGeneralInformation candGenInfo){
		candGenInfo.setEmergencyContactName("Jim Johnson");
		candGenInfo.setEmergencyContactPhone("+1 770 345 2234");
		candGenInfo.setEmergencyContactNotes("Jim is an ER nurse and can also be reached at United Hospital Systems");
		candGenInfo.setErrorRetrievingInfo(new Boolean(false));
	}
}
