/*
 * Created on Jan 12, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pagecode.GEMTEntryForm;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;

import pagecode.PageCodeBase;

import com.ibm.faces.component.html.HtmlRequestLink;
import com.ibm.portal.puma.User;
import com.manpower.dom.util.DOMUtil;
import com.manpower.dom.util.XMLGenerator;
import com.manpower.portal.gemt.hbn.beans.GemtDirectReportHbnBean;
import com.manpower.portal.gemt.ui.beans.GemtSummaryReportUIBean;
import com.manpower.portal.gemt.ui.services.GEMTServiceLocator;
import com.manpower.portal.gemt.ui.services.GemtSummaryReportService;
import com.manpower.portal.portlet.gemt.GEMTServlet;
import com.manpower.portal.utility.UserManager;
import com.ibm.faces.component.html.HtmlCommandExButton;
import javax.faces.component.html.HtmlForm;
import com.ibm.faces.component.html.HtmlGraphicImageEx;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.UIParameter;
import com.ibm.faces.component.html.HtmlScriptCollector;

import javax.faces.component.html.HtmlMessages;
import javax.faces.component.html.HtmlInputText;
import javax.faces.event.ActionEvent;
import javax.faces.component.html.HtmlCommandLink;
/**
 * @author Eric Evans
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GEMTEntryFormView extends PageCodeBase {

	private static Logger log=Logger.getLogger(GEMTEntryFormView.class);
	
	public static String SESS_REPORTLIST = "gemtentryformreportlist";
	public static String SESS_DISPLAYREPORT = "gemtentryformreportdisplay";
	public static String SESS_CONTEXTPATH = "gemtentryformcontextpath";
	public static String SESS_EMAIL="gemtentryformemail";
	public static String SESS_ACTIVETAB="gemtentryformactivetab";
	public static String SESS_PORTLETMODE = "gemtentryformportletmode";
	
	
	
	protected HtmlCommandExButton phantomButton;
	protected HtmlForm form1;
	protected HtmlGraphicImageEx imageEx2;
	protected HtmlGraphicImageEx imageEx1;
	protected HtmlOutputText text3;
	protected HtmlOutputText text2;
	protected HtmlGraphicImageEx imageEx3;
	protected HtmlGraphicImageEx imageEx4;
	protected UIParameter param2;
	protected HtmlScriptCollector scriptCollector1;
	protected HtmlMessages messages1;
	protected HtmlOutputText text4;
	protected HtmlOutputText text1;
	protected HtmlCommandExButton viewReports;
	protected HtmlInputText submitId;
	protected HtmlCommandExButton deleteReport;
	protected UIParameter param3;
	protected HtmlOutputText txtViewEdit;
	protected HtmlRequestLink createMidYear;
	protected HtmlOutputText text6;
	protected HtmlOutputText txtUserInfo;
	protected HtmlOutputText text5;
	protected HtmlRequestLink createAnnual;
	protected HtmlOutputText text7;
	protected HtmlCommandLink link1;
	public void onPageLoadBegin(FacesContext facescontext) {
		// Type Java code to handle page load begin event here
		User user=null;
		try{
			UserManager um = new UserManager(facescontext);
			user = (User) um.getCurrentUser();
			sessionScope.put(SESS_EMAIL, user.getUserID());
		}catch(Exception e){
			e.printStackTrace();
		}
		PortletRequest portletRequest = (PortletRequest)facescontext.getExternalContext().getRequest();
		
		PortletPreferences preferences=portletRequest.getPreferences();
		String portletMode=preferences.getValue(GEMTEntryFormEdit.PREF_MGREMPMODE, "0");
		sessionScope.put(SESS_PORTLETMODE, portletMode);
		
		if (portletRequest.getPortletSession().getAttribute(GEMTServlet.SESS_ACTIVEREPORT, PortletSession.APPLICATION_SCOPE)==null){
//			List list = GEMTServiceLocator.getInstance().getSummaryReportService().getAllbyUserEmail(user.getUserID(),"gemt_sum_repdate", GemtNotesHbnBean.ORDER_ASC);
//			List list = GEMTServiceLocator.getInstance().getSummaryReportService().getAllBeforeAfterMonth(user.getUserID(), "6", "<", "gemt_sum_repdate", BaseHibernateBean.ORDER_ASC);			
			List list = GEMTServiceLocator.getInstance().getSummaryReportService().getAllbyPeriod(user.getUserID(),new Long("1"), portletMode);
//			List list = GEMTServiceLocator.getInstance().getSummaryReportService().getAllbyPeriod("Sven Munaron",new Long("1"), portletMode);
//			System.out.println("USER ID:  "+user.getUserID());
			StringBuffer sb = new StringBuffer();
			try{
				HttpServletRequest request = (HttpServletRequest)facescontext.getExternalContext().getRequest();
				String urlPath = request.getContextPath();
				sb.append("<?xml-stylesheet type=\"text/xsl\"?>");
				sb.append("<!DOCTYPE stylesheet [<!ENTITY nbsp \"&#160;\"><!-- no-break space -->]>");
				sb.append("<results>");
				for(int i=0;i<list.size();i++){
					GemtSummaryReportUIBean gsrub;
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
					sb.append(XMLGenerator.getAsXMLMessage(gsrub));					
				}
				
				sb.append("</results>");
				//System.out.println(sb.toString());
				Document document = DocumentHelper.parseText(sb.toString());
				String filePath = request.getSession().getServletContext().getRealPath("/");
				sessionScope.put(SESS_REPORTLIST,DOMUtil.getTransformedString(document,filePath+"/xsl/GEMTReportList.xsl"));
	
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			sessionScope.put(SESS_REPORTLIST, portletRequest.getPortletSession().getAttribute(GEMTServlet.SESS_ACTIVEREPORT, PortletSession.APPLICATION_SCOPE));
			sessionScope.put(SESS_ACTIVETAB, portletRequest.getPortletSession().getAttribute(GEMTServlet.SESS_ACTIVETAB, PortletSession.APPLICATION_SCOPE));
		}
//		GEMTServiceLocator.getInstance().getSummaryReportService().getAllbyPeriod(user.getUserID(), new Long(GemtSummaryReportUIBean.TYPE_MID_YEAR_REPORT), GEMTEntryForm.MODE_EMPLOYEE);
//		String midYearReport
//		Object[]test=new Object[3];
//		List testList=GEMTServiceLocator.getInstance().getSummaryReportService().getAllByIdArray(test, "test", "test");
//		System.out.println("TST LIST SIZE:  "+testList.size());
		
		
	}
	
	public String doButton1Action() {
		// Type Java code that runs when the component is clicked
		getSessionScope().remove(SESS_DISPLAYREPORT);
		getSessionScope().remove(GEMTEntryFormReportEdit.SESS_REPORTSUMMARYUIBEAN);
		// TODO: Return outcome that corresponds to a navigation rule
		return "NEW_REPORT";
	}
	
	public String showMidYearReport() {
		// Type Java code that runs when the component is clicked
		getSessionScope().remove(SESS_DISPLAYREPORT);
		getSessionScope().remove(GEMTEntryFormReportEdit.SESS_REPORTSUMMARYUIBEAN);
		
		getSessionScope().put(SESS_ACTIVETAB,"tab1");
		
		String email=(String)sessionScope.get(SESS_EMAIL);
		String mode=(String)sessionScope.get(SESS_PORTLETMODE);
		
		GemtSummaryReportService service=GEMTServiceLocator.getInstance().getSummaryReportService();
		
		List reports=service.getAllActiveReportsByPeriod(email, new Long("1"), mode);
		
		if(reports.isEmpty())
		{
			log.debug("New midyear report will be displayed");
			
			return "NEW_REPORT";
		}
		else
		{		
			GemtSummaryReportUIBean firstReport=(GemtSummaryReportUIBean)reports.get(0);
			
			getSessionScope().put(SESS_DISPLAYREPORT,String.valueOf(firstReport.getId()));
			
			log.debug("Existing midyear report #"+firstReport.getId()+" will be edited");
			
			return "VIEW_REPORT";
		}
	}
	
	public String showAnnualReport() {
		// Type Java code that runs when the component is clicked
		getSessionScope().remove(SESS_DISPLAYREPORT);
		getSessionScope().remove(GEMTEntryFormReportEdit.SESS_REPORTSUMMARYUIBEAN);
		
		getSessionScope().put(SESS_ACTIVETAB,"tab2");

		String email=(String)sessionScope.get(SESS_EMAIL);
		String mode=(String)sessionScope.get(SESS_PORTLETMODE);
		
		GemtSummaryReportService service=GEMTServiceLocator.getInstance().getSummaryReportService();
		
		List reports=service.getAllActiveReportsByPeriod(email, new Long("2"), mode);
		
		if(reports.isEmpty())
		{
			log.debug("New annual report will be displayed");
			
			return "NEW_REPORT";
		}
		else
		{		
			GemtSummaryReportUIBean firstReport=(GemtSummaryReportUIBean)reports.get(0);
			
			getSessionScope().put(SESS_DISPLAYREPORT,String.valueOf(firstReport.getId()));
			
			log.debug("Existing annual report #"+firstReport.getId()+" will be edited");
			
			return "VIEW_REPORT";
		}
	}
	
	public String doLinkViewReportAction() {

		getSessionScope().put(SESS_DISPLAYREPORT,HtmlRequestLink.getParameter("paramId").toString());
		
		return "VIEW_PAGE";
	}
	public String doViewReportsAction() {
		// Tied to hidden button on the form.  Allows us to submit values generated by XML as JSF
		// components.
		getSessionScope().put(SESS_DISPLAYREPORT,getSubmitId().getValue().toString());
		// TODO: Return outcome that corresponds to a navigation rule
		return "";
	}

	public void doDeleteReport(ActionEvent event)
	{
		GemtSummaryReportService service=GEMTServiceLocator.getInstance().getSummaryReportService();
		
		service.delete(Long.valueOf(getSubmitId().getValue().toString()));
	}
	
	protected HtmlCommandExButton getPhantomButton() {
		if (phantomButton == null) {
			phantomButton = (HtmlCommandExButton) findComponentInRoot("phantomButton");
		}
		return phantomButton;
	}

	protected HtmlForm getForm1() {
		if (form1 == null) {
			form1 = (HtmlForm) findComponentInRoot("form1");
		}
		return form1;
	}

	protected HtmlGraphicImageEx getImageEx2() {
		if (imageEx2 == null) {
			imageEx2 = (HtmlGraphicImageEx) findComponentInRoot("imageEx2");
		}
		return imageEx2;
	}

	protected HtmlGraphicImageEx getImageEx1() {
		if (imageEx1 == null) {
			imageEx1 = (HtmlGraphicImageEx) findComponentInRoot("imageEx1");
		}
		return imageEx1;
	}

	protected HtmlOutputText getText3() {
		if (text3 == null) {
			text3 = (HtmlOutputText) findComponentInRoot("text3");
		}
		return text3;
	}

	protected HtmlOutputText getText2() {
		if (text2 == null) {
			text2 = (HtmlOutputText) findComponentInRoot("text2");
		}
		return text2;
	}

	protected HtmlGraphicImageEx getImageEx3() {
		if (imageEx3 == null) {
			imageEx3 = (HtmlGraphicImageEx) findComponentInRoot("imageEx3");
		}
		return imageEx3;
	}

	protected HtmlGraphicImageEx getImageEx4() {
		if (imageEx4 == null) {
			imageEx4 = (HtmlGraphicImageEx) findComponentInRoot("imageEx4");
		}
		return imageEx4;
	}

	protected UIParameter getParam2() {
		if (param2 == null) {
			param2 = (UIParameter) findComponentInRoot("param2");
		}
		return param2;
	}

	protected HtmlScriptCollector getScriptCollector1() {
		if (scriptCollector1 == null) {
			scriptCollector1 = (HtmlScriptCollector) findComponentInRoot("scriptCollector1");
		}
		return scriptCollector1;
	}

	protected HtmlMessages getMessages1() {
		if (messages1 == null) {
			messages1 = (HtmlMessages) findComponentInRoot("messages1");
		}
		return messages1;
	}

	protected HtmlOutputText getText4() {
		if (text4 == null) {
			text4 = (HtmlOutputText) findComponentInRoot("text4");
		}
		return text4;
	}

	protected HtmlOutputText getText1() {
		if (text1 == null) {
			text1 = (HtmlOutputText) findComponentInRoot("text1");
		}
		return text1;
	}

	protected HtmlCommandExButton getViewReports() {
		if (viewReports == null) {
			viewReports = (HtmlCommandExButton) findComponentInRoot("viewReports");
		}
		return viewReports;
	}

	protected HtmlInputText getSubmitId() {
		if (submitId == null) {
			submitId = (HtmlInputText) findComponentInRoot("submitId");
		}
		return submitId;
	}

	protected HtmlCommandExButton getDeleteReport() {
		if (deleteReport == null) {
			deleteReport = (HtmlCommandExButton) findComponentInRoot("deleteReport");
		}
		return deleteReport;
	}

	protected UIParameter getParam3() {
		if (param3 == null) {
			param3 = (UIParameter) findComponentInRoot("param3");
		}
		return param3;
	}

	protected HtmlOutputText getTxtViewEdit() {
		if (txtViewEdit == null) {
			txtViewEdit = (HtmlOutputText) findComponentInRoot("txtViewEdit");
		}
		return txtViewEdit;
	}

	protected HtmlRequestLink getCreateMidYear() {
		if (createMidYear == null) {
			createMidYear = (HtmlRequestLink) findComponentInRoot("createMidYear");
		}
		return createMidYear;
	}

	protected HtmlOutputText getText6() {
		if (text6 == null) {
			text6 = (HtmlOutputText) findComponentInRoot("text6");
		}
		return text6;
	}

	protected HtmlOutputText getTxtUserInfo() {
		if (txtUserInfo == null) {
			txtUserInfo = (HtmlOutputText) findComponentInRoot("txtUserInfo");
		}
		return txtUserInfo;
	}

	protected HtmlOutputText getText5() {
		if (text5 == null) {
			text5 = (HtmlOutputText) findComponentInRoot("text5");
		}
		return text5;
	}

	protected HtmlRequestLink getCreateAnnual() {
		if (createAnnual == null) {
			createAnnual = (HtmlRequestLink) findComponentInRoot("createAnnual");
		}
		return createAnnual;
	}

	protected HtmlOutputText getText7() {
		if (text7 == null) {
			text7 = (HtmlOutputText) findComponentInRoot("text7");
		}
		return text7;
	}

	protected HtmlCommandLink getLink1() {
		if (link1 == null) {
			link1 = (HtmlCommandLink) findComponentInRoot("link1");
		}
		return link1;
	}
}