package com.bis.webservice.util;

import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.rpc.ServiceException;

import com.bis.webservice.codegroup.CodeGroupAccess;
import com.bis.webservice.codegroup.CodeGroupAccessLocator;
import com.bis.webservice.codegroup.ICodeGroup;
import com.bis.webservice.codegroup.types.CodeBean;
import com.bis.webservice.codegroup.types.CodeGroupBean;
import com.bis.webservice.entity.*;
import com.bis.webservice.entity.types.*;
import com.bis.webservice.search.*;
import com.bis.webservice.search.types.*;
import com.bis.webservice.search.types.DataNotFoundException;
import com.bis.webservice.search.types.InvalidArgumentException;
import com.bis.webservice.search.types.ServerErrorException;
import com.manpower.directoffice.pojos.CandidateEntity;
import com.manpower.directoffice.pojos.CandidateProfile;
import com.manpower.directoffice.xml.EntityXMLProcessor;

import javax.xml.rpc.Stub;

public class EntityAccessWebService {
	static final String ENTITY_SERVICE_URL = "http://10.14.2.101:80/WebApp/EntityAccess";
	static final String SEARCH_SERVICE_URL = "http://10.14.2.101:80/WebApp/SearchService";
	static String candidateEntityXML;
	static String personalDetailsEntityXML;

private static ISearchService getSearchService() throws ServiceException {
	SearchService search_service = new SearchServiceLocator();
	ISearchService search_port;
	search_port = search_service.getISearchServicePort();
	((Stub) search_port)._setProperty(Stub.ENDPOINT_ADDRESS_PROPERTY, SEARCH_SERVICE_URL);
	return search_port;
}

private static IEntityAccess getEntityService() throws ServiceException {
	EntityAccessService entity_service = new EntityAccessServiceLocator();
	IEntityAccess entity_port;
	entity_port=entity_service.getIEntityAccessPort();
	  ((Stub) entity_port)._setProperty(Stub.ENDPOINT_ADDRESS_PROPERTY, ENTITY_SERVICE_URL);
	return entity_port;
}

public static CandidateEntity getCandidateWithDirectTalentID(String id) throws ServiceException, ServerErrorException, InvalidArgumentException, DataNotFoundException, RemoteException{
	CandidateEntity candidateEntity = new CandidateEntity();
	SearchParameter[] spList = setSearchParameter(id);
	ISearchService run_search_service = getSearchService();
	IEntityAccess get_entity_service = getEntityService();
	SearchResultBean searchResult = run_search_service.runSearchByName("mpdomain1_dev", "CAND_CandidateForDTalentID", spList, 10);
	long[] eids = searchResult.getEntityIDs();
	for (int j = 0; j < eids.length; j++) {
		  long entityid = eids[j];
		  candidateEntityXML = get_entity_service.getEntity("mpdomain1_dev", entityid);
		  try {
			String strEntityID = String.valueOf(entityid);
			candidateEntity.getCandidateGeneralInformation().setDOEntityID(strEntityID);
			EntityXMLProcessor.buildCandidateProfile(candidateEntityXML,candidateEntity);
		 } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
   return candidateEntity;
}

static public String getCandidateBOC(long entityid) throws ServiceException, ServerErrorException, InvalidArgumentException, DataNotFoundException, RemoteException{
	
	String entityXML_BOC;
	IEntityAccess get_entity_service = getEntityService();
	entityXML_BOC = get_entity_service.getEntity("mpdomain1_dev", entityid);
   return entityXML_BOC;
}

static  SearchParameter[] setSearchParameter (String talentID){
    SearchParameter sp = new SearchParameter();
	sp.setDataType(1);
    sp.setName("DTalentID");
    sp.setStringValue("");
    sp.setDateValue(GregorianCalendar.getInstance());
    sp.setStringValue(talentID);   
    SearchParameter[] spList = new SearchParameter[1];
    spList[0]=sp;
    return spList;
}
}
