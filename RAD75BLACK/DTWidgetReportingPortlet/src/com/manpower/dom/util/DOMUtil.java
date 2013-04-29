/*
 * Created on Jan 5, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.dom.util;

import java.io.StringWriter;
import java.io.Writer;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.dom4j.Document;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * @author Eric Evans
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DOMUtil {

	   public static Element getFirstElement( Element element, String name ) {
	      NodeList nl = element.getElementsByTagName( name );
	      if ( nl.getLength() < 1 )
	         throw new RuntimeException(
	            "Element: "+element+" does not contain: "+name);
	      return (Element)nl.item(0);
	   }

	   public static String getSimpleElementText( Element node, String name ) 
	   {
	      Element namedElement = getFirstElement( node, name );
	      return getSimpleElementText( namedElement );
	   }

	   public static String getSimpleElementText( Element node ) 
	   {
	      StringBuffer sb = new StringBuffer();
	      NodeList children = node.getChildNodes();
	      for(int i=0; i<children.getLength(); i++) {
	         Node child = children.item(i);
	         if ( child instanceof Text )
	            sb.append( child.getNodeValue() );
	      }
	      return sb.toString();
	   }

	   public static Document getTransformedDoc(Document document,String stylesheet){
//	  load the transformer using JAXP
	   		Document transformedDoc = null;
	   		try{
		        TransformerFactory factory = TransformerFactory.newInstance();
		        Transformer transformer = factory.newTransformer( 
		        		new StreamSource( stylesheet ));

        		// now lets style the given document
		        DocumentSource source = new DocumentSource( document );
		        DocumentResult result = new DocumentResult();
		        transformer.transform( source, result );

        		// return the transformed document
        		transformedDoc = result.getDocument();
	   		}catch (Exception e){
	   			e.printStackTrace();
	   		}
	   		return transformedDoc;
	   }
	   public static String getTransformedString(Document document,String stylesheet){
	   		Writer writer = new StringWriter();
	   	
	   		try{
	   			TransformerFactory factory = TransformerFactory.newInstance();
		        Transformer transformer = factory.newTransformer( 
		        		new StreamSource( stylesheet ));
		        StreamResult result = new StreamResult(writer);
		        DocumentSource source = new DocumentSource( document );
		        transformer.transform( source, result );
	   		}catch (Exception e){
	   			e.printStackTrace();
	   		}
	   		return writer.toString();
	   }
}
