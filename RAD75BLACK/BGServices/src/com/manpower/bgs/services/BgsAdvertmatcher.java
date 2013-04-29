package com.manpower.bgs.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.bgt.lens.LensException;
import com.bgt.lens.LensMessage;
import com.bgt.lens.LensSession;
import com.manpower.hbn.bgs.beans.DTAdvertContactBean;
import com.manpower.hbn.shared.dao.DAOFactory;
import com.manpower.test.lens.LensConnection;

public class BgsAdvertmatcher {
	
	public static List<String> getRecruiterIds(String kioskFile){
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;
			
		try {
			docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(kioskFile);
			Element kioskdata = (Element)doc.getFirstChild();
			Element advertisementList = (Element)kioskdata.getElementsByTagName("advertisementList").item(0);
			NodeList ads = advertisementList.getElementsByTagName("advertisement");
			for (int i=0;i<ads.getLength();i++){
				Element ad = (Element)ads.item(i);
				System.out.println("RECRUITER ID: "+ad.getElementsByTagName("recruiterId").item(0).getTextContent());
				DTAdvertContactBean recruiter = (DTAdvertContactBean) DAOFactory.getDAOFactory().getAdvertContactDAO().findByID(new Long(ad.getElementsByTagName("recruiterId").item(0).getTextContent()));
				if (recruiter!=null) System.out.println(recruiter.getName());
				
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public static void createMatchFiles(String kioskFile){
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;
		LensSession session = null;
		ResourceBundle rb = ResourceBundle.getBundle("com.manpower.bgs.services.ServicesConfig");
		clearDirectory(new File("c:/xxLensXML/ads"));
		
		try {
			docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(kioskFile);
			Element kioskdata = (Element)doc.getFirstChild();
			Element advertisementList = (Element)kioskdata.getElementsByTagName("advertisementList").item(0);
			NodeList ads = advertisementList.getElementsByTagName("advertisement");
			for (int i=0;i<ads.getLength();i++){
				
				Element ad = (Element)ads.item(i);
				System.out.println("AD ID:  "+ad.getElementsByTagName("adID").item(0).getTextContent());
				DOMImplementation impl = docBuilder.getDOMImplementation();
				Document lensDoc = impl.createDocument(null,null,null);
				Element bgtcmd = lensDoc.createElement("bgtcmd");
				Element search = lensDoc.createElement("search");
				search.setAttribute("type", "resume");
				search.setAttribute("vendor", "USCampus");
				search.setAttribute("count", "10");
				search.setAttribute("min", "0");
				bgtcmd.appendChild(search);
				Element JobDoc = lensDoc.createElement("JobDoc");
				search.appendChild(JobDoc);
				Element posting = lensDoc.createElement("posting");
				JobDoc.appendChild(posting);
				Element duties = lensDoc.createElement("duties");
				duties.setTextContent(ad.getElementsByTagName("jobDescription").item(0).getTextContent());
				posting.appendChild(duties);
				Element title = lensDoc.createElement("title");
				title.setTextContent(ad.getElementsByTagName("jobTitle").item(0).getTextContent());
				duties.appendChild(title);
				Element qualifications = lensDoc.createElement("qualifications");
				posting.appendChild(qualifications);
				Element required = lensDoc.createElement("required");
				if (ad.getElementsByTagName("candidateProfile").getLength()>0)
					required.setTextContent(ad.getElementsByTagName("candidateProfile").item(0).getTextContent());
				qualifications.appendChild(required);
				Element skill = lensDoc.createElement("skill");
				if (ad.getElementsByTagName("candidateSkills").getLength()>0)
					skill.setTextContent(ad.getElementsByTagName("candidateSkills").item(0).getTextContent());
				required.appendChild(skill);
				Element keyword = lensDoc.createElement("keyword");
				search.appendChild(keyword);
				Element include0 = lensDoc.createElement("include");
				include0.setAttribute("var", "id");
				Element include1 = lensDoc.createElement("include");
				include1.setAttribute("var", "title");
				Element include2 = lensDoc.createElement("include");
				include2.setAttribute("var", "lens");
				Element include3 = lensDoc.createElement("include");
				include3.setAttribute("var", "name");
				Element include4 = lensDoc.createElement("include");
				include4.setAttribute("var", "jobs");
				search.appendChild(include0);
				search.appendChild(include1);
				search.appendChild(include2);
				search.appendChild(include3);
				search.appendChild(include4);
				lensDoc.appendChild(bgtcmd);
			
				
				DOMSource domSource = new DOMSource(lensDoc);
		        TransformerFactory tf = TransformerFactory.newInstance();
		        Transformer transformer = tf.newTransformer();
		        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		        transformer.setOutputProperty(OutputKeys.ENCODING,"ISO-8859-1");
		        transformer.setOutputProperty
		            ("{http://xml.apache.org/xslt}indent-amount", "4");
		        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		        java.io.StringWriter sw = new java.io.StringWriter();
		        StreamResult sr = new StreamResult(sw);
		        transformer.transform(domSource, sr);
		        String xml = sw.toString();
		       	      
		        System.out.println("LENS START:  "+new Date());
		        session = LensConnection.getSession("usd1w070s.CORP.ROOT.GLOBAL", 2010);
		        LensMessage englishMessage = LensMessage.create(xml, LensMessage.XML_TYPE);
				LensMessage returnMessage = session.sendMessage(englishMessage);
				writeOutput(returnMessage.toString(), rb.getString("adPath")+ad.getElementsByTagName("adID").item(0).getTextContent()+".xml");
				session.close();
				System.out.println("LENS FINIS:  "+new Date());
				System.out.println(i);
				
				if (i==5)break;
//				response.getWriter().println(returnMessage.toString());//,"c:/lensTest/rmess.xml");
//		        System.out.println("FINISH");
		       
				
				
			}
//			clearDirectory(new File("c:/xxLensXML/ads"));
		
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				session.close();
			} catch (LensException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	static void writeOutput(String str, String file) {

	    try {
		FileOutputStream fos = new FileOutputStream(file);
		Writer out = new OutputStreamWriter(fos, "UTF8");
		out.write(str);
		out.close();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
	
	  static public boolean deleteDirectory(File path) {
		    if( path.exists() ) {
		      File[] files = path.listFiles();
		      for(int i=0; i<files.length; i++) {
		         if(files[i].isDirectory()) {
		           deleteDirectory(files[i]);
		         }
		         else {
		           files[i].delete();
		         }
		      }
		    }
		    return( path.delete() );
		  }

	static void clearDirectory(File path){
		if( path.exists() ) {
		      File[] files = path.listFiles();
		      for(int i=0; i<files.length; i++) {
		         if(files[i].isDirectory()) {
		           deleteDirectory(files[i]);
		         }
		         else {
		           files[i].delete();
		         }
		      }
		}
		
	}

}
