package com.manpower.xml;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;

public class TestDOM {
  public TestDOM() { }
    public String doit()
      throws javax.xml.parsers.ParserConfigurationException,
             javax.xml.transform.TransformerException,
             javax.xml.transform.TransformerConfigurationException{

        DocumentBuilderFactory factory
          = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation impl = builder.getDOMImplementation();

        Document doc = impl.createDocument(null,null,null);
        
        Element e1 = doc.createElement("howto");
        e1.setTextContent("THIS IS A TEST!!");
        doc.appendChild(e1);

        Element e2 = doc.createElement("java");
        e1.appendChild(e2);

        e2.setAttribute("url","http://www.rgagnon.com/howto.html");


        // transform the Document into a String
        DOMSource domSource = new DOMSource(doc);
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
        return xml;
    }

    public static void main(String args[])
      throws javax.xml.parsers.ParserConfigurationException,
             javax.xml.transform.TransformerException,
             javax.xml.transform.TransformerConfigurationException{
        System.out.println(new TestDOM().doit());
//        /*
//          output :
//          <?xml version="1.0" encoding="ISO-8859-1"?>
//          <howto>
//          <java url="http://www.ragagnon.com/howto.html"/>
//          </howto>
//        /*
     }
}