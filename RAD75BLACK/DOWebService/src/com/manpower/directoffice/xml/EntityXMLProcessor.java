package com.manpower.directoffice.xml;

import java.io.*;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;
import javax.xml.xpath.*;

import org.xml.sax.InputSource;




import com.bis.webservice.search.types.DataNotFoundException;
import com.bis.webservice.search.types.InvalidArgumentException;
import com.bis.webservice.search.types.ServerErrorException;
import com.bis.webservice.util.EntityAccessWebService;

import com.manpower.directoffice.pojos.Address;
import com.manpower.directoffice.pojos.CandidateEntity;
import com.manpower.directoffice.pojos.CandidateGeneralInformation;
import com.manpower.directoffice.pojos.CandidateProfile;

import com.manpower.directoffice.xml.XMLExtractor;



public class EntityXMLProcessor {
	static String xmlData;

	private static String getEntityXML() {
		return xmlData;
	}
	
	public static void buildCandidateProfile(String entityXML, CandidateEntity entity) throws UnsupportedEncodingException {
			
		long xboc=0;
		String boc = null;  

		try {
			getGeneralInformation(entity, entityXML);
			buildPersonalBODetails(entity);

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
public static void getGeneralInformation(CandidateEntity candidate, String entityXML) throws UnsupportedEncodingException {
		
		XPathFactory  factory=XPathFactory.newInstance();
		
		XPath xPath=factory.newXPath();
		
		try {
			//System.out.println(entityXML);
			//candidate.getCandidateGeneralInformation().setDOEntityID(XMLExtractor.extractXML(entityXML, "XMLExtractor.entityID", xPath));
			candidate.getCandidateGeneralInformation().setEmergencyContactPhone(XMLExtractor.extractXML(entityXML, "XMLExtractor.emergencyContactPhone", xPath));
			candidate.getCandidateGeneralInformation().setEmergencyContactName(XMLExtractor.extractXML(entityXML, "XMLExtractor.emergencyContactName", xPath));
			candidate.getCandidateGeneralInformation().setEmergencyContactNotes(XMLExtractor.extractXML(entityXML, "XMLExtractor.emergencyNotes", xPath));
			
			candidate.getCandidateGeneralInformation().setDateOfBirth(XMLExtractor.extractXML(entityXML, "XMLExtractor.dob", xPath));
			
			// main address
			candidate.getCandidateGeneralInformation().getMainAddress().setAddressLine1(XMLExtractor.extractXML(entityXML, "XMLExtractor.addressLine1", xPath));
			candidate.getCandidateGeneralInformation().getMainAddress().setAddressLine2(XMLExtractor.extractXML(entityXML, "XMLExtractor.addressLine2", xPath));
			candidate.getCandidateGeneralInformation().getMainAddress().setMunicipality(XMLExtractor.extractXML(entityXML, "XMLExtractor.city", xPath));
			candidate.getCandidateGeneralInformation().getMainAddress().setPostal_code(XMLExtractor.extractXML(entityXML, "XMLExtractor.zipCode", xPath));
			candidate.getCandidateGeneralInformation().getMainAddress().setAddress_type(Address.PRIMARY_ADDRESS);
			candidate.getCandidateGeneralInformation().getMainAddress().setCountry(XMLExtractor.extractXML(entityXML, "XMLExtractor.country", xPath));
			candidate.getCandidateGeneralInformation().getMainAddress().setState_province(XMLExtractor.extractXML(entityXML, "XMLExtractor.state", xPath));

			candidate.getCandidateGeneralInformation().setFirstName(XMLExtractor.extractXML(entityXML, "XMLExtractor.firstName", xPath));
			candidate.getCandidateGeneralInformation().setLastName(XMLExtractor.extractXML(entityXML, "XMLExtractor.lastName", xPath));
			candidate.getCandidateGeneralInformation().setKnownAs(XMLExtractor.extractXML(entityXML, "XMLExtractor.knownAs", xPath));
			candidate.getCandidateGeneralInformation().setTitle(XMLExtractor.extractXML(entityXML, "XMLExtractor.title", xPath));

			candidate.getCandidateGeneralInformation().setResetPassword(XMLExtractor.extractXML(entityXML, "XMLExtractor.resetPassword", xPath));
			candidate.getCandidateGeneralInformation().setPassword(XMLExtractor.extractXML(entityXML, "XMLExtractor.password", xPath));
			
			Integer boActivatedCount = new Integer(XMLExtractor.extractXML(entityXML,"XMLExtractro.bocCount",xPath));
	
			candidate.getCandidateGeneralInformation().setNumberOfBackOfficeProfiles(boActivatedCount.intValue());
			
			if (boActivatedCount.intValue() == 1) 
				candidate.getCandidateGeneralInformation().getBoIDs()[0] = XMLExtractor.extractXML(entityXML, "XMLExtractor.xboc", xPath);
			if (boActivatedCount.intValue() == 2) {
				candidate.getCandidateGeneralInformation().getBoIDs()[0] = XMLExtractor.extractXML(entityXML, "XMLExtractor.xboc", xPath);
				candidate.getCandidateGeneralInformation().getBoIDs()[1] = XMLExtractor.extractXML(entityXML, "XMLExtractor.xboc2", xPath);
			}
			
			//cand_general_info.printMe();			
	
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
public static void buildPersonalBODetails(CandidateEntity entity) throws UnsupportedEncodingException {
		
		for (int i=0; i<entity.getCandidateGeneralInformation().getNumberOfBackOfficeProfiles(); i++){
			
			String backofficeEntityId = entity.getCandidateGeneralInformation().getBoIDs()[i];
			long boID = Long.parseLong(backofficeEntityId.trim());
			
			CandidateProfile candidateBOProfile = new CandidateProfile();
			XPathFactory  factory=XPathFactory.newInstance();
			XPath xPath=factory.newXPath();
			
			try {
				String entityBOCXML;
				try {
					
					entityBOCXML = EntityAccessWebService.getCandidateBOC(boID);
//					System.out.println("****WJRW****"+entityBOCXML+"*****WJRW");
					String boCountry = XMLExtractor.extractXML(entityBOCXML,"XMLExtractor.backOfficeCountry", xPath);
					//System.out.println("BACKOFFICE INFORMATION");
					
					
					// populate Address.  Same for all countries
					candidateBOProfile.setIsPayAddressDifferentThanMain(XMLExtractor.extractXML(entityBOCXML,"XMLExtractor.payAddrDifferent", xPath));
					if (candidateBOProfile.isPayAddressDifferentThanMain().equals("Y")) {						
						candidateBOProfile.getPayrollAddress().setAddressLine1(XMLExtractor.extractXML(entityBOCXML, "XMLExtractor.addressLine1", xPath));
						candidateBOProfile.getPayrollAddress().setAddressLine2(XMLExtractor.extractXML(entityBOCXML, "XMLExtractor.addressLine2", xPath));
						candidateBOProfile.getPayrollAddress().setMunicipality(XMLExtractor.extractXML(entityBOCXML, "XMLExtractor.city", xPath));
						candidateBOProfile.getPayrollAddress().setPostal_code(XMLExtractor.extractXML(entityBOCXML, "XMLExtractor.zipCode", xPath));
						candidateBOProfile.getPayrollAddress().setAddress_type(Address.PAYROLL_ADDRESS);
						candidateBOProfile.getPayrollAddress().setCountry(XMLExtractor.extractXML(entityBOCXML, "XMLExtractor.country", xPath));
						candidateBOProfile.getPayrollAddress().setState_province(XMLExtractor.extractXML(entityBOCXML, "XMLExtractor.state", xPath));
						candidateBOProfile.setBusinessUnit(XMLExtractor.extractXML(entityBOCXML, "XMLExtractor.office", xPath));
					} else {
						candidateBOProfile.setPayrollAddress(entity.getCandidateGeneralInformation().getMainAddress());
					}
					
					// populate country specific field values
					if (boCountry.equals(CandidateProfile.BOCOUNTRY_USA)) {
						candidateBOProfile.setCountryCode("USA");
						candidateBOProfile.setFromWebServiceLegallyEntiltedToWork(XMLExtractor.extractXML(entityBOCXML, "XMLExtractor.usLegalRightToWork", xPath));
						candidateBOProfile.setFromWebserviceAcceptDirectDepositByEmail(XMLExtractor.extractXML(entityBOCXML,"XMLExtractor.usEStatement", xPath));
						candidateBOProfile.setFromWebServiceCriminalConvictions(XMLExtractor.extractXML(entityBOCXML,"XMLExtractor.usCrimConv", xPath));
						candidateBOProfile.setFromWebServiceHaveGovernmentID(XMLExtractor.extractXML(entityBOCXML,"XMLExtractor.haveSSN", xPath));
						candidateBOProfile.setFromWebServiceHasWorkVisa(XMLExtractor.extractXML(entityBOCXML,"XMLExtractor.usHoldsVisa", xPath));
						candidateBOProfile.setFromWebServiceCitizen(XMLExtractor.extractXML(entityBOCXML,"XMLExtractor.isCitizen", xPath));
						candidateBOProfile.setFromServiceWillingToTakeDrugTest(XMLExtractor.extractXML(entityBOCXML,"XMLExtractor.usDrugTest", xPath));
						candidateBOProfile.setWorkVisaExpirationDate(XMLExtractor.extractXML(entityBOCXML,"XMLExtractor.usVisaExpDt", xPath));
						candidateBOProfile.setFromWebserviceIsEighteenOrOlder(XMLExtractor.extractXML(entityBOCXML,"XMLExtractor.usOlder", xPath));
						candidateBOProfile.setBackOfficeIDNumber(XMLExtractor.extractXML(entityBOCXML,"XMLExtractor.boID", xPath));
						candidateBOProfile.setBusinessUnit(XMLExtractor.extractXML(entityBOCXML, "XMLExtractor.office", xPath));
						entity.addNewCandidateProfileForCountry(candidateBOProfile, CandidateProfile.BOCOUNTRY_USA);
					}
					if (boCountry.equals(CandidateProfile.BOCOUNTRY_CANADA)) {
						
						candidateBOProfile.setCountryCode("CAN");
						candidateBOProfile.setFromWebServiceCriminalConvictions(XMLExtractor.extractXML(entityBOCXML,"XMLExtractor.canCrimConv", xPath));
						candidateBOProfile.setFromWebServiceHaveGovernmentID(XMLExtractor.extractXML(entityBOCXML,"XMLExtractor.haveSIN", xPath));
						candidateBOProfile.setFromWebServiceHasWorkVisa(XMLExtractor.extractXML(entityBOCXML,"XMLExtractor.canHoldsVisa", xPath));
						candidateBOProfile.setWorkVisaExpirationDate(XMLExtractor.extractXML(entityBOCXML,"XMLExtractor.canVisaExpryDate", xPath));
						candidateBOProfile.setFromWebserviceIsEighteenOrOlder(XMLExtractor.extractXML(entityBOCXML,"XMLExtractor.canOlder", xPath));
						candidateBOProfile.setFromWebServiceLegallyEntiltedToWork(XMLExtractor.extractXML(entityBOCXML,"XMLExtractor.canLegalRightToWork", xPath));
						
						entity.addNewCandidateProfileForCountry(candidateBOProfile, CandidateProfile.BOCOUNTRY_CANADA);
					}
					
				} catch (ServerErrorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DataNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (XPathExpressionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //try
		
		} 
		//entity.setCandidateProfiles(profiles);
	}

}
