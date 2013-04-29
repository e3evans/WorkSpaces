package com.manpower.portal.portlet.gemt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dom4j.Document;

import com.manpower.dom.util.DOMUtil;
import com.manpower.portal.gemt.hbn.beans.BaseHibernateBean;
import com.manpower.portal.gemt.hbn.beans.GemtDirectReportHbnBean;
import com.manpower.portal.gemt.ui.beans.GemtSummaryReportUIBean;
import com.manpower.portal.gemt.ui.services.GEMTServiceLocator;

/**
 * Servlet implementation class for Servlet: GEMTServlet
 *
 */
 public class GEMTServlet extends BaseAjaxServlet implements Servlet {
   	
//	 public static String ACTION_
	 public static String PARAM_ID = "com.manpower.portal.portlet.gemt.param.id";
	 public static String PARAM_ACTION ="com.manpower.portal.portlet.gemt.param.action";
	 public static String PARAM_EMAIL ="com.manpower.portal.portlet.gemt.param.email";
	 
	 public static String SESS_ACTIVETAB="com.manpower.portal.portlet.gemt.sess.activetab";
	 public static String SESS_ACTIVEREPORT="com.manpower.portal.portlet.gemt.sess.activereport";
	 
	 public static String ACTION_VIEW_REPORTS="viewReports";
	 public static String ACTION_INPROGRESS="InProgress";
	 public static String ACTION_MIDYEAR="MidYear";
	 public static String ACTION_ANNUAL="Annual";
	 public static String ACTION_HISTORY="History";
	 public static String ACTION_QUICKVIEW="quickView";
	 public static String ACTION_QUICKVIEW_SELECTED="quickViewSelected";
	 public static String ACTION_SHOW_DIRECT_REPORTS="directReports";
	 
	 public static String PARAM_PORTLET_MODE="com.manpower.portal.portlet.gemt.param.portletMode";

	 private static Logger log=Logger.getLogger(GEMTServlet.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -2906578877735510964L;

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
	
	private String processAction(HttpServletRequest request, HttpServletResponse response){
		String styleSheet="";
		String filePath = request.getSession().getServletContext().getRealPath("/");
		String urlPath = request.getContextPath();
		String id = request.getParameter(PARAM_ID);
		String action = request.getParameter(PARAM_ACTION);
		String email = request.getParameter(PARAM_EMAIL);
		String portletMode=request.getParameter(PARAM_PORTLET_MODE);
		String activeTab=request.getParameter(SESS_ACTIVETAB);
		StringBuffer transformResults=new StringBuffer();
		StringBuffer sb = beginXmlDoc();
		boolean quickView = false;
		
		if (request.getSession().getAttribute(SESS_ACTIVETAB)!=null){
			activeTab=(String) request.getSession().getAttribute(SESS_ACTIVETAB);
		}
		if (action.equals(ACTION_QUICKVIEW)){
			GemtSummaryReportUIBean gsrub = GEMTServiceLocator.getInstance().getSummaryReportService().findById(new Long(id));
			gsrub.setXml_real_path(urlPath);
			sb.append(getXMLElementFromHibBean(gsrub));
			styleSheet = "GEMTGraphs.xsl";
			quickView=true;
		}else if (action.equals(ACTION_QUICKVIEW_SELECTED)) {
			/*
			 * Create a List of ID's based on the incoming comma seperated list
			 */
			System.out.println(id);
			String[]ids = id.split(",");
			Object[]objs = new Object[id.length()];
			for (int i=0;i<ids.length;i++){
				objs[i]=new Long(ids[i]);
			}
			List list = GEMTServiceLocator.getInstance().getSummaryReportService().getAllByIdArray(objs, "gemt_sum_repdate", BaseHibernateBean.ORDER_ASC);
			sb=processReportUIBeanList(sb, list, urlPath,portletMode);
			
			styleSheet="GEMTGraphsConsolidated.xsl";
			quickView=true;
		}else if (action.equals(ACTION_MIDYEAR)){
			//System.out.println(email);
//			List list = GEMTServiceLocator.getInstance().getSummaryReportService().getAllBeforeAfterMonth(email, "6", "<", "gemt_sum_repdate", BaseHibernateBean.ORDER_ASC);
			List list = GEMTServiceLocator.getInstance().getSummaryReportService().getAllbyPeriod(email,new Long("1"), portletMode);
			sb=processReportUIBeanList(sb,list,urlPath,portletMode);
			styleSheet="GEMTReportList.xsl";
			activeTab="tab1";
//		}else if (action.equals(ACTION_INPROGRESS)){
//			List list = GEMTServiceLocator.getInstance().getSummaryReportService().getAllbyUserEmail(email,"gemt_sum_repdate", BaseHibernateBean.ORDER_ASC);
//			sb=processReportUIBeanList(sb, list, urlPath);
//			styleSheet="GEMTReportList.xsl";
//			activeTab="tab1";
		}else if (action.equals(ACTION_ANNUAL)){
//			List list = GEMTServiceLocator.getInstance().getSummaryReportService().getAllBeforeAfterMonth(email, "5", ">", "gemt_sum_repdate", BaseHibernateBean.ORDER_ASC);
			List list = GEMTServiceLocator.getInstance().getSummaryReportService().getAllbyPeriod(email,new Long("2"), portletMode);
			sb=processReportUIBeanList(sb,list,urlPath,portletMode);
			styleSheet="GEMTReportList.xsl";
			activeTab="tab2";
		}else if (action.equals(ACTION_HISTORY)){
			List list = GEMTServiceLocator.getInstance().getSummaryReportService().findAllFinalized(email, portletMode);
			sb=processReportUIBeanList(sb,list,urlPath,portletMode);
//			sb.append("<NORESULTS/>");
			styleSheet="GEMTHistoryList.xsl";
			activeTab="tab3";
		}
		else if(action.equals(ACTION_SHOW_DIRECT_REPORTS))
		{
			log.debug("ACTION_SHOW_DIRECT_REPORTS");
			
			if(activeTab.equals("tab1"))
			{
				log.debug("tab1, email="+email);
				List list = GEMTServiceLocator.getInstance().getSummaryReportService().getAllbyPeriod(email,new Long("1"), portletMode);
				sb=processReportUIBeanList(sb,list,urlPath,portletMode);
				styleSheet="GEMTReportList.xsl";
			}
			else if(activeTab.equals("tab2"))
			{
				List list = GEMTServiceLocator.getInstance().getSummaryReportService().getAllbyPeriod(email,new Long("2"), portletMode);
				sb=processReportUIBeanList(sb,list,urlPath,portletMode);
				styleSheet="GEMTReportList.xsl";				
			}
			else
			{
				List list = GEMTServiceLocator.getInstance().getSummaryReportService().findAllFinalized(email, portletMode);
				sb=processReportUIBeanList(sb,list,urlPath,portletMode);
				styleSheet="GEMTHistoryList.xsl";				
			}
		}
		
		sb=endXMLDoc(sb);

		if (activeTab.equals(""))activeTab="tab1";
		
		Document document = getXMLDocFromResult(sb.toString());
		transformResults.append(DOMUtil.getTransformedString(document,filePath+"/xsl/"+styleSheet));
		request.getSession().setAttribute(SESS_ACTIVETAB,activeTab);
		if (!quickView && !action.equals(ACTION_SHOW_DIRECT_REPORTS)){
			request.getSession().setAttribute(SESS_ACTIVEREPORT, transformResults.toString());
		}
		return transformResults.toString();
	}

	private StringBuffer processReportUIBeanList(StringBuffer sb,List list,String urlPath, String portletMode){
		if (list.size()==0){
			sb.append("<NORESULTS/>");
		}else{
			GemtSummaryReportUIBean gsrub;
			for(int i=0;i<list.size();i++){
				gsrub = (GemtSummaryReportUIBean) list.get(i);
				gsrub.setXml_real_path(urlPath);
				
				String showDirectReportsButton="false";
				
				List directReports=GEMTServiceLocator.getInstance().getDirectReportService()
				.findAllByManagerId(gsrub.getGemt_sum_empemail(), "gemt_sum_empname", 
						GemtDirectReportHbnBean.ORDER_ASC);
				
				if(portletMode.equals("1") && !directReports.isEmpty())
				{
					showDirectReportsButton="true";
				}
				
				gsrub.setShowDirectReportsButton(showDirectReportsButton);
				sb.append(getXMLElementFromHibBean(gsrub));
			}
		}
		return sb;
	}	
}