package com.manpower.portal.portlet.wcmviewer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;

import javax.portlet.PortletPreferences;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentHelper;

import pagecode.PageCodeBase;
import pagecode.WcmViewer.WcmViewerConfig;

import com.ibm.workplace.wcm.api.Content;
import com.ibm.workplace.wcm.api.DocumentId;
import com.ibm.workplace.wcm.api.DocumentIdIterator;
import com.ibm.workplace.wcm.api.RenderingContext;
import com.ibm.workplace.wcm.api.Repository;
import com.ibm.workplace.wcm.api.Site;
import com.ibm.workplace.wcm.api.SiteArea;
import com.ibm.workplace.wcm.api.WCM_API;
import com.ibm.workplace.wcm.api.Workspace;
import com.manpower.dom.util.DOMUtil;
import com.manpower.dom.util.XMLGenerator;
import com.manpower.portal.portlet.wcmutility.beans.WcmSiteAreaBean;

/**
 * Servlet implementation class for Servlet: WcmViewerServlet
 *
 */
 public class WcmViewerServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String CONST_SITE_CLASS_NAME ="com.ibm.workplace.wcm.api.WCM_Site";
	private static final String CONST_SITE_AREA_CLASS_NAME="com.ibm.workplace.wcm.api.WCM_SiteArea";
	private static final String CONST_SITE_CONTENT_CLASS_NAME="com.ibm.workplace.wcm.api.WCM_Content";
	public static final String PARAM_SITE_ID="com.manpower.portal.portlet.wcmviewer.param.site.id";
	public static final String PARAM_SITE_TYPE="com.manpower.portal.portlet.wcmviewer.param.site.type";
	protected Repository repository;

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public WcmViewerServlet() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	protected PortletPreferences getPortletPreferences(HttpServletRequest request){
		PortletPreferences prefs = null;
		for (Enumeration e  = request.getSession().getAttributeNames(); e.hasMoreElements();){
			String temp = (String)e.nextElement();
			if (temp.indexOf(PageCodeBase.SESS_PORTLET_PREFERENCES)>-1){
				prefs=(PortletPreferences)request.getSession().getAttribute(temp);
				return prefs;
			}
		}
		return null;
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		PortletPreferences prefs = getPortletPreferences(request);
		
		if (request.getParameter(PARAM_SITE_ID)!=null){
			try{
				Workspace workspace = getWcmWorkSpace(prefs);
				DocumentId selectedSiteId = workspace.createDocumentId(request.getParameter(PARAM_SITE_ID));
				StringBuffer sb = new StringBuffer();
				
				
				sb.append("<?xml-stylesheet type=\"text/xsl\"?>");
				sb.append("<!DOCTYPE stylesheet [<!ENTITY nbsp \"&#160;\"><!-- no-break space -->]>");
				sb.append("<results>");
				WcmSiteAreaBean wsab = new WcmSiteAreaBean();

				Object selectedSite=workspace.getById(selectedSiteId);
				if (selectedSite.getClass().getName().equals(CONST_SITE_CONTENT_CLASS_NAME)){
					Content selectedObject = (Content)selectedSite;
					DocumentIdIterator dii = selectedObject.getParents();
					if (dii.hasNext()){
						DocumentId sAreaId =(DocumentId)dii.next();
						SiteArea contentSiteArea = (SiteArea)workspace.getById(sAreaId);
						try{
							HashMap params = new HashMap();
							String appPath = "http://"+request.getServerName()+":"+request.getServerPort()+"/wps/wcm";
							RenderingContext rc = workspace.createRenderingContext(request,response,params,appPath,"/connect");
							rc.setRenderedContent(selectedObject,contentSiteArea);
							if (rc.getContent() != null) {
								
								StringBuffer contentDisplay = new StringBuffer();
								contentDisplay.append(workspace.render(rc));
								out.println(contentDisplay.toString());
							}
						}catch (Exception e){
							e.printStackTrace();
						}
					
					}
					
					
				}else{
					DocumentIdIterator children=null;
					if (selectedSite.getClass().getName().equals(CONST_SITE_AREA_CLASS_NAME)){
						SiteArea selectedObject = (SiteArea)selectedSite;
						children = selectedObject.getChildren();
					}else if (selectedSite.getClass().getName().equals(CONST_SITE_CLASS_NAME)){
						Site selectedObject = (Site)selectedSite; 
						children = selectedObject.getChildren();
					}	
					
					while(children.hasNext()){
						DocumentId siteAreaId = (DocumentId)children.next();
						wsab = new WcmSiteAreaBean(workspace.getById(siteAreaId));
						sb.append(XMLGenerator.getAsXMLMessage(wsab));
					}
					}
					sb.append("</results>");
					try{
						org.dom4j.Document document = DocumentHelper.parseText(sb.toString());
						String filePath = request.getSession().getServletContext().getRealPath("/");
						out.println(DOMUtil.getTransformedString(document,filePath+"/xsl/wcmselector.xsl"));
					}catch(Exception e){
						e.printStackTrace();
					}

					
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				endRepository();
			}
		}
	}  
	
	protected String processXMLDocument(HttpServletRequest request,StringBuffer rawXML){
		StringBuffer result = new StringBuffer();
		try{
			org.dom4j.Document document = DocumentHelper.parseText(rawXML.toString());
			String filePath = request.getSession().getServletContext().getRealPath("/");
			result.append(DOMUtil.getTransformedString(document,filePath+"/xsl/wcmselector.xsl"));
		}catch(Exception e){
			e.printStackTrace();
		}
		return result.toString();
	}
	
	protected Repository getWcmRepository(){
		repository=WCM_API.getRepository();
		return repository;
	}
	protected void endRepository(){
		if (repository!=null){
			repository.endWorkspace();
			repository=null;
		}
	}
	
	protected Workspace getWcmWorkSpace(PortletPreferences prefs){
		Workspace workspace = null;
		try {			
			workspace = getWcmRepository().getWorkspace(prefs.getValue(WcmViewerConfig.CONFIG_PREF_WCMUSERNAME, ""),
					prefs.getValue(WcmViewerConfig.CONFIG_PREF_WCMUSERPASSWORD, ""));
			
//			workspace = getWcmRepository().getWorkspace("wcmadmin","WcmStagePass");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return workspace;
	}
}