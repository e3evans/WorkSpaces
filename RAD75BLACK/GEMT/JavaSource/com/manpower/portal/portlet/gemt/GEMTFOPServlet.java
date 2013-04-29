package com.manpower.portal.portlet.gemt;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.apache.log4j.Logger;
import com.manpower.portal.gemt.ui.beans.GemtSummaryReportUIBean;
import com.manpower.portal.gemt.ui.services.GEMTServiceLocator;

/**
 * Servlet implementation class for Servlet: GEMTFOPServlet
 *
 */
 public class GEMTFOPServlet extends BaseAjaxServlet implements javax.servlet.Servlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 9066557292246845843L;
	private static Logger log=Logger.getLogger(GEMTFOPServlet.class);
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public GEMTFOPServlet() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String filePath = request.getSession().getServletContext().getRealPath("/");		
		StringBuffer sb = beginXmlDoc();
		GemtSummaryReportUIBean gsrub = GEMTServiceLocator.getInstance().getSummaryReportService().findById(new Long(request.getParameter("reportid")));
		gsrub.setXml_real_path(filePath);
		gsrub.convertHtmlVals();
		sb.append(getXMLElementFromHibBean(gsrub));
		endXMLDoc(sb);
		log.debug("XML=\n"+sb);
		
		//Setup a buffer to obtain the content length
	    ByteArrayOutputStream out = new ByteArrayOutputStream();

	    try
	    {
		    //Setup FOP
		    FopFactory fopFactory = FopFactory.newInstance();
		    Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);
	
		    //Setup JAXP using identity transformer
	        TransformerFactory factory = TransformerFactory.newInstance();
		    Source xsltSrc = new StreamSource(new File(filePath+"/xsl/"+"GEMTReport.xsl"));
		    Transformer transformer = factory.newTransformer(xsltSrc);
	
		    //Make sure the XSL transformation's result is piped through to FOP
		    Result res = new SAXResult(fop.getDefaultHandler());
	
		    //Setup input
		    StringReader sr = new StringReader(sb.toString());
		    Source src = new StreamSource(sr);
	
		    //Start the transformation and rendering process
		    transformer.transform(src, res);
	    }
	    catch(FOPException fope)
	    {
	    	log.error("Exception:",fope);
	    }
	    catch(TransformerConfigurationException tce)
	    {
	    	log.error("Exception:",tce);
	    }
	    catch(TransformerException te)
	    {
	    	log.error("Exception:",te);
	    }
	    //Prepare response
	    response.setHeader("Content-disposition",
                "attachment; filename=" +gsrub.getGemt_sum_empname().replaceAll(" ", "_")+".pdf");
	    response.setContentType("application/pdf");
	    response.setContentLength(out.size());
	    
	    //Send content to Browser
	    response.getOutputStream().write(out.toByteArray());
	    response.getOutputStream().flush();
		
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}   	  	    
}