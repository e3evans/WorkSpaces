package com.manpower.directoffice.xml;

import java.io.*;
import java.net.URLDecoder;

import javax.xml.xpath.*;
import org.xml.sax.*;

public class XMLExtractor {

	public static String extractXML(String xml, String xml_expression, XPath xPath) throws UnsupportedEncodingException, XPathExpressionException {
		InputStream is = new ByteArrayInputStream(xml.getBytes("UTF-8"));
	    InputSource inputSource=new InputSource(is);
	    
	   
	    
		return  URLDecoder.decode(xPath.evaluate(XPathExpressions.getString(xml_expression),inputSource), "UTF-8");
	}
}
	
	