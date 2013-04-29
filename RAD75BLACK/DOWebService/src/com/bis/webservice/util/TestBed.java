package com.bis.webservice.util;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

import com.manpower.directoffice.api.DOCandidateHandler;
import com.manpower.directoffice.api.DOSecurityUtility;
import com.manpower.directoffice.pojos.Address;
import com.manpower.directoffice.pojos.CandidateEntity;
import com.manpower.directoffice.pojos.CandidateProfile;
import com.manpower.directoffice.xml.exception.CanNotAccessDirectOfficeException;

public class TestBed {

	/**
	 * @param args
	 */
	/**/public static void main(String[] args) {
		long talentID = 28317;
		String strTalentID = "28317";
		String sssPinNumber = "Password3";
		CandidateEntity candidate;
		String entityID = "80685";

		Logger logger = Logger.getLogger(TestBed.class);
		   
		SimpleLayout layout = new SimpleLayout();

		FileAppender appender = null;
		try {
			appender = new FileAppender(layout,"output1.txt",false);
		      } catch(Exception e) {}

		      logger.addAppender(appender);
		      logger.setLevel((Level) Level.DEBUG);

		      logger.debug("Here is some DEBUG");
		      logger.info("Here is some INFO");
		      logger.warn("Here is some WARN");
		      logger.error("Here is some ERROR");
		      logger.fatal("Here is some FATAL");

		      
		      
		try {
			//CodeListHandler.initializeCodeLists("English");
			
			//CandidateEntity candidate1 = DOCandidateHandler.getDirectOfficeCandidate(strTalentID);
			//candidate1.getCandidateGeneralInformation().printMe();
			//candidate1.getCandidateDetailsForCountry(CandidateProfile.BOCOUNTRY_USA).printMe();
			//System.out.println("CANDIDATE RETRIEVED");
			
// TEST GET CANDIDATE WITH LOGIN CHECK		
			candidate = DOSecurityUtility.validateUser(sssPinNumber,strTalentID);
			System.out.println("LOGIN SUCCESS:  "+candidate.wasLoginSuccessful());
			System.out.println("PROFILE:  "+candidate.getCandidateDetailsForCountry(CandidateProfile.BOCOUNTRY_USA));
			DOSecurityUtility.updateGeneralInformation(candidate);
//			candidate.getCandidateDetailsForCountry(CandidateProfile.BOCOUNTRY_USA).printMe();
//			candidate.getCandidateDetailsForCountry(CandidateProfile.BOCOUNTRY_CANADA).printMe();
			
// TEST UPDATE GENERAL INFORMATION
			//candidate.getCandidateGeneralInformation().setEmergencyContactName("Rick Kornman");
			//candidate.getCandidateGeneralInformation().setEmergencyContactPhone("1-111-222-3333");
			//DOSecurityUtility.updateGeneralInformation(candidate);
			
			
// TEST UPDATE PERSONAL DETAILS - CANADA
			//Address address = new Address();
			//address.setAddressLine1("123456");
		//	address.setMunicipality("racine");
			
		//	candidate.getCandidateDetailsForCountry(CandidateProfile.BOCOUNTRY_USA);
		//	//candidate.getCandidateDetailsForCountry(CandidateProfile.BOCOUNTRY_USA).setLegallyEntiltedToWork("Yes");
		//	//candidate.getCandidateDetailsForCountry(CandidateProfile.BOCOUNTRY_USA).setIsWillingToTakeDrugTest("Yes");
		//	//candidate.getCandidateDetailsForCountry(CandidateProfile.BOCOUNTRY_USA).setAcceptDirectDepositByEmail("Yes");
		//	candidate.getCandidateDetailsForCountry(CandidateProfile.BOCOUNTRY_USA).setCriminalConvictions("Yes");
		//	candidate.getCandidateDetailsForCountry(CandidateProfile.BOCOUNTRY_USA).setIsPayAddressDifferentThanMain("Y");
		//	candidate.getCandidateDetailsForCountry(CandidateProfile.BOCOUNTRY_USA).getPayrollAddress().setAddressLine1("123456");
		//	candidate.getCandidateDetailsForCountry(CandidateProfile.BOCOUNTRY_USA).setPayrollAddress(address);
		//	DOSecurityUtility.updateProfileForCountry(candidate,CandidateProfile.BOCOUNTRY_USA);
			
			
// TEST THE CHANGE LOGIN 		
			//System.out.println("Changing Password......");
			//System.out.println("Password Changed");
			
// TEST UPDATE DETAILS
	//		System.out.println ("Updating the profile");
			
			
		} catch (CanNotAccessDirectOfficeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
/**/
}

 