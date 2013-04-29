/*
 * Created on Jan 24, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pagecode.GEMTEntryForm;

import java.util.List;
import java.util.Map;

import javax.faces.component.UIColumn;
import javax.faces.component.UIData;
import javax.faces.component.html.HtmlForm;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

import pagecode.PageCodeBase;

import com.ibm.faces.component.html.HtmlCommandExButton;
import com.ibm.faces.component.html.HtmlGraphicImageEx;
import com.ibm.faces.component.html.HtmlScriptCollector;
import com.ibm.portal.puma.User;
import com.manpower.portal.gemt.ui.beans.GemtDirectReportUIBean;
import com.manpower.portal.gemt.ui.beans.GemtManagersUIBean;
import com.manpower.portal.gemt.ui.beans.GemtSummaryReportFileLiteUIBean;
import com.manpower.portal.gemt.ui.beans.GemtSummaryReportUIBean;
import com.manpower.portal.gemt.ui.services.GEMTServiceLocator;
import com.manpower.portal.gemt.ui.services.GemtSummaryReportFileLiteService;
import com.manpower.portal.gemt.ui.services.builder.GemtSmmaryReportBuilder;
import com.manpower.portal.utility.EmailConfig;
import com.manpower.portal.utility.EmailSender;
import com.manpower.portal.utility.UserManager;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlInputText;
import com.ibm.faces.component.html.HtmlOutputLinkEx;
import javax.faces.component.html.HtmlCommandLink;

import org.apache.log4j.Logger;
/**
 * @author Eric Evans
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GEMTEntryFormReportEdit extends PageCodeBase {

	private static Logger log=Logger.getLogger(GEMTEntryFormReportEdit.class);
	
	public static String SESS_REPORTSUMMARYUIBEAN = "GemtSummaryReportUIBean";
	public static String SESS_CURRENT_USER = "GemtSummaryReportCurrentUser";
	protected HtmlCommandExButton tyleClas;
	protected HtmlCommandExButton styleClas;
	protected HtmlForm form1;
	protected HtmlGraphicImageEx imageEx2;
	protected HtmlGraphicImageEx imageEx1;
	protected HtmlCommandExButton cancel;
	protected HtmlScriptCollector scriptCollector1;
	protected HtmlCommandExButton route;
	protected HtmlCommandExButton toggle;
	private String portletMode;
	protected HtmlCommandExButton saveReport;
	protected HtmlOutputText textevalueeName;
	protected HtmlOutputText gemt_sum_empname;
	protected HtmlOutputText textevalueeTitle;
	protected HtmlInputText gemt_sum_emptitle;
	protected HtmlOutputText textevalDate;
	protected HtmlInputText gemt_sum_repdate;
	protected HtmlOutputText textManager;
	protected HtmlOutputText gemt_sum_managername;
	protected HtmlOutputText textselectperiod;
	protected HtmlOutputText textLeadershipRole;
	protected HtmlOutputText textUnsat;
	protected HtmlOutputText textDeveloping;
	protected HtmlOutputText textProficient;
	protected HtmlOutputText textOutstanding;
	protected HtmlOutputText textClient;
	protected HtmlOutputText textPeople;
	protected HtmlOutputText textThought;
	protected HtmlOutputText textDay2Day;
	protected HtmlOutputText textBelowThresh;
	protected HtmlOutputText textThresh;
	protected HtmlOutputText textTarget;
	protected HtmlOutputText textOutstanding2;
	protected HtmlOutputText textScoreCardFinancial;
	protected HtmlOutputText textScoreCardKPI;
	protected HtmlOutputText textOverall;
	protected HtmlOutputText textUnsat2;
	protected HtmlOutputText textDev2;
	protected HtmlOutputText textProf2;
	protected HtmlOutputText textOut2;
	protected HtmlOutputText textresultsOverview;
	protected HtmlOutputText textComments;
	protected HtmlOutputText textIndividualDev;
	protected HtmlOutputText textDevStrengths;
	protected HtmlOutputText textDevNeeds;
	protected HtmlOutputText textDevActions;
	protected HtmlOutputText textResultsOverview;
	protected HtmlOutputText textExpectations;
	protected HtmlOutputLinkEx viewFileLink;
	protected HtmlCommandLink removeFile;
	protected HtmlCommandExButton cancel2;
	protected HtmlGraphicImageEx imageEx3;
	protected HtmlGraphicImageEx imageEx4;
	protected HtmlCommandExButton route2;
	protected HtmlCommandExButton toggle2;
	protected HtmlCommandExButton saveReport2;
	protected HtmlInputText id;
	protected HtmlInputText gemt_sum_empemail;
	protected HtmlOutputText text1;
	protected HtmlOutputText textPerfHighs;
	protected HtmlOutputText textMissedOpps;
	protected HtmlForm filesUploading;
	protected HtmlCommandExButton uploadFile;
	
	private String fileURL;
	
	public GEMTEntryFormReportEdit()
	{
		PortletRequest portletRequest = (PortletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		
		PortletPreferences preferences=portletRequest.getPreferences();
		this.portletMode=preferences.getValue(GEMTEntryFormEdit.PREF_MGREMPMODE, "0");
		
    	fileURL= getFacesContext().getExternalContext().getRequestContextPath()+"/GemtRetrieveFile"; 

	}
	
	public void onPageLoadBegin(FacesContext facescontext) {

		/**
		 * USED TO GET THE USER AND HIS MAPPED ATTRIBUTES
		 * DO NOT ERASE!!!!
		 */
//		 Type Java code to handle page load begin event here
		User user=null;
		try{
			UserManager um = new UserManager(facescontext);
			user = (User) um.getCurrentUser();
			sessionScope.put(SESS_CURRENT_USER, user.getUserID());
//			System.out.println(user.getUserID());
		}catch(Exception e){
			
			e.printStackTrace();
		}

		if (sessionScope.get(GEMTEntryFormView.SESS_DISPLAYREPORT)!=null){
			GemtSummaryReportUIBean gsrb =GEMTServiceLocator.getInstance()
				.getSummaryReportService().findById(new Long(sessionScope.get(GEMTEntryFormView.SESS_DISPLAYREPORT).toString()));
			gsrb.convertHtmlVals();
			
			
			gsrb.setGemt_sum_period(getCurrentPeriod());
			
			sessionScope.put(SESS_REPORTSUMMARYUIBEAN,gsrb);
		}else if (sessionScope.get(SESS_REPORTSUMMARYUIBEAN)==null){
			GemtSummaryReportUIBean gsrb=new GemtSummaryReportUIBean();
			gsrb.setGemt_sum_empemail(user.getUserID());

			populateReport(gsrb);
			
			try {
				gsrb.setGemt_sum_empname(user.getCommonName());
			} catch (Exception e){
				e.printStackTrace();
			}
					
			gsrb.setGemt_sum_period(getCurrentPeriod());
			
			sessionScope.put(SESS_REPORTSUMMARYUIBEAN,gsrb);
			
		}
//		System.out.println("RECEIEVED:  "+sessionScope.get(GEMTEntryFormView.SESS_DISPLAYREPORT).toString());
	
	}
	
	private long getCurrentPeriod()
	{
		String activeTab=(String)(getSessionScope().get(GEMTEntryFormView.SESS_ACTIVETAB));
		return Long.valueOf(activeTab.substring(3)).longValue();
	}
	
	public String doCreateRowTestAction() {
		
		Map fieldMap = getFieldMap(FacesContext.getCurrentInstance());
		String formBase = (String)fieldMap.get("formBase");
		GemtSummaryReportUIBean gsrb = 	new GemtSummaryReportUIBean(formBase,fieldMap);

		gsrb.setGemt_sum_period(getCurrentPeriod());
		gsrb.setGemt_sum_status(1);
		
		populateReport(gsrb);

		GemtSummaryReportUIBean currentReport=getSummaryReport();
		
		if(currentReport!=null)
		{
			gsrb.setGemt_sum_mgremail(currentReport.getGemt_sum_mgremail());
			gsrb.setGemt_parent_id(currentReport.getGemt_parent_id());
			gsrb.setGemt_sum_locked(currentReport.getGemt_sum_locked());
			gsrb.setGemt_sum_empname(currentReport.getGemt_sum_empname());
		}
		
		gsrb=GEMTServiceLocator.getInstance().getSummaryReportService().makePersistent(gsrb);
		gsrb.convertHtmlVals();
		sessionScope.put(GEMTEntryFormView.SESS_DISPLAYREPORT,String.valueOf(gsrb.getId()));
		sessionScope.put(SESS_REPORTSUMMARYUIBEAN,gsrb);
		
		return "";
	}
	
	public String doCancelAction() {
		// Type Java code that runs when the component is clicked
		
		// TODO: Return outcome that corresponds to a navigation rule
		return "CANCEL";
	}


	public String doToggleAction() {
		// Type Java code that runs when the component is clicked
		
		// TODO: Return outcome that corresponds to a navigation rule
		// return "CANCEL";
		return "TOGGLE";
	}


	public GemtSummaryReportUIBean getSummaryReport()
	{
		GemtSummaryReportUIBean report=null;
		
		if (sessionScope.get(GEMTEntryFormView.SESS_DISPLAYREPORT)!=null){
			report=GEMTServiceLocator.getInstance()
				.getSummaryReportService().findById(new Long(sessionScope.get(GEMTEntryFormView.SESS_DISPLAYREPORT).toString()));
			report.convertHtmlVals();
			sessionScope.put(SESS_REPORTSUMMARYUIBEAN,report);
		}else if (sessionScope.get(SESS_REPORTSUMMARYUIBEAN)==null){
			report=new GemtSummaryReportUIBean();
			//report.setGemt_sum_empemail(user.getUserID());
			sessionScope.put(SESS_REPORTSUMMARYUIBEAN,report);			
		}
		else if(sessionScope.get(SESS_REPORTSUMMARYUIBEAN)!=null)
		{
			return (GemtSummaryReportUIBean)sessionScope.get(SESS_REPORTSUMMARYUIBEAN);
		}
		
		return report;
	}
	
	public String doRouteDocumentAction()
	{
		String outcome="ROUTE";
		
		return outcome;
	}
	
	public void doRouteDocument(ActionEvent event)
	{
		Map fieldMap = getFieldMap(FacesContext.getCurrentInstance());
		String formBase = (String)fieldMap.get("formBase");
		GemtSummaryReportUIBean report = 	new GemtSummaryReportUIBean(formBase,fieldMap);
		
		populateReport(report);
		
		routeDocument(report);
	}
	
	public void doRouteSavedReport(ActionEvent event)
	{		
		routeDocument(getSummaryReport());
	}
	
	private void routeDocument(GemtSummaryReportUIBean report)
	{
		GemtSummaryReportUIBean currentReport=getSummaryReport();
		
		if(currentReport!=null)
		{
			report.setGemt_sum_empname(currentReport.getGemt_sum_empname());
		}		
		
		if(portletMode.equals("1"))
		{
			report.setGemt_sum_locked("FINALIZED");
			
			if(currentReport!=null)
			{
				report.setGemt_parent_id(currentReport.getGemt_parent_id());
			}				
		}
		else
		{
			report.setGemt_sum_locked("LOCKED");
		}
		
		GEMTServiceLocator.getInstance()
		.getSummaryReportService().makePersistent(report);		
		
		GemtSummaryReportUIBean parentReport=GemtSmmaryReportBuilder.clone(report);
		
		if(portletMode.equals("2"))
		{
			report.setGemt_parent_id(report.getId());
			report.setId(0);
			report.setGemt_sum_locked("FOR_REVIEW");
			
			GEMTServiceLocator.getInstance()
			.getSummaryReportService().makePersistent(report);
			
			sendEmail(report,"Test message", "This is a simple test of email");
		}
		
		report.convertHtmlVals();
		
		sessionScope.put(SESS_REPORTSUMMARYUIBEAN,parentReport);
		sessionScope.put(GEMTEntryFormView.SESS_DISPLAYREPORT,String.valueOf(parentReport.getId()));		
	}	
	private void sendEmail(GemtSummaryReportUIBean report,String subject, String message)
	{
		EmailSender email=new EmailSender(EmailConfig.getEmailHost(),
				EmailConfig.getEmailUser(),
				EmailConfig.getEmailPassword(),
				EmailConfig.getEmailUser(),
				report.getGemt_sum_empname(),
				report.getGemt_sum_mgremail(),
				report.getGemt_sum_managername(),
				subject,
				message);
		
		Thread thread=new Thread(email);
		thread.start();
	}
	
	public String getRouteButtonName()
	{
		String name="Route to Manager";
		
		if(portletMode.equals("1"))
		{
			name="Finalize";
		}
		
		return name;
	}
	
	public boolean isNotFinalized()
	{
		boolean result=true;
		
		if(getSummaryReport()==null)
		{
			return true;
		}
		
		if(portletMode.equals("2"))
		{
			if(getSummaryReport().getGemt_sum_locked()!=null )
			{
				result=false;
			}			
		}
		else if(portletMode.equals("1"))
		{
			if(getSummaryReport().getGemt_sum_locked()!=null && getSummaryReport().getGemt_sum_locked().equals("FINALIZED"))
			{
				result=false;
			}
		}
		
		return result;
		
	}
	
	private void populateReport(GemtSummaryReportUIBean report)
	{
		GemtDirectReportUIBean directReport=GEMTServiceLocator.getInstance().getDirectReportService().findByEmployeeEmail(report.getGemt_sum_empemail());
		
		if(directReport!=null)
		{
			report.setGemt_sum_mgremail(directReport.getGemt_sum_mgremail());
			report.setGemt_sum_empname(directReport.getGemt_sum_empname());
					
			GemtManagersUIBean manager=GEMTServiceLocator.getInstance().getManagersService().findByEmail(report.getGemt_sum_mgremail());
			
			report.setGemt_sum_managername(manager.getGemt_sum_managername());
		}
		else
		{
			report.setGemt_sum_mgremail("UNASSIGNED");
			report.setGemt_sum_managername("UNASSIGNED");
		}		
	}

	public String removeFileAction()
	{
		return "";
	}
	
	public void removeFile(ActionEvent event)
	{
		String commandId=event.getComponent().getId();
		
		if(commandId.equals("removeFile"))
		{
            UIColumn column = (UIColumn) event.getComponent().getParent();

            UIData table = (UIData) column.getParent();

            GemtSummaryReportFileLiteUIBean row = (GemtSummaryReportFileLiteUIBean) table.getRowData();
            
            Long fileId=new Long(row.getId());
            
            GemtSummaryReportFileLiteService service =  GEMTServiceLocator.getInstance().getGemtSummaryReportFileLiteService();
    		
    		log.debug("About to delete file with fileId="+fileId);
    		
    		service.delete(fileId);

		}
	}
	
	public List getFiles()
	{
		GemtSummaryReportFileLiteService service =  GEMTServiceLocator.getInstance().getGemtSummaryReportFileLiteService();
		
	    long reportId=((GemtSummaryReportUIBean)sessionScope.get(GEMTEntryFormReportEdit.SESS_REPORTSUMMARYUIBEAN)).getId();

		return service.findAllByReportId(reportId); 
	}
		
	public List getCommonFiles()
	{
		GemtSummaryReportFileLiteService service =  GEMTServiceLocator.getInstance().getGemtSummaryReportFileLiteService();
		
	    long reportId=((GemtSummaryReportUIBean)sessionScope.get(GEMTEntryFormReportEdit.SESS_REPORTSUMMARYUIBEAN)).getId();

	    log.debug("Report ID="+reportId);
	    
	    if(reportId==0)
	    {
	    	return null;
	    }
	    
		return service.findCommonFilesByReportId(reportId); 
	}
	
	public String getFileURL() {
		return fileURL;
	}
	
	protected HtmlCommandExButton getTyleClas() {
		if (tyleClas == null) {
			tyleClas = (HtmlCommandExButton) findComponentInRoot("tyleClas");
		}
		return tyleClas;
	}


	protected HtmlCommandExButton getStyleClas() {
		if (styleClas == null) {
			styleClas = (HtmlCommandExButton) findComponentInRoot("styleClas");
		}
		return styleClas;
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


	protected HtmlCommandExButton getCancel() {
		if (cancel == null) {
			cancel = (HtmlCommandExButton) findComponentInRoot("cancel");
		}
		return cancel;
	}


	protected HtmlScriptCollector getScriptCollector1() {
		if (scriptCollector1 == null) {
			scriptCollector1 = (HtmlScriptCollector) findComponentInRoot("scriptCollector1");
		}
		return scriptCollector1;
	}


	protected HtmlCommandExButton getRoute() {
		if (route == null) {
			route = (HtmlCommandExButton) findComponentInRoot("route");
		}
		return route;
	}


	protected HtmlCommandExButton getToggle() {
		if (toggle == null) {
			toggle = (HtmlCommandExButton) findComponentInRoot("toggle");
		}
		return toggle;
	}

	protected HtmlCommandExButton getSaveReport() {
		if (saveReport == null) {
			saveReport = (HtmlCommandExButton) findComponentInRoot("saveReport");
		}
		return saveReport;
	}

	protected HtmlOutputText getTextevalueeName() {
		if (textevalueeName == null) {
			textevalueeName = (HtmlOutputText) findComponentInRoot("textevalueeName");
		}
		return textevalueeName;
	}

	protected HtmlOutputText getGemt_sum_empname() {
		if (gemt_sum_empname == null) {
			gemt_sum_empname = (HtmlOutputText) findComponentInRoot("gemt_sum_empname");
		}
		return gemt_sum_empname;
	}

	protected HtmlOutputText getTextevalueeTitle() {
		if (textevalueeTitle == null) {
			textevalueeTitle = (HtmlOutputText) findComponentInRoot("textevalueeTitle");
		}
		return textevalueeTitle;
	}

	protected HtmlInputText getGemt_sum_emptitle() {
		if (gemt_sum_emptitle == null) {
			gemt_sum_emptitle = (HtmlInputText) findComponentInRoot("gemt_sum_emptitle");
		}
		return gemt_sum_emptitle;
	}

	protected HtmlOutputText getTextevalDate() {
		if (textevalDate == null) {
			textevalDate = (HtmlOutputText) findComponentInRoot("textevalDate");
		}
		return textevalDate;
	}

	protected HtmlInputText getGemt_sum_repdate() {
		if (gemt_sum_repdate == null) {
			gemt_sum_repdate = (HtmlInputText) findComponentInRoot("gemt_sum_repdate");
		}
		return gemt_sum_repdate;
	}

	protected HtmlOutputText getTextManager() {
		if (textManager == null) {
			textManager = (HtmlOutputText) findComponentInRoot("textManager");
		}
		return textManager;
	}

	protected HtmlOutputText getGemt_sum_managername() {
		if (gemt_sum_managername == null) {
			gemt_sum_managername = (HtmlOutputText) findComponentInRoot("gemt_sum_managername");
		}
		return gemt_sum_managername;
	}

	protected HtmlOutputText getTextselectperiod() {
		if (textselectperiod == null) {
			textselectperiod = (HtmlOutputText) findComponentInRoot("textselectperiod");
		}
		return textselectperiod;
	}

	protected HtmlOutputText getTextLeadershipRole() {
		if (textLeadershipRole == null) {
			textLeadershipRole = (HtmlOutputText) findComponentInRoot("textLeadershipRole");
		}
		return textLeadershipRole;
	}

	protected HtmlOutputText getTextUnsat() {
		if (textUnsat == null) {
			textUnsat = (HtmlOutputText) findComponentInRoot("textUnsat");
		}
		return textUnsat;
	}

	protected HtmlOutputText getTextDeveloping() {
		if (textDeveloping == null) {
			textDeveloping = (HtmlOutputText) findComponentInRoot("textDeveloping");
		}
		return textDeveloping;
	}

	protected HtmlOutputText getTextProficient() {
		if (textProficient == null) {
			textProficient = (HtmlOutputText) findComponentInRoot("textProficient");
		}
		return textProficient;
	}

	protected HtmlOutputText getTextOutstanding() {
		if (textOutstanding == null) {
			textOutstanding = (HtmlOutputText) findComponentInRoot("textOutstanding");
		}
		return textOutstanding;
	}

	protected HtmlOutputText getTextClient() {
		if (textClient == null) {
			textClient = (HtmlOutputText) findComponentInRoot("textClient");
		}
		return textClient;
	}

	protected HtmlOutputText getTextPeople() {
		if (textPeople == null) {
			textPeople = (HtmlOutputText) findComponentInRoot("textPeople");
		}
		return textPeople;
	}

	protected HtmlOutputText getTextThought() {
		if (textThought == null) {
			textThought = (HtmlOutputText) findComponentInRoot("textThought");
		}
		return textThought;
	}

	protected HtmlOutputText getTextDay2Day() {
		if (textDay2Day == null) {
			textDay2Day = (HtmlOutputText) findComponentInRoot("textDay2Day");
		}
		return textDay2Day;
	}

	protected HtmlOutputText getTextBelowThresh() {
		if (textBelowThresh == null) {
			textBelowThresh = (HtmlOutputText) findComponentInRoot("textBelowThresh");
		}
		return textBelowThresh;
	}

	protected HtmlOutputText getTextThresh() {
		if (textThresh == null) {
			textThresh = (HtmlOutputText) findComponentInRoot("textThresh");
		}
		return textThresh;
	}

	protected HtmlOutputText getTextTarget() {
		if (textTarget == null) {
			textTarget = (HtmlOutputText) findComponentInRoot("textTarget");
		}
		return textTarget;
	}

	protected HtmlOutputText getTextOutstanding2() {
		if (textOutstanding2 == null) {
			textOutstanding2 = (HtmlOutputText) findComponentInRoot("textOutstanding2");
		}
		return textOutstanding2;
	}

	protected HtmlOutputText getTextScoreCardFinancial() {
		if (textScoreCardFinancial == null) {
			textScoreCardFinancial = (HtmlOutputText) findComponentInRoot("textScoreCardFinancial");
		}
		return textScoreCardFinancial;
	}

	protected HtmlOutputText getTextScoreCardKPI() {
		if (textScoreCardKPI == null) {
			textScoreCardKPI = (HtmlOutputText) findComponentInRoot("textScoreCardKPI");
		}
		return textScoreCardKPI;
	}

	protected HtmlOutputText getTextOverall() {
		if (textOverall == null) {
			textOverall = (HtmlOutputText) findComponentInRoot("textOverall");
		}
		return textOverall;
	}

	protected HtmlOutputText getTextUnsat2() {
		if (textUnsat2 == null) {
			textUnsat2 = (HtmlOutputText) findComponentInRoot("textUnsat2");
		}
		return textUnsat2;
	}

	protected HtmlOutputText getTextDev2() {
		if (textDev2 == null) {
			textDev2 = (HtmlOutputText) findComponentInRoot("textDev2");
		}
		return textDev2;
	}

	protected HtmlOutputText getTextProf2() {
		if (textProf2 == null) {
			textProf2 = (HtmlOutputText) findComponentInRoot("textProf2");
		}
		return textProf2;
	}

	protected HtmlOutputText getTextOut2() {
		if (textOut2 == null) {
			textOut2 = (HtmlOutputText) findComponentInRoot("textOut2");
		}
		return textOut2;
	}

	protected HtmlOutputText getTextresultsOverview() {
		if (textresultsOverview == null) {
			textresultsOverview = (HtmlOutputText) findComponentInRoot("textresultsOverview");
		}
		return textresultsOverview;
	}

	protected HtmlOutputText getTextComments() {
		if (textComments == null) {
			textComments = (HtmlOutputText) findComponentInRoot("textComments");
		}
		return textComments;
	}

	protected HtmlOutputText getTextIndividualDev() {
		if (textIndividualDev == null) {
			textIndividualDev = (HtmlOutputText) findComponentInRoot("textIndividualDev");
		}
		return textIndividualDev;
	}

	protected HtmlOutputText getTextDevStrengths() {
		if (textDevStrengths == null) {
			textDevStrengths = (HtmlOutputText) findComponentInRoot("textDevStrengths");
		}
		return textDevStrengths;
	}

	protected HtmlOutputText getTextDevNeeds() {
		if (textDevNeeds == null) {
			textDevNeeds = (HtmlOutputText) findComponentInRoot("textDevNeeds");
		}
		return textDevNeeds;
	}

	protected HtmlOutputText getTextDevActions() {
		if (textDevActions == null) {
			textDevActions = (HtmlOutputText) findComponentInRoot("textDevActions");
		}
		return textDevActions;
	}

	protected HtmlOutputText getTextResultsOverview() {
		if (textResultsOverview == null) {
			textResultsOverview = (HtmlOutputText) findComponentInRoot("textResultsOverview");
		}
		return textResultsOverview;
	}

	protected HtmlOutputText getTextExpectations() {
		if (textExpectations == null) {
			textExpectations = (HtmlOutputText) findComponentInRoot("textExpectations");
		}
		return textExpectations;
	}

	protected HtmlOutputLinkEx getViewFileLink() {
		if (viewFileLink == null) {
			viewFileLink = (HtmlOutputLinkEx) findComponentInRoot("viewFileLink");
		}
		return viewFileLink;
	}

	protected HtmlCommandLink getRemoveFile() {
		if (removeFile == null) {
			removeFile = (HtmlCommandLink) findComponentInRoot("removeFile");
		}
		return removeFile;
	}

	protected HtmlCommandExButton getCancel2() {
		if (cancel2 == null) {
			cancel2 = (HtmlCommandExButton) findComponentInRoot("cancel2");
		}
		return cancel2;
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

	protected HtmlCommandExButton getRoute2() {
		if (route2 == null) {
			route2 = (HtmlCommandExButton) findComponentInRoot("route2");
		}
		return route2;
	}

	protected HtmlCommandExButton getToggle2() {
		if (toggle2 == null) {
			toggle2 = (HtmlCommandExButton) findComponentInRoot("toggle2");
		}
		return toggle2;
	}

	protected HtmlCommandExButton getSaveReport2() {
		if (saveReport2 == null) {
			saveReport2 = (HtmlCommandExButton) findComponentInRoot("saveReport2");
		}
		return saveReport2;
	}

	protected HtmlInputText getId() {
		if (id == null) {
			id = (HtmlInputText) findComponentInRoot("id");
		}
		return id;
	}

	protected HtmlInputText getGemt_sum_empemail() {
		if (gemt_sum_empemail == null) {
			gemt_sum_empemail = (HtmlInputText) findComponentInRoot("gemt_sum_empemail");
		}
		return gemt_sum_empemail;
	}

	protected HtmlOutputText getText1() {
		if (text1 == null) {
			text1 = (HtmlOutputText) findComponentInRoot("text1");
		}
		return text1;
	}

	protected HtmlOutputText getTextPerfHighs() {
		if (textPerfHighs == null) {
			textPerfHighs = (HtmlOutputText) findComponentInRoot("textPerfHighs");
		}
		return textPerfHighs;
	}

	protected HtmlOutputText getTextMissedOpps() {
		if (textMissedOpps == null) {
			textMissedOpps = (HtmlOutputText) findComponentInRoot("textMissedOpps");
		}
		return textMissedOpps;
	}

	protected HtmlForm getFilesUploading() {
		if (filesUploading == null) {
			filesUploading = (HtmlForm) findComponentInRoot("filesUploading");
		}
		return filesUploading;
	}

	protected HtmlCommandExButton getUploadFile() {
		if (uploadFile == null) {
			uploadFile = (HtmlCommandExButton) findComponentInRoot("uploadFile");
		}
		return uploadFile;
	}
}