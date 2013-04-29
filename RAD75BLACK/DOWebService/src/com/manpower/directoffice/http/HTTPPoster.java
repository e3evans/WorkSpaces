package com.manpower.directoffice.http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class HTTPPoster
{
	
	// TODO: extract to method so that various templates/destinations can use this HTTPPoster class
	// TODO: propertize the values
	public static void postXML(String xmlData)
	{
		try
		{
			// Create socket
			
			//DEV
			String hostname = "10.14.2.101";
			//UAT
//			String hostname = "10.151.100.63";
			int port = 80;
			InetAddress addr = InetAddress.getByName(hostname);
			Socket sock = new Socket(addr, port);
			
			// Send header
			String path = "/WebApp/bowebservice";
			BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream(), "UTF-8"));
			
			// You can use "UTF8" for compatibility with the Microsoft virtual machine.
			wr.write("POST " + path + " HTTP/1.0\r\n");
			wr.write("Content-Type: text/xml; charset=\"utf-8\"\r\n");
			wr.write("Accept: application/soap+xml, application/dime, multipart/related, text/*\r\n");
			wr.write("Host: 10.14.2.101:80\r\n");
			wr.write("Cache-Control: no-cache\r\n");
			wr.write("Pragma: no-cache\r\n");
			wr.write("SOAPAction: \"\"\r\n");
			wr.write("Content-Length: " + xmlData.length() + "\r\n");
			wr.write("\r\n");
			
			// Send data
			wr.write(xmlData);
			wr.flush();
			
			// Response
			BufferedReader rd = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			
			//logger.debug(RRLoggerUtil.produceLogString("HTTP response: ", (SourceCountry)MDC.get(CycledJob.MDC_COUNTRY_KEY)));
			String line;
	
			while ((line = rd.readLine()) != null)
			{
				// TODO: Interpret response for success/failure
				//logger.debug(line);
				System.out.println(line);
			}
		} catch (IOException ioe)
		{
			ioe.printStackTrace();
		//	logger.error(RRLoggerUtil.produceLogString("Encountered an IO Exception while attempting to HTTP post to Direct Office.", (SourceCountry)MDC.get(CycledJob.MDC_COUNTRY_KEY)));
		//	logger.debug(ioe);
		}
	}
	
	public static Document packagePayloadInSOAP(Document payloadDoc, String soapTemplateName)
	{
		Document soapDoc = loadSOAPTemplate(soapTemplateName);

		
		// Injects payload into SOAP Envelope Template
		Element stringThreeNode = (Element) soapDoc.selectSingleNode("//String_3");
		stringThreeNode.addCDATA(payloadDoc.asXML());
		
//		logger.debug(RRLoggerUtil.produceLogString(soapDoc.asXML(), (SourceCountry)MDC.get(CycledJob.MDC_COUNTRY_KEY)));
	
		return soapDoc;
	}

	/**
	 * Loads the DOWebServiceSOAP.xml from resources for injection prior to delivery.
	 * 
	 * @return Document An XML document to inject SOAP payload into.
	 */
	public static Document loadSOAPTemplate(String templateName)
	{
		Document soapXMLDoc = null;
		
		try
		{
			InputStream targetInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(templateName);
			SAXReader targetISReader = new SAXReader();
			soapXMLDoc = targetISReader.read(targetInputStream);
		}
		catch (DocumentException de)
		{
			//System.out.println("ERROR");
//		logger.error(RRLoggerUtil.produceLogString("Encountered a DocumentException while attempting to parse the DOWebServiceSOAP.xml template",
//			        (SourceCountry) MDC.get(CycledJob.MDC_COUNTRY_KEY)));
//			logger.error(de);
		}
		
		return soapXMLDoc;
	}
}