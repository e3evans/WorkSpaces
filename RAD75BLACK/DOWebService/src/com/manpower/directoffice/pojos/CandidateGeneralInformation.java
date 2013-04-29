package com.manpower.directoffice.pojos;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;

import com.manpower.directoffice.codelists.CodeListHandler;


public class CandidateGeneralInformation {
	static final boolean USE_TEST_DATA = false;
	static final String YES = "8262736";
	static final String NO = "8262737 ";
	
	private HashMap<Object, Object> fieldNames = new HashMap<Object, Object>();
	String doEntityID;					// direct office entity id
	String dtCandidateID;				// direct talent candidate id
	String dtUserID;					// direct talent user id
	
	Boolean errorRetrievingInfo;
	String firstName;					//extracted
	String gender;						// TODO: populate gender
	String emergencyContactName;		//extracted
	String emergencyContactPhone;		//extracted
	String emergencyContactNotes;	
	Address mainAddress = new Address();//extracted					
	String title;						// extracted
	String lastName;					//extracted
	String knownAs;						// extracted	
	Date dateOfBirth;					//extracted	
	String dateOfBirthString;
	
	boolean willingToTakeDrugTest;	
	String hasAccess_USA = NO;
	String hasAccess_Canada = NO;
	String resetPassword;				// extracted
	String password;					// extracted
	String[] boIDs = new String[10];	// back office id's that are specific to DO.
	int numberOfBackOfficeProfiles = 0;	// the number of back office profiles this candidate has.
	
	public String getDateOfBirthString() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dob =  formatter.format(dateOfBirth);
//		System.out.println("DATE DATE DATE" + dob);
		return dob;
	}
	
	public void setGender (String gender){
		this.gender = gender;
	}
	
	public String getGender () {
		return gender;
	}
	public void setDOEntityID (String entityID) {
		this.doEntityID = entityID;
	}
	
	public String getDOEntityID () {
		return doEntityID;
	}

	public void setNumberOfBackOfficeProfiles (int count) {
		numberOfBackOfficeProfiles=count;
	}
	
	public int getNumberOfBackOfficeProfiles () {
		return numberOfBackOfficeProfiles;
	}
	
	public String[] getBoIDs() {
		return boIDs;
	}
	
	public String getResetPassword() {
		return resetPassword;
	}

	public void setResetPassword(String resetPassword) {
		this.resetPassword = resetPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public boolean hasAccess_USA() {
		return hasAccess_USA.equals(YES);
	}

	public void setHasAccess_USA(String hasAccess_USA) {
		this.hasAccess_USA = hasAccess_USA;
	}

	public boolean hasAccess_Canada() {
		
		return hasAccess_Canada.equals(YES);
		
	}

	public void setHasAccess_Canada(String hasAccess_Canada) {
		this.hasAccess_Canada = hasAccess_Canada;
	}

	public Address getMainAddress() {
		if (USE_TEST_DATA)return Address.getTestMainAddressData();
		
		return mainAddress;
	}
	
	public void setMainAddress(Address mainAddress) {
		this.mainAddress = mainAddress;
	}
	
	@SuppressWarnings("unchecked")
	public String getTitle() {
		if (USE_TEST_DATA)return "Professor";
		Hashtable list = CodeListHandler.getTitleList();
		String title = (String) list.get(this.title);
		if (title == null)return "";
		return title;
		
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getLastName() {
		if (USE_TEST_DATA)return "Stanton";
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getKnownAs() {
		if (USE_TEST_DATA)return "Tony";
		return knownAs;
	}
	
	public void setKnownAs(String knownAs) {
		this.knownAs = knownAs;
	}
	
	public Date getDateOfBirth() throws java.text.ParseException {
	if (USE_TEST_DATA){
		String pattern = "yyyy-MM-dd";
	    SimpleDateFormat format = new SimpleDateFormat(pattern);
	    Date date = format.parse("12/31/1973");
	}
		return dateOfBirth;
	}
	
	public void setDateOfBirth(String dateOfBirth) {
		//System.out.println("SETTING DOB IN CGI:  "+dateOfBirth);
		String pattern = "dd/MM/yy";
	    SimpleDateFormat format = new SimpleDateFormat(pattern);
	    Date date = null;
		try {
			date = format.parse(dateOfBirth);
		} catch (java.text.ParseException e) {
			 // TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.dateOfBirth = date;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	
	public boolean isWillingToTakeDrugTest() {
		if (USE_TEST_DATA)return false;
		return willingToTakeDrugTest;
	}
	
	public void setWillingToTakeDrugTest(boolean willingToTakeDrugTest) {
		this.willingToTakeDrugTest = willingToTakeDrugTest;
	}
		
	
	public String getFirstName() {
		if (USE_TEST_DATA)return "Anthony";
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public Boolean getErrorRetrievingInfo() {
		return errorRetrievingInfo;
	}

	public void setErrorRetrievingInfo(Boolean errorRetrievingInfo) {
		this.errorRetrievingInfo = errorRetrievingInfo;
	}

	public String getEmergencyContactName() {
		if (USE_TEST_DATA)return "Jim Johnson";

		return emergencyContactName;
	}

	public void setEmergencyContactName(String emergencyContactName) {
		this.emergencyContactName = emergencyContactName;
	}
	public String getEmergencyContactPhone() {
		if (USE_TEST_DATA)return "+1 888 225 3369";
		return emergencyContactPhone;
	}
	
	public void setEmergencyContactPhone(String emergencyContactPhone) {
		this.emergencyContactPhone = emergencyContactPhone;
	}
	
	public String getEmergencyContactNotes() {
		if (USE_TEST_DATA)return "Jim is an ER nurse and can also be reached at United Hospital Systems";
		return emergencyContactNotes;
	}
	
	public void setEmergencyContactNotes(String emergencyContactNotes) {
		this.emergencyContactNotes = emergencyContactNotes;
	}

	public HashMap<Object,Object> getFields(){
		fieldNames.put("doEntityID",getDOEntityID());		
		fieldNames.put("dtCandidateID",""); //NO METHOD FOR SETTING OR RETRIEVING THIS..
		fieldNames.put("dtUserID",""); //NO METHOD FOR SETTING OR RETRIEVING THIS..
//		fieldNames.put("errorRetrievingInfo",getErrorRetrievingInfo().toString());
		fieldNames.put("firstName",getFirstName());
//		fieldNames.put("middleName", )
		fieldNames.put("gender",getGender());
		fieldNames.put("emergencyContactName",getEmergencyContactName());
		fieldNames.put("emergencyContactPhone",getEmergencyContactPhone());
		fieldNames.put("mainAddress",getMainAddress()); // Address Object
		fieldNames.put("title",getTitle());
		fieldNames.put("lastName",getLastName());
		
		fieldNames.put("knownAs",getKnownAs());
		try {
//			System.out.println("DOB:  "+getDateOfBirth());
			fieldNames.put("dateOfBirth",formatDate(getDateOfBirth()));
		} catch (ParseException e) {
			fieldNames.put("dateOfBirth", "INVALID DATA"); 
		} 
		fieldNames.put("dateOfBirthString",getDateOfBirthString());
		fieldNames.put("willingToTakeDrugTest",Boolean.toString(isWillingToTakeDrugTest()));
		fieldNames.put("hasAccess_USA",hasAccess_USA());
		fieldNames.put("hasAccess_Canada",hasAccess_Canada());
		fieldNames.put("resetPassword",getResetPassword());
		fieldNames.put("password",getPassword());
		fieldNames.put("boIDs",getBoIDs()); //String []
		fieldNames.put("numberOfBackOfficeProfiles", Integer.toString(getNumberOfBackOfficeProfiles()));
		return fieldNames;
		
	}
	
	private String formatDate(Date date){
		 String DATE_FORMAT = "yyyy-MM-dd";
		 SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		 return sdf.format(date);
		
	}
	
}
