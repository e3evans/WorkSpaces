package com.manpower.directoffice.api;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import com.bis.webservice.search.types.DataNotFoundException;
import com.bis.webservice.search.types.InvalidArgumentException;
import com.bis.webservice.search.types.ServerErrorException;
import com.bis.webservice.util.EntityAccessWebService;
import com.manpower.directoffice.pojos.CandidateEntity;
import com.manpower.directoffice.pojos.CandidateGeneralInformation;
import com.manpower.directoffice.pojos.CandidateProfile;
import com.manpower.directoffice.xml.exception.CanNotAccessDirectOfficeException;

public class DOCandidateHandler {

	static public CandidateEntity getDirectOfficeCandidate(String candidateID) throws
	CanNotAccessDirectOfficeException {
	CandidateEntity candidate = new CandidateEntity();
	try {
		
		candidate = EntityAccessWebService.getCandidateWithDirectTalentID(candidateID);
		
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
	return candidate;
}
	
}
