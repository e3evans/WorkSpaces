package com.manpower.portal.portlet.gemt;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.portlet.*;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.portlet.PortletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.log4j.Logger;

import pagecode.GEMTEntryForm.GEMTEntryFormEdit;
import pagecode.GEMTEntryForm.GEMTEntryFormReportEdit;

import com.ibm.faces.portlet.FacesPortlet;
import com.manpower.portal.gemt.ui.beans.GemtSummaryReportFileUIBean;
import com.manpower.portal.gemt.ui.beans.GemtSummaryReportUIBean;
import com.manpower.portal.gemt.ui.services.GEMTServiceLocator;
import com.manpower.portal.gemt.ui.services.GemtSummaryReportFileLiteService;
import com.manpower.portal.gemt.ui.services.GemtSummaryReportFileService;
import com.manpower.portal.portlet.gemt.exceptions.GEMTFileException;

public class GEMTEntryForm extends FacesPortlet {

	public static final String JSP_FOLDER    = "/GEMTEntryForm/";
	public static final String JSP_PAGE_SPLASH = "GEMTEntryFormSplash.jsp";
	public static final String JSP_PAGE_REPORTVIEW = "/GEMTEntryForm/GEMTEntryFormReportView.jsp";
	public static final String JSP_PAGE_REPORTEDIT = "/GEMTEntryForm/GEMTEntryFormReportView.jsp";
	public static final String FILE_ID="GEMTEntryForm.FILE_ID";
	
	public static final String MODE_MANAGER="1";
	public static final String MODE_EMPLOYEE="2";
	
	private static Logger log=Logger.getLogger(GEMTEntryForm.class);
	public static final int FILES_TO_BE_UPLOADED=2;
	public static final String UPLOAD_ERROR="UPLOAD_ERROR";
	public static final int FILE_SIZE=10485760;
	
	public void destroy() {
		super.destroy();
	}

	public void doConfigure(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		super.doConfigure(request, response);
	}

	public void doEdit(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		super.doEdit(request, response);
	}

	public void doHelp(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		super.doHelp(request, response);
	}

	public void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		
		super.doView(request, response);
	}

	public void init(PortletConfig portletConfig) throws PortletException {
		super.init(portletConfig);
	}

	public void processAction(ActionRequest request, ActionResponse response) throws PortletException {
		
		String portletMode=request.getPreferences().getValue(GEMTEntryFormEdit.PREF_MGREMPMODE, "0");
		
		if(PortletFileUpload.isMultipartContent(request)){
	    	
	    	PortletFileUpload upload = new PortletFileUpload();
	    		    
	    	PortletSession session = request.getPortletSession();
	    	
	    	GemtSummaryReportUIBean report=(GemtSummaryReportUIBean)session.getAttribute(GEMTEntryFormReportEdit.SESS_REPORTSUMMARYUIBEAN);
	    	
	    	if(report==null)
	    	{
	    		log.error("There is no Report in the Session!!!");
	    		
	    		super.processAction(request, response);
	    	}
    	    
			GemtSummaryReportFileLiteService service =  GEMTServiceLocator.getInstance().getGemtSummaryReportFileLiteService();

			List files=service.findCommonFilesByReportId(report.getId());
			
			if(files!=null && files.size()==FILES_TO_BE_UPLOADED)
			{
				log.info("It is allowed to upload up to "+FILES_TO_BE_UPLOADED+" files!!!");
				
				session.setAttribute(UPLOAD_ERROR, "Only "+FILES_TO_BE_UPLOADED+" attachments allowed per report.");
				
				super.processAction(request, response);
			}
			
	    	try{
		    	//Parse the request for request parameters
		    	FileItemIterator iter = upload.getItemIterator(request);
		    	while (iter.hasNext()) {
		    	    FileItemStream item = iter.next();
		    	    String name = item.getFieldName();
		    	    InputStream stream = item.openStream();
		    	    
		    	    if (item.isFormField()) {
		    	    	log.debug("Form field " + name + " with value "
		    	            +Streams.asString(stream)  + " detected.");
		    	        
		    	    }	
		    	    else
		    	    {
		    	    	String itemName=item.getName();
		    	    	
		    	        log.debug("File field " + name + " with file name "
			    	            + itemName + " detected.");
			    	        // Process the input stream
		    	        String fileName=itemName.substring(itemName.lastIndexOf(File.separatorChar)+1);
		    	        
			    	    if(fileName!=null)
			    	    {
			    	    	log.debug("File "+ fileName+ " for "+report.getId()+" is to be saved");
				    		
			    	    	saveFile(stream, fileName, report,portletMode);
			    	    }		    	        
		    	    	
		    	    }
		    	}		    	
	    	}
	    	catch(IOException ioe)
	    	{
	    		log.error("Exception:",ioe);
	    	}
	    	catch(FileUploadException fue)
	    	{
	    		log.error("Exception:",fue);
	    	}
	    	catch(GEMTFileException gemtfe)
	    	{
	    		session.setAttribute(UPLOAD_ERROR, gemtfe.getMessage());
	    	}
		}
		super.processAction(request, response);
	}

	 protected long saveFile(InputStream file, String fileName, GemtSummaryReportUIBean report,String portletMode)throws GEMTFileException{
					 
	  		GemtSummaryReportFileService service =  GEMTServiceLocator.getInstance().getGemtSummaryReportFileService();

	  		GemtSummaryReportFileUIBean reportFile=new GemtSummaryReportFileUIBean(); 		

	  		
	  		reportFile.setGemt_sum_file_name(fileName);
	  		reportFile.setFileAsStream(file);	
	  		if(portletMode.equals("2"))
	  		{
	  			reportFile.setCreated_by(report.getGemt_sum_empname());
	  		}
	  		else
	  		{
	  			reportFile.setCreated_by(report.getGemt_sum_managername());
	  		}
	  		
	  		reportFile.setCreated_on(new Date());
	  		
	  		if(reportFile.getFileAsByteArray().length>FILE_SIZE)
	  		{
	  			String message="It is not allowed to upload files bigger than 10MB!!!";
				log.info(message);
				
				throw new GEMTFileException(message);
	  		}
	  		
			try{				
				
				reportFile = 	service.makePersistent(reportFile,report.getId());
													
				return reportFile.getId();
				
			}catch(Exception ex){
						    
				log.error("FAILED to save a file",ex);
			}			
			
			return 0;
		}	
}
