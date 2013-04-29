package com.bis.webservice.util;

import java.rmi.RemoteException;
import javax.xml.rpc.ServiceException;

import com.bis.webservice.codegroup.*;
import com.bis.webservice.codegroup.types.*;

import javax.xml.rpc.Stub;

public class TestCodeGroupWebService {

	/**
	 * @param args
	 * @param serviceCall 
	 */
	public static void main(String[] args ) {
		
	      
		  String codeGroupServiceUrl = "http://10.14.2.101:80/WebApp/CodeGroup";
		  CodeGroupAccess service = new CodeGroupAccessLocator();
		  ICodeGroup port;
		  try {
			port = service.getICodeGroupPort();
			((Stub) port)._setProperty(Stub.ENDPOINT_ADDRESS_PROPERTY, codeGroupServiceUrl);
			
	        CodeGroupBean codeGroupBean = port.getCodeGroupByLanguage("mpdomain1_dev", "States US","English");
	        CodeBean codes[] = codeGroupBean.getCodes();
	        for (int i=0;i<codes.length;i++)
	        	System.out.println ("ID: " + codes[i].getId() +" NAME: " + codes[i].getName() + "   LocalName: " + codes[i].getLocalizedDesc());
	        System.out.println("stop");
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
	        
	        
	        
		
		
		System.out.println("finished");
	}

}
