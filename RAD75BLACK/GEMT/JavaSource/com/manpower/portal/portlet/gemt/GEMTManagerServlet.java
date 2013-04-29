package com.manpower.portal.portlet.gemt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;

import com.ibm.portal.puma.User;
import com.manpower.dom.util.DOMUtil;
import com.manpower.portal.gemt.hbn.beans.GemtDirectReportHbnBean;
import com.manpower.portal.gemt.ui.beans.GemtDirectReportUIBean;
import com.manpower.portal.gemt.ui.beans.GemtManagersUIBean;
import com.manpower.portal.gemt.ui.services.GEMTServiceLocator;
import com.manpower.portal.utility.UserManager;

/**
 * Servlet implementation class for Servlet: GEMTManagerServlet
 *
 */
 public class GEMTManagerServlet extends BaseAjaxServlet implements javax.servlet.Servlet {

	public static String PARAM_ID = "com.manpower.portal.portlet.gemt.gemtmanagerservlet.param.id";
	public static String PARAM_ACTION ="com.manpower.portal.portlet.gemt.gemtmanagerservlet.param.action"; 
	public static String PARAM_NAMESPACE="com.manpower.portal.portlet.gemt.gemtmanagerservlet.param.namespace";
	public static String PARAM_SELECTED_REGION = "com.manpower.portal.portlet.gemt.gemtmanagerservlet.param.selectedregion";
	public static String PARAM_SELECTED_USERS = "com.manpower.portal.portlet.gemt.gemtmanagerservelet.param.selectedusers";
	
	public static String SESS_SELECTED_MANAGER = "com_manpower_portal_portlet_gemt_gemtmanager_selectedmanager";
	public static String SESS_AVAIL_REGIONS = "com_manpower_portal_portlet_gemt_gemtmanager_availregions";
	public static String SESS_SELECTED_REGION = "com_manpower_portal_portlet_gemt_gemtmanager_selectedregion";
	
	public static String ACTION_VIEWMANAGERS = "viewmanagers";
	public static String ACTION_REMOVEUSER = "removeuser";
	public static String ACTION_ADDUSER = "adduser";
	public static String ACTION_PICKUSER = "pickuser";
	public static String ACTION_FILTERUSERS = "filterusers";
    /**
	 * 
	 */ 
	private static final long serialVersionUID = -2635108104632997676L;

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public GEMTManagerServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		out.println(processAction(request, response));
	} 
	private String processAction (HttpServletRequest request,HttpServletResponse response){
		String styleSheet="GEMTDirectReports.xsl";
		String filePath = request.getSession().getServletContext().getRealPath("/");
		String urlPath = request.getContextPath();
		String id = request.getParameter(PARAM_ID);
		String action = request.getParameter(PARAM_ACTION);
		String namespace = request.getParameter(PARAM_NAMESPACE);
//		String selectedregion = request.getParameter(PARAM_SELECTED_REGION);
		StringBuffer transformResults=new StringBuffer();
		/*
		 * Open the XML Document...
		 */
		StringBuffer sb = beginXmlDoc();
		
		if (action.equals(ACTION_VIEWMANAGERS)){
			GemtManagersUIBean gemtUI = GEMTServiceLocator.getInstance().getManagersService().findByEmail(id);
			request.getSession().setAttribute(SESS_SELECTED_MANAGER, id);
			if (gemtUI==null){
				gemtUI = getManagerFromLdap(request,id);
				gemtUI = GEMTServiceLocator.getInstance().getManagersService().AddManager(gemtUI);
			}
			if (gemtUI!=null){
				gemtUI.setGemt_direct_reports(GEMTServiceLocator.getInstance().getDirectReportService()
							.findAllByManagerId(gemtUI.getGemt_sum_mgremail(), "gemt_sum_empname", 
						GemtDirectReportHbnBean.ORDER_ASC));
				gemtUI.setXml_real_path(urlPath);
				gemtUI.setPortletnamespace(namespace);
				sb.append(getXMLElementFromHibBean(gemtUI));
			}
		}else if (action.equals(ACTION_REMOVEUSER)){
			String selectedManager = (String) request.getSession().getAttribute(SESS_SELECTED_MANAGER);
			GemtManagersUIBean gemtUI = GEMTServiceLocator.getInstance().getManagersService().findByEmail(selectedManager);
			//REMOVE THE SELECTE USER
			GEMTServiceLocator.getInstance().getDirectReportService().delete(new Long(id));
			//RECREATE THE LIST MINUS THE USER
			if (gemtUI!=null){
				gemtUI.setGemt_direct_reports(GEMTServiceLocator.getInstance().getDirectReportService()
							.findAllByManagerId(gemtUI.getGemt_sum_mgremail(), "gemt_sum_empname", 
						GemtDirectReportHbnBean.ORDER_ASC));
				gemtUI.setXml_real_path(urlPath);
				gemtUI.setPortletnamespace(namespace);
				sb.append(getXMLElementFromHibBean(gemtUI));
			}
			
		}else if (action.equals(ACTION_ADDUSER)){
			System.out.println("ADD USER!!!");
			System.out.println("SELECTED MANAGER");
			System.out.println(request.getParameter(PARAM_SELECTED_USERS));
			System.out.println(request.getSession().getAttribute(SESS_SELECTED_MANAGER).toString());
			
			String [] userstoadd = request.getParameter(PARAM_SELECTED_USERS).split(",");
			try{
				UserManager um = new UserManager(request);
				
				for (int i = 0;i<userstoadd.length;i++){
//					System.out.println("TEST1");
					GemtDirectReportUIBean gdrui = new GemtDirectReportUIBean((User)um.findUserByUid(userstoadd[i]));
//					System.out.println("TEST2");
					gdrui.setGemt_sum_mgremail(request.getSession().getAttribute(SESS_SELECTED_MANAGER).toString());
					System.out.println("TEST3");
					GEMTServiceLocator.getInstance().getDirectReportService().addUpdateDirectReportFromLdap(gdrui);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			GemtManagersUIBean gemtUI = GEMTServiceLocator.getInstance().getManagersService().findByEmail(request.getSession().getAttribute(SESS_SELECTED_MANAGER).toString());
			gemtUI.setGemt_direct_reports(GEMTServiceLocator.getInstance().getDirectReportService()
					.findAllByManagerId(gemtUI.getGemt_sum_mgremail(), "gemt_sum_empname", 
				GemtDirectReportHbnBean.ORDER_ASC));
			gemtUI.setXml_real_path(urlPath);
			gemtUI.setPortletnamespace(namespace);
			sb.append(getXMLElementFromHibBean(gemtUI));
		}else if (action.equals(ACTION_PICKUSER)|| action.equals(ACTION_FILTERUSERS)){
			
			try {
				UserManager um = new UserManager(request);
				
				String [] regions = (String [])request.getSession().getAttribute(SESS_AVAIL_REGIONS);

				
				sb.append("<Portletnamespace>");
				sb.append(request.getParameter(PARAM_NAMESPACE));
				sb.append("</Portletnamespace>");
//				Group group = um.getGroup("gemt_users");
				List users = new ArrayList();
				HashMap map = new HashMap();
				if (!request.getParameter(PARAM_SELECTED_REGION).equals("")){
					String[] localityNames = request.getParameter(PARAM_SELECTED_REGION).split(",");
					users = um.findUsersByLocality(localityNames);
					//Create an easy way to see what is selected and save that when the
					//page is re-rendered.
					for (int i = 0;i<localityNames.length;i++){
						map.put(localityNames[i], localityNames[i]);
					}
				}else{
//					users = um.findUsersByLocality("NA");
					users = um.getAllUsersFromGroup("gemt_users");
				}
				
				for (int x=0;x<regions.length;x++){
					sb.append("<Region>");
					sb.append("<Region_name>"+regions[x]+"</Region_name>");
					sb.append("<Region_selected>");
					if(map.get(regions[x])!=null)sb.append("true");
					sb.append("</Region_selected>");
					sb.append("</Region>");
				}
				
				
				
//				List users = group.getMembers();
				for (int i = 0;i<users.size();i++){
					User user = (User)users.get(i);
					GemtDirectReportUIBean gdrui = new GemtDirectReportUIBean(user);
					gdrui.setPortletnamespace(namespace);
					gdrui.setXml_real_path(urlPath);
					sb.append(getXMLElementFromHibBean(gdrui));
				}
				styleSheet="GEMTAddUser.xsl";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		/*
		 * Close the XML Document and Transform the results....
		 */
		sb=endXMLDoc(sb);
		Document document = getXMLDocFromResult(sb.toString());
		transformResults.append(DOMUtil.getTransformedString(document,filePath+"/xsl/"+styleSheet));
		
		return transformResults.toString();
	}
	private GemtManagersUIBean getManagerFromLdap(HttpServletRequest request,String managerUid){
		GemtManagersUIBean gemtUI = null;
		try {
			UserManager um = new UserManager(request);
			User user = (User) um.findUserByUid(managerUid);
			if (user!=null){
				gemtUI=new GemtManagersUIBean(user);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return gemtUI;
	}
}