/*
 * Created on Jan 24, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pagecode.GEMTEntryForm;

import javax.faces.context.FacesContext;
import pagecode.PageCodeBase;
import com.manpower.portal.gemt.ui.beans.GemtSummaryReportUIBean;
import com.manpower.portal.gemt.ui.services.GEMTServiceLocator;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.faces.component.html.HtmlForm;
import com.ibm.faces.component.html.HtmlGraphicImageEx;
import com.ibm.faces.component.html.HtmlCommandExButton;
import javax.faces.component.html.HtmlOutputText;
import com.ibm.faces.component.html.HtmlScriptCollector;
import com.ibm.faces.component.html.HtmlOutputLinkEx;
import javax.faces.component.html.HtmlInputText;
/**
 * @author Eric Evans
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GEMTEntryFormReportView extends PageCodeBase {

	public static String SESS_REPORTSUMMARYUIBEAN = "GemtSummaryReportUIBean";
	public static String SESS_FORM_MODE = "GetmSummaryReportFormMode";
	private String portletMode;
	protected HtmlForm form1;
	protected HtmlGraphicImageEx imageEx2;
	protected HtmlGraphicImageEx imageEx1;
	protected HtmlCommandExButton cancel;
	protected HtmlOutputText textevalueeName;
	protected HtmlOutputText gemt_sum_empname;
	protected HtmlOutputText textevalueeTitle;
	protected HtmlOutputText gemt_sum_emptitle;
	protected HtmlOutputText textevalDate;
	protected HtmlOutputText gemt_sum_repdate;
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
	protected HtmlOutputText textResultsOverview;
	protected HtmlOutputText textBelowThresh;
	protected HtmlOutputText textThresh;
	protected HtmlOutputText textTarget;
	protected HtmlOutputText textOutstanding2;
	protected HtmlOutputText textScoreCardFinancial;
	protected HtmlOutputText textScoreCardKPI;
	protected HtmlOutputText textPerfHighs;
	protected HtmlOutputText textMissedOpps;
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
	protected HtmlOutputText textEmpAck;
	protected HtmlOutputText textEmpSig;
	protected HtmlOutputText textSigDate;
	protected HtmlCommandExButton cancel2;
	protected HtmlGraphicImageEx imageEx3;
	protected HtmlGraphicImageEx imageEx4;
	protected HtmlScriptCollector scriptCollector1;
	protected HtmlCommandExButton editReport;
	protected HtmlCommandExButton showParentReport;
	protected HtmlOutputLinkEx viewFileLink;
	protected HtmlCommandExButton editReport2;
	protected HtmlInputText id;
	protected HtmlCommandExButton pdfReport;
	protected HtmlCommandExButton pdfReport1;
	protected HtmlCommandExButton route1;
	protected HtmlOutputText textDevPriorities;
	public GEMTEntryFormReportView()
	{
		PortletRequest portletRequest = (PortletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		
		PortletPreferences preferences=portletRequest.getPreferences();
		this.portletMode=preferences.getValue(GEMTEntryFormEdit.PREF_MGREMPMODE, "0");
	}
	
	public void onPageLoadBegin(FacesContext facescontext) {

		/**
		 * USED TO GET THE USER AND HIS MAPPED ATTRIBUTES
		 * DO NOT ERASE!!!!
		 */
//		try {	
//			UserManager um = new UserManager(facescontext);
////			User user = um.getCurrentUser();
////			System.out.println(user.toString());
//			Map temp = um.getCurrentUserAttributes();
//			System.out.println(temp.get("cn"));
//		} catch (PortletServiceUnavailableException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NamingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		GemtSummaryReportHbnBean gsrhb = (GemtSummaryReportHbnBean)DAOFactory.getDAOFactory().getGemtSummaryReportDAO().findByID(new Long("7"));
//		GemtSummaryReportUIBean gsrb = new GemtSummaryReportUIBean();
//		try{
//			BeanUtils.copyProperties(gsrb,gsrhb);
//			gsrb.convertHtmlVals();
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		
//		sessionScope.put(SESS_REPORTSUMMARYUIBEAN,gsrb);
//		sessionScope.put(SESS_FORM_MODE,"edit");
//		HttpServletRequest hReq = (HttpServletRequest) facescontext.getExternalContext().getRequest();
//		HttpSession hSession = hReq.getSession();
//		hSession.setAttribute(SESS_FORM_MODE,"edit");
		
//		System.out.println(StringUtils.replace(gsrb.getGemt_sum_comments(),"&lt;","<"));
		
		if (sessionScope.get(GEMTEntryFormView.SESS_DISPLAYREPORT)!=null){
			GemtSummaryReportUIBean gsrb =GEMTServiceLocator.getInstance()
				.getSummaryReportService().findById(new Long(sessionScope.get(GEMTEntryFormView.SESS_DISPLAYREPORT).toString()));
			gsrb.convertHtmlVals();
			sessionScope.put(SESS_REPORTSUMMARYUIBEAN,gsrb);
		}else{
			//TEST CONDITIONS!!!
			GemtSummaryReportUIBean gsrb =GEMTServiceLocator.getInstance()
			.getSummaryReportService().findById(new Long("7"));
			gsrb.convertHtmlVals();
			
		}
		System.out.println("RECEIEVED:  "+sessionScope.get(GEMTEntryFormView.SESS_DISPLAYREPORT).toString());
	
	
	}
	
	public String doCancelAction() {
		// Type Java code that runs when the component is clicked
		
		// TODO: Return outcome that corresponds to a navigation rule
		return "CANCEL";
	}


	public String doEditReportAction() {	
		
		return "EDIT_REPORT";
	}

	public boolean isEditButtonShown()
	{
		boolean outcome=true;
		
		if(portletMode.equals("1"))
		{
			return outcome;
		}
		
		if(getSummaryReport().getGemt_sum_locked()!=null)
		{
			outcome=false;
		}
		
		return outcome;
	}
	
	public boolean isOriginalReportAvailabe()
	{
		boolean result=false;
	
		if (sessionScope.get(GEMTEntryFormView.SESS_DISPLAYREPORT)!=null){
			GemtSummaryReportUIBean gsrb =GEMTServiceLocator.getInstance()
			.getSummaryReportService().findParentReport(new Long(sessionScope.get(GEMTEntryFormView.SESS_DISPLAYREPORT).toString()));
			
			if(gsrb!=null)
			{
				result=true;
			}
		}
		
		return result;
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
		
		return report;
	}
	
	
	public String doShowParentReportAction()
	{
		String outcome="SHOW_PARENT_REPORT";
		
		return outcome;
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

	protected HtmlOutputText getGemt_sum_emptitle() {
		if (gemt_sum_emptitle == null) {
			gemt_sum_emptitle = (HtmlOutputText) findComponentInRoot("gemt_sum_emptitle");
		}
		return gemt_sum_emptitle;
	}

	protected HtmlOutputText getTextevalDate() {
		if (textevalDate == null) {
			textevalDate = (HtmlOutputText) findComponentInRoot("textevalDate");
		}
		return textevalDate;
	}

	protected HtmlOutputText getGemt_sum_repdate() {
		if (gemt_sum_repdate == null) {
			gemt_sum_repdate = (HtmlOutputText) findComponentInRoot("gemt_sum_repdate");
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

	protected HtmlOutputText getTextResultsOverview() {
		if (textResultsOverview == null) {
			textResultsOverview = (HtmlOutputText) findComponentInRoot("textResultsOverview");
		}
		return textResultsOverview;
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

	protected HtmlOutputText getTextEmpAck() {
		if (textEmpAck == null) {
			textEmpAck = (HtmlOutputText) findComponentInRoot("textEmpAck");
		}
		return textEmpAck;
	}

	protected HtmlOutputText getTextEmpSig() {
		if (textEmpSig == null) {
			textEmpSig = (HtmlOutputText) findComponentInRoot("textEmpSig");
		}
		return textEmpSig;
	}

	protected HtmlOutputText getTextSigDate() {
		if (textSigDate == null) {
			textSigDate = (HtmlOutputText) findComponentInRoot("textSigDate");
		}
		return textSigDate;
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

	protected HtmlScriptCollector getScriptCollector1() {
		if (scriptCollector1 == null) {
			scriptCollector1 = (HtmlScriptCollector) findComponentInRoot("scriptCollector1");
		}
		return scriptCollector1;
	}

	protected HtmlCommandExButton getEditReport() {
		if (editReport == null) {
			editReport = (HtmlCommandExButton) findComponentInRoot("editReport");
		}
		return editReport;
	}

	protected HtmlCommandExButton getShowParentReport() {
		if (showParentReport == null) {
			showParentReport = (HtmlCommandExButton) findComponentInRoot("showParentReport");
		}
		return showParentReport;
	}

	protected HtmlOutputLinkEx getViewFileLink() {
		if (viewFileLink == null) {
			viewFileLink = (HtmlOutputLinkEx) findComponentInRoot("viewFileLink");
		}
		return viewFileLink;
	}

	protected HtmlCommandExButton getEditReport2() {
		if (editReport2 == null) {
			editReport2 = (HtmlCommandExButton) findComponentInRoot("editReport2");
		}
		return editReport2;
	}

	protected HtmlInputText getId() {
		if (id == null) {
			id = (HtmlInputText) findComponentInRoot("id");
		}
		return id;
	}

	protected HtmlCommandExButton getPdfReport() {
		if (pdfReport == null) {
			pdfReport = (HtmlCommandExButton) findComponentInRoot("pdfReport");
		}
		return pdfReport;
	}

	protected HtmlCommandExButton getPdfReport1() {
		if (pdfReport1 == null) {
			pdfReport1 = (HtmlCommandExButton) findComponentInRoot("pdfReport1");
		}
		return pdfReport1;
	}

	protected HtmlCommandExButton getRoute1() {
		if (route1 == null) {
			route1 = (HtmlCommandExButton) findComponentInRoot("route1");
		}
		return route1;
	}

	protected HtmlOutputText getTextDevPriorities() {
		if (textDevPriorities == null) {
			textDevPriorities = (HtmlOutputText) findComponentInRoot("textDevPriorities");
		}
		return textDevPriorities;
	}
}