package com.manpower.directoffice.codelists;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;
import javax.xml.rpc.Stub;

import com.bis.webservice.codegroup.CodeGroupAccess;
import com.bis.webservice.codegroup.CodeGroupAccessLocator;
import com.bis.webservice.codegroup.ICodeGroup;
import com.bis.webservice.codegroup.types.CodeBean;
import com.bis.webservice.codegroup.types.CodeGroupBean;
import com.bis.webservice.codegroup.types.DataNotFoundException;
import com.bis.webservice.codegroup.types.InvalidArgumentException;
import com.bis.webservice.codegroup.types.ServerErrorException;
import java.util.Hashtable;

public class CodeListHandler {
	
	// TODO: Externalize the code group names
	static final String STATE_CODE_GROUP = "STATES US";
	static final String COUNTRY_CODE_GROUP = "COUNTRY US";
	static final String TITLE_CODE_GROUP = "Title US";
	
	static Hashtable titleList = new Hashtable();
	static Hashtable stateList = new Hashtable();
	static Hashtable countryList = new Hashtable();
	
	static String descriptionLanguage = "English";
	
	// TODO: externalize to a prop. file.
	static String codeGroupServiceUrl = "http://10.14.2.101:80/WebApp/CodeGroup";

	public static void initializeCodeLists(String language) {	
		descriptionLanguage = language;
		getCountryList();
		getStateList();
		getTitleList();
	}
	
	/**
	 * 
	 * getCountryList - Retrieves the Direct Office Code Group for the code list Country.  
	 * 					On the first time access it will initialize the country list
	 * 					from Direct Office.  Each time after it will simply return the hash table.  
	 * 					This ensures a single call to Direct Office for this 
	 * 					list in a given session.
	 * @param langugage
	 * @return
	 */
	public static Hashtable getCountryList() {
		if (!countryList.isEmpty()) return countryList;
	  	CodeGroupAccess service = new CodeGroupAccessLocator();
	  	ICodeGroup port;
	  	try {
	  		port = service.getICodeGroupPort();
	  		((Stub) port)._setProperty(Stub.ENDPOINT_ADDRESS_PROPERTY, codeGroupServiceUrl);
		
	  		CodeGroupBean codeGroupBean = port.getCodeGroupByLanguage("mpdomain1_dev", COUNTRY_CODE_GROUP,descriptionLanguage);
	  		CodeBean codes[] = codeGroupBean.getCodes();
	  		for (int i=0;i<codes.length;i++){
	  			long id = codes[i].getId();
	  			String.valueOf(id);
	  			
	  			countryList.put(String.valueOf(codes[i].getId()), codes[i].getLocalizedDesc());
	  		}
	  	} catch (ServiceException e) {
	  		// TODO Auto-generated catch block
	  		e.printStackTrace();
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
	  	}
		return countryList;
	}
	
	/**
	 * 
	 * getStateList - 	Retrieves the Direct Office Code Group for the code list State.  
	 * 					On the first time access it will initialize the state list
	 * 					from Direct Office.  Each time after it will simply return the hash table.  
	 * 					This ensures a single call to Direct Office for this 
	 * 					list in a given session.
	 * @param langugage - the language that you would like to have returned to you.  
	 * @return
	 */
	public static Hashtable getStateList() {
		if (!stateList.isEmpty()) return stateList;
	  	CodeGroupAccess service = new CodeGroupAccessLocator();
	  	ICodeGroup port;
	  	try {
	  		port = service.getICodeGroupPort();
	  		((Stub) port)._setProperty(Stub.ENDPOINT_ADDRESS_PROPERTY, codeGroupServiceUrl);
		
	  		CodeGroupBean codeGroupBean = port.getCodeGroupByLanguage("mpdomain1_dev", STATE_CODE_GROUP,descriptionLanguage);
	  		CodeBean codes[] = codeGroupBean.getCodes();
	  		for (int i=0;i<codes.length;i++){
	  			long id = codes[i].getId();
	  			String.valueOf(id);
	  			
	  			stateList.put(String.valueOf(codes[i].getId()), codes[i].getLocalizedDesc());
	  		}
	  	} catch (ServiceException e) {
	  		// TODO Auto-generated catch block
	  		e.printStackTrace();
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
	  	}
		return stateList;
	}

	/**
	 * 
	 * getTitleList - 	Retrieves the Direct Office Code Group for the code list title (i.e. Miss, Mister, Dr., etc...).  
	 * 					On the first time access it will initialize the title list
	 * 					from Direct Office.  Each time after it will simply return the hash table.  
	 * 					This ensures a single call to Direct Office for this 
	 * 					list in a given session.
	 * @param langugage - the language that you would like to have returned to you.  
	 * @return
	 */
	public static Hashtable getTitleList() {
		if (!titleList.isEmpty()) return titleList;
	  	CodeGroupAccess service = new CodeGroupAccessLocator();
	  	ICodeGroup port;
	  	try {
	  		port = service.getICodeGroupPort();
	  		((Stub) port)._setProperty(Stub.ENDPOINT_ADDRESS_PROPERTY, codeGroupServiceUrl);
		
	  		CodeGroupBean codeGroupBean = port.getCodeGroupByLanguage("mpdomain1_dev", "Title US",descriptionLanguage);
	  		CodeBean codes[] = codeGroupBean.getCodes();
	  		for (int i=0;i<codes.length;i++){
	  			long id = codes[i].getId();
	  			String.valueOf(id);
	  			
	  			titleList.put(String.valueOf(codes[i].getId()), codes[i].getLocalizedDesc());
	  		}
	  	} catch (ServiceException e) {
	  		// TODO Auto-generated catch block
	  		e.printStackTrace();
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
	  	}
		return titleList;
	}

}
