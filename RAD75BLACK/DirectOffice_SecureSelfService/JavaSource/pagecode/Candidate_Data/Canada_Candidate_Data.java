package pagecode.Candidate_Data;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import pagecode.PageCodeBase;
import utils.SSSConstants;

import com.manpower.directoffice.api.DOCandidateHandler;
import com.manpower.directoffice.pojos.CandidateEntity;
import com.manpower.directoffice.pojos.CandidateGeneralInformation;
import com.manpower.directoffice.pojos.CandidateProfile;
import com.manpower.directoffice.xml.exception.CanNotAccessDirectOfficeException;

public class Canada_Candidate_Data extends PageCodeBase{
	
	private static final String YES_CONST = "yes";

	private static final String NO_CONST = "no";

	private static final String BLANK_CONST = "blank";

	private static Logger logger = Logger.getLogger(Canada_Candidate_Data.class);
	
	private String title;
	private String firstName;
	private String lastName;
	private String knownAs;
	private String mailingAddressOne;
	private String mailingAddressTwo;
	private String mailingCity;
	private String mailingRegion;
	private String mailingPostCode;
	private String mailingCountry;
	private String legalAge;
	private String usaCitizen;
	private String legallyEntitledToWork;
	private String haveGovernmentId;
	private String haveWorkVisa;
	private Date workVisaExpirationDate;
	private String haveCriminalConvictions;
	private String willingToTakeDrugTest;
	private Date dateOfBirth;
	private String acceptDirectDepositByEmail;
	private String paymentAddrDiffFromMainAddr;
	private String mainAddressOne;
	private String mainAddressTwo;
	private String mainCity;
	private String mainRegion;
	private String mainPostCode;
	private String mainCountry;
	
	private String boIdNumber;
	
	private String eighteenYearsOldorOlder;
	
	private String value;
	private CandidateProfile candidateProfile;
	private CandidateGeneralInformation candidateGeneralInformation;
	
	private boolean isFormValid;
	
	
	ResourceBundle rb = null;
	
	public Canada_Candidate_Data()
	{
		rb = getResourceBundle(getCountryBundleName());
		candidateProfile = getCandidateProfile();
		candidateGeneralInformation = getGeneralInformation();
		
	}
	
	public String getCountryBundleName()
	{
		String countryBundleName="application.messages.nl.USA_ApplicationMessages";
		
		return countryBundleName;
	}
	
	public List getSelectItems()
	{
		List items = new ArrayList();
		String labelYes = rb.getString(SSSConstants.YES);
		SelectItem selectYes = new SelectItem(new Boolean(true), labelYes);
		String labelNo = rb.getString(SSSConstants.NO);
		SelectItem selectNo = new SelectItem(new Boolean(false), labelNo);
		
		items.add(selectYes);
		items.add(selectNo);
		
		return items;
	}
	
	public List getWorkSelectItems()
	{
		List items = new ArrayList();
		String labelYes = rb.getString(SSSConstants.YES);
		SelectItem selectYes = new SelectItem(rb.getString(SSSConstants.YES), labelYes);
		String labelNo = rb.getString(SSSConstants.NO);
		SelectItem selectNo = new SelectItem(rb.getString(SSSConstants.NO), labelNo);
		SelectItem blankAnswer = new SelectItem(BLANK_CONST, "");
		
		items.add(blankAnswer);
		items.add(selectYes);
		items.add(selectNo);
		
		return items;
	}
	
	public List getWorkVisaSelectItems()
	{
		List items = new ArrayList();
		String labelYes = rb.getString(SSSConstants.YES);
		SelectItem selectYes = new SelectItem("true", labelYes);
		String labelNo = rb.getString(SSSConstants.NO);
		SelectItem selectNo = new SelectItem("false", labelNo);
		SelectItem blankAnswer = new SelectItem(SSSConstants.BLANK_CONST, "");
		
		items.add(blankAnswer);
		items.add(selectYes);
		items.add(selectNo);
		
		return items;
	}
	
	public List getRegionSelectItems()
	{
		List regionSelectItems = new ArrayList();
		List canadaRegions = getConfigPropertyValueAsList(SSSConstants.CANADA_REGIONS);
		
		if(canadaRegions != null)
		{
			for(int i = 0; i < canadaRegions.size(); i++)
			{
				SelectItem regionItem = null;
				if(i == 0)
				{
					regionItem = new SelectItem("", (String)canadaRegions.get(i));
				}else{
					regionItem = new SelectItem(canadaRegions.get(i), (String)canadaRegions.get(i));
				}
				
				regionSelectItems.add(regionItem);
			}
		}else{
			logger.error("The list of regions is NULL");
		}
		
		return regionSelectItems;
	}
	
	public CandidateGeneralInformation getGeneralInformation()
	{
		if(candidateGeneralInformation == null)
		{
			loadGeneralInformation();
		}
		
		return candidateGeneralInformation;
	}
	
	public void setGeneralInformation(CandidateGeneralInformation general)
	{
		sessionScope.put(SSSConstants.CAN_GEN_PROFILE_KEY, general);
		this.candidateGeneralInformation = general;
	}
	
	private void loadGeneralInformation()
	{
		
		CandidateGeneralInformation general = (CandidateGeneralInformation)sessionScope.get(SSSConstants.CAN_GEN_PROFILE_KEY);
		
		if(general == null)
		{
			logger.debug("There is not a general information in session scope");
			long candidateId = getCandidateId();
			CandidateEntity entity = null;
			
			try {
				entity = DOCandidateHandler.getDirectOfficeCandidate(Long.toString(candidateId));
			} catch (CanNotAccessDirectOfficeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			general = entity.getCandidateGeneralInformation();
		}
		setGeneralInformation(general);
	}
	
	public CandidateProfile getCandidateProfile()
	{
		if(candidateProfile == null)
		{
			loadCandidateProfile();
		}
		
		return candidateProfile;
	}
	
	private void loadCandidateProfile()
	{
		CandidateProfile profile = (CandidateProfile)sessionScope.get(SSSConstants.CAN_CANDIDATE_PROFILE_KEY);
		
		if(profile == null)
		{
			logger.debug("There is not candidate profile in session scope. Get it from the Handler for Canada");
			long dtcid = getCandidateId();
			
			CandidateEntity entity = null;
			try {
				entity = DOCandidateHandler.getDirectOfficeCandidate(Long.toString(dtcid));
			} catch (CanNotAccessDirectOfficeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			profile = entity.getCandidateDetailsForCountry(SSSConstants.CANADA);
		}
		setCandidateProfile(profile);
		
	}
	
	public void setCandidateProfile(CandidateProfile candidateProfile)
	{
		sessionScope.put(SSSConstants.CAN_CANDIDATE_PROFILE_KEY, candidateProfile);
		this.candidateProfile = candidateProfile;
	}
	
	private void validate()
	{
		CandidateGeneralInformation general = getGeneralInformation();
		if(!isStringInputValid(general.getTitle()))
		{
			isFormValid = false;
		}
		if(!isStringInputValid(general.getMainAddress().getAddressLine1()))
		{
			isFormValid = false;
		}
	}
	
	public String getValue()
	{
		return value;
	}
	public void setValue(String value)
	{
		this.value = value;
	}
	
	public void submitData(ActionEvent event)
	{
		
		CandidateProfile candidateProfile = getCandidateProfile();
		
		// save candidate's data
		long dtcid = getCandidateId();
		
		CandidateEntity entity = null;
		try {
			entity = DOCandidateHandler.getDirectOfficeCandidate(Long.toString(dtcid));
		} catch (CanNotAccessDirectOfficeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		candidateProfile = entity.getCandidateDetailsForCountry(SSSConstants.CANADA);
		
		// show confirmation message that data is saved
		sessionScope.put(SSSConstants.SHOW_CONFIRM_MSG_ON_UPDATE, new Boolean(true));
		
		setCandidateProfile(candidateProfile);
		
	}
	
	public String submit()
	{
		return "";
	}
	
	public void cancel(ActionEvent event)
	{
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
	
	
	public String getDateOfBirth() {
		
		logger.debug("Canada_Candidate_Data.getDateOfBirth - ENTRY");
		Date birthDate = null;
		try {
			birthDate = candidateGeneralInformation.getDateOfBirth();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.debug("Could not parse the date of birth");
		}
		String birthDateValue = null;
		if(birthDate != null)
		{
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			birthDateValue = formatter.format(birthDate);
		}
		
		logger.debug("Canada_Candidate_Data.getDateOfBirth - EXIT");
		return birthDateValue;
	}
	public void setDateOfBirth(String dateOfBirth) {
		
		logger.debug("Canada_Candidate_Data.setDateOfBirth - ENTRY");
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date birthDate = null;
		try {
			if(dateOfBirth != null && dateOfBirth.length() > 0)
			{
				birthDate = formatter.parse(dateOfBirth);
			}
			
		} catch (ParseException e) {
			logger.error("Error while parsing date", e);
		}
		
		candidateGeneralInformation.setDateOfBirth(birthDate);
		
		this.dateOfBirth = birthDate;
		logger.debug("Canada_Candidate_Data.setDateOfBirth - EXIT");
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getKnownAs() {
		return knownAs;
	}
	public void setKnownAs(String knownAs) {
		this.knownAs = knownAs;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getMailingAddressOne() {
		return mailingAddressOne;
	}
	public void setMailingAddressOne(String mailingAddressOne) {
		this.mailingAddressOne = mailingAddressOne;
	}
	public String getMailingAddressTwo() {
		return mailingAddressTwo;
	}
	public void setMailingAddressTwo(String mailingAddressTwo) {
		this.mailingAddressTwo = mailingAddressTwo;
	}
	public String getMailingCity() {
		return mailingCity;
	}
	public void setMailingCity(String mailingCity) {
		this.mailingCity = mailingCity;
	}
	public String getMailingCountry() {
		return mailingCountry;
	}
	public void setMailingCountry(String mailingCountry) {
		this.mailingCountry = mailingCountry;
	}
	public String getMailingPostCode() {
		return mailingPostCode;
	}
	public void setMailingPostCode(String mailingPostCode) {
		this.mailingPostCode = mailingPostCode;
	}
	public String getMailingRegion() {
		return mailingRegion;
	}
	public void setMailingRegion(String mailingRegion) {
		this.mailingRegion = mailingRegion;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getWorkVisaExpirationDate() {
		
		logger.debug("Canada_Candidate_Data.getWorkVisaExpirationDate - ENTRY");
		Date visaExpDate = null;
		try {
			visaExpDate = candidateProfile.getWorkVisaExpirationDate();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.debug("Could not parse the work visa expiration date");
		}
		String visaExpDateValue = null;
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		if(visaExpDate != null)
		{
			visaExpDateValue = formatter.format(visaExpDate);
		}
		
		logger.debug("Canada_Candidate_Data.getWorkVisaExpirationDate - EXIT");
		return visaExpDateValue;
	}
	
	public void setWorkVisaExpirationDate(String workVisaExpirationDate) {
		
		logger.debug("Canada_Candidate_Data.setWorkVisaExpirationDate - ENTRY");
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date formattedDate = null;
		try {
			if(workVisaExpirationDate != null && workVisaExpirationDate.length() > 0)
			{
				formattedDate = formatter.parse(workVisaExpirationDate);
			}
			
		} catch (ParseException e) {
			logger.error("Error while parsing date", e);
		}
		
		CandidateProfile candidateProfile = getCandidateProfile();
		
		candidateProfile.setWorkVisaExpirationDate(formattedDate.toString());
		this.workVisaExpirationDate = formattedDate;
		logger.debug("Canada_Candidate_Data.setWorkVisaExpirationDate - EXIT");
	}
	public String getBoIdNumber() {
		return boIdNumber;
	}
	public void setBoIdNumber(String boIdNumber) {
		this.boIdNumber = boIdNumber;
	}
	public String getMainAddressOne() {
		return mainAddressOne;
	}
	public void setMainAddressOne(String mainAddressOne) {
		this.mainAddressOne = mainAddressOne;
	}
	public String getMainAddressTwo() {
		return mainAddressTwo;
	}
	public void setMainAddressTwo(String mainAddressTwo) {
		this.mainAddressTwo = mainAddressTwo;
	}
	public String getMainCity() {
		return mainCity;
	}
	public void setMainCity(String mainCity) {
		this.mainCity = mainCity;
	}
	public String getMainCountry() {
		return mainCountry;
	}
	public void setMainCountry(String mainCountry) {
		this.mainCountry = mainCountry;
	}
	public String getMainPostCode() {
		return mainPostCode;
	}
	public void setMainPostCode(String mainPostCode) {
		this.mainPostCode = mainPostCode;
	}
	public String getMainRegion() {
		return mainRegion;
	}
	public void setMainRegion(String mainRegion) {
		this.mainRegion = mainRegion;
	}

	public String getAcceptDirectDepositByEmail() {
		return acceptDirectDepositByEmail;
	}

	public void setAcceptDirectDepositByEmail(String acceptDirectDepositByEmail) {
		this.acceptDirectDepositByEmail = acceptDirectDepositByEmail;
	}

	public String getHaveCriminalConvictions() {
		
/*	if(!candidateProfile.isCandidateAnsweredCriminalQuest())
		{
			return "";
		}
*/		
		if (candidateProfile.hasCriminalConvictions().equals("Yes")) {
			return rb.getString(SSSConstants.YES);
		} else if (candidateProfile.hasCriminalConvictions().equals("Yes")) {
			return rb.getString(rb.getString(SSSConstants.NO));
		}
		
		return "";
	}

	public void setHaveCriminalConvictions(String haveCriminalConvictions) {
		
		if(haveCriminalConvictions.equalsIgnoreCase(rb.getString(SSSConstants.YES)))
		{
			candidateProfile.setCriminalConvictions("Yes");
		}
		
		if(haveCriminalConvictions.equalsIgnoreCase(rb.getString(SSSConstants.NO)))
		{	
			candidateProfile.setCriminalConvictions("No");
		}
		
	}

	public String getHaveGovernmentId() {
/*		if(!candidateProfile.isCandidatePointedAboutGovernmentId())
		{
			return "";
		}
*/
		boolean hasGovernmentID = candidateProfile.hasGovernmentID().equals("Yes");
		if (hasGovernmentID){
			return rb.getString(SSSConstants.YES);
		}
		return rb.getString(rb.getString(SSSConstants.NO));
	}

	public void setHaveGovernmentId(String haveGovernmentId) {
		
		if(haveGovernmentId.equals(rb.getString(SSSConstants.YES)))
		{
			//candidateProfile.setCandidatePointedAboutGovernmentId(true);
			candidateProfile.setHaveGovermentID("Yes");
		}
		
		if(haveGovernmentId.equals(rb.getString(SSSConstants.NO)))
		{
			//candidateProfile.setCandidatePointedAboutGovernmentId(true);
			candidateProfile.setHaveGovermentID("No");
		}
		
	}

	public String getHaveWorkVisa() {
/*		if(!candidateProfile.isCandidatePointedAboutWorkVisa())
		{
			return "";
		}
*/
		if(candidateProfile.hasWorkVisa().equals("Yes"))
		{
			return rb.getString(SSSConstants.YES);
		}
		
		return rb.getString(SSSConstants.NO);
	}

	public void setHaveWorkVisa(String haveWorkVisa) {
		
		if(haveWorkVisa.equals(rb.getString(SSSConstants.YES)))
		{
//			candidateProfile.setCandidatePointedAboutWorkVisa(true);
			candidateProfile.setHasWorkVisa("Yes");
		}
		
		if(haveWorkVisa.equals(rb.getString(SSSConstants.NO)))
		{
//			candidateProfile.setCandidatePointedAboutWorkVisa(true);
			candidateProfile.setHasWorkVisa("No");
		}
		
	}

	public String getLegalAge() {
		return legalAge;
	}

	public void setLegalAge(String legalAge) {
		this.legalAge = legalAge;
	}

	public String getLegallyEntitledToWork() {
		
/*		if(!candidateProfile.isCandidatePointedWhetherEntitled())
		{
			return "";
		}
*/
		if(candidateProfile.isLegallyEntiltedToWork().equals("Yes"))
		{
			return rb.getString(SSSConstants.YES);
		}
		
		return rb.getString(SSSConstants.NO);
	}

	public void setLegallyEntitledToWork(String legallyEntitledToWork) {
		
		if(legallyEntitledToWork.equals(rb.getString(SSSConstants.YES)))
		{
//			candidateProfile.setCandidatePointedWhetherEntitled(true);
			candidateProfile.setLegallyEntiltedToWork("Yes");
		}
		
		if(legallyEntitledToWork.equals(rb.getString(SSSConstants.NO)))
		{
//			candidateProfile.setCandidatePointedWhetherEntitled(true);
			candidateProfile.setLegallyEntiltedToWork("No");
		}
		
	}

	public String getUsaCitizen() {
		return usaCitizen;
	}

	public void setUsaCitizen(String usaCitizen) {
		this.usaCitizen = usaCitizen;
	}

	public String getWillingToTakeDrugTest() {
		return willingToTakeDrugTest;
	}

	public void setWillingToTakeDrugTest(String willingToTakeDrugTest) {
		this.willingToTakeDrugTest = willingToTakeDrugTest;
	}

	public String getPaymentAddrDiffFromMainAddr() {
		return paymentAddrDiffFromMainAddr;
	}

	public void setPaymentAddrDiffFromMainAddr(String paymentAddrDiffFromMainAddr) {
		this.paymentAddrDiffFromMainAddr = paymentAddrDiffFromMainAddr;
	}

	public String getEighteenYearsOldorOlder() {
/*		if(!candidateGeneralInformation.isCandidatePointedAboutAge())
		{
			return "";
		}
*/
		if(candidateProfile.isEighteenOrOlder().equals("Yes"))
		{
			return rb.getString(SSSConstants.YES);
		}
		
		return rb.getString(SSSConstants.NO);
	}

	public void setEighteenYearsOldorOlder(String eighteenYearsOldorOlder) {
		
		if(eighteenYearsOldorOlder.equals(rb.getString(SSSConstants.YES)))
		{
//			candidateGeneralInformation.setCandidatePointedAboutAge(true);
			candidateProfile.setIsEighteenOrOlder("Yes");
		}
		if(eighteenYearsOldorOlder.equals(rb.getString(SSSConstants.NO)))
		{
//			candidateGeneralInformation.setCandidatePointedAboutAge(true);
			candidateProfile.setIsEighteenOrOlder("No");
		}
		
	}
}
