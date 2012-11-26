package com.ibm.slwcmbulkupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.PortletSession;
import javax.portlet.ReadOnlyException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.ValidatorException;
import javax.portlet.WindowState;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import com.ibm.workplace.wcm.api.ChildPosition;
import com.ibm.workplace.wcm.api.Content;
import com.ibm.workplace.wcm.api.DocumentId;
import com.ibm.workplace.wcm.api.DocumentIdIterator;
import com.ibm.workplace.wcm.api.DocumentLibrary;
import com.ibm.workplace.wcm.api.DocumentType;
import com.ibm.workplace.wcm.api.DocumentTypes;
import com.ibm.workplace.wcm.api.FileComponent;
import com.ibm.workplace.wcm.api.LinkComponent;
import com.ibm.workplace.wcm.api.SiteArea;
import com.ibm.workplace.wcm.api.WCM_API;
import com.ibm.workplace.wcm.api.Workspace;
import com.ibm.workplace.wcm.api.exceptions.AuthorizationException;
import com.ibm.workplace.wcm.api.exceptions.DocumentRetrievalException;
import com.ibm.workplace.wcm.api.exceptions.OperationFailedException;
import com.ibm.workplace.wcm.api.exceptions.ServiceNotAvailableException;

/**
 * A sample portlet based on GenericPortlet
 */
public class SLWcmBulkUploadPortlet extends GenericPortlet {

	public static final String JSP_FOLDER    = "/_SLWcmBulkUpload/jsp/";    // JSP folder name

	public static final String VIEW_JSP      = "SLWcmBulkUploadPortletView";         // JSP file name to be rendered on the view mode
	public static final String EDIT_JSP      = "SLWcmBulkUploadPortletEdit";         // JSP file name to be rendered on the edit mode
	
	public static final String CONFIG_JSP    = "SLWcmBulkUploadPortletConfig";       // JSP file name to be rendered on the configure mode
	public static final String SESSION_BEAN  = "SLWcmBulkUploadPortletSessionBean";  // Bean name for the portlet session
	public static final String FORM_SUBMIT   = "SLWcmBulkUploadPortletFormSubmit";   // Action name for submit form
	public static final String FORM_TEXT     = "SLWcmBulkUploadPortletFormText";     // Parameter name for the text input
	public static final String FILE_INPUT 	 = "SLWcmBulkUploadPortletFormFileInput";
	public static final String FILE_PATH	 = "SLWcmBulkUploadPortletFormFilePath";
	public static final String FILES_DIRECTORY = "SLWcmBulkUploadPortletFromFilesDirectory";
	
	public static final String SESS_TOTAL_ROWS = "SLWcmBulkUploadPortletTotalRows";
	public static final String SESS_COUNT = "SLWcmBulkUploadPortletSessionCount";
   
	public static final String EDIT_SUBMIT   = "SLWcmBulkUploadPortletEditSubmit";   // Action name for submit form
	public static final String EDIT_TEXT     = "SLWcmBulkUploadPortletEditText";     // Parameter name for the text input
	public static final String EDIT_KEY      = ".SLWcmBulkUploadPortletEditKey";     // Key for the portlet preferences
	public static final String CONFIG_SUBMIT = "SLWcmBulkUploadPortletConfigSubmit"; // Action name for submit form
	public static final String CONFIG_TEXT   = "SLWcmBulkUploadPortletConfigText";   // Parameter name for the text input
	public static final String CONFIG_KEY    = ".SLWcmBulkUploadPortletConfigKey";   // Key for the portlet preferences

	private static final PortletMode CUSTOM_CONFIG_MODE = new PortletMode("config");
	
	private static final String EXTERNAL = "Document Link";
	private static final String INTERNAL = "Document";
	private static final String LIB_FR = "SL Content_FR";
	private static final String LIB_EN = "SL Content";
	private static final String LIB_AUTH = "Web Content";
	/**
	 * @see javax.portlet.Portlet#init()
	 */
	public void init() throws PortletException{
		super.init();
	}

	/**
	 * Serve up the <code>view</code> mode.
	 * 
	 * @see javax.portlet.GenericPortlet#doView(javax.portlet.RenderRequest, javax.portlet.RenderResponse)
	 */
	public void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		// Set the MIME type for the render response
		response.setContentType(request.getResponseContentType());

		// Check if portlet session exists
		SLWcmBulkUploadPortletSessionBean sessionBean = getSessionBean(request);
		if( sessionBean==null ) {
			response.getWriter().println("<b>NO PORTLET SESSION YET</b>");
			return;
		}

		
		
		
		
		// Invoke the JSP to render
		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(getJspFilePath(request, VIEW_JSP));
		rd.include(request,response);
	}

	/**
	 * Serve up the <code>edit</code> mode.
	 * 
	 * @see javax.portlet.GenericPortlet#doEdit(javax.portlet.RenderRequest, javax.portlet.RenderResponse)
	 */
	public void doEdit(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		// Set the MIME type for the render response
		response.setContentType(request.getResponseContentType());

		// Check if portlet session exists
		SLWcmBulkUploadPortletSessionBean sessionBean = getSessionBean(request);
		if( sessionBean==null ) {
		    response.getWriter().println("<b>NO PORTLET SESSION YET</b>");
			return;
		}

		// Invoke the JSP to render
		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(getJspFilePath(request, EDIT_JSP));
		rd.include(request,response);
	}
	
	/**
	 * Serve up the custom <code>config</code> mode.
	 */
	protected void doCustomConfigure(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		// Set the MIME type for the render response
		response.setContentType(request.getResponseContentType());
		// Invoke the JSP to render
		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(getJspFilePath(request, CONFIG_JSP));
		rd.include(request,response);
	}
	
	/**
	 * Override doDispatch method for handling custom portlet modes.
	 * 
	 * @see javax.portlet.GenericPortlet#doDispatch(javax.portlet.RenderRequest, javax.portlet.RenderResponse)
	 */
	protected void doDispatch(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		if (!WindowState.MINIMIZED.equals(request.getWindowState())){
			PortletMode mode = request.getPortletMode();			
			if (CUSTOM_CONFIG_MODE.equals(mode)) {
				doCustomConfigure(request, response);
				return;
			}
		}
		super.doDispatch(request, response);
	}

	/**
	 * Process an action request.
	 * 
	 * @see javax.portlet.Portlet#processAction(javax.portlet.ActionRequest, javax.portlet.ActionResponse)
	 */
	public void processAction(ActionRequest request, ActionResponse response) throws PortletException, java.io.IOException {
		
		if( request.getParameter(FORM_SUBMIT) != null ) {
			
		}
	
		if( request.getParameter(EDIT_SUBMIT) != null ) {
			PortletPreferences prefs = request.getPreferences();
			try {
				prefs.setValue(EDIT_KEY,request.getParameter(EDIT_TEXT));
				prefs.store();
			}
			catch( ReadOnlyException roe ) {
			}
			catch( ValidatorException ve ) {
			}
		}
		if( request.getParameter(CONFIG_SUBMIT) != null ) {
			PortletPreferences prefs = request.getPreferences();
			try {
				prefs.setValue(CONFIG_KEY,request.getParameter(CONFIG_TEXT));
				prefs.store();
			}
			catch( ReadOnlyException roe ) {
			}
			catch( ValidatorException ve ) {
			}
		}
	}

	/**
	 * Get SessionBean.
	 * 
	 * @param request PortletRequest
	 * @return SLWcmBulkUploadPortletSessionBean
	 */
	private static SLWcmBulkUploadPortletSessionBean getSessionBean(PortletRequest request) {
		PortletSession session = request.getPortletSession();
		if( session == null )
			return null;
		SLWcmBulkUploadPortletSessionBean sessionBean = (SLWcmBulkUploadPortletSessionBean)session.getAttribute(SESSION_BEAN);
		if( sessionBean == null ) {
			sessionBean = new SLWcmBulkUploadPortletSessionBean();
			session.setAttribute(SESSION_BEAN,sessionBean);
		}
		return sessionBean;
	}

	/**
	 * Returns JSP file path.
	 * 
	 * @param request Render request
	 * @param jspFile JSP file name
	 * @return JSP file path
	 */
	private static String getJspFilePath(RenderRequest request, String jspFile) {
		String markup = request.getProperty("wps.markup");
		if( markup == null )
			markup = getMarkup(request.getResponseContentType());
		return JSP_FOLDER + markup + "/" + jspFile + "." + getJspExtension(markup);
	}

	/**
	 * Convert MIME type to markup name.
	 * 
	 * @param contentType MIME type
	 * @return Markup name
	 */
	private static String getMarkup(String contentType) {
		if( "text/vnd.wap.wml".equals(contentType) )
			return "wml";
        else
            return "html";
	}

	/**
	 * Returns the file extension for the JSP file
	 * 
	 * @param markupName Markup name
	 * @return JSP extension
	 */
	private static String getJspExtension(String markupName) {
		return "jsp";
	}

	public static DocumentId getDocumentId(Workspace oWorkspace, DocumentType oDocumentType, String fileName) throws Exception{
		DocumentId oDocumentID = null;
		DocumentIdIterator oDocumentIdIteratorContents = oWorkspace.findByName(oDocumentType,fileName);
		
		if(oDocumentIdIteratorContents.hasNext()){
			oDocumentID= (DocumentId) oDocumentIdIteratorContents.next();
		}
		return oDocumentID;
	}

	private static File getFile(URL url,String count){
		
		URLConnection conn=null;
		InputStream is=null;
		OutputStream out=null;
		File tempFile=null;
		try {
			conn = url.openConnection();
		
		String[]fileName = url.toString().split("/");
		
		tempFile = new File("C:/temp/test/"+count+fileName[fileName.length-1]);
		is = conn.getInputStream();
		out=new FileOutputStream(tempFile);
		  byte buf[]=new byte[2048];
		  int len;
		  while((len=is.read(buf))>0){
		  out.write(buf,0,len);
		  
		 
		  }	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		  try {
			if (out!=null)out.flush();
			if (out!=null)out.close();
			if (is!=null)is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		}
		return tempFile;
	}
	@Override
	public void serveResource(ResourceRequest request, ResourceResponse response)
			throws PortletException, IOException {
		Workspace workSpace=null;
		InputStream xlsFile = null;
	
		
		if( request.getParameter("actionSubmit") != null ) {
			if (request.getParameter(FILE_PATH)!=null){
				try {
				xlsFile = new FileInputStream(request.getParameter(FILE_PATH));
				HSSFWorkbook wb = new HSSFWorkbook(xlsFile);
				HSSFSheet sheet = wb.getSheetAt(0);
				Iterator<Row> rows = sheet.rowIterator();
				//GET Total Number of rows....
				int count = 0;
				while (rows.hasNext()){
					
					count++;
					rows.next();
				}
				request.getPortletSession().setAttribute(SESS_TOTAL_ROWS, count);
				
			//SET WORKSPACE
				workSpace = WCM_API.getRepository().getWorkspace(request.getUserPrincipal());
				Short internal = 0;
				Short docTitleEN = 1;
				Short urlEN = 2;
				Short docTitleFR = 3;
				Short urlFR= 4;
				/*
				 * COL (0) - Internal / External
				 * COL (1) - Doc Title EN
				 * COL (2) - URL EN
				 * COL (3) - Doc Title FR
				 * COL (4) - URL FR
				 * 
				 * Authoring Template
				 * 
				 * File Component - "Document"
				 * Name 
				 * Description
				 * 
				 */
				rows=sheet.rowIterator();
				count = 0;
				while (rows.hasNext()){
					count++;
					File tempFile = null;
					request.getPortletSession().setAttribute(SESS_COUNT, count);
					HSSFRow row = (HSSFRow)rows.next();

					//SET AT AUTHORING TEMPLATE LIBRARY
					workSpace.setCurrentDocumentLibrary(workSpace.getDocumentLibrary(LIB_AUTH));	
					
					
					try {
						//SET AUTHORING TEMPLATE
						DocumentId authoringTemplate = null;
						if (row.getCell(internal).getStringCellValue().equals("External")){
							authoringTemplate = getDocumentId(workSpace, DocumentTypes.AuthoringTemplate, EXTERNAL);
						}else{
							authoringTemplate = getDocumentId(workSpace, DocumentTypes.AuthoringTemplate,INTERNAL);
						}
						//SET CONTENT DOC LIBRARY
						//ENGLISH
						workSpace = WCM_API.getRepository().getWorkspace(request.getUserPrincipal());
						workSpace.setCurrentDocumentLibrary(workSpace.getDocumentLibrary(LIB_EN));
						//DEBUG
						DocumentIdIterator dii = workSpace.findByType(DocumentTypes.SiteArea);
						while (dii.hasNext()){
							
							SiteArea sa;
							try {
								sa = (SiteArea) workSpace.getById(dii.nextId());
								System.out.println(sa.getId());
								System.out.println(sa.getName());
							} catch (DocumentRetrievalException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (AuthorizationException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}				
							
							
						}
						//DEBUG
						
						
						System.out.println("XX"+workSpace.getCurrentDocumentLibrary().getName());
						//SET SITE AREA
						//Not sure where you want to store this so, just setting it at the base "SLContent" for now.
						DocumentId siteArea = getDocumentId(workSpace, DocumentTypes.SiteArea, "SLContent");
						System.out.println("X"+siteArea);
//						//CREATE CONTENT
						Content content = workSpace.createContent(authoringTemplate, siteArea, null, ChildPosition.END);
						content.setName(row.getCell(docTitleEN).getStringCellValue());
						content.setDescription(row.getCell(docTitleEN).getStringCellValue());
						if (row.getCell(internal).getStringCellValue().equals("External")){
							LinkComponent linkComponent = (LinkComponent)content.getComponent("Document");
							linkComponent.setLinkDescription(row.getCell(docTitleEN).getStringCellValue());
							linkComponent.setLinkText(row.getCell(docTitleEN).getStringCellValue());
							linkComponent.setLinkTarget(row.getCell(urlEN).getStringCellValue());
						}else{
							FileComponent fileComponent = (FileComponent)content.getComponent("Document");
	//						String fileName = Integer.toString(count)+row.getCell(new Short("0")).getStringCellValue();
							URL url = new URL(row.getCell(urlEN).getStringCellValue());
							tempFile = getFile(url,Integer.toString(count));   
							fileComponent.setFile(Integer.toString(count)+row.getCell(new Short("0")).getStringCellValue(),tempFile);
							content.setComponent("Document", fileComponent);
						}
						System.out.println("ATTEMPTING SAVE!!");
						String errors[]=workSpace.save(content);
						
						for (int i = 0; i<errors.length;i++){
							System.out.println(errors[i]);
						}
						//System.out.println("PAST SAVE");
						if (tempFile!=null){
							tempFile.delete();
							tempFile = null;
						}
						
	
						//FRENCH
						workSpace.setCurrentDocumentLibrary(workSpace.getDocumentLibrary(LIB_FR));	
						//SET SITE AREA
						//Not sure where you want to store this so, just setting it at the base "SLContent" for now.
						siteArea = getDocumentId(workSpace, DocumentTypes.SiteArea, "SLContent");
//						//CREATE CONTENT
						content = workSpace.createContent(authoringTemplate, siteArea, null, ChildPosition.END);
						content.setName(row.getCell(docTitleEN).getStringCellValue());
						content.setDescription(row.getCell(docTitleEN).getStringCellValue());
						if (row.getCell(internal).getStringCellValue().equals("External")){
							LinkComponent linkComponent = (LinkComponent)content.getComponent("Document");
							linkComponent.setLinkDescription(row.getCell(docTitleFR).getStringCellValue());
							linkComponent.setLinkText(row.getCell(docTitleFR).getStringCellValue());
							linkComponent.setLinkTarget(row.getCell(urlFR).getStringCellValue());
						}else{
							FileComponent fileComponent = (FileComponent)content.getComponent("Document");
	//						String fileName = Integer.toString(count)+row.getCell(new Short("0")).getStringCellValue();
							URL url = new URL(row.getCell(urlFR).getStringCellValue());
							tempFile = getFile(url,Integer.toString(count));   
							fileComponent.setFile(Integer.toString(count)+row.getCell(new Short("0")).getStringCellValue(),tempFile);
							content.setComponent("Document", fileComponent);
						}
						String errorsFR[]=workSpace.save(content);
						
						for (int i = 0; i<errorsFR.length;i++){
							System.out.println(errorsFR[i]);
						}
						//System.out.println("PAST SAVE");
						if (tempFile!=null)tempFile.delete();
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	

//					break;
				}
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServiceNotAvailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (OperationFailedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				if (workSpace!=null)workSpace.logout();
				if (xlsFile!=null)xlsFile.close();
			}
		}
		}
		
		if (request.getParameter("check")!=null){
			if (request.getPortletSession().getAttribute(SESS_TOTAL_ROWS)!=null){
				Integer totalRows = (Integer)request.getPortletSession().getAttribute(SESS_TOTAL_ROWS);
				Integer count = (Integer)request.getPortletSession().getAttribute(SESS_COUNT);
				float percentage =(float)count/totalRows;
				int percent = Math.round(percentage*100);
				response.getWriter().println(percent);
			}else{
				response.getWriter().println(0);
			}
		}

		
		super.serveResource(request, response);
	}
	
	  public static float Round(float Rval, int Rpl) {
		  float p = (float)Math.pow(10,Rpl);
		  Rval = Rval * p;
		  float tmp = Math.round(Rval);
		  return (float)tmp/p;
		  }
	
}
