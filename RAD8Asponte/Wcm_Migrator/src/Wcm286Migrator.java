import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class Wcm286Migrator {

//	public static String JSR168PortletRef ="3_CGAH47L000T950I4B4R9CD0GN0";
//	public static String JSR286PortletRef = "3_6D7PQKG1006M00IE6HLO8B08B7";
	
	public static String JSR168PortletRef ="3_NO2UF4I11G7IE026HCTVU020K3";
	public static String JSR286PortletRef = "3_48N4IQ6IUQ3450IE8MKV6D2892";
	
	public static String portletRef = "portletref";
	public static String portletInst = "portletinstance";
	public static String JSR286Pref = "preferences";
	public static String JSR286Value = "value";
	
	
	private static Document delete168Portlets(Document doc,String elementName, String attributeName,String value){
		
		NodeList nList = doc.getElementsByTagName(elementName);
		
		for (int i=0;i<nList.getLength();i++){
			Node n = nList.item(i);
			Element e = (Element)n;
			if (e.getAttribute(portletRef).equals(JSR168PortletRef))e.setAttribute(attributeName, value);
		}
		
		return doc;
		
	}
	
	private static Document modify168to286(Document doc){
	
		NodeList nList = doc.getElementsByTagName(portletInst);
		for(int i=0;i<nList.getLength();i++){
			Node n = nList.item(i);
			Element e = (Element)n;
			if (e.getAttribute(portletRef).equals(JSR168PortletRef)){
				e.setAttribute(portletRef, JSR286PortletRef);
				if (n.hasChildNodes()){
					NodeList params = n.getChildNodes();
					for(int x=0;x<params.getLength();x++){	
						Node p = params.item(x);
						if (p.hasAttributes()){
							NamedNodeMap nnm = p.getAttributes();
							Element newPref = doc.createElement(JSR286Pref);
							for (int y=0;y<nnm.getLength();y++){
								if (!nnm.item(y).getNodeName().equals("type"))newPref.setAttribute(nnm.item(y).getNodeName(), nnm.item(y).getNodeValue());
							}
							Element newPrefValue = doc.createElement(JSR286Value);
							String textContent = p.getTextContent();
							if (newPref.getAttribute("name").equals("WCM_CONTENT_CONTEXT_TYPE")){
								String temp[] = textContent.split("\\.");
								textContent=temp[temp.length-1].toLowerCase();
							}
							CDATASection cdata = doc.createCDATASection(textContent);
							newPrefValue.appendChild(cdata);
							newPref.appendChild(newPrefValue);
							e.appendChild(newPref);
							e.removeChild(p);
						}
					}
				}
			}
		}
		
		return doc;
	}
	
	private static void saveDocument(Document doc,String filePathName){
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filePathName));
			transformer.transform(source, result);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("RUNNING!!");
		try {
//			String filepath = "c:/tempXML/PageIn.xml";
			String filepath = "c:/tempXML/PHExport.xml";
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);
			doc = delete168Portlets(doc, "portletinstance", "action", "delete");
			//SAVE DELTE DOCUMENT
//			saveDocument(doc,"C:/tempXML/PageInDelete1.xml");
			saveDocument(doc,"C:/tempXML/PHDelete.xml");
			//GET FRESH DOCUMENT
			doc=docBuilder.parse(filepath);
			modify168to286(doc);
//			saveDocument(doc,"C:/tempXML/testfornow.xml");
			saveDocument(doc,"C:/tempXML/PHImport.xml");
			
			System.out.println("FINISHED!");
		
			
			
	 
		   } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		   } catch (IOException ioe) {
			ioe.printStackTrace();
		   } catch (SAXException sae) {
			sae.printStackTrace();
		   }

	}

}
