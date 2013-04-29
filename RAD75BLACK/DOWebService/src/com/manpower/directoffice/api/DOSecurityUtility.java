package com.manpower.directoffice.api;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.dom4j.Document;

import com.bis.webservice.search.types.DataNotFoundException;
import com.bis.webservice.search.types.InvalidArgumentException;
import com.bis.webservice.search.types.ServerErrorException;
import com.bis.webservice.util.EntityAccessWebService;
import com.bis.webservice.util.PasswordValidator;

import com.manpower.directoffice.http.HTTPPoster;
import com.manpower.directoffice.pojos.CandidateEntity;
import com.manpower.directoffice.pojos.CandidateProfile;
import com.manpower.directoffice.xml.XMLInjector;
import com.manpower.directoffice.xml.exception.CanNotAccessDirectOfficeException;

public class DOSecurityUtility {
	public final static int FIRST_TIME_LOGIN = 1;
	public final static int LOGIN_SUCCESSFUL = 2;
	public final static int LOGIN_FAILED = 3;
	private static Logger logger = Logger.getLogger(DOSecurityUtility.class);
	
	public static CandidateEntity validateUser (String sssPinNumber, String candidateID) throws
		CanNotAccessDirectOfficeException {
		
		CandidateEntity candidate = new CandidateEntity();
		
		SimpleLayout layout = new SimpleLayout();
		FileAppender appender = null;
		
		try {
			appender = new FileAppender(layout,"/logs/dev/candidate/WebSphere_Portal/output1.txt",false);
		} 
		catch(Exception e) {}
		
		logger.addAppender(appender);
		logger.setLevel(Level.ALL);
		
		try {
			logger.info("PinNumber--> " + sssPinNumber);
//			logger.info("START CUSTOM:  ");
			candidate = EntityAccessWebService.getCandidateWithDirectTalentID(candidateID);
//			logger.info("GOT THIS FAR!!");
			candidate.setLoginStatus(checkPassword (sssPinNumber,candidate));
			
		} catch (ServerErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			candidate.setLoginStatus(LOGIN_FAILED);
		} catch (InvalidArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			candidate.setLoginStatus(LOGIN_FAILED);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			candidate.setLoginStatus(LOGIN_FAILED);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			candidate.setLoginStatus(LOGIN_FAILED);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			candidate.setLoginStatus(LOGIN_FAILED);
		}
		return candidate;
	}
	
	private static int checkPassword(String userid, CandidateEntity candidate) {
		if ((candidate.getCandidateGeneralInformation().getPassword().equals(userid))) {  		// does the password match?
			if (candidate.getCandidateGeneralInformation().getResetPassword().equals("Y"))		// and is it the first time logging in?
				return FIRST_TIME_LOGIN;
			else return LOGIN_SUCCESSFUL;														// password matched and was not the first time login.
		} else {
			return LOGIN_FAILED;																// password did not match.
		}
	}
	

	public static  int changeUserid ( String sssPinNumber, String candidateID){
		int retValue = 0;
		String soapTemplateName = "templates/DOWebServiceSOAP.xml";
		retValue = PasswordValidator.validateNewPasswordStrength(sssPinNumber);
		if (retValue < 0) return retValue;
		
		Document xmlDocument = XMLInjector.performChangePasswordXMLInjection(candidateID, sssPinNumber);
		Document soapDoc = HTTPPoster.packagePayloadInSOAP(xmlDocument,soapTemplateName);
		HTTPPoster.postXML(soapDoc.asXML());
		
		return 0;
	}
	
	public static void updateProfileForCountry(CandidateEntity entity,String countryCode){
	
		String soapTemplateName = "templates/DOWebServiceInboundSSSSOAP.xml";	// TODO: Externalize this
		//BEGIN TEST CODE!!!
		
//		System.out.println("DO SEC UPDATE:  " +entity.getCandidateDetailsForCountry(CandidateProfile.BOCOUNTRY_USA).getPayrollAddress().getState_province());
		
		//END TEST COPE
		
		Document xmlDocument = XMLInjector.performUpdateCandidateXMLInjection(entity, countryCode);
		Document soapDoc = HTTPPoster.packagePayloadInSOAP(xmlDocument, soapTemplateName);
		HTTPPoster.postXML(soapDoc.asXML());
	}
	
	public static void updateGeneralInformation(CandidateEntity entity){
		
		String soapTemplateName = "templates/DOUpdateCommonWebServiceSOAP.xml";	// TODO: Externalize this
		Document xmlDocument = XMLInjector.performUpdateGeneralInformationXMLInjection(entity);
		//System.out.println(xmlDocument.asXML());
		Document soapDoc = HTTPPoster.packagePayloadInSOAP(xmlDocument, soapTemplateName);
		HTTPPoster.postXML(soapDoc.asXML());
	}
	
	/**
	 * @param candidateID - direct talent candidate id. 	 
	 * @return - returns true if the user the request was successful.  False otherwise.
	 */
	public static boolean userForgotPassword ( long candidateID){
		// TODO: Send the request to Direct Office for the password re-send
		return true;
	}
	
	
}
