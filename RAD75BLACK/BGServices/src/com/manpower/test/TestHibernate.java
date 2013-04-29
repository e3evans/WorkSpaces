package com.manpower.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.manpower.hbn.bgs.beans.DTAdvertBean;
import com.manpower.hbn.bgs.beans.DTAdvertContactBean;
import com.manpower.hbn.bgs.beans.DTBranchBean;
import com.manpower.hbn.shared.dao.DAOFactory;

/**
 * Servlet implementation class TestHibernate
 */
public class TestHibernate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestHibernate() {
        super();
     
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		
		response.setContentType("text/xml; charset=UTF-8");  
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation impl = builder.getDOMImplementation();
			Document doc = impl.createDocument(null,null,null);
			List<Object> tempBranches = (List)DAOFactory.getDAOFactory().getBranchesDAO().findAllBySiteId(new Long(10420));
			Element e1 = doc.createElement("branches");
			e1.setAttribute("siteid", "10420");
			doc.appendChild(e1);
			for (int i=0;i<tempBranches.size();i++){
				DTBranchBean branch = (DTBranchBean)tempBranches.get(i);
				Element branchElement = doc.createElement("branch");
				branchElement.setAttribute("branchid", Long.toString(branch.getId()));
				branchElement.setAttribute("branchname", branch.getBranchname());
				branchElement.setAttribute("siteid", Long.toString(branch.getSite_id()));
				e1.appendChild(branchElement);
				List tempRecruiters = (List)DAOFactory.getDAOFactory().getAdvertContactDAO().findAllByBranchId(branch.getId());
				for (int x=0;x<tempRecruiters.size();x++){
					DTAdvertContactBean recruiter = (DTAdvertContactBean)tempRecruiters.get(x);
					Element recruiterElement = doc.createElement("recruiter");
					recruiterElement.setAttribute("advertcontactid", Long.toString(recruiter.getAdvertcontactid()));
					recruiterElement.setAttribute("branchid", Long.toString(recruiter.getBranch_id()));
					recruiterElement.setAttribute("name", recruiter.getName());
					recruiterElement.setAttribute("phonenumber", recruiter.getPhonenumber());
					recruiterElement.setAttribute("siteid", Long.toString(recruiter.getSite_id()));
					branchElement.appendChild(recruiterElement);
//					List tempAdverts = (List)DAOFactory.getDAOFactory().getAdvertDAO().findAllByContactId(recruiter.getAdvertcontactid());
//					for (int y=0;y<tempAdverts.size();y++){
//						DTAdvertBean advert = (DTAdvertBean)tempAdverts.get(y);
//						Element advertElement = doc.createElement("advertisement");
//						if (advert.getJobtitle()!=null)advertElement.setAttribute("jobtitle", advert.getJobtitle());
//						if (advert.getCandidateprofile()!=null)advertElement.setAttribute("profile", advert.getCandidateprofile());
//						if (advert.getCandidateskills()!=null)advertElement.setAttribute("skills", advert.getCandidateskills());
//						if (advert.getFoadvertid()!=null)advertElement.setAttribute("foadvertid", advert.getFoadvertid());
//						if (advert.getJobcode()!=null)advertElement.setAttribute("jobcode", advert.getJobcode());
//						if (advert.getJobdescription()!=null)advertElement.setAttribute("description", advert.getJobdescription());
//						advertElement.setAttribute("recruiterid", Long.toString(advert.getAdvertcontactid()));
//						advertElement.setAttribute("adid", Long.toString(advert.getAdvertisementid()));
//						if (advert.getExpirationdate()!=null)advertElement.setAttribute("expiredate", advert.getExpirationdate().toString());
//						advertElement.setAttribute("siteid", Long.toString(advert.getSite_id()));
//						recruiterElement.appendChild(advertElement);
//					}
				}
			}
			
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
			out.println(xml);
			writeOutput(xml, "C:/xxLensXML/BranchRecruiter.xml");

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
       

		
//		out.println("<ul>");
//		for (int i=0;i<temp.size();i++){
//			DTBranchBean bbean = (DTBranchBean)temp.get(i);
//			out.println("<li>"+bbean.getBranchname()+"</li>");
//		}
//		out.println("</ul>");
//		out.println(temp.size());
//		
//		List<Object> temp2 = (List)DAOFactory.getDAOFactory().getAdvertContactDAO().findAll();
//		out.println("CONTACT LIST SIZE:  "+temp2.size());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
	
}
