package com.manpower.portal.portlet.gemt;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;

import com.manpower.dom.util.XMLGenerator;

public abstract class BaseAjaxServlet extends HttpServlet implements Servlet {
	protected Document getXMLDocFromResult(String result){
		Document document=null;
		try{
			document = DocumentHelper.parseText(result);
		}catch (Exception e){
			e.printStackTrace();
		}
		return document;	
	}
	protected String getXMLElementFromHibBean(Object gsrub){
		String result="";
		try{
			result=XMLGenerator.getAsXMLMessage(gsrub);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	protected StringBuffer beginXmlDoc(){
		StringBuffer sb=new StringBuffer();
		sb.append("<?xml-stylesheet type=\"text/xsl\"?>");
		sb.append("<!DOCTYPE stylesheet [<!ENTITY nbsp \"&#160;\"><!-- no-break space -->]>");
		sb.append("<results>");
		return sb;
	}
	protected StringBuffer endXMLDoc(StringBuffer sb){
		return sb.append("</results>");
	}
}
