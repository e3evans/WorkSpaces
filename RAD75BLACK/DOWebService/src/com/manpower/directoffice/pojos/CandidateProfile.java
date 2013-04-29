package com.manpower.directoffice.pojos;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class CandidateProfile {
	static final boolean USE_TEST_DATA = false;
	
	
	// TODO: Externalze all of the DO code lists
	static final String YES = "8262736";
	static final String NO = "8262737";
	
	static final String USA = "USA";
	static final String CANADA = "CAN";
    static public final String BOCOUNTRY_CANADA = "8262733";
    static public final String BOCOUNTRY_USA = "8262732";
    static public final String BOCOUNTRY_GERMANY = "8263059";
    static public final String BOCOUNTRY_TAIWAN = "8262738";
    
	String countryID;								// country specific
	String payAddressDifferent;
	Address payrollAddress;							// What is your payroll address ?
	String backOfficeIDNumber="";							// country specific
	String acceptDirectDepositByEmail;				// DONE country specific
	String citizen;									// DONE country specific
	String legallyEntiltedToWork;					// country specific
	String hasGovernmentID;						// DONE country specific
	String governmentID;							// 
	String hasWorkVisa;								// country specific
	Date workVisaExpirationDate;					// country specific	
	String criminalConvictions;						// DONE Have you ever had a criminal conviction ? Yes / No	
	String isWillingToTakeDrugTest;
	String eighteenOrOlder;
	String countryCode;	
	String businessUnit;
	String workVisaString;
	
	public String getWorkVisaExpirationString(){
		String temp="";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		if (getWorkVisaExpirationDate()!=null){
			temp =  formatter.format(getWorkVisaExpirationDate());
		}
//		System.out.println("DATE DATE DATE" + dob);
		return temp;
	}
	
	
	public String getBusinessUnit() {
		return businessUnit;
	}
	public void setBusinessUnit(String bu){
		this.businessUnit = bu;
	}
	public String isPayAddressDifferentThanMain() {
//		System.out.println("GETTINGN DIFFERENT:  "+payAddressDifferent);
		return payAddressDifferent;
	}
	
	// valid values are "Y" or "N"
	public void setIsPayAddressDifferentThanMain(String different) {
		this.payAddressDifferent=different;
	}
	
	public Address getPayrollAddress() {
		if (this.payrollAddress==null) {
			this.payrollAddress = new Address();
		}
		return payrollAddress;
	}

	public void setPayrollAddress(Address payrollAddress) {
		this.payrollAddress = payrollAddress;
	}

	public String getBackOfficeIDNumber() {
		
		if (countryID!=null){
			if (countryID.equals(USA)) return "12345";
			if (countryID.equals(CANADA))return "67890";
		}
		return backOfficeIDNumber;
	}
	
	public void setBackOfficeIDNumber(String backOfficeIDNumber) {
		this.backOfficeIDNumber = backOfficeIDNumber;
	}	
	
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	
	public String getCountryCode() {
		return this.countryCode;
	}




	public String isWillingToTakeDrugTest() {
		if (USE_TEST_DATA) {
			if (countryID.equals(USA)) return "Y";
			if (countryID.equals(CANADA))return "N";
		}
		return this.isWillingToTakeDrugTest;
	}
	
	public void setIsWillingToTakeDrugTest(String isWillingToTakeDrugTest){
		
		this.isWillingToTakeDrugTest = isWillingToTakeDrugTest;
	}
	
	// This method is to be used from within the EntityXMLProcessor only when retrieving 
	// data from Direct Office
	public void setFromServiceWillingToTakeDrugTest(String isWillingToTakeDrugTest) {
		if (isWillingToTakeDrugTest.equals(YES)) {
			this.isWillingToTakeDrugTest = "Y";
		}else if (isWillingToTakeDrugTest.equals(NO)) {
			this.isWillingToTakeDrugTest = "N";
		}else {
			this.isWillingToTakeDrugTest = isWillingToTakeDrugTest;
		}
	}
	
	
	
	public void setIsEighteenOrOlder(String older){
//		System.out.println("CALLING BAD SET!!!--->"+older);
		this.eighteenOrOlder = older;
	}
	// This method is to be used from within the EntityXMLProcessor only when retrieving 
	// data from Direct Office
	public void setFromWebserviceIsEighteenOrOlder(String older) {
//		System.out.println("SETTING FROM WS EO:  "+older);
		if (older.equals(YES)) this.eighteenOrOlder = "Y";
		if (older.equals(NO)) this.eighteenOrOlder = "N";
//		System.out.println("VALUE SET FROM WS:  "+eighteenOrOlder);
	}
	public String isEighteenOrOlder() {
		return this.eighteenOrOlder;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	public String acceptDirectDepositByEmail() {
		if (USE_TEST_DATA) {
			if (countryID.equals(USA)) return "Y";
			if (countryID.equals(CANADA))return "N";
		}
		return this.acceptDirectDepositByEmail;
	}
	public void setFromWebserviceAcceptDirectDepositByEmail(String acceptDirectDepositByEmail){
		if (acceptDirectDepositByEmail.equals(YES)) this.acceptDirectDepositByEmail="Y";
		if (acceptDirectDepositByEmail.equals(NO)) this.acceptDirectDepositByEmail="N";
	}
	//To be used by UI
	public void setAcceptDirectDepositByEmail(String acceptDirectDepositByEmail) {
		this.acceptDirectDepositByEmail = acceptDirectDepositByEmail;
	}
	///////////////////////////////////////////////////////////////////////////////////
	
	
	//////////////////////////////////////////////////////////////////////////////////
	// CITIZEN
	public String isCitizen() {
		return citizen;
	}
	public void setCitizen(String citizen) {
		this.citizen=citizen;
	}
	public void setFromWebServiceCitizen(String citizen) {
		if (citizen.equals(YES)) this.citizen="Y";
		if (citizen.equals(NO)) this.citizen="N";
	}
	///////////////////////////////////////////////////////////////////////////////////
	
	
	///////////////////////////////////////////////////////////////////////////////////
	// LEGALLY ENTITLED TO WORK
	public String isLegallyEntiltedToWork() {
	
		return this.legallyEntiltedToWork;
	}
	
	public void setLegallyEntiltedToWork(String legallyEntiltedToWork) {
//		System.out.println("LEGALLY ENTITLED---->"+legallyEntiltedToWork);
//		if (legallyEntiltedToWork.equals(YES)||legallyEntiltedToWork.equals("Yes")) this.legallyEntiltedToWork = "Y";
//		if (legallyEntiltedToWork.equals(NO)||legallyEntiltedToWork.equals("No")) this.legallyEntiltedToWork = "N";
		this.legallyEntiltedToWork=legallyEntiltedToWork;
	}
	
	public void setFromWebServiceLegallyEntiltedToWork (String legallyEntitledToWork) {
		
		if (legallyEntitledToWork.equals(YES)) this.legallyEntiltedToWork = "Y";
		if (legallyEntitledToWork.equals(NO)) this.legallyEntiltedToWork = "N";
	}
	///////////////////////////////////////////////////////////////////////////////////


	///////////////////////////////////////////////////////////////////////////////////
	//HAS GOVERNMENT ID
	public String hasGovernmentID() {
		return hasGovernmentID;
	}

	public void setHaveGovermentID (String hasGovernmentID) {
		this.hasGovernmentID=hasGovernmentID;
	}

	public void setFromWebServiceHaveGovernmentID(String hasGovernmentID) {
		
		if (hasGovernmentID.equals(YES)) this.hasGovernmentID = "Y";
		if (hasGovernmentID.equals(NO)) this.hasGovernmentID = "N";
	}
	///////////////////////////////////////////////////////////////////////////////////

	
	///////////////////////////////////////////////////////////////////////////////////
	// HAS WORK VISA
	public String hasWorkVisa() {
		if (USE_TEST_DATA) {
			if (countryID.equals(USA)) return "N";
			if (countryID.equals(CANADA))return "Y";
		}
		return this.hasWorkVisa;
	}
	
	public void setHasWorkVisa(String hasWorkVisa) {
		this.hasWorkVisa = hasWorkVisa;
	}

	public void setFromWebServiceHasWorkVisa(String hasWorkVisa){
		if (hasWorkVisa.equals(YES)) this.hasWorkVisa = "Y";
		if (hasWorkVisa.equals(NO)) this.hasWorkVisa = "N";
	}
	///////////////////////////////////////////////////////////////////////////////////
	
	///////////////////////////////////////////////////////////////////////////////////
	//WORK VISA EXPRY DATE
	public Date getWorkVisaExpirationDate(){
//		if (USE_TEST_DATA){
//			String pattern = "yyyy/MM/dd";
//		    SimpleDateFormat format = new SimpleDateFormat(pattern);
//		    Date date = format.parse("12/31/1973");
//		}

			
//			System.out.println("VISA EXPIRATION DATE:  "+workVisaExpirationDate);
			return workVisaExpirationDate;
	}
	
	public String getWorkVisaExpirationDateXMLValue(){
		String formattedDate = "";
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		if (getWorkVisaExpirationDate()!=null){
			formattedDate = format.format(getWorkVisaExpirationDate());
		}
		
		return formattedDate;
		
	}
	
	public void setWorkVisaExpirationDate(Date date){
		this.workVisaExpirationDate = date;
	}
		public void setWorkVisaExpirationDate(String visaDate) {
			
			if (visaDate.equals("")) return;
			
			String pattern = "MM/dd/yy";
		    SimpleDateFormat format = new SimpleDateFormat(pattern);
		    Date date = null;
			if (visaDate.length()== 0) return;
		    try {
				date = format.parse(visaDate);
			} catch (java.text.ParseException e) {
				 // TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.workVisaExpirationDate = date;
		}
	///////////////////////////////////////////////////////////////////////////////////
		
	///////////////////////////////////////////////////////////////////////////////////
	//HAS CRIMINAL CONVICTION
	public String hasCriminalConvictions() {
		if (USE_TEST_DATA) return "Y";
		return this.criminalConvictions;
	}

	public void setCriminalConvictions(String criminalConvictions) {
		this.criminalConvictions = criminalConvictions;
	}

	public void setFromWebServiceCriminalConvictions(String criminalConvictions) {
//		System.out.println("SETTING CRIMINAL CONVICTIONS!!!"+criminalConvictions);
		if (criminalConvictions.equals(YES) || criminalConvictions.equals("Y")) this.criminalConvictions = "Y";
		if (criminalConvictions.equals(NO) || criminalConvictions.equals("N")) this.criminalConvictions = "N";
	}
	///////////////////////////////////////////////////////////////////////////////////

	
	private HashMap<Object, Object> fieldNames = new HashMap<Object, Object>();
	public HashMap<Object,Object> getFields(){
		
		fieldNames.put("countryID", ""); //NO GETTER
		fieldNames.put("payAddressDifferent", isPayAddressDifferentThanMain()); 
		fieldNames.put("payrollAddress", getPayrollAddress());
		fieldNames.put("backOfficeIDNumber", getBackOfficeIDNumber());
		fieldNames.put("acceptDirectDepositByEmail", acceptDirectDepositByEmail()); 
		fieldNames.put("citizen", isCitizen());
		fieldNames.put("legallyEntiltedToWork", isLegallyEntiltedToWork());
		fieldNames.put("hasGovernmentID", hasGovernmentID());
		fieldNames.put("governmentID",""); //NO GETTER
		fieldNames.put("hasWorkVisa", hasWorkVisa());
		fieldNames.put("workVisaExpirationDate", getWorkVisaExpirationDate());
	
		fieldNames.put("criminalConvictions", hasCriminalConvictions());
		fieldNames.put("isWillingToTakeDrugTest", isWillingToTakeDrugTest());
		fieldNames.put("eighteenOrOlder", isEighteenOrOlder());
		fieldNames.put("countryCode", getCountryCode());
		fieldNames.put("businessUnit", getBusinessUnit());
		fieldNames.put("workVisaExpirationString", getWorkVisaExpirationString());

		return fieldNames;
	}
	
	
}
