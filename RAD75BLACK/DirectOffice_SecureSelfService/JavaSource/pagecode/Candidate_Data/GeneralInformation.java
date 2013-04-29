package pagecode.Candidate_Data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import pagecode.PageCodeBase;
import utils.SSSConstants;

import com.manpower.directoffice.api.DOCandidateHandler;
import com.manpower.directoffice.pojos.CandidateEntity;
import com.manpower.directoffice.pojos.CandidateGeneralInformation;
import com.manpower.directoffice.xml.exception.CanNotAccessDirectOfficeException;

public class GeneralInformation extends PageCodeBase{

	private static Logger logger = Logger.getLogger(GeneralInformation.class);
	private String candidateName;
	private String emergencyContactName;
	private String emergencyContactPhone;
	private String emergencyContactNotes;
	
	private boolean showConfirmationMsg;
	
	private boolean showWelcomeMessage;
	
	private boolean usaTabVisible;
	private boolean canadaTabVisible;
	
	private CandidateGeneralInformation candidateGeneralInformation;
	
	
	
	public GeneralInformation()
	{
		loadGeneralInformation();
				
		if(candidateGeneralInformation != null)
		{
			String candidateName = candidateGeneralInformation.getFirstName() + " " + candidateGeneralInformation.getLastName();
			logger.debug("candidateGeneralInformation.getCandidateName()=" + candidateName);
			setCandidateName(candidateName);
			logger.debug("candidateGeneralInformation.getEmergencyContactName()=" + candidateGeneralInformation.getEmergencyContactName());
			setEmergencyContactName(candidateGeneralInformation.getEmergencyContactName());
			setEmergencyContactPhone(candidateGeneralInformation.getEmergencyContactPhone());
			setEmergencyContactNotes(candidateGeneralInformation.getEmergencyContactNotes());
			
			setShowWelcomeMessage(true);
			
		}else{
			logger.debug("setShowWelcomeMessage to false");
			setShowWelcomeMessage(false);
		}
		
		PortletRequest portletRequest = (PortletRequest)facesContext.getExternalContext().getRequest();
		String canadaVisible = portletRequest.getProperty(SSSConstants.CANADA_VISIBLE);
		String usaVisible = portletRequest.getProperty(SSSConstants.USA_VISIBLE);
		// temporarily
		if(canadaVisible == null)
		{
			canadaVisible = "true";
		}
		if(usaVisible == null)
		{
			usaVisible = "true";
		}
		
		setUsaTabVisible(new Boolean(usaVisible).booleanValue());
		setCanadaTabVisible(new Boolean(canadaVisible).booleanValue());
	}
	
	
	private void loadGeneralInformation()
	{
		candidateGeneralInformation = (CandidateGeneralInformation)sessionScope.
																get(SSSConstants.GENERAL_INFO_SESSION_KEY);
		if(candidateGeneralInformation == null)
		{
			long candidateId = getCandidateId();
			
			CandidateEntity entity = null;
			try {
				entity = DOCandidateHandler.getDirectOfficeCandidate(Long.toString(candidateId));
			} catch (CanNotAccessDirectOfficeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			candidateGeneralInformation = entity.getCandidateGeneralInformation();
		}
	}
	
	public boolean isHasAccess_USA()
	{
		//long candidateId = getCandidateId();
		return candidateGeneralInformation.hasAccess_USA();
		
	}
	
	public boolean isHasAccess_Canada()
	{
		//long candidateId = getCandidateId();
		return candidateGeneralInformation.hasAccess_Canada();
	}
	
	public String getCountryBundleName()
	{
		String countryBundleName="application.messages.nl.USA_ApplicationMessages";
		
		return countryBundleName;
	}

	public String getEmergencyContactName() {
		return emergencyContactName;
	}

	public void setEmergencyContactName(String emergencyContactName) {
		this.emergencyContactName = emergencyContactName;
	}

	public String getEmergencyContactNotes() {
		return emergencyContactNotes;
	}

	public void setEmergencyContactNotes(String emergencyContactNotes) {
		this.emergencyContactNotes = emergencyContactNotes;
	}

	public String getEmergencyContactPhone() {
		return emergencyContactPhone;
	}

	public void setEmergencyContactPhone(String emergencyContactPhone) {
		this.emergencyContactPhone = emergencyContactPhone;
	}

	public String getCandidateName() {
		logger.debug("candidateName=" + candidateName);
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public boolean isShowWelcomeMessage() {
		return showWelcomeMessage;
	}

	public void setShowWelcomeMessage(boolean showWelcomeMessage) {
		this.showWelcomeMessage = showWelcomeMessage;
	}

	public boolean isShowConfirmationMsg() {
		
		logger.debug("GeneralInformation.isShowConfirmationMsg - ENTRY");
		Boolean temp = (Boolean)sessionScope.get(SSSConstants.SHOW_CONFIRM_MSG_ON_UPDATE);
		if(temp != null)
		{
			logger.debug("temp.booleanValue() = " + temp.booleanValue());
			logger.debug("GeneralInformation.isShowConfirmationMsg - EXIT");
			return temp.booleanValue();
		}else{
			setShowConfirmationMsg(false);
		}
		
		logger.debug("showConfirmationMsg = " + showConfirmationMsg);
		logger.debug("GeneralInformation.isShowConfirmationMsg - EXIT");
		return showConfirmationMsg;
	}

	public void setShowConfirmationMsg(boolean showConfirmationMsg) {
		
		sessionScope.put(SSSConstants.SHOW_CONFIRM_MSG_ON_UPDATE, new Boolean(showConfirmationMsg));
		this.showConfirmationMsg = showConfirmationMsg;
	}



	public boolean isCanadaTabVisible() {
		
		Boolean temp = (Boolean)sessionScope.get(SSSConstants.CANADA_VISIBLE);
		if(temp == null)
		{
			setCanadaTabVisible(false);
		}else{
			setCanadaTabVisible(temp.booleanValue());
		}
		
		return canadaTabVisible;
	}
	
	public void back(ActionEvent event)
	{
		// hide the confirmation message data is saved
		sessionScope.remove(SSSConstants.SHOW_CONFIRM_MSG_ON_UPDATE);
		String redirectToConstant = getStringValue("cancel.return.to");
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest httpServletRequest = (HttpServletRequest)externalContext.getRequest();
		String redirectURL = setRedirectURL(httpServletRequest, redirectToConstant);
		logger.debug("redirectURL = " + redirectURL);
		try {
			externalContext.redirect(redirectURL);
		} catch (IOException e) {
			logger.error("Exception while redirecting", e);
		}
	}
	
	public void submitData(ActionEvent event)
	{
		logger.debug("GeneralInformation.submitData - ENTRY");
		// hide the confirmation message data is saved
		sessionScope.remove(SSSConstants.SHOW_CONFIRM_MSG_ON_UPDATE);
		redirectToPortlet(SSSConstants.MY_MANPOWER);
		logger.debug("GeneralInformation.submitData - EXIT");
	}

	public void setCanadaTabVisible(boolean canadaTabVisible) {
		
		sessionScope.put(SSSConstants.CANADA_VISIBLE, new Boolean(canadaTabVisible));
		this.canadaTabVisible = canadaTabVisible;
	}



	public boolean isUsaTabVisible() {
		
		Boolean temp = (Boolean)sessionScope.get(SSSConstants.USA_VISIBLE);
		if(temp == null)
		{
			setUsaTabVisible(false);
		}else{
			setUsaTabVisible(temp.booleanValue());
		}
		
		return usaTabVisible;
	}



	public void setUsaTabVisible(boolean usaTabVisible) {
		sessionScope.put(SSSConstants.USA_VISIBLE, new Boolean(usaTabVisible));
		this.usaTabVisible = usaTabVisible;
	}

	
}
